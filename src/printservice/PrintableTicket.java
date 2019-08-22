/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printservice;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class PrintableTicket implements Printable {

    private final InputStream is;
    

    public PrintableTicket(InputStream is) {
        this.is = is;
    }

    public static int mm2pt(int mm) {
        return (int) (mm * 2.835);
    }

    private int cnt = 0;

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Paper paper = new Paper();
        paper.setSize(mm2pt(297), mm2pt(210));
        pageFormat.setPaper(paper);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        if (cnt == 1) {
            printDocument_PrintPage(is, graphics);
        }
        cnt++;
        return PAGE_EXISTS;
    }

    public static void printDocument_PrintPage(InputStream is, Graphics pg) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new InputSource(is)); //use InputSource here to get better support for encodings
            if (document != null) {
                Element root = document.getDocumentElement();
                NodeList nList = root.getElementsByTagName("label");
                for (int i = 0; i < nList.getLength(); i++) {
                    Element labelElem = (Element) nList.item(i);
                    NodeList labelNodes = labelElem.getChildNodes();
                    String print = "";
                    int font = 0, top = 0, left = 0;
                    for (int j = 0; j < labelNodes.getLength(); j++) {
                        Element elem = (Element) labelNodes.item(j);
                        String data = ((Text) elem.getChildNodes().item(0)).getWholeText();
                        switch (elem.getLocalName()) {
                            case "print":
                                print = data;
                                break;
                            case "font":
                                font = Integer.parseInt(data);
                                break;
                            case "top":
                                top = Integer.parseInt(data);
                                break;
                            case "left":
                                left = Integer.parseInt(data);
                                break;
                        }
                    }
                    pg.setFont(new Font("Arial", Font.PLAIN, mm2pt(font)));
                    pg.drawString(print, mm2pt(left), mm2pt(top));
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
