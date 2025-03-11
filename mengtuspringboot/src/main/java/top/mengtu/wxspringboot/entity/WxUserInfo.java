package top.mengtu.wxspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/*
 * 小程序用户信息类
 * */

@TableName(value = "wx_users")
@Data
public class WxUserInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "openid")
    private String openid;
    @TableField(value = "nickName")
    private String nickName;
    @TableField(value = "avatarUrl")
    private String avatarUrl;
    @TableField(value = "avatarBase64File")
    private String avatarBase64File;
    @TableField(value = "registerTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date registerTime;
    @TableField(value = "lastLoginTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date lastLoginTime;
    @TableField(select = false, exist = false)
    private String code;
}
