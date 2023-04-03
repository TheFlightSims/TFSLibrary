package org.apache.xerces.xni;

public class XMLString {
  public char[] ch;
  
  public int offset;
  
  public int length;
  
  public XMLString() {}
  
  public XMLString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    setValues(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public XMLString(XMLString paramXMLString) {
    setValues(paramXMLString);
  }
  
  public void setValues(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.ch = paramArrayOfchar;
    this.offset = paramInt1;
    this.length = paramInt2;
  }
  
  public void setValues(XMLString paramXMLString) {
    setValues(paramXMLString.ch, paramXMLString.offset, paramXMLString.length);
  }
  
  public void clear() {
    this.ch = null;
    this.offset = 0;
    this.length = -1;
  }
  
  public boolean equals(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramArrayOfchar == null)
      return false; 
    if (this.length != paramInt2)
      return false; 
    for (byte b = 0; b < paramInt2; b++) {
      if (this.ch[this.offset + b] != paramArrayOfchar[paramInt1 + b])
        return false; 
    } 
    return true;
  }
  
  public boolean equals(String paramString) {
    if (paramString == null)
      return false; 
    if (this.length != paramString.length())
      return false; 
    for (byte b = 0; b < this.length; b++) {
      if (this.ch[this.offset + b] != paramString.charAt(b))
        return false; 
    } 
    return true;
  }
  
  public String toString() {
    return (this.length > 0) ? new String(this.ch, this.offset, this.length) : "";
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\XMLString.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */