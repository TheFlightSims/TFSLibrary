package org.hsqldb.server;

import java.io.IOException;
import java.io.OutputStream;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;

class OdbcPacketOutputStream extends DataOutputStream {
  private HsqlByteArrayOutputStream byteArrayOutputStream;
  
  private HsqlByteArrayOutputStream stringWriterOS = new HsqlByteArrayOutputStream();
  
  private DataOutputStream stringWriterDos = new DataOutputStream((OutputStream)this.stringWriterOS);
  
  private int packetStart = 0;
  
  public int getSize() {
    return this.count - this.packetStart;
  }
  
  synchronized void write(String paramString) throws IOException {
    write(paramString, true);
  }
  
  synchronized void write(String paramString, boolean paramBoolean) throws IOException {
    this.stringWriterDos.writeUTF(paramString);
    write(this.stringWriterOS.toByteArray(), 2, this.stringWriterOS.size() - 2);
    this.stringWriterOS.reset();
    if (paramBoolean)
      writeByte(0); 
  }
  
  synchronized void writeSized(String paramString) throws IOException {
    this.stringWriterDos.writeUTF(paramString);
    byte[] arrayOfByte = this.stringWriterOS.toByteArray();
    this.stringWriterOS.reset();
    writeInt(arrayOfByte.length - 2);
    write(arrayOfByte, 2, arrayOfByte.length - 2);
  }
  
  synchronized void reset() throws IOException {
    this.byteArrayOutputStream.reset();
    this.packetStart = this.count;
    writeInt(-1);
  }
  
  static OdbcPacketOutputStream newOdbcPacketOutputStream() throws IOException {
    return new OdbcPacketOutputStream(new HsqlByteArrayOutputStream());
  }
  
  protected OdbcPacketOutputStream(HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream) throws IOException {
    super((OutputStream)paramHsqlByteArrayOutputStream);
    this.byteArrayOutputStream = paramHsqlByteArrayOutputStream;
    reset();
  }
  
  synchronized int xmit(char paramChar, DataOutputStream paramDataOutputStream) throws IOException {
    byte[] arrayOfByte = this.byteArrayOutputStream.toByteArray();
    arrayOfByte[0] = (byte)(arrayOfByte.length >> 24);
    arrayOfByte[1] = (byte)(arrayOfByte.length >> 16);
    arrayOfByte[2] = (byte)(arrayOfByte.length >> 8);
    arrayOfByte[3] = (byte)arrayOfByte.length;
    reset();
    paramDataOutputStream.writeByte(paramChar);
    paramDataOutputStream.write(arrayOfByte);
    paramDataOutputStream.flush();
    return arrayOfByte.length;
  }
  
  public synchronized void close() throws IOException {
    super.close();
    this.stringWriterDos.close();
  }
  
  public synchronized void writeByteChar(char paramChar) throws IOException {
    writeByte(paramChar);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\OdbcPacketOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */