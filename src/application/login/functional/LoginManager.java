package application.login.functional;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager {
    private DatabaseConnection databaseConnection;

    public LoginManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public int authenticateUser(DatabaseConnection databaseConnection, String enteredEmail, String enteredPassword) throws SQLException {
        int role = 0;
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT idRole FROM utilisateurs WHERE nomUtilisateur = ? AND motdepasse = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, enteredEmail);
        preparedStatement.setString(2, enteredPassword);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            role = resultSet.getInt("idRole");
        resultSet.close();
        preparedStatement.close();
        return role;
    }
}
