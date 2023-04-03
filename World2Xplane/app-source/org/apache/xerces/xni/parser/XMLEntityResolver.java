package org.apache.xerces.xni.parser;

import java.io.IOException;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;

public interface XMLEntityResolver {
  XMLInputSource resolveEntity(XMLResourceIdentifier paramXMLResourceIdentifier) throws XNIException, IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\parser\XMLEntityResolver.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */