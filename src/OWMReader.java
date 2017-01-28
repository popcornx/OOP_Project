import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class capable of reading XML files from openweathermap.org
 * @author Lorenz Rasch & Nicole Scheffel
 */

public final class OWMReader {

    private static final String APIKEY = "7b6accdf1ca7788a21c89b2a3f0e8e76";
    private static final String URL = "http://api.openweathermap.org/data/2.5/forecast?mode=xml&units=metric&appid=" + APIKEY + "&id=";

    private OWMReader(){};

    /**
     * Loads and reads XML files from openweathermap.org
     * @return Forecast object from the file
     * @exception IOException On input error.
     * @exception SAXException Error from either the XML parser or the application.
     */
    public static Forecast readOWMFile(int cityID, String city) throws IOException, SAXException {
        XMLReader r = XMLReaderFactory.createXMLReader();
        Forecast.Builder bf = new Forecast.Builder(city);
        r.setContentHandler(new DefaultHandler(){
            private Weather.Builder bw;

            @Override
            public void startElement(String uri,
                                     String lName,
                                     String qName,
                                     Attributes atts)
                    throws SAXException {
                switch(qName) {
                    case "time":
                        bw = new Weather.Builder();
                        bw.addTime(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(atts.getValue("from"))));
                        break;
                    case "symbol":
                        bw.addID(atts.getValue("var"));
                        break;
                    case "precipitation":
                        if(atts.getLength() != 0) {
                            bw.addPreci(Double.parseDouble(atts.getValue("value")));
                        }
                        break;
                    case "windSpeed":
                        bw.addWind(Double.parseDouble(atts.getValue("mps")));
                        break;
                    case "temperature":
                        bw.addTemp(Double.parseDouble(atts.getValue("value")));
                        break;
                    case "pressure":
                        bw.addPress(Double.parseDouble(atts.getValue("value")));
                        break;
                    case "humidity":
                        bw.addHumidity(Double.parseDouble(atts.getValue("value")));
                        break;
                    case "clouds":
                        bw.addCloud(Double.parseDouble(atts.getValue("all")));
                        break;
                }
            }

            @Override
            public void endElement(String uri,
                                   String lName,
                                   String qName) {
                if(qName.equals("time")){
                    bf.addWeather(bw.build());
                }
            }
        });
        r.parse(new InputSource(new URL(URL + cityID).openStream()));
        return bf.build();
    }
}
