/*    */ package org.geotools.metadata.iso.extent;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import org.opengis.metadata.extent.GeographicExtent;
/*    */ import org.opengis.metadata.extent.SpatialTemporalExtent;
/*    */ import org.opengis.metadata.extent.TemporalExtent;
/*    */ 
/*    */ public class SpatialTemporalExtentImpl extends TemporalExtentImpl implements SpatialTemporalExtent {
/*    */   private static final long serialVersionUID = 821702768255546660L;
/*    */   
/*    */   private Collection<GeographicExtent> spatialExtent;
/*    */   
/*    */   public SpatialTemporalExtentImpl() {}
/*    */   
/*    */   public SpatialTemporalExtentImpl(SpatialTemporalExtent source) {
/* 66 */     super((TemporalExtent)source);
/*    */   }
/*    */   
/*    */   public SpatialTemporalExtentImpl(Date startTime, Date endTime, Collection<? extends GeographicExtent> spatialExtent) {
/* 75 */     super(startTime, endTime);
/* 76 */     setSpatialExtent(spatialExtent);
/*    */   }
/*    */   
/*    */   public synchronized Collection<GeographicExtent> getSpatialExtent() {
/* 86 */     return this.spatialExtent = nonNullCollection(this.spatialExtent, GeographicExtent.class);
/*    */   }
/*    */   
/*    */   public synchronized void setSpatialExtent(Collection<? extends GeographicExtent> newValues) {
/* 96 */     this.spatialExtent = copyCollection(newValues, this.spatialExtent, GeographicExtent.class);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\SpatialTemporalExtentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */