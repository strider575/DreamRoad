package top.mengtu.wxspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mengtu.wxspringboot.entity.SleepArticleInfo;
import top.mengtu.wxspringboot.mapper.SleepArticleInfoMapper;
import top.mengtu.wxspringboot.service.ISleepArticleInfoService;

@Service
public class ISleepArticleInfoServiceImpl extends ServiceImpl<SleepArticleInfoMapper, SleepArticleInfo> implements ISleepArticleInfoService {
    @Autowired
    private SleepArticleInfoMapper sleepArticleInfoMapper;
}
