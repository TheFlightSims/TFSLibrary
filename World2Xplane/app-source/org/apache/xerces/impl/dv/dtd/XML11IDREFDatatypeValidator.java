package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.XML11Char;

public class XML11IDREFDatatypeValidator extends IDREFDatatypeValidator {
  public void validate(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    if (paramValidationContext.useNamespaces()) {
      if (!XML11Char.isXML11ValidNCName(paramString))
        throw new InvalidDatatypeValueException("IDREFInvalidWithNamespaces", new Object[] { paramString }); 
    } else if (!XML11Char.isXML11ValidName(paramString)) {
      throw new InvalidDatatypeValueException("IDREFInvalid", new Object[] { paramString });
    } 
    paramValidationContext.addIdRef(paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\dtd\XML11IDREFDatatypeValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */