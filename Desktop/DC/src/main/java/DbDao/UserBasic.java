package DbDao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class UserBasic {
    private String user_id;
    private String user_name;
    private String user_sex;
    private String user_introduction;
    private String user_date;
    private String user_city;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public String getUser_introduction() {
        return user_introduction;
    }

    public String getUser_date() {
        return user_date;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public void setUser_introduction(String user_introduction) {
        this.user_introduction = user_introduction;
    }

    public void setUser_date(String user_date) {
        this.user_date = user_date;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }
    public Object[] populate(int id){
        return new Object[]{id,getUser_id()
        ,getUser_name(),getUser_sex(),getUser_introduction(),
        getUser_date(),getUser_city()};
    }
}
