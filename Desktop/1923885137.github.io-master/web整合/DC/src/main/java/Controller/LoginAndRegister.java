package Controller;

import DbDao.Sql;
import DbDao.SqlConnect;
import DbDao.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/Login")
@Scope("prototype")
public class LoginAndRegister {
    private SqlConnect sqlConnect;
    private UserInfo userInfo;

    @Autowired
    @Qualifier("sqlConnect")
    public void setSqlConnect(SqlConnect sqlConnect) {
        this.sqlConnect = sqlConnect;
    }
    public SqlConnect getSqlConnect() {
        return sqlConnect;
    }


    @ResponseBody
    @RequestMapping(path = "/Login1.do",method =RequestMethod.POST)
    public String LoginCheck(UserInfo userInfo) {
        this.userInfo = userInfo;
        String[]field={"password"};
        String addition="where user_email="+ "'" +userInfo.getID()+"'";
        List<String[]>check=Sql.Select(sqlConnect.getConnect(),field,"email",addition);
        if(check.isEmpty()){
            return "fail";
        }
        if(check.get(0)[0].equals(userInfo.getPassWord())){
            return "success";
        }else{
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/Login2.do",method =RequestMethod.POST)
    public String register(UserInfo userInfo) {
        this.userInfo = userInfo;
        String[]field={"id","user_email","password"};
        String[]values={null,userInfo.getID(),userInfo.getPassWord()};
        if(Sql.Insert(sqlConnect.getConnect(),field,values,"email")){
            return "success";
        }else{
            return "fail";
        }
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
