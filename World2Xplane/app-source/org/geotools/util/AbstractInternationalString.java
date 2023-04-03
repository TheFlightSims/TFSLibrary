/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractInternationalString implements InternationalString {
/*     */   transient String defaultValue;
/*     */   
/*     */   static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/*  77 */     if (object == null)
/*  78 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */   
/*     */   public int length() {
/*  87 */     if (this.defaultValue == null) {
/*  88 */       this.defaultValue = toString();
/*  89 */       if (this.defaultValue == null)
/*  90 */         return 0; 
/*     */     } 
/*  93 */     return this.defaultValue.length();
/*     */   }
/*     */   
/*     */   public char charAt(int index) throws IndexOutOfBoundsException {
/* 105 */     if (this.defaultValue == null) {
/* 106 */       this.defaultValue = toString();
/* 107 */       if (this.defaultValue == null)
/* 108 */         throw new IndexOutOfBoundsException(String.valueOf(index)); 
/*     */     } 
/* 111 */     return this.defaultValue.charAt(index);
/*     */   }
/*     */   
/*     */   public CharSequence subSequence(int start, int end) {
/* 125 */     if (this.defaultValue == null) {
/* 126 */       this.defaultValue = toString();
/* 127 */       if (this.defaultValue == null)
/* 128 */         throw new IndexOutOfBoundsException(String.valueOf(start)); 
/*     */     } 
/* 131 */     return this.defaultValue.substring(start, end);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 155 */     if (this.defaultValue == null) {
/* 156 */       this.defaultValue = toString(Locale.getDefault());
/* 157 */       if (this.defaultValue == null)
/* 158 */         return ""; 
/*     */     } 
/* 161 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public int compareTo(InternationalString object) {
/* 174 */     return toString().compareTo(object.toString());
/*     */   }
/*     */   
/*     */   public abstract String toString(Locale paramLocale);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\AbstractInternationalString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */