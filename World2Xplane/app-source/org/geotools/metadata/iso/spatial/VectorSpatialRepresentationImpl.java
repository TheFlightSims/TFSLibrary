/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.opengis.metadata.spatial.GeometricObjects;
/*     */ import org.opengis.metadata.spatial.SpatialRepresentation;
/*     */ import org.opengis.metadata.spatial.TopologyLevel;
/*     */ import org.opengis.metadata.spatial.VectorSpatialRepresentation;
/*     */ 
/*     */ public class VectorSpatialRepresentationImpl extends SpatialRepresentationImpl implements VectorSpatialRepresentation {
/*     */   private static final long serialVersionUID = 5643234643524810592L;
/*     */   
/*     */   private TopologyLevel topologyLevel;
/*     */   
/*     */   private Collection<GeometricObjects> geometricObjects;
/*     */   
/*     */   public VectorSpatialRepresentationImpl() {}
/*     */   
/*     */   public VectorSpatialRepresentationImpl(VectorSpatialRepresentation source) {
/*  70 */     super((SpatialRepresentation)source);
/*     */   }
/*     */   
/*     */   public TopologyLevel getTopologyLevel() {
/*  77 */     return this.topologyLevel;
/*     */   }
/*     */   
/*     */   public synchronized void setTopologyLevel(TopologyLevel newValue) {
/*  84 */     checkWritePermission();
/*  85 */     this.topologyLevel = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<GeometricObjects> getGeometricObjects() {
/*  92 */     return this.geometricObjects = nonNullCollection(this.geometricObjects, GeometricObjects.class);
/*     */   }
/*     */   
/*     */   public synchronized void setGeometricObjects(Collection<? extends GeometricObjects> newValues) {
/* 101 */     this.geometricObjects = copyCollection(newValues, this.geometricObjects, GeometricObjects.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\VectorSpatialRepresentationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */