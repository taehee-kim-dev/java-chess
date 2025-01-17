package chess.dao.setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess_game"; // MySQL DATABASE 이름
    private static final String OPTION = "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "inbi"; //  MySQL 서버 아이디
    private static final String PASSWORD = "1234"; // MySQL 서버 비밀번호
    private static Connection CONNECTION;

    static {
        try {
            loadJDBCDriver();
            connectDriverManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadJDBCDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private static void connectDriverManager() throws SQLException {
        CONNECTION = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, PASSWORD);
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
