package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

class PrecisionDecimalDV extends TypeValidator {
  public short getAllowedFacets() {
    return 4088;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return new XPrecisionDecimal(paramString);
    } catch (NumberFormatException numberFormatException) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "precisionDecimal" });
    } 
  }
  
  public int compare(Object paramObject1, Object paramObject2) {
    return ((XPrecisionDecimal)paramObject1).compareTo((XPrecisionDecimal)paramObject2);
  }
  
  public int getFractionDigits(Object paramObject) {
    return ((XPrecisionDecimal)paramObject).fracDigits;
  }
  
  public int getTotalDigits(Object paramObject) {
    return ((XPrecisionDecimal)paramObject).totalDigits;
  }
  
  public boolean isIdentical(Object paramObject1, Object paramObject2) {
    return (!(paramObject2 instanceof XPrecisionDecimal) || !(paramObject1 instanceof XPrecisionDecimal)) ? false : ((XPrecisionDecimal)paramObject1).isIdentical((XPrecisionDecimal)paramObject2);
  }
  
  static class XPrecisionDecimal {
    int sign = 1;
    
    int totalDigits = 0;
    
    int intDigits = 0;
    
    int fracDigits = 0;
    
    String ivalue = "";
    
    String fvalue = "";
    
    int pvalue = 0;
    
    private String canonical;
    
    XPrecisionDecimal(String param1String) throws NumberFormatException {
      if (param1String.equals("NaN")) {
        this.ivalue = param1String;
        this.sign = 0;
      } 
      if (param1String.equals("+INF") || param1String.equals("INF") || param1String.equals("-INF")) {
        this.ivalue = (param1String.charAt(0) == '+') ? param1String.substring(1) : param1String;
        return;
      } 
      initD(param1String);
    }
    
    void initD(String param1String) throws NumberFormatException {
      int i = param1String.length();
      if (i == 0)
        throw new NumberFormatException(); 
      byte b1 = 0;
      byte b2 = 0;
      int j = 0;
      int k = 0;
      if (param1String.charAt(0) == '+') {
        b1 = 1;
      } else if (param1String.charAt(0) == '-') {
        b1 = 1;
        this.sign = -1;
      } 
      byte b3;
      for (b3 = b1; b3 < i && param1String.charAt(b3) == '0'; b3++);
      for (b2 = b3; b2 < i && TypeValidator.isDigit(param1String.charAt(b2)); b2++);
      if (b2 < i) {
        if (param1String.charAt(b2) != '.' && param1String.charAt(b2) != 'E' && param1String.charAt(b2) != 'e')
          throw new NumberFormatException(); 
        if (param1String.charAt(b2) == '.') {
          j = b2 + 1;
          for (k = j; k < i && TypeValidator.isDigit(param1String.charAt(k)); k++);
        } else {
          this.pvalue = Integer.parseInt(param1String.substring(b2 + 1, i));
        } 
      } 
      if (b1 == b2 && j == k)
        throw new NumberFormatException(); 
      for (int m = j; m < k; m++) {
        if (!TypeValidator.isDigit(param1String.charAt(m)))
          throw new NumberFormatException(); 
      } 
      this.intDigits = b2 - b3;
      this.fracDigits = k - j;
      if (this.intDigits > 0)
        this.ivalue = param1String.substring(b3, b2); 
      if (this.fracDigits > 0) {
        this.fvalue = param1String.substring(j, k);
        if (k < i)
          this.pvalue = Integer.parseInt(param1String.substring(k + 1, i)); 
      } 
      this.totalDigits = this.intDigits + this.fracDigits;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof XPrecisionDecimal))
        return false; 
      XPrecisionDecimal xPrecisionDecimal = (XPrecisionDecimal)param1Object;
      return (compareTo(xPrecisionDecimal) == 0);
    }
    
    private int compareFractionalPart(XPrecisionDecimal param1XPrecisionDecimal) {
      if (this.fvalue.equals(param1XPrecisionDecimal.fvalue))
        return 0; 
      StringBuffer stringBuffer1 = new StringBuffer(this.fvalue);
      StringBuffer stringBuffer2 = new StringBuffer(param1XPrecisionDecimal.fvalue);
      truncateTrailingZeros(stringBuffer1, stringBuffer2);
      return stringBuffer1.toString().compareTo(stringBuffer2.toString());
    }
    
    private void truncateTrailingZeros(StringBuffer param1StringBuffer1, StringBuffer param1StringBuffer2) {
      for (int i = param1StringBuffer1.length() - 1; i >= 0 && param1StringBuffer1.charAt(i) == '0'; i--)
        param1StringBuffer1.deleteCharAt(i); 
      for (int j = param1StringBuffer2.length() - 1; j >= 0 && param1StringBuffer2.charAt(j) == '0'; j--)
        param1StringBuffer2.deleteCharAt(j); 
    }
    
    public int compareTo(XPrecisionDecimal param1XPrecisionDecimal) {
      return (this.sign == 0) ? 2 : ((this.ivalue.equals("INF") || param1XPrecisionDecimal.ivalue.equals("INF")) ? (this.ivalue.equals(param1XPrecisionDecimal.ivalue) ? 0 : (this.ivalue.equals("INF") ? 1 : -1)) : ((this.ivalue.equals("-INF") || param1XPrecisionDecimal.ivalue.equals("-INF")) ? (this.ivalue.equals(param1XPrecisionDecimal.ivalue) ? 0 : (this.ivalue.equals("-INF") ? -1 : 1)) : ((this.sign != param1XPrecisionDecimal.sign) ? ((this.sign > param1XPrecisionDecimal.sign) ? 1 : -1) : (this.sign * compare(param1XPrecisionDecimal)))));
    }
    
    private int compare(XPrecisionDecimal param1XPrecisionDecimal) {
      if (this.pvalue != 0 || param1XPrecisionDecimal.pvalue != 0) {
        if (this.pvalue == param1XPrecisionDecimal.pvalue)
          return intComp(param1XPrecisionDecimal); 
        if (this.intDigits + this.pvalue != param1XPrecisionDecimal.intDigits + param1XPrecisionDecimal.pvalue)
          return (this.intDigits + this.pvalue > param1XPrecisionDecimal.intDigits + param1XPrecisionDecimal.pvalue) ? 1 : -1; 
        if (this.pvalue > param1XPrecisionDecimal.pvalue) {
          int j = this.pvalue - param1XPrecisionDecimal.pvalue;
          StringBuffer stringBuffer3 = new StringBuffer(this.ivalue);
          StringBuffer stringBuffer4 = new StringBuffer(this.fvalue);
          for (byte b1 = 0; b1 < j; b1++) {
            if (b1 < this.fracDigits) {
              stringBuffer3.append(this.fvalue.charAt(b1));
              stringBuffer4.deleteCharAt(b1);
            } else {
              stringBuffer3.append('0');
            } 
          } 
          return compareDecimal(stringBuffer3.toString(), param1XPrecisionDecimal.ivalue, stringBuffer4.toString(), param1XPrecisionDecimal.fvalue);
        } 
        int i = param1XPrecisionDecimal.pvalue - this.pvalue;
        StringBuffer stringBuffer1 = new StringBuffer(param1XPrecisionDecimal.ivalue);
        StringBuffer stringBuffer2 = new StringBuffer(param1XPrecisionDecimal.fvalue);
        for (byte b = 0; b < i; b++) {
          if (b < param1XPrecisionDecimal.fracDigits) {
            stringBuffer1.append(param1XPrecisionDecimal.fvalue.charAt(b));
            stringBuffer2.deleteCharAt(b);
          } else {
            stringBuffer1.append('0');
          } 
        } 
        return compareDecimal(this.ivalue, stringBuffer1.toString(), this.fvalue, stringBuffer2.toString());
      } 
      return intComp(param1XPrecisionDecimal);
    }
    
    private int intComp(XPrecisionDecimal param1XPrecisionDecimal) {
      return (this.intDigits != param1XPrecisionDecimal.intDigits) ? ((this.intDigits > param1XPrecisionDecimal.intDigits) ? 1 : -1) : compareDecimal(this.ivalue, param1XPrecisionDecimal.ivalue, this.fvalue, param1XPrecisionDecimal.fvalue);
    }
    
    private int compareDecimal(String param1String1, String param1String2, String param1String3, String param1String4) {
      int i = param1String1.compareTo(param1String3);
      if (i != 0)
        return (i > 0) ? 1 : -1; 
      if (param1String2.equals(param1String4))
        return 0; 
      StringBuffer stringBuffer1 = new StringBuffer(param1String2);
      StringBuffer stringBuffer2 = new StringBuffer(param1String4);
      truncateTrailingZeros(stringBuffer1, stringBuffer2);
      i = stringBuffer1.toString().compareTo(stringBuffer2.toString());
      return (i == 0) ? 0 : ((i > 0) ? 1 : -1);
    }
    
    public synchronized String toString() {
      if (this.canonical == null)
        makeCanonical(); 
      return this.canonical;
    }
    
    private void makeCanonical() {
      this.canonical = "TBD by Working Group";
    }
    
    public boolean isIdentical(XPrecisionDecimal param1XPrecisionDecimal) {
      return (this.ivalue.equals(param1XPrecisionDecimal.ivalue) && (this.ivalue.equals("INF") || this.ivalue.equals("-INF") || this.ivalue.equals("NaN"))) ? true : ((this.sign == param1XPrecisionDecimal.sign && this.intDigits == param1XPrecisionDecimal.intDigits && this.fracDigits == param1XPrecisionDecimal.fracDigits && this.pvalue == param1XPrecisionDecimal.pvalue && this.ivalue.equals(param1XPrecisionDecimal.ivalue) && this.fvalue.equals(param1XPrecisionDecimal.fvalue)));
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\PrecisionDecimalDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */