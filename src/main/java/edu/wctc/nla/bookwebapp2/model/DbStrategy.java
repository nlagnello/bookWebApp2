/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nla.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Nick
 */
public interface DbStrategy {
    
    void closeConnection() throws SQLException;
    
    void openConnection(DataSource ds) throws Exception;
    
    void openConnection(String driverClass, String url, String userName, String password) throws Exception;

    void deleteByPrimaryKey(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException;
    
    void deleteByPrimaryKeyPrepareStatement(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException;
    
    void createRecordPrepareStatement(String tableName, List newRecordCols, List newRecordValues) throws SQLException;
    
    void createRecord(String tableName, List newRecordCols, List newRecordValues) throws SQLException;
    
    void updateByPrimaryKey(String tableName, String primaryKey, Object primaryKeyValue, List updateRecordCols, List updateRecordValues) throws SQLException;

    void updateByPrimaryKeyPrepareStatement(String tableName, String primaryKey, Object primaryKeyValue, List updateRecordCols, List updateRecordValues) throws SQLException;
    
    List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;
}
