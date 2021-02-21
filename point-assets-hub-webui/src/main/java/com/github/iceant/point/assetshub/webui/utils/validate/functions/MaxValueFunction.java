package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.validate.ValidateFunction;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateRule;

public class MaxValueFunction implements ValidateFunction<Number> {
    Number maxValue;

    public MaxValueFunction(Number maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public ValidateResult apply(ValidateRule rule, Number value) {
        if(value==null || NumberUtil.compareTo(value, maxValue)>0){
            return ValidateResult.error(rule.getErrorCode(), rule.getErrorMessage());
        }
        return ValidateResult.success();
    }
}
