package org.geotools.filter;

public interface FilterType {
  public static final short LOGIC_OR = 1;
  
  public static final short LOGIC_AND = 2;
  
  public static final short LOGIC_NOT = 3;
  
  public static final short GEOMETRY_BBOX = 4;
  
  public static final short GEOMETRY_EQUALS = 5;
  
  public static final short GEOMETRY_DISJOINT = 6;
  
  public static final short GEOMETRY_INTERSECTS = 7;
  
  public static final short GEOMETRY_TOUCHES = 8;
  
  public static final short GEOMETRY_CROSSES = 9;
  
  public static final short GEOMETRY_WITHIN = 10;
  
  public static final short GEOMETRY_CONTAINS = 11;
  
  public static final short GEOMETRY_OVERLAPS = 12;
  
  public static final short GEOMETRY_BEYOND = 13;
  
  public static final short GEOMETRY_DWITHIN = 24;
  
  public static final short COMPARE_EQUALS = 14;
  
  public static final short COMPARE_LESS_THAN = 15;
  
  public static final short COMPARE_GREATER_THAN = 16;
  
  public static final short COMPARE_LESS_THAN_EQUAL = 17;
  
  public static final short COMPARE_GREATER_THAN_EQUAL = 18;
  
  public static final short COMPARE_NOT_EQUALS = 23;
  
  public static final short BETWEEN = 19;
  
  public static final short NULL = 21;
  
  public static final short LIKE = 20;
  
  public static final short FID = 22;
  
  public static final short NONE = 12345;
  
  public static final short ALL = -12345;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */