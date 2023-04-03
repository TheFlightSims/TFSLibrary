/*     */ package com.ctc.wstx.sr;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.io.BranchingReaderSource;
/*     */ import com.ctc.wstx.io.InputBootstrapper;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.ri.Stax2Util;
/*     */ import org.codehaus.stax2.ri.typed.CharArrayBase64Decoder;
/*     */ import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.typed.Base64Variants;
/*     */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*     */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*     */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*     */ 
/*     */ public class TypedStreamReader extends BasicStreamReader {
/*     */   protected static final int MASK_TYPED_ACCESS_ARRAY = 4182;
/*     */   
/*     */   protected static final int MASK_TYPED_ACCESS_BINARY = 4178;
/*     */   
/*     */   static final int MIN_BINARY_CHUNK = 2000;
/*     */   
/*     */   protected ValueDecoderFactory _decoderFactory;
/*     */   
/*  81 */   protected CharArrayBase64Decoder _base64Decoder = null;
/*     */   
/*     */   protected TypedStreamReader(InputBootstrapper bs, BranchingReaderSource input, ReaderCreator owner, ReaderConfig cfg, InputElementStack elemStack, boolean forER) throws XMLStreamException {
/*  95 */     super(bs, input, owner, cfg, elemStack, forER);
/*     */   }
/*     */   
/*     */   public static TypedStreamReader createStreamReader(BranchingReaderSource input, ReaderCreator owner, ReaderConfig cfg, InputBootstrapper bs, boolean forER) throws XMLStreamException {
/* 112 */     TypedStreamReader sr = new TypedStreamReader(bs, input, owner, cfg, createElementStack(cfg), forER);
/* 114 */     return sr;
/*     */   }
/*     */   
/*     */   public boolean getElementAsBoolean() throws XMLStreamException {
/* 126 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/* 127 */     getElementAs((TypedValueDecoder)dec);
/* 128 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public int getElementAsInt() throws XMLStreamException {
/* 133 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/* 134 */     getElementAs((TypedValueDecoder)dec);
/* 135 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public long getElementAsLong() throws XMLStreamException {
/* 140 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/* 141 */     getElementAs((TypedValueDecoder)dec);
/* 142 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public float getElementAsFloat() throws XMLStreamException {
/* 147 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/* 148 */     getElementAs((TypedValueDecoder)dec);
/* 149 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public double getElementAsDouble() throws XMLStreamException {
/* 154 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/* 155 */     getElementAs((TypedValueDecoder)dec);
/* 156 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigInteger getElementAsInteger() throws XMLStreamException {
/* 161 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/* 162 */     getElementAs((TypedValueDecoder)dec);
/* 163 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigDecimal getElementAsDecimal() throws XMLStreamException {
/* 168 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/* 169 */     getElementAs((TypedValueDecoder)dec);
/* 170 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public QName getElementAsQName() throws XMLStreamException {
/* 175 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/* 176 */     getElementAs((TypedValueDecoder)dec);
/* 177 */     return _verifyQName(dec.getValue());
/*     */   }
/*     */   
/*     */   public final byte[] getElementAsBinary() throws XMLStreamException {
/* 182 */     return getElementAsBinary(Base64Variants.getDefaultVariant());
/*     */   }
/*     */   
/*     */   public byte[] getElementAsBinary(Base64Variant v) throws XMLStreamException {
/* 188 */     Stax2Util.ByteAggregator aggr = _base64Decoder().getByteAggregator();
/* 189 */     byte[] buffer = aggr.startAggregation();
/*     */     while (true) {
/* 191 */       int offset = 0;
/* 192 */       int len = buffer.length;
/*     */       while (true) {
/* 195 */         int readCount = readElementAsBinary(buffer, offset, len, v);
/* 196 */         if (readCount < 1)
/* 197 */           return aggr.aggregateAll(buffer, offset); 
/* 199 */         offset += readCount;
/* 200 */         len -= readCount;
/* 201 */         if (len <= 0)
/* 202 */           buffer = aggr.addFullBlock(buffer); 
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getElementAs(TypedValueDecoder tvd) throws XMLStreamException {
/*     */     int type;
/* 208 */     if (this.mCurrToken != 1)
/* 209 */       throwParseError(ErrorConsts.ERR_STATE_NOT_STELEM); 
/* 215 */     if (this.mStEmptyElem) {
/* 221 */       this.mStEmptyElem = false;
/* 222 */       this.mCurrToken = 2;
/* 223 */       _handleEmptyValue(tvd);
/*     */       return;
/*     */     } 
/*     */     while (true) {
/* 228 */       type = next();
/* 229 */       if (type == 2) {
/* 230 */         _handleEmptyValue(tvd);
/*     */         return;
/*     */       } 
/* 233 */       if (type == 5 || type == 3)
/*     */         continue; 
/*     */       break;
/*     */     } 
/* 236 */     if ((1 << type & 0x1250) == 0)
/* 237 */       throwParseError("Expected a text token, got " + tokenTypeDesc(type) + "."); 
/* 241 */     if (this.mTokenState < 3)
/* 242 */       readCoalescedText(this.mCurrToken, false); 
/* 248 */     if (this.mInputPtr + 1 < this.mInputEnd && this.mInputBuffer[this.mInputPtr] == '<' && this.mInputBuffer[this.mInputPtr + 1] == '/') {
/* 251 */       this.mInputPtr += 2;
/* 252 */       this.mCurrToken = 2;
/*     */       try {
/* 260 */         this.mTextBuffer.decode(tvd);
/* 261 */       } catch (IllegalArgumentException iae) {
/* 262 */         throw _constructTypeException(iae, this.mTextBuffer.contentsAsString());
/*     */       } 
/* 264 */       readEndElem();
/*     */       return;
/*     */     } 
/* 269 */     int extra = 1 + (this.mTextBuffer.size() >> 1);
/* 270 */     StringBuffer sb = this.mTextBuffer.contentsAsStringBuffer(extra);
/*     */     int i;
/* 273 */     while ((i = next()) != 2) {
/* 274 */       if ((1 << i & 0x1250) != 0) {
/* 275 */         if (this.mTokenState < 3)
/* 276 */           readCoalescedText(i, false); 
/* 278 */         this.mTextBuffer.contentsToStringBuffer(sb);
/*     */         continue;
/*     */       } 
/* 281 */       if (i != 5 && i != 3)
/* 282 */         throwParseError("Expected a text token, got " + tokenTypeDesc(i) + "."); 
/*     */     } 
/* 286 */     String str = sb.toString();
/* 287 */     String tstr = Stax2Util.trimSpaces(str);
/* 288 */     if (tstr == null) {
/* 289 */       _handleEmptyValue(tvd);
/*     */     } else {
/*     */       try {
/* 292 */         tvd.decode(tstr);
/* 293 */       } catch (IllegalArgumentException iae) {
/* 294 */         throw _constructTypeException(iae, str);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int readElementAsIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 307 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getIntArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public int readElementAsLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 312 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getLongArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public int readElementAsFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 317 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getFloatArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public int readElementAsDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 322 */     return readElementAsArray((TypedArrayDecoder)_decoderFactory().getDoubleArrayDecoder(value, from, length));
/*     */   }
/*     */   
/*     */   public final int readElementAsArray(TypedArrayDecoder dec) throws XMLStreamException {
/* 340 */     int type = this.mCurrToken;
/* 342 */     if ((1 << type & 0x1056) == 0)
/* 343 */       throwNotTextualOrElem(type); 
/* 347 */     if (type == 1) {
/* 349 */       if (this.mStEmptyElem) {
/* 350 */         this.mStEmptyElem = false;
/* 351 */         this.mCurrToken = 2;
/* 352 */         return -1;
/*     */       } 
/*     */       while (true) {
/* 356 */         type = next();
/* 357 */         if (type == 2)
/* 359 */           return -1; 
/* 361 */         if (type == 5 || type == 3)
/*     */           continue; 
/*     */         break;
/*     */       } 
/* 364 */       if (type != 4 && type != 12)
/* 368 */         throw _constructUnexpectedInTyped(type); 
/*     */     } 
/* 372 */     int count = 0;
/* 373 */     while (type != 2) {
/* 379 */       if (type == 4 || type == 12 || type == 6) {
/* 380 */         if (this.mTokenState < 3)
/* 381 */           readCoalescedText(type, false); 
/*     */       } else {
/* 383 */         if (type == 5 || type == 3) {
/* 384 */           type = next();
/*     */           continue;
/*     */         } 
/* 387 */         throw _constructUnexpectedInTyped(type);
/*     */       } 
/* 389 */       count += this.mTextBuffer.decodeElements(dec, this);
/* 390 */       if (!dec.hasRoom())
/*     */         break; 
/* 393 */       type = next();
/*     */     } 
/* 397 */     return (count > 0) ? count : -1;
/*     */   }
/*     */   
/*     */   public final int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength) throws XMLStreamException {
/* 409 */     return readElementAsBinary(resultBuffer, offset, maxLength, Base64Variants.getDefaultVariant());
/*     */   }
/*     */   
/*     */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength, Base64Variant v) throws XMLStreamException {
/* 415 */     if (resultBuffer == null)
/* 416 */       throw new IllegalArgumentException("resultBuffer is null"); 
/* 418 */     if (offset < 0)
/* 419 */       throw new IllegalArgumentException("Illegal offset (" + offset + "), must be [0, " + resultBuffer.length + "["); 
/* 421 */     if (maxLength < 1 || offset + maxLength > resultBuffer.length) {
/* 422 */       if (maxLength == 0)
/* 423 */         return 0; 
/* 425 */       throw new IllegalArgumentException("Illegal maxLength (" + maxLength + "), has to be positive number, and offset+maxLength can not exceed" + resultBuffer.length);
/*     */     } 
/* 428 */     CharArrayBase64Decoder dec = _base64Decoder();
/* 429 */     int type = this.mCurrToken;
/* 431 */     if ((1 << type & 0x1052) == 0) {
/* 432 */       if (type == 2) {
/* 434 */         if (!dec.hasData())
/* 435 */           return -1; 
/*     */       } else {
/* 438 */         throwNotTextualOrElem(type);
/*     */       } 
/* 440 */     } else if (type == 1) {
/* 441 */       if (this.mStEmptyElem) {
/* 442 */         this.mStEmptyElem = false;
/* 443 */         this.mCurrToken = 2;
/* 444 */         return -1;
/*     */       } 
/*     */       while (true) {
/* 448 */         type = next();
/* 449 */         if (type == 2)
/* 451 */           return -1; 
/* 453 */         if (type == 5 || type == 3)
/*     */           continue; 
/*     */         break;
/*     */       } 
/* 459 */       if (this.mTokenState < this.mStTextThreshold)
/* 460 */         finishToken(false); 
/* 462 */       _initBinaryChunks(v, dec, type, true);
/*     */     } 
/* 467 */     int totalCount = 0;
/*     */     while (true) {
/*     */       int count;
/*     */       try {
/* 474 */         count = dec.decode(resultBuffer, offset, maxLength);
/* 475 */       } catch (IllegalArgumentException iae) {
/* 477 */         throw _constructTypeException(iae.getMessage(), "");
/*     */       } 
/* 479 */       offset += count;
/* 480 */       totalCount += count;
/* 481 */       maxLength -= count;
/* 486 */       if (maxLength < 1 || this.mCurrToken == 2)
/*     */         break; 
/*     */       while (true) {
/* 491 */         type = next();
/* 492 */         if (type == 5 || type == 3 || type == 6)
/*     */           continue; 
/*     */         break;
/*     */       } 
/* 496 */       if (type == 2) {
/* 503 */         int left = dec.endOfContent();
/* 504 */         if (left < 0)
/* 505 */           throw _constructTypeException("Incomplete base64 triplet at the end of decoded content", ""); 
/* 506 */         if (left > 0)
/*     */           continue; 
/*     */         break;
/*     */       } 
/* 515 */       if (this.mTokenState < this.mStTextThreshold)
/* 516 */         finishToken(false); 
/* 518 */       _initBinaryChunks(v, dec, type, false);
/*     */     } 
/* 524 */     return (totalCount > 0) ? totalCount : -1;
/*     */   }
/*     */   
/*     */   private final void _initBinaryChunks(Base64Variant v, CharArrayBase64Decoder dec, int type, boolean isFirst) throws XMLStreamException {
/* 530 */     if (type == 4) {
/* 531 */       if (this.mTokenState < this.mStTextThreshold)
/* 532 */         this.mTokenState = readTextSecondary(2000, false) ? 3 : 2; 
/* 535 */     } else if (type == 12) {
/* 536 */       if (this.mTokenState < this.mStTextThreshold)
/* 537 */         this.mTokenState = readCDataSecondary(2000) ? 3 : 2; 
/*     */     } else {
/* 541 */       throw _constructUnexpectedInTyped(type);
/*     */     } 
/* 543 */     this.mTextBuffer.initBinaryChunks(v, dec, isFirst);
/*     */   }
/*     */   
/*     */   public int getAttributeIndex(String namespaceURI, String localName) {
/* 555 */     if (this.mCurrToken != 1)
/* 556 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/* 558 */     return this.mElementStack.findAttributeIndex(namespaceURI, localName);
/*     */   }
/*     */   
/*     */   public boolean getAttributeAsBoolean(int index) throws XMLStreamException {
/* 563 */     ValueDecoderFactory.BooleanDecoder dec = _decoderFactory().getBooleanDecoder();
/* 564 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 565 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public int getAttributeAsInt(int index) throws XMLStreamException {
/* 570 */     ValueDecoderFactory.IntDecoder dec = _decoderFactory().getIntDecoder();
/* 571 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 572 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public long getAttributeAsLong(int index) throws XMLStreamException {
/* 577 */     ValueDecoderFactory.LongDecoder dec = _decoderFactory().getLongDecoder();
/* 578 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 579 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public float getAttributeAsFloat(int index) throws XMLStreamException {
/* 584 */     ValueDecoderFactory.FloatDecoder dec = _decoderFactory().getFloatDecoder();
/* 585 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 586 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public double getAttributeAsDouble(int index) throws XMLStreamException {
/* 591 */     ValueDecoderFactory.DoubleDecoder dec = _decoderFactory().getDoubleDecoder();
/* 592 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 593 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigInteger getAttributeAsInteger(int index) throws XMLStreamException {
/* 598 */     ValueDecoderFactory.IntegerDecoder dec = _decoderFactory().getIntegerDecoder();
/* 599 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 600 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public BigDecimal getAttributeAsDecimal(int index) throws XMLStreamException {
/* 605 */     ValueDecoderFactory.DecimalDecoder dec = _decoderFactory().getDecimalDecoder();
/* 606 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 607 */     return dec.getValue();
/*     */   }
/*     */   
/*     */   public QName getAttributeAsQName(int index) throws XMLStreamException {
/* 612 */     ValueDecoderFactory.QNameDecoder dec = _decoderFactory().getQNameDecoder(getNamespaceContext());
/* 613 */     getAttributeAs(index, (TypedValueDecoder)dec);
/* 614 */     return _verifyQName(dec.getValue());
/*     */   }
/*     */   
/*     */   public void getAttributeAs(int index, TypedValueDecoder tvd) throws XMLStreamException {
/* 619 */     if (this.mCurrToken != 1)
/* 620 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/*     */     try {
/* 623 */       this.mAttrCollector.decodeValue(index, tvd);
/* 624 */     } catch (IllegalArgumentException iae) {
/* 625 */       throw _constructTypeException(iae, this.mAttrCollector.getValue(index));
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getAttributeAsIntArray(int index) throws XMLStreamException {
/* 631 */     ValueDecoderFactory.IntArrayDecoder dec = _decoderFactory().getIntArrayDecoder();
/* 632 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 633 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public long[] getAttributeAsLongArray(int index) throws XMLStreamException {
/* 638 */     ValueDecoderFactory.LongArrayDecoder dec = _decoderFactory().getLongArrayDecoder();
/* 639 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 640 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public float[] getAttributeAsFloatArray(int index) throws XMLStreamException {
/* 645 */     ValueDecoderFactory.FloatArrayDecoder dec = _decoderFactory().getFloatArrayDecoder();
/* 646 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 647 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public double[] getAttributeAsDoubleArray(int index) throws XMLStreamException {
/* 652 */     ValueDecoderFactory.DoubleArrayDecoder dec = _decoderFactory().getDoubleArrayDecoder();
/* 653 */     getAttributeAsArray(index, (TypedArrayDecoder)dec);
/* 654 */     return dec.getValues();
/*     */   }
/*     */   
/*     */   public int getAttributeAsArray(int index, TypedArrayDecoder tad) throws XMLStreamException {
/* 665 */     if (this.mCurrToken != 1)
/* 666 */       throw new IllegalStateException(ErrorConsts.ERR_STATE_NOT_STELEM); 
/* 668 */     return this.mAttrCollector.decodeValues(index, tad, this);
/*     */   }
/*     */   
/*     */   public byte[] getAttributeAsBinary(int index) throws XMLStreamException {
/* 673 */     return getAttributeAsBinary(index, Base64Variants.getDefaultVariant());
/*     */   }
/*     */   
/*     */   public byte[] getAttributeAsBinary(int index, Base64Variant v) throws XMLStreamException {
/* 678 */     return this.mAttrCollector.decodeBinary(index, v, _base64Decoder(), this);
/*     */   }
/*     */   
/*     */   protected QName _verifyQName(QName n) throws TypedXMLStreamException {
/* 698 */     String ln = n.getLocalPart();
/* 699 */     int ix = WstxInputData.findIllegalNameChar(ln, this.mCfgNsEnabled, this.mXml11);
/* 700 */     if (ix >= 0) {
/* 701 */       String prefix = n.getPrefix();
/* 702 */       String pname = (prefix != null && prefix.length() > 0) ? (prefix + ":" + ln) : ln;
/* 704 */       throw _constructTypeException("Invalid local name \"" + ln + "\" (character at #" + ix + " is invalid)", pname);
/*     */     } 
/* 706 */     return n;
/*     */   }
/*     */   
/*     */   protected ValueDecoderFactory _decoderFactory() {
/* 711 */     if (this._decoderFactory == null)
/* 712 */       this._decoderFactory = new ValueDecoderFactory(); 
/* 714 */     return this._decoderFactory;
/*     */   }
/*     */   
/*     */   protected CharArrayBase64Decoder _base64Decoder() {
/* 719 */     if (this._base64Decoder == null)
/* 720 */       this._base64Decoder = new CharArrayBase64Decoder(); 
/* 722 */     return this._base64Decoder;
/*     */   }
/*     */   
/*     */   private void _handleEmptyValue(TypedValueDecoder dec) throws XMLStreamException {
/*     */     try {
/* 735 */       dec.handleEmptyValue();
/* 736 */     } catch (IllegalArgumentException iae) {
/* 737 */       throw _constructTypeException(iae, "");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected TypedXMLStreamException _constructTypeException(IllegalArgumentException iae, String lexicalValue) {
/* 751 */     return new TypedXMLStreamException(lexicalValue, iae.getMessage(), (Location)getStartLocation(), iae);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\TypedStreamReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */