import application.Application;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            new Application().setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}