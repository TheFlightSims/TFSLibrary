package org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;

public interface Serializer {
  void setOutputByteStream(OutputStream paramOutputStream);
  
  void setOutputCharStream(Writer paramWriter);
  
  void setOutputFormat(OutputFormat paramOutputFormat);
  
  DocumentHandler asDocumentHandler() throws IOException;
  
  ContentHandler asContentHandler() throws IOException;
  
  DOMSerializer asDOMSerializer() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\Serializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */