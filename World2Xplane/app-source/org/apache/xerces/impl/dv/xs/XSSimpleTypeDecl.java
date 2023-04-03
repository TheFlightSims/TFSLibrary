package org.apache.xerces.impl.dv.xs;

import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.xerces.impl.dv.DatatypeException;
import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xpath.regex.RegularExpression;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.util.ShortListImpl;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSFacet;
import org.apache.xerces.xs.XSMultiValueFacet;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.datatypes.ObjectList;
import org.w3c.dom.TypeInfo;

public class XSSimpleTypeDecl implements XSSimpleType, TypeInfo {
  static final short DV_STRING = 1;
  
  static final short DV_BOOLEAN = 2;
  
  static final short DV_DECIMAL = 3;
  
  static final short DV_FLOAT = 4;
  
  static final short DV_DOUBLE = 5;
  
  static final short DV_DURATION = 6;
  
  static final short DV_DATETIME = 7;
  
  static final short DV_TIME = 8;
  
  static final short DV_DATE = 9;
  
  static final short DV_GYEARMONTH = 10;
  
  static final short DV_GYEAR = 11;
  
  static final short DV_GMONTHDAY = 12;
  
  static final short DV_GDAY = 13;
  
  static final short DV_GMONTH = 14;
  
  static final short DV_HEXBINARY = 15;
  
  static final short DV_BASE64BINARY = 16;
  
  static final short DV_ANYURI = 17;
  
  static final short DV_QNAME = 18;
  
  static final short DV_PRECISIONDECIMAL = 19;
  
  static final short DV_NOTATION = 20;
  
  static final short DV_ANYSIMPLETYPE = 0;
  
  static final short DV_ID = 21;
  
  static final short DV_IDREF = 22;
  
  static final short DV_ENTITY = 23;
  
  static final short DV_INTEGER = 24;
  
  static final short DV_LIST = 25;
  
  static final short DV_UNION = 26;
  
  static final short DV_YEARMONTHDURATION = 27;
  
  static final short DV_DAYTIMEDURATION = 28;
  
  static final short DV_ANYATOMICTYPE = 29;
  
  static final TypeValidator[] fDVs = new TypeValidator[] { 
      new AnySimpleDV(), new StringDV(), new BooleanDV(), new DecimalDV(), new FloatDV(), new DoubleDV(), new DurationDV(), new DateTimeDV(), new TimeDV(), new DateDV(), 
      new YearMonthDV(), new YearDV(), new MonthDayDV(), new DayDV(), new MonthDV(), new HexBinaryDV(), new Base64BinaryDV(), new AnyURIDV(), new QNameDV(), new PrecisionDecimalDV(), 
      new QNameDV(), new IDDV(), new IDREFDV(), new EntityDV(), new IntegerDV(), new ListDV(), new UnionDV(), new YearMonthDurationDV(), new DayTimeDurationDV(), new AnyAtomicDV() };
  
  static final short NORMALIZE_NONE = 0;
  
  static final short NORMALIZE_TRIM = 1;
  
  static final short NORMALIZE_FULL = 2;
  
  static final short[] fDVNormalizeType = new short[] { 
      0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 
      1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 
      1, 1, 1, 1, 1, 2, 0, 1, 1, 0 };
  
  static final short SPECIAL_PATTERN_NONE = 0;
  
  static final short SPECIAL_PATTERN_NMTOKEN = 1;
  
  static final short SPECIAL_PATTERN_NAME = 2;
  
  static final short SPECIAL_PATTERN_NCNAME = 3;
  
  static final String[] SPECIAL_PATTERN_STRING = new String[] { "NONE", "NMTOKEN", "Name", "NCName" };
  
  static final String[] WS_FACET_STRING = new String[] { "preserve", "replace", "collapse" };
  
  static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
  
  static final String ANY_TYPE = "anyType";
  
  public static final short YEARMONTHDURATION_DT = 46;
  
  public static final short DAYTIMEDURATION_DT = 47;
  
  public static final short PRECISIONDECIMAL_DT = 48;
  
  public static final short ANYATOMICTYPE_DT = 49;
  
  static final int DERIVATION_ANY = 0;
  
  static final int DERIVATION_RESTRICTION = 1;
  
  static final int DERIVATION_EXTENSION = 2;
  
  static final int DERIVATION_UNION = 4;
  
  static final int DERIVATION_LIST = 8;
  
  static final ValidationContext fEmptyContext = new ValidationContext() {
      public boolean needFacetChecking() {
        return true;
      }
      
      public boolean needExtraChecking() {
        return false;
      }
      
      public boolean needToNormalize() {
        return true;
      }
      
      public boolean useNamespaces() {
        return true;
      }
      
      public boolean isEntityDeclared(String param1String) {
        return false;
      }
      
      public boolean isEntityUnparsed(String param1String) {
        return false;
      }
      
      public boolean isIdDeclared(String param1String) {
        return false;
      }
      
      public void addId(String param1String) {}
      
      public void addIdRef(String param1String) {}
      
      public String getSymbol(String param1String) {
        return param1String.intern();
      }
      
      public String getURI(String param1String) {
        return null;
      }
    };
  
  private boolean fIsImmutable = false;
  
  private XSSimpleTypeDecl fItemType;
  
  private XSSimpleTypeDecl[] fMemberTypes;
  
  private short fBuiltInKind;
  
  private String fTypeName;
  
  private String fTargetNamespace;
  
  private short fFinalSet = 0;
  
  private XSSimpleTypeDecl fBase;
  
  private short fVariety = -1;
  
  private short fValidationDV = -1;
  
  private short fFacetsDefined = 0;
  
  private short fFixedFacet = 0;
  
  private short fWhiteSpace = 0;
  
  private int fLength = -1;
  
  private int fMinLength = -1;
  
  private int fMaxLength = -1;
  
  private int fTotalDigits = -1;
  
  private int fFractionDigits = -1;
  
  private Vector fPattern;
  
  private Vector fPatternStr;
  
  private Vector fEnumeration;
  
  private short[] fEnumerationType;
  
  private ShortList[] fEnumerationItemType;
  
  private ShortList fEnumerationTypeList;
  
  private ObjectList fEnumerationItemTypeList;
  
  private StringList fLexicalPattern;
  
  private StringList fLexicalEnumeration;
  
  private ObjectList fActualEnumeration;
  
  private Object fMaxInclusive;
  
  private Object fMaxExclusive;
  
  private Object fMinExclusive;
  
  private Object fMinInclusive;
  
  public XSAnnotation lengthAnnotation;
  
  public XSAnnotation minLengthAnnotation;
  
  public XSAnnotation maxLengthAnnotation;
  
  public XSAnnotation whiteSpaceAnnotation;
  
  public XSAnnotation totalDigitsAnnotation;
  
  public XSAnnotation fractionDigitsAnnotation;
  
  public XSObjectListImpl patternAnnotations;
  
  public XSObjectList enumerationAnnotations;
  
  public XSAnnotation maxInclusiveAnnotation;
  
  public XSAnnotation maxExclusiveAnnotation;
  
  public XSAnnotation minInclusiveAnnotation;
  
  public XSAnnotation minExclusiveAnnotation;
  
  private XSObjectListImpl fFacets;
  
  private XSObjectListImpl fMultiValueFacets;
  
  private XSObjectList fAnnotations = null;
  
  private short fPatternType = 0;
  
  private short fOrdered;
  
  private boolean fFinite;
  
  private boolean fBounded;
  
  private boolean fNumeric;
  
  static final XSSimpleTypeDecl fAnySimpleType = new XSSimpleTypeDecl(null, "anySimpleType", (short)0, (short)0, false, true, false, true, (short)1);
  
  static final XSSimpleTypeDecl fAnyAtomicType = new XSSimpleTypeDecl(fAnySimpleType, "anyAtomicType", (short)29, (short)0, false, true, false, true, (short)49);
  
  static final ValidationContext fDummyContext = new ValidationContext() {
      public boolean needFacetChecking() {
        return true;
      }
      
      public boolean needExtraChecking() {
        return false;
      }
      
      public boolean needToNormalize() {
        return false;
      }
      
      public boolean useNamespaces() {
        return true;
      }
      
      public boolean isEntityDeclared(String param1String) {
        return false;
      }
      
      public boolean isEntityUnparsed(String param1String) {
        return false;
      }
      
      public boolean isIdDeclared(String param1String) {
        return false;
      }
      
      public void addId(String param1String) {}
      
      public void addIdRef(String param1String) {}
      
      public String getSymbol(String param1String) {
        return param1String.intern();
      }
      
      public String getURI(String param1String) {
        return null;
      }
    };
  
  private boolean fAnonymous = false;
  
  public XSSimpleTypeDecl() {}
  
  protected XSSimpleTypeDecl(XSSimpleTypeDecl paramXSSimpleTypeDecl, String paramString, short paramShort1, short paramShort2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, short paramShort3) {
    this.fIsImmutable = paramBoolean4;
    this.fBase = paramXSSimpleTypeDecl;
    this.fTypeName = paramString;
    this.fTargetNamespace = "http://www.w3.org/2001/XMLSchema";
    this.fVariety = 1;
    this.fValidationDV = paramShort1;
    this.fFacetsDefined = 16;
    if (paramShort1 == 1) {
      this.fWhiteSpace = 0;
    } else {
      this.fWhiteSpace = 2;
      this.fFixedFacet = 16;
    } 
    this.fOrdered = paramShort2;
    this.fBounded = paramBoolean1;
    this.fFinite = paramBoolean2;
    this.fNumeric = paramBoolean3;
    this.fAnnotations = null;
    this.fBuiltInKind = paramShort3;
  }
  
  protected XSSimpleTypeDecl(XSSimpleTypeDecl paramXSSimpleTypeDecl, String paramString1, String paramString2, short paramShort1, boolean paramBoolean, XSObjectList paramXSObjectList, short paramShort2) {
    this(paramXSSimpleTypeDecl, paramString1, paramString2, paramShort1, paramBoolean, paramXSObjectList);
    this.fBuiltInKind = paramShort2;
  }
  
  protected XSSimpleTypeDecl(XSSimpleTypeDecl paramXSSimpleTypeDecl, String paramString1, String paramString2, short paramShort, boolean paramBoolean, XSObjectList paramXSObjectList) {
    this.fBase = paramXSSimpleTypeDecl;
    this.fTypeName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fFinalSet = paramShort;
    this.fAnnotations = paramXSObjectList;
    this.fVariety = this.fBase.fVariety;
    this.fValidationDV = this.fBase.fValidationDV;
    switch (this.fVariety) {
      case 2:
        this.fItemType = this.fBase.fItemType;
        break;
      case 3:
        this.fMemberTypes = this.fBase.fMemberTypes;
        break;
    } 
    this.fLength = this.fBase.fLength;
    this.fMinLength = this.fBase.fMinLength;
    this.fMaxLength = this.fBase.fMaxLength;
    this.fPattern = this.fBase.fPattern;
    this.fPatternStr = this.fBase.fPatternStr;
    this.fEnumeration = this.fBase.fEnumeration;
    this.fEnumerationType = this.fBase.fEnumerationType;
    this.fEnumerationItemType = this.fBase.fEnumerationItemType;
    this.fWhiteSpace = this.fBase.fWhiteSpace;
    this.fMaxExclusive = this.fBase.fMaxExclusive;
    this.fMaxInclusive = this.fBase.fMaxInclusive;
    this.fMinExclusive = this.fBase.fMinExclusive;
    this.fMinInclusive = this.fBase.fMinInclusive;
    this.fTotalDigits = this.fBase.fTotalDigits;
    this.fFractionDigits = this.fBase.fFractionDigits;
    this.fPatternType = this.fBase.fPatternType;
    this.fFixedFacet = this.fBase.fFixedFacet;
    this.fFacetsDefined = this.fBase.fFacetsDefined;
    this.lengthAnnotation = this.fBase.lengthAnnotation;
    this.minLengthAnnotation = this.fBase.minLengthAnnotation;
    this.maxLengthAnnotation = this.fBase.maxLengthAnnotation;
    this.patternAnnotations = this.fBase.patternAnnotations;
    this.enumerationAnnotations = this.fBase.enumerationAnnotations;
    this.whiteSpaceAnnotation = this.fBase.whiteSpaceAnnotation;
    this.maxExclusiveAnnotation = this.fBase.maxExclusiveAnnotation;
    this.maxInclusiveAnnotation = this.fBase.maxInclusiveAnnotation;
    this.minExclusiveAnnotation = this.fBase.minExclusiveAnnotation;
    this.minInclusiveAnnotation = this.fBase.minInclusiveAnnotation;
    this.totalDigitsAnnotation = this.fBase.totalDigitsAnnotation;
    this.fractionDigitsAnnotation = this.fBase.fractionDigitsAnnotation;
    calcFundamentalFacets();
    this.fIsImmutable = paramBoolean;
    this.fBuiltInKind = paramXSSimpleTypeDecl.fBuiltInKind;
  }
  
  protected XSSimpleTypeDecl(String paramString1, String paramString2, short paramShort, XSSimpleTypeDecl paramXSSimpleTypeDecl, boolean paramBoolean, XSObjectList paramXSObjectList) {
    this.fBase = fAnySimpleType;
    this.fTypeName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fFinalSet = paramShort;
    this.fAnnotations = paramXSObjectList;
    this.fVariety = 2;
    this.fItemType = paramXSSimpleTypeDecl;
    this.fValidationDV = 25;
    this.fFacetsDefined = 16;
    this.fFixedFacet = 16;
    this.fWhiteSpace = 2;
    calcFundamentalFacets();
    this.fIsImmutable = paramBoolean;
    this.fBuiltInKind = 44;
  }
  
  protected XSSimpleTypeDecl(String paramString1, String paramString2, short paramShort, XSSimpleTypeDecl[] paramArrayOfXSSimpleTypeDecl, XSObjectList paramXSObjectList) {
    this.fBase = fAnySimpleType;
    this.fTypeName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fFinalSet = paramShort;
    this.fAnnotations = paramXSObjectList;
    this.fVariety = 3;
    this.fMemberTypes = paramArrayOfXSSimpleTypeDecl;
    this.fValidationDV = 26;
    this.fFacetsDefined = 16;
    this.fWhiteSpace = 2;
    calcFundamentalFacets();
    this.fIsImmutable = false;
    this.fBuiltInKind = 45;
  }
  
  protected XSSimpleTypeDecl setRestrictionValues(XSSimpleTypeDecl paramXSSimpleTypeDecl, String paramString1, String paramString2, short paramShort, XSObjectList paramXSObjectList) {
    if (this.fIsImmutable)
      return null; 
    this.fBase = paramXSSimpleTypeDecl;
    this.fTypeName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fFinalSet = paramShort;
    this.fAnnotations = paramXSObjectList;
    this.fVariety = this.fBase.fVariety;
    this.fValidationDV = this.fBase.fValidationDV;
    switch (this.fVariety) {
      case 2:
        this.fItemType = this.fBase.fItemType;
        break;
      case 3:
        this.fMemberTypes = this.fBase.fMemberTypes;
        break;
    } 
    this.fLength = this.fBase.fLength;
    this.fMinLength = this.fBase.fMinLength;
    this.fMaxLength = this.fBase.fMaxLength;
    this.fPattern = this.fBase.fPattern;
    this.fPatternStr = this.fBase.fPatternStr;
    this.fEnumeration = this.fBase.fEnumeration;
    this.fEnumerationType = this.fBase.fEnumerationType;
    this.fEnumerationItemType = this.fBase.fEnumerationItemType;
    this.fWhiteSpace = this.fBase.fWhiteSpace;
    this.fMaxExclusive = this.fBase.fMaxExclusive;
    this.fMaxInclusive = this.fBase.fMaxInclusive;
    this.fMinExclusive = this.fBase.fMinExclusive;
    this.fMinInclusive = this.fBase.fMinInclusive;
    this.fTotalDigits = this.fBase.fTotalDigits;
    this.fFractionDigits = this.fBase.fFractionDigits;
    this.fPatternType = this.fBase.fPatternType;
    this.fFixedFacet = this.fBase.fFixedFacet;
    this.fFacetsDefined = this.fBase.fFacetsDefined;
    calcFundamentalFacets();
    this.fBuiltInKind = paramXSSimpleTypeDecl.fBuiltInKind;
    return this;
  }
  
  protected XSSimpleTypeDecl setListValues(String paramString1, String paramString2, short paramShort, XSSimpleTypeDecl paramXSSimpleTypeDecl, XSObjectList paramXSObjectList) {
    if (this.fIsImmutable)
      return null; 
    this.fBase = fAnySimpleType;
    this.fTypeName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fFinalSet = paramShort;
    this.fAnnotations = paramXSObjectList;
    this.fVariety = 2;
    this.fItemType = paramXSSimpleTypeDecl;
    this.fValidationDV = 25;
    this.fFacetsDefined = 16;
    this.fFixedFacet = 16;
    this.fWhiteSpace = 2;
    calcFundamentalFacets();
    this.fBuiltInKind = 44;
    return this;
  }
  
  protected XSSimpleTypeDecl setUnionValues(String paramString1, String paramString2, short paramShort, XSSimpleTypeDecl[] paramArrayOfXSSimpleTypeDecl, XSObjectList paramXSObjectList) {
    if (this.fIsImmutable)
      return null; 
    this.fBase = fAnySimpleType;
    this.fTypeName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fFinalSet = paramShort;
    this.fAnnotations = paramXSObjectList;
    this.fVariety = 3;
    this.fMemberTypes = paramArrayOfXSSimpleTypeDecl;
    this.fValidationDV = 26;
    this.fFacetsDefined = 16;
    this.fWhiteSpace = 2;
    calcFundamentalFacets();
    this.fBuiltInKind = 45;
    return this;
  }
  
  public short getType() {
    return 3;
  }
  
  public short getTypeCategory() {
    return 16;
  }
  
  public String getName() {
    return getAnonymous() ? null : this.fTypeName;
  }
  
  public String getTypeName() {
    return this.fTypeName;
  }
  
  public String getNamespace() {
    return this.fTargetNamespace;
  }
  
  public short getFinal() {
    return this.fFinalSet;
  }
  
  public boolean isFinal(short paramShort) {
    return ((this.fFinalSet & paramShort) != 0);
  }
  
  public XSTypeDefinition getBaseType() {
    return (XSTypeDefinition)this.fBase;
  }
  
  public boolean getAnonymous() {
    return (this.fAnonymous || this.fTypeName == null);
  }
  
  public short getVariety() {
    return (this.fValidationDV == 0) ? 0 : this.fVariety;
  }
  
  public boolean isIDType() {
    byte b;
    switch (this.fVariety) {
      case 1:
        return (this.fValidationDV == 21);
      case 2:
        return this.fItemType.isIDType();
      case 3:
        for (b = 0; b < this.fMemberTypes.length; b++) {
          if (this.fMemberTypes[b].isIDType())
            return true; 
        } 
        break;
    } 
    return false;
  }
  
  public short getWhitespace() throws DatatypeException {
    if (this.fVariety == 3)
      throw new DatatypeException("dt-whitespace", new Object[] { this.fTypeName }); 
    return this.fWhiteSpace;
  }
  
  public short getPrimitiveKind() {
    return (this.fVariety == 1 && this.fValidationDV != 0) ? ((this.fValidationDV == 21 || this.fValidationDV == 22 || this.fValidationDV == 23) ? 1 : ((this.fValidationDV == 24) ? 3 : this.fValidationDV)) : 0;
  }
  
  public short getBuiltInKind() {
    return this.fBuiltInKind;
  }
  
  public XSSimpleTypeDefinition getPrimitiveType() {
    if (this.fVariety == 1 && this.fValidationDV != 0) {
      for (XSSimpleTypeDecl xSSimpleTypeDecl = this; xSSimpleTypeDecl.fBase != fAnySimpleType; xSSimpleTypeDecl = xSSimpleTypeDecl.fBase);
      return (XSSimpleTypeDefinition)xSSimpleTypeDecl;
    } 
    return null;
  }
  
  public XSSimpleTypeDefinition getItemType() {
    return (XSSimpleTypeDefinition)((this.fVariety == 2) ? this.fItemType : null);
  }
  
  public XSObjectList getMemberTypes() {
    return (XSObjectList)((this.fVariety == 3) ? new XSObjectListImpl((XSObject[])this.fMemberTypes, this.fMemberTypes.length) : XSObjectListImpl.EMPTY_LIST);
  }
  
  public void applyFacets(XSFacets paramXSFacets, short paramShort1, short paramShort2, ValidationContext paramValidationContext) throws InvalidDatatypeFacetException {
    applyFacets(paramXSFacets, paramShort1, paramShort2, (short)0, paramValidationContext);
  }
  
  void applyFacets1(XSFacets paramXSFacets, short paramShort1, short paramShort2) {
    try {
      applyFacets(paramXSFacets, paramShort1, paramShort2, (short)0, fDummyContext);
    } catch (InvalidDatatypeFacetException invalidDatatypeFacetException) {
      throw new RuntimeException("internal error");
    } 
    this.fIsImmutable = true;
  }
  
  void applyFacets1(XSFacets paramXSFacets, short paramShort1, short paramShort2, short paramShort3) {
    try {
      applyFacets(paramXSFacets, paramShort1, paramShort2, paramShort3, fDummyContext);
    } catch (InvalidDatatypeFacetException invalidDatatypeFacetException) {
      throw new RuntimeException("internal error");
    } 
    this.fIsImmutable = true;
  }
  
  void applyFacets(XSFacets paramXSFacets, short paramShort1, short paramShort2, short paramShort3, ValidationContext paramValidationContext) throws InvalidDatatypeFacetException {
    if (this.fIsImmutable)
      return; 
    ValidatedInfo validatedInfo = new ValidatedInfo();
    this.fFacetsDefined = 0;
    this.fFixedFacet = 0;
    int i = 0;
    short s = fDVs[this.fValidationDV].getAllowedFacets();
    if ((paramShort1 & 0x1) != 0)
      if ((s & 0x1) == 0) {
        reportError("cos-applicable-facets", new Object[] { "length", this.fTypeName });
      } else {
        this.fLength = paramXSFacets.length;
        this.lengthAnnotation = paramXSFacets.lengthAnnotation;
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x1);
        if ((paramShort2 & 0x1) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x1); 
      }  
    if ((paramShort1 & 0x2) != 0)
      if ((s & 0x2) == 0) {
        reportError("cos-applicable-facets", new Object[] { "minLength", this.fTypeName });
      } else {
        this.fMinLength = paramXSFacets.minLength;
        this.minLengthAnnotation = paramXSFacets.minLengthAnnotation;
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x2);
        if ((paramShort2 & 0x2) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x2); 
      }  
    if ((paramShort1 & 0x4) != 0)
      if ((s & 0x4) == 0) {
        reportError("cos-applicable-facets", new Object[] { "maxLength", this.fTypeName });
      } else {
        this.fMaxLength = paramXSFacets.maxLength;
        this.maxLengthAnnotation = paramXSFacets.maxLengthAnnotation;
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x4);
        if ((paramShort2 & 0x4) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x4); 
      }  
    if ((paramShort1 & 0x8) != 0)
      if ((s & 0x8) == 0) {
        reportError("cos-applicable-facets", new Object[] { "pattern", this.fTypeName });
      } else {
        this.patternAnnotations = paramXSFacets.patternAnnotations;
        RegularExpression regularExpression = null;
        try {
          regularExpression = new RegularExpression(paramXSFacets.pattern, "X");
        } catch (Exception exception) {
          reportError("InvalidRegex", new Object[] { paramXSFacets.pattern, exception.getLocalizedMessage() });
        } 
        if (regularExpression != null) {
          this.fPattern = new Vector();
          this.fPattern.addElement(regularExpression);
          this.fPatternStr = new Vector();
          this.fPatternStr.addElement(paramXSFacets.pattern);
          this.fFacetsDefined = (short)(this.fFacetsDefined | 0x8);
          if ((paramShort2 & 0x8) != 0)
            this.fFixedFacet = (short)(this.fFixedFacet | 0x8); 
        } 
      }  
    if ((paramShort1 & 0x800) != 0)
      if ((s & 0x800) == 0) {
        reportError("cos-applicable-facets", new Object[] { "enumeration", this.fTypeName });
      } else {
        this.fEnumeration = new Vector();
        Vector vector = paramXSFacets.enumeration;
        this.fEnumerationType = new short[vector.size()];
        this.fEnumerationItemType = new ShortList[vector.size()];
        Vector vector1 = paramXSFacets.enumNSDecls;
        ValidationContextImpl validationContextImpl = new ValidationContextImpl(paramValidationContext);
        this.enumerationAnnotations = paramXSFacets.enumAnnotations;
        for (byte b = 0; b < vector.size(); b++) {
          if (vector1 != null)
            validationContextImpl.setNSContext(vector1.elementAt(b)); 
          try {
            ValidatedInfo validatedInfo1 = this.fBase.validateWithInfo(vector.elementAt(b), validationContextImpl, validatedInfo);
            this.fEnumeration.addElement(validatedInfo1.actualValue);
            this.fEnumerationType[b] = validatedInfo1.actualValueType;
            this.fEnumerationItemType[b] = validatedInfo1.itemValueTypes;
          } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
            reportError("enumeration-valid-restriction", new Object[] { vector.elementAt(b), getBaseType().getName() });
          } 
        } 
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x800);
        if ((paramShort2 & 0x800) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x800); 
      }  
    if ((paramShort1 & 0x10) != 0)
      if ((s & 0x10) == 0) {
        reportError("cos-applicable-facets", new Object[] { "whiteSpace", this.fTypeName });
      } else {
        this.fWhiteSpace = paramXSFacets.whiteSpace;
        this.whiteSpaceAnnotation = paramXSFacets.whiteSpaceAnnotation;
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x10);
        if ((paramShort2 & 0x10) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x10); 
      }  
    if ((paramShort1 & 0x20) != 0)
      if ((s & 0x20) == 0) {
        reportError("cos-applicable-facets", new Object[] { "maxInclusive", this.fTypeName });
      } else {
        this.maxInclusiveAnnotation = paramXSFacets.maxInclusiveAnnotation;
        try {
          this.fMaxInclusive = this.fBase.getActualValue(paramXSFacets.maxInclusive, paramValidationContext, validatedInfo, true);
          this.fFacetsDefined = (short)(this.fFacetsDefined | 0x20);
          if ((paramShort2 & 0x20) != 0)
            this.fFixedFacet = (short)(this.fFixedFacet | 0x20); 
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.maxInclusive, "maxInclusive", this.fBase.getName() });
        } 
        if ((this.fBase.fFacetsDefined & 0x20) != 0 && (this.fBase.fFixedFacet & 0x20) != 0 && fDVs[this.fValidationDV].compare(this.fMaxInclusive, this.fBase.fMaxInclusive) != 0)
          reportError("FixedFacetValue", new Object[] { "maxInclusive", this.fMaxInclusive, this.fBase.fMaxInclusive, this.fTypeName }); 
        try {
          this.fBase.validate(paramValidationContext, validatedInfo);
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.maxInclusive, "maxInclusive", this.fBase.getName() });
        } 
      }  
    boolean bool = true;
    if ((paramShort1 & 0x40) != 0)
      if ((s & 0x40) == 0) {
        reportError("cos-applicable-facets", new Object[] { "maxExclusive", this.fTypeName });
      } else {
        this.maxExclusiveAnnotation = paramXSFacets.maxExclusiveAnnotation;
        try {
          this.fMaxExclusive = this.fBase.getActualValue(paramXSFacets.maxExclusive, paramValidationContext, validatedInfo, true);
          this.fFacetsDefined = (short)(this.fFacetsDefined | 0x40);
          if ((paramShort2 & 0x40) != 0)
            this.fFixedFacet = (short)(this.fFixedFacet | 0x40); 
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.maxExclusive, "maxExclusive", this.fBase.getName() });
        } 
        if ((this.fBase.fFacetsDefined & 0x40) != 0) {
          i = fDVs[this.fValidationDV].compare(this.fMaxExclusive, this.fBase.fMaxExclusive);
          if ((this.fBase.fFixedFacet & 0x40) != 0 && i != 0)
            reportError("FixedFacetValue", new Object[] { "maxExclusive", paramXSFacets.maxExclusive, this.fBase.fMaxExclusive, this.fTypeName }); 
          if (i == 0)
            bool = false; 
        } 
        if (bool) {
          try {
            this.fBase.validate(paramValidationContext, validatedInfo);
          } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
            reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
            reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.maxExclusive, "maxExclusive", this.fBase.getName() });
          } 
        } else if ((this.fBase.fFacetsDefined & 0x20) != 0 && fDVs[this.fValidationDV].compare(this.fMaxExclusive, this.fBase.fMaxInclusive) > 0) {
          reportError("maxExclusive-valid-restriction.2", new Object[] { paramXSFacets.maxExclusive, this.fBase.fMaxInclusive });
        } 
      }  
    bool = true;
    if ((paramShort1 & 0x80) != 0)
      if ((s & 0x80) == 0) {
        reportError("cos-applicable-facets", new Object[] { "minExclusive", this.fTypeName });
      } else {
        this.minExclusiveAnnotation = paramXSFacets.minExclusiveAnnotation;
        try {
          this.fMinExclusive = this.fBase.getActualValue(paramXSFacets.minExclusive, paramValidationContext, validatedInfo, true);
          this.fFacetsDefined = (short)(this.fFacetsDefined | 0x80);
          if ((paramShort2 & 0x80) != 0)
            this.fFixedFacet = (short)(this.fFixedFacet | 0x80); 
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.minExclusive, "minExclusive", this.fBase.getName() });
        } 
        if ((this.fBase.fFacetsDefined & 0x80) != 0) {
          i = fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fBase.fMinExclusive);
          if ((this.fBase.fFixedFacet & 0x80) != 0 && i != 0)
            reportError("FixedFacetValue", new Object[] { "minExclusive", paramXSFacets.minExclusive, this.fBase.fMinExclusive, this.fTypeName }); 
          if (i == 0)
            bool = false; 
        } 
        if (bool) {
          try {
            this.fBase.validate(paramValidationContext, validatedInfo);
          } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
            reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
            reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.minExclusive, "minExclusive", this.fBase.getName() });
          } 
        } else if ((this.fBase.fFacetsDefined & 0x100) != 0 && fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fBase.fMinInclusive) < 0) {
          reportError("minExclusive-valid-restriction.3", new Object[] { paramXSFacets.minExclusive, this.fBase.fMinInclusive });
        } 
      }  
    if ((paramShort1 & 0x100) != 0)
      if ((s & 0x100) == 0) {
        reportError("cos-applicable-facets", new Object[] { "minInclusive", this.fTypeName });
      } else {
        this.minInclusiveAnnotation = paramXSFacets.minInclusiveAnnotation;
        try {
          this.fMinInclusive = this.fBase.getActualValue(paramXSFacets.minInclusive, paramValidationContext, validatedInfo, true);
          this.fFacetsDefined = (short)(this.fFacetsDefined | 0x100);
          if ((paramShort2 & 0x100) != 0)
            this.fFixedFacet = (short)(this.fFixedFacet | 0x100); 
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.minInclusive, "minInclusive", this.fBase.getName() });
        } 
        if ((this.fBase.fFacetsDefined & 0x100) != 0 && (this.fBase.fFixedFacet & 0x100) != 0 && fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fBase.fMinInclusive) != 0)
          reportError("FixedFacetValue", new Object[] { "minInclusive", paramXSFacets.minInclusive, this.fBase.fMinInclusive, this.fTypeName }); 
        try {
          this.fBase.validate(paramValidationContext, validatedInfo);
        } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
          reportError(invalidDatatypeValueException.getKey(), invalidDatatypeValueException.getArgs());
          reportError("FacetValueFromBase", new Object[] { this.fTypeName, paramXSFacets.minInclusive, "minInclusive", this.fBase.getName() });
        } 
      }  
    if ((paramShort1 & 0x200) != 0)
      if ((s & 0x200) == 0) {
        reportError("cos-applicable-facets", new Object[] { "totalDigits", this.fTypeName });
      } else {
        this.totalDigitsAnnotation = paramXSFacets.totalDigitsAnnotation;
        this.fTotalDigits = paramXSFacets.totalDigits;
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x200);
        if ((paramShort2 & 0x200) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x200); 
      }  
    if ((paramShort1 & 0x400) != 0)
      if ((s & 0x400) == 0) {
        reportError("cos-applicable-facets", new Object[] { "fractionDigits", this.fTypeName });
      } else {
        this.fFractionDigits = paramXSFacets.fractionDigits;
        this.fractionDigitsAnnotation = paramXSFacets.fractionDigitsAnnotation;
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x400);
        if ((paramShort2 & 0x400) != 0)
          this.fFixedFacet = (short)(this.fFixedFacet | 0x400); 
      }  
    if (paramShort3 != 0)
      this.fPatternType = paramShort3; 
    if (this.fFacetsDefined != 0) {
      if ((this.fFacetsDefined & 0x2) != 0 && (this.fFacetsDefined & 0x4) != 0 && this.fMinLength > this.fMaxLength)
        reportError("minLength-less-than-equal-to-maxLength", new Object[] { Integer.toString(this.fMinLength), Integer.toString(this.fMaxLength), this.fTypeName }); 
      if ((this.fFacetsDefined & 0x40) != 0 && (this.fFacetsDefined & 0x20) != 0)
        reportError("maxInclusive-maxExclusive", new Object[] { this.fMaxInclusive, this.fMaxExclusive, this.fTypeName }); 
      if ((this.fFacetsDefined & 0x80) != 0 && (this.fFacetsDefined & 0x100) != 0)
        reportError("minInclusive-minExclusive", new Object[] { this.fMinInclusive, this.fMinExclusive, this.fTypeName }); 
      if ((this.fFacetsDefined & 0x20) != 0 && (this.fFacetsDefined & 0x100) != 0) {
        i = fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxInclusive);
        if (i != -1 && i != 0)
          reportError("minInclusive-less-than-equal-to-maxInclusive", new Object[] { this.fMinInclusive, this.fMaxInclusive, this.fTypeName }); 
      } 
      if ((this.fFacetsDefined & 0x40) != 0 && (this.fFacetsDefined & 0x80) != 0) {
        i = fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxExclusive);
        if (i != -1 && i != 0)
          reportError("minExclusive-less-than-equal-to-maxExclusive", new Object[] { this.fMinExclusive, this.fMaxExclusive, this.fTypeName }); 
      } 
      if ((this.fFacetsDefined & 0x20) != 0 && (this.fFacetsDefined & 0x80) != 0 && fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxInclusive) != -1)
        reportError("minExclusive-less-than-maxInclusive", new Object[] { this.fMinExclusive, this.fMaxInclusive, this.fTypeName }); 
      if ((this.fFacetsDefined & 0x40) != 0 && (this.fFacetsDefined & 0x100) != 0 && fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxExclusive) != -1)
        reportError("minInclusive-less-than-maxExclusive", new Object[] { this.fMinInclusive, this.fMaxExclusive, this.fTypeName }); 
      if ((this.fFacetsDefined & 0x400) != 0 && (this.fFacetsDefined & 0x200) != 0 && this.fFractionDigits > this.fTotalDigits)
        reportError("fractionDigits-totalDigits", new Object[] { Integer.toString(this.fFractionDigits), Integer.toString(this.fTotalDigits), this.fTypeName }); 
      if ((this.fFacetsDefined & 0x1) != 0) {
        if ((this.fBase.fFacetsDefined & 0x2) != 0 && this.fLength < this.fBase.fMinLength)
          reportError("length-minLength-maxLength.1.1", new Object[] { this.fTypeName, Integer.toString(this.fLength), Integer.toString(this.fBase.fMinLength) }); 
        if ((this.fBase.fFacetsDefined & 0x4) != 0 && this.fLength > this.fBase.fMaxLength)
          reportError("length-minLength-maxLength.2.1", new Object[] { this.fTypeName, Integer.toString(this.fLength), Integer.toString(this.fBase.fMaxLength) }); 
        if ((this.fBase.fFacetsDefined & 0x1) != 0 && this.fLength != this.fBase.fLength)
          reportError("length-valid-restriction", new Object[] { Integer.toString(this.fLength), Integer.toString(this.fBase.fLength), this.fTypeName }); 
      } 
      if ((this.fBase.fFacetsDefined & 0x1) != 0 || (this.fFacetsDefined & 0x1) != 0) {
        if ((this.fFacetsDefined & 0x2) != 0) {
          if (this.fBase.fLength < this.fMinLength)
            reportError("length-minLength-maxLength.1.1", new Object[] { this.fTypeName, Integer.toString(this.fBase.fLength), Integer.toString(this.fMinLength) }); 
          if ((this.fBase.fFacetsDefined & 0x2) == 0)
            reportError("length-minLength-maxLength.1.2.a", new Object[] { this.fTypeName }); 
          if (this.fMinLength != this.fBase.fMinLength)
            reportError("length-minLength-maxLength.1.2.b", new Object[] { this.fTypeName, Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength) }); 
        } 
        if ((this.fFacetsDefined & 0x4) != 0) {
          if (this.fBase.fLength > this.fMaxLength)
            reportError("length-minLength-maxLength.2.1", new Object[] { this.fTypeName, Integer.toString(this.fBase.fLength), Integer.toString(this.fMaxLength) }); 
          if ((this.fBase.fFacetsDefined & 0x4) == 0)
            reportError("length-minLength-maxLength.2.2.a", new Object[] { this.fTypeName }); 
          if (this.fMaxLength != this.fBase.fMaxLength)
            reportError("length-minLength-maxLength.2.2.b", new Object[] { this.fTypeName, Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fBase.fMaxLength) }); 
        } 
      } 
      if ((this.fFacetsDefined & 0x2) != 0)
        if ((this.fBase.fFacetsDefined & 0x4) != 0) {
          if (this.fMinLength > this.fBase.fMaxLength)
            reportError("minLength-less-than-equal-to-maxLength", new Object[] { Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMaxLength), this.fTypeName }); 
        } else if ((this.fBase.fFacetsDefined & 0x2) != 0) {
          if ((this.fBase.fFixedFacet & 0x2) != 0 && this.fMinLength != this.fBase.fMinLength)
            reportError("FixedFacetValue", new Object[] { "minLength", Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength), this.fTypeName }); 
          if (this.fMinLength < this.fBase.fMinLength)
            reportError("minLength-valid-restriction", new Object[] { Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength), this.fTypeName }); 
        }  
      if ((this.fFacetsDefined & 0x4) != 0 && (this.fBase.fFacetsDefined & 0x2) != 0 && this.fMaxLength < this.fBase.fMinLength)
        reportError("minLength-less-than-equal-to-maxLength", new Object[] { Integer.toString(this.fBase.fMinLength), Integer.toString(this.fMaxLength) }); 
      if ((this.fFacetsDefined & 0x4) != 0 && (this.fBase.fFacetsDefined & 0x4) != 0) {
        if ((this.fBase.fFixedFacet & 0x4) != 0 && this.fMaxLength != this.fBase.fMaxLength)
          reportError("FixedFacetValue", new Object[] { "maxLength", Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fMaxLength), this.fTypeName }); 
        if (this.fMaxLength > this.fBase.fMaxLength)
          reportError("maxLength-valid-restriction", new Object[] { Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fMaxLength), this.fTypeName }); 
      } 
      if ((this.fFacetsDefined & 0x200) != 0 && (this.fBase.fFacetsDefined & 0x200) != 0) {
        if ((this.fBase.fFixedFacet & 0x200) != 0 && this.fTotalDigits != this.fBase.fTotalDigits)
          reportError("FixedFacetValue", new Object[] { "totalDigits", Integer.toString(this.fTotalDigits), Integer.toString(this.fBase.fTotalDigits), this.fTypeName }); 
        if (this.fTotalDigits > this.fBase.fTotalDigits)
          reportError("totalDigits-valid-restriction", new Object[] { Integer.toString(this.fTotalDigits), Integer.toString(this.fBase.fTotalDigits), this.fTypeName }); 
      } 
      if ((this.fFacetsDefined & 0x400) != 0 && (this.fBase.fFacetsDefined & 0x200) != 0 && this.fFractionDigits > this.fBase.fTotalDigits)
        reportError("fractionDigits-totalDigits", new Object[] { Integer.toString(this.fFractionDigits), Integer.toString(this.fTotalDigits), this.fTypeName }); 
      if ((this.fFacetsDefined & 0x400) != 0)
        if ((this.fBase.fFacetsDefined & 0x400) != 0) {
          if (((this.fBase.fFixedFacet & 0x400) != 0 && this.fFractionDigits != this.fBase.fFractionDigits) || (this.fValidationDV == 24 && this.fFractionDigits != 0))
            reportError("FixedFacetValue", new Object[] { "fractionDigits", Integer.toString(this.fFractionDigits), Integer.toString(this.fBase.fFractionDigits), this.fTypeName }); 
          if (this.fFractionDigits > this.fBase.fFractionDigits)
            reportError("fractionDigits-valid-restriction", new Object[] { Integer.toString(this.fFractionDigits), Integer.toString(this.fBase.fFractionDigits), this.fTypeName }); 
        } else if (this.fValidationDV == 24 && this.fFractionDigits != 0) {
          reportError("FixedFacetValue", new Object[] { "fractionDigits", Integer.toString(this.fFractionDigits), "0", this.fTypeName });
        }  
      if ((this.fFacetsDefined & 0x10) != 0 && (this.fBase.fFacetsDefined & 0x10) != 0) {
        if ((this.fBase.fFixedFacet & 0x10) != 0 && this.fWhiteSpace != this.fBase.fWhiteSpace)
          reportError("FixedFacetValue", new Object[] { "whiteSpace", whiteSpaceValue(this.fWhiteSpace), whiteSpaceValue(this.fBase.fWhiteSpace), this.fTypeName }); 
        if (this.fWhiteSpace == 0 && this.fBase.fWhiteSpace == 2)
          reportError("whiteSpace-valid-restriction.1", new Object[] { this.fTypeName, "preserve" }); 
        if (this.fWhiteSpace == 1 && this.fBase.fWhiteSpace == 2)
          reportError("whiteSpace-valid-restriction.1", new Object[] { this.fTypeName, "replace" }); 
        if (this.fWhiteSpace == 0 && this.fBase.fWhiteSpace == 1)
          reportError("whiteSpace-valid-restriction.2", new Object[] { this.fTypeName }); 
      } 
    } 
    if ((this.fFacetsDefined & 0x1) == 0 && (this.fBase.fFacetsDefined & 0x1) != 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x1);
      this.fLength = this.fBase.fLength;
      this.lengthAnnotation = this.fBase.lengthAnnotation;
    } 
    if ((this.fFacetsDefined & 0x2) == 0 && (this.fBase.fFacetsDefined & 0x2) != 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x2);
      this.fMinLength = this.fBase.fMinLength;
      this.minLengthAnnotation = this.fBase.minLengthAnnotation;
    } 
    if ((this.fFacetsDefined & 0x4) == 0 && (this.fBase.fFacetsDefined & 0x4) != 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x4);
      this.fMaxLength = this.fBase.fMaxLength;
      this.maxLengthAnnotation = this.fBase.maxLengthAnnotation;
    } 
    if ((this.fBase.fFacetsDefined & 0x8) != 0)
      if ((this.fFacetsDefined & 0x8) == 0) {
        this.fFacetsDefined = (short)(this.fFacetsDefined | 0x8);
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.patternAnnotations = this.fBase.patternAnnotations;
      } else {
        for (int j = this.fBase.fPattern.size() - 1; j >= 0; j--) {
          this.fPattern.addElement(this.fBase.fPattern.elementAt(j));
          this.fPatternStr.addElement(this.fBase.fPatternStr.elementAt(j));
        } 
        if (this.fBase.patternAnnotations != null)
          if (this.patternAnnotations != null) {
            for (int k = this.fBase.patternAnnotations.getLength() - 1; k >= 0; k--)
              this.patternAnnotations.add(this.fBase.patternAnnotations.item(k)); 
          } else {
            this.patternAnnotations = this.fBase.patternAnnotations;
          }  
      }  
    if ((this.fFacetsDefined & 0x10) == 0 && (this.fBase.fFacetsDefined & 0x10) != 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x10);
      this.fWhiteSpace = this.fBase.fWhiteSpace;
      this.whiteSpaceAnnotation = this.fBase.whiteSpaceAnnotation;
    } 
    if ((this.fFacetsDefined & 0x800) == 0 && (this.fBase.fFacetsDefined & 0x800) != 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x800);
      this.fEnumeration = this.fBase.fEnumeration;
      this.enumerationAnnotations = this.fBase.enumerationAnnotations;
    } 
    if ((this.fBase.fFacetsDefined & 0x40) != 0 && (this.fFacetsDefined & 0x40) == 0 && (this.fFacetsDefined & 0x20) == 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x40);
      this.fMaxExclusive = this.fBase.fMaxExclusive;
      this.maxExclusiveAnnotation = this.fBase.maxExclusiveAnnotation;
    } 
    if ((this.fBase.fFacetsDefined & 0x20) != 0 && (this.fFacetsDefined & 0x40) == 0 && (this.fFacetsDefined & 0x20) == 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x20);
      this.fMaxInclusive = this.fBase.fMaxInclusive;
      this.maxInclusiveAnnotation = this.fBase.maxInclusiveAnnotation;
    } 
    if ((this.fBase.fFacetsDefined & 0x80) != 0 && (this.fFacetsDefined & 0x80) == 0 && (this.fFacetsDefined & 0x100) == 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x80);
      this.fMinExclusive = this.fBase.fMinExclusive;
      this.minExclusiveAnnotation = this.fBase.minExclusiveAnnotation;
    } 
    if ((this.fBase.fFacetsDefined & 0x100) != 0 && (this.fFacetsDefined & 0x80) == 0 && (this.fFacetsDefined & 0x100) == 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x100);
      this.fMinInclusive = this.fBase.fMinInclusive;
      this.minInclusiveAnnotation = this.fBase.minInclusiveAnnotation;
    } 
    if ((this.fBase.fFacetsDefined & 0x200) != 0 && (this.fFacetsDefined & 0x200) == 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x200);
      this.fTotalDigits = this.fBase.fTotalDigits;
      this.totalDigitsAnnotation = this.fBase.totalDigitsAnnotation;
    } 
    if ((this.fBase.fFacetsDefined & 0x400) != 0 && (this.fFacetsDefined & 0x400) == 0) {
      this.fFacetsDefined = (short)(this.fFacetsDefined | 0x400);
      this.fFractionDigits = this.fBase.fFractionDigits;
      this.fractionDigitsAnnotation = this.fBase.fractionDigitsAnnotation;
    } 
    if (this.fPatternType == 0 && this.fBase.fPatternType != 0)
      this.fPatternType = this.fBase.fPatternType; 
    this.fFixedFacet = (short)(this.fFixedFacet | this.fBase.fFixedFacet);
    calcFundamentalFacets();
  }
  
  public Object validate(String paramString, ValidationContext paramValidationContext, ValidatedInfo paramValidatedInfo) throws InvalidDatatypeValueException {
    if (paramValidationContext == null)
      paramValidationContext = fEmptyContext; 
    if (paramValidatedInfo == null) {
      paramValidatedInfo = new ValidatedInfo();
    } else {
      paramValidatedInfo.memberType = null;
    } 
    boolean bool = (paramValidationContext == null || paramValidationContext.needToNormalize()) ? true : false;
    Object object = getActualValue(paramString, paramValidationContext, paramValidatedInfo, bool);
    validate(paramValidationContext, paramValidatedInfo);
    return object;
  }
  
  public ValidatedInfo validateWithInfo(String paramString, ValidationContext paramValidationContext, ValidatedInfo paramValidatedInfo) throws InvalidDatatypeValueException {
    if (paramValidationContext == null)
      paramValidationContext = fEmptyContext; 
    if (paramValidatedInfo == null) {
      paramValidatedInfo = new ValidatedInfo();
    } else {
      paramValidatedInfo.memberType = null;
    } 
    boolean bool = (paramValidationContext == null || paramValidationContext.needToNormalize()) ? true : false;
    getActualValue(paramString, paramValidationContext, paramValidatedInfo, bool);
    validate(paramValidationContext, paramValidatedInfo);
    return paramValidatedInfo;
  }
  
  public Object validate(Object paramObject, ValidationContext paramValidationContext, ValidatedInfo paramValidatedInfo) throws InvalidDatatypeValueException {
    if (paramValidationContext == null)
      paramValidationContext = fEmptyContext; 
    if (paramValidatedInfo == null) {
      paramValidatedInfo = new ValidatedInfo();
    } else {
      paramValidatedInfo.memberType = null;
    } 
    boolean bool = (paramValidationContext == null || paramValidationContext.needToNormalize()) ? true : false;
    Object object = getActualValue(paramObject, paramValidationContext, paramValidatedInfo, bool);
    validate(paramValidationContext, paramValidatedInfo);
    return object;
  }
  
  public void validate(ValidationContext paramValidationContext, ValidatedInfo paramValidatedInfo) throws InvalidDatatypeValueException {
    if (paramValidationContext == null)
      paramValidationContext = fEmptyContext; 
    if (paramValidationContext.needFacetChecking() && this.fFacetsDefined != 0 && this.fFacetsDefined != 16)
      checkFacets(paramValidatedInfo); 
    if (paramValidationContext.needExtraChecking())
      checkExtraRules(paramValidationContext, paramValidatedInfo); 
  }
  
  private void checkFacets(ValidatedInfo paramValidatedInfo) throws InvalidDatatypeValueException {
    Object object = paramValidatedInfo.actualValue;
    String str = paramValidatedInfo.normalizedValue;
    short s = paramValidatedInfo.actualValueType;
    ShortList shortList = paramValidatedInfo.itemValueTypes;
    if (this.fValidationDV != 18 && this.fValidationDV != 20) {
      int i = fDVs[this.fValidationDV].getDataLength(object);
      if ((this.fFacetsDefined & 0x4) != 0 && i > this.fMaxLength)
        throw new InvalidDatatypeValueException("cvc-maxLength-valid", new Object[] { str, Integer.toString(i), Integer.toString(this.fMaxLength), this.fTypeName }); 
      if ((this.fFacetsDefined & 0x2) != 0 && i < this.fMinLength)
        throw new InvalidDatatypeValueException("cvc-minLength-valid", new Object[] { str, Integer.toString(i), Integer.toString(this.fMinLength), this.fTypeName }); 
      if ((this.fFacetsDefined & 0x1) != 0 && i != this.fLength)
        throw new InvalidDatatypeValueException("cvc-length-valid", new Object[] { str, Integer.toString(i), Integer.toString(this.fLength), this.fTypeName }); 
    } 
    if ((this.fFacetsDefined & 0x800) != 0) {
      boolean bool = false;
      int i = this.fEnumeration.size();
      short s1 = convertToPrimitiveKind(s);
      for (byte b = 0; b < i; b++) {
        short s2 = convertToPrimitiveKind(this.fEnumerationType[b]);
        if ((s1 == s2 || (s1 == 1 && s2 == 2) || (s1 == 2 && s2 == 1)) && this.fEnumeration.elementAt(b).equals(object))
          if (s1 == 44 || s1 == 43) {
            ShortList shortList1 = this.fEnumerationItemType[b];
            byte b1 = (shortList != null) ? shortList.getLength() : 0;
            byte b2 = (shortList1 != null) ? shortList1.getLength() : 0;
            if (b1 == b2) {
              byte b3 = 0;
              while (b3 < b1) {
                short s3 = convertToPrimitiveKind(shortList.item(b3));
                short s4 = convertToPrimitiveKind(shortList1.item(b3));
                if (s3 == s4 || (s3 == 1 && s4 == 2) || (s3 == 2 && s4 == 1)) {
                  b3++;
                  continue;
                } 
                break;
              } 
              if (b3 == b1) {
                bool = true;
                break;
              } 
            } 
          } else {
            bool = true;
            break;
          }  
      } 
      if (!bool)
        throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { str, this.fEnumeration.toString() }); 
    } 
    if ((this.fFacetsDefined & 0x400) != 0) {
      int i = fDVs[this.fValidationDV].getFractionDigits(object);
      if (i > this.fFractionDigits)
        throw new InvalidDatatypeValueException("cvc-fractionDigits-valid", new Object[] { str, Integer.toString(i), Integer.toString(this.fFractionDigits) }); 
    } 
    if ((this.fFacetsDefined & 0x200) != 0) {
      int i = fDVs[this.fValidationDV].getTotalDigits(object);
      if (i > this.fTotalDigits)
        throw new InvalidDatatypeValueException("cvc-totalDigits-valid", new Object[] { str, Integer.toString(i), Integer.toString(this.fTotalDigits) }); 
    } 
    if ((this.fFacetsDefined & 0x20) != 0) {
      int i = fDVs[this.fValidationDV].compare(object, this.fMaxInclusive);
      if (i != -1 && i != 0)
        throw new InvalidDatatypeValueException("cvc-maxInclusive-valid", new Object[] { str, this.fMaxInclusive, this.fTypeName }); 
    } 
    if ((this.fFacetsDefined & 0x40) != 0) {
      int i = fDVs[this.fValidationDV].compare(object, this.fMaxExclusive);
      if (i != -1)
        throw new InvalidDatatypeValueException("cvc-maxExclusive-valid", new Object[] { str, this.fMaxExclusive, this.fTypeName }); 
    } 
    if ((this.fFacetsDefined & 0x100) != 0) {
      int i = fDVs[this.fValidationDV].compare(object, this.fMinInclusive);
      if (i != 1 && i != 0)
        throw new InvalidDatatypeValueException("cvc-minInclusive-valid", new Object[] { str, this.fMinInclusive, this.fTypeName }); 
    } 
    if ((this.fFacetsDefined & 0x80) != 0) {
      int i = fDVs[this.fValidationDV].compare(object, this.fMinExclusive);
      if (i != 1)
        throw new InvalidDatatypeValueException("cvc-minExclusive-valid", new Object[] { str, this.fMinExclusive, this.fTypeName }); 
    } 
  }
  
  private void checkExtraRules(ValidationContext paramValidationContext, ValidatedInfo paramValidatedInfo) throws InvalidDatatypeValueException {
    Object object = paramValidatedInfo.actualValue;
    if (this.fVariety == 1) {
      fDVs[this.fValidationDV].checkExtraRules(object, paramValidationContext);
    } else if (this.fVariety == 2) {
      ListDV.ListData listData = (ListDV.ListData)object;
      int i = listData.getLength();
      if (this.fItemType.fVariety == 3) {
        XSSimpleTypeDecl[] arrayOfXSSimpleTypeDecl = (XSSimpleTypeDecl[])paramValidatedInfo.memberTypes;
        XSSimpleType xSSimpleType = paramValidatedInfo.memberType;
        for (int j = i - 1; j >= 0; j--) {
          paramValidatedInfo.actualValue = listData.item(j);
          paramValidatedInfo.memberType = arrayOfXSSimpleTypeDecl[j];
          this.fItemType.checkExtraRules(paramValidationContext, paramValidatedInfo);
        } 
        paramValidatedInfo.memberType = xSSimpleType;
      } else {
        for (int j = i - 1; j >= 0; j--) {
          paramValidatedInfo.actualValue = listData.item(j);
          this.fItemType.checkExtraRules(paramValidationContext, paramValidatedInfo);
        } 
      } 
      paramValidatedInfo.actualValue = listData;
    } else {
      ((XSSimpleTypeDecl)paramValidatedInfo.memberType).checkExtraRules(paramValidationContext, paramValidatedInfo);
    } 
  }
  
  private Object getActualValue(Object paramObject, ValidationContext paramValidationContext, ValidatedInfo paramValidatedInfo, boolean paramBoolean) throws InvalidDatatypeValueException {
    String str;
    if (paramBoolean) {
      str = normalize(paramObject, this.fWhiteSpace);
    } else {
      str = paramObject.toString();
    } 
    if ((this.fFacetsDefined & 0x8) != 0)
      for (int i = this.fPattern.size() - 1; i >= 0; i--) {
        RegularExpression regularExpression = this.fPattern.elementAt(i);
        if (!regularExpression.matches(str))
          throw new InvalidDatatypeValueException("cvc-pattern-valid", new Object[] { paramObject, this.fPatternStr.elementAt(i), this.fTypeName }); 
      }  
    if (this.fVariety == 1) {
      if (this.fPatternType != 0) {
        boolean bool = false;
        if (this.fPatternType == 1) {
          bool = !XMLChar.isValidNmtoken(str) ? true : false;
        } else if (this.fPatternType == 2) {
          bool = !XMLChar.isValidName(str) ? true : false;
        } else if (this.fPatternType == 3) {
          bool = !XMLChar.isValidNCName(str) ? true : false;
        } 
        if (bool)
          throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { str, SPECIAL_PATTERN_STRING[this.fPatternType] }); 
      } 
      paramValidatedInfo.normalizedValue = str;
      Object object = fDVs[this.fValidationDV].getActualValue(str, paramValidationContext);
      paramValidatedInfo.actualValue = object;
      paramValidatedInfo.actualValueType = this.fBuiltInKind;
      return object;
    } 
    if (this.fVariety == 2) {
      StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
      int i = stringTokenizer.countTokens();
      Object[] arrayOfObject = new Object[i];
      boolean bool = (this.fItemType.getVariety() == 3) ? true : false;
      short[] arrayOfShort = new short[bool ? i : 1];
      if (!bool)
        arrayOfShort[0] = this.fItemType.fBuiltInKind; 
      XSSimpleTypeDecl[] arrayOfXSSimpleTypeDecl = new XSSimpleTypeDecl[i];
      for (byte b = 0; b < i; b++) {
        arrayOfObject[b] = this.fItemType.getActualValue(stringTokenizer.nextToken(), paramValidationContext, paramValidatedInfo, false);
        if (paramValidationContext.needFacetChecking() && this.fItemType.fFacetsDefined != 0 && this.fItemType.fFacetsDefined != 16)
          this.fItemType.checkFacets(paramValidatedInfo); 
        arrayOfXSSimpleTypeDecl[b] = (XSSimpleTypeDecl)paramValidatedInfo.memberType;
        if (bool)
          arrayOfShort[b] = (arrayOfXSSimpleTypeDecl[b]).fBuiltInKind; 
      } 
      ListDV.ListData listData = new ListDV.ListData(arrayOfObject);
      paramValidatedInfo.actualValue = listData;
      paramValidatedInfo.actualValueType = bool ? 43 : 44;
      paramValidatedInfo.memberType = null;
      paramValidatedInfo.memberTypes = (XSSimpleType[])arrayOfXSSimpleTypeDecl;
      paramValidatedInfo.itemValueTypes = (ShortList)new ShortListImpl(arrayOfShort, arrayOfShort.length);
      paramValidatedInfo.normalizedValue = str;
      return listData;
    } 
    byte b1 = 0;
    while (b1 < this.fMemberTypes.length) {
      try {
        Object object = this.fMemberTypes[b1].getActualValue(paramObject, paramValidationContext, paramValidatedInfo, true);
        if (paramValidationContext.needFacetChecking() && (this.fMemberTypes[b1]).fFacetsDefined != 0 && (this.fMemberTypes[b1]).fFacetsDefined != 16)
          this.fMemberTypes[b1].checkFacets(paramValidatedInfo); 
        paramValidatedInfo.memberType = this.fMemberTypes[b1];
        return object;
      } catch (InvalidDatatypeValueException invalidDatatypeValueException) {
        b1++;
      } 
    } 
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b2 = 0; b2 < this.fMemberTypes.length; b2++) {
      if (b2 != 0)
        stringBuffer.append(" | "); 
      XSSimpleTypeDecl xSSimpleTypeDecl = this.fMemberTypes[b2];
      if (xSSimpleTypeDecl.fTargetNamespace != null) {
        stringBuffer.append('{');
        stringBuffer.append(xSSimpleTypeDecl.fTargetNamespace);
        stringBuffer.append('}');
      } 
      stringBuffer.append(xSSimpleTypeDecl.fTypeName);
      if (xSSimpleTypeDecl.fEnumeration != null) {
        Vector vector = xSSimpleTypeDecl.fEnumeration;
        stringBuffer.append(" : [");
        for (byte b = 0; b < vector.size(); b++) {
          if (b != 0)
            stringBuffer.append(','); 
          stringBuffer.append(vector.elementAt(b));
        } 
        stringBuffer.append(']');
      } 
    } 
    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { paramObject, this.fTypeName, stringBuffer.toString() });
  }
  
  public boolean isEqual(Object paramObject1, Object paramObject2) {
    return (paramObject1 == null) ? false : paramObject1.equals(paramObject2);
  }
  
  public boolean isIdentical(Object paramObject1, Object paramObject2) {
    return (paramObject1 == null) ? false : fDVs[this.fValidationDV].isIdentical(paramObject1, paramObject2);
  }
  
  public static String normalize(String paramString, short paramShort) {
    byte b = (paramString == null) ? 0 : paramString.length();
    if (!b || paramShort == 0)
      return paramString; 
    StringBuffer stringBuffer = new StringBuffer();
    if (paramShort == 1) {
      for (byte b1 = 0; b1 < b; b1++) {
        char c = paramString.charAt(b1);
        if (c != '\t' && c != '\n' && c != '\r') {
          stringBuffer.append(c);
        } else {
          stringBuffer.append(' ');
        } 
      } 
    } else {
      boolean bool = true;
      for (byte b1 = 0; b1 < b; b1++) {
        char c = paramString.charAt(b1);
        if (c != '\t' && c != '\n' && c != '\r' && c != ' ') {
          stringBuffer.append(c);
          bool = false;
        } else {
          while (b1 < b - 1) {
            c = paramString.charAt(b1 + 1);
            if (c != '\t' && c != '\n' && c != '\r' && c != ' ')
              break; 
            b1++;
          } 
          if (b1 < b - 1 && !bool)
            stringBuffer.append(' '); 
        } 
      } 
    } 
    return stringBuffer.toString();
  }
  
  protected String normalize(Object paramObject, short paramShort) {
    if (paramObject == null)
      return null; 
    if ((this.fFacetsDefined & 0x8) == 0) {
      short s = fDVNormalizeType[this.fValidationDV];
      if (s == 0)
        return paramObject.toString(); 
      if (s == 1)
        return XMLChar.trim(paramObject.toString()); 
    } 
    if (!(paramObject instanceof StringBuffer)) {
      String str = paramObject.toString();
      return normalize(str, paramShort);
    } 
    StringBuffer stringBuffer = (StringBuffer)paramObject;
    int i = stringBuffer.length();
    if (i == 0)
      return ""; 
    if (paramShort == 0)
      return stringBuffer.toString(); 
    if (paramShort == 1) {
      for (byte b = 0; b < i; b++) {
        char c = stringBuffer.charAt(b);
        if (c == '\t' || c == '\n' || c == '\r')
          stringBuffer.setCharAt(b, ' '); 
      } 
    } else {
      byte b2 = 0;
      boolean bool = true;
      for (byte b1 = 0; b1 < i; b1++) {
        char c = stringBuffer.charAt(b1);
        if (c != '\t' && c != '\n' && c != '\r' && c != ' ') {
          stringBuffer.setCharAt(b2++, c);
          bool = false;
        } else {
          while (b1 < i - 1) {
            c = stringBuffer.charAt(b1 + 1);
            if (c != '\t' && c != '\n' && c != '\r' && c != ' ')
              break; 
            b1++;
          } 
          if (b1 < i - 1 && !bool)
            stringBuffer.setCharAt(b2++, ' '); 
        } 
      } 
      stringBuffer.setLength(b2);
    } 
    return stringBuffer.toString();
  }
  
  void reportError(String paramString, Object[] paramArrayOfObject) throws InvalidDatatypeFacetException {
    throw new InvalidDatatypeFacetException(paramString, paramArrayOfObject);
  }
  
  private String whiteSpaceValue(short paramShort) {
    return WS_FACET_STRING[paramShort];
  }
  
  public short getOrdered() {
    return this.fOrdered;
  }
  
  public boolean getBounded() {
    return this.fBounded;
  }
  
  public boolean getFinite() {
    return this.fFinite;
  }
  
  public boolean getNumeric() {
    return this.fNumeric;
  }
  
  public boolean isDefinedFacet(short paramShort) {
    return ((this.fFacetsDefined & paramShort) != 0) ? true : ((this.fPatternType != 0) ? ((paramShort == 8)) : ((this.fValidationDV == 24) ? ((paramShort == 8 || paramShort == 1024)) : false));
  }
  
  public short getDefinedFacets() {
    return (this.fPatternType != 0) ? (short)(this.fFacetsDefined | 0x8) : ((this.fValidationDV == 24) ? (short)(this.fFacetsDefined | 0x8 | 0x400) : this.fFacetsDefined);
  }
  
  public boolean isFixedFacet(short paramShort) {
    return ((this.fFixedFacet & paramShort) != 0) ? true : ((this.fValidationDV == 24) ? ((paramShort == 1024)) : false);
  }
  
  public short getFixedFacets() {
    return (this.fValidationDV == 24) ? (short)(this.fFixedFacet | 0x400) : this.fFixedFacet;
  }
  
  public String getLexicalFacetValue(short paramShort) {
    switch (paramShort) {
      case 1:
        return (this.fLength == -1) ? null : Integer.toString(this.fLength);
      case 2:
        return (this.fMinLength == -1) ? null : Integer.toString(this.fMinLength);
      case 4:
        return (this.fMaxLength == -1) ? null : Integer.toString(this.fMaxLength);
      case 16:
        return WS_FACET_STRING[this.fWhiteSpace];
      case 32:
        return (this.fMaxInclusive == null) ? null : this.fMaxInclusive.toString();
      case 64:
        return (this.fMaxExclusive == null) ? null : this.fMaxExclusive.toString();
      case 128:
        return (this.fMinExclusive == null) ? null : this.fMinExclusive.toString();
      case 256:
        return (this.fMinInclusive == null) ? null : this.fMinInclusive.toString();
      case 512:
        return (this.fTotalDigits == -1) ? null : Integer.toString(this.fTotalDigits);
      case 1024:
        return (this.fValidationDV == 24) ? "0" : ((this.fFractionDigits == -1) ? null : Integer.toString(this.fFractionDigits));
    } 
    return null;
  }
  
  public StringList getLexicalEnumeration() {
    if (this.fLexicalEnumeration == null) {
      if (this.fEnumeration == null)
        return StringListImpl.EMPTY_LIST; 
      int i = this.fEnumeration.size();
      String[] arrayOfString = new String[i];
      for (byte b = 0; b < i; b++)
        arrayOfString[b] = this.fEnumeration.elementAt(b).toString(); 
      this.fLexicalEnumeration = (StringList)new StringListImpl(arrayOfString, i);
    } 
    return this.fLexicalEnumeration;
  }
  
  public ObjectList getActualEnumeration() {
    if (this.fActualEnumeration == null)
      this.fActualEnumeration = new ObjectList(this) {
          private final XSSimpleTypeDecl this$0;
          
          public int getLength() {
            return (this.this$0.fEnumeration != null) ? this.this$0.fEnumeration.size() : 0;
          }
          
          public boolean contains(Object param1Object) {
            return (this.this$0.fEnumeration != null && this.this$0.fEnumeration.contains(param1Object));
          }
          
          public Object item(int param1Int) {
            return (param1Int < 0 || param1Int >= getLength()) ? null : this.this$0.fEnumeration.elementAt(param1Int);
          }
        }; 
    return this.fActualEnumeration;
  }
  
  public ObjectList getEnumerationItemTypeList() {
    if (this.fEnumerationItemTypeList == null) {
      if (this.fEnumerationItemType == null)
        return null; 
      this.fEnumerationItemTypeList = new ObjectList(this) {
          private final XSSimpleTypeDecl this$0;
          
          public int getLength() {
            return (this.this$0.fEnumerationItemType != null) ? this.this$0.fEnumerationItemType.length : 0;
          }
          
          public boolean contains(Object param1Object) {
            if (this.this$0.fEnumerationItemType == null || !(param1Object instanceof ShortList))
              return false; 
            for (byte b = 0; b < this.this$0.fEnumerationItemType.length; b++) {
              if (this.this$0.fEnumerationItemType[b] == param1Object)
                return true; 
            } 
            return false;
          }
          
          public Object item(int param1Int) {
            return (param1Int < 0 || param1Int >= getLength()) ? null : this.this$0.fEnumerationItemType[param1Int];
          }
        };
    } 
    return this.fEnumerationItemTypeList;
  }
  
  public ShortList getEnumerationTypeList() {
    if (this.fEnumerationTypeList == null) {
      if (this.fEnumerationType == null)
        return ShortListImpl.EMPTY_LIST; 
      this.fEnumerationTypeList = (ShortList)new ShortListImpl(this.fEnumerationType, this.fEnumerationType.length);
    } 
    return this.fEnumerationTypeList;
  }
  
  public StringList getLexicalPattern() {
    if (this.fPatternType == 0 && this.fValidationDV != 24 && this.fPatternStr == null)
      return StringListImpl.EMPTY_LIST; 
    if (this.fLexicalPattern == null) {
      String[] arrayOfString;
      byte b1 = (this.fPatternStr == null) ? 0 : this.fPatternStr.size();
      if (this.fPatternType == 1) {
        arrayOfString = new String[b1 + 1];
        arrayOfString[b1] = "\\c+";
      } else if (this.fPatternType == 2) {
        arrayOfString = new String[b1 + 1];
        arrayOfString[b1] = "\\i\\c*";
      } else if (this.fPatternType == 3) {
        arrayOfString = new String[b1 + 2];
        arrayOfString[b1] = "\\i\\c*";
        arrayOfString[b1 + 1] = "[\\i-[:]][\\c-[:]]*";
      } else if (this.fValidationDV == 24) {
        arrayOfString = new String[b1 + 1];
        arrayOfString[b1] = "[\\-+]?[0-9]+";
      } else {
        arrayOfString = new String[b1];
      } 
      for (byte b2 = 0; b2 < b1; b2++)
        arrayOfString[b2] = this.fPatternStr.elementAt(b2); 
      this.fLexicalPattern = (StringList)new StringListImpl(arrayOfString, arrayOfString.length);
    } 
    return this.fLexicalPattern;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
  }
  
  private void calcFundamentalFacets() {
    setOrdered();
    setNumeric();
    setBounded();
    setCardinality();
  }
  
  private void setOrdered() {
    if (this.fVariety == 1) {
      this.fOrdered = this.fBase.fOrdered;
    } else if (this.fVariety == 2) {
      this.fOrdered = 0;
    } else if (this.fVariety == 3) {
      int i = this.fMemberTypes.length;
      if (i == 0) {
        this.fOrdered = 1;
        return;
      } 
      short s = getPrimitiveDV((this.fMemberTypes[0]).fValidationDV);
      boolean bool1 = (s != 0) ? true : false;
      boolean bool2 = ((this.fMemberTypes[0]).fOrdered == 0) ? true : false;
      for (byte b = 1; b < this.fMemberTypes.length && (bool1 || bool2); b++) {
        if (bool1)
          bool1 = (s == getPrimitiveDV((this.fMemberTypes[b]).fValidationDV)) ? true : false; 
        if (bool2)
          bool2 = ((this.fMemberTypes[b]).fOrdered == 0) ? true : false; 
      } 
      if (bool1) {
        this.fOrdered = (this.fMemberTypes[0]).fOrdered;
      } else if (bool2) {
        this.fOrdered = 0;
      } else {
        this.fOrdered = 1;
      } 
    } 
  }
  
  private void setNumeric() {
    if (this.fVariety == 1) {
      this.fNumeric = this.fBase.fNumeric;
    } else if (this.fVariety == 2) {
      this.fNumeric = false;
    } else if (this.fVariety == 3) {
      XSSimpleTypeDecl[] arrayOfXSSimpleTypeDecl = this.fMemberTypes;
      for (byte b = 0; b < arrayOfXSSimpleTypeDecl.length; b++) {
        if (!arrayOfXSSimpleTypeDecl[b].getNumeric()) {
          this.fNumeric = false;
          return;
        } 
      } 
      this.fNumeric = true;
    } 
  }
  
  private void setBounded() {
    if (this.fVariety == 1) {
      if (((this.fFacetsDefined & 0x100) != 0 || (this.fFacetsDefined & 0x80) != 0) && ((this.fFacetsDefined & 0x20) != 0 || (this.fFacetsDefined & 0x40) != 0)) {
        this.fBounded = true;
      } else {
        this.fBounded = false;
      } 
    } else if (this.fVariety == 2) {
      if ((this.fFacetsDefined & 0x1) != 0 || ((this.fFacetsDefined & 0x2) != 0 && (this.fFacetsDefined & 0x4) != 0)) {
        this.fBounded = true;
      } else {
        this.fBounded = false;
      } 
    } else if (this.fVariety == 3) {
      XSSimpleTypeDecl[] arrayOfXSSimpleTypeDecl = this.fMemberTypes;
      short s = 0;
      if (arrayOfXSSimpleTypeDecl.length > 0)
        s = getPrimitiveDV((arrayOfXSSimpleTypeDecl[0]).fValidationDV); 
      for (byte b = 0; b < arrayOfXSSimpleTypeDecl.length; b++) {
        if (!arrayOfXSSimpleTypeDecl[b].getBounded() || s != getPrimitiveDV((arrayOfXSSimpleTypeDecl[b]).fValidationDV)) {
          this.fBounded = false;
          return;
        } 
      } 
      this.fBounded = true;
    } 
  }
  
  private boolean specialCardinalityCheck() {
    return (this.fBase.fValidationDV == 9 || this.fBase.fValidationDV == 10 || this.fBase.fValidationDV == 11 || this.fBase.fValidationDV == 12 || this.fBase.fValidationDV == 13 || this.fBase.fValidationDV == 14);
  }
  
  private void setCardinality() {
    if (this.fVariety == 1) {
      if (this.fBase.fFinite) {
        this.fFinite = true;
      } else if ((this.fFacetsDefined & 0x1) != 0 || (this.fFacetsDefined & 0x4) != 0 || (this.fFacetsDefined & 0x200) != 0) {
        this.fFinite = true;
      } else if (((this.fFacetsDefined & 0x100) != 0 || (this.fFacetsDefined & 0x80) != 0) && ((this.fFacetsDefined & 0x20) != 0 || (this.fFacetsDefined & 0x40) != 0)) {
        if ((this.fFacetsDefined & 0x400) != 0 || specialCardinalityCheck()) {
          this.fFinite = true;
        } else {
          this.fFinite = false;
        } 
      } else {
        this.fFinite = false;
      } 
    } else if (this.fVariety == 2) {
      if ((this.fFacetsDefined & 0x1) != 0 || ((this.fFacetsDefined & 0x2) != 0 && (this.fFacetsDefined & 0x4) != 0)) {
        this.fFinite = true;
      } else {
        this.fFinite = false;
      } 
    } else if (this.fVariety == 3) {
      XSSimpleTypeDecl[] arrayOfXSSimpleTypeDecl = this.fMemberTypes;
      for (byte b = 0; b < arrayOfXSSimpleTypeDecl.length; b++) {
        if (!arrayOfXSSimpleTypeDecl[b].getFinite()) {
          this.fFinite = false;
          return;
        } 
      } 
      this.fFinite = true;
    } 
  }
  
  private short getPrimitiveDV(short paramShort) {
    return (paramShort == 21 || paramShort == 22 || paramShort == 23) ? 1 : ((paramShort == 24) ? 3 : paramShort);
  }
  
  public boolean derivedFromType(XSTypeDefinition paramXSTypeDefinition, short paramShort) {
    XSTypeDefinition xSTypeDefinition;
    if (paramXSTypeDefinition == null)
      return false; 
    if (paramXSTypeDefinition.getBaseType() == paramXSTypeDefinition)
      return true; 
    XSSimpleTypeDecl xSSimpleTypeDecl = this;
    while (xSSimpleTypeDecl != paramXSTypeDefinition && xSSimpleTypeDecl != fAnySimpleType)
      xSTypeDefinition = xSSimpleTypeDecl.getBaseType(); 
    return (xSTypeDefinition == paramXSTypeDefinition);
  }
  
  public boolean derivedFrom(String paramString1, String paramString2, short paramShort) {
    XSTypeDefinition xSTypeDefinition;
    if (paramString2 == null)
      return false; 
    if ("http://www.w3.org/2001/XMLSchema".equals(paramString1) && "anyType".equals(paramString2))
      return true; 
    XSSimpleTypeDecl xSSimpleTypeDecl = this;
    while ((!paramString2.equals(xSSimpleTypeDecl.getName()) || ((paramString1 != null || xSSimpleTypeDecl.getNamespace() != null) && (paramString1 == null || !paramString1.equals(xSSimpleTypeDecl.getNamespace())))) && xSSimpleTypeDecl != fAnySimpleType)
      xSTypeDefinition = xSSimpleTypeDecl.getBaseType(); 
    return (xSTypeDefinition != fAnySimpleType);
  }
  
  public boolean isDOMDerivedFrom(String paramString1, String paramString2, int paramInt) {
    return (paramString2 == null) ? false : ((SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(paramString1) && "anyType".equals(paramString2) && ((paramInt & 0x1) != 0 || paramInt == 0)) ? true : (((paramInt & 0x1) != 0 && isDerivedByRestriction(paramString1, paramString2, (XSTypeDefinition)this)) ? true : (((paramInt & 0x8) != 0 && isDerivedByList(paramString1, paramString2, (XSTypeDefinition)this)) ? true : (((paramInt & 0x4) != 0 && isDerivedByUnion(paramString1, paramString2, (XSTypeDefinition)this)) ? true : (((paramInt & 0x2) != 0 && (paramInt & 0x1) == 0 && (paramInt & 0x8) == 0 && (paramInt & 0x4) == 0) ? false : (((paramInt & 0x2) == 0 && (paramInt & 0x1) == 0 && (paramInt & 0x8) == 0 && (paramInt & 0x4) == 0) ? isDerivedByAny(paramString1, paramString2, (XSTypeDefinition)this) : false))))));
  }
  
  private boolean isDerivedByAny(String paramString1, String paramString2, XSTypeDefinition paramXSTypeDefinition) {
    boolean bool = false;
    XSTypeDefinition xSTypeDefinition = null;
    while (paramXSTypeDefinition != null && paramXSTypeDefinition != xSTypeDefinition) {
      if (paramString2.equals(paramXSTypeDefinition.getName()) && ((paramString1 == null && paramXSTypeDefinition.getNamespace() == null) || (paramString1 != null && paramString1.equals(paramXSTypeDefinition.getNamespace())))) {
        bool = true;
        break;
      } 
      if (isDerivedByRestriction(paramString1, paramString2, paramXSTypeDefinition))
        return true; 
      if (isDerivedByList(paramString1, paramString2, paramXSTypeDefinition))
        return true; 
      if (isDerivedByUnion(paramString1, paramString2, paramXSTypeDefinition))
        return true; 
      xSTypeDefinition = paramXSTypeDefinition;
      if (((XSSimpleTypeDecl)paramXSTypeDefinition).getVariety() == 0 || ((XSSimpleTypeDecl)paramXSTypeDefinition).getVariety() == 1) {
        paramXSTypeDefinition = paramXSTypeDefinition.getBaseType();
        continue;
      } 
      if (((XSSimpleTypeDecl)paramXSTypeDefinition).getVariety() == 3) {
        byte b = 0;
        if (b >= ((XSSimpleTypeDecl)paramXSTypeDefinition).getMemberTypes().getLength())
          continue; 
        return isDerivedByAny(paramString1, paramString2, (XSTypeDefinition)((XSSimpleTypeDecl)paramXSTypeDefinition).getMemberTypes().item(b));
      } 
      if (((XSSimpleTypeDecl)paramXSTypeDefinition).getVariety() == 2)
        XSSimpleTypeDefinition xSSimpleTypeDefinition = ((XSSimpleTypeDecl)paramXSTypeDefinition).getItemType(); 
    } 
    return bool;
  }
  
  private boolean isDerivedByRestriction(String paramString1, String paramString2, XSTypeDefinition paramXSTypeDefinition) {
    XSTypeDefinition xSTypeDefinition = null;
    while (paramXSTypeDefinition != null && paramXSTypeDefinition != xSTypeDefinition) {
      if (paramString2.equals(paramXSTypeDefinition.getName()) && ((paramString1 != null && paramString1.equals(paramXSTypeDefinition.getNamespace())) || (paramXSTypeDefinition.getNamespace() == null && paramString1 == null)))
        return true; 
      xSTypeDefinition = paramXSTypeDefinition;
      paramXSTypeDefinition = paramXSTypeDefinition.getBaseType();
    } 
    return false;
  }
  
  private boolean isDerivedByList(String paramString1, String paramString2, XSTypeDefinition paramXSTypeDefinition) {
    if (paramXSTypeDefinition != null && ((XSSimpleTypeDefinition)paramXSTypeDefinition).getVariety() == 2) {
      XSSimpleTypeDefinition xSSimpleTypeDefinition = ((XSSimpleTypeDefinition)paramXSTypeDefinition).getItemType();
      if (xSSimpleTypeDefinition != null && isDerivedByRestriction(paramString1, paramString2, (XSTypeDefinition)xSSimpleTypeDefinition))
        return true; 
    } 
    return false;
  }
  
  private boolean isDerivedByUnion(String paramString1, String paramString2, XSTypeDefinition paramXSTypeDefinition) {
    if (paramXSTypeDefinition != null && ((XSSimpleTypeDefinition)paramXSTypeDefinition).getVariety() == 3) {
      XSObjectList xSObjectList = ((XSSimpleTypeDefinition)paramXSTypeDefinition).getMemberTypes();
      for (byte b = 0; b < xSObjectList.getLength(); b++) {
        if (xSObjectList.item(b) != null && isDerivedByRestriction(paramString1, paramString2, (XSTypeDefinition)xSObjectList.item(b)))
          return true; 
      } 
    } 
    return false;
  }
  
  public void reset() {
    if (this.fIsImmutable)
      return; 
    this.fItemType = null;
    this.fMemberTypes = null;
    this.fTypeName = null;
    this.fTargetNamespace = null;
    this.fFinalSet = 0;
    this.fBase = null;
    this.fVariety = -1;
    this.fValidationDV = -1;
    this.fFacetsDefined = 0;
    this.fFixedFacet = 0;
    this.fWhiteSpace = 0;
    this.fLength = -1;
    this.fMinLength = -1;
    this.fMaxLength = -1;
    this.fTotalDigits = -1;
    this.fFractionDigits = -1;
    this.fPattern = null;
    this.fPatternStr = null;
    this.fEnumeration = null;
    this.fEnumerationType = null;
    this.fEnumerationItemType = null;
    this.fLexicalPattern = null;
    this.fLexicalEnumeration = null;
    this.fMaxInclusive = null;
    this.fMaxExclusive = null;
    this.fMinExclusive = null;
    this.fMinInclusive = null;
    this.lengthAnnotation = null;
    this.minLengthAnnotation = null;
    this.maxLengthAnnotation = null;
    this.whiteSpaceAnnotation = null;
    this.totalDigitsAnnotation = null;
    this.fractionDigitsAnnotation = null;
    this.patternAnnotations = null;
    this.enumerationAnnotations = null;
    this.maxInclusiveAnnotation = null;
    this.maxExclusiveAnnotation = null;
    this.minInclusiveAnnotation = null;
    this.minExclusiveAnnotation = null;
    this.fPatternType = 0;
    this.fAnnotations = null;
    this.fFacets = null;
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return null;
  }
  
  public String toString() {
    return this.fTargetNamespace + "," + this.fTypeName;
  }
  
  public XSObjectList getFacets() {
    if (this.fFacets == null && (this.fFacetsDefined != 0 || this.fValidationDV == 24)) {
      XSFacetImpl[] arrayOfXSFacetImpl = new XSFacetImpl[10];
      byte b = 0;
      if ((this.fFacetsDefined & 0x10) != 0) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)16, WS_FACET_STRING[this.fWhiteSpace], ((this.fFixedFacet & 0x10) != 0), this.whiteSpaceAnnotation);
        b++;
      } 
      if (this.fLength != -1) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)1, Integer.toString(this.fLength), ((this.fFixedFacet & 0x1) != 0), this.lengthAnnotation);
        b++;
      } 
      if (this.fMinLength != -1) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)2, Integer.toString(this.fMinLength), ((this.fFixedFacet & 0x2) != 0), this.minLengthAnnotation);
        b++;
      } 
      if (this.fMaxLength != -1) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)4, Integer.toString(this.fMaxLength), ((this.fFixedFacet & 0x4) != 0), this.maxLengthAnnotation);
        b++;
      } 
      if (this.fTotalDigits != -1) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)512, Integer.toString(this.fTotalDigits), ((this.fFixedFacet & 0x200) != 0), this.totalDigitsAnnotation);
        b++;
      } 
      if (this.fValidationDV == 24) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)1024, "0", true, this.fractionDigitsAnnotation);
        b++;
      } else if (this.fFractionDigits != -1) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)1024, Integer.toString(this.fFractionDigits), ((this.fFixedFacet & 0x400) != 0), this.fractionDigitsAnnotation);
        b++;
      } 
      if (this.fMaxInclusive != null) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)32, this.fMaxInclusive.toString(), ((this.fFixedFacet & 0x20) != 0), this.maxInclusiveAnnotation);
        b++;
      } 
      if (this.fMaxExclusive != null) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)64, this.fMaxExclusive.toString(), ((this.fFixedFacet & 0x40) != 0), this.maxExclusiveAnnotation);
        b++;
      } 
      if (this.fMinExclusive != null) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)128, this.fMinExclusive.toString(), ((this.fFixedFacet & 0x80) != 0), this.minExclusiveAnnotation);
        b++;
      } 
      if (this.fMinInclusive != null) {
        arrayOfXSFacetImpl[b] = new XSFacetImpl((short)256, this.fMinInclusive.toString(), ((this.fFixedFacet & 0x100) != 0), this.minInclusiveAnnotation);
        b++;
      } 
      this.fFacets = new XSObjectListImpl((XSObject[])arrayOfXSFacetImpl, b);
    } 
    return (this.fFacets != null) ? (XSObjectList)this.fFacets : XSObjectListImpl.EMPTY_LIST;
  }
  
  public XSObjectList getMultiValueFacets() {
    if (this.fMultiValueFacets == null && ((this.fFacetsDefined & 0x800) != 0 || (this.fFacetsDefined & 0x8) != 0 || this.fPatternType != 0 || this.fValidationDV == 24)) {
      XSMVFacetImpl[] arrayOfXSMVFacetImpl = new XSMVFacetImpl[2];
      byte b = 0;
      if ((this.fFacetsDefined & 0x8) != 0 || this.fPatternType != 0 || this.fValidationDV == 24) {
        arrayOfXSMVFacetImpl[b] = new XSMVFacetImpl((short)8, getLexicalPattern(), (XSObjectList)this.patternAnnotations);
        b++;
      } 
      if (this.fEnumeration != null) {
        arrayOfXSMVFacetImpl[b] = new XSMVFacetImpl((short)2048, getLexicalEnumeration(), this.enumerationAnnotations);
        b++;
      } 
      this.fMultiValueFacets = new XSObjectListImpl((XSObject[])arrayOfXSMVFacetImpl, b);
    } 
    return (this.fMultiValueFacets != null) ? (XSObjectList)this.fMultiValueFacets : XSObjectListImpl.EMPTY_LIST;
  }
  
  public Object getMinInclusiveValue() {
    return this.fMinInclusive;
  }
  
  public Object getMinExclusiveValue() {
    return this.fMinExclusive;
  }
  
  public Object getMaxInclusiveValue() {
    return this.fMaxInclusive;
  }
  
  public Object getMaxExclusiveValue() {
    return this.fMaxExclusive;
  }
  
  public void setAnonymous(boolean paramBoolean) {
    this.fAnonymous = paramBoolean;
  }
  
  public String getTypeNamespace() {
    return getNamespace();
  }
  
  public boolean isDerivedFrom(String paramString1, String paramString2, int paramInt) {
    return isDOMDerivedFrom(paramString1, paramString2, paramInt);
  }
  
  private short convertToPrimitiveKind(short paramShort) {
    return (paramShort <= 20) ? paramShort : ((paramShort <= 29) ? 2 : ((paramShort <= 42) ? 4 : paramShort));
  }
  
  private static final class XSMVFacetImpl implements XSMultiValueFacet {
    final short kind;
    
    final XSObjectList annotations;
    
    final StringList values;
    
    public XSMVFacetImpl(short param1Short, StringList param1StringList, XSObjectList param1XSObjectList) {
      this.kind = param1Short;
      this.values = param1StringList;
      this.annotations = (param1XSObjectList != null) ? param1XSObjectList : XSObjectListImpl.EMPTY_LIST;
    }
    
    public short getFacetKind() {
      return this.kind;
    }
    
    public XSObjectList getAnnotations() {
      return this.annotations;
    }
    
    public StringList getLexicalFacetValues() {
      return this.values;
    }
    
    public String getName() {
      return null;
    }
    
    public String getNamespace() {
      return null;
    }
    
    public XSNamespaceItem getNamespaceItem() {
      return null;
    }
    
    public short getType() {
      return 14;
    }
  }
  
  private static final class XSFacetImpl implements XSFacet {
    final short kind;
    
    final String value;
    
    final boolean fixed;
    
    final XSObjectList annotations;
    
    public XSFacetImpl(short param1Short, String param1String, boolean param1Boolean, XSAnnotation param1XSAnnotation) {
      this.kind = param1Short;
      this.value = param1String;
      this.fixed = param1Boolean;
      if (param1XSAnnotation != null) {
        this.annotations = (XSObjectList)new XSObjectListImpl();
        ((XSObjectListImpl)this.annotations).add((XSObject)param1XSAnnotation);
      } else {
        this.annotations = XSObjectListImpl.EMPTY_LIST;
      } 
    }
    
    public XSAnnotation getAnnotation() {
      return (XSAnnotation)this.annotations.item(0);
    }
    
    public XSObjectList getAnnotations() {
      return this.annotations;
    }
    
    public short getFacetKind() {
      return this.kind;
    }
    
    public String getLexicalFacetValue() {
      return this.value;
    }
    
    public boolean getFixed() {
      return this.fixed;
    }
    
    public String getName() {
      return null;
    }
    
    public String getNamespace() {
      return null;
    }
    
    public XSNamespaceItem getNamespaceItem() {
      return null;
    }
    
    public short getType() {
      return 13;
    }
  }
  
  static final class ValidationContextImpl implements ValidationContext {
    final ValidationContext fExternal;
    
    NamespaceContext fNSContext;
    
    ValidationContextImpl(ValidationContext param1ValidationContext) {
      this.fExternal = param1ValidationContext;
    }
    
    void setNSContext(NamespaceContext param1NamespaceContext) {
      this.fNSContext = param1NamespaceContext;
    }
    
    public boolean needFacetChecking() {
      return this.fExternal.needFacetChecking();
    }
    
    public boolean needExtraChecking() {
      return this.fExternal.needExtraChecking();
    }
    
    public boolean needToNormalize() {
      return this.fExternal.needToNormalize();
    }
    
    public boolean useNamespaces() {
      return true;
    }
    
    public boolean isEntityDeclared(String param1String) {
      return this.fExternal.isEntityDeclared(param1String);
    }
    
    public boolean isEntityUnparsed(String param1String) {
      return this.fExternal.isEntityUnparsed(param1String);
    }
    
    public boolean isIdDeclared(String param1String) {
      return this.fExternal.isIdDeclared(param1String);
    }
    
    public void addId(String param1String) {
      this.fExternal.addId(param1String);
    }
    
    public void addIdRef(String param1String) {
      this.fExternal.addIdRef(param1String);
    }
    
    public String getSymbol(String param1String) {
      return this.fExternal.getSymbol(param1String);
    }
    
    public String getURI(String param1String) {
      return (this.fNSContext == null) ? this.fExternal.getURI(param1String) : this.fNSContext.getURI(param1String);
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\XSSimpleTypeDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */