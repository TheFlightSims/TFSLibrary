package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.opti.ElementImpl;
import org.w3c.dom.Element;

final class XSAnnotationInfo {
  String fAnnotation;
  
  int fLine;
  
  int fColumn;
  
  int fCharOffset;
  
  XSAnnotationInfo next;
  
  XSAnnotationInfo(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    this.fAnnotation = paramString;
    this.fLine = paramInt1;
    this.fColumn = paramInt2;
    this.fCharOffset = paramInt3;
  }
  
  XSAnnotationInfo(String paramString, Element paramElement) {
    this.fAnnotation = paramString;
    if (paramElement instanceof ElementImpl) {
      ElementImpl elementImpl = (ElementImpl)paramElement;
      this.fLine = elementImpl.getLineNumber();
      this.fColumn = elementImpl.getColumnNumber();
      this.fCharOffset = elementImpl.getCharacterOffset();
    } else {
      this.fLine = -1;
      this.fColumn = -1;
      this.fCharOffset = -1;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSAnnotationInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */