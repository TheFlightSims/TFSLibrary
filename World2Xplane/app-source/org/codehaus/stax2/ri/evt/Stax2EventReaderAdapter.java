/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import org.codehaus.stax2.XMLEventReader2;
/*     */ 
/*     */ public class Stax2EventReaderAdapter implements XMLEventReader2 {
/*     */   final XMLEventReader mReader;
/*     */   
/*     */   protected Stax2EventReaderAdapter(XMLEventReader er) {
/*  41 */     this.mReader = er;
/*     */   }
/*     */   
/*     */   public static XMLEventReader2 wrapIfNecessary(XMLEventReader er) {
/*  53 */     if (er instanceof XMLEventReader2)
/*  54 */       return (XMLEventReader2)er; 
/*  56 */     return new Stax2EventReaderAdapter(er);
/*     */   }
/*     */   
/*     */   public void close() throws XMLStreamException {
/*  66 */     this.mReader.close();
/*     */   }
/*     */   
/*     */   public String getElementText() throws XMLStreamException {
/*  70 */     return this.mReader.getElementText();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  74 */     return this.mReader.getProperty(name);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  78 */     return this.mReader.hasNext();
/*     */   }
/*     */   
/*     */   public XMLEvent nextEvent() throws XMLStreamException {
/*  83 */     return this.mReader.nextEvent();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  87 */     return this.mReader.next();
/*     */   }
/*     */   
/*     */   public XMLEvent nextTag() throws XMLStreamException {
/*  91 */     return this.mReader.nextTag();
/*     */   }
/*     */   
/*     */   public XMLEvent peek() throws XMLStreamException {
/*  95 */     return this.mReader.peek();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 102 */     this.mReader.remove();
/*     */   }
/*     */   
/*     */   public boolean hasNextEvent() throws XMLStreamException {
/* 113 */     return (peek() != null);
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String name) {
/*     */     try {
/* 123 */       this.mReader.getProperty(name);
/* 124 */     } catch (IllegalArgumentException iae) {
/* 125 */       return false;
/*     */     } 
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public boolean setProperty(String name, Object value) {
/* 133 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\Stax2EventReaderAdapter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */