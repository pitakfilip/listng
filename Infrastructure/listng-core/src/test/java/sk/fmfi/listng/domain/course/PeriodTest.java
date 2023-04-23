package sk.fmfi.listng.domain.course;

import org.junit.jupiter.api.Test;
import sk.fmfi.listng.domain.administration.MultiLangText;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @Test
    public void isDateWithinPeriod(){
        Period period = new Period();
        Date s = new Date(2023, 1, 1);
        Date e = new Date(2023, 2, 1);
        period.setStart(s);
        period.setEnd(e);
        
        Date day1 = new Date(2023, 1, 20);
        Date day2 = new Date(2023,2,2);
        Date day3 = new Date(2023,1,1);
        Date day4 = new Date(2023,2,1);
        
        assertTrue(period.isWithinPeriod(day1));
        assertFalse(period.isWithinPeriod(day2));
        assertTrue(period.isWithinPeriod(day3));
        assertTrue(period.isWithinPeriod(day4));
    }
    
    @Test
    public void arePeriodsOverlapping(){
        MultiLangText dummy = new MultiLangText("", "");
        Date start = new Date(2023, 4, 1);
        Date end = new Date(2023, 5, 1);
        Period period = new Period(dummy, start, end);
        
        Date d1 = new Date(2023, 3, 10);
        Date d2 = new Date(2023, 3, 21);
        Date d3 = new Date(2023, 4, 10);
        Date d4 = new Date(2023, 4, 28);
        Date d5 = new Date(2023, 5,10);
        Date d6 = new Date(2023, 5, 11);
        
        Period p1 = new Period(dummy, d1, d2);
        Period p2 = new Period(dummy, d2, d3);
        Period p3 = new Period(dummy, d3, d4);
        Period p4 = new Period(dummy, d4, d5);
        Period p5 = new Period(dummy, d5, d6);
        
        assertFalse(period.overlaps(p1)); // starts after other period ends
        assertFalse(period.overlaps(p5)); // ends before other period starts
        
        assertTrue(period.overlaps(p2)); // starts before other ends
        assertTrue(period.overlaps(p3)); // period within period
        assertTrue(period.overlaps(p4)); // ends before other starts
    }
}
