package org.apache.xerces.impl.xs.util;

import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSTypeDefinition;

public class XSNamedMap4Types extends XSNamedMapImpl {
  short fType;
  
  public XSNamedMap4Types(String paramString, SymbolHash paramSymbolHash, short paramShort) {
    super(paramString, paramSymbolHash);
    this.fType = paramShort;
  }
  
  public XSNamedMap4Types(String[] paramArrayOfString, SymbolHash[] paramArrayOfSymbolHash, int paramInt, short paramShort) {
    super(paramArrayOfString, paramArrayOfSymbolHash, paramInt);
    this.fType = paramShort;
  }
  
  public synchronized int getLength() {
    if (this.fLength == -1) {
      int i = 0;
      for (byte b1 = 0; b1 < this.fNSNum; b1++)
        i += this.fMaps[b1].getLength(); 
      int j = 0;
      XSObject[] arrayOfXSObject = new XSObject[i];
      for (byte b2 = 0; b2 < this.fNSNum; b2++)
        j += this.fMaps[b2].getValues((Object[])arrayOfXSObject, j); 
      this.fLength = 0;
      this.fArray = new XSObject[i];
      for (byte b3 = 0; b3 < i; b3++) {
        XSTypeDefinition xSTypeDefinition = (XSTypeDefinition)arrayOfXSObject[b3];
        if (xSTypeDefinition.getTypeCategory() == this.fType)
          this.fArray[this.fLength++] = (XSObject)xSTypeDefinition; 
      } 
    } 
    return this.fLength;
  }
  
  public XSObject itemByName(String paramString1, String paramString2) {
    for (byte b = 0; b < this.fNSNum; b++) {
      if (isEqual(paramString1, this.fNamespaces[b])) {
        XSTypeDefinition xSTypeDefinition = (XSTypeDefinition)this.fMaps[b].get(paramString2);
        return (XSObject)((xSTypeDefinition.getTypeCategory() == this.fType) ? xSTypeDefinition : null);
      } 
    } 
    return null;
  }
  
  public synchronized XSObject item(int paramInt) {
    if (this.fArray == null)
      getLength(); 
    return (paramInt < 0 || paramInt >= this.fLength) ? null : this.fArray[paramInt];
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\XSNamedMap4Types.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */