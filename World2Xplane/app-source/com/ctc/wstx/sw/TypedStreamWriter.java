/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.api.WriterConfig;
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import java.io.IOException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.ri.typed.AsciiValueEncoder;
/*     */ import org.codehaus.stax2.ri.typed.ValueEncoderFactory;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.typed.Base64Variants;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class TypedStreamWriter extends BaseStreamWriter {
/*     */   protected ValueEncoderFactory mValueEncoderFactory;
/*     */   
/*     */   protected TypedStreamWriter(XmlWriter xw, String enc, WriterConfig cfg) {
/*  58 */     super(xw, enc, cfg);
/*     */   }
/*     */   
/*     */   protected final ValueEncoderFactory valueEncoderFactory() {
/*  63 */     if (this.mValueEncoderFactory == null)
/*  64 */       this.mValueEncoderFactory = new ValueEncoderFactory(); 
/*  66 */     return this.mValueEncoderFactory;
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean value) throws XMLStreamException {
/*  81 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeInt(int value) throws XMLStreamException {
/*  87 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeLong(long value) throws XMLStreamException {
/*  93 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeFloat(float value) throws XMLStreamException {
/*  99 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeDouble(double value) throws XMLStreamException {
/* 105 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeInteger(BigInteger value) throws XMLStreamException {
/* 115 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getScalarEncoder(value.toString()));
/*     */   }
/*     */   
/*     */   public void writeDecimal(BigDecimal value) throws XMLStreamException {
/* 124 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getScalarEncoder(value.toString()));
/*     */   }
/*     */   
/*     */   public void writeQName(QName name) throws XMLStreamException {
/* 133 */     writeCharacters(serializeQName(name));
/*     */   }
/*     */   
/*     */   public final void writeIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 139 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 145 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 151 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 157 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeBinary(byte[] value, int from, int length) throws XMLStreamException {
/* 163 */     Base64Variant v = Base64Variants.getDefaultVariant();
/* 164 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(v, value, from, length));
/*     */   }
/*     */   
/*     */   public void writeBinary(Base64Variant v, byte[] value, int from, int length) throws XMLStreamException {
/* 170 */     writeTypedElement((AsciiValueEncoder)valueEncoderFactory().getEncoder(v, value, from, length));
/*     */   }
/*     */   
/*     */   protected final void writeTypedElement(AsciiValueEncoder enc) throws XMLStreamException {
/* 176 */     if (this.mStartElementOpen)
/* 177 */       closeStartElement(this.mEmptyElement); 
/* 180 */     if (this.mCheckStructure && 
/* 181 */       inPrologOrEpilog())
/* 182 */       reportNwfStructure(ErrorConsts.WERR_PROLOG_NONWS_TEXT); 
/* 186 */     if (this.mVldContent <= 1)
/* 187 */       reportInvalidContent(4); 
/*     */     try {
/* 192 */       XMLValidator vld = (this.mVldContent == 3) ? this.mValidator : null;
/* 194 */       if (vld == null) {
/* 195 */         this.mWriter.writeTypedElement(enc);
/*     */       } else {
/* 197 */         this.mWriter.writeTypedElement(enc, vld, getCopyBuffer());
/*     */       } 
/* 199 */     } catch (IOException ioe) {
/* 200 */       throw new WstxIOException(ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeBooleanAttribute(String prefix, String nsURI, String localName, boolean value) throws XMLStreamException {
/* 209 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeIntAttribute(String prefix, String nsURI, String localName, int value) throws XMLStreamException {
/* 216 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeLongAttribute(String prefix, String nsURI, String localName, long value) throws XMLStreamException {
/* 223 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeFloatAttribute(String prefix, String nsURI, String localName, float value) throws XMLStreamException {
/* 230 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeDoubleAttribute(String prefix, String nsURI, String localName, double value) throws XMLStreamException {
/* 237 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value));
/*     */   }
/*     */   
/*     */   public void writeIntegerAttribute(String prefix, String nsURI, String localName, BigInteger value) throws XMLStreamException {
/* 245 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getScalarEncoder(value.toString()));
/*     */   }
/*     */   
/*     */   public void writeDecimalAttribute(String prefix, String nsURI, String localName, BigDecimal value) throws XMLStreamException {
/* 253 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getScalarEncoder(value.toString()));
/*     */   }
/*     */   
/*     */   public void writeQNameAttribute(String prefix, String nsURI, String localName, QName name) throws XMLStreamException {
/* 263 */     writeAttribute(prefix, nsURI, localName, serializeQName(name));
/*     */   }
/*     */   
/*     */   public void writeIntArrayAttribute(String prefix, String nsURI, String localName, int[] value) throws XMLStreamException {
/* 269 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeLongArrayAttribute(String prefix, String nsURI, String localName, long[] value) throws XMLStreamException {
/* 276 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeFloatArrayAttribute(String prefix, String nsURI, String localName, float[] value) throws XMLStreamException {
/* 283 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeDoubleArrayAttribute(String prefix, String nsURI, String localName, double[] value) throws XMLStreamException {
/* 290 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(String prefix, String nsURI, String localName, byte[] value) throws XMLStreamException {
/* 297 */     Base64Variant v = Base64Variants.getDefaultVariant();
/* 298 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(v, value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(Base64Variant v, String prefix, String nsURI, String localName, byte[] value) throws XMLStreamException {
/* 305 */     writeTypedAttribute(prefix, nsURI, localName, (AsciiValueEncoder)valueEncoderFactory().getEncoder(v, value, 0, value.length));
/*     */   }
/*     */   
/*     */   protected abstract void writeTypedAttribute(String paramString1, String paramString2, String paramString3, AsciiValueEncoder paramAsciiValueEncoder) throws XMLStreamException;
/*     */   
/*     */   private String serializeQName(QName name) throws XMLStreamException {
/* 321 */     String vp = validateQNamePrefix(name);
/* 322 */     String local = name.getLocalPart();
/* 323 */     if (vp == null || vp.length() == 0)
/* 324 */       return local; 
/* 328 */     return vp + ":" + local;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\TypedStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */