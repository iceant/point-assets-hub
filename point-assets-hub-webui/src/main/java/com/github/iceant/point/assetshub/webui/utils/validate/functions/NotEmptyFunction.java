package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.validate.ValidateError;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateFunction;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateRule;

public class NotEmptyFunction implements ValidateFunction<String> {
    @Override
    public ValidateResult apply(ValidateRule rule, String value) {
        if(value==null || value.length()<1) {
            return new ValidateResult().addError(new ValidateError().setCode(rule.getErrorCode()).setMessage(rule.getErrorMessage()));
        }
        return ValidateResult.success();
    }
}
