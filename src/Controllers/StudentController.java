package Controllers;


import Models.Student;

import java.sql.SQLException;

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


}
