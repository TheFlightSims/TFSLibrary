package org.geotools.filter;

public interface ExpressionType {
  public static final short LITERAL_UNDECLARED = 115;
  
  public static final short LITERAL_DOUBLE = 101;
  
  public static final short LITERAL_INTEGER = 102;
  
  public static final short LITERAL_STRING = 103;
  
  public static final short LITERAL_GEOMETRY = 104;
  
  public static final short LITERAL_LONG = 99;
  
  public static final short MATH_ADD = 105;
  
  public static final short MATH_SUBTRACT = 106;
  
  public static final short MATH_MULTIPLY = 107;
  
  public static final short MATH_DIVIDE = 108;
  
  public static final short ATTRIBUTE_DOUBLE = 109;
  
  public static final short ATTRIBUTE_INTEGER = 110;
  
  public static final short ATTRIBUTE_STRING = 111;
  
  public static final short ATTRIBUTE_GEOMETRY = 112;
  
  public static final short ATTRIBUTE_UNDECLARED = 115;
  
  public static final short ATTRIBUTE = 113;
  
  public static final short FUNCTION = 114;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\ExpressionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */