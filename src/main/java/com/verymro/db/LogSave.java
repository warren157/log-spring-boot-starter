package com.verymro.db;

import com.verymro.prop.LogDbProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author rui.wang
 * @version 1.0
 * @description: TODO
 * @date 2021/6/23 9:36
 */

@Component
public class LogSave {



    private JdbcTemplate jdbcTemplate;

    LogDbProperty property;

    public LogSave(JdbcTemplate jdbcTemplate,LogDbProperty property){
        this.jdbcTemplate =jdbcTemplate;
        this.property = property;
    }
    public void  saveLog(String desc) {
        if(property.getTableName()!=null && property.getColumn()!=null){

            String sql = "insert into "+property.getTableName()+" (`"+property.getColumn()+"`) values ('"+desc+"')";
            System.out.println("sql = "+sql);
            jdbcTemplate.batchUpdate(sql);
        }
    }
}
