package DbDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sql {
    public static List<String[]> Select(Connection connect, String[]field, String table,String addition){
        if(addition==null){
            addition="";
        }
        List<String[]>result=new ArrayList<>();
        int len=field==null?0:field.length;
        StringBuilder sql=new StringBuilder();
        if(len==0){
            sql.append("select * from").append(table);
        }else{
            sql.append("select ");
            for(String x:field){
                sql.append(x).append(", ");
            }
            sql.delete(sql.length()-2,sql.length()).append(" ");
            sql.append("from").append(" ").append(table).
                    append(" ").append(addition).append(" ").append(";");
        }
        String Sql=sql.toString();
        PreparedStatement statement=null;
        try {
            System.out.println(sql);
           statement=connect.prepareStatement(Sql);
           ResultSet res= statement.executeQuery();
           while(res.next()){
               String[]str=new String[len];
               for(int i=0;i<len;i++){
                   str[i]=res.getString(i+1);
               }
               result.add(str);
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return result;
    }
    public static boolean Insert(Connection connect,String[]field,String[]values,String table){
          StringBuilder sql=new StringBuilder();
          sql.append("insert into ").append(table).append("( ");
          for (String s : field) {
            sql.append(s).append(",");
          }
          sql.delete(sql.length()-1,sql.length()).append(")values(");
          for(String s:values){
              if(s!=null) {
                  sql.append('\'').append(s).append("',");
              }else{
                  sql.append("null").append(",");
              }
          }
          sql.delete(sql.length()-1,sql.length()).append(")");
        try {
            System.out.println(sql);
            PreparedStatement statement=connect.prepareStatement(sql.toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("test.xml");
        SqlConnect connect=context.getBean("sqlConnect",SqlConnect.class);
        String[]field={"id","user_email","password"};
        String[]values={null,"ian","123"};
        List<String[]>test=Sql.Select(connect.getConnect(),field,"email","");
        System.out.println(Arrays.toString(test.get(0)));
    }
}
