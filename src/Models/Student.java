package Models;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student {
    private String UserName;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String url = Database.url;
    private String dbusername = Database.username;
    private String dbpassword = Database.password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Student() throws SQLException, ClassNotFoundException {
        UserName = "";
        FirstName = "";
        LastName = "";
        Email = "";
        Password = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, dbusername, dbpassword);
    }


    public boolean login() throws SQLException {

        String query = "SELECT * FROM Student WHERE UserName = ? AND Password = ?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, Password);

        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public HashMap<String, String> getClasses() throws SQLException {

        String query = "SELECT Classroom.ClassName ClassName, Classroom.TeacherName TeacherName FROM StudentInClass JOIN Classroom ON StudentInClass.ClassName = Classroom.ClassName WHERE StudentName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);
        resultSet = preparedStatement.executeQuery();
        HashMap<String, String> classes = new HashMap<>();
        while(resultSet.next()){
            classes.put(resultSet.getString("ClassName"), resultSet.getString("TeacherName"));
        }
        return classes;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public boolean checkUser() throws SQLException {

        String query = "SELECT * FROM Student WHERE UserName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);

        resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()){
            query = "SELECT * FROM Teacher WHERE UserName = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, UserName);

            resultSet = preparedStatement.executeQuery();
        }

        return resultSet.next();
    }
    public boolean register() throws SQLException {

        String query = "INSERT INTO Student VALUES (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, FirstName);
        preparedStatement.setString(3, LastName);
        preparedStatement.setString(4,Email);
        preparedStatement.setString(5, Password);

        int resultSet = preparedStatement.executeUpdate();

        return resultSet >= 1;
    }
}
