/*     */ package org.codehaus.stax2.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.util.StreamReaderDelegate;
/*     */ import org.codehaus.stax2.AttributeInfo;
/*     */ import org.codehaus.stax2.DTDInfo;
/*     */ import org.codehaus.stax2.LocationInfo;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*     */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public class StreamReader2Delegate extends StreamReaderDelegate implements XMLStreamReader2 {
/*     */   protected XMLStreamReader2 mDelegate2;
/*     */   
/*     */   public StreamReader2Delegate(XMLStreamReader2 sr) {
/*  39 */     super((XMLStreamReader)sr);
/*  40 */     this.mDelegate2 = sr;
/*     */   }
/*     */   
/*     */   public void setParent(XMLStreamReader pr) {
/*  46 */     super.setParent(pr);
/*  47 */     this.mDelegate2 = (XMLStreamReader2)pr;
/*     */   }
/*     */   
/*     */   public void closeCompletely() throws XMLStreamException {
/*  57 */     this.mDelegate2.closeCompletely();
/*     */   }
/*     */   
/*     */   public AttributeInfo getAttributeInfo() throws XMLStreamException {
/*  61 */     return this.mDelegate2.getAttributeInfo();
/*     */   }
/*     */   
/*     */   public DTDInfo getDTDInfo() throws XMLStreamException {
/*  65 */     return this.mDelegate2.getDTDInfo();
/*     */   }
/*     */   
/*     */   public int getDepth() {
/*  69 */     return this.mDelegate2.getDepth();
/*     */   }
/*     */   
/*     */   public Object getFeature(String name) {
/*  73 */     return this.mDelegate2.getFeature(name);
/*     */   }
/*     */   
/*     */   public LocationInfo getLocationInfo() {
/*  77 */     return this.mDelegate2.getLocationInfo();
/*     */   }
/*     */   
/*     */   public NamespaceContext getNonTransientNamespaceContext() {
/*  81 */     return this.mDelegate2.getNonTransientNamespaceContext();
/*     */   }
/*     */   
/*     */   public String getPrefixedName() {
/*  85 */     return this.mDelegate2.getPrefixedName();
/*     */   }
/*     */   
/*     */   public int getText(Writer w, boolean preserveContents) throws IOException, XMLStreamException {
/*  91 */     return this.mDelegate2.getText(w, preserveContents);
/*     */   }
/*     */   
/*     */   public boolean isEmptyElement() throws XMLStreamException {
/*  97 */     return this.mDelegate2.isEmptyElement();
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/* 101 */     return this.mDelegate2.isPropertySupported(name);
/*     */   }
/*     */   
/*     */   public void setFeature(String name, Object value) {
/* 105 */     this.mDelegate2.setFeature(name, value);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/* 109 */     return this.mDelegate2.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void skipElement() throws XMLStreamException {
/* 113 */     this.mDelegate2.skipElement();
/*     */   }
/*     */   
/*     */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 123 */     return this.mDelegate2.setValidationProblemHandler(h);
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 129 */     return this.mDelegate2.stopValidatingAgainst(schema);
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 135 */     return this.mDelegate2.stopValidatingAgainst(validator);
/*     */   }
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 141 */     return this.mDelegate2.validateAgainst(schema);
/*     */   }
/*     */   
/*     */   public int getAttributeIndex(String namespaceURI, String localName) {
/* 151 */     return this.mDelegate2.getAttributeIndex(namespaceURI, localName);
/*     */   }
/*     */   
/*     */   public boolean getAttributeAsBoolean(int index) throws XMLStreamException {
/* 156 */     return this.mDelegate2.getAttributeAsBoolean(index);
/*     */   }
/*     */   
/*     */   public BigDecimal getAttributeAsDecimal(int index) throws XMLStreamException {
/* 162 */     return this.mDelegate2.getAttributeAsDecimal(index);
/*     */   }
/*     */   
/*     */   public double getAttributeAsDouble(int index) throws XMLStreamException {
/* 166 */     return this.mDelegate2.getAttributeAsDouble(index);
/*     */   }
/*     */   
/*     */   public float getAttributeAsFloat(int index) throws XMLStreamException {
/* 170 */     return this.mDelegate2.getAttributeAsFloat(index);
/*     */   }
/*     */   
/*     */   public int getAttributeAsInt(int index) throws XMLStreamException {
/* 174 */     return this.mDelegate2.getAttributeAsInt(index);
/*     */   }
/*     */   
/*     */   public BigInteger getAttributeAsInteger(int index) throws XMLStreamException {
/* 180 */     return this.mDelegate2.getAttributeAsInteger(index);
/*     */   }
/*     */   
/*     */   public long getAttributeAsLong(int index) throws XMLStreamException {
/* 184 */     return this.mDelegate2.getAttributeAsLong(index);
/*     */   }
/*     */   
/*     */   public QName getAttributeAsQName(int index) throws XMLStreamException {
/* 188 */     return this.mDelegate2.getAttributeAsQName(index);
/*     */   }
/*     */   
/*     */   public int[] getAttributeAsIntArray(int index) throws XMLStreamException {
/* 192 */     return this.mDelegate2.getAttributeAsIntArray(index);
/*     */   }
/*     */   
/*     */   public long[] getAttributeAsLongArray(int index) throws XMLStreamException {
/* 196 */     return this.mDelegate2.getAttributeAsLongArray(index);
/*     */   }
/*     */   
/*     */   public float[] getAttributeAsFloatArray(int index) throws XMLStreamException {
/* 200 */     return this.mDelegate2.getAttributeAsFloatArray(index);
/*     */   }
/*     */   
/*     */   public double[] getAttributeAsDoubleArray(int index) throws XMLStreamException {
/* 204 */     return this.mDelegate2.getAttributeAsDoubleArray(index);
/*     */   }
/*     */   
/*     */   public void getElementAs(TypedValueDecoder tvd) throws XMLStreamException {
/* 208 */     this.mDelegate2.getElementAs(tvd);
/*     */   }
/*     */   
/*     */   public boolean getElementAsBoolean() throws XMLStreamException {
/* 212 */     return this.mDelegate2.getElementAsBoolean();
/*     */   }
/*     */   
/*     */   public BigDecimal getElementAsDecimal() throws XMLStreamException {
/* 216 */     return this.mDelegate2.getElementAsDecimal();
/*     */   }
/*     */   
/*     */   public double getElementAsDouble() throws XMLStreamException {
/* 220 */     return this.mDelegate2.getElementAsDouble();
/*     */   }
/*     */   
/*     */   public float getElementAsFloat() throws XMLStreamException {
/* 224 */     return this.mDelegate2.getElementAsFloat();
/*     */   }
/*     */   
/*     */   public int getElementAsInt() throws XMLStreamException {
/* 228 */     return this.mDelegate2.getElementAsInt();
/*     */   }
/*     */   
/*     */   public BigInteger getElementAsInteger() throws XMLStreamException {
/* 232 */     return this.mDelegate2.getElementAsInteger();
/*     */   }
/*     */   
/*     */   public long getElementAsLong() throws XMLStreamException {
/* 236 */     return this.mDelegate2.getElementAsLong();
/*     */   }
/*     */   
/*     */   public QName getElementAsQName() throws XMLStreamException {
/* 240 */     return this.mDelegate2.getElementAsQName();
/*     */   }
/*     */   
/*     */   public byte[] getElementAsBinary() throws XMLStreamException {
/* 244 */     return this.mDelegate2.getElementAsBinary();
/*     */   }
/*     */   
/*     */   public byte[] getElementAsBinary(Base64Variant v) throws XMLStreamException {
/* 248 */     return this.mDelegate2.getElementAsBinary(v);
/*     */   }
/*     */   
/*     */   public void getAttributeAs(int index, TypedValueDecoder tvd) throws XMLStreamException {
/* 254 */     this.mDelegate2.getAttributeAs(index, tvd);
/*     */   }
/*     */   
/*     */   public int getAttributeAsArray(int index, TypedArrayDecoder tad) throws XMLStreamException {
/* 260 */     return this.mDelegate2.getAttributeAsArray(index, tad);
/*     */   }
/*     */   
/*     */   public byte[] getAttributeAsBinary(int index) throws XMLStreamException {
/* 265 */     return this.mDelegate2.getAttributeAsBinary(index);
/*     */   }
/*     */   
/*     */   public byte[] getAttributeAsBinary(int index, Base64Variant v) throws XMLStreamException {
/* 270 */     return this.mDelegate2.getAttributeAsBinary(index, v);
/*     */   }
/*     */   
/*     */   public int readElementAsDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 276 */     return this.mDelegate2.readElementAsDoubleArray(value, from, length);
/*     */   }
/*     */   
/*     */   public int readElementAsFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 282 */     return this.mDelegate2.readElementAsFloatArray(value, from, length);
/*     */   }
/*     */   
/*     */   public int readElementAsIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 287 */     return this.mDelegate2.readElementAsIntArray(value, from, length);
/*     */   }
/*     */   
/*     */   public int readElementAsLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 293 */     return this.mDelegate2.readElementAsLongArray(value, from, length);
/*     */   }
/*     */   
/*     */   public int readElementAsArray(TypedArrayDecoder tad) throws XMLStreamException {
/* 299 */     return this.mDelegate2.readElementAsArray(tad);
/*     */   }
/*     */   
/*     */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength) throws XMLStreamException {
/* 305 */     return this.mDelegate2.readElementAsBinary(resultBuffer, offset, maxLength);
/*     */   }
/*     */   
/*     */   public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength, Base64Variant v) throws XMLStreamException {
/* 312 */     return this.mDelegate2.readElementAsBinary(resultBuffer, offset, maxLength, v);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax\\util\StreamReader2Delegate.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */