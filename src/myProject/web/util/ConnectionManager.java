package myProject.web.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class ConnectionManager {
    private static final String URL_PATH = "db.url";
    private static final String USER_PATH = "db.user";
    private static final String PASSWORD_PATH = "db.password";

    static {
        loadDriver();
    }

    @SneakyThrows
    private void loadDriver(){
        Class.forName("org.postgresql.Driver");
    }

    @SneakyThrows
    public static Connection getConnection(){
        return DriverManager.getConnection(
                PropertiesUtil.getByKey(URL_PATH),
                PropertiesUtil.getByKey(USER_PATH),
                PropertiesUtil.getByKey(PASSWORD_PATH));
    }
}
