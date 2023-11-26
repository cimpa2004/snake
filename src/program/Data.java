package program;

import java.util.Arrays;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Data {

    private Rangletra [] top5helyezett = new Rangletra[5];
    //for testing
    public boolean countains(Rangletra elem){
        for (int i = 0; i<5;i++){
            if (top5helyezett[i].equals(elem))
                return true;
        }
        return false;
    }

    //end

    public String getAtIndexString(int index){
        if (index >=0 && index < top5helyezett.length){
            return top5helyezett[index].nev;
        }
        System.err.println("Felre indexeles");
        return null;
    }
    public Integer getAtIndexInteger(int index){
        if (index >=0 && index < top5helyezett.length){
            return top5helyezett[index].pontszam;
        }
        System.err.println("Felre indexeles");
        return null;
    }
    public void addIfNeded(Rangletra elem) {
        for (int i = 0; i < top5helyezett.length; i++) {
            if (top5helyezett[i] == null || elem.pontszam > top5helyezett[i].pontszam) {
                // Insert the new element and shift existing elements if needed
                for (int j = top5helyezett.length - 1; j > i; j--) {
                    top5helyezett[j] = top5helyezett[j - 1];
                }
                top5helyezett[i] = elem;
                break;
            }
        }
    }


    public void writeToXML(String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Top5Helyezett");
            doc.appendChild(root);

            for (Rangletra r : top5helyezett) {
                if (r != null) {
                    Element rangletraElement = doc.createElement("Rangletra");
                    root.appendChild(rangletraElement);

                    Element nevElement = doc.createElement("Nev");
                    nevElement.appendChild(doc.createTextNode(r.nev));
                    rangletraElement.appendChild(nevElement);

                    Element pontszamElement = doc.createElement("Pontszam");
                    pontszamElement.appendChild(doc.createTextNode(String.valueOf(r.pontszam)));
                    rangletraElement.appendChild(pontszamElement);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void readFromXML(String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));

            NodeList rangletraList = doc.getElementsByTagName("Rangletra");

            for (int i = 0; i < rangletraList.getLength(); i++) {
                Element rangletraElement = (Element) rangletraList.item(i);
                String nev = rangletraElement.getElementsByTagName("Nev").item(0).getTextContent();
                int pontszam = Integer.parseInt(rangletraElement.getElementsByTagName("Pontszam").item(0).getTextContent());

                Rangletra rangletra = new Rangletra(nev, pontszam);
                addIfNeded(rangletra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

};
