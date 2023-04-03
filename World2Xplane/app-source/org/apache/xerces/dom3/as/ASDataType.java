package org.apache.xerces.dom3.as;

public interface ASDataType {
  public static final short STRING_DATATYPE = 1;
  
  public static final short NOTATION_DATATYPE = 10;
  
  public static final short ID_DATATYPE = 11;
  
  public static final short IDREF_DATATYPE = 12;
  
  public static final short IDREFS_DATATYPE = 13;
  
  public static final short ENTITY_DATATYPE = 14;
  
  public static final short ENTITIES_DATATYPE = 15;
  
  public static final short NMTOKEN_DATATYPE = 16;
  
  public static final short NMTOKENS_DATATYPE = 17;
  
  public static final short BOOLEAN_DATATYPE = 100;
  
  public static final short FLOAT_DATATYPE = 101;
  
  public static final short DOUBLE_DATATYPE = 102;
  
  public static final short DECIMAL_DATATYPE = 103;
  
  public static final short HEXBINARY_DATATYPE = 104;
  
  public static final short BASE64BINARY_DATATYPE = 105;
  
  public static final short ANYURI_DATATYPE = 106;
  
  public static final short QNAME_DATATYPE = 107;
  
  public static final short DURATION_DATATYPE = 108;
  
  public static final short DATETIME_DATATYPE = 109;
  
  public static final short DATE_DATATYPE = 110;
  
  public static final short TIME_DATATYPE = 111;
  
  public static final short GYEARMONTH_DATATYPE = 112;
  
  public static final short GYEAR_DATATYPE = 113;
  
  public static final short GMONTHDAY_DATATYPE = 114;
  
  public static final short GDAY_DATATYPE = 115;
  
  public static final short GMONTH_DATATYPE = 116;
  
  public static final short INTEGER = 117;
  
  public static final short NAME_DATATYPE = 200;
  
  public static final short NCNAME_DATATYPE = 201;
  
  public static final short NORMALIZEDSTRING_DATATYPE = 202;
  
  public static final short TOKEN_DATATYPE = 203;
  
  public static final short LANGUAGE_DATATYPE = 204;
  
  public static final short NONPOSITIVEINTEGER_DATATYPE = 205;
  
  public static final short NEGATIVEINTEGER_DATATYPE = 206;
  
  public static final short LONG_DATATYPE = 207;
  
  public static final short INT_DATATYPE = 208;
  
  public static final short SHORT_DATATYPE = 209;
  
  public static final short BYTE_DATATYPE = 210;
  
  public static final short NONNEGATIVEINTEGER_DATATYPE = 211;
  
  public static final short UNSIGNEDLONG_DATATYPE = 212;
  
  public static final short UNSIGNEDINT_DATATYPE = 213;
  
  public static final short UNSIGNEDSHORT_DATATYPE = 214;
  
  public static final short UNSIGNEDBYTE_DATATYPE = 215;
  
  public static final short POSITIVEINTEGER_DATATYPE = 216;
  
  public static final short OTHER_SIMPLE_DATATYPE = 1000;
  
  public static final short COMPLEX_DATATYPE = 1001;
  
  short getDataType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\ASDataType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */