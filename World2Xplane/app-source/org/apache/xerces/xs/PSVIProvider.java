package org.apache.xerces.xs;

public interface PSVIProvider {
  ElementPSVI getElementPSVI();
  
  AttributePSVI getAttributePSVI(int paramInt);
  
  AttributePSVI getAttributePSVIByName(String paramString1, String paramString2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\PSVIProvider.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */