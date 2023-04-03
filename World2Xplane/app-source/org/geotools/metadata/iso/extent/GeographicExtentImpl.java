/*    */ package org.geotools.metadata.iso.extent;
/*    */ 
/*    */ import org.geotools.metadata.iso.MetadataEntity;
/*    */ import org.opengis.metadata.extent.GeographicExtent;
/*    */ 
/*    */ public class GeographicExtentImpl extends MetadataEntity implements GeographicExtent {
/*    */   private static final long serialVersionUID = -8844015895495563161L;
/*    */   
/*    */   private Boolean inclusion;
/*    */   
/*    */   public GeographicExtentImpl() {}
/*    */   
/*    */   public GeographicExtentImpl(GeographicExtent extent) {
/* 62 */     super(extent);
/*    */   }
/*    */   
/*    */   public GeographicExtentImpl(boolean inclusion) {
/* 69 */     setInclusion(Boolean.valueOf(inclusion));
/*    */   }
/*    */   
/*    */   public Boolean getInclusion() {
/* 79 */     return this.inclusion;
/*    */   }
/*    */   
/*    */   public synchronized void setInclusion(Boolean newValue) {
/* 87 */     checkWritePermission();
/* 88 */     this.inclusion = newValue;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\GeographicExtentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */