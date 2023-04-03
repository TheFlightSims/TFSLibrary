package org.apache.xerces.impl.dtd;

import org.apache.xerces.impl.dtd.models.ContentModelValidator;
import org.apache.xerces.xni.QName;

public class XMLElementDecl {
  public static final short TYPE_ANY = 0;
  
  public static final short TYPE_EMPTY = 1;
  
  public static final short TYPE_MIXED = 2;
  
  public static final short TYPE_CHILDREN = 3;
  
  public static final short TYPE_SIMPLE = 4;
  
  public final QName name = new QName();
  
  public int scope = -1;
  
  public short type = -1;
  
  public ContentModelValidator contentModelValidator;
  
  public final XMLSimpleType simpleType = new XMLSimpleType();
  
  public void setValues(QName paramQName, int paramInt, short paramShort, ContentModelValidator paramContentModelValidator, XMLSimpleType paramXMLSimpleType) {
    this.name.setValues(paramQName);
    this.scope = paramInt;
    this.type = paramShort;
    this.contentModelValidator = paramContentModelValidator;
    this.simpleType.setValues(paramXMLSimpleType);
  }
  
  public void clear() {
    this.name.clear();
    this.type = -1;
    this.scope = -1;
    this.contentModelValidator = null;
    this.simpleType.clear();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\XMLElementDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */