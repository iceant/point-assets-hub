package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.validate.ValidateFunction;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateRule;

public class MaxLengthFunction implements ValidateFunction<String> {
    int maxLength;
    @Override
    public ValidateResult apply(ValidateRule rule, String value) {
        if(value==null || value.length()>maxLength){
            return ValidateResult.error(rule.getErrorCode(), rule.getErrorMessage());
        }
        return ValidateResult.success();
    }

    public MaxLengthFunction(int maxLength) {
        this.maxLength = maxLength;
    }
}
