package org.opengis.filter.identity;

public interface Identifier {
  Object getID();
  
  boolean matches(Object paramObject);
  
  boolean equals(Object paramObject);
  
  int hashCode();
  
  String toString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\identity\Identifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */