package org.apache.xerces.xs;

public interface XSElementDeclaration extends XSTerm {
  XSTypeDefinition getTypeDefinition();
  
  short getScope();
  
  XSComplexTypeDefinition getEnclosingCTDefinition();
  
  short getConstraintType();
  
  String getConstraintValue();
  
  Object getActualVC() throws XSException;
  
  short getActualVCType() throws XSException;
  
  ShortList getItemValueTypes() throws XSException;
  
  boolean getNillable();
  
  XSNamedMap getIdentityConstraints();
  
  XSElementDeclaration getSubstitutionGroupAffiliation();
  
  boolean isSubstitutionGroupExclusion(short paramShort);
  
  short getSubstitutionGroupExclusions();
  
  boolean isDisallowedSubstitution(short paramShort);
  
  short getDisallowedSubstitutions();
  
  boolean getAbstract();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSElementDeclaration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */