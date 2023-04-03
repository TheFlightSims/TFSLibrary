/*     */ package com.ctc.wstx.evt;
/*     */ 
/*     */ import com.ctc.wstx.io.TextEscaper;
/*     */ import com.ctc.wstx.util.BaseNsContext;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ 
/*     */ public class SimpleStartElement extends BaseStartElement {
/*     */   final Map mAttrs;
/*     */   
/*     */   protected SimpleStartElement(Location loc, QName name, BaseNsContext nsCtxt, Map attr) {
/*  40 */     super(loc, name, nsCtxt);
/*  41 */     this.mAttrs = attr;
/*     */   }
/*     */   
/*     */   public static SimpleStartElement construct(Location loc, QName name, Map attrs, List ns, NamespaceContext nsCtxt) {
/*  52 */     BaseNsContext myCtxt = MergedNsContext.construct(nsCtxt, ns);
/*  53 */     return new SimpleStartElement(loc, name, myCtxt, attrs);
/*     */   }
/*     */   
/*     */   public static SimpleStartElement construct(Location loc, QName name, Iterator attrs, Iterator ns, NamespaceContext nsCtxt) {
/*     */     Map attrMap;
/*     */     BaseNsContext myCtxt;
/*  61 */     if (attrs == null || !attrs.hasNext()) {
/*  62 */       attrMap = null;
/*     */     } else {
/*  64 */       attrMap = new LinkedHashMap();
/*     */       do {
/*  66 */         Attribute attr = attrs.next();
/*  67 */         attrMap.put(attr.getName(), attr);
/*  68 */       } while (attrs.hasNext());
/*     */     } 
/*  72 */     if (ns != null && ns.hasNext()) {
/*  73 */       ArrayList l = new ArrayList();
/*     */       while (true) {
/*  75 */         l.add(ns.next());
/*  76 */         if (!ns.hasNext()) {
/*  77 */           myCtxt = MergedNsContext.construct(nsCtxt, l);
/*  90 */           return new SimpleStartElement(loc, name, myCtxt, attrMap);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     if (nsCtxt == null) {
/*     */       myCtxt = null;
/*     */     } else if (nsCtxt instanceof BaseNsContext) {
/*     */       myCtxt = (BaseNsContext)nsCtxt;
/*     */     } else {
/*     */       myCtxt = MergedNsContext.construct(nsCtxt, null);
/*     */     } 
/*  90 */     return new SimpleStartElement(loc, name, myCtxt, attrMap);
/*     */   }
/*     */   
/*     */   public Attribute getAttributeByName(QName name) {
/* 101 */     if (this.mAttrs == null)
/* 102 */       return null; 
/* 104 */     return (Attribute)this.mAttrs.get(name);
/*     */   }
/*     */   
/*     */   public Iterator getAttributes() {
/* 109 */     if (this.mAttrs == null)
/* 110 */       return (Iterator)EmptyIterator.getInstance(); 
/* 112 */     return this.mAttrs.values().iterator();
/*     */   }
/*     */   
/*     */   protected void outputNsAndAttr(Writer w) throws IOException {
/* 118 */     if (this.mNsCtxt != null)
/* 119 */       this.mNsCtxt.outputNamespaceDeclarations(w); 
/* 122 */     if (this.mAttrs != null && this.mAttrs.size() > 0) {
/* 123 */       Iterator it = this.mAttrs.values().iterator();
/* 124 */       while (it.hasNext()) {
/* 125 */         Attribute attr = it.next();
/* 127 */         if (!attr.isSpecified())
/*     */           continue; 
/* 131 */         w.write(32);
/* 132 */         QName name = attr.getName();
/* 133 */         String prefix = name.getPrefix();
/* 134 */         if (prefix != null && prefix.length() > 0) {
/* 135 */           w.write(prefix);
/* 136 */           w.write(58);
/*     */         } 
/* 138 */         w.write(name.getLocalPart());
/* 139 */         w.write("=\"");
/* 140 */         String val = attr.getValue();
/* 141 */         if (val != null && val.length() > 0)
/* 142 */           TextEscaper.writeEscapedAttrValue(w, val); 
/* 144 */         w.write(34);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void outputNsAndAttr(XMLStreamWriter w) throws XMLStreamException {
/* 152 */     if (this.mNsCtxt != null)
/* 153 */       this.mNsCtxt.outputNamespaceDeclarations(w); 
/* 156 */     if (this.mAttrs != null && this.mAttrs.size() > 0) {
/* 157 */       Iterator it = this.mAttrs.values().iterator();
/* 158 */       while (it.hasNext()) {
/* 159 */         Attribute attr = it.next();
/* 161 */         if (!attr.isSpecified())
/*     */           continue; 
/* 164 */         QName name = attr.getName();
/* 165 */         String prefix = name.getPrefix();
/* 166 */         String ln = name.getLocalPart();
/* 167 */         String nsURI = name.getNamespaceURI();
/* 168 */         w.writeAttribute(prefix, nsURI, ln, attr.getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\SimpleStartElement.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */