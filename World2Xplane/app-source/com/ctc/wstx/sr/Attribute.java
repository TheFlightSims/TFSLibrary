/*     */ package com.ctc.wstx.sr;
/*     */ 
/*     */ import com.ctc.wstx.compat.QNameCreator;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ final class Attribute {
/*     */   protected String mLocalName;
/*     */   
/*     */   protected String mPrefix;
/*     */   
/*     */   protected String mNamespaceURI;
/*     */   
/*     */   protected int mValueStartOffset;
/*     */   
/*     */   protected String mReusableValue;
/*     */   
/*     */   public Attribute(String prefix, String localName, int valueStart) {
/*  67 */     this.mLocalName = localName;
/*  68 */     this.mPrefix = prefix;
/*  69 */     this.mValueStartOffset = valueStart;
/*     */   }
/*     */   
/*     */   public void reset(String prefix, String localName, int valueStart) {
/*  74 */     this.mLocalName = localName;
/*  75 */     this.mPrefix = prefix;
/*  76 */     this.mValueStartOffset = valueStart;
/*  77 */     this.mNamespaceURI = null;
/*  78 */     this.mReusableValue = null;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/*  85 */     this.mReusableValue = value;
/*     */   }
/*     */   
/*     */   protected boolean hasQName(String uri, String localName) {
/* 105 */     if (localName != this.mLocalName && !localName.equals(this.mLocalName))
/* 106 */       return false; 
/* 108 */     if (this.mNamespaceURI == uri)
/* 109 */       return true; 
/* 111 */     if (uri == null)
/* 112 */       return (this.mNamespaceURI == null || this.mNamespaceURI.length() == 0); 
/* 114 */     return (this.mNamespaceURI != null && uri.equals(this.mNamespaceURI));
/*     */   }
/*     */   
/*     */   public QName getQName() {
/* 119 */     if (this.mPrefix == null) {
/* 120 */       if (this.mNamespaceURI == null)
/* 121 */         return new QName(this.mLocalName); 
/* 123 */       return new QName(this.mNamespaceURI, this.mLocalName);
/*     */     } 
/* 125 */     String uri = this.mNamespaceURI;
/* 126 */     if (uri == null)
/* 127 */       uri = ""; 
/* 130 */     return QNameCreator.create(uri, this.mLocalName, this.mPrefix);
/*     */   }
/*     */   
/*     */   public String getValue(String allValues) {
/* 139 */     if (this.mReusableValue == null)
/* 140 */       this.mReusableValue = (this.mValueStartOffset == 0) ? allValues : allValues.substring(this.mValueStartOffset); 
/* 143 */     return this.mReusableValue;
/*     */   }
/*     */   
/*     */   public String getValue(String allValues, int endOffset) {
/* 148 */     if (this.mReusableValue == null)
/* 149 */       this.mReusableValue = allValues.substring(this.mValueStartOffset, endOffset); 
/* 151 */     return this.mReusableValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\Attribute.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */