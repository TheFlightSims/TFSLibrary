/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ 
/*     */ public final class BijectiveNsMap {
/*     */   static final int DEFAULT_ARRAY_SIZE = 32;
/*     */   
/*     */   final int mScopeStart;
/*     */   
/*     */   String[] mNsStrings;
/*     */   
/*     */   int mScopeEnd;
/*     */   
/*     */   private BijectiveNsMap(int scopeStart, String[] strs) {
/*  75 */     this.mScopeStart = this.mScopeEnd = scopeStart;
/*  76 */     this.mNsStrings = strs;
/*     */   }
/*     */   
/*     */   public static BijectiveNsMap createEmpty() {
/*  81 */     String[] strs = new String[32];
/*  83 */     strs[0] = "xml";
/*  84 */     strs[1] = "http://www.w3.org/XML/1998/namespace";
/*  85 */     strs[2] = "xmlns";
/*  86 */     strs[3] = "http://www.w3.org/2000/xmlns/";
/*  91 */     return new BijectiveNsMap(4, strs);
/*     */   }
/*     */   
/*     */   public BijectiveNsMap createChild() {
/*  95 */     return new BijectiveNsMap(this.mScopeEnd, this.mNsStrings);
/*     */   }
/*     */   
/*     */   public String findUriByPrefix(String prefix) {
/* 109 */     String[] strs = this.mNsStrings;
/* 110 */     int phash = prefix.hashCode();
/* 112 */     for (int ix = this.mScopeEnd - 2; ix >= 0; ix -= 2) {
/* 113 */       String thisP = strs[ix];
/* 114 */       if (thisP == prefix || (thisP.hashCode() == phash && thisP.equals(prefix)))
/* 116 */         return strs[ix + 1]; 
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public String findPrefixByUri(String uri) {
/* 132 */     String[] strs = this.mNsStrings;
/* 133 */     int uhash = uri.hashCode();
/*     */     int ix;
/* 136 */     label25: for (ix = this.mScopeEnd - 1; ix > 0; ix -= 2) {
/* 137 */       String thisU = strs[ix];
/* 138 */       if (thisU == uri || (thisU.hashCode() == uhash && thisU.equals(uri))) {
/* 141 */         String prefix = strs[ix - 1];
/* 145 */         if (ix < this.mScopeStart) {
/* 146 */           int phash = prefix.hashCode();
/* 147 */           for (int j = ix + 1, end = this.mScopeEnd; j < end; ) {
/* 148 */             String thisP = strs[j];
/* 149 */             if (thisP != prefix) {
/* 149 */               if (thisP.hashCode() == phash && thisP.equals(prefix))
/*     */                 continue label25; 
/*     */               j += 2;
/*     */             } 
/*     */             continue label25;
/*     */           } 
/*     */         } 
/* 157 */         return prefix;
/*     */       } 
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public List getPrefixesBoundToUri(String uri, List l) {
/* 168 */     String[] strs = this.mNsStrings;
/* 169 */     int uhash = uri.hashCode();
/*     */     int ix;
/* 172 */     label27: for (ix = this.mScopeEnd - 1; ix > 0; ix -= 2) {
/* 173 */       String thisU = strs[ix];
/* 174 */       if (thisU == uri || (thisU.hashCode() == uhash && thisU.equals(uri))) {
/* 177 */         String prefix = strs[ix - 1];
/* 181 */         if (ix < this.mScopeStart) {
/* 182 */           int phash = prefix.hashCode();
/* 183 */           for (int j = ix + 1, end = this.mScopeEnd; j < end; ) {
/* 184 */             String thisP = strs[j];
/* 185 */             if (thisP != prefix) {
/* 185 */               if (thisP.hashCode() == phash && thisP.equals(prefix))
/*     */                 continue label27; 
/*     */               j += 2;
/*     */             } 
/*     */             continue label27;
/*     */           } 
/*     */         } 
/* 193 */         if (l == null)
/* 194 */           l = new ArrayList(); 
/* 196 */         l.add(prefix);
/*     */       } 
/*     */     } 
/* 199 */     return l;
/*     */   }
/*     */   
/*     */   public int size() {
/* 203 */     return this.mScopeEnd >> 1;
/*     */   }
/*     */   
/*     */   public int localSize() {
/* 207 */     return this.mScopeEnd - this.mScopeStart >> 1;
/*     */   }
/*     */   
/*     */   public String addMapping(String prefix, String uri) {
/* 229 */     String[] strs = this.mNsStrings;
/* 230 */     int phash = prefix.hashCode();
/* 232 */     for (int ix = this.mScopeStart, end = this.mScopeEnd; ix < end; ix += 2) {
/* 233 */       String thisP = strs[ix];
/* 234 */       if (thisP == prefix || (thisP.hashCode() == phash && thisP.equals(prefix))) {
/* 237 */         String old = strs[ix + 1];
/* 238 */         strs[ix + 1] = uri;
/* 239 */         return old;
/*     */       } 
/*     */     } 
/* 243 */     if (this.mScopeEnd >= strs.length) {
/* 245 */       strs = DataUtil.growArrayBy(strs, strs.length);
/* 246 */       this.mNsStrings = strs;
/*     */     } 
/* 248 */     strs[this.mScopeEnd++] = prefix;
/* 249 */     strs[this.mScopeEnd++] = uri;
/* 251 */     return null;
/*     */   }
/*     */   
/*     */   public String addGeneratedMapping(String prefixBase, NamespaceContext ctxt, String uri, int[] seqArr) {
/* 261 */     String prefix, strs[] = this.mNsStrings;
/* 262 */     int seqNr = seqArr[0];
/*     */     label21: while (true) {
/* 270 */       prefix = (prefixBase + seqNr).intern();
/* 271 */       seqNr++;
/* 278 */       int phash = prefix.hashCode();
/* 280 */       for (int ix = this.mScopeEnd - 2; ix >= 0; ) {
/* 281 */         String thisP = strs[ix];
/* 282 */         if (thisP != prefix) {
/* 282 */           if (thisP.hashCode() == phash && thisP.equals(prefix))
/*     */             continue label21; 
/*     */           ix -= 2;
/*     */         } 
/*     */         continue label21;
/*     */       } 
/* 291 */       if (ctxt != null && ctxt.getNamespaceURI(prefix) != null)
/*     */         continue; 
/*     */       break;
/*     */     } 
/* 296 */     seqArr[0] = seqNr;
/* 299 */     if (this.mScopeEnd >= strs.length) {
/* 301 */       strs = DataUtil.growArrayBy(strs, strs.length);
/* 302 */       this.mNsStrings = strs;
/*     */     } 
/* 304 */     strs[this.mScopeEnd++] = prefix;
/* 305 */     strs[this.mScopeEnd++] = uri;
/* 307 */     return prefix;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 317 */     return "[" + getClass().toString() + "; " + size() + " entries; of which " + localSize() + " local]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\BijectiveNsMap.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */