/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import javax.xml.stream.EventFilter;
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import org.codehaus.stax2.XMLEventReader2;
/*     */ 
/*     */ public class Stax2FilteredEventReader implements XMLEventReader2, XMLStreamConstants {
/*     */   final XMLEventReader2 mReader;
/*     */   
/*     */   final EventFilter mFilter;
/*     */   
/*     */   public Stax2FilteredEventReader(XMLEventReader2 r, EventFilter f) {
/*  26 */     this.mReader = r;
/*  27 */     this.mFilter = f;
/*     */   }
/*     */   
/*     */   public void close() throws XMLStreamException {
/*  39 */     this.mReader.close();
/*     */   }
/*     */   
/*     */   public String getElementText() throws XMLStreamException {
/*  46 */     return this.mReader.getElementText();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  50 */     return this.mReader.getProperty(name);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*     */     try {
/*  56 */       return (peek() != null);
/*  57 */     } catch (XMLStreamException sex) {
/*  58 */       throw new RuntimeException(sex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLEvent nextEvent() throws XMLStreamException {
/*     */     XMLEvent evt;
/*     */     do {
/*  66 */       evt = this.mReader.nextEvent();
/*  67 */     } while (evt != null && !this.mFilter.accept(evt));
/*  69 */     return evt;
/*     */   }
/*     */   
/*     */   public Object next() {
/*     */     try {
/*  77 */       return nextEvent();
/*  78 */     } catch (XMLStreamException sex) {
/*  79 */       throw new RuntimeException(sex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public XMLEvent nextTag() throws XMLStreamException {
/*     */     XMLEvent evt;
/*     */     do {
/*  89 */       evt = this.mReader.nextTag();
/*  90 */     } while (evt != null && !this.mFilter.accept(evt));
/*  91 */     return evt;
/*     */   }
/*     */   
/*     */   public XMLEvent peek() throws XMLStreamException {
/*     */     while (true) {
/* 104 */       XMLEvent evt = this.mReader.peek();
/* 105 */       if (evt == null || this.mFilter.accept(evt))
/* 106 */         return evt; 
/* 109 */       this.mReader.nextEvent();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/* 117 */     this.mReader.remove();
/*     */   }
/*     */   
/*     */   public boolean hasNextEvent() throws XMLStreamException {
/* 129 */     return (peek() != null);
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/* 134 */     return this.mReader.isPropertySupported(name);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/* 139 */     return this.mReader.setProperty(name, value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\Stax2FilteredEventReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */