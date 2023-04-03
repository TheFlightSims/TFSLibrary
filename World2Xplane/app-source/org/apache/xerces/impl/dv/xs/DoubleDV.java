package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xs.datatypes.XSDouble;

public class DoubleDV extends TypeValidator {
  public short getAllowedFacets() {
    return 2552;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return new XDouble(paramString);
    } catch (NumberFormatException numberFormatException) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "double" });
    } 
  }
  
  public int compare(Object paramObject1, Object paramObject2) {
    return ((XDouble)paramObject1).compareTo((XDouble)paramObject2);
  }
  
  public boolean isIdentical(Object paramObject1, Object paramObject2) {
    return (paramObject2 instanceof XDouble) ? ((XDouble)paramObject1).isIdentical((XDouble)paramObject2) : false;
  }
  
  static boolean isPossibleFP(String paramString) {
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+' && c != 'E' && c != 'e')
        return false; 
    } 
    return true;
  }
  
  private static final class XDouble implements XSDouble {
    private final double value;
    
    private String canonical;
    
    public XDouble(String param1String) throws NumberFormatException {
      if (DoubleDV.isPossibleFP(param1String)) {
        this.value = Double.parseDouble(param1String);
      } else if (param1String.equals("INF")) {
        this.value = Double.POSITIVE_INFINITY;
      } else if (param1String.equals("-INF")) {
        this.value = Double.NEGATIVE_INFINITY;
      } else if (param1String.equals("NaN")) {
        this.value = Double.NaN;
      } else {
        throw new NumberFormatException(param1String);
      } 
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof XDouble))
        return false; 
      XDouble xDouble = (XDouble)param1Object;
      return (this.value == xDouble.value) ? true : ((this.value != this.value && xDouble.value != xDouble.value));
    }
    
    public int hashCode() {
      if (this.value == 0.0D)
        return 0; 
      long l = Double.doubleToLongBits(this.value);
      return (int)(l ^ l >>> 32L);
    }
    
    public boolean isIdentical(XDouble param1XDouble) {
      return (param1XDouble == this) ? true : ((this.value == param1XDouble.value) ? ((this.value != 0.0D || Double.doubleToLongBits(this.value) == Double.doubleToLongBits(param1XDouble.value))) : ((this.value != this.value && param1XDouble.value != param1XDouble.value)));
    }
    
    private int compareTo(XDouble param1XDouble) {
      double d = param1XDouble.value;
      return (this.value < d) ? -1 : ((this.value > d) ? 1 : ((this.value == d) ? 0 : ((this.value != this.value) ? ((d != d) ? 0 : 2) : 2)));
    }
    
    public synchronized String toString() {
      if (this.canonical == null)
        if (this.value == Double.POSITIVE_INFINITY) {
          this.canonical = "INF";
        } else if (this.value == Double.NEGATIVE_INFINITY) {
          this.canonical = "-INF";
        } else if (this.value != this.value) {
          this.canonical = "NaN";
        } else if (this.value == 0.0D) {
          this.canonical = "0.0E1";
        } else {
          this.canonical = Double.toString(this.value);
          if (this.canonical.indexOf('E') == -1) {
            int i = this.canonical.length();
            char[] arrayOfChar = new char[i + 3];
            this.canonical.getChars(0, i, arrayOfChar, 0);
            byte b = (arrayOfChar[0] == '-') ? 2 : 1;
            if (this.value >= 1.0D || this.value <= -1.0D) {
              int j = this.canonical.indexOf('.');
              for (int k = j; k > b; k--)
                arrayOfChar[k] = arrayOfChar[k - 1]; 
              arrayOfChar[b] = '.';
              while (arrayOfChar[i - 1] == '0')
                i--; 
              if (arrayOfChar[i - 1] == '.')
                i++; 
              arrayOfChar[i++] = 'E';
              int m = j - b;
              arrayOfChar[i++] = (char)(m + 48);
            } else {
              int j;
              for (j = b + 1; arrayOfChar[j] == '0'; j++);
              arrayOfChar[b - 1] = arrayOfChar[j];
              arrayOfChar[b] = '.';
              int k = j + 1;
              for (int m = b + 1; k < i; m++) {
                arrayOfChar[m] = arrayOfChar[k];
                k++;
              } 
              i -= j - b;
              if (i == b + 1)
                arrayOfChar[i++] = '0'; 
              arrayOfChar[i++] = 'E';
              arrayOfChar[i++] = '-';
              int n = j - b;
              arrayOfChar[i++] = (char)(n + 48);
            } 
            this.canonical = new String(arrayOfChar, 0, i);
          } 
        }  
      return this.canonical;
    }
    
    public double getValue() {
      return this.value;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\DoubleDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */