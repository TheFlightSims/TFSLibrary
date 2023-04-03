/*     */ package com.ctc.wstx.cfg;
/*     */ 
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ 
/*     */ public class ErrorConsts implements XMLStreamConstants {
/*  18 */   public static String WT_ENT_DECL = "entity declaration";
/*     */   
/*  19 */   public static String WT_ELEM_DECL = "element declaration";
/*     */   
/*  20 */   public static String WT_ATTR_DECL = "attribute declaration";
/*     */   
/*  21 */   public static String WT_XML_DECL = "xml declaration";
/*     */   
/*  22 */   public static String WT_DT_DECL = "doctype declaration";
/*     */   
/*  23 */   public static String WT_NS_DECL = "namespace declaration";
/*     */   
/*  29 */   public static String WT_VALIDATION = "schema validation";
/*     */   
/*  33 */   public static String W_UNDEFINED_ELEM = "Undefined element \"{0}\"; referred to by attribute(s)";
/*     */   
/*  34 */   public static String W_MIXED_ENCODINGS = "Inconsistent text encoding; declared as \"{0}\" in xml declaration, application had passed \"{1}\"";
/*     */   
/*  35 */   public static String W_MISSING_DTD = "Missing DOCTYPE declaration in validating mode; can not validate elements or attributes";
/*     */   
/*  36 */   public static String W_DTD_DUP_ATTR = "Attribute \"{0}\" (for element <{1}>) declared multiple times";
/*     */   
/*  37 */   public static String W_DTD_ATTR_REDECL = "Attribute \"{0}\" already declared for element <{1}>; ignoring re-declaration";
/*     */   
/*  41 */   public static String ERR_INTERNAL = "Internal error";
/*     */   
/*  42 */   public static String ERR_NULL_ARG = "Illegal to pass null as argument";
/*     */   
/*  43 */   public static String ERR_UNKNOWN_FEATURE = "Unrecognized feature \"{0}\"";
/*     */   
/*  47 */   public static String ERR_STATE_NOT_STELEM = "Current event not START_ELEMENT";
/*     */   
/*  48 */   public static String ERR_STATE_NOT_ELEM = "Current event not START_ELEMENT or END_ELEMENT";
/*     */   
/*  49 */   public static String ERR_STATE_NOT_PI = "Current event not PROCESSING_INSTRUCTION";
/*     */   
/*  50 */   public static String ERR_STATE_NOT_ELEM_OR_TEXT = "Current event ({0}) not START_ELEMENT, END_ELEMENT, CHARACTERS or CDATA";
/*     */   
/*  54 */   public static String ERR_XML_10_VS_11 = "XML 1.0 document can not refer to XML 1.1 parsed external entities";
/*     */   
/*  58 */   public static String ERR_DTD_IN_EPILOG = "Can not have DOCTYPE declaration in epilog";
/*     */   
/*  59 */   public static String ERR_DTD_DUP = "Duplicate DOCTYPE declaration";
/*     */   
/*  60 */   public static String ERR_CDATA_IN_EPILOG = " (CDATA not allowed in prolog/epilog)";
/*     */   
/*  64 */   public static String ERR_HYPHENS_IN_COMMENT = "String '--' not allowed in comment (missing '>'?)";
/*     */   
/*  65 */   public static String ERR_BRACKET_IN_TEXT = "String ']]>' not allowed in textual content, except as the end marker of CDATA section";
/*     */   
/*  69 */   public static String ERR_UNEXP_KEYWORD = "Unexpected keyword \"{0}\"; expected \"{1}\"";
/*     */   
/*  71 */   public static String ERR_WF_PI_MISSING_TARGET = "Missing processing instruction target";
/*     */   
/*  72 */   public static String ERR_WF_PI_XML_TARGET = "Illegal processing instruction target (\"{0}\"); 'xml' (case insensitive) is reserved by the specs.";
/*     */   
/*  73 */   public static String ERR_WF_PI_XML_MISSING_SPACE = "excepted either space or \"?>\" after PI target";
/*     */   
/*  77 */   public static String ERR_WF_ENTITY_EXT_DECLARED = "Entity \"{0}\" declared externally, but referenced from a document declared 'standalone=\"yes\"'";
/*     */   
/*  79 */   public static String ERR_WF_GE_UNDECLARED = "Undeclared general entity \"{0}\"";
/*     */   
/*  81 */   public static String ERR_WF_GE_UNDECLARED_SA = "Undeclared general entity \"{0}\" (document in stand-alone mode; perhaps declared externally?)";
/*     */   
/*  85 */   public static String ERR_NS_UNDECLARED = "Undeclared namespace prefix \"{0}\"";
/*     */   
/*  86 */   public static String ERR_NS_UNDECLARED_FOR_ATTR = "Undeclared namespace prefix \"{0}\" (for attribute \"{1}\")";
/*     */   
/*  88 */   public static String ERR_NS_REDECL_XML = "Trying to redeclare prefix 'xml' from its default URI 'http://www.w3.org/XML/1998/namespace' to \"{0}\"";
/*     */   
/*  92 */   public static String ERR_NS_REDECL_XMLNS = "Trying to declare prefix 'xmlns' (illegal as per NS 1.1 #4)";
/*     */   
/*  94 */   public static String ERR_NS_REDECL_XML_URI = "Trying to bind URI 'http://www.w3.org/XML/1998/namespace to prefix \"{0}\" (can only bind to 'xml')";
/*     */   
/*  97 */   public static String ERR_NS_REDECL_XMLNS_URI = "Trying to bind URI 'http://www.w3.org/2000/xmlns/ to prefix \"{0}\" (can not be explicitly bound)";
/*     */   
/* 100 */   public static String ERR_NS_EMPTY = "Non-default namespace can not map to empty URI (as per Namespace 1.0 # 2) in XML 1.0 documents";
/*     */   
/* 106 */   public static String ERR_DTD_MAINLEVEL_KEYWORD = "; expected a keyword (ATTLIST, ELEMENT, ENTITY, NOTATION), comment, or conditional section";
/*     */   
/* 108 */   public static String ERR_DTD_ATTR_TYPE = "; expected one of type (CDATA, ID, IDREF, IDREFS, ENTITY, ENTITIES NOTATION, NMTOKEN or NMTOKENS)";
/*     */   
/* 110 */   public static String ERR_DTD_DEFAULT_TYPE = "; expected #REQUIRED, #IMPLIED or #FIXED";
/*     */   
/* 112 */   public static String ERR_DTD_ELEM_REDEFD = "Trying to redefine element \"{0}\" (originally defined at {1})";
/*     */   
/* 114 */   public static String ERR_DTD_NOTATION_REDEFD = "Trying to redefine notation \"{0}\" (originally defined at {1})";
/*     */   
/* 117 */   public static String ERR_DTD_UNDECLARED_ENTITY = "Undeclared {0} entity \"{1}\"";
/*     */   
/* 120 */   public static String ERR_DTD_XML_SPACE = "Attribute xml:space has to be defined of type enumerated, and have 1 or 2 values, 'default' and/or 'preserve'";
/*     */   
/* 122 */   public static String ERR_DTD_XML_ID = "Attribute xml:id has to have attribute type of ID, as per Xml:id specification";
/*     */   
/* 127 */   public static String ERR_VLD_UNKNOWN_ELEM = "Undefined element <{0}> encountered";
/*     */   
/* 129 */   public static String ERR_VLD_EMPTY = "Element <{0}> has EMPTY content specification; can not contain {1}";
/*     */   
/* 130 */   public static String ERR_VLD_NON_MIXED = "Element <{0}> has non-mixed content specification; can not contain non-white space text, or any CDATA sections";
/*     */   
/* 131 */   public static String ERR_VLD_ANY = "Element <{0}> has ANY content specification; can not contain {1}";
/*     */   
/* 132 */   public static String ERR_VLD_UNKNOWN_ATTR = "Element <{0}> has no attribute \"{1}\"";
/*     */   
/* 133 */   public static String ERR_VLD_WRONG_ROOT = "Unexpected root element <{0}>; expected <{0}> as per DOCTYPE declaration";
/*     */   
/* 137 */   public static String WERR_PROLOG_CDATA = "Trying to output a CDATA block outside main element tree (in prolog or epilog)";
/*     */   
/* 139 */   public static String WERR_PROLOG_NONWS_TEXT = "Trying to output non-whitespace characters outside main element tree (in prolog or epilog)";
/*     */   
/* 141 */   public static String WERR_PROLOG_SECOND_ROOT = "Trying to output second root, <{0}>";
/*     */   
/* 144 */   public static String WERR_CDATA_CONTENT = "Illegal input: CDATA block has embedded ']]>' in it (index {0})";
/*     */   
/* 146 */   public static String WERR_COMMENT_CONTENT = "Illegal input: comment content has embedded '--' in it (index {0})";
/*     */   
/* 149 */   public static String WERR_ATTR_NO_ELEM = "Trying to write an attribute when there is no open start element.";
/*     */   
/* 152 */   public static String WERR_NAME_EMPTY = "Illegal to pass empty name";
/*     */   
/* 154 */   public static String WERR_NAME_ILLEGAL_FIRST_CHAR = "Illegal first name character {0}";
/*     */   
/* 155 */   public static String WERR_NAME_ILLEGAL_CHAR = "Illegal name character {0}";
/*     */   
/*     */   public static String tokenTypeDesc(int type) {
/* 165 */     switch (type) {
/*     */       case 1:
/* 167 */         return "START_ELEMENT";
/*     */       case 2:
/* 169 */         return "END_ELEMENT";
/*     */       case 7:
/* 171 */         return "START_DOCUMENT";
/*     */       case 8:
/* 173 */         return "END_DOCUMENT";
/*     */       case 4:
/* 176 */         return "CHARACTERS";
/*     */       case 12:
/* 178 */         return "CDATA";
/*     */       case 6:
/* 180 */         return "SPACE";
/*     */       case 5:
/* 183 */         return "COMMENT";
/*     */       case 3:
/* 185 */         return "PROCESSING_INSTRUCTION";
/*     */       case 11:
/* 187 */         return "DTD";
/*     */       case 9:
/* 189 */         return "ENTITY_REFERENCE";
/*     */     } 
/* 191 */     return "[" + type + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\cfg\ErrorConsts.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */