package com.shadowzlh.lib.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userSeatStr")
public class UserSeatstr {
    @MppMultiId
    private Integer userId;
    @MppMultiId
    private String seatstr;
}

