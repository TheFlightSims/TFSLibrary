package org.hsqldb.server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.InOutUtil;
import org.hsqldb.resources.ResourceBundleHandler;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;

class WebServerConnection implements Runnable {
  static final String ENCODING = "ISO-8859-1";
  
  private Charset iso = Charset.forName("ISO-8859-1");
  
  private CharsetDecoder iso_8859_1_decoder = this.iso.newDecoder();
  
  private Socket socket;
  
  private WebServer server;
  
  private static final int REQUEST_TYPE_BAD = 0;
  
  private static final int REQUEST_TYPE_GET = 1;
  
  private static final int REQUEST_TYPE_HEAD = 2;
  
  private static final int REQUEST_TYPE_POST = 3;
  
  private static final String HEADER_OK = "HTTP/1.0 200 OK";
  
  private static final String HEADER_BAD_REQUEST = "HTTP/1.0 400 Bad Request";
  
  private static final String HEADER_NOT_FOUND = "HTTP/1.0 404 Not Found";
  
  private static final String HEADER_FORBIDDEN = "HTTP/1.0 403 Forbidden";
  
  static final int BUFFER_SIZE = 256;
  
  final byte[] mainBuffer = new byte[256];
  
  private RowOutputBinary rowOut = new RowOutputBinary(this.mainBuffer);
  
  private RowInputBinary rowIn = new RowInputBinary(this.rowOut);
  
  static byte[] BYTES_GET;
  
  static byte[] BYTES_HEAD;
  
  static byte[] BYTES_POST;
  
  static byte[] BYTES_CONTENT;
  
  static final byte[] BYTES_WHITESPACE = new byte[] { 32, 9 };
  
  private static final int hnd_content_types = ResourceBundleHandler.getBundleHandle("webserver-content-types", null);
  
  WebServerConnection(Socket paramSocket, WebServer paramWebServer) {
    this.server = paramWebServer;
    this.socket = paramSocket;
  }
  
  private String getMimeTypeString(String paramString) {
    if (paramString == null)
      return "text/html"; 
    int i = paramString.lastIndexOf('.');
    String str1 = null;
    String str2 = null;
    if (i >= 0) {
      str1 = paramString.substring(i).toLowerCase();
      str2 = this.server.serverProperties.getProperty(str1);
    } 
    if (str2 == null && str1.length() > 1)
      str2 = ResourceBundleHandler.getString(hnd_content_types, str1.substring(1)); 
    return (str2 == null) ? "text/html" : str2;
  }
  
  public void run() {
    DataInputStream dataInputStream = null;
    try {
      byte[] arrayOfByte;
      int j;
      dataInputStream = new DataInputStream(this.socket.getInputStream());
      String str = null;
      byte b = 0;
      while (true) {
        i = InOutUtil.readLine(dataInputStream, (OutputStream)this.rowOut);
        if (i == 0)
          throw new Exception(); 
        if (i >= 2) {
          arrayOfByte = this.rowOut.toByteArray();
          j = this.rowOut.size() - i;
          if (ArrayUtil.containsAt(arrayOfByte, j, BYTES_POST)) {
            b = 3;
            j += BYTES_POST.length;
          } else if (ArrayUtil.containsAt(arrayOfByte, j, BYTES_GET)) {
            b = 1;
            j += BYTES_GET.length;
          } else if (ArrayUtil.containsAt(arrayOfByte, j, BYTES_HEAD)) {
            b = 2;
            j += BYTES_HEAD.length;
          } else {
            b = 0;
          } 
          i = ArrayUtil.countStartElementsAt(arrayOfByte, j, BYTES_WHITESPACE);
          if (i == 0)
            b = 0; 
          break;
        } 
      } 
      j += i;
      int i = ArrayUtil.countNonStartElementsAt(arrayOfByte, j, BYTES_WHITESPACE);
      str = new String(arrayOfByte, j, i, "ISO-8859-1");
      switch (b) {
        case 3:
          processPost(dataInputStream, str);
          break;
        case 0:
          processError(0);
          break;
        case 1:
          processGet(str, true);
          break;
        case 2:
          processGet(str, false);
          break;
      } 
    } catch (Exception exception) {
      this.server.printStackTrace(exception);
    } finally {
      try {
        if (dataInputStream != null)
          dataInputStream.close(); 
        this.socket.close();
      } catch (IOException iOException) {
        this.server.printStackTrace(iOException);
      } 
    } 
  }
  
  private void processPost(InputStream paramInputStream, String paramString) throws IOException {
    try {
      while (true) {
        int i = InOutUtil.readLine(paramInputStream, (OutputStream)this.rowOut);
        if (i <= 2) {
          String str = this.iso_8859_1_decoder.decode(ByteBuffer.wrap(this.rowOut.toByteArray())).toString();
          if (str.indexOf("Content-Type: application/octet-stream") < 0)
            throw new Exception(); 
          processQuery(paramInputStream);
          return;
        } 
      } 
    } catch (Exception exception) {
      processError(400);
      return;
    } 
  }
  
  void processQuery(InputStream paramInputStream) {
    try {
      Result result2;
      DataInputStream dataInputStream = new DataInputStream(paramInputStream);
      int i = dataInputStream.readInt();
      long l = dataInputStream.readLong();
      byte b = dataInputStream.readByte();
      Session session = DatabaseManager.getSession(i, l);
      Result result1 = Result.newResult(session, b, dataInputStream, this.rowIn);
      result1.setDatabaseId(i);
      result1.setSessionId(l);
      if (result1.getType() == 31) {
        try {
          String str1 = result1.getDatabaseName();
          int k = this.server.getDBIndex(str1);
          int m = this.server.dbID[k];
          session = DatabaseManager.newSession(m, result1.getMainString(), result1.getSubString(), result1.getZoneString(), result1.getUpdateCount());
          result1.readAdditionalResults((SessionInterface)session, dataInputStream, this.rowIn);
          result2 = Result.newConnectionAcknowledgeResponse(session.getDatabase(), session.getId(), m);
        } catch (HsqlException hsqlException) {
          result2 = Result.newErrorResult((Throwable)hsqlException);
        } catch (RuntimeException runtimeException) {
          result2 = Result.newErrorResult(runtimeException);
        } 
      } else {
        int k = result1.getDatabaseId();
        if (session == null) {
          result2 = Result.newErrorResult((Throwable)Error.error(402));
        } else {
          result1.setSession((SessionInterface)session);
          result1.readLobResults((SessionInterface)session, dataInputStream, this.rowIn);
          result2 = session.execute(result1);
        } 
      } 
      int j = result1.getType();
      if (j == 32 || j == 10) {
        DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        String str1 = getHead("HTTP/1.0 200 OK", false, "application/octet-stream", 6);
        dataOutputStream.write(str1.getBytes("ISO-8859-1"));
        dataOutputStream.writeByte(32);
        dataOutputStream.writeInt(4);
        dataOutputStream.writeByte(0);
        dataOutputStream.close();
        return;
      } 
      HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
      DataOutputStream dataOutputStream1 = new DataOutputStream((OutputStream)hsqlByteArrayOutputStream);
      result2.write((SessionInterface)session, dataOutputStream1, (RowOutputInterface)this.rowOut);
      DataOutputStream dataOutputStream2 = new DataOutputStream(this.socket.getOutputStream());
      String str = getHead("HTTP/1.0 200 OK", false, "application/octet-stream", hsqlByteArrayOutputStream.size());
      dataOutputStream2.write(str.getBytes("ISO-8859-1"));
      hsqlByteArrayOutputStream.writeTo((OutputStream)dataOutputStream2);
      dataOutputStream2.close();
    } catch (Exception exception) {
      this.server.printStackTrace(exception);
    } 
  }
  
  private void processGet(String paramString, boolean paramBoolean) {
    try {
      String str;
      if (paramString.endsWith("/"))
        paramString = paramString + this.server.getDefaultWebPage(); 
      if (paramString.indexOf("..") != -1) {
        processError(403);
        return;
      } 
      paramString = this.server.getWebRoot() + paramString;
      if (File.separatorChar != '/')
        paramString = paramString.replace('/', File.separatorChar); 
      DataInputStream dataInputStream = null;
      this.server.printWithThread("GET " + paramString);
      try {
        File file = new File(paramString);
        dataInputStream = new DataInputStream(new FileInputStream(file));
        str = getHead("HTTP/1.0 200 OK", true, getMimeTypeString(paramString), (int)file.length());
      } catch (IOException iOException) {
        processError(404);
        if (dataInputStream != null)
          dataInputStream.close(); 
        return;
      } 
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.socket.getOutputStream());
      bufferedOutputStream.write(str.getBytes("ISO-8859-1"));
      if (paramBoolean) {
        int i;
        while ((i = dataInputStream.read()) != -1)
          bufferedOutputStream.write(i); 
      } 
      bufferedOutputStream.flush();
      bufferedOutputStream.close();
      dataInputStream.close();
    } catch (Exception exception) {
      this.server.printError("processGet: " + exception.toString());
      this.server.printStackTrace(exception);
    } 
  }
  
  String getHead(String paramString1, boolean paramBoolean, String paramString2, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append(paramString1).append("\r\n");
    if (paramBoolean) {
      stringBuffer.append("Allow: GET, HEAD, POST\nMIME-Version: 1.0\r\n");
      stringBuffer.append("Server: ").append("HSQL Database Engine").append("\r\n");
    } 
    if (paramString2 != null) {
      stringBuffer.append("Cache-Control: no-cache\r\n");
      stringBuffer.append("Content-Type: ").append(paramString2).append("\r\n");
    } 
    stringBuffer.append("\r\n");
    return stringBuffer.toString();
  }
  
  private void processError(int paramInt) {
    String str;
    this.server.printWithThread("processError " + paramInt);
    switch (paramInt) {
      case 400:
        str = getHead("HTTP/1.0 400 Bad Request", false, null, 0);
        str = str + ResourceBundleHandler.getString(WebServer.webBundleHandle, "BAD_REQUEST");
        break;
      case 403:
        str = getHead("HTTP/1.0 403 Forbidden", false, null, 0);
        str = str + ResourceBundleHandler.getString(WebServer.webBundleHandle, "FORBIDDEN");
        break;
      default:
        str = getHead("HTTP/1.0 404 Not Found", false, null, 0);
        str = str + ResourceBundleHandler.getString(WebServer.webBundleHandle, "NOT_FOUND");
        break;
    } 
    try {
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.socket.getOutputStream());
      bufferedOutputStream.write(str.getBytes("ISO-8859-1"));
      bufferedOutputStream.flush();
      bufferedOutputStream.close();
    } catch (Exception exception) {
      this.server.printError("processError: " + exception.toString());
      this.server.printStackTrace(exception);
    } 
  }
  
  String getConnectionThreadName() {
    return "HSQLDB HTTP Connection @" + Integer.toString(hashCode(), 16);
  }
  
  static {
    try {
      BYTES_GET = "GET".getBytes("ISO-8859-1");
      BYTES_HEAD = "HEAD".getBytes("ISO-8859-1");
      BYTES_POST = "POST".getBytes("ISO-8859-1");
      BYTES_CONTENT = "Content-Length: ".getBytes("ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Error.runtimeError(201, "RowOutputTextLog");
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\WebServerConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */