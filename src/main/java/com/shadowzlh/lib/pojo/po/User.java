package com.shadowzlh.lib.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("user")
public class User {
    private Integer userId;
    @TableField
    @MppMultiId
    private String username;
    
    private String password;
    
    private String email;

    public User(String username,String password,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return userId;
    }
}

