package org.apache.xerces.xs;

public interface XSParticle extends XSObject {
  int getMinOccurs();
  
  int getMaxOccurs();
  
  boolean getMaxOccursUnbounded();
  
  XSTerm getTerm();
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSParticle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */