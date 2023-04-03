package org.apache.xerces.util;

import org.apache.xerces.xni.parser.XMLInputSource;
import org.w3c.dom.Node;

public final class DOMInputSource extends XMLInputSource {
  private Node fNode;
  
  public DOMInputSource() {
    this(null);
  }
  
  public DOMInputSource(Node paramNode) {
    super(null, getSystemIdFromNode(paramNode), null);
    this.fNode = paramNode;
  }
  
  public DOMInputSource(Node paramNode, String paramString) {
    super(null, paramString, null);
    this.fNode = paramNode;
  }
  
  public Node getNode() {
    return this.fNode;
  }
  
  public void setNode(Node paramNode) {
    this.fNode = paramNode;
  }
  
  private static String getSystemIdFromNode(Node paramNode) {
    if (paramNode != null)
      try {
        return paramNode.getBaseURI();
      } catch (NoSuchMethodError noSuchMethodError) {
        return null;
      } catch (Exception exception) {
        return null;
      }  
    return null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\DOMInputSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */