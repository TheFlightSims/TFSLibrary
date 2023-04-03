/*     */ package com.ctc.wstx.api;
/*     */ 
/*     */ import com.ctc.wstx.cfg.OutputConfigFlags;
/*     */ import com.ctc.wstx.io.BufferRecycler;
/*     */ import com.ctc.wstx.util.ArgUtil;
/*     */ import com.ctc.wstx.util.DataUtil;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.HashMap;
/*     */ import javax.xml.stream.XMLReporter;
/*     */ import org.codehaus.stax2.io.EscapingWriterFactory;
/*     */ 
/*     */ public final class WriterConfig extends CommonConfig implements OutputConfigFlags {
/*     */   protected static final String DEFAULT_AUTOMATIC_NS_PREFIX = "wstxns";
/*     */   
/*     */   static final int PROP_AUTOMATIC_NS = 1;
/*     */   
/*     */   static final int PROP_AUTOMATIC_EMPTY_ELEMENTS = 2;
/*     */   
/*     */   static final int PROP_AUTO_CLOSE_OUTPUT = 3;
/*     */   
/*     */   static final int PROP_ENABLE_NS = 4;
/*     */   
/*     */   static final int PROP_AUTOMATIC_NS_PREFIX = 5;
/*     */   
/*     */   static final int PROP_TEXT_ESCAPER = 6;
/*     */   
/*     */   static final int PROP_ATTR_VALUE_ESCAPER = 7;
/*     */   
/*     */   static final int PROP_PROBLEM_REPORTER = 8;
/*     */   
/*     */   static final int PROP_OUTPUT_CDATA_AS_TEXT = 11;
/*     */   
/*     */   static final int PROP_COPY_DEFAULT_ATTRS = 12;
/*     */   
/*     */   static final int PROP_ESCAPE_CR = 13;
/*     */   
/*     */   static final int PROP_ADD_SPACE_AFTER_EMPTY_ELEM = 14;
/*     */   
/*     */   static final int PROP_AUTOMATIC_END_ELEMENTS = 15;
/*     */   
/*     */   static final int PROP_VALIDATE_STRUCTURE = 16;
/*     */   
/*     */   static final int PROP_VALIDATE_CONTENT = 17;
/*     */   
/*     */   static final int PROP_VALIDATE_ATTR = 18;
/*     */   
/*     */   static final int PROP_VALIDATE_NAMES = 19;
/*     */   
/*     */   static final int PROP_FIX_CONTENT = 20;
/*     */   
/*     */   static final int PROP_OUTPUT_INVALID_CHAR_HANDLER = 21;
/*     */   
/*     */   static final int PROP_OUTPUT_EMPTY_ELEMENT_HANDLER = 22;
/*     */   
/*     */   static final int PROP_UNDERLYING_STREAM = 30;
/*     */   
/*     */   static final int PROP_UNDERLYING_WRITER = 31;
/*     */   
/*     */   static final boolean DEFAULT_OUTPUT_CDATA_AS_TEXT = false;
/*     */   
/*     */   static final boolean DEFAULT_COPY_DEFAULT_ATTRS = false;
/*     */   
/*     */   static final boolean DEFAULT_ESCAPE_CR = true;
/*     */   
/*     */   static final boolean DEFAULT_ADD_SPACE_AFTER_EMPTY_ELEM = false;
/*     */   
/*     */   static final boolean DEFAULT_VALIDATE_STRUCTURE = true;
/*     */   
/*     */   static final boolean DEFAULT_VALIDATE_CONTENT = true;
/*     */   
/*     */   static final boolean DEFAULT_VALIDATE_ATTR = false;
/*     */   
/*     */   static final boolean DEFAULT_VALIDATE_NAMES = false;
/*     */   
/*     */   static final boolean DEFAULT_FIX_CONTENT = false;
/*     */   
/*     */   static final int DEFAULT_FLAGS_J2ME = 933;
/*     */   
/*     */   static final int DEFAULT_FLAGS_FULL = 933;
/*     */   
/* 164 */   static final HashMap sProperties = new HashMap(8);
/*     */   
/*     */   final boolean mIsJ2MESubset;
/*     */   
/*     */   protected int mConfigFlags;
/*     */   
/*     */   static {
/* 167 */     sProperties.put("javax.xml.stream.isRepairingNamespaces", DataUtil.Integer(1));
/* 173 */     sProperties.put("javax.xml.stream.isNamespaceAware", DataUtil.Integer(4));
/* 177 */     sProperties.put("org.codehaus.stax2.automaticEmptyElements", DataUtil.Integer(2));
/* 179 */     sProperties.put("org.codehaus.stax2.autoCloseOutput", DataUtil.Integer(3));
/* 182 */     sProperties.put("org.codehaus.stax2.automaticNsPrefix", DataUtil.Integer(5));
/* 185 */     sProperties.put("org.codehaus.stax2.textEscaper", DataUtil.Integer(6));
/* 187 */     sProperties.put("org.codehaus.stax2.attrValueEscaper", DataUtil.Integer(7));
/* 190 */     sProperties.put("javax.xml.stream.reporter", DataUtil.Integer(8));
/* 196 */     sProperties.put("com.ctc.wstx.outputCDataAsText", DataUtil.Integer(11));
/* 198 */     sProperties.put("com.ctc.wstx.copyDefaultAttrs", DataUtil.Integer(12));
/* 200 */     sProperties.put("com.ctc.wstx.outputEscapeCr", DataUtil.Integer(13));
/* 202 */     sProperties.put("com.ctc.wstx.addSpaceAfterEmptyElem", DataUtil.Integer(14));
/* 205 */     sProperties.put("com.ctc.wstx.automaticEndElements", DataUtil.Integer(15));
/* 207 */     sProperties.put("com.ctc.wstx.outputInvalidCharHandler", DataUtil.Integer(21));
/* 209 */     sProperties.put("com.ctc.wstx.outputEmptyElementHandler", DataUtil.Integer(22));
/* 213 */     sProperties.put("com.ctc.wstx.outputValidateStructure", DataUtil.Integer(16));
/* 215 */     sProperties.put("com.ctc.wstx.outputValidateContent", DataUtil.Integer(17));
/* 217 */     sProperties.put("com.ctc.wstx.outputValidateAttr", DataUtil.Integer(18));
/* 219 */     sProperties.put("com.ctc.wstx.outputValidateNames", DataUtil.Integer(19));
/* 221 */     sProperties.put("com.ctc.wstx.outputFixContent", DataUtil.Integer(20));
/* 225 */     sProperties.put("com.ctc.wstx.outputUnderlyingStream", DataUtil.Integer(30));
/* 227 */     sProperties.put("com.ctc.wstx.outputUnderlyingStream", DataUtil.Integer(30));
/*     */   }
/*     */   
/* 253 */   Object[] mSpecialProperties = null;
/*     */   
/*     */   private static final int SPEC_PROC_COUNT = 6;
/*     */   
/*     */   private static final int SP_IX_AUTO_NS_PREFIX = 0;
/*     */   
/*     */   private static final int SP_IX_TEXT_ESCAPER_FACTORY = 1;
/*     */   
/*     */   private static final int SP_IX_ATTR_VALUE_ESCAPER_FACTORY = 2;
/*     */   
/*     */   private static final int SP_IX_PROBLEM_REPORTER = 3;
/*     */   
/*     */   private static final int SP_IX_INVALID_CHAR_HANDLER = 4;
/*     */   
/*     */   private static final int SP_IX_EMPTY_ELEMENT_HANDLER = 5;
/*     */   
/* 275 */   static final ThreadLocal mRecyclerRef = new ThreadLocal();
/*     */   
/* 283 */   BufferRecycler mCurrRecycler = null;
/*     */   
/*     */   private WriterConfig(WriterConfig base, boolean j2meSubset, int flags, Object[] specProps) {
/* 294 */     super(base);
/* 295 */     this.mIsJ2MESubset = j2meSubset;
/* 296 */     this.mConfigFlags = flags;
/* 297 */     this.mSpecialProperties = specProps;
/* 305 */     SoftReference ref = mRecyclerRef.get();
/* 306 */     if (ref != null)
/* 307 */       this.mCurrRecycler = ref.get(); 
/*     */   }
/*     */   
/*     */   public static WriterConfig createJ2MEDefaults() {
/* 313 */     return new WriterConfig(null, true, 933, null);
/*     */   }
/*     */   
/*     */   public static WriterConfig createFullDefaults() {
/* 318 */     return new WriterConfig(null, false, 933, null);
/*     */   }
/*     */   
/*     */   public WriterConfig createNonShared() {
/*     */     Object[] specProps;
/* 325 */     if (this.mSpecialProperties != null) {
/* 326 */       int len = this.mSpecialProperties.length;
/* 327 */       specProps = new Object[len];
/* 328 */       System.arraycopy(this.mSpecialProperties, 0, specProps, 0, len);
/*     */     } else {
/* 330 */       specProps = null;
/*     */     } 
/* 332 */     return new WriterConfig(this, this.mIsJ2MESubset, this.mConfigFlags, specProps);
/*     */   }
/*     */   
/*     */   protected int findPropertyId(String propName) {
/* 343 */     Integer I = (Integer)sProperties.get(propName);
/* 344 */     return (I == null) ? -1 : I.intValue();
/*     */   }
/*     */   
/*     */   public Object getProperty(int id) {
/* 355 */     switch (id) {
/*     */       case 1:
/* 360 */         return automaticNamespacesEnabled() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 4:
/* 367 */         return willSupportNamespaces() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 8:
/* 369 */         return getProblemReporter();
/*     */       case 2:
/* 373 */         return automaticEmptyElementsEnabled() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 3:
/* 375 */         return willAutoCloseOutput() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 5:
/* 377 */         return getAutomaticNsPrefix();
/*     */       case 6:
/* 379 */         return getTextEscaperFactory();
/*     */       case 7:
/* 381 */         return getAttrValueEscaperFactory();
/*     */       case 11:
/* 386 */         return willOutputCDataAsText() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 12:
/* 388 */         return willCopyDefaultAttrs() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 13:
/* 390 */         return willEscapeCr() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 14:
/* 392 */         return willAddSpaceAfterEmptyElem() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 15:
/* 394 */         return automaticEndElementsEnabled() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 16:
/* 397 */         return willValidateStructure() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 17:
/* 399 */         return willValidateContent() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 18:
/* 401 */         return willValidateAttributes() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 19:
/* 403 */         return willValidateNames() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 20:
/* 405 */         return willFixContent() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 21:
/* 407 */         return getInvalidCharHandler();
/*     */       case 22:
/* 409 */         return getEmptyElementHandler();
/*     */       case 30:
/*     */       case 31:
/* 414 */         throw new IllegalStateException("Can not access per-stream-writer properties via factory");
/*     */     } 
/* 417 */     throw new IllegalStateException("Internal error: no handler for property with internal id " + id + ".");
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, int id, Object value) {
/* 426 */     switch (id) {
/*     */       case 1:
/* 430 */         enableAutomaticNamespaces(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 4:
/*     */         doSupportNamespaces(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 8:
/*     */         setProblemReporter((XMLReporter)value);
/* 510 */         return true;
/*     */       case 2:
/*     */         enableAutomaticEmptyElements(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 3:
/*     */         doAutoCloseOutput(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 5:
/*     */         setAutomaticNsPrefix(value.toString());
/* 510 */         return true;
/*     */       case 6:
/*     */         setTextEscaperFactory((EscapingWriterFactory)value);
/* 510 */         return true;
/*     */       case 7:
/*     */         setAttrValueEscaperFactory((EscapingWriterFactory)value);
/* 510 */         return true;
/*     */       case 11:
/*     */         doOutputCDataAsText(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 12:
/*     */         doCopyDefaultAttrs(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 13:
/*     */         doEscapeCr(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 14:
/*     */         doAddSpaceAfterEmptyElem(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 15:
/*     */         enableAutomaticEndElements(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 16:
/*     */         doValidateStructure(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 17:
/*     */         doValidateContent(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 18:
/*     */         doValidateAttributes(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 19:
/*     */         doValidateNames(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 20:
/*     */         doFixContent(ArgUtil.convertToBoolean(name, value));
/* 510 */         return true;
/*     */       case 21:
/*     */         setInvalidCharHandler((InvalidCharHandler)value);
/* 510 */         return true;
/*     */       case 22:
/*     */         setEmptyElementHandler((EmptyElementHandler)value);
/* 510 */         return true;
/*     */       case 30:
/*     */       case 31:
/*     */         throw new IllegalStateException("Can not modify per-stream-writer properties via factory");
/*     */     } 
/*     */     throw new IllegalStateException("Internal error: no handler for property with internal id " + id + ".");
/*     */   }
/*     */   
/*     */   public int getConfigFlags() {
/* 521 */     return this.mConfigFlags;
/*     */   }
/*     */   
/*     */   public boolean automaticNamespacesEnabled() {
/* 527 */     return hasConfigFlag(2);
/*     */   }
/*     */   
/*     */   public boolean automaticEmptyElementsEnabled() {
/* 533 */     return hasConfigFlag(4);
/*     */   }
/*     */   
/*     */   public boolean willAutoCloseOutput() {
/* 537 */     return hasConfigFlag(8192);
/*     */   }
/*     */   
/*     */   public boolean willSupportNamespaces() {
/* 541 */     return hasConfigFlag(1);
/*     */   }
/*     */   
/*     */   public boolean willOutputCDataAsText() {
/* 545 */     return hasConfigFlag(8);
/*     */   }
/*     */   
/*     */   public boolean willCopyDefaultAttrs() {
/* 549 */     return hasConfigFlag(16);
/*     */   }
/*     */   
/*     */   public boolean willEscapeCr() {
/* 553 */     return hasConfigFlag(32);
/*     */   }
/*     */   
/*     */   public boolean willAddSpaceAfterEmptyElem() {
/* 557 */     return hasConfigFlag(64);
/*     */   }
/*     */   
/*     */   public boolean automaticEndElementsEnabled() {
/* 561 */     return hasConfigFlag(128);
/*     */   }
/*     */   
/*     */   public boolean willValidateStructure() {
/* 565 */     return hasConfigFlag(256);
/*     */   }
/*     */   
/*     */   public boolean willValidateContent() {
/* 569 */     return hasConfigFlag(512);
/*     */   }
/*     */   
/*     */   public boolean willValidateAttributes() {
/* 573 */     return hasConfigFlag(2048);
/*     */   }
/*     */   
/*     */   public boolean willValidateNames() {
/* 577 */     return hasConfigFlag(1024);
/*     */   }
/*     */   
/*     */   public boolean willFixContent() {
/* 581 */     return hasConfigFlag(4096);
/*     */   }
/*     */   
/*     */   public String getAutomaticNsPrefix() {
/* 590 */     String prefix = (String)getSpecialProperty(0);
/* 591 */     if (prefix == null)
/* 592 */       prefix = "wstxns"; 
/* 594 */     return prefix;
/*     */   }
/*     */   
/*     */   public EscapingWriterFactory getTextEscaperFactory() {
/* 598 */     return (EscapingWriterFactory)getSpecialProperty(1);
/*     */   }
/*     */   
/*     */   public EscapingWriterFactory getAttrValueEscaperFactory() {
/* 602 */     return (EscapingWriterFactory)getSpecialProperty(2);
/*     */   }
/*     */   
/*     */   public XMLReporter getProblemReporter() {
/* 606 */     return (XMLReporter)getSpecialProperty(3);
/*     */   }
/*     */   
/*     */   public InvalidCharHandler getInvalidCharHandler() {
/* 610 */     return (InvalidCharHandler)getSpecialProperty(4);
/*     */   }
/*     */   
/*     */   public EmptyElementHandler getEmptyElementHandler() {
/* 614 */     return (EmptyElementHandler)getSpecialProperty(5);
/*     */   }
/*     */   
/*     */   public void enableAutomaticNamespaces(boolean state) {
/* 622 */     setConfigFlag(2, state);
/*     */   }
/*     */   
/*     */   public void enableAutomaticEmptyElements(boolean state) {
/* 628 */     setConfigFlag(4, state);
/*     */   }
/*     */   
/*     */   public void doAutoCloseOutput(boolean state) {
/* 632 */     setConfigFlag(8192, state);
/*     */   }
/*     */   
/*     */   public void doSupportNamespaces(boolean state) {
/* 636 */     setConfigFlag(1, state);
/*     */   }
/*     */   
/*     */   public void doOutputCDataAsText(boolean state) {
/* 640 */     setConfigFlag(8, state);
/*     */   }
/*     */   
/*     */   public void doCopyDefaultAttrs(boolean state) {
/* 644 */     setConfigFlag(16, state);
/*     */   }
/*     */   
/*     */   public void doEscapeCr(boolean state) {
/* 648 */     setConfigFlag(32, state);
/*     */   }
/*     */   
/*     */   public void doAddSpaceAfterEmptyElem(boolean state) {
/* 652 */     setConfigFlag(64, state);
/*     */   }
/*     */   
/*     */   public void enableAutomaticEndElements(boolean state) {
/* 656 */     setConfigFlag(128, state);
/*     */   }
/*     */   
/*     */   public void doValidateStructure(boolean state) {
/* 660 */     setConfigFlag(256, state);
/*     */   }
/*     */   
/*     */   public void doValidateContent(boolean state) {
/* 664 */     setConfigFlag(512, state);
/*     */   }
/*     */   
/*     */   public void doValidateAttributes(boolean state) {
/* 668 */     setConfigFlag(2048, state);
/*     */   }
/*     */   
/*     */   public void doValidateNames(boolean state) {
/* 672 */     setConfigFlag(1024, state);
/*     */   }
/*     */   
/*     */   public void doFixContent(boolean state) {
/* 676 */     setConfigFlag(4096, state);
/*     */   }
/*     */   
/*     */   public void setAutomaticNsPrefix(String prefix) {
/* 684 */     setSpecialProperty(0, prefix);
/*     */   }
/*     */   
/*     */   public void setTextEscaperFactory(EscapingWriterFactory f) {
/* 688 */     setSpecialProperty(1, f);
/*     */   }
/*     */   
/*     */   public void setAttrValueEscaperFactory(EscapingWriterFactory f) {
/* 692 */     setSpecialProperty(2, f);
/*     */   }
/*     */   
/*     */   public void setProblemReporter(XMLReporter rep) {
/* 696 */     setSpecialProperty(3, rep);
/*     */   }
/*     */   
/*     */   public void setInvalidCharHandler(InvalidCharHandler h) {
/* 700 */     setSpecialProperty(4, h);
/*     */   }
/*     */   
/*     */   public void setEmptyElementHandler(EmptyElementHandler h) {
/* 704 */     setSpecialProperty(5, h);
/*     */   }
/*     */   
/*     */   public void configureForXmlConformance() {
/* 719 */     doValidateAttributes(true);
/* 720 */     doValidateContent(true);
/* 721 */     doValidateStructure(true);
/* 722 */     doValidateNames(true);
/*     */   }
/*     */   
/*     */   public void configureForRobustness() {
/* 732 */     doValidateAttributes(true);
/* 733 */     doValidateStructure(true);
/* 734 */     doValidateNames(true);
/* 740 */     doValidateContent(true);
/* 741 */     doFixContent(true);
/*     */   }
/*     */   
/*     */   public void configureForSpeed() {
/* 751 */     doValidateAttributes(false);
/* 752 */     doValidateContent(false);
/* 753 */     doValidateNames(false);
/*     */   }
/*     */   
/*     */   public char[] allocMediumCBuffer(int minSize) {
/* 770 */     if (this.mCurrRecycler != null) {
/* 771 */       char[] result = this.mCurrRecycler.getMediumCBuffer(minSize);
/* 772 */       if (result != null)
/* 773 */         return result; 
/*     */     } 
/* 776 */     return new char[minSize];
/*     */   }
/*     */   
/*     */   public void freeMediumCBuffer(char[] buffer) {
/* 782 */     if (this.mCurrRecycler == null)
/* 783 */       this.mCurrRecycler = createRecycler(); 
/* 785 */     this.mCurrRecycler.returnMediumCBuffer(buffer);
/*     */   }
/*     */   
/*     */   public char[] allocFullCBuffer(int minSize) {
/* 790 */     if (this.mCurrRecycler != null) {
/* 791 */       char[] result = this.mCurrRecycler.getFullCBuffer(minSize);
/* 792 */       if (result != null)
/* 793 */         return result; 
/*     */     } 
/* 796 */     return new char[minSize];
/*     */   }
/*     */   
/*     */   public void freeFullCBuffer(char[] buffer) {
/* 802 */     if (this.mCurrRecycler == null)
/* 803 */       this.mCurrRecycler = createRecycler(); 
/* 805 */     this.mCurrRecycler.returnFullCBuffer(buffer);
/*     */   }
/*     */   
/*     */   public byte[] allocFullBBuffer(int minSize) {
/* 810 */     if (this.mCurrRecycler != null) {
/* 811 */       byte[] result = this.mCurrRecycler.getFullBBuffer(minSize);
/* 812 */       if (result != null)
/* 813 */         return result; 
/*     */     } 
/* 816 */     return new byte[minSize];
/*     */   }
/*     */   
/*     */   public void freeFullBBuffer(byte[] buffer) {
/* 822 */     if (this.mCurrRecycler == null)
/* 823 */       this.mCurrRecycler = createRecycler(); 
/* 825 */     this.mCurrRecycler.returnFullBBuffer(buffer);
/*     */   }
/*     */   
/*     */   private BufferRecycler createRecycler() {
/* 830 */     BufferRecycler recycler = new BufferRecycler();
/* 832 */     mRecyclerRef.set(new SoftReference(recycler));
/* 833 */     return recycler;
/*     */   }
/*     */   
/*     */   private void setConfigFlag(int flag, boolean state) {
/* 843 */     if (state) {
/* 844 */       this.mConfigFlags |= flag;
/*     */     } else {
/* 846 */       this.mConfigFlags &= flag ^ 0xFFFFFFFF;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean hasConfigFlag(int flag) {
/* 851 */     return ((this.mConfigFlags & flag) == flag);
/*     */   }
/*     */   
/*     */   private final Object getSpecialProperty(int ix) {
/* 856 */     if (this.mSpecialProperties == null)
/* 857 */       return null; 
/* 859 */     return this.mSpecialProperties[ix];
/*     */   }
/*     */   
/*     */   private final void setSpecialProperty(int ix, Object value) {
/* 864 */     if (this.mSpecialProperties == null)
/* 865 */       this.mSpecialProperties = new Object[6]; 
/* 867 */     this.mSpecialProperties[ix] = value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\api\WriterConfig.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */