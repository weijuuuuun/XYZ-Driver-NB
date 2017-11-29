package chai.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {

    SimpleDateFormat simpleDateFormat;

    public DateService(String dateFormat){
        this.simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    public Date stringToDate(String stringDate) throws ParseException {
        return this.simpleDateFormat.parse(stringDate);
    }

    public String dateToString(Date date){
        return this.simpleDateFormat.format(date);
    }
}
