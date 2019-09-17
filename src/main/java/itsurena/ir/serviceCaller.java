package itsurena.ir;

import itsurena.ir.distributedTransaction.IntegratedService;
import itsurena.ir.saga.OneSaga;
import org.springframework.beans.factory.annotation.Autowired;

public class serviceCaller {

    @Autowired
    static IntegratedService integratedService;

    @Autowired
    static OneSaga oneSaga;

    public static void main(String[] args) {

        integratedService.serviceOne();

        oneSaga.createOneService();

    }

}
