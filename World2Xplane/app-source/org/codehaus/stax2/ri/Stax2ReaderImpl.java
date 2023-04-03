/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.AttributeInfo;
/*     */ import org.codehaus.stax2.DTDInfo;
/*     */ import org.codehaus.stax2.LocationInfo;
/*     */ import org.codehaus.stax2.XMLStreamLocation2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.typed.Base64Variants;
/*     */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*     */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*     */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*     */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class Stax2ReaderImpl implements XMLStreamReader2, AttributeInfo, DTDInfo, LocationInfo {
/*     */   protected ValueDecoderFactory _decoderFactory;
/*     */   
/*     */   public Object getFeature(String name) {
/*  72 */     return null;
/*     */   }
/*     */   
/*     */   public void setFeature(String name, Object value) {}
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public void skipElement() throws XMLStreamException {
/*  99 */     if (getEventType() != 1)
/* 100 */       throwNotStartElem(); 
/* 102 */     int nesting = 1;
/*     */     while (true) {
/* 105 */       int type = next();
/* 106 */       if (type == 1) {
/* 107 */         nesting++;
/*     */         continue;
/*     */       } 
/* 108 */       if (type == 2 && 
/* 109 */         --nesting == 0)
/*     */         break; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AttributeInfo getAttributeInfo() throws XMLStreamException {
/* 120 */     if (getEventType() != 1)
/* 121 */       throwNotStartElem(); 
/* 123 */     return this;
/*     */   }
/*     */   
/*     */   public DTDInfo getDTDInfo() throws XMLStreamException {
/* 130 */     if (getEventType() != 11)
/* 131 */       return null; 
/* 133 */     return this;
/*     */   }
/*     */   
/*     */   public final LocationInfo getLocationInfo() {
/* 142 */     return this;
/*     */   }
/*     */   
/*     */   public int getText(Writer w, boolean preserveContents) throws IOException, XMLStreamException {
/* 150 */     char[] cbuf = getTextCharacters();
/* 151 */     int start = getTextStart();
/* 152 */     int len = getTextLength();
/* 154 */     if (len > 0)
/* 155 */       w.write(cbuf, start, len); 
/* 157 */     return len;
/*     */   }
/*     */   
/*     */   public abstract int getDepth();
/*     */   
/*     */   public abstract boolean isEmptyElement() throws XMLStreamException;
/*     */   
/*     */   public abstract NamespaceContext getNonTransientNamespaceContext();
/*     */   
/*     */   public String getPrefixedName() {
/*     */     String prefix;
/*     */     String ln;
/*     */     StringBuffer sb;
/* 174 */     switch (getEventType()) {
/*     */       case 1:
/*     */       case 2:
/* 178 */         prefix = getPrefix();
/* 179 */         ln = getLocalName();
/* 181 */         if (prefix == null)
/* 182 */           return ln; 
/* 184 */         sb = new StringBuffer(ln.length() + 1 + prefix.length());
/* 185 */         sb.append(prefix);
/* 186 */         sb.append(':');
/* 187 */         sb.append(ln);
/* 188 */         return sb.toString();
/*     */       case 9:
/* 191 */         return getLocalName();
/*     */       case 3:
/* 193 */         return getPITarget();
/*     */       case 11:
/* 195 */         return getDTDRootName();
/*     */     } 
/* 198 */     throw new IllegalStateException("Current state not START_ELEMENT, END_ELEMENT, ENTITY_REFERENCE, PROCESSING_INSTRUCTION or DTD");
/*     */   }
/*     */   
/*     */   public void closeCompletely() throws XMLStreamException {
/* 206 */     close();
/*     */   }
/*     */   
/*     */   public int findAttributeIndex(String nsURI, String localName) {
/* 221 */     return -1;
/*     */   }
/*     */   
/*     */   public int getIdAttributeIndex() {
/* 227 */     return -1;
/*     */   }
/*     */   
/*     */   public int getNotationAttributeIndex() {
/* 233 */     return -1;
/*     */   }
/*     */   
/*     */   public Object getProcessedDTD() {
/* 243 */     return null;
/*     */   }
/*     */   
/*     */   public String getDTDRootName() {
/* 247 */     return null;
/*     */   }
/*     */   
/*     */   public String getDTDPublicId() {
/* 251 */     return null;
/*     */   }
/*     */   
/*     */   public String getDTDSystemId() {
/* 255 */     return null;
/*     */   }
/*     */   
/*     */   public String getDTDInternalSubset() {
/* 263 */     return null;
/*     */   }
/*     */   
/*     */   public DTDValidationSchema getProcessedDTDSchema() {
/* 269 */     return null;
/*     */   }
/*     */   
/*     */   public long getStartingByteOffset() {
/* 281 */     return -1L;
/*     */   }
/*     */   
/*     */   public long getStartingCharOffset() {
/* 285 */     return 0L;
/*     */   }
/*     */   
/*     */   public long getEndingByteOffset() throws XMLStreamException {
/* 290 */     return -1L;
/*     */   }
/*     */   
/*     */   public long getEndingCharOffset() throws XMLStreamException {
/* 295 */     return -1L;
/*     */   }
/*     */   
/*     */   public abstract XMLStreamLocation2 getStartLocation();
/*     */   
/*     */   public abstract XMLStreamLocation2 getCurrentLocation();
/*     */   
/*     */   public abstract XMLStreamLocation2 getEndLocation() throws XMLStreamException;
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 316 */     throwUnsupported();
/* 317 */     return null;
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 323 */     throwUnsupported();
/* 324 */     return null;
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 330 */     throwUnsupported();
/* 331 */     return null;
/*     */   }
/*     */   
/*     */   public abstract ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler paramValidationProblemHandler);
/*     */   
/*     */   public boolean getElementAsBoolean() throws XMLStreamException {
/* 344 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/* 345 */     getElementAs((TypedValueDecoder)dec);
/* 346 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public int getElementAsInt() throws XMLStreamException {
/* 351 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/* 352 */     getElementAs((TypedValueDecoder)dec);
/* 353 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public long getElementAsLong() throws XMLStreamException {
/* 358 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/* 359 */     getElementAs((TypedValueDecoder)dec);
/* 360 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public float getElementAsFloat() throws XMLStreamException {
/* 365 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/* 366 */     getElementAs((TypedValueDecoder)dec);
/* 367 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public double getElementAsDouble() throws XMLStreamException {
/* 372 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/* 373 */     getElementAs((TypedValueDecoder)dec);
/* 374 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigInteger getElementAsInteger() throws XMLStreamException {
/* 379 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/* 380 */     getElementAs((TypedValueDecoder)dec);
/* 381 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigDecimal getElementAsDecimal() throws XMLStreamException {
/* 386 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/* 387 */     getElementAs((TypedValueDecoder)dec);
/* 388 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public QName getElementAsQName() throws XMLStreamException {
/* 393 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/* 394 */     getElementAs((TypedValueDecoder)dec);
/* 396 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public byte[] getElementAsBinary() throws XMLStreamException {
/* 401 */     return getElementAsBinary(Base64Variants.getDefaultVariant());
/*     */   }
/*     */   
/*     */   public abstract byte[] getElementAsBinary(Base64Variant paramBase64Variant) throws XMLStreamException;
/*     */   
/*     */   public void getElementAs(TypedValueDecoder tvd) throws XMLStreamException {
/* 409 */     String value = getElementText();
/*     */     try {
/* 411 */       tvd.decode(value);
/* 412 */     } catch (IllegalArgumentException iae) {
/* 413 */       throw _constructTypeException(iae, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int readElementAsIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 425 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getIntArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public int readElementAsLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 430 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getLongArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public int readElementAsFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 435 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getFloatArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public int readElementAsDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 440 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getDoubleArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public abstract int readElementAsArray(TypedArrayDecoder paramTypedArrayDecoder) throws XMLStreamException;
/*     */   
/*     */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength) throws XMLStreamException {
/* 461 */     return readElementAsBinary(Base64Variants.getDefaultVariant(), resultBuffer, offset, maxLength);
/*     */   }
/*     */   
/*     */   public abstract int readElementAsBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws XMLStreamException;
/*     */   
/*     */   public abstract int getAttributeIndex(String paramString1, String paramString2);
/*     */   
/*     */   public boolean getAttributeAsBoolean(int index) throws XMLStreamException {
/* 477 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/* 478 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 479 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public int getAttributeAsInt(int index) throws XMLStreamException {
/* 484 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/* 485 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 486 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public long getAttributeAsLong(int index) throws XMLStreamException {
/* 491 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/* 492 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 493 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public float getAttributeAsFloat(int index) throws XMLStreamException {
/* 498 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/* 499 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 500 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public double getAttributeAsDouble(int index) throws XMLStreamException {
/* 505 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/* 506 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 507 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigInteger getAttributeAsInteger(int index) throws XMLStreamException {
/* 512 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/* 513 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 514 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigDecimal getAttributeAsDecimal(int index) throws XMLStreamException {
/* 519 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/* 520 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 521 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public QName getAttributeAsQName(int index) throws XMLStreamException {
/* 526 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/* 527 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 529 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public void getAttributeAs(int index, TypedValueDecoder tvd) throws XMLStreamException {
/* 534 */     String value = getAttributeValue(index);
/*     */     try {
/* 536 */       tvd.decode(value);
/* 537 */     } catch (IllegalArgumentException iae) {
/* 538 */       throw _constructTypeException(iae, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getAttributeAsIntArray(int index) throws XMLStreamException {
/* 544 */     ValueDecoderFactory.IntArrayDecoder dec = _decoderFactory().getIntArrayDecoder();
/* 545 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 546 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public long[] getAttributeAsLongArray(int index) throws XMLStreamException {
/* 551 */     ValueDecoderFactory.LongArrayDecoder dec = _decoderFactory().getLongArrayDecoder();
/* 552 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 553 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public float[] getAttributeAsFloatArray(int index) throws XMLStreamException {
/* 558 */     ValueDecoderFactory.FloatArrayDecoder dec = _decoderFactory().getFloatArrayDecoder();
/* 559 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 560 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public double[] getAttributeAsDoubleArray(int index) throws XMLStreamException {
/* 565 */     ValueDecoderFactory.DoubleArrayDecoder dec = _decoderFactory().getDoubleArrayDecoder();
/* 566 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 567 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public abstract int getAttributeAsArray(int paramInt, TypedArrayDecoder paramTypedArrayDecoder) throws XMLStreamException;
/*     */   
/*     */   public byte[] getAttributeAsBinary(int index) throws XMLStreamException {
/* 579 */     return getAttributeAsBinary(Base64Variants.getDefaultVariant(), index);
/*     */   }
/*     */   
/*     */   public abstract byte[] getAttributeAsBinary(Base64Variant paramBase64Variant, int paramInt) throws XMLStreamException;
/*     */   
/*     */   protected ValueDecoderFactory _decoderFactory() {
/* 592 */     if (this._decoderFactory == null)
/* 593 */       this._decoderFactory = new ValueDecoderFactory(); 
/* 595 */     return this._decoderFactory;
/*     */   }
/*     */   
/*     */   protected TypedXMLStreamException _constructTypeException(IllegalArgumentException iae, String lexicalValue) {
/* 600 */     return new TypedXMLStreamException(lexicalValue, iae.getMessage(), (Location)getStartLocation(), iae);
/*     */   }
/*     */   
/*     */   protected void throwUnsupported() throws XMLStreamException {
/* 612 */     throw new XMLStreamException("Unsupported method");
/*     */   }
/*     */   
/*     */   protected void throwNotStartElem() {
/* 617 */     throw new IllegalStateException("Current state not START_ELEMENT");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2ReaderImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */