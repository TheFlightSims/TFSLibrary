package org.postgresql.core;

public interface Query {
  ParameterList createParameterList();
  
  String toString(ParameterList paramParameterList);
  
  void close();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */