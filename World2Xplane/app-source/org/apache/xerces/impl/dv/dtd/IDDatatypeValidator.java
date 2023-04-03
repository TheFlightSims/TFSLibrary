package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.DatatypeValidator;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.XMLChar;

public class IDDatatypeValidator implements DatatypeValidator {
  public void validate(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    if (paramValidationContext.useNamespaces()) {
      if (!XMLChar.isValidNCName(paramString))
        throw new InvalidDatatypeValueException("IDInvalidWithNamespaces", new Object[] { paramString }); 
    } else if (!XMLChar.isValidName(paramString)) {
      throw new InvalidDatatypeValueException("IDInvalid", new Object[] { paramString });
    } 
    if (paramValidationContext.isIdDeclared(paramString))
      throw new InvalidDatatypeValueException("IDNotUnique", new Object[] { paramString }); 
    paramValidationContext.addId(paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\dtd\IDDatatypeValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */