package lt.aktkc.baser;

import org.junit.Test;

import java.io.FileInputStream;

public class DescriptionFileParserTest {

    @Test
    public void testName() throws Exception {

        DescriptionFileParser parser = new DescriptionFileParser();
        FileInputStream fileInputStream = new FileInputStream("files/event.xml");
        parser.parse(fileInputStream);



    }
}