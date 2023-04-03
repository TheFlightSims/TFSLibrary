package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSModelGroup;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;

public class XSGroupDecl implements XSModelGroupDefinition {
  public String fName = null;
  
  public String fTargetNamespace = null;
  
  public XSModelGroupImpl fModelGroup = null;
  
  public XSObjectList fAnnotations = null;
  
  public short getType() {
    return 6;
  }
  
  public String getName() {
    return this.fName;
  }
  
  public String getNamespace() {
    return this.fTargetNamespace;
  }
  
  public XSModelGroup getModelGroup() {
    return this.fModelGroup;
  }
  
  public XSAnnotation getAnnotation() {
    return (this.fAnnotations != null) ? (XSAnnotation)this.fAnnotations.item(0) : null;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XSGroupDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */