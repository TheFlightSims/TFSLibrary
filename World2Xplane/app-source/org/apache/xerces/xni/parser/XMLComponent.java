package org.apache.xerces.xni.parser;

public interface XMLComponent {
  void reset(XMLComponentManager paramXMLComponentManager) throws XMLConfigurationException;
  
  String[] getRecognizedFeatures();
  
  void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException;
  
  String[] getRecognizedProperties();
  
  void setProperty(String paramString, Object paramObject) throws XMLConfigurationException;
  
  Boolean getFeatureDefault(String paramString);
  
  Object getPropertyDefault(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLComponent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */