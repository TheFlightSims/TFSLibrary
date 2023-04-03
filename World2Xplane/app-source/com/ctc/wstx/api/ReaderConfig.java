/*      */ package com.ctc.wstx.api;
/*      */ 
/*      */ import com.ctc.wstx.cfg.InputConfigFlags;
/*      */ import com.ctc.wstx.dtd.DTDEventListener;
/*      */ import com.ctc.wstx.ent.EntityDecl;
/*      */ import com.ctc.wstx.ent.IntEntity;
/*      */ import com.ctc.wstx.io.BufferRecycler;
/*      */ import com.ctc.wstx.util.ArgUtil;
/*      */ import com.ctc.wstx.util.DataUtil;
/*      */ import com.ctc.wstx.util.SymbolTable;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.net.URL;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import javax.xml.stream.XMLReporter;
/*      */ import javax.xml.stream.XMLResolver;
/*      */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*      */ 
/*      */ public final class ReaderConfig extends CommonConfig implements InputConfigFlags {
/*      */   static final int PROP_COALESCE_TEXT = 1;
/*      */   
/*      */   static final int PROP_NAMESPACE_AWARE = 2;
/*      */   
/*      */   static final int PROP_REPLACE_ENTITY_REFS = 3;
/*      */   
/*      */   static final int PROP_SUPPORT_EXTERNAL_ENTITIES = 4;
/*      */   
/*      */   static final int PROP_VALIDATE_AGAINST_DTD = 5;
/*      */   
/*      */   static final int PROP_SUPPORT_DTD = 6;
/*      */   
/*      */   public static final int PROP_EVENT_ALLOCATOR = 7;
/*      */   
/*      */   static final int PROP_WARNING_REPORTER = 8;
/*      */   
/*      */   static final int PROP_XML_RESOLVER = 9;
/*      */   
/*      */   static final int PROP_INTERN_NS_URIS = 20;
/*      */   
/*      */   static final int PROP_INTERN_NAMES = 21;
/*      */   
/*      */   static final int PROP_REPORT_CDATA = 22;
/*      */   
/*      */   static final int PROP_REPORT_PROLOG_WS = 23;
/*      */   
/*      */   static final int PROP_PRESERVE_LOCATION = 24;
/*      */   
/*      */   static final int PROP_AUTO_CLOSE_INPUT = 25;
/*      */   
/*      */   static final int PROP_SUPPORT_XMLID = 26;
/*      */   
/*      */   static final int PROP_DTD_OVERRIDE = 27;
/*      */   
/*      */   static final int PROP_NORMALIZE_LFS = 40;
/*      */   
/*      */   static final int PROP_CACHE_DTDS = 42;
/*      */   
/*      */   static final int PROP_CACHE_DTDS_BY_PUBLIC_ID = 43;
/*      */   
/*      */   static final int PROP_LAZY_PARSING = 44;
/*      */   
/*      */   static final int PROP_SUPPORT_DTDPP = 45;
/*      */   
/*      */   static final int PROP_TREAT_CHAR_REFS_AS_ENTS = 46;
/*      */   
/*      */   static final int PROP_INPUT_BUFFER_LENGTH = 50;
/*      */   
/*      */   static final int PROP_MIN_TEXT_SEGMENT = 52;
/*      */   
/*      */   static final int PROP_CUSTOM_INTERNAL_ENTITIES = 53;
/*      */   
/*      */   static final int PROP_DTD_RESOLVER = 54;
/*      */   
/*      */   static final int PROP_ENTITY_RESOLVER = 55;
/*      */   
/*      */   static final int PROP_UNDECLARED_ENTITY_RESOLVER = 56;
/*      */   
/*      */   static final int PROP_BASE_URL = 57;
/*      */   
/*      */   static final int PROP_INPUT_PARSING_MODE = 58;
/*      */   
/*      */   static final int MIN_INPUT_BUFFER_LENGTH = 8;
/*      */   
/*      */   static final int DTD_CACHE_SIZE_J2SE = 12;
/*      */   
/*      */   static final int DTD_CACHE_SIZE_J2ME = 5;
/*      */   
/*      */   static final int DEFAULT_SHORTEST_TEXT_SEGMENT = 64;
/*      */   
/*      */   static final int DEFAULT_FLAGS_FULL = 2973213;
/*      */   
/*      */   static final int DEFAULT_FLAGS_J2ME = 2973213;
/*      */   
/*  230 */   static final HashMap sProperties = new HashMap(64);
/*      */   
/*      */   final boolean mIsJ2MESubset;
/*      */   
/*      */   final SymbolTable mSymbols;
/*      */   
/*      */   int mConfigFlags;
/*      */   
/*      */   int mConfigFlagMods;
/*      */   
/*      */   static final int PROP_INTERN_NAMES_EXPLICIT = 26;
/*      */   
/*      */   static final int PROP_INTERN_NS_URIS_EXPLICIT = 27;
/*      */   
/*      */   int mInputBufferLen;
/*      */   
/*      */   int mMinTextSegmentLen;
/*      */   
/*      */   static {
/*  233 */     sProperties.put("javax.xml.stream.isCoalescing", DataUtil.Integer(1));
/*  235 */     sProperties.put("javax.xml.stream.isNamespaceAware", DataUtil.Integer(2));
/*  237 */     sProperties.put("javax.xml.stream.isReplacingEntityReferences", DataUtil.Integer(3));
/*  239 */     sProperties.put("javax.xml.stream.isSupportingExternalEntities", DataUtil.Integer(4));
/*  241 */     sProperties.put("javax.xml.stream.isValidating", DataUtil.Integer(5));
/*  243 */     sProperties.put("javax.xml.stream.supportDTD", DataUtil.Integer(6));
/*  247 */     sProperties.put("javax.xml.stream.allocator", DataUtil.Integer(7));
/*  249 */     sProperties.put("javax.xml.stream.reporter", DataUtil.Integer(8));
/*  251 */     sProperties.put("javax.xml.stream.resolver", DataUtil.Integer(9));
/*  255 */     sProperties.put("org.codehaus.stax2.internNames", DataUtil.Integer(21));
/*  257 */     sProperties.put("org.codehaus.stax2.internNsUris", DataUtil.Integer(20));
/*  259 */     sProperties.put("http://java.sun.com/xml/stream/properties/report-cdata-event", DataUtil.Integer(22));
/*  261 */     sProperties.put("org.codehaus.stax2.reportPrologWhitespace", DataUtil.Integer(23));
/*  263 */     sProperties.put("org.codehaus.stax2.preserveLocation", DataUtil.Integer(24));
/*  265 */     sProperties.put("org.codehaus.stax2.closeInputSource", DataUtil.Integer(25));
/*  267 */     sProperties.put("org.codehaus.stax2.supportXmlId", DataUtil.Integer(26));
/*  269 */     sProperties.put("org.codehaus.stax2.propDtdOverride", DataUtil.Integer(27));
/*  274 */     sProperties.put("com.ctc.wstx.cacheDTDs", DataUtil.Integer(42));
/*  276 */     sProperties.put("com.ctc.wstx.cacheDTDsByPublicId", DataUtil.Integer(43));
/*  278 */     sProperties.put("com.ctc.wstx.lazyParsing", DataUtil.Integer(44));
/*  280 */     sProperties.put("com.ctc.wstx.supportDTDPP", DataUtil.Integer(45));
/*  282 */     sProperties.put("com.ctc.wstx.treatCharRefsAsEnts", DataUtil.Integer(46));
/*  284 */     sProperties.put("com.ctc.wstx.normalizeLFs", DataUtil.Integer(40));
/*  290 */     sProperties.put("com.ctc.wstx.inputBufferLength", DataUtil.Integer(50));
/*  292 */     sProperties.put("com.ctc.wstx.minTextSegment", DataUtil.Integer(52));
/*  294 */     sProperties.put("com.ctc.wstx.customInternalEntities", DataUtil.Integer(53));
/*  296 */     sProperties.put("com.ctc.wstx.dtdResolver", DataUtil.Integer(54));
/*  298 */     sProperties.put("com.ctc.wstx.entityResolver", DataUtil.Integer(55));
/*  300 */     sProperties.put("com.ctc.wstx.undeclaredEntityResolver", DataUtil.Integer(56));
/*  302 */     sProperties.put("com.ctc.wstx.baseURL", DataUtil.Integer(57));
/*  304 */     sProperties.put("com.ctc.wstx.fragmentMode", DataUtil.Integer(58));
/*      */   }
/*      */   
/*  348 */   URL mBaseURL = null;
/*      */   
/*  355 */   WstxInputProperties.ParsingMode mParsingMode = WstxInputProperties.PARSING_MODE_DOCUMENT;
/*      */   
/*      */   boolean mXml11 = false;
/*      */   
/*      */   XMLReporter mReporter;
/*      */   
/*  375 */   XMLResolver mDtdResolver = null;
/*      */   
/*  376 */   XMLResolver mEntityResolver = null;
/*      */   
/*  388 */   Object[] mSpecialProperties = null;
/*      */   
/*      */   private static final int SPEC_PROC_COUNT = 4;
/*      */   
/*      */   private static final int SP_IX_CUSTOM_ENTITIES = 0;
/*      */   
/*      */   private static final int SP_IX_UNDECL_ENT_RESOLVER = 1;
/*      */   
/*      */   private static final int SP_IX_DTD_EVENT_LISTENER = 2;
/*      */   
/*      */   private static final int SP_IX_DTD_OVERRIDE = 3;
/*      */   
/*  408 */   static final ThreadLocal mRecyclerRef = new ThreadLocal();
/*      */   
/*  416 */   BufferRecycler mCurrRecycler = null;
/*      */   
/*      */   private ReaderConfig(ReaderConfig base, boolean j2meSubset, SymbolTable symbols, int configFlags, int configFlagMods, int inputBufLen, int minTextSegmentLen) {
/*  430 */     super(base);
/*  431 */     this.mIsJ2MESubset = j2meSubset;
/*  432 */     this.mSymbols = symbols;
/*  434 */     this.mConfigFlags = configFlags;
/*  435 */     this.mConfigFlagMods = configFlagMods;
/*  437 */     this.mInputBufferLen = inputBufLen;
/*  438 */     this.mMinTextSegmentLen = minTextSegmentLen;
/*  446 */     SoftReference ref = mRecyclerRef.get();
/*  447 */     if (ref != null)
/*  448 */       this.mCurrRecycler = ref.get(); 
/*      */   }
/*      */   
/*      */   public static ReaderConfig createJ2MEDefaults() {
/*  457 */     ReaderConfig rc = new ReaderConfig(null, true, null, 2973213, 0, 2000, 64);
/*  462 */     return rc;
/*      */   }
/*      */   
/*      */   public static ReaderConfig createFullDefaults() {
/*  470 */     ReaderConfig rc = new ReaderConfig(null, false, null, 2973213, 0, 4000, 64);
/*  475 */     return rc;
/*      */   }
/*      */   
/*      */   public ReaderConfig createNonShared(SymbolTable sym) {
/*  482 */     ReaderConfig rc = new ReaderConfig(this, this.mIsJ2MESubset, sym, this.mConfigFlags, this.mConfigFlagMods, this.mInputBufferLen, this.mMinTextSegmentLen);
/*  487 */     rc.mReporter = this.mReporter;
/*  488 */     rc.mDtdResolver = this.mDtdResolver;
/*  489 */     rc.mEntityResolver = this.mEntityResolver;
/*  490 */     rc.mBaseURL = this.mBaseURL;
/*  491 */     rc.mParsingMode = this.mParsingMode;
/*  492 */     if (this.mSpecialProperties != null) {
/*  493 */       int len = this.mSpecialProperties.length;
/*  494 */       Object[] specProps = new Object[len];
/*  495 */       System.arraycopy(this.mSpecialProperties, 0, specProps, 0, len);
/*  496 */       rc.mSpecialProperties = specProps;
/*      */     } 
/*  498 */     return rc;
/*      */   }
/*      */   
/*      */   public void resetState() {
/*  510 */     this.mXml11 = false;
/*      */   }
/*      */   
/*      */   protected int findPropertyId(String propName) {
/*  521 */     Integer I = (Integer)sProperties.get(propName);
/*  522 */     return (I == null) ? -1 : I.intValue();
/*      */   }
/*      */   
/*      */   public SymbolTable getSymbols() {
/*  533 */     return this.mSymbols;
/*      */   }
/*      */   
/*      */   public int getDtdCacheSize() {
/*  540 */     return this.mIsJ2MESubset ? 5 : 12;
/*      */   }
/*      */   
/*      */   public int getConfigFlags() {
/*  545 */     return this.mConfigFlags;
/*      */   }
/*      */   
/*      */   public boolean willCoalesceText() {
/*  550 */     return _hasConfigFlag(2);
/*      */   }
/*      */   
/*      */   public boolean willSupportNamespaces() {
/*  554 */     return _hasConfigFlag(1);
/*      */   }
/*      */   
/*      */   public boolean willReplaceEntityRefs() {
/*  558 */     return _hasConfigFlag(4);
/*      */   }
/*      */   
/*      */   public boolean willSupportExternalEntities() {
/*  562 */     return _hasConfigFlag(8);
/*      */   }
/*      */   
/*      */   public boolean willSupportDTDs() {
/*  566 */     return _hasConfigFlag(16);
/*      */   }
/*      */   
/*      */   public boolean willValidateWithDTD() {
/*  570 */     return _hasConfigFlag(32);
/*      */   }
/*      */   
/*      */   public boolean willReportCData() {
/*  576 */     return _hasConfigFlag(512);
/*      */   }
/*      */   
/*      */   public boolean willParseLazily() {
/*  580 */     return _hasConfigFlag(262144);
/*      */   }
/*      */   
/*      */   public boolean willInternNames() {
/*  584 */     return _hasConfigFlag(1024);
/*      */   }
/*      */   
/*      */   public boolean willInternNsURIs() {
/*  588 */     return _hasConfigFlag(2048);
/*      */   }
/*      */   
/*      */   public boolean willPreserveLocation() {
/*  592 */     return _hasConfigFlag(4096);
/*      */   }
/*      */   
/*      */   public boolean willAutoCloseInput() {
/*  596 */     return _hasConfigFlag(8192);
/*      */   }
/*      */   
/*      */   public boolean willReportPrologWhitespace() {
/*  602 */     return _hasConfigFlag(256);
/*      */   }
/*      */   
/*      */   public boolean willCacheDTDs() {
/*  606 */     return _hasConfigFlag(65536);
/*      */   }
/*      */   
/*      */   public boolean willCacheDTDsByPublicId() {
/*  610 */     return _hasConfigFlag(131072);
/*      */   }
/*      */   
/*      */   public boolean willDoXmlIdTyping() {
/*  614 */     return _hasConfigFlag(2097152);
/*      */   }
/*      */   
/*      */   public boolean willDoXmlIdUniqChecks() {
/*  618 */     return _hasConfigFlag(4194304);
/*      */   }
/*      */   
/*      */   public boolean willSupportDTDPP() {
/*  622 */     return _hasConfigFlag(524288);
/*      */   }
/*      */   
/*      */   public boolean willNormalizeLFs() {
/*  626 */     return _hasConfigFlag(16384);
/*      */   }
/*      */   
/*      */   public boolean willTreatCharRefsAsEnts() {
/*  630 */     return _hasConfigFlag(8388608);
/*      */   }
/*      */   
/*      */   public int getInputBufferLength() {
/*  633 */     return this.mInputBufferLen;
/*      */   }
/*      */   
/*      */   public int getShortestReportedTextSegment() {
/*  635 */     return this.mMinTextSegmentLen;
/*      */   }
/*      */   
/*      */   public Map getCustomInternalEntities() {
/*  639 */     Map custEnt = (Map)_getSpecialProperty(0);
/*  640 */     if (custEnt == null)
/*  641 */       return Collections.EMPTY_MAP; 
/*  644 */     int len = custEnt.size();
/*  645 */     HashMap m = new HashMap(len + (len >> 2), 0.81F);
/*  646 */     Iterator it = custEnt.entrySet().iterator();
/*  647 */     while (it.hasNext()) {
/*  648 */       Map.Entry me = it.next();
/*  652 */       m.put(me.getKey(), me.getValue());
/*      */     } 
/*  654 */     return m;
/*      */   }
/*      */   
/*      */   public EntityDecl findCustomInternalEntity(String id) {
/*  659 */     Map custEnt = (Map)_getSpecialProperty(0);
/*  660 */     if (custEnt == null)
/*  661 */       return null; 
/*  663 */     return (EntityDecl)custEnt.get(id);
/*      */   }
/*      */   
/*      */   public XMLReporter getXMLReporter() {
/*  666 */     return this.mReporter;
/*      */   }
/*      */   
/*      */   public XMLResolver getXMLResolver() {
/*  668 */     return this.mEntityResolver;
/*      */   }
/*      */   
/*      */   public XMLResolver getDtdResolver() {
/*  670 */     return this.mDtdResolver;
/*      */   }
/*      */   
/*      */   public XMLResolver getEntityResolver() {
/*  671 */     return this.mEntityResolver;
/*      */   }
/*      */   
/*      */   public XMLResolver getUndeclaredEntityResolver() {
/*  673 */     return (XMLResolver)_getSpecialProperty(1);
/*      */   }
/*      */   
/*      */   public URL getBaseURL() {
/*  676 */     return this.mBaseURL;
/*      */   }
/*      */   
/*      */   public WstxInputProperties.ParsingMode getInputParsingMode() {
/*  679 */     return this.mParsingMode;
/*      */   }
/*      */   
/*      */   public boolean inputParsingModeDocuments() {
/*  683 */     return (this.mParsingMode == WstxInputProperties.PARSING_MODE_DOCUMENTS);
/*      */   }
/*      */   
/*      */   public boolean inputParsingModeFragment() {
/*  687 */     return (this.mParsingMode == WstxInputProperties.PARSING_MODE_FRAGMENT);
/*      */   }
/*      */   
/*      */   public boolean isXml11() {
/*  696 */     return this.mXml11;
/*      */   }
/*      */   
/*      */   public DTDEventListener getDTDEventListener() {
/*  700 */     return (DTDEventListener)_getSpecialProperty(2);
/*      */   }
/*      */   
/*      */   public DTDValidationSchema getDTDOverride() {
/*  704 */     return (DTDValidationSchema)_getSpecialProperty(3);
/*      */   }
/*      */   
/*      */   public boolean hasInternNamesBeenEnabled() {
/*  713 */     return _hasExplicitConfigFlag(1024);
/*      */   }
/*      */   
/*      */   public boolean hasInternNsURIsBeenEnabled() {
/*  717 */     return _hasExplicitConfigFlag(2048);
/*      */   }
/*      */   
/*      */   public void setConfigFlag(int flag) {
/*  727 */     this.mConfigFlags |= flag;
/*  728 */     this.mConfigFlagMods |= flag;
/*      */   }
/*      */   
/*      */   public void clearConfigFlag(int flag) {
/*  732 */     this.mConfigFlags &= flag ^ 0xFFFFFFFF;
/*  733 */     this.mConfigFlagMods |= flag;
/*      */   }
/*      */   
/*      */   public void doCoalesceText(boolean state) {
/*  739 */     setConfigFlag(2, state);
/*      */   }
/*      */   
/*      */   public void doSupportNamespaces(boolean state) {
/*  743 */     setConfigFlag(1, state);
/*      */   }
/*      */   
/*      */   public void doReplaceEntityRefs(boolean state) {
/*  747 */     setConfigFlag(4, state);
/*      */   }
/*      */   
/*      */   public void doSupportExternalEntities(boolean state) {
/*  751 */     setConfigFlag(8, state);
/*      */   }
/*      */   
/*      */   public void doSupportDTDs(boolean state) {
/*  755 */     setConfigFlag(16, state);
/*      */   }
/*      */   
/*      */   public void doValidateWithDTD(boolean state) {
/*  759 */     setConfigFlag(32, state);
/*      */   }
/*      */   
/*      */   public void doInternNames(boolean state) {
/*  765 */     setConfigFlag(1024, state);
/*      */   }
/*      */   
/*      */   public void doInternNsURIs(boolean state) {
/*  769 */     setConfigFlag(2048, state);
/*      */   }
/*      */   
/*      */   public void doReportPrologWhitespace(boolean state) {
/*  773 */     setConfigFlag(256, state);
/*      */   }
/*      */   
/*      */   public void doReportCData(boolean state) {
/*  777 */     setConfigFlag(512, state);
/*      */   }
/*      */   
/*      */   public void doCacheDTDs(boolean state) {
/*  781 */     setConfigFlag(65536, state);
/*      */   }
/*      */   
/*      */   public void doCacheDTDsByPublicId(boolean state) {
/*  785 */     setConfigFlag(131072, state);
/*      */   }
/*      */   
/*      */   public void doParseLazily(boolean state) {
/*  789 */     setConfigFlag(262144, state);
/*      */   }
/*      */   
/*      */   public void doXmlIdTyping(boolean state) {
/*  793 */     setConfigFlag(2097152, state);
/*      */   }
/*      */   
/*      */   public void doXmlIdUniqChecks(boolean state) {
/*  797 */     setConfigFlag(4194304, state);
/*      */   }
/*      */   
/*      */   public void doPreserveLocation(boolean state) {
/*  801 */     setConfigFlag(4096, state);
/*      */   }
/*      */   
/*      */   public void doAutoCloseInput(boolean state) {
/*  805 */     setConfigFlag(8192, state);
/*      */   }
/*      */   
/*      */   public void doSupportDTDPP(boolean state) {
/*  809 */     setConfigFlag(524288, state);
/*      */   }
/*      */   
/*      */   public void doTreatCharRefsAsEnts(boolean state) {
/*  813 */     setConfigFlag(8388608, state);
/*      */   }
/*      */   
/*      */   public void doNormalizeLFs(boolean state) {
/*  817 */     setConfigFlag(16384, state);
/*      */   }
/*      */   
/*      */   public void setInputBufferLength(int value) {
/*  825 */     if (value < 8)
/*  826 */       value = 8; 
/*  828 */     this.mInputBufferLen = value;
/*      */   }
/*      */   
/*      */   public void setShortestReportedTextSegment(int value) {
/*  832 */     this.mMinTextSegmentLen = value;
/*      */   }
/*      */   
/*      */   public void setCustomInternalEntities(Map m) {
/*      */     Map entMap;
/*  838 */     if (m == null || m.size() < 1) {
/*  839 */       entMap = Collections.EMPTY_MAP;
/*      */     } else {
/*  841 */       int len = m.size();
/*  842 */       entMap = new HashMap(len + (len >> 1), 0.75F);
/*  843 */       Iterator it = m.entrySet().iterator();
/*  844 */       while (it.hasNext()) {
/*      */         char[] ch;
/*  845 */         Map.Entry me = it.next();
/*  846 */         Object val = me.getValue();
/*  848 */         if (val == null) {
/*  849 */           ch = DataUtil.getEmptyCharArray();
/*  850 */         } else if (val instanceof char[]) {
/*  851 */           ch = (char[])val;
/*      */         } else {
/*  854 */           String str = val.toString();
/*  855 */           ch = str.toCharArray();
/*      */         } 
/*  857 */         String name = (String)me.getKey();
/*  858 */         entMap.put(name, IntEntity.create(name, ch));
/*      */       } 
/*      */     } 
/*  861 */     _setSpecialProperty(0, entMap);
/*      */   }
/*      */   
/*      */   public void setXMLReporter(XMLReporter r) {
/*  865 */     this.mReporter = r;
/*      */   }
/*      */   
/*      */   public void setXMLResolver(XMLResolver r) {
/*  873 */     this.mEntityResolver = r;
/*  874 */     this.mDtdResolver = r;
/*      */   }
/*      */   
/*      */   public void setDtdResolver(XMLResolver r) {
/*  878 */     this.mDtdResolver = r;
/*      */   }
/*      */   
/*      */   public void setEntityResolver(XMLResolver r) {
/*  882 */     this.mEntityResolver = r;
/*      */   }
/*      */   
/*      */   public void setUndeclaredEntityResolver(XMLResolver r) {
/*  886 */     _setSpecialProperty(1, r);
/*      */   }
/*      */   
/*      */   public void setBaseURL(URL baseURL) {
/*  889 */     this.mBaseURL = baseURL;
/*      */   }
/*      */   
/*      */   public void setInputParsingMode(WstxInputProperties.ParsingMode mode) {
/*  892 */     this.mParsingMode = mode;
/*      */   }
/*      */   
/*      */   public void enableXml11(boolean state) {
/*  900 */     this.mXml11 = state;
/*      */   }
/*      */   
/*      */   public void setDTDEventListener(DTDEventListener l) {
/*  904 */     _setSpecialProperty(2, l);
/*      */   }
/*      */   
/*      */   public void setDTDOverride(DTDValidationSchema schema) {
/*  908 */     _setSpecialProperty(3, schema);
/*      */   }
/*      */   
/*      */   public void configureForXmlConformance() {
/*  936 */     doSupportNamespaces(true);
/*  937 */     doSupportDTDs(true);
/*  938 */     doSupportExternalEntities(true);
/*  939 */     doReplaceEntityRefs(true);
/*  944 */     doXmlIdTyping(true);
/*  945 */     doXmlIdUniqChecks(true);
/*      */   }
/*      */   
/*      */   public void configureForConvenience() {
/*  970 */     doCoalesceText(true);
/*  971 */     doReplaceEntityRefs(true);
/*  974 */     doReportCData(false);
/*  975 */     doReportPrologWhitespace(false);
/*  979 */     doPreserveLocation(true);
/*  986 */     doParseLazily(false);
/*      */   }
/*      */   
/*      */   public void configureForSpeed() {
/* 1020 */     doCoalesceText(false);
/* 1023 */     doPreserveLocation(false);
/* 1024 */     doReportPrologWhitespace(false);
/* 1026 */     doInternNsURIs(true);
/* 1027 */     doXmlIdUniqChecks(false);
/* 1030 */     doCacheDTDs(true);
/* 1031 */     doParseLazily(true);
/* 1038 */     setShortestReportedTextSegment(16);
/* 1039 */     setInputBufferLength(8000);
/*      */   }
/*      */   
/*      */   public void configureForLowMemUsage() {
/* 1069 */     doCoalesceText(false);
/* 1073 */     doPreserveLocation(false);
/* 1076 */     doCacheDTDs(false);
/* 1077 */     doParseLazily(true);
/* 1078 */     doXmlIdUniqChecks(false);
/* 1079 */     setShortestReportedTextSegment(64);
/* 1080 */     setInputBufferLength(512);
/*      */   }
/*      */   
/*      */   public void configureForRoundTripping() {
/* 1108 */     doCoalesceText(false);
/* 1109 */     doReplaceEntityRefs(false);
/* 1112 */     doReportCData(true);
/* 1113 */     doReportPrologWhitespace(true);
/* 1116 */     doTreatCharRefsAsEnts(true);
/* 1117 */     doNormalizeLFs(false);
/* 1120 */     setShortestReportedTextSegment(2147483647);
/*      */   }
/*      */   
/*      */   public char[] allocSmallCBuffer(int minSize) {
/* 1132 */     if (this.mCurrRecycler != null) {
/* 1133 */       char[] result = this.mCurrRecycler.getSmallCBuffer(minSize);
/* 1134 */       if (result != null)
/* 1135 */         return result; 
/*      */     } 
/* 1139 */     return new char[minSize];
/*      */   }
/*      */   
/*      */   public void freeSmallCBuffer(char[] buffer) {
/* 1146 */     if (this.mCurrRecycler == null)
/* 1147 */       this.mCurrRecycler = createRecycler(); 
/* 1149 */     this.mCurrRecycler.returnSmallCBuffer(buffer);
/*      */   }
/*      */   
/*      */   public char[] allocMediumCBuffer(int minSize) {
/* 1155 */     if (this.mCurrRecycler != null) {
/* 1156 */       char[] result = this.mCurrRecycler.getMediumCBuffer(minSize);
/* 1157 */       if (result != null)
/* 1158 */         return result; 
/*      */     } 
/* 1161 */     return new char[minSize];
/*      */   }
/*      */   
/*      */   public void freeMediumCBuffer(char[] buffer) {
/* 1167 */     if (this.mCurrRecycler == null)
/* 1168 */       this.mCurrRecycler = createRecycler(); 
/* 1170 */     this.mCurrRecycler.returnMediumCBuffer(buffer);
/*      */   }
/*      */   
/*      */   public char[] allocFullCBuffer(int minSize) {
/* 1176 */     if (this.mCurrRecycler != null) {
/* 1177 */       char[] result = this.mCurrRecycler.getFullCBuffer(minSize);
/* 1178 */       if (result != null)
/* 1179 */         return result; 
/*      */     } 
/* 1182 */     return new char[minSize];
/*      */   }
/*      */   
/*      */   public void freeFullCBuffer(char[] buffer) {
/* 1189 */     if (this.mCurrRecycler == null)
/* 1190 */       this.mCurrRecycler = createRecycler(); 
/* 1192 */     this.mCurrRecycler.returnFullCBuffer(buffer);
/*      */   }
/*      */   
/*      */   public byte[] allocFullBBuffer(int minSize) {
/* 1198 */     if (this.mCurrRecycler != null) {
/* 1199 */       byte[] result = this.mCurrRecycler.getFullBBuffer(minSize);
/* 1200 */       if (result != null)
/* 1201 */         return result; 
/*      */     } 
/* 1204 */     return new byte[minSize];
/*      */   }
/*      */   
/*      */   public void freeFullBBuffer(byte[] buffer) {
/* 1211 */     if (this.mCurrRecycler == null)
/* 1212 */       this.mCurrRecycler = createRecycler(); 
/* 1214 */     this.mCurrRecycler.returnFullBBuffer(buffer);
/*      */   }
/*      */   
/*      */   private BufferRecycler createRecycler() {
/* 1219 */     BufferRecycler recycler = new BufferRecycler();
/* 1222 */     mRecyclerRef.set(new SoftReference(recycler));
/* 1223 */     return recycler;
/*      */   }
/*      */   
/*      */   private void setConfigFlag(int flag, boolean state) {
/* 1234 */     if (state) {
/* 1235 */       this.mConfigFlags |= flag;
/*      */     } else {
/* 1237 */       this.mConfigFlags &= flag ^ 0xFFFFFFFF;
/*      */     } 
/* 1239 */     this.mConfigFlagMods |= flag;
/*      */   }
/*      */   
/*      */   public Object getProperty(int id) {
/* 1244 */     switch (id) {
/*      */       case 1:
/* 1248 */         return willCoalesceText() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 2:
/* 1250 */         return willSupportNamespaces() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 3:
/* 1252 */         return willReplaceEntityRefs() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 4:
/* 1254 */         return willSupportExternalEntities() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 5:
/* 1257 */         return willValidateWithDTD() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 6:
/* 1259 */         return willSupportDTDs() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 8:
/* 1261 */         return getXMLReporter();
/*      */       case 9:
/* 1263 */         return getXMLResolver();
/*      */       case 7:
/* 1268 */         return null;
/*      */       case 23:
/* 1273 */         return willReportPrologWhitespace() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 22:
/* 1275 */         return willReportCData() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 21:
/* 1278 */         return willInternNames() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 20:
/* 1280 */         return willInternNsURIs() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 24:
/* 1283 */         return willPreserveLocation() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 25:
/* 1285 */         return willAutoCloseInput() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 27:
/* 1288 */         return getDTDOverride();
/*      */       case 42:
/* 1294 */         return willCacheDTDs() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 43:
/* 1296 */         return willCacheDTDsByPublicId() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 44:
/* 1298 */         return willParseLazily() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 26:
/* 1301 */         if (!_hasConfigFlag(2097152))
/* 1302 */           return "disable"; 
/* 1304 */         return _hasConfigFlag(4194304) ? "xmlidFull" : "xmlidTyping";
/*      */       case 46:
/* 1310 */         return willTreatCharRefsAsEnts() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 40:
/* 1313 */         return willNormalizeLFs() ? Boolean.TRUE : Boolean.FALSE;
/*      */       case 50:
/* 1317 */         return DataUtil.Integer(getInputBufferLength());
/*      */       case 52:
/* 1319 */         return DataUtil.Integer(getShortestReportedTextSegment());
/*      */       case 53:
/* 1321 */         return getCustomInternalEntities();
/*      */       case 54:
/* 1323 */         return getDtdResolver();
/*      */       case 55:
/* 1325 */         return getEntityResolver();
/*      */       case 56:
/* 1327 */         return getUndeclaredEntityResolver();
/*      */       case 57:
/* 1329 */         return getBaseURL();
/*      */       case 58:
/* 1331 */         return getInputParsingMode();
/*      */     } 
/* 1334 */     throw new IllegalStateException("Internal error: no handler for property with internal id " + id + ".");
/*      */   }
/*      */   
/*      */   public boolean setProperty(String propName, int id, Object value) {
/*      */     boolean typing;
/*      */     URL u;
/*      */     boolean uniq;
/* 1340 */     switch (id) {
/*      */       case 1:
/* 1344 */         doCoalesceText(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 2:
/*      */         doSupportNamespaces(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 3:
/*      */         doReplaceEntityRefs(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 4:
/*      */         doSupportExternalEntities(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 6:
/*      */         doSupportDTDs(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 5:
/*      */         doValidateWithDTD(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 8:
/*      */         setXMLReporter((XMLReporter)value);
/* 1516 */         return true;
/*      */       case 9:
/*      */         setXMLResolver((XMLResolver)value);
/* 1516 */         return true;
/*      */       case 7:
/*      */         return false;
/*      */       case 20:
/*      */         doInternNsURIs(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 21:
/*      */         doInternNames(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 22:
/*      */         doReportCData(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 23:
/*      */         doReportPrologWhitespace(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 24:
/*      */         doPreserveLocation(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 25:
/*      */         doAutoCloseInput(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 26:
/*      */         if ("disable".equals(value)) {
/*      */           typing = uniq = false;
/*      */         } else if ("xmlidTyping".equals(value)) {
/*      */           typing = true;
/*      */           uniq = false;
/*      */         } else if ("xmlidFull".equals(value)) {
/*      */           typing = uniq = true;
/*      */         } else {
/*      */           throw new IllegalArgumentException("Illegal argument ('" + value + "') to set property " + "org.codehaus.stax2.supportXmlId" + " to: has to be one of '" + "disable" + "', '" + "xmlidTyping" + "' or '" + "xmlidFull" + "'");
/*      */         } 
/*      */         setConfigFlag(2097152, typing);
/*      */         setConfigFlag(4194304, uniq);
/* 1516 */         return true;
/*      */       case 27:
/*      */         setDTDOverride((DTDValidationSchema)value);
/* 1516 */         return true;
/*      */       case 42:
/*      */         doCacheDTDs(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 43:
/*      */         doCacheDTDsByPublicId(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 44:
/*      */         doParseLazily(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 46:
/*      */         doTreatCharRefsAsEnts(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 40:
/*      */         doNormalizeLFs(ArgUtil.convertToBoolean(propName, value));
/* 1516 */         return true;
/*      */       case 50:
/*      */         setInputBufferLength(ArgUtil.convertToInt(propName, value, 1));
/* 1516 */         return true;
/*      */       case 52:
/*      */         setShortestReportedTextSegment(ArgUtil.convertToInt(propName, value, 1));
/* 1516 */         return true;
/*      */       case 53:
/*      */         setCustomInternalEntities((Map)value);
/* 1516 */         return true;
/*      */       case 54:
/*      */         setDtdResolver((XMLResolver)value);
/* 1516 */         return true;
/*      */       case 55:
/*      */         setEntityResolver((XMLResolver)value);
/* 1516 */         return true;
/*      */       case 56:
/*      */         setUndeclaredEntityResolver((XMLResolver)value);
/* 1516 */         return true;
/*      */       case 57:
/*      */         if (value == null) {
/*      */           u = null;
/*      */         } else if (value instanceof URL) {
/*      */           u = (URL)value;
/*      */         } else {
/*      */           try {
/*      */             u = new URL(value.toString());
/*      */           } catch (Exception ioe) {
/*      */             Exception exception1;
/*      */             throw new IllegalArgumentException(exception1.getMessage(), exception1);
/*      */           } 
/*      */         } 
/*      */         setBaseURL(u);
/* 1516 */         return true;
/*      */       case 58:
/*      */         setInputParsingMode((WstxInputProperties.ParsingMode)value);
/* 1516 */         return true;
/*      */     } 
/*      */     throw new IllegalStateException("Internal error: no handler for property with internal id " + id + ".");
/*      */   }
/*      */   
/*      */   protected boolean _hasConfigFlag(int flag) {
/* 1520 */     return ((this.mConfigFlags & flag) != 0);
/*      */   }
/*      */   
/*      */   protected boolean _hasExplicitConfigFlag(int flag) {
/* 1529 */     return (_hasConfigFlag(flag) && (this.mConfigFlagMods & flag) != 0);
/*      */   }
/*      */   
/*      */   private final Object _getSpecialProperty(int ix) {
/* 1534 */     if (this.mSpecialProperties == null)
/* 1535 */       return null; 
/* 1537 */     return this.mSpecialProperties[ix];
/*      */   }
/*      */   
/*      */   private final void _setSpecialProperty(int ix, Object value) {
/* 1542 */     if (this.mSpecialProperties == null)
/* 1543 */       this.mSpecialProperties = new Object[4]; 
/* 1545 */     this.mSpecialProperties[ix] = value;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\api\ReaderConfig.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */