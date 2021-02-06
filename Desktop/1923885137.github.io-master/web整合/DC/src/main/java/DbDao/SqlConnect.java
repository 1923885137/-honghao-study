package DbDao;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
@Scope("singleton")
public class SqlConnect{
    private  static String Driver;
    private  static String URL;
    private  static String User;
    private  static String PassWord;
    public Connection connect;

    @Value("#{pool['Driver']}")
    public  void setDriver(String driver) {
        Driver = driver;
    }

    @Value("#{pool['URL']}")
    public  void setURL(String url) {
        URL = url;
    }

    @Value("#{pool['User']}")
    public  void setUser(String user) {
        User = user;
    }

    @Value("#{pool['PassWord']}")
    public  void setPassWord(String passWord) {
        PassWord = passWord;
    }


    public  String getDriver() {
        return Driver;
    }

    public  String getURL() {
        return URL;
    }

    public  String getUser() {
        return User;
    }

    public  String getPassWord() {
        return PassWord;
    }

    public  Connection getConnect() {
        if(connect==null) {
            DruidDataSource druid = new DruidDataSource();
            druid.setDriverClassName(getDriver());
            druid.setUrl(getURL());
            druid.setUsername(getUser());
            druid.setPassword(getPassWord());

            try {
                connect = druid.getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connect;
    }
}
