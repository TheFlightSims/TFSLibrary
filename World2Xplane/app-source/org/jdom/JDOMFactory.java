package org.jdom;

import java.util.Map;

public interface JDOMFactory {
  Attribute attribute(String paramString1, String paramString2, Namespace paramNamespace);
  
  Attribute attribute(String paramString1, String paramString2, int paramInt, Namespace paramNamespace);
  
  Attribute attribute(String paramString1, String paramString2);
  
  Attribute attribute(String paramString1, String paramString2, int paramInt);
  
  CDATA cdata(String paramString);
  
  Text text(String paramString);
  
  Comment comment(String paramString);
  
  DocType docType(String paramString1, String paramString2, String paramString3);
  
  DocType docType(String paramString1, String paramString2);
  
  DocType docType(String paramString);
  
  Document document(Element paramElement, DocType paramDocType);
  
  Document document(Element paramElement, DocType paramDocType, String paramString);
  
  Document document(Element paramElement);
  
  Element element(String paramString, Namespace paramNamespace);
  
  Element element(String paramString);
  
  Element element(String paramString1, String paramString2);
  
  Element element(String paramString1, String paramString2, String paramString3);
  
  ProcessingInstruction processingInstruction(String paramString, Map paramMap);
  
  ProcessingInstruction processingInstruction(String paramString1, String paramString2);
  
  EntityRef entityRef(String paramString);
  
  EntityRef entityRef(String paramString1, String paramString2, String paramString3);
  
  EntityRef entityRef(String paramString1, String paramString2);
  
  void addContent(Parent paramParent, Content paramContent);
  
  void setAttribute(Element paramElement, Attribute paramAttribute);
  
  void addNamespaceDeclaration(Element paramElement, Namespace paramNamespace);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\JDOMFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */