package application.login.functional;

import database.DatabaseConnection;

public class LoginManager {
    private DatabaseConnection databaseConnection;

    public LoginManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
