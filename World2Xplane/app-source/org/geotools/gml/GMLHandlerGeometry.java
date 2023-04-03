package org.geotools.gml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface GMLHandlerGeometry extends ContentHandler {
  void geometryStart(String paramString, Attributes paramAttributes) throws SAXException;
  
  void geometryEnd(String paramString) throws SAXException;
  
  void geometrySub(String paramString) throws SAXException;
  
  void gmlCoordinates(double paramDouble1, double paramDouble2) throws SAXException;
  
  void gmlCoordinates(double paramDouble1, double paramDouble2, double paramDouble3) throws SAXException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLHandlerGeometry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */