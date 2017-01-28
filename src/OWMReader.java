import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public final class OWMReader {

    /**
     * Reads the forecast file that was recalled by OWMLoader.
     * @exception IOException On input error.
     * @exception SAXException Error from either the XML parser or the application.
     */
	
    private OWMReader(){};

    public static Forecast readOWMFile(String fileName, String city) throws IOException, SAXException {
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
                    /*case "windSpeed":
                        bw.addWind(Double.parseDouble(atts.getValue("mps")));
                        break;*/
                    case "temperature":
                        bw.addTemp(Double.parseDouble(atts.getValue("value")));
                        break;
                    /*case "pressure":
                        bw.addPress(Double.parseDouble(atts.getValue("value")));
                        break;*/
                    /*case "humidity":
                        bw.addHumidity(Double.parseDouble(atts.getValue("value")));
                        break;*/
                    /*case "clouds":
                        bw.addCloud(Double.parseDouble(atts.getValue("all")));
                        break;*/
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
        try (InputStream i = new FileInputStream(fileName)) {
            r.parse(new InputSource(i));
        }
        return bf.build();
    }
}
