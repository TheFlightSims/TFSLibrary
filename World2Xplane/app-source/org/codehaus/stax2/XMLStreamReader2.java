package org.codehaus.stax2;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.typed.TypedXMLStreamReader;
import org.codehaus.stax2.validation.Validatable;

public interface XMLStreamReader2 extends TypedXMLStreamReader, Validatable {
  public static final String FEATURE_DTD_OVERRIDE = "org.codehaus.stax2.propDtdOverride";
  
  boolean isPropertySupported(String paramString);
  
  boolean setProperty(String paramString, Object paramObject);
  
  Object getFeature(String paramString);
  
  void setFeature(String paramString, Object paramObject);
  
  void skipElement() throws XMLStreamException;
  
  DTDInfo getDTDInfo() throws XMLStreamException;
  
  AttributeInfo getAttributeInfo() throws XMLStreamException;
  
  LocationInfo getLocationInfo();
  
  int getText(Writer paramWriter, boolean paramBoolean) throws IOException, XMLStreamException;
  
  boolean isEmptyElement() throws XMLStreamException;
  
  int getDepth();
  
  NamespaceContext getNonTransientNamespaceContext();
  
  String getPrefixedName();
  
  void closeCompletely() throws XMLStreamException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\XMLStreamReader2.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */