/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public final class NameFactory {
/*     */   public static GenericName create(String name) {
/*  54 */     return create(name, ':');
/*     */   }
/*     */   
/*     */   public static GenericName create(String name, char separator) {
/*  65 */     List<String> names = new ArrayList<String>();
/*  66 */     int lower = 0;
/*     */     while (true) {
/*  68 */       int upper = name.indexOf(separator, lower);
/*  69 */       if (upper >= 0) {
/*  70 */         names.add(name.substring(lower, upper));
/*  71 */         lower = upper + 1;
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/*  73 */     names.add(name.substring(lower));
/*  77 */     return create(names.<CharSequence>toArray((CharSequence[])new String[names.size()]), separator);
/*     */   }
/*     */   
/*     */   public static GenericName create(CharSequence[] names) {
/*  91 */     return create(names, ':');
/*     */   }
/*     */   
/*     */   public static GenericName create(CharSequence[] names, char separator) {
/* 106 */     return create(names, names.length, separator);
/*     */   }
/*     */   
/*     */   private static GenericName create(CharSequence[] names, int length, char separator) {
/* 121 */     if (length <= 0)
/* 122 */       throw new IllegalArgumentException(String.valueOf(length)); 
/* 124 */     if (length == 1)
/* 125 */       return new LocalName(names[0]); 
/* 127 */     return new ScopedName(create(names, length - 1, separator), separator, names[length - 1]);
/*     */   }
/*     */   
/*     */   public static GenericName[] toArray(Object value) throws ClassCastException {
/* 140 */     if (value instanceof GenericName[])
/* 141 */       return (GenericName[])value; 
/* 143 */     if (value instanceof GenericName)
/* 144 */       return new GenericName[] { (GenericName)value }; 
/* 148 */     if (value instanceof CharSequence)
/* 149 */       return new GenericName[] { create(value.toString()) }; 
/* 153 */     if (value instanceof CharSequence[]) {
/* 154 */       CharSequence[] values = (CharSequence[])value;
/* 155 */       GenericName[] names = new GenericName[values.length];
/* 156 */       for (int i = 0; i < values.length; i++) {
/* 157 */         CharSequence v = values[i];
/* 158 */         names[i] = (v instanceof GenericName) ? (GenericName)v : create(v.toString());
/*     */       } 
/* 160 */       return names;
/*     */     } 
/* 162 */     if (value instanceof Identifier[]) {
/* 163 */       Identifier[] values = (Identifier[])value;
/* 164 */       GenericName[] names = new GenericName[values.length];
/* 165 */       for (int i = 0; i < values.length; i++) {
/* 166 */         Identifier v = values[i];
/* 167 */         names[i] = (v instanceof GenericName) ? (GenericName)v : create(v.getCode());
/*     */       } 
/* 169 */       return names;
/*     */     } 
/* 172 */     throw new ClassCastException("Cannot convert " + Classes.getShortClassName(value) + " to GenericName[]");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\NameFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */