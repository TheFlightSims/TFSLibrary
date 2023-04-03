/*     */ package org.codehaus.stax2.util;
/*     */ 
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ 
/*     */ public class StreamWriterDelegate implements XMLStreamWriter {
/*     */   protected XMLStreamWriter mDelegate;
/*     */   
/*     */   public StreamWriterDelegate(XMLStreamWriter parentWriter) {
/*  47 */     this.mDelegate = parentWriter;
/*     */   }
/*     */   
/*     */   public void setParent(XMLStreamWriter parentWriter) {
/*  52 */     this.mDelegate = parentWriter;
/*     */   }
/*     */   
/*     */   public XMLStreamWriter getParent() {
/*  57 */     return this.mDelegate;
/*     */   }
/*     */   
/*     */   public void close() throws XMLStreamException {
/*  67 */     this.mDelegate.close();
/*     */   }
/*     */   
/*     */   public void flush() throws XMLStreamException {
/*  71 */     this.mDelegate.flush();
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/*  76 */     return this.mDelegate.getNamespaceContext();
/*     */   }
/*     */   
/*     */   public String getPrefix(String ns) throws XMLStreamException {
/*  80 */     return this.mDelegate.getPrefix(ns);
/*     */   }
/*     */   
/*     */   public Object getProperty(String pname) throws IllegalArgumentException {
/*  84 */     return this.mDelegate.getProperty(pname);
/*     */   }
/*     */   
/*     */   public void setDefaultNamespace(String ns) throws XMLStreamException {
/*  88 */     this.mDelegate.setDefaultNamespace(ns);
/*     */   }
/*     */   
/*     */   public void setNamespaceContext(NamespaceContext nc) throws XMLStreamException {
/*  95 */     this.mDelegate.setNamespaceContext(nc);
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix, String uri) throws XMLStreamException {
/* 100 */     this.mDelegate.setPrefix(prefix, uri);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String arg0, String arg1) throws XMLStreamException {
/* 105 */     this.mDelegate.writeAttribute(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String arg0, String arg1, String arg2) throws XMLStreamException {
/* 110 */     this.mDelegate.writeAttribute(arg0, arg1, arg2);
/*     */   }
/*     */   
/*     */   public void writeAttribute(String arg0, String arg1, String arg2, String arg3) throws XMLStreamException {
/* 114 */     this.mDelegate.writeAttribute(arg0, arg1, arg2, arg3);
/*     */   }
/*     */   
/*     */   public void writeCData(String arg0) throws XMLStreamException {
/* 118 */     this.mDelegate.writeCData(arg0);
/*     */   }
/*     */   
/*     */   public void writeCharacters(String arg0) throws XMLStreamException {
/* 123 */     this.mDelegate.writeCharacters(arg0);
/*     */   }
/*     */   
/*     */   public void writeCharacters(char[] arg0, int arg1, int arg2) throws XMLStreamException {
/* 129 */     this.mDelegate.writeCharacters(arg0, arg1, arg2);
/*     */   }
/*     */   
/*     */   public void writeComment(String arg0) throws XMLStreamException {
/* 134 */     this.mDelegate.writeComment(arg0);
/*     */   }
/*     */   
/*     */   public void writeDTD(String arg0) throws XMLStreamException {
/* 139 */     this.mDelegate.writeDTD(arg0);
/*     */   }
/*     */   
/*     */   public void writeDefaultNamespace(String arg0) throws XMLStreamException {
/* 144 */     this.mDelegate.writeDefaultNamespace(arg0);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String arg0) throws XMLStreamException {
/* 149 */     this.mDelegate.writeEmptyElement(arg0);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String arg0, String arg1) throws XMLStreamException {
/* 154 */     this.mDelegate.writeEmptyElement(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void writeEmptyElement(String arg0, String arg1, String arg2) throws XMLStreamException {
/* 160 */     this.mDelegate.writeEmptyElement(arg0, arg1, arg2);
/*     */   }
/*     */   
/*     */   public void writeEndDocument() throws XMLStreamException {
/* 165 */     this.mDelegate.writeEndDocument();
/*     */   }
/*     */   
/*     */   public void writeEndElement() throws XMLStreamException {
/* 170 */     this.mDelegate.writeEndElement();
/*     */   }
/*     */   
/*     */   public void writeEntityRef(String arg0) throws XMLStreamException {
/* 175 */     this.mDelegate.writeEntityRef(arg0);
/*     */   }
/*     */   
/*     */   public void writeNamespace(String arg0, String arg1) throws XMLStreamException {
/* 181 */     this.mDelegate.writeNamespace(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void writeProcessingInstruction(String arg0) throws XMLStreamException {
/* 187 */     this.mDelegate.writeProcessingInstruction(arg0);
/*     */   }
/*     */   
/*     */   public void writeProcessingInstruction(String arg0, String arg1) throws XMLStreamException {
/* 193 */     this.mDelegate.writeProcessingInstruction(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void writeStartDocument() throws XMLStreamException {
/* 198 */     this.mDelegate.writeStartDocument();
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String arg0) throws XMLStreamException {
/* 203 */     this.mDelegate.writeStartDocument(arg0);
/*     */   }
/*     */   
/*     */   public void writeStartDocument(String arg0, String arg1) throws XMLStreamException {
/* 209 */     this.mDelegate.writeStartDocument(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String arg0) throws XMLStreamException {
/* 214 */     this.mDelegate.writeStartElement(arg0);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String arg0, String arg1) throws XMLStreamException {
/* 220 */     this.mDelegate.writeStartElement(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void writeStartElement(String arg0, String arg1, String arg2) throws XMLStreamException {
/* 225 */     this.mDelegate.writeStartElement(arg0, arg1, arg2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax\\util\StreamWriterDelegate.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */