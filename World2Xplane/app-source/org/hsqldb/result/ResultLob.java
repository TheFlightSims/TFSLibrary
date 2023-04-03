package org.hsqldb.result;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.rowio.RowOutputInterface;

public final class ResultLob extends Result {
  long lobID;
  
  int subType;
  
  long blockOffset;
  
  long blockLength;
  
  byte[] byteBlock;
  
  char[] charBlock;
  
  Reader reader;
  
  InputStream stream;
  
  private ResultLob() {
    super(18);
  }
  
  public static ResultLob newLobGetLengthRequest(long paramLong) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 10;
    resultLob.lobID = paramLong;
    return resultLob;
  }
  
  public static ResultLob newLobGetBytesRequest(long paramLong1, long paramLong2, int paramInt) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 1;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.blockLength = paramInt;
    return resultLob;
  }
  
  public static ResultLob newLobGetCharsRequest(long paramLong1, long paramLong2, int paramInt) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 3;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.blockLength = paramInt;
    return resultLob;
  }
  
  public static ResultLob newLobSetBytesRequest(long paramLong1, long paramLong2, byte[] paramArrayOfbyte) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 2;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.byteBlock = paramArrayOfbyte;
    resultLob.blockLength = paramArrayOfbyte.length;
    return resultLob;
  }
  
  public static ResultLob newLobSetCharsRequest(long paramLong1, long paramLong2, char[] paramArrayOfchar) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 4;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.charBlock = paramArrayOfchar;
    resultLob.blockLength = paramArrayOfchar.length;
    return resultLob;
  }
  
  public static ResultLob newLobTruncateRequest(long paramLong1, long paramLong2) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 9;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    return resultLob;
  }
  
  public static ResultLob newLobGetBytesResponse(long paramLong1, long paramLong2, byte[] paramArrayOfbyte) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 21;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.byteBlock = paramArrayOfbyte;
    resultLob.blockLength = paramArrayOfbyte.length;
    return resultLob;
  }
  
  public static ResultLob newLobGetCharsResponse(long paramLong1, long paramLong2, char[] paramArrayOfchar) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 23;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.charBlock = paramArrayOfchar;
    resultLob.blockLength = paramArrayOfchar.length;
    return resultLob;
  }
  
  public static ResultLob newLobSetResponse(long paramLong1, long paramLong2) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 22;
    resultLob.lobID = paramLong1;
    resultLob.blockLength = paramLong2;
    return resultLob;
  }
  
  public static ResultLob newLobGetBytePatternPositionRequest(long paramLong1, byte[] paramArrayOfbyte, long paramLong2) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 5;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.byteBlock = paramArrayOfbyte;
    resultLob.blockLength = paramArrayOfbyte.length;
    return resultLob;
  }
  
  public static ResultLob newLobGetBytePatternPositionRequest(long paramLong1, long paramLong2, long paramLong3) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 5;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong3;
    return resultLob;
  }
  
  public static ResultLob newLobGetCharPatternPositionRequest(long paramLong1, char[] paramArrayOfchar, long paramLong2) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 6;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.charBlock = paramArrayOfchar;
    resultLob.blockLength = paramArrayOfchar.length;
    return resultLob;
  }
  
  public static ResultLob newLobGetCharPatternPositionRequest(long paramLong1, long paramLong2, long paramLong3) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 6;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong3;
    resultLob.blockLength = paramLong2;
    return resultLob;
  }
  
  public static ResultLob newLobCreateBlobRequest(long paramLong1, long paramLong2, InputStream paramInputStream, long paramLong3) {
    ResultLob resultLob = new ResultLob();
    resultLob.lobID = paramLong2;
    resultLob.subType = 7;
    resultLob.blockLength = paramLong3;
    resultLob.stream = paramInputStream;
    return resultLob;
  }
  
  public static ResultLob newLobCreateClobRequest(long paramLong1, long paramLong2, Reader paramReader, long paramLong3) {
    ResultLob resultLob = new ResultLob();
    resultLob.lobID = paramLong2;
    resultLob.subType = 8;
    resultLob.blockLength = paramLong3;
    resultLob.reader = paramReader;
    return resultLob;
  }
  
  public static ResultLob newLobGetTruncateLength(long paramLong) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 13;
    resultLob.lobID = paramLong;
    return resultLob;
  }
  
  public static ResultLob newLobCreateBlobResponse(long paramLong) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 27;
    resultLob.lobID = paramLong;
    return resultLob;
  }
  
  public static ResultLob newLobCreateClobResponse(long paramLong) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 28;
    resultLob.lobID = paramLong;
    return resultLob;
  }
  
  public static ResultLob newLobTruncateResponse(long paramLong1, long paramLong2) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 29;
    resultLob.lobID = paramLong1;
    resultLob.blockLength = paramLong2;
    return resultLob;
  }
  
  public static ResultLob newLobGetRequest(long paramLong1, long paramLong2, long paramLong3) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 11;
    resultLob.lobID = paramLong1;
    resultLob.blockOffset = paramLong2;
    resultLob.blockLength = paramLong3;
    return resultLob;
  }
  
  public static ResultLob newLobDuplicateRequest(long paramLong) {
    ResultLob resultLob = new ResultLob();
    resultLob.subType = 12;
    resultLob.lobID = paramLong;
    return resultLob;
  }
  
  public static ResultLob newLob(DataInput paramDataInput, boolean paramBoolean) throws IOException {
    byte b;
    ResultLob resultLob = new ResultLob();
    resultLob.databaseID = paramDataInput.readInt();
    resultLob.sessionID = paramDataInput.readLong();
    resultLob.lobID = paramDataInput.readLong();
    resultLob.subType = paramDataInput.readInt();
    switch (resultLob.subType) {
      case 7:
      case 8:
        resultLob.blockOffset = paramDataInput.readLong();
        resultLob.blockLength = paramDataInput.readLong();
        break;
      case 1:
      case 3:
      case 11:
      case 12:
        resultLob.blockOffset = paramDataInput.readLong();
        resultLob.blockLength = paramDataInput.readLong();
        break;
      case 2:
      case 5:
        resultLob.blockOffset = paramDataInput.readLong();
        resultLob.blockLength = paramDataInput.readLong();
        resultLob.byteBlock = new byte[(int)resultLob.blockLength];
        paramDataInput.readFully(resultLob.byteBlock);
        break;
      case 4:
      case 6:
        resultLob.blockOffset = paramDataInput.readLong();
        resultLob.blockLength = paramDataInput.readLong();
        resultLob.charBlock = new char[(int)resultLob.blockLength];
        for (b = 0; b < resultLob.charBlock.length; b++)
          resultLob.charBlock[b] = paramDataInput.readChar(); 
        break;
      case 9:
      case 10:
        resultLob.blockOffset = paramDataInput.readLong();
        break;
      case 21:
        resultLob.blockOffset = paramDataInput.readLong();
        resultLob.blockLength = paramDataInput.readLong();
        resultLob.byteBlock = new byte[(int)resultLob.blockLength];
        paramDataInput.readFully(resultLob.byteBlock);
        break;
      case 23:
        resultLob.blockOffset = paramDataInput.readLong();
        resultLob.blockLength = paramDataInput.readLong();
        resultLob.charBlock = new char[(int)resultLob.blockLength];
        for (b = 0; b < resultLob.charBlock.length; b++)
          resultLob.charBlock[b] = paramDataInput.readChar(); 
        break;
      case 22:
      case 27:
      case 28:
      case 29:
        resultLob.blockLength = paramDataInput.readLong();
        break;
      case 25:
      case 26:
        resultLob.blockOffset = paramDataInput.readLong();
        break;
      default:
        throw Error.runtimeError(201, "ResultLob");
    } 
    if (paramBoolean)
      paramDataInput.readByte(); 
    return resultLob;
  }
  
  public void write(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream, RowOutputInterface paramRowOutputInterface) throws IOException {
    writeBody(paramSessionInterface, paramDataOutputStream);
    paramDataOutputStream.writeByte(0);
    paramDataOutputStream.flush();
  }
  
  public void writeBody(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream) throws IOException {
    switch (this.subType) {
      case 7:
        if (this.blockLength >= 0L) {
          writeCreate(paramSessionInterface, paramDataOutputStream);
          return;
        } 
        writeCreateByteSegments(paramSessionInterface, paramDataOutputStream);
        return;
      case 8:
        if (this.blockLength >= 0L) {
          writeCreate(paramSessionInterface, paramDataOutputStream);
          return;
        } 
        writeCreateCharSegments(paramSessionInterface, paramDataOutputStream);
        return;
    } 
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    switch (this.subType) {
      case 2:
      case 5:
        paramDataOutputStream.writeLong(this.blockOffset);
        paramDataOutputStream.writeLong(this.blockLength);
        paramDataOutputStream.write(this.byteBlock);
        return;
      case 4:
      case 6:
        paramDataOutputStream.writeLong(this.blockOffset);
        paramDataOutputStream.writeLong(this.blockLength);
        paramDataOutputStream.writeChars(this.charBlock);
        return;
      case 1:
      case 3:
      case 11:
      case 12:
        paramDataOutputStream.writeLong(this.blockOffset);
        paramDataOutputStream.writeLong(this.blockLength);
        return;
      case 9:
      case 10:
        paramDataOutputStream.writeLong(this.blockOffset);
        return;
      case 21:
        paramDataOutputStream.writeLong(this.blockOffset);
        paramDataOutputStream.writeLong(this.blockLength);
        paramDataOutputStream.write(this.byteBlock);
        return;
      case 23:
        paramDataOutputStream.writeLong(this.blockOffset);
        paramDataOutputStream.writeLong(this.blockLength);
        paramDataOutputStream.writeChars(this.charBlock);
        return;
      case 22:
      case 27:
      case 28:
      case 29:
        paramDataOutputStream.writeLong(this.blockLength);
        return;
      case 25:
      case 26:
        paramDataOutputStream.writeLong(this.blockOffset);
        return;
    } 
    throw Error.runtimeError(201, "ResultLob");
  }
  
  private void writeCreate(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    paramDataOutputStream.writeLong(this.blockOffset);
    paramDataOutputStream.writeLong(this.blockLength);
    switch (this.subType) {
      case 7:
        paramDataOutputStream.write(this.stream, this.blockLength);
        break;
      case 8:
        paramDataOutputStream.write(this.reader, this.blockLength);
        break;
    } 
  }
  
  private void writeCreateByteSegments(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream) throws IOException {
    int i = paramSessionInterface.getStreamBlockSize();
    long l = this.blockOffset;
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(i);
    hsqlByteArrayOutputStream.reset();
    hsqlByteArrayOutputStream.write(this.stream, i);
    paramDataOutputStream.writeLong(l);
    paramDataOutputStream.writeLong(hsqlByteArrayOutputStream.size());
    paramDataOutputStream.write(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size());
    l += hsqlByteArrayOutputStream.size();
    if (hsqlByteArrayOutputStream.size() < i)
      return; 
    do {
      hsqlByteArrayOutputStream.reset();
      hsqlByteArrayOutputStream.write(this.stream, i);
      if (hsqlByteArrayOutputStream.size() == 0)
        break; 
      paramDataOutputStream.writeByte(this.mode);
      paramDataOutputStream.writeInt(this.databaseID);
      paramDataOutputStream.writeLong(this.sessionID);
      paramDataOutputStream.writeLong(this.lobID);
      paramDataOutputStream.writeInt(2);
      paramDataOutputStream.writeLong(l);
      paramDataOutputStream.writeLong(hsqlByteArrayOutputStream.size());
      paramDataOutputStream.write(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size());
      l += hsqlByteArrayOutputStream.size();
    } while (hsqlByteArrayOutputStream.size() >= i);
  }
  
  private void writeCreateCharSegments(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream) throws IOException {
    int i = paramSessionInterface.getStreamBlockSize();
    long l = this.blockOffset;
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(i);
    hsqlByteArrayOutputStream.reset();
    hsqlByteArrayOutputStream.write(this.reader, i / 2);
    paramDataOutputStream.writeLong(l);
    paramDataOutputStream.writeLong((hsqlByteArrayOutputStream.size() / 2));
    paramDataOutputStream.write(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size());
    l += (hsqlByteArrayOutputStream.size() / 2);
    if (hsqlByteArrayOutputStream.size() < i)
      return; 
    do {
      hsqlByteArrayOutputStream.reset();
      hsqlByteArrayOutputStream.write(this.reader, i / 2);
      if (hsqlByteArrayOutputStream.size() == 0)
        break; 
      paramDataOutputStream.writeByte(this.mode);
      paramDataOutputStream.writeInt(this.databaseID);
      paramDataOutputStream.writeLong(this.sessionID);
      paramDataOutputStream.writeLong(this.lobID);
      paramDataOutputStream.writeInt(4);
      paramDataOutputStream.writeLong(l);
      paramDataOutputStream.writeLong((hsqlByteArrayOutputStream.size() / 2));
      paramDataOutputStream.write(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size());
      l += (hsqlByteArrayOutputStream.size() / 2);
    } while (hsqlByteArrayOutputStream.size() >= i);
  }
  
  public long getLobID() {
    return this.lobID;
  }
  
  public int getSubType() {
    return this.subType;
  }
  
  public long getOffset() {
    return this.blockOffset;
  }
  
  public long getBlockLength() {
    return this.blockLength;
  }
  
  public byte[] getByteArray() {
    return this.byteBlock;
  }
  
  public char[] getCharArray() {
    return this.charBlock;
  }
  
  public InputStream getInputStream() {
    return this.stream;
  }
  
  public Reader getReader() {
    return this.reader;
  }
  
  public static interface LobResultTypes {
    public static final int REQUEST_GET_BYTES = 1;
    
    public static final int REQUEST_SET_BYTES = 2;
    
    public static final int REQUEST_GET_CHARS = 3;
    
    public static final int REQUEST_SET_CHARS = 4;
    
    public static final int REQUEST_GET_BYTE_PATTERN_POSITION = 5;
    
    public static final int REQUEST_GET_CHAR_PATTERN_POSITION = 6;
    
    public static final int REQUEST_CREATE_BYTES = 7;
    
    public static final int REQUEST_CREATE_CHARS = 8;
    
    public static final int REQUEST_TRUNCATE = 9;
    
    public static final int REQUEST_GET_LENGTH = 10;
    
    public static final int REQUEST_GET_LOB = 11;
    
    public static final int REQUEST_DUPLICATE_LOB = 12;
    
    public static final int REQUEST_GET_TRUNCATE_LENGTH = 13;
    
    public static final int RESPONSE_GET_BYTES = 21;
    
    public static final int RESPONSE_SET = 22;
    
    public static final int RESPONSE_GET_CHARS = 23;
    
    public static final int RESPONSE_GET_BYTE_PATTERN_POSITION = 25;
    
    public static final int RESPONSE_GET_CHAR_PATTERN_POSITION = 26;
    
    public static final int RESPONSE_CREATE_BYTES = 27;
    
    public static final int RESPONSE_CREATE_CHARS = 28;
    
    public static final int RESPONSE_TRUNCATE = 29;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\result\ResultLob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */