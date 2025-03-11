package top.mengtu.wxspringboot.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import top.mengtu.wxspringboot.entity.WxUserInfo;
import top.mengtu.wxspringboot.properties.WxLoginProperties;
import top.mengtu.wxspringboot.service.IWxUserInfoService;
import top.mengtu.wxspringboot.util.HttpClientUtil;
import top.mengtu.wxspringboot.util.JwtUtils;
import top.mengtu.wxspringboot.util.R;
import top.mengtu.wxspringboot.constant.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mengtu")
public class WxLoginController {
    @Autowired
    private WxLoginProperties wxloginproperties;
    @Autowired
    private HttpClientUtil httpclientutil;
    @Autowired
    private IWxUserInfoService wxuserInfoService;

    @RequestMapping("/wxLogin")
    public R wxLogin(@RequestBody WxUserInfo WxuserInfo) {
        System.out.println(WxuserInfo.getCode());
        String code2SessionUrl = wxloginproperties.getJscode2sessionUrl() + "?appid=" + wxloginproperties.getAppid() + "&secret=" + wxloginproperties.getSecret() + "&js_code=" + WxuserInfo.getCode() + "&grant_type=authorization_code";
        System.out.println(code2SessionUrl);
        String res = httpclientutil.sendHttpGet(code2SessionUrl);
        System.out.println(res);
        //回传json解析
        JSONObject jsonObject = JSON.parseObject(res);
        String openid = jsonObject.get("openid").toString();
        System.out.println(openid);

        WxUserInfo resWxUser = wxuserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("openid", openid));
        if (resWxUser == null) {
            System.out.println("不存在 插入用户");
            WxuserInfo.setOpenid(openid);
            WxuserInfo.setRegisterTime(new Date());
            WxuserInfo.setLastLoginTime(new Date());
            wxuserInfoService.save(WxuserInfo);
        } else {
            System.out.println("存在 更新用户");
            resWxUser.setNickName(WxuserInfo.getNickName());
            resWxUser.setAvatarUrl(WxuserInfo.getAvatarUrl());
            resWxUser.setAvatarBase64File(WxuserInfo.getAvatarBase64File());
            resWxUser.setLastLoginTime(new Date());
            boolean result = wxuserInfoService.updateById(resWxUser);
            System.out.println(result);
        }

        String token = JwtUtils.createJWT(openid, WxuserInfo.getNickName(), SystemConstant.JWT_TTL);
        System.out.println(token);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("token", token);
        resMap.put("nickName", WxuserInfo.getNickName());
        return R.ok(resMap);
    }
}
