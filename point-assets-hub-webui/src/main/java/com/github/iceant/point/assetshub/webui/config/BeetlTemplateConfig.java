package com.github.iceant.point.assetshub.webui.config;

import com.github.iceant.point.assetshub.webui.ext.beetl.I18nFunction;
import com.ibeetl.starter.BeetlTemplateCustomize;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnClass(value = {BeetlGroupUtilConfiguration.class})
public class BeetlTemplateConfig {

    private final WebApplicationContext wac;

    public BeetlTemplateConfig(WebApplicationContext wac) {
        this.wac = wac;
    }

    @Bean(name = {"beetlTemplateCustomize"})
    public BeetlTemplateCustomize beetlTemplateCustomize() {
        return new BeetlTemplateCustomize() {
            public void customize(GroupTemplate groupTemplate) {
                Map<String, Object> sharedVars = new HashMap<String, Object>();
                groupTemplate.setSharedVars(sharedVars);
                groupTemplate.registerFunction("i18n", new I18nFunction(wac));
            }
        };
    }
}
