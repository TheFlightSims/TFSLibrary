package org.apache.xerces.jaxp.validation;

import javax.xml.transform.stream.StreamSource;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

final class Util {
  public static final XMLInputSource toXMLInputSource(StreamSource paramStreamSource) {
    return (paramStreamSource.getReader() != null) ? new XMLInputSource(paramStreamSource.getPublicId(), paramStreamSource.getSystemId(), paramStreamSource.getSystemId(), paramStreamSource.getReader(), null) : ((paramStreamSource.getInputStream() != null) ? new XMLInputSource(paramStreamSource.getPublicId(), paramStreamSource.getSystemId(), paramStreamSource.getSystemId(), paramStreamSource.getInputStream(), null) : new XMLInputSource(paramStreamSource.getPublicId(), paramStreamSource.getSystemId(), paramStreamSource.getSystemId()));
  }
  
  public static SAXException toSAXException(XNIException paramXNIException) {
    return (paramXNIException instanceof XMLParseException) ? toSAXParseException((XMLParseException)paramXNIException) : ((paramXNIException.getException() instanceof SAXException) ? (SAXException)paramXNIException.getException() : new SAXException(paramXNIException.getMessage(), paramXNIException.getException()));
  }
  
  public static SAXParseException toSAXParseException(XMLParseException paramXMLParseException) {
    return (paramXMLParseException.getException() instanceof SAXParseException) ? (SAXParseException)paramXMLParseException.getException() : new SAXParseException(paramXMLParseException.getMessage(), paramXMLParseException.getPublicId(), paramXMLParseException.getExpandedSystemId(), paramXMLParseException.getLineNumber(), paramXMLParseException.getColumnNumber(), paramXMLParseException.getException());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\Util.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */