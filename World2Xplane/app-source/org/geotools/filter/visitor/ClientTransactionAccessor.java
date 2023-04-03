package org.geotools.filter.visitor;

import org.opengis.filter.Filter;

public interface ClientTransactionAccessor {
  Filter getDeleteFilter();
  
  Filter getUpdateFilter(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\ClientTransactionAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */