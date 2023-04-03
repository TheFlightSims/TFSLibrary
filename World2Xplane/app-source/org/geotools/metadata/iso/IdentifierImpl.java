/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ 
/*     */ public class IdentifierImpl extends MetadataEntity implements Identifier {
/*     */   private static final long serialVersionUID = 7459062382170865919L;
/*     */   
/*     */   private String code;
/*     */   
/*     */   private String version;
/*     */   
/*     */   private Citation authority;
/*     */   
/*     */   public IdentifierImpl() {}
/*     */   
/*     */   public IdentifierImpl(Identifier source) {
/*  76 */     super(source);
/*     */   }
/*     */   
/*     */   public IdentifierImpl(String code) {
/*  83 */     setCode(code);
/*     */   }
/*     */   
/*     */   public IdentifierImpl(Citation authority, String code) {
/*  92 */     setAuthority(authority);
/*  93 */     setCode(code);
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 102 */     return this.code;
/*     */   }
/*     */   
/*     */   public synchronized void setCode(String newValue) {
/* 109 */     checkWritePermission();
/* 110 */     this.code = newValue;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 123 */     return this.version;
/*     */   }
/*     */   
/*     */   public synchronized void setVersion(String newValue) {
/* 130 */     checkWritePermission();
/* 131 */     this.version = newValue;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 141 */     return this.authority;
/*     */   }
/*     */   
/*     */   public synchronized void setAuthority(Citation newValue) {
/* 149 */     checkWritePermission();
/* 150 */     this.authority = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\IdentifierImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */