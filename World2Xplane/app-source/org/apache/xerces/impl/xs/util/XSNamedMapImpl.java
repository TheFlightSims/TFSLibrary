package org.apache.xerces.impl.xs.util;

import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSObject;

public class XSNamedMapImpl implements XSNamedMap {
  public static final XSNamedMap EMPTY_MAP = new XSNamedMap() {
      public int getLength() {
        return 0;
      }
      
      public XSObject itemByName(String param1String1, String param1String2) {
        return null;
      }
      
      public XSObject item(int param1Int) {
        return null;
      }
    };
  
  String[] fNamespaces;
  
  int fNSNum;
  
  SymbolHash[] fMaps;
  
  XSObject[] fArray = null;
  
  int fLength = -1;
  
  QName fName = new QName();
  
  public XSNamedMapImpl(String paramString, SymbolHash paramSymbolHash) {
    this.fNamespaces = new String[] { paramString };
    this.fMaps = new SymbolHash[] { paramSymbolHash };
    this.fNSNum = 1;
  }
  
  public XSNamedMapImpl(String[] paramArrayOfString, SymbolHash[] paramArrayOfSymbolHash, int paramInt) {
    this.fNamespaces = paramArrayOfString;
    this.fMaps = paramArrayOfSymbolHash;
    this.fNSNum = paramInt;
  }
  
  public XSNamedMapImpl(XSObject[] paramArrayOfXSObject, int paramInt) {
    if (paramInt == 0) {
      this.fNSNum = 0;
      this.fLength = 0;
      return;
    } 
    this.fNamespaces = new String[] { paramArrayOfXSObject[0].getNamespace() };
    this.fMaps = null;
    this.fNSNum = 1;
    this.fArray = paramArrayOfXSObject;
    this.fLength = paramInt;
  }
  
  public synchronized int getLength() {
    if (this.fLength == -1) {
      this.fLength = 0;
      for (byte b = 0; b < this.fNSNum; b++)
        this.fLength += this.fMaps[b].getLength(); 
    } 
    return this.fLength;
  }
  
  public XSObject itemByName(String paramString1, String paramString2) {
    for (byte b = 0; b < this.fNSNum; b++) {
      if (isEqual(paramString1, this.fNamespaces[b])) {
        if (this.fMaps != null)
          return (XSObject)this.fMaps[b].get(paramString2); 
        for (byte b1 = 0; b1 < this.fLength; b1++) {
          XSObject xSObject = this.fArray[b1];
          if (xSObject.getName().equals(paramString2))
            return xSObject; 
        } 
        return null;
      } 
    } 
    return null;
  }
  
  public synchronized XSObject item(int paramInt) {
    if (this.fArray == null) {
      getLength();
      this.fArray = new XSObject[this.fLength];
      int i = 0;
      for (byte b = 0; b < this.fNSNum; b++)
        i += this.fMaps[b].getValues((Object[])this.fArray, i); 
    } 
    return (paramInt < 0 || paramInt >= this.fLength) ? null : this.fArray[paramInt];
  }
  
  final boolean isEqual(String paramString1, String paramString2) {
    return (paramString1 != null) ? paramString1.equals(paramString2) : ((paramString2 == null));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\XSNamedMapImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */