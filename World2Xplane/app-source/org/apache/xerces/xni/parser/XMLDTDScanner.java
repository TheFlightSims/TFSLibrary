package org.apache.xerces.xni.parser;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;

public interface XMLDTDScanner extends XMLDTDSource, XMLDTDContentModelSource {
  void setInputSource(XMLInputSource paramXMLInputSource) throws IOException;
  
  boolean scanDTDInternalSubset(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws IOException, XNIException;
  
  boolean scanDTDExternalSubset(boolean paramBoolean) throws IOException, XNIException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLDTDScanner.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */