/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ import org.codehaus.stax2.ri.EmptyNamespaceContext;
/*     */ 
/*     */ public class StartElementEventImpl extends BaseEventImpl implements StartElement {
/*     */   protected final QName mName;
/*     */   
/*     */   protected final ArrayList mAttrs;
/*     */   
/*     */   protected final ArrayList mNsDecls;
/*     */   
/*     */   protected NamespaceContext mParentNsCtxt;
/*     */   
/*  42 */   NamespaceContext mActualNsCtxt = null;
/*     */   
/*     */   protected StartElementEventImpl(Location loc, QName name, ArrayList attrs, ArrayList nsDecls, NamespaceContext parentNsCtxt) {
/*  54 */     super(loc);
/*  55 */     this.mName = name;
/*  56 */     this.mAttrs = attrs;
/*  57 */     this.mNsDecls = nsDecls;
/*  58 */     this.mParentNsCtxt = (parentNsCtxt == null) ? (NamespaceContext)EmptyNamespaceContext.getInstance() : parentNsCtxt;
/*     */   }
/*     */   
/*     */   public static StartElementEventImpl construct(Location loc, QName name, Iterator attrIt, Iterator nsDeclIt, NamespaceContext nsCtxt) {
/*     */     ArrayList attrs;
/*     */     ArrayList nsDecls;
/*  67 */     if (attrIt == null || !attrIt.hasNext()) {
/*  68 */       attrs = null;
/*     */     } else {
/*  70 */       attrs = new ArrayList();
/*     */       do {
/*  73 */         attrs.add(attrIt.next());
/*  74 */       } while (attrIt.hasNext());
/*     */     } 
/*  78 */     if (nsDeclIt == null || !nsDeclIt.hasNext()) {
/*  79 */       nsDecls = null;
/*     */     } else {
/*  81 */       nsDecls = new ArrayList();
/*     */       do {
/*  83 */         nsDecls.add(nsDeclIt.next());
/*  84 */       } while (nsDeclIt.hasNext());
/*     */     } 
/*  86 */     return new StartElementEventImpl(loc, name, attrs, nsDecls, nsCtxt);
/*     */   }
/*     */   
/*     */   public StartElement asStartElement() {
/*  96 */     return this;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/* 100 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isStartElement() {
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/* 111 */       w.write(60);
/* 112 */       String prefix = this.mName.getPrefix();
/* 113 */       if (prefix != null && prefix.length() > 0) {
/* 114 */         w.write(prefix);
/* 115 */         w.write(58);
/*     */       } 
/* 117 */       w.write(this.mName.getLocalPart());
/* 120 */       if (this.mNsDecls != null)
/* 121 */         for (int i = 0, len = this.mNsDecls.size(); i < len; i++) {
/* 122 */           w.write(32);
/* 123 */           ((Namespace)this.mNsDecls.get(i)).writeAsEncodedUnicode(w);
/*     */         }  
/* 128 */       if (this.mAttrs != null)
/* 129 */         for (int i = 0, len = this.mAttrs.size(); i < len; i++) {
/* 130 */           Attribute attr = this.mAttrs.get(i);
/* 132 */           if (attr.isSpecified()) {
/* 133 */             w.write(32);
/* 134 */             attr.writeAsEncodedUnicode(w);
/*     */           } 
/*     */         }  
/* 139 */       w.write(62);
/* 140 */     } catch (IOException ie) {
/* 141 */       throw new XMLStreamException(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 sw) throws XMLStreamException {
/* 147 */     QName n = this.mName;
/* 148 */     sw.writeStartElement(n.getPrefix(), n.getLocalPart(), n.getNamespaceURI());
/* 152 */     if (this.mNsDecls != null)
/* 153 */       for (int i = 0, len = this.mNsDecls.size(); i < len; i++) {
/* 154 */         Namespace ns = this.mNsDecls.get(i);
/* 155 */         String prefix = ns.getPrefix();
/* 156 */         String uri = ns.getNamespaceURI();
/* 157 */         if (prefix == null || prefix.length() == 0) {
/* 158 */           sw.writeDefaultNamespace(uri);
/*     */         } else {
/* 160 */           sw.writeNamespace(prefix, uri);
/*     */         } 
/*     */       }  
/* 166 */     if (this.mAttrs != null)
/* 167 */       for (int i = 0, len = this.mAttrs.size(); i < len; i++) {
/* 168 */         Attribute attr = this.mAttrs.get(i);
/* 170 */         if (attr.isSpecified()) {
/* 171 */           QName name = attr.getName();
/* 172 */           sw.writeAttribute(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart(), attr.getValue());
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public final QName getName() {
/* 185 */     return this.mName;
/*     */   }
/*     */   
/*     */   public Iterator getNamespaces() {
/* 190 */     return (this.mNsDecls == null) ? (Iterator)EmptyIterator.getInstance() : this.mNsDecls.iterator();
/*     */   }
/*     */   
/*     */   public NamespaceContext getNamespaceContext() {
/* 196 */     if (this.mActualNsCtxt == null)
/* 197 */       if (this.mNsDecls == null) {
/* 198 */         this.mActualNsCtxt = this.mParentNsCtxt;
/*     */       } else {
/* 200 */         this.mActualNsCtxt = MergedNsContext.construct(this.mParentNsCtxt, this.mNsDecls);
/*     */       }  
/* 203 */     return this.mActualNsCtxt;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI(String prefix) {
/* 208 */     if (this.mNsDecls != null) {
/* 209 */       if (prefix == null)
/* 210 */         prefix = ""; 
/* 212 */       for (int i = 0, len = this.mNsDecls.size(); i < len; i++) {
/* 213 */         Namespace ns = this.mNsDecls.get(i);
/* 214 */         String thisPrefix = ns.getPrefix();
/* 215 */         if (thisPrefix == null)
/* 216 */           thisPrefix = ""; 
/* 218 */         if (prefix.equals(thisPrefix))
/* 219 */           return ns.getNamespaceURI(); 
/*     */       } 
/*     */     } 
/* 224 */     return null;
/*     */   }
/*     */   
/*     */   public Attribute getAttributeByName(QName nameIn) {
/* 229 */     if (this.mAttrs == null)
/* 230 */       return null; 
/* 233 */     String ln = nameIn.getLocalPart();
/* 234 */     String uri = nameIn.getNamespaceURI();
/* 235 */     int len = this.mAttrs.size();
/* 237 */     boolean notInNs = (uri == null || uri.length() == 0);
/* 238 */     for (int i = 0; i < len; i++) {
/* 239 */       Attribute attr = this.mAttrs.get(i);
/* 240 */       QName name = attr.getName();
/* 241 */       if (name.getLocalPart().equals(ln)) {
/* 242 */         String thisUri = name.getNamespaceURI();
/* 243 */         if (notInNs) {
/* 244 */           if (thisUri == null || thisUri.length() == 0)
/* 245 */             return attr; 
/* 248 */         } else if (uri.equals(thisUri)) {
/* 249 */           return attr;
/*     */         } 
/*     */       } 
/*     */     } 
/* 254 */     return null;
/*     */   }
/*     */   
/*     */   public Iterator getAttributes() {
/* 259 */     if (this.mAttrs == null)
/* 260 */       return (Iterator)EmptyIterator.getInstance(); 
/* 262 */     return this.mAttrs.iterator();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 273 */     if (o == this)
/* 273 */       return true; 
/* 274 */     if (o == null)
/* 274 */       return false; 
/* 276 */     if (!(o instanceof StartElement))
/* 276 */       return false; 
/* 278 */     StartElement other = (StartElement)o;
/* 281 */     if (this.mName.equals(other.getName()))
/* 288 */       if (iteratedEquals(getNamespaces(), other.getNamespaces()))
/* 289 */         return iteratedEquals(getAttributes(), other.getAttributes());  
/* 292 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 297 */     int hash = this.mName.hashCode();
/* 298 */     hash = addHash(getNamespaces(), hash);
/* 299 */     hash = addHash(getAttributes(), hash);
/* 300 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\StartElementEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */