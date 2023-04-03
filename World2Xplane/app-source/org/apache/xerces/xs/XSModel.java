package org.apache.xerces.xs;

public interface XSModel {
  StringList getNamespaces();
  
  XSNamespaceItemList getNamespaceItems();
  
  XSNamedMap getComponents(short paramShort);
  
  XSNamedMap getComponentsByNamespace(short paramShort, String paramString);
  
  XSObjectList getAnnotations();
  
  XSElementDeclaration getElementDeclaration(String paramString1, String paramString2);
  
  XSAttributeDeclaration getAttributeDeclaration(String paramString1, String paramString2);
  
  XSTypeDefinition getTypeDefinition(String paramString1, String paramString2);
  
  XSAttributeGroupDefinition getAttributeGroup(String paramString1, String paramString2);
  
  XSModelGroupDefinition getModelGroupDefinition(String paramString1, String paramString2);
  
  XSNotationDeclaration getNotationDeclaration(String paramString1, String paramString2);
  
  XSObjectList getSubstitutionGroup(XSElementDeclaration paramXSElementDeclaration);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */