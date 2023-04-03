package org.apache.xml.serialize;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;

public interface DOMSerializer {
  void serialize(Element paramElement) throws IOException;
  
  void serialize(Document paramDocument) throws IOException;
  
  void serialize(DocumentFragment paramDocumentFragment) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\DOMSerializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */