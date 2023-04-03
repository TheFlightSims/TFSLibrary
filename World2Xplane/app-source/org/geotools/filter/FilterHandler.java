package org.geotools.filter;

import org.opengis.filter.Filter;
import org.xml.sax.ContentHandler;

public interface FilterHandler extends ContentHandler {
  void filter(Filter paramFilter);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */