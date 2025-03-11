package top.mengtu.wxspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mengtu.wxspringboot.entity.CheckResult;
import top.mengtu.wxspringboot.entity.SleepRecord;
import top.mengtu.wxspringboot.mapper.SleepRecordMapper;
import top.mengtu.wxspringboot.service.ISleepRecordService;
import top.mengtu.wxspringboot.util.JwtUtils;
import top.mengtu.wxspringboot.util.R;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SleepRecordController {
    @Autowired
    private ISleepRecordService sleepRecordService;
    @Autowired
    private SleepRecordMapper sleepRecordMapper;

    @RequestMapping("/mengtu/NewSleepRecord")
    public R NewSleepRecord(@RequestBody SleepRecord sleeprecord) {
        System.out.println(sleeprecord.getToken());
        CheckResult tokenRes = JwtUtils.validateJWT(sleeprecord.getToken());
        if (tokenRes.isSuccess()) {
            System.out.println(sleeprecord.getToken());
            System.out.println(tokenRes.getClaims());
            System.out.println(tokenRes.getClaims().getId());
            String openid = tokenRes.getClaims().getId();
            sleeprecord.setOpenid(openid);
            sleeprecord.setRecordTime(new Date());
            sleeprecord.setBelongMonth(sleeprecord.getBelongDay().substring(0, 7));
            sleeprecord.setDoubleSleepDuration(timeStringtoNumber(sleeprecord.getSleepDuration()));
            sleepRecordService.save(sleeprecord);
        }
        return R.ok();
    }

    @RequestMapping("/mengtu/GetSleepRecord")
    public R GetSleepRecord(@RequestParam("token") String token, @RequestParam("time_frame") Integer time_frame) {
        System.out.println(token);
        System.out.println(time_frame);
        CheckResult tokenRes = JwtUtils.validateJWT(token);
        String openid = tokenRes.getClaims().getId();
        //获取当前时间，并且把时分秒置零
        Date now = new Date();
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date endDate = java.sql.Date.valueOf(localDate);
        Date startDate = DateUtils.addDays(endDate, -time_frame);
        System.out.println(startDate);
        System.out.println(endDate);

        Map<String, Object> resMap = new HashMap<>();
        if (time_frame == 7 || time_frame == 30) {
            //ge大于等于，le小于等于
            List<SleepRecord> recordList = sleepRecordMapper.selectList(new QueryWrapper<SleepRecord>()
                    .eq("openid", openid)
                    .ge("belongDay", startDate).le("belongDay", endDate)
                    .orderByAsc("belongDay")
                    .select("belongDay", "sleepBeginTime", "sleepEndTime", "doubleSleepDuration", "sleepScore"));
            resMap.put("message", recordList);
            recordList.forEach(System.out::println);
        } else if (time_frame == 365) {
            //需要按月合并
            //avg(doubleSleepDuration) as sleepDurationAvg
            List<SleepRecord> recordList = sleepRecordService.list(new QueryWrapper<SleepRecord>()
                    .select("belongMonth", "AVG(doubleSleepDuration) as sleepDurationAvg")
                    .eq("openid", openid)
                    .ge("belongDay", startDate).le("belongDay", endDate)
                    .orderByAsc("belongMonth")
                    .groupBy("belongMonth"));
            recordList.forEach(sleepRecord -> sleepRecord.setSleepDurationAvg(numberFix2Digit(sleepRecord.getSleepDurationAvg())));

            resMap.put("message", recordList);
            recordList.forEach(System.out::println);
        }

        //.orderByAsc("belong_day")
//        List<SleepRecord> recordList = sleepRecordMapper.selectList(new QueryWrapper<SleepRecord>()
//                .eq("openid", openid).orderByAsc("belongDay").select("belongDay", "sleepBeginTime", "sleepEndTime", "sleepDuration"));
//
//        resMap.put("message", recordList);
        return R.ok(resMap);
    }

    public double timeStringtoNumber(String str) {
        String hour = str.split(":")[0];
        String minute = str.split(":")[1];
        return Math.floor((Integer.parseInt(hour) + (double) Integer.parseInt(minute) / 60) * 100) / 100;
    }

    public double numberFix2Digit(double num) {
        return Math.floor(num * 100) / 100;
    }

}
