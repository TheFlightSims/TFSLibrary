package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xs.datatypes.ObjectList;

public class ListDV extends TypeValidator {
  public short getAllowedFacets() {
    return 2079;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    return paramString;
  }
  
  public int getDataLength(Object paramObject) {
    return ((ListData)paramObject).getLength();
  }
  
  static final class ListData implements ObjectList {
    final Object[] data;
    
    private String canonical;
    
    public ListData(Object[] param1ArrayOfObject) {
      this.data = param1ArrayOfObject;
    }
    
    public synchronized String toString() {
      if (this.canonical == null) {
        int i = this.data.length;
        StringBuffer stringBuffer = new StringBuffer();
        if (i > 0)
          stringBuffer.append(this.data[0].toString()); 
        for (byte b = 1; b < i; b++) {
          stringBuffer.append(' ');
          stringBuffer.append(this.data[b].toString());
        } 
        this.canonical = stringBuffer.toString();
      } 
      return this.canonical;
    }
    
    public int getLength() {
      return this.data.length;
    }
    
    public boolean equals(Object param1Object) {
      if (!(param1Object instanceof ListData))
        return false; 
      Object[] arrayOfObject = ((ListData)param1Object).data;
      int i = this.data.length;
      if (i != arrayOfObject.length)
        return false; 
      for (byte b = 0; b < i; b++) {
        if (!this.data[b].equals(arrayOfObject[b]))
          return false; 
      } 
      return true;
    }
    
    public int hashCode() {
      int i = 0;
      for (byte b = 0; b < this.data.length; b++)
        i ^= this.data[b].hashCode(); 
      return i;
    }
    
    public boolean contains(Object param1Object) {
      for (byte b = 0; b < this.data.length; b++) {
        if (param1Object == this.data[b])
          return true; 
      } 
      return false;
    }
    
    public Object item(int param1Int) {
      return (param1Int < 0 || param1Int >= this.data.length) ? null : this.data[param1Int];
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\ListDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */