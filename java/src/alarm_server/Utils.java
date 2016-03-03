package alarm_server;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Chris
 * Date: 03/03/2016
 * Time: 4:30 PM
 */
public class Utils {
    public static final String NEWLINE = System.getProperty("line.separator");

    public static boolean isBlank(String s)
    {
        boolean result = false;
        if(s == null || s.trim().equals(""))
        {
            result = true;
        }
        return result;
    }

    public static List<String> formList(String s)
    {
        List<String> result = new ArrayList<String>();
        result.add(s);
        return result;
    }    
}
