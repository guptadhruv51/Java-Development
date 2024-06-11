package com.example.demodb.repository;

import com.example.demodb.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

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
    // using constructor injection to lad dependencies on startup of the application
    // if we do it at the class level, will not be able to resolve before calling the constructor
    // we should not do field injection if we want to work with the dependency in the constructor itself, should use constructor injection.
    BookRepository(@Value("${db.url}") String url,
                   @Value("${db.username}") String username,
                   @Value("${db.password}") String password) throws SQLException {
        this.url=url;
        this.username=username;
        this.password=password;
        createTable();
    }
    private Connection getConnection()  {
        if(connection==null)
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
                "author_email varchar(50),author_country varchar(30),genre varchar(30), created_on date, updated_on date)";

        Statement statement=connection.createStatement();
        statement.execute(sql);
        //.execute(String): Will return true when we are getting some data, false when we are updating/adding new data
    }
    public void create(Book book) throws SQLException {
        String sql="INSERT INTO book (name,author_name,author_email,author_country,genre,create_on,updated_on) " +
                "VALUES (?,?,?,?,?,?,?)";
        /**
         * "INSERT INTO book (name,author_name,author_email,author_country,genre,create_on,updated_on) " +
         *                 "VALUES (book.getName(),book.getAuthorName(),?,?,?,?)"
         *
         * prepared statement vs. normal placeholder (static query)
         * The SQL server will first compile the query and then execute the compiled query.
         * In the case of a prepared statement, the query is cached and saved.
         * So the compilation time will be less for future queries.
         * Optimises the compilation cost and API calls (less latent), dynamic query
         *
         */
        PreparedStatement preparedStatement=getConnection().prepareStatement(sql);
        preparedStatement.setString(1,book.getName());
        preparedStatement.setString(2,book.getAuthorName());
        preparedStatement.setString(3,book.getAuthorEmail());
        preparedStatement.setString(4,book.getAuthorCountry());
        preparedStatement.setString(5,book.getGenre().name()); //.name is being used because Genre is an enum .name(): converts enum to string
        preparedStatement.setDate(6, new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(7,new Date(book.getUpdatedOn().getTime()));
        preparedStatement.execute();

    }
}
