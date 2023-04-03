/*     */ package com.ctc.wstx.sr;
/*     */ 
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ public final class ElemAttrs {
/*     */   private static final int OFFSET_NS_URI = 1;
/*     */   
/*     */   private final String[] mRawAttrs;
/*     */   
/*     */   private final int mDefaultOffset;
/*     */   
/*     */   private final int[] mAttrMap;
/*     */   
/*     */   private final int mAttrHashSize;
/*     */   
/*     */   private final int mAttrSpillEnd;
/*     */   
/*     */   public ElemAttrs(String[] rawAttrs, int defOffset) {
/*  71 */     this.mRawAttrs = rawAttrs;
/*  72 */     this.mAttrMap = null;
/*  73 */     this.mAttrHashSize = 0;
/*  74 */     this.mAttrSpillEnd = 0;
/*  75 */     this.mDefaultOffset = defOffset << 2;
/*     */   }
/*     */   
/*     */   public ElemAttrs(String[] rawAttrs, int defOffset, int[] attrMap, int hashSize, int spillEnd) {
/*  91 */     this.mRawAttrs = rawAttrs;
/*  92 */     this.mDefaultOffset = defOffset << 2;
/*  93 */     this.mAttrMap = attrMap;
/*  94 */     this.mAttrHashSize = hashSize;
/*  95 */     this.mAttrSpillEnd = spillEnd;
/*     */   }
/*     */   
/*     */   public String[] getRawAttrs() {
/* 105 */     return this.mRawAttrs;
/*     */   }
/*     */   
/*     */   public int findIndex(QName name) {
/* 111 */     if (this.mAttrMap != null)
/* 112 */       return findMapIndex(name.getNamespaceURI(), name.getLocalPart()); 
/* 116 */     String ln = name.getLocalPart();
/* 117 */     String uri = name.getNamespaceURI();
/* 118 */     boolean defaultNs = (uri == null || uri.length() == 0);
/* 119 */     String[] raw = this.mRawAttrs;
/* 121 */     for (int i = 0, len = raw.length; i < len; i += 4) {
/* 122 */       if (ln.equals(raw[i])) {
/* 125 */         String thisUri = raw[i + 1];
/* 126 */         if (defaultNs) {
/* 127 */           if (thisUri == null || thisUri.length() == 0)
/* 128 */             return i; 
/* 131 */         } else if (thisUri != null && (thisUri == uri || thisUri.equals(uri))) {
/* 133 */           return i;
/*     */         } 
/*     */       } 
/*     */     } 
/* 137 */     return -1;
/*     */   }
/*     */   
/*     */   public int getFirstDefaultOffset() {
/* 141 */     return this.mDefaultOffset;
/*     */   }
/*     */   
/*     */   public boolean isDefault(int ix) {
/* 145 */     return (ix >= this.mDefaultOffset);
/*     */   }
/*     */   
/*     */   private final int findMapIndex(String nsURI, String localName) {
/* 165 */     int hash = localName.hashCode();
/* 166 */     if (nsURI == null) {
/* 167 */       nsURI = "";
/* 168 */     } else if (nsURI.length() > 0) {
/* 169 */       hash ^= nsURI.hashCode();
/*     */     } 
/* 171 */     int ix = this.mAttrMap[hash & this.mAttrHashSize - 1];
/* 172 */     if (ix == 0)
/* 173 */       return -1; 
/* 176 */     ix = ix - 1 << 2;
/* 179 */     String[] raw = this.mRawAttrs;
/* 180 */     String thisName = raw[ix];
/* 185 */     if (thisName == localName || thisName.equals(localName)) {
/* 186 */       String thisURI = raw[ix + 1];
/* 187 */       if (thisURI == nsURI)
/* 188 */         return ix; 
/* 190 */       if (thisURI == null) {
/* 191 */         if (nsURI.length() == 0)
/* 192 */           return ix; 
/* 194 */       } else if (thisURI.equals(nsURI)) {
/* 195 */         return ix;
/*     */       } 
/*     */     } 
/* 202 */     for (int i = this.mAttrHashSize, len = this.mAttrSpillEnd; i < len; i += 2) {
/* 203 */       if (this.mAttrMap[i] == hash) {
/* 209 */         ix = this.mAttrMap[i + 1] << 2;
/* 210 */         thisName = raw[ix];
/* 211 */         if (thisName == localName || thisName.equals(localName)) {
/* 212 */           String thisURI = raw[ix + 1];
/* 213 */           if (thisURI == nsURI)
/* 214 */             return ix; 
/* 216 */           if (thisURI == null) {
/* 217 */             if (nsURI.length() == 0)
/* 218 */               return ix; 
/* 220 */           } else if (thisURI.equals(nsURI)) {
/* 221 */             return ix;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 226 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\ElemAttrs.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */