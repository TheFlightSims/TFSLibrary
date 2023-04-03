package com.ctc.wstx.dtd;

import java.net.URL;
import javax.xml.stream.XMLStreamException;

public interface DTDEventListener {
  boolean dtdReportComments();
  
  void dtdProcessingInstruction(String paramString1, String paramString2);
  
  void dtdComment(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  void dtdSkippedEntity(String paramString);
  
  void dtdNotationDecl(String paramString1, String paramString2, String paramString3, URL paramURL) throws XMLStreamException;
  
  void dtdUnparsedEntityDecl(String paramString1, String paramString2, String paramString3, String paramString4, URL paramURL) throws XMLStreamException;
  
  void attributeDecl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
  
  void dtdElementDecl(String paramString1, String paramString2);
  
  void dtdExternalEntityDecl(String paramString1, String paramString2, String paramString3);
  
  void dtdInternalEntityDecl(String paramString1, String paramString2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDEventListener.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */