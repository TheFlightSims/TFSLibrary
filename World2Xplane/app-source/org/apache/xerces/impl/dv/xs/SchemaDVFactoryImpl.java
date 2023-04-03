package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.SchemaDVFactory;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.XSDeclarationPool;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xs.XSObjectList;

public class SchemaDVFactoryImpl extends SchemaDVFactory {
  static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
  
  static final SymbolHash fBuiltInTypes = new SymbolHash();
  
  protected XSDeclarationPool fDeclPool = null;
  
  public XSSimpleType getBuiltInType(String paramString) {
    return (XSSimpleType)fBuiltInTypes.get(paramString);
  }
  
  public SymbolHash getBuiltInTypes() {
    return fBuiltInTypes.makeClone();
  }
  
  public XSSimpleType createTypeRestriction(String paramString1, String paramString2, short paramShort, XSSimpleType paramXSSimpleType, XSObjectList paramXSObjectList) {
    if (this.fDeclPool != null) {
      XSSimpleTypeDecl xSSimpleTypeDecl = this.fDeclPool.getSimpleTypeDecl();
      return xSSimpleTypeDecl.setRestrictionValues((XSSimpleTypeDecl)paramXSSimpleType, paramString1, paramString2, paramShort, paramXSObjectList);
    } 
    return new XSSimpleTypeDecl((XSSimpleTypeDecl)paramXSSimpleType, paramString1, paramString2, paramShort, false, paramXSObjectList);
  }
  
  public XSSimpleType createTypeList(String paramString1, String paramString2, short paramShort, XSSimpleType paramXSSimpleType, XSObjectList paramXSObjectList) {
    if (this.fDeclPool != null) {
      XSSimpleTypeDecl xSSimpleTypeDecl = this.fDeclPool.getSimpleTypeDecl();
      return xSSimpleTypeDecl.setListValues(paramString1, paramString2, paramShort, (XSSimpleTypeDecl)paramXSSimpleType, paramXSObjectList);
    } 
    return new XSSimpleTypeDecl(paramString1, paramString2, paramShort, (XSSimpleTypeDecl)paramXSSimpleType, false, paramXSObjectList);
  }
  
  public XSSimpleType createTypeUnion(String paramString1, String paramString2, short paramShort, XSSimpleType[] paramArrayOfXSSimpleType, XSObjectList paramXSObjectList) {
    int i = paramArrayOfXSSimpleType.length;
    XSSimpleTypeDecl[] arrayOfXSSimpleTypeDecl = new XSSimpleTypeDecl[i];
    System.arraycopy(paramArrayOfXSSimpleType, 0, arrayOfXSSimpleTypeDecl, 0, i);
    if (this.fDeclPool != null) {
      XSSimpleTypeDecl xSSimpleTypeDecl = this.fDeclPool.getSimpleTypeDecl();
      return xSSimpleTypeDecl.setUnionValues(paramString1, paramString2, paramShort, arrayOfXSSimpleTypeDecl, paramXSObjectList);
    } 
    return new XSSimpleTypeDecl(paramString1, paramString2, paramShort, arrayOfXSSimpleTypeDecl, paramXSObjectList);
  }
  
  static void createBuiltInTypes() {
    XSFacets xSFacets = new XSFacets();
    XSSimpleTypeDecl xSSimpleTypeDecl1 = XSSimpleTypeDecl.fAnySimpleType;
    XSSimpleTypeDecl xSSimpleTypeDecl2 = XSSimpleTypeDecl.fAnyAtomicType;
    XSSimpleTypeDecl xSSimpleTypeDecl3 = null;
    xSSimpleTypeDecl3 = xSSimpleTypeDecl1;
    fBuiltInTypes.put("anySimpleType", xSSimpleTypeDecl1);
    XSSimpleTypeDecl xSSimpleTypeDecl4 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, "string", (short)1, (short)0, false, false, false, true, (short)2);
    fBuiltInTypes.put("string", xSSimpleTypeDecl4);
    fBuiltInTypes.put("boolean", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "boolean", (short)2, (short)0, false, true, false, true, (short)3));
    XSSimpleTypeDecl xSSimpleTypeDecl5 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, "decimal", (short)3, (short)2, false, false, true, true, (short)4);
    fBuiltInTypes.put("decimal", xSSimpleTypeDecl5);
    fBuiltInTypes.put("anyURI", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "anyURI", (short)17, (short)0, false, false, false, true, (short)18));
    fBuiltInTypes.put("base64Binary", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "base64Binary", (short)16, (short)0, false, false, false, true, (short)17));
    XSSimpleTypeDecl xSSimpleTypeDecl6 = new XSSimpleTypeDecl(xSSimpleTypeDecl3, "duration", (short)6, (short)1, false, false, false, true, (short)7);
    fBuiltInTypes.put("duration", xSSimpleTypeDecl6);
    fBuiltInTypes.put("dateTime", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "dateTime", (short)7, (short)1, false, false, false, true, (short)8));
    fBuiltInTypes.put("time", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "time", (short)8, (short)1, false, false, false, true, (short)9));
    fBuiltInTypes.put("date", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "date", (short)9, (short)1, false, false, false, true, (short)10));
    fBuiltInTypes.put("gYearMonth", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "gYearMonth", (short)10, (short)1, false, false, false, true, (short)11));
    fBuiltInTypes.put("gYear", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "gYear", (short)11, (short)1, false, false, false, true, (short)12));
    fBuiltInTypes.put("gMonthDay", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "gMonthDay", (short)12, (short)1, false, false, false, true, (short)13));
    fBuiltInTypes.put("gDay", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "gDay", (short)13, (short)1, false, false, false, true, (short)14));
    fBuiltInTypes.put("gMonth", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "gMonth", (short)14, (short)1, false, false, false, true, (short)15));
    XSSimpleTypeDecl xSSimpleTypeDecl7 = new XSSimpleTypeDecl(xSSimpleTypeDecl5, "integer", (short)24, (short)2, false, false, true, true, (short)30);
    fBuiltInTypes.put("integer", xSSimpleTypeDecl7);
    xSFacets.maxInclusive = "0";
    XSSimpleTypeDecl xSSimpleTypeDecl8 = new XSSimpleTypeDecl(xSSimpleTypeDecl7, "nonPositiveInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)31);
    xSSimpleTypeDecl8.applyFacets1(xSFacets, (short)32, (short)0);
    fBuiltInTypes.put("nonPositiveInteger", xSSimpleTypeDecl8);
    xSFacets.maxInclusive = "-1";
    XSSimpleTypeDecl xSSimpleTypeDecl9 = new XSSimpleTypeDecl(xSSimpleTypeDecl7, "negativeInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)32);
    xSSimpleTypeDecl9.applyFacets1(xSFacets, (short)32, (short)0);
    fBuiltInTypes.put("negativeInteger", xSSimpleTypeDecl9);
    xSFacets.maxInclusive = "9223372036854775807";
    xSFacets.minInclusive = "-9223372036854775808";
    XSSimpleTypeDecl xSSimpleTypeDecl10 = new XSSimpleTypeDecl(xSSimpleTypeDecl7, "long", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)33);
    xSSimpleTypeDecl10.applyFacets1(xSFacets, (short)288, (short)0);
    fBuiltInTypes.put("long", xSSimpleTypeDecl10);
    xSFacets.maxInclusive = "2147483647";
    xSFacets.minInclusive = "-2147483648";
    XSSimpleTypeDecl xSSimpleTypeDecl11 = new XSSimpleTypeDecl(xSSimpleTypeDecl10, "int", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)34);
    xSSimpleTypeDecl11.applyFacets1(xSFacets, (short)288, (short)0);
    fBuiltInTypes.put("int", xSSimpleTypeDecl11);
    xSFacets.maxInclusive = "32767";
    xSFacets.minInclusive = "-32768";
    XSSimpleTypeDecl xSSimpleTypeDecl12 = new XSSimpleTypeDecl(xSSimpleTypeDecl11, "short", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)35);
    xSSimpleTypeDecl12.applyFacets1(xSFacets, (short)288, (short)0);
    fBuiltInTypes.put("short", xSSimpleTypeDecl12);
    xSFacets.maxInclusive = "127";
    xSFacets.minInclusive = "-128";
    XSSimpleTypeDecl xSSimpleTypeDecl13 = new XSSimpleTypeDecl(xSSimpleTypeDecl12, "byte", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)36);
    xSSimpleTypeDecl13.applyFacets1(xSFacets, (short)288, (short)0);
    fBuiltInTypes.put("byte", xSSimpleTypeDecl13);
    xSFacets.minInclusive = "0";
    XSSimpleTypeDecl xSSimpleTypeDecl14 = new XSSimpleTypeDecl(xSSimpleTypeDecl7, "nonNegativeInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)37);
    xSSimpleTypeDecl14.applyFacets1(xSFacets, (short)256, (short)0);
    fBuiltInTypes.put("nonNegativeInteger", xSSimpleTypeDecl14);
    xSFacets.maxInclusive = "18446744073709551615";
    XSSimpleTypeDecl xSSimpleTypeDecl15 = new XSSimpleTypeDecl(xSSimpleTypeDecl14, "unsignedLong", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)38);
    xSSimpleTypeDecl15.applyFacets1(xSFacets, (short)32, (short)0);
    fBuiltInTypes.put("unsignedLong", xSSimpleTypeDecl15);
    xSFacets.maxInclusive = "4294967295";
    XSSimpleTypeDecl xSSimpleTypeDecl16 = new XSSimpleTypeDecl(xSSimpleTypeDecl15, "unsignedInt", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)39);
    xSSimpleTypeDecl16.applyFacets1(xSFacets, (short)32, (short)0);
    fBuiltInTypes.put("unsignedInt", xSSimpleTypeDecl16);
    xSFacets.maxInclusive = "65535";
    XSSimpleTypeDecl xSSimpleTypeDecl17 = new XSSimpleTypeDecl(xSSimpleTypeDecl16, "unsignedShort", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)40);
    xSSimpleTypeDecl17.applyFacets1(xSFacets, (short)32, (short)0);
    fBuiltInTypes.put("unsignedShort", xSSimpleTypeDecl17);
    xSFacets.maxInclusive = "255";
    XSSimpleTypeDecl xSSimpleTypeDecl18 = new XSSimpleTypeDecl(xSSimpleTypeDecl17, "unsignedByte", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)41);
    xSSimpleTypeDecl18.applyFacets1(xSFacets, (short)32, (short)0);
    fBuiltInTypes.put("unsignedByte", xSSimpleTypeDecl18);
    xSFacets.minInclusive = "1";
    XSSimpleTypeDecl xSSimpleTypeDecl19 = new XSSimpleTypeDecl(xSSimpleTypeDecl14, "positiveInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)42);
    xSSimpleTypeDecl19.applyFacets1(xSFacets, (short)256, (short)0);
    fBuiltInTypes.put("positiveInteger", xSSimpleTypeDecl19);
    fBuiltInTypes.put("float", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "float", (short)4, (short)1, true, true, true, true, (short)5));
    fBuiltInTypes.put("double", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "double", (short)5, (short)1, true, true, true, true, (short)6));
    fBuiltInTypes.put("hexBinary", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "hexBinary", (short)15, (short)0, false, false, false, true, (short)16));
    fBuiltInTypes.put("NOTATION", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "NOTATION", (short)20, (short)0, false, false, false, true, (short)20));
    xSFacets.whiteSpace = 1;
    XSSimpleTypeDecl xSSimpleTypeDecl20 = new XSSimpleTypeDecl(xSSimpleTypeDecl4, "normalizedString", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)21);
    xSSimpleTypeDecl20.applyFacets1(xSFacets, (short)16, (short)0);
    fBuiltInTypes.put("normalizedString", xSSimpleTypeDecl20);
    xSFacets.whiteSpace = 2;
    XSSimpleTypeDecl xSSimpleTypeDecl21 = new XSSimpleTypeDecl(xSSimpleTypeDecl20, "token", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)22);
    xSSimpleTypeDecl21.applyFacets1(xSFacets, (short)16, (short)0);
    fBuiltInTypes.put("token", xSSimpleTypeDecl21);
    xSFacets.whiteSpace = 2;
    xSFacets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
    XSSimpleTypeDecl xSSimpleTypeDecl22 = new XSSimpleTypeDecl(xSSimpleTypeDecl21, "language", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)23);
    xSSimpleTypeDecl22.applyFacets1(xSFacets, (short)24, (short)0);
    fBuiltInTypes.put("language", xSSimpleTypeDecl22);
    xSFacets.whiteSpace = 2;
    XSSimpleTypeDecl xSSimpleTypeDecl23 = new XSSimpleTypeDecl(xSSimpleTypeDecl21, "Name", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)25);
    xSSimpleTypeDecl23.applyFacets1(xSFacets, (short)16, (short)0, (short)2);
    fBuiltInTypes.put("Name", xSSimpleTypeDecl23);
    xSFacets.whiteSpace = 2;
    XSSimpleTypeDecl xSSimpleTypeDecl24 = new XSSimpleTypeDecl(xSSimpleTypeDecl23, "NCName", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)26);
    xSSimpleTypeDecl24.applyFacets1(xSFacets, (short)16, (short)0, (short)3);
    fBuiltInTypes.put("NCName", xSSimpleTypeDecl24);
    fBuiltInTypes.put("QName", new XSSimpleTypeDecl(xSSimpleTypeDecl3, "QName", (short)18, (short)0, false, false, false, true, (short)19));
    fBuiltInTypes.put("ID", new XSSimpleTypeDecl(xSSimpleTypeDecl24, "ID", (short)21, (short)0, false, false, false, true, (short)27));
    XSSimpleTypeDecl xSSimpleTypeDecl25 = new XSSimpleTypeDecl(xSSimpleTypeDecl24, "IDREF", (short)22, (short)0, false, false, false, true, (short)28);
    fBuiltInTypes.put("IDREF", xSSimpleTypeDecl25);
    xSFacets.minLength = 1;
    XSSimpleTypeDecl xSSimpleTypeDecl26 = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, xSSimpleTypeDecl25, true, null);
    XSSimpleTypeDecl xSSimpleTypeDecl27 = new XSSimpleTypeDecl(xSSimpleTypeDecl26, "IDREFS", "http://www.w3.org/2001/XMLSchema", (short)0, false, null);
    xSSimpleTypeDecl27.applyFacets1(xSFacets, (short)2, (short)0);
    fBuiltInTypes.put("IDREFS", xSSimpleTypeDecl27);
    XSSimpleTypeDecl xSSimpleTypeDecl28 = new XSSimpleTypeDecl(xSSimpleTypeDecl24, "ENTITY", (short)23, (short)0, false, false, false, true, (short)29);
    fBuiltInTypes.put("ENTITY", xSSimpleTypeDecl28);
    xSFacets.minLength = 1;
    xSSimpleTypeDecl26 = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, xSSimpleTypeDecl28, true, null);
    XSSimpleTypeDecl xSSimpleTypeDecl29 = new XSSimpleTypeDecl(xSSimpleTypeDecl26, "ENTITIES", "http://www.w3.org/2001/XMLSchema", (short)0, false, null);
    xSSimpleTypeDecl29.applyFacets1(xSFacets, (short)2, (short)0);
    fBuiltInTypes.put("ENTITIES", xSSimpleTypeDecl29);
    xSFacets.whiteSpace = 2;
    XSSimpleTypeDecl xSSimpleTypeDecl30 = new XSSimpleTypeDecl(xSSimpleTypeDecl21, "NMTOKEN", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)24);
    xSSimpleTypeDecl30.applyFacets1(xSFacets, (short)16, (short)0, (short)1);
    fBuiltInTypes.put("NMTOKEN", xSSimpleTypeDecl30);
    xSFacets.minLength = 1;
    xSSimpleTypeDecl26 = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, xSSimpleTypeDecl30, true, null);
    XSSimpleTypeDecl xSSimpleTypeDecl31 = new XSSimpleTypeDecl(xSSimpleTypeDecl26, "NMTOKENS", "http://www.w3.org/2001/XMLSchema", (short)0, false, null);
    xSSimpleTypeDecl31.applyFacets1(xSFacets, (short)2, (short)0);
    fBuiltInTypes.put("NMTOKENS", xSSimpleTypeDecl31);
  }
  
  public void setDeclPool(XSDeclarationPool paramXSDeclarationPool) {
    this.fDeclPool = paramXSDeclarationPool;
  }
  
  static {
    createBuiltInTypes();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\SchemaDVFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */