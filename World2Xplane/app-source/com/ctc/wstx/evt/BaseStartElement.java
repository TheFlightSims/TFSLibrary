/*     */ package com.ctc.wstx.evt;
/*     */ 
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import com.ctc.wstx.util.BaseNsContext;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ import org.codehaus.stax2.ri.evt.BaseEventImpl;
/*     */ 
/*     */ abstract class BaseStartElement extends BaseEventImpl implements StartElement {
/*     */   protected final QName mName;
/*     */   
/*     */   protected final BaseNsContext mNsCtxt;
/*     */   
/*     */   protected BaseStartElement(Location loc, QName name, BaseNsContext nsCtxt) {
/*  54 */     super(loc);
/*  55 */     this.mName = name;
/*  56 */     this.mNsCtxt = nsCtxt;
/*     */   }
/*     */   
/*     */   public abstract Attribute getAttributeByName(QName paramQName);
/*     */   
/*     */   public abstract Iterator getAttributes();
/*     */   
/*     */   public final QName getName() {
/*  70 */     return this.mName;
/*     */   }
/*     */   
/*     */   public Iterator getNamespaces() {
/*  75 */     if (this.mNsCtxt == null)
/*  76 */       return (Iterator)EmptyIterator.getInstance(); 
/*  82 */     return this.mNsCtxt.getNamespaces();
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/*  87 */     return (NamespaceContext)this.mNsCtxt;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI(String prefix) {
/*  91 */     return (this.mNsCtxt == null) ? null : this.mNsCtxt.getNamespaceURI(prefix);
/*     */   }
/*     */   
/*     */   public StartElement asStartElement() {
/* 101 */     return this;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/* 105 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isStartElement() {
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/* 116 */       w.write(60);
/* 117 */       String prefix = this.mName.getPrefix();
/* 118 */       if (prefix != null && prefix.length() > 0) {
/* 119 */         w.write(prefix);
/* 120 */         w.write(58);
/*     */       } 
/* 122 */       w.write(this.mName.getLocalPart());
/* 125 */       outputNsAndAttr(w);
/* 127 */       w.write(62);
/* 128 */     } catch (IOException ie) {
/* 129 */       throw new WstxIOException(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/* 135 */     QName n = this.mName;
/* 136 */     w.writeStartElement(n.getPrefix(), n.getLocalPart(), n.getNamespaceURI());
/* 138 */     outputNsAndAttr((XMLStreamWriter)w);
/*     */   }
/*     */   
/*     */   protected abstract void outputNsAndAttr(Writer paramWriter) throws IOException;
/*     */   
/*     */   protected abstract void outputNsAndAttr(XMLStreamWriter paramXMLStreamWriter) throws XMLStreamException;
/*     */   
/*     */   public boolean equals(Object o) {
/* 155 */     if (o == this)
/* 155 */       return true; 
/* 156 */     if (o == null)
/* 156 */       return false; 
/* 158 */     if (!(o instanceof StartElement))
/* 158 */       return false; 
/* 160 */     StartElement other = (StartElement)o;
/* 163 */     if (this.mName.equals(other.getName()))
/* 170 */       if (iteratedEquals(getNamespaces(), other.getNamespaces()))
/* 171 */         return iteratedEquals(getAttributes(), other.getAttributes());  
/* 174 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 179 */     int hash = this.mName.hashCode();
/* 180 */     hash = addHash(getNamespaces(), hash);
/* 181 */     hash = addHash(getAttributes(), hash);
/* 182 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\BaseStartElement.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */