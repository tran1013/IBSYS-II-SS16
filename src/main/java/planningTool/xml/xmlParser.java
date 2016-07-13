package planningTool.xml;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

/**
 * Created by Duc on 12.07.16.
 */
public class xmlParser {


    // TODO: Mapping is missing
    // Methods to get out attributes which you need to proceed are also missing!!!
    
    public void findArticleByID(int id) {

        try

        {
            File inputFile = new File("src/main/java/planningTool/xml/XML_Files/resultServlet.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("article");
            Node n;


            for (int i = 0; i < nList.getLength(); i++) {
                n = nList.item(i);
                if (n.getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(id))) {
                    System.out.println("id: " + n.getAttributes().getNamedItem("id").getNodeValue() + " ");
                    System.out.println("startamount " + n.getAttributes().getNamedItem("startamount").getNodeValue() + " ");
                    System.out.println("pct " + n.getAttributes().getNamedItem("pct").getNodeValue() + " ");
                    System.out.println("price " + n.getAttributes().getNamedItem("price").getNodeValue() + " ");
                    System.out.println("stockvalue " + n.getAttributes().getNamedItem("stockvalue").getNodeValue() + " ");
                }


            }

            System.out.println();
            {

            }
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }
    }
}


