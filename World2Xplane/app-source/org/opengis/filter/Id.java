package org.opengis.filter;

import java.util.Set;
import org.opengis.filter.identity.Identifier;

public interface Id extends Filter {
  Set<Object> getIDs();
  
  Set<Identifier> getIdentifiers();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\Id.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */