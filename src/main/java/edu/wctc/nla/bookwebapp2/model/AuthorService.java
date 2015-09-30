/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nla.bookwebapp2.model;

import java.util.List;

/**
 *
 * @author Nick
 */
public class AuthorService {
    private AuthorDAO dao;

    public AuthorService(AuthorDAO dao) {
        this.dao = dao;
    }
    
    public List<Author> getAllAuthors() throws Exception{
        return dao.getAllAuthors();
    }
    
    public List<Author> deleteByPrimaryKeyPrepareStatement(String tableName, String primaryKey, Object primaryKeyValue) throws Exception{
        return dao.deleteByPrimaryKeyPrepareStatement(tableName, primaryKey, primaryKeyValue);
    }
    public List<Author> updateByPrimaryKeyPrepareStatement(String tableName, String primaryKey, Object primaryKeyValue, List updateRecordCols, List updateRecordValues) throws Exception{
        return dao.updateByPrimaryKeyPrepareStatement(tableName, primaryKey, primaryKeyValue, updateRecordCols, updateRecordValues);
    }
    public List<Author> createRecordPrepareStatement(String tableName, List newRecordCols, List newRecordValues) throws Exception{
        return dao.createRecordPrepareStatement(tableName, newRecordCols, newRecordValues);
    }
}
