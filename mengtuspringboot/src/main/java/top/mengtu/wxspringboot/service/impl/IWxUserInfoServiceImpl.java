package top.mengtu.wxspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mengtu.wxspringboot.entity.WxUserInfo;
import top.mengtu.wxspringboot.service.IWxUserInfoService;
import top.mengtu.wxspringboot.mapper.WxUserInfoMapper;

@Service
public class IWxUserInfoServiceImpl extends ServiceImpl<WxUserInfoMapper, WxUserInfo> implements IWxUserInfoService {

    @Autowired
    private WxUserInfoMapper wxuserinfomapper;
}
