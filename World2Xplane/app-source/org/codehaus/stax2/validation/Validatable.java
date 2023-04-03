package org.codehaus.stax2.validation;

import javax.xml.stream.XMLStreamException;

public interface Validatable {
  XMLValidator validateAgainst(XMLValidationSchema paramXMLValidationSchema) throws XMLStreamException;
  
  XMLValidator stopValidatingAgainst(XMLValidationSchema paramXMLValidationSchema) throws XMLStreamException;
  
  XMLValidator stopValidatingAgainst(XMLValidator paramXMLValidator) throws XMLStreamException;
  
  ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler paramValidationProblemHandler);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\Validatable.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */