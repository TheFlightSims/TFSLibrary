/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.spatial.GeometricObjectType;
/*     */ import org.opengis.metadata.spatial.GeometricObjects;
/*     */ 
/*     */ public class GeometricObjectsImpl extends MetadataEntity implements GeometricObjects {
/*     */   private static final long serialVersionUID = 8755950031078638313L;
/*     */   
/*     */   private GeometricObjectType geometricObjectType;
/*     */   
/*     */   private Integer geometricObjectCount;
/*     */   
/*     */   public GeometricObjectsImpl() {}
/*     */   
/*     */   public GeometricObjectsImpl(GeometricObjects source) {
/*  67 */     super(source);
/*     */   }
/*     */   
/*     */   public GeometricObjectsImpl(GeometricObjectType geometricObjectType) {
/*  74 */     setGeometricObjectType(geometricObjectType);
/*     */   }
/*     */   
/*     */   public GeometricObjectType getGeometricObjectType() {
/*  81 */     return this.geometricObjectType;
/*     */   }
/*     */   
/*     */   public synchronized void setGeometricObjectType(GeometricObjectType newValue) {
/*  88 */     checkWritePermission();
/*  89 */     this.geometricObjectType = newValue;
/*     */   }
/*     */   
/*     */   public Integer getGeometricObjectCount() {
/*  96 */     return this.geometricObjectCount;
/*     */   }
/*     */   
/*     */   public synchronized void setGeometricObjectCount(Integer newValue) {
/* 103 */     checkWritePermission();
/* 104 */     this.geometricObjectCount = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\GeometricObjectsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */