package com.shadowzlh.lib.pojo.po;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSeatstr {
    @MppMultiId
    private Integer userId;
    @MppMultiId
    private String seatstr;
}

