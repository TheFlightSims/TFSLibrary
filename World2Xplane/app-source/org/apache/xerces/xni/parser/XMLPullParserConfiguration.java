package org.apache.xerces.xni.parser;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;

public interface XMLPullParserConfiguration extends XMLParserConfiguration {
  void setInputSource(XMLInputSource paramXMLInputSource) throws XMLConfigurationException, IOException;
  
  boolean parse(boolean paramBoolean) throws XNIException, IOException;
  
  void cleanup();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLPullParserConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */