package org.apache.xerces.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;

public class ParserConfigurationSettings implements XMLComponentManager {
  protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
  
  protected ArrayList fRecognizedProperties = new ArrayList();
  
  protected HashMap fProperties = new HashMap();
  
  protected ArrayList fRecognizedFeatures = new ArrayList();
  
  protected HashMap fFeatures = new HashMap();
  
  protected XMLComponentManager fParentSettings;
  
  public ParserConfigurationSettings() {
    this(null);
  }
  
  public ParserConfigurationSettings(XMLComponentManager paramXMLComponentManager) {
    this.fParentSettings = paramXMLComponentManager;
  }
  
  public void addRecognizedFeatures(String[] paramArrayOfString) {
    byte b1 = (paramArrayOfString != null) ? paramArrayOfString.length : 0;
    for (byte b2 = 0; b2 < b1; b2++) {
      String str = paramArrayOfString[b2];
      if (!this.fRecognizedFeatures.contains(str))
        this.fRecognizedFeatures.add(str); 
    } 
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws XMLConfigurationException {
    checkFeature(paramString);
    this.fFeatures.put(paramString, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
  }
  
  public void addRecognizedProperties(String[] paramArrayOfString) {
    byte b1 = (paramArrayOfString != null) ? paramArrayOfString.length : 0;
    for (byte b2 = 0; b2 < b1; b2++) {
      String str = paramArrayOfString[b2];
      if (!this.fRecognizedProperties.contains(str))
        this.fRecognizedProperties.add(str); 
    } 
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    checkProperty(paramString);
    this.fProperties.put(paramString, paramObject);
  }
  
  public boolean getFeature(String paramString) throws XMLConfigurationException {
    Boolean bool = (Boolean)this.fFeatures.get(paramString);
    if (bool == null) {
      checkFeature(paramString);
      return false;
    } 
    return bool.booleanValue();
  }
  
  public Object getProperty(String paramString) throws XMLConfigurationException {
    Object object = this.fProperties.get(paramString);
    if (object == null)
      checkProperty(paramString); 
    return object;
  }
  
  protected void checkFeature(String paramString) throws XMLConfigurationException {
    if (!this.fRecognizedFeatures.contains(paramString))
      if (this.fParentSettings != null) {
        this.fParentSettings.getFeature(paramString);
      } else {
        boolean bool = false;
        throw new XMLConfigurationException(bool, paramString);
      }  
  }
  
  protected void checkProperty(String paramString) throws XMLConfigurationException {
    if (!this.fRecognizedProperties.contains(paramString))
      if (this.fParentSettings != null) {
        this.fParentSettings.getProperty(paramString);
      } else {
        boolean bool = false;
        throw new XMLConfigurationException(bool, paramString);
      }  
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\ParserConfigurationSettings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */