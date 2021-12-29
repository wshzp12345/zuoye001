package com.lagou.pojo;

import lombok.*;

@Data
public class MappedStatement {

    //id标识
    private String id;
    //返回值类型
    private String resultType;
    //参数值类型
    private String paramterType;
    //sql语句
    private String sql;
    // sql 类型
    private String sqlType;

}
