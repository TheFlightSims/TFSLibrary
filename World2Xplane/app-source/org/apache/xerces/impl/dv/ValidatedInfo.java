package org.apache.xerces.impl.dv;

import org.apache.xerces.xs.ShortList;

public class ValidatedInfo {
  public String normalizedValue;
  
  public Object actualValue;
  
  public short actualValueType;
  
  public XSSimpleType memberType;
  
  public XSSimpleType[] memberTypes;
  
  public ShortList itemValueTypes;
  
  public void reset() {
    this.normalizedValue = null;
    this.actualValue = null;
    this.memberType = null;
    this.memberTypes = null;
  }
  
  public String stringValue() {
    return (this.actualValue == null) ? this.normalizedValue : this.actualValue.toString();
  }
  
  public static boolean isComparable(ValidatedInfo paramValidatedInfo1, ValidatedInfo paramValidatedInfo2) {
    short s1 = convertToPrimitiveKind(paramValidatedInfo1.actualValueType);
    short s2 = convertToPrimitiveKind(paramValidatedInfo2.actualValueType);
    if (s1 != s2)
      return ((s1 == 1 && s2 == 2) || (s1 == 2 && s2 == 1)); 
    if (s1 == 44 || s1 == 43) {
      ShortList shortList1 = paramValidatedInfo1.itemValueTypes;
      ShortList shortList2 = paramValidatedInfo2.itemValueTypes;
      byte b1 = (shortList1 != null) ? shortList1.getLength() : 0;
      byte b2 = (shortList2 != null) ? shortList2.getLength() : 0;
      if (b1 != b2)
        return false; 
      byte b3 = 0;
      while (b3 < b1) {
        short s3 = convertToPrimitiveKind(shortList1.item(b3));
        short s4 = convertToPrimitiveKind(shortList2.item(b3));
        if (s3 == s4 || (s3 == 1 && s4 == 2) || (s3 == 2 && s4 == 1)) {
          b3++;
          continue;
        } 
        return false;
      } 
    } 
    return true;
  }
  
  private static short convertToPrimitiveKind(short paramShort) {
    return (paramShort <= 20) ? paramShort : ((paramShort <= 29) ? 2 : ((paramShort <= 42) ? 4 : paramShort));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\ValidatedInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */