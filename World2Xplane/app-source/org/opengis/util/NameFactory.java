package org.opengis.util;

import java.util.Locale;
import java.util.Map;

public interface NameFactory {
  InternationalString createInternationalString(Map<Locale, String> paramMap);
  
  NameSpace createNameSpace(GenericName paramGenericName, String paramString1, String paramString2);
  
  LocalName createLocalName(NameSpace paramNameSpace, CharSequence paramCharSequence);
  
  GenericName createGenericName(NameSpace paramNameSpace, CharSequence[] paramArrayOfCharSequence);
  
  GenericName parseGenericName(NameSpace paramNameSpace, CharSequence paramCharSequence);
  
  @Deprecated
  LocalName createLocalName(GenericName paramGenericName, String paramString, InternationalString paramInternationalString);
  
  @Deprecated
  ScopedName createScopedName(GenericName paramGenericName, String paramString, InternationalString paramInternationalString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\NameFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */