package DbDao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class UserEdu {
     private String school;
     private String time;
     private String background;
     private String major;

    public String getSchool() {
        return school;
    }

    public String getTime() {
        return time;
    }

    public String getBackground() {
        return background;
    }

    public String getMajor() {
        return major;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public Object[] populate(int id){
        return new Object[]{id,getSchool(),getMajor()
        ,getTime(),getBackground()};
    }
}

