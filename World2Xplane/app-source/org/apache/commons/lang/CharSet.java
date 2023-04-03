/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CharSet implements Serializable {
/*     */   private static final long serialVersionUID = 5947847346149275958L;
/*     */   
/*  53 */   public static final CharSet EMPTY = new CharSet((String)null);
/*     */   
/*  59 */   public static final CharSet ASCII_ALPHA = new CharSet("a-zA-Z");
/*     */   
/*  65 */   public static final CharSet ASCII_ALPHA_LOWER = new CharSet("a-z");
/*     */   
/*  71 */   public static final CharSet ASCII_ALPHA_UPPER = new CharSet("A-Z");
/*     */   
/*  77 */   public static final CharSet ASCII_NUMERIC = new CharSet("0-9");
/*     */   
/*  84 */   protected static final Map COMMON = Collections.synchronizedMap(new HashMap());
/*     */   
/*     */   static {
/*  87 */     COMMON.put(null, EMPTY);
/*  88 */     COMMON.put("", EMPTY);
/*  89 */     COMMON.put("a-zA-Z", ASCII_ALPHA);
/*  90 */     COMMON.put("A-Za-z", ASCII_ALPHA);
/*  91 */     COMMON.put("a-z", ASCII_ALPHA_LOWER);
/*  92 */     COMMON.put("A-Z", ASCII_ALPHA_UPPER);
/*  93 */     COMMON.put("0-9", ASCII_NUMERIC);
/*     */   }
/*     */   
/*  97 */   private final Set set = Collections.synchronizedSet(new HashSet());
/*     */   
/*     */   public static CharSet getInstance(String setStr) {
/* 144 */     Object set = COMMON.get(setStr);
/* 145 */     if (set != null)
/* 146 */       return (CharSet)set; 
/* 148 */     return new CharSet(setStr);
/*     */   }
/*     */   
/*     */   public static CharSet getInstance(String[] setStrs) {
/* 160 */     if (setStrs == null)
/* 161 */       return null; 
/* 163 */     return new CharSet(setStrs);
/*     */   }
/*     */   
/*     */   protected CharSet(String setStr) {
/* 175 */     add(setStr);
/*     */   }
/*     */   
/*     */   protected CharSet(String[] set) {
/* 187 */     int sz = set.length;
/* 188 */     for (int i = 0; i < sz; i++)
/* 189 */       add(set[i]); 
/*     */   }
/*     */   
/*     */   protected void add(String str) {
/* 200 */     if (str == null)
/*     */       return; 
/* 204 */     int len = str.length();
/* 205 */     int pos = 0;
/* 206 */     while (pos < len) {
/* 207 */       int remainder = len - pos;
/* 208 */       if (remainder >= 4 && str.charAt(pos) == '^' && str.charAt(pos + 2) == '-') {
/* 210 */         this.set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
/* 211 */         pos += 4;
/*     */         continue;
/*     */       } 
/* 212 */       if (remainder >= 3 && str.charAt(pos + 1) == '-') {
/* 214 */         this.set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
/* 215 */         pos += 3;
/*     */         continue;
/*     */       } 
/* 216 */       if (remainder >= 2 && str.charAt(pos) == '^') {
/* 218 */         this.set.add(CharRange.isNot(str.charAt(pos + 1)));
/* 219 */         pos += 2;
/*     */         continue;
/*     */       } 
/* 222 */       this.set.add(CharRange.is(str.charAt(pos)));
/* 223 */       pos++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public CharRange[] getCharRanges() {
/* 236 */     return (CharRange[])this.set.toArray((Object[])new CharRange[this.set.size()]);
/*     */   }
/*     */   
/*     */   public boolean contains(char ch) {
/* 248 */     for (Iterator it = this.set.iterator(); it.hasNext(); ) {
/* 249 */       CharRange range = it.next();
/* 250 */       if (range.contains(ch))
/* 251 */         return true; 
/*     */     } 
/* 254 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 271 */     if (obj == this)
/* 272 */       return true; 
/* 274 */     if (!(obj instanceof CharSet))
/* 275 */       return false; 
/* 277 */     CharSet other = (CharSet)obj;
/* 278 */     return this.set.equals(other.set);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 288 */     return 89 + this.set.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 297 */     return this.set.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\CharSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */