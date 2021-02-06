package DbDao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class UserInfo {
    public String ID;
    public String PassWord;

    public String getID() {
        return ID;
    }
    public String getPassWord() {
        return PassWord;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
