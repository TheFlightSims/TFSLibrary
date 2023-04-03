package org.geotools.filter;

import java.util.Iterator;
import java.util.List;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;

public interface LogicFilter extends Filter {
  boolean contains(SimpleFeature paramSimpleFeature);
  
  List getChildren();
  
  Iterator getFilterIterator();
  
  void addFilter(Filter paramFilter) throws IllegalFilterException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LogicFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */