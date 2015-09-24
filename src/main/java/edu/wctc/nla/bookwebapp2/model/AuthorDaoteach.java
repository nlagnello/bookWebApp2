package edu.wctc.nla.bookwebapp2.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This DAO is a strategy object that uses a manual connection to a database. 
 * Such connections are not very performant so it's best to use a connection 
 * pool created on the app server. See ConnPoolAuthorDao.java for an 
 * example of a DAO that talks to such a pool.
 * 
 * @author jlombardo
 */
public class AuthorDaoteach implements AuthorDaoStrategyTeach {
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    public AuthorDaoteach(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public final List<AuthorTeach> getAllAuthors() throws Exception {
        db.openConnection(driverClass, url, userName, password);
        List<AuthorTeach> records = new ArrayList<>();

        List<Map<String,Object>> rawData = db.findAllRecords("author");
        for(Map rawRec : rawData) {
            AuthorTeach author = new AuthorTeach();
            Object obj = rawRec.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));
            
            String name = rawRec.get("author_name") == null ? "" : rawRec.get("author_name").toString();
            author.setAuthorName(name);
            
            obj = rawRec.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date)rawRec.get("date_added");
            author.setDateAdded(dateAdded);
            records.add(author);
        }
        
        // Actually closes connection
        db.closeConnection();
        
        return records;
        
    }
    
    // Test harness - not used in production
    public static void main(String[] args) throws Exception {
        AuthorDaoteach dao = new AuthorDaoteach(new MySqlDb(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
    
        List<AuthorTeach> authors = dao.getAllAuthors();
        for(AuthorTeach a : authors) {
            System.out.println(a);
        }
    }
}
