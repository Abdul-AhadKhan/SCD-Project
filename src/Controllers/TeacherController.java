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
}
