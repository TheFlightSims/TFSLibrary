package org.apache.xerces.dom;

import org.w3c.dom.DOMLocator;
import org.w3c.dom.Node;

public class DOMLocatorImpl implements DOMLocator {
  public int fColumnNumber = -1;
  
  public int fLineNumber = -1;
  
  public Node fRelatedNode = null;
  
  public String fUri = null;
  
  public int fByteOffset = -1;
  
  public int fUtf16Offset = -1;
  
  public DOMLocatorImpl() {}
  
  public DOMLocatorImpl(int paramInt1, int paramInt2, String paramString) {
    this.fLineNumber = paramInt1;
    this.fColumnNumber = paramInt2;
    this.fUri = paramString;
  }
  
  public DOMLocatorImpl(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    this.fLineNumber = paramInt1;
    this.fColumnNumber = paramInt2;
    this.fUri = paramString;
    this.fUtf16Offset = paramInt3;
  }
  
  public DOMLocatorImpl(int paramInt1, int paramInt2, int paramInt3, Node paramNode, String paramString) {
    this.fLineNumber = paramInt1;
    this.fColumnNumber = paramInt2;
    this.fByteOffset = paramInt3;
    this.fRelatedNode = paramNode;
    this.fUri = paramString;
  }
  
  public DOMLocatorImpl(int paramInt1, int paramInt2, int paramInt3, Node paramNode, String paramString, int paramInt4) {
    this.fLineNumber = paramInt1;
    this.fColumnNumber = paramInt2;
    this.fByteOffset = paramInt3;
    this.fRelatedNode = paramNode;
    this.fUri = paramString;
    this.fUtf16Offset = paramInt4;
  }
  
  public int getLineNumber() {
    return this.fLineNumber;
  }
  
  public int getColumnNumber() {
    return this.fColumnNumber;
  }
  
  public String getUri() {
    return this.fUri;
  }
  
  public Node getRelatedNode() {
    return this.fRelatedNode;
  }
  
  public int getByteOffset() {
    return this.fByteOffset;
  }
  
  public int getUtf16Offset() {
    return this.fUtf16Offset;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMLocatorImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */