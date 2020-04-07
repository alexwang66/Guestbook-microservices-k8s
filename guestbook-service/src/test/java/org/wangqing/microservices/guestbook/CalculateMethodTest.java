package org.wangqing.microservices.guestbook;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringApplicationConfiguration(SpringBootApplication.class)
public class CalculateMethodTest {
    CalculateMethod Cm = new CalculateMethod();

    @Test
    public void testPlus(){
        int a = 2;
        int b = 5;
        Assert.assertTrue (7==Cm.plus(a,b));
    }

    @Test
    public void testMultiplication(){
        int a = 2;
        int b = 5;
        Assert.assertTrue (10==Cm.multiplication(a,b));
    }

    @Test
    public void testDivision(){
        int a = 10;
        int b = 5;
        Assert.assertTrue (2==Cm.division(a,b));

    }

    @Test
    public void testSubtraction(){
        int a = 10;
        int b = 5;
        Assert.assertTrue (5==Cm.subtraction(a,b));

    }
}
