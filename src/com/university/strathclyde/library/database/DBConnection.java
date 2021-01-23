package com.university.strathclyde.library.database;

import com.mysql.cj.jdbc.Driver;
import com.university.strathclyde.library.LibraryMember;
import com.university.strathclyde.library.ProgramConstant;
import com.university.strathclyde.library.resource.LibraryResource;
import com.university.strathclyde.library.resource.PhysicalBook;
import strathclyde.core.Utility;

import java.sql.*;

public class DBConnection implements IDatabaseFunction {

    public DBConnection() {

    }

    private Connection getDbConnection() {
        String url = "jdbc:mysql://localhost:3306/LibraryManagement";
        String user = "test";
        String password = "test";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }

    public void addLibraryMember(LibraryMember newLibraryMember) {
        String sql = "insert into library_memeber(member_id, first_name, last_name, address, emailId, user_password) " +
                "values(?,?,?,?,?,?)";

        Connection connection = getDbConnection();
        try {
            System.out.println(newLibraryMember);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(newLibraryMember.getMemberId()));
            preparedStatement.setString(2, newLibraryMember.getFirstName());
            preparedStatement.setString(3, newLibraryMember.getLastName());
            preparedStatement.setString(4, newLibraryMember.getAddress());
            preparedStatement.setString(5, newLibraryMember.getEmailId());
            preparedStatement.setString(6, newLibraryMember.getPassword());
            int result = preparedStatement.executeUpdate();
            System.out.println(result);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ResultSet getAllLoginDetails() {
        Connection connection = getDbConnection();
        String loginDetailsSql = "select " + ProgramConstant.EMAILID_COLUMN + ", user_password from library_memeber;";
        ResultSet loginDetails = null;
        try {
            Statement loginDetailsStatement = connection.createStatement();
            loginDetails = loginDetailsStatement.executeQuery(loginDetailsSql);

        } catch (SQLException sqlException) {
            System.out.println("Sql Exception while retrieving login details.");
            sqlException.printStackTrace();
        }
        return loginDetails;
    }


    public ResultSet getLibraryMember() {
        String sql = "Select * from library_memeber;";
        Connection connection = getDbConnection();
        ResultSet resultSet = null;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.out.println("Exeception while closing a connection");
                sqlException.printStackTrace();
            }

        }
        return resultSet;
    }

    public void addABook(PhysicalBook physicalBook) {
        Connection connection = getDbConnection();
        String sqlToAddABook = "insert into physical_book(isbn, resource_id, resource_name, author_name, book_title, member_Id)" +
                                 "values(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlToAddABook);
            preparedStatement.setString(1,physicalBook.getIsbn());
            preparedStatement.setString(2,physicalBook.getResourceId());
            preparedStatement.setString(3,physicalBook.getResourceName());
            preparedStatement.setString(4,physicalBook.getAuthorName());
            preparedStatement.setString(5,physicalBook.getTitle());
            String memberId = "";
            if (physicalBook.getIssuedTo() != null) {
                memberId = String.valueOf(physicalBook.getIssuedTo().getMemberId());
            }
            preparedStatement.setString(6, memberId);
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public ResultSet checkBookAvailable(final String isbn) {
        Connection connection = getDbConnection();
        ResultSet resultSet = null;
        try {
            CallableStatement callableStatement = connection.prepareCall("call LibraryManagement.book_available(?)");
            callableStatement.setString(1, isbn);
            resultSet = callableStatement.executeQuery();
            System.out.println("Size of result set is " + resultSet.getRow());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return resultSet;
    }

}

