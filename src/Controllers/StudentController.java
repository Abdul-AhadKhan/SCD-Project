package Controllers;


import Models.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentController {
    Student student;

    public StudentController() throws SQLException, ClassNotFoundException {
        student = new Student();
    }

    public boolean login(String username, String password) throws SQLException {

        student.setUserName(username);
        student.setPassword(password);

        return student.login();
    }
    public boolean register(String username, String firstname, String lastname, String email, String password) throws SQLException {

        student.setUserName(username);
        student.setFirstName(firstname);
        student.setLastName(lastname);
        student.setEmail(email);
        student.setPassword(password);

        if (!student.checkUser()){
            return student.register();
        }
        return false;
    }

    public HashMap<String, String> getClasses(String username) throws SQLException {
        student.setUserName(username);
        return student.getClasses();
    }


    public ArrayList<ArrayList<String>> getAllClasses(String studentName) throws SQLException {

        student.setUserName(studentName);
        return student.getAllClasses();
    }

    public boolean checkAlreadyJoined(String studentName, String className) throws SQLException {

        student.setUserName(studentName);
        return student.checkAlreadyJoined(className);
    }

    public boolean joinClass(String studentName, String className) throws SQLException {

        student.setUserName(studentName);
        return student.joinClass(className);
    }

    public HashMap<String, String> getLectures(String className) throws SQLException {
        return student.getLectures(className);
    }

    public byte[] getLecture(String title, String className) throws SQLException {
        return student.getLecture(title, className);
    }


    public boolean checkReiew(String studentName, String className) throws SQLException {

        student.setUserName(studentName);
        return student.checkReview(className);
    }

    public boolean submitReview(String studentName, String className, int rating, String review) throws SQLException {

        student.setUserName(studentName);
        return student.submitReview(className, rating, review);
    }

    public ArrayList<String> getQuizes(String className) throws SQLException {
        return student.getQuizes(className);
    }

    public List<Question> getQuestions(String title, String className) throws SQLException {
        return student.getQuestions(title, className);
    }
    public HashMap<Integer, ArrayList<String>> getStudentComments(String title, String className) throws SQLException {
        return student.getStudentComments(title, className);
    }

    public HashMap<Integer, String> getTeacherComments(String title, String className) throws SQLException {
        return student.getTeacherComments(title, className);
    }

    public String getReply(int id) throws SQLException {
        return student.getReply(id);
    }

    public int comment(String username, String title, String className, String comment) throws SQLException {
        return student.comment(username, title, className, comment);
    }
}
