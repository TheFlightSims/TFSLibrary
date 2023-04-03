/*      */ package com.ctc.wstx.sax;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.dtd.DTDEventListener;
/*      */ import com.ctc.wstx.exc.WstxIOException;
/*      */ import com.ctc.wstx.io.DefaultInputResolver;
/*      */ import com.ctc.wstx.io.InputBootstrapper;
/*      */ import com.ctc.wstx.io.ReaderBootstrapper;
/*      */ import com.ctc.wstx.io.StreamBootstrapper;
/*      */ import com.ctc.wstx.sr.AttributeCollector;
/*      */ import com.ctc.wstx.sr.BasicStreamReader;
/*      */ import com.ctc.wstx.sr.InputElementStack;
/*      */ import com.ctc.wstx.stax.WstxInputFactory;
/*      */ import com.ctc.wstx.util.ExceptionUtil;
/*      */ import com.ctc.wstx.util.URLUtil;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.net.URL;
/*      */ import java.util.Locale;
/*      */ import javax.xml.parsers.SAXParser;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLResolver;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.xml.sax.AttributeList;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.DocumentHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.HandlerBase;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.Parser;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.SAXNotSupportedException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.ext.Attributes2;
/*      */ import org.xml.sax.ext.DeclHandler;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ import org.xml.sax.ext.Locator2;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ public class WstxSAXParser extends SAXParser implements Parser, XMLReader, Attributes2, Locator2, DTDEventListener {
/*      */   static final boolean FEAT_DEFAULT_NS_PREFIXES = false;
/*      */   
/*      */   protected final WstxInputFactory mStaxFactory;
/*      */   
/*      */   protected final ReaderConfig mConfig;
/*      */   
/*      */   protected boolean mFeatNsPrefixes;
/*      */   
/*      */   protected BasicStreamReader mScanner;
/*      */   
/*      */   protected AttributeCollector mAttrCollector;
/*      */   
/*      */   protected InputElementStack mElemStack;
/*      */   
/*      */   protected String mEncoding;
/*      */   
/*      */   protected String mXmlVersion;
/*      */   
/*      */   protected boolean mStandalone;
/*      */   
/*      */   protected ContentHandler mContentHandler;
/*      */   
/*      */   protected DTDHandler mDTDHandler;
/*      */   
/*      */   private EntityResolver mEntityResolver;
/*      */   
/*      */   private ErrorHandler mErrorHandler;
/*      */   
/*      */   private LexicalHandler mLexicalHandler;
/*      */   
/*      */   private DeclHandler mDeclHandler;
/*      */   
/*      */   protected int mAttrCount;
/*      */   
/*  117 */   protected int mNsCount = 0;
/*      */   
/*      */   public WstxSAXParser(WstxInputFactory sf, boolean nsPrefixes) {
/*  132 */     this.mStaxFactory = sf;
/*  133 */     this.mFeatNsPrefixes = nsPrefixes;
/*  134 */     this.mConfig = sf.createPrivateConfig();
/*  135 */     this.mConfig.doSupportDTDs(true);
/*  141 */     ResolverProxy r = new ResolverProxy();
/*  146 */     this.mConfig.setDtdResolver(r);
/*  147 */     this.mConfig.setEntityResolver(r);
/*  148 */     this.mConfig.setDTDEventListener(this);
/*      */   }
/*      */   
/*      */   public WstxSAXParser() {
/*  171 */     this(new WstxInputFactory(), false);
/*      */   }
/*      */   
/*      */   public final Parser getParser() {
/*  176 */     return this;
/*      */   }
/*      */   
/*      */   public final XMLReader getXMLReader() {
/*  181 */     return this;
/*      */   }
/*      */   
/*      */   public final ReaderConfig getStaxConfig() {
/*  191 */     return this.mConfig;
/*      */   }
/*      */   
/*      */   public boolean isNamespaceAware() {
/*  201 */     return this.mConfig.willSupportNamespaces();
/*      */   }
/*      */   
/*      */   public boolean isValidating() {
/*  205 */     return this.mConfig.willValidateWithDTD();
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
/*  211 */     SAXProperty prop = SAXProperty.findByUri(name);
/*  212 */     if (prop == SAXProperty.DECLARATION_HANDLER)
/*  213 */       return this.mDeclHandler; 
/*  214 */     if (prop == SAXProperty.DOCUMENT_XML_VERSION)
/*  215 */       return this.mXmlVersion; 
/*  216 */     if (prop == SAXProperty.DOM_NODE)
/*  217 */       return null; 
/*  218 */     if (prop == SAXProperty.LEXICAL_HANDLER)
/*  219 */       return this.mLexicalHandler; 
/*  220 */     if (prop == SAXProperty.XML_STRING)
/*  221 */       return null; 
/*  224 */     throw new SAXNotRecognizedException("Property '" + name + "' not recognized");
/*      */   }
/*      */   
/*      */   public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
/*  230 */     SAXProperty prop = SAXProperty.findByUri(name);
/*  231 */     if (prop == SAXProperty.DECLARATION_HANDLER) {
/*  232 */       this.mDeclHandler = (DeclHandler)value;
/*      */       return;
/*      */     } 
/*  234 */     if (prop != SAXProperty.DOCUMENT_XML_VERSION)
/*  236 */       if (prop != SAXProperty.DOM_NODE) {
/*  238 */         if (prop == SAXProperty.LEXICAL_HANDLER) {
/*  239 */           this.mLexicalHandler = (LexicalHandler)value;
/*      */           return;
/*      */         } 
/*  241 */         if (prop != SAXProperty.XML_STRING)
/*  244 */           throw new SAXNotRecognizedException("Property '" + name + "' not recognized"); 
/*      */       }  
/*  248 */     throw new SAXNotSupportedException("Property '" + name + "' is read-only, can not be modified");
/*      */   }
/*      */   
/*      */   public void parse(InputSource is, HandlerBase hb) throws SAXException, IOException {
/*  265 */     if (hb != null) {
/*  270 */       if (this.mContentHandler == null)
/*  271 */         setDocumentHandler(hb); 
/*  273 */       if (this.mEntityResolver == null)
/*  274 */         setEntityResolver(hb); 
/*  276 */       if (this.mErrorHandler == null)
/*  277 */         setErrorHandler(hb); 
/*  279 */       if (this.mDTDHandler == null)
/*  280 */         setDTDHandler(hb); 
/*      */     } 
/*  283 */     parse(is);
/*      */   }
/*      */   
/*      */   public void parse(InputSource is, DefaultHandler dh) throws SAXException, IOException {
/*  289 */     if (dh != null) {
/*  294 */       if (this.mContentHandler == null)
/*  295 */         setContentHandler(dh); 
/*  297 */       if (this.mEntityResolver == null)
/*  298 */         setEntityResolver(dh); 
/*  300 */       if (this.mErrorHandler == null)
/*  301 */         setErrorHandler(dh); 
/*  303 */       if (this.mDTDHandler == null)
/*  304 */         setDTDHandler(dh); 
/*      */     } 
/*  307 */     parse(is);
/*      */   }
/*      */   
/*      */   public ContentHandler getContentHandler() {
/*  318 */     return this.mContentHandler;
/*      */   }
/*      */   
/*      */   public DTDHandler getDTDHandler() {
/*  323 */     return this.mDTDHandler;
/*      */   }
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/*  328 */     return this.mEntityResolver;
/*      */   }
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/*  333 */     return this.mErrorHandler;
/*      */   }
/*      */   
/*      */   public boolean getFeature(String name) throws SAXNotRecognizedException {
/*  339 */     SAXFeature stdFeat = SAXFeature.findByUri(name);
/*  341 */     if (stdFeat == SAXFeature.EXTERNAL_GENERAL_ENTITIES)
/*  342 */       return this.mConfig.willSupportExternalEntities(); 
/*  343 */     if (stdFeat == SAXFeature.EXTERNAL_PARAMETER_ENTITIES)
/*  344 */       return this.mConfig.willSupportExternalEntities(); 
/*  345 */     if (stdFeat == SAXFeature.IS_STANDALONE)
/*  346 */       return this.mStandalone; 
/*  347 */     if (stdFeat == SAXFeature.LEXICAL_HANDLER_PARAMETER_ENTITIES)
/*  349 */       return false; 
/*  350 */     if (stdFeat == SAXFeature.NAMESPACES)
/*  351 */       return this.mConfig.willSupportNamespaces(); 
/*  352 */     if (stdFeat == SAXFeature.NAMESPACE_PREFIXES)
/*  353 */       return !this.mConfig.willSupportNamespaces(); 
/*  354 */     if (stdFeat == SAXFeature.RESOLVE_DTD_URIS)
/*  356 */       return false; 
/*  357 */     if (stdFeat == SAXFeature.STRING_INTERNING)
/*  358 */       return true; 
/*  359 */     if (stdFeat == SAXFeature.UNICODE_NORMALIZATION_CHECKING)
/*  360 */       return false; 
/*  361 */     if (stdFeat == SAXFeature.USE_ATTRIBUTES2)
/*  362 */       return true; 
/*  363 */     if (stdFeat == SAXFeature.USE_LOCATOR2)
/*  364 */       return true; 
/*  365 */     if (stdFeat == SAXFeature.USE_ENTITY_RESOLVER2)
/*  366 */       return true; 
/*  367 */     if (stdFeat == SAXFeature.VALIDATION)
/*  368 */       return this.mConfig.willValidateWithDTD(); 
/*  369 */     if (stdFeat == SAXFeature.XMLNS_URIS)
/*  373 */       return true; 
/*  374 */     if (stdFeat == SAXFeature.XML_1_1)
/*  375 */       return true; 
/*  378 */     throw new SAXNotRecognizedException("Feature '" + name + "' not recognized");
/*      */   }
/*      */   
/*      */   public void setContentHandler(ContentHandler handler) {
/*  392 */     this.mContentHandler = handler;
/*      */   }
/*      */   
/*      */   public void setDTDHandler(DTDHandler handler) {
/*  397 */     this.mDTDHandler = handler;
/*      */   }
/*      */   
/*      */   public void setEntityResolver(EntityResolver resolver) {
/*  402 */     this.mEntityResolver = resolver;
/*      */   }
/*      */   
/*      */   public void setErrorHandler(ErrorHandler handler) {
/*  407 */     this.mErrorHandler = handler;
/*      */   }
/*      */   
/*      */   public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
/*  413 */     boolean invalidValue = false;
/*  414 */     boolean readOnly = false;
/*  415 */     SAXFeature stdFeat = SAXFeature.findByUri(name);
/*  417 */     if (stdFeat == SAXFeature.EXTERNAL_GENERAL_ENTITIES) {
/*  418 */       this.mConfig.doSupportExternalEntities(value);
/*  419 */     } else if (stdFeat != SAXFeature.EXTERNAL_PARAMETER_ENTITIES) {
/*  421 */       if (stdFeat == SAXFeature.IS_STANDALONE) {
/*  422 */         readOnly = true;
/*  423 */       } else if (stdFeat != SAXFeature.LEXICAL_HANDLER_PARAMETER_ENTITIES) {
/*  425 */         if (stdFeat == SAXFeature.NAMESPACES) {
/*  426 */           this.mConfig.doSupportNamespaces(value);
/*  427 */         } else if (stdFeat == SAXFeature.NAMESPACE_PREFIXES) {
/*  428 */           this.mFeatNsPrefixes = value;
/*  429 */         } else if (stdFeat != SAXFeature.RESOLVE_DTD_URIS) {
/*  431 */           if (stdFeat == SAXFeature.STRING_INTERNING) {
/*  432 */             invalidValue = !value;
/*  433 */           } else if (stdFeat == SAXFeature.UNICODE_NORMALIZATION_CHECKING) {
/*  434 */             invalidValue = value;
/*  435 */           } else if (stdFeat == SAXFeature.USE_ATTRIBUTES2) {
/*  436 */             readOnly = true;
/*  437 */           } else if (stdFeat == SAXFeature.USE_LOCATOR2) {
/*  438 */             readOnly = true;
/*  439 */           } else if (stdFeat == SAXFeature.USE_ENTITY_RESOLVER2) {
/*  440 */             readOnly = true;
/*  441 */           } else if (stdFeat == SAXFeature.VALIDATION) {
/*  442 */             this.mConfig.doValidateWithDTD(value);
/*  443 */           } else if (stdFeat == SAXFeature.XMLNS_URIS) {
/*  444 */             invalidValue = !value;
/*  445 */           } else if (stdFeat == SAXFeature.XML_1_1) {
/*  446 */             readOnly = true;
/*      */           } else {
/*  448 */             throw new SAXNotRecognizedException("Feature '" + name + "' not recognized");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  452 */     if (readOnly)
/*  453 */       throw new SAXNotSupportedException("Feature '" + name + "' is read-only, can not be modified"); 
/*  455 */     if (invalidValue)
/*  456 */       throw new SAXNotSupportedException("Trying to set invalid value for feature '" + name + "', '" + value + "'"); 
/*      */   }
/*      */   
/*      */   public void parse(InputSource input) throws SAXException {
/*  472 */     this.mScanner = null;
/*  473 */     String systemId = input.getSystemId();
/*  474 */     ReaderConfig cfg = this.mConfig;
/*  475 */     URL srcUrl = null;
/*  478 */     InputStream is = null;
/*  479 */     Reader r = input.getCharacterStream();
/*  480 */     if (r == null) {
/*  481 */       is = input.getByteStream();
/*  482 */       if (is == null) {
/*  483 */         if (systemId == null)
/*  484 */           throw new SAXException("Invalid InputSource passed: neither character or byte stream passed, nor system id specified"); 
/*      */         try {
/*  487 */           srcUrl = URLUtil.urlFromSystemId(systemId);
/*  488 */           is = URLUtil.inputStreamFromURL(srcUrl);
/*  489 */         } catch (IOException ioe) {
/*  490 */           SAXException saxe = new SAXException(ioe);
/*  491 */           ExceptionUtil.setInitCause(saxe, ioe);
/*  492 */           throw saxe;
/*      */         } 
/*      */       } 
/*      */     } 
/*  497 */     if (this.mContentHandler != null) {
/*  498 */       this.mContentHandler.setDocumentLocator(this);
/*  499 */       this.mContentHandler.startDocument();
/*      */     } 
/*  505 */     cfg.resetState();
/*      */     try {
/*  508 */       String inputEnc = input.getEncoding();
/*  509 */       String publicId = input.getPublicId();
/*  512 */       if (r == null && inputEnc != null && inputEnc.length() > 0)
/*  513 */         r = DefaultInputResolver.constructOptimizedReader(cfg, is, false, inputEnc); 
/*  516 */       if (r != null) {
/*  517 */         ReaderBootstrapper readerBootstrapper = ReaderBootstrapper.getInstance(publicId, systemId, r, inputEnc);
/*  519 */         this.mScanner = (BasicStreamReader)this.mStaxFactory.createSR(cfg, systemId, (InputBootstrapper)readerBootstrapper, false, false);
/*      */       } else {
/*  521 */         StreamBootstrapper streamBootstrapper = StreamBootstrapper.getInstance(publicId, systemId, is);
/*  522 */         this.mScanner = (BasicStreamReader)this.mStaxFactory.createSR(cfg, systemId, (InputBootstrapper)streamBootstrapper, false, false);
/*      */       } 
/*  527 */       String enc2 = this.mScanner.getEncoding();
/*  528 */       if (enc2 == null)
/*  529 */         enc2 = this.mScanner.getCharacterEncodingScheme(); 
/*  531 */       this.mEncoding = enc2;
/*  533 */       this.mXmlVersion = this.mScanner.getVersion();
/*  534 */       this.mStandalone = this.mScanner.standaloneSet();
/*  535 */       this.mAttrCollector = this.mScanner.getAttributeCollector();
/*  536 */       this.mElemStack = this.mScanner.getInputElementStack();
/*  537 */       fireEvents();
/*  538 */     } catch (IOException io) {
/*  539 */       throwSaxException(io);
/*  540 */     } catch (XMLStreamException strex) {
/*  541 */       throwSaxException(strex);
/*      */     } finally {
/*  543 */       if (this.mContentHandler != null)
/*  544 */         this.mContentHandler.endDocument(); 
/*  549 */       if (this.mScanner != null) {
/*  550 */         BasicStreamReader sr = this.mScanner;
/*  551 */         this.mScanner = null;
/*      */         try {
/*  553 */           sr.close();
/*  554 */         } catch (XMLStreamException sex) {}
/*      */       } 
/*  556 */       if (r != null)
/*      */         try {
/*  558 */           r.close();
/*  559 */         } catch (IOException ioe) {} 
/*  561 */       if (is != null)
/*      */         try {
/*  563 */           is.close();
/*  564 */         } catch (IOException ioe) {} 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void parse(String systemId) throws SAXException {
/*  572 */     InputSource src = new InputSource(systemId);
/*  573 */     parse(src);
/*      */   }
/*      */   
/*      */   private final void fireEvents() throws IOException, SAXException, XMLStreamException {
/*  598 */     this.mConfig.doParseLazily(false);
/*      */     int type;
/*  600 */     while ((type = this.mScanner.next()) != 1)
/*  601 */       fireAuxEvent(type, false); 
/*  605 */     fireStartTag();
/*  607 */     int depth = 1;
/*      */     while (true) {
/*  609 */       type = this.mScanner.next();
/*  610 */       if (type == 1) {
/*  611 */         fireStartTag();
/*  612 */         depth++;
/*      */         continue;
/*      */       } 
/*  613 */       if (type == 2) {
/*  614 */         this.mScanner.fireSaxEndElement(this.mContentHandler);
/*  615 */         if (--depth < 1)
/*      */           break; 
/*      */         continue;
/*      */       } 
/*  618 */       if (type == 4) {
/*  619 */         this.mScanner.fireSaxCharacterEvents(this.mContentHandler);
/*      */         continue;
/*      */       } 
/*  621 */       fireAuxEvent(type, true);
/*      */     } 
/*      */     while (true) {
/*  627 */       type = this.mScanner.next();
/*  628 */       if (type == 8)
/*      */         break; 
/*  631 */       if (type == 6)
/*      */         continue; 
/*  636 */       fireAuxEvent(type, false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private final void fireAuxEvent(int type, boolean inTree) throws IOException, SAXException, XMLStreamException {
/*  643 */     switch (type) {
/*      */       case 5:
/*  645 */         this.mScanner.fireSaxCommentEvent(this.mLexicalHandler);
/*      */         return;
/*      */       case 12:
/*  648 */         if (this.mLexicalHandler != null) {
/*  649 */           this.mLexicalHandler.startCDATA();
/*  650 */           this.mScanner.fireSaxCharacterEvents(this.mContentHandler);
/*  651 */           this.mLexicalHandler.endCDATA();
/*      */         } else {
/*  653 */           this.mScanner.fireSaxCharacterEvents(this.mContentHandler);
/*      */         } 
/*      */         return;
/*      */       case 11:
/*  657 */         if (this.mLexicalHandler != null) {
/*  664 */           String rootName = this.mScanner.getDTDRootName();
/*  665 */           String sysId = this.mScanner.getDTDSystemId();
/*  666 */           String pubId = this.mScanner.getDTDPublicId();
/*  667 */           this.mLexicalHandler.startDTD(rootName, pubId, sysId);
/*      */           try {
/*  670 */             this.mScanner.getDTDInfo();
/*  671 */           } catch (WrappedSaxException wse) {
/*  672 */             throw wse.getSaxException();
/*      */           } 
/*  674 */           this.mLexicalHandler.endDTD();
/*      */         } 
/*      */         return;
/*      */       case 3:
/*  678 */         this.mScanner.fireSaxPIEvent(this.mContentHandler);
/*      */         return;
/*      */       case 6:
/*  683 */         if (inTree)
/*  684 */           this.mScanner.fireSaxSpaceEvents(this.mContentHandler); 
/*      */         return;
/*      */       case 9:
/*  691 */         if (this.mContentHandler != null)
/*  692 */           this.mContentHandler.skippedEntity(this.mScanner.getLocalName()); 
/*      */         return;
/*      */     } 
/*  696 */     if (type == 8)
/*  697 */       throwSaxException("Unexpected end-of-input in " + (inTree ? "tree" : "prolog")); 
/*  699 */     throw new RuntimeException("Internal error: unexpected type, " + type);
/*      */   }
/*      */   
/*      */   private final void fireStartTag() throws SAXException {
/*  706 */     this.mAttrCount = this.mAttrCollector.getCount();
/*  707 */     if (this.mFeatNsPrefixes)
/*  718 */       this.mNsCount = this.mElemStack.getCurrentNsCount(); 
/*  720 */     this.mScanner.fireSaxStartElement(this.mContentHandler, this);
/*      */   }
/*      */   
/*      */   public void setDocumentHandler(DocumentHandler handler) {
/*  737 */     setContentHandler(new DocHandlerWrapper(handler));
/*      */   }
/*      */   
/*      */   public void setLocale(Locale locale) {}
/*      */   
/*      */   public int getIndex(String qName) {
/*  753 */     if (this.mElemStack == null)
/*  754 */       return -1; 
/*  756 */     int ix = this.mElemStack.findAttributeIndex(null, qName);
/*  758 */     return ix;
/*      */   }
/*      */   
/*      */   public int getIndex(String uri, String localName) {
/*  763 */     if (this.mElemStack == null)
/*  764 */       return -1; 
/*  766 */     int ix = this.mElemStack.findAttributeIndex(uri, localName);
/*  768 */     return ix;
/*      */   }
/*      */   
/*      */   public int getLength() {
/*  773 */     return this.mAttrCount + this.mNsCount;
/*      */   }
/*      */   
/*      */   public String getLocalName(int index) {
/*  778 */     if (index < this.mAttrCount)
/*  779 */       return (index < 0) ? null : this.mAttrCollector.getLocalName(index); 
/*  781 */     index -= this.mAttrCount;
/*  782 */     if (index < this.mNsCount) {
/*  787 */       String prefix = this.mElemStack.getLocalNsPrefix(index);
/*  788 */       return (prefix == null || prefix.length() == 0) ? "xmlns" : prefix;
/*      */     } 
/*  791 */     return null;
/*      */   }
/*      */   
/*      */   public String getQName(int index) {
/*  796 */     if (index < this.mAttrCount) {
/*  797 */       if (index < 0)
/*  798 */         return null; 
/*  800 */       String prefix = this.mAttrCollector.getPrefix(index);
/*  801 */       String ln = this.mAttrCollector.getLocalName(index);
/*  802 */       return (prefix == null || prefix.length() == 0) ? ln : (prefix + ":" + ln);
/*      */     } 
/*  805 */     index -= this.mAttrCount;
/*  806 */     if (index < this.mNsCount) {
/*  811 */       String prefix = this.mElemStack.getLocalNsPrefix(index);
/*  812 */       if (prefix == null || prefix.length() == 0)
/*  813 */         return "xmlns"; 
/*  815 */       return "xmlns:" + prefix;
/*      */     } 
/*  817 */     return null;
/*      */   }
/*      */   
/*      */   public String getType(int index) {
/*  822 */     if (index < this.mAttrCount) {
/*  823 */       if (index < 0)
/*  824 */         return null; 
/*  830 */       String type = this.mElemStack.getAttributeType(index);
/*  832 */       if (type == "ENUMERATED")
/*  833 */         type = "NMTOKEN"; 
/*  835 */       return type;
/*      */     } 
/*  838 */     index -= this.mAttrCount;
/*  839 */     if (index < this.mNsCount)
/*  840 */       return "CDATA"; 
/*  842 */     return null;
/*      */   }
/*      */   
/*      */   public String getType(String qName) {
/*  847 */     return getType(getIndex(qName));
/*      */   }
/*      */   
/*      */   public String getType(String uri, String localName) {
/*  852 */     return getType(getIndex(uri, localName));
/*      */   }
/*      */   
/*      */   public String getURI(int index) {
/*  857 */     if (index < this.mAttrCount) {
/*  858 */       if (index < 0)
/*  859 */         return null; 
/*  861 */       String uri = this.mAttrCollector.getURI(index);
/*  862 */       return (uri == null) ? "" : uri;
/*      */     } 
/*  864 */     if (index - this.mAttrCount < this.mNsCount)
/*  865 */       return "http://www.w3.org/2000/xmlns/"; 
/*  867 */     return null;
/*      */   }
/*      */   
/*      */   public String getValue(int index) {
/*  872 */     if (index < this.mAttrCount)
/*  873 */       return (index < 0) ? null : this.mAttrCollector.getValue(index); 
/*  875 */     index -= this.mAttrCount;
/*  876 */     if (index < this.mNsCount) {
/*  881 */       String uri = this.mElemStack.getLocalNsURI(index);
/*  882 */       return (uri == null) ? "" : uri;
/*      */     } 
/*  884 */     return null;
/*      */   }
/*      */   
/*      */   public String getValue(String qName) {
/*  889 */     return getValue(getIndex(qName));
/*      */   }
/*      */   
/*      */   public String getValue(String uri, String localName) {
/*  894 */     return getValue(getIndex(uri, localName));
/*      */   }
/*      */   
/*      */   public boolean isDeclared(int index) {
/*  905 */     if (index < this.mAttrCount) {
/*  906 */       if (index >= 0)
/*  908 */         return true; 
/*      */     } else {
/*  911 */       index -= this.mAttrCount;
/*  912 */       if (index < this.mNsCount)
/*  918 */         return true; 
/*      */     } 
/*  921 */     throwNoSuchAttribute(index);
/*  922 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isDeclared(String qName) {
/*  927 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isDeclared(String uri, String localName) {
/*  932 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isSpecified(int index) {
/*  937 */     if (index < this.mAttrCount) {
/*  938 */       if (index >= 0)
/*  939 */         return this.mAttrCollector.isSpecified(index); 
/*      */     } else {
/*  942 */       index -= this.mAttrCount;
/*  943 */       if (index < this.mNsCount)
/*  948 */         return true; 
/*      */     } 
/*  951 */     throwNoSuchAttribute(index);
/*  952 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isSpecified(String qName) {
/*  957 */     int ix = getIndex(qName);
/*  958 */     if (ix < 0)
/*  959 */       throw new IllegalArgumentException("No attribute with qName '" + qName + "'"); 
/*  961 */     return isSpecified(ix);
/*      */   }
/*      */   
/*      */   public boolean isSpecified(String uri, String localName) {
/*  966 */     int ix = getIndex(uri, localName);
/*  967 */     if (ix < 0)
/*  968 */       throw new IllegalArgumentException("No attribute with uri " + uri + ", local name '" + localName + "'"); 
/*  970 */     return isSpecified(ix);
/*      */   }
/*      */   
/*      */   public int getColumnNumber() {
/*  981 */     if (this.mScanner != null) {
/*  982 */       Location loc = this.mScanner.getLocation();
/*  983 */       return loc.getColumnNumber();
/*      */     } 
/*  985 */     return -1;
/*      */   }
/*      */   
/*      */   public int getLineNumber() {
/*  990 */     if (this.mScanner != null) {
/*  991 */       Location loc = this.mScanner.getLocation();
/*  992 */       return loc.getLineNumber();
/*      */     } 
/*  994 */     return -1;
/*      */   }
/*      */   
/*      */   public String getPublicId() {
/*  999 */     if (this.mScanner != null) {
/* 1000 */       Location loc = this.mScanner.getLocation();
/* 1001 */       return loc.getPublicId();
/*      */     } 
/* 1003 */     return null;
/*      */   }
/*      */   
/*      */   public String getSystemId() {
/* 1008 */     if (this.mScanner != null) {
/* 1009 */       Location loc = this.mScanner.getLocation();
/* 1010 */       return loc.getSystemId();
/*      */     } 
/* 1012 */     return null;
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/* 1023 */     return this.mEncoding;
/*      */   }
/*      */   
/*      */   public String getXMLVersion() {
/* 1028 */     return this.mXmlVersion;
/*      */   }
/*      */   
/*      */   public boolean dtdReportComments() {
/* 1039 */     return (this.mLexicalHandler != null);
/*      */   }
/*      */   
/*      */   public void dtdComment(char[] data, int offset, int len) {
/* 1044 */     if (this.mLexicalHandler != null)
/*      */       try {
/* 1046 */         this.mLexicalHandler.comment(data, offset, len);
/* 1047 */       } catch (SAXException sex) {
/* 1048 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void dtdProcessingInstruction(String target, String data) {
/* 1055 */     if (this.mContentHandler != null)
/*      */       try {
/* 1057 */         this.mContentHandler.processingInstruction(target, data);
/* 1058 */       } catch (SAXException sex) {
/* 1059 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void dtdSkippedEntity(String name) {
/* 1066 */     if (this.mContentHandler != null)
/*      */       try {
/* 1068 */         this.mContentHandler.skippedEntity(name);
/* 1069 */       } catch (SAXException sex) {
/* 1070 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void dtdNotationDecl(String name, String publicId, String systemId, URL baseURL) throws XMLStreamException {
/* 1079 */     if (this.mDTDHandler != null) {
/* 1083 */       if (systemId != null && systemId.indexOf(':') < 0)
/*      */         try {
/* 1085 */           systemId = URLUtil.urlFromSystemId(systemId, baseURL).toExternalForm();
/* 1086 */         } catch (IOException ioe) {
/* 1087 */           throw new WstxIOException(ioe);
/*      */         }  
/*      */       try {
/* 1091 */         this.mDTDHandler.notationDecl(name, publicId, systemId);
/* 1092 */       } catch (SAXException sex) {
/* 1093 */         throw new WrappedSaxException(sex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void dtdUnparsedEntityDecl(String name, String publicId, String systemId, String notationName, URL baseURL) throws XMLStreamException {
/* 1101 */     if (this.mDTDHandler != null) {
/* 1103 */       if (systemId.indexOf(':') < 0)
/*      */         try {
/* 1105 */           systemId = URLUtil.urlFromSystemId(systemId, baseURL).toExternalForm();
/* 1106 */         } catch (IOException ioe) {
/* 1107 */           throw new WstxIOException(ioe);
/*      */         }  
/*      */       try {
/* 1111 */         this.mDTDHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
/* 1112 */       } catch (SAXException sex) {
/* 1113 */         throw new WrappedSaxException(sex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void attributeDecl(String eName, String aName, String type, String mode, String value) {
/* 1122 */     if (this.mDeclHandler != null)
/*      */       try {
/* 1124 */         this.mDeclHandler.attributeDecl(eName, aName, type, mode, value);
/* 1125 */       } catch (SAXException sex) {
/* 1126 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void dtdElementDecl(String name, String model) {
/* 1133 */     if (this.mDeclHandler != null)
/*      */       try {
/* 1135 */         this.mDeclHandler.elementDecl(name, model);
/* 1136 */       } catch (SAXException sex) {
/* 1137 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void dtdExternalEntityDecl(String name, String publicId, String systemId) {
/* 1144 */     if (this.mDeclHandler != null)
/*      */       try {
/* 1146 */         this.mDeclHandler.externalEntityDecl(name, publicId, systemId);
/* 1147 */       } catch (SAXException sex) {
/* 1148 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void dtdInternalEntityDecl(String name, String value) {
/* 1155 */     if (this.mDeclHandler != null)
/*      */       try {
/* 1157 */         this.mDeclHandler.internalEntityDecl(name, value);
/* 1158 */       } catch (SAXException sex) {
/* 1159 */         throw new WrappedSaxException(sex);
/*      */       }  
/*      */   }
/*      */   
/*      */   private void throwSaxException(Exception src) throws SAXException {
/* 1173 */     SAXParseException se = new SAXParseException(src.getMessage(), this, src);
/* 1174 */     ExceptionUtil.setInitCause(se, src);
/* 1175 */     if (this.mErrorHandler != null)
/* 1176 */       this.mErrorHandler.fatalError(se); 
/* 1178 */     throw se;
/*      */   }
/*      */   
/*      */   private void throwSaxException(String msg) throws SAXException {
/* 1184 */     SAXParseException se = new SAXParseException(msg, this);
/* 1185 */     if (this.mErrorHandler != null)
/* 1186 */       this.mErrorHandler.fatalError(se); 
/* 1188 */     throw se;
/*      */   }
/*      */   
/*      */   private void throwNoSuchAttribute(int index) {
/* 1193 */     throw new IllegalArgumentException("No attribute with index " + index + " (have " + (this.mAttrCount + this.mNsCount) + " attributes)");
/*      */   }
/*      */   
/*      */   final class ResolverProxy implements XMLResolver {
/*      */     private final WstxSAXParser this$0;
/*      */     
/*      */     public Object resolveEntity(String publicID, String systemID, String baseURI, String namespace) throws XMLStreamException {
/* 1214 */       if (WstxSAXParser.this.mEntityResolver != null)
/*      */         try {
/* 1219 */           URL url = new URL(baseURI);
/* 1220 */           String ref = (new URL(url, systemID)).toExternalForm();
/* 1221 */           InputSource isrc = WstxSAXParser.this.mEntityResolver.resolveEntity(publicID, ref);
/* 1222 */           if (isrc != null) {
/* 1224 */             InputStream in = isrc.getByteStream();
/* 1225 */             if (in != null)
/* 1226 */               return in; 
/* 1228 */             Reader r = isrc.getCharacterStream();
/* 1229 */             if (r != null)
/* 1230 */               return r; 
/*      */           } 
/* 1235 */           return null;
/* 1236 */         } catch (IOException ex) {
/* 1237 */           throw new WstxIOException(ex);
/* 1238 */         } catch (Exception ex) {
/* 1239 */           throw new XMLStreamException(ex.getMessage(), ex);
/*      */         }  
/* 1242 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class DocHandlerWrapper implements ContentHandler {
/*      */     final DocumentHandler mDocHandler;
/*      */     
/* 1258 */     final WstxSAXParser.AttributesWrapper mAttrWrapper = new WstxSAXParser.AttributesWrapper();
/*      */     
/*      */     DocHandlerWrapper(DocumentHandler h) {
/* 1262 */       this.mDocHandler = h;
/*      */     }
/*      */     
/*      */     public void characters(char[] ch, int start, int length) throws SAXException {
/* 1268 */       this.mDocHandler.characters(ch, start, length);
/*      */     }
/*      */     
/*      */     public void endDocument() throws SAXException {
/* 1273 */       this.mDocHandler.endDocument();
/*      */     }
/*      */     
/*      */     public void endElement(String uri, String localName, String qName) throws SAXException {
/* 1279 */       if (qName == null)
/* 1280 */         qName = localName; 
/* 1282 */       this.mDocHandler.endElement(qName);
/*      */     }
/*      */     
/*      */     public void endPrefixMapping(String prefix) {}
/*      */     
/*      */     public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 1293 */       this.mDocHandler.ignorableWhitespace(ch, start, length);
/*      */     }
/*      */     
/*      */     public void processingInstruction(String target, String data) throws SAXException {
/* 1299 */       this.mDocHandler.processingInstruction(target, data);
/*      */     }
/*      */     
/*      */     public void setDocumentLocator(Locator locator) {
/* 1304 */       this.mDocHandler.setDocumentLocator(locator);
/*      */     }
/*      */     
/*      */     public void skippedEntity(String name) {}
/*      */     
/*      */     public void startDocument() throws SAXException {
/* 1315 */       this.mDocHandler.startDocument();
/*      */     }
/*      */     
/*      */     public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
/* 1322 */       if (qName == null)
/* 1323 */         qName = localName; 
/* 1326 */       this.mAttrWrapper.setAttributes(attrs);
/* 1327 */       this.mDocHandler.startElement(qName, this.mAttrWrapper);
/*      */     }
/*      */     
/*      */     public void startPrefixMapping(String prefix, String uri) {}
/*      */   }
/*      */   
/*      */   static final class AttributesWrapper implements AttributeList {
/*      */     Attributes mAttrs;
/*      */     
/*      */     public void setAttributes(Attributes a) {
/* 1344 */       this.mAttrs = a;
/*      */     }
/*      */     
/*      */     public int getLength() {
/* 1349 */       return this.mAttrs.getLength();
/*      */     }
/*      */     
/*      */     public String getName(int i) {
/* 1354 */       String n = this.mAttrs.getQName(i);
/* 1355 */       return (n == null) ? this.mAttrs.getLocalName(i) : n;
/*      */     }
/*      */     
/*      */     public String getType(int i) {
/* 1360 */       return this.mAttrs.getType(i);
/*      */     }
/*      */     
/*      */     public String getType(String name) {
/* 1365 */       return this.mAttrs.getType(name);
/*      */     }
/*      */     
/*      */     public String getValue(int i) {
/* 1370 */       return this.mAttrs.getValue(i);
/*      */     }
/*      */     
/*      */     public String getValue(String name) {
/* 1375 */       return this.mAttrs.getValue(name);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sax\WstxSAXParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */