/*    */ package org.geotools.metadata.iso.extent;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.opengis.geometry.Geometry;
/*    */ import org.opengis.metadata.extent.BoundingPolygon;
/*    */ import org.opengis.metadata.extent.GeographicExtent;
/*    */ 
/*    */ public class BoundingPolygonImpl extends GeographicExtentImpl implements BoundingPolygon {
/*    */   private static final long serialVersionUID = 8174011874910887918L;
/*    */   
/*    */   private Collection<Geometry> polygons;
/*    */   
/*    */   public BoundingPolygonImpl() {}
/*    */   
/*    */   public BoundingPolygonImpl(BoundingPolygon source) {
/* 64 */     super((GeographicExtent)source);
/*    */   }
/*    */   
/*    */   public BoundingPolygonImpl(Collection<Geometry> polygons) {
/* 71 */     setPolygons(polygons);
/*    */   }
/*    */   
/*    */   public synchronized Collection<Geometry> getPolygons() {
/* 79 */     return this.polygons = nonNullCollection(this.polygons, Geometry.class);
/*    */   }
/*    */   
/*    */   public synchronized void setPolygons(Collection<? extends Geometry> newValues) {
/* 86 */     this.polygons = copyCollection(newValues, this.polygons, Geometry.class);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\BoundingPolygonImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */