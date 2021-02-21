package com.github.iceant.point.assetshub.webui.utils.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateStrategy {
    private ValidateFailStrategy failStrategy;
    private List<ValidateRule> validateRuleList = new ArrayList<>();

    public ValidateStrategy addRule(ValidateRule rule){
        validateRuleList.add(rule);
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////


    public ValidateFailStrategy getFailStrategy() {
        return failStrategy;
    }

    public ValidateStrategy setFailStrategy(ValidateFailStrategy failStrategy) {
        this.failStrategy = failStrategy;
        return this;
    }

    public List<ValidateRule> getValidateRuleList() {
        return validateRuleList;
    }

    public ValidateStrategy setValidateRuleList(List<ValidateRule> validateRuleList) {
        this.validateRuleList = validateRuleList;
        return this;
    }
}
