package alarm_server;

import java.math.BigDecimal;

/**
 * User: Chris FAST XPS
 * Date: 12/05/12
 * Time: 12:37 PM
 */
public interface GraphPointDOI
{
    String getSampleTimeStr();
    BigDecimal getMinVal();
    BigDecimal getMaxVal();
    BigDecimal getAvgVal();
    String getMinValTimeStr();
    String getMaxValTimeStr();
    String toString(WhatsGood whatsGood);
}
