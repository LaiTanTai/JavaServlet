package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysqlconfig {
    public static final String url = "jdbc:mysql://localhost:3308/crm_app";
    public static final String username ="root";
    public static final String password = "Tantai1210";
    public static Connection getConnection(){
        Connection connection = null;
        try {
//            chỉ định driver sử dụng
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            System.out.println("Lỗi kết nối cơ sở dữ liệu " + e.getMessage());
        }
        return connection;
    }
}
