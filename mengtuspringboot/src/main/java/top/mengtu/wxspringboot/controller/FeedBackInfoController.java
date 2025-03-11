package top.mengtu.wxspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mengtu.wxspringboot.entity.CheckResult;
import top.mengtu.wxspringboot.entity.FeedBackInfo;
import top.mengtu.wxspringboot.service.IFeedBackInfoService;
import top.mengtu.wxspringboot.util.JwtUtils;
import top.mengtu.wxspringboot.util.R;

import java.util.Date;

@RestController
public class FeedBackInfoController {
    @Autowired
    private IFeedBackInfoService feedBackInfoService;

    @RequestMapping("/mengtu/SendFeedBack")
    public R SendFeedBack (@RequestBody FeedBackInfo feedBackInfo){
        CheckResult tokenRes = JwtUtils.validateJWT(feedBackInfo.getToken());
        String openid = tokenRes.getClaims().getId();
        feedBackInfo.setOpenid(openid);
        feedBackInfo.setUploadTime(new Date());
        //存入数据库
        feedBackInfoService.save(feedBackInfo);
        return R.ok();
    }
}
