/*      */ package com.ctc.wstx.sw;
/*      */ 
/*      */ import com.ctc.wstx.api.WriterConfig;
/*      */ import com.ctc.wstx.cfg.ErrorConsts;
/*      */ import com.ctc.wstx.cfg.OutputConfigFlags;
/*      */ import com.ctc.wstx.exc.WstxIOException;
/*      */ import com.ctc.wstx.exc.WstxValidationException;
/*      */ import com.ctc.wstx.io.WstxInputLocation;
/*      */ import com.ctc.wstx.sr.AttributeCollector;
/*      */ import com.ctc.wstx.sr.InputElementStack;
/*      */ import com.ctc.wstx.sr.StreamReaderImpl;
/*      */ import com.ctc.wstx.util.DataUtil;
/*      */ import com.ctc.wstx.util.StringUtil;
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.text.MessageFormat;
/*      */ import javax.xml.namespace.NamespaceContext;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLReporter;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import javax.xml.stream.XMLStreamReader;
/*      */ import javax.xml.stream.events.Characters;
/*      */ import javax.xml.stream.events.StartElement;
/*      */ import org.codehaus.stax2.DTDInfo;
/*      */ import org.codehaus.stax2.XMLStreamLocation2;
/*      */ import org.codehaus.stax2.XMLStreamReader2;
/*      */ import org.codehaus.stax2.ri.Stax2WriterImpl;
/*      */ import org.codehaus.stax2.validation.ValidationContext;
/*      */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*      */ import org.codehaus.stax2.validation.ValidatorPair;
/*      */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*      */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ 
/*      */ public abstract class BaseStreamWriter extends Stax2WriterImpl implements ValidationContext, OutputConfigFlags {
/*      */   protected static final int STATE_PROLOG = 1;
/*      */   
/*      */   protected static final int STATE_TREE = 2;
/*      */   
/*      */   protected static final int STATE_EPILOG = 3;
/*      */   
/*      */   protected static final char CHAR_SPACE = ' ';
/*      */   
/*      */   protected static final int MIN_ARRAYCOPY = 12;
/*      */   
/*      */   protected static final int ATTR_MIN_ARRAYCOPY = 12;
/*      */   
/*      */   protected static final int DEFAULT_COPYBUFFER_LEN = 512;
/*      */   
/*      */   protected final XmlWriter mWriter;
/*      */   
/*  102 */   protected char[] mCopyBuffer = null;
/*      */   
/*      */   protected final WriterConfig mConfig;
/*      */   
/*      */   protected final boolean mCfgCDataAsText;
/*      */   
/*      */   protected final boolean mCfgCopyDefaultAttrs;
/*      */   
/*      */   protected final boolean mCfgAutomaticEmptyElems;
/*      */   
/*      */   protected boolean mCheckStructure;
/*      */   
/*      */   protected boolean mCheckAttrs;
/*      */   
/*      */   protected String mEncoding;
/*      */   
/*  144 */   protected XMLValidator mValidator = null;
/*      */   
/*      */   protected boolean mXml11 = false;
/*      */   
/*  155 */   protected ValidationProblemHandler mVldProbHandler = null;
/*      */   
/*  163 */   protected int mState = 1;
/*      */   
/*      */   protected boolean mAnyOutput = false;
/*      */   
/*      */   protected boolean mStartElementOpen = false;
/*      */   
/*      */   protected boolean mEmptyElement = false;
/*      */   
/*  196 */   protected int mVldContent = 4;
/*      */   
/*  205 */   protected String mDtdRootElem = null;
/*      */   
/*      */   protected boolean mReturnNullForDefaultNamespace;
/*      */   
/*      */   protected BaseStreamWriter(XmlWriter xw, String enc, WriterConfig cfg) {
/*  217 */     this.mWriter = xw;
/*  218 */     this.mEncoding = enc;
/*  219 */     this.mConfig = cfg;
/*  221 */     int flags = cfg.getConfigFlags();
/*  223 */     this.mCheckStructure = ((flags & 0x100) != 0);
/*  224 */     this.mCheckAttrs = ((flags & 0x800) != 0);
/*  226 */     this.mCfgAutomaticEmptyElems = ((flags & 0x4) != 0);
/*  227 */     this.mCfgCDataAsText = ((flags & 0x8) != 0);
/*  228 */     this.mCfgCopyDefaultAttrs = ((flags & 0x10) != 0);
/*  230 */     this.mReturnNullForDefaultNamespace = this.mConfig.returnNullForDefaultNamespace();
/*      */   }
/*      */   
/*      */   public void close() throws XMLStreamException {
/*  247 */     _finishDocument(false);
/*      */   }
/*      */   
/*      */   public void flush() throws XMLStreamException {
/*      */     try {
/*  259 */       this.mWriter.flush();
/*  260 */     } catch (IOException ie) {
/*  261 */       throw new WstxIOException(ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract NamespaceContext getNamespaceContext();
/*      */   
/*      */   public abstract String getPrefix(String paramString);
/*      */   
/*      */   public Object getProperty(String name) {
/*  275 */     if (name.equals("com.ctc.wstx.outputUnderlyingStream"))
/*  276 */       return this.mWriter.getOutputStream(); 
/*  278 */     if (name.equals("com.ctc.wstx.outputUnderlyingWriter"))
/*  279 */       return this.mWriter.getWriter(); 
/*  281 */     return this.mConfig.getProperty(name);
/*      */   }
/*      */   
/*      */   public abstract void setDefaultNamespace(String paramString) throws XMLStreamException;
/*      */   
/*      */   public abstract void setNamespaceContext(NamespaceContext paramNamespaceContext) throws XMLStreamException;
/*      */   
/*      */   public abstract void setPrefix(String paramString1, String paramString2) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeAttribute(String paramString1, String paramString2) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
/*      */   
/*      */   public void writeCData(String data) throws XMLStreamException {
/*      */     int ix;
/*  311 */     if (this.mCfgCDataAsText) {
/*  312 */       writeCharacters(data);
/*      */       return;
/*      */     } 
/*  316 */     this.mAnyOutput = true;
/*  318 */     if (this.mStartElementOpen)
/*  319 */       closeStartElement(this.mEmptyElement); 
/*  321 */     verifyWriteCData();
/*  322 */     if (this.mVldContent == 3 && this.mValidator != null)
/*  327 */       this.mValidator.validateText(data, false); 
/*      */     try {
/*  331 */       ix = this.mWriter.writeCData(data);
/*  332 */     } catch (IOException ioe) {
/*  333 */       throw new WstxIOException(ioe);
/*      */     } 
/*  335 */     if (ix >= 0)
/*  336 */       reportNwfContent(ErrorConsts.WERR_CDATA_CONTENT, DataUtil.Integer(ix)); 
/*      */   }
/*      */   
/*      */   public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
/*  343 */     this.mAnyOutput = true;
/*  345 */     if (this.mStartElementOpen)
/*  346 */       closeStartElement(this.mEmptyElement); 
/*  352 */     if (this.mCheckStructure && 
/*  353 */       inPrologOrEpilog() && 
/*  354 */       !StringUtil.isAllWhitespace(text, start, len))
/*  355 */       reportNwfStructure(ErrorConsts.WERR_PROLOG_NONWS_TEXT); 
/*  360 */     if (this.mVldContent <= 1) {
/*  361 */       if (this.mVldContent == 0) {
/*  362 */         reportInvalidContent(4);
/*  364 */       } else if (!StringUtil.isAllWhitespace(text, start, len)) {
/*  365 */         reportInvalidContent(4);
/*      */       } 
/*  368 */     } else if (this.mVldContent == 3 && 
/*  369 */       this.mValidator != null) {
/*  373 */       this.mValidator.validateText(text, start, len, false);
/*      */     } 
/*  377 */     if (len > 0)
/*      */       try {
/*  382 */         if (inPrologOrEpilog()) {
/*  383 */           this.mWriter.writeRaw(text, start, len);
/*      */         } else {
/*  385 */           this.mWriter.writeCharacters(text, start, len);
/*      */         } 
/*  387 */       } catch (IOException ioe) {
/*  388 */         throw new WstxIOException(ioe);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void writeCharacters(String text) throws XMLStreamException {
/*  396 */     this.mAnyOutput = true;
/*  398 */     if (this.mStartElementOpen)
/*  399 */       closeStartElement(this.mEmptyElement); 
/*  403 */     if (this.mCheckStructure)
/*  405 */       if (inPrologOrEpilog() && 
/*  406 */         !StringUtil.isAllWhitespace(text))
/*  407 */         reportNwfStructure(ErrorConsts.WERR_PROLOG_NONWS_TEXT);  
/*  417 */     if (this.mVldContent <= 1) {
/*  418 */       if (this.mVldContent == 0) {
/*  419 */         reportInvalidContent(4);
/*  421 */       } else if (!StringUtil.isAllWhitespace(text)) {
/*  422 */         reportInvalidContent(4);
/*      */       } 
/*  425 */     } else if (this.mVldContent == 3 && 
/*  426 */       this.mValidator != null) {
/*  430 */       this.mValidator.validateText(text, false);
/*      */     } 
/*  438 */     if (inPrologOrEpilog()) {
/*      */       try {
/*  440 */         this.mWriter.writeRaw(text);
/*  441 */       } catch (IOException ioe) {
/*  442 */         throw new WstxIOException(ioe);
/*      */       } 
/*      */       return;
/*      */     } 
/*  451 */     int len = text.length();
/*  452 */     if (len >= 12) {
/*  453 */       char[] buf = getCopyBuffer();
/*  455 */       int offset = 0;
/*  456 */       while (len > 0) {
/*  457 */         int thisLen = (len > buf.length) ? buf.length : len;
/*  458 */         text.getChars(offset, offset + thisLen, buf, 0);
/*      */         try {
/*  460 */           this.mWriter.writeCharacters(buf, 0, thisLen);
/*  461 */         } catch (IOException ioe) {
/*  462 */           throw new WstxIOException(ioe);
/*      */         } 
/*  464 */         offset += thisLen;
/*  465 */         len -= thisLen;
/*      */       } 
/*      */     } else {
/*      */       try {
/*  469 */         this.mWriter.writeCharacters(text);
/*  470 */       } catch (IOException ioe) {
/*  471 */         throw new WstxIOException(ioe);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeComment(String data) throws XMLStreamException {
/*      */     int ix;
/*  479 */     this.mAnyOutput = true;
/*  481 */     if (this.mStartElementOpen)
/*  482 */       closeStartElement(this.mEmptyElement); 
/*  486 */     if (this.mVldContent == 0)
/*  487 */       reportInvalidContent(5); 
/*      */     try {
/*  496 */       ix = this.mWriter.writeComment(data);
/*  497 */     } catch (IOException ioe) {
/*  498 */       throw new WstxIOException(ioe);
/*      */     } 
/*  501 */     if (ix >= 0)
/*  502 */       reportNwfContent(ErrorConsts.WERR_COMMENT_CONTENT, DataUtil.Integer(ix)); 
/*      */   }
/*      */   
/*      */   public abstract void writeDefaultNamespace(String paramString) throws XMLStreamException;
/*      */   
/*      */   public void writeDTD(String dtd) throws XMLStreamException {
/*  512 */     verifyWriteDTD();
/*  513 */     this.mDtdRootElem = "";
/*      */     try {
/*  515 */       this.mWriter.writeDTD(dtd);
/*  516 */     } catch (IOException ioe) {
/*  517 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void writeEmptyElement(String paramString) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeEmptyElement(String paramString1, String paramString2) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeEmptyElement(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*      */   
/*      */   public void writeEndDocument() throws XMLStreamException {
/*  536 */     _finishDocument(false);
/*      */   }
/*      */   
/*      */   public abstract void writeEndElement() throws XMLStreamException;
/*      */   
/*      */   public void writeEntityRef(String name) throws XMLStreamException {
/*  544 */     this.mAnyOutput = true;
/*  546 */     if (this.mStartElementOpen)
/*  547 */       closeStartElement(this.mEmptyElement); 
/*  551 */     if (this.mCheckStructure && 
/*  552 */       inPrologOrEpilog())
/*  553 */       reportNwfStructure("Trying to output an entity reference outside main element tree (in prolog or epilog)"); 
/*  557 */     if (this.mVldContent == 0)
/*  561 */       reportInvalidContent(9); 
/*      */     try {
/*  571 */       this.mWriter.writeEntityReference(name);
/*  572 */     } catch (IOException ioe) {
/*  573 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void writeNamespace(String paramString1, String paramString2) throws XMLStreamException;
/*      */   
/*      */   public void writeProcessingInstruction(String target) throws XMLStreamException {
/*  583 */     writeProcessingInstruction(target, null);
/*      */   }
/*      */   
/*      */   public void writeProcessingInstruction(String target, String data) throws XMLStreamException {
/*      */     int ix;
/*  589 */     this.mAnyOutput = true;
/*  591 */     if (this.mStartElementOpen)
/*  592 */       closeStartElement(this.mEmptyElement); 
/*  597 */     if (this.mVldContent == 0)
/*  598 */       reportInvalidContent(3); 
/*      */     try {
/*  602 */       ix = this.mWriter.writePI(target, data);
/*  603 */     } catch (IOException ioe) {
/*  604 */       throw new WstxIOException(ioe);
/*      */     } 
/*  606 */     if (ix >= 0)
/*  607 */       throw new XMLStreamException("Illegal input: processing instruction content has embedded '?>' in it (index " + ix + ")"); 
/*      */   }
/*      */   
/*      */   public void writeStartDocument() throws XMLStreamException {
/*  623 */     if (this.mEncoding == null)
/*  624 */       this.mEncoding = "UTF-8"; 
/*  626 */     writeStartDocument(this.mEncoding, "1.0");
/*      */   }
/*      */   
/*      */   public void writeStartDocument(String version) throws XMLStreamException {
/*  632 */     writeStartDocument(this.mEncoding, version);
/*      */   }
/*      */   
/*      */   public void writeStartDocument(String encoding, String version) throws XMLStreamException {
/*  638 */     doWriteStartDocument(version, encoding, null);
/*      */   }
/*      */   
/*      */   protected void doWriteStartDocument(String version, String encoding, String standAlone) throws XMLStreamException {
/*  648 */     if (this.mCheckStructure && 
/*  649 */       this.mAnyOutput)
/*  650 */       reportNwfStructure("Can not output XML declaration, after other output has already been done."); 
/*  654 */     this.mAnyOutput = true;
/*  656 */     if (this.mConfig.willValidateContent())
/*  660 */       if (version != null && version.length() > 0 && 
/*  661 */         !version.equals("1.0") && !version.equals("1.1"))
/*  663 */         reportNwfContent("Illegal version argument ('" + version + "'); should only use '" + "1.0" + "' or '" + "1.1" + "'");  
/*  670 */     if (version == null || version.length() == 0)
/*  671 */       version = "1.0"; 
/*  677 */     this.mXml11 = "1.1".equals(version);
/*  678 */     if (this.mXml11)
/*  679 */       this.mWriter.enableXml11(); 
/*  682 */     if (encoding != null && encoding.length() > 0)
/*  686 */       if (this.mEncoding == null || this.mEncoding.length() == 0)
/*  687 */         this.mEncoding = encoding;  
/*      */     try {
/*  691 */       this.mWriter.writeXmlDeclaration(version, encoding, standAlone);
/*  692 */     } catch (IOException ioe) {
/*  693 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void writeStartElement(String paramString) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeStartElement(String paramString1, String paramString2) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeStartElement(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*      */   
/*      */   public void copyEventFromReader(XMLStreamReader2 sr, boolean preserveEventData) throws XMLStreamException {
/*      */     try {
/*      */       String version;
/*      */       DTDInfo info;
/*  729 */       switch (sr.getEventType()) {
/*      */         case 7:
/*  732 */           version = sr.getVersion();
/*  737 */           if (version != null && version.length() != 0)
/*  740 */             if (sr.standaloneSet()) {
/*  741 */               writeStartDocument(sr.getVersion(), sr.getCharacterEncodingScheme(), sr.isStandalone());
/*      */             } else {
/*  745 */               writeStartDocument(sr.getCharacterEncodingScheme(), sr.getVersion());
/*      */             }  
/*      */           return;
/*      */         case 8:
/*  753 */           writeEndDocument();
/*      */           return;
/*      */         case 1:
/*  758 */           if (sr instanceof StreamReaderImpl) {
/*  759 */             StreamReaderImpl impl = (StreamReaderImpl)sr;
/*  760 */             copyStartElement(impl.getInputElementStack(), impl.getAttributeCollector());
/*      */           } else {
/*  762 */             copyStartElement((XMLStreamReader)sr);
/*      */           } 
/*      */           return;
/*      */         case 2:
/*  767 */           writeEndElement();
/*      */           return;
/*      */         case 6:
/*  772 */           this.mAnyOutput = true;
/*  774 */           if (this.mStartElementOpen)
/*  775 */             closeStartElement(this.mEmptyElement); 
/*  781 */           sr.getText(wrapAsRawWriter(), preserveEventData);
/*      */           return;
/*      */         case 12:
/*  788 */           if (!this.mCfgCDataAsText) {
/*  789 */             this.mAnyOutput = true;
/*  791 */             if (this.mStartElementOpen)
/*  792 */               closeStartElement(this.mEmptyElement); 
/*  796 */             if (this.mCheckStructure && 
/*  797 */               inPrologOrEpilog())
/*  798 */               reportNwfStructure(ErrorConsts.WERR_PROLOG_CDATA); 
/*  804 */             this.mWriter.writeCDataStart();
/*  805 */             sr.getText(wrapAsRawWriter(), preserveEventData);
/*  806 */             this.mWriter.writeCDataEnd();
/*      */             return;
/*      */           } 
/*      */         case 4:
/*  816 */           this.mAnyOutput = true;
/*  818 */           if (this.mStartElementOpen)
/*  819 */             closeStartElement(this.mEmptyElement); 
/*  821 */           sr.getText(wrapAsTextWriter(), preserveEventData);
/*      */           return;
/*      */         case 5:
/*  827 */           this.mAnyOutput = true;
/*  828 */           if (this.mStartElementOpen)
/*  829 */             closeStartElement(this.mEmptyElement); 
/*  834 */           this.mWriter.writeCommentStart();
/*  835 */           sr.getText(wrapAsRawWriter(), preserveEventData);
/*  836 */           this.mWriter.writeCommentEnd();
/*      */           return;
/*      */         case 3:
/*  842 */           this.mWriter.writePIStart(sr.getPITarget(), true);
/*  843 */           sr.getText(wrapAsRawWriter(), preserveEventData);
/*  844 */           this.mWriter.writePIEnd();
/*      */           return;
/*      */         case 11:
/*  850 */           info = sr.getDTDInfo();
/*  851 */           if (info == null)
/*  855 */             throwOutputError("Current state DOCTYPE, but not DTDInfo Object returned -- reader doesn't support DTDs?"); 
/*  861 */           writeDTD(info);
/*      */           return;
/*      */         case 9:
/*  866 */           writeEntityRef(sr.getLocalName());
/*      */           return;
/*      */       } 
/*  875 */     } catch (IOException ioe) {
/*  876 */       throw new WstxIOException(ioe);
/*      */     } 
/*  879 */     throw new XMLStreamException("Unrecognized event type (" + sr.getEventType() + "); not sure how to copy");
/*      */   }
/*      */   
/*      */   public void closeCompletely() throws XMLStreamException {
/*  892 */     _finishDocument(true);
/*      */   }
/*      */   
/*      */   public boolean isPropertySupported(String name) {
/*  905 */     return this.mConfig.isPropertySupported(name);
/*      */   }
/*      */   
/*      */   public boolean setProperty(String name, Object value) {
/*  920 */     return this.mConfig.setProperty(name, value);
/*      */   }
/*      */   
/*      */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/*  926 */     XMLValidator vld = schema.createValidator(this);
/*  928 */     if (this.mValidator == null) {
/*  933 */       this.mCheckStructure = true;
/*  934 */       this.mCheckAttrs = true;
/*  935 */       this.mValidator = vld;
/*      */     } else {
/*  937 */       this.mValidator = (XMLValidator)new ValidatorPair(this.mValidator, vld);
/*      */     } 
/*  939 */     return vld;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/*  945 */     XMLValidator[] results = new XMLValidator[2];
/*  946 */     XMLValidator found = null;
/*  947 */     if (ValidatorPair.removeValidator(this.mValidator, schema, results)) {
/*  948 */       found = results[0];
/*  949 */       this.mValidator = results[1];
/*  950 */       found.validationCompleted(false);
/*  951 */       if (this.mValidator == null)
/*  952 */         resetValidationFlags(); 
/*      */     } 
/*  955 */     return found;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/*  961 */     XMLValidator[] results = new XMLValidator[2];
/*  962 */     XMLValidator found = null;
/*  963 */     if (ValidatorPair.removeValidator(this.mValidator, validator, results)) {
/*  964 */       found = results[0];
/*  965 */       this.mValidator = results[1];
/*  966 */       found.validationCompleted(false);
/*  967 */       if (this.mValidator == null)
/*  968 */         resetValidationFlags(); 
/*      */     } 
/*  971 */     return found;
/*      */   }
/*      */   
/*      */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/*  976 */     ValidationProblemHandler oldH = this.mVldProbHandler;
/*  977 */     this.mVldProbHandler = h;
/*  978 */     return oldH;
/*      */   }
/*      */   
/*      */   private void resetValidationFlags() {
/*  983 */     int flags = this.mConfig.getConfigFlags();
/*  984 */     this.mCheckStructure = ((flags & 0x100) != 0);
/*  985 */     this.mCheckAttrs = ((flags & 0x800) != 0);
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getLocation() {
/*  996 */     return (XMLStreamLocation2)new WstxInputLocation(null, null, null, this.mWriter.getAbsOffset(), this.mWriter.getRow(), this.mWriter.getColumn());
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/* 1003 */     return this.mEncoding;
/*      */   }
/*      */   
/*      */   public void writeCData(char[] cbuf, int start, int len) throws XMLStreamException {
/*      */     int ix;
/* 1019 */     if (this.mCfgCDataAsText) {
/* 1020 */       writeCharacters(cbuf, start, len);
/*      */       return;
/*      */     } 
/* 1024 */     this.mAnyOutput = true;
/* 1026 */     if (this.mStartElementOpen)
/* 1027 */       closeStartElement(this.mEmptyElement); 
/* 1029 */     verifyWriteCData();
/* 1030 */     if (this.mVldContent == 3 && this.mValidator != null)
/* 1035 */       this.mValidator.validateText(cbuf, start, len, false); 
/*      */     try {
/* 1039 */       ix = this.mWriter.writeCData(cbuf, start, len);
/* 1040 */     } catch (IOException ioe) {
/* 1041 */       throw new WstxIOException(ioe);
/*      */     } 
/* 1043 */     if (ix >= 0)
/* 1044 */       throwOutputError(ErrorConsts.WERR_CDATA_CONTENT, DataUtil.Integer(ix)); 
/*      */   }
/*      */   
/*      */   public void writeDTD(DTDInfo info) throws XMLStreamException {
/* 1051 */     writeDTD(info.getDTDRootName(), info.getDTDSystemId(), info.getDTDPublicId(), info.getDTDInternalSubset());
/*      */   }
/*      */   
/*      */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws XMLStreamException {
/* 1059 */     verifyWriteDTD();
/* 1060 */     this.mDtdRootElem = rootName;
/*      */     try {
/* 1062 */       this.mWriter.writeDTD(rootName, systemId, publicId, internalSubset);
/* 1063 */     } catch (IOException ioe) {
/* 1064 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void writeFullEndElement() throws XMLStreamException;
/*      */   
/*      */   public void writeStartDocument(String version, String encoding, boolean standAlone) throws XMLStreamException {
/* 1074 */     doWriteStartDocument(version, encoding, standAlone ? "yes" : "no");
/*      */   }
/*      */   
/*      */   public void writeRaw(String text) throws XMLStreamException {
/* 1080 */     this.mAnyOutput = true;
/* 1081 */     if (this.mStartElementOpen)
/* 1082 */       closeStartElement(this.mEmptyElement); 
/*      */     try {
/* 1085 */       this.mWriter.writeRaw(text, 0, text.length());
/* 1086 */     } catch (IOException ioe) {
/* 1087 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeRaw(String text, int start, int offset) throws XMLStreamException {
/* 1094 */     this.mAnyOutput = true;
/* 1095 */     if (this.mStartElementOpen)
/* 1096 */       closeStartElement(this.mEmptyElement); 
/*      */     try {
/* 1099 */       this.mWriter.writeRaw(text, start, offset);
/* 1100 */     } catch (IOException ioe) {
/* 1101 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeRaw(char[] text, int start, int offset) throws XMLStreamException {
/* 1108 */     this.mAnyOutput = true;
/* 1109 */     if (this.mStartElementOpen)
/* 1110 */       closeStartElement(this.mEmptyElement); 
/*      */     try {
/* 1113 */       this.mWriter.writeRaw(text, start, offset);
/* 1114 */     } catch (IOException ioe) {
/* 1115 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeSpace(String text) throws XMLStreamException {
/* 1125 */     writeRaw(text);
/*      */   }
/*      */   
/*      */   public void writeSpace(char[] text, int offset, int length) throws XMLStreamException {
/* 1132 */     writeRaw(text, offset, length);
/*      */   }
/*      */   
/*      */   public String getXmlVersion() {
/* 1142 */     return this.mXml11 ? "1.1" : "1.0";
/*      */   }
/*      */   
/*      */   public abstract QName getCurrentElementName();
/*      */   
/*      */   public abstract String getNamespaceURI(String paramString);
/*      */   
/*      */   public String getBaseUri() {
/* 1154 */     return null;
/*      */   }
/*      */   
/*      */   public Location getValidationLocation() {
/* 1158 */     return (Location)getLocation();
/*      */   }
/*      */   
/*      */   public void reportProblem(XMLValidationProblem prob) throws XMLStreamException {
/* 1165 */     if (this.mVldProbHandler != null) {
/* 1166 */       this.mVldProbHandler.reportProblem(prob);
/*      */       return;
/*      */     } 
/* 1180 */     if (prob.getSeverity() > 2)
/* 1181 */       throw WstxValidationException.create(prob); 
/* 1183 */     XMLReporter rep = this.mConfig.getProblemReporter();
/* 1184 */     if (rep != null) {
/* 1185 */       doReportProblem(rep, prob);
/* 1190 */     } else if (prob.getSeverity() >= 2) {
/* 1191 */       throw WstxValidationException.create(prob);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int addDefaultAttribute(String localName, String uri, String prefix, String value) {
/* 1204 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean isNotationDeclared(String name) {
/* 1209 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isUnparsedEntityDeclared(String name) {
/* 1211 */     return false;
/*      */   }
/*      */   
/*      */   public int getAttributeCount() {
/* 1220 */     return 0;
/*      */   }
/*      */   
/*      */   public String getAttributeLocalName(int index) {
/* 1222 */     return null;
/*      */   }
/*      */   
/*      */   public String getAttributeNamespace(int index) {
/* 1224 */     return null;
/*      */   }
/*      */   
/*      */   public String getAttributePrefix(int index) {
/* 1226 */     return null;
/*      */   }
/*      */   
/*      */   public String getAttributeValue(int index) {
/* 1228 */     return null;
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String nsURI, String localName) {
/* 1231 */     return null;
/*      */   }
/*      */   
/*      */   public String getAttributeType(int index) {
/* 1235 */     return "";
/*      */   }
/*      */   
/*      */   public int findAttributeIndex(String nsURI, String localName) {
/* 1239 */     return -1;
/*      */   }
/*      */   
/*      */   public final Writer wrapAsRawWriter() {
/* 1255 */     return this.mWriter.wrapAsRawWriter();
/*      */   }
/*      */   
/*      */   public final Writer wrapAsTextWriter() {
/* 1265 */     return this.mWriter.wrapAsTextWriter();
/*      */   }
/*      */   
/*      */   protected boolean isValidating() {
/* 1277 */     return (this.mValidator != null);
/*      */   }
/*      */   
/*      */   public abstract void writeStartElement(StartElement paramStartElement) throws XMLStreamException;
/*      */   
/*      */   public abstract void writeEndElement(QName paramQName) throws XMLStreamException;
/*      */   
/*      */   public void writeCharacters(Characters ch) throws XMLStreamException {
/* 1313 */     if (this.mStartElementOpen)
/* 1314 */       closeStartElement(this.mEmptyElement); 
/* 1320 */     if (this.mCheckStructure && 
/* 1321 */       inPrologOrEpilog() && 
/* 1322 */       !ch.isIgnorableWhiteSpace() && !ch.isWhiteSpace())
/* 1323 */       reportNwfStructure(ErrorConsts.WERR_PROLOG_NONWS_TEXT); 
/* 1328 */     if (this.mVldContent <= 1) {
/* 1329 */       if (this.mVldContent == 0) {
/* 1330 */         reportInvalidContent(4);
/* 1332 */       } else if (!ch.isIgnorableWhiteSpace() && !ch.isWhiteSpace()) {
/* 1333 */         reportInvalidContent(4);
/*      */       } 
/* 1336 */     } else if (this.mVldContent == 3 && 
/* 1337 */       this.mValidator != null) {
/* 1341 */       this.mValidator.validateText(ch.getData(), false);
/*      */     } 
/*      */     try {
/* 1347 */       this.mWriter.writeCharacters(ch.getData());
/* 1348 */     } catch (IOException ioe) {
/* 1349 */       throw new WstxIOException(ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected abstract void closeStartElement(boolean paramBoolean) throws XMLStreamException;
/*      */   
/*      */   protected final boolean inPrologOrEpilog() {
/* 1362 */     return (this.mState != 2);
/*      */   }
/*      */   
/*      */   private final void _finishDocument(boolean forceRealClose) throws XMLStreamException {
/* 1375 */     if (this.mState != 3) {
/* 1376 */       if (this.mCheckStructure && this.mState == 1)
/* 1377 */         reportNwfStructure("Trying to write END_DOCUMENT when document has no root (ie. trying to output empty document)."); 
/* 1381 */       if (this.mStartElementOpen)
/* 1382 */         closeStartElement(this.mEmptyElement); 
/* 1388 */       if (this.mState != 3 && this.mConfig.automaticEndElementsEnabled())
/*      */         do {
/* 1390 */           writeEndElement();
/* 1391 */         } while (this.mState != 3); 
/*      */     } 
/* 1398 */     char[] buf = this.mCopyBuffer;
/* 1399 */     if (buf != null) {
/* 1400 */       this.mCopyBuffer = null;
/* 1401 */       this.mConfig.freeMediumCBuffer(buf);
/*      */     } 
/*      */     try {
/* 1404 */       this.mWriter.close(forceRealClose);
/* 1405 */     } catch (IOException ie) {
/* 1406 */       throw new WstxIOException(ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void copyStartElement(InputElementStack paramInputElementStack, AttributeCollector paramAttributeCollector) throws IOException, XMLStreamException;
/*      */   
/*      */   public abstract String validateQNamePrefix(QName paramQName) throws XMLStreamException;
/*      */   
/*      */   protected final void verifyWriteCData() throws XMLStreamException {
/* 1441 */     if (this.mCheckStructure && 
/* 1442 */       inPrologOrEpilog())
/* 1443 */       reportNwfStructure(ErrorConsts.WERR_PROLOG_CDATA); 
/* 1447 */     if (this.mVldContent <= 1)
/* 1449 */       reportInvalidContent(12); 
/*      */   }
/*      */   
/*      */   protected final void verifyWriteDTD() throws XMLStreamException {
/* 1457 */     if (this.mCheckStructure) {
/* 1458 */       if (this.mState != 1)
/* 1459 */         throw new XMLStreamException("Can not write DOCTYPE declaration (DTD) when not in prolog any more (state " + this.mState + "; start element(s) written)"); 
/* 1462 */       if (this.mDtdRootElem != null)
/* 1463 */         throw new XMLStreamException("Trying to write multiple DOCTYPE declarations"); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void verifyRootElement(String localName, String prefix) throws XMLStreamException {
/* 1474 */     if (isValidating())
/* 1478 */       if (this.mDtdRootElem != null && this.mDtdRootElem.length() > 0) {
/* 1479 */         String wrongElem = null;
/* 1485 */         if (!localName.equals(this.mDtdRootElem)) {
/* 1488 */           int lnLen = localName.length();
/* 1489 */           int oldLen = this.mDtdRootElem.length();
/* 1491 */           if (oldLen <= lnLen || !this.mDtdRootElem.endsWith(localName) || this.mDtdRootElem.charAt(oldLen - lnLen - 1) != ':')
/* 1496 */             if (prefix == null) {
/* 1497 */               wrongElem = localName;
/* 1498 */             } else if (prefix.length() == 0) {
/* 1499 */               wrongElem = "[unknown]:" + localName;
/*      */             } else {
/* 1501 */               wrongElem = prefix + ":" + localName;
/*      */             }  
/*      */         } 
/* 1505 */         if (wrongElem != null)
/* 1506 */           reportValidationProblem(ErrorConsts.ERR_VLD_WRONG_ROOT, wrongElem, this.mDtdRootElem); 
/*      */       }  
/* 1510 */     this.mState = 2;
/*      */   }
/*      */   
/*      */   protected static void throwOutputError(String msg) throws XMLStreamException {
/* 1522 */     throw new XMLStreamException(msg);
/*      */   }
/*      */   
/*      */   protected static void throwOutputError(String format, Object arg) throws XMLStreamException {
/* 1528 */     String msg = MessageFormat.format(format, new Object[] { arg });
/* 1529 */     throwOutputError(msg);
/*      */   }
/*      */   
/*      */   protected static void reportIllegalMethod(String msg) throws XMLStreamException {
/* 1539 */     throwOutputError(msg);
/*      */   }
/*      */   
/*      */   protected static void reportNwfStructure(String msg) throws XMLStreamException {
/* 1551 */     throwOutputError(msg);
/*      */   }
/*      */   
/*      */   protected static void reportNwfStructure(String msg, Object arg) throws XMLStreamException {
/* 1557 */     throwOutputError(msg, arg);
/*      */   }
/*      */   
/*      */   protected static void reportNwfContent(String msg) throws XMLStreamException {
/* 1569 */     throwOutputError(msg);
/*      */   }
/*      */   
/*      */   protected static void reportNwfContent(String msg, Object arg) throws XMLStreamException {
/* 1575 */     throwOutputError(msg, arg);
/*      */   }
/*      */   
/*      */   protected static void reportNwfAttr(String msg) throws XMLStreamException {
/* 1587 */     throwOutputError(msg);
/*      */   }
/*      */   
/*      */   protected static void reportNwfAttr(String msg, Object arg) throws XMLStreamException {
/* 1593 */     throwOutputError(msg, arg);
/*      */   }
/*      */   
/*      */   protected static void throwFromIOE(IOException ioe) throws XMLStreamException {
/* 1599 */     throw new WstxIOException(ioe);
/*      */   }
/*      */   
/*      */   protected static void reportIllegalArg(String msg) throws IllegalArgumentException {
/* 1605 */     throw new IllegalArgumentException(msg);
/*      */   }
/*      */   
/*      */   protected void reportInvalidContent(int evtType) throws XMLStreamException {
/* 1617 */     switch (this.mVldContent) {
/*      */       case 0:
/* 1619 */         reportValidationProblem(ErrorConsts.ERR_VLD_EMPTY, getTopElementDesc(), ErrorConsts.tokenTypeDesc(evtType));
/*      */         return;
/*      */       case 1:
/* 1624 */         reportValidationProblem(ErrorConsts.ERR_VLD_NON_MIXED, getTopElementDesc());
/*      */         return;
/*      */       case 3:
/*      */       case 4:
/* 1632 */         reportValidationProblem(ErrorConsts.ERR_VLD_ANY, getTopElementDesc(), ErrorConsts.tokenTypeDesc(evtType));
/*      */         return;
/*      */     } 
/* 1637 */     reportValidationProblem("Internal error: trying to report invalid content for " + evtType);
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String msg, Location loc, int severity) throws XMLStreamException {
/* 1644 */     reportProblem(new XMLValidationProblem(loc, msg, severity));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String msg, int severity) throws XMLStreamException {
/* 1650 */     reportProblem(new XMLValidationProblem(getValidationLocation(), msg, severity));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String msg) throws XMLStreamException {
/* 1657 */     reportProblem(new XMLValidationProblem(getValidationLocation(), msg, 2));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(Location loc, String msg) throws XMLStreamException {
/* 1665 */     reportProblem(new XMLValidationProblem(getValidationLocation(), msg));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String format, Object arg) throws XMLStreamException {
/* 1671 */     String msg = MessageFormat.format(format, new Object[] { arg });
/* 1672 */     reportProblem(new XMLValidationProblem(getValidationLocation(), msg));
/*      */   }
/*      */   
/*      */   public void reportValidationProblem(String format, Object arg, Object arg2) throws XMLStreamException {
/* 1679 */     String msg = MessageFormat.format(format, new Object[] { arg, arg2 });
/* 1680 */     reportProblem(new XMLValidationProblem(getValidationLocation(), msg));
/*      */   }
/*      */   
/*      */   protected void doReportProblem(XMLReporter rep, String probType, String msg, Location loc) throws XMLStreamException {
/*      */     XMLStreamLocation2 xMLStreamLocation2;
/* 1686 */     if (loc == null)
/* 1687 */       xMLStreamLocation2 = getLocation(); 
/* 1689 */     doReportProblem(rep, new XMLValidationProblem((Location)xMLStreamLocation2, msg, 2, probType));
/*      */   }
/*      */   
/*      */   protected void doReportProblem(XMLReporter rep, XMLValidationProblem prob) throws XMLStreamException {
/* 1695 */     if (rep != null) {
/*      */       XMLStreamLocation2 xMLStreamLocation2;
/* 1696 */       Location loc = prob.getLocation();
/* 1697 */       if (loc == null) {
/* 1698 */         xMLStreamLocation2 = getLocation();
/* 1699 */         prob.setLocation((Location)xMLStreamLocation2);
/*      */       } 
/* 1702 */       if (prob.getType() == null)
/* 1703 */         prob.setType(ErrorConsts.WT_VALIDATION); 
/* 1706 */       rep.report(prob.getMessage(), prob.getType(), prob, (Location)xMLStreamLocation2);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected abstract String getTopElementDesc();
/*      */   
/*      */   protected final char[] getCopyBuffer() {
/* 1723 */     char[] buf = this.mCopyBuffer;
/* 1724 */     if (buf == null)
/* 1725 */       this.mCopyBuffer = buf = this.mConfig.allocMediumCBuffer(512); 
/* 1727 */     return buf;
/*      */   }
/*      */   
/*      */   protected final char[] getCopyBuffer(int minLen) {
/* 1732 */     char[] buf = this.mCopyBuffer;
/* 1733 */     if (buf == null || minLen > buf.length)
/* 1734 */       this.mCopyBuffer = buf = this.mConfig.allocMediumCBuffer(Math.max(512, minLen)); 
/* 1736 */     return buf;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1741 */     return "[StreamWriter: " + getClass() + ", underlying outputter: " + ((this.mWriter == null) ? "NULL" : (this.mWriter.toString() + "]"));
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\BaseStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */