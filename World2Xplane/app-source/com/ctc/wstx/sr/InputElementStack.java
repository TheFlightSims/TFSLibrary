/*      */ package com.ctc.wstx.sr;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.cfg.ErrorConsts;
/*      */ import com.ctc.wstx.compat.QNameCreator;
/*      */ import com.ctc.wstx.dtd.DTDValidatorBase;
/*      */ import com.ctc.wstx.util.BaseNsContext;
/*      */ import com.ctc.wstx.util.EmptyNamespaceContext;
/*      */ import com.ctc.wstx.util.StringVector;
/*      */ import com.ctc.wstx.util.TextBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import javax.xml.namespace.NamespaceContext;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.codehaus.stax2.AttributeInfo;
/*      */ import org.codehaus.stax2.ri.EmptyIterator;
/*      */ import org.codehaus.stax2.ri.SingletonIterator;
/*      */ import org.codehaus.stax2.validation.ValidationContext;
/*      */ import org.codehaus.stax2.validation.ValidatorPair;
/*      */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*      */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ 
/*      */ public final class InputElementStack implements AttributeInfo, NamespaceContext, ValidationContext {
/*      */   static final int ID_ATTR_NONE = -1;
/*      */   
/*      */   protected final boolean mNsAware;
/*      */   
/*      */   protected final AttributeCollector mAttrCollector;
/*      */   
/*      */   protected final ReaderConfig mConfig;
/*      */   
/*   73 */   protected InputProblemReporter mReporter = null;
/*      */   
/*      */   protected NsDefaultProvider mNsDefaultProvider;
/*      */   
/*   88 */   protected int mDepth = 0;
/*      */   
/*   95 */   protected final StringVector mNamespaces = new StringVector(64);
/*      */   
/*      */   protected Element mCurrElement;
/*      */   
/*      */   protected boolean mMayHaveNsDefaults = false;
/*      */   
/*  116 */   protected XMLValidator mValidator = null;
/*      */   
/*  123 */   protected int mIdAttrIndex = -1;
/*      */   
/*  133 */   protected String mLastLocalName = null;
/*      */   
/*  134 */   protected String mLastPrefix = null;
/*      */   
/*  135 */   protected String mLastNsURI = null;
/*      */   
/*  137 */   protected QName mLastName = null;
/*      */   
/*  153 */   protected BaseNsContext mLastNsContext = null;
/*      */   
/*  157 */   protected Element mFreeElement = null;
/*      */   
/*      */   protected InputElementStack(ReaderConfig cfg, boolean nsAware) {
/*  167 */     this.mConfig = cfg;
/*  168 */     this.mNsAware = nsAware;
/*  169 */     this.mAttrCollector = new AttributeCollector(cfg, nsAware);
/*      */   }
/*      */   
/*      */   protected void connectReporter(InputProblemReporter rep) {
/*  174 */     this.mReporter = rep;
/*      */   }
/*      */   
/*      */   protected XMLValidator addValidator(XMLValidator vld) {
/*  179 */     if (this.mValidator == null) {
/*  180 */       this.mValidator = vld;
/*      */     } else {
/*  182 */       this.mValidator = (XMLValidator)new ValidatorPair(this.mValidator, vld);
/*      */     } 
/*  184 */     return vld;
/*      */   }
/*      */   
/*      */   protected void setAutomaticDTDValidator(XMLValidator validator, NsDefaultProvider nsDefs) {
/*  196 */     this.mNsDefaultProvider = nsDefs;
/*  197 */     addValidator(validator);
/*      */   }
/*      */   
/*      */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/*  212 */     return addValidator(schema.createValidator(this));
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/*  220 */     XMLValidator[] results = new XMLValidator[2];
/*  221 */     if (ValidatorPair.removeValidator(this.mValidator, schema, results)) {
/*  222 */       XMLValidator found = results[0];
/*  223 */       this.mValidator = results[1];
/*  224 */       found.validationCompleted(false);
/*  225 */       return found;
/*      */     } 
/*  227 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/*  233 */     XMLValidator[] results = new XMLValidator[2];
/*  234 */     if (ValidatorPair.removeValidator(this.mValidator, validator, results)) {
/*  235 */       XMLValidator found = results[0];
/*  236 */       this.mValidator = results[1];
/*  237 */       found.validationCompleted(false);
/*  238 */       return found;
/*      */     } 
/*  240 */     return null;
/*      */   }
/*      */   
/*      */   protected boolean reallyValidating() {
/*  266 */     if (this.mValidator == null)
/*  268 */       return false; 
/*  270 */     if (!(this.mValidator instanceof DTDValidatorBase))
/*  272 */       return true; 
/*  274 */     return ((DTDValidatorBase)this.mValidator).reallyValidating();
/*      */   }
/*      */   
/*      */   public final AttributeCollector getAttrCollector() {
/*  282 */     return this.mAttrCollector;
/*      */   }
/*      */   
/*      */   public BaseNsContext createNonTransientNsContext(Location loc) {
/*  293 */     if (this.mLastNsContext != null)
/*  294 */       return this.mLastNsContext; 
/*  298 */     int totalNsSize = this.mNamespaces.size();
/*  299 */     if (totalNsSize < 1)
/*  300 */       return this.mLastNsContext = (BaseNsContext)EmptyNamespaceContext.getInstance(); 
/*  304 */     int localCount = getCurrentNsCount() << 1;
/*  305 */     BaseNsContext nsCtxt = new CompactNsContext(loc, getDefaultNsURI(), this.mNamespaces.asArray(), totalNsSize, totalNsSize - localCount);
/*  313 */     if (localCount == 0)
/*  314 */       this.mLastNsContext = nsCtxt; 
/*  316 */     return nsCtxt;
/*      */   }
/*      */   
/*      */   public final void push(String prefix, String localName) {
/*  326 */     this.mDepth++;
/*  327 */     String defaultNs = (this.mCurrElement == null) ? "" : this.mCurrElement.mDefaultNsURI;
/*  330 */     if (this.mFreeElement == null) {
/*  331 */       this.mCurrElement = new Element(this.mCurrElement, this.mNamespaces.size(), prefix, localName);
/*      */     } else {
/*  333 */       Element newElem = this.mFreeElement;
/*  334 */       this.mFreeElement = newElem.mParent;
/*  335 */       newElem.reset(this.mCurrElement, this.mNamespaces.size(), prefix, localName);
/*  336 */       this.mCurrElement = newElem;
/*      */     } 
/*  338 */     this.mCurrElement.mDefaultNsURI = defaultNs;
/*  339 */     this.mAttrCollector.reset();
/*  344 */     if (this.mNsDefaultProvider != null)
/*  345 */       this.mMayHaveNsDefaults = this.mNsDefaultProvider.mayHaveNsDefaults(prefix, localName); 
/*      */   }
/*      */   
/*      */   public final boolean pop() throws XMLStreamException {
/*  360 */     if (this.mCurrElement == null)
/*  361 */       throw new IllegalStateException("Popping from empty stack"); 
/*  363 */     this.mDepth--;
/*  365 */     Element child = this.mCurrElement;
/*  366 */     Element parent = child.mParent;
/*  367 */     this.mCurrElement = parent;
/*  370 */     child.relink(this.mFreeElement);
/*  371 */     this.mFreeElement = child;
/*  374 */     int nsCount = this.mNamespaces.size() - child.mNsOffset;
/*  375 */     if (nsCount > 0) {
/*  376 */       this.mLastNsContext = null;
/*  377 */       this.mNamespaces.removeLast(nsCount);
/*      */     } 
/*  379 */     return (parent != null);
/*      */   }
/*      */   
/*      */   public int resolveAndValidateElement() throws XMLStreamException {
/*      */     String ns;
/*  393 */     if (this.mDepth == 0)
/*  394 */       throw new IllegalStateException("Calling validate() on empty stack."); 
/*  396 */     AttributeCollector ac = this.mAttrCollector;
/*  400 */     int nsCount = ac.getNsCount();
/*  401 */     if (nsCount > 0) {
/*  405 */       this.mLastNsContext = null;
/*  407 */       boolean internNsUris = this.mConfig.willInternNsURIs();
/*  408 */       for (int i = 0; i < nsCount; i++) {
/*  409 */         Attribute attribute = ac.resolveNamespaceDecl(i, internNsUris);
/*  410 */         String nsUri = attribute.mNamespaceURI;
/*  412 */         String str1 = attribute.mLocalName;
/*  418 */         if (str1 == "xmlns") {
/*  420 */           this.mReporter.throwParseError(ErrorConsts.ERR_NS_REDECL_XMLNS);
/*  421 */         } else if (str1 == "xml") {
/*  423 */           if (!nsUri.equals("http://www.w3.org/XML/1998/namespace"))
/*  424 */             this.mReporter.throwParseError(ErrorConsts.ERR_NS_REDECL_XML, nsUri, null); 
/*      */         } else {
/*  436 */           if (nsUri == null || nsUri.length() == 0)
/*  437 */             nsUri = ""; 
/*  440 */           if (str1 == null)
/*  441 */             this.mCurrElement.mDefaultNsURI = nsUri; 
/*  447 */           if (internNsUris) {
/*  448 */             if (nsUri == "http://www.w3.org/XML/1998/namespace") {
/*  449 */               this.mReporter.throwParseError(ErrorConsts.ERR_NS_REDECL_XML_URI, str1, null);
/*  450 */             } else if (nsUri == "http://www.w3.org/2000/xmlns/") {
/*  451 */               this.mReporter.throwParseError(ErrorConsts.ERR_NS_REDECL_XMLNS_URI);
/*      */             } 
/*  454 */           } else if (nsUri.equals("http://www.w3.org/XML/1998/namespace")) {
/*  455 */             this.mReporter.throwParseError(ErrorConsts.ERR_NS_REDECL_XML_URI, str1, null);
/*  456 */           } else if (nsUri.equals("http://www.w3.org/2000/xmlns/")) {
/*  457 */             this.mReporter.throwParseError(ErrorConsts.ERR_NS_REDECL_XMLNS_URI);
/*      */           } 
/*  463 */           this.mNamespaces.addStrings(str1, nsUri);
/*      */         } 
/*      */       } 
/*      */     } 
/*  472 */     if (this.mMayHaveNsDefaults)
/*  473 */       this.mNsDefaultProvider.checkNsDefaults(this); 
/*  477 */     String prefix = this.mCurrElement.mPrefix;
/*  480 */     if (prefix == null) {
/*  481 */       ns = this.mCurrElement.mDefaultNsURI;
/*  482 */     } else if (prefix == "xml") {
/*  483 */       ns = "http://www.w3.org/XML/1998/namespace";
/*      */     } else {
/*  486 */       ns = this.mNamespaces.findLastFromMap(prefix);
/*  491 */       if (ns == null || ns.length() == 0)
/*  492 */         this.mReporter.throwParseError(ErrorConsts.ERR_NS_UNDECLARED, prefix, null); 
/*      */     } 
/*  495 */     this.mCurrElement.mNamespaceURI = ns;
/*  498 */     int xmlidIx = ac.resolveNamespaces(this.mReporter, this.mNamespaces);
/*  499 */     this.mIdAttrIndex = xmlidIx;
/*  501 */     XMLValidator vld = this.mValidator;
/*  505 */     if (vld == null) {
/*  506 */       if (xmlidIx >= 0)
/*  507 */         ac.normalizeSpacesInValue(xmlidIx); 
/*  509 */       return 4;
/*      */     } 
/*  517 */     vld.validateElementStart(this.mCurrElement.mLocalName, this.mCurrElement.mNamespaceURI, this.mCurrElement.mPrefix);
/*  521 */     int attrLen = ac.getCount();
/*  522 */     if (attrLen > 0)
/*  523 */       for (int i = 0; i < attrLen; i++)
/*  524 */         ac.validateAttribute(i, this.mValidator);  
/*  531 */     return this.mValidator.validateElementAndAttributes();
/*      */   }
/*      */   
/*      */   public int validateEndElement() throws XMLStreamException {
/*  545 */     if (this.mValidator == null)
/*  546 */       return 4; 
/*  548 */     int result = this.mValidator.validateElementEnd(this.mCurrElement.mLocalName, this.mCurrElement.mNamespaceURI, this.mCurrElement.mPrefix);
/*  550 */     if (this.mDepth == 1)
/*  551 */       this.mValidator.validationCompleted(true); 
/*  553 */     return result;
/*      */   }
/*      */   
/*      */   public final int getAttributeCount() {
/*  564 */     return this.mAttrCollector.getCount();
/*      */   }
/*      */   
/*      */   public final int findAttributeIndex(String nsURI, String localName) {
/*  569 */     return this.mAttrCollector.findIndex(nsURI, localName);
/*      */   }
/*      */   
/*      */   public final int getIdAttributeIndex() {
/*  579 */     if (this.mIdAttrIndex >= 0)
/*  580 */       return this.mIdAttrIndex; 
/*  582 */     return (this.mValidator == null) ? -1 : this.mValidator.getIdAttrIndex();
/*      */   }
/*      */   
/*      */   public final int getNotationAttributeIndex() {
/*  592 */     return (this.mValidator == null) ? -1 : this.mValidator.getNotationAttrIndex();
/*      */   }
/*      */   
/*      */   public final String getNamespaceURI(String prefix) {
/*  604 */     if (prefix == null)
/*  605 */       throw new IllegalArgumentException(ErrorConsts.ERR_NULL_ARG); 
/*  607 */     if (prefix.length() == 0) {
/*  608 */       if (this.mDepth == 0)
/*  612 */         return ""; 
/*  614 */       return this.mCurrElement.mDefaultNsURI;
/*      */     } 
/*  616 */     if (prefix.equals("xml"))
/*  617 */       return "http://www.w3.org/XML/1998/namespace"; 
/*  619 */     if (prefix.equals("xmlns"))
/*  620 */       return "http://www.w3.org/2000/xmlns/"; 
/*  626 */     return this.mNamespaces.findLastNonInterned(prefix);
/*      */   }
/*      */   
/*      */   public final String getPrefix(String nsURI) {
/*  631 */     if (nsURI == null || nsURI.length() == 0)
/*  632 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/*  634 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/*  635 */       return "xml"; 
/*  637 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/*  638 */       return "xmlns"; 
/*  645 */     String prefix = null;
/*  648 */     String[] strs = this.mNamespaces.getInternalArray();
/*  649 */     int len = this.mNamespaces.size();
/*      */     int index;
/*  652 */     label26: for (index = len - 1; index > 0; index -= 2) {
/*  653 */       if (nsURI.equals(strs[index])) {
/*  655 */         prefix = strs[index - 1];
/*  656 */         for (int j = index + 1; j < len; j += 2) {
/*  657 */           if (strs[j] == prefix) {
/*  658 */             prefix = null;
/*      */             continue label26;
/*      */           } 
/*      */         } 
/*  664 */         if (prefix == null)
/*  665 */           prefix = ""; 
/*      */         break;
/*      */       } 
/*      */     } 
/*  671 */     return prefix;
/*      */   }
/*      */   
/*      */   public final Iterator getPrefixes(String nsURI) {
/*  676 */     if (nsURI == null || nsURI.length() == 0)
/*  677 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/*  679 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/*  680 */       return (Iterator)new SingletonIterator("xml"); 
/*  682 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/*  683 */       return (Iterator)new SingletonIterator("xmlns"); 
/*  687 */     String[] strs = this.mNamespaces.getInternalArray();
/*  688 */     int len = this.mNamespaces.size();
/*  689 */     ArrayList l = null;
/*      */     int index;
/*  692 */     label28: for (index = len - 1; index > 0; index -= 2) {
/*  693 */       if (nsURI.equals(strs[index])) {
/*  695 */         String prefix = strs[index - 1];
/*  696 */         for (int j = index + 1; j < len; j += 2) {
/*  697 */           if (strs[j] == prefix)
/*      */             continue label28; 
/*      */         } 
/*  702 */         if (l == null)
/*  703 */           l = new ArrayList(); 
/*  705 */         l.add(prefix);
/*      */       } 
/*      */     } 
/*  709 */     return (l == null) ? (Iterator)EmptyIterator.getInstance() : l.iterator();
/*      */   }
/*      */   
/*      */   public final String getXmlVersion() {
/*  720 */     return this.mConfig.isXml11() ? "1.1" : "1.0";
/*      */   }
/*      */   
/*      */   public String getAttributeLocalName(int index) {
/*  727 */     return getAttrCollector().getLocalName(index);
/*      */   }
/*      */   
/*      */   public String getAttributeNamespace(int index) {
/*  731 */     return getAttrCollector().getURI(index);
/*      */   }
/*      */   
/*      */   public String getAttributePrefix(int index) {
/*  735 */     return getAttrCollector().getPrefix(index);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(int index) {
/*  739 */     return getAttrCollector().getValue(index);
/*      */   }
/*      */   
/*      */   public String getAttributeValue(String nsURI, String localName) {
/*  744 */     int ix = findAttributeIndex(nsURI, localName);
/*  745 */     return (ix < 0) ? null : getAttributeValue(ix);
/*      */   }
/*      */   
/*      */   public boolean isNotationDeclared(String name) {
/*  754 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isUnparsedEntityDeclared(String name) {
/*  760 */     return false;
/*      */   }
/*      */   
/*      */   public String getBaseUri() {
/*  766 */     return null;
/*      */   }
/*      */   
/*      */   public final QName getCurrentElementName() {
/*  771 */     if (this.mDepth == 0)
/*  772 */       return null; 
/*  774 */     String prefix = this.mCurrElement.mPrefix;
/*  779 */     if (prefix == null)
/*  780 */       prefix = ""; 
/*  787 */     String nsURI = this.mCurrElement.mNamespaceURI;
/*  788 */     String ln = this.mCurrElement.mLocalName;
/*  793 */     if (ln != this.mLastLocalName) {
/*  794 */       this.mLastLocalName = ln;
/*  795 */       this.mLastPrefix = prefix;
/*  796 */       this.mLastNsURI = nsURI;
/*  797 */     } else if (prefix != this.mLastPrefix) {
/*  798 */       this.mLastPrefix = prefix;
/*  799 */       this.mLastNsURI = nsURI;
/*  800 */     } else if (nsURI != this.mLastNsURI) {
/*  801 */       this.mLastNsURI = nsURI;
/*      */     } else {
/*  803 */       return this.mLastName;
/*      */     } 
/*  805 */     QName n = QNameCreator.create(nsURI, ln, prefix);
/*  806 */     this.mLastName = n;
/*  807 */     return n;
/*      */   }
/*      */   
/*      */   public Location getValidationLocation() {
/*  815 */     return this.mReporter.getLocation();
/*      */   }
/*      */   
/*      */   public void reportProblem(XMLValidationProblem problem) throws XMLStreamException {
/*  821 */     this.mReporter.reportValidationProblem(problem);
/*      */   }
/*      */   
/*      */   public int addDefaultAttribute(String localName, String uri, String prefix, String value) {
/*  833 */     return this.mAttrCollector.addDefaultAttribute(localName, uri, prefix, value);
/*      */   }
/*      */   
/*      */   public boolean isPrefixLocallyDeclared(String internedPrefix) {
/*  844 */     if (internedPrefix != null && internedPrefix.length() == 0)
/*  845 */       internedPrefix = null; 
/*  848 */     int offset = this.mCurrElement.mNsOffset;
/*  849 */     for (int len = this.mNamespaces.size(); offset < len; offset += 2) {
/*  851 */       String thisPrefix = this.mNamespaces.getString(offset);
/*  852 */       if (thisPrefix == internedPrefix)
/*  853 */         return true; 
/*      */     } 
/*  856 */     return false;
/*      */   }
/*      */   
/*      */   public void addNsBinding(String prefix, String uri) {
/*  867 */     if (uri == null || uri.length() == 0)
/*  868 */       uri = null; 
/*  872 */     if (prefix == null || prefix.length() == 0) {
/*  873 */       prefix = null;
/*  874 */       this.mCurrElement.mDefaultNsURI = uri;
/*      */     } 
/*  876 */     this.mNamespaces.addStrings(prefix, uri);
/*      */   }
/*      */   
/*      */   public final void validateText(TextBuffer tb, boolean lastTextSegment) throws XMLStreamException {
/*  888 */     tb.validateText(this.mValidator, lastTextSegment);
/*      */   }
/*      */   
/*      */   public final void validateText(String contents, boolean lastTextSegment) throws XMLStreamException {
/*  894 */     this.mValidator.validateText(contents, lastTextSegment);
/*      */   }
/*      */   
/*      */   public final boolean isNamespaceAware() {
/*  906 */     return this.mNsAware;
/*      */   }
/*      */   
/*      */   public final boolean isEmpty() {
/*  912 */     return (this.mDepth == 0);
/*      */   }
/*      */   
/*      */   public final int getDepth() {
/*  919 */     return this.mDepth;
/*      */   }
/*      */   
/*      */   public final String getDefaultNsURI() {
/*  924 */     if (this.mDepth == 0)
/*  925 */       throw new IllegalStateException("Illegal access, empty stack."); 
/*  927 */     return this.mCurrElement.mDefaultNsURI;
/*      */   }
/*      */   
/*      */   public final String getNsURI() {
/*  931 */     if (this.mDepth == 0)
/*  932 */       throw new IllegalStateException("Illegal access, empty stack."); 
/*  934 */     return this.mCurrElement.mNamespaceURI;
/*      */   }
/*      */   
/*      */   public final String getPrefix() {
/*  938 */     if (this.mDepth == 0)
/*  939 */       throw new IllegalStateException("Illegal access, empty stack."); 
/*  941 */     return this.mCurrElement.mPrefix;
/*      */   }
/*      */   
/*      */   public final String getLocalName() {
/*  945 */     if (this.mDepth == 0)
/*  946 */       throw new IllegalStateException("Illegal access, empty stack."); 
/*  948 */     return this.mCurrElement.mLocalName;
/*      */   }
/*      */   
/*      */   public final boolean matches(String prefix, String localName) {
/*  953 */     if (this.mDepth == 0)
/*  954 */       throw new IllegalStateException("Illegal access, empty stack."); 
/*  956 */     String thisPrefix = this.mCurrElement.mPrefix;
/*  957 */     if (prefix == null || prefix.length() == 0) {
/*  958 */       if (thisPrefix != null && thisPrefix.length() > 0)
/*  959 */         return false; 
/*  962 */     } else if (thisPrefix != prefix && !thisPrefix.equals(prefix)) {
/*  963 */       return false;
/*      */     } 
/*  967 */     String thisName = this.mCurrElement.mLocalName;
/*  968 */     return (thisName == localName || thisName.equals(localName));
/*      */   }
/*      */   
/*      */   public final String getTopElementDesc() {
/*  973 */     if (this.mDepth == 0)
/*  974 */       throw new IllegalStateException("Illegal access, empty stack."); 
/*  976 */     String name = this.mCurrElement.mLocalName;
/*  977 */     String prefix = this.mCurrElement.mPrefix;
/*  978 */     if (prefix == null)
/*  979 */       return name; 
/*  981 */     return prefix + ":" + name;
/*      */   }
/*      */   
/*      */   public final int getTotalNsCount() {
/*  991 */     return this.mNamespaces.size() >> 1;
/*      */   }
/*      */   
/*      */   public final int getCurrentNsCount() {
/* 1001 */     return this.mNamespaces.size() - this.mCurrElement.mNsOffset >> 1;
/*      */   }
/*      */   
/*      */   public final String getLocalNsPrefix(int index) {
/* 1006 */     int offset = this.mCurrElement.mNsOffset;
/* 1007 */     int localCount = this.mNamespaces.size() - offset;
/* 1008 */     index <<= 1;
/* 1009 */     if (index < 0 || index >= localCount)
/* 1010 */       throwIllegalIndex(index >> 1, localCount >> 1); 
/* 1012 */     return this.mNamespaces.getString(offset + index);
/*      */   }
/*      */   
/*      */   public final String getLocalNsURI(int index) {
/* 1017 */     int offset = this.mCurrElement.mNsOffset;
/* 1018 */     int localCount = this.mNamespaces.size() - offset;
/* 1019 */     index <<= 1;
/* 1020 */     if (index < 0 || index >= localCount)
/* 1021 */       throwIllegalIndex(index >> 1, localCount >> 1); 
/* 1023 */     return this.mNamespaces.getString(offset + index + 1);
/*      */   }
/*      */   
/*      */   private void throwIllegalIndex(int index, int localCount) {
/* 1028 */     throw new IllegalArgumentException("Illegal namespace index " + (index >> 1) + "; current scope only has " + (localCount >> 1) + " namespace declarations.");
/*      */   }
/*      */   
/*      */   public final String getAttributeType(int index) {
/* 1043 */     if (index == this.mIdAttrIndex && index >= 0)
/* 1044 */       return "ID"; 
/* 1046 */     return (this.mValidator == null) ? "CDATA" : this.mValidator.getAttributeType(index);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\InputElementStack.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */