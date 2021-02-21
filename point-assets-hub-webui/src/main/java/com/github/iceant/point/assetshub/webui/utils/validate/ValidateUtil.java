package com.github.iceant.point.assetshub.webui.utils.validate;

import com.alibaba.fastjson.JSONObject;

public class ValidateUtil {
    public static ValidateResult check(JSONObject param, ValidateStrategy strategy){
        ValidateResult finalResult = new ValidateResult();
        for(ValidateRule rule : strategy.getValidateRuleList()){
            FieldBasedValidateRule fieldBasedValidateRule = (FieldBasedValidateRule) rule;
            String fieldName = fieldBasedValidateRule.getFieldName();
            Object value = param.getObject(fieldName, fieldBasedValidateRule.getFieldType());
            try {
                ValidateResult result = fieldBasedValidateRule.getValidateFunction().apply(fieldBasedValidateRule, value);
                if (result.hasError()) {
                    for (ValidateError error : result.getErrors()) {
                        finalResult.addError(error);
                    }
                }
            }catch (Exception err){
                finalResult.addError(new ValidateError().setMessage(err.getMessage()));
            }
            if(finalResult.hasError() && ValidateFailStrategy.FAIL_FAST == strategy.getFailStrategy()){
                return finalResult;
            }
        }
        return finalResult;
    }
}
