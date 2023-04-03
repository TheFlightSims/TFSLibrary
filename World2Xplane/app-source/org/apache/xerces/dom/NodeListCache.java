package org.apache.xerces.dom;

import java.io.Serializable;

class NodeListCache implements Serializable {
  private static final long serialVersionUID = -7927529254918631002L;
  
  int fLength = -1;
  
  int fChildIndex = -1;
  
  ChildNode fChild;
  
  ParentNode fOwner;
  
  NodeListCache next;
  
  NodeListCache(ParentNode paramParentNode) {
    this.fOwner = paramParentNode;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\NodeListCache.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */