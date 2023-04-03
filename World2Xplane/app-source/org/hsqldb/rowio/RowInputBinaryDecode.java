package org.hsqldb.rowio;

import java.io.IOException;
import org.hsqldb.persist.Crypto;
import org.hsqldb.types.Type;

public class RowInputBinaryDecode extends RowInputBinary {
  final Crypto crypto;
  
  public RowInputBinaryDecode(Crypto paramCrypto, byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
    this.crypto = paramCrypto;
  }
  
  public Object[] readData(Type[] paramArrayOfType) throws IOException {
    if (this.crypto != null) {
      int i = this.pos;
      int j = readInt();
      this.crypto.decode(this.buffer, this.pos, j, this.buffer, i);
      this.pos = i;
    } 
    return super.readData(paramArrayOfType);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputBinaryDecode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */