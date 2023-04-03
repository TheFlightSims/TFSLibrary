package org.codehaus.stax2;

import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.typed.TypedXMLStreamWriter;
import org.codehaus.stax2.validation.Validatable;

public interface XMLStreamWriter2 extends TypedXMLStreamWriter, Validatable {
  boolean isPropertySupported(String paramString);
  
  boolean setProperty(String paramString, Object paramObject);
  
  XMLStreamLocation2 getLocation();
  
  String getEncoding();
  
  void writeCData(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeDTD(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
  
  void writeFullEndElement() throws XMLStreamException;
  
  void writeStartDocument(String paramString1, String paramString2, boolean paramBoolean) throws XMLStreamException;
  
  void writeSpace(String paramString) throws XMLStreamException;
  
  void writeSpace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeRaw(String paramString) throws XMLStreamException;
  
  void writeRaw(String paramString, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void copyEventFromReader(XMLStreamReader2 paramXMLStreamReader2, boolean paramBoolean) throws XMLStreamException;
  
  void closeCompletely() throws XMLStreamException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\XMLStreamWriter2.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */