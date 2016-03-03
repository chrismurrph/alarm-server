package alarm_server;

/**
 *
 * @author Chris
 */
public class WhatsGood
{
    private String name;

    private WhatsGood(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public static final WhatsGood HIGH_VALUES_GOOD = new WhatsGood( "HIGH_VALUES_GOOD");
    public static final WhatsGood LOW_VALUES_GOOD = new WhatsGood( "LOW_VALUES_GOOD");
    public static final WhatsGood MEDIUM_VALUES_GOOD = new WhatsGood( "MEDIUM_VALUES_GOOD");
    public static final WhatsGood NULL = new WhatsGood( "NULL");
}
