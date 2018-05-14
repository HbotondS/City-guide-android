package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;

public abstract class XMLWorker {
	private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder db;
	private static Document doc;

	public static ArrayList<Score> read(String filename) {
		ArrayList<Score> scores = new ArrayList<>();
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(filename);
			doc.getDocumentElement().normalize();

			NodeList nlist = doc.getElementsByTagName("score");

			for (int i = 0; i < nlist.getLength(); i++) {
				Node node = nlist.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)node;

					Score temp = new Score();
					temp.setName(element.getElementsByTagName("name").item(0).getTextContent());
					temp.setValue(Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent()));

					scores.add(temp);
				}
			}


		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return scores;
	}

	public static void write(String filename, ArrayList<Score> scores) {
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.newDocument();

			Element root = doc.createElement("scores");
			doc.appendChild(root);
			Element score, name, value;

			for (int i = 0; i < scores.size(); i++) {
				score = doc.createElement("score");
				root.appendChild(score);

				name = doc.createElement("name");
				name.appendChild(doc.createTextNode(scores.get(i).getName()));
				score.appendChild(name);

				value = doc.createElement("value");
				value.appendChild(doc.createTextNode(String.valueOf(scores.get(i).getValue())));
				score.appendChild(value);
			}

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(filename);

			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

	}
}
