package org.apache.xerces.dom;

import org.w3c.dom.Node;

public interface DeferredNode extends Node {
  public static final short TYPE_NODE = 20;
  
  int getNodeIndex();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DeferredNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */