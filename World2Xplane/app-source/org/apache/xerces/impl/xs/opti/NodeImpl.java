package org.apache.xerces.impl.xs.opti;

public class NodeImpl extends DefaultNode {
  String prefix;
  
  String localpart;
  
  String rawname;
  
  String uri;
  
  short nodeType;
  
  boolean hidden;
  
  public NodeImpl() {}
  
  public NodeImpl(String paramString1, String paramString2, String paramString3, String paramString4, short paramShort) {
    this.prefix = paramString1;
    this.localpart = paramString2;
    this.rawname = paramString3;
    this.uri = paramString4;
    this.nodeType = paramShort;
  }
  
  public String getNodeName() {
    return this.rawname;
  }
  
  public String getNamespaceURI() {
    return this.uri;
  }
  
  public String getPrefix() {
    return this.prefix;
  }
  
  public String getLocalName() {
    return this.localpart;
  }
  
  public short getNodeType() {
    return this.nodeType;
  }
  
  public void setReadOnly(boolean paramBoolean1, boolean paramBoolean2) {
    this.hidden = paramBoolean1;
  }
  
  public boolean getReadOnly() {
    return this.hidden;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\opti\NodeImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */