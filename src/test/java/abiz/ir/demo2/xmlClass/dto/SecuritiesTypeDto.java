package abiz.ir.demo2.xmlClass.dto;


import abiz.ir.demo2.xmlClass.SecuritiesStatusType;

import java.util.List;

public class SecuritiesTypeDto extends GeneralSimaResponse {

    private String securitiesCode;
    private String securitiesName;
    private String securitiesGroupCode;
    private String securitiesGroupDescription;
    private SecuritiesStatusType status;
    private List<SecuritiesFeatureDto> features;

    public String getSecuritiesCode() {
        return securitiesCode;
    }

    public void setSecuritiesCode(String securitiesCode) {
        this.securitiesCode = securitiesCode;
    }

    public SecuritiesTypeDto securitiesCode(String securitiesCode) {
        this.securitiesCode = securitiesCode;
        return this;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public SecuritiesTypeDto securitiesName(String securitiesName) {
        this.securitiesName = securitiesName;
        return this;
    }

    public String getSecuritiesGroupCode() {
        return securitiesGroupCode;
    }

    public void setSecuritiesGroupCode(String securitiesGroupCode) {
        this.securitiesGroupCode = securitiesGroupCode;
    }

    public SecuritiesTypeDto securitiesGroupCode(String securitiesGroupCode) {
        this.securitiesGroupCode = securitiesGroupCode;
        return this;
    }

    public String getSecuritiesGroupDescription() {
        return securitiesGroupDescription;
    }

    public void setSecuritiesGroupDescription(String securitiesGroupDescription) {
        this.securitiesGroupDescription = securitiesGroupDescription;
    }

    public SecuritiesTypeDto securitiesGroupDescription(String securitiesGroupDescription) {
        this.securitiesGroupDescription = securitiesGroupDescription;
        return this;
    }

    public SecuritiesStatusType getStatus() {
        return status;
    }

    public void setStatus(SecuritiesStatusType status) {
        this.status = status;
    }

    public SecuritiesTypeDto status(SecuritiesStatusType status) {
        this.status = status;
        return this;
    }

    public List<SecuritiesFeatureDto> getFeatures() {
        return features;
    }

    public SecuritiesTypeDto features(List<SecuritiesFeatureDto> features) {
        this.features = features;
        return this;
    }

    @Override
    public SimaOperationType getSimaOperationType() {
        return SimaOperationType.GET_SECURITIES_FOR_SALE;
    }
}
