package edu.wctc.nla.bookwebapp2.model;

import java.util.List;

/**
 *
 * @author jlombardo
 */
public interface AuthorDaoStrategy {

    List<Author> getAllAuthors() throws Exception;
    
}
