package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaCountTest {

    @Test
    void getArea() {
        AreaCount a = new AreaCount("test",1);
        assertEquals("test", a.getArea());
    }

    @Test
    void getAreaCount() {
        AreaCount a = new AreaCount("test",1);
        assertEquals(1, a.getAreaCount());
    }

    @Test
    void setArea() {
        AreaCount a = new AreaCount("test",1);
        a.setArea("new");
        assertEquals("new", a.getArea());
    }
}