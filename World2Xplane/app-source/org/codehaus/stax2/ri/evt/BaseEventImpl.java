/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.evt.XMLEvent2;
/*     */ 
/*     */ public abstract class BaseEventImpl implements XMLEvent2 {
/*     */   protected final Location mLocation;
/*     */   
/*     */   protected BaseEventImpl(Location loc) {
/*  31 */     this.mLocation = loc;
/*     */   }
/*     */   
/*     */   public Characters asCharacters() {
/*  41 */     return (Characters)this;
/*     */   }
/*     */   
/*     */   public EndElement asEndElement() {
/*  45 */     return (EndElement)this;
/*     */   }
/*     */   
/*     */   public StartElement asStartElement() {
/*  49 */     return (StartElement)this;
/*     */   }
/*     */   
/*     */   public abstract int getEventType();
/*     */   
/*     */   public Location getLocation() {
/*  55 */     return this.mLocation;
/*     */   }
/*     */   
/*     */   public QName getSchemaType() {
/*  59 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isAttribute() {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCharacters() {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEndDocument() {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEndElement() {
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEntityReference() {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isNamespace() {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isProcessingInstruction() {
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isStartDocument() {
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isStartElement() {
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public abstract void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException;
/*     */   
/*     */   public abstract void writeUsing(XMLStreamWriter2 paramXMLStreamWriter2) throws XMLStreamException;
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */   
/*     */   public abstract int hashCode();
/*     */   
/*     */   public String toString() {
/* 135 */     return "[Stax Event #" + getEventType() + "]";
/*     */   }
/*     */   
/*     */   protected void throwFromIOE(IOException ioe) throws XMLStreamException {
/* 147 */     throw new XMLStreamException(ioe.getMessage(), ioe);
/*     */   }
/*     */   
/*     */   protected static boolean stringsWithNullsEqual(String s1, String s2) {
/* 157 */     if (s1 == null || s1.length() == 0)
/* 158 */       return (s2 == null || s2.length() == 0); 
/* 160 */     return (s2 != null && s1.equals(s2));
/*     */   }
/*     */   
/*     */   protected static boolean iteratedEquals(Iterator it1, Iterator it2) {
/* 165 */     if (it1 == null || it2 == null)
/* 166 */       return (it1 == it2); 
/* 169 */     while (it1.hasNext()) {
/* 170 */       if (!it2.hasNext())
/* 171 */         return false; 
/* 173 */       Object o1 = it1.next();
/* 174 */       Object o2 = it2.next();
/* 176 */       if (!o1.equals(o2))
/* 177 */         return false; 
/*     */     } 
/* 180 */     return true;
/*     */   }
/*     */   
/*     */   protected static int addHash(Iterator it, int baseHash) {
/* 185 */     int hash = baseHash;
/* 186 */     if (it != null)
/* 187 */       while (it.hasNext())
/* 188 */         hash ^= it.next().hashCode();  
/* 191 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\BaseEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */