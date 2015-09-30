/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nla.bookwebapp2.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nick
 */
public class AuthorDAO {
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDAO(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    public List<Author> getAllAuthors() throws Exception{
        db.openConnection(driverClass, url, userName, password);
        List<Author> records = new ArrayList();
        List<Map<String,Object>> data = db.findAllRecords("author");
        
        for(Map record : data){
            Author author = new Author();
            Object obj = record.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));
            
            obj =  record.get("author_name");
            author.setAuthorName(obj.toString());
            
            obj = record.get("date_created");
            Date dateCreated = (Date)obj;
            author.setDateCreated(dateCreated);    
            
            records.add(author);
        }
        db.closeConnection();
        return records;
    }
    
    public List<Author> deleteByPrimaryKeyPrepareStatement(String tableName, String primaryKey, Object primaryKeyValue) throws Exception{
        db.openConnection(driverClass, url, userName, password);
        db.deleteByPrimaryKeyPrepareStatement(tableName, primaryKey, primaryKeyValue);
        List<Author> authors = getAllAuthors();
        db.closeConnection();
        return authors;
    }
    
    public List<Author> updateByPrimaryKeyPrepareStatement(String tableName, String primaryKey, Object primaryKeyValue, List updateRecordCols, List updateRecordValues) throws Exception{
        db.openConnection(driverClass, url, userName, password);
        db.updateByPrimaryKey(tableName, primaryKey, primaryKeyValue, updateRecordCols, updateRecordValues);
        List<Author> authors = getAllAuthors();
        db.closeConnection();
        return authors;
    }
    public List<Author> createRecordPrepareStatement(String tableName, List newRecordCols, List newRecordValues) throws Exception{
        db.openConnection(driverClass, url, userName, password);
        db.createRecordPrepareStatement(tableName, newRecordCols, newRecordValues);
        List<Author> authors = getAllAuthors();
        db.closeConnection();
        return authors;
    }
}
