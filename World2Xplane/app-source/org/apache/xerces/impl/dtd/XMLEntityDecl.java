package org.apache.xerces.impl.dtd;

public class XMLEntityDecl {
  public String name;
  
  public String publicId;
  
  public String systemId;
  
  public String baseSystemId;
  
  public String notation;
  
  public boolean isPE;
  
  public boolean inExternal;
  
  public String value;
  
  public void setValues(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2) {
    setValues(paramString1, paramString2, paramString3, paramString4, paramString5, null, paramBoolean1, paramBoolean2);
  }
  
  public void setValues(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, boolean paramBoolean1, boolean paramBoolean2) {
    this.name = paramString1;
    this.publicId = paramString2;
    this.systemId = paramString3;
    this.baseSystemId = paramString4;
    this.notation = paramString5;
    this.value = paramString6;
    this.isPE = paramBoolean1;
    this.inExternal = paramBoolean2;
  }
  
  public void clear() {
    this.name = null;
    this.publicId = null;
    this.systemId = null;
    this.baseSystemId = null;
    this.notation = null;
    this.value = null;
    this.isPE = false;
    this.inExternal = false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\XMLEntityDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */