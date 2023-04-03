package org.apache.xerces.impl.xs.identity;

public interface FieldActivator {
  void startValueScopeFor(IdentityConstraint paramIdentityConstraint, int paramInt);
  
  XPathMatcher activateField(Field paramField, int paramInt);
  
  void endValueScopeFor(IdentityConstraint paramIdentityConstraint, int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\identity\FieldActivator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */