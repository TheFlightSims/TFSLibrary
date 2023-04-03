/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.util.prefs.Preferences;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ 
/*     */ public class Formattable {
/*     */   static final String INDENTATION = "Indentation";
/*     */   
/*  59 */   private static final ThreadLocal<Formatter> FORMATTER = new ThreadLocal<Formatter>();
/*     */   
/*     */   public static final int SINGLE_LINE = 0;
/*     */   
/*     */   public String toString() {
/*  88 */     return toWKT(Citations.OGC, getIndentation(), false);
/*     */   }
/*     */   
/*     */   public String toWKT() throws UnformattableObjectException {
/* 105 */     return toWKT(getIndentation());
/*     */   }
/*     */   
/*     */   public String toWKT(int indentation) throws UnformattableObjectException {
/* 123 */     return toWKT(Citations.OGC, indentation);
/*     */   }
/*     */   
/*     */   public String toWKT(int indentation, boolean strict) throws UnformattableObjectException {
/* 147 */     return toWKT(Citations.OGC, indentation, strict);
/*     */   }
/*     */   
/*     */   public String toWKT(Citation authority, int indentation) throws UnformattableObjectException {
/* 168 */     return toWKT(authority, indentation, true);
/*     */   }
/*     */   
/*     */   private String toWKT(Citation authority, int indentation, boolean strict) throws UnformattableObjectException {
/* 179 */     if (authority == null)
/* 180 */       throw new IllegalArgumentException(Errors.format(143, "authority")); 
/* 183 */     Formatter formatter = FORMATTER.get();
/* 184 */     if (formatter == null || formatter.indentation != indentation || formatter.getAuthority() != authority) {
/* 188 */       formatter = new Formatter(Symbols.DEFAULT, indentation);
/* 189 */       formatter.setAuthority(authority);
/* 190 */       FORMATTER.set(formatter);
/*     */     } 
/*     */     try {
/* 193 */       if (this instanceof GeneralParameterValue) {
/* 197 */         formatter.append((GeneralParameterValue)this);
/*     */       } else {
/* 199 */         formatter.append(this);
/*     */       } 
/* 201 */       if (strict && formatter.isInvalidWKT()) {
/* 202 */         Class unformattable = formatter.getUnformattableClass();
/* 203 */         throw new UnformattableObjectException(formatter.warning, unformattable);
/*     */       } 
/* 205 */       return formatter.toString();
/*     */     } finally {
/* 207 */       formatter.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 238 */     Class<?> type = getClass();
/* 239 */     formatter.setInvalidWKT(type);
/* 240 */     Class[] interfaces = type.getInterfaces();
/* 241 */     for (int i = 0; i < interfaces.length; i++) {
/* 242 */       Class<?> candidate = interfaces[i];
/* 243 */       if (candidate.getName().startsWith("org.opengis.referencing.")) {
/* 244 */         type = candidate;
/*     */         break;
/*     */       } 
/*     */     } 
/* 248 */     return Classes.getShortName(type);
/*     */   }
/*     */   
/*     */   static int getIndentation() {
/*     */     try {
/* 256 */       return Preferences.userNodeForPackage(Formattable.class).getInt("Indentation", 2);
/* 257 */     } catch (SecurityException ignore) {
/* 259 */       return 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void setIndentation(int indentation) throws SecurityException {
/* 270 */     Preferences.userNodeForPackage(Formattable.class).putInt("Indentation", indentation);
/*     */   }
/*     */   
/*     */   public static void cleanupThreadLocals() {
/* 278 */     FORMATTER.remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Formattable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */