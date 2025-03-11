package top.mengtu.wxspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mengtu.wxspringboot.entity.FeedBackInfo;
import top.mengtu.wxspringboot.mapper.FeedBackInfoMapper;
import top.mengtu.wxspringboot.service.IFeedBackInfoService;

@Service
public class IFeedBackInfoServiceImpl extends ServiceImpl<FeedBackInfoMapper, FeedBackInfo> implements IFeedBackInfoService {
    @Autowired
    private FeedBackInfoMapper feedBackInfoMapper;
}
