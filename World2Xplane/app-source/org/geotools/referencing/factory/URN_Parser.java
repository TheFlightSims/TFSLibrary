/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Version;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ 
/*     */ final class URN_Parser extends URI_Parser {
/*  43 */   private static final String[] URN_BASES = new String[] { "urn:ogc:def:", "urn:x-ogc:def:" };
/*     */   
/*     */   private static final char URN_SEPARATOR = ':';
/*     */   
/*     */   protected URN_Parser(String urn, URI_Type type, String authority, Version version, String code) {
/*  63 */     super(urn, type, authority, version, code);
/*     */   }
/*     */   
/*     */   public static URN_Parser buildParser(String urn) throws NoSuchAuthorityCodeException {
/*  77 */     String code = urn.trim();
/*  78 */     String type = urn;
/*  79 */     for (int i = 0; i < URN_BASES.length; i++) {
/*  80 */       String urnBase = URN_BASES[i];
/*  81 */       int typeStart = urnBase.length();
/*  82 */       if (code.regionMatches(true, 0, urnBase, 0, typeStart)) {
/*  83 */         int typeEnd = code.indexOf(':', typeStart);
/*  84 */         if (typeEnd >= 0) {
/*  85 */           type = code.substring(typeStart, typeEnd).trim();
/*  86 */           URI_Type candidate = URI_Type.get(type);
/*  87 */           if (candidate != null) {
/*  88 */             int nameEnd = code.indexOf(':', typeEnd + 1);
/*  89 */             if (nameEnd >= 0) {
/*  90 */               int lastEnd = code.lastIndexOf(':');
/*  91 */               Version urnVersion = (lastEnd <= nameEnd) ? null : new Version(code.substring(nameEnd + 1, lastEnd));
/*  93 */               String urnAuthority = code.substring(typeEnd + 1, nameEnd).trim();
/*  94 */               String urnCode = code.substring(lastEnd + 1).trim();
/*  95 */               URI_Type urnType = candidate;
/*  96 */               return new URN_Parser(urn, urnType, urnAuthority, urnVersion, urnCode);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 102 */     throw new NoSuchAuthorityCodeException(Errors.format(67, type), "urn:ogc:def", type);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\URN_Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */