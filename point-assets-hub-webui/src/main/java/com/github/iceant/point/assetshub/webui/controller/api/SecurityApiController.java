package com.github.iceant.point.assetshub.webui.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.github.iceant.point.assetshub.webui.beans.ResultCode;
import com.github.iceant.point.assetshub.webui.beans.WebResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api"})
public class SecurityApiController {

    final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityApiController(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(path = {"/login"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object login(@RequestBody JSONObject param) {
        String username = param.getString("username");
        String password = param.getString("password");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean match = passwordEncoder.matches(password, userDetails.getPassword());
        if (match) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            return WebResponse.makeSuccess().setData(token);
        } else {
            return WebResponse.makeFail().setCode(ResultCode.INVALID_AUTHCODE.ordinal());
        }
    }
}
