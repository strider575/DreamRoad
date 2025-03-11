package top.mengtu.wxspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.mengtu.wxspringboot.entity.SleepRecord;

public interface ISleepRecordService  extends IService<SleepRecord> {
    void saveSleepRecord(SleepRecord sleepRecord);
}
