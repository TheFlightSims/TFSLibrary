package org.tukaani.xz;

import org.tukaani.xz.lzma.LZMAEncoder;

class LZMA2Encoder extends LZMA2Coder implements FilterEncoder {
  private final LZMA2Options options;
  
  private final byte[] props = new byte[1];
  
  LZMA2Encoder(LZMA2Options paramLZMA2Options) {
    if (paramLZMA2Options.getPresetDict() != null)
      throw new IllegalArgumentException("XZ doesn't support a preset dictionary for now"); 
    if (paramLZMA2Options.getMode() == 0) {
      this.props[0] = 0;
    } else {
      int i = Math.max(paramLZMA2Options.getDictSize(), 4096);
      this.props[0] = (byte)(LZMAEncoder.getDistSlot(i - 1) - 23);
    } 
    this.options = (LZMA2Options)paramLZMA2Options.clone();
  }
  
  public long getFilterID() {
    return 33L;
  }
  
  public byte[] getFilterProps() {
    return this.props;
  }
  
  public boolean supportsFlushing() {
    return true;
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return this.options.getOutputStream(paramFinishableOutputStream);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\LZMA2Encoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */