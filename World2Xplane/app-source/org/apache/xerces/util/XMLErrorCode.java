package org.apache.xerces.util;

final class XMLErrorCode {
  private String fDomain;
  
  private String fKey;
  
  public XMLErrorCode(String paramString1, String paramString2) {
    this.fDomain = paramString1;
    this.fKey = paramString2;
  }
  
  public void setValues(String paramString1, String paramString2) {
    this.fDomain = paramString1;
    this.fKey = paramString2;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof XMLErrorCode))
      return false; 
    XMLErrorCode xMLErrorCode = (XMLErrorCode)paramObject;
    return (this.fDomain.equals(xMLErrorCode.fDomain) && this.fKey.equals(xMLErrorCode.fKey));
  }
  
  public int hashCode() {
    return this.fDomain.hashCode() + this.fKey.hashCode();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\XMLErrorCode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */