package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import com.github.iceant.point.assetshub.webui.utils.validate.ValidateFunction;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatterValidateFunction implements ValidateFunction<String> {
    String regex;

    public RegexPatterValidateFunction(String regex) {
        this.regex = regex;
    }

    @Override
    public ValidateResult apply(ValidateRule rule, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches()){
            return ValidateResult.error(rule.getErrorCode(), rule.getErrorMessage());
        }
        return ValidateResult.success();
    }
}
