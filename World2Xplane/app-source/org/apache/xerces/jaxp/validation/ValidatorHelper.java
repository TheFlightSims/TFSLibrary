package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.xml.sax.SAXException;

interface ValidatorHelper {
  void validate(Source paramSource, Result paramResult) throws SAXException, IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\ValidatorHelper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */