package org.apache.xerces.impl.xs.opti;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NamedNodeMapImpl implements NamedNodeMap {
  Attr[] attrs;
  
  public NamedNodeMapImpl(Attr[] paramArrayOfAttr) {
    this.attrs = paramArrayOfAttr;
  }
  
  public Node getNamedItem(String paramString) {
    for (byte b = 0; b < this.attrs.length; b++) {
      if (this.attrs[b].getName().equals(paramString))
        return this.attrs[b]; 
    } 
    return null;
  }
  
  public Node item(int paramInt) {
    return (paramInt < 0 && paramInt > getLength()) ? null : this.attrs[paramInt];
  }
  
  public int getLength() {
    return this.attrs.length;
  }
  
  public Node getNamedItemNS(String paramString1, String paramString2) {
    for (byte b = 0; b < this.attrs.length; b++) {
      if (this.attrs[b].getName().equals(paramString2) && this.attrs[b].getNamespaceURI().equals(paramString1))
        return this.attrs[b]; 
    } 
    return null;
  }
  
  public Node setNamedItemNS(Node paramNode) throws DOMException {
    throw new DOMException((short)9, "Method not supported");
  }
  
  public Node setNamedItem(Node paramNode) throws DOMException {
    throw new DOMException((short)9, "Method not supported");
  }
  
  public Node removeNamedItem(String paramString) throws DOMException {
    throw new DOMException((short)9, "Method not supported");
  }
  
  public Node removeNamedItemNS(String paramString1, String paramString2) throws DOMException {
    throw new DOMException((short)9, "Method not supported");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\opti\NamedNodeMapImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */