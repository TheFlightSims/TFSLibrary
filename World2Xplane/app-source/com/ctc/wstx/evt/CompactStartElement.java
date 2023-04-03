/*     */ package com.ctc.wstx.evt;
/*     */ 
/*     */ import com.ctc.wstx.io.TextEscaper;
/*     */ import com.ctc.wstx.sr.ElemAttrs;
/*     */ import com.ctc.wstx.util.BaseNsContext;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ import org.codehaus.stax2.ri.SingletonIterator;
/*     */ import org.codehaus.stax2.ri.evt.AttributeEventImpl;
/*     */ 
/*     */ public class CompactStartElement extends BaseStartElement {
/*     */   private static final int OFFSET_NS_URI = 1;
/*     */   
/*     */   private static final int OFFSET_NS_PREFIX = 2;
/*     */   
/*     */   private static final int OFFSET_VALUE = 3;
/*     */   
/*     */   final ElemAttrs mAttrs;
/*     */   
/*     */   final String[] mRawAttrs;
/*     */   
/*  55 */   private ArrayList mAttrList = null;
/*     */   
/*     */   protected CompactStartElement(Location loc, QName name, BaseNsContext nsCtxt, ElemAttrs attrs) {
/*  67 */     super(loc, name, nsCtxt);
/*  68 */     this.mAttrs = attrs;
/*  69 */     this.mRawAttrs = (attrs == null) ? null : attrs.getRawAttrs();
/*     */   }
/*     */   
/*     */   public Attribute getAttributeByName(QName name) {
/*  80 */     if (this.mAttrs == null)
/*  81 */       return null; 
/*  83 */     int ix = this.mAttrs.findIndex(name);
/*  84 */     if (ix < 0)
/*  85 */       return null; 
/*  87 */     return constructAttr(this.mRawAttrs, ix, !this.mAttrs.isDefault(ix));
/*     */   }
/*     */   
/*     */   public Iterator getAttributes() {
/*  92 */     if (this.mAttrList == null) {
/*  93 */       if (this.mAttrs == null)
/*  94 */         return (Iterator)EmptyIterator.getInstance(); 
/*  96 */       String[] rawAttrs = this.mRawAttrs;
/*  97 */       int rawLen = rawAttrs.length;
/*  98 */       int defOffset = this.mAttrs.getFirstDefaultOffset();
/*  99 */       if (rawLen == 4)
/* 100 */         return (Iterator)new SingletonIterator(constructAttr(rawAttrs, 0, (defOffset == 0))); 
/* 103 */       ArrayList l = new ArrayList(rawLen >> 2);
/* 104 */       for (int i = 0; i < rawLen; i += 4)
/* 105 */         l.add(constructAttr(rawAttrs, i, (i >= defOffset))); 
/* 107 */       this.mAttrList = l;
/*     */     } 
/* 109 */     return this.mAttrList.iterator();
/*     */   }
/*     */   
/*     */   protected void outputNsAndAttr(Writer w) throws IOException {
/* 114 */     if (this.mNsCtxt != null)
/* 115 */       this.mNsCtxt.outputNamespaceDeclarations(w); 
/* 118 */     String[] raw = this.mRawAttrs;
/* 119 */     if (raw != null)
/* 120 */       for (int i = 0, len = raw.length; i < len; i += 4) {
/* 121 */         w.write(32);
/* 122 */         String prefix = raw[i + 2];
/* 123 */         if (prefix != null && prefix.length() > 0) {
/* 124 */           w.write(prefix);
/* 125 */           w.write(58);
/*     */         } 
/* 127 */         w.write(raw[i]);
/* 128 */         w.write("=\"");
/* 129 */         TextEscaper.writeEscapedAttrValue(w, raw[i + 3]);
/* 130 */         w.write(34);
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void outputNsAndAttr(XMLStreamWriter w) throws XMLStreamException {
/* 137 */     if (this.mNsCtxt != null)
/* 138 */       this.mNsCtxt.outputNamespaceDeclarations(w); 
/* 140 */     String[] raw = this.mRawAttrs;
/* 141 */     if (raw != null)
/* 142 */       for (int i = 0, len = raw.length; i < len; i += 4) {
/* 143 */         String ln = raw[i];
/* 144 */         String prefix = raw[i + 2];
/* 145 */         String nsURI = raw[i + 1];
/* 146 */         w.writeAttribute(prefix, nsURI, ln, raw[i + 3]);
/*     */       }  
/*     */   }
/*     */   
/*     */   public Attribute constructAttr(String[] raw, int rawIndex, boolean isDef) {
/* 159 */     return (Attribute)new AttributeEventImpl(this.mLocation, raw[rawIndex], raw[rawIndex + 1], raw[rawIndex + 2], raw[rawIndex + 3], isDef);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\CompactStartElement.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */