package com.shadowzlh.lib.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.AutoMap;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userArea")
public class UserArea {
    //userid
    @TableField("user_id")
    @MppMultiId
    private Integer userId;
    //area
    @TableField("area")
    @MppMultiId
    private String area;
}

