package alarm_server;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: Chris
 * Date: 28/07/2010
 * Time: 10:56:06 PM
 */
public class GraphLineServer implements RequestGraphLineServiceI
{
    public GraphLineServer()
    {
    }

    @Override
    public MultigasDOI requestGraphLine(
        String startTimeStr,
        String endTimeStr,
        List<String> substancesNames,
        String displayName,
        String displayTimeStr,
        String sessionId)
    {
        //Err.pr( "When about to requestGraphLine session dataContext is " + BaseContext.getThreadObjectContext());
        Err.pr( "requestGraphLine PARAMS:");
        Err.pr( "startTimeStr: " + startTimeStr);
        Err.pr( "endTimeStr: " + endTimeStr);
        //Print.prList(substancesNames, "substancesNames");
        Err.pr( "displayName: " + displayName);
        Err.pr( "displayTimeStr: " + displayTimeStr);

        GraphLineDOI line = GraphLineReqDO.newInstance();
        GraphPointDOI point = GraphPointReqDO.newInstance(
                1, startTimeStr, startTimeStr, startTimeStr, startTimeStr,
                new BigDecimal(5) ,new BigDecimal(5) ,new BigDecimal(5));
        line.addGraphPoint( point);
        MultigasReqDO result = new MultigasReqDO();
        result.gasNames.add( "Methane");
        result.setCountInEach( 1);
        result.gasGraphLines.put( "Methane", line);
        Err.pr("To RET (in Java) " + result);
        return result;
    }
}
