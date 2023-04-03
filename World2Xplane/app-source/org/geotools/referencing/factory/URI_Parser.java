/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.util.Version;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ 
/*     */ abstract class URI_Parser {
/*     */   private static final char AUTHORITY_CODE_SEPARATOR = ':';
/*     */   
/*     */   public final String uri;
/*     */   
/*     */   public final URI_Type type;
/*     */   
/*     */   public final String authority;
/*     */   
/*     */   public final Version version;
/*     */   
/*     */   public final String code;
/*     */   
/*     */   protected URI_Parser(String uri, URI_Type type, String authority, Version version, String code) {
/*  83 */     this.uri = uri;
/*  84 */     this.type = type;
/*  85 */     this.authority = authority;
/*  86 */     this.version = version;
/*  87 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getAuthorityCode() {
/*  95 */     return this.authority + ':' + this.code;
/*     */   }
/*     */   
/*     */   final void logWarningIfTypeMismatch(AuthorityFactory authorityFactory, Class<? extends AuthorityFactory> expected) {
/* 107 */     if (!expected.isAssignableFrom(this.type.type)) {
/* 109 */       LogRecord record = Loggings.format(Level.WARNING, 30, this.uri);
/* 112 */       record.setSourceClassName(authorityFactory.getClass().getName());
/* 113 */       record.setSourceMethodName("get" + Classes.getShortName(expected));
/* 114 */       Logger logger = AbstractAuthorityFactory.LOGGER;
/* 115 */       record.setLoggerName(logger.getName());
/* 116 */       logger.log(record);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return this.uri;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\URI_Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */