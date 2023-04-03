package org.tukaani.xz.index;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.tukaani.xz.XZIOException;
import org.tukaani.xz.common.EncoderUtil;

public class IndexEncoder extends IndexBase {
  private final ArrayList records = new ArrayList();
  
  public IndexEncoder() {
    super(new XZIOException("XZ Stream or its Index has grown too big"));
  }
  
  public void add(long paramLong1, long paramLong2) throws XZIOException {
    super.add(paramLong1, paramLong2);
    this.records.add(new IndexRecord(paramLong1, paramLong2));
  }
  
  public void encode(OutputStream paramOutputStream) throws IOException {
    CRC32 cRC32 = new CRC32();
    CheckedOutputStream checkedOutputStream = new CheckedOutputStream(paramOutputStream, cRC32);
    checkedOutputStream.write(0);
    EncoderUtil.encodeVLI(checkedOutputStream, this.recordCount);
    for (IndexRecord indexRecord : this.records) {
      EncoderUtil.encodeVLI(checkedOutputStream, indexRecord.unpadded);
      EncoderUtil.encodeVLI(checkedOutputStream, indexRecord.uncompressed);
    } 
    for (int i = getIndexPaddingSize(); i > 0; i--)
      checkedOutputStream.write(0); 
    long l = cRC32.getValue();
    for (byte b = 0; b < 4; b++)
      paramOutputStream.write((byte)(int)(l >>> b * 8)); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\index\IndexEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */