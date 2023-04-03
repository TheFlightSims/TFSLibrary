package org.hsqldb.types;

import java.io.Reader;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;

public class ClobDataID implements ClobData {
  long id;
  
  long length = -1L;
  
  public ClobDataID(long paramLong) {
    this.id = paramLong;
  }
  
  public char[] getChars(SessionInterface paramSessionInterface, long paramLong, int paramInt) {
    ResultLob resultLob = ResultLob.newLobGetCharsRequest(this.id, paramLong, paramInt);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getCharArray();
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
  
  public String getSubString(SessionInterface paramSessionInterface, long paramLong, int paramInt) {
    char[] arrayOfChar = getChars(paramSessionInterface, paramLong, paramInt);
    return new String(arrayOfChar);
  }
  
  public ClobData duplicate(SessionInterface paramSessionInterface) {
    ResultLob resultLob = ResultLob.newLobDuplicateRequest(this.id);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    long l = ((ResultLob)result).getLobID();
    return new ClobDataID(l);
  }
  
  public ClobData getClob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2) {
    ResultLob resultLob = ResultLob.newLobGetRequest(this.id, paramLong1, paramLong2);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    long l = ((ResultLob)result).getLobID();
    return new ClobDataID(l);
  }
  
  public void truncate(SessionInterface paramSessionInterface, long paramLong) {
    ResultLob resultLob = ResultLob.newLobTruncateRequest(this.id, paramLong);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    this.length = ((ResultLob)result).getBlockLength();
  }
  
  public Reader getCharacterStream(SessionInterface paramSessionInterface) {
    long l = length(paramSessionInterface);
    return new ClobInputStream(paramSessionInterface, this, 0L, l);
  }
  
  public void setCharacterStream(SessionInterface paramSessionInterface, long paramLong, Reader paramReader) {}
  
  public void setString(SessionInterface paramSessionInterface, long paramLong, String paramString) {
    ResultLob resultLob = ResultLob.newLobSetCharsRequest(this.id, paramLong, paramString.toCharArray());
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    this.length = ((ResultLob)result).getBlockLength();
  }
  
  public void setClob(SessionInterface paramSessionInterface, long paramLong1, ClobData paramClobData, long paramLong2, long paramLong3) {}
  
  public void setChars(SessionInterface paramSessionInterface, long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramInt1 != 0 || paramInt2 != paramArrayOfchar.length) {
      if (!isInLimits(paramArrayOfchar.length, paramInt1, paramInt2))
        throw Error.error(3401); 
      if (paramInt1 != 0 || paramInt2 != paramArrayOfchar.length) {
        char[] arrayOfChar = new char[paramInt2];
        System.arraycopy(paramArrayOfchar, paramInt1, arrayOfChar, 0, paramInt2);
        paramArrayOfchar = arrayOfChar;
      } 
    } 
    ResultLob resultLob = ResultLob.newLobSetCharsRequest(this.id, paramLong, paramArrayOfchar);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    this.length = ((ResultLob)result).getBlockLength();
  }
  
  public long position(SessionInterface paramSessionInterface, String paramString, long paramLong) {
    ResultLob resultLob = ResultLob.newLobGetCharPatternPositionRequest(this.id, paramString.toCharArray(), paramLong);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getOffset();
  }
  
  public long position(SessionInterface paramSessionInterface, ClobData paramClobData, long paramLong) {
    ResultLob resultLob = ResultLob.newLobGetCharPatternPositionRequest(this.id, paramClobData.getId(), paramLong);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getOffset();
  }
  
  public long nonSpaceLength(SessionInterface paramSessionInterface) {
    ResultLob resultLob = ResultLob.newLobGetTruncateLength(this.id);
    Result result = paramSessionInterface.execute((Result)resultLob);
    if (result.isError())
      throw result.getException(); 
    return ((ResultLob)result).getBlockLength();
  }
  
  public Reader getCharacterStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2) {
    return new ClobInputStream(paramSessionInterface, this, paramLong1, paramLong2);
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setId(long paramLong) {
    this.id = paramLong;
  }
  
  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3) {
    return (paramLong2 >= 0L && paramLong3 >= 0L && paramLong2 + paramLong3 <= paramLong1);
  }
  
  public void setSession(SessionInterface paramSessionInterface) {}
  
  public boolean isBinary() {
    return false;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof BlobDataID) ? ((this.id == ((BlobDataID)paramObject).id)) : false;
  }
  
  public int hashCode() {
    return (int)this.id;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\ClobDataID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */