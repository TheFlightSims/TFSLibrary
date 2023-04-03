/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ 
/*     */ public class EndElementEventImpl extends BaseEventImpl implements EndElement {
/*     */   final QName mName;
/*     */   
/*     */   final ArrayList mNamespaces;
/*     */   
/*     */   public EndElementEventImpl(Location loc, XMLStreamReader r) {
/*  31 */     super(loc);
/*  32 */     this.mName = r.getName();
/*  35 */     int nsCount = r.getNamespaceCount();
/*  36 */     if (nsCount == 0) {
/*  37 */       this.mNamespaces = null;
/*     */     } else {
/*  39 */       ArrayList l = new ArrayList(nsCount);
/*  40 */       for (int i = 0; i < nsCount; i++)
/*  41 */         l.add(NamespaceEventImpl.constructNamespace(loc, r.getNamespacePrefix(i), r.getNamespaceURI(i))); 
/*  44 */       this.mNamespaces = l;
/*     */     } 
/*     */   }
/*     */   
/*     */   public EndElementEventImpl(Location loc, QName name, Iterator namespaces) {
/*  53 */     super(loc);
/*  54 */     this.mName = name;
/*  55 */     if (namespaces == null || !namespaces.hasNext()) {
/*  56 */       this.mNamespaces = null;
/*     */     } else {
/*  58 */       ArrayList l = new ArrayList();
/*  59 */       while (namespaces.hasNext())
/*  64 */         l.add(namespaces.next()); 
/*  66 */       this.mNamespaces = l;
/*     */     } 
/*     */   }
/*     */   
/*     */   public QName getName() {
/*  77 */     return this.mName;
/*     */   }
/*     */   
/*     */   public Iterator getNamespaces() {
/*  82 */     return (this.mNamespaces == null) ? (Iterator)EmptyIterator.getInstance() : this.mNamespaces.iterator();
/*     */   }
/*     */   
/*     */   public EndElement asEndElement() {
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/*  97 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean isEndElement() {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/* 108 */       w.write("</");
/* 109 */       String prefix = this.mName.getPrefix();
/* 110 */       if (prefix != null && prefix.length() > 0) {
/* 111 */         w.write(prefix);
/* 112 */         w.write(58);
/*     */       } 
/* 114 */       w.write(this.mName.getLocalPart());
/* 115 */       w.write(62);
/* 116 */     } catch (IOException ie) {
/* 117 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/* 123 */     w.writeEndElement();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 134 */     if (o == this)
/* 134 */       return true; 
/* 135 */     if (o == null)
/* 135 */       return false; 
/* 137 */     if (!(o instanceof EndElement))
/* 137 */       return false; 
/* 139 */     EndElement other = (EndElement)o;
/* 141 */     if (getName().equals(other.getName()))
/* 147 */       return true; 
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 154 */     return getName().hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\EndElementEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */