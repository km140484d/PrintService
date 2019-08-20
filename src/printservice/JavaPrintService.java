package printservice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

public class JavaPrintService
        implements Runnable {

    private final Socket m_socket;
    private final int m_num;

    JavaPrintService(Socket socket, int num) {
        m_socket = socket;
        m_num = num;

        Thread handler = new Thread(this, "handler-" + m_num);
        handler.start();
    }

    public void run() {
        try {
            try {
                System.out.println(m_num + " Connected.");
                BufferedReader in = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
                OutputStreamWriter out = new OutputStreamWriter(m_socket.getOutputStream());
                out.write("Welcome connection #" + m_num + "\n\r");
                out.flush();

                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        System.out.println(m_num + " Closed.");
                        return;
                    } else {
                        if (line.equals("exit")) {
                            System.out.println(m_num + " Closing Connection.");
                            return;
                        } else {
                            try {
                                PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                                try (InputStream is = new ByteArrayInputStream((line+ "\f").getBytes("UTF8"))) {
                                    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                                    pras.add(new Copies(1));
//                                pras.add(MediaName.ISO_A4_WHITE);

                                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                                    Doc doc = new SimpleDoc(is, flavor, null);
                                    DocPrintJob docPrintJob = printService.createPrintJob();
                                    docPrintJob.addPrintJobListener(new PrintJobWatcher(docPrintJob));
                                    docPrintJob.print(doc, null);
                                }
                                System.out.println("PRINT SERVICE NAME IS: " + printService.getName());
                            } catch (IOException e) {
                                System.out.println(e.getLocalizedMessage());
                            } catch (PrintException ex) {
                                Logger.getLogger(JavaPrintService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            } finally {
                m_socket.close();
            }
        } catch (IOException e) {
            System.out.println(m_num + " Error: " + e.toString());
        }
    }

    public static void main(String[] args)
            throws Exception {
        int port = 9002;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.println("Accepting connections on port: " + port);
        int nextNum = 1;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            JavaPrintService hw = new JavaPrintService(socket, nextNum++);
        }
    }

}
