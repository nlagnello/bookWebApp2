package edu.wctc.nla.bookwebapp2.model;

import java.util.List;

/**
 * The high-level class in the DIP acts as a service to the rest of the app.
 * It depends on one or more strategy objects (DAOs) to delegate the work of 
 * accessing data on the database.
 * 
 * @author jlombardo
 */
public class AuthorServiceTeach {
    private AuthorDaoStrategyTeach dao;


    public AuthorServiceTeach(AuthorDaoStrategyTeach dao) {
        this.dao = dao;

    }
    
    public final List<AuthorTeach> getAllAuthors() throws Exception {
        return dao.getAllAuthors();
    }
    
    // Test harness - not used in production
    public static void main(String[] args) throws Exception {
        AuthorServiceTeach authServ = new AuthorServiceTeach(
                new AuthorDaoteach(new MySqlDb(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin")
        );
        
        List<AuthorTeach> authors = authServ.getAllAuthors();
        for(AuthorTeach a: authors) {
            System.out.println(a);
        }
    }
}
