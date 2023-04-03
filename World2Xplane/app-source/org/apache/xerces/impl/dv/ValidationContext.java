package org.apache.xerces.impl.dv;

public interface ValidationContext {
  boolean needFacetChecking();
  
  boolean needExtraChecking();
  
  boolean needToNormalize();
  
  boolean useNamespaces();
  
  boolean isEntityDeclared(String paramString);
  
  boolean isEntityUnparsed(String paramString);
  
  boolean isIdDeclared(String paramString);
  
  void addId(String paramString);
  
  void addIdRef(String paramString);
  
  String getSymbol(String paramString);
  
  String getURI(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\ValidationContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */