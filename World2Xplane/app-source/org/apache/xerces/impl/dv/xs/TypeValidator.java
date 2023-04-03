package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public abstract class TypeValidator {
  public static final short LESS_THAN = -1;
  
  public static final short EQUAL = 0;
  
  public static final short GREATER_THAN = 1;
  
  public static final short INDETERMINATE = 2;
  
  public abstract short getAllowedFacets();
  
  public abstract Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException;
  
  public void checkExtraRules(Object paramObject, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {}
  
  public boolean isIdentical(Object paramObject1, Object paramObject2) {
    return paramObject1.equals(paramObject2);
  }
  
  public int compare(Object paramObject1, Object paramObject2) {
    return -1;
  }
  
  public int getDataLength(Object paramObject) {
    return (paramObject instanceof String) ? ((String)paramObject).length() : -1;
  }
  
  public int getTotalDigits(Object paramObject) {
    return -1;
  }
  
  public int getFractionDigits(Object paramObject) {
    return -1;
  }
  
  public static final boolean isDigit(char paramChar) {
    return (paramChar >= '0' && paramChar <= '9');
  }
  
  public static final int getDigit(char paramChar) {
    return isDigit(paramChar) ? (paramChar - 48) : -1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\TypeValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */