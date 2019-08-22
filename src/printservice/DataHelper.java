/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printservice;

import java.awt.Font;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Mirjana
 */
public class DataHelper {

    private static Document root = createDocument();

    public static void initInputStream() {
        createDOMTree(pripremaPrometaZaStampu(PoljePriznanice.STAMPA_PRIZNANICE_KONTINUALNA));
    } 

    private static Document createDocument() {
        //instanca DocumentBuilderFactory-a
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //instanca builder-a
            DocumentBuilder db = dbf.newDocumentBuilder();
            //instanca DOM - potvrda
            return db.newDocument();
        } catch (ParserConfigurationException pce) {
            System.out.println("Greska kod stampanja uplatnice ili potvrde uplate - slanje podataka na stampac");
            return null;
        }
    }

    private static void createDOMTree(ArrayList<PoljePriznanice> lista) {

        Element rootEle = root.createElement("EDIS");
        root.appendChild(rootEle);

        Iterator it = lista.iterator();
        while (it.hasNext()) {
            PoljePriznanice stavka = (PoljePriznanice) it.next();
            Element edisElement = createEdisElement(stavka);
            if (edisElement != null) {
                rootEle.appendChild(edisElement);
            }
        }

    }

    private static Element createEdisElement(PoljePriznanice stavka) {

        if (stavka != null
                && stavka.getPozicijaLeft() != null
                && stavka.getPozicijaTop() != null
                && stavka.getFont() != null) {

            Element el = root.createElement("label");

            //kreira print element i dodaje text sa vrednoscu i dodaje u root
            Element print = root.createElement("print");
            Text printText = root.createTextNode(stavka.getVrednost());
            print.appendChild(printText);
            el.appendChild(print);

            Element font = root.createElement("font");
            Text fontText = root.createTextNode(stavka.getFont());
            font.appendChild(fontText);
            el.appendChild(font);

            Element pozicijaTop = root.createElement("top");
            Text pozicijaTopText = root.createTextNode(stavka.getPozicijaTop());
            pozicijaTop.appendChild(pozicijaTopText);
            el.appendChild(pozicijaTop);

            Element pozicijaLeft = root.createElement("left");
            Text pozicijaLeftText = root.createTextNode(stavka.getPozicijaLeft());
            pozicijaLeft.appendChild(pozicijaLeftText);
            el.appendChild(pozicijaLeft);
            return el;
        }
        return null;
    }

    static int iter = 1;

    public static ArrayList<PoljePriznanice> pripremaPrometaZaStampu(BigDecimal stampaPriznaniceNacin) {
        ArrayList<PoljePriznanice> poljaPriznanice = new ArrayList<PoljePriznanice>();

        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.OJ, "oj: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.OJ_2, "oj_2: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.OJ_GRAD, "oj_grad: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.TELEFON, "telefon: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.TRANSAKCIJA, "transakcija: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.TRANSAKCIJA_2, "transakcija 2: " + iter));

        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.IZNOS, "izons: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.IZNOS_2, "iznos 2: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.IZNOS_SLOVIMA, "iznos_slovima: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.PRIMALAC, "primalac: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.POTROSACKI_BROJ, "potrosacki_broj: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.DATUM_VALUTE, "datum_valute: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.DATUM_VALUTE_2, "datum_valute_2: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.NAPLATIO, "naplatio: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.NACIN_PLACANJA, "nacin_placanja: " + iter));

        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.BLAGAJNA, "blagajna: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.BLAGAJNA_2, "blagajna_2: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.ID_STAMPE, "id_stampe: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.ID_STAMPE_2, "id_stampe_2: " + iter));
        poljaPriznanice.add(new PoljePriznanice(stampaPriznaniceNacin, PoljePriznanice.POZIV_NA_BROJ, "poziv_na_broj: " + iter));
        iter++;
        return poljaPriznanice;
    }

    public static ByteArrayInputStream getFile() {
        try {
            initInputStream();
            DOMSource source = new DOMSource(root);
            StringWriter xmlAsWriter = new StringWriter();
            StreamResult result = new StreamResult(xmlAsWriter);
            TransformerFactory.newInstance().newTransformer().transform(source, result);
            System.out.println(xmlAsWriter);
            String printedDocument = xmlAsWriter.toString();
            ByteArrayInputStream inputStream = new ByteArrayInputStream((xmlAsWriter.toString()).getBytes("UTF-8"));
            return inputStream;
        } catch (Exception ie) {
            ie.printStackTrace();
            System.out.println("Greska - transformisanje DOM-a u ByteArrayInputStream");
        }
        return null;
    }

    public static Document getRoot() {
        return root;
    }

}
