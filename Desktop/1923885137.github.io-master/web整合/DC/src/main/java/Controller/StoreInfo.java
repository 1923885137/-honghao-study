package Controller;

import DbDao.Sql;
import DbDao.SqlConnect;
import DbDao.UserBasic;
import DbDao.UserEdu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/Info")
@Scope("prototype")
public class StoreInfo {
    private SqlConnect sqlConnect;
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired(required=false)
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    @Qualifier("sqlConnect")
    public void setSqlConnect(SqlConnect sqlConnect) {
        this.sqlConnect = sqlConnect;
    }

    public SqlConnect getSqlConnect() {
        return sqlConnect;
    }

    public int getUser_id(){
        HttpSession session= request.getSession();
        String email=(String) session.getAttribute("email");
        if(email==null){
            return -1;
        }
        String addition="where user_email ='"+email+"'";
        return (int)Sql.Select(sqlConnect.connect,new String[]{"id"},"email",addition ).get(0)[0];
    }

    @ResponseBody
    @RequestMapping(path = "/Basic.do", method = RequestMethod.POST)
    public String storeBasic(UserBasic userBasic) {
        String[] field = {"id", "user_id", "user_name",
                "user_sex", "user_introduction", "user_date", "user_city"};
        int id=getUser_id();
        if(id==-1){
            return "";
        }
        Object[]values= userBasic.populate(id);
        if(Sql.Insert(sqlConnect.getConnect(),field,values,"basicinfo")){
            return "success";
        }else{
            return "";
        }

    }

    @ResponseBody
    @RequestMapping(path = "/Edu.do",method = RequestMethod.POST)
    public String storeEdu(UserEdu userEdu){
        String[]field={"id","school","major","time","background"};
        int id=getUser_id();
        if(id==-1){
            return "";
        }
        Object[]values= userEdu.populate(id);
        if(Sql.Insert(sqlConnect.getConnect(),field,values,"eduinfo")){
            return "success";
        }else{
            return "";
        }

    }

}
