package com.ctc.wstx.sr;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.validation.XMLValidationProblem;

public interface InputProblemReporter {
  void throwParseError(String paramString) throws XMLStreamException;
  
  void throwParseError(String paramString, Object paramObject1, Object paramObject2) throws XMLStreamException;
  
  void reportValidationProblem(XMLValidationProblem paramXMLValidationProblem) throws XMLStreamException;
  
  void reportValidationProblem(String paramString) throws XMLStreamException;
  
  void reportValidationProblem(String paramString, Object paramObject1, Object paramObject2) throws XMLStreamException;
  
  void reportProblem(Location paramLocation, String paramString1, String paramString2, Object paramObject1, Object paramObject2) throws XMLStreamException;
  
  Location getLocation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\InputProblemReporter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */