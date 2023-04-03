package org.hsqldb.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hsqldb.DatabaseManager;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;

public class Servlet extends HttpServlet {
  private static final int BUFFER_SIZE = 256;
  
  private String dbType;
  
  private String dbPath;
  
  private String errorStr;
  
  private RowOutputBinary rowOut;
  
  private RowInputBinary rowIn;
  
  private int iQueries;
  
  private static long lModified = 0L;
  
  public void init(ServletConfig paramServletConfig) {
    try {
      super.init(paramServletConfig);
      this.rowOut = new RowOutputBinary(256, 1);
      this.rowIn = new RowInputBinary(this.rowOut);
    } catch (ServletException servletException) {
      log(servletException.toString());
    } 
    String str1 = getInitParameter("hsqldb.server.database");
    if (str1 == null)
      str1 = "."; 
    String str2 = getInitParameter("hsqldb.server.use_web-inf_path");
    if (!str1.equals(".") && "true".equalsIgnoreCase(str2))
      str1 = getServletContext().getRealPath("/") + "WEB-INF/" + str1; 
    HsqlProperties hsqlProperties = DatabaseURL.parseURL(str1, false, false);
    log("Database filename = " + str1);
    if (hsqlProperties == null) {
      this.errorStr = "Bad Database name";
    } else {
      this.dbPath = hsqlProperties.getProperty("database");
      this.dbType = hsqlProperties.getProperty("connection_type");
      try {
        DatabaseManager.getDatabase(this.dbType, this.dbPath, hsqlProperties);
      } catch (HsqlException hsqlException) {
        this.errorStr = hsqlException.getMessage();
      } 
    } 
    if (this.errorStr == null) {
      log("Initialization completed.");
    } else {
      log("Database could not be initialised.");
      log(this.errorStr);
    } 
  }
  
  protected long getLastModified(HttpServletRequest paramHttpServletRequest) {
    return lModified++;
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws IOException, ServletException {
    String str = paramHttpServletRequest.getQueryString();
    if (str == null || str.length() == 0) {
      paramHttpServletResponse.setContentType("text/html");
      paramHttpServletResponse.setHeader("Pragma", "no-cache");
      PrintWriter printWriter = paramHttpServletResponse.getWriter();
      printWriter.println("<html><head><title>HSQL Database Engine Servlet</title>");
      printWriter.println("</head><body><h1>HSQL Database Engine Servlet</h1>");
      printWriter.println("The servlet is running.<p>");
      if (this.errorStr == null) {
        printWriter.println("The database is also running.<p>");
        printWriter.println("Database name: " + this.dbType + this.dbPath + "<p>");
        printWriter.println("Queries processed: " + this.iQueries + "<p>");
      } else {
        printWriter.println("<h2>The database is not running!</h2>");
        printWriter.println("The error message is:<p>");
        printWriter.println(this.errorStr);
      } 
      printWriter.println("</body></html>");
    } 
  }
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws IOException, ServletException {
    synchronized (this) {
      DataInputStream dataInputStream = null;
      DataOutputStream dataOutputStream = null;
      try {
        Result result2;
        dataInputStream = new DataInputStream((InputStream)paramHttpServletRequest.getInputStream());
        int i = dataInputStream.readInt();
        long l = dataInputStream.readLong();
        byte b = dataInputStream.readByte();
        Session session = DatabaseManager.getSession(i, l);
        Result result1 = Result.newResult(session, b, dataInputStream, this.rowIn);
        result1.setDatabaseId(i);
        result1.setSessionId(l);
        int j = result1.getType();
        if (j == 31) {
          try {
            session = DatabaseManager.newSession(this.dbType, this.dbPath, result1.getMainString(), result1.getSubString(), new HsqlProperties(), result1.getZoneString(), result1.getUpdateCount());
            result1.readAdditionalResults(null, dataInputStream, this.rowIn);
            result2 = Result.newConnectionAcknowledgeResponse(session.getDatabase(), session.getId(), session.getDatabase().getDatabaseID());
          } catch (HsqlException hsqlException) {
            result2 = Result.newErrorResult((Throwable)hsqlException);
          } 
        } else {
          if (j == 32 || j == 10) {
            paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
            paramHttpServletResponse.setContentType("application/octet-stream");
            paramHttpServletResponse.setContentLength(6);
            dataOutputStream = new DataOutputStream((OutputStream)paramHttpServletResponse.getOutputStream());
            dataOutputStream.writeByte(32);
            dataOutputStream.writeInt(4);
            dataOutputStream.writeByte(0);
            dataOutputStream.close();
            return;
          } 
          int k = result1.getDatabaseId();
          long l1 = result1.getSessionId();
          session = DatabaseManager.getSession(k, l1);
          result1.readLobResults((SessionInterface)session, dataInputStream, this.rowIn);
          result2 = session.execute(result1);
        } 
        HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
        DataOutputStream dataOutputStream1 = new DataOutputStream((OutputStream)hsqlByteArrayOutputStream);
        result2.write((SessionInterface)session, dataOutputStream1, (RowOutputInterface)this.rowOut);
        paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
        paramHttpServletResponse.setContentType("application/octet-stream");
        paramHttpServletResponse.setContentLength(hsqlByteArrayOutputStream.size());
        dataOutputStream = new DataOutputStream((OutputStream)paramHttpServletResponse.getOutputStream());
        hsqlByteArrayOutputStream.writeTo((OutputStream)dataOutputStream);
        this.iQueries++;
      } catch (HsqlException hsqlException) {
      
      } finally {
        if (dataOutputStream != null)
          dataOutputStream.close(); 
        if (dataInputStream != null)
          dataInputStream.close(); 
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\Servlet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */