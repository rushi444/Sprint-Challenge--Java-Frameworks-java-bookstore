package com.lambdaschool.starthere.models;


import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @Column(name = "lname")
    private String lastname;

    @Column(name = "fname")
    private String firstname;

    public Author(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
