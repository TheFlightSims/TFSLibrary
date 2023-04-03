/*      */ package com.ctc.wstx.sr;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.cfg.ErrorConsts;
/*      */ import com.ctc.wstx.sw.XmlWriter;
/*      */ import com.ctc.wstx.util.DataUtil;
/*      */ import com.ctc.wstx.util.InternCache;
/*      */ import com.ctc.wstx.util.StringUtil;
/*      */ import com.ctc.wstx.util.StringVector;
/*      */ import com.ctc.wstx.util.TextBuilder;
/*      */ import java.io.IOException;
/*      */ import java.util.Arrays;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.codehaus.stax2.ri.typed.CharArrayBase64Decoder;
/*      */ import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
/*      */ import org.codehaus.stax2.typed.Base64Variant;
/*      */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*      */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*      */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ 
/*      */ public final class AttributeCollector {
/*      */   static final int INT_SPACE = 32;
/*      */   
/*      */   protected static final int LONG_ATTR_LIST_LEN = 4;
/*      */   
/*      */   protected static final int EXP_ATTR_COUNT = 12;
/*      */   
/*      */   protected static final int EXP_NS_COUNT = 6;
/*      */   
/*      */   protected static final int XMLID_IX_DISABLED = -2;
/*      */   
/*      */   protected static final int XMLID_IX_NONE = -1;
/*      */   
/*   77 */   protected static final InternCache sInternCache = InternCache.getInstance();
/*      */   
/*      */   final String mXmlIdPrefix;
/*      */   
/*      */   final String mXmlIdLocalName;
/*      */   
/*      */   protected Attribute[] mAttributes;
/*      */   
/*      */   protected int mAttrCount;
/*      */   
/*      */   protected int mNonDefCount;
/*      */   
/*      */   protected Attribute[] mNamespaces;
/*      */   
/*      */   protected int mNsCount;
/*      */   
/*      */   protected boolean mDefaultNsDeclared = false;
/*      */   
/*      */   protected int mXmlIdAttrIndex;
/*      */   
/*  150 */   protected TextBuilder mValueBuilder = null;
/*      */   
/*  156 */   private final TextBuilder mNamespaceBuilder = new TextBuilder(6);
/*      */   
/*  180 */   protected int[] mAttrMap = null;
/*      */   
/*      */   protected int mAttrHashSize;
/*      */   
/*      */   protected int mAttrSpillEnd;
/*      */   
/*      */   protected AttributeCollector(ReaderConfig cfg, boolean nsAware) {
/*  202 */     this.mXmlIdAttrIndex = cfg.willDoXmlIdTyping() ? -1 : -2;
/*  203 */     if (nsAware) {
/*  204 */       this.mXmlIdPrefix = "xml";
/*  205 */       this.mXmlIdLocalName = "id";
/*      */     } else {
/*  207 */       this.mXmlIdPrefix = null;
/*  208 */       this.mXmlIdLocalName = "xml:id";
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reset() {
/*  224 */     if (this.mNsCount > 0) {
/*  225 */       this.mNamespaceBuilder.reset();
/*  226 */       this.mDefaultNsDeclared = false;
/*  227 */       this.mNsCount = 0;
/*      */     } 
/*  235 */     if (this.mAttrCount > 0) {
/*  236 */       this.mValueBuilder.reset();
/*  237 */       this.mAttrCount = 0;
/*  238 */       if (this.mXmlIdAttrIndex >= 0)
/*  239 */         this.mXmlIdAttrIndex = -1; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void normalizeSpacesInValue(int index) {
/*  258 */     char[] attrCB = this.mValueBuilder.getCharBuffer();
/*  259 */     String normValue = StringUtil.normalizeSpaces(attrCB, getValueStartOffset(index), getValueStartOffset(index + 1));
/*  261 */     if (normValue != null)
/*  262 */       this.mAttributes[index].setValue(normValue); 
/*      */   }
/*      */   
/*      */   protected int getNsCount() {
/*  277 */     return this.mNsCount;
/*      */   }
/*      */   
/*      */   public boolean hasDefaultNs() {
/*  281 */     return this.mDefaultNsDeclared;
/*      */   }
/*      */   
/*      */   public final int getCount() {
/*  287 */     return this.mAttrCount;
/*      */   }
/*      */   
/*      */   public int getSpecifiedCount() {
/*  296 */     return this.mNonDefCount;
/*      */   }
/*      */   
/*      */   public String getNsPrefix(int index) {
/*  300 */     if (index < 0 || index >= this.mNsCount)
/*  301 */       throwIndex(index); 
/*  304 */     return (this.mNamespaces[index]).mLocalName;
/*      */   }
/*      */   
/*      */   public String getNsURI(int index) {
/*  308 */     if (index < 0 || index >= this.mNsCount)
/*  309 */       throwIndex(index); 
/*  311 */     return (this.mNamespaces[index]).mNamespaceURI;
/*      */   }
/*      */   
/*      */   public String getPrefix(int index) {
/*  317 */     if (index < 0 || index >= this.mAttrCount)
/*  318 */       throwIndex(index); 
/*  320 */     return (this.mAttributes[index]).mPrefix;
/*      */   }
/*      */   
/*      */   public String getLocalName(int index) {
/*  324 */     if (index < 0 || index >= this.mAttrCount)
/*  325 */       throwIndex(index); 
/*  327 */     return (this.mAttributes[index]).mLocalName;
/*      */   }
/*      */   
/*      */   public String getURI(int index) {
/*  331 */     if (index < 0 || index >= this.mAttrCount)
/*  332 */       throwIndex(index); 
/*  334 */     return (this.mAttributes[index]).mNamespaceURI;
/*      */   }
/*      */   
/*      */   public QName getQName(int index) {
/*  338 */     if (index < 0 || index >= this.mAttrCount)
/*  339 */       throwIndex(index); 
/*  341 */     return this.mAttributes[index].getQName();
/*      */   }
/*      */   
/*      */   public final String getValue(int index) {
/*  354 */     if (index < 0 || index >= this.mAttrCount)
/*  355 */       throwIndex(index); 
/*  357 */     String full = this.mValueBuilder.getAllValues();
/*  358 */     Attribute attr = this.mAttributes[index];
/*  359 */     index++;
/*  360 */     if (index < this.mAttrCount) {
/*  361 */       int endOffset = (this.mAttributes[index]).mValueStartOffset;
/*  362 */       return attr.getValue(full, endOffset);
/*      */     } 
/*  365 */     return attr.getValue(full);
/*      */   }
/*      */   
/*      */   public String getValue(String nsURI, String localName) {
/*  371 */     int hashSize = this.mAttrHashSize;
/*  372 */     if (hashSize == 0)
/*  373 */       return null; 
/*  375 */     int hash = localName.hashCode();
/*  376 */     if (nsURI != null)
/*  377 */       if (nsURI.length() == 0) {
/*  378 */         nsURI = null;
/*      */       } else {
/*  380 */         hash ^= nsURI.hashCode();
/*      */       }  
/*  383 */     int ix = this.mAttrMap[hash & hashSize - 1];
/*  384 */     if (ix == 0)
/*  385 */       return null; 
/*  387 */     ix--;
/*  390 */     if (this.mAttributes[ix].hasQName(nsURI, localName))
/*  391 */       return getValue(ix); 
/*  397 */     for (int i = hashSize, len = this.mAttrSpillEnd; i < len; i += 2) {
/*  398 */       if (this.mAttrMap[i] == hash) {
/*  404 */         ix = this.mAttrMap[i + 1];
/*  405 */         if (this.mAttributes[ix].hasQName(nsURI, localName))
/*  406 */           return getValue(ix); 
/*      */       } 
/*      */     } 
/*  410 */     return null;
/*      */   }
/*      */   
/*      */   public int findIndex(String localName) {
/*  414 */     return findIndex(null, localName);
/*      */   }
/*      */   
/*      */   public int findIndex(String nsURI, String localName) {
/*  426 */     int hashSize = this.mAttrHashSize;
/*  427 */     if (hashSize == 0)
/*  428 */       return -1; 
/*  430 */     int hash = localName.hashCode();
/*  431 */     if (nsURI != null)
/*  432 */       if (nsURI.length() == 0) {
/*  433 */         nsURI = null;
/*      */       } else {
/*  435 */         hash ^= nsURI.hashCode();
/*      */       }  
/*  438 */     int ix = this.mAttrMap[hash & hashSize - 1];
/*  439 */     if (ix == 0)
/*  440 */       return -1; 
/*  442 */     ix--;
/*  445 */     if (this.mAttributes[ix].hasQName(nsURI, localName))
/*  446 */       return ix; 
/*  452 */     for (int i = hashSize, len = this.mAttrSpillEnd; i < len; i += 2) {
/*  453 */       if (this.mAttrMap[i] == hash) {
/*  459 */         ix = this.mAttrMap[i + 1];
/*  460 */         if (this.mAttributes[ix].hasQName(nsURI, localName))
/*  461 */           return ix; 
/*      */       } 
/*      */     } 
/*  464 */     return -1;
/*      */   }
/*      */   
/*      */   public final boolean isSpecified(int index) {
/*  468 */     return (index < this.mNonDefCount);
/*      */   }
/*      */   
/*      */   public final int getXmlIdAttrIndex() {
/*  472 */     return this.mXmlIdAttrIndex;
/*      */   }
/*      */   
/*      */   public final void decodeValue(int index, TypedValueDecoder tvd) throws IllegalArgumentException {
/*  489 */     if (index < 0 || index >= this.mAttrCount)
/*  490 */       throwIndex(index); 
/*  496 */     char[] buf = this.mValueBuilder.getCharBuffer();
/*  497 */     int start = (this.mAttributes[index]).mValueStartOffset;
/*  498 */     int end = getValueStartOffset(index + 1);
/*      */     while (true) {
/*  501 */       if (start >= end) {
/*  502 */         tvd.handleEmptyValue();
/*      */         return;
/*      */       } 
/*  505 */       if (!StringUtil.isSpace(buf[start]))
/*      */         break; 
/*  508 */       start++;
/*      */     } 
/*  511 */     while (--end > start && StringUtil.isSpace(buf[end]));
/*  512 */     tvd.decode(buf, start, end + 1);
/*      */   }
/*      */   
/*      */   public final int decodeValues(int index, TypedArrayDecoder tad, InputProblemReporter rep) throws XMLStreamException {
/*  525 */     if (index < 0 || index >= this.mAttrCount)
/*  526 */       throwIndex(index); 
/*  529 */     return decodeValues(tad, rep, this.mValueBuilder.getCharBuffer(), (this.mAttributes[index]).mValueStartOffset, getValueStartOffset(index + 1));
/*      */   }
/*      */   
/*      */   public final byte[] decodeBinary(int index, Base64Variant v, CharArrayBase64Decoder dec, InputProblemReporter rep) throws XMLStreamException {
/*  539 */     if (index < 0 || index >= this.mAttrCount)
/*  540 */       throwIndex(index); 
/*  545 */     Attribute attr = this.mAttributes[index];
/*  546 */     char[] cbuf = this.mValueBuilder.getCharBuffer();
/*  547 */     int start = attr.mValueStartOffset;
/*  548 */     int end = getValueStartOffset(index + 1);
/*  549 */     int len = end - start;
/*  550 */     dec.init(v, true, cbuf, start, len, null);
/*      */     try {
/*  552 */       return dec.decodeCompletely();
/*  553 */     } catch (IllegalArgumentException iae) {
/*  555 */       String lexical = new String(cbuf, start, len);
/*  556 */       throw new TypedXMLStreamException(lexical, iae.getMessage(), rep.getLocation(), iae);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final int decodeValues(TypedArrayDecoder tad, InputProblemReporter rep, char[] buf, int ptr, int end) throws XMLStreamException {
/*  565 */     int start = ptr;
/*  566 */     int count = 0;
/*      */     try {
/*  570 */       while (ptr < end) {
/*  572 */         while (buf[ptr] <= ' ') {
/*  573 */           if (++ptr >= end)
/*      */             // Byte code: goto -> 140 
/*      */         } 
/*  578 */         start = ptr;
/*  579 */         ptr++;
/*  580 */         while (ptr < end && buf[ptr] > ' ')
/*  581 */           ptr++; 
/*  583 */         int tokenEnd = ptr;
/*  584 */         ptr++;
/*  586 */         count++;
/*  587 */         if (tad.decodeValue(buf, start, tokenEnd) && 
/*  588 */           !checkExpand(tad))
/*      */           break; 
/*      */       } 
/*  593 */     } catch (IllegalArgumentException iae) {
/*  595 */       Location loc = rep.getLocation();
/*  596 */       String lexical = new String(buf, start, ptr - start);
/*  597 */       throw new TypedXMLStreamException(lexical, iae.getMessage(), loc, iae);
/*      */     } 
/*  599 */     return count;
/*      */   }
/*      */   
/*      */   private static final boolean checkExpand(TypedArrayDecoder tad) {
/*  610 */     if (tad instanceof ValueDecoderFactory.BaseArrayDecoder) {
/*  611 */       ((ValueDecoderFactory.BaseArrayDecoder)tad).expand();
/*  612 */       return true;
/*      */     } 
/*  614 */     return false;
/*      */   }
/*      */   
/*      */   protected int getValueStartOffset(int index) {
/*  630 */     if (index < this.mAttrCount)
/*  631 */       return (this.mAttributes[index]).mValueStartOffset; 
/*  633 */     return this.mValueBuilder.getCharSize();
/*      */   }
/*      */   
/*      */   protected char[] getSharedValueBuffer() {
/*  638 */     return this.mValueBuilder.getCharBuffer();
/*      */   }
/*      */   
/*      */   protected Attribute resolveNamespaceDecl(int index, boolean internURI) {
/*      */     String uri;
/*  649 */     Attribute ns = this.mNamespaces[index];
/*  650 */     String full = this.mNamespaceBuilder.getAllValues();
/*  653 */     if (this.mNsCount == 0) {
/*  654 */       uri = full;
/*      */     } else {
/*  656 */       index++;
/*  657 */       if (index < this.mNsCount) {
/*  658 */         int endOffset = (this.mNamespaces[index]).mValueStartOffset;
/*  659 */         uri = ns.getValue(full, endOffset);
/*      */       } else {
/*  661 */         uri = ns.getValue(full);
/*      */       } 
/*      */     } 
/*  664 */     if (internURI && uri.length() > 0)
/*  665 */       uri = sInternCache.intern(uri); 
/*  667 */     ns.mNamespaceURI = uri;
/*  668 */     return ns;
/*      */   }
/*      */   
/*      */   public ElemAttrs buildAttrOb() {
/*  678 */     int count = this.mAttrCount;
/*  679 */     if (count == 0)
/*  680 */       return null; 
/*  685 */     String[] raw = new String[count << 2];
/*  686 */     for (int i = 0; i < count; i++) {
/*  687 */       Attribute attr = this.mAttributes[i];
/*  688 */       int ix = i << 2;
/*  689 */       raw[ix] = attr.mLocalName;
/*  690 */       raw[ix + 1] = attr.mNamespaceURI;
/*  691 */       raw[ix + 2] = attr.mPrefix;
/*  692 */       raw[ix + 3] = getValue(i);
/*      */     } 
/*  696 */     if (count < 4)
/*  697 */       return new ElemAttrs(raw, this.mNonDefCount); 
/*  704 */     int amapLen = this.mAttrMap.length;
/*  705 */     int[] amap = new int[amapLen];
/*  707 */     System.arraycopy(this.mAttrMap, 0, amap, 0, amapLen);
/*  708 */     return new ElemAttrs(raw, this.mNonDefCount, amap, this.mAttrHashSize, this.mAttrSpillEnd);
/*      */   }
/*      */   
/*      */   protected void validateAttribute(int index, XMLValidator vld) throws XMLStreamException {
/*  715 */     Attribute attr = this.mAttributes[index];
/*  716 */     String normValue = vld.validateAttribute(attr.mLocalName, attr.mNamespaceURI, attr.mPrefix, this.mValueBuilder.getCharBuffer(), getValueStartOffset(index), getValueStartOffset(index + 1));
/*  722 */     if (normValue != null)
/*  723 */       attr.setValue(normValue); 
/*      */   }
/*      */   
/*      */   public final TextBuilder getAttrBuilder(String attrPrefix, String attrLocalName) {
/*  744 */     if (this.mAttrCount == 0) {
/*  745 */       if (this.mAttributes == null)
/*  746 */         allocBuffers(); 
/*  748 */       this.mAttributes[0] = new Attribute(attrPrefix, attrLocalName, 0);
/*      */     } else {
/*  750 */       int valueStart = this.mValueBuilder.getCharSize();
/*  751 */       if (this.mAttrCount >= this.mAttributes.length)
/*  752 */         this.mAttributes = (Attribute[])DataUtil.growArrayBy50Pct(this.mAttributes); 
/*  754 */       Attribute curr = this.mAttributes[this.mAttrCount];
/*  755 */       if (curr == null) {
/*  756 */         this.mAttributes[this.mAttrCount] = new Attribute(attrPrefix, attrLocalName, valueStart);
/*      */       } else {
/*  758 */         curr.reset(attrPrefix, attrLocalName, valueStart);
/*      */       } 
/*      */     } 
/*  761 */     this.mAttrCount++;
/*  763 */     if (attrLocalName == this.mXmlIdLocalName && 
/*  764 */       attrPrefix == this.mXmlIdPrefix && 
/*  765 */       this.mXmlIdAttrIndex != -2)
/*  766 */       this.mXmlIdAttrIndex = this.mAttrCount - 1; 
/*  773 */     return this.mValueBuilder;
/*      */   }
/*      */   
/*      */   public int addDefaultAttribute(String localName, String uri, String prefix, String value) {
/*  786 */     int attrIndex = this.mAttrCount;
/*  787 */     if (attrIndex < 1)
/*  791 */       initHashArea(); 
/*  798 */     int hash = localName.hashCode();
/*  799 */     if (uri != null && uri.length() > 0)
/*  800 */       hash ^= uri.hashCode(); 
/*  802 */     int index = hash & this.mAttrHashSize - 1;
/*  803 */     int[] map = this.mAttrMap;
/*  804 */     if (map[index] == 0) {
/*  805 */       map[index] = attrIndex + 1;
/*      */     } else {
/*  807 */       int currIndex = map[index] - 1;
/*  808 */       int spillIndex = this.mAttrSpillEnd;
/*  809 */       map = spillAttr(uri, localName, map, currIndex, spillIndex, attrIndex, hash, this.mAttrHashSize);
/*  811 */       if (map == null)
/*  812 */         return -1; 
/*  814 */       map[++spillIndex] = attrIndex;
/*  815 */       this.mAttrMap = map;
/*  816 */       this.mAttrSpillEnd = ++spillIndex;
/*      */     } 
/*  822 */     getAttrBuilder(prefix, localName);
/*  823 */     Attribute attr = this.mAttributes[this.mAttrCount - 1];
/*  824 */     attr.mNamespaceURI = uri;
/*  825 */     attr.setValue(value);
/*  827 */     return this.mAttrCount - 1;
/*      */   }
/*      */   
/*      */   public final void setNormalizedValue(int index, String value) {
/*  839 */     this.mAttributes[index].setValue(value);
/*      */   }
/*      */   
/*      */   public TextBuilder getDefaultNsBuilder() {
/*  848 */     if (this.mDefaultNsDeclared)
/*  849 */       return null; 
/*  851 */     this.mDefaultNsDeclared = true;
/*  852 */     return getNsBuilder(null);
/*      */   }
/*      */   
/*      */   public TextBuilder getNsBuilder(String prefix) {
/*  862 */     if (this.mNsCount == 0) {
/*  863 */       if (this.mNamespaces == null)
/*  864 */         this.mNamespaces = new Attribute[6]; 
/*  866 */       this.mNamespaces[0] = new Attribute(null, prefix, 0);
/*      */     } else {
/*  868 */       int len = this.mNsCount;
/*  872 */       if (prefix != null)
/*  873 */         for (int i = 0; i < len; i++) {
/*  875 */           if (prefix == (this.mNamespaces[i]).mLocalName)
/*  876 */             return null; 
/*      */         }  
/*  880 */       if (len >= this.mNamespaces.length)
/*  881 */         this.mNamespaces = (Attribute[])DataUtil.growArrayBy50Pct(this.mNamespaces); 
/*  883 */       int uriStart = this.mNamespaceBuilder.getCharSize();
/*  884 */       Attribute curr = this.mNamespaces[len];
/*  885 */       if (curr == null) {
/*  886 */         this.mNamespaces[len] = new Attribute(null, prefix, uriStart);
/*      */       } else {
/*  888 */         curr.reset(null, prefix, uriStart);
/*      */       } 
/*      */     } 
/*  891 */     this.mNsCount++;
/*  892 */     return this.mNamespaceBuilder;
/*      */   }
/*      */   
/*      */   public int resolveNamespaces(InputProblemReporter rep, StringVector ns) throws XMLStreamException {
/*  908 */     int attrCount = this.mAttrCount;
/*  913 */     this.mNonDefCount = attrCount;
/*  915 */     if (attrCount < 1) {
/*  917 */       this.mAttrHashSize = this.mAttrSpillEnd = 0;
/*  919 */       return this.mXmlIdAttrIndex;
/*      */     } 
/*  921 */     for (int i = 0; i < attrCount; i++) {
/*  922 */       Attribute attr = this.mAttributes[i];
/*  923 */       String prefix = attr.mPrefix;
/*  925 */       if (prefix != null)
/*  926 */         if (prefix == "xml") {
/*  927 */           attr.mNamespaceURI = "http://www.w3.org/XML/1998/namespace";
/*      */         } else {
/*  929 */           String uri = ns.findLastFromMap(prefix);
/*  930 */           if (uri == null)
/*  931 */             rep.throwParseError(ErrorConsts.ERR_NS_UNDECLARED_FOR_ATTR, prefix, attr.mLocalName); 
/*  934 */           attr.mNamespaceURI = uri;
/*      */         }  
/*      */     } 
/*  943 */     int[] map = this.mAttrMap;
/*  948 */     int hashCount = 4;
/*  950 */     int min = attrCount + (attrCount >> 2);
/*  954 */     while (hashCount < min)
/*  955 */       hashCount += hashCount; 
/*  958 */     this.mAttrHashSize = hashCount;
/*  959 */     min = hashCount + (hashCount >> 4);
/*  960 */     if (map == null || map.length < min) {
/*  961 */       map = new int[min];
/*      */     } else {
/*  967 */       Arrays.fill(map, 0, hashCount, 0);
/*      */     } 
/*  972 */     int mask = hashCount - 1;
/*  973 */     int spillIndex = hashCount;
/*  976 */     for (int j = 0; j < attrCount; j++) {
/*  977 */       Attribute attr = this.mAttributes[j];
/*  978 */       String name = attr.mLocalName;
/*  979 */       int hash = name.hashCode();
/*  980 */       String uri = attr.mNamespaceURI;
/*  981 */       if (uri != null)
/*  982 */         hash ^= uri.hashCode(); 
/*  984 */       int index = hash & mask;
/*  986 */       if (map[index] == 0) {
/*  987 */         map[index] = j + 1;
/*      */       } else {
/*  989 */         int currIndex = map[index] - 1;
/*  994 */         map = spillAttr(uri, name, map, currIndex, spillIndex, attrCount, hash, hashCount);
/*  996 */         if (map == null) {
/*  997 */           throwDupAttr(rep, currIndex);
/*      */         } else {
/* 1000 */           map[++spillIndex] = j;
/* 1001 */           spillIndex++;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1005 */     this.mAttrSpillEnd = spillIndex;
/* 1007 */     this.mAttrMap = map;
/* 1008 */     return this.mXmlIdAttrIndex;
/*      */   }
/*      */   
/*      */   protected void throwIndex(int index) {
/* 1018 */     throw new IllegalArgumentException("Invalid index " + index + "; current element has only " + getCount() + " attributes");
/*      */   }
/*      */   
/*      */   public void writeAttribute(int index, XmlWriter xw) throws IOException, XMLStreamException {
/* 1032 */     Attribute attr = this.mAttributes[index];
/* 1033 */     String ln = attr.mLocalName;
/* 1034 */     String prefix = attr.mPrefix;
/* 1035 */     if (prefix == null || prefix.length() == 0) {
/* 1036 */       xw.writeAttribute(ln, getValue(index));
/*      */     } else {
/* 1038 */       xw.writeAttribute(prefix, ln, getValue(index));
/*      */     } 
/*      */   }
/*      */   
/*      */   protected final void allocBuffers() {
/* 1048 */     if (this.mAttributes == null)
/* 1049 */       this.mAttributes = new Attribute[8]; 
/* 1051 */     if (this.mValueBuilder == null)
/* 1052 */       this.mValueBuilder = new TextBuilder(12); 
/*      */   }
/*      */   
/*      */   private int[] spillAttr(String uri, String name, int[] map, int currIndex, int spillIndex, int attrCount, int hash, int hashCount) {
/* 1074 */     Attribute oldAttr = this.mAttributes[currIndex];
/* 1075 */     if (oldAttr.mLocalName == name) {
/* 1077 */       String currURI = oldAttr.mNamespaceURI;
/* 1078 */       if (currURI == uri || (currURI != null && currURI.equals(uri)))
/* 1079 */         return null; 
/*      */     } 
/* 1086 */     if (spillIndex + 1 >= map.length)
/* 1088 */       map = DataUtil.growArrayBy(map, 8); 
/* 1091 */     for (int j = hashCount; j < spillIndex; j += 2) {
/* 1092 */       if (map[j] == hash) {
/* 1093 */         currIndex = map[j + 1];
/* 1094 */         Attribute attr = this.mAttributes[currIndex];
/* 1095 */         if (oldAttr.mLocalName == name) {
/* 1096 */           String currURI = attr.mNamespaceURI;
/* 1097 */           if (currURI == uri || (currURI != null && currURI.equals(uri)))
/* 1098 */             return null; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1103 */     map[spillIndex] = hash;
/* 1104 */     return map;
/*      */   }
/*      */   
/*      */   private void initHashArea() {
/* 1118 */     this.mAttrHashSize = this.mAttrSpillEnd = 4;
/* 1119 */     if (this.mAttrMap == null || this.mAttrMap.length < this.mAttrHashSize)
/* 1120 */       this.mAttrMap = new int[this.mAttrHashSize + 1]; 
/* 1122 */     this.mAttrMap[3] = 0;
/* 1122 */     this.mAttrMap[2] = 0;
/* 1122 */     this.mAttrMap[1] = 0;
/* 1122 */     this.mAttrMap[0] = 0;
/* 1123 */     allocBuffers();
/*      */   }
/*      */   
/*      */   protected void throwDupAttr(InputProblemReporter rep, int index) throws XMLStreamException {
/* 1143 */     rep.throwParseError("Duplicate attribute '" + getQName(index) + "'.");
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\AttributeCollector.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */