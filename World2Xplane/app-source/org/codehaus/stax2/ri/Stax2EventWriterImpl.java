/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.XMLEventWriter;
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import javax.xml.stream.events.Comment;
/*     */ import javax.xml.stream.events.DTD;
/*     */ import javax.xml.stream.events.EntityReference;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import javax.xml.stream.events.ProcessingInstruction;
/*     */ import javax.xml.stream.events.StartDocument;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.evt.XMLEvent2;
/*     */ 
/*     */ public class Stax2EventWriterImpl implements XMLEventWriter, XMLStreamConstants {
/*     */   final XMLStreamWriter2 mWriter;
/*     */   
/*     */   public Stax2EventWriterImpl(XMLStreamWriter2 sw) {
/*  30 */     this.mWriter = sw;
/*     */   }
/*     */   
/*     */   public void add(XMLEvent event) throws XMLStreamException {
/*     */     Attribute attr;
/*     */     Namespace ns;
/*     */     StartDocument sd;
/*     */     StartElement se;
/*     */     Characters ch;
/*     */     ProcessingInstruction pi;
/*     */     QName name;
/*     */     QName n;
/*     */     String text;
/*     */     Iterator it;
/*  51 */     switch (event.getEventType()) {
/*     */       case 10:
/*  58 */         attr = (Attribute)event;
/*  59 */         name = attr.getName();
/*  60 */         this.mWriter.writeAttribute(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart(), attr.getValue());
/*     */         return;
/*     */       case 8:
/*  66 */         this.mWriter.writeEndDocument();
/*     */         return;
/*     */       case 2:
/*  70 */         this.mWriter.writeEndElement();
/*     */         return;
/*     */       case 13:
/*  75 */         ns = (Namespace)event;
/*  76 */         this.mWriter.writeNamespace(ns.getPrefix(), ns.getNamespaceURI());
/*     */         return;
/*     */       case 7:
/*  82 */         sd = (StartDocument)event;
/*  83 */         if (!sd.encodingSet()) {
/*  84 */           this.mWriter.writeStartDocument(sd.getVersion());
/*     */         } else {
/*  86 */           this.mWriter.writeStartDocument(sd.getCharacterEncodingScheme(), sd.getVersion());
/*     */         } 
/*     */         return;
/*     */       case 1:
/*  95 */         se = event.asStartElement();
/*  96 */         n = se.getName();
/*  97 */         this.mWriter.writeStartElement(n.getPrefix(), n.getLocalPart(), n.getNamespaceURI());
/*  99 */         it = se.getNamespaces();
/* 100 */         while (it.hasNext()) {
/* 101 */           Namespace namespace = it.next();
/* 102 */           add(namespace);
/*     */         } 
/* 104 */         it = (Iterator)se.getAttributes();
/* 105 */         while (it.hasNext()) {
/* 106 */           Attribute attribute = it.next();
/* 107 */           add(attribute);
/*     */         } 
/*     */         return;
/*     */       case 4:
/* 119 */         ch = event.asCharacters();
/* 120 */         text = ch.getData();
/* 121 */         if (ch.isCData()) {
/* 122 */           this.mWriter.writeCData(text);
/*     */         } else {
/* 124 */           this.mWriter.writeCharacters(text);
/*     */         } 
/*     */         return;
/*     */       case 12:
/* 130 */         this.mWriter.writeCData(event.asCharacters().getData());
/*     */         return;
/*     */       case 5:
/* 134 */         this.mWriter.writeComment(((Comment)event).getText());
/*     */         return;
/*     */       case 11:
/* 138 */         this.mWriter.writeDTD(((DTD)event).getDocumentTypeDeclaration());
/*     */         return;
/*     */       case 9:
/* 142 */         this.mWriter.writeEntityRef(((EntityReference)event).getName());
/*     */         return;
/*     */       case 3:
/* 147 */         pi = (ProcessingInstruction)event;
/* 148 */         this.mWriter.writeProcessingInstruction(pi.getTarget(), pi.getData());
/*     */         return;
/*     */     } 
/* 158 */     if (event instanceof XMLEvent2) {
/* 159 */       ((XMLEvent2)event).writeUsing(this.mWriter);
/*     */     } else {
/* 162 */       throw new XMLStreamException("Don't know how to output event " + event);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(XMLEventReader reader) throws XMLStreamException {
/* 170 */     while (reader.hasNext())
/* 171 */       add(reader.nextEvent()); 
/*     */   }
/*     */   
/*     */   public void close() throws XMLStreamException {
/* 178 */     this.mWriter.close();
/*     */   }
/*     */   
/*     */   public void flush() throws XMLStreamException {
/* 184 */     this.mWriter.flush();
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/* 188 */     return this.mWriter.getNamespaceContext();
/*     */   }
/*     */   
/*     */   public String getPrefix(String uri) throws XMLStreamException {
/* 194 */     return this.mWriter.getPrefix(uri);
/*     */   }
/*     */   
/*     */   public void setDefaultNamespace(String uri) throws XMLStreamException {
/* 200 */     this.mWriter.setDefaultNamespace(uri);
/*     */   }
/*     */   
/*     */   public void setNamespaceContext(NamespaceContext ctxt) throws XMLStreamException {
/* 206 */     this.mWriter.setNamespaceContext(ctxt);
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix, String uri) throws XMLStreamException {
/* 212 */     this.mWriter.setPrefix(prefix, uri);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2EventWriterImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */