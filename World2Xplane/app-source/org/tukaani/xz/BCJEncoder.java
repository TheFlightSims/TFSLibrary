package org.tukaani.xz;

class BCJEncoder extends BCJCoder implements FilterEncoder {
  private final BCJOptions options;
  
  private final long filterID;
  
  private final byte[] props;
  
  static final boolean $assertionsDisabled;
  
  BCJEncoder(BCJOptions paramBCJOptions, long paramLong) {
    assert isBCJFilterID(paramLong);
    int i = paramBCJOptions.getStartOffset();
    if (i == 0) {
      this.props = new byte[0];
    } else {
      this.props = new byte[4];
      for (byte b = 0; b < 4; b++)
        this.props[b] = (byte)(i >>> b * 8); 
    } 
    this.filterID = paramLong;
    this.options = (BCJOptions)paramBCJOptions.clone();
  }
  
  public long getFilterID() {
    return this.filterID;
  }
  
  public byte[] getFilterProps() {
    return this.props;
  }
  
  public boolean supportsFlushing() {
    return false;
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return this.options.getOutputStream(paramFinishableOutputStream);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\BCJEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */