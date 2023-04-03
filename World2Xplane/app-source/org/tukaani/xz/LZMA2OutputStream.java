package org.tukaani.xz;

import java.io.DataOutputStream;
import java.io.IOException;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoder;

class LZMA2OutputStream extends FinishableOutputStream {
  static final int COMPRESSED_SIZE_MAX = 65536;
  
  private FinishableOutputStream out;
  
  private final DataOutputStream outData;
  
  private final LZEncoder lz;
  
  private final RangeEncoder rc;
  
  private final LZMAEncoder lzma;
  
  private int props;
  
  private boolean dictResetNeeded = true;
  
  private boolean stateResetNeeded = true;
  
  private boolean propsNeeded = true;
  
  private int pendingSize = 0;
  
  private boolean finished = false;
  
  private IOException exception = null;
  
  static final boolean $assertionsDisabled;
  
  private static int getExtraSizeBefore(int paramInt) {
    return (65536 > paramInt) ? (65536 - paramInt) : 0;
  }
  
  static int getMemoryUsage(LZMA2Options paramLZMA2Options) {
    int i = paramLZMA2Options.getDictSize();
    int j = getExtraSizeBefore(i);
    return 70 + LZMAEncoder.getMemoryUsage(paramLZMA2Options.getMode(), i, j, paramLZMA2Options.getMatchFinder());
  }
  
  LZMA2OutputStream(FinishableOutputStream paramFinishableOutputStream, LZMA2Options paramLZMA2Options) {
    if (paramFinishableOutputStream == null)
      throw new NullPointerException(); 
    this.out = paramFinishableOutputStream;
    this.outData = new DataOutputStream(paramFinishableOutputStream);
    this.rc = new RangeEncoder(65536);
    int i = paramLZMA2Options.getDictSize();
    int j = getExtraSizeBefore(i);
    this.lzma = LZMAEncoder.getInstance(this.rc, paramLZMA2Options.getLc(), paramLZMA2Options.getLp(), paramLZMA2Options.getPb(), paramLZMA2Options.getMode(), i, j, paramLZMA2Options.getNiceLen(), paramLZMA2Options.getMatchFinder(), paramLZMA2Options.getDepthLimit());
    this.lz = this.lzma.getLZEncoder();
    this.lz.setPresetDict(i, paramLZMA2Options.getPresetDict());
    this.props = (paramLZMA2Options.getPb() * 5 + paramLZMA2Options.getLp()) * 9 + paramLZMA2Options.getLc();
  }
  
  public void write(int paramInt) throws IOException {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)paramInt;
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
      while (paramInt2 > 0) {
        int i = this.lz.fillWindow(paramArrayOfbyte, paramInt1, paramInt2);
        paramInt1 += i;
        paramInt2 -= i;
        this.pendingSize += i;
        if (this.lzma.encodeForLZMA2())
          writeChunk(); 
      } 
    } catch (IOException iOException) {
      this.exception = iOException;
      throw iOException;
    } 
  }
  
  private void writeChunk() throws IOException {
    int i = this.rc.finish();
    int j = this.lzma.getUncompressedSize();
    if (i + 2 < j) {
      writeLZMA(j, i);
    } else {
      this.lzma.reset();
      j = this.lzma.getUncompressedSize();
      writeUncompressed(j);
    } 
    this.pendingSize -= j;
    this.lzma.resetUncompressedSize();
    this.rc.reset();
  }
  
  private void writeLZMA(int paramInt1, int paramInt2) throws IOException {
    int i;
    if (this.propsNeeded) {
      if (this.dictResetNeeded) {
        i = 224;
      } else {
        i = 192;
      } 
    } else if (this.stateResetNeeded) {
      i = 160;
    } else {
      i = 128;
    } 
    i |= paramInt1 - 1 >>> 16;
    this.outData.writeByte(i);
    this.outData.writeShort(paramInt1 - 1);
    this.outData.writeShort(paramInt2 - 1);
    if (this.propsNeeded)
      this.outData.writeByte(this.props); 
    this.rc.write(this.out);
    this.propsNeeded = false;
    this.stateResetNeeded = false;
    this.dictResetNeeded = false;
  }
  
  private void writeUncompressed(int paramInt) throws IOException {
    while (paramInt > 0) {
      int i = Math.min(paramInt, 65536);
      this.outData.writeByte(this.dictResetNeeded ? 1 : 2);
      this.outData.writeShort(i - 1);
      this.lz.copyUncompressed(this.out, paramInt, i);
      paramInt -= i;
      this.dictResetNeeded = false;
    } 
    this.stateResetNeeded = true;
  }
  
  private void writeEndMarker() throws IOException {
    assert !this.finished;
    if (this.exception != null)
      throw this.exception; 
    this.lz.setFinishing();
    try {
      while (this.pendingSize > 0) {
        this.lzma.encodeForLZMA2();
        writeChunk();
      } 
      this.out.write(0);
    } catch (IOException iOException) {
      this.exception = iOException;
      throw iOException;
    } 
    this.finished = true;
  }
  
  public void flush() throws IOException {
    if (this.exception != null)
      throw this.exception; 
    if (this.finished)
      throw new XZIOException("Stream finished or closed"); 
    try {
      this.lz.setFlushing();
      while (this.pendingSize > 0) {
        this.lzma.encodeForLZMA2();
        writeChunk();
      } 
      this.out.flush();
    } catch (IOException iOException) {
      this.exception = iOException;
      throw iOException;
    } 
  }
  
  public void finish() throws IOException {
    if (!this.finished) {
      writeEndMarker();
      try {
        this.out.finish();
      } catch (IOException iOException) {
        this.exception = iOException;
        throw iOException;
      } 
      this.finished = true;
    } 
  }
  
  public void close() throws IOException {
    if (this.out != null) {
      if (!this.finished)
        try {
          writeEndMarker();
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
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\LZMA2OutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */