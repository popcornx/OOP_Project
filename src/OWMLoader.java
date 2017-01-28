import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
//TODO javadoc
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */
public class OWMLoader {

    private OWMLoader(){};

    public static void loadOWMFile(int cityID) throws IOException {
        URL website = new URL(OWMConstants.URL + cityID);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("data/" + cityID + ".xml");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}
