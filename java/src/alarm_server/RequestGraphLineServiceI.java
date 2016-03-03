package alarm_server;

import java.util.List;

/**
 * User: Chris
 * Date: 22/07/2010
 * Time: 3:49:33 AM
 */
public interface RequestGraphLineServiceI 
{
    MultigasDOI requestGraphLine(
            String startTimeStr,
            String endTimeStr,
            List<String> physicalSubstanceNames,
            String displayName,
            String displayTimeStr,
            String sessionId
    );
}
