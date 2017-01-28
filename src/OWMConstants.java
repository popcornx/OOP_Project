/**
 * @author Lorenz Rasch
 */

/**
 * Contains the following data:
 * API key and URL that are used by OWMLoader to recall data.
 * City code that is used by the forecast object.
 */
public interface OWMConstants {

    public static final String APIKEY = "7b6accdf1ca7788a21c89b2a3f0e8e76";
    public static final String URL = "http://api.openweathermap.org/data/2.5/forecast?mode=xml&appid=" + APIKEY + "&id=";

    public static final int BERN = 2661552;

}
