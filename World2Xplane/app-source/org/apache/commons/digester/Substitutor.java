package org.apache.commons.digester;

import org.xml.sax.Attributes;

public abstract class Substitutor {
  public abstract Attributes substitute(Attributes paramAttributes);
  
  public abstract String substitute(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\Substitutor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */