package org.tukaani.xz.index;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.XZIOException;
import org.tukaani.xz.check.CRC32;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.check.SHA256;
import org.tukaani.xz.common.DecoderUtil;

public class IndexHash extends IndexBase {
  private Check hash;
  
  public IndexHash() {
    super((XZIOException)new CorruptedInputException());
    try {
      this.hash = (Check)new SHA256();
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      this.hash = (Check)new CRC32();
    } 
  }
  
  public void add(long paramLong1, long paramLong2) throws XZIOException {
    super.add(paramLong1, paramLong2);
    ByteBuffer byteBuffer = ByteBuffer.allocate(16);
    byteBuffer.putLong(paramLong1);
    byteBuffer.putLong(paramLong2);
    this.hash.update(byteBuffer.array());
  }
  
  public void validate(InputStream paramInputStream) throws IOException {
    CRC32 cRC32 = new CRC32();
    cRC32.update(0);
    CheckedInputStream checkedInputStream = new CheckedInputStream(paramInputStream, cRC32);
    long l1 = DecoderUtil.decodeVLI(checkedInputStream);
    if (l1 != this.recordCount)
      throw new CorruptedInputException("XZ Index is corrupt"); 
    IndexHash indexHash = new IndexHash();
    long l2;
    for (l2 = 0L; l2 < this.recordCount; l2++) {
      long l4 = DecoderUtil.decodeVLI(checkedInputStream);
      long l5 = DecoderUtil.decodeVLI(checkedInputStream);
      try {
        indexHash.add(l4, l5);
      } catch (XZIOException xZIOException) {
        throw new CorruptedInputException("XZ Index is corrupt");
      } 
      if (indexHash.blocksSum > this.blocksSum || indexHash.uncompressedSum > this.uncompressedSum || indexHash.indexListSize > this.indexListSize)
        throw new CorruptedInputException("XZ Index is corrupt"); 
    } 
    if (indexHash.blocksSum != this.blocksSum || indexHash.uncompressedSum != this.uncompressedSum || indexHash.indexListSize != this.indexListSize || !Arrays.equals(indexHash.hash.finish(), this.hash.finish()))
      throw new CorruptedInputException("XZ Index is corrupt"); 
    DataInputStream dataInputStream = new DataInputStream(checkedInputStream);
    for (int i = getIndexPaddingSize(); i > 0; i--) {
      if (dataInputStream.readUnsignedByte() != 0)
        throw new CorruptedInputException("XZ Index is corrupt"); 
    } 
    long l3 = cRC32.getValue();
    for (byte b = 0; b < 4; b++) {
      if ((l3 >>> b * 8 & 0xFFL) != dataInputStream.readUnsignedByte())
        throw new CorruptedInputException("XZ Index is corrupt"); 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\index\IndexHash.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */