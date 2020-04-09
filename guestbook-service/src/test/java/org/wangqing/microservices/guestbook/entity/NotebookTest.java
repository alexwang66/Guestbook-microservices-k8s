package org.wangqing.microservices.guestbook.entity;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringApplicationConfiguration(SpringBootApplication.class)

/**
 * Notebook Tester.
 *
 * @author <Authors name>
 * @since
 * @version 1.0
 */
public class NotebookTest {

    Notebook notebook = new Notebook();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: setId(long id)
     *
     */
    @Test
    public void testSetId() throws Exception {
        int id = 10;
        notebook.setId(id);
        Assert.assertTrue(notebook.getId()==10);
    }

    /**
     *
     * Method: getId()
     *
     */
    @Test
    public void testGetId() throws Exception {
        int id = 50;
        notebook.setId(id);
        Assert.assertTrue(notebook.getId()==50);
    }

//    /**
//     *
//     * Method: setName(String name)
//     *
//     */
//    @Test
//    public void testSetName() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: setEmail(String email)
//     *
//     */
//    @Test
//    public void testSetEmail() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: getName()
//     *
//     */
//    @Test
//    public void testGetName() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: getEmail()
//     *
//     */
//    @Test
//    public void testGetEmail() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: getPhoneNo()
//     *
//     */
//    @Test
//    public void testGetPhoneNo() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     *
//     * Method: setPhoneNo(long phoneNo)
//     *
//     */
//    @Test
//    public void testSetPhoneNo() throws Exception {
////TODO: Test goes here...
//    }


}
