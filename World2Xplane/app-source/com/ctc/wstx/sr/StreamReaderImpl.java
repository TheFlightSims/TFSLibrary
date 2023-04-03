package com.ctc.wstx.sr;

import com.ctc.wstx.ent.EntityDecl;
import javax.xml.stream.Location;
import org.codehaus.stax2.XMLStreamReader2;

public interface StreamReaderImpl extends XMLStreamReader2 {
  EntityDecl getCurrentEntityDecl();
  
  Object withStartElement(ElemCallback paramElemCallback, Location paramLocation);
  
  boolean isNamespaceAware();
  
  AttributeCollector getAttributeCollector();
  
  InputElementStack getInputElementStack();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\StreamReaderImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */