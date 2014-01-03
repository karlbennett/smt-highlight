package shiver.me.timbers.transform;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This class contains helper method and constants for access the test file data.
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static String testFileText() {

        try {

            return IOUtils.toString(testFileInputStream());

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public static InputStream testFileInputStream() {

        try {

            return new FileInputStream(testFile());

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    public static File testFile() {

        final URL url = FileUtils.class.getResource("Test.txt");

        return new File(url.getFile());
    }
}
