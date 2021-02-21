package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.validate.ValidateFunction;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateRule;


import static com.github.iceant.point.assetshub.webui.utils.validate.functions.NumberUtil.compareTo;

public class MinValueFunction implements ValidateFunction<Number> {
    private Number minValue;

    public MinValueFunction(Number minValue) {
        this.minValue = minValue;
    }

    @Override
    public ValidateResult apply(ValidateRule rule, Number value) {
        if(value==null || compareTo(minValue, value)>0){
            return ValidateResult.error(rule.getErrorCode(), rule.getErrorMessage());
        }
        return ValidateResult.success();
    }
}
