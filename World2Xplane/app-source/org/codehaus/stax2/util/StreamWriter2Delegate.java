/*     */ package org.codehaus.stax2.util;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import org.codehaus.stax2.XMLStreamLocation2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public class StreamWriter2Delegate extends StreamWriterDelegate implements XMLStreamWriter2 {
/*     */   protected XMLStreamWriter2 mDelegate2;
/*     */   
/*     */   public StreamWriter2Delegate(XMLStreamWriter2 parent) {
/*  32 */     super((XMLStreamWriter)parent);
/*     */   }
/*     */   
/*     */   public void setParent(XMLStreamWriter w) {
/*  37 */     super.setParent(w);
/*  38 */     this.mDelegate2 = (XMLStreamWriter2)w;
/*     */   }
/*     */   
/*     */   public void closeCompletely() throws XMLStreamException {
/*  49 */     this.mDelegate2.closeCompletely();
/*     */   }
/*     */   
/*     */   public void copyEventFromReader(XMLStreamReader2 r, boolean preserveEventData) throws XMLStreamException {
/*  55 */     this.mDelegate2.copyEventFromReader(r, preserveEventData);
/*     */   }
/*     */   
/*     */   public String getEncoding() {
/*  59 */     return this.mDelegate2.getEncoding();
/*     */   }
/*     */   
/*     */   public XMLStreamLocation2 getLocation() {
/*  63 */     return this.mDelegate2.getLocation();
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/*  67 */     return this.mDelegate2.isPropertySupported(name);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/*  71 */     return this.mDelegate2.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void writeCData(char[] text, int start, int len) throws XMLStreamException {
/*  76 */     this.mDelegate2.writeCData(text, start, len);
/*     */   }
/*     */   
/*     */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws XMLStreamException {
/*  81 */     this.mDelegate2.writeDTD(rootName, systemId, publicId, internalSubset);
/*     */   }
/*     */   
/*     */   public void writeFullEndElement() throws XMLStreamException {
/*  85 */     this.mDelegate2.writeFullEndElement();
/*     */   }
/*     */   
/*     */   public void writeRaw(String text) throws XMLStreamException {
/*  89 */     this.mDelegate2.writeRaw(text);
/*     */   }
/*     */   
/*     */   public void writeRaw(String text, int offset, int length) throws XMLStreamException {
/*  95 */     this.mDelegate2.writeRaw(text, offset, length);
/*     */   }
/*     */   
/*     */   public void writeRaw(char[] text, int offset, int length) throws XMLStreamException {
/* 100 */     this.mDelegate2.writeRaw(text, offset, length);
/*     */   }
/*     */   
/*     */   public void writeSpace(String text) throws XMLStreamException {
/* 104 */     this.mDelegate2.writeSpace(text);
/*     */   }
/*     */   
/*     */   public void writeSpace(char[] text, int offset, int length) throws XMLStreamException {
/* 110 */     this.mDelegate2.writeSpace(text, offset, length);
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String version, String encoding, boolean standAlone) throws XMLStreamException {
/* 115 */     this.mDelegate2.writeStartDocument(version, encoding, standAlone);
/*     */   }
/*     */   
/*     */   public void writeBinary(byte[] value, int from, int length) throws XMLStreamException {
/* 121 */     this.mDelegate2.writeBinary(value, from, length);
/*     */   }
/*     */   
/*     */   public void writeBinary(Base64Variant v, byte[] value, int from, int length) throws XMLStreamException {
/* 127 */     this.mDelegate2.writeBinary(v, value, from, length);
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(String prefix, String namespaceURI, String localName, byte[] value) throws XMLStreamException {
/* 132 */     this.mDelegate2.writeBinaryAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(Base64Variant v, String prefix, String namespaceURI, String localName, byte[] value) throws XMLStreamException {
/* 137 */     this.mDelegate2.writeBinaryAttribute(v, prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean value) throws XMLStreamException {
/* 141 */     this.mDelegate2.writeBoolean(value);
/*     */   }
/*     */   
/*     */   public void writeBooleanAttribute(String prefix, String namespaceURI, String localName, boolean value) throws XMLStreamException {
/* 146 */     this.mDelegate2.writeBooleanAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeDecimal(BigDecimal value) throws XMLStreamException {
/* 150 */     this.mDelegate2.writeDecimal(value);
/*     */   }
/*     */   
/*     */   public void writeDecimalAttribute(String prefix, String namespaceURI, String localName, BigDecimal value) throws XMLStreamException {
/* 155 */     this.mDelegate2.writeDecimalAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeDouble(double value) throws XMLStreamException {
/* 159 */     this.mDelegate2.writeDouble(value);
/*     */   }
/*     */   
/*     */   public void writeDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 164 */     this.mDelegate2.writeDoubleArray(value, from, length);
/*     */   }
/*     */   
/*     */   public void writeDoubleArrayAttribute(String prefix, String namespaceURI, String localName, double[] value) throws XMLStreamException {
/* 169 */     this.mDelegate2.writeDoubleArrayAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeDoubleAttribute(String prefix, String namespaceURI, String localName, double value) throws XMLStreamException {
/* 174 */     this.mDelegate2.writeDoubleAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeFloat(float value) throws XMLStreamException {
/* 178 */     this.mDelegate2.writeFloat(value);
/*     */   }
/*     */   
/*     */   public void writeFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 184 */     this.mDelegate2.writeFloatArray(value, from, length);
/*     */   }
/*     */   
/*     */   public void writeFloatArrayAttribute(String prefix, String namespaceURI, String localName, float[] value) throws XMLStreamException {
/* 189 */     this.mDelegate2.writeFloatArrayAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeFloatAttribute(String prefix, String namespaceURI, String localName, float value) throws XMLStreamException {
/* 194 */     this.mDelegate2.writeFloatAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeInt(int value) throws XMLStreamException {
/* 198 */     this.mDelegate2.writeInt(value);
/*     */   }
/*     */   
/*     */   public void writeIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 203 */     this.mDelegate2.writeIntArray(value, from, length);
/*     */   }
/*     */   
/*     */   public void writeIntArrayAttribute(String prefix, String namespaceURI, String localName, int[] value) throws XMLStreamException {
/* 208 */     this.mDelegate2.writeIntArrayAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeIntAttribute(String prefix, String namespaceURI, String localName, int value) throws XMLStreamException {
/* 213 */     this.mDelegate2.writeIntAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeInteger(BigInteger value) throws XMLStreamException {
/* 217 */     this.mDelegate2.writeInteger(value);
/*     */   }
/*     */   
/*     */   public void writeIntegerAttribute(String prefix, String namespaceURI, String localName, BigInteger value) throws XMLStreamException {
/* 222 */     this.mDelegate2.writeIntegerAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeLong(long value) throws XMLStreamException {
/* 226 */     this.mDelegate2.writeLong(value);
/*     */   }
/*     */   
/*     */   public void writeLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 231 */     this.mDelegate2.writeLongArray(value, from, length);
/*     */   }
/*     */   
/*     */   public void writeLongArrayAttribute(String prefix, String namespaceURI, String localName, long[] value) throws XMLStreamException {
/* 236 */     this.mDelegate2.writeLongArrayAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeLongAttribute(String prefix, String namespaceURI, String localName, long value) throws XMLStreamException {
/* 241 */     this.mDelegate2.writeLongAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeQName(QName value) throws XMLStreamException {
/* 245 */     this.mDelegate2.writeQName(value);
/*     */   }
/*     */   
/*     */   public void writeQNameAttribute(String prefix, String namespaceURI, String localName, QName value) throws XMLStreamException {
/* 251 */     this.mDelegate2.writeQNameAttribute(prefix, namespaceURI, localName, value);
/*     */   }
/*     */   
/*     */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 255 */     return this.mDelegate2.setValidationProblemHandler(h);
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 261 */     return this.mDelegate2.stopValidatingAgainst(schema);
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 267 */     return this.mDelegate2.stopValidatingAgainst(validator);
/*     */   }
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 273 */     return this.mDelegate2.validateAgainst(schema);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax\\util\StreamWriter2Delegate.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */