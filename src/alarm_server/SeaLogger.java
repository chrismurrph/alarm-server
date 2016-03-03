/*
    Strandz LGPL - an API that matches the user to the data.
    Copyright (C) 2007 Chris Murphy

    Strandz LGPL is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA


    The authors can be contacted via www.strandz.org
*/
package alarm_server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is an implementation of ErrorLogger, an interface that can be logged to.
 * It outputs both to a Refreshable and to a file. It can be marked to not write to a
 * file at all, for instance if Web Start is being used. A Refreshable may for instance
 * be hooked up to a screen
 *
 * @author Chris Murphy
 */
public class SeaLogger
{
    private FileWriter out;
    /**
     * Only instantiated if a Refreshable is set. In which case don't
     * log too much or you will fill the ArrayList() up!
     */
    private List list;
    private String fileName;
    private Refreshable refreshable;
    /**
     * true if this class (not object) is able to write to the filesystem.
     * It is necessary to set this to false for a certificate-less web
     * start application.
     */
    private boolean interactWithFileSystem = false;
    private boolean silent = false;
    private static int times;
    private static boolean DEBUG = false;

    /**
     * Milliseconds (.SSS) are there because sometimes a user event and a
     * data measure do happen in the same second. On reading the log file
     * we check that nothing happens at the same time, because we go on
     * to sort for order by this date.
     */
    private static final String MILLISECONDS_DATE_TIME_PARSE_STRING = "dd_MM_yyyy__HH_mm_ss.SSS";
    private static final ThreadSafeDateFormat MILLISECONDS_DATEFORMAT = new ThreadSafeDateFormat( MILLISECONDS_DATE_TIME_PARSE_STRING);

    private static final String FILESTAMP_DATE_TIME_PARSE_STRING = "dd_MM_yyyy__HH_mm_ss";
    private static final ThreadSafeDateFormat FILESTAMP_DATEFORMAT = new ThreadSafeDateFormat( FILESTAMP_DATE_TIME_PARSE_STRING);

    /**
     * Keeps a file of text lines, and allows the user to write to this file
     * using many log() methods. The GUI display (see Refreshable interface)
     * is simulataneously updated.
     *
     * @param fileName  The name of the file that the log messages will go to
     * @param reset     true if we want to wipe all the prior existing lines
     * @param startText the heading of the file, that will appear on the first line
     */
    public static SeaLogger newInstance
    (
        String fileName,
        boolean reset,
        String startText,
        boolean interactWithFileSystem,
        boolean silent
    )
    {
        Assert.isFalse(fileName == null, "No fileName to log to");
        SeaLogger result = new SeaLogger( fileName, reset, startText, interactWithFileSystem, silent);
        return result;
    }

    private SeaLogger(
        String fileName,
        boolean reset,
        String startText,
        boolean interactWithFileSystem,
        boolean silent)
    {
        this.interactWithFileSystem = interactWithFileSystem;
        this.silent = silent;
        /*
        * Opening for append
        */
        try
        {
            File file = new File(fileName);
            if(reset && interactWithFileSystem)
            {
                file.delete();
            }
            if(interactWithFileSystem)
            {
                out = new FileWriter(file, true);
            }
        }
        catch(IOException e)
        {
            Err.error("Could not create or open file - <" + fileName + ">");
        }
        // Err.setBatch( false); //so can hear sound of error
        this.fileName = fileName;
    }

    /**
     * Set the Refreshable interface that will receive refresh events each time
     * a message (or list of messages) is output.
     *
     * @param v
     */
    public void setRefreshable(Refreshable v)
    {
        refreshable = v;
        list = new ArrayList();
    }

    /**
     * Get the internal list of message lines
     *
     * @return a Vector of every line that has been output since Logger construction
     */
    @SuppressWarnings("unchecked")
    public List getList()
    {
        return list;
    }
    
    private void quickMessage()
    {
        System.err.println("Have written error to <" + fileName + ">");
    }
    
    static private String stack2string(Error e)
    {
        String result = null;
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            result = "------" + Utils.NEWLINE + sw.toString() + "------"
                + Utils.NEWLINE;
        }
        catch(Exception e2)
        {
            Err.error("bad stack2string");
        }
        return result;
    }

    /**
     * Get the file name for messages
     * @return The file name that the log messages were written to
     */
    public String getFileName()
    {
        return fileName;
    }

    private static String dateToStr( Date date)
    {
        String result = null;
        if(date != null)
        {
            result = MILLISECONDS_DATEFORMAT.format( date);
        }
        return result;
    }

    public static Date strToDate( String timeStr)
    {
        return strToDate( timeStr, null);
    }

    public static boolean isADateStr( String timeStr)
    {
        boolean result = false;
        if(!Utils.isBlank( timeStr))
        {
            try
            {
                MILLISECONDS_DATEFORMAT.parse( timeStr);
                result = true;
            }
            catch (ParseException e)
            {
            }
            catch (NumberFormatException e)
            {
            }
        }
        return result;
    }

    public static Date strToDate( String timeStr, String sessionId)
    {
        Date result = null;
        if(!Utils.isBlank( timeStr))
        {
            try
            {
                result = MILLISECONDS_DATEFORMAT.parse( timeStr);
            }
            catch (ParseException e)
            {
                Err.pr( "ParseException, strToDate() can't parse <" + timeStr + "> came from sessionId <" + sessionId + "> at time <" + new Date() + ">");
                Err.error( e);
            }
            catch (NumberFormatException e)
            {
                /*
                 * java.lang.NumberFormatException: For input string: ".5555EE11"
                 * Doubt will ever happen on a real server. Only happened after un-hibernated
                 * a desktop machine.
                 * Did happen on real server for "25_01_2012__18_50_24.328", which tests out (see SeaLoggerTest) fine.
                 * So this is a mystery. Phil wasn't doing two things at once so that rules against some kind of
                 * synchronization issue (from looking at other calls to this method). Not that SmartgasServer isn't
                 * already a singleton and the method requestGases() not already synchronized. By time of error was not
                 * collecting values which is only other thing this server does when user doing nothing, not that doing
                 * that would involve any calls to this method.
                 * Knowing that this happens on live our only choice is to let it slip thru with a null result and the
                 * call that resulted in this stack (requestGases()) to go thru and do nothing. I can see that null
                 * requests have been ignored before, so this shouldn't break the system.
                 *
                 * Perhaps issue is somehow related to SimpleDateFormat not being thread safe, despite all arguments
                 * above that I can't see how two threads can be getting in here. So marking this method as synchronized
                 * might help! Also made all methods that access MILLISECONDS_DATEFORMAT thread safe.
                 *
                 * Now universally not using SimpleDateFormat but rather ThreadSafeDateFormat, and have removed the
                 * synchronized keyword use in this class.
                 */
                Err.pr( "NumberFormatException, strToDate() can't parse <" + timeStr + "> came from sessionId <" + sessionId + "> at time <" + new Date() + ">");
                //Err.error( e);
            }
        }
        return result;
    }

    public static ThreadSafeDateFormat getMillisecondsDateFormat()
    {
        return MILLISECONDS_DATEFORMAT;
    }

    private static String dateToFilestampStr( Date date)
    {
        String result = null;
        if(date != null)
        {
            result = FILESTAMP_DATEFORMAT.format( date);
        }
        return result;
    }

    /* Does nothing more than dateToStr()
    public static String stringifyDate( Date date)
    {
        String result = null;
        if(date != null)
        {
            result = dateToStr( date);
        }
        return result;
    }
    */

    public static String format(Date date)
    {
        return dateToStr( date);
    }
    public static Date parse(String str)
    {
        return strToDate( str);
    }

    public static String formatAsFileStamp(Date date)
    {
        return dateToFilestampStr( date);
    }
}
