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

    public ArrayList<ArrayList<String>> getAllClasses() throws SQLException {

        String query = "SELECT * FROM Classroom WHERE ClassName NOT IN (SELECT ClassName FROM StudentInClass WHERE StudentName = ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);

        resultSet = preparedStatement.executeQuery();
        ArrayList<ArrayList<String>> classes = new ArrayList<>();

        while (resultSet.next()){
            ArrayList<String> classDetails = new ArrayList<>();
            classDetails.add(resultSet.getString("TeacherName"));
            classDetails.add(resultSet.getString("ClassName"));
            classDetails.add(resultSet.getString("Description"));
            classes.add(classDetails);
        }
        return classes;
    }


    public boolean checkAlreadyJoined(String className) throws SQLException {

        String query = "SELECT * FROM StudentInClass WHERE StudentName = ? AND ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, className);

        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public boolean joinClass(String className) throws SQLException {

        String query = "INSERT INTO StudentInClass VALUES (?, ?, CURDATE())";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, className);


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


    public byte[] getLecture(String title, String className) throws SQLException {

        String query = "SELECT Video FROM Lecture WHERE ClassName = ? AND Title = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);
        preparedStatement.setString(2, title);
        resultSet = preparedStatement.executeQuery();

        byte[] videoData;
        if (resultSet.next()){
            videoData = resultSet.getBytes("Video");
            return videoData;
        }
        return  null;
    }


    public boolean checkReview(String className) throws SQLException {

        String query = "SELECT * FROM RatingnReview WHERE StudentName = ? AND ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, this.UserName);
        preparedStatement.setString(2, className);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next();

    }

    public boolean submitReview (String className, int rating, String review) throws SQLException {

        String query = "INSERT INTO RatingnReview VALUES (?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, this.UserName);
        preparedStatement.setString(2, className);
        preparedStatement.setInt(3, rating);
        preparedStatement.setString(4, review);
        int result = preparedStatement.executeUpdate();

        return result >= 1;

    }

    public HashMap<Integer, ArrayList<String>> getStudentComments(String title, String className) throws SQLException {

        String query = "SELECT * FROM Comments WHERE LectureTitle = ? AND ClassName = ? AND TeacherComment = FALSE ORDER BY Time DESC";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, className);
        resultSet = preparedStatement.executeQuery();

        HashMap<Integer, ArrayList<String>> comments = new HashMap<>();
        while (resultSet.next()){
            ArrayList<String> comment = new ArrayList<String>();
            comment.add(resultSet.getString("UserName"));
            comment.add(resultSet.getString("Comment"));
            comments.put(resultSet.getInt("Id"), comment);
        }
        return comments;
    }

    public HashMap<Integer, String> getTeacherComments(String title, String className) throws SQLException {

        String query = "SELECT * FROM Comments WHERE LectureTitle = ? AND ClassName = ? AND TeacherComment = TRUE ORDER BY Time DESC";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, className);
        resultSet = preparedStatement.executeQuery();

        HashMap<Integer, String> comments = new HashMap<>();
        while (resultSet.next()){
            comments.put(resultSet.getInt("Id"), resultSet.getString("Comment"));
        }
        return comments;
    }

    public String getReply(int id) throws SQLException {

        String query = "SELECT * FROM Replies WHERE Id = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("Reply");
        }
        else{
            return "";
        }
    }

    public int comment(String username, String title, String className, String comment) throws SQLException {

        String query = "SELECT MAX(Id) AS Id FROM Comments";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()){
            id = resultSet.getInt("Id") + 1;
        }
        else{
            id = 1;
        }

        query = "INSERT INTO Comments VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP(), ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, comment);
        preparedStatement.setString(4, title);
        preparedStatement.setString(5, className);
        preparedStatement.setBoolean(6, false);

        int result = preparedStatement.executeUpdate();

        if (result >= 1){
            return id;
        }
        else{
            return 0;
        }
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


    public ArrayList<String> getQuizes(String className) throws SQLException {

        String query = "SELECT DISTINCT Title FROM Quiz WHERE ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);
        resultSet = preparedStatement.executeQuery();

        ArrayList<String> quizes = new ArrayList<>();
        while (resultSet.next()){
            quizes.add(resultSet.getString("Title"));
        }
        return quizes;
    }

    public List<Question> getQuestions(String title, String className) throws SQLException {

        String query = "SELECT * FROM Quiz WHERE Title = ? AND ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, className);
        resultSet = preparedStatement.executeQuery();

        List<Question> questions = new ArrayList<>();

        while (resultSet.next()){

            Question question = new Question();
            question.question = resultSet.getString("Question");
            question.answers.answer_a = resultSet.getString("OptionA");
            question.answers.answer_b = resultSet.getString("OptionB");
            question.answers.answer_c = resultSet.getString("OptionC");
            question.answers.answer_d = resultSet.getString("OptionD");
            question.answers.answer_e = resultSet.getString("OptionE");
            question.answers.answer_f = resultSet.getString("OptionF");
            String[] correctOptions = resultSet.getString("CorrectOption").split(",");
            for (int i = 0; i < correctOptions.length; i++){
                if (correctOptions[i].trim().equalsIgnoreCase("a")){
                    question.correct_answers.answer_a_correct = true;
                }
                else if (correctOptions[i].trim().equalsIgnoreCase("b")){
                    question.correct_answers.answer_b_correct = true;
                }
                else if (correctOptions[i].trim().equalsIgnoreCase("c")){
                    question.correct_answers.answer_c_correct = true;
                }
                else if (correctOptions[i].trim().equalsIgnoreCase("d")){
                    question.correct_answers.answer_d_correct = true;
                }
                else if (correctOptions[i].trim().equalsIgnoreCase("e")){
                    question.correct_answers.answer_e_correct = true;
                }
                else if (correctOptions[i].trim().equalsIgnoreCase("f")){
                    question.correct_answers.answer_f_correct = true;
                }
            }
            questions.add(question);
        }
        return questions;
    }

}
