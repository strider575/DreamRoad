package top.mengtu.wxspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mengtu.wxspringboot.entity.SleepRecord;
import top.mengtu.wxspringboot.mapper.SleepRecordMapper;
import top.mengtu.wxspringboot.service.ISleepRecordService;

@Service
public class ISleepRecordServiceImpl extends ServiceImpl<SleepRecordMapper, SleepRecord> implements ISleepRecordService {
    @Autowired
    private SleepRecordMapper sleepRecordMapper;

    public void saveSleepRecord(SleepRecord sleepRecord) {
        sleepRecordMapper.insertSleepRecord(sleepRecord);
        // 插入完成后，userDto的id属性会被自动填充
        String generatedId = sleepRecord.getId();
    }
}
