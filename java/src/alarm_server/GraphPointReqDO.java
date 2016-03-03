package alarm_server;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: Chris FAST XPS
 * Date: 12/05/12
 * Time: 11:23 AM
 */
public class GraphPointReqDO implements Serializable, GraphPointDOI
{
    private final Integer ordinal; //starts at 0 and goes to however many minutes are in requested time period
    private final String sampleTimeStr;
    private String minValTimeStr;
    private String maxValTimeStr;
    private String avgValTimeStr;
    private BigDecimal minVal;
    private BigDecimal maxVal;
    private BigDecimal avgVal;

    public static GraphPointDOI newInstance(
        Integer ordinal,
        String sampleTimeStr,
        String minValTimeStr,
        String maxValTimeStr,
        String avgValTimeStr,
        BigDecimal minVal,
        BigDecimal maxVal,
        BigDecimal avgVal)
    {
        return new GraphPointReqDO( ordinal, sampleTimeStr, minValTimeStr, maxValTimeStr, avgValTimeStr, minVal, maxVal, avgVal);
    }

    private GraphPointReqDO(
            Integer ordinal,
            String sampleTimeStr,
            String minValTimeStr,
            String maxValTimeStr,
            String avgValTimeStr,
            BigDecimal minVal,
            BigDecimal maxVal,
            BigDecimal avgVal)
    {
        this.ordinal = ordinal;
        this.sampleTimeStr = sampleTimeStr;
        this.minValTimeStr = minValTimeStr;
        this.maxValTimeStr = maxValTimeStr;
        this.avgValTimeStr = avgValTimeStr;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.avgVal = avgVal;
    }

    //        @Override
//        public String toString()
//        {
//            return ordinal + "./ minValue: " + minVal + ", maxValue: " + maxVal + ", sampleTimeStr: " + sampleTimeStr;
//        }
    public String toString(WhatsGood whatsGood)
    {
        String result = null;
        if(whatsGood == null || whatsGood == WhatsGood.MEDIUM_VALUES_GOOD || whatsGood == WhatsGood.NULL) {
            result = ordinal + "./ avgVal: " + avgVal + ", avgValTimeStr: " + avgValTimeStr + ", sampleTimeStr: " + sampleTimeStr;
        } else if(whatsGood == WhatsGood.HIGH_VALUES_GOOD) {
            result = ordinal + "./ minVal: " + minVal + ", minValTimeStr: " + minValTimeStr + ", sampleTimeStr: " + sampleTimeStr;
        } else if(whatsGood == WhatsGood.LOW_VALUES_GOOD) {
            result = ordinal + "./ maxVal: " + maxVal + ", maxValTimeStr: " + maxValTimeStr + ", sampleTimeStr: " + sampleTimeStr;
        }
        return result;
    }

    @Override
    public String toString()
    {
        return toString(WhatsGood.NULL);
    }

    public String getSampleTimeStr()
    {
        return sampleTimeStr;
    }

    public BigDecimal getMinVal()
    {
        return minVal;
    }

    public BigDecimal getMaxVal()
    {
        return maxVal;
    }

    public BigDecimal getAvgVal()
    {
        return avgVal;
    }

    public String getMinValTimeStr() {
        return minValTimeStr;
    }

    public String getMaxValTimeStr() {
        return maxValTimeStr;
    }
}
