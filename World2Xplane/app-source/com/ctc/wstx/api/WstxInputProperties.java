/*     */ package com.ctc.wstx.api;
/*     */ 
/*     */ public final class WstxInputProperties {
/*     */   public static final String UNKNOWN_ATTR_TYPE = "CDATA";
/*     */   
/*     */   public static final String P_NORMALIZE_LFS = "com.ctc.wstx.normalizeLFs";
/*     */   
/*     */   public static final String P_VALIDATE_TEXT_CHARS = "com.ctc.wstx.validateTextChars";
/*     */   
/*     */   public static final String P_CACHE_DTDS = "com.ctc.wstx.cacheDTDs";
/*     */   
/*     */   public static final String P_CACHE_DTDS_BY_PUBLIC_ID = "com.ctc.wstx.cacheDTDsByPublicId";
/*     */   
/*     */   public static final String P_LAZY_PARSING = "com.ctc.wstx.lazyParsing";
/*     */   
/*     */   public static final String P_RETURN_NULL_FOR_DEFAULT_NAMESPACE = "com.ctc.wstx.returnNullForDefaultNamespace";
/*     */   
/*     */   public static final String P_SUPPORT_DTDPP = "com.ctc.wstx.supportDTDPP";
/*     */   
/*     */   public static final String P_TREAT_CHAR_REFS_AS_ENTS = "com.ctc.wstx.treatCharRefsAsEnts";
/*     */   
/*     */   public static final String P_INPUT_BUFFER_LENGTH = "com.ctc.wstx.inputBufferLength";
/*     */   
/*     */   public static final String P_MIN_TEXT_SEGMENT = "com.ctc.wstx.minTextSegment";
/*     */   
/*     */   public static final String P_CUSTOM_INTERNAL_ENTITIES = "com.ctc.wstx.customInternalEntities";
/*     */   
/*     */   public static final String P_DTD_RESOLVER = "com.ctc.wstx.dtdResolver";
/*     */   
/*     */   public static final String P_ENTITY_RESOLVER = "com.ctc.wstx.entityResolver";
/*     */   
/*     */   public static final String P_UNDECLARED_ENTITY_RESOLVER = "com.ctc.wstx.undeclaredEntityResolver";
/*     */   
/*     */   public static final String P_BASE_URL = "com.ctc.wstx.baseURL";
/*     */   
/*     */   public static final String P_INPUT_PARSING_MODE = "com.ctc.wstx.fragmentMode";
/*     */   
/* 252 */   public static final ParsingMode PARSING_MODE_DOCUMENT = new ParsingMode();
/*     */   
/* 253 */   public static final ParsingMode PARSING_MODE_FRAGMENT = new ParsingMode();
/*     */   
/* 254 */   public static final ParsingMode PARSING_MODE_DOCUMENTS = new ParsingMode();
/*     */   
/*     */   public static final class ParsingMode {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\api\WstxInputProperties.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */