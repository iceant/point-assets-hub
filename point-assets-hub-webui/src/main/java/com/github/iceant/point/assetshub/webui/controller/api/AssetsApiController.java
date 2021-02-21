package com.github.iceant.point.assetshub.webui.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.github.iceant.point.assetshub.webui.beans.ResultCode;
import com.github.iceant.point.assetshub.webui.beans.WebResponse;
import com.github.iceant.point.assetshub.webui.dos.AssetsDO;
import com.github.iceant.point.assetshub.webui.page.Page;
import com.github.iceant.point.assetshub.webui.page.PageRequest;
import com.github.iceant.point.assetshub.webui.services.AssetsService;
import com.github.iceant.point.assetshub.webui.utils.AppUtil;
import com.github.iceant.point.assetshub.webui.utils.validate.*;
import com.github.iceant.point.assetshub.webui.utils.validate.functions.NotEmptyFunction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/assets"})
public class AssetsApiController {

    final AssetsService assetsService;

    public AssetsApiController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @RequestMapping(path = {"/search"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object search(AssetsDO obj, PageRequest pageRequest) {
        Page page =  assetsService.searchByExample(obj, pageRequest);
        return WebResponse.makeSuccess().setData(page);
    }

    @RequestMapping(path = {"/create"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object create(@RequestBody JSONObject param) {
        String groupId = param.getString("groupId");
        String artifactId = param.getString("artifactId");
        String version = param.getString("version");
        String detail = param.getString("detail");

        NotEmptyFunction notEmptyFunction = new NotEmptyFunction();
        ValidateStrategy validateStrategy = new ValidateStrategy();
        validateStrategy.addRule(new FieldBasedValidateRule().setFieldName("groupId").setFieldType(String.class).setValidateFunction(notEmptyFunction).setErrorCode(ResultCode.PARAMS_ERROR.code()).setErrorMessage(AppUtil.msg("errors.empty_value")));
        validateStrategy.addRule(new FieldBasedValidateRule().setFieldName("artifactId").setFieldType(String.class).setValidateFunction(notEmptyFunction).setErrorCode(ResultCode.PARAMS_ERROR.code()).setErrorMessage(AppUtil.msg("errors.empty_value")));
        validateStrategy.addRule(new FieldBasedValidateRule().setFieldName("version").setFieldType(String.class).setValidateFunction(notEmptyFunction).setErrorCode(ResultCode.PARAMS_ERROR.code()).setErrorMessage(AppUtil.msg("errors.empty_value")));
        ValidateResult validateResult = ValidateUtil.check(param, validateStrategy);
        if (validateResult.hasError()) {
            return WebResponse.makeFail().setData(validateResult.getErrors());
        }

        AssetsDO assetsDO = assetsService.create(groupId, artifactId, version, detail);
        if (assetsDO != null && assetsDO.getId() != null) {
            return WebResponse.makeSuccess();
        } else {
            return WebResponse.makeFail();
        }

    }

    @PostMapping(path = {"/update"})
    public Object update(@RequestBody AssetsDO assetsDO){
        AssetsDO updatedAssets = assetsService.update(assetsDO);
        if(updatedAssets==null){
            return WebResponse.makeFail();
        }
        return WebResponse.makeSuccess().setData(updatedAssets);
    }

    @RequestMapping(path = {"/get/{id}"})
    public Object get(@PathVariable("id") Long id){
        AssetsDO assetsDO = assetsService.findByPk(id);
        if(assetsDO==null){
            return WebResponse.makeFail();
        }
        return WebResponse.makeSuccess().setData(assetsDO);
    }

    @RequestMapping(path ={"/delete/{id}"} )
    public Object delete(@PathVariable("id") Long id){
        AssetsDO assetsDO = assetsService.delete(new AssetsDO().setId(id));
        return WebResponse.makeSuccess().setData(assetsDO);
    }

}
