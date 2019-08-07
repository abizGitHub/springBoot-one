package itsurena.ir.demo2;


import com.fasterxml.jackson.databind.util.ClassUtil;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJunit {

    @Test
    public void w() {
        assertEquals(1, 1);
    }

    @Test
    public void a() {
        assertEquals(1, 1);
        try {
            System.out.println(1 / 0);
            fail("falied");
        } catch (Exception e) {
            assertTrue(e instanceof ArithmeticException);
            //assertTrue(e instanceof ChangeSetPersister.NotFoundException);
        }
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void b() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage("/ by zero");
        int x = 1 / 0;
        fail();
        x *= 0;
    }



}
