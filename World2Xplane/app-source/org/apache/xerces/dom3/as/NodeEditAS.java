package org.apache.xerces.dom3.as;

import org.w3c.dom.Node;

public interface NodeEditAS {
  public static final short WF_CHECK = 1;
  
  public static final short NS_WF_CHECK = 2;
  
  public static final short PARTIAL_VALIDITY_CHECK = 3;
  
  public static final short STRICT_VALIDITY_CHECK = 4;
  
  boolean canInsertBefore(Node paramNode1, Node paramNode2);
  
  boolean canRemoveChild(Node paramNode);
  
  boolean canReplaceChild(Node paramNode1, Node paramNode2);
  
  boolean canAppendChild(Node paramNode);
  
  boolean isNodeValid(boolean paramBoolean, short paramShort) throws DOMASException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\NodeEditAS.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */