package org.geotools.gml;

import com.vividsolutions.jts.geom.Geometry;
import org.xml.sax.ContentHandler;

public interface GMLHandlerJTS extends ContentHandler {
  void geometry(Geometry paramGeometry);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLHandlerJTS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */