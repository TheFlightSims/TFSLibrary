package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.util.Base64;
import org.apache.xerces.impl.dv.util.ByteListImpl;

public class Base64BinaryDV extends TypeValidator {
  public short getAllowedFacets() {
    return 2079;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    byte[] arrayOfByte = Base64.decode(paramString);
    if (arrayOfByte == null)
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "base64Binary" }); 
    return new XBase64(arrayOfByte);
  }
  
  public int getDataLength(Object paramObject) {
    return ((XBase64)paramObject).getLength();
  }
  
  private static final class XBase64 extends ByteListImpl {
    public XBase64(byte[] param1ArrayOfbyte) {
      super(param1ArrayOfbyte);
    }
    
    public synchronized String toString() {
      if (this.canonical == null)
        this.canonical = Base64.encode(this.data); 
      return this.canonical;
    }
    
    public boolean equals(Object param1Object) {
      if (!(param1Object instanceof XBase64))
        return false; 
      byte[] arrayOfByte = ((XBase64)param1Object).data;
      int i = this.data.length;
      if (i != arrayOfByte.length)
        return false; 
      for (byte b = 0; b < i; b++) {
        if (this.data[b] != arrayOfByte[b])
          return false; 
      } 
      return true;
    }
    
    public int hashCode() {
      int i = 0;
      for (byte b = 0; b < this.data.length; b++)
        i = i * 37 + (this.data[b] & 0xFF); 
      return i;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\Base64BinaryDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */