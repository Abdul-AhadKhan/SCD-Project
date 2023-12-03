package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Teacher {
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



    public Teacher() throws SQLException, ClassNotFoundException {
        UserName = "";
        FirstName = "";
        LastName = "";
        Email = "";
        Password = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, dbusername, dbpassword);
    }

    public boolean login() throws SQLException {

        String query = "SELECT * FROM Teacher WHERE UserName = ? AND Password = ?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, Password);

        resultSet = preparedStatement.executeQuery();

        return resultSet.next();


    }

    public boolean checkUser() throws SQLException {

        String query = "SELECT * FROM Teacher WHERE UserName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);

        resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()){

            query = "SELECT * FROM Student WHERE UserName = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, UserName);

            resultSet = preparedStatement.executeQuery();
        }
        return resultSet.next();
    }

    public boolean register() throws SQLException {

        String query = "INSERT INTO Teacher VALUES (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, FirstName);
        preparedStatement.setString(3, LastName);
        preparedStatement.setString(4,Email);
        preparedStatement.setString(5, Password);

        int resultSet = preparedStatement.executeUpdate();

        return resultSet >= 1;
    }
    public ArrayList<String> getClasses() throws SQLException {

        String query = "SELECT ClassName FROM Classroom WHERE TeacherName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);
        resultSet = preparedStatement.executeQuery();
        ArrayList<String> classes = new ArrayList<>();
        while(resultSet.next()){
            classes.add(resultSet.getString("ClassName"));
        }
        return classes;
    }
    public boolean checkClassName(String className) throws SQLException {

        String query = "SELECT * FROM Classroom WHERE ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);

        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public boolean createClass(String className, String description) throws SQLException {

        String query = "INSERT INTO Classroom VALUES (?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, this.UserName);
        preparedStatement.setString(2, className);
        preparedStatement.setString(3, description);
        int result = preparedStatement.executeUpdate();

        return result >= 1;
    }

    public HashMap<String, String> getLectures(String className) throws SQLException {

        String query = "SELECT Title, Description FROM Lecture WHERE ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);
        resultSet = preparedStatement.executeQuery();

        HashMap<String, String> lectures = new HashMap<>();
        while(resultSet.next()){
            lectures.put(resultSet.getString("Title"), resultSet.getString("Description"));
        }
        return lectures;
    }

    public boolean checkLectureTitle(String className, String title) throws SQLException {

        String query = "SELECT * FROM Lecture WHERE ClassName = ? AND Title = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);
        preparedStatement.setString(2, title);

        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public boolean uploadLecture(String className, String title, String description, byte[] fileData) throws SQLException {

        String query = "INSERT INTO Lecture VALUES (?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, description);
        preparedStatement.setBytes(4, fileData);
        int result = preparedStatement.executeUpdate();

        return result >= 1;
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
}
