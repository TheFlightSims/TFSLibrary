package org.apache.xerces.xs;

public interface XSModelGroup extends XSTerm {
  public static final short COMPOSITOR_SEQUENCE = 1;
  
  public static final short COMPOSITOR_CHOICE = 2;
  
  public static final short COMPOSITOR_ALL = 3;
  
  short getCompositor();
  
  XSObjectList getParticles();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSModelGroup.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */