package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.AppUtil;
import com.github.iceant.point.assetshub.webui.utils.validate.*;

public class NotEmptyFunction implements ValidateFunction<String> {
    @Override
    public ValidateResult apply(ValidateRule rule, String value) {
        if(value==null || value.length()<1) {
            String pattern = rule.getErrorMessage();
            FieldBasedValidateRule fieldBasedValidateRule = (FieldBasedValidateRule) rule;
            String errorMsg = AppUtil.format(pattern, fieldBasedValidateRule.getFieldName());
            return new ValidateResult().addError(new ValidateError().setCode(rule.getErrorCode()).setMessage(errorMsg));
        }
        return ValidateResult.success();
    }
}
