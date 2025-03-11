package top.mengtu.wxspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mengtu.wxspringboot.entity.CheckResult;
import top.mengtu.wxspringboot.entity.SleepArticleInfo;
import top.mengtu.wxspringboot.entity.SleepRecord;
import top.mengtu.wxspringboot.entity.WxUserInfo;
import top.mengtu.wxspringboot.service.ISleepRecordService;
import top.mengtu.wxspringboot.service.IWxUserInfoService;
import top.mengtu.wxspringboot.util.JwtUtils;
import top.mengtu.wxspringboot.util.R;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WxUserInfoController {
    @Autowired
    private IWxUserInfoService wxUserInfoService;
    @Autowired
    private ISleepRecordService sleepRecordService;

    @RequestMapping("mengtu/GetUserInfoDetail")
    public R GetUserInfoDetail(@RequestParam("token") String token) {
        System.out.println(token);
        CheckResult tokenRes = JwtUtils.validateJWT(token);
        String openid = tokenRes.getClaims().getId();

        Map<String, Object> resMap = new HashMap<>();
        WxUserInfo resUserInfo = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>()
                .eq("openid", openid)
                .select("registerTime","lastLoginTime"));
        Long resRecordCount = sleepRecordService.count(new QueryWrapper<SleepRecord>()
                .eq("openid", openid));

        resMap.put("resUserInfo", resUserInfo);
        resMap.put("resRecordCount", resRecordCount);

        return R.ok(resMap);
    }
}
