package printservice;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class JavaPrintService
        implements Runnable {

    private final Socket m_socket;
    private static int port = 12345;
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
                InputStream is = m_socket.getInputStream();
                try {
                    PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
                    PrinterJob pjob = PrinterJob.getPrinterJob();
                    pjob.setPrintService(ps);
                    pjob.setJobName("EDIS-priznanica");
                    Printable printable = new PrintableTicket(is);
                    pjob.setPrintable(printable);
                    pjob.print();
                    is.close();
                } catch (PrinterException ex) {
                    Logger.getLogger(JavaPrintService.class.getName()).log(Level.SEVERE, null, ex);
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
