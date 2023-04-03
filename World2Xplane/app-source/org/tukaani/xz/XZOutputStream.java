package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.EncoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexEncoder;

public class XZOutputStream extends FinishableOutputStream {
  private OutputStream out;
  
  private final StreamFlags streamFlags = new StreamFlags();
  
  private final Check check;
  
  private final IndexEncoder index = new IndexEncoder();
  
  private BlockOutputStream blockEncoder = null;
  
  private FilterEncoder[] filters;
  
  private boolean filtersSupportFlushing;
  
  private IOException exception = null;
  
  private boolean finished = false;
  
  public XZOutputStream(OutputStream paramOutputStream, FilterOptions paramFilterOptions) throws IOException {
    this(paramOutputStream, paramFilterOptions, 4);
  }
  
  public XZOutputStream(OutputStream paramOutputStream, FilterOptions paramFilterOptions, int paramInt) throws IOException {
    this(paramOutputStream, new FilterOptions[] { paramFilterOptions }, paramInt);
  }
  
  public XZOutputStream(OutputStream paramOutputStream, FilterOptions[] paramArrayOfFilterOptions) throws IOException {
    this(paramOutputStream, paramArrayOfFilterOptions, 4);
  }
  
  public XZOutputStream(OutputStream paramOutputStream, FilterOptions[] paramArrayOfFilterOptions, int paramInt) throws IOException {
    this.out = paramOutputStream;
    updateFilters(paramArrayOfFilterOptions);
    this.streamFlags.checkType = paramInt;
    this.check = Check.getInstance(paramInt);
    encodeStreamHeader();
  }
  
  public void updateFilters(FilterOptions paramFilterOptions) throws XZIOException {
    FilterOptions[] arrayOfFilterOptions = new FilterOptions[1];
    arrayOfFilterOptions[0] = paramFilterOptions;
    updateFilters(arrayOfFilterOptions);
  }
  
  public void updateFilters(FilterOptions[] paramArrayOfFilterOptions) throws XZIOException {
    if (this.blockEncoder != null)
      throw new UnsupportedOptionsException("Changing filter options in the middle of a XZ Block not implemented"); 
    if (paramArrayOfFilterOptions.length < 1 || paramArrayOfFilterOptions.length > 4)
      throw new UnsupportedOptionsException("XZ filter chain must be 1-4 filters"); 
    this.filtersSupportFlushing = true;
    FilterEncoder[] arrayOfFilterEncoder = new FilterEncoder[paramArrayOfFilterOptions.length];
    for (byte b = 0; b < paramArrayOfFilterOptions.length; b++) {
      arrayOfFilterEncoder[b] = paramArrayOfFilterOptions[b].getFilterEncoder();
      this.filtersSupportFlushing &= arrayOfFilterEncoder[b].supportsFlushing();
    } 
    RawCoder.validate((FilterCoder[])arrayOfFilterEncoder);
    this.filters = arrayOfFilterEncoder;
  }
  
  public void write(int paramInt) throws IOException {
    byte[] arrayOfByte = { (byte)paramInt };
    write(arrayOfByte, 0, 1);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 + paramInt2 < 0 || paramInt1 + paramInt2 > paramArrayOfbyte.length)
      throw new IndexOutOfBoundsException(); 
    if (this.exception != null)
      throw this.exception; 
    if (this.finished)
      throw new XZIOException("Stream finished or closed"); 
    try {
      if (this.blockEncoder == null)
        this.blockEncoder = new BlockOutputStream(this.out, this.filters, this.check); 
      this.blockEncoder.write(paramArrayOfbyte, paramInt1, paramInt2);
    } catch (IOException iOException) {
      this.exception = iOException;
      throw iOException;
    } 
  }
  
  public void endBlock() throws IOException {
    if (this.exception != null)
      throw this.exception; 
    if (this.finished)
      throw new XZIOException("Stream finished or closed"); 
    if (this.blockEncoder != null)
      try {
        this.blockEncoder.finish();
        this.index.add(this.blockEncoder.getUnpaddedSize(), this.blockEncoder.getUncompressedSize());
        this.blockEncoder = null;
      } catch (IOException iOException) {
        this.exception = iOException;
        throw iOException;
      }  
  }
  
  public void flush() throws IOException {
    if (this.exception != null)
      throw this.exception; 
    if (this.finished)
      throw new XZIOException("Stream finished or closed"); 
    try {
      if (this.blockEncoder != null) {
        if (this.filtersSupportFlushing) {
          this.blockEncoder.flush();
        } else {
          endBlock();
          this.out.flush();
        } 
      } else {
        this.out.flush();
      } 
    } catch (IOException iOException) {
      this.exception = iOException;
      throw iOException;
    } 
  }
  
  public void finish() throws IOException {
    if (!this.finished) {
      endBlock();
      try {
        this.index.encode(this.out);
        encodeStreamFooter();
      } catch (IOException iOException) {
        this.exception = iOException;
        throw iOException;
      } 
      this.finished = true;
    } 
  }
  
  public void close() throws IOException {
    if (this.out != null) {
      try {
        finish();
      } catch (IOException iOException) {}
      try {
        this.out.close();
      } catch (IOException iOException) {
        if (this.exception == null)
          this.exception = iOException; 
      } 
      this.out = null;
    } 
    if (this.exception != null)
      throw this.exception; 
  }
  
  private void encodeStreamFlags(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = 0;
    paramArrayOfbyte[paramInt + 1] = (byte)this.streamFlags.checkType;
  }
  
  private void encodeStreamHeader() throws IOException {
    this.out.write(XZ.HEADER_MAGIC);
    byte[] arrayOfByte = new byte[2];
    encodeStreamFlags(arrayOfByte, 0);
    this.out.write(arrayOfByte);
    EncoderUtil.writeCRC32(this.out, arrayOfByte);
  }
  
  private void encodeStreamFooter() throws IOException {
    byte[] arrayOfByte = new byte[6];
    long l = this.index.getIndexSize() / 4L - 1L;
    for (byte b = 0; b < 4; b++)
      arrayOfByte[b] = (byte)(int)(l >>> b * 8); 
    encodeStreamFlags(arrayOfByte, 4);
    EncoderUtil.writeCRC32(this.out, arrayOfByte);
    this.out.write(arrayOfByte);
    this.out.write(XZ.FOOTER_MAGIC);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\XZOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */