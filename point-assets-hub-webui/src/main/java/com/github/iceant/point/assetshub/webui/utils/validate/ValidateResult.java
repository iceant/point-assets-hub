package com.github.iceant.point.assetshub.webui.utils.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateResult {
    private List<ValidateError> errors = new ArrayList<>();

    public static ValidateResult success() {
        return new ValidateResult().setErrors(null);
    }

    public static ValidateResult error(int errorCode, String errorMessage) {
        return new ValidateResult().addError(new ValidateError().setCode(errorCode).setMessage(errorMessage));
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public ValidateResult addError(ValidateError error){
        errors.add(error);
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////

    public List<ValidateError> getErrors() {
        return errors;
    }

    public ValidateResult setErrors(List<ValidateError> errors) {
        this.errors = errors;
        return this;
    }

    public boolean hasError() {
        return this.errors!=null && this.errors.size()>0;
    }
}
