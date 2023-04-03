package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xs.datatypes.XSFloat;

public class FloatDV extends TypeValidator {
  public short getAllowedFacets() {
    return 2552;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return new XFloat(paramString);
    } catch (NumberFormatException numberFormatException) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "float" });
    } 
  }
  
  public int compare(Object paramObject1, Object paramObject2) {
    return ((XFloat)paramObject1).compareTo((XFloat)paramObject2);
  }
  
  public boolean isIdentical(Object paramObject1, Object paramObject2) {
    return (paramObject2 instanceof XFloat) ? ((XFloat)paramObject1).isIdentical((XFloat)paramObject2) : false;
  }
  
  private static final class XFloat implements XSFloat {
    private final float value;
    
    private String canonical;
    
    public XFloat(String param1String) throws NumberFormatException {
      if (DoubleDV.isPossibleFP(param1String)) {
        this.value = Float.parseFloat(param1String);
      } else if (param1String.equals("INF")) {
        this.value = Float.POSITIVE_INFINITY;
      } else if (param1String.equals("-INF")) {
        this.value = Float.NEGATIVE_INFINITY;
      } else if (param1String.equals("NaN")) {
        this.value = Float.NaN;
      } else {
        throw new NumberFormatException(param1String);
      } 
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof XFloat))
        return false; 
      XFloat xFloat = (XFloat)param1Object;
      return (this.value == xFloat.value) ? true : ((this.value != this.value && xFloat.value != xFloat.value));
    }
    
    public int hashCode() {
      return (this.value == 0.0F) ? 0 : Float.floatToIntBits(this.value);
    }
    
    public boolean isIdentical(XFloat param1XFloat) {
      return (param1XFloat == this) ? true : ((this.value == param1XFloat.value) ? ((this.value != 0.0F || Float.floatToIntBits(this.value) == Float.floatToIntBits(param1XFloat.value))) : ((this.value != this.value && param1XFloat.value != param1XFloat.value)));
    }
    
    private int compareTo(XFloat param1XFloat) {
      float f = param1XFloat.value;
      return (this.value < f) ? -1 : ((this.value > f) ? 1 : ((this.value == f) ? 0 : ((this.value != this.value) ? ((f != f) ? 0 : 2) : 2)));
    }
    
    public synchronized String toString() {
      if (this.canonical == null)
        if (this.value == Float.POSITIVE_INFINITY) {
          this.canonical = "INF";
        } else if (this.value == Float.NEGATIVE_INFINITY) {
          this.canonical = "-INF";
        } else if (this.value != this.value) {
          this.canonical = "NaN";
        } else if (this.value == 0.0F) {
          this.canonical = "0.0E1";
        } else {
          this.canonical = Float.toString(this.value);
          if (this.canonical.indexOf('E') == -1) {
            int i = this.canonical.length();
            char[] arrayOfChar = new char[i + 3];
            this.canonical.getChars(0, i, arrayOfChar, 0);
            byte b = (arrayOfChar[0] == '-') ? 2 : 1;
            if (this.value >= 1.0F || this.value <= -1.0F) {
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
    
    public float getValue() {
      return this.value;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\FloatDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */