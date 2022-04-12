import com.example.project.domain.User;
import com.example.project.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    public void fun(){
        String s=null;
        String s1="";
        System.out.println(s.length());
        System.out.println(s1.length());
    }
    @org.junit.jupiter.api.Test
    public void fun1(){
        List<String> list=new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("bb");
        System.out.println(list.get(0));
    }
    @org.junit.jupiter.api.Test
    public void fun2(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDatasource());
        List<User> list=new ArrayList<>();
        try {
            list = jdbcTemplate.query("select * from user where username=?", new BeanPropertyRowMapper<>(User.class),"j");

        }catch (Exception e){
            System.out.println(list);
        }
        System.out.println(list);
    }
    @org.junit.jupiter.api.Test
    public void fun3() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=" 2008-07-10 19:20:00";
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
    }
}
