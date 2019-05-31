package fake_data;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FakeData {

    public static Faker fakeData = new Faker();

    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(new Date());
    }

    public static String getCurrentDateWithIncorrectFormat(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date());
    }

}
