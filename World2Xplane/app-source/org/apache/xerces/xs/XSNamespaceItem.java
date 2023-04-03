package org.apache.xerces.xs;

public interface XSNamespaceItem {
  String getSchemaNamespace();
  
  XSNamedMap getComponents(short paramShort);
  
  XSObjectList getAnnotations();
  
  XSElementDeclaration getElementDeclaration(String paramString);
  
  XSAttributeDeclaration getAttributeDeclaration(String paramString);
  
  XSTypeDefinition getTypeDefinition(String paramString);
  
  XSAttributeGroupDefinition getAttributeGroup(String paramString);
  
  XSModelGroupDefinition getModelGroupDefinition(String paramString);
  
  XSNotationDeclaration getNotationDeclaration(String paramString);
  
  StringList getDocumentLocations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSNamespaceItem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */