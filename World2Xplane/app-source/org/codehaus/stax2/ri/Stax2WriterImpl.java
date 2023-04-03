/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import org.codehaus.stax2.DTDInfo;
/*     */ import org.codehaus.stax2.XMLStreamLocation2;
/*     */ import org.codehaus.stax2.XMLStreamReader2;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.validation.ValidationProblemHandler;
/*     */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class Stax2WriterImpl implements XMLStreamWriter2, XMLStreamConstants {
/*     */   public boolean isPropertySupported(String name) {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/*  54 */     throw new IllegalArgumentException("No settable property '" + name + "'");
/*     */   }
/*     */   
/*     */   public abstract XMLStreamLocation2 getLocation();
/*     */   
/*     */   public abstract String getEncoding();
/*     */   
/*     */   public void writeCData(char[] text, int start, int len) throws XMLStreamException {
/*  64 */     writeCData(new String(text, start, len));
/*     */   }
/*     */   
/*     */   public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws XMLStreamException {
/*  74 */     StringBuffer sb = new StringBuffer();
/*  75 */     sb.append("<!DOCTYPE");
/*  76 */     sb.append(rootName);
/*  77 */     if (systemId != null) {
/*  78 */       if (publicId != null) {
/*  79 */         sb.append(" PUBLIC \"");
/*  80 */         sb.append(publicId);
/*  81 */         sb.append("\" \"");
/*     */       } else {
/*  83 */         sb.append(" SYSTEM \"");
/*     */       } 
/*  85 */       sb.append(systemId);
/*  86 */       sb.append('"');
/*     */     } 
/*  89 */     if (internalSubset != null && internalSubset.length() > 0) {
/*  90 */       sb.append(" [");
/*  91 */       sb.append(internalSubset);
/*  92 */       sb.append(']');
/*     */     } 
/*  94 */     sb.append('>');
/*  95 */     writeDTD(sb.toString());
/*     */   }
/*     */   
/*     */   public void writeFullEndElement() throws XMLStreamException {
/* 105 */     writeCharacters("");
/* 106 */     writeEndElement();
/*     */   }
/*     */   
/*     */   public void writeSpace(String text) throws XMLStreamException {
/* 117 */     writeRaw(text);
/*     */   }
/*     */   
/*     */   public void writeSpace(char[] text, int offset, int length) throws XMLStreamException {
/* 124 */     writeRaw(text, offset, length);
/*     */   }
/*     */   
/*     */   public abstract void writeStartDocument(String paramString1, String paramString2, boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public void writeRaw(String text) throws XMLStreamException {
/* 140 */     writeRaw(text, 0, text.length());
/*     */   }
/*     */   
/*     */   public abstract void writeRaw(String paramString, int paramInt1, int paramInt2) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws XMLStreamException;
/*     */   
/*     */   public void copyEventFromReader(XMLStreamReader2 sr, boolean preserveEventData) throws XMLStreamException {
/*     */     String version;
/*     */     DTDInfo info;
/* 152 */     switch (sr.getEventType()) {
/*     */       case 7:
/* 155 */         version = sr.getVersion();
/* 160 */         if (version != null && version.length() != 0)
/* 163 */           if (sr.standaloneSet()) {
/* 164 */             writeStartDocument(sr.getVersion(), sr.getCharacterEncodingScheme(), sr.isStandalone());
/*     */           } else {
/* 168 */             writeStartDocument(sr.getCharacterEncodingScheme(), sr.getVersion());
/*     */           }  
/*     */         return;
/*     */       case 8:
/* 176 */         writeEndDocument();
/*     */         return;
/*     */       case 1:
/* 185 */         copyStartElement((XMLStreamReader)sr);
/*     */         return;
/*     */       case 2:
/* 189 */         writeEndElement();
/*     */         return;
/*     */       case 6:
/* 193 */         writeSpace(sr.getTextCharacters(), sr.getTextStart(), sr.getTextLength());
/*     */         return;
/*     */       case 12:
/* 197 */         writeCData(sr.getTextCharacters(), sr.getTextStart(), sr.getTextLength());
/*     */         return;
/*     */       case 4:
/* 201 */         writeCharacters(sr.getTextCharacters(), sr.getTextStart(), sr.getTextLength());
/*     */         return;
/*     */       case 5:
/* 205 */         writeComment(sr.getText());
/*     */         return;
/*     */       case 3:
/* 209 */         writeProcessingInstruction(sr.getPITarget(), sr.getPIData());
/*     */         return;
/*     */       case 11:
/* 214 */         info = sr.getDTDInfo();
/* 215 */         if (info == null)
/* 219 */           throw new XMLStreamException("Current state DOCTYPE, but not DTDInfo Object returned -- reader doesn't support DTDs?"); 
/* 221 */         writeDTD(info.getDTDRootName(), info.getDTDSystemId(), info.getDTDPublicId(), info.getDTDInternalSubset());
/*     */         return;
/*     */       case 9:
/* 227 */         writeEntityRef(sr.getLocalName());
/*     */         return;
/*     */     } 
/* 236 */     throw new XMLStreamException("Unrecognized event type (" + sr.getEventType() + "); not sure how to copy");
/*     */   }
/*     */   
/*     */   public XMLValidator validateAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 249 */     throw new UnsupportedOperationException("Not yet implemented");
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws XMLStreamException {
/* 255 */     return null;
/*     */   }
/*     */   
/*     */   public XMLValidator stopValidatingAgainst(XMLValidator validator) throws XMLStreamException {
/* 261 */     return null;
/*     */   }
/*     */   
/*     */   public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
/* 269 */     return null;
/*     */   }
/*     */   
/*     */   protected void copyStartElement(XMLStreamReader sr) throws XMLStreamException {
/* 287 */     int nsCount = sr.getNamespaceCount();
/* 288 */     if (nsCount > 0)
/* 293 */       for (int i = 0; i < nsCount; i++) {
/* 294 */         String prefix = sr.getNamespacePrefix(i);
/* 295 */         String uri = sr.getNamespaceURI(i);
/* 296 */         if (prefix == null || prefix.length() == 0) {
/* 297 */           setDefaultNamespace(uri);
/*     */         } else {
/* 299 */           setPrefix(prefix, uri);
/*     */         } 
/*     */       }  
/* 303 */     writeStartElement(sr.getPrefix(), sr.getLocalName(), sr.getNamespaceURI());
/* 305 */     if (nsCount > 0)
/* 307 */       for (int i = 0; i < nsCount; i++) {
/* 308 */         String prefix = sr.getNamespacePrefix(i);
/* 309 */         String uri = sr.getNamespaceURI(i);
/* 311 */         if (prefix == null || prefix.length() == 0) {
/* 312 */           writeDefaultNamespace(uri);
/*     */         } else {
/* 314 */           writeNamespace(prefix, uri);
/*     */         } 
/*     */       }  
/* 322 */     int attrCount = sr.getAttributeCount();
/* 323 */     if (attrCount > 0)
/* 324 */       for (int i = 0; i < attrCount; i++)
/* 325 */         writeAttribute(sr.getAttributePrefix(i), sr.getAttributeNamespace(i), sr.getAttributeLocalName(i), sr.getAttributeValue(i));  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2WriterImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */