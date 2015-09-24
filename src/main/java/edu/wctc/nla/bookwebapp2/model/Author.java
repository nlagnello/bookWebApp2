/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nla.bookwebapp2.model;

import java.util.Date;

/**
 *
 * @author Nick
 */
public class Author {
    
    private String authorName;
    private int authorId;
    private Date dateCreated;

    public Author(String authorName, int authorId, Date dateCreated) {
        this.authorName = authorName;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
    }

    public Author(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.authorId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author2{" + "authorName=" + authorName + ", authorId=" + authorId + ", dateCreated=" + dateCreated + '}';
    }
    
    
}
