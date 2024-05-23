package com.example.demodb.repository;

import com.example.demodb.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Nothing except for database creation should be done at the server side
 * Database creation: Manual/code
 * Table creation: Using code (ORM: Object relation mapping)
 * Migrations: In the code
 */

@Repository // inherits component
public class BookRepository
{
    private String url;
    private String username;
    private String password;
    private static Connection connection=null;

    // acting like a bean
    BookRepository(@Value("${db.url}") String url,
                   @Value("${db.username}") String username,
                   @Value("${db.password}") String password) throws SQLException {
        this.url=url;
        this.username=username;
        this.password=password;
        createTable();
    }
    private Connection getConnection()  {
        if(this.connection==null)
        {
            try {
                connection = DriverManager.getConnection(this.url, this.username, this.password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }
    private void createTable() throws SQLException {
        Connection connection=getConnection();
        String sql="Create table if not exists book(id int primary key auto_increment, name varchar(50) not null,author_name varchar(50)," +
                "author_email varchar(50),author_country varchar(30),genre varchar(30), created_on timestamp, updated_on timestamp)";

        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
}
