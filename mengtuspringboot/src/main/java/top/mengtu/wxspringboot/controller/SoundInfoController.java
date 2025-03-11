package top.mengtu.wxspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mengtu.wxspringboot.entity.SleepArticleInfo;
import top.mengtu.wxspringboot.entity.SoundInfo;
import top.mengtu.wxspringboot.service.ISoundInfoService;
import top.mengtu.wxspringboot.util.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SoundInfoController {
    @Autowired
    private ISoundInfoService soundInfoService;
//@RequestParam("id") String id
    @RequestMapping("/mengtu/GetSoundInfo")
    public R GetSoundInfo() {
        Map<String, Object> resMap = new HashMap<>();
        //ge大于等于，le小于等于
        List<SoundInfo> recordList = soundInfoService.list(new QueryWrapper<SoundInfo>()
                .orderByAsc("SoundIndex"));
                //.select("articleId", "articlePicUrl", "articleTitle", "articleBrief", "articleReadCount"));
        resMap.put("SoundInfo", recordList);
        recordList.forEach(System.out::println);

        return R.ok(resMap);
    }
}
