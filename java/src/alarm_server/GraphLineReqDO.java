package alarm_server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Chris
 * Date: 28/07/2010
 * Time: 8:17:57 PM
 */
public class GraphLineReqDO implements Serializable, GraphLineDOI
{
    private List<GraphPointDOI> graphPoints = new ArrayList<GraphPointDOI>();

    public static GraphLineDOI newInstance()
    {
        return new GraphLineReqDO();
    }

    private GraphLineReqDO()
    {
    }

    public void addGraphPoint( GraphPointDOI graphPoint)
    {
        graphPoints.add( graphPoint);
    }

    public GraphPointDOI getGraphPoint( int ordinal)
    {
        if(ordinal >= graphPoints.size())
        {
            /*
             * countInEach problem
             */
            Err.pr( "Line: " + this);
            Err.error( "Only has " + graphPoints.size() + ", when trying to index at " + ordinal);
        }
        return graphPoints.get( ordinal);
    }

    public List getGraphPoints()
    {
        return graphPoints;
    }

    public int size()
    {
        return graphPoints.size();
    }

    public boolean isEmpty()
    {
        return graphPoints.isEmpty();
    }

    @Override
    public String toString() {
        return toString(WhatsGood.NULL);
    }

    public String toString(WhatsGood whatsGood)
    {
        StringBuffer result = new StringBuffer();
        result.append( "GraphLineReqDO of size " + size() + " (1st 10 only)\n");
        for (int i = 0; i < graphPoints.size() && i < 10; i++)
        {
            GraphPointDOI graphPoint = graphPoints.get(i);
            result.append( "\tgraphPoint: " + graphPoint.toString( whatsGood) + "\n");
        }
        return result.toString();
    }
}
