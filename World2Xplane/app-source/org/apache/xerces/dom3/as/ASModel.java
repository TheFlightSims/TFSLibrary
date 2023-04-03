package org.apache.xerces.dom3.as;

import org.w3c.dom.DOMException;

public interface ASModel extends ASObject {
  boolean getIsNamespaceAware();
  
  short getUsageLocation();
  
  String getAsLocation();
  
  void setAsLocation(String paramString);
  
  String getAsHint();
  
  void setAsHint(String paramString);
  
  ASNamedObjectMap getElementDeclarations();
  
  ASNamedObjectMap getAttributeDeclarations();
  
  ASNamedObjectMap getNotationDeclarations();
  
  ASNamedObjectMap getEntityDeclarations();
  
  ASNamedObjectMap getContentModelDeclarations();
  
  void addASModel(ASModel paramASModel);
  
  ASObjectList getASModels();
  
  void removeAS(ASModel paramASModel);
  
  boolean validate();
  
  ASElementDeclaration createASElementDeclaration(String paramString1, String paramString2) throws DOMException;
  
  ASAttributeDeclaration createASAttributeDeclaration(String paramString1, String paramString2) throws DOMException;
  
  ASNotationDeclaration createASNotationDeclaration(String paramString1, String paramString2, String paramString3, String paramString4) throws DOMException;
  
  ASEntityDeclaration createASEntityDeclaration(String paramString) throws DOMException;
  
  ASContentModel createASContentModel(int paramInt1, int paramInt2, short paramShort) throws DOMASException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\ASModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */