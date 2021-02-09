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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public String LoginCheck(UserInfo userInfo,HttpServletRequest request) {
        this.userInfo = userInfo;
        String[]field={"password"};
        String addition="where user_email="+ "'" +userInfo.getID()+"'";
        List<Object[]>check=Sql.Select(sqlConnect.getConnect(),field,"email",addition);
        if(check.isEmpty()){
            return "fail";
        }
        if(check.get(0)[0].equals(userInfo.getPassWord())){
            HttpSession session= request.getSession();
            session.setMaxInactiveInterval(10*60);
            session.setAttribute("email",userInfo.getID());
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

    @ResponseBody
    @RequestMapping(path = "/getSession.do",method = RequestMethod.GET)
    public String getSession(HttpServletResponse response,HttpServletRequest request){
        return (String)request.getSession().getAttribute("email");
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
