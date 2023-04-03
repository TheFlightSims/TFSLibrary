package org.apache.xerces.xni.parser;

import java.io.IOException;
import java.util.Locale;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XNIException;

public interface XMLParserConfiguration extends XMLComponentManager {
  void parse(XMLInputSource paramXMLInputSource) throws XNIException, IOException;
  
  void addRecognizedFeatures(String[] paramArrayOfString);
  
  void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException;
  
  boolean getFeature(String paramString) throws XMLConfigurationException;
  
  void addRecognizedProperties(String[] paramArrayOfString);
  
  void setProperty(String paramString, Object paramObject) throws XMLConfigurationException;
  
  Object getProperty(String paramString) throws XMLConfigurationException;
  
  void setErrorHandler(XMLErrorHandler paramXMLErrorHandler);
  
  XMLErrorHandler getErrorHandler();
  
  void setDocumentHandler(XMLDocumentHandler paramXMLDocumentHandler);
  
  XMLDocumentHandler getDocumentHandler();
  
  void setDTDHandler(XMLDTDHandler paramXMLDTDHandler);
  
  XMLDTDHandler getDTDHandler();
  
  void setDTDContentModelHandler(XMLDTDContentModelHandler paramXMLDTDContentModelHandler);
  
  XMLDTDContentModelHandler getDTDContentModelHandler();
  
  void setEntityResolver(XMLEntityResolver paramXMLEntityResolver);
  
  XMLEntityResolver getEntityResolver();
  
  void setLocale(Locale paramLocale) throws XNIException;
  
  Locale getLocale();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLParserConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */