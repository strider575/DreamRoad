package top.mengtu.wxspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mengtu.wxspringboot.entity.SoundInfo;
import top.mengtu.wxspringboot.mapper.SoundInfoMapper;
import top.mengtu.wxspringboot.service.ISoundInfoService;

@Service
public class ISoundInfoServiceImpl extends ServiceImpl<SoundInfoMapper, SoundInfo> implements ISoundInfoService {
    @Autowired
    private SoundInfoMapper soundInfoMapper;
}
