package com.shadowzlh.lib.common;

import com.shadowzlh.lib.pojo.po.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class OnlineUserMap {
    // 账号 session
    public static HashMap<String, HttpSession> onlineUserMap = new HashMap<>();
}
