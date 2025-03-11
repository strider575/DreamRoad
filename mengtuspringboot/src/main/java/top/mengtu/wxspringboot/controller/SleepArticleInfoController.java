package top.mengtu.wxspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mengtu.wxspringboot.entity.SleepArticleInfo;
import top.mengtu.wxspringboot.entity.SleepRecord;
import top.mengtu.wxspringboot.mapper.SleepArticleInfoMapper;
import top.mengtu.wxspringboot.service.ISleepArticleInfoService;
import top.mengtu.wxspringboot.util.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SleepArticleInfoController {
    @Autowired
    private ISleepArticleInfoService sleepArticleInfoService;
    @Autowired
    private SleepArticleInfoMapper sleepArticleInfoMapper;

    @RequestMapping("/mengtu/GetSleepArticleBrief")
    public R GetSleepArticleBrief(@RequestParam("id") String id) {
        Map<String, Object> resMap = new HashMap<>();
        //ge大于等于，le小于等于
        List<SleepArticleInfo> recordList = sleepArticleInfoMapper.selectList(new QueryWrapper<SleepArticleInfo>()
                .orderByDesc("articleLastUpdateTime")
                .select("articleId", "articlePicUrl", "articleTitle", "articleBrief", "articleReadCount"));
        resMap.put("articleBrief", recordList);
        recordList.forEach(System.out::println);

        return R.ok(resMap);
    }

    @RequestMapping("/mengtu/GetSleepArticleContent")
    public R GetSleepArticleContent(@RequestParam("requestArticleId") String articleId) {
        Map<String, Object> resMap = new HashMap<>();
        //ge大于等于，le小于等于
        SleepArticleInfo resArticle = sleepArticleInfoService.getOne(new QueryWrapper<SleepArticleInfo>()
                .eq("articleId", articleId)
                .orderByDesc("articleLastUpdateTime")
                .select("articleId","articlePicUrl", "articleTitle","articleSource","articleAuthor", "articleBrief", "articleReadCount", "articleContent"));
        //更新阅读量
        resArticle.setArticleReadCount(resArticle.getArticleReadCount() + 1);
        boolean result = sleepArticleInfoService.updateById(resArticle);
        System.out.println(result);
        resMap.put("articleBrief", resArticle);
        System.out.println(resArticle);
        return R.ok(resMap);
    }
}
