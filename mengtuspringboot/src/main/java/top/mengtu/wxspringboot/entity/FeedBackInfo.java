package top.mengtu.wxspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/*
 * 意见反馈类
 * */
@TableName(value = "feed_back_info")
@Data
public class FeedBackInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(select = false, exist = false)
    private String token;

    @TableField(value = "openid")
    private String openid;

    @TableField(value = "title")
    private String title;

    @TableField(value = "detail")
    private String detail;

    @TableField(value = "uploadTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date uploadTime;
}
