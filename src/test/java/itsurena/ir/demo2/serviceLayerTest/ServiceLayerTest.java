package itsurena.ir.demo2.serviceLayerTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ServiceLayerTest {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(CreateTest.class, FindTest.class, ModificationTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println("failures : " + failure.toString());
        }
        System.err.println("success : " + result.wasSuccessful());
    }

}
