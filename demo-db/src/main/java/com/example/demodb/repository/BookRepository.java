package com.example.demodb.repository;

import com.example.demodb.models.Book;
import com.example.demodb.models.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql="INSERT INTO book (name,author_name,author_email,author_country,genre,created_on,updated_on) " +
                "VALUES (?,?,?,?,?,?,?)";
        /**
         * "INSERT INTO book (name,author_name,author_email,author_country,genre,create_on,updated_on) " +
         *                 "VALUES (book.getName(),book.getAuthorName(),?,?,?,?)"
         *
         * Prepared statement vs. normal placeholder (static query)
         * The SQL server will first compile the query and then execute the compiled query.
         * In the case of a prepared statement, the query is cached and saved.
         * So the compilation time will be less for future queries.
         * Optimises the compilation cost and API calls (less latent), dynamic query
         *
         */
        PreparedStatement preparedStatement=getConnection().prepareStatement(sql);
        //indexing starts with 1 not 0
        preparedStatement.setString(1,book.getName());
        preparedStatement.setString(2,book.getAuthorName());
        preparedStatement.setString(3,book.getAuthorEmail());
        preparedStatement.setString(4,book.getAuthorCountry());
        preparedStatement.setString(5,book.getGenre().name()); //.name is being used because Genre is an enum .name(): converts enum to string
        preparedStatement.setDate(6, new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(7,new Date(book.getUpdatedOn().getTime()));
        preparedStatement.execute();

    }

    public List<Book> getAll() throws SQLException
    {
        List<Book> bookList=new ArrayList<>();
        String sql="Select * from book";
        Statement statement=this.getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql); //ExecuteQuery gives you the data
        //ResultSet: rows and columns (iterator)
        while(result.next())
        {
            // object relation mapping
            //java to sql mapping
            String name=result.getString("name"); //column label or column index
            String authorName=result.getString("author_name");
            String authorEmail=result.getString("author_email");
            String authorCountry=result.getString("author_country");
            Genre genre= Genre.valueOf(result.getString("genre"));
            int id=result.getInt("id");
            java.util.Date createdOn=result.getDate("created_on");
            java.util.Date updatedOn=result.getDate("updated_on");
            /**
             * Column label
             * Changes in column name will be an issue.
             * Column Index
             * Addition or deletion of columns will lead to wrong column numbering
             */
            Book book=Book.builder()
                    .name(name)
                    .id(id)
                    .authorName(authorName)
                    .authorEmail(authorEmail)
                    .authorCountry(authorCountry)
                    .genre(genre)
                    .createdOn(createdOn)
                    .updatedOn(updatedOn)
                    .build();
            bookList.add(book);

        }
        return bookList;

    }
    public Book getById(int bookId) throws SQLException {

        String sql="Select * from book where id=?";
        PreparedStatement preparedStatement=this.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,bookId);
        ResultSet result =preparedStatement.executeQuery();
        //ExecuteQuery gives you the data
        //ResultSet: rows and columns (iterator)

            // object relation mapping
            //java to sql mapping
        while(result.next()) {
            String name = result.getString("name"); //column label or column index
            String authorName = result.getString("author_name");
            String authorEmail = result.getString("author_email");
            String authorCountry = result.getString("author_country");
            Genre genre = Genre.valueOf(result.getString("genre"));
            int id = result.getInt("id");
            java.util.Date createdOn = result.getDate("created_on");
            java.util.Date updatedOn = result.getDate("updated_on");
            /**
             * Column label
             * Changes in column name will be an issue.
             * Column Index
             * Addition or deletion of columns will lead to wrong column numbering
             */
            return Book.builder()
                    .name(name)
                    .id(id)
                    .authorName(authorName)
                    .authorEmail(authorEmail)
                    .authorCountry(authorCountry)
                    .genre(genre)
                    .createdOn(createdOn)
                    .updatedOn(updatedOn)
                    .build();

        }
return null;
    }

    public boolean deletebyId(int id) throws SQLException
    {
        Connection connection=getConnection();
        String sql="DELETE from book where id="+id;

        Statement statement=connection.createStatement();
        return statement.execute(sql);

//        String sql="Delete from book where id=?";
//        PreparedStatement preparedStatement=this.getConnection().prepareStatement(sql);
//        preparedStatement.setInt(1,id);
//        return preparedStatement.execute();
    }
}
