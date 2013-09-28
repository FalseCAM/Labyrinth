/**
 *
 */
package net.falsecam.labyrinth.model.map;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author FalseCAM
 *
 */
public class MapLoader implements AssetLoader {

    private String data;
    private int width;
    private int height;
    private MapElement change;
    private MapElement[][] objects;

    public MapLoader() {
    }

    private MapLoader(InputStream in) {
        this.width = 0;
        this.height = 0;
        parse(in);
        calculateDimensions();
        calculateObjects();
    }

    private MapElement[][] getObjects() {
        return objects;
    }

    private MapElement getChange() {
        return change;
    }

    private void calculateDimensions() {
        String str;
        BufferedReader reader = new BufferedReader(
                new StringReader(data));
        this.width = 0;
        this.height = 0;
        try {
            while ((str = reader.readLine()) != null) {
                if (str.length() == 0) {
                    continue;
                }
                if (str.length() > width) {
                    this.width = str.length();
                }
                this.height++;
            }
        } catch (IOException e) {
        }
    }

    private void calculateObjects() {
        this.objects = new MapElement[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.objects[j][i] = new MapElement(MapType.CLOSED);
            }
        }

        String str;
        BufferedReader reader = new BufferedReader(
                new StringReader(data));
        int j = 0;
        try {
            while ((str = reader.readLine()) != null) {
                if (str.length() == 0) {
                    continue;
                }
                for (int i = 0; i < str.length(); i++) {
                    this.objects[j][i] = new MapElement(MapType.get(str.substring(i, i + 1)));
                }
                j++;
            }
        } catch (IOException e) {
        }
    }

    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        InputStream in = assetInfo.openStream();
        MapLoader mapLoader = new MapLoader(in);
        AbstractMap map = new AbstractMap(mapLoader.getObjects(), mapLoader.getChange());

        return map;
    }

    private void parse(InputStream in) {
        DocumentBuilderFactory builderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = builderFactory.newDocumentBuilder();
            document = builder.parse(in);
            parse(document);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parse(Document doc) {
        NodeList changeNode = doc.getElementsByTagName("change");
        change = new MapElement(MapType.get(changeNode.item(0).getChildNodes().item(0).getNodeValue()));
        NodeList map = doc.getElementsByTagName("mapdata");
        data = map.item(0).getChildNodes().item(1).getNodeValue();
    }
}
