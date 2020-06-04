package org.wangqing.microservices.guestbook.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wangqing.microservices.guestbook.Application;

/**
 * Student Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringApplicationConfiguration(SpringBootApplication.class)
public class StudentTest {

    Student stu = new Student();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
//
//    /**
//     *
//     * Method: getId()
//     *
//     */
//    @Test
//    public void testGetId() throws Exception {
//
//    }
//
//    /**
//     *
//     * Method: setId(Long id)
//     *
//     */
//    @Test
//    public void testSetId() throws Exception {
////TODO: Test goes here...
//    }

    /**
     *
     * Method: getName()
     *
     */
    @Test
    public void testGetName() throws Exception {
       String name = "Mike";
       stu.setName(name);
       Assert.assertTrue(stu.getName().equals(name));
    }

    /**
     *
     * Method: setName(String name)
     *
     */
    @Test
    public void testSetName() throws Exception {
        String name = "AlexWang";
        stu.setName(name);
        Assert.assertTrue(stu.getName().equals(name));
    }

//    /**
//     *
//     * Method: getPassportNumber()
//     *
//     */
//    @Test
//    public void testGetPassportNumber() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: setPassportNumber(String passportNumber)
//     *
//     */
//    @Test
//    public void testSetPassportNumber() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: toString()
//     *
//     */
//    @Test
//    public void testToString() throws Exception {
////TODO: Test goes here...
//    }
//

} 
