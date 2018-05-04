package com.jhqc.pxsj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jhqc.pxsj.TestService;


@RunWith(SpringJUnit4ClassRunner.class)   // 1
public class MyTest {

    private TestService ts;
    
    @Test
    public void testMain(){
    	System.out.println(ts.getName());
    }

}