package org.codehaus.stax2;

import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.validation.XMLValidationProblem;

public interface XMLReporter2 extends XMLReporter {
  void report(XMLValidationProblem paramXMLValidationProblem) throws XMLStreamException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\XMLReporter2.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */