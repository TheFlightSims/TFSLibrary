package org.apache.xerces.impl.xs.util;

import java.util.Vector;
import org.apache.xerces.xs.StringList;

public class StringListImpl implements StringList {
  public static final StringList EMPTY_LIST = new StringList() {
      public int getLength() {
        return 0;
      }
      
      public boolean contains(String param1String) {
        return false;
      }
      
      public String item(int param1Int) {
        return null;
      }
    };
  
  private String[] fArray = null;
  
  private int fLength = 0;
  
  private Vector fVector;
  
  public StringListImpl(Vector paramVector) {
    this.fVector = paramVector;
    this.fLength = (paramVector == null) ? 0 : paramVector.size();
  }
  
  public StringListImpl(String[] paramArrayOfString, int paramInt) {
    this.fArray = paramArrayOfString;
    this.fLength = paramInt;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public boolean contains(String paramString) {
    if (this.fVector != null)
      return this.fVector.contains(paramString); 
    if (paramString == null) {
      for (byte b = 0; b < this.fLength; b++) {
        if (this.fArray[b] == null)
          return true; 
      } 
    } else {
      for (byte b = 0; b < this.fLength; b++) {
        if (paramString.equals(this.fArray[b]))
          return true; 
      } 
    } 
    return false;
  }
  
  public String item(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : ((this.fVector != null) ? this.fVector.elementAt(paramInt) : this.fArray[paramInt]);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\StringListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */