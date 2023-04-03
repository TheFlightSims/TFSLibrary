/*     */ package com.ctc.wstx.msv;
/*     */ 
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ final class AttributeProxy implements Attributes {
/*     */   private final ValidationContext mContext;
/*     */   
/*     */   public AttributeProxy(ValidationContext ctxt) {
/*  33 */     this.mContext = ctxt;
/*     */   }
/*     */   
/*     */   public int getIndex(String qName) {
/*  44 */     int cix = qName.indexOf(':');
/*  45 */     int acount = this.mContext.getAttributeCount();
/*  46 */     if (cix < 0) {
/*  47 */       for (int i = 0; i < acount; i++) {
/*  48 */         if (qName.equals(this.mContext.getAttributeLocalName(i))) {
/*  49 */           String prefix = this.mContext.getAttributePrefix(i);
/*  50 */           if (prefix == null || prefix.length() == 0)
/*  51 */             return i; 
/*     */         } 
/*     */       } 
/*     */     } else {
/*  56 */       String prefix = qName.substring(0, cix);
/*  57 */       String ln = qName.substring(cix + 1);
/*  59 */       for (int i = 0; i < acount; i++) {
/*  60 */         if (ln.equals(this.mContext.getAttributeLocalName(i))) {
/*  61 */           String p2 = this.mContext.getAttributePrefix(i);
/*  62 */           if (p2 != null && prefix.equals(p2))
/*  63 */             return i; 
/*     */         } 
/*     */       } 
/*     */     } 
/*  68 */     return -1;
/*     */   }
/*     */   
/*     */   public int getIndex(String uri, String localName) {
/*  73 */     return this.mContext.findAttributeIndex(uri, localName);
/*     */   }
/*     */   
/*     */   public int getLength() {
/*  78 */     return this.mContext.getAttributeCount();
/*     */   }
/*     */   
/*     */   public String getLocalName(int index) {
/*  83 */     return this.mContext.getAttributeLocalName(index);
/*     */   }
/*     */   
/*     */   public String getQName(int index) {
/*  88 */     String prefix = this.mContext.getAttributePrefix(index);
/*  89 */     String ln = this.mContext.getAttributeLocalName(index);
/*  91 */     if (prefix == null || prefix.length() == 0)
/*  92 */       return ln; 
/*  94 */     StringBuffer sb = new StringBuffer(prefix.length() + 1 + ln.length());
/*  95 */     sb.append(prefix);
/*  96 */     sb.append(':');
/*  97 */     sb.append(ln);
/*  98 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String getType(int index) {
/* 103 */     return this.mContext.getAttributeType(index);
/*     */   }
/*     */   
/*     */   public String getType(String qName) {
/* 108 */     return getType(getIndex(qName));
/*     */   }
/*     */   
/*     */   public String getType(String uri, String localName) {
/* 113 */     return getType(getIndex(uri, localName));
/*     */   }
/*     */   
/*     */   public String getURI(int index) {
/* 118 */     return this.mContext.getAttributeNamespace(index);
/*     */   }
/*     */   
/*     */   public String getValue(int index) {
/* 123 */     return this.mContext.getAttributeValue(index);
/*     */   }
/*     */   
/*     */   public String getValue(String qName) {
/* 128 */     return getValue(getIndex(qName));
/*     */   }
/*     */   
/*     */   public String getValue(String uri, String localName) {
/* 133 */     return this.mContext.getAttributeValue(uri, localName);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\AttributeProxy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */