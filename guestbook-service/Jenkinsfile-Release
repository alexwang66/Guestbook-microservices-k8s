node {
    //定义Artifactory server
    def artiServer = Artifactory.server 'arti-platform'
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo = Artifactory.newBuildInfo()
    def descriptor = Artifactory.mavenDescriptor()
    def yapiUrl = 'http://${YAPI_HOST}:3000/api/open/run_auto_test?id=11&token=180de366142562488b9f7a176d5b6579e38eaf981981ca730c105d3335eecbfd&mode=json&email=false&download=false'
    def Version_num = "1.$BUILD_NUMBER"
    //jar包下载路径
    def deploy_path = "/opt/springcloud-app"
    def SONAR_SCANNER_TOOL = 'sonar-scanner'
    def SONAR_SERVER = 'sonar'
    def SONAR_SOURCES = './'
    def SONAR_HOST_URL = 'http://sonar.jfrogchina.com/'
    //ArtifactoryAPIKEY
    def RT_API_KEY = ''

    stage('SCM'){
        cleanWs notFailBuild: true
        git 'https://github.com/mikesun666/Guestbook-microservices-k8s.git'
    }

    stage('Prepare') {
        buildInfo.env.capture = true
        rtMaven.deployer releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: artiServer
        rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: artiServer
        rtMaven.deployer.deployArtifacts = false

        descriptor.setVersion "org.wangqing:guestbook-microservices-k8s", "$Version_num"
        descriptor.setVersion "org.wangqing.guestbook-microservices-k8s:gateway-service", "$Version_num"
        descriptor.setVersion "org.wangqing.guestbook-microservices-k8s:discovery-service", "$Version_num"
        descriptor.setVersion "org.wangqing.guestbook-microservices-k8s:guestbook-service", "$Version_num"
        descriptor.setVersion "org.wangqing.guestbook-microservices-k8s:zipkin-service", "$Version_num"
        descriptor.transform()
    }



    stage('Build'){
        //maven构建使用Artifactory 插件
        rtMaven.tool = 'maven' // Tool name from Jenkins configuration
        rtMaven.run pom: './pom.xml', goals: 'clean org.jacoco:jacoco-maven-plugin:prepare-agent install', buildInfo: buildInfo
        artiServer.publishBuildInfo buildInfo
    }

    stage('SonarScanner') {
        def scannerHome = tool SONAR_SCANNER_TOOL;
        withSonarQubeEnv(SONAR_SERVER) {
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.language=java -Dsonar.projectKey=${JOB_NAME} -Dsonar.java.binaries=* -Dsonar.jacoco.itReportPath=*/target/jacoco.exec -Dsonar.sources=${SONAR_SOURCES} "
        }

    }
    //解析单元测试
    stage('unitest'){
        junit ( testResults: '**/surefire-reports/**/*.xml' )
        def testDatas=manager.build.getAction(hudson.tasks.junit.TestResultAction.class)
        if (testDatas) {
            result = testDatas.result
            JTtotal=result.getTotalCount().toString()
            JTfailed=result.getFailCount().toString()
            JTpassed=result.getPassCount().toString()
            JTskiped=result.getSkipCount().toString()
            passRate=result.getPassCount()/result.getTotalCount()*100
            passRate=passRate.toString()+"%"
        } else {
            JTtotal='0'
            JTfailed='0'
            JTpassed='0'
            JTskiped='0'
            passRate='0'
        }
        print("Total unit test case Number : "+JTtotal)
        print("Pass unit test case Number : "+JTpassed)
        print("Passrate : "+ passRate)
        rtMaven.deployer.addProperty("unittest.summary.total_number", JTtotal)
        rtMaven.deployer.addProperty("unittest.summary.pass_number", JTpassed)
        rtMaven.deployer.addProperty("unittest.summary.pass_rate", passRate)
    }

    stage("Sonar Quality Gate") {
        timeout(time: 1, unit: 'HOURS') {

            sleep 10
                // Just in case something goes wrong, pipeline will be killed after a timeout
            def qg = waitForQualityGate()// Reuse taskId previously collected by withSonarQubeEnv
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            } else {
            //获取sonar扫描结果
                def surl="${SONAR_HOST_URL}/api/measures/component?componentKey=${JOB_NAME}&metricKeys=alert_status,quality_gate_details,coverage,new_coverage,bugs,new_bugs,reliability_rating,vulnerabilities,new_vulnerabilities,security_rating,sqale_rating,sqale_index,sqale_debt_ratio,new_sqale_debt_ratio,duplicated_lines_density&additionalFields=metrics,periods"
                def response=httpRequest consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', ignoreSslErrors: true, url: surl
                    def propssonar = readJSON text: response.content
                if (propssonar.component.measures) {
                    propssonar.component.measures.each{ measure ->
                        def val
                        if (measure.periods){
                            val = measure.periods[0].value
                        }else {
                            val = measure.value
                        }
                        rtMaven.deployer.addProperty("sonar.quality.${measure.metric}", val)
                    }
                }
                //增加sonar扫描结果到artifactory
                rtMaven.deployer.addProperty("qulity.gate.sonarUrl", SONAR_HOST_URL + "/dashboard/index/" + JOB_NAME)
            }
        }
    }
    stage('xray scan') {
        def xrayConfig = [
            'buildName'     : env.JOB_NAME,
            'buildNumber'   : env.BUILD_NUMBER,
            'failBuild'  : false
        ]
        def xrayResults = artiServer.xrayScan xrayConfig
        echo xrayResults as String
        xrayurl = readJSON text:xrayResults.toString()
        echo xrayurl as String
        rtMaven.deployer.addProperty("xrayresult.summary.total_alerts", xrayurl.summary.total_alerts as String)
    }

    stage('deploy'){
        buildInfo.env.capture = true
        rtMaven.deployer.deployArtifacts buildInfo
        sh "$deploy_path/stopAll.sh"
        sh "rm -rf $deploy_path/*.jar*"
        def response = httpRequest contentType: 'APPLICATION_JSON', customHeaders: [[maskValue: true, name: 'X-JFrog-Art-Api', value: RT_API_KEY]], httpMode: 'POST', ignoreSslErrors: true, requestBody: '{"buildName": "'+env.JOB_NAME+'","buildNumber": "'+env.BUILD_NUMBER+'" }', url: 'http://182.92.214.141:8081/artifactory/api/search/buildArtifacts'
        downloadjson = readJSON text: response.content
        for (i in downloadjson.results) {
            if (i.downloadUri.contains("jar")) {
                sh "wget -q --user=admin --password=$RT_API_KEY $i.downloadUri -P $deploy_path"
            }
        }
    }

    stage('Start Service') {
    //脚本中启动Jar包版本号通过变量$Version_num传递
        sh "nohup $deploy_path/runAll.sh $Version_num >>/dev/null 2>&1 &"
    }

    stage('InterfaceTest'){
        def response=httpRequest consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', ignoreSslErrors: true, url: yapiUrl
        def InterfaceTestresult = readJSON text: response.content
        print(InterfaceTestresult.message.msg)
    }
}