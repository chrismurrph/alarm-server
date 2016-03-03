package alarm_server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * One dimension is the names. For each name there is a list of GraphPoints.
 * (GraphLineReqDO is nothing more than a list of GraphLineReqDO.GraphPoint[s])
 * The size of GraphPoints is usually
 * the same for each name. However sometimes one name may be missing a GraphPoint. The GraphPoints extend over
 * time. The same idx into a different GraphPoints list should be at the same time. Thus as well as a countInEach
 * we could have a list of times that is the same size as countInEach.
 *
 * User: Chris
 * Date: 07/09/2010
 * Time: 2:28:24 PM
 */
public class MultigasReqDO implements Serializable, MultigasDOI
{
    public HashMap<String,GraphLineDOI> gasGraphLines = new HashMap<String,GraphLineDOI>();
    public List<String> gasNames = new ArrayList<String>();
    private Integer countInEach = 0;

    /**
     * Ideally we would get a Scorecard from the name, then get WhatsGood, but too much effort. This actually
     * won't work for certain types of alarms which we don't have yet.
     * (In fact if we ever want an alarm for high values of Oxygen then it is time to do away with String
     * representations of gases altogether).
     */
    private WhatsGood getWhatsGoodFromName( String name)
    {
        WhatsGood result;
        if(Utils.isBlank(name)) {
            result = WhatsGood.MEDIUM_VALUES_GOOD;
        } else if(name.equals("Carbon_Dioxide")) {
            result = WhatsGood.LOW_VALUES_GOOD;
        } else if(name.equals("Carbon_Monoxide")) {
            result = WhatsGood.LOW_VALUES_GOOD;
        } else if(name.equals("Methane")) {
           result = WhatsGood.LOW_VALUES_GOOD;
        } else if(name.equals("Oxygen")) {
           result = WhatsGood.HIGH_VALUES_GOOD;
        } else {
            result = WhatsGood.MEDIUM_VALUES_GOOD;
        }
        return result;
    }

    @Override
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        Iterator it = gasGraphLines.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            //Err.pr( "Got a key " + entry.getKey().getClass().getName());
            //Err.pr( "Value " + entry.getValue().getClass().getName());
            String gasName = (String)entry.getKey();
            /*
             * Seems like with T/B we use the 'average' no matter what the gas is, because these are
             * real readings.
             */
            WhatsGood whatsGood = WhatsGood.MEDIUM_VALUES_GOOD; // getWhatsGoodFromName(gasName);
            result.append( "Gas: " + gasName + "\n");
            GraphLineReqDO graphLine = (GraphLineReqDO)entry.getValue();
            //graphLine.computeCounts();
            //if(graphLine.minutelyCount > 0) result.append( "Count minutely points: " + graphLine.minutelyCount + "\n");
            //if(graphLine.tenMinutelyCount > 0) result.append( "Count ten-minutely points: " + graphLine.tenMinutelyCount + "\n");
            //if(graphLine.hourlyCount > 0) result.append( "Count hourly points: " + graphLine.hourlyCount + "\n");
            //if(graphLine.dailyCount > 0) result.append( "Count daily points: " + graphLine.dailyCount + "\n");
            result.append( "First ten graph points:" + "\n");
            for (int i = 0; i < graphLine.getGraphPoints().size() && i < 10; i++)
            {
                GraphPointReqDO graphPoint = (GraphPointReqDO)graphLine.getGraphPoints().get(i);
                result.append( "\tgraphPoint: " + graphPoint.toString( whatsGood) + "\n");
            }
        }
        /*
        result.append( "MultigasReqDO of size " + count + "\n");
        */
        return result.toString();
    }

    public GraphLineDOI getGraphLine( String gasName)
    {
        return gasGraphLines.get( gasName);
    }

    public Integer getCountInEach()
    {
        return countInEach;
    }

    public void setCountInEach( Integer countInEach)
    {
        this.countInEach = countInEach;
    }

    public int gasNamesSize()
    {
        return gasNames.size();
    }

    public List<String> getGasNamesList()
    {
        return gasNames;
    }
}
