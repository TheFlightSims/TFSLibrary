/*      */ package com.ctc.wstx.sr;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.cfg.ErrorConsts;
/*      */ import com.ctc.wstx.dtd.MinimalDTDReader;
/*      */ import com.ctc.wstx.ent.EntityDecl;
/*      */ import com.ctc.wstx.exc.WstxException;
/*      */ import com.ctc.wstx.io.BranchingReaderSource;
/*      */ import com.ctc.wstx.io.InputBootstrapper;
/*      */ import com.ctc.wstx.io.WstxInputSource;
/*      */ import com.ctc.wstx.util.DefaultXmlSymbolTable;
/*      */ import com.ctc.wstx.util.TextBuffer;
/*      */ import com.ctc.wstx.util.TextBuilder;
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import javax.xml.namespace.NamespaceContext;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.codehaus.stax2.AttributeInfo;
/*      */ import org.codehaus.stax2.DTDInfo;
/*      */ import org.codehaus.stax2.LocationInfo;
/*      */ import org.codehaus.stax2.XMLStreamLocation2;
/*      */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*      */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*      */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*      */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ 
/*      */ public abstract class BasicStreamReader extends StreamScanner implements StreamReaderImpl, DTDInfo, LocationInfo {
/*      */   static final int DOC_STANDALONE_UNKNOWN = 0;
/*      */   
/*      */   static final int DOC_STANDALONE_YES = 1;
/*      */   
/*      */   static final int DOC_STANDALONE_NO = 2;
/*      */   
/*      */   static final int STATE_PROLOG = 0;
/*      */   
/*      */   static final int STATE_TREE = 1;
/*      */   
/*      */   static final int STATE_EPILOG = 2;
/*      */   
/*      */   static final int STATE_MULTIDOC_HACK = 3;
/*      */   
/*      */   static final int STATE_CLOSED = 4;
/*      */   
/*      */   static final int TOKEN_NOT_STARTED = 0;
/*      */   
/*      */   static final int TOKEN_STARTED = 1;
/*      */   
/*      */   static final int TOKEN_PARTIAL_SINGLE = 2;
/*      */   
/*      */   static final int TOKEN_FULL_SINGLE = 3;
/*      */   
/*      */   static final int TOKEN_FULL_COALESCED = 4;
/*      */   
/*      */   protected static final int MASK_GET_TEXT = 6768;
/*      */   
/*      */   protected static final int MASK_GET_TEXT_XXX = 4208;
/*      */   
/*      */   protected static final int MASK_GET_TEXT_WITH_WRITER = 6776;
/*      */   
/*      */   protected static final int MASK_GET_ELEMENT_TEXT = 4688;
/*      */   
/*      */   static final int ALL_WS_UNKNOWN = 0;
/*      */   
/*      */   static final int ALL_WS_YES = 1;
/*      */   
/*      */   static final int ALL_WS_NO = 2;
/*      */   
/*      */   private static final int INDENT_CHECK_START = 16;
/*      */   
/*      */   private static final int INDENT_CHECK_MAX = 40;
/*      */   
/*  162 */   protected static final String sPrefixXml = DefaultXmlSymbolTable.getXmlSymbol();
/*      */   
/*  164 */   protected static final String sPrefixXmlns = DefaultXmlSymbolTable.getXmlnsSymbol();
/*      */   
/*      */   protected final int mConfigFlags;
/*      */   
/*      */   protected final boolean mCfgCoalesceText;
/*      */   
/*      */   protected final boolean mCfgReportTextAsChars;
/*      */   
/*      */   protected final boolean mCfgLazyParsing;
/*      */   
/*      */   protected final int mShortestTextSegment;
/*      */   
/*      */   protected final ReaderCreator mOwner;
/*      */   
/*  217 */   protected int mDocStandalone = 0;
/*      */   
/*      */   String mRootPrefix;
/*      */   
/*      */   String mRootLName;
/*      */   
/*      */   protected String mDtdPublicId;
/*      */   
/*      */   protected String mDtdSystemId;
/*      */   
/*      */   protected final TextBuffer mTextBuffer;
/*      */   
/*      */   protected final InputElementStack mElementStack;
/*      */   
/*      */   protected final AttributeCollector mAttrCollector;
/*      */   
/*      */   protected boolean mStDoctypeFound = false;
/*      */   
/*  286 */   protected int mTokenState = 4;
/*      */   
/*      */   protected final int mStTextThreshold;
/*      */   
/*      */   protected boolean mStEmptyElem = false;
/*      */   
/*      */   protected int mParseState;
/*      */   
/*  309 */   protected int mCurrToken = 7;
/*      */   
/*  316 */   protected int mSecondaryToken = 7;
/*      */   
/*      */   protected int mWsStatus;
/*      */   
/*      */   protected boolean mValidateText = false;
/*      */   
/*      */   protected int mCheckIndentation;
/*      */   
/*  345 */   protected XMLStreamException mPendingException = null;
/*      */   
/*  359 */   protected Map mGeneralEntities = null;
/*      */   
/*  370 */   protected int mVldContent = 4;
/*      */   
/*      */   protected boolean mReturnNullForDefaultNamespace;
/*      */   
/*      */   protected BasicStreamReader(InputBootstrapper bs, BranchingReaderSource input, ReaderCreator owner, ReaderConfig cfg, InputElementStack elemStack, boolean forER) throws XMLStreamException {
/*  399 */     super((WstxInputSource)input, cfg, cfg.getEntityResolver());
/*  401 */     this.mOwner = owner;
/*  403 */     this.mTextBuffer = TextBuffer.createRecyclableBuffer(cfg);
/*  407 */     this.mConfigFlags = cfg.getConfigFlags();
/*  408 */     this.mCfgCoalesceText = ((this.mConfigFlags & 0x2) != 0);
/*  409 */     this.mCfgReportTextAsChars = ((this.mConfigFlags & 0x200) == 0);
/*  410 */     this.mXml11 = cfg.isXml11();
/*  413 */     this.mCheckIndentation = this.mNormalizeLFs ? 16 : 0;
/*  422 */     this.mCfgLazyParsing = (!forER && (this.mConfigFlags & 0x40000) != 0);
/*  427 */     if (this.mCfgCoalesceText) {
/*  428 */       this.mStTextThreshold = 4;
/*  429 */       this.mShortestTextSegment = Integer.MAX_VALUE;
/*      */     } else {
/*  431 */       this.mStTextThreshold = 2;
/*  432 */       if (forER) {
/*  438 */         this.mShortestTextSegment = Integer.MAX_VALUE;
/*      */       } else {
/*  440 */         this.mShortestTextSegment = cfg.getShortestReportedTextSegment();
/*      */       } 
/*      */     } 
/*  446 */     this.mDocXmlVersion = bs.getDeclaredVersion();
/*  447 */     this.mDocInputEncoding = bs.getInputEncoding();
/*  448 */     this.mDocXmlEncoding = bs.getDeclaredEncoding();
/*  450 */     String sa = bs.getStandalone();
/*  451 */     if (sa == null) {
/*  452 */       this.mDocStandalone = 0;
/*  454 */     } else if ("yes".equals(sa)) {
/*  455 */       this.mDocStandalone = 1;
/*      */     } else {
/*  457 */       this.mDocStandalone = 2;
/*      */     } 
/*  468 */     this.mParseState = this.mConfig.inputParsingModeFragment() ? 1 : 0;
/*  473 */     this.mElementStack = elemStack;
/*  474 */     this.mAttrCollector = elemStack.getAttrCollector();
/*  477 */     input.initInputLocation(this, this.mCurrDepth);
/*  479 */     elemStack.connectReporter(this);
/*  480 */     this.mReturnNullForDefaultNamespace = this.mConfig.returnNullForDefaultNamespace();
/*      */   }
/*      */   
/*      */   protected static InputElementStack createElementStack(ReaderConfig cfg) {
/*  485 */     return new InputElementStack(cfg, cfg.willSupportNamespaces());
/*      */   }
/*      */   
/*      */   public String getCharacterEncodingScheme() {
/*  501 */     return this.mDocXmlEncoding;
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/*  511 */     return this.mDocInputEncoding;
/*      */   }
/*      */   
/*      */   public String getVersion() {
/*  516 */     if (this.mDocXmlVersion == 256)
/*  517 */       return "1.0"; 
/*  519 */     if (this.mDocXmlVersion == 272)
/*  520 */       return "1.1"; 
/*  522 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isStandalone() {
/*  526 */     return (this.mDocStandalone == 1);
/*      */   }
/*      */   
/*      */   public boolean standaloneSet() {
/*  530 */     return (this.mDocStandalone != 0);
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/*  546 */     if ("com.ctc.wstx.baseURL".equals(name))
/*  547 */       return this.mInput.getSource(); 
/*  554 */     return this.mConfig.safeGetProperty(name);
/*      */   }
/*      */   
/*      */   public int getAttributeCount() {
/*  566 */     if (this.mCurrToken != 1)
/*  567 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  569 */     return this.mAttrCollector.getCount();
/*      */   }
/*      */   
/*      */   public String getAttributeLocalName(int index) {
/*  573 */     if (this.mCurrToken != 1)
/*  574 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  576 */     return this.mAttrCollector.getLocalName(index);
/*      */   }
/*      */   
/*      */   public QName getAttributeName(int index) {
/*  580 */     if (this.mCurrToken != 1)
/*  581 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  583 */     return this.mAttrCollector.getQName(index);
/*      */   }
/*      */   
/*      */   public String getAttributeNamespace(int index) {
/*  587 */     if (this.mCurrToken != 1)
/*  588 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  591 */     String uri = this.mAttrCollector.getURI(index);
/*  592 */     return (uri == null) ? "" : uri;
/*      */   }
/*      */   
/*      */   public String getAttributePrefix(int index) {
/*  596 */     if (this.mCurrToken != 1)
/*  597 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  600 */     String p = this.mAttrCollector.getPrefix(index);
/*  601 */     return (p == null) ? "" : p;
/*      */   }
/*      */   
/*      */   public String getAttributeType(int index) {
/*  605 */     if (this.mCurrToken != 1)
/*  606 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  609 */     return this.mElementStack.getAttributeType(index);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(int index) {
/*  613 */     if (this.mCurrToken != 1)
/*  614 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  616 */     return this.mAttrCollector.getValue(index);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String nsURI, String localName) {
/*  620 */     if (this.mCurrToken != 1)
/*  621 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  623 */     return this.mAttrCollector.getValue(nsURI, localName);
/*      */   }
/*      */   
/*      */   public String getElementText() throws XMLStreamException {
/*      */     int type;
/*  640 */     if (this.mCurrToken != 1)
/*  641 */       throwParseError(ErrorConsts.ERR_STATE_NOT_STELEM, (Object)null, (Object)null); 
/*  647 */     if (this.mStEmptyElem) {
/*  653 */       this.mStEmptyElem = false;
/*  654 */       this.mCurrToken = 2;
/*  655 */       return "";
/*      */     } 
/*      */     while (true) {
/*  659 */       type = next();
/*  660 */       if (type == 2)
/*  661 */         return ""; 
/*  663 */       if (type == 5 || type == 3)
/*      */         continue; 
/*      */       break;
/*      */     } 
/*  666 */     if ((1 << type & 0x1250) == 0)
/*  667 */       throw _constructUnexpectedInTyped(type); 
/*  671 */     if (this.mTokenState < 3)
/*  672 */       readCoalescedText(this.mCurrToken, false); 
/*  678 */     if (this.mInputPtr + 1 < this.mInputEnd && this.mInputBuffer[this.mInputPtr] == '<' && this.mInputBuffer[this.mInputPtr + 1] == '/') {
/*  681 */       this.mInputPtr += 2;
/*  682 */       this.mCurrToken = 2;
/*  684 */       String result = this.mTextBuffer.contentsAsString();
/*  686 */       readEndElem();
/*  688 */       return result;
/*      */     } 
/*  692 */     int extra = 1 + (this.mTextBuffer.size() >> 1);
/*  693 */     StringBuffer sb = this.mTextBuffer.contentsAsStringBuffer(extra);
/*      */     int i;
/*  696 */     while ((i = next()) != 2) {
/*  697 */       if ((1 << i & 0x1250) != 0) {
/*  698 */         if (this.mTokenState < this.mStTextThreshold)
/*  699 */           finishToken(false); 
/*  701 */         this.mTextBuffer.contentsToStringBuffer(sb);
/*      */         continue;
/*      */       } 
/*  704 */       if (i != 5 && i != 3)
/*  705 */         throw _constructUnexpectedInTyped(i); 
/*      */     } 
/*  709 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public int getEventType() {
/*  721 */     if (this.mCurrToken == 12 && (
/*  722 */       this.mCfgCoalesceText || this.mCfgReportTextAsChars))
/*  723 */       return 4; 
/*  726 */     return this.mCurrToken;
/*      */   }
/*      */   
/*      */   public String getLocalName() {
/*  732 */     if (this.mCurrToken == 1 || this.mCurrToken == 2)
/*  733 */       return this.mElementStack.getLocalName(); 
/*  735 */     if (this.mCurrToken == 9)
/*  739 */       return (this.mCurrEntity == null) ? this.mCurrName : this.mCurrEntity.getName(); 
/*  741 */     throw new IllegalStateException("Current state not START_ELEMENT, END_ELEMENT or ENTITY_REFERENCE");
/*      */   }
/*      */   
/*      */   public QName getName() {
/*  748 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  749 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  751 */     return this.mElementStack.getCurrentElementName();
/*      */   }
/*      */   
/*      */   public NamespaceContext getNamespaceContext() {
/*  763 */     return this.mElementStack;
/*      */   }
/*      */   
/*      */   public int getNamespaceCount() {
/*  767 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  768 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  770 */     return this.mElementStack.getCurrentNsCount();
/*      */   }
/*      */   
/*      */   public String getNamespacePrefix(int index) {
/*  774 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  775 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  778 */     String p = this.mElementStack.getLocalNsPrefix(index);
/*  779 */     if (p == null)
/*  780 */       return this.mReturnNullForDefaultNamespace ? null : ""; 
/*  782 */     return p;
/*      */   }
/*      */   
/*      */   public String getNamespaceURI() {
/*  786 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  787 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  790 */     String uri = this.mElementStack.getNsURI();
/*  791 */     return (uri == null) ? "" : uri;
/*      */   }
/*      */   
/*      */   public String getNamespaceURI(int index) {
/*  796 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  797 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  800 */     String uri = this.mElementStack.getLocalNsURI(index);
/*  801 */     return (uri == null) ? "" : uri;
/*      */   }
/*      */   
/*      */   public String getNamespaceURI(String prefix) {
/*  806 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  807 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  812 */     return this.mElementStack.getNamespaceURI(prefix);
/*      */   }
/*      */   
/*      */   public String getPIData() {
/*  816 */     if (this.mCurrToken != 3)
/*  817 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_PI); 
/*  819 */     if (this.mTokenState <= 1)
/*  820 */       safeFinishToken(); 
/*  822 */     return this.mTextBuffer.contentsAsString();
/*      */   }
/*      */   
/*      */   public String getPITarget() {
/*  826 */     if (this.mCurrToken != 3)
/*  827 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_PI); 
/*  830 */     return this.mCurrName;
/*      */   }
/*      */   
/*      */   public String getPrefix() {
/*  834 */     if (this.mCurrToken != 1 && this.mCurrToken != 2)
/*  835 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_ELEM); 
/*  838 */     String p = this.mElementStack.getPrefix();
/*  839 */     return (p == null) ? "" : p;
/*      */   }
/*      */   
/*      */   public String getText() {
/*  844 */     if ((1 << this.mCurrToken & 0x1A70) == 0)
/*  845 */       throwNotTextual(this.mCurrToken); 
/*  847 */     if (this.mTokenState < this.mStTextThreshold)
/*  848 */       safeFinishToken(); 
/*  850 */     if (this.mCurrToken == 9)
/*  851 */       return (this.mCurrEntity == null) ? null : this.mCurrEntity.getReplacementText(); 
/*  853 */     if (this.mCurrToken == 11)
/*  858 */       return getDTDInternalSubset(); 
/*  860 */     return this.mTextBuffer.contentsAsString();
/*      */   }
/*      */   
/*      */   public char[] getTextCharacters() {
/*  865 */     if ((1 << this.mCurrToken & 0x1070) == 0)
/*  866 */       throwNotTextXxx(this.mCurrToken); 
/*  868 */     if (this.mTokenState < this.mStTextThreshold)
/*  869 */       safeFinishToken(); 
/*  871 */     if (this.mCurrToken == 9)
/*  872 */       return this.mCurrEntity.getReplacementChars(); 
/*  874 */     if (this.mCurrToken == 11)
/*  875 */       return getDTDInternalSubsetArray(); 
/*  877 */     return this.mTextBuffer.getTextBuffer();
/*      */   }
/*      */   
/*      */   public int getTextCharacters(int sourceStart, char[] target, int targetStart, int len) {
/*  882 */     if ((1 << this.mCurrToken & 0x1070) == 0)
/*  883 */       throwNotTextXxx(this.mCurrToken); 
/*  885 */     if (this.mTokenState < this.mStTextThreshold)
/*  886 */       safeFinishToken(); 
/*  888 */     return this.mTextBuffer.contentsToArray(sourceStart, target, targetStart, len);
/*      */   }
/*      */   
/*      */   public int getTextLength() {
/*  893 */     if ((1 << this.mCurrToken & 0x1070) == 0)
/*  894 */       throwNotTextXxx(this.mCurrToken); 
/*  896 */     if (this.mTokenState < this.mStTextThreshold)
/*  897 */       safeFinishToken(); 
/*  899 */     return this.mTextBuffer.size();
/*      */   }
/*      */   
/*      */   public int getTextStart() {
/*  904 */     if ((1 << this.mCurrToken & 0x1070) == 0)
/*  905 */       throwNotTextXxx(this.mCurrToken); 
/*  907 */     if (this.mTokenState < this.mStTextThreshold)
/*  908 */       safeFinishToken(); 
/*  910 */     return this.mTextBuffer.getTextStart();
/*      */   }
/*      */   
/*      */   public boolean hasName() {
/*  914 */     return (this.mCurrToken == 1 || this.mCurrToken == 2);
/*      */   }
/*      */   
/*      */   public boolean hasNext() {
/*  921 */     return (this.mCurrToken != 8 || this.mParseState == 3);
/*      */   }
/*      */   
/*      */   public boolean hasText() {
/*  926 */     return ((1 << this.mCurrToken & 0x1A70) != 0);
/*      */   }
/*      */   
/*      */   public boolean isAttributeSpecified(int index) {
/*  933 */     if (this.mCurrToken != 1)
/*  934 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*  936 */     return this.mAttrCollector.isSpecified(index);
/*      */   }
/*      */   
/*      */   public boolean isCharacters() {
/*  950 */     return (getEventType() == 4);
/*      */   }
/*      */   
/*      */   public boolean isEndElement() {
/*  954 */     return (this.mCurrToken == 2);
/*      */   }
/*      */   
/*      */   public boolean isStartElement() {
/*  958 */     return (this.mCurrToken == 1);
/*      */   }
/*      */   
/*      */   public boolean isWhiteSpace() {
/*  969 */     if (this.mCurrToken == 4 || this.mCurrToken == 12) {
/*  970 */       if (this.mTokenState < this.mStTextThreshold)
/*  971 */         safeFinishToken(); 
/*  973 */       if (this.mWsStatus == 0)
/*  974 */         this.mWsStatus = this.mTextBuffer.isAllWhitespace() ? 1 : 2; 
/*  977 */       return (this.mWsStatus == 1);
/*      */     } 
/*  979 */     return (this.mCurrToken == 6);
/*      */   }
/*      */   
/*      */   public void require(int type, String nsUri, String localName) throws XMLStreamException {
/*  985 */     int curr = this.mCurrToken;
/*  991 */     if (curr != type)
/*  992 */       if (curr == 12) {
/*  993 */         if (this.mCfgCoalesceText || this.mCfgReportTextAsChars)
/*  994 */           curr = 4; 
/*  996 */       } else if (curr == 6) {
/*      */       
/*      */       }  
/* 1003 */     if (type != curr)
/* 1004 */       throwParseError("Expected type " + tokenTypeDesc(type) + ", current type " + tokenTypeDesc(curr)); 
/* 1009 */     if (localName != null) {
/* 1010 */       if (curr != 1 && curr != 2 && curr != 9)
/* 1012 */         throwParseError("Expected non-null local name, but current token not a START_ELEMENT, END_ELEMENT or ENTITY_REFERENCE (was " + tokenTypeDesc(this.mCurrToken) + ")"); 
/* 1014 */       String n = getLocalName();
/* 1015 */       if (n != localName && !n.equals(localName))
/* 1016 */         throwParseError("Expected local name '" + localName + "'; current local name '" + n + "'."); 
/*      */     } 
/* 1019 */     if (nsUri != null) {
/* 1020 */       if (curr != 1 && curr != 2)
/* 1021 */         throwParseError("Expected non-null NS URI, but current token not a START_ELEMENT or END_ELEMENT (was " + tokenTypeDesc(curr) + ")"); 
/* 1023 */       String uri = this.mElementStack.getNsURI();
/* 1025 */       if (nsUri.length() == 0) {
/* 1026 */         if (uri != null && uri.length() > 0)
/* 1027 */           throwParseError("Expected empty namespace, instead have '" + uri + "'."); 
/* 1030 */       } else if (nsUri != uri && !nsUri.equals(uri)) {
/* 1031 */         throwParseError("Expected namespace '" + nsUri + "'; have '" + uri + "'.");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public final int next() throws XMLStreamException {
/* 1052 */     if (this.mPendingException != null) {
/* 1053 */       XMLStreamException strEx = this.mPendingException;
/* 1054 */       this.mPendingException = null;
/* 1055 */       throw strEx;
/*      */     } 
/* 1061 */     if (this.mParseState == 1) {
/* 1062 */       int type = nextFromTree();
/* 1063 */       this.mCurrToken = type;
/* 1064 */       if (this.mTokenState < this.mStTextThreshold)
/* 1069 */         if (!this.mCfgLazyParsing || (this.mValidateText && (type == 4 || type == 12)))
/* 1071 */           finishToken(false);  
/* 1080 */       if (type == 12) {
/* 1081 */         if (this.mValidateText)
/* 1082 */           this.mElementStack.validateText(this.mTextBuffer, false); 
/* 1084 */         if (this.mCfgCoalesceText || this.mCfgReportTextAsChars)
/* 1085 */           return 4; 
/* 1091 */       } else if (type == 4 && 
/* 1092 */         this.mValidateText) {
/* 1100 */         if (this.mInputPtr + 1 < this.mInputEnd && this.mInputBuffer[this.mInputPtr] == '<' && this.mInputBuffer[this.mInputPtr + 1] == '/') {
/* 1104 */           this.mElementStack.validateText(this.mTextBuffer, true);
/*      */         } else {
/* 1106 */           this.mElementStack.validateText(this.mTextBuffer, false);
/*      */         } 
/*      */       } 
/* 1110 */       return type;
/*      */     } 
/* 1113 */     if (this.mParseState == 0) {
/* 1114 */       nextFromProlog(true);
/* 1115 */     } else if (this.mParseState == 2) {
/* 1116 */       if (nextFromProlog(false))
/* 1118 */         this.mSecondaryToken = 0; 
/* 1121 */     } else if (this.mParseState == 3) {
/* 1122 */       this.mCurrToken = nextFromMultiDocState();
/*      */     } else {
/* 1124 */       if (this.mSecondaryToken == 8) {
/* 1125 */         this.mSecondaryToken = 0;
/* 1126 */         return 8;
/*      */       } 
/* 1128 */       throw new NoSuchElementException();
/*      */     } 
/* 1130 */     return this.mCurrToken;
/*      */   }
/*      */   
/*      */   public int nextTag() throws XMLStreamException {
/*      */     while (true) {
/* 1137 */       int next = next();
/* 1139 */       switch (next) {
/*      */         case 3:
/*      */         case 5:
/*      */         case 6:
/*      */           continue;
/*      */         case 4:
/*      */         case 12:
/* 1146 */           if (isWhiteSpace())
/*      */             continue; 
/* 1149 */           throwParseError("Received non-all-whitespace CHARACTERS or CDATA event in nextTag().");
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/* 1153 */           return next;
/*      */       } 
/* 1155 */       throwParseError("Received event " + ErrorConsts.tokenTypeDesc(next) + ", instead of START_ELEMENT or END_ELEMENT.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() throws XMLStreamException {
/* 1170 */     if (this.mParseState != 4) {
/* 1171 */       this.mParseState = 4;
/* 1176 */       if (this.mCurrToken != 8) {
/* 1177 */         this.mCurrToken = this.mSecondaryToken = 8;
/* 1178 */         if (this.mSymbols.isDirty())
/* 1179 */           this.mOwner.updateSymbolTable(this.mSymbols); 
/*      */       } 
/* 1189 */       closeAllInput(false);
/* 1191 */       this.mTextBuffer.recycle(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getFeature(String name) {
/* 1206 */     throw new IllegalArgumentException(MessageFormat.format(ErrorConsts.ERR_UNKNOWN_FEATURE, new Object[] { name }));
/*      */   }
/*      */   
/*      */   public void setFeature(String name, Object value) {
/* 1212 */     throw new IllegalArgumentException(MessageFormat.format(ErrorConsts.ERR_UNKNOWN_FEATURE, new Object[] { name }));
/*      */   }
/*      */   
/*      */   public boolean isPropertySupported(String name) {
/* 1219 */     return this.mConfig.isPropertySupported(name);
/*      */   }
/*      */   
/*      */   public boolean setProperty(String name, Object value) {
/* 1231 */     boolean ok = this.mConfig.setProperty(name, value);
/* 1236 */     if (ok && "com.ctc.wstx.baseURL".equals(name))
/* 1238 */       this.mInput.overrideSource(this.mConfig.getBaseURL()); 
/* 1240 */     return ok;
/*      */   }
/*      */   
/*      */   public void skipElement() throws XMLStreamException {
/* 1247 */     if (this.mCurrToken != 1)
/* 1248 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/* 1250 */     int nesting = 1;
/*      */     while (true) {
/* 1253 */       int type = next();
/* 1254 */       if (type == 1) {
/* 1255 */         nesting++;
/*      */         continue;
/*      */       } 
/* 1256 */       if (type == 2 && 
/* 1257 */         --nesting == 0)
/*      */         break; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public AttributeInfo getAttributeInfo() throws XMLStreamException {
/* 1268 */     if (this.mCurrToken != 1)
/* 1269 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/* 1275 */     return this.mElementStack;
/*      */   }
/*      */   
/*      */   public DTDInfo getDTDInfo() throws XMLStreamException {
/* 1289 */     if (this.mCurrToken != 11)
/* 1290 */       return null; 
/* 1292 */     if (this.mTokenState < 3)
/* 1293 */       finishToken(false); 
/* 1295 */     return this;
/*      */   }
/*      */   
/*      */   public final LocationInfo getLocationInfo() {
/* 1304 */     return this;
/*      */   }
/*      */   
/*      */   public int getText(Writer w, boolean preserveContents) throws IOException, XMLStreamException {
/* 1334 */     if ((1 << this.mCurrToken & 0x1A78) == 0)
/* 1335 */       throwNotTextual(this.mCurrToken); 
/* 1342 */     if (!preserveContents) {
/* 1343 */       if (this.mCurrToken == 4) {
/* 1344 */         int count = this.mTextBuffer.rawContentsTo(w);
/* 1350 */         this.mTextBuffer.resetWithEmpty();
/* 1351 */         if (this.mTokenState < 3)
/* 1352 */           count += readAndWriteText(w); 
/* 1354 */         if (this.mCfgCoalesceText && this.mTokenState < 4)
/* 1356 */           if (this.mCfgCoalesceText)
/* 1357 */             count += readAndWriteCoalesced(w, false);  
/* 1360 */         return count;
/*      */       } 
/* 1361 */       if (this.mCurrToken == 12) {
/* 1362 */         int count = this.mTextBuffer.rawContentsTo(w);
/* 1363 */         this.mTextBuffer.resetWithEmpty();
/* 1364 */         if (this.mTokenState < 3)
/* 1365 */           count += readAndWriteCData(w); 
/* 1367 */         if (this.mCfgCoalesceText && this.mTokenState < 4)
/* 1369 */           if (this.mCfgCoalesceText)
/* 1370 */             count += readAndWriteCoalesced(w, true);  
/* 1373 */         return count;
/*      */       } 
/*      */     } 
/* 1376 */     if (this.mTokenState < this.mStTextThreshold)
/* 1380 */       finishToken(false); 
/* 1382 */     if (this.mCurrToken == 9)
/* 1383 */       return this.mCurrEntity.getReplacementText(w); 
/* 1385 */     if (this.mCurrToken == 11) {
/* 1386 */       char[] ch = getDTDInternalSubsetArray();
/* 1387 */       if (ch != null) {
/* 1388 */         w.write(ch);
/* 1389 */         return ch.length;
/*      */       } 
/* 1391 */       return 0;
/*      */     } 
/* 1393 */     return this.mTextBuffer.rawContentsTo(w);
/*      */   }
/*      */   
/*      */   public int getDepth() {
/* 1408 */     return this.mElementStack.getDepth();
/*      */   }
/*      */   
/*      */   public boolean isEmptyElement() throws XMLStreamException {
/* 1418 */     return (this.mCurrToken == 1) ? this.mStEmptyElem : false;
/*      */   }
/*      */   
/*      */   public NamespaceContext getNonTransientNamespaceContext() {
/* 1424 */     return (NamespaceContext)this.mElementStack.createNonTransientNsContext(null);
/*      */   }
/*      */   
/*      */   public String getPrefixedName() {
/*      */     String prefix;
/*      */     String ln;
/*      */     StringBuffer sb;
/* 1429 */     switch (this.mCurrToken) {
/*      */       case 1:
/*      */       case 2:
/* 1433 */         prefix = this.mElementStack.getPrefix();
/* 1434 */         ln = this.mElementStack.getLocalName();
/* 1436 */         if (prefix == null)
/* 1437 */           return ln; 
/* 1439 */         sb = new StringBuffer(ln.length() + 1 + prefix.length());
/* 1440 */         sb.append(prefix);
/* 1441 */         sb.append(':');
/* 1442 */         sb.append(ln);
/* 1443 */         return sb.toString();
/*      */       case 9:
/* 1446 */         return getLocalName();
/*      */       case 3:
/* 1448 */         return getPITarget();
/*      */       case 11:
/* 1450 */         return getDTDRootName();
/*      */     } 
/* 1453 */     throw new IllegalStateException("Current state not START_ELEMENT, END_ELEMENT, ENTITY_REFERENCE, PROCESSING_INSTRUCTION or DTD");
/*      */   }
/*      */   
/*      */   public void closeCompletely() throws XMLStreamException {
/* 1458 */     closeAllInput(true);
/*      */   }
/*      */   
/*      */   public Object getProcessedDTD() {
/* 1472 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDRootName() {
/* 1476 */     if (this.mRootPrefix == null)
/* 1477 */       return this.mRootLName; 
/* 1479 */     return this.mRootPrefix + ":" + this.mRootLName;
/*      */   }
/*      */   
/*      */   public String getDTDPublicId() {
/* 1483 */     return this.mDtdPublicId;
/*      */   }
/*      */   
/*      */   public String getDTDSystemId() {
/* 1487 */     return this.mDtdSystemId;
/*      */   }
/*      */   
/*      */   public String getDTDInternalSubset() {
/* 1495 */     if (this.mCurrToken != 11)
/* 1496 */       return null; 
/* 1498 */     return this.mTextBuffer.contentsAsString();
/*      */   }
/*      */   
/*      */   private char[] getDTDInternalSubsetArray() {
/* 1509 */     return this.mTextBuffer.contentsAsArray();
/*      */   }
/*      */   
/*      */   public DTDValidationSchema getProcessedDTDSchema() {
/* 1518 */     return null;
/*      */   }
/*      */   
/*      */   public long getStartingByteOffset() {
/* 1534 */     return -1L;
/*      */   }
/*      */   
/*      */   public long getStartingCharOffset() {
/* 1538 */     return this.mTokenInputTotal;
/*      */   }
/*      */   
/*      */   public long getEndingByteOffset() throws XMLStreamException {
/* 1547 */     return -1L;
/*      */   }
/*      */   
/*      */   public long getEndingCharOffset() throws XMLStreamException {
/* 1553 */     if (this.mTokenState < this.mStTextThreshold)
/* 1554 */       finishToken(false); 
/* 1556 */     return this.mCurrInputProcessed + this.mInputPtr;
/*      */   }
/*      */   
/*      */   public final Location getLocation() {
/* 1562 */     return (Location)getStartLocation();
/*      */   }
/*      */   
/*      */   public final XMLStreamLocation2 getEndLocation() throws XMLStreamException {
/* 1572 */     if (this.mTokenState < this.mStTextThreshold)
/* 1573 */       finishToken(false); 
/* 1576 */     return getCurrentLocation();
/*      */   }
/*      */   
/*      */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 1589 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 1596 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 1603 */     return null;
/*      */   }
/*      */   
/*      */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 1609 */     return null;
/*      */   }
/*      */   
/*      */   public EntityDecl getCurrentEntityDecl() {
/* 1619 */     return this.mCurrEntity;
/*      */   }
/*      */   
/*      */   public Object withStartElement(ElemCallback cb, Location loc) {
/* 1632 */     if (this.mCurrToken != 1)
/* 1633 */       return null; 
/* 1635 */     return cb.withStartElement(loc, getName(), this.mElementStack.createNonTransientNsContext(loc), this.mAttrCollector.buildAttrOb(), this.mStEmptyElem);
/*      */   }
/*      */   
/*      */   public boolean isNamespaceAware() {
/* 1642 */     return this.mCfgNsEnabled;
/*      */   }
/*      */   
/*      */   public InputElementStack getInputElementStack() {
/* 1651 */     return this.mElementStack;
/*      */   }
/*      */   
/*      */   public AttributeCollector getAttributeCollector() {
/* 1660 */     return this.mAttrCollector;
/*      */   }
/*      */   
/*      */   public void fireSaxStartElement(ContentHandler h, Attributes attrs) throws SAXException {
/* 1672 */     if (h != null) {
/* 1674 */       int nsCount = this.mElementStack.getCurrentNsCount();
/* 1675 */       for (int i = 0; i < nsCount; i++) {
/* 1676 */         String prefix = this.mElementStack.getLocalNsPrefix(i);
/* 1677 */         String str1 = this.mElementStack.getLocalNsURI(i);
/* 1678 */         h.startPrefixMapping((prefix == null) ? "" : prefix, str1);
/*      */       } 
/* 1682 */       String uri = this.mElementStack.getNsURI();
/* 1684 */       h.startElement((uri == null) ? "" : uri, this.mElementStack.getLocalName(), getPrefixedName(), attrs);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxEndElement(ContentHandler h) throws SAXException {
/* 1692 */     if (h != null) {
/* 1696 */       String uri = this.mElementStack.getNsURI();
/* 1698 */       h.endElement((uri == null) ? "" : uri, this.mElementStack.getLocalName(), getPrefixedName());
/* 1701 */       int nsCount = this.mElementStack.getCurrentNsCount();
/* 1702 */       for (int i = 0; i < nsCount; i++) {
/* 1703 */         String prefix = this.mElementStack.getLocalNsPrefix(i);
/* 1705 */         h.endPrefixMapping((prefix == null) ? "" : prefix);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxCharacterEvents(ContentHandler h) throws XMLStreamException, SAXException {
/* 1713 */     if (h != null) {
/* 1714 */       if (this.mPendingException != null) {
/* 1715 */         XMLStreamException sex = this.mPendingException;
/* 1716 */         this.mPendingException = null;
/* 1717 */         throw sex;
/*      */       } 
/* 1722 */       if (this.mTokenState < this.mStTextThreshold)
/* 1723 */         finishToken(false); 
/* 1725 */       this.mTextBuffer.fireSaxCharacterEvents(h);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxSpaceEvents(ContentHandler h) throws XMLStreamException, SAXException {
/* 1732 */     if (h != null) {
/* 1733 */       if (this.mTokenState < this.mStTextThreshold)
/* 1734 */         finishToken(false); 
/* 1736 */       this.mTextBuffer.fireSaxSpaceEvents(h);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxCommentEvent(LexicalHandler h) throws XMLStreamException, SAXException {
/* 1743 */     if (h != null) {
/* 1744 */       if (this.mTokenState < this.mStTextThreshold)
/* 1745 */         finishToken(false); 
/* 1747 */       this.mTextBuffer.fireSaxCommentEvent(h);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxPIEvent(ContentHandler h) throws XMLStreamException, SAXException {
/* 1754 */     if (h != null) {
/* 1755 */       if (this.mTokenState < this.mStTextThreshold)
/* 1756 */         finishToken(false); 
/* 1758 */       h.processingInstruction(this.mCurrName, this.mTextBuffer.contentsAsString());
/*      */     } 
/*      */   }
/*      */   
/*      */   protected final boolean hasConfigFlags(int flags) {
/* 1769 */     return ((this.mConfigFlags & flags) == flags);
/*      */   }
/*      */   
/*      */   protected String checkKeyword(char c, String expected) throws XMLStreamException {
/* 1785 */     int ptr = 0;
/* 1786 */     int len = expected.length();
/* 1788 */     while (expected.charAt(ptr) == c && ++ptr < len) {
/* 1789 */       if (this.mInputPtr < this.mInputEnd) {
/* 1790 */         c = this.mInputBuffer[this.mInputPtr++];
/*      */         continue;
/*      */       } 
/* 1792 */       int ci = getNext();
/* 1793 */       if (ci < 0)
/*      */         break; 
/* 1796 */       c = (char)ci;
/*      */     } 
/* 1800 */     if (ptr == len) {
/* 1802 */       int i = peekNext();
/* 1803 */       if (i < 0 || (!isNameChar((char)i) && i != 58))
/* 1804 */         return null; 
/*      */     } 
/* 1809 */     StringBuffer sb = new StringBuffer(expected.length() + 16);
/* 1810 */     sb.append(expected.substring(0, ptr));
/* 1811 */     if (ptr < len)
/* 1812 */       sb.append(c); 
/*      */     while (true) {
/* 1816 */       if (this.mInputPtr < this.mInputEnd) {
/* 1817 */         c = this.mInputBuffer[this.mInputPtr++];
/*      */       } else {
/* 1819 */         int ci = getNext();
/* 1820 */         if (ci < 0)
/*      */           break; 
/* 1823 */         c = (char)ci;
/*      */       } 
/* 1825 */       if (!isNameChar(c)) {
/* 1827 */         this.mInputPtr--;
/*      */         break;
/*      */       } 
/* 1830 */       sb.append(c);
/*      */     } 
/* 1833 */     return sb.toString();
/*      */   }
/*      */   
/*      */   protected void checkCData() throws XMLStreamException {
/* 1839 */     String wrong = checkKeyword(getNextCharFromCurrent(" in CDATA section"), "CDATA");
/* 1840 */     if (wrong != null)
/* 1841 */       throwParseError("Unrecognized XML directive '" + wrong + "'; expected 'CDATA'."); 
/* 1844 */     char c = getNextCharFromCurrent(" in CDATA section");
/* 1845 */     if (c != '[')
/* 1846 */       throwUnexpectedChar(c, "excepted '[' after '<![CDATA'"); 
/*      */   }
/*      */   
/*      */   private final void parseAttrValue(char openingQuote, TextBuilder tb) throws XMLStreamException {
/* 1864 */     char[] outBuf = tb.getCharBuffer();
/* 1865 */     int outPtr = tb.getCharSize();
/* 1866 */     int outLen = outBuf.length;
/* 1867 */     WstxInputSource currScope = this.mInput;
/*      */     while (true) {
/* 1870 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(" in attribute value");
/* 1873 */       if (c <= '\'') {
/* 1874 */         if (c < ' ') {
/* 1875 */           if (c == '\n') {
/* 1876 */             markLF();
/* 1877 */           } else if (c == '\r') {
/* 1886 */             if (this.mNormalizeLFs) {
/* 1887 */               c = getNextChar(" in attribute value");
/* 1888 */               if (c != '\n')
/* 1889 */                 this.mInputPtr--; 
/*      */             } 
/* 1892 */             markLF();
/* 1893 */           } else if (c != '\t') {
/* 1894 */             throwInvalidSpace(c);
/*      */           } 
/* 1897 */           c = ' ';
/* 1898 */         } else if (c == openingQuote) {
/* 1904 */           if (this.mInput == currScope)
/*      */             break; 
/* 1907 */         } else if (c == '&') {
/*      */           int ch;
/* 1909 */           if (inputInBuffer() < 3 || (ch = resolveSimpleEntity(true)) == 0) {
/* 1914 */             ch = fullyResolveEntity(false);
/* 1915 */             if (ch == 0)
/*      */               continue; 
/*      */           } 
/* 1920 */           if (ch <= 65535) {
/* 1921 */             c = (char)ch;
/*      */           } else {
/* 1923 */             ch -= 65536;
/* 1924 */             if (outPtr >= outLen) {
/* 1925 */               outBuf = tb.bufferFull(1);
/* 1926 */               outLen = outBuf.length;
/*      */             } 
/* 1928 */             outBuf[outPtr++] = (char)((ch >> 10) + 55296);
/* 1929 */             c = (char)((ch & 0x3FF) + 56320);
/*      */           } 
/*      */         } 
/* 1932 */       } else if (c == '<') {
/* 1933 */         throwParseError("Unexpected '<'  in attribute value");
/*      */       } 
/* 1937 */       if (outPtr >= outLen) {
/* 1938 */         outBuf = tb.bufferFull(1);
/* 1939 */         outLen = outBuf.length;
/*      */       } 
/* 1941 */       outBuf[outPtr++] = c;
/*      */     } 
/* 1945 */     tb.setBufferSize(outPtr);
/*      */   }
/*      */   
/*      */   private boolean nextFromProlog(boolean isProlog) throws XMLStreamException {
/*      */     int i;
/* 1968 */     if (this.mTokenState < this.mStTextThreshold) {
/* 1969 */       this.mTokenState = 4;
/* 1970 */       i = skipToken();
/*      */     } else {
/* 1974 */       this.mTokenInputTotal = this.mCurrInputProcessed + this.mInputPtr;
/* 1975 */       this.mTokenInputRow = this.mCurrInputRow;
/* 1976 */       this.mTokenInputCol = this.mInputPtr - this.mCurrInputRowStart;
/* 1977 */       i = getNext();
/*      */     } 
/* 1981 */     if (i <= 32 && i >= 0) {
/* 1983 */       if (hasConfigFlags(256)) {
/* 1984 */         this.mCurrToken = 6;
/* 1985 */         if (readSpacePrimary((char)i, true)) {
/* 1989 */           this.mTokenState = 4;
/* 1991 */         } else if (this.mCfgLazyParsing) {
/* 1996 */           this.mTokenState = 1;
/*      */         } else {
/* 1998 */           readSpaceSecondary(true);
/* 1999 */           this.mTokenState = 4;
/*      */         } 
/* 2002 */         return false;
/*      */       } 
/* 2005 */       this.mInputPtr--;
/* 2006 */       i = getNextAfterWS();
/* 2007 */       if (i >= 0) {
/* 2014 */         this.mTokenInputTotal = this.mCurrInputProcessed + this.mInputPtr - 1L;
/* 2015 */         this.mTokenInputRow = this.mCurrInputRow;
/* 2016 */         this.mTokenInputCol = this.mInputPtr - this.mCurrInputRowStart - 1;
/*      */       } 
/*      */     } 
/* 2021 */     if (i < 0) {
/* 2022 */       handleEOF(isProlog);
/* 2023 */       this.mParseState = 4;
/* 2024 */       return true;
/*      */     } 
/* 2028 */     if (i != 60)
/* 2029 */       throwUnexpectedChar(i, (isProlog ? " in prolog" : " in epilog") + "; expected '<'"); 
/* 2034 */     char c = getNextChar(isProlog ? " in prolog" : " in epilog");
/* 2036 */     if (c == '?') {
/* 2037 */       this.mCurrToken = readPIPrimary();
/* 2038 */     } else if (c == '!') {
/* 2040 */       nextFromPrologBang(isProlog);
/* 2041 */     } else if (c == '/') {
/* 2042 */       if (isProlog)
/* 2043 */         throwParseError("Unexpected character combination '</' in prolog."); 
/* 2045 */       throwParseError("Unexpected character combination '</' in epilog (extra close tag?).");
/* 2046 */     } else if (c == ':' || isNameStartChar(c)) {
/* 2048 */       if (!isProlog) {
/* 2052 */         this.mCurrToken = handleExtraRoot(c);
/* 2053 */         return false;
/*      */       } 
/* 2055 */       handleRootElem(c);
/* 2056 */       this.mCurrToken = 1;
/*      */     } else {
/* 2058 */       throwUnexpectedChar(c, (isProlog ? " in prolog" : " in epilog") + ", after '<'.");
/*      */     } 
/* 2063 */     if (!this.mCfgLazyParsing && this.mTokenState < this.mStTextThreshold)
/* 2064 */       finishToken(false); 
/* 2067 */     return false;
/*      */   }
/*      */   
/*      */   protected void handleRootElem(char c) throws XMLStreamException {
/* 2073 */     this.mParseState = 1;
/* 2074 */     initValidation();
/* 2075 */     handleStartElem(c);
/* 2078 */     if (this.mRootLName != null && 
/* 2079 */       hasConfigFlags(32) && 
/* 2080 */       !this.mElementStack.matches(this.mRootPrefix, this.mRootLName)) {
/* 2081 */       String actual = (this.mRootPrefix == null) ? this.mRootLName : (this.mRootPrefix + ":" + this.mRootLName);
/* 2083 */       reportValidationProblem(ErrorConsts.ERR_VLD_WRONG_ROOT, actual, this.mRootLName);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void initValidation() throws XMLStreamException {}
/*      */   
/*      */   protected int handleEOF(boolean isProlog) throws XMLStreamException {
/* 2107 */     this.mCurrToken = this.mSecondaryToken = 8;
/* 2113 */     this.mTextBuffer.recycle(true);
/* 2115 */     if (isProlog)
/* 2116 */       throwUnexpectedEOF(" in prolog"); 
/* 2118 */     return this.mCurrToken;
/*      */   }
/*      */   
/*      */   private int handleExtraRoot(char c) throws XMLStreamException {
/* 2132 */     if (!this.mConfig.inputParsingModeDocuments())
/* 2137 */       throwParseError("Illegal to have multiple roots (start tag in epilog?)."); 
/* 2140 */     this.mInputPtr--;
/* 2141 */     return handleMultiDocStart(1);
/*      */   }
/*      */   
/*      */   protected int handleMultiDocStart(int nextEvent) {
/* 2154 */     this.mParseState = 3;
/* 2155 */     this.mTokenState = 4;
/* 2156 */     this.mSecondaryToken = nextEvent;
/* 2157 */     return 8;
/*      */   }
/*      */   
/*      */   private int nextFromMultiDocState() throws XMLStreamException {
/* 2168 */     if (this.mCurrToken == 8) {
/* 2173 */       if (this.mSecondaryToken == 7) {
/* 2174 */         handleMultiDocXmlDecl();
/*      */       } else {
/* 2176 */         this.mDocXmlEncoding = null;
/* 2177 */         this.mDocXmlVersion = 0;
/* 2178 */         this.mDocStandalone = 0;
/*      */       } 
/* 2180 */       return 7;
/*      */     } 
/* 2182 */     if (this.mCurrToken == 7) {
/* 2183 */       this.mParseState = 0;
/* 2186 */       if (this.mSecondaryToken == 7) {
/* 2187 */         nextFromProlog(true);
/* 2188 */         return this.mCurrToken;
/*      */       } 
/* 2191 */       if (this.mSecondaryToken == 1) {
/* 2192 */         handleRootElem(getNextChar(" in start tag"));
/* 2193 */         return 1;
/*      */       } 
/* 2195 */       if (this.mSecondaryToken == 11) {
/* 2196 */         this.mStDoctypeFound = true;
/* 2197 */         startDTD();
/* 2198 */         return 11;
/*      */       } 
/*      */     } 
/* 2201 */     throw new IllegalStateException("Internal error: unexpected state; current event " + tokenTypeDesc(this.mCurrToken) + ", sec. state: " + tokenTypeDesc(this.mSecondaryToken));
/*      */   }
/*      */   
/*      */   protected void handleMultiDocXmlDecl() throws XMLStreamException {
/* 2209 */     this.mDocStandalone = 0;
/* 2210 */     this.mDocXmlEncoding = null;
/* 2212 */     char c = getNextInCurrAfterWS(" in xml declaration");
/* 2213 */     String wrong = checkKeyword(c, "version");
/* 2214 */     if (wrong != null)
/* 2215 */       throwParseError(ErrorConsts.ERR_UNEXP_KEYWORD, wrong, "version"); 
/* 2217 */     c = skipEquals("version", " in xml declaration");
/* 2218 */     TextBuffer tb = this.mTextBuffer;
/* 2219 */     tb.resetInitialized();
/* 2220 */     parseQuoted("version", c, tb);
/* 2222 */     if (tb.equalsString("1.0")) {
/* 2223 */       this.mDocXmlVersion = 256;
/* 2224 */       this.mXml11 = false;
/* 2225 */     } else if (tb.equalsString("1.1")) {
/* 2226 */       this.mDocXmlVersion = 272;
/* 2227 */       this.mXml11 = true;
/*      */     } else {
/* 2229 */       this.mDocXmlVersion = 0;
/* 2230 */       this.mXml11 = false;
/* 2231 */       throwParseError("Unexpected xml version '" + tb.toString() + "'; expected '" + "1.0" + "' or '" + "1.1" + "'");
/*      */     } 
/* 2234 */     c = getNextInCurrAfterWS(" in xml declaration");
/* 2236 */     if (c != '?') {
/* 2237 */       if (c == 'e') {
/* 2238 */         wrong = checkKeyword(c, "encoding");
/* 2239 */         if (wrong != null)
/* 2240 */           throwParseError(ErrorConsts.ERR_UNEXP_KEYWORD, wrong, "encoding"); 
/* 2242 */         c = skipEquals("encoding", " in xml declaration");
/* 2243 */         tb.resetWithEmpty();
/* 2244 */         parseQuoted("encoding", c, tb);
/* 2245 */         this.mDocXmlEncoding = tb.toString();
/* 2250 */         c = getNextInCurrAfterWS(" in xml declaration");
/* 2251 */       } else if (c != 's') {
/* 2252 */         throwUnexpectedChar(c, " in xml declaration; expected either 'encoding' or 'standalone' pseudo-attribute");
/*      */       } 
/* 2256 */       if (c == 's') {
/* 2257 */         wrong = checkKeyword(c, "standalone");
/* 2258 */         if (wrong != null)
/* 2259 */           throwParseError(ErrorConsts.ERR_UNEXP_KEYWORD, wrong, "standalone"); 
/* 2261 */         c = skipEquals("standalone", " in xml declaration");
/* 2262 */         tb.resetWithEmpty();
/* 2263 */         parseQuoted("standalone", c, tb);
/* 2264 */         if (tb.equalsString("yes")) {
/* 2265 */           this.mDocStandalone = 1;
/* 2266 */         } else if (tb.equalsString("no")) {
/* 2267 */           this.mDocStandalone = 2;
/*      */         } else {
/* 2269 */           throwParseError("Unexpected xml 'standalone' pseudo-attribute value '" + tb.toString() + "'; expected '" + "yes" + "' or '" + "no" + "'");
/*      */         } 
/* 2273 */         c = getNextInCurrAfterWS(" in xml declaration");
/*      */       } 
/*      */     } 
/* 2277 */     if (c != '?')
/* 2278 */       throwUnexpectedChar(c, " in xml declaration; expected '?>' as the end marker"); 
/* 2280 */     c = getNextCharFromCurrent(" in xml declaration");
/* 2281 */     if (c != '>')
/* 2282 */       throwUnexpectedChar(c, " in xml declaration; expected '>' to close the declaration"); 
/*      */   }
/*      */   
/*      */   protected final char skipEquals(String name, String eofMsg) throws XMLStreamException {
/* 2295 */     char c = getNextInCurrAfterWS(eofMsg);
/* 2296 */     if (c != '=')
/* 2297 */       throwUnexpectedChar(c, " in xml declaration; expected '=' to follow pseudo-attribute '" + name + "'"); 
/* 2300 */     return getNextInCurrAfterWS(eofMsg);
/*      */   }
/*      */   
/*      */   protected final void parseQuoted(String name, char quoteChar, TextBuffer tbuf) throws XMLStreamException {
/* 2317 */     if (quoteChar != '"' && quoteChar != '\'')
/* 2318 */       throwUnexpectedChar(quoteChar, " in xml declaration; waited ' or \" to start a value for pseudo-attribute '" + name + "'"); 
/* 2320 */     char[] outBuf = tbuf.getCurrentSegment();
/* 2321 */     int outPtr = 0;
/*      */     while (true) {
/* 2324 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(" in xml declaration");
/* 2327 */       if (c == quoteChar)
/*      */         break; 
/* 2330 */       if (c < ' ' || c == '<') {
/* 2331 */         throwUnexpectedChar(c, " in xml declaration");
/* 2332 */       } else if (c == '\000') {
/* 2333 */         throwNullChar();
/*      */       } 
/* 2335 */       if (outPtr >= outBuf.length) {
/* 2336 */         outBuf = tbuf.finishCurrentSegment();
/* 2337 */         outPtr = 0;
/*      */       } 
/* 2339 */       outBuf[outPtr++] = c;
/*      */     } 
/* 2341 */     tbuf.setCurrentLength(outPtr);
/*      */   }
/*      */   
/*      */   private void nextFromPrologBang(boolean isProlog) throws XMLStreamException {
/* 2353 */     int i = getNext();
/* 2354 */     if (i < 0)
/* 2355 */       throwUnexpectedEOF(" in prolog"); 
/* 2357 */     if (i == 68) {
/* 2358 */       String keyw = checkKeyword('D', "DOCTYPE");
/* 2359 */       if (keyw != null)
/* 2360 */         throwParseError("Unrecognized XML directive '<!" + keyw + "' (misspelled DOCTYPE?)."); 
/* 2363 */       if (!isProlog)
/* 2365 */         if (this.mConfig.inputParsingModeDocuments()) {
/* 2366 */           if (!this.mStDoctypeFound) {
/* 2367 */             this.mCurrToken = handleMultiDocStart(11);
/*      */             return;
/*      */           } 
/*      */         } else {
/* 2371 */           throwParseError(ErrorConsts.ERR_DTD_IN_EPILOG);
/*      */         }  
/* 2374 */       if (this.mStDoctypeFound)
/* 2375 */         throwParseError(ErrorConsts.ERR_DTD_DUP); 
/* 2377 */       this.mStDoctypeFound = true;
/* 2379 */       this.mCurrToken = 11;
/* 2380 */       startDTD();
/*      */       return;
/*      */     } 
/* 2382 */     if (i == 45) {
/* 2383 */       char c = getNextChar(isProlog ? " in prolog" : " in epilog");
/* 2384 */       if (c != '-')
/* 2385 */         throwUnexpectedChar(i, " (malformed comment?)"); 
/* 2388 */       this.mTokenState = 1;
/* 2389 */       this.mCurrToken = 5;
/*      */       return;
/*      */     } 
/* 2391 */     if (i == 91) {
/* 2392 */       i = peekNext();
/* 2394 */       if (i == 67)
/* 2395 */         throwUnexpectedChar(i, ErrorConsts.ERR_CDATA_IN_EPILOG); 
/*      */     } 
/* 2399 */     throwUnexpectedChar(i, " after '<!' (malformed comment?)");
/*      */   }
/*      */   
/*      */   private void startDTD() throws XMLStreamException {
/* 2413 */     this.mTextBuffer.resetInitialized();
/* 2420 */     char c = getNextInCurrAfterWS(" in DOCTYPE declaration");
/* 2421 */     if (this.mCfgNsEnabled) {
/* 2422 */       String str = parseLocalName(c);
/* 2423 */       c = getNextChar(" in DOCTYPE declaration");
/* 2424 */       if (c == ':') {
/* 2425 */         this.mRootPrefix = str;
/* 2426 */         this.mRootLName = parseLocalName(getNextChar("; expected an identifier"));
/* 2427 */       } else if (c <= ' ' || c == '[' || c == '>') {
/* 2429 */         this.mInputPtr--;
/* 2430 */         this.mRootPrefix = null;
/* 2431 */         this.mRootLName = str;
/*      */       } else {
/* 2433 */         throwUnexpectedChar(c, " in DOCTYPE declaration; expected '[' or white space.");
/*      */       } 
/*      */     } else {
/* 2436 */       this.mRootLName = parseFullName(c);
/* 2437 */       this.mRootPrefix = null;
/*      */     } 
/* 2441 */     c = getNextInCurrAfterWS(" in DOCTYPE declaration");
/* 2442 */     if (c != '[' && c != '>') {
/* 2443 */       String keyw = null;
/* 2445 */       if (c == 'P') {
/* 2446 */         keyw = checkKeyword(getNextChar(" in DOCTYPE declaration"), "UBLIC");
/* 2447 */         if (keyw != null) {
/* 2448 */           keyw = "P" + keyw;
/*      */         } else {
/* 2450 */           if (!skipWS(getNextChar(" in DOCTYPE declaration")))
/* 2451 */             throwUnexpectedChar(c, " in DOCTYPE declaration; expected a space between PUBLIC keyword and public id"); 
/* 2453 */           c = getNextCharFromCurrent(" in DOCTYPE declaration");
/* 2454 */           if (c != '"' && c != '\'')
/* 2455 */             throwUnexpectedChar(c, " in DOCTYPE declaration; expected a public identifier."); 
/* 2457 */           this.mDtdPublicId = parsePublicId(c, " in DOCTYPE declaration");
/* 2458 */           if (this.mDtdPublicId.length() == 0);
/* 2463 */           if (!skipWS(getNextChar(" in DOCTYPE declaration")))
/* 2464 */             throwUnexpectedChar(c, " in DOCTYPE declaration; expected a space between public and system identifiers"); 
/* 2466 */           c = getNextCharFromCurrent(" in DOCTYPE declaration");
/* 2467 */           if (c != '"' && c != '\'')
/* 2468 */             throwParseError(" in DOCTYPE declaration; expected a system identifier."); 
/* 2470 */           this.mDtdSystemId = parseSystemId(c, this.mNormalizeLFs, " in DOCTYPE declaration");
/* 2471 */           if (this.mDtdSystemId.length() == 0);
/*      */         } 
/* 2477 */       } else if (c == 'S') {
/* 2478 */         this.mDtdPublicId = null;
/* 2479 */         keyw = checkKeyword(getNextChar(" in DOCTYPE declaration"), "YSTEM");
/* 2480 */         if (keyw != null) {
/* 2481 */           keyw = "S" + keyw;
/*      */         } else {
/* 2483 */           c = getNextInCurrAfterWS(" in DOCTYPE declaration");
/* 2484 */           if (c != '"' && c != '\'')
/* 2485 */             throwUnexpectedChar(c, " in DOCTYPE declaration; expected a system identifier."); 
/* 2487 */           this.mDtdSystemId = parseSystemId(c, this.mNormalizeLFs, " in DOCTYPE declaration");
/* 2488 */           if (this.mDtdSystemId.length() == 0)
/* 2490 */             this.mDtdSystemId = null; 
/*      */         } 
/* 2494 */       } else if (!isNameStartChar(c)) {
/* 2495 */         throwUnexpectedChar(c, " in DOCTYPE declaration; expected keywords 'PUBLIC' or 'SYSTEM'.");
/*      */       } else {
/* 2497 */         this.mInputPtr--;
/* 2498 */         keyw = checkKeyword(c, "SYSTEM");
/*      */       } 
/* 2502 */       if (keyw != null)
/* 2503 */         throwParseError("Unexpected keyword '" + keyw + "'; expected 'PUBLIC' or 'SYSTEM'"); 
/* 2507 */       c = getNextInCurrAfterWS(" in DOCTYPE declaration");
/*      */     } 
/* 2510 */     if (c != '[')
/* 2513 */       if (c != '>')
/* 2514 */         throwUnexpectedChar(c, " in DOCTYPE declaration; expected closing '>'.");  
/* 2522 */     this.mInputPtr--;
/* 2523 */     this.mTokenState = 1;
/*      */   }
/*      */   
/*      */   protected void finishDTD(boolean copyContents) throws XMLStreamException {
/* 2546 */     char c = getNextChar(" in DOCTYPE declaration");
/* 2547 */     if (c == '[') {
/* 2549 */       if (copyContents)
/* 2550 */         ((BranchingReaderSource)this.mInput).startBranch(this.mTextBuffer, this.mInputPtr, this.mNormalizeLFs); 
/*      */       try {
/* 2554 */         MinimalDTDReader.skipInternalSubset(this, this.mInput, this.mConfig);
/*      */       } finally {
/* 2559 */         if (copyContents)
/* 2564 */           ((BranchingReaderSource)this.mInput).endBranch(this.mInputPtr - 1); 
/*      */       } 
/* 2569 */       c = getNextCharAfterWS(" in internal DTD subset");
/*      */     } 
/* 2572 */     if (c != '>')
/* 2573 */       throwUnexpectedChar(c, "; expected '>' to finish DOCTYPE declaration."); 
/*      */   }
/*      */   
/*      */   private final int nextFromTree() throws XMLStreamException {
/*      */     int i;
/* 2593 */     if (this.mTokenState < this.mStTextThreshold) {
/* 2599 */       if (this.mVldContent == 3 && (
/* 2600 */         this.mCurrToken == 4 || this.mCurrToken == 12))
/* 2601 */         throwParseError("Internal error: skipping validatable text"); 
/* 2604 */       i = skipToken();
/*      */     } else {
/* 2610 */       if (this.mCurrToken == 1) {
/* 2612 */         if (this.mStEmptyElem) {
/* 2614 */           this.mStEmptyElem = false;
/* 2619 */           int vld = this.mElementStack.validateEndElement();
/* 2620 */           this.mVldContent = vld;
/* 2621 */           this.mValidateText = (vld == 3);
/* 2622 */           return 2;
/*      */         } 
/* 2624 */       } else if (this.mCurrToken == 2) {
/* 2626 */         if (!this.mElementStack.pop())
/* 2628 */           if (!this.mConfig.inputParsingModeFragment())
/* 2629 */             return closeContentTree();  
/* 2633 */       } else if (this.mCurrToken == 12 && this.mTokenState <= 2) {
/* 2644 */         this.mTokenInputTotal = this.mCurrInputProcessed + this.mInputPtr;
/* 2645 */         this.mTokenInputRow = this.mCurrInputRow;
/* 2646 */         this.mTokenInputCol = this.mInputPtr - this.mCurrInputRowStart;
/* 2647 */         char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(" in CDATA section");
/* 2649 */         if (readCDataPrimary(c)) {
/* 2655 */           if (this.mTextBuffer.size() > 0)
/* 2656 */             return 12; 
/* 2664 */         } else if (this.mTextBuffer.size() == 0 && readCDataSecondary(this.mCfgLazyParsing ? 1 : this.mShortestTextSegment)) {
/* 2668 */           if (this.mTextBuffer.size() > 0) {
/* 2670 */             this.mTokenState = 3;
/* 2671 */             return 12;
/*      */           } 
/*      */         } else {
/* 2675 */           this.mTokenState = 2;
/* 2676 */           return 12;
/*      */         } 
/*      */       } 
/* 2685 */       this.mTokenInputTotal = this.mCurrInputProcessed + this.mInputPtr;
/* 2686 */       this.mTokenInputRow = this.mCurrInputRow;
/* 2687 */       this.mTokenInputCol = this.mInputPtr - this.mCurrInputRowStart;
/* 2688 */       i = getNext();
/*      */     } 
/* 2691 */     if (i < 0) {
/* 2696 */       if (!this.mElementStack.isEmpty())
/* 2697 */         throwUnexpectedEOF(); 
/* 2699 */       return handleEOF(false);
/*      */     } 
/* 2707 */     while (i == 38) {
/* 2708 */       this.mWsStatus = 0;
/* 2713 */       if (this.mVldContent == 0)
/* 2717 */         reportInvalidContent(9); 
/* 2723 */       int ch = this.mCfgReplaceEntities ? fullyResolveEntity(true) : resolveCharOnlyEntity(true);
/* 2726 */       if (ch != 0) {
/* 2733 */         if (this.mVldContent <= 1)
/* 2735 */           if (ch > 32)
/* 2744 */             reportInvalidContent(4);  
/* 2747 */         TextBuffer tb = this.mTextBuffer;
/* 2748 */         tb.resetInitialized();
/* 2749 */         if (ch <= 65535) {
/* 2750 */           tb.append((char)ch);
/*      */         } else {
/* 2752 */           ch -= 65536;
/* 2753 */           tb.append((char)((ch >> 10) + 55296));
/* 2754 */           tb.append((char)((ch & 0x3FF) + 56320));
/*      */         } 
/* 2756 */         this.mTokenState = 1;
/* 2757 */         return 4;
/*      */       } 
/* 2763 */       if (!this.mCfgReplaceEntities || this.mCfgTreatCharRefsAsEntities) {
/* 2764 */         if (!this.mCfgTreatCharRefsAsEntities) {
/* 2765 */           EntityDecl ed = resolveNonCharEntity();
/* 2767 */           this.mCurrEntity = ed;
/*      */         } 
/* 2770 */         this.mTokenState = 4;
/* 2778 */         return 9;
/*      */       } 
/* 2782 */       i = getNextChar(" in main document content");
/*      */     } 
/* 2785 */     if (i == 60) {
/* 2787 */       char c = getNextChar(" in start tag");
/* 2788 */       if (c == '?') {
/* 2790 */         if (this.mVldContent == 0)
/* 2791 */           reportInvalidContent(3); 
/* 2793 */         return readPIPrimary();
/*      */       } 
/* 2796 */       if (c == '!') {
/* 2798 */         int type = nextFromTreeCommentOrCData();
/* 2800 */         if (this.mVldContent == 0)
/* 2801 */           reportInvalidContent(type); 
/* 2803 */         return type;
/*      */       } 
/* 2805 */       if (c == '/') {
/* 2806 */         readEndElem();
/* 2807 */         return 2;
/*      */       } 
/* 2810 */       if (c == ':' || isNameStartChar(c)) {
/* 2814 */         handleStartElem(c);
/* 2815 */         return 1;
/*      */       } 
/* 2817 */       if (c == '[')
/* 2818 */         throwUnexpectedChar(c, " in content after '<' (malformed <![CDATA[]] directive?)"); 
/* 2820 */       throwUnexpectedChar(c, " in content after '<' (malformed start element?).");
/*      */     } 
/* 2830 */     if (this.mVldContent <= 2) {
/* 2831 */       if (this.mVldContent == 0 && 
/* 2832 */         this.mElementStack.reallyValidating())
/* 2833 */         reportInvalidContent(4); 
/* 2836 */       if (i <= 32) {
/* 2840 */         this.mTokenState = readSpacePrimary((char)i, false) ? 4 : 1;
/* 2842 */         return 6;
/*      */       } 
/* 2845 */       if (this.mElementStack.reallyValidating())
/* 2846 */         reportInvalidContent(4); 
/*      */     } 
/* 2856 */     if (readTextPrimary((char)i)) {
/* 2857 */       this.mTokenState = 3;
/* 2860 */     } else if (!this.mCfgCoalesceText && this.mTextBuffer.size() >= this.mShortestTextSegment) {
/* 2862 */       this.mTokenState = 2;
/*      */     } else {
/* 2864 */       this.mTokenState = 1;
/*      */     } 
/* 2867 */     return 4;
/*      */   }
/*      */   
/*      */   private int closeContentTree() throws XMLStreamException {
/* 2884 */     this.mParseState = 2;
/* 2886 */     if (nextFromProlog(false))
/* 2887 */       this.mSecondaryToken = 0; 
/* 2893 */     if (this.mSymbols.isDirty())
/* 2894 */       this.mOwner.updateSymbolTable(this.mSymbols); 
/* 2900 */     this.mTextBuffer.recycle(false);
/* 2901 */     return this.mCurrToken;
/*      */   }
/*      */   
/*      */   private final void handleStartElem(char c) throws XMLStreamException {
/*      */     boolean empty;
/* 2912 */     this.mTokenState = 4;
/* 2915 */     if (this.mCfgNsEnabled) {
/* 2916 */       String str = parseLocalName(c);
/* 2917 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent("; expected an identifier");
/* 2919 */       if (c == ':') {
/* 2920 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent("; expected an identifier");
/* 2922 */         this.mElementStack.push(str, parseLocalName(c));
/* 2923 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/*      */       } else {
/* 2926 */         this.mElementStack.push(null, str);
/*      */       } 
/* 2938 */       empty = (c == '>') ? false : handleNsAttrs(c);
/*      */     } else {
/* 2940 */       this.mElementStack.push(null, parseFullName(c));
/* 2941 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/* 2943 */       empty = (c == '>') ? false : handleNonNsAttrs(c);
/*      */     } 
/* 2945 */     if (!empty)
/* 2946 */       this.mCurrDepth++; 
/* 2948 */     this.mStEmptyElem = empty;
/* 2954 */     int vld = this.mElementStack.resolveAndValidateElement();
/* 2955 */     this.mVldContent = vld;
/* 2956 */     this.mValidateText = (vld == 3);
/*      */   }
/*      */   
/*      */   private final boolean handleNsAttrs(char c) throws XMLStreamException {
/* 2965 */     AttributeCollector ac = this.mAttrCollector;
/*      */     while (true) {
/*      */       String prefix, localName;
/*      */       TextBuilder tb;
/* 2968 */       if (c <= ' ') {
/* 2969 */         c = getNextInCurrAfterWS(" in start tag", c);
/* 2970 */       } else if (c != '/' && c != '>') {
/* 2971 */         throwUnexpectedChar(c, " excepted space, or '>' or \"/>\"");
/*      */       } 
/* 2974 */       if (c == '/') {
/* 2975 */         c = getNextCharFromCurrent(" in start tag");
/* 2976 */         if (c != '>')
/* 2977 */           throwUnexpectedChar(c, " expected '>'"); 
/* 2979 */         return true;
/*      */       } 
/* 2980 */       if (c == '>')
/* 2981 */         return false; 
/* 2982 */       if (c == '<')
/* 2983 */         throwParseError("Unexpected '<' character in element (missing closing '>'?)"); 
/* 2987 */       String str = parseLocalName(c);
/* 2988 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent("; expected an identifier");
/* 2990 */       if (c == ':') {
/* 2991 */         prefix = str;
/* 2992 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent("; expected an identifier");
/* 2994 */         localName = parseLocalName(c);
/*      */       } else {
/* 2996 */         this.mInputPtr--;
/* 2997 */         prefix = null;
/* 2998 */         localName = str;
/*      */       } 
/* 3001 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/* 3003 */       if (c <= ' ')
/* 3004 */         c = getNextInCurrAfterWS(" in start tag", c); 
/* 3006 */       if (c != '=')
/* 3007 */         throwUnexpectedChar(c, " expected '='"); 
/* 3009 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/* 3011 */       if (c <= ' ')
/* 3012 */         c = getNextInCurrAfterWS(" in start tag", c); 
/* 3016 */       if (c != '"' && c != '\'')
/* 3017 */         throwUnexpectedChar(c, " in start tag Expected a quote"); 
/* 3021 */       int startLen = -1;
/* 3024 */       if (prefix == sPrefixXmlns) {
/* 3025 */         tb = ac.getNsBuilder(localName);
/* 3027 */         if (null == tb)
/* 3028 */           throwParseError("Duplicate declaration for namespace prefix '" + localName + "'."); 
/* 3030 */         startLen = tb.getCharSize();
/* 3031 */       } else if (localName == sPrefixXmlns && prefix == null) {
/* 3032 */         tb = ac.getDefaultNsBuilder();
/* 3034 */         if (null == tb)
/* 3035 */           throwParseError("Duplicate default namespace declaration."); 
/*      */       } else {
/* 3038 */         tb = ac.getAttrBuilder(prefix, localName);
/*      */       } 
/* 3040 */       parseAttrValue(c, tb);
/* 3053 */       if (!this.mXml11 && 
/* 3054 */         startLen >= 0 && tb.getCharSize() == startLen)
/* 3055 */         throwParseError(ErrorConsts.ERR_NS_EMPTY); 
/* 3060 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/*      */     } 
/*      */   }
/*      */   
/*      */   private final boolean handleNonNsAttrs(char c) throws XMLStreamException {
/* 3072 */     AttributeCollector ac = this.mAttrCollector;
/*      */     while (true) {
/* 3075 */       if (c <= ' ') {
/* 3076 */         c = getNextInCurrAfterWS(" in start tag", c);
/* 3077 */       } else if (c != '/' && c != '>') {
/* 3078 */         throwUnexpectedChar(c, " excepted space, or '>' or \"/>\"");
/*      */       } 
/* 3080 */       if (c == '/') {
/* 3081 */         c = getNextCharFromCurrent(" in start tag");
/* 3082 */         if (c != '>')
/* 3083 */           throwUnexpectedChar(c, " expected '>'"); 
/* 3085 */         return true;
/*      */       } 
/* 3086 */       if (c == '>')
/* 3087 */         return false; 
/* 3088 */       if (c == '<')
/* 3089 */         throwParseError("Unexpected '<' character in element (missing closing '>'?)"); 
/* 3092 */       String name = parseFullName(c);
/* 3093 */       TextBuilder tb = ac.getAttrBuilder(null, name);
/* 3094 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/* 3096 */       if (c <= ' ')
/* 3097 */         c = getNextInCurrAfterWS(" in start tag", c); 
/* 3099 */       if (c != '=')
/* 3100 */         throwUnexpectedChar(c, " expected '='"); 
/* 3102 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/* 3104 */       if (c <= ' ')
/* 3105 */         c = getNextInCurrAfterWS(" in start tag", c); 
/* 3109 */       if (c != '"' && c != '\'')
/* 3110 */         throwUnexpectedChar(c, " in start tag Expected a quote"); 
/* 3114 */       parseAttrValue(c, tb);
/* 3116 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in start tag");
/*      */     } 
/*      */   }
/*      */   
/*      */   protected final void readEndElem() throws XMLStreamException {
/* 3129 */     this.mTokenState = 4;
/* 3131 */     if (this.mElementStack.isEmpty()) {
/* 3133 */       reportExtraEndElem();
/*      */       return;
/*      */     } 
/* 3137 */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in end tag");
/* 3140 */     if (!isNameStartChar(c) && c != ':') {
/* 3141 */       if (c <= ' ')
/* 3142 */         throwUnexpectedChar(c, "; missing element name?"); 
/* 3144 */       throwUnexpectedChar(c, "; expected an element name.");
/*      */     } 
/* 3150 */     String expPrefix = this.mElementStack.getPrefix();
/* 3151 */     String expLocalName = this.mElementStack.getLocalName();
/* 3154 */     if (expPrefix != null && expPrefix.length() > 0) {
/* 3155 */       int j = expPrefix.length();
/* 3156 */       int k = 0;
/*      */       while (true) {
/* 3159 */         if (c != expPrefix.charAt(k)) {
/* 3160 */           reportWrongEndPrefix(expPrefix, expLocalName, k);
/*      */           return;
/*      */         } 
/* 3163 */         if (++k >= j)
/*      */           break; 
/* 3166 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in end tag");
/*      */       } 
/* 3170 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in end tag");
/* 3172 */       if (c != ':') {
/* 3173 */         reportWrongEndPrefix(expPrefix, expLocalName, k);
/*      */         return;
/*      */       } 
/* 3176 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in end tag");
/*      */     } 
/* 3181 */     int len = expLocalName.length();
/* 3182 */     int i = 0;
/*      */     while (true) {
/* 3185 */       if (c != expLocalName.charAt(i)) {
/* 3187 */         reportWrongEndElem(expPrefix, expLocalName, i);
/*      */         return;
/*      */       } 
/* 3190 */       if (++i >= len)
/*      */         break; 
/* 3193 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in end tag");
/*      */     } 
/* 3198 */     c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in end tag");
/* 3200 */     if (c <= ' ') {
/* 3201 */       c = getNextInCurrAfterWS(" in end tag", c);
/* 3202 */     } else if (c != '>') {
/* 3204 */       if (c == ':' || isNameChar(c))
/* 3205 */         reportWrongEndElem(expPrefix, expLocalName, len); 
/*      */     } 
/* 3209 */     if (c != '>')
/* 3210 */       throwUnexpectedChar(c, " in end tag Expected '>'."); 
/* 3214 */     int vld = this.mElementStack.validateEndElement();
/* 3215 */     this.mVldContent = vld;
/* 3216 */     this.mValidateText = (vld == 3);
/* 3223 */     if (this.mCurrDepth == this.mInputTopDepth)
/* 3224 */       handleGreedyEntityProblem(this.mInput); 
/* 3226 */     this.mCurrDepth--;
/*      */   }
/*      */   
/*      */   private void reportExtraEndElem() throws XMLStreamException {
/* 3232 */     String name = parseFNameForError();
/* 3233 */     throwParseError("Unbalanced close tag </" + name + ">; no open start tag.");
/*      */   }
/*      */   
/*      */   private void reportWrongEndPrefix(String prefix, String localName, int done) throws XMLStreamException {
/* 3239 */     this.mInputPtr--;
/* 3240 */     String fullName = prefix + ":" + localName;
/* 3241 */     String rest = parseFNameForError();
/* 3242 */     String actName = fullName.substring(0, done) + rest;
/* 3243 */     throwParseError("Unexpected close tag </" + actName + ">; expected </" + fullName + ">.");
/*      */   }
/*      */   
/*      */   private void reportWrongEndElem(String prefix, String localName, int done) throws XMLStreamException {
/*      */     String fullName;
/* 3250 */     this.mInputPtr--;
/* 3252 */     if (prefix != null && prefix.length() > 0) {
/* 3253 */       fullName = prefix + ":" + localName;
/* 3254 */       done += 1 + prefix.length();
/*      */     } else {
/* 3256 */       fullName = localName;
/*      */     } 
/* 3258 */     String rest = parseFNameForError();
/* 3259 */     String actName = fullName.substring(0, done) + rest;
/* 3260 */     throwParseError("Unexpected close tag </" + actName + ">; expected </" + fullName + ">.");
/*      */   }
/*      */   
/*      */   private int nextFromTreeCommentOrCData() throws XMLStreamException {
/* 3274 */     char c = getNextCharFromCurrent(" in main document content");
/* 3275 */     if (c == '[') {
/* 3276 */       checkCData();
/* 3280 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in CDATA section");
/* 3282 */       readCDataPrimary(c);
/* 3283 */       return 12;
/*      */     } 
/* 3285 */     if (c == '-' && getNextCharFromCurrent(" in main document content") == '-') {
/* 3286 */       this.mTokenState = 1;
/* 3287 */       return 5;
/*      */     } 
/* 3289 */     throwParseError("Unrecognized XML directive; expected CDATA or comment ('<![CDATA[' or '<!--').");
/* 3290 */     return 0;
/*      */   }
/*      */   
/*      */   private int skipToken() throws XMLStreamException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield mCurrToken : I
/*      */     //   4: tableswitch default -> 435, 1 -> 435, 2 -> 435, 3 -> 168, 4 -> 133, 5 -> 118, 6 -> 308, 7 -> 400, 8 -> 400, 9 -> 400, 10 -> 435, 11 -> 158, 12 -> 80, 13 -> 435, 14 -> 400, 15 -> 400
/*      */     //   80: aload_0
/*      */     //   81: getfield mTokenState : I
/*      */     //   84: iconst_2
/*      */     //   85: if_icmpgt -> 97
/*      */     //   88: aload_0
/*      */     //   89: ldc ' in CDATA section'
/*      */     //   91: bipush #93
/*      */     //   93: iconst_0
/*      */     //   94: invokespecial skipCommentOrCData : (Ljava/lang/String;CZ)V
/*      */     //   97: aload_0
/*      */     //   98: invokevirtual getNext : ()I
/*      */     //   101: istore_1
/*      */     //   102: aload_0
/*      */     //   103: getfield mCfgCoalesceText : Z
/*      */     //   106: ifeq -> 470
/*      */     //   109: aload_0
/*      */     //   110: iload_1
/*      */     //   111: invokespecial skipCoalescedText : (I)I
/*      */     //   114: istore_1
/*      */     //   115: goto -> 470
/*      */     //   118: aload_0
/*      */     //   119: ldc_w ' in comment'
/*      */     //   122: bipush #45
/*      */     //   124: iconst_1
/*      */     //   125: invokespecial skipCommentOrCData : (Ljava/lang/String;CZ)V
/*      */     //   128: iconst_0
/*      */     //   129: istore_1
/*      */     //   130: goto -> 470
/*      */     //   133: aload_0
/*      */     //   134: aload_0
/*      */     //   135: invokevirtual getNext : ()I
/*      */     //   138: invokespecial skipTokenText : (I)I
/*      */     //   141: istore_1
/*      */     //   142: aload_0
/*      */     //   143: getfield mCfgCoalesceText : Z
/*      */     //   146: ifeq -> 470
/*      */     //   149: aload_0
/*      */     //   150: iload_1
/*      */     //   151: invokespecial skipCoalescedText : (I)I
/*      */     //   154: istore_1
/*      */     //   155: goto -> 470
/*      */     //   158: aload_0
/*      */     //   159: iconst_0
/*      */     //   160: invokevirtual finishDTD : (Z)V
/*      */     //   163: iconst_0
/*      */     //   164: istore_1
/*      */     //   165: goto -> 470
/*      */     //   168: aload_0
/*      */     //   169: getfield mInputPtr : I
/*      */     //   172: aload_0
/*      */     //   173: getfield mInputEnd : I
/*      */     //   176: if_icmpge -> 198
/*      */     //   179: aload_0
/*      */     //   180: getfield mInputBuffer : [C
/*      */     //   183: aload_0
/*      */     //   184: dup
/*      */     //   185: getfield mInputPtr : I
/*      */     //   188: dup_x1
/*      */     //   189: iconst_1
/*      */     //   190: iadd
/*      */     //   191: putfield mInputPtr : I
/*      */     //   194: caload
/*      */     //   195: goto -> 205
/*      */     //   198: aload_0
/*      */     //   199: ldc_w ' in processing instruction'
/*      */     //   202: invokevirtual getNextCharFromCurrent : (Ljava/lang/String;)C
/*      */     //   205: istore_2
/*      */     //   206: iload_2
/*      */     //   207: bipush #63
/*      */     //   209: if_icmpne -> 267
/*      */     //   212: aload_0
/*      */     //   213: getfield mInputPtr : I
/*      */     //   216: aload_0
/*      */     //   217: getfield mInputEnd : I
/*      */     //   220: if_icmpge -> 242
/*      */     //   223: aload_0
/*      */     //   224: getfield mInputBuffer : [C
/*      */     //   227: aload_0
/*      */     //   228: dup
/*      */     //   229: getfield mInputPtr : I
/*      */     //   232: dup_x1
/*      */     //   233: iconst_1
/*      */     //   234: iadd
/*      */     //   235: putfield mInputPtr : I
/*      */     //   238: caload
/*      */     //   239: goto -> 249
/*      */     //   242: aload_0
/*      */     //   243: ldc_w ' in processing instruction'
/*      */     //   246: invokevirtual getNextCharFromCurrent : (Ljava/lang/String;)C
/*      */     //   249: istore_2
/*      */     //   250: iload_2
/*      */     //   251: bipush #63
/*      */     //   253: if_icmpeq -> 212
/*      */     //   256: iload_2
/*      */     //   257: bipush #62
/*      */     //   259: if_icmpne -> 267
/*      */     //   262: iconst_0
/*      */     //   263: istore_1
/*      */     //   264: goto -> 470
/*      */     //   267: iload_2
/*      */     //   268: bipush #32
/*      */     //   270: if_icmpge -> 305
/*      */     //   273: iload_2
/*      */     //   274: bipush #10
/*      */     //   276: if_icmpeq -> 285
/*      */     //   279: iload_2
/*      */     //   280: bipush #13
/*      */     //   282: if_icmpne -> 294
/*      */     //   285: aload_0
/*      */     //   286: iload_2
/*      */     //   287: invokevirtual skipCRLF : (C)Z
/*      */     //   290: pop
/*      */     //   291: goto -> 305
/*      */     //   294: iload_2
/*      */     //   295: bipush #9
/*      */     //   297: if_icmpeq -> 305
/*      */     //   300: aload_0
/*      */     //   301: iload_2
/*      */     //   302: invokevirtual throwInvalidSpace : (I)V
/*      */     //   305: goto -> 168
/*      */     //   308: aload_0
/*      */     //   309: getfield mInputPtr : I
/*      */     //   312: aload_0
/*      */     //   313: getfield mInputEnd : I
/*      */     //   316: if_icmpge -> 388
/*      */     //   319: aload_0
/*      */     //   320: getfield mInputBuffer : [C
/*      */     //   323: aload_0
/*      */     //   324: dup
/*      */     //   325: getfield mInputPtr : I
/*      */     //   328: dup_x1
/*      */     //   329: iconst_1
/*      */     //   330: iadd
/*      */     //   331: putfield mInputPtr : I
/*      */     //   334: caload
/*      */     //   335: istore_2
/*      */     //   336: iload_2
/*      */     //   337: bipush #32
/*      */     //   339: if_icmple -> 347
/*      */     //   342: iload_2
/*      */     //   343: istore_1
/*      */     //   344: goto -> 470
/*      */     //   347: iload_2
/*      */     //   348: bipush #10
/*      */     //   350: if_icmpeq -> 359
/*      */     //   353: iload_2
/*      */     //   354: bipush #13
/*      */     //   356: if_icmpne -> 368
/*      */     //   359: aload_0
/*      */     //   360: iload_2
/*      */     //   361: invokevirtual skipCRLF : (C)Z
/*      */     //   364: pop
/*      */     //   365: goto -> 385
/*      */     //   368: iload_2
/*      */     //   369: bipush #32
/*      */     //   371: if_icmpeq -> 385
/*      */     //   374: iload_2
/*      */     //   375: bipush #9
/*      */     //   377: if_icmpeq -> 385
/*      */     //   380: aload_0
/*      */     //   381: iload_2
/*      */     //   382: invokevirtual throwInvalidSpace : (I)V
/*      */     //   385: goto -> 308
/*      */     //   388: aload_0
/*      */     //   389: invokevirtual loadMore : ()Z
/*      */     //   392: ifne -> 308
/*      */     //   395: iconst_m1
/*      */     //   396: istore_1
/*      */     //   397: goto -> 470
/*      */     //   400: new java/lang/IllegalStateException
/*      */     //   403: dup
/*      */     //   404: new java/lang/StringBuffer
/*      */     //   407: dup
/*      */     //   408: invokespecial <init> : ()V
/*      */     //   411: ldc_w 'skipToken() called when current token is '
/*      */     //   414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   417: aload_0
/*      */     //   418: aload_0
/*      */     //   419: getfield mCurrToken : I
/*      */     //   422: invokevirtual tokenTypeDesc : (I)Ljava/lang/String;
/*      */     //   425: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   428: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   431: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   434: athrow
/*      */     //   435: new java/lang/IllegalStateException
/*      */     //   438: dup
/*      */     //   439: new java/lang/StringBuffer
/*      */     //   442: dup
/*      */     //   443: invokespecial <init> : ()V
/*      */     //   446: ldc_w 'Internal error: unexpected token '
/*      */     //   449: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   452: aload_0
/*      */     //   453: aload_0
/*      */     //   454: getfield mCurrToken : I
/*      */     //   457: invokevirtual tokenTypeDesc : (I)Ljava/lang/String;
/*      */     //   460: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   463: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   466: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   469: athrow
/*      */     //   470: iload_1
/*      */     //   471: iconst_1
/*      */     //   472: if_icmpge -> 523
/*      */     //   475: aload_0
/*      */     //   476: aload_0
/*      */     //   477: getfield mCurrInputRow : I
/*      */     //   480: putfield mTokenInputRow : I
/*      */     //   483: aload_0
/*      */     //   484: aload_0
/*      */     //   485: getfield mCurrInputProcessed : J
/*      */     //   488: aload_0
/*      */     //   489: getfield mInputPtr : I
/*      */     //   492: i2l
/*      */     //   493: ladd
/*      */     //   494: putfield mTokenInputTotal : J
/*      */     //   497: aload_0
/*      */     //   498: aload_0
/*      */     //   499: getfield mInputPtr : I
/*      */     //   502: aload_0
/*      */     //   503: getfield mCurrInputRowStart : I
/*      */     //   506: isub
/*      */     //   507: putfield mTokenInputCol : I
/*      */     //   510: iload_1
/*      */     //   511: ifge -> 518
/*      */     //   514: iload_1
/*      */     //   515: goto -> 522
/*      */     //   518: aload_0
/*      */     //   519: invokevirtual getNext : ()I
/*      */     //   522: ireturn
/*      */     //   523: aload_0
/*      */     //   524: aload_0
/*      */     //   525: getfield mCurrInputRow : I
/*      */     //   528: putfield mTokenInputRow : I
/*      */     //   531: aload_0
/*      */     //   532: aload_0
/*      */     //   533: getfield mCurrInputProcessed : J
/*      */     //   536: aload_0
/*      */     //   537: getfield mInputPtr : I
/*      */     //   540: i2l
/*      */     //   541: ladd
/*      */     //   542: lconst_1
/*      */     //   543: lsub
/*      */     //   544: putfield mTokenInputTotal : J
/*      */     //   547: aload_0
/*      */     //   548: aload_0
/*      */     //   549: getfield mInputPtr : I
/*      */     //   552: aload_0
/*      */     //   553: getfield mCurrInputRowStart : I
/*      */     //   556: isub
/*      */     //   557: iconst_1
/*      */     //   558: isub
/*      */     //   559: putfield mTokenInputCol : I
/*      */     //   562: iload_1
/*      */     //   563: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #3317	-> 0
/*      */     //   #3325	-> 80
/*      */     //   #3327	-> 88
/*      */     //   #3329	-> 97
/*      */     //   #3331	-> 102
/*      */     //   #3332	-> 109
/*      */     //   #3338	-> 118
/*      */     //   #3339	-> 128
/*      */     //   #3340	-> 130
/*      */     //   #3344	-> 133
/*      */     //   #3346	-> 142
/*      */     //   #3347	-> 149
/*      */     //   #3353	-> 158
/*      */     //   #3354	-> 163
/*      */     //   #3355	-> 165
/*      */     //   #3359	-> 168
/*      */     //   #3361	-> 206
/*      */     //   #3363	-> 212
/*      */     //   #3365	-> 250
/*      */     //   #3366	-> 256
/*      */     //   #3367	-> 262
/*      */     //   #3368	-> 264
/*      */     //   #3371	-> 267
/*      */     //   #3372	-> 273
/*      */     //   #3373	-> 285
/*      */     //   #3374	-> 294
/*      */     //   #3375	-> 300
/*      */     //   #3378	-> 305
/*      */     //   #3385	-> 308
/*      */     //   #3386	-> 319
/*      */     //   #3387	-> 336
/*      */     //   #3388	-> 342
/*      */     //   #3389	-> 344
/*      */     //   #3391	-> 347
/*      */     //   #3392	-> 359
/*      */     //   #3393	-> 368
/*      */     //   #3394	-> 380
/*      */     //   #3396	-> 385
/*      */     //   #3397	-> 388
/*      */     //   #3398	-> 395
/*      */     //   #3399	-> 397
/*      */     //   #3410	-> 400
/*      */     //   #3422	-> 435
/*      */     //   #3436	-> 470
/*      */     //   #3437	-> 475
/*      */     //   #3438	-> 483
/*      */     //   #3439	-> 497
/*      */     //   #3440	-> 510
/*      */     //   #3444	-> 523
/*      */     //   #3445	-> 531
/*      */     //   #3446	-> 547
/*      */     //   #3447	-> 562
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   102	165	1	result	I
/*      */     //   206	99	2	c	C
/*      */     //   344	3	1	result	I
/*      */     //   336	49	2	c	C
/*      */     //   0	564	0	this	Lcom/ctc/wstx/sr/BasicStreamReader;
/*      */     //   397	167	1	result	I
/*      */   }
/*      */   
/*      */   private void skipCommentOrCData(String errorMsg, char endChar, boolean preventDoubles) throws XMLStreamException {
/*      */     while (true) {
/* 3459 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(errorMsg);
/* 3461 */       if (c < ' ')
/* 3462 */         if (c == '\n' || c == '\r') {
/* 3463 */           skipCRLF(c);
/* 3464 */         } else if (c != '\t') {
/* 3465 */           throwInvalidSpace(c);
/*      */         }  
/* 3468 */       if (c == endChar) {
/* 3471 */         c = getNextChar(errorMsg);
/* 3472 */         if (c == endChar) {
/* 3474 */           c = getNextChar(errorMsg);
/* 3475 */           if (c == '>')
/*      */             break; 
/* 3478 */           if (preventDoubles)
/* 3479 */             throwParseError("String '--' not allowed in comment (missing '>'?)"); 
/* 3482 */           while (c == endChar)
/* 3483 */             c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(errorMsg); 
/* 3486 */           if (c == '>')
/*      */             break; 
/*      */         } 
/* 3492 */         if (c < ' ') {
/* 3493 */           if (c == '\n' || c == '\r') {
/* 3494 */             skipCRLF(c);
/*      */             continue;
/*      */           } 
/* 3495 */           if (c != '\t')
/* 3496 */             throwInvalidSpace(c); 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private int skipCoalescedText(int i) throws XMLStreamException {
/*      */     do {
/* 3517 */       while (i == 60) {
/* 3519 */         if (!ensureInput(3))
/* 3523 */           return i; 
/* 3525 */         if (this.mInputBuffer[this.mInputPtr] != '!' || this.mInputBuffer[this.mInputPtr + 1] != '[')
/* 3528 */           return i; 
/* 3531 */         this.mInputPtr += 2;
/* 3533 */         checkCData();
/* 3534 */         skipCommentOrCData(" in CDATA section", ']', false);
/* 3535 */         i = getNext();
/*      */       } 
/* 3536 */       if (i < 0)
/* 3537 */         return i; 
/* 3539 */       i = skipTokenText(i);
/* 3544 */     } while (i != 38 && i >= 0);
/* 3545 */     return i;
/*      */   }
/*      */   
/*      */   private int skipTokenText(int i) throws XMLStreamException {
/*      */     while (true) {
/* 3559 */       if (i == 60)
/* 3560 */         return i; 
/* 3562 */       if (i == 38) {
/* 3564 */         if (this.mCfgReplaceEntities) {
/* 3566 */           if (this.mInputEnd - this.mInputPtr < 3 || resolveSimpleEntity(true) == 0)
/* 3570 */             i = fullyResolveEntity(true); 
/* 3579 */         } else if (resolveCharOnlyEntity(true) == 0) {
/* 3583 */           return i;
/*      */         } 
/* 3586 */       } else if (i < 32) {
/* 3587 */         if (i == 13 || i == 10) {
/* 3588 */           skipCRLF((char)i);
/*      */         } else {
/* 3589 */           if (i < 0)
/* 3590 */             return i; 
/* 3591 */           if (i != 9)
/* 3592 */             throwInvalidSpace(i); 
/*      */         } 
/*      */       } 
/* 3598 */       while (this.mInputPtr < this.mInputEnd) {
/* 3599 */         char c = this.mInputBuffer[this.mInputPtr++];
/* 3600 */         if (c < '?')
/* 3601 */           i = c; 
/*      */       } 
/* 3606 */       i = getNext();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void ensureFinishToken() throws XMLStreamException {
/* 3620 */     if (this.mTokenState < this.mStTextThreshold)
/* 3621 */       finishToken(false); 
/*      */   }
/*      */   
/*      */   protected void safeEnsureFinishToken() {
/* 3627 */     if (this.mTokenState < this.mStTextThreshold)
/* 3628 */       safeFinishToken(); 
/*      */   }
/*      */   
/*      */   protected void safeFinishToken() {
/*      */     try {
/* 3640 */       boolean deferErrors = (this.mCurrToken == 4);
/* 3641 */       finishToken(deferErrors);
/* 3642 */     } catch (XMLStreamException strex) {
/* 3643 */       throwLazyError(strex);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void finishToken(boolean deferErrors) throws XMLStreamException {
/*      */     boolean prolog;
/* 3660 */     switch (this.mCurrToken) {
/*      */       case 12:
/* 3662 */         if (this.mCfgCoalesceText) {
/* 3663 */           readCoalescedText(this.mCurrToken, deferErrors);
/* 3665 */         } else if (readCDataSecondary(this.mShortestTextSegment)) {
/* 3666 */           this.mTokenState = 3;
/*      */         } else {
/* 3668 */           this.mTokenState = 2;
/*      */         } 
/*      */         return;
/*      */       case 4:
/* 3674 */         if (this.mCfgCoalesceText) {
/* 3679 */           if (this.mTokenState == 3 && this.mInputPtr + 1 < this.mInputEnd && this.mInputBuffer[this.mInputPtr + 1] != '!') {
/* 3682 */             this.mTokenState = 4;
/*      */             return;
/*      */           } 
/* 3685 */           readCoalescedText(this.mCurrToken, deferErrors);
/* 3687 */         } else if (readTextSecondary(this.mShortestTextSegment, deferErrors)) {
/* 3688 */           this.mTokenState = 3;
/*      */         } else {
/* 3690 */           this.mTokenState = 2;
/*      */         } 
/*      */         return;
/*      */       case 6:
/* 3701 */         prolog = (this.mParseState != 1);
/* 3702 */         readSpaceSecondary(prolog);
/* 3703 */         this.mTokenState = 4;
/*      */         return;
/*      */       case 5:
/* 3708 */         readComment();
/* 3709 */         this.mTokenState = 4;
/*      */         return;
/*      */       case 11:
/*      */         try {
/* 3721 */           finishDTD(true);
/*      */         } finally {
/* 3723 */           this.mTokenState = 4;
/*      */         } 
/*      */         return;
/*      */       case 3:
/* 3728 */         readPI();
/* 3729 */         this.mTokenState = 4;
/*      */         return;
/*      */       case 1:
/*      */       case 2:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*      */       case 14:
/*      */       case 15:
/* 3739 */         throw new IllegalStateException("finishToken() called when current token is " + tokenTypeDesc(this.mCurrToken));
/*      */     } 
/* 3747 */     throw new IllegalStateException("Internal error: unexpected token " + tokenTypeDesc(this.mCurrToken));
/*      */   }
/*      */   
/*      */   private void readComment() throws XMLStreamException {
/* 3753 */     char[] inputBuf = this.mInputBuffer;
/* 3754 */     int inputLen = this.mInputEnd;
/* 3755 */     int ptr = this.mInputPtr;
/* 3756 */     int start = ptr;
/* 3759 */     while (ptr < inputLen) {
/* 3760 */       char c = inputBuf[ptr++];
/* 3761 */       if (c > '-')
/*      */         continue; 
/* 3765 */       if (c < ' ') {
/* 3766 */         if (c == '\n') {
/* 3767 */           markLF(ptr);
/*      */           continue;
/*      */         } 
/* 3768 */         if (c == '\r') {
/* 3769 */           if (!this.mNormalizeLFs && ptr < inputLen) {
/* 3770 */             if (inputBuf[ptr] == '\n')
/* 3771 */               ptr++; 
/* 3773 */             markLF(ptr);
/*      */             continue;
/*      */           } 
/* 3775 */           ptr--;
/*      */           break;
/*      */         } 
/* 3778 */         if (c != '\t')
/* 3779 */           throwInvalidSpace(c); 
/*      */         continue;
/*      */       } 
/* 3781 */       if (c == '-') {
/* 3784 */         if (ptr + 1 >= inputLen) {
/* 3788 */           ptr--;
/*      */           break;
/*      */         } 
/* 3792 */         if (inputBuf[ptr] != '-')
/*      */           continue; 
/* 3797 */         c = inputBuf[ptr + 1];
/* 3798 */         if (c != '>')
/* 3799 */           throwParseError("String '--' not allowed in comment (missing '>'?)"); 
/* 3801 */         this.mTextBuffer.resetWithShared(inputBuf, start, ptr - start - 1);
/* 3802 */         this.mInputPtr = ptr + 2;
/*      */         return;
/*      */       } 
/*      */     } 
/* 3807 */     this.mInputPtr = ptr;
/* 3808 */     this.mTextBuffer.resetWithCopy(inputBuf, start, ptr - start);
/* 3809 */     readComment2(this.mTextBuffer);
/*      */   }
/*      */   
/*      */   private void readComment2(TextBuffer tb) throws XMLStreamException {
/* 3818 */     char[] outBuf = this.mTextBuffer.getCurrentSegment();
/* 3819 */     int outPtr = this.mTextBuffer.getCurrentSegmentSize();
/* 3820 */     int outLen = outBuf.length;
/*      */     while (true) {
/* 3823 */       char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in comment");
/* 3826 */       if (c < ' ') {
/* 3827 */         if (c == '\n') {
/* 3828 */           markLF();
/* 3829 */         } else if (c == '\r') {
/* 3830 */           if (skipCRLF(c)) {
/* 3831 */             if (!this.mNormalizeLFs) {
/* 3832 */               if (outPtr >= outLen) {
/* 3833 */                 outBuf = this.mTextBuffer.finishCurrentSegment();
/* 3834 */                 outLen = outBuf.length;
/* 3835 */                 outPtr = 0;
/*      */               } 
/* 3837 */               outBuf[outPtr++] = c;
/*      */             } 
/* 3840 */             c = '\n';
/* 3841 */           } else if (this.mNormalizeLFs) {
/* 3842 */             c = '\n';
/*      */           } 
/* 3844 */         } else if (c != '\t') {
/* 3845 */           throwInvalidSpace(c);
/*      */         } 
/* 3847 */       } else if (c == '-') {
/* 3848 */         c = getNextCharFromCurrent(" in comment");
/* 3849 */         if (c == '-') {
/* 3851 */           c = getNextCharFromCurrent(" in comment");
/* 3852 */           if (c != '>')
/* 3853 */             throwParseError(ErrorConsts.ERR_HYPHENS_IN_COMMENT); 
/*      */           break;
/*      */         } 
/* 3862 */         c = '-';
/* 3863 */         this.mInputPtr--;
/*      */       } 
/* 3867 */       if (outPtr >= outLen) {
/* 3868 */         outBuf = this.mTextBuffer.finishCurrentSegment();
/* 3869 */         outLen = outBuf.length;
/* 3870 */         outPtr = 0;
/*      */       } 
/* 3873 */       outBuf[outPtr++] = c;
/*      */     } 
/* 3877 */     this.mTextBuffer.setCurrentLength(outPtr);
/*      */   }
/*      */   
/*      */   private final int readPIPrimary() throws XMLStreamException {
/* 3892 */     String target = parseFullName();
/* 3893 */     this.mCurrName = target;
/* 3895 */     if (target.length() == 0)
/* 3896 */       throwParseError(ErrorConsts.ERR_WF_PI_MISSING_TARGET); 
/* 3900 */     if (target.equalsIgnoreCase("xml")) {
/* 3902 */       if (!this.mConfig.inputParsingModeDocuments())
/* 3903 */         throwParseError(ErrorConsts.ERR_WF_PI_XML_TARGET, target, (Object)null); 
/* 3906 */       char c1 = getNextCharFromCurrent(" in xml declaration");
/* 3907 */       if (!isSpaceChar(c1))
/* 3908 */         throwUnexpectedChar(c1, "excepted a space in xml declaration after 'xml'"); 
/* 3910 */       return handleMultiDocStart(7);
/*      */     } 
/* 3914 */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in processing instruction");
/* 3916 */     if (isSpaceChar(c)) {
/* 3917 */       this.mTokenState = 1;
/* 3919 */       skipWS(c);
/*      */     } else {
/* 3921 */       this.mTokenState = 4;
/* 3922 */       this.mTextBuffer.resetWithEmpty();
/* 3924 */       if (c != '?' || getNextCharFromCurrent(" in processing instruction") != '>')
/* 3925 */         throwUnexpectedChar(c, ErrorConsts.ERR_WF_PI_XML_MISSING_SPACE); 
/*      */     } 
/* 3929 */     return 3;
/*      */   }
/*      */   
/*      */   private void readPI() throws XMLStreamException {
/* 3939 */     int ptr = this.mInputPtr;
/* 3940 */     int start = ptr;
/* 3941 */     char[] inputBuf = this.mInputBuffer;
/* 3942 */     int inputLen = this.mInputEnd;
/* 3945 */     while (ptr < inputLen) {
/* 3946 */       char c = inputBuf[ptr++];
/* 3947 */       if (c < ' ') {
/* 3948 */         if (c == '\n') {
/* 3949 */           markLF(ptr);
/*      */           continue;
/*      */         } 
/* 3950 */         if (c == '\r') {
/* 3951 */           if (ptr < inputLen && !this.mNormalizeLFs) {
/* 3952 */             if (inputBuf[ptr] == '\n')
/* 3953 */               ptr++; 
/* 3955 */             markLF(ptr);
/*      */             continue;
/*      */           } 
/* 3957 */           ptr--;
/*      */           break;
/*      */         } 
/* 3960 */         if (c != '\t')
/* 3961 */           throwInvalidSpace(c); 
/*      */         continue;
/*      */       } 
/* 3963 */       if (c == '?') {
/*      */         while (true) {
/* 3966 */           if (ptr >= inputLen) {
/* 3971 */             ptr--;
/*      */             break;
/*      */           } 
/* 3974 */           c = inputBuf[ptr++];
/* 3975 */           if (c == '>') {
/* 3976 */             this.mInputPtr = ptr;
/* 3978 */             this.mTextBuffer.resetWithShared(inputBuf, start, ptr - start - 2);
/*      */             return;
/*      */           } 
/* 3981 */           if (c != '?')
/* 3983 */             ptr--; 
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/* 3990 */     this.mInputPtr = ptr;
/* 3992 */     this.mTextBuffer.resetWithCopy(inputBuf, start, ptr - start);
/* 3993 */     readPI2(this.mTextBuffer);
/*      */   }
/*      */   
/*      */   private void readPI2(TextBuffer tb) throws XMLStreamException {
/* 3999 */     char[] inputBuf = this.mInputBuffer;
/* 4000 */     int inputLen = this.mInputEnd;
/* 4001 */     int inputPtr = this.mInputPtr;
/* 4006 */     char[] outBuf = tb.getCurrentSegment();
/* 4007 */     int outPtr = tb.getCurrentSegmentSize();
/*      */     label49: while (true) {
/* 4012 */       if (inputPtr >= inputLen) {
/* 4013 */         loadMoreFromCurrent(" in processing instruction");
/* 4014 */         inputBuf = this.mInputBuffer;
/* 4015 */         inputPtr = this.mInputPtr;
/* 4016 */         inputLen = this.mInputEnd;
/*      */       } 
/* 4020 */       char c = inputBuf[inputPtr++];
/* 4021 */       if (c < ' ') {
/* 4022 */         if (c == '\n') {
/* 4023 */           markLF(inputPtr);
/* 4024 */         } else if (c == '\r') {
/* 4025 */           this.mInputPtr = inputPtr;
/* 4026 */           if (skipCRLF(c)) {
/* 4027 */             if (!this.mNormalizeLFs) {
/* 4029 */               if (outPtr >= outBuf.length) {
/* 4030 */                 outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4031 */                 outPtr = 0;
/*      */               } 
/* 4033 */               outBuf[outPtr++] = c;
/*      */             } 
/* 4036 */             c = '\n';
/* 4037 */           } else if (this.mNormalizeLFs) {
/* 4038 */             c = '\n';
/*      */           } 
/* 4043 */           inputPtr = this.mInputPtr;
/* 4044 */           inputBuf = this.mInputBuffer;
/* 4045 */           inputLen = this.mInputEnd;
/* 4046 */         } else if (c != '\t') {
/* 4047 */           throwInvalidSpace(c);
/*      */         } 
/* 4049 */       } else if (c == '?') {
/* 4050 */         this.mInputPtr = inputPtr;
/*      */         while (true) {
/* 4054 */           c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in processing instruction");
/* 4056 */           if (c == '>')
/*      */             break; 
/* 4058 */           if (c == '?') {
/* 4059 */             if (outPtr >= outBuf.length) {
/* 4060 */               outBuf = tb.finishCurrentSegment();
/* 4061 */               outPtr = 0;
/*      */             } 
/* 4063 */             outBuf[outPtr++] = c;
/*      */             continue;
/*      */           } 
/* 4071 */           inputPtr = --this.mInputPtr;
/* 4072 */           inputBuf = this.mInputBuffer;
/* 4073 */           inputLen = this.mInputEnd;
/* 4074 */           c = '?';
/*      */           continue label49;
/*      */         } 
/*      */         break;
/*      */       } 
/* 4081 */       if (outPtr >= outBuf.length) {
/* 4082 */         outBuf = tb.finishCurrentSegment();
/* 4083 */         outPtr = 0;
/*      */       } 
/* 4086 */       outBuf[outPtr++] = c;
/*      */     } 
/* 4090 */     tb.setCurrentLength(outPtr);
/*      */   }
/*      */   
/*      */   protected void readCoalescedText(int currType, boolean deferErrors) throws XMLStreamException {
/* 4109 */     if (currType == 4 || currType == 6) {
/* 4110 */       readTextSecondary(2147483647, deferErrors);
/* 4111 */       boolean wasCData = false;
/* 4112 */     } else if (currType == 12) {
/* 4116 */       if (this.mTokenState <= 2)
/* 4117 */         readCDataSecondary(2147483647); 
/* 4119 */       boolean wasCData = true;
/*      */     } else {
/* 4121 */       throw new IllegalStateException("Internal error: unexpected token " + tokenTypeDesc(this.mCurrToken) + "; expected CHARACTERS, CDATA or SPACE.");
/*      */     } 
/* 4125 */     while (!deferErrors || this.mPendingException == null) {
/* 4126 */       if (this.mInputPtr >= this.mInputEnd) {
/* 4127 */         this.mTextBuffer.ensureNotShared();
/* 4128 */         if (!loadMore())
/*      */           break; 
/*      */       } 
/* 4134 */       char c = this.mInputBuffer[this.mInputPtr];
/* 4135 */       if (c == '<') {
/* 4137 */         if (this.mInputEnd - this.mInputPtr < 3) {
/* 4138 */           this.mTextBuffer.ensureNotShared();
/* 4139 */           if (!ensureInput(3))
/*      */             break; 
/*      */         } 
/* 4143 */         if (this.mInputBuffer[this.mInputPtr + 1] != '!' || this.mInputBuffer[this.mInputPtr + 2] != '[')
/*      */           break; 
/* 4149 */         this.mInputPtr += 3;
/* 4151 */         checkCData();
/* 4156 */         readCDataSecondary(2147483647);
/* 4157 */         wasCData = true;
/*      */         continue;
/*      */       } 
/* 4162 */       if (c == '&' && !wasCData)
/*      */         break; 
/* 4166 */       readTextSecondary(2147483647, deferErrors);
/* 4167 */       boolean wasCData = false;
/*      */     } 
/* 4171 */     this.mTokenState = 4;
/*      */   }
/*      */   
/*      */   private final boolean readCDataPrimary(char c) throws XMLStreamException {
/* 4193 */     this.mWsStatus = (c <= ' ') ? 0 : 2;
/* 4195 */     int ptr = this.mInputPtr;
/* 4196 */     int inputLen = this.mInputEnd;
/* 4197 */     char[] inputBuf = this.mInputBuffer;
/* 4198 */     int start = ptr - 1;
/*      */     while (true) {
/* 4201 */       if (c < ' ') {
/* 4202 */         if (c == '\n') {
/* 4203 */           markLF(ptr);
/* 4204 */         } else if (c == '\r') {
/* 4205 */           if (ptr >= inputLen) {
/* 4206 */             ptr--;
/*      */             break;
/*      */           } 
/* 4209 */           if (this.mNormalizeLFs) {
/* 4210 */             if (inputBuf[ptr] == '\n') {
/* 4211 */               ptr--;
/*      */               break;
/*      */             } 
/* 4214 */             inputBuf[ptr - 1] = '\n';
/* 4217 */           } else if (inputBuf[ptr] == '\n') {
/* 4218 */             ptr++;
/*      */           } 
/* 4221 */           markLF(ptr);
/* 4222 */         } else if (c != '\t') {
/* 4223 */           throwInvalidSpace(c);
/*      */         } 
/* 4225 */       } else if (c == ']') {
/* 4227 */         if (ptr + 1 >= inputLen) {
/* 4228 */           ptr--;
/*      */           break;
/*      */         } 
/* 4233 */         if (inputBuf[ptr] == ']') {
/* 4234 */           ptr++;
/*      */           while (true) {
/* 4237 */             if (ptr >= inputLen) {
/* 4241 */               ptr -= 2;
/*      */               break;
/*      */             } 
/* 4244 */             c = inputBuf[ptr++];
/* 4245 */             if (c == '>') {
/* 4246 */               this.mInputPtr = ptr;
/* 4247 */               ptr -= start + 3;
/* 4248 */               this.mTextBuffer.resetWithShared(inputBuf, start, ptr);
/* 4249 */               this.mTokenState = 3;
/* 4250 */               return true;
/*      */             } 
/* 4252 */             if (c != ']') {
/* 4254 */               ptr--;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 4262 */       if (ptr >= inputLen)
/*      */         break; 
/* 4265 */       c = inputBuf[ptr++];
/*      */     } 
/* 4268 */     this.mInputPtr = ptr;
/* 4276 */     int len = ptr - start;
/* 4277 */     this.mTextBuffer.resetWithShared(inputBuf, start, len);
/* 4278 */     if (this.mCfgCoalesceText || this.mTextBuffer.size() < this.mShortestTextSegment) {
/* 4280 */       this.mTokenState = 1;
/*      */     } else {
/* 4282 */       this.mTokenState = 2;
/*      */     } 
/* 4284 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean readCDataSecondary(int shortestSegment) throws XMLStreamException {
/* 4295 */     char[] inputBuf = this.mInputBuffer;
/* 4296 */     int inputLen = this.mInputEnd;
/* 4297 */     int inputPtr = this.mInputPtr;
/* 4302 */     char[] outBuf = this.mTextBuffer.getCurrentSegment();
/* 4303 */     int outPtr = this.mTextBuffer.getCurrentSegmentSize();
/*      */     while (true) {
/* 4306 */       if (inputPtr >= inputLen) {
/* 4307 */         loadMore(" in CDATA section");
/* 4308 */         inputBuf = this.mInputBuffer;
/* 4309 */         inputPtr = this.mInputPtr;
/* 4310 */         inputLen = this.mInputEnd;
/*      */       } 
/* 4312 */       char c = inputBuf[inputPtr++];
/* 4314 */       if (c < ' ') {
/* 4315 */         if (c == '\n') {
/* 4316 */           markLF(inputPtr);
/* 4317 */         } else if (c == '\r') {
/* 4318 */           this.mInputPtr = inputPtr;
/* 4319 */           if (skipCRLF(c)) {
/* 4320 */             if (!this.mNormalizeLFs) {
/* 4322 */               outBuf[outPtr++] = c;
/* 4323 */               if (outPtr >= outBuf.length) {
/* 4324 */                 outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4325 */                 outPtr = 0;
/*      */               } 
/*      */             } 
/* 4329 */             c = '\n';
/* 4330 */           } else if (this.mNormalizeLFs) {
/* 4331 */             c = '\n';
/*      */           } 
/* 4336 */           inputPtr = this.mInputPtr;
/* 4337 */           inputBuf = this.mInputBuffer;
/* 4338 */           inputLen = this.mInputEnd;
/* 4339 */         } else if (c != '\t') {
/* 4340 */           throwInvalidSpace(c);
/*      */         } 
/* 4342 */       } else if (c == ']') {
/* 4344 */         this.mInputPtr = inputPtr;
/* 4345 */         if (checkCDataEnd(outBuf, outPtr))
/* 4346 */           return true; 
/* 4348 */         inputPtr = this.mInputPtr;
/* 4349 */         inputBuf = this.mInputBuffer;
/* 4350 */         inputLen = this.mInputEnd;
/* 4352 */         outBuf = this.mTextBuffer.getCurrentSegment();
/* 4353 */         outPtr = this.mTextBuffer.getCurrentSegmentSize();
/*      */         continue;
/*      */       } 
/* 4358 */       outBuf[outPtr++] = c;
/* 4361 */       if (outPtr >= outBuf.length) {
/* 4362 */         TextBuffer tb = this.mTextBuffer;
/* 4364 */         if (!this.mCfgCoalesceText) {
/* 4365 */           tb.setCurrentLength(outBuf.length);
/* 4366 */           if (tb.size() >= shortestSegment) {
/* 4367 */             this.mInputPtr = inputPtr;
/* 4368 */             return false;
/*      */           } 
/*      */         } 
/* 4372 */         outBuf = tb.finishCurrentSegment();
/* 4373 */         outPtr = 0;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean checkCDataEnd(char[] outBuf, int outPtr) throws XMLStreamException {
/*      */     char c;
/* 4390 */     int bracketCount = 0;
/*      */     do {
/* 4393 */       bracketCount++;
/* 4394 */       c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in CDATA section");
/* 4396 */     } while (c == ']');
/* 4398 */     boolean match = (bracketCount >= 2 && c == '>');
/* 4399 */     if (match)
/* 4400 */       bracketCount -= 2; 
/* 4402 */     while (bracketCount > 0) {
/* 4403 */       bracketCount--;
/* 4404 */       outBuf[outPtr++] = ']';
/* 4405 */       if (outPtr >= outBuf.length) {
/* 4409 */         outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4410 */         outPtr = 0;
/*      */       } 
/*      */     } 
/* 4413 */     this.mTextBuffer.setCurrentLength(outPtr);
/* 4415 */     if (match)
/* 4416 */       return true; 
/* 4419 */     this.mInputPtr--;
/* 4420 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean readTextPrimary(char c) throws XMLStreamException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield mInputPtr : I
/*      */     //   4: istore_2
/*      */     //   5: iload_2
/*      */     //   6: iconst_1
/*      */     //   7: isub
/*      */     //   8: istore_3
/*      */     //   9: iload_1
/*      */     //   10: bipush #32
/*      */     //   12: if_icmpgt -> 130
/*      */     //   15: aload_0
/*      */     //   16: getfield mInputEnd : I
/*      */     //   19: istore #4
/*      */     //   21: iload_2
/*      */     //   22: iload #4
/*      */     //   24: if_icmpge -> 122
/*      */     //   27: aload_0
/*      */     //   28: getfield mNormalizeLFs : Z
/*      */     //   31: ifeq -> 122
/*      */     //   34: iload_1
/*      */     //   35: bipush #13
/*      */     //   37: if_icmpne -> 78
/*      */     //   40: bipush #10
/*      */     //   42: istore_1
/*      */     //   43: aload_0
/*      */     //   44: getfield mInputBuffer : [C
/*      */     //   47: iload_2
/*      */     //   48: caload
/*      */     //   49: iload_1
/*      */     //   50: if_icmpne -> 68
/*      */     //   53: iinc #3, 1
/*      */     //   56: iinc #2, 1
/*      */     //   59: iload_2
/*      */     //   60: iload #4
/*      */     //   62: if_icmplt -> 87
/*      */     //   65: goto -> 122
/*      */     //   68: aload_0
/*      */     //   69: getfield mInputBuffer : [C
/*      */     //   72: iload_3
/*      */     //   73: iload_1
/*      */     //   74: castore
/*      */     //   75: goto -> 87
/*      */     //   78: iload_1
/*      */     //   79: bipush #10
/*      */     //   81: if_icmpeq -> 87
/*      */     //   84: goto -> 122
/*      */     //   87: aload_0
/*      */     //   88: iload_2
/*      */     //   89: invokevirtual markLF : (I)V
/*      */     //   92: aload_0
/*      */     //   93: getfield mCheckIndentation : I
/*      */     //   96: ifle -> 112
/*      */     //   99: aload_0
/*      */     //   100: iload_1
/*      */     //   101: iload_2
/*      */     //   102: invokespecial readIndentation : (CI)I
/*      */     //   105: istore_2
/*      */     //   106: iload_2
/*      */     //   107: ifge -> 112
/*      */     //   110: iconst_1
/*      */     //   111: ireturn
/*      */     //   112: aload_0
/*      */     //   113: getfield mInputBuffer : [C
/*      */     //   116: iload_2
/*      */     //   117: iinc #2, 1
/*      */     //   120: caload
/*      */     //   121: istore_1
/*      */     //   122: aload_0
/*      */     //   123: iconst_0
/*      */     //   124: putfield mWsStatus : I
/*      */     //   127: goto -> 135
/*      */     //   130: aload_0
/*      */     //   131: iconst_2
/*      */     //   132: putfield mWsStatus : I
/*      */     //   135: aload_0
/*      */     //   136: getfield mInputBuffer : [C
/*      */     //   139: astore #4
/*      */     //   141: aload_0
/*      */     //   142: getfield mInputEnd : I
/*      */     //   145: istore #5
/*      */     //   147: iload_1
/*      */     //   148: bipush #63
/*      */     //   150: if_icmpge -> 407
/*      */     //   153: iload_1
/*      */     //   154: bipush #60
/*      */     //   156: if_icmpne -> 182
/*      */     //   159: aload_0
/*      */     //   160: iinc #2, -1
/*      */     //   163: iload_2
/*      */     //   164: putfield mInputPtr : I
/*      */     //   167: aload_0
/*      */     //   168: getfield mTextBuffer : Lcom/ctc/wstx/util/TextBuffer;
/*      */     //   171: aload #4
/*      */     //   173: iload_3
/*      */     //   174: iload_2
/*      */     //   175: iload_3
/*      */     //   176: isub
/*      */     //   177: invokevirtual resetWithShared : ([CII)V
/*      */     //   180: iconst_1
/*      */     //   181: ireturn
/*      */     //   182: iload_1
/*      */     //   183: bipush #32
/*      */     //   185: if_icmpge -> 326
/*      */     //   188: iload_1
/*      */     //   189: bipush #10
/*      */     //   191: if_icmpne -> 202
/*      */     //   194: aload_0
/*      */     //   195: iload_2
/*      */     //   196: invokevirtual markLF : (I)V
/*      */     //   199: goto -> 407
/*      */     //   202: iload_1
/*      */     //   203: bipush #13
/*      */     //   205: if_icmpne -> 273
/*      */     //   208: iload_2
/*      */     //   209: iload #5
/*      */     //   211: if_icmplt -> 220
/*      */     //   214: iinc #2, -1
/*      */     //   217: goto -> 427
/*      */     //   220: aload_0
/*      */     //   221: getfield mNormalizeLFs : Z
/*      */     //   224: ifeq -> 253
/*      */     //   227: aload #4
/*      */     //   229: iload_2
/*      */     //   230: caload
/*      */     //   231: bipush #10
/*      */     //   233: if_icmpne -> 242
/*      */     //   236: iinc #2, -1
/*      */     //   239: goto -> 427
/*      */     //   242: aload #4
/*      */     //   244: iload_2
/*      */     //   245: iconst_1
/*      */     //   246: isub
/*      */     //   247: bipush #10
/*      */     //   249: castore
/*      */     //   250: goto -> 265
/*      */     //   253: aload #4
/*      */     //   255: iload_2
/*      */     //   256: caload
/*      */     //   257: bipush #10
/*      */     //   259: if_icmpne -> 265
/*      */     //   262: iinc #2, 1
/*      */     //   265: aload_0
/*      */     //   266: iload_2
/*      */     //   267: invokevirtual markLF : (I)V
/*      */     //   270: goto -> 407
/*      */     //   273: iload_1
/*      */     //   274: bipush #9
/*      */     //   276: if_icmpeq -> 407
/*      */     //   279: aload_0
/*      */     //   280: iload_2
/*      */     //   281: putfield mInputPtr : I
/*      */     //   284: aload_0
/*      */     //   285: getfield mTextBuffer : Lcom/ctc/wstx/util/TextBuffer;
/*      */     //   288: aload #4
/*      */     //   290: iload_3
/*      */     //   291: iload_2
/*      */     //   292: iload_3
/*      */     //   293: isub
/*      */     //   294: iconst_1
/*      */     //   295: isub
/*      */     //   296: invokevirtual resetWithShared : ([CII)V
/*      */     //   299: iload_2
/*      */     //   300: iload_3
/*      */     //   301: isub
/*      */     //   302: iconst_1
/*      */     //   303: if_icmple -> 310
/*      */     //   306: iconst_1
/*      */     //   307: goto -> 311
/*      */     //   310: iconst_0
/*      */     //   311: istore #6
/*      */     //   313: aload_0
/*      */     //   314: aload_0
/*      */     //   315: iload_1
/*      */     //   316: iload #6
/*      */     //   318: invokevirtual throwInvalidSpace : (IZ)Lcom/ctc/wstx/exc/WstxException;
/*      */     //   321: putfield mPendingException : Ljavax/xml/stream/XMLStreamException;
/*      */     //   324: iconst_1
/*      */     //   325: ireturn
/*      */     //   326: iload_1
/*      */     //   327: bipush #38
/*      */     //   329: if_icmpne -> 338
/*      */     //   332: iinc #2, -1
/*      */     //   335: goto -> 427
/*      */     //   338: iload_1
/*      */     //   339: bipush #62
/*      */     //   341: if_icmpne -> 407
/*      */     //   344: iload_2
/*      */     //   345: iload_3
/*      */     //   346: isub
/*      */     //   347: iconst_3
/*      */     //   348: if_icmplt -> 407
/*      */     //   351: aload #4
/*      */     //   353: iload_2
/*      */     //   354: iconst_3
/*      */     //   355: isub
/*      */     //   356: caload
/*      */     //   357: bipush #93
/*      */     //   359: if_icmpne -> 407
/*      */     //   362: aload #4
/*      */     //   364: iload_2
/*      */     //   365: iconst_2
/*      */     //   366: isub
/*      */     //   367: caload
/*      */     //   368: bipush #93
/*      */     //   370: if_icmpne -> 407
/*      */     //   373: aload_0
/*      */     //   374: iload_2
/*      */     //   375: putfield mInputPtr : I
/*      */     //   378: aload_0
/*      */     //   379: getfield mTextBuffer : Lcom/ctc/wstx/util/TextBuffer;
/*      */     //   382: aload #4
/*      */     //   384: iload_3
/*      */     //   385: iload_2
/*      */     //   386: iload_3
/*      */     //   387: isub
/*      */     //   388: iconst_1
/*      */     //   389: isub
/*      */     //   390: invokevirtual resetWithShared : ([CII)V
/*      */     //   393: aload_0
/*      */     //   394: aload_0
/*      */     //   395: getstatic com/ctc/wstx/cfg/ErrorConsts.ERR_BRACKET_IN_TEXT : Ljava/lang/String;
/*      */     //   398: iconst_1
/*      */     //   399: invokevirtual throwWfcException : (Ljava/lang/String;Z)Lcom/ctc/wstx/exc/WstxException;
/*      */     //   402: putfield mPendingException : Ljavax/xml/stream/XMLStreamException;
/*      */     //   405: iconst_1
/*      */     //   406: ireturn
/*      */     //   407: iload_2
/*      */     //   408: iload #5
/*      */     //   410: if_icmplt -> 416
/*      */     //   413: goto -> 427
/*      */     //   416: aload #4
/*      */     //   418: iload_2
/*      */     //   419: iinc #2, 1
/*      */     //   422: caload
/*      */     //   423: istore_1
/*      */     //   424: goto -> 147
/*      */     //   427: aload_0
/*      */     //   428: iload_2
/*      */     //   429: putfield mInputPtr : I
/*      */     //   432: aload_0
/*      */     //   433: getfield mTextBuffer : Lcom/ctc/wstx/util/TextBuffer;
/*      */     //   436: aload #4
/*      */     //   438: iload_3
/*      */     //   439: iload_2
/*      */     //   440: iload_3
/*      */     //   441: isub
/*      */     //   442: invokevirtual resetWithShared : ([CII)V
/*      */     //   445: iconst_0
/*      */     //   446: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #4441	-> 0
/*      */     //   #4442	-> 5
/*      */     //   #4445	-> 9
/*      */     //   #4446	-> 15
/*      */     //   #4456	-> 21
/*      */     //   #4457	-> 34
/*      */     //   #4458	-> 40
/*      */     //   #4459	-> 43
/*      */     //   #4463	-> 53
/*      */     //   #4465	-> 56
/*      */     //   #4466	-> 65
/*      */     //   #4469	-> 68
/*      */     //   #4471	-> 78
/*      */     //   #4472	-> 84
/*      */     //   #4474	-> 87
/*      */     //   #4475	-> 92
/*      */     //   #4476	-> 99
/*      */     //   #4477	-> 106
/*      */     //   #4478	-> 110
/*      */     //   #4482	-> 112
/*      */     //   #4487	-> 122
/*      */     //   #4488	-> 127
/*      */     //   #4489	-> 130
/*      */     //   #4492	-> 135
/*      */     //   #4493	-> 141
/*      */     //   #4497	-> 147
/*      */     //   #4498	-> 153
/*      */     //   #4499	-> 159
/*      */     //   #4500	-> 167
/*      */     //   #4501	-> 180
/*      */     //   #4503	-> 182
/*      */     //   #4504	-> 188
/*      */     //   #4505	-> 194
/*      */     //   #4506	-> 202
/*      */     //   #4507	-> 208
/*      */     //   #4508	-> 214
/*      */     //   #4509	-> 217
/*      */     //   #4511	-> 220
/*      */     //   #4512	-> 227
/*      */     //   #4513	-> 236
/*      */     //   #4514	-> 239
/*      */     //   #4522	-> 242
/*      */     //   #4525	-> 253
/*      */     //   #4526	-> 262
/*      */     //   #4529	-> 265
/*      */     //   #4530	-> 273
/*      */     //   #4532	-> 279
/*      */     //   #4533	-> 284
/*      */     //   #4538	-> 299
/*      */     //   #4539	-> 313
/*      */     //   #4540	-> 324
/*      */     //   #4542	-> 326
/*      */     //   #4544	-> 332
/*      */     //   #4545	-> 335
/*      */     //   #4546	-> 338
/*      */     //   #4548	-> 344
/*      */     //   #4549	-> 351
/*      */     //   #4554	-> 373
/*      */     //   #4555	-> 378
/*      */     //   #4556	-> 393
/*      */     //   #4557	-> 405
/*      */     //   #4563	-> 407
/*      */     //   #4564	-> 413
/*      */     //   #4566	-> 416
/*      */     //   #4568	-> 427
/*      */     //   #4576	-> 432
/*      */     //   #4577	-> 445
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   21	106	4	len	I
/*      */     //   313	13	6	deferErrors	Z
/*      */     //   0	447	0	this	Lcom/ctc/wstx/sr/BasicStreamReader;
/*      */     //   0	447	1	c	C
/*      */     //   5	442	2	ptr	I
/*      */     //   9	438	3	start	I
/*      */     //   141	306	4	inputBuf	[C
/*      */     //   147	300	5	inputLen	I
/*      */   }
/*      */   
/*      */   protected final boolean readTextSecondary(int shortestSegment, boolean deferErrors) throws XMLStreamException {
/* 4596 */     char[] outBuf = this.mTextBuffer.getCurrentSegment();
/* 4597 */     int outPtr = this.mTextBuffer.getCurrentSegmentSize();
/* 4598 */     int inputPtr = this.mInputPtr;
/* 4599 */     char[] inputBuffer = this.mInputBuffer;
/* 4600 */     int inputLen = this.mInputEnd;
/*      */     while (true) {
/* 4603 */       if (inputPtr >= inputLen) {
/* 4609 */         this.mInputPtr = inputPtr;
/* 4610 */         if (!loadMore())
/*      */           break; 
/* 4613 */         inputPtr = this.mInputPtr;
/* 4614 */         inputBuffer = this.mInputBuffer;
/* 4615 */         inputLen = this.mInputEnd;
/*      */       } 
/* 4617 */       char c = inputBuffer[inputPtr++];
/* 4620 */       if (c < '?')
/* 4621 */         if (c < ' ') {
/* 4622 */           if (c == '\n') {
/* 4623 */             markLF(inputPtr);
/* 4624 */           } else if (c == '\r') {
/* 4625 */             this.mInputPtr = inputPtr;
/* 4626 */             if (skipCRLF(c)) {
/* 4627 */               if (!this.mNormalizeLFs) {
/* 4629 */                 outBuf[outPtr++] = c;
/* 4630 */                 if (outPtr >= outBuf.length) {
/* 4631 */                   outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4632 */                   outPtr = 0;
/*      */                 } 
/*      */               } 
/* 4636 */               c = '\n';
/* 4637 */             } else if (this.mNormalizeLFs) {
/* 4638 */               c = '\n';
/*      */             } 
/* 4645 */             inputLen = this.mInputEnd;
/* 4646 */             inputPtr = this.mInputPtr;
/* 4647 */           } else if (c != '\t') {
/* 4648 */             this.mTextBuffer.setCurrentLength(outPtr);
/* 4649 */             this.mInputPtr = inputPtr;
/* 4650 */             this.mPendingException = (XMLStreamException)throwInvalidSpace(c, deferErrors);
/*      */             break;
/*      */           } 
/*      */         } else {
/* 4653 */           if (c == '<') {
/* 4654 */             this.mInputPtr = inputPtr - 1;
/*      */             break;
/*      */           } 
/* 4656 */           if (c == '&') {
/*      */             int ch;
/* 4657 */             this.mInputPtr = inputPtr;
/* 4659 */             if (this.mCfgReplaceEntities) {
/* 4660 */               if (inputLen - inputPtr < 3 || (ch = resolveSimpleEntity(true)) == 0) {
/* 4664 */                 ch = fullyResolveEntity(true);
/* 4665 */                 if (ch == 0) {
/* 4667 */                   inputBuffer = this.mInputBuffer;
/* 4668 */                   inputLen = this.mInputEnd;
/* 4669 */                   inputPtr = this.mInputPtr;
/*      */                   continue;
/*      */                 } 
/*      */               } 
/*      */             } else {
/* 4678 */               ch = resolveCharOnlyEntity(true);
/* 4679 */               if (ch == 0) {
/* 4683 */                 this.mInputPtr--;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 4688 */             if (ch <= 65535) {
/* 4689 */               c = (char)ch;
/*      */             } else {
/* 4691 */               ch -= 65536;
/* 4693 */               if (outPtr >= outBuf.length) {
/* 4694 */                 outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4695 */                 outPtr = 0;
/*      */               } 
/* 4697 */               outBuf[outPtr++] = (char)((ch >> 10) + 55296);
/* 4698 */               c = (char)((ch & 0x3FF) + 56320);
/*      */             } 
/* 4700 */             inputPtr = this.mInputPtr;
/* 4702 */             inputLen = this.mInputEnd;
/* 4703 */           } else if (c == '>') {
/* 4711 */             if (inputPtr > 2)
/* 4713 */               if (inputBuffer[inputPtr - 3] == ']' && inputBuffer[inputPtr - 2] == ']') {
/* 4715 */                 this.mInputPtr = inputPtr;
/* 4720 */                 this.mTextBuffer.setCurrentLength(outPtr);
/* 4721 */                 this.mPendingException = (XMLStreamException)throwWfcException(ErrorConsts.ERR_BRACKET_IN_TEXT, deferErrors);
/*      */                 break;
/*      */               }  
/*      */           } 
/*      */         }  
/* 4734 */       outBuf[outPtr++] = c;
/* 4737 */       if (outPtr >= outBuf.length) {
/* 4738 */         TextBuffer tb = this.mTextBuffer;
/* 4740 */         tb.setCurrentLength(outBuf.length);
/* 4741 */         if (tb.size() >= shortestSegment) {
/* 4742 */           this.mInputPtr = inputPtr;
/* 4743 */           return false;
/*      */         } 
/* 4746 */         outBuf = tb.finishCurrentSegment();
/* 4747 */         outPtr = 0;
/*      */       } 
/*      */     } 
/* 4751 */     this.mTextBuffer.setCurrentLength(outPtr);
/* 4752 */     return true;
/*      */   }
/*      */   
/*      */   private final int readIndentation(char c, int ptr) throws XMLStreamException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield mInputEnd : I
/*      */     //   4: istore_3
/*      */     //   5: aload_0
/*      */     //   6: getfield mInputBuffer : [C
/*      */     //   9: astore #4
/*      */     //   11: iload_2
/*      */     //   12: iconst_1
/*      */     //   13: isub
/*      */     //   14: istore #5
/*      */     //   16: iload_1
/*      */     //   17: istore #6
/*      */     //   19: aload #4
/*      */     //   21: iload_2
/*      */     //   22: iinc #2, 1
/*      */     //   25: caload
/*      */     //   26: istore_1
/*      */     //   27: iload_1
/*      */     //   28: bipush #32
/*      */     //   30: if_icmpeq -> 39
/*      */     //   33: iload_1
/*      */     //   34: bipush #9
/*      */     //   36: if_icmpne -> 118
/*      */     //   39: iload_1
/*      */     //   40: bipush #32
/*      */     //   42: if_icmpne -> 50
/*      */     //   45: bipush #32
/*      */     //   47: goto -> 52
/*      */     //   50: bipush #8
/*      */     //   52: istore #7
/*      */     //   54: iload #7
/*      */     //   56: iload_2
/*      */     //   57: iadd
/*      */     //   58: istore #7
/*      */     //   60: iload #7
/*      */     //   62: iload_3
/*      */     //   63: if_icmple -> 69
/*      */     //   66: iload_3
/*      */     //   67: istore #7
/*      */     //   69: iload_2
/*      */     //   70: iload #7
/*      */     //   72: if_icmplt -> 81
/*      */     //   75: iinc #2, -1
/*      */     //   78: goto -> 196
/*      */     //   81: aload #4
/*      */     //   83: iload_2
/*      */     //   84: iinc #2, 1
/*      */     //   87: caload
/*      */     //   88: istore #8
/*      */     //   90: iload #8
/*      */     //   92: iload_1
/*      */     //   93: if_icmpeq -> 112
/*      */     //   96: iload #8
/*      */     //   98: bipush #60
/*      */     //   100: if_icmpne -> 106
/*      */     //   103: goto -> 115
/*      */     //   106: iinc #2, -1
/*      */     //   109: goto -> 196
/*      */     //   112: goto -> 69
/*      */     //   115: goto -> 130
/*      */     //   118: iload_1
/*      */     //   119: bipush #60
/*      */     //   121: if_icmpeq -> 130
/*      */     //   124: iinc #2, -1
/*      */     //   127: goto -> 196
/*      */     //   130: iload_2
/*      */     //   131: iload_3
/*      */     //   132: if_icmpge -> 193
/*      */     //   135: aload #4
/*      */     //   137: iload_2
/*      */     //   138: caload
/*      */     //   139: bipush #33
/*      */     //   141: if_icmpeq -> 193
/*      */     //   144: aload_0
/*      */     //   145: iinc #2, -1
/*      */     //   148: iload_2
/*      */     //   149: putfield mInputPtr : I
/*      */     //   152: aload_0
/*      */     //   153: getfield mTextBuffer : Lcom/ctc/wstx/util/TextBuffer;
/*      */     //   156: iload_2
/*      */     //   157: iload #5
/*      */     //   159: isub
/*      */     //   160: iconst_1
/*      */     //   161: isub
/*      */     //   162: iload_1
/*      */     //   163: invokevirtual resetWithIndentation : (IC)V
/*      */     //   166: aload_0
/*      */     //   167: getfield mCheckIndentation : I
/*      */     //   170: bipush #40
/*      */     //   172: if_icmpge -> 186
/*      */     //   175: aload_0
/*      */     //   176: dup
/*      */     //   177: getfield mCheckIndentation : I
/*      */     //   180: bipush #16
/*      */     //   182: iadd
/*      */     //   183: putfield mCheckIndentation : I
/*      */     //   186: aload_0
/*      */     //   187: iconst_1
/*      */     //   188: putfield mWsStatus : I
/*      */     //   191: iconst_m1
/*      */     //   192: ireturn
/*      */     //   193: iinc #2, -1
/*      */     //   196: aload_0
/*      */     //   197: dup
/*      */     //   198: getfield mCheckIndentation : I
/*      */     //   201: iconst_1
/*      */     //   202: isub
/*      */     //   203: putfield mCheckIndentation : I
/*      */     //   206: iload #6
/*      */     //   208: bipush #13
/*      */     //   210: if_icmpne -> 220
/*      */     //   213: aload #4
/*      */     //   215: iload #5
/*      */     //   217: bipush #10
/*      */     //   219: castore
/*      */     //   220: iload_2
/*      */     //   221: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #4782	-> 0
/*      */     //   #4783	-> 5
/*      */     //   #4784	-> 11
/*      */     //   #4785	-> 16
/*      */     //   #4790	-> 19
/*      */     //   #4791	-> 27
/*      */     //   #4793	-> 39
/*      */     //   #4794	-> 54
/*      */     //   #4795	-> 60
/*      */     //   #4796	-> 66
/*      */     //   #4801	-> 69
/*      */     //   #4802	-> 75
/*      */     //   #4803	-> 78
/*      */     //   #4805	-> 81
/*      */     //   #4806	-> 90
/*      */     //   #4807	-> 96
/*      */     //   #4808	-> 103
/*      */     //   #4810	-> 106
/*      */     //   #4811	-> 109
/*      */     //   #4813	-> 112
/*      */     //   #4815	-> 115
/*      */     //   #4816	-> 124
/*      */     //   #4817	-> 127
/*      */     //   #4821	-> 130
/*      */     //   #4823	-> 144
/*      */     //   #4824	-> 152
/*      */     //   #4826	-> 166
/*      */     //   #4827	-> 175
/*      */     //   #4829	-> 186
/*      */     //   #4830	-> 191
/*      */     //   #4833	-> 193
/*      */     //   #4840	-> 196
/*      */     //   #4846	-> 206
/*      */     //   #4847	-> 213
/*      */     //   #4849	-> 220
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   90	22	8	d	C
/*      */     //   54	61	7	lastIndCharPos	I
/*      */     //   0	222	0	this	Lcom/ctc/wstx/sr/BasicStreamReader;
/*      */     //   0	222	1	c	C
/*      */     //   0	222	2	ptr	I
/*      */     //   5	217	3	inputLen	I
/*      */     //   11	211	4	inputBuf	[C
/*      */     //   16	206	5	start	I
/*      */     //   19	203	6	lf	C
/*      */   }
/*      */   
/*      */   private final boolean readSpacePrimary(char c, boolean prologWS) throws XMLStreamException {
/* 4870 */     int ptr = this.mInputPtr;
/* 4871 */     char[] inputBuf = this.mInputBuffer;
/* 4872 */     int inputLen = this.mInputEnd;
/* 4873 */     int start = ptr - 1;
/*      */     while (true) {
/* 4884 */       if (c > ' ') {
/* 4885 */         this.mInputPtr = --ptr;
/* 4886 */         this.mTextBuffer.resetWithShared(this.mInputBuffer, start, ptr - start);
/* 4887 */         return true;
/*      */       } 
/* 4890 */       if (c == '\n') {
/* 4891 */         markLF(ptr);
/* 4892 */       } else if (c == '\r') {
/* 4893 */         if (ptr >= this.mInputEnd) {
/* 4894 */           ptr--;
/*      */           break;
/*      */         } 
/* 4897 */         if (this.mNormalizeLFs) {
/* 4898 */           if (inputBuf[ptr] == '\n') {
/* 4899 */             ptr--;
/*      */             break;
/*      */           } 
/* 4902 */           inputBuf[ptr - 1] = '\n';
/* 4905 */         } else if (inputBuf[ptr] == '\n') {
/* 4906 */           ptr++;
/*      */         } 
/* 4909 */         markLF(ptr);
/* 4910 */       } else if (c != ' ' && c != '\t') {
/* 4911 */         throwInvalidSpace(c);
/*      */       } 
/* 4913 */       if (ptr >= inputLen)
/*      */         break; 
/* 4916 */       c = inputBuf[ptr++];
/*      */     } 
/* 4919 */     this.mInputPtr = ptr;
/* 4924 */     this.mTextBuffer.resetWithShared(inputBuf, start, ptr - start);
/* 4925 */     return false;
/*      */   }
/*      */   
/*      */   private void readSpaceSecondary(boolean prologWS) throws XMLStreamException {
/* 4942 */     char[] outBuf = this.mTextBuffer.getCurrentSegment();
/* 4943 */     int outPtr = this.mTextBuffer.getCurrentSegmentSize();
/*      */     while (true) {
/* 4946 */       if (this.mInputPtr >= this.mInputEnd)
/* 4951 */         if (!loadMore())
/*      */           break;  
/* 4955 */       char c = this.mInputBuffer[this.mInputPtr];
/* 4956 */       if (c > ' ')
/*      */         break; 
/* 4959 */       this.mInputPtr++;
/* 4960 */       if (c == '\n') {
/* 4961 */         markLF();
/* 4962 */       } else if (c == '\r') {
/* 4963 */         if (skipCRLF(c)) {
/* 4964 */           if (!this.mNormalizeLFs) {
/* 4966 */             outBuf[outPtr++] = c;
/* 4967 */             if (outPtr >= outBuf.length) {
/* 4968 */               outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4969 */               outPtr = 0;
/*      */             } 
/*      */           } 
/* 4972 */           c = '\n';
/* 4973 */         } else if (this.mNormalizeLFs) {
/* 4974 */           c = '\n';
/*      */         } 
/* 4976 */       } else if (c != ' ' && c != '\t') {
/* 4977 */         throwInvalidSpace(c);
/*      */       } 
/* 4981 */       outBuf[outPtr++] = c;
/* 4984 */       if (outPtr >= outBuf.length) {
/* 4985 */         outBuf = this.mTextBuffer.finishCurrentSegment();
/* 4986 */         outPtr = 0;
/*      */       } 
/*      */     } 
/* 4989 */     this.mTextBuffer.setCurrentLength(outPtr);
/*      */   }
/*      */   
/*      */   private int readAndWriteText(Writer w) throws IOException, XMLStreamException {
/* 5003 */     this.mTokenState = 3;
/* 5011 */     int start = this.mInputPtr;
/* 5012 */     int count = 0;
/*      */     while (true) {
/*      */       char c;
/* 5018 */       if (this.mInputPtr >= this.mInputEnd) {
/* 5019 */         int i = this.mInputPtr - start;
/* 5020 */         if (i > 0) {
/* 5021 */           w.write(this.mInputBuffer, start, i);
/* 5022 */           count += i;
/*      */         } 
/* 5024 */         c = getNextChar(" in document text content");
/* 5025 */         start = this.mInputPtr - 1;
/*      */       } else {
/* 5027 */         c = this.mInputBuffer[this.mInputPtr++];
/*      */       } 
/* 5030 */       if (c < '?') {
/* 5031 */         if (c < ' ') {
/* 5032 */           if (c == '\n') {
/* 5033 */             markLF();
/*      */             continue;
/*      */           } 
/* 5034 */           if (c == '\r') {
/*      */             char d;
/* 5036 */             if (this.mInputPtr >= this.mInputEnd) {
/* 5041 */               int i = this.mInputPtr - start;
/* 5042 */               if (i > 0) {
/* 5043 */                 w.write(this.mInputBuffer, start, i);
/* 5044 */                 count += i;
/*      */               } 
/* 5046 */               d = getNextChar(" in document text content");
/* 5047 */               start = this.mInputPtr;
/*      */             } else {
/* 5049 */               d = this.mInputBuffer[this.mInputPtr++];
/*      */             } 
/* 5051 */             if (d == '\n') {
/* 5052 */               if (this.mNormalizeLFs) {
/* 5058 */                 int i = this.mInputPtr - 2 - start;
/* 5059 */                 if (i > 0) {
/* 5060 */                   w.write(this.mInputBuffer, start, i);
/* 5061 */                   count += i;
/*      */                 } 
/* 5063 */                 start = this.mInputPtr - 1;
/*      */               } 
/*      */             } else {
/* 5068 */               this.mInputPtr--;
/* 5069 */               if (this.mNormalizeLFs)
/* 5070 */                 this.mInputBuffer[this.mInputPtr - 1] = '\n'; 
/*      */             } 
/* 5073 */             markLF();
/*      */             continue;
/*      */           } 
/* 5074 */           if (c != '\t')
/* 5075 */             throwInvalidSpace(c); 
/*      */           continue;
/*      */         } 
/* 5077 */         if (c == '<')
/*      */           break; 
/* 5079 */         if (c == '&') {
/* 5083 */           int ch, i = this.mInputPtr - 1 - start;
/* 5084 */           if (i > 0) {
/* 5085 */             w.write(this.mInputBuffer, start, i);
/* 5086 */             count += i;
/*      */           } 
/* 5089 */           if (this.mCfgReplaceEntities) {
/* 5090 */             if (this.mInputEnd - this.mInputPtr < 3 || (ch = resolveSimpleEntity(true)) == 0)
/* 5092 */               ch = fullyResolveEntity(true); 
/*      */           } else {
/* 5095 */             ch = resolveCharOnlyEntity(true);
/* 5096 */             if (ch == 0) {
/* 5102 */               start = this.mInputPtr;
/*      */               break;
/*      */             } 
/*      */           } 
/* 5106 */           if (ch != 0) {
/* 5107 */             if (ch <= 65535) {
/* 5108 */               c = (char)ch;
/*      */             } else {
/* 5110 */               ch -= 65536;
/* 5111 */               w.write((char)((ch >> 10) + 55296));
/* 5112 */               c = (char)((ch & 0x3FF) + 56320);
/*      */             } 
/* 5114 */             w.write(c);
/* 5115 */             count++;
/*      */           } 
/* 5117 */           start = this.mInputPtr;
/*      */           continue;
/*      */         } 
/* 5118 */         if (c == '>') {
/* 5122 */           if (this.mInputPtr >= 2 && 
/* 5123 */             this.mInputBuffer[this.mInputPtr - 2] == ']' && this.mInputBuffer[this.mInputPtr - 1] == ']') {
/* 5126 */             int i = this.mInputPtr - start;
/* 5127 */             if (i > 0)
/* 5128 */               w.write(this.mInputBuffer, start, i); 
/* 5130 */             throwParseError(ErrorConsts.ERR_BRACKET_IN_TEXT);
/*      */           } 
/*      */           continue;
/*      */         } 
/* 5135 */         if (c == '\000')
/* 5136 */           throwNullChar(); 
/*      */       } 
/*      */     } 
/* 5144 */     this.mInputPtr--;
/* 5147 */     int len = this.mInputPtr - start;
/* 5148 */     if (len > 0) {
/* 5149 */       w.write(this.mInputBuffer, start, len);
/* 5150 */       count += len;
/*      */     } 
/* 5152 */     return count;
/*      */   }
/*      */   
/*      */   private int readAndWriteCData(Writer w) throws IOException, XMLStreamException {
/*      */     boolean match;
/* 5168 */     this.mTokenState = 3;
/* 5175 */     char c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextChar(" in CDATA section");
/* 5177 */     int count = 0;
/*      */     do {
/* 5181 */       int start = this.mInputPtr - 1;
/*      */       while (true) {
/* 5185 */         if (c > '\r') {
/* 5186 */           if (c == ']')
/*      */             break; 
/* 5190 */         } else if (c < ' ') {
/* 5191 */           if (c == '\n') {
/* 5192 */             markLF();
/* 5193 */           } else if (c == '\r') {
/*      */             char d;
/* 5195 */             if (this.mInputPtr >= this.mInputEnd) {
/* 5200 */               int i = this.mInputPtr - start;
/* 5201 */               if (i > 0) {
/* 5202 */                 w.write(this.mInputBuffer, start, i);
/* 5203 */                 count += i;
/*      */               } 
/* 5205 */               d = getNextChar(" in CDATA section");
/* 5206 */               start = this.mInputPtr;
/*      */             } else {
/* 5208 */               d = this.mInputBuffer[this.mInputPtr++];
/*      */             } 
/* 5210 */             if (d == '\n') {
/* 5211 */               if (this.mNormalizeLFs) {
/* 5217 */                 int i = this.mInputPtr - 2 - start;
/* 5218 */                 if (i > 0) {
/* 5219 */                   w.write(this.mInputBuffer, start, i);
/* 5220 */                   count += i;
/*      */                 } 
/* 5222 */                 start = this.mInputPtr - 1;
/*      */               } 
/*      */             } else {
/* 5227 */               this.mInputPtr--;
/* 5228 */               if (this.mNormalizeLFs)
/* 5229 */                 this.mInputBuffer[this.mInputPtr - 1] = '\n'; 
/*      */             } 
/* 5232 */             markLF();
/* 5233 */           } else if (c != '\t') {
/* 5234 */             throwInvalidSpace(c);
/*      */           } 
/*      */         } 
/* 5239 */         if (this.mInputPtr >= this.mInputEnd) {
/* 5240 */           int i = this.mInputPtr - start;
/* 5241 */           if (i > 0) {
/* 5242 */             w.write(this.mInputBuffer, start, i);
/* 5243 */             count += i;
/*      */           } 
/* 5245 */           start = 0;
/* 5246 */           c = getNextChar(" in CDATA section");
/*      */           continue;
/*      */         } 
/* 5248 */         c = this.mInputBuffer[this.mInputPtr++];
/*      */       } 
/* 5257 */       int len = this.mInputPtr - start - 1;
/* 5258 */       if (len > 0) {
/* 5259 */         w.write(this.mInputBuffer, start, len);
/* 5260 */         count += len;
/*      */       } 
/* 5268 */       int bracketCount = 0;
/*      */       do {
/* 5270 */         bracketCount++;
/* 5271 */         c = (this.mInputPtr < this.mInputEnd) ? this.mInputBuffer[this.mInputPtr++] : getNextCharFromCurrent(" in CDATA section");
/* 5273 */       } while (c == ']');
/* 5275 */       match = (bracketCount >= 2 && c == '>');
/* 5276 */       if (match)
/* 5277 */         bracketCount -= 2; 
/* 5279 */       while (bracketCount > 0) {
/* 5280 */         bracketCount--;
/* 5281 */         w.write(93);
/* 5282 */         count++;
/*      */       } 
/* 5284 */     } while (!match);
/* 5292 */     return count;
/*      */   }
/*      */   
/*      */   private int readAndWriteCoalesced(Writer w, boolean wasCData) throws IOException, XMLStreamException {
/* 5301 */     this.mTokenState = 4;
/* 5302 */     int count = 0;
/* 5309 */     while (this.mInputPtr < this.mInputEnd || 
/* 5310 */       loadMore()) {
/* 5318 */       char c = this.mInputBuffer[this.mInputPtr];
/* 5319 */       if (c == '<') {
/* 5321 */         if (this.mInputEnd - this.mInputPtr < 3 && 
/* 5322 */           !ensureInput(3))
/*      */           break; 
/* 5326 */         if (this.mInputBuffer[this.mInputPtr + 1] != '!' || this.mInputBuffer[this.mInputPtr + 2] != '[')
/*      */           break; 
/* 5332 */         this.mInputPtr += 3;
/* 5334 */         checkCData();
/* 5336 */         count += readAndWriteCData(w);
/* 5337 */         wasCData = true;
/*      */         continue;
/*      */       } 
/* 5343 */       if (c == '&' && !wasCData)
/*      */         break; 
/* 5346 */       count += readAndWriteText(w);
/* 5347 */       wasCData = false;
/*      */     } 
/* 5351 */     return count;
/*      */   }
/*      */   
/*      */   protected final boolean skipWS(char c) throws XMLStreamException {
/* 5369 */     if (c > ' ')
/* 5370 */       return false; 
/*      */     while (true) {
/* 5374 */       if (c == '\n' || c == '\r') {
/* 5375 */         skipCRLF(c);
/* 5376 */       } else if (c != ' ' && c != '\t') {
/* 5377 */         throwInvalidSpace(c);
/*      */       } 
/* 5379 */       if (this.mInputPtr >= this.mInputEnd)
/* 5381 */         if (!loadMoreFromCurrent())
/* 5382 */           return true;  
/* 5385 */       c = this.mInputBuffer[this.mInputPtr];
/* 5386 */       if (c > ' ')
/* 5387 */         return true; 
/* 5389 */       this.mInputPtr++;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected EntityDecl findEntity(String id, Object arg) throws XMLStreamException {
/* 5403 */     EntityDecl ed = this.mConfig.findCustomInternalEntity(id);
/* 5404 */     if (ed == null && this.mGeneralEntities != null)
/* 5405 */       ed = (EntityDecl)this.mGeneralEntities.get(id); 
/* 5410 */     if (this.mDocStandalone == 1 && 
/* 5411 */       ed != null && ed.wasDeclaredExternally())
/* 5412 */       throwParseError(ErrorConsts.ERR_WF_ENTITY_EXT_DECLARED, ed.getName(), (Object)null); 
/* 5415 */     return ed;
/*      */   }
/*      */   
/*      */   protected void handleUndeclaredEntity(String id) throws XMLStreamException {
/* 5421 */     throwParseError((this.mDocStandalone == 1) ? ErrorConsts.ERR_WF_GE_UNDECLARED_SA : ErrorConsts.ERR_WF_GE_UNDECLARED, id, (Object)null);
/*      */   }
/*      */   
/*      */   protected void handleIncompleteEntityProblem(WstxInputSource closing) throws XMLStreamException {
/* 5430 */     String top = this.mElementStack.isEmpty() ? "[ROOT]" : this.mElementStack.getTopElementDesc();
/* 5431 */     throwParseError("Unexpected end of entity expansion for entity &{0}; was expecting a close tag for element <{1}>", closing.getEntityId(), top);
/*      */   }
/*      */   
/*      */   protected void handleGreedyEntityProblem(WstxInputSource input) throws XMLStreamException {
/* 5449 */     String top = this.mElementStack.isEmpty() ? "[ROOT]" : this.mElementStack.getTopElementDesc();
/* 5450 */     throwParseError("Improper GE/element nesting: entity &" + input.getEntityId() + " contains closing tag for <" + top + ">");
/*      */   }
/*      */   
/*      */   private void throwNotTextual(int type) {
/* 5456 */     throw new IllegalStateException("Not a textual event (" + tokenTypeDesc(this.mCurrToken) + ")");
/*      */   }
/*      */   
/*      */   private void throwNotTextXxx(int type) {
/* 5462 */     throw new IllegalStateException("getTextXxx() methods can not be called on " + tokenTypeDesc(this.mCurrToken));
/*      */   }
/*      */   
/*      */   protected void throwNotTextualOrElem(int type) {
/* 5468 */     throw new IllegalStateException(MessageFormat.format(ErrorConsts.ERR_STATE_NOT_ELEM_OR_TEXT, new Object[] { tokenTypeDesc(type) }));
/*      */   }
/*      */   
/*      */   protected void throwUnexpectedEOF() throws WstxException {
/* 5478 */     throwUnexpectedEOF("; was expecting a close tag for element <" + this.mElementStack.getTopElementDesc() + ">");
/*      */   }
/*      */   
/*      */   protected XMLStreamException _constructUnexpectedInTyped(int nextToken) {
/* 5486 */     if (nextToken == 1)
/* 5487 */       return (XMLStreamException)_constructTypeException("Element content can not contain child START_ELEMENT when using Typed Access methods", (String)null); 
/* 5489 */     return (XMLStreamException)_constructTypeException("Expected a text token, got " + tokenTypeDesc(nextToken), (String)null);
/*      */   }
/*      */   
/*      */   protected TypedXMLStreamException _constructTypeException(String msg, String lexicalValue) {
/* 5494 */     return new TypedXMLStreamException(lexicalValue, msg, (Location)getStartLocation());
/*      */   }
/*      */   
/*      */   protected void reportInvalidContent(int evtType) throws XMLStreamException {
/* 5507 */     throwParseError("Internal error: sub-class should override method");
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\BasicStreamReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */