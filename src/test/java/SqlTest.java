import com.example.project.util.JDBCUtils;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;


public class SqlTest {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDatasource());
    @Test
    public void fun1()  {
        String sql="insert into shoppingcart values("+
                "11"+","+
                "22"+","+
                1+","+
                44+","+
                "55"+")";
        jdbcTemplate.update(sql);
    }


}
