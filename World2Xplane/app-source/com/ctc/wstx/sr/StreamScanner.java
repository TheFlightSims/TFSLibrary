/*      */ package com.ctc.wstx.sr;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.cfg.ErrorConsts;
/*      */ import com.ctc.wstx.cfg.InputConfigFlags;
/*      */ import com.ctc.wstx.cfg.ParsingErrorMsgs;
/*      */ import com.ctc.wstx.ent.EntityDecl;
/*      */ import com.ctc.wstx.ent.IntEntity;
/*      */ import com.ctc.wstx.exc.WstxEOFException;
/*      */ import com.ctc.wstx.exc.WstxException;
/*      */ import com.ctc.wstx.exc.WstxIOException;
/*      */ import com.ctc.wstx.exc.WstxLazyException;
/*      */ import com.ctc.wstx.exc.WstxParsingException;
/*      */ import com.ctc.wstx.exc.WstxUnexpectedCharException;
/*      */ import com.ctc.wstx.exc.WstxValidationException;
/*      */ import com.ctc.wstx.io.DefaultInputResolver;
/*      */ import com.ctc.wstx.io.WstxInputData;
/*      */ import com.ctc.wstx.io.WstxInputLocation;
/*      */ import com.ctc.wstx.io.WstxInputSource;
/*      */ import com.ctc.wstx.util.ExceptionUtil;
/*      */ import com.ctc.wstx.util.SymbolTable;
/*      */ import com.ctc.wstx.util.TextBuffer;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.net.URL;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLReporter;
/*      */ import javax.xml.stream.XMLResolver;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.codehaus.stax2.XMLReporter2;
/*      */ import org.codehaus.stax2.XMLStreamLocation2;
/*      */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*      */ 
/*      */ public abstract class StreamScanner extends WstxInputData implements InputProblemReporter, InputConfigFlags, ParsingErrorMsgs {
/*      */   public static final char CHAR_CR_LF_OR_NULL = '\r';
/*      */   
/*      */   public static final int INT_CR_LF_OR_NULL = 13;
/*      */   
/*      */   protected static final char CHAR_FIRST_PURE_TEXT = '?';
/*      */   
/*      */   protected static final char CHAR_LOWEST_LEGAL_LOCALNAME_CHAR = '-';
/*      */   
/*      */   private static final int VALID_CHAR_COUNT = 256;
/*      */   
/*      */   private static final byte NAME_CHAR_INVALID_B = 0;
/*      */   
/*      */   private static final byte NAME_CHAR_ALL_VALID_B = 1;
/*      */   
/*      */   private static final byte NAME_CHAR_VALID_NONFIRST_B = -1;
/*      */   
/*  107 */   private static final byte[] sCharValidity = new byte[256];
/*      */   
/*      */   private static final int VALID_PUBID_CHAR_COUNT = 128;
/*      */   
/*      */   static {
/*  113 */     sCharValidity[95] = 1;
/*      */     int i, last;
/*  114 */     for (i = 0, last = 25; i <= last; i++) {
/*  115 */       sCharValidity[65 + i] = 1;
/*  116 */       sCharValidity[97 + i] = 1;
/*      */     } 
/*  118 */     for (i = 192; i < 246; i++)
/*  119 */       sCharValidity[i] = 1; 
/*  122 */     sCharValidity[215] = 0;
/*  123 */     sCharValidity[247] = 0;
/*  127 */     sCharValidity[45] = -1;
/*  128 */     sCharValidity[46] = -1;
/*  129 */     sCharValidity[183] = -1;
/*  130 */     for (i = 48; i <= 57; i++)
/*  131 */       sCharValidity[i] = -1; 
/*      */   }
/*      */   
/*  139 */   private static final byte[] sPubidValidity = new byte[128];
/*      */   
/*      */   private static final byte PUBID_CHAR_VALID_B = 1;
/*      */   
/*      */   protected final ReaderConfig mConfig;
/*      */   
/*      */   protected final boolean mCfgNsEnabled;
/*      */   
/*      */   protected boolean mCfgReplaceEntities;
/*      */   
/*      */   final SymbolTable mSymbols;
/*      */   
/*      */   protected String mCurrName;
/*      */   
/*      */   protected WstxInputSource mInput;
/*      */   
/*      */   protected final WstxInputSource mRootInput;
/*      */   
/*      */   static {
/*  143 */     for (i = 0, last = 25; i <= last; i++) {
/*  144 */       sPubidValidity[65 + i] = 1;
/*  145 */       sPubidValidity[97 + i] = 1;
/*      */     } 
/*  147 */     for (i = 48; i <= 57; i++)
/*  148 */       sPubidValidity[i] = 1; 
/*  152 */     sPubidValidity[10] = 1;
/*  153 */     sPubidValidity[13] = 1;
/*  154 */     sPubidValidity[32] = 1;
/*  157 */     sPubidValidity[45] = 1;
/*  158 */     sPubidValidity[39] = 1;
/*  159 */     sPubidValidity[40] = 1;
/*  160 */     sPubidValidity[41] = 1;
/*  161 */     sPubidValidity[43] = 1;
/*  162 */     sPubidValidity[44] = 1;
/*  163 */     sPubidValidity[46] = 1;
/*  164 */     sPubidValidity[47] = 1;
/*  165 */     sPubidValidity[58] = 1;
/*  166 */     sPubidValidity[61] = 1;
/*  167 */     sPubidValidity[63] = 1;
/*  168 */     sPubidValidity[59] = 1;
/*  169 */     sPubidValidity[33] = 1;
/*  170 */     sPubidValidity[42] = 1;
/*  171 */     sPubidValidity[35] = 1;
/*  172 */     sPubidValidity[64] = 1;
/*  173 */     sPubidValidity[36] = 1;
/*  174 */     sPubidValidity[95] = 1;
/*  175 */     sPubidValidity[37] = 1;
/*      */   }
/*      */   
/*  252 */   XMLResolver mEntityResolver = null;
/*      */   
/*  265 */   protected int mCurrDepth = 0;
/*      */   
/*  267 */   protected int mInputTopDepth = 0;
/*      */   
/*      */   protected boolean mNormalizeLFs;
/*      */   
/*  289 */   protected char[] mNameBuffer = null;
/*      */   
/*  305 */   protected long mTokenInputTotal = 0L;
/*      */   
/*  310 */   protected int mTokenInputRow = 1;
/*      */   
/*  316 */   protected int mTokenInputCol = 0;
/*      */   
/*  330 */   String mDocInputEncoding = null;
/*      */   
/*  336 */   String mDocXmlEncoding = null;
/*      */   
/*  342 */   protected int mDocXmlVersion = 0;
/*      */   
/*      */   protected Map mCachedEntities;
/*      */   
/*      */   protected boolean mCfgTreatCharRefsAsEntities;
/*      */   
/*      */   protected EntityDecl mCurrEntity;
/*      */   
/*      */   protected StreamScanner(WstxInputSource input, ReaderConfig cfg, XMLResolver res) {
/*  373 */     this.mInput = input;
/*  375 */     this.mRootInput = input;
/*  377 */     this.mConfig = cfg;
/*  378 */     this.mSymbols = cfg.getSymbols();
/*  379 */     int cf = cfg.getConfigFlags();
/*  380 */     this.mCfgNsEnabled = ((cf & 0x1) != 0);
/*  381 */     this.mCfgReplaceEntities = ((cf & 0x4) != 0);
/*  383 */     this.mNormalizeLFs = this.mConfig.willNormalizeLFs();
/*  384 */     this.mInputBuffer = null;
/*  385 */     this.mInputPtr = this.mInputEnd = 0;
/*  386 */     this.mEntityResolver = res;
/*  388 */     this.mCfgTreatCharRefsAsEntities = this.mConfig.willTreatCharRefsAsEnts();
/*  389 */     this.mCachedEntities = this.mCfgTreatCharRefsAsEntities ? new HashMap() : Collections.EMPTY_MAP;
/*      */   }
/*      */   
/*      */   protected WstxInputLocation getLastCharLocation() {
/*  405 */     return this.mInput.getLocation(this.mCurrInputProcessed + this.mInputPtr - 1L, this.mCurrInputRow, this.mInputPtr - this.mCurrInputRowStart);
/*      */   }
/*      */   
/*      */   protected URL getSource() {
/*  411 */     return this.mInput.getSource();
/*      */   }
/*      */   
/*      */   protected String getSystemId() {
/*  415 */     return this.mInput.getSystemId();
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getStartLocation() {
/*  435 */     return (XMLStreamLocation2)this.mInput.getLocation(this.mTokenInputTotal, this.mTokenInputRow, this.mTokenInputCol + 1);
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getCurrentLocation() {
/*  441 */     return (XMLStreamLocation2)this.mInput.getLocation(this.mCurrInputProcessed + this.mInputPtr, this.mCurrInputRow, this.mInputPtr - this.mCurrInputRowStart + 1);
/*      */   }
/*      */   
/*      */   public WstxException throwWfcException(String msg, boolean deferErrors) throws WstxException {
/*  455 */     WstxException ex = constructWfcException(msg);
/*  456 */     if (!deferErrors)
/*  457 */       throw ex; 
/*  459 */     return ex;
/*      */   }
/*      */   
/*      */   public void throwParseError(String msg) throws XMLStreamException {
/*  464 */     throwParseError(msg, (Object)null, (Object)null);
/*      */   }
/*      */   
/*      */   public void throwParseError(String format, Object arg, Object arg2) throws XMLStreamException {
/*  477 */     String msg = (arg != null || arg2 != null) ? MessageFormat.format(format, new Object[] { arg, arg2 }) : format;
/*  479 */     throw constructWfcException(msg);
/*      */   }
/*      */   
/*      */   public void reportProblem(String probType, String format, Object arg, Object arg2) throws XMLStreamException {
/*  485 */     XMLReporter rep = this.mConfig.getXMLReporter();
/*  486 */     if (rep != null)
/*  487 */       _reportProblem(rep, probType, MessageFormat.format(format, new Object[] { arg, arg2 }), (Location)null); 
/*      */   }
/*      */   
/*      */   public void reportProblem(Location loc, String probType, String format, Object arg, Object arg2) throws XMLStreamException {
/*  496 */     XMLReporter rep = this.mConfig.getXMLReporter();
/*  497 */     if (rep != null) {
/*  498 */       String msg = (arg != null || arg2 != null) ? MessageFormat.format(format, new Object[] { arg, arg2 }) : format;
/*  500 */       _reportProblem(rep, probType, msg, loc);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void _reportProblem(XMLReporter rep, String probType, String msg, Location loc) throws XMLStreamException {
/*      */     WstxInputLocation wstxInputLocation;
/*  507 */     if (loc == null)
/*  508 */       wstxInputLocation = getLastCharLocation(); 
/*  510 */     _reportProblem(rep, new XMLValidationProblem((Location)wstxInputLocation, msg, 2, probType));
/*      */   }
/*      */   
/*      */   protected void _reportProblem(XMLReporter rep, XMLValidationProblem prob) throws XMLStreamException {
/*  516 */     if (rep != null) {
/*      */       WstxInputLocation wstxInputLocation;
/*  517 */       Location loc = prob.getLocation();
/*  518 */       if (loc == null) {
/*  519 */         wstxInputLocation = getLastCharLocation();
/*  520 */         prob.setLocation((Location)wstxInputLocation);
/*      */       } 
/*  523 */       if (prob.getType() == null)
/*  524 */         prob.setType(ErrorConsts.WT_VALIDATION); 
/*  528 */       if (rep instanceof XMLReporter2) {
/*  529 */         ((XMLReporter2)rep).report(prob);
/*      */       } else {
/*  531 */         rep.report(prob.getMessage(), prob.getType(), prob, (Location)wstxInputLocation);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(XMLValidationProblem prob) throws XMLStreamException {
/*  555 */     if (prob.getSeverity() > 2)
/*  556 */       throw WstxValidationException.create(prob); 
/*  558 */     XMLReporter rep = this.mConfig.getXMLReporter();
/*  559 */     if (rep != null) {
/*  560 */       _reportProblem(rep, prob);
/*  565 */     } else if (prob.getSeverity() >= 2) {
/*  566 */       throw WstxValidationException.create(prob);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String msg, int severity) throws XMLStreamException {
/*  574 */     reportValidationProblem(new XMLValidationProblem((Location)getLastCharLocation(), msg, severity));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String msg) throws XMLStreamException {
/*  581 */     reportValidationProblem(new XMLValidationProblem((Location)getLastCharLocation(), msg, 2));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(Location loc, String msg) throws XMLStreamException {
/*  589 */     reportValidationProblem(new XMLValidationProblem(loc, msg));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String format, Object arg, Object arg2) throws XMLStreamException {
/*  595 */     reportValidationProblem(MessageFormat.format(format, new Object[] { arg, arg2 }));
/*      */   }
/*      */   
/*      */   protected WstxException constructWfcException(String msg) {
/*  606 */     return (WstxException)new WstxParsingException(msg, (Location)getLastCharLocation());
/*      */   }
/*      */   
/*      */   protected WstxException constructFromIOE(IOException ioe) {
/*  625 */     return (WstxException)new WstxIOException(ioe);
/*      */   }
/*      */   
/*      */   protected WstxException constructNullCharException() {
/*  630 */     return (WstxException)new WstxUnexpectedCharException("Illegal character (NULL, unicode 0) encountered: not valid in any content", (Location)getLastCharLocation(), false);
/*      */   }
/*      */   
/*      */   protected void throwUnexpectedChar(int i, String msg) throws WstxException {
/*  637 */     char c = (char)i;
/*  638 */     String excMsg = "Unexpected character " + getCharDesc(c) + msg;
/*  639 */     throw new WstxUnexpectedCharException(excMsg, getLastCharLocation(), c);
/*      */   }
/*      */   
/*      */   protected void throwNullChar() throws WstxException {
/*  645 */     throw constructNullCharException();
/*      */   }
/*      */   
/*      */   protected void throwInvalidSpace(int i) throws WstxException {
/*  651 */     throwInvalidSpace(i, false);
/*      */   }
/*      */   
/*      */   protected WstxException throwInvalidSpace(int i, boolean deferErrors) throws WstxException {
/*      */     WstxUnexpectedCharException wstxUnexpectedCharException;
/*  657 */     char c = (char)i;
/*  659 */     if (c == '\000') {
/*  660 */       WstxException ex = constructNullCharException();
/*      */     } else {
/*  662 */       String msg = "Illegal character (" + getCharDesc(c) + ")";
/*  663 */       if (this.mXml11)
/*  664 */         msg = msg + " [note: in XML 1.1, it could be included via entity expansion]"; 
/*  666 */       wstxUnexpectedCharException = new WstxUnexpectedCharException(msg, (Location)getLastCharLocation(), c);
/*      */     } 
/*  668 */     if (!deferErrors)
/*  669 */       throw wstxUnexpectedCharException; 
/*  671 */     return (WstxException)wstxUnexpectedCharException;
/*      */   }
/*      */   
/*      */   protected void throwUnexpectedEOF(String msg) throws WstxException {
/*  677 */     throw new WstxEOFException("Unexpected EOF" + ((msg == null) ? "" : msg), getLastCharLocation());
/*      */   }
/*      */   
/*      */   protected void throwUnexpectedEOB(String msg) throws WstxException {
/*  691 */     throw new WstxEOFException("Unexpected end of input block" + ((msg == null) ? "" : msg), getLastCharLocation());
/*      */   }
/*      */   
/*      */   protected void throwFromIOE(IOException ioe) throws WstxException {
/*  699 */     throw new WstxIOException(ioe);
/*      */   }
/*      */   
/*      */   protected void throwFromStrE(XMLStreamException strex) throws WstxException {
/*  705 */     if (strex instanceof WstxException)
/*  706 */       throw (WstxException)strex; 
/*  708 */     WstxException newEx = new WstxException(strex);
/*  709 */     ExceptionUtil.setInitCause((Throwable)newEx, strex);
/*  710 */     throw newEx;
/*      */   }
/*      */   
/*      */   protected void throwLazyError(Exception e) {
/*  719 */     if (e instanceof XMLStreamException)
/*  720 */       WstxLazyException.throwLazily((XMLStreamException)e); 
/*  722 */     ExceptionUtil.throwRuntimeException(e);
/*      */   }
/*      */   
/*      */   protected String tokenTypeDesc(int type) {
/*  727 */     return ErrorConsts.tokenTypeDesc(type);
/*      */   }
/*      */   
/*      */   public final WstxInputSource getCurrentInput() {
/*  743 */     return this.mInput;
/*      */   }
/*      */   
/*      */   protected final int inputInBuffer() {
/*  747 */     return this.mInputEnd - this.mInputPtr;
/*      */   }
/*      */   
/*      */   protected final int getNext() throws XMLStreamException {
/*  753 */     if (this.mInputPtr >= this.mInputEnd && 
/*  754 */       !loadMore())
/*  755 */       return -1; 
/*  758 */     return this.mInputBuffer[this.mInputPtr++];
/*      */   }
/*      */   
/*      */   protected final int peekNext() throws XMLStreamException {
/*  773 */     if (this.mInputPtr >= this.mInputEnd && 
/*  774 */       !loadMoreFromCurrent())
/*  775 */       return -1; 
/*  778 */     return this.mInputBuffer[this.mInputPtr];
/*      */   }
/*      */   
/*      */   protected final char getNextChar(String errorMsg) throws XMLStreamException {
/*  784 */     if (this.mInputPtr >= this.mInputEnd)
/*  785 */       loadMore(errorMsg); 
/*  787 */     return this.mInputBuffer[this.mInputPtr++];
/*      */   }
/*      */   
/*      */   protected final char getNextCharFromCurrent(String errorMsg) throws XMLStreamException {
/*  801 */     if (this.mInputPtr >= this.mInputEnd)
/*  802 */       loadMoreFromCurrent(errorMsg); 
/*  804 */     return this.mInputBuffer[this.mInputPtr++];
/*      */   }
/*      */   
/*      */   protected final int getNextAfterWS() throws XMLStreamException {
/*  815 */     if (this.mInputPtr >= this.mInputEnd && 
/*  816 */       !loadMore())
/*  817 */       return -1; 
/*  820 */     char c = this.mInputBuffer[this.mInputPtr++];
/*  821 */     while (c <= ' ') {
/*  823 */       if (c == '\n' || c == '\r') {
/*  824 */         skipCRLF(c);
/*  825 */       } else if (c != ' ' && c != '\t') {
/*  826 */         throwInvalidSpace(c);
/*      */       } 
/*  829 */       if (this.mInputPtr >= this.mInputEnd && 
/*  830 */         !loadMore())
/*  831 */         return -1; 
/*  834 */       c = this.mInputBuffer[this.mInputPtr++];
/*      */     } 
/*  836 */     return c;
/*      */   }
/*      */   
/*      */   protected final char getNextCharAfterWS(String errorMsg) throws XMLStreamException {
/*  842 */     if (this.mInputPtr >= this.mInputEnd)
/*  843 */       loadMore(errorMsg); 
/*  846 */     char c = this.mInputBuffer[this.mInputPtr++];
/*  847 */     while (c <= ' ') {
/*  849 */       if (c == '\n' || c == '\r') {
/*  850 */         skipCRLF(c);
/*  851 */       } else if (c != ' ' && c != '\t') {
/*  852 */         throwInvalidSpace(c);
/*      */       } 
/*  856 */       if (this.mInputPtr >= this.mInputEnd)
/*  857 */         loadMore(errorMsg); 
/*  859 */       c = this.mInputBuffer[this.mInputPtr++];
/*      */     } 
/*  861 */     return c;
/*      */   }
/*      */   
/*      */   protected final char getNextInCurrAfterWS(String errorMsg) throws XMLStreamException {
/*  867 */     return getNextInCurrAfterWS(errorMsg, getNextCharFromCurrent(errorMsg));
/*      */   }
/*      */   
/*      */   protected final char getNextInCurrAfterWS(String errorMsg, char c) throws XMLStreamException {
/*  873 */     while (c <= ' ') {
/*  875 */       if (c == '\n' || c == '\r') {
/*  876 */         skipCRLF(c);
/*  877 */       } else if (c != ' ' && c != '\t') {
/*  878 */         throwInvalidSpace(c);
/*      */       } 
/*  882 */       if (this.mInputPtr >= this.mInputEnd)
/*  883 */         loadMoreFromCurrent(errorMsg); 
/*  885 */       c = this.mInputBuffer[this.mInputPtr++];
/*      */     } 
/*  887 */     return c;
/*      */   }
/*      */   
/*      */   protected final boolean skipCRLF(char c) throws XMLStreamException {
/*      */     boolean result;
/*  903 */     if (c == '\r' && peekNext() == 10) {
/*  904 */       this.mInputPtr++;
/*  905 */       result = true;
/*      */     } else {
/*  907 */       result = false;
/*      */     } 
/*  909 */     this.mCurrInputRow++;
/*  910 */     this.mCurrInputRowStart = this.mInputPtr;
/*  911 */     return result;
/*      */   }
/*      */   
/*      */   protected final void markLF() {
/*  915 */     this.mCurrInputRow++;
/*  916 */     this.mCurrInputRowStart = this.mInputPtr;
/*      */   }
/*      */   
/*      */   protected final void markLF(int inputPtr) {
/*  920 */     this.mCurrInputRow++;
/*  921 */     this.mCurrInputRowStart = inputPtr;
/*      */   }
/*      */   
/*      */   protected final void pushback() {
/*  929 */     this.mInputPtr--;
/*      */   }
/*      */   
/*      */   protected void initInputSource(WstxInputSource newInput, boolean isExt, String entityId) throws XMLStreamException {
/*  948 */     this.mInput = newInput;
/*  950 */     this.mInputPtr = 0;
/*  951 */     this.mInputEnd = 0;
/*  955 */     this.mInputTopDepth = this.mCurrDepth;
/*  956 */     this.mInput.initInputLocation(this, this.mCurrDepth);
/*  961 */     if (isExt) {
/*  962 */       this.mNormalizeLFs = true;
/*      */     } else {
/*  964 */       this.mNormalizeLFs = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean loadMore() throws XMLStreamException {
/*  978 */     WstxInputSource input = this.mInput;
/*      */     do {
/*  984 */       this.mCurrInputProcessed += this.mInputEnd;
/*  985 */       this.mCurrInputRowStart -= this.mInputEnd;
/*      */       try {
/*  988 */         int count = input.readInto(this);
/*  989 */         if (count > 0)
/*  990 */           return true; 
/*  992 */         input.close();
/*  993 */       } catch (IOException ioe) {
/*  994 */         throw constructFromIOE(ioe);
/*      */       } 
/*  996 */       if (input == this.mRootInput)
/* 1001 */         return false; 
/* 1003 */       WstxInputSource parent = input.getParent();
/* 1004 */       if (parent == null)
/* 1005 */         throwNullParent(input); 
/* 1010 */       if (this.mCurrDepth != input.getScopeId())
/* 1011 */         handleIncompleteEntityProblem(input); 
/* 1014 */       this.mInput = input = parent;
/* 1015 */       input.restoreContext(this);
/* 1016 */       this.mInputTopDepth = input.getScopeId();
/* 1021 */       if (this.mNormalizeLFs)
/*      */         continue; 
/* 1022 */       this.mNormalizeLFs = !input.fromInternalEntity();
/* 1025 */     } while (this.mInputPtr >= this.mInputEnd);
/* 1027 */     return true;
/*      */   }
/*      */   
/*      */   protected final boolean loadMore(String errorMsg) throws XMLStreamException {
/* 1033 */     if (!loadMore())
/* 1034 */       throwUnexpectedEOF(errorMsg); 
/* 1036 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean loadMoreFromCurrent() throws XMLStreamException {
/* 1043 */     this.mCurrInputProcessed += this.mInputEnd;
/* 1044 */     this.mCurrInputRowStart -= this.mInputEnd;
/*      */     try {
/* 1046 */       int count = this.mInput.readInto(this);
/* 1047 */       return (count > 0);
/* 1048 */     } catch (IOException ie) {
/* 1049 */       throw constructFromIOE(ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected final boolean loadMoreFromCurrent(String errorMsg) throws XMLStreamException {
/* 1056 */     if (!loadMoreFromCurrent())
/* 1057 */       throwUnexpectedEOB(errorMsg); 
/* 1059 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean ensureInput(int minAmount) throws XMLStreamException {
/* 1079 */     int currAmount = this.mInputEnd - this.mInputPtr;
/* 1080 */     if (currAmount >= minAmount)
/* 1081 */       return true; 
/*      */     try {
/* 1084 */       return this.mInput.readMore(this, minAmount);
/* 1085 */     } catch (IOException ie) {
/* 1086 */       throw constructFromIOE(ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void closeAllInput(boolean force) throws XMLStreamException {
/* 1093 */     WstxInputSource input = this.mInput;
/*      */     while (true) {
/*      */       try {
/* 1096 */         if (force) {
/* 1097 */           input.closeCompletely();
/*      */         } else {
/* 1099 */           input.close();
/*      */         } 
/* 1101 */       } catch (IOException ie) {
/* 1102 */         throw constructFromIOE(ie);
/*      */       } 
/* 1104 */       if (input == this.mRootInput)
/*      */         break; 
/* 1107 */       WstxInputSource parent = input.getParent();
/* 1108 */       if (parent == null)
/* 1109 */         throwNullParent(input); 
/* 1111 */       this.mInput = input = parent;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void throwNullParent(WstxInputSource curr) {
/* 1117 */     throw new IllegalStateException(ErrorConsts.ERR_INTERNAL);
/*      */   }
/*      */   
/*      */   protected int resolveSimpleEntity(boolean checkStd) throws XMLStreamException {
/* 1156 */     char[] buf = this.mInputBuffer;
/* 1157 */     int ptr = this.mInputPtr;
/* 1158 */     char c = buf[ptr++];
/* 1161 */     if (c == '#') {
/* 1162 */       c = buf[ptr++];
/* 1163 */       int value = 0;
/* 1164 */       int inputLen = this.mInputEnd;
/* 1165 */       if (c == 'x') {
/* 1166 */         while (ptr < inputLen) {
/* 1167 */           c = buf[ptr++];
/* 1168 */           if (c == ';')
/*      */             break; 
/* 1171 */           value <<= 4;
/* 1172 */           if (c <= '9' && c >= '0') {
/* 1173 */             value += c - 48;
/* 1174 */           } else if (c >= 'a' && c <= 'f') {
/* 1175 */             value += 10 + c - 97;
/* 1176 */           } else if (c >= 'A' && c <= 'F') {
/* 1177 */             value += 10 + c - 65;
/*      */           } else {
/* 1179 */             this.mInputPtr = ptr;
/* 1180 */             throwUnexpectedChar(c, "; expected a hex digit (0-9a-fA-F).");
/*      */           } 
/* 1185 */           if (value > 1114111)
/* 1186 */             reportUnicodeOverflow(); 
/*      */         } 
/*      */       } else {
/* 1190 */         while (c != ';') {
/* 1191 */           if (c <= '9' && c >= '0') {
/* 1192 */             value = value * 10 + c - 48;
/* 1194 */             if (value > 1114111)
/* 1195 */               reportUnicodeOverflow(); 
/*      */           } else {
/* 1198 */             this.mInputPtr = ptr;
/* 1199 */             throwUnexpectedChar(c, "; expected a decimal number.");
/*      */           } 
/* 1201 */           if (ptr >= inputLen)
/*      */             break; 
/* 1204 */           c = buf[ptr++];
/*      */         } 
/*      */       } 
/* 1210 */       if (c == ';') {
/* 1211 */         this.mInputPtr = ptr;
/* 1212 */         validateChar(value);
/* 1213 */         return value;
/*      */       } 
/* 1219 */     } else if (checkStd) {
/* 1223 */       if (c == 'a') {
/* 1224 */         c = buf[ptr++];
/* 1226 */         if (c == 'm') {
/* 1227 */           if (buf[ptr++] == 'p' && 
/* 1228 */             ptr < this.mInputEnd && buf[ptr++] == ';') {
/* 1229 */             this.mInputPtr = ptr;
/* 1230 */             return 38;
/*      */           } 
/* 1233 */         } else if (c == 'p' && 
/* 1234 */           buf[ptr++] == 'o') {
/* 1235 */           int len = this.mInputEnd;
/* 1236 */           if (ptr < len && buf[ptr++] == 's' && 
/* 1237 */             ptr < len && buf[ptr++] == ';') {
/* 1238 */             this.mInputPtr = ptr;
/* 1239 */             return 39;
/*      */           } 
/*      */         } 
/* 1244 */       } else if (c == 'g') {
/* 1245 */         if (buf[ptr++] == 't' && buf[ptr++] == ';') {
/* 1246 */           this.mInputPtr = ptr;
/* 1247 */           return 62;
/*      */         } 
/* 1249 */       } else if (c == 'l') {
/* 1250 */         if (buf[ptr++] == 't' && buf[ptr++] == ';') {
/* 1251 */           this.mInputPtr = ptr;
/* 1252 */           return 60;
/*      */         } 
/* 1254 */       } else if (c == 'q' && 
/* 1255 */         buf[ptr++] == 'u' && buf[ptr++] == 'o') {
/* 1256 */         int len = this.mInputEnd;
/* 1257 */         if (ptr < len && buf[ptr++] == 't' && 
/* 1258 */           ptr < len && buf[ptr++] == ';') {
/* 1259 */           this.mInputPtr = ptr;
/* 1260 */           return 34;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1266 */     return 0;
/*      */   }
/*      */   
/*      */   protected int resolveCharOnlyEntity(boolean checkStd) throws XMLStreamException {
/* 1303 */     int avail = this.mInputEnd - this.mInputPtr;
/* 1304 */     if (avail < 6) {
/* 1309 */       this.mInputPtr--;
/* 1313 */       if (!ensureInput(6)) {
/* 1314 */         avail = inputInBuffer();
/* 1315 */         if (avail < 3)
/* 1316 */           throwUnexpectedEOF(" in entity reference"); 
/*      */       } else {
/* 1319 */         avail = 6;
/*      */       } 
/* 1322 */       this.mInputPtr++;
/*      */     } 
/* 1328 */     char c = this.mInputBuffer[this.mInputPtr];
/* 1331 */     if (c == '#') {
/* 1332 */       this.mInputPtr++;
/* 1333 */       return resolveCharEnt((StringBuffer)null);
/*      */     } 
/* 1337 */     if (checkStd)
/* 1338 */       if (c == 'a') {
/* 1339 */         char d = this.mInputBuffer[this.mInputPtr + 1];
/* 1340 */         if (d == 'm') {
/* 1341 */           if (avail >= 4 && this.mInputBuffer[this.mInputPtr + 2] == 'p' && this.mInputBuffer[this.mInputPtr + 3] == ';') {
/* 1344 */             this.mInputPtr += 4;
/* 1345 */             return 38;
/*      */           } 
/* 1347 */         } else if (d == 'p' && 
/* 1348 */           avail >= 5 && this.mInputBuffer[this.mInputPtr + 2] == 'o' && this.mInputBuffer[this.mInputPtr + 3] == 's' && this.mInputBuffer[this.mInputPtr + 4] == ';') {
/* 1352 */           this.mInputPtr += 5;
/* 1353 */           return 39;
/*      */         } 
/* 1356 */       } else if (c == 'l') {
/* 1357 */         if (avail >= 3 && this.mInputBuffer[this.mInputPtr + 1] == 't' && this.mInputBuffer[this.mInputPtr + 2] == ';') {
/* 1360 */           this.mInputPtr += 3;
/* 1361 */           return 60;
/*      */         } 
/* 1363 */       } else if (c == 'g') {
/* 1364 */         if (avail >= 3 && this.mInputBuffer[this.mInputPtr + 1] == 't' && this.mInputBuffer[this.mInputPtr + 2] == ';') {
/* 1367 */           this.mInputPtr += 3;
/* 1368 */           return 62;
/*      */         } 
/* 1370 */       } else if (c == 'q' && 
/* 1371 */         avail >= 5 && this.mInputBuffer[this.mInputPtr + 1] == 'u' && this.mInputBuffer[this.mInputPtr + 2] == 'o' && this.mInputBuffer[this.mInputPtr + 3] == 't' && this.mInputBuffer[this.mInputPtr + 4] == ';') {
/* 1376 */         this.mInputPtr += 5;
/* 1377 */         return 34;
/*      */       }  
/* 1381 */     return 0;
/*      */   }
/*      */   
/*      */   protected EntityDecl resolveNonCharEntity() throws XMLStreamException {
/* 1395 */     int avail = this.mInputEnd - this.mInputPtr;
/* 1396 */     if (avail < 6) {
/* 1401 */       this.mInputPtr--;
/* 1406 */       if (!ensureInput(6)) {
/* 1407 */         avail = inputInBuffer();
/* 1408 */         if (avail < 3)
/* 1409 */           throwUnexpectedEOF(" in entity reference"); 
/*      */       } else {
/* 1412 */         avail = 6;
/*      */       } 
/* 1415 */       this.mInputPtr++;
/*      */     } 
/* 1419 */     char c = this.mInputBuffer[this.mInputPtr];
/* 1420 */     if (c == '#')
/* 1421 */       return null; 
/* 1430 */     if (c == 'a') {
/* 1431 */       char d = this.mInputBuffer[this.mInputPtr + 1];
/* 1432 */       if (d == 'm') {
/* 1433 */         if (avail >= 4 && this.mInputBuffer[this.mInputPtr + 2] == 'p' && this.mInputBuffer[this.mInputPtr + 3] == ';')
/* 1439 */           return null; 
/* 1441 */       } else if (d == 'p' && 
/* 1442 */         avail >= 5 && this.mInputBuffer[this.mInputPtr + 2] == 'o' && this.mInputBuffer[this.mInputPtr + 3] == 's' && this.mInputBuffer[this.mInputPtr + 4] == ';') {
/* 1446 */         return null;
/*      */       } 
/* 1449 */     } else if (c == 'l') {
/* 1450 */       if (avail >= 3 && this.mInputBuffer[this.mInputPtr + 1] == 't' && this.mInputBuffer[this.mInputPtr + 2] == ';')
/* 1453 */         return null; 
/* 1455 */     } else if (c == 'g') {
/* 1456 */       if (avail >= 3 && this.mInputBuffer[this.mInputPtr + 1] == 't' && this.mInputBuffer[this.mInputPtr + 2] == ';')
/* 1459 */         return null; 
/* 1461 */     } else if (c == 'q' && 
/* 1462 */       avail >= 5 && this.mInputBuffer[this.mInputPtr + 1] == 'u' && this.mInputBuffer[this.mInputPtr + 2] == 'o' && this.mInputBuffer[this.mInputPtr + 3] == 't' && this.mInputBuffer[this.mInputPtr + 4] == ';') {
/* 1467 */       return null;
/*      */     } 
/* 1472 */     this.mInputPtr++;
/* 1473 */     String id = parseEntityName(c);
/* 1474 */     this.mCurrName = id;
/* 1476 */     return findEntity(id, (Object)null);
/*      */   }
/*      */   
/*      */   protected int fullyResolveEntity(boolean allowExt) throws XMLStreamException {
/* 1497 */     char c = getNextCharFromCurrent(" in entity reference");
/* 1499 */     if (c == '#') {
/* 1500 */       StringBuffer originalSurface = new StringBuffer("#");
/* 1501 */       int ch = resolveCharEnt(originalSurface);
/* 1502 */       if (this.mCfgTreatCharRefsAsEntities) {
/* 1503 */         char[] originalChars = new char[originalSurface.length()];
/* 1504 */         originalSurface.getChars(0, originalSurface.length(), originalChars, 0);
/* 1505 */         this.mCurrEntity = getIntEntity(ch, originalChars);
/* 1506 */         return 0;
/*      */       } 
/* 1508 */       return ch;
/*      */     } 
/* 1511 */     String id = parseEntityName(c);
/* 1514 */     c = id.charAt(0);
/* 1519 */     char d = Character.MIN_VALUE;
/* 1520 */     if (c == 'a') {
/* 1521 */       if (id.equals("amp")) {
/* 1522 */         d = '&';
/* 1523 */       } else if (id.equals("apos")) {
/* 1524 */         d = '\'';
/*      */       } 
/* 1526 */     } else if (c == 'g') {
/* 1527 */       if (id.length() == 2 && id.charAt(1) == 't')
/* 1528 */         d = '>'; 
/* 1530 */     } else if (c == 'l') {
/* 1531 */       if (id.length() == 2 && id.charAt(1) == 't')
/* 1532 */         d = '<'; 
/* 1534 */     } else if (c == 'q' && 
/* 1535 */       id.equals("quot")) {
/* 1536 */       d = '"';
/*      */     } 
/* 1540 */     if (d != '\000') {
/* 1541 */       if (this.mCfgTreatCharRefsAsEntities) {
/* 1542 */         char[] originalChars = new char[id.length()];
/* 1543 */         id.getChars(0, id.length(), originalChars, 0);
/* 1544 */         this.mCurrEntity = getIntEntity(d, originalChars);
/* 1545 */         return 0;
/*      */       } 
/* 1547 */       return d;
/*      */     } 
/* 1550 */     EntityDecl e = expandEntity(id, allowExt, (Object)null);
/* 1551 */     if (this.mCfgTreatCharRefsAsEntities)
/* 1552 */       this.mCurrEntity = e; 
/* 1554 */     return 0;
/*      */   }
/*      */   
/*      */   protected EntityDecl getIntEntity(int ch, char[] originalChars) {
/* 1563 */     String cacheKey = new String(originalChars);
/* 1565 */     IntEntity entity = (IntEntity)this.mCachedEntities.get(cacheKey);
/* 1566 */     if (entity == null) {
/*      */       String repl;
/* 1568 */       if (ch <= 65535) {
/* 1569 */         repl = Character.toString((char)ch);
/*      */       } else {
/* 1571 */         StringBuffer sb = new StringBuffer(2);
/* 1572 */         ch -= 65536;
/* 1573 */         sb.append((char)((ch >> 10) + 55296));
/* 1574 */         sb.append((char)((ch & 0x3FF) + 56320));
/* 1575 */         repl = sb.toString();
/*      */       } 
/* 1577 */       entity = IntEntity.create(new String(originalChars), repl);
/* 1578 */       this.mCachedEntities.put(cacheKey, entity);
/*      */     } 
/* 1580 */     return (EntityDecl)entity;
/*      */   }
/*      */   
/*      */   protected EntityDecl expandEntity(String id, boolean allowExt, Object extraArg) throws XMLStreamException {
/* 1599 */     this.mCurrName = id;
/* 1601 */     EntityDecl ed = findEntity(id, extraArg);
/* 1603 */     if (ed == null) {
/* 1611 */       if (this.mCfgReplaceEntities)
/* 1612 */         this.mCurrEntity = expandUnresolvedEntity(id); 
/* 1614 */       return null;
/*      */     } 
/* 1617 */     if (!this.mCfgTreatCharRefsAsEntities || this instanceof com.ctc.wstx.dtd.MinimalDTDReader)
/* 1618 */       expandEntity(ed, allowExt); 
/* 1621 */     return ed;
/*      */   }
/*      */   
/*      */   private void expandEntity(EntityDecl ed, boolean allowExt) throws XMLStreamException {
/* 1637 */     String id = ed.getName();
/* 1642 */     if (this.mInput.isOrIsExpandedFrom(id))
/* 1643 */       throwRecursionError(id); 
/* 1650 */     if (!ed.isParsed())
/* 1651 */       throwParseError("Illegal reference to unparsed external entity \"{0}\"", id, (Object)null); 
/* 1655 */     boolean isExt = ed.isExternal();
/* 1656 */     if (isExt) {
/* 1657 */       if (!allowExt)
/* 1658 */         throwParseError("Encountered a reference to external parsed entity \"{0}\" when expanding attribute value: not legal as per XML 1.0/1.1 #3.1", id, (Object)null); 
/* 1660 */       if (!this.mConfig.willSupportExternalEntities())
/* 1661 */         throwParseError("Encountered a reference to external entity \"{0}\", but stream reader has feature \"{1}\" disabled", id, "javax.xml.stream.isSupportingExternalEntities"); 
/*      */     } 
/* 1667 */     WstxInputSource oldInput = this.mInput;
/* 1668 */     oldInput.saveContext(this);
/* 1669 */     WstxInputSource newInput = null;
/*      */     try {
/* 1671 */       newInput = ed.expand(oldInput, this.mEntityResolver, this.mConfig, this.mDocXmlVersion);
/* 1672 */     } catch (FileNotFoundException fex) {
/* 1676 */       throwParseError("(was {0}) {1}", fex.getClass().getName(), fex.getMessage());
/* 1677 */     } catch (IOException ioe) {
/* 1678 */       throw constructFromIOE(ioe);
/*      */     } 
/* 1683 */     initInputSource(newInput, isExt, id);
/*      */   }
/*      */   
/*      */   private EntityDecl expandUnresolvedEntity(String id) throws XMLStreamException {
/* 1693 */     XMLResolver resolver = this.mConfig.getUndeclaredEntityResolver();
/* 1694 */     if (resolver != null) {
/*      */       WstxInputSource newInput;
/* 1700 */       if (this.mInput.isOrIsExpandedFrom(id))
/* 1701 */         throwRecursionError(id); 
/* 1704 */       WstxInputSource oldInput = this.mInput;
/* 1705 */       oldInput.saveContext(this);
/* 1707 */       int xmlVersion = this.mDocXmlVersion;
/* 1709 */       if (xmlVersion == 0)
/* 1710 */         xmlVersion = 256; 
/*      */       try {
/* 1714 */         newInput = DefaultInputResolver.resolveEntityUsing(oldInput, id, null, null, resolver, this.mConfig, xmlVersion);
/* 1716 */         if (this.mCfgTreatCharRefsAsEntities)
/* 1717 */           return (EntityDecl)new IntEntity((Location)WstxInputLocation.getEmptyLocation(), newInput.getEntityId(), newInput.getSource(), new char[0], (Location)WstxInputLocation.getEmptyLocation()); 
/* 1720 */       } catch (IOException ioe) {
/* 1721 */         throw constructFromIOE(ioe);
/*      */       } 
/* 1723 */       if (newInput != null) {
/* 1725 */         initInputSource(newInput, true, id);
/* 1726 */         return null;
/*      */       } 
/*      */     } 
/* 1729 */     handleUndeclaredEntity(id);
/* 1730 */     return null;
/*      */   }
/*      */   
/*      */   protected String parseLocalName(char c) throws XMLStreamException {
/* 1794 */     if (!isNameStartChar(c)) {
/* 1795 */       if (c == ':')
/* 1796 */         throwUnexpectedChar(c, " (missing namespace prefix?)"); 
/* 1798 */       throwUnexpectedChar(c, " (expected a name start character)");
/*      */     } 
/* 1801 */     int ptr = this.mInputPtr;
/* 1802 */     int hash = c;
/* 1803 */     int inputLen = this.mInputEnd;
/* 1804 */     int startPtr = ptr - 1;
/* 1805 */     char[] inputBuf = this.mInputBuffer;
/*      */     while (true) {
/* 1811 */       if (ptr >= inputLen) {
/* 1816 */         this.mInputPtr = ptr;
/* 1817 */         return parseLocalName2(startPtr, hash);
/*      */       } 
/* 1820 */       c = inputBuf[ptr];
/* 1821 */       if (c < '-')
/*      */         break; 
/* 1824 */       if (!isNameChar(c))
/*      */         break; 
/* 1827 */       hash = hash * 31 + c;
/* 1828 */       ptr++;
/*      */     } 
/* 1830 */     this.mInputPtr = ptr;
/* 1831 */     return this.mSymbols.findSymbol(this.mInputBuffer, startPtr, ptr - startPtr, hash);
/*      */   }
/*      */   
/*      */   protected String parseLocalName2(int start, int hash) throws XMLStreamException {
/* 1845 */     int ptr = this.mInputEnd - start;
/* 1847 */     char[] outBuf = getNameBuffer(ptr + 8);
/* 1849 */     if (ptr > 0)
/* 1850 */       System.arraycopy(this.mInputBuffer, start, outBuf, 0, ptr); 
/* 1853 */     int outLen = outBuf.length;
/* 1856 */     while (this.mInputPtr < this.mInputEnd || 
/* 1857 */       loadMoreFromCurrent()) {
/* 1861 */       char c = this.mInputBuffer[this.mInputPtr];
/* 1862 */       if (c < '-')
/*      */         break; 
/* 1865 */       if (!isNameChar(c))
/*      */         break; 
/* 1868 */       this.mInputPtr++;
/* 1869 */       if (ptr >= outLen) {
/* 1870 */         this.mNameBuffer = outBuf = expandBy50Pct(outBuf);
/* 1871 */         outLen = outBuf.length;
/*      */       } 
/* 1873 */       outBuf[ptr++] = c;
/* 1874 */       hash = hash * 31 + c;
/*      */     } 
/* 1877 */     return this.mSymbols.findSymbol(outBuf, 0, ptr, hash);
/*      */   }
/*      */   
/*      */   protected String parseFullName() throws XMLStreamException {
/* 1900 */     if (this.mInputPtr >= this.mInputEnd)
/* 1901 */       loadMoreFromCurrent(); 
/* 1903 */     return parseFullName(this.mInputBuffer[this.mInputPtr++]);
/*      */   }
/*      */   
/*      */   protected String parseFullName(char c) throws XMLStreamException {
/* 1910 */     if (!isNameStartChar(c))
/* 1911 */       if (c == ':') {
/* 1912 */         if (this.mCfgNsEnabled)
/* 1913 */           throwNsColonException(parseFNameForError()); 
/*      */       } else {
/* 1917 */         if (c <= ' ')
/* 1918 */           throwUnexpectedChar(c, " (missing name?)"); 
/* 1920 */         throwUnexpectedChar(c, " (expected a name start character)");
/*      */       }  
/* 1924 */     int ptr = this.mInputPtr;
/* 1925 */     int hash = c;
/* 1926 */     int inputLen = this.mInputEnd;
/* 1927 */     int startPtr = ptr - 1;
/*      */     while (true) {
/* 1933 */       if (ptr >= inputLen) {
/* 1938 */         this.mInputPtr = ptr;
/* 1939 */         return parseFullName2(startPtr, hash);
/*      */       } 
/* 1941 */       c = this.mInputBuffer[ptr];
/* 1942 */       if (c == ':') {
/* 1943 */         if (this.mCfgNsEnabled) {
/* 1944 */           this.mInputPtr = ptr;
/* 1945 */           throwNsColonException(new String(this.mInputBuffer, startPtr, ptr - startPtr) + parseFNameForError());
/*      */         } 
/*      */       } else {
/* 1948 */         if (c < '-')
/*      */           break; 
/* 1951 */         if (!isNameChar(c))
/*      */           break; 
/*      */       } 
/* 1955 */       hash = hash * 31 + c;
/* 1956 */       ptr++;
/*      */     } 
/* 1958 */     this.mInputPtr = ptr;
/* 1959 */     return this.mSymbols.findSymbol(this.mInputBuffer, startPtr, ptr - startPtr, hash);
/*      */   }
/*      */   
/*      */   protected String parseFullName2(int start, int hash) throws XMLStreamException {
/* 1965 */     int ptr = this.mInputEnd - start;
/* 1967 */     char[] outBuf = getNameBuffer(ptr + 8);
/* 1969 */     if (ptr > 0)
/* 1970 */       System.arraycopy(this.mInputBuffer, start, outBuf, 0, ptr); 
/* 1973 */     int outLen = outBuf.length;
/* 1980 */     while (this.mInputPtr < this.mInputEnd || 
/* 1981 */       loadMoreFromCurrent()) {
/* 1985 */       char c = this.mInputBuffer[this.mInputPtr];
/* 1986 */       if (c == ':') {
/* 1987 */         if (this.mCfgNsEnabled)
/* 1988 */           throwNsColonException(new String(outBuf, 0, ptr) + c + parseFNameForError()); 
/*      */       } else {
/* 1990 */         if (c < '-')
/*      */           break; 
/* 1992 */         if (!isNameChar(c))
/*      */           break; 
/*      */       } 
/* 1995 */       this.mInputPtr++;
/* 1997 */       if (ptr >= outLen) {
/* 1998 */         this.mNameBuffer = outBuf = expandBy50Pct(outBuf);
/* 1999 */         outLen = outBuf.length;
/*      */       } 
/* 2001 */       outBuf[ptr++] = c;
/* 2002 */       hash = hash * 31 + c;
/*      */     } 
/* 2006 */     return this.mSymbols.findSymbol(outBuf, 0, ptr, hash);
/*      */   }
/*      */   
/*      */   protected String parseFNameForError() throws XMLStreamException {
/* 2019 */     StringBuffer sb = new StringBuffer(100);
/*      */     while (true) {
/*      */       char c;
/* 2023 */       if (this.mInputPtr < this.mInputEnd) {
/* 2024 */         c = this.mInputBuffer[this.mInputPtr++];
/*      */       } else {
/* 2026 */         int i = getNext();
/* 2027 */         if (i < 0)
/*      */           break; 
/* 2030 */         c = (char)i;
/*      */       } 
/* 2032 */       if (c != ':' && !isNameChar(c)) {
/* 2033 */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/* 2036 */       sb.append(c);
/*      */     } 
/* 2038 */     return sb.toString();
/*      */   }
/*      */   
/*      */   protected final String parseEntityName(char c) throws XMLStreamException {
/* 2044 */     String id = parseFullName(c);
/* 2046 */     if (this.mInputPtr >= this.mInputEnd && 
/* 2047 */       !loadMoreFromCurrent())
/* 2048 */       throwParseError("Missing semicolon after reference for entity \"{0}\"", id, (Object)null); 
/* 2051 */     c = this.mInputBuffer[this.mInputPtr++];
/* 2052 */     if (c != ';')
/* 2053 */       throwUnexpectedChar(c, "; expected a semi-colon after the reference for entity '" + id + "'"); 
/* 2055 */     return id;
/*      */   }
/*      */   
/*      */   protected int skipFullName(char c) throws XMLStreamException {
/* 2070 */     if (!isNameStartChar(c)) {
/* 2071 */       this.mInputPtr--;
/* 2072 */       return 0;
/*      */     } 
/* 2078 */     int count = 1;
/*      */     while (true) {
/* 2080 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar("; expected an identifier");
/* 2082 */       if (c != ':' && !isNameChar(c))
/*      */         break; 
/* 2085 */       count++;
/*      */     } 
/* 2087 */     return count;
/*      */   }
/*      */   
/*      */   protected final String parseSystemId(char quoteChar, boolean convertLFs, String errorMsg) throws XMLStreamException {
/* 2106 */     char[] buf = getNameBuffer(-1);
/* 2107 */     int ptr = 0;
/*      */     while (true) {
/* 2110 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(errorMsg);
/* 2112 */       if (c == quoteChar)
/*      */         break; 
/* 2119 */       if (c == '\n') {
/* 2120 */         markLF();
/* 2121 */       } else if (c == '\r') {
/* 2122 */         if (peekNext() == 10) {
/* 2123 */           this.mInputPtr++;
/* 2124 */           if (!convertLFs) {
/* 2128 */             if (ptr >= buf.length)
/* 2129 */               buf = expandBy50Pct(buf); 
/* 2131 */             buf[ptr++] = '\r';
/*      */           } 
/* 2133 */           c = '\n';
/* 2134 */         } else if (convertLFs) {
/* 2135 */           c = '\n';
/*      */         } 
/*      */       } 
/* 2140 */       if (ptr >= buf.length)
/* 2141 */         buf = expandBy50Pct(buf); 
/* 2143 */       buf[ptr++] = c;
/*      */     } 
/* 2146 */     return (ptr == 0) ? "" : new String(buf, 0, ptr);
/*      */   }
/*      */   
/*      */   protected final String parsePublicId(char quoteChar, String errorMsg) throws XMLStreamException {
/* 2166 */     char[] buf = getNameBuffer(-1);
/* 2167 */     int ptr = 0;
/* 2168 */     boolean spaceToAdd = false;
/*      */     while (true) {
/* 2171 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(errorMsg);
/* 2173 */       if (c == quoteChar)
/*      */         break; 
/* 2176 */       if (c == '\n') {
/* 2177 */         markLF();
/* 2178 */         spaceToAdd = true;
/*      */         continue;
/*      */       } 
/* 2180 */       if (c == '\r') {
/* 2181 */         if (peekNext() == 10)
/* 2182 */           this.mInputPtr++; 
/* 2184 */         spaceToAdd = true;
/*      */         continue;
/*      */       } 
/* 2186 */       if (c == ' ') {
/* 2187 */         spaceToAdd = true;
/*      */         continue;
/*      */       } 
/* 2191 */       if (c >= '' || sPubidValidity[c] != 1)
/* 2193 */         throwUnexpectedChar(c, " in public identifier"); 
/* 2198 */       if (ptr >= buf.length)
/* 2199 */         buf = expandBy50Pct(buf); 
/* 2204 */       if (spaceToAdd) {
/* 2205 */         if (c == ' ')
/*      */           continue; 
/* 2212 */         spaceToAdd = false;
/* 2213 */         if (ptr > 0) {
/* 2214 */           buf[ptr++] = ' ';
/* 2215 */           if (ptr >= buf.length)
/* 2216 */             buf = expandBy50Pct(buf); 
/*      */         } 
/*      */       } 
/* 2220 */       buf[ptr++] = c;
/*      */     } 
/* 2223 */     return (ptr == 0) ? "" : new String(buf, 0, ptr);
/*      */   }
/*      */   
/*      */   protected final void parseUntil(TextBuffer tb, char endChar, boolean convertLFs, String errorMsg) throws XMLStreamException {
/* 2231 */     if (this.mInputPtr >= this.mInputEnd)
/* 2232 */       loadMore(errorMsg); 
/*      */     while (true) {
/* 2236 */       char[] inputBuf = this.mInputBuffer;
/* 2237 */       int inputLen = this.mInputEnd;
/* 2238 */       int ptr = this.mInputPtr;
/* 2239 */       int startPtr = ptr;
/* 2240 */       while (ptr < inputLen) {
/* 2241 */         char c = inputBuf[ptr++];
/* 2242 */         if (c == endChar) {
/* 2243 */           int i = ptr - startPtr - 1;
/* 2244 */           if (i > 0)
/* 2245 */             tb.append(inputBuf, startPtr, i); 
/* 2247 */           this.mInputPtr = ptr;
/*      */           return;
/*      */         } 
/* 2250 */         if (c == '\n') {
/* 2251 */           this.mInputPtr = ptr;
/* 2252 */           markLF();
/*      */           continue;
/*      */         } 
/* 2253 */         if (c == '\r') {
/* 2254 */           if (!convertLFs && ptr < inputLen) {
/* 2255 */             if (inputBuf[ptr] == '\n')
/* 2256 */               ptr++; 
/* 2258 */             this.mInputPtr = ptr;
/* 2259 */             markLF();
/*      */             continue;
/*      */           } 
/* 2261 */           int i = ptr - startPtr - 1;
/* 2262 */           if (i > 0)
/* 2263 */             tb.append(inputBuf, startPtr, i); 
/* 2265 */           this.mInputPtr = ptr;
/* 2266 */           c = getNextChar(errorMsg);
/* 2267 */           if (c != '\n') {
/* 2268 */             this.mInputPtr--;
/* 2269 */             tb.append(convertLFs ? 10 : 13);
/* 2271 */           } else if (convertLFs) {
/* 2272 */             tb.append('\n');
/*      */           } else {
/* 2274 */             tb.append('\r');
/* 2275 */             tb.append('\n');
/*      */           } 
/* 2278 */           startPtr = ptr = this.mInputPtr;
/* 2279 */           markLF();
/*      */         } 
/*      */       } 
/* 2283 */       int thisLen = ptr - startPtr;
/* 2284 */       if (thisLen > 0)
/* 2285 */         tb.append(inputBuf, startPtr, thisLen); 
/* 2287 */       loadMore(errorMsg);
/* 2288 */       startPtr = ptr = this.mInputPtr;
/* 2289 */       inputBuf = this.mInputBuffer;
/* 2290 */       inputLen = this.mInputEnd;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int resolveCharEnt(StringBuffer originalCharacters) throws XMLStreamException {
/* 2303 */     int value = 0;
/* 2304 */     char c = getNextChar(" in entity reference");
/* 2306 */     if (originalCharacters != null)
/* 2307 */       originalCharacters.append(c); 
/* 2310 */     if (c == 'x') {
/*      */       while (true) {
/* 2312 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in entity reference");
/* 2314 */         if (c == ';')
/*      */           break; 
/* 2318 */         if (originalCharacters != null)
/* 2319 */           originalCharacters.append(c); 
/* 2321 */         value <<= 4;
/* 2322 */         if (c <= '9' && c >= '0') {
/* 2323 */           value += c - 48;
/* 2324 */         } else if (c >= 'a' && c <= 'f') {
/* 2325 */           value += 10 + c - 97;
/* 2326 */         } else if (c >= 'A' && c <= 'F') {
/* 2327 */           value += 10 + c - 65;
/*      */         } else {
/* 2329 */           throwUnexpectedChar(c, "; expected a hex digit (0-9a-fA-F).");
/*      */         } 
/* 2332 */         if (value > 1114111)
/* 2333 */           reportUnicodeOverflow(); 
/*      */       } 
/*      */     } else {
/* 2337 */       while (c != ';') {
/* 2338 */         if (c <= '9' && c >= '0') {
/* 2339 */           value = value * 10 + c - 48;
/* 2341 */           if (value > 1114111)
/* 2342 */             reportUnicodeOverflow(); 
/*      */         } else {
/* 2345 */           throwUnexpectedChar(c, "; expected a decimal number.");
/*      */         } 
/* 2347 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in entity reference");
/* 2350 */         if (originalCharacters != null && c != ';')
/* 2351 */           originalCharacters.append(c); 
/*      */       } 
/*      */     } 
/* 2355 */     validateChar(value);
/* 2356 */     return value;
/*      */   }
/*      */   
/*      */   private final void validateChar(int value) throws XMLStreamException {
/* 2369 */     if (value >= 55296) {
/* 2370 */       if (value < 57344)
/* 2371 */         reportIllegalChar(value); 
/* 2373 */       if (value > 65535) {
/* 2375 */         if (value > 1114111)
/* 2376 */           reportUnicodeOverflow(); 
/* 2378 */       } else if (value >= 65534) {
/* 2379 */         reportIllegalChar(value);
/*      */       } 
/* 2382 */     } else if (value < 32) {
/* 2383 */       if (value == 0)
/* 2384 */         throwParseError("Invalid character reference: null character not allowed in XML content."); 
/* 2387 */       if (!this.mXml11 && value != 9 && value != 10 && value != 13)
/* 2389 */         reportIllegalChar(value); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected final char[] getNameBuffer(int minSize) {
/* 2396 */     char[] buf = this.mNameBuffer;
/* 2398 */     if (buf == null) {
/* 2399 */       this.mNameBuffer = buf = new char[(minSize > 48) ? (minSize + 16) : 64];
/* 2400 */     } else if (minSize >= buf.length) {
/* 2401 */       int len = buf.length;
/* 2402 */       len += len >> 1;
/* 2403 */       this.mNameBuffer = buf = new char[(minSize >= len) ? (minSize + 16) : len];
/*      */     } 
/* 2405 */     return buf;
/*      */   }
/*      */   
/*      */   protected final char[] expandBy50Pct(char[] buf) {
/* 2410 */     int len = buf.length;
/* 2411 */     char[] newBuf = new char[len + (len >> 1)];
/* 2412 */     System.arraycopy(buf, 0, newBuf, 0, len);
/* 2413 */     return newBuf;
/*      */   }
/*      */   
/*      */   private void throwNsColonException(String name) throws XMLStreamException {
/* 2424 */     throwParseError("Illegal name \"{0}\" (PI target, entity/notation name): can not contain a colon (XML Namespaces 1.0#6)", name, (Object)null);
/*      */   }
/*      */   
/*      */   private void throwRecursionError(String entityName) throws XMLStreamException {
/* 2430 */     throwParseError("Illegal entity expansion: entity \"{0}\" expands itself recursively.", entityName, (Object)null);
/*      */   }
/*      */   
/*      */   private void reportUnicodeOverflow() throws XMLStreamException {
/* 2436 */     throwParseError("Illegal character entity: value higher than max allowed (0x{0})", Integer.toHexString(1114111), (Object)null);
/*      */   }
/*      */   
/*      */   private void reportIllegalChar(int value) throws XMLStreamException {
/* 2442 */     throwParseError("Illegal character entity: expansion character (code 0x{0}", Integer.toHexString(value), (Object)null);
/*      */   }
/*      */   
/*      */   public abstract Location getLocation();
/*      */   
/*      */   protected abstract EntityDecl findEntity(String paramString, Object paramObject) throws XMLStreamException;
/*      */   
/*      */   protected abstract void handleUndeclaredEntity(String paramString) throws XMLStreamException;
/*      */   
/*      */   protected abstract void handleIncompleteEntityProblem(WstxInputSource paramWstxInputSource) throws XMLStreamException;
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\StreamScanner.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */