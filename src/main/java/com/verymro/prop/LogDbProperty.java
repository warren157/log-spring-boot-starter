package com.verymro.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author rui.wang
 * @version 1.0
 * @description: TODO
 * @date 2021/6/23 9:27
 */
@ConfigurationProperties(prefix = "mylog")
public class LogDbProperty {


    private String tableName;

    private String column;



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "LogDbProperty{" +
                "tableName='" + tableName + '\'' +
                ", column='" + column + '\'' +
                '}';
    }
}
