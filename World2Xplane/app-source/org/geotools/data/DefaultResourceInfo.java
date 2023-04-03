/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class DefaultResourceInfo implements ResourceInfo {
/*     */   private String title;
/*     */   
/*     */   private URI schema;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private Set<String> keywords;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   private ReferencedEnvelope bounds;
/*     */   
/*     */   public DefaultResourceInfo() {}
/*     */   
/*     */   public DefaultResourceInfo(ResourceInfo copy) {
/*  49 */     this.title = copy.getTitle();
/*  50 */     this.schema = copy.getSchema();
/*  51 */     this.name = copy.getName();
/*  52 */     this.keywords = new HashSet<String>();
/*  53 */     if (copy.getKeywords() != null)
/*  54 */       this.keywords.addAll(copy.getKeywords()); 
/*  56 */     this.description = copy.getDescription();
/*  57 */     this.crs = copy.getCRS();
/*  58 */     this.bounds = copy.getBounds();
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  64 */     return this.title;
/*     */   }
/*     */   
/*     */   public URI getSchema() {
/*  71 */     return this.schema;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  78 */     return this.name;
/*     */   }
/*     */   
/*     */   public Set<String> getKeywords() {
/*  85 */     return this.keywords;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  92 */     return this.description;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/*  99 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCRS() {
/* 106 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCRS(CoordinateReferenceSystem crs) {
/* 113 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 120 */     this.title = title;
/*     */   }
/*     */   
/*     */   public void setSchema(URI schema) {
/* 127 */     this.schema = schema;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 134 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setKeywords(Set<String> keywords) {
/* 141 */     this.keywords = keywords;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 148 */     this.description = description;
/*     */   }
/*     */   
/*     */   public void setBounds(ReferencedEnvelope bounds) {
/* 155 */     this.bounds = bounds;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultResourceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */