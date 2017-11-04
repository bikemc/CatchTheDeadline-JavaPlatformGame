package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBInterface {

    // Variables
    private static Connection con;

    // Getting connection to the database and create it
    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:scores.db");
        initializeAccounts();
    }

    // Initialize Accounts' Table
    private void initializeAccounts() throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS accounts ( id integer,"
                +"username varchar(48), pasw varchar(48), scores integer,"
                + "primary key (id));");
        //Statement state3 = con.createStatement();
        //state3.execute("INSERT INTO accounts (id, username, pasw) VALUES (1, 'admin', 'admin1')");
    }

    // Add Account
    public void createUser (String username, String pasw) throws ClassNotFoundException, SQLException{
        if (con == null) {
            getConnection();
        }
        PreparedStatement prep = con.prepareStatement("INSERT INTO accounts (id, username, pasw) VALUES(?,?,?);");
        prep.setString(2, username);
        prep.setString(3, pasw);
        prep.executeUpdate();
        prep.close();
    }

    // Password checker
    public ResultSet checkPasw(String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT username, pasw FROM accounts WHERE username = '" + username + "';");
        return res;
    }

    // Save High Scores
    public void saveHighScores (String username, int scores) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        String query = "UPDATE accounts set scores = " + scores + " WHERE username = '" + username + "';";
        try (PreparedStatement prep = con.prepareStatement( query)) {
            prep.executeUpdate();
            prep.close();
        }
    }

    // Get Scores
    public ResultSet search(String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT scores FROM accounts WHERE (username = '" + username + "' COLLATE NOCASE);");
        return res;
    }

    // Update Guest account
    public void updateGuestAccount (String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        String query = "UPDATE accounts set scores = " + 0 + " WHERE username = '" + username + "';";
        try (PreparedStatement prep = con.prepareStatement( query)) {
            prep.executeUpdate();
            prep.close();
        }
    }
}