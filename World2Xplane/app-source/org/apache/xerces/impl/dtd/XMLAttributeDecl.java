package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.QName;

public class XMLAttributeDecl {
  public final QName name = new QName();
  
  public final XMLSimpleType simpleType = new XMLSimpleType();
  
  public boolean optional;
  
  public void setValues(QName paramQName, XMLSimpleType paramXMLSimpleType, boolean paramBoolean) {
    this.name.setValues(paramQName);
    this.simpleType.setValues(paramXMLSimpleType);
    this.optional = paramBoolean;
  }
  
  public void clear() {
    this.name.clear();
    this.simpleType.clear();
    this.optional = false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\XMLAttributeDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */