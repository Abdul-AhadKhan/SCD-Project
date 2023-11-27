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

}
