package top.mengtu.wxspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@TableName(value = "sleep_records")
@Data
public class SleepRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(select = false, exist = false)
    private String token;

    @TableField(value = "openid")
    private String openid;
    //睡眠记录属于哪一天
    @TableField(value = "belongDay")
    private String belongDay;

    //睡眠记录属于哪一个月
    @TableField(value = "belongMonth")
    private String belongMonth;

    @TableField(value = "sleepBeginTime")
    //@JsonSerialize(using = CustomDateTimeSerializer.class)
    private String sleepBeginTime;

    @TableField(value = "sleepEndTime")
    //@JsonSerialize(using = CustomDateTimeSerializer.class)
    private String sleepEndTime;

    @TableField(value = "sleepDuration")
    // @JsonSerialize(using = CustomDateTimeSerializer.class)
    private String sleepDuration;

    @TableField(value = "doubleSleepDuration")
    private double doubleSleepDuration;

    @TableField(value = "sleepScore")
    private int sleepScore;

    @TableField(value = "recordTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date recordTime;

    private double sleepDurationAvg;

}
