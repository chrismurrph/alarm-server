package alarm_server;

import java.util.List;

/**
 * User: Chris FAST XPS
 * Date: 14/05/12
 * Time: 8:11 AM
 */
public interface MultigasDOI
{
    int gasNamesSize();
    Integer getCountInEach();
    void setCountInEach(Integer countInEach);
    List<String> getGasNamesList();
    GraphLineDOI getGraphLine(String gasName);
}
