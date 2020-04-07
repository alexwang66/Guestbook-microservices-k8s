package org.wangqing.microservices.guestbook.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wangqing.microservices.guestbook.Application;

/** 
* Account Tester. 
* 
* @author <Authors name> 
* @since <pre>ËÄÔÂ 7, 2020</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringApplicationConfiguration(SpringBootApplication.class)
public class AccountTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getId() 
* 
*/ 
@Test
public void testGetId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setId(Integer id) 
* 
*/ 
@Test
public void testSetId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerId() 
* 
*/ 
@Test
public void testGetCustomerId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCustomerId(Integer customerId) 
* 
*/ 
@Test
public void testSetCustomerId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNumber() 
* 
*/ 
@Test
public void testGetNumber() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setNumber(String number) 
* 
*/ 
@Test
public void testSetNumber() throws Exception { 
//TODO: Test goes here... 
} 


} 
