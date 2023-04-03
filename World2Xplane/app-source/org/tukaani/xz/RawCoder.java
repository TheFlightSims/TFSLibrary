package org.tukaani.xz;

class RawCoder {
  static void validate(FilterCoder[] paramArrayOfFilterCoder) throws UnsupportedOptionsException {
    byte b1;
    for (b1 = 0; b1 < paramArrayOfFilterCoder.length - 1; b1++) {
      if (!paramArrayOfFilterCoder[b1].nonLastOK())
        throw new UnsupportedOptionsException("Unsupported XZ filter chain"); 
    } 
    if (!paramArrayOfFilterCoder[paramArrayOfFilterCoder.length - 1].lastOK())
      throw new UnsupportedOptionsException("Unsupported XZ filter chain"); 
    b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfFilterCoder.length; b2++) {
      if (paramArrayOfFilterCoder[b2].changesSize())
        b1++; 
    } 
    if (b1 > 3)
      throw new UnsupportedOptionsException("Unsupported XZ filter chain"); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\RawCoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */