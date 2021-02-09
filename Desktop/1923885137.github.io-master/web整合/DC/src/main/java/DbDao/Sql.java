package DbDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Sql {
    public static List<Object[]> Select(Connection connect, String[]field, String table,String addition){
        if(addition==null){
            addition="";
        }
        List<Object[]>result=new ArrayList<>();
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
        PreparedStatement statement;
        try {
           statement=connect.prepareStatement(Sql);
           ResultSet res= statement.executeQuery();
           while(res.next()){
               Object[]str= new Object[len];
               for(int i=0;i<len;i++){
                   str[i]=res.getObject(i+1);
               }
               result.add(str);
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return result;
    }
    public static boolean Insert(Connection connect,String[]field,Object[]values,String table){
          StringBuilder sql=new StringBuilder();
          sql.append("insert into ").append(table).append("( ");
          for (String s : field) {
            sql.append(s).append(",");
          }
          sql.delete(sql.length()-1,sql.length()).append(")values(");
          PreparedStatement statement;
         try {
            for(Object s:values){
              if(s!=null) {
                  sql.append("?").append(",");
              }else{
                  sql.append("null").append(",");
              }
          }
            sql.delete(sql.length()-1,sql.length()).append(')');
            statement = connect.prepareStatement(sql.toString());
            int index=1;
             for(Object s:values){
                 if(s!=null) {
                    statement.setObject(index++,s);
                 }
             }
          sql.delete(sql.length()-1,sql.length()).append(")");

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean Update(Connection connect,String[]field,Object[]values,String table){
        return false;
    }

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("test.xml");
        SqlConnect connect=context.getBean("sqlConnect",SqlConnect.class);
//        String[] field = {"id","user_email","password"};
//        Object[] values={null,"213","123"};
//        Sql.Insert(connect.getConnect(),field,values,"email");
        System.out.println(Sql.Select(connect.getConnect(),new String[]{"id"},"email","where user_email= 'Aa15340521294@163.com' " ).get(0)[0]);
    }
}
