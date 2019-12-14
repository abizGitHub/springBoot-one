package abiz.ir.demo2.xmlClass.dto;


public class GetAllocatedSecuritiesSimaResponse extends GeneralSimaResponse {
    private String principalIban;
    private String interestIban;
    private SecuritiesTypeDto securities;

    public GetAllocatedSecuritiesSimaResponse principalIban(String principalIban) {
        this.principalIban = principalIban;
        return this;
    }

    public GetAllocatedSecuritiesSimaResponse interestIban(String interestIban) {
        this.interestIban = interestIban;
        return this;
    }

    public GetAllocatedSecuritiesSimaResponse securities(SecuritiesTypeDto securities) {
        this.securities = securities;
        return this;
    }

    public String getPrincipalIban() {
        return principalIban;
    }

    public void setPrincipalIban(String principalIban) {
        this.principalIban = principalIban;
    }

    public String getInterestIban() {
        return interestIban;
    }

    public void setInterestIban(String interestIban) {
        this.interestIban = interestIban;
    }

    public SecuritiesTypeDto getSecurities() {
        return securities;
    }

    public void setSecurities(SecuritiesTypeDto securities) {
        this.securities = securities;
    }

    @Override
    public SimaOperationType getSimaOperationType() {
        return SimaOperationType.GET_ALLOCATED_SECURITIES;
    }
}
