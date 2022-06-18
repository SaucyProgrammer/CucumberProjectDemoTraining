package utilities;

import java.sql.DriverManager;
import java.sql.SQLException;


public class TestJDBC {





        public static void main(String[] args) throws SQLException {



            String url = "jdbc:mysql://db-duotech.cc652zs7kmja.us-east-2.rds.amazonaws.com/";

            DriverManager.getConnection(url, "duotech", "duotech2021");

            System.out.println("Connection established");

        }

    }
