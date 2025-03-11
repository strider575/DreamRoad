package top.mengtu.wxspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@TableName(value = "sleep_article_info")
@Data
public class SleepArticleInfo {
    @TableId(value = "articleId", type = IdType.AUTO)
    private String articleId;

    @TableField("articleReadCount")
    private Long articleReadCount;

    @TableField("articlePicUrl")
    private String articlePicUrl;

    @TableField("articleTitle")
    private String articleTitle;

    @TableField("articleSource")
    private String articleSource;

    @TableField("articleAuthor")
    private String articleAuthor;

    @TableField("articleBrief")
    private String articleBrief;

    @TableField("articleContent")
    private String articleContent;

    @TableField("articleAddedTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private String articleAddedTime;

    @TableField("articleLastUpdateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private String articleLastUpdateTime;
}
