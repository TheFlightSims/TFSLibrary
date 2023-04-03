package org.hsqldb;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.result.Result;

public class ClientConnectionHTTP extends ClientConnection {
  static final String ENCODING = "ISO-8859-1";
  
  static final int IDLENGTH = 12;
  
  private HttpURLConnection httpConnection = null;
  
  public ClientConnectionHTTP(String paramString1, int paramInt1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, String paramString4, String paramString5, int paramInt2) {
    super(paramString1, paramInt1, paramString2, paramString3, paramBoolean1, paramBoolean2, paramString4, paramString5, paramInt2);
  }
  
  protected void initConnection(String paramString, int paramInt, boolean paramBoolean) {}
  
  protected void openConnection(String paramString, int paramInt, boolean paramBoolean) {
    try {
      URL uRL = null;
      String str = "";
      if (!this.path.endsWith("/"))
        str = "/"; 
      str = "http://" + paramString + ":" + paramInt + this.path + str + this.database;
      if (paramBoolean) {
        uRL = new URL("https://" + paramString + ":" + paramInt + this.path + str + this.database);
      } else {
        uRL = new URL(str);
      } 
      this.httpConnection = (HttpURLConnection)uRL.openConnection();
      this.httpConnection.setDefaultUseCaches(false);
    } catch (IOException iOException) {
      iOException.printStackTrace(System.out);
    } 
  }
  
  protected void closeConnection() {}
  
  public synchronized Result execute(Result paramResult) {
    openConnection(this.host, this.port, this.isTLS);
    Result result = super.execute(paramResult);
    closeConnection();
    return result;
  }
  
  protected void write(Result paramResult) throws IOException, HsqlException {
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)hsqlByteArrayOutputStream);
    paramResult.write(this, dataOutputStream, this.rowOut);
    this.httpConnection.setRequestMethod("POST");
    this.httpConnection.setDoOutput(true);
    this.httpConnection.setUseCaches(false);
    this.httpConnection.setRequestProperty("Content-Type", "application/octet-stream");
    this.httpConnection.setRequestProperty("Content-Length", String.valueOf(12 + hsqlByteArrayOutputStream.size()));
    this.dataOutput = new DataOutputStream(this.httpConnection.getOutputStream());
    this.dataOutput.writeInt(paramResult.getDatabaseId());
    this.dataOutput.writeLong(paramResult.getSessionId());
    hsqlByteArrayOutputStream.writeTo((OutputStream)this.dataOutput);
    this.dataOutput.flush();
  }
  
  protected Result read() throws IOException, HsqlException {
    this.dataInput = new DataInputStream(new BufferedInputStream(this.httpConnection.getInputStream()));
    this.rowOut.reset();
    Result result = Result.newResult(this.dataInput, this.rowIn);
    result.readAdditionalResults(this, this.dataInput, this.rowIn);
    this.dataInput.close();
    return result;
  }
  
  protected void handshake() throws IOException {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ClientConnectionHTTP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */