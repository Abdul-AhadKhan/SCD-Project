package Controllers;


import Models.Teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherController {

    private Teacher teacher;
    public TeacherController() throws SQLException, ClassNotFoundException {
        teacher = new Teacher();
    }
    public boolean login(String username, String password) throws SQLException {

        teacher.setUserName(username);
        teacher.setPassword(password);

        return teacher.login();
    }

    public ArrayList<String> getClasses(String username) throws SQLException {

        teacher.setUserName(username);
        return teacher.getClasses();
    }
    public boolean register(String username, String firstname, String lastname, String email, String password) throws SQLException {

        teacher.setUserName(username);
        teacher.setFirstName(firstname);
        teacher.setLastName(lastname);
        teacher.setEmail(email);
        teacher.setPassword(password);

        if (!teacher.checkUser()){
            return teacher.register();
        }

        return false;
    }
    public boolean checkClassName(String teacherName, String className) throws SQLException {

        teacher.setUserName(teacherName);
        return teacher.checkClassName(className);
    }

    public boolean createClass(String teacherName, String className, String description) throws SQLException {

        teacher.setUserName(teacherName);
        return teacher.createClass(className, description);
    }

    public HashMap<String, String> getLectures(String className) throws SQLException {
        return teacher.getLectures(className);
    }


    public boolean checkQuizTitle(String title, String className) throws SQLException {
        return teacher.checkQuizTitle(title, className);
    }
    public List<Question> getQuestions(String category, String difficulty) throws IOException, InterruptedException {
        return teacher.getQuestions(category, difficulty);
    }

    public boolean postQuiz(String title, String className, List<Question> questions) throws SQLException {
        return teacher.postQuiz(title, className, questions);
    }

}
