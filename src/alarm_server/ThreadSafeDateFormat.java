package alarm_server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Chris FAST XPS
 * Date: 29/01/12
 * Time: 11:12 AM
 */
public class ThreadSafeDateFormat {
    private DateFormat df;

    public ThreadSafeDateFormat(String format) {
        this.df = new SimpleDateFormat(format);
    }

    public synchronized String format(Date date) {
        return df.format(date);
    }
    public synchronized String format(Long date) {
        return df.format(date);
    }

    public synchronized Date parse(String string) throws ParseException {
        return df.parse(string);
    }
}
