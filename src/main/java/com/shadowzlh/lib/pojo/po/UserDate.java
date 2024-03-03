package com.shadowzlh.lib.pojo.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.AutoMap;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userDate")
public class UserDate {
    //date
    @TableField("date")
    @MppMultiId
    private Date date;
    //userid
    @TableField("user_id")
    @MppMultiId
    private Integer userId;
}

