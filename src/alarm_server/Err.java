package alarm_server;

/**
 * User: Chris
 * Date: 03/03/2016
 * Time: 2:29 PM
 */
public class Err {
    public static void pr(String txt) {
        System.err.println( txt);
    }
    public static void error(String txt) {
        throw new Error( txt);
    }
    public static void stack(String txt) {
        throw new Error( txt);
    }
    public static void stack() {
        throw new Error();
    }
    public static void alarm(String txt) {
        throw new Error( txt);
    }
    public static void error(Throwable th) {
        throw new Error( th);
    }
    
}
