package org.apache.xerces.xs;

public interface XSAnnotation extends XSObject {
  public static final short W3C_DOM_ELEMENT = 1;
  
  public static final short SAX_CONTENTHANDLER = 2;
  
  public static final short W3C_DOM_DOCUMENT = 3;
  
  boolean writeAnnotation(Object paramObject, short paramShort);
  
  String getAnnotationString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSAnnotation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */