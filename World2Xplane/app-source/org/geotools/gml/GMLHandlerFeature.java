package org.geotools.gml;

import org.opengis.feature.simple.SimpleFeature;
import org.xml.sax.ContentHandler;

public interface GMLHandlerFeature extends ContentHandler {
  void feature(SimpleFeature paramSimpleFeature);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLHandlerFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */