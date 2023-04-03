package org.hsqldb.scriptio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import org.hsqldb.Database;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LineReader;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.persist.Crypto;
import org.hsqldb.rowio.RowInputTextLog;

public class ScriptReaderDecode extends ScriptReaderText {
  DataInputStream dataInput;
  
  Crypto crypto;
  
  byte[] buffer = new byte[256];
  
  public ScriptReaderDecode(Database paramDatabase, String paramString, Crypto paramCrypto, boolean paramBoolean) throws IOException {
    this(paramDatabase, paramDatabase.logger.getFileAccess().openInputStreamElement(paramString), paramCrypto, paramBoolean);
  }
  
  public ScriptReaderDecode(Database paramDatabase, InputStream paramInputStream, Crypto paramCrypto, boolean paramBoolean) throws IOException {
    super(paramDatabase);
    this.crypto = paramCrypto;
    this.rowIn = new RowInputTextLog();
    if (paramBoolean) {
      this.dataInput = new DataInputStream(new BufferedInputStream(paramInputStream));
    } else {
      InputStream inputStream = paramCrypto.getInputStream(new BufferedInputStream(paramInputStream));
      inputStream = new GZIPInputStream(inputStream);
      this.dataStreamIn = new LineReader(inputStream, "ISO-8859-1");
    } 
  }
  
  public boolean readLoggedStatement(Session paramSession) {
    String str;
    if (this.dataInput == null)
      return super.readLoggedStatement(paramSession); 
    try {
      i = this.dataInput.readInt();
      if (i * 2 > this.buffer.length)
        this.buffer = new byte[i * 2]; 
      this.dataInput.readFully(this.buffer, 0, i);
    } catch (Throwable throwable) {
      return false;
    } 
    int i = this.crypto.decode(this.buffer, 0, i, this.buffer, 0);
    try {
      str = new String(this.buffer, 0, i, "ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw Error.error(unsupportedEncodingException, 452, null);
    } 
    this.lineCount++;
    this.statement = StringConverter.unicodeStringToString(str);
    if (this.statement == null)
      return false; 
    processStatement(paramSession);
    return true;
  }
  
  public void close() {
    try {
      if (this.dataStreamIn != null)
        this.dataStreamIn.close(); 
      if (this.dataInput != null)
        this.dataInput.close(); 
    } catch (Exception exception) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\scriptio\ScriptReaderDecode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */