package top.mengtu.wxspringboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "sound_info")
@Data
public class SoundInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    @TableField(value = "SoundIndex")
    private String SoundIndex;

    @TableField(value = "SoundTitle")
    private String SoundTitle;

    @TableField(value = "SoundSoundUrl")
    private String SoundSoundUrl;

    @TableField(value = "SoundPicUrl")
    private String SoundPicUrl;

    @TableField(value = "SoundPlayedCount")
    private long SoundPlayedCount;
}
