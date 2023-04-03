/*      */ package org.codehaus.stax2.ri;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import javax.xml.namespace.NamespaceContext;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import javax.xml.stream.XMLStreamReader;
/*      */ import javax.xml.stream.util.StreamReaderDelegate;
/*      */ import org.codehaus.stax2.AttributeInfo;
/*      */ import org.codehaus.stax2.DTDInfo;
/*      */ import org.codehaus.stax2.LocationInfo;
/*      */ import org.codehaus.stax2.XMLStreamLocation2;
/*      */ import org.codehaus.stax2.XMLStreamReader2;
/*      */ import org.codehaus.stax2.ri.typed.StringBase64Decoder;
/*      */ import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
/*      */ import org.codehaus.stax2.typed.Base64Variant;
/*      */ import org.codehaus.stax2.typed.Base64Variants;
/*      */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*      */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*      */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*      */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*      */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*      */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ 
/*      */ public class Stax2ReaderAdapter extends StreamReaderDelegate implements XMLStreamReader2, AttributeInfo, DTDInfo, LocationInfo {
/*      */   static final int INT_SPACE = 32;
/*      */   
/*      */   private static final int MASK_GET_ELEMENT_TEXT = 4688;
/*      */   
/*      */   protected static final int MASK_TYPED_ACCESS_BINARY = 4178;
/*      */   
/*      */   protected ValueDecoderFactory _decoderFactory;
/*      */   
/*   72 */   protected StringBase64Decoder _base64Decoder = null;
/*      */   
/*   77 */   protected int _depth = 0;
/*      */   
/*      */   protected String _typedContent;
/*      */   
/*      */   protected Stax2ReaderAdapter(XMLStreamReader sr) {
/*   94 */     super(sr);
/*      */   }
/*      */   
/*      */   public static XMLStreamReader2 wrapIfNecessary(XMLStreamReader sr) {
/*  106 */     if (sr instanceof XMLStreamReader2)
/*  107 */       return (XMLStreamReader2)sr; 
/*  109 */     return new Stax2ReaderAdapter(sr);
/*      */   }
/*      */   
/*      */   public int next() throws XMLStreamException {
/*  124 */     if (this._typedContent != null) {
/*  125 */       this._typedContent = null;
/*  126 */       return 2;
/*      */     } 
/*  129 */     int type = super.next();
/*  130 */     if (type == 1) {
/*  131 */       this._depth++;
/*  132 */     } else if (type == 2) {
/*  133 */       this._depth--;
/*      */     } 
/*  135 */     return type;
/*      */   }
/*      */   
/*      */   public String getElementText() throws XMLStreamException {
/*  146 */     boolean hadStart = (getEventType() == 1);
/*  147 */     String text = super.getElementText();
/*  148 */     if (hadStart)
/*  149 */       this._depth--; 
/*  151 */     return text;
/*      */   }
/*      */   
/*      */   public boolean getElementAsBoolean() throws XMLStreamException {
/*  162 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/*  163 */     getElementAs((TypedValueDecoder)dec);
/*  164 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public int getElementAsInt() throws XMLStreamException {
/*  169 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/*  170 */     getElementAs((TypedValueDecoder)dec);
/*  171 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public long getElementAsLong() throws XMLStreamException {
/*  176 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/*  177 */     getElementAs((TypedValueDecoder)dec);
/*  178 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public float getElementAsFloat() throws XMLStreamException {
/*  183 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/*  184 */     getElementAs((TypedValueDecoder)dec);
/*  185 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public double getElementAsDouble() throws XMLStreamException {
/*  190 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/*  191 */     getElementAs((TypedValueDecoder)dec);
/*  192 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigInteger getElementAsInteger() throws XMLStreamException {
/*  197 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/*  198 */     getElementAs((TypedValueDecoder)dec);
/*  199 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigDecimal getElementAsDecimal() throws XMLStreamException {
/*  204 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/*  205 */     getElementAs((TypedValueDecoder)dec);
/*  206 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public QName getElementAsQName() throws XMLStreamException {
/*  211 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/*  212 */     getElementAs((TypedValueDecoder)dec);
/*  213 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public byte[] getElementAsBinary() throws XMLStreamException {
/*  218 */     return getElementAsBinary(Base64Variants.getDefaultVariant());
/*      */   }
/*      */   
/*      */   public byte[] getElementAsBinary(Base64Variant v) throws XMLStreamException {
/*  224 */     Stax2Util.ByteAggregator aggr = _base64Decoder().getByteAggregator();
/*  225 */     byte[] buffer = aggr.startAggregation();
/*      */     while (true) {
/*  227 */       int offset = 0;
/*  228 */       int len = buffer.length;
/*      */       while (true) {
/*  230 */         int readCount = readElementAsBinary(buffer, offset, len, v);
/*  231 */         if (readCount < 1)
/*  232 */           return aggr.aggregateAll(buffer, offset); 
/*  234 */         offset += readCount;
/*  235 */         len -= readCount;
/*  236 */         if (len <= 0)
/*  237 */           buffer = aggr.addFullBlock(buffer); 
/*      */       } 
/*      */       break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void getElementAs(TypedValueDecoder tvd) throws XMLStreamException {
/*  243 */     String value = getElementText();
/*  244 */     value = Stax2Util.trimSpaces(value);
/*      */     try {
/*  246 */       if (value == null) {
/*  247 */         tvd.handleEmptyValue();
/*      */       } else {
/*  249 */         tvd.decode(value);
/*      */       } 
/*  251 */     } catch (IllegalArgumentException iae) {
/*  252 */       throw _constructTypeException(iae, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int readElementAsIntArray(int[] value, int from, int length) throws XMLStreamException {
/*  258 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getIntArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsLongArray(long[] value, int from, int length) throws XMLStreamException {
/*  263 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getLongArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsFloatArray(float[] value, int from, int length) throws XMLStreamException {
/*  268 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getFloatArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/*  273 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getDoubleArrayDecoder(value, from, length));
/*      */   }
/*      */   
/*      */   public int readElementAsArray(TypedArrayDecoder tad) throws XMLStreamException {
/*  279 */     if (this._typedContent == null) {
/*  280 */       int type = getEventType();
/*  281 */       if (type == 2)
/*  282 */         return -1; 
/*  284 */       if (type != 1)
/*  285 */         throw new IllegalStateException("First call to readElementAsArray() must be for a START_ELEMENT"); 
/*  287 */       this._typedContent = getElementText();
/*      */     } 
/*  294 */     String input = this._typedContent;
/*  295 */     int end = input.length();
/*  296 */     int ptr = 0;
/*  297 */     int count = 0;
/*  298 */     String value = null;
/*      */     try {
/*  302 */       label43: while (ptr < end) {
/*  304 */         while (input.charAt(ptr) <= ' ') {
/*  305 */           if (++ptr >= end)
/*      */             break label43; 
/*      */         } 
/*  310 */         int start = ptr;
/*  311 */         ptr++;
/*  312 */         while (ptr < end && input.charAt(ptr) > ' ')
/*  313 */           ptr++; 
/*  315 */         count++;
/*  317 */         value = input.substring(start, ptr);
/*  319 */         ptr++;
/*  320 */         if (tad.decodeValue(value))
/*      */           break; 
/*      */       } 
/*  324 */     } catch (IllegalArgumentException iae) {
/*  329 */       Location loc = getLocation();
/*  330 */       throw new TypedXMLStreamException(value, iae.getMessage(), loc, iae);
/*      */     } finally {
/*  332 */       int len = end - ptr;
/*  334 */       this._typedContent = (len < 1) ? null : input.substring(ptr);
/*      */     } 
/*  336 */     return (count < 1) ? -1 : count;
/*      */   }
/*      */   
/*      */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength) throws XMLStreamException {
/*  348 */     return readElementAsBinary(resultBuffer, offset, maxLength, Base64Variants.getDefaultVariant());
/*      */   }
/*      */   
/*      */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength, Base64Variant v) throws XMLStreamException {
/*  355 */     if (resultBuffer == null)
/*  356 */       throw new IllegalArgumentException("resultBuffer is null"); 
/*  358 */     if (offset < 0)
/*  359 */       throw new IllegalArgumentException("Illegal offset (" + offset + "), must be [0, " + resultBuffer.length + "["); 
/*  361 */     if (maxLength < 1 || offset + maxLength > resultBuffer.length) {
/*  362 */       if (maxLength == 0)
/*  363 */         return 0; 
/*  365 */       throw new IllegalArgumentException("Illegal maxLength (" + maxLength + "), has to be positive number, and offset+maxLength can not exceed" + resultBuffer.length);
/*      */     } 
/*  368 */     StringBase64Decoder dec = _base64Decoder();
/*  369 */     int type = getEventType();
/*  371 */     if ((1 << type & 0x1052) == 0)
/*  372 */       if (type == 2) {
/*  374 */         if (!dec.hasData())
/*  375 */           return -1; 
/*      */       } else {
/*  378 */         throwNotStartElemOrTextual(type);
/*      */       }  
/*  383 */     if (type == 1) {
/*      */       while (true) {
/*  386 */         type = next();
/*  387 */         if (type == 2)
/*  389 */           return -1; 
/*  391 */         if (type == 5 || type == 3)
/*      */           continue; 
/*      */         break;
/*      */       } 
/*  394 */       if ((1 << type & 0x1250) == 0)
/*  395 */         throwNotStartElemOrTextual(type); 
/*  397 */       dec.init(v, true, getText());
/*      */     } 
/*  402 */     int totalCount = 0;
/*      */     while (true) {
/*      */       int count;
/*      */       try {
/*  409 */         count = dec.decode(resultBuffer, offset, maxLength);
/*  410 */       } catch (IllegalArgumentException iae) {
/*  411 */         throw _constructTypeException(iae, "");
/*      */       } 
/*  413 */       offset += count;
/*  414 */       totalCount += count;
/*  415 */       maxLength -= count;
/*  420 */       if (maxLength < 1 || getEventType() == 2)
/*      */         break; 
/*      */       while (true) {
/*  425 */         type = next();
/*  426 */         if (type == 5 || type == 3 || type == 6)
/*      */           continue; 
/*      */         break;
/*      */       } 
/*  430 */       if (type == 2) {
/*  437 */         int left = dec.endOfContent();
/*  438 */         if (left < 0)
/*  439 */           throw _constructTypeException("Incomplete base64 triplet at the end of decoded content", ""); 
/*  440 */         if (left > 0)
/*      */           continue; 
/*      */         break;
/*      */       } 
/*  446 */       if ((1 << type & 0x1250) == 0)
/*  447 */         throwNotStartElemOrTextual(type); 
/*  449 */       dec.init(v, false, getText());
/*      */     } 
/*  455 */     return (totalCount > 0) ? totalCount : -1;
/*      */   }
/*      */   
/*      */   public int getAttributeIndex(String namespaceURI, String localName) {
/*  466 */     return findAttributeIndex(namespaceURI, localName);
/*      */   }
/*      */   
/*      */   public boolean getAttributeAsBoolean(int index) throws XMLStreamException {
/*  471 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/*  472 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  473 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public int getAttributeAsInt(int index) throws XMLStreamException {
/*  478 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/*  479 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  480 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public long getAttributeAsLong(int index) throws XMLStreamException {
/*  485 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/*  486 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  487 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public float getAttributeAsFloat(int index) throws XMLStreamException {
/*  492 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/*  493 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  494 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public double getAttributeAsDouble(int index) throws XMLStreamException {
/*  499 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/*  500 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  501 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigInteger getAttributeAsInteger(int index) throws XMLStreamException {
/*  506 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/*  507 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  508 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public BigDecimal getAttributeAsDecimal(int index) throws XMLStreamException {
/*  513 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/*  514 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  515 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public QName getAttributeAsQName(int index) throws XMLStreamException {
/*  520 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/*  521 */     getAttributeAs(index, (TypedValueDecoder)dec);
/*  522 */     return dec.getValue();
/*      */   }
/*      */   
/*      */   public void getAttributeAs(int index, TypedValueDecoder tvd) throws XMLStreamException {
/*  527 */     String value = getAttributeValue(index);
/*  528 */     value = Stax2Util.trimSpaces(value);
/*      */     try {
/*  530 */       if (value == null) {
/*  531 */         tvd.handleEmptyValue();
/*      */       } else {
/*  533 */         tvd.decode(value);
/*      */       } 
/*  535 */     } catch (IllegalArgumentException iae) {
/*  536 */       throw _constructTypeException(iae, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int[] getAttributeAsIntArray(int index) throws XMLStreamException {
/*  542 */     ValueDecoderFactory.IntArrayDecoder dec = _decoderFactory().getIntArrayDecoder();
/*  543 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/*  544 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public long[] getAttributeAsLongArray(int index) throws XMLStreamException {
/*  549 */     ValueDecoderFactory.LongArrayDecoder dec = _decoderFactory().getLongArrayDecoder();
/*  550 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/*  551 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public float[] getAttributeAsFloatArray(int index) throws XMLStreamException {
/*  556 */     ValueDecoderFactory.FloatArrayDecoder dec = _decoderFactory().getFloatArrayDecoder();
/*  557 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/*  558 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public double[] getAttributeAsDoubleArray(int index) throws XMLStreamException {
/*  563 */     ValueDecoderFactory.DoubleArrayDecoder dec = _decoderFactory().getDoubleArrayDecoder();
/*  564 */     _getAttributeAsArray((TypedArrayDecoder)dec, getAttributeValue(index));
/*  565 */     return dec.getValues();
/*      */   }
/*      */   
/*      */   public int getAttributeAsArray(int index, TypedArrayDecoder tad) throws XMLStreamException {
/*  570 */     return _getAttributeAsArray(tad, getAttributeValue(index));
/*      */   }
/*      */   
/*      */   protected int _getAttributeAsArray(TypedArrayDecoder tad, String attrValue) throws XMLStreamException {
/*  575 */     int ptr = 0;
/*  576 */     int start = 0;
/*  577 */     int end = attrValue.length();
/*  578 */     String lexical = null;
/*  579 */     int count = 0;
/*      */     try {
/*  583 */       while (ptr < end) {
/*  585 */         while (attrValue.charAt(ptr) <= ' ') {
/*  586 */           if (++ptr >= end)
/*      */             // Byte code: goto -> 145 
/*      */         } 
/*  591 */         start = ptr;
/*  592 */         ptr++;
/*  593 */         while (ptr < end && attrValue.charAt(ptr) > ' ')
/*  594 */           ptr++; 
/*  596 */         int tokenEnd = ptr;
/*  597 */         ptr++;
/*  599 */         lexical = attrValue.substring(start, tokenEnd);
/*  600 */         count++;
/*  601 */         if (tad.decodeValue(lexical) && 
/*  602 */           !checkExpand(tad))
/*      */           break; 
/*      */       } 
/*  607 */     } catch (IllegalArgumentException iae) {
/*  609 */       Location loc = getLocation();
/*  610 */       throw new TypedXMLStreamException(lexical, iae.getMessage(), loc, iae);
/*      */     } 
/*  612 */     return count;
/*      */   }
/*      */   
/*      */   private final boolean checkExpand(TypedArrayDecoder tad) {
/*  623 */     if (tad instanceof ValueDecoderFactory.BaseArrayDecoder) {
/*  624 */       ((ValueDecoderFactory.BaseArrayDecoder)tad).expand();
/*  625 */       return true;
/*      */     } 
/*  627 */     return false;
/*      */   }
/*      */   
/*      */   public byte[] getAttributeAsBinary(int index) throws XMLStreamException {
/*  632 */     return getAttributeAsBinary(index, Base64Variants.getDefaultVariant());
/*      */   }
/*      */   
/*      */   public byte[] getAttributeAsBinary(int index, Base64Variant v) throws XMLStreamException {
/*  637 */     String lexical = getAttributeValue(index);
/*  638 */     StringBase64Decoder dec = _base64Decoder();
/*  639 */     dec.init(v, true, lexical);
/*      */     try {
/*  641 */       return dec.decodeCompletely();
/*  642 */     } catch (IllegalArgumentException iae) {
/*  643 */       throw new TypedXMLStreamException(lexical, iae.getMessage(), getLocation(), iae);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getFeature(String name) {
/*  658 */     return null;
/*      */   }
/*      */   
/*      */   public void setFeature(String name, Object value) {}
/*      */   
/*      */   public boolean isPropertySupported(String name) {
/*  673 */     return false;
/*      */   }
/*      */   
/*      */   public boolean setProperty(String name, Object value) {
/*  678 */     return false;
/*      */   }
/*      */   
/*      */   public void skipElement() throws XMLStreamException {
/*  685 */     if (getEventType() != 1)
/*  686 */       throwNotStartElem(getEventType()); 
/*  688 */     int nesting = 1;
/*      */     while (true) {
/*  691 */       int type = next();
/*  692 */       if (type == 1) {
/*  693 */         nesting++;
/*      */         continue;
/*      */       } 
/*  694 */       if (type == 2 && 
/*  695 */         --nesting == 0)
/*      */         break; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public AttributeInfo getAttributeInfo() throws XMLStreamException {
/*  706 */     if (getEventType() != 1)
/*  707 */       throwNotStartElem(getEventType()); 
/*  709 */     return this;
/*      */   }
/*      */   
/*      */   public DTDInfo getDTDInfo() throws XMLStreamException {
/*  716 */     if (getEventType() != 11)
/*  717 */       return null; 
/*  719 */     return this;
/*      */   }
/*      */   
/*      */   public final LocationInfo getLocationInfo() {
/*  728 */     return this;
/*      */   }
/*      */   
/*      */   public int getText(Writer w, boolean preserveContents) throws IOException, XMLStreamException {
/*  736 */     char[] cbuf = getTextCharacters();
/*  737 */     int start = getTextStart();
/*  738 */     int len = getTextLength();
/*  740 */     if (len > 0)
/*  741 */       w.write(cbuf, start, len); 
/*  743 */     return len;
/*      */   }
/*      */   
/*      */   public int getDepth() {
/*  757 */     if (getEventType() == 2)
/*  758 */       return this._depth + 1; 
/*  760 */     return this._depth;
/*      */   }
/*      */   
/*      */   public boolean isEmptyElement() throws XMLStreamException {
/*  769 */     return false;
/*      */   }
/*      */   
/*      */   public NamespaceContext getNonTransientNamespaceContext() {
/*  778 */     return null;
/*      */   }
/*      */   
/*      */   public String getPrefixedName() {
/*      */     String prefix;
/*      */     String ln;
/*      */     StringBuffer sb;
/*  783 */     switch (getEventType()) {
/*      */       case 1:
/*      */       case 2:
/*  787 */         prefix = getPrefix();
/*  788 */         ln = getLocalName();
/*  790 */         if (prefix == null || prefix.length() == 0)
/*  791 */           return ln; 
/*  793 */         sb = new StringBuffer(ln.length() + 1 + prefix.length());
/*  794 */         sb.append(prefix);
/*  795 */         sb.append(':');
/*  796 */         sb.append(ln);
/*  797 */         return sb.toString();
/*      */       case 9:
/*  800 */         return getLocalName();
/*      */       case 3:
/*  802 */         return getPITarget();
/*      */       case 11:
/*  804 */         return getDTDRootName();
/*      */     } 
/*  807 */     throw new IllegalStateException("Current state not START_ELEMENT, END_ELEMENT, ENTITY_REFERENCE, PROCESSING_INSTRUCTION or DTD");
/*      */   }
/*      */   
/*      */   public void closeCompletely() throws XMLStreamException {
/*  815 */     close();
/*      */   }
/*      */   
/*      */   public int findAttributeIndex(String nsURI, String localName) {
/*  829 */     if ("".equals(nsURI))
/*  830 */       nsURI = null; 
/*  832 */     for (int i = 0, len = getAttributeCount(); i < len; i++) {
/*  833 */       if (getAttributeLocalName(i).equals(localName)) {
/*  834 */         String otherUri = getAttributeNamespace(i);
/*  835 */         if (nsURI == null) {
/*  836 */           if (otherUri == null || otherUri.length() == 0)
/*  837 */             return i; 
/*  840 */         } else if (nsURI.equals(otherUri)) {
/*  841 */           return i;
/*      */         } 
/*      */       } 
/*      */     } 
/*  846 */     return -1;
/*      */   }
/*      */   
/*      */   public int getIdAttributeIndex() {
/*  851 */     for (int i = 0, len = getAttributeCount(); i < len; i++) {
/*  852 */       if ("ID".equals(getAttributeType(i)))
/*  853 */         return i; 
/*      */     } 
/*  856 */     return -1;
/*      */   }
/*      */   
/*      */   public int getNotationAttributeIndex() {
/*  861 */     for (int i = 0, len = getAttributeCount(); i < len; i++) {
/*  862 */       if ("NOTATION".equals(getAttributeType(i)))
/*  863 */         return i; 
/*      */     } 
/*  866 */     return -1;
/*      */   }
/*      */   
/*      */   public Object getProcessedDTD() {
/*  876 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDRootName() {
/*  880 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDPublicId() {
/*  884 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDSystemId() {
/*  888 */     return null;
/*      */   }
/*      */   
/*      */   public String getDTDInternalSubset() {
/*  901 */     if (getEventType() == 11)
/*  902 */       return getText(); 
/*  904 */     return null;
/*      */   }
/*      */   
/*      */   public DTDValidationSchema getProcessedDTDSchema() {
/*  910 */     return null;
/*      */   }
/*      */   
/*      */   public long getStartingByteOffset() {
/*  922 */     return -1L;
/*      */   }
/*      */   
/*      */   public long getStartingCharOffset() {
/*  926 */     return 0L;
/*      */   }
/*      */   
/*      */   public long getEndingByteOffset() throws XMLStreamException {
/*  931 */     return -1L;
/*      */   }
/*      */   
/*      */   public long getEndingCharOffset() throws XMLStreamException {
/*  936 */     return -1L;
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getStartLocation() {
/*  947 */     return getCurrentLocation();
/*      */   }
/*      */   
/*      */   public XMLStreamLocation2 getCurrentLocation() {
/*  953 */     return new Stax2LocationAdapter(getLocation());
/*      */   }
/*      */   
/*      */   public final XMLStreamLocation2 getEndLocation() throws XMLStreamException {
/*  963 */     return getCurrentLocation();
/*      */   }
/*      */   
/*      */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/*  975 */     throwUnsupported();
/*  976 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/*  982 */     throwUnsupported();
/*  983 */     return null;
/*      */   }
/*      */   
/*      */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/*  989 */     throwUnsupported();
/*  990 */     return null;
/*      */   }
/*      */   
/*      */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/*  995 */     return null;
/*      */   }
/*      */   
/*      */   protected ValueDecoderFactory _decoderFactory() {
/* 1006 */     if (this._decoderFactory == null)
/* 1007 */       this._decoderFactory = new ValueDecoderFactory(); 
/* 1009 */     return this._decoderFactory;
/*      */   }
/*      */   
/*      */   protected StringBase64Decoder _base64Decoder() {
/* 1014 */     if (this._base64Decoder == null)
/* 1015 */       this._base64Decoder = new StringBase64Decoder(); 
/* 1017 */     return this._base64Decoder;
/*      */   }
/*      */   
/*      */   protected void throwUnsupported() throws XMLStreamException {
/* 1023 */     throw new XMLStreamException("Unsupported method");
/*      */   }
/*      */   
/*      */   protected void throwNotStartElem(int type) {
/* 1028 */     throw new IllegalStateException("Current event (" + Stax2Util.eventTypeDesc(type) + ") not START_ELEMENT");
/*      */   }
/*      */   
/*      */   protected void throwNotStartElemOrTextual(int type) {
/* 1033 */     throw new IllegalStateException("Current event (" + Stax2Util.eventTypeDesc(type) + ") not START_ELEMENT, END_ELEMENT, CHARACTERS or CDATA");
/*      */   }
/*      */   
/*      */   protected TypedXMLStreamException _constructTypeException(IllegalArgumentException iae, String lexicalValue) {
/* 1046 */     String msg = iae.getMessage();
/* 1047 */     if (msg == null)
/* 1048 */       msg = ""; 
/* 1050 */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/* 1051 */     if (xMLStreamLocation2 == null)
/* 1052 */       return new TypedXMLStreamException(lexicalValue, msg, iae); 
/* 1054 */     return new TypedXMLStreamException(lexicalValue, msg, (Location)xMLStreamLocation2, iae);
/*      */   }
/*      */   
/*      */   protected TypedXMLStreamException _constructTypeException(String msg, String lexicalValue) {
/* 1059 */     XMLStreamLocation2 xMLStreamLocation2 = getStartLocation();
/* 1060 */     if (xMLStreamLocation2 == null)
/* 1061 */       return new TypedXMLStreamException(lexicalValue, msg); 
/* 1063 */     return new TypedXMLStreamException(lexicalValue, msg, (Location)xMLStreamLocation2);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2ReaderAdapter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */