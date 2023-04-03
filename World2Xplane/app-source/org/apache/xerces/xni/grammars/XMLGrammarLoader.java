package org.apache.xerces.xni.grammars;

import java.io.IOException;
import java.util.Locale;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLInputSource;

public interface XMLGrammarLoader {
  String[] getRecognizedFeatures();
  
  boolean getFeature(String paramString) throws XMLConfigurationException;
  
  void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException;
  
  String[] getRecognizedProperties();
  
  Object getProperty(String paramString) throws XMLConfigurationException;
  
  void setProperty(String paramString, Object paramObject) throws XMLConfigurationException;
  
  void setLocale(Locale paramLocale);
  
  Locale getLocale();
  
  void setErrorHandler(XMLErrorHandler paramXMLErrorHandler);
  
  XMLErrorHandler getErrorHandler();
  
  void setEntityResolver(XMLEntityResolver paramXMLEntityResolver);
  
  XMLEntityResolver getEntityResolver();
  
  Grammar loadGrammar(XMLInputSource paramXMLInputSource) throws IOException, XNIException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\grammars\XMLGrammarLoader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */