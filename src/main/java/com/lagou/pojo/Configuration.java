package com.lagou.pojo;

import lombok.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Data
public class Configuration {

    public static final String UPDATE = "update";
    public static final String INSERT = "insert";
    public static final String DELETE = "delete";
    public static final String SELECT = "select";
    private DataSource dataSource;

    /*
    *   key: statementid  value:封装好的mappedStatement对象
     * */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

}
