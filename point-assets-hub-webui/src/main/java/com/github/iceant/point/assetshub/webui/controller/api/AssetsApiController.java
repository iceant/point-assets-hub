package com.github.iceant.point.assetshub.webui.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.github.iceant.point.assetshub.webui.beans.WebResponse;
import com.github.iceant.point.assetshub.webui.dos.AssetsDO;
import com.github.iceant.point.assetshub.webui.page.Page;
import com.github.iceant.point.assetshub.webui.page.PageRequest;
import com.github.iceant.point.assetshub.webui.services.AssetsService;
import com.github.iceant.point.assetshub.webui.utils.AppUtil;
import com.github.iceant.point.assetshub.webui.utils.validate.FieldBasedValidateRule;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateResult;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateStrategy;
import com.github.iceant.point.assetshub.webui.utils.validate.ValidateUtil;
import com.github.iceant.point.assetshub.webui.utils.validate.functions.NotEmptyFunction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        validateStrategy.addRule(new FieldBasedValidateRule().setFieldName("groupId").setFieldType(String.class).setValidateFunction(notEmptyFunction).setErrorMessage(AppUtil.msg("errors.empty_value")));
        validateStrategy.addRule(new FieldBasedValidateRule().setFieldName("artifactId").setFieldType(String.class).setValidateFunction(notEmptyFunction).setErrorMessage(AppUtil.msg("errors.empty_value")));
        validateStrategy.addRule(new FieldBasedValidateRule().setFieldName("version").setFieldType(String.class).setValidateFunction(notEmptyFunction).setErrorMessage(AppUtil.msg("errors.empty_value")));
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

}
