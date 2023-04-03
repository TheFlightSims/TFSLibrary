package org.apache.xerces.dom3.as;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ElementEditAS extends NodeEditAS {
  NodeList getDefinedElementTypes();
  
  short contentType();
  
  boolean canSetAttribute(String paramString1, String paramString2);
  
  boolean canSetAttributeNode(Attr paramAttr);
  
  boolean canSetAttributeNS(String paramString1, String paramString2, String paramString3);
  
  boolean canRemoveAttribute(String paramString);
  
  boolean canRemoveAttributeNS(String paramString1, String paramString2);
  
  boolean canRemoveAttributeNode(Node paramNode);
  
  NodeList getChildElements();
  
  NodeList getParentElements();
  
  NodeList getAttributeList();
  
  boolean isElementDefined(String paramString);
  
  boolean isElementDefinedNS(String paramString1, String paramString2, String paramString3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\ElementEditAS.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */