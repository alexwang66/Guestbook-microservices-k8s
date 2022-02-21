#  解决问题 - VB安装K8S集群
1. VB虚拟机默认无法连接外网
2. VB虚拟机之间无法联通
3. 笔记本虚机ip不固定，不同网络环境IP地址会自动被分配

#  名词解释
NAT 和Host Only
NAT（Network Address Translation），是指网络地址转换，1994年提出的。
当在专用网内部的一些主机本来已经分配到了本地IP地址（即仅在本专用网内使用的专用地址），但又想和因特网上的主机通信（并不需要加密）时，可使用NAT方法。
这种方法需要在专用网（私网IP）连接到因特网（公网IP）的路由器上安装NAT软件。装有NAT软件的路由器叫做NAT路由器，它至少有一个有效的外部全球IP地址（公网IP地址）。这样，所有使用本地地址（私网IP地址）的主机在和外界通信时，都要在NAT路由器上将其本地地址转换成全球IP地址，才能和因特网连接

Host-only networking can be thought of as a hybrid between the bridged and internal networking modes. As with bridged networking, the virtual machines can talk to each other and the host as if they were connected through a physical Ethernet switch. As with internal networking, a physical networking interface need not be present, and the virtual machines cannot talk to the world outside the host since they are not connected to a physical networking interface.

When host-only networking is used, Oracle VM VirtualBox creates a new software interface on the host which then appears next to your existing network interfaces. In other words, whereas with bridged networking an existing physical interface is used to attach virtual machines to, with host-only networking a new loopback interface is created on the host. And whereas with internal networking, the traffic between the virtual machines cannot be seen, the traffic on the loopback interface on the host can be intercepted.

On Linux, Mac OS X and Solaris Oracle VM VirtualBox will only allow IP addresses in 192.168.56.0/21 range to be assigned to host-only adapters. For IPv6 only link-local addresses are allowed. If other ranges are desired, they can be enabled by creating /etc/vbox/networks.conf and specifying allowed ranges there. For example, to allow 10.0.0.0/8 and 192.168.0.0/16 IPv4 ranges as well as 2001::/64 range put the following lines into /etc/vbox/networks.conf:
      * 10.0.0.0/8 192.168.0.0/16
      * 2001::/64



虚拟机双网卡配置

#  Virtualbox 虚拟机配置双网卡实现固定IP
- 安装 Virtualbox
- 新建 Virtualbox 虚拟机
![image](https://user-images.githubusercontent.com/23203831/154903898-5c914ea4-5f9b-43aa-a053-f960916b39fd.png)


- Virtualbox安装 CentOS
虚拟机需要分配 2 个 CPU Core，用于运行 Kubeadm
![image](https://user-images.githubusercontent.com/23203831/154904017-f76028bd-4f7a-431c-9b31-590c1250ce97.png)

- 下载阿里云 centos7 镜像
http://mirrors.aliyun.com/centos/7/isos/x86_64/CentOS-7-x86_64-DVD-2009.iso
选择下载的.iso 文件作为系统盘，启动虚拟机。

![image](https://user-images.githubusercontent.com/23203831/154904046-3bec8b60-5e66-464b-9d40-87ba98f4481c.png)


- 配置虚机双网卡,实现固定 IP，且能访问外网

查看虚拟机网络，点击‘文件’—>‘主机网络管理器’，选择“手动配置网卡”，修改ip地址为（192.168.56.1）。
![image](https://user-images.githubusercontent.com/23203831/154905132-6aa45898-9912-4066-aa36-d9ec43bea1bc.png)

- 为虚拟机配置网卡
网卡 1： 选择host-only网卡（注意第一个网卡选择 host-only）
![image](https://user-images.githubusercontent.com/23203831/154905101-819f675f-f973-44f5-88c6-d5f186a835af.png)

网卡 2： 选择NAT
![image](https://user-images.githubusercontent.com/23203831/154905172-2758b0fd-bc04-4903-aaf4-63a026704f18.png)




- 重启虚拟机，此时在虚拟机 ping www.baidu.com 是返回成功的。
- 设置外部网络访问虚拟机
设置静态ip地址，编辑网络配置文件，编辑网络设置文件
```
vi /etc/sysconfig/network-scripts/ifcfg-enp0s3
```
修改下面的内容：
```
ONBOOT=yes
BOOTPROTO=static
IPADDR=192.168.56.101
```

其中，ONBOOT=Yes 表示开机启动，BOOTPROTO=static为静态 IP 生效，IP 地址为192.168.56.101。修改完如下所示：
```
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
#BOOTPROTO=dhcp
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=enp0s3
UUID=08012b4a-d6b1-41d9-a34d-e0f52a123e7a
DEVICE=enp0s3
ONBOOT=yes
BOOTPROTO=static
IPADDR=192.168.56.101
```
- 重启网络
```
systemctl restart network
```

- 查看 enp0s3 网卡的 ip
```
[root@localhost Final]#ip addr |grep 192
inet 192.168.56.101/24 brd 192.168.56.255 scope global noprefixroute enp0s3
```
- 此时虚拟机既可以访问外网，也能够和宿主机( 192.168.31.178)进行通信
```
ping 192.168.31.178
PING 192.168.31.178 (192.168.31.178): 56 data bytes
64 bytes from 192.168.31.178: icmp_seq=0 ttl=64 time=0.060 ms
```
- 使用iTerm2 免密登录虚拟机Master
```
ssh-copy-id root@192.168.56.101
ssh 'root@192.168.56.101'
```

- 配置 Master 和 work 节点的域名
vim /etc/hosts
```
 192.168.56.101 master
 192.168.56.102 node1
 192.168.56.103 node2
```
