package org.tukaani.xz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.EncoderUtil;

class BlockOutputStream extends FinishableOutputStream {
  private final OutputStream out;
  
  private final CountingOutputStream outCounted;
  
  private FinishableOutputStream filterChain;
  
  private final Check check;
  
  private final int headerSize;
  
  private final long compressedSizeLimit;
  
  private long uncompressedSize = 0L;
  
  public BlockOutputStream(OutputStream paramOutputStream, FilterEncoder[] paramArrayOfFilterEncoder, Check paramCheck) throws IOException {
    this.out = paramOutputStream;
    this.check = paramCheck;
    this.outCounted = new CountingOutputStream(paramOutputStream);
    this.filterChain = this.outCounted;
    for (int i = paramArrayOfFilterEncoder.length - 1; i >= 0; i--)
      this.filterChain = paramArrayOfFilterEncoder[i].getOutputStream(this.filterChain); 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write(0);
    byteArrayOutputStream.write(paramArrayOfFilterEncoder.length - 1);
    for (byte b = 0; b < paramArrayOfFilterEncoder.length; b++) {
      EncoderUtil.encodeVLI(byteArrayOutputStream, paramArrayOfFilterEncoder[b].getFilterID());
      byte[] arrayOfByte1 = paramArrayOfFilterEncoder[b].getFilterProps();
      EncoderUtil.encodeVLI(byteArrayOutputStream, arrayOfByte1.length);
      byteArrayOutputStream.write(arrayOfByte1);
    } 
    while ((byteArrayOutputStream.size() & 0x3) != 0)
      byteArrayOutputStream.write(0); 
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    this.headerSize = arrayOfByte.length + 4;
    if (this.headerSize > 1024)
      throw new UnsupportedOptionsException(); 
    arrayOfByte[0] = (byte)(arrayOfByte.length / 4);
    paramOutputStream.write(arrayOfByte);
    EncoderUtil.writeCRC32(paramOutputStream, arrayOfByte);
    this.compressedSizeLimit = 9223372036854775804L - this.headerSize - paramCheck.getSize();
  }
  
  public void write(int paramInt) throws IOException {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)paramInt;
    write(arrayOfByte, 0, 1);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.filterChain.write(paramArrayOfbyte, paramInt1, paramInt2);
    this.check.update(paramArrayOfbyte, paramInt1, paramInt2);
    this.uncompressedSize += paramInt2;
    validate();
  }
  
  public void flush() throws IOException {
    this.filterChain.flush();
    validate();
  }
  
  public void finish() throws IOException {
    this.filterChain.finish();
    validate();
    for (long l = this.outCounted.getSize(); (l & 0x3L) != 0L; l++)
      this.out.write(0); 
    this.out.write(this.check.finish());
  }
  
  private void validate() throws IOException {
    long l = this.outCounted.getSize();
    if (l < 0L || l > this.compressedSizeLimit || this.uncompressedSize < 0L)
      throw new XZIOException("XZ Stream has grown too big"); 
  }
  
  public long getUnpaddedSize() {
    return this.headerSize + this.outCounted.getSize() + this.check.getSize();
  }
  
  public long getUncompressedSize() {
    return this.uncompressedSize;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\BlockOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */