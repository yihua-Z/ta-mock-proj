package com.psbc.mapper.impl;

import com.psbc.mapper.FileDAO;
import com.psbc.pojo.TableModel;
import com.psbc.utils.jdbc.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class FileDAOImpl implements FileDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

/*    @Override
    public void createTable(TableModel clazz) {

    }*/

    @Override
    public void addRecord(TableModel record) {
        String sql = "";
        template.update(sql);
    }
}