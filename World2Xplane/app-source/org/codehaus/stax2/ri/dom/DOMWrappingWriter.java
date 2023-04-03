/*     */ package org.codehaus.stax2.ri.dom;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.text.MessageFormat;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.XMLStreamLocation2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.ri.typed.SimpleValueEncoder;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.typed.Base64Variants;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public abstract class DOMWrappingWriter implements XMLStreamWriter2 {
/*     */   static final String DEFAULT_OUTPUT_ENCODING = "UTF-8";
/*     */   
/*     */   static final String DEFAULT_XML_VERSION = "1.0";
/*     */   
/*     */   protected final boolean mNsAware;
/*     */   
/*     */   protected final boolean mNsRepairing;
/*     */   
/*  79 */   protected String mEncoding = null;
/*     */   
/*     */   protected NamespaceContext mNsContext;
/*     */   
/*     */   protected final Document mDocument;
/*     */   
/*     */   protected SimpleValueEncoder mValueEncoder;
/*     */   
/*     */   protected DOMWrappingWriter(Node treeRoot, boolean nsAware, boolean nsRepairing) throws XMLStreamException {
/* 121 */     if (treeRoot == null)
/* 122 */       throw new IllegalArgumentException("Can not pass null Node for constructing a DOM-based XMLStreamWriter"); 
/* 124 */     this.mNsAware = nsAware;
/* 125 */     this.mNsRepairing = nsRepairing;
/* 130 */     switch (treeRoot.getNodeType()) {
/*     */       case 9:
/* 132 */         this.mDocument = (Document)treeRoot;
/*     */         break;
/*     */       case 1:
/* 140 */         this.mDocument = treeRoot.getOwnerDocument();
/*     */         break;
/*     */       case 11:
/* 144 */         this.mDocument = treeRoot.getOwnerDocument();
/*     */         break;
/*     */       default:
/* 149 */         throw new XMLStreamException("Can not create an XMLStreamWriter for a DOM node of type " + treeRoot.getClass());
/*     */     } 
/* 151 */     if (this.mDocument == null)
/* 152 */       throw new XMLStreamException("Can not create an XMLStreamWriter for given node (of type " + treeRoot.getClass() + "): did not have owner document"); 
/*     */   }
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public void flush() {}
/*     */   
/*     */   public abstract NamespaceContext getNamespaceContext();
/*     */   
/*     */   public abstract String getPrefix(String paramString);
/*     */   
/*     */   public abstract Object getProperty(String paramString);
/*     */   
/*     */   public abstract void setDefaultNamespace(String paramString);
/*     */   
/*     */   public void setNamespaceContext(NamespaceContext context) {
/* 176 */     this.mNsContext = context;
/*     */   }
/*     */   
/*     */   public abstract void setPrefix(String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeAttribute(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
/*     */   
/*     */   public void writeCData(String data) throws XMLStreamException {
/* 192 */     appendLeaf(this.mDocument.createCDATASection(data));
/*     */   }
/*     */   
/*     */   public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
/* 198 */     writeCharacters(new String(text, start, len));
/*     */   }
/*     */   
/*     */   public void writeCharacters(String text) throws XMLStreamException {
/* 204 */     appendLeaf(this.mDocument.createTextNode(text));
/*     */   }
/*     */   
/*     */   public void writeComment(String data) throws XMLStreamException {
/* 210 */     appendLeaf(this.mDocument.createCDATASection(data));
/*     */   }
/*     */   
/*     */   public abstract void writeDefaultNamespace(String paramString) throws XMLStreamException;
/*     */   
/*     */   public void writeDTD(String dtd) throws XMLStreamException {
/* 222 */     reportUnsupported("writeDTD()");
/*     */   }
/*     */   
/*     */   public abstract void writeEmptyElement(String paramString) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeEmptyElement(String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeEmptyElement(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeEndDocument() throws XMLStreamException;
/*     */   
/*     */   public void writeEntityRef(String name) throws XMLStreamException {
/* 236 */     appendLeaf(this.mDocument.createEntityReference(name));
/*     */   }
/*     */   
/*     */   public void writeProcessingInstruction(String target) throws XMLStreamException {
/* 242 */     writeProcessingInstruction(target, (String)null);
/*     */   }
/*     */   
/*     */   public void writeProcessingInstruction(String target, String data) throws XMLStreamException {
/* 248 */     appendLeaf(this.mDocument.createProcessingInstruction(target, data));
/*     */   }
/*     */   
/*     */   public void writeStartDocument() throws XMLStreamException {
/* 257 */     writeStartDocument("UTF-8", "1.0");
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String version) throws XMLStreamException {
/* 263 */     writeStartDocument((String)null, version);
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String encoding, String version) throws XMLStreamException {
/* 270 */     this.mEncoding = encoding;
/*     */   }
/*     */   
/*     */   public XMLStreamLocation2 getLocation() {
/* 283 */     return null;
/*     */   }
/*     */   
/*     */   public String getEncoding() {
/* 287 */     return this.mEncoding;
/*     */   }
/*     */   
/*     */   public abstract boolean isPropertySupported(String paramString);
/*     */   
/*     */   public abstract boolean setProperty(String paramString, Object paramObject);
/*     */   
/*     */   public void writeCData(char[] text, int start, int len) throws XMLStreamException {
/* 303 */     writeCData(new String(text, start, len));
/*     */   }
/*     */   
/*     */   public abstract void writeDTD(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
/*     */   
/*     */   public void writeFullEndElement() throws XMLStreamException {
/* 315 */     writeEndElement();
/*     */   }
/*     */   
/*     */   public void writeSpace(char[] text, int start, int len) throws XMLStreamException {
/* 321 */     writeSpace(new String(text, start, len));
/*     */   }
/*     */   
/*     */   public void writeSpace(String text) throws XMLStreamException {
/* 332 */     writeCharacters(text);
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String version, String encoding, boolean standAlone) throws XMLStreamException {
/* 338 */     writeStartDocument(encoding, version);
/*     */   }
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 351 */     return null;
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 358 */     return null;
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 365 */     return null;
/*     */   }
/*     */   
/*     */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 371 */     return null;
/*     */   }
/*     */   
/*     */   public void writeRaw(String text) throws XMLStreamException {
/* 383 */     reportUnsupported("writeRaw()");
/*     */   }
/*     */   
/*     */   public void writeRaw(String text, int start, int offset) throws XMLStreamException {
/* 389 */     reportUnsupported("writeRaw()");
/*     */   }
/*     */   
/*     */   public void writeRaw(char[] text, int offset, int length) throws XMLStreamException {
/* 395 */     reportUnsupported("writeRaw()");
/*     */   }
/*     */   
/*     */   public void copyEventFromReader(XMLStreamReader2 r, boolean preserveEventData) throws XMLStreamException {}
/*     */   
/*     */   public void closeCompletely() {}
/*     */   
/*     */   public void writeBoolean(boolean value) throws XMLStreamException {
/* 426 */     writeCharacters(value ? "true" : "false");
/*     */   }
/*     */   
/*     */   public void writeInt(int value) throws XMLStreamException {
/* 431 */     writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeLong(long value) throws XMLStreamException {
/* 436 */     writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeFloat(float value) throws XMLStreamException {
/* 441 */     writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeDouble(double value) throws XMLStreamException {
/* 446 */     writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeInteger(BigInteger value) throws XMLStreamException {
/* 451 */     writeCharacters(value.toString());
/*     */   }
/*     */   
/*     */   public void writeDecimal(BigDecimal value) throws XMLStreamException {
/* 456 */     writeCharacters(value.toString());
/*     */   }
/*     */   
/*     */   public void writeQName(QName name) throws XMLStreamException {
/* 461 */     writeCharacters(serializeQNameValue(name));
/*     */   }
/*     */   
/*     */   public void writeIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 470 */     writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 477 */     writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 484 */     writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 491 */     writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeBinary(byte[] value, int from, int length) throws XMLStreamException {
/* 497 */     writeBinary(Base64Variants.getDefaultVariant(), value, from, length);
/*     */   }
/*     */   
/*     */   public void writeBinary(Base64Variant v, byte[] value, int from, int length) throws XMLStreamException {
/* 503 */     writeCharacters(getValueEncoder().encodeAsString(v, value, from, length));
/*     */   }
/*     */   
/*     */   public void writeBooleanAttribute(String prefix, String nsURI, String localName, boolean value) throws XMLStreamException {
/* 511 */     writeAttribute(prefix, nsURI, localName, value ? "true" : "false");
/*     */   }
/*     */   
/*     */   public void writeIntAttribute(String prefix, String nsURI, String localName, int value) throws XMLStreamException {
/* 517 */     writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeLongAttribute(String prefix, String nsURI, String localName, long value) throws XMLStreamException {
/* 523 */     writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeFloatAttribute(String prefix, String nsURI, String localName, float value) throws XMLStreamException {
/* 529 */     writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeDoubleAttribute(String prefix, String nsURI, String localName, double value) throws XMLStreamException {
/* 535 */     writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeIntegerAttribute(String prefix, String nsURI, String localName, BigInteger value) throws XMLStreamException {
/* 541 */     writeAttribute(prefix, nsURI, localName, value.toString());
/*     */   }
/*     */   
/*     */   public void writeDecimalAttribute(String prefix, String nsURI, String localName, BigDecimal value) throws XMLStreamException {
/* 547 */     writeAttribute(prefix, nsURI, localName, value.toString());
/*     */   }
/*     */   
/*     */   public void writeQNameAttribute(String prefix, String nsURI, String localName, QName name) throws XMLStreamException {
/* 553 */     writeAttribute(prefix, nsURI, localName, serializeQNameValue(name));
/*     */   }
/*     */   
/*     */   public void writeIntArrayAttribute(String prefix, String nsURI, String localName, int[] value) throws XMLStreamException {
/* 559 */     writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeLongArrayAttribute(String prefix, String nsURI, String localName, long[] value) throws XMLStreamException {
/* 565 */     writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeFloatArrayAttribute(String prefix, String nsURI, String localName, float[] value) throws XMLStreamException {
/* 571 */     writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeDoubleArrayAttribute(String prefix, String nsURI, String localName, double[] value) throws XMLStreamException {
/* 577 */     writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(String prefix, String nsURI, String localName, byte[] value) throws XMLStreamException {
/* 583 */     writeBinaryAttribute(Base64Variants.getDefaultVariant(), prefix, nsURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(Base64Variant v, String prefix, String nsURI, String localName, byte[] value) throws XMLStreamException {
/* 588 */     writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(v, value, 0, value.length));
/*     */   }
/*     */   
/*     */   protected abstract void appendLeaf(Node paramNode) throws IllegalStateException;
/*     */   
/*     */   protected String serializeQNameValue(QName name) throws XMLStreamException {
/*     */     String prefix;
/* 618 */     if (this.mNsRepairing) {
/* 619 */       String uri = name.getNamespaceURI();
/* 621 */       NamespaceContext ctxt = getNamespaceContext();
/* 622 */       prefix = (ctxt == null) ? null : ctxt.getPrefix(uri);
/* 623 */       if (prefix == null) {
/* 625 */         String origPrefix = name.getPrefix();
/* 626 */         if (origPrefix == null || origPrefix.length() == 0) {
/* 627 */           prefix = "";
/* 632 */           writeDefaultNamespace(uri);
/*     */         } else {
/* 634 */           prefix = origPrefix;
/* 635 */           writeNamespace(prefix, uri);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 639 */       prefix = name.getPrefix();
/*     */     } 
/* 641 */     String local = name.getLocalPart();
/* 642 */     if (prefix == null || prefix.length() == 0)
/* 643 */       return local; 
/* 647 */     return prefix + ":" + local;
/*     */   }
/*     */   
/*     */   protected SimpleValueEncoder getValueEncoder() {
/* 652 */     if (this.mValueEncoder == null)
/* 653 */       this.mValueEncoder = new SimpleValueEncoder(); 
/* 655 */     return this.mValueEncoder;
/*     */   }
/*     */   
/*     */   protected static void throwOutputError(String msg) throws XMLStreamException {
/* 667 */     throw new XMLStreamException(msg);
/*     */   }
/*     */   
/*     */   protected static void throwOutputError(String format, Object arg) throws XMLStreamException {
/* 673 */     String msg = MessageFormat.format(format, new Object[] { arg });
/* 674 */     throwOutputError(msg);
/*     */   }
/*     */   
/*     */   protected void reportUnsupported(String operName) {
/* 679 */     throw new UnsupportedOperationException(operName + " can not be used with DOM-backed writer");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\dom\DOMWrappingWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */