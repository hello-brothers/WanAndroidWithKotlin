package com.lh.wanandroid.testStudyExample.JunitAndMockitoTestExample;

import com.lh.wanandroid.MyRuler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * des
 *
 * @author hellobrothers
 * @data 2020/12/23
 */
public class JunitAndMockitoTest {


    @Spy
    People mPeople = mock(People.class);




    @Rule
    public MyRuler myRuler = new MyRuler();

    @Before
    public void setUp() {

    }

    @Test()
    public void WhenAndThenReturnTest(){
        when(mPeople.getAge()).thenReturn(19);
        System.out.println(mPeople.getAge());
    }


}
