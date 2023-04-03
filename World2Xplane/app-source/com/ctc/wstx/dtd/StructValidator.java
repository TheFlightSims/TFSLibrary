package com.ctc.wstx.dtd;

import com.ctc.wstx.util.PrefixedName;

public abstract class StructValidator {
  public abstract StructValidator newInstance();
  
  public abstract String tryToValidate(PrefixedName paramPrefixedName);
  
  public abstract String fullyValid();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\StructValidator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */