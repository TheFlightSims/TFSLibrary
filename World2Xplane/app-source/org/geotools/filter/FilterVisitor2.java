package org.geotools.filter;

import org.opengis.filter.ExcludeFilter;
import org.opengis.filter.IncludeFilter;

public interface FilterVisitor2 extends FilterVisitor {
  void visit(IncludeFilter paramIncludeFilter);
  
  void visit(ExcludeFilter paramExcludeFilter);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterVisitor2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */