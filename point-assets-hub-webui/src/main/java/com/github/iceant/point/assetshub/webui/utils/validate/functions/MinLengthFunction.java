package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.validate.ValidateFunction;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateRule;

public class MinLengthFunction implements ValidateFunction<String> {
    private int minLength;

    @Override
    public ValidateResult apply(ValidateRule rule, String value) {
        if(value==null || value.length()<minLength){
            return ValidateResult.error(rule.getErrorCode(), rule.getErrorMessage());
        }
        return ValidateResult.success();
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////


    public MinLengthFunction(int minLength) {
        this.minLength = minLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public MinLengthFunction setMinLength(int minLength) {
        this.minLength = minLength;
        return this;
    }
}
