# SCD-Project---------TECH TEACH--------------

**Quickstart**

1) Clone the repository
   
2) Open the project in your IDE: IntelliJ IDEA (recommended) or Eclipse

3) To Run The Project Fine:
  ->  You need to write your own database username and password in the database class
  ->  Also You need to run the sql script on your machine  which is given in the project 
   

**DATABASE**

MySQL can be used as the database for this project. The database connection can be configured in the Database file in Models,
with the appropriate values for the following properties:

public class Database {
    public static final String url = "jdbc:mysql://localhost:3306/techteach";
    public static final String username = "your_username";
    public static final String password = "your_password";

}
