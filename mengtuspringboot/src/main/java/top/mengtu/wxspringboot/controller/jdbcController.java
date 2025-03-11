package top.mengtu.wxspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class jdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //写一个list请求，查询数据库信息
    @GetMapping("/list")
    public List<Map<String, Object>> list() {

        String sql = "select * from wx_users";
        List<Map<String, Object>> list_map = jdbcTemplate.queryForList(sql);
        return list_map;

    }

}

