package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ElementDefinitionImpl extends ParentNode {
  static final long serialVersionUID = -8373890672670022714L;
  
  protected String name;
  
  protected NamedNodeMapImpl attributes;
  
  public ElementDefinitionImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl);
    this.name = paramString;
    this.attributes = new NamedNodeMapImpl(paramCoreDocumentImpl);
  }
  
  public short getNodeType() {
    return 21;
  }
  
  public String getNodeName() {
    if (needsSyncData())
      synchronizeData(); 
    return this.name;
  }
  
  public Node cloneNode(boolean paramBoolean) {
    ElementDefinitionImpl elementDefinitionImpl = (ElementDefinitionImpl)super.cloneNode(paramBoolean);
    elementDefinitionImpl.attributes = this.attributes.cloneMap(elementDefinitionImpl);
    return elementDefinitionImpl;
  }
  
  public NamedNodeMap getAttributes() {
    if (needsSyncChildren())
      synchronizeChildren(); 
    return this.attributes;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\ElementDefinitionImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */