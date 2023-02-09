package com.example;

import com.deloitte.excel.art.ArtReport;
import com.deloitte.excel.report.ExportAllReport;
import com.deloitte.publish.Publisher;
import com.deloitte.publish.mail.MailPublisher;
import com.deloitte.publish.sharepoint.SharePointPublisher;
import com.microsoft.graph.models.Workbook;
import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.InvocationEvent;
import com.salesforce.functions.jvm.sdk.SalesforceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.time.Instant;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;   

import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

/**
 * Describe MyfunctionFunction here.
 */
public class MyfunctionFunction implements SalesforceFunction<FunctionInput, FunctionOutput> {
  private static final Logger LOGGER = LoggerFactory.getLogger(MyfunctionFunction.class);

@Override
  public FunctionOutput apply(InvocationEvent<FunctionInput> event, Context context)
      throws Exception {

        Instant startTime = Instant.now();
        System.out.println("Start Time Query Execution==> " + startTime);
        String status = event.getData().getStatus();
        System.out.println("Status ===>" + status);
        FunctionInput functionInput = event.getData();
        List<String> entityId = event.getData().getEntityList();
        System.out.println("Entity Id ===>" + entityId);
        String childFilter = event.getData().getChildFilter();
        System.out.println("Child Filter===>" + childFilter);
        
        String selectedEntityId = entityId.get(0);

        //Publisher publisher = new MailPublisher(functionInput);
        Publisher publisher = new SharePointPublisher(functionInput);

        ExportAllReport exportAllReport = new ExportAllReport();
        //Generate Export All Report
    //File conflictExportAllReportFile = exportAllReport.generateReport(context, selectedEntityId);
        //Publish Excel file
        //publisher.send(conflictExportAllReportFile);
        System.out.println("\nExport All Report has been successfully published to sharepoint ");

        //In case the art report need to be in the same file as the Export All Report
        //Follow these steps
        // 1 - Return the SXSSFWorkbook from ExportAllReport
        // 2 - Pass it as the parameter to ArtReport
        // 3 - Use that workbook for the ArtReport
        //ArtReport artReport = new ArtReport();
        //Art Report
        File conflictArtReportFile = ArtReport.generateReport(context, selectedEntityId);
        //Publish Excel file
        //publisher.send(conflictArtReportFile);
        System.out.println("\nArt Report has been successfully published to sharepoint ");
        // Export the XLSX file
        
        List<FunctionWrapper> finalresults = new ArrayList<FunctionWrapper>();
        List<Object> mdmresults=new ArrayList<>(); 
       /* 
         File file = new File("conflict-export-all-report.xlsx");   //creating a new file instance  
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
        //creating Workbook instance that refers to .xlsx file  
        
      
        XSSFWorkbook wb = new XSSFWorkbook(fis);   
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
        while (itr.hasNext())                 
        {  
          Row row = itr.next();  
          Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
          while (cellIterator.hasNext())   
          {  
              Cell cell = cellIterator.next(); 
              mdmresults.add(cell.getStringCellValue()); 
              
          }  
        }  
         */
        mdmresults.add("Rameswari added :");
        String REMOTE_HOST = "emamftstage1.deloitteresources.com";  
          //variable for user name  
          String USERNAME = "dtt_sc_dep_outbound";  
          //variable for password  
          String PASSWORD = "@N3mw#n<sG<p5Gfb7T0V";  
          //port number for SFTP  
          int REMOTE_PORT = 9022;  
          String localFile = conflictArtReportFile.getPath();  
          String remoteFile = "/GCRM/POC";  
          Session jschSession = null; 
          mdmresults.add("local file path:"+localFile); 
          try   
          {  
          JSch jsch = new JSch();  
          jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
          jschSession.setConfig("StrictHostKeyChecking", "no");
          jschSession.setPassword(PASSWORD);
          jschSession.connect(); 
          // authenticate using private key  
          // jsch.addIdentity("/home/javatpoint/.ssh/id_rsa");  
          // authenticate using password  
          // 10 seconds session timeout  
          Channel sftp = jschSession.openChannel("sftp");  
          // 5 seconds timeout  
          sftp.connect();  
          ChannelSftp channelSftp = (ChannelSftp) sftp;  
          // transfer file from local to remote server  
          channelSftp.put(localFile, remoteFile);  
          // download file from remote server to local  
          // channelSftp.get(remoteFile, localFile);  
          channelSftp.exit();  
          }   
          catch (JSchException | SftpException e)   
          {  
              e.printStackTrace();  
          }   
          finally   
          {  
          if (jschSession != null)   
          {  
          jschSession.disconnect();  
          }  
      }
       finalresults.add(new FunctionWrapper(mdmresults));
        return new FunctionOutput(finalresults);
  }
}
