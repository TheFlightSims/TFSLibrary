/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class Version implements CharSequence, Comparable<Version>, Serializable {
/*     */   private static final long serialVersionUID = -6793384507333713770L;
/*     */   
/*  50 */   private static final Pattern PATTERN = Pattern.compile("(\\.|\\-)");
/*     */   
/*     */   private final String version;
/*     */   
/*     */   private transient String[] components;
/*     */   
/*     */   private transient Comparable<?>[] parsed;
/*     */   
/*     */   private transient int hashCode;
/*     */   
/*     */   public Version(String version) {
/*  78 */     this.version = version.trim();
/*     */   }
/*     */   
/*     */   public Comparable<?> getMajor() {
/*  88 */     return getComponent(0);
/*     */   }
/*     */   
/*     */   public Comparable<?> getMinor() {
/*  99 */     return getComponent(1);
/*     */   }
/*     */   
/*     */   public Comparable<?> getRevision() {
/* 110 */     return getComponent(2);
/*     */   }
/*     */   
/*     */   public synchronized Comparable<?> getComponent(int index) {
/* 127 */     if (this.parsed == null) {
/* 128 */       if (this.components == null)
/* 129 */         this.components = PATTERN.split(this.version); 
/* 131 */       this.parsed = (Comparable<?>[])new Comparable[this.components.length];
/*     */     } 
/* 133 */     if (index >= this.parsed.length)
/* 134 */       return null; 
/* 136 */     Comparable<?> candidate = this.parsed[index];
/* 137 */     if (candidate == null) {
/* 138 */       String value = this.components[index].trim();
/*     */       try {
/* 140 */         candidate = Integer.valueOf(value);
/* 141 */       } catch (NumberFormatException e) {
/* 142 */         candidate = value;
/*     */       } 
/* 144 */       this.parsed[index] = candidate;
/*     */     } 
/* 146 */     return candidate;
/*     */   }
/*     */   
/*     */   private static int getTypeRank(Object value) {
/* 154 */     if (value instanceof CharSequence)
/* 155 */       return 0; 
/* 157 */     if (value instanceof Number)
/* 158 */       return 1; 
/* 160 */     throw new IllegalArgumentException(String.valueOf(value));
/*     */   }
/*     */   
/*     */   public int compareTo(Version other, int limit) {
/* 176 */     for (int i = 0; i < limit; i++) {
/* 177 */       Comparable<?> v1 = getComponent(i);
/* 178 */       Comparable<?> v2 = other.getComponent(i);
/* 179 */       if (v1 == null)
/* 180 */         return (v2 == null) ? 0 : -1; 
/* 181 */       if (v2 == null)
/* 182 */         return 1; 
/* 184 */       int dr = getTypeRank(v1) - getTypeRank(v2);
/* 185 */       if (dr != 0)
/* 203 */         return dr; 
/* 206 */       int c = v1.compareTo(v2);
/* 207 */       if (c != 0)
/* 208 */         return c; 
/*     */     } 
/* 211 */     return 0;
/*     */   }
/*     */   
/*     */   public int compareTo(Version other) {
/* 223 */     return compareTo(other, 2147483647);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 234 */     if (other != null && getClass().equals(other.getClass()))
/* 235 */       return (compareTo((Version)other) == 0); 
/* 237 */     return false;
/*     */   }
/*     */   
/*     */   public int length() {
/* 244 */     return this.version.length();
/*     */   }
/*     */   
/*     */   public char charAt(int index) {
/* 251 */     return this.version.charAt(index);
/*     */   }
/*     */   
/*     */   public CharSequence subSequence(int start, int end) {
/* 258 */     return this.version.subSequence(start, end);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 266 */     return this.version;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 274 */     if (this.hashCode == 0) {
/* 275 */       int code = -1849991018;
/* 276 */       int index = 0;
/*     */       Comparable<?> component;
/* 278 */       while ((component = getComponent(index)) != null) {
/* 279 */         code = code * 37 + component.hashCode();
/* 280 */         index++;
/*     */       } 
/* 282 */       this.hashCode = code;
/*     */     } 
/* 284 */     return this.hashCode;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */