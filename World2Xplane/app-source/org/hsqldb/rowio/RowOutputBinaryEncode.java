package org.hsqldb.rowio;

import org.hsqldb.Row;
import org.hsqldb.persist.Crypto;
import org.hsqldb.types.Type;

public class RowOutputBinaryEncode extends RowOutputBinary {
  final Crypto crypto;
  
  public RowOutputBinaryEncode(Crypto paramCrypto, int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2);
    this.crypto = paramCrypto;
  }
  
  public void writeData(Row paramRow, Type[] paramArrayOfType) {
    if (this.crypto == null) {
      super.writeData(paramRow, paramArrayOfType);
    } else {
      int i = this.count;
      ensureRoom(paramRow.getStorageSize());
      writeInt(0);
      super.writeData(paramRow, paramArrayOfType);
      int j = this.count - i - 4;
      int k = this.crypto.encode(this.buffer, i + 4, j, this.buffer, i + 4);
      writeIntData(k, i);
      this.count = i + 4 + k;
    } 
  }
  
  public int getSize(Row paramRow) {
    int i = super.getSize(paramRow);
    if (this.crypto != null)
      i = this.crypto.getEncodedSize(i - 4) + 8; 
    return i;
  }
  
  public RowOutputInterface duplicate() {
    return new RowOutputBinaryEncode(this.crypto, 128, this.scale);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowOutputBinaryEncode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */