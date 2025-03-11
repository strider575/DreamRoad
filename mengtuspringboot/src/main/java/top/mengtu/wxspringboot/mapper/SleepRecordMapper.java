package top.mengtu.wxspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.mengtu.wxspringboot.entity.SleepRecord;

public interface SleepRecordMapper extends BaseMapper<SleepRecord> {
    void insertSleepRecord(SleepRecord sleepRecord);
}
