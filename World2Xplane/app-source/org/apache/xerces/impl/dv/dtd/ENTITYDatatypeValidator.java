package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.DatatypeValidator;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class ENTITYDatatypeValidator implements DatatypeValidator {
  public void validate(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    if (!paramValidationContext.isEntityUnparsed(paramString))
      throw new InvalidDatatypeValueException("ENTITYNotUnparsed", new Object[] { paramString }); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\dtd\ENTITYDatatypeValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */