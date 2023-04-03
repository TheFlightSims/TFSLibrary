/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import org.opengis.metadata.FeatureTypeList;
/*     */ 
/*     */ public class FeatureTypeListImpl extends MetadataEntity implements FeatureTypeList {
/*     */   private static final long serialVersionUID = 5417914796207743856L;
/*     */   
/*     */   private String spatialObject;
/*     */   
/*     */   private String spatialSchemaName;
/*     */   
/*     */   public FeatureTypeListImpl() {}
/*     */   
/*     */   public FeatureTypeListImpl(FeatureTypeList source) {
/*  65 */     super(source);
/*     */   }
/*     */   
/*     */   public FeatureTypeListImpl(String spatialObject, String spatialSchemaName) {
/*  74 */     setSpatialObject(spatialObject);
/*  75 */     setSpatialSchemaName(spatialSchemaName);
/*     */   }
/*     */   
/*     */   public String getSpatialObject() {
/*  82 */     return this.spatialObject;
/*     */   }
/*     */   
/*     */   public synchronized void setSpatialObject(String newValue) {
/*  89 */     checkWritePermission();
/*  90 */     this.spatialObject = newValue;
/*     */   }
/*     */   
/*     */   public String getSpatialSchemaName() {
/*  97 */     return this.spatialSchemaName;
/*     */   }
/*     */   
/*     */   public synchronized void setSpatialSchemaName(String newValue) {
/* 104 */     checkWritePermission();
/* 105 */     this.spatialSchemaName = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\FeatureTypeListImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */