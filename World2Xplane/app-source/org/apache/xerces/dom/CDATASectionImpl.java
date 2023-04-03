package org.apache.xerces.dom;

import org.w3c.dom.CDATASection;

public class CDATASectionImpl extends TextImpl implements CDATASection {
  static final long serialVersionUID = 2372071297878177780L;
  
  public CDATASectionImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl, paramString);
  }
  
  public short getNodeType() {
    return 4;
  }
  
  public String getNodeName() {
    return "#cdata-section";
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\CDATASectionImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */