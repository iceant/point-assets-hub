package com.github.iceant.point.assetshub.webui.utils.validate;

public class FieldBasedValidateRule implements ValidateRule{
    private int errorCode;
    private String fieldName;
    private Class fieldType;
    private ValidateFunction validateFunction;
    private String errorMessage;

    ////////////////////////////////////////////////////////////////////////////////
    ////

    ////////////////////////////////////////////////////////////////////////////////
    ////


    @Override
    public int getErrorCode() {
        return errorCode;
    }

    public FieldBasedValidateRule setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Class getFieldType() {
        return fieldType;
    }

    public FieldBasedValidateRule setFieldType(Class fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    @Override
    public ValidateFunction getValidateFunction() {
        return validateFunction;
    }

    public FieldBasedValidateRule setValidateFunction(ValidateFunction validateFunction) {
        this.validateFunction = validateFunction;
        return this;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public FieldBasedValidateRule setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldBasedValidateRule setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }
}
