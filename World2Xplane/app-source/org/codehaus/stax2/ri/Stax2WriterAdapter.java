/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import org.codehaus.stax2.DTDInfo;
/*     */ import org.codehaus.stax2.XMLStreamLocation2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.ri.typed.SimpleValueEncoder;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ import org.codehaus.stax2.typed.Base64Variants;
/*     */ import org.codehaus.stax2.util.StreamWriterDelegate;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public class Stax2WriterAdapter extends StreamWriterDelegate implements XMLStreamWriter2, XMLStreamConstants {
/*     */   protected String mEncoding;
/*     */   
/*     */   protected SimpleValueEncoder mValueEncoder;
/*     */   
/*     */   protected final boolean mNsRepairing;
/*     */   
/*     */   protected Stax2WriterAdapter(XMLStreamWriter sw) {
/*  75 */     super(sw);
/*  76 */     this.mDelegate = sw;
/*  77 */     Object value = sw.getProperty("javax.xml.stream.isRepairingNamespaces");
/*  78 */     this.mNsRepairing = (value instanceof Boolean && ((Boolean)value).booleanValue());
/*     */   }
/*     */   
/*     */   public static XMLStreamWriter2 wrapIfNecessary(XMLStreamWriter sw) {
/*  91 */     if (sw instanceof XMLStreamWriter2)
/*  92 */       return (XMLStreamWriter2)sw; 
/*  94 */     return new Stax2WriterAdapter(sw);
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean b) throws XMLStreamException {
/* 108 */     this.mDelegate.writeCharacters(b ? "true" : "false");
/*     */   }
/*     */   
/*     */   public void writeInt(int value) throws XMLStreamException {
/* 113 */     this.mDelegate.writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeLong(long value) throws XMLStreamException {
/* 118 */     this.mDelegate.writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeFloat(float value) throws XMLStreamException {
/* 123 */     this.mDelegate.writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeDouble(double value) throws XMLStreamException {
/* 128 */     this.mDelegate.writeCharacters(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeInteger(BigInteger value) throws XMLStreamException {
/* 133 */     this.mDelegate.writeCharacters(value.toString());
/*     */   }
/*     */   
/*     */   public void writeDecimal(BigDecimal value) throws XMLStreamException {
/* 138 */     this.mDelegate.writeCharacters(value.toString());
/*     */   }
/*     */   
/*     */   public void writeQName(QName name) throws XMLStreamException {
/* 143 */     this.mDelegate.writeCharacters(serializeQNameValue(name));
/*     */   }
/*     */   
/*     */   public void writeIntArray(int[] value, int from, int length) throws XMLStreamException {
/* 149 */     this.mDelegate.writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeLongArray(long[] value, int from, int length) throws XMLStreamException {
/* 155 */     this.mDelegate.writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeFloatArray(float[] value, int from, int length) throws XMLStreamException {
/* 161 */     this.mDelegate.writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeDoubleArray(double[] value, int from, int length) throws XMLStreamException {
/* 167 */     this.mDelegate.writeCharacters(getValueEncoder().encodeAsString(value, from, length));
/*     */   }
/*     */   
/*     */   public void writeBinary(Base64Variant v, byte[] value, int from, int length) throws XMLStreamException {
/* 173 */     this.mDelegate.writeCharacters(getValueEncoder().encodeAsString(v, value, from, length));
/*     */   }
/*     */   
/*     */   public void writeBinary(byte[] value, int from, int length) throws XMLStreamException {
/* 179 */     writeBinary(Base64Variants.getDefaultVariant(), value, from, length);
/*     */   }
/*     */   
/*     */   public void writeBooleanAttribute(String prefix, String nsURI, String localName, boolean b) throws XMLStreamException {
/* 186 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, b ? "true" : "false");
/*     */   }
/*     */   
/*     */   public void writeIntAttribute(String prefix, String nsURI, String localName, int value) throws XMLStreamException {
/* 191 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeLongAttribute(String prefix, String nsURI, String localName, long value) throws XMLStreamException {
/* 196 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeFloatAttribute(String prefix, String nsURI, String localName, float value) throws XMLStreamException {
/* 201 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeDoubleAttribute(String prefix, String nsURI, String localName, double value) throws XMLStreamException {
/* 206 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, String.valueOf(value));
/*     */   }
/*     */   
/*     */   public void writeIntegerAttribute(String prefix, String nsURI, String localName, BigInteger value) throws XMLStreamException {
/* 211 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, value.toString());
/*     */   }
/*     */   
/*     */   public void writeDecimalAttribute(String prefix, String nsURI, String localName, BigDecimal value) throws XMLStreamException {
/* 216 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, value.toString());
/*     */   }
/*     */   
/*     */   public void writeQNameAttribute(String prefix, String nsURI, String localName, QName name) throws XMLStreamException {
/* 221 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, serializeQNameValue(name));
/*     */   }
/*     */   
/*     */   public void writeIntArrayAttribute(String prefix, String nsURI, String localName, int[] value) throws XMLStreamException {
/* 226 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeLongArrayAttribute(String prefix, String nsURI, String localName, long[] value) throws XMLStreamException {
/* 232 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeFloatArrayAttribute(String prefix, String nsURI, String localName, float[] value) throws XMLStreamException {
/* 238 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeDoubleArrayAttribute(String prefix, String nsURI, String localName, double[] value) throws XMLStreamException {
/* 244 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(value, 0, value.length));
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(String prefix, String nsURI, String localName, byte[] value) throws XMLStreamException {
/* 250 */     writeBinaryAttribute(Base64Variants.getDefaultVariant(), prefix, nsURI, localName, value);
/*     */   }
/*     */   
/*     */   public void writeBinaryAttribute(Base64Variant v, String prefix, String nsURI, String localName, byte[] value) throws XMLStreamException {
/* 255 */     this.mDelegate.writeAttribute(prefix, nsURI, localName, getValueEncoder().encodeAsString(v, value, 0, value.length));
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/* 270 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/* 275 */     throw new IllegalArgumentException("No settable property '" + name + "'");
/*     */   }
/*     */   
/*     */   public XMLStreamLocation2 getLocation() {
/* 281 */     return null;
/*     */   }
/*     */   
/*     */   public String getEncoding() {
/* 287 */     return this.mEncoding;
/*     */   }
/*     */   
/*     */   public void writeCData(char[] text, int start, int len) throws XMLStreamException {
/* 293 */     writeCData(new String(text, start, len));
/*     */   }
/*     */   
/*     */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws XMLStreamException {
/* 303 */     StringBuffer sb = new StringBuffer();
/* 304 */     sb.append("<!DOCTYPE");
/* 305 */     sb.append(rootName);
/* 306 */     if (systemId != null) {
/* 307 */       if (publicId != null) {
/* 308 */         sb.append(" PUBLIC \"");
/* 309 */         sb.append(publicId);
/* 310 */         sb.append("\" \"");
/*     */       } else {
/* 312 */         sb.append(" SYSTEM \"");
/*     */       } 
/* 314 */       sb.append(systemId);
/* 315 */       sb.append('"');
/*     */     } 
/* 318 */     if (internalSubset != null && internalSubset.length() > 0) {
/* 319 */       sb.append(" [");
/* 320 */       sb.append(internalSubset);
/* 321 */       sb.append(']');
/*     */     } 
/* 323 */     sb.append('>');
/* 324 */     writeDTD(sb.toString());
/*     */   }
/*     */   
/*     */   public void writeFullEndElement() throws XMLStreamException {
/* 334 */     this.mDelegate.writeCharacters("");
/* 335 */     this.mDelegate.writeEndElement();
/*     */   }
/*     */   
/*     */   public void writeSpace(String text) throws XMLStreamException {
/* 346 */     writeRaw(text);
/*     */   }
/*     */   
/*     */   public void writeSpace(char[] text, int offset, int length) throws XMLStreamException {
/* 353 */     writeRaw(text, offset, length);
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String version, String encoding, boolean standAlone) throws XMLStreamException {
/* 361 */     writeStartDocument(encoding, version);
/*     */   }
/*     */   
/*     */   public void writeRaw(String text) throws XMLStreamException {
/* 373 */     writeRaw(text, 0, text.length());
/*     */   }
/*     */   
/*     */   public void writeRaw(String text, int offset, int len) throws XMLStreamException {
/* 380 */     throw new UnsupportedOperationException("Not implemented");
/*     */   }
/*     */   
/*     */   public void writeRaw(char[] text, int offset, int length) throws XMLStreamException {
/* 386 */     writeRaw(new String(text, offset, length));
/*     */   }
/*     */   
/*     */   public void copyEventFromReader(XMLStreamReader2 sr, boolean preserveEventData) throws XMLStreamException {
/*     */     String version;
/*     */     DTDInfo info;
/* 392 */     switch (sr.getEventType()) {
/*     */       case 7:
/* 395 */         version = sr.getVersion();
/* 400 */         if (version != null && version.length() != 0)
/* 403 */           if (sr.standaloneSet()) {
/* 404 */             writeStartDocument(sr.getVersion(), sr.getCharacterEncodingScheme(), sr.isStandalone());
/*     */           } else {
/* 408 */             writeStartDocument(sr.getCharacterEncodingScheme(), sr.getVersion());
/*     */           }  
/*     */         return;
/*     */       case 8:
/* 416 */         writeEndDocument();
/*     */         return;
/*     */       case 1:
/* 425 */         copyStartElement((XMLStreamReader)sr);
/*     */         return;
/*     */       case 2:
/* 429 */         writeEndElement();
/*     */         return;
/*     */       case 6:
/* 433 */         writeSpace(sr.getTextCharacters(), sr.getTextStart(), sr.getTextLength());
/*     */         return;
/*     */       case 12:
/* 437 */         writeCData(sr.getTextCharacters(), sr.getTextStart(), sr.getTextLength());
/*     */         return;
/*     */       case 4:
/* 441 */         writeCharacters(sr.getTextCharacters(), sr.getTextStart(), sr.getTextLength());
/*     */         return;
/*     */       case 5:
/* 445 */         writeComment(sr.getText());
/*     */         return;
/*     */       case 3:
/* 449 */         writeProcessingInstruction(sr.getPITarget(), sr.getPIData());
/*     */         return;
/*     */       case 11:
/* 454 */         info = sr.getDTDInfo();
/* 455 */         if (info == null)
/* 459 */           throw new XMLStreamException("Current state DOCTYPE, but not DTDInfo Object returned -- reader doesn't support DTDs?"); 
/* 461 */         writeDTD(info.getDTDRootName(), info.getDTDSystemId(), info.getDTDPublicId(), info.getDTDInternalSubset());
/*     */         return;
/*     */       case 9:
/* 467 */         writeEntityRef(sr.getLocalName());
/*     */         return;
/*     */     } 
/* 476 */     throw new XMLStreamException("Unrecognized event type (" + sr.getEventType() + "); not sure how to copy");
/*     */   }
/*     */   
/*     */   public void closeCompletely() throws XMLStreamException {
/* 492 */     close();
/*     */   }
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 505 */     throw new UnsupportedOperationException("Not yet implemented");
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 511 */     return null;
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 517 */     return null;
/*     */   }
/*     */   
/*     */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 525 */     return null;
/*     */   }
/*     */   
/*     */   protected void copyStartElement(XMLStreamReader sr) throws XMLStreamException {
/* 538 */     int nsCount = sr.getNamespaceCount();
/* 539 */     if (nsCount > 0)
/* 544 */       for (int i = 0; i < nsCount; i++) {
/* 545 */         String prefix = sr.getNamespacePrefix(i);
/* 546 */         String uri = sr.getNamespaceURI(i);
/* 547 */         if (prefix == null || prefix.length() == 0) {
/* 548 */           setDefaultNamespace(uri);
/*     */         } else {
/* 550 */           setPrefix(prefix, uri);
/*     */         } 
/*     */       }  
/* 554 */     writeStartElement(sr.getPrefix(), sr.getLocalName(), sr.getNamespaceURI());
/* 556 */     if (nsCount > 0)
/* 558 */       for (int i = 0; i < nsCount; i++) {
/* 559 */         String prefix = sr.getNamespacePrefix(i);
/* 560 */         String uri = sr.getNamespaceURI(i);
/* 562 */         if (prefix == null || prefix.length() == 0) {
/* 563 */           writeDefaultNamespace(uri);
/*     */         } else {
/* 565 */           writeNamespace(prefix, uri);
/*     */         } 
/*     */       }  
/* 573 */     int attrCount = sr.getAttributeCount();
/* 574 */     if (attrCount > 0)
/* 575 */       for (int i = 0; i < attrCount; i++)
/* 576 */         writeAttribute(sr.getAttributePrefix(i), sr.getAttributeNamespace(i), sr.getAttributeLocalName(i), sr.getAttributeValue(i));  
/*     */   }
/*     */   
/*     */   protected String serializeQNameValue(QName name) throws XMLStreamException {
/*     */     String prefix;
/* 594 */     if (this.mNsRepairing) {
/* 595 */       String uri = name.getNamespaceURI();
/* 597 */       NamespaceContext ctxt = getNamespaceContext();
/* 598 */       prefix = (ctxt == null) ? null : ctxt.getPrefix(uri);
/* 599 */       if (prefix == null) {
/* 601 */         String origPrefix = name.getPrefix();
/* 602 */         if (origPrefix == null || origPrefix.length() == 0) {
/* 603 */           prefix = "";
/* 608 */           writeDefaultNamespace(uri);
/*     */         } else {
/* 610 */           prefix = origPrefix;
/* 611 */           writeNamespace(prefix, uri);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 615 */       prefix = name.getPrefix();
/*     */     } 
/* 617 */     String local = name.getLocalPart();
/* 618 */     if (prefix == null || prefix.length() == 0)
/* 619 */       return local; 
/* 623 */     return prefix + ":" + local;
/*     */   }
/*     */   
/*     */   protected SimpleValueEncoder getValueEncoder() {
/* 628 */     if (this.mValueEncoder == null)
/* 629 */       this.mValueEncoder = new SimpleValueEncoder(); 
/* 631 */     return this.mValueEncoder;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2WriterAdapter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */