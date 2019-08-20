/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printservice;

import javax.print.*;
import javax.print.event.*;

/**
 *
 * @author Mirjana
 */
public class PrintJobWatcher implements PrintJobListener {

    private DocPrintJob docPrintJob;
    
    PrintJobWatcher(DocPrintJob job) {
        this.docPrintJob = job;
    }

    @Override
    public void printDataTransferCompleted(PrintJobEvent pje) {
        System.out.println("helloservice.PrintJobWatcher.printDataTransferCompleted()");
    }

    @Override
    public void printJobCompleted(PrintJobEvent pje) {
        System.out.println("helloservice.PrintJobWatcher.printJobCompleted()");
    }

    @Override
    public void printJobFailed(PrintJobEvent pje) {
        System.out.println("helloservice.PrintJobWatcher.printJobFailed()");
    }

    @Override
    public void printJobCanceled(PrintJobEvent pje) {
        System.out.println("helloservice.PrintJobWatcher.printJobCanceled()");
    }

    @Override
    public void printJobNoMoreEvents(PrintJobEvent pje) {
        System.out.println("helloservice.PrintJobWatcher.printJobNoMoreEvents()");
    }

    @Override
    public void printJobRequiresAttention(PrintJobEvent pje) {
        System.out.println("helloservice.PrintJobWatcher.printJobRequiresAttention()");
    }
}
