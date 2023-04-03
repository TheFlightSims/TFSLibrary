package org.apache.xerces.xs;

public interface XSMultiValueFacet extends XSObject {
  short getFacetKind();
  
  StringList getLexicalFacetValues();
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSMultiValueFacet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */