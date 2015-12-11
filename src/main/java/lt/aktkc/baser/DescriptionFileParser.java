package lt.aktkc.baser;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

public class DescriptionFileParser {

    public DescriptionFileParser() {
    }

    public DescriptionFile parse(InputStream input){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(input);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList) xPath.evaluate("field", doc.getDocumentElement(), XPathConstants.NODESET);

            DescriptionFile descriptionFile = new DescriptionFile();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                NamedNodeMap attributes = node.getAttributes();
                String id = attributes.getNamedItem("id").getNodeValue();
                String type = attributes.getNamedItem("type").getNodeValue();
                String name = attributes.getNamedItem("name").getNodeValue();
                Node getter = attributes.getNamedItem("getter");
                Node setter = attributes.getNamedItem("setter");


                DescriptionFileField descriptionFileField = new DescriptionFileField(
                        Integer.parseInt(id),
                        type, name);
                if (getter != null) {
                    String nodeValue = getter.getNodeValue();
                    if (nodeValue != null && !nodeValue.isEmpty()) {
                         descriptionFileField.setGetter(nodeValue);
                    }
                }
                if (setter != null) {
                    String nodeValue = setter.getNodeValue();
                    if (nodeValue != null && !nodeValue.isEmpty()) {
                        descriptionFileField.setSetter(nodeValue);
                    }
                }

                descriptionFile.fields.put(descriptionFileField.id, descriptionFileField );

            }

            return descriptionFile;
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;


    }
}
