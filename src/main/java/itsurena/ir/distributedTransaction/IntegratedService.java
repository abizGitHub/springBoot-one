package itsurena.ir.distributedTransaction;

import org.springframework.beans.factory.annotation.Autowired;

public class IntegratedService {

    @Autowired
    NasimService nasimService;

    @Autowired
    SimaService simaService;

    @Autowired
    UmsService usmService;

    public void serviceOne() {
        try {
            nasimService.doSomeThing();
            simaService.doSomeThing();
            usmService.doSomeThing();
        } catch (Exception e) {
            nasimService.reverseSomeThing();
            simaService.reverseSomeThing();
            usmService.reverseSomeThing();
        }
    }


}
