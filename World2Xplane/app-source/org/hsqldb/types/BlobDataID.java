package org.hsqldb.types;

import java.io.InputStream;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;

public class BlobDataID implements BlobData {
  long id;
  
  long length = -1L;
  
  public BlobDataID(long paramLong) {
    this.id = paramLong;
  }
  
  public BlobData duplicate(SessionInterface paramSessionInterface) {
    ResultLob resultLob = ResultLob.newLobDuplicateRequest(this.id);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    long l = ((ResultLob)result).getLobID();
    return new BlobDataID(l);
  }
  
  public void free() {}
  
  public InputStream getBinaryStream(SessionInterface paramSessionInterface) {
    long l = length(paramSessionInterface);
    return new BlobInputStream(paramSessionInterface, this, 0L, l);
  }
  
  public InputStream getBinaryStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2) {
    return new BlobInputStream(paramSessionInterface, this, paramLong1, paramLong2);
  }
  
  public byte[] getBytes() {
    return null;
  }
  
  public byte[] getBytes(SessionInterface paramSessionInterface, long paramLong, int paramInt) {
    ResultLob resultLob = ResultLob.newLobGetBytesRequest(this.id, paramLong, paramInt);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw Error.error(result); 
    return ((ResultLob)result).getByteArray();
  }
  
  public BlobData getBlob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2) {
    ResultLob resultLob = ResultLob.newLobGetRequest(this.id, paramLong1, paramLong2);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw Error.error(result); 
    long l = ((ResultLob)result).getLobID();
    return new BlobDataID(l);
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setId(long paramLong) {
    this.id = paramLong;
  }
  
  public int getStreamBlockSize() {
    return 0;
  }
  
  public boolean isClosed() {
    return false;
  }
  
  public long length(SessionInterface paramSessionInterface) {
    if (this.length > -1L)
      return this.length; 
    ResultLob resultLob = ResultLob.newLobGetLengthRequest(this.id);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    this.length = ((ResultLob)result).getBlockLength();
    return this.length;
  }
  
  public long bitLength(SessionInterface paramSessionInterface) {
    return length(paramSessionInterface) * 8L;
  }
  
  public boolean isBits() {
    return false;
  }
  
  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong) {
    ResultLob resultLob = ResultLob.newLobGetCharPatternPositionRequest(this.id, paramBlobData.getId(), paramLong);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getOffset();
  }
  
  public long position(SessionInterface paramSessionInterface, byte[] paramArrayOfbyte, long paramLong) {
    ResultLob resultLob = ResultLob.newLobGetBytePatternPositionRequest(this.id, paramArrayOfbyte, paramLong);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getOffset();
  }
  
  public long nonZeroLength(SessionInterface paramSessionInterface) {
    ResultLob resultLob = ResultLob.newLobGetTruncateLength(this.id);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getBlockLength();
  }
  
  public void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt1 != 0 || paramInt2 != paramArrayOfbyte.length) {
      if (!BinaryData.isInLimits(paramArrayOfbyte.length, paramInt1, paramInt2))
        throw new IndexOutOfBoundsException(); 
      byte[] arrayOfByte = new byte[paramInt2];
      System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, paramInt2);
      paramArrayOfbyte = arrayOfByte;
    } 
    ResultLob resultLob = ResultLob.newLobSetBytesRequest(this.id, paramLong, paramArrayOfbyte);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    this.length = ((ResultLob)result).getBlockLength();
  }
  
  public void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfbyte) {
    setBytes(paramSessionInterface, paramLong, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void setBytes(SessionInterface paramSessionInterface, long paramLong1, BlobData paramBlobData, long paramLong2, long paramLong3) {
    if (paramLong3 > 2147483647L)
      throw new IndexOutOfBoundsException(); 
    byte[] arrayOfByte = paramBlobData.getBytes(paramSessionInterface, paramLong2, (int)paramLong3);
    setBytes(paramSessionInterface, paramLong1, arrayOfByte, 0, arrayOfByte.length);
  }
  
  public void setBinaryStream(SessionInterface paramSessionInterface, long paramLong, InputStream paramInputStream) {}
  
  public void setSession(SessionInterface paramSessionInterface) {}
  
  public void truncate(SessionInterface paramSessionInterface, long paramLong) {
    ResultLob resultLob = ResultLob.newLobTruncateRequest(this.id, paramLong);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
  }
  
  public boolean isBinary() {
    return true;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof BlobDataID) ? ((this.id == ((BlobDataID)paramObject).id)) : false;
  }
  
  public int hashCode() {
    return (int)this.id;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\BlobDataID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */