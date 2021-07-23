package database;

import models.PhoneNumber;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.*;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class DataBase {

    private static JdbcTemplate jdbcTemplate;
    public static void setJdbcTemplate(JdbcTemplate instance){
        if(jdbcTemplate ==null){
            jdbcTemplate =instance;
        }
    }

    public static List<PhoneNumber> getPhoneNumbers(){
        String query=String.format("SELECT phone FROM customer");
        List<PhoneNumber> res=jdbcTemplate.query(query,(resultSet, rowNum) -> new PhoneNumber(resultSet.getString("phone")));
        return res;
    }


}
