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

}
