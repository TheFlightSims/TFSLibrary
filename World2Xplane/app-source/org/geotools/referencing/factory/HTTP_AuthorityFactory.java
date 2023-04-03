/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ public class HTTP_AuthorityFactory extends AuthorityFactoryAdapter implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory {
/*     */   public static final String BASE_URL = "http://www.opengis.net/gml/srs/";
/*     */   
/*     */   public HTTP_AuthorityFactory() {
/*  59 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public HTTP_AuthorityFactory(Hints userHints) {
/*  71 */     this(getFactory(userHints, "http"));
/*     */   }
/*     */   
/*     */   public HTTP_AuthorityFactory(AllAuthoritiesFactory factory) {
/*  80 */     super(factory);
/*     */   }
/*     */   
/*     */   static boolean defaultAxisOrderHints(Hints hints, String authority) {
/*  94 */     Object value = null;
/*  95 */     if (hints != null)
/*  96 */       value = hints.get(Hints.FORCE_AXIS_ORDER_HONORING); 
/*  98 */     if (value == null)
/*  99 */       value = Hints.getSystemDefault((RenderingHints.Key)Hints.FORCE_AXIS_ORDER_HONORING); 
/* 101 */     if (value instanceof CharSequence) {
/* 102 */       String list = value.toString();
/* 103 */       int i = 0;
/* 104 */       while ((i = list.indexOf(authority, i)) >= 0) {
/* 105 */         if (i == 0 || !Character.isJavaIdentifierPart(list.charAt(i - 1))) {
/* 106 */           int j = i + authority.length();
/* 107 */           if (j == list.length() || !Character.isJavaIdentifierPart(list.charAt(j)))
/* 109 */             return true; 
/*     */         } 
/* 112 */         i++;
/*     */       } 
/*     */     } 
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   static AllAuthoritiesFactory getFactory(Hints hints, String authority) {
/* 123 */     if (!defaultAxisOrderHints(hints, authority)) {
/* 124 */       hints = new Hints((RenderingHints)hints);
/* 125 */       hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/*     */     } 
/* 127 */     return new AllAuthoritiesFactory(hints);
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 135 */     return Citations.HTTP_OGC;
/*     */   }
/*     */   
/*     */   protected String toBackingFactoryCode(String code) throws FactoryException {
/* 148 */     code = code.trim();
/* 149 */     int length = "http://www.opengis.net/gml/srs/".length();
/* 150 */     if (code.regionMatches(true, 0, "http://www.opengis.net/gml/srs/", 0, length)) {
/* 151 */       code = code.substring(length);
/* 152 */       if (code.indexOf('/') < 0) {
/* 153 */         int split = code.indexOf('#');
/* 154 */         if (split >= 0 && code.indexOf('#', split + 1) < 0) {
/* 155 */           String authority = code.substring(0, split).trim();
/* 156 */           int ext = authority.lastIndexOf('.');
/* 157 */           if (ext > 0)
/* 159 */             authority = authority.substring(0, ext); 
/* 161 */           code = code.substring(split + 1).trim();
/* 162 */           code = authority + ':' + code;
/* 163 */           return code;
/*     */         } 
/*     */       } 
/*     */     } 
/* 167 */     throw new NoSuchAuthorityCodeException(Errors.format(58, "code", code), "http://www.opengis.net/gml/srs/", code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\HTTP_AuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */