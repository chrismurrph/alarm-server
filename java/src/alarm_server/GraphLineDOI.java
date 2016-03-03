package alarm_server;

/**
 * User: Chris FAST XPS
 * Date: 12/05/12
 * Time: 12:32 PM
 */
public interface GraphLineDOI
{
    void addGraphPoint( GraphPointDOI graphPoint);
    GraphPointDOI getGraphPoint( int ordinal);
    int size();
    boolean isEmpty();
    String toString(WhatsGood whatsGood);
}
