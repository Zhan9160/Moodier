package com.laurier.joelucy.CP670project;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import kotlin.text.UStringsKt;

public class DateUtilTest extends TestCase {

    private String time= "2017-10-15 16:00:02";

    private  long timeStamp = 1508054402000L;

    private Date mDate;

    @Before
    public void setUp() throws Exception {
        System.out.println("Starting Test!");
        mDate = new Date();
        mDate.setTime(timeStamp);
        //super.setUp();
    }
    @After
    public void tearDown() throws Exception {
        System.out.println("Testing finish!");
    }

    public void testDateToStamp() {
    }
    @Test
    public void testStampToDate() {
        assertEquals("expected time", DateUtil.stampToDate(timeStamp));
    }
}