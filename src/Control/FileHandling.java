package Control;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import Model.Player;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileHandling {
    private List<Player> players;
    private String xmlName = "Toplist";
    private Comparator<Player> cmp;



    public FileHandling() {
        players = new ArrayList<>();
        cmp = Comparator.comparing(Player::GetPoints);
        cmp = cmp.reversed();
    }

    public List<Player> readXML() {
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(xmlName);

            Element doc = dom.getDocumentElement();

            String tmpName = null;
            String tmpPoints = null;
            for(int i = 0; i < 10; i++) {
                tmpName = getTextValue(tmpName, doc, "playerName"+i);
                if (tmpName != null) {
                    if (!tmpName.isEmpty()){
                        tmpPoints = getTextValue(tmpPoints, doc, "playerScore"+i);
                        if (tmpPoints != null) {
                            if (!tmpPoints.isEmpty()){
                                Player tmp = new Player(tmpName);
                                tmp.SetPoints(Integer.parseInt(tmpPoints));
                                players.add(tmp);
                            }
                        }
                    }
                }
            }
            Collections.sort(players, cmp);
            return players;
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return null;
    }

    public void saveToXML() {
        Document dom;
        Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement(xmlName);
            System.out.println(players.size());
            for(int i = 0; i < players.size(); i++) {
                // create data elements and place them under root
                e = dom.createElement("playerName"+i);
                e.appendChild(dom.createTextNode(players.get(i).GetName()));
                rootEle.appendChild(e);

                e = dom.createElement("playerScore"+i);
                Integer tmpInt = players.get(i).GetPoints();
                e.appendChild(dom.createTextNode(tmpInt.toString()));
                rootEle.appendChild(e);
            }
            Collections.sort(players, cmp);
            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Toplist.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xmlName)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    public void AddPlayer(Player p){
        if(players.size() < 10) {
            players.add(p);
            saveToXML();
        } else if (p.GetPoints() > players.get(players.size()-1).GetPoints()){
            players.remove(players.get(players.size()-1));
            players.add(p);
            Collections.sort(players, cmp);
            saveToXML();
        }
    }

    private String getTextValue(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}
