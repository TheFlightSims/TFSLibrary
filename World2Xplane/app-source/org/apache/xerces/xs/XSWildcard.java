package org.apache.xerces.xs;

public interface XSWildcard extends XSTerm {
  public static final short NSCONSTRAINT_ANY = 1;
  
  public static final short NSCONSTRAINT_NOT = 2;
  
  public static final short NSCONSTRAINT_LIST = 3;
  
  public static final short PC_STRICT = 1;
  
  public static final short PC_SKIP = 2;
  
  public static final short PC_LAX = 3;
  
  short getConstraintType();
  
  StringList getNsConstraintList();
  
  short getProcessContents();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSWildcard.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */