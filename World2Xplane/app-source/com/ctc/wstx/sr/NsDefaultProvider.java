package com.ctc.wstx.sr;

import javax.xml.stream.XMLStreamException;

public interface NsDefaultProvider {
  boolean mayHaveNsDefaults(String paramString1, String paramString2);
  
  void checkNsDefaults(InputElementStack paramInputElementStack) throws XMLStreamException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\NsDefaultProvider.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */