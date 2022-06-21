package utilities;

import java.sql.*;

public class defaultDBTest {





        public static void main(String[] args) throws SQLException {



            String url = "";

            Connection connection = DriverManager.getConnection(url, "", "");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("Select * from ");

            resultSet.next();
            String testAnswer = resultSet.getString("");

            System.out.println(testAnswer);

        }

    }
