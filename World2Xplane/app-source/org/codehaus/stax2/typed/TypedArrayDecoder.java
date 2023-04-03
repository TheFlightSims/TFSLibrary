package org.codehaus.stax2.typed;

public abstract class TypedArrayDecoder {
  public abstract boolean decodeValue(String paramString) throws IllegalArgumentException;
  
  public abstract boolean decodeValue(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IllegalArgumentException;
  
  public abstract int getCount();
  
  public abstract boolean hasRoom();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\typed\TypedArrayDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */