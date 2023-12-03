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


    public List<Question> getQuestions(String category, String diffficulty) throws IOException, InterruptedException {

        var url = "https://quizapi.io/api/v1/questions?apiKey=024j93f95YcObn0lEvgqrHQVzqPtYtWepSCaL5wK&category=" + category + "&difficulty=" + diffficulty + "&limit=10";

        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 ) {

            ObjectMapper objectMapper = new ObjectMapper();
            List<Question> questions = objectMapper.readValue(response.body(), new TypeReference<List<Question>>() {
            });

            return questions;

        }

        return new ArrayList<Question>();
    }

    public boolean postQuiz(String title, String className, List<Question> questions) throws SQLException {

        String query = "INSERT INTO Quiz VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String correctAnswer = "";
        int result = 0;

        for (int i = 0; i < questions.size(); i++) {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, questions.get(i).question);
            preparedStatement.setString(4, questions.get(i).answers.answer_a);
            preparedStatement.setString(5, questions.get(i).answers.answer_b);
            preparedStatement.setString(6, questions.get(i).answers.answer_c);
            preparedStatement.setString(7, questions.get(i).answers.answer_d);
            preparedStatement.setString(8, questions.get(i).answers.answer_e);
            preparedStatement.setString(9, questions.get(i).answers.answer_f);
            correctAnswer = "";

            if (questions.get(i).correct_answers.answer_a_correct){
                correctAnswer = correctAnswer + "a";
            }
            if (questions.get(i).correct_answers.answer_b_correct){
                if (!correctAnswer.isEmpty()){
                    correctAnswer = correctAnswer + ",b";
                }
                else{
                    correctAnswer = correctAnswer + "b";
                }
            }
            if (questions.get(i).correct_answers.answer_c_correct){
                if (!correctAnswer.isEmpty()){
                    correctAnswer = correctAnswer + ",c";
                }
                else{
                    correctAnswer = correctAnswer + "c";
                }
            }
            if (questions.get(i).correct_answers.answer_d_correct){
                if (!correctAnswer.isEmpty()){
                    correctAnswer = correctAnswer + ",d";
                }
                else{
                    correctAnswer = correctAnswer + "d";
                }
            }
            if (questions.get(i).correct_answers.answer_e_correct){
                if (!correctAnswer.isEmpty()){
                    correctAnswer = correctAnswer + ",e";
                }
                else{
                    correctAnswer = correctAnswer + "e";
                }
            }
            if (questions.get(i).correct_answers.answer_f_correct){
                if (!correctAnswer.isEmpty()){
                    correctAnswer = correctAnswer + ",f";
                }
                else{
                    correctAnswer = correctAnswer + "f";
                }
            }
            preparedStatement.setString(10, correctAnswer);

            result = preparedStatement.executeUpdate();
        }

        return result > 0;
    }

    public boolean checkQuizTitle(String title, String className) throws SQLException {

        String query = "SELECT * FROM Quiz WHERE title = ? AND ClassName = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, className);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public HashMap<Integer, ArrayList<String>> getStudentComments(String title, String className) throws SQLException {

        String query = "SELECT * FROM Comments WHERE LectureTitle = ? AND ClassName = ? AND TeacherComment = FALSE ORDER BY Time DESC";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, className);
        resultSet = preparedStatement.executeQuery();

        HashMap<Integer, ArrayList<String>> comments = new HashMap<>();
        while (resultSet.next()){
            ArrayList<String> comment = new ArrayList<>();
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

    public int comment(String title, String className, String comment) throws SQLException {

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

        query = "INSERT INTO Comments (Id, Comment, LectureTitle, ClassName, Time, TeacherComment) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP(), ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, comment);
        preparedStatement.setString(3, title);
        preparedStatement.setString(4, className);
        preparedStatement.setBoolean(5, true);

        int result = preparedStatement.executeUpdate();

        if (result >= 1){
            return id;
        }
        else{
            return 0;
        }
    }

    public boolean checkReply(int commentId) throws SQLException {

        String query = "SELECT * FROM Replies WHERE Id = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, commentId);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public boolean reply(int commentId, String reply) throws SQLException {

        String query = "INSERT INTO Replies VALUES (?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, commentId);
        preparedStatement.setString(2, reply);

        int result = preparedStatement.executeUpdate();
        return result >= 1;

    }

    public HashMap<Integer, Integer> studentsJoinedClassroom(String className) throws SQLException {

        String query = "SELECT COUNT(*) AS StudentJoined, MONTH (DateJoined) AS Month FROM StudentInClass WHERE ClassName = ? GROUP BY MONTH (DateJoined)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);

        resultSet = preparedStatement.executeQuery();

        HashMap<Integer, Integer> studentsJoined = new HashMap<>();
        while (resultSet.next()){
            studentsJoined.put(resultSet.getInt("Month"), resultSet.getInt("StudentJoined"));
        }

        return studentsJoined;
    }

    public HashMap<Integer, Integer> rating(String className) throws SQLException {

        String query = "SELECT COUNT(*) AS Rating, Rating AS Value FROM RatingnReview WHERE ClassName = ? GROUP BY Rating";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, className);

        resultSet = preparedStatement.executeQuery();
        HashMap<Integer, Integer> rating = new HashMap<>();
        while (resultSet.next()){
            rating.put(resultSet.getInt("Value"), resultSet.getInt("Rating"));
        }
        return rating;
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
