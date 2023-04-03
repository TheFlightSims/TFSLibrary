/*    */ package org.geotools.metadata.iso;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.opengis.metadata.FeatureTypeList;
/*    */ import org.opengis.metadata.SpatialAttributeSupplement;
/*    */ 
/*    */ public class SpatialAttributeSupplementImpl extends MetadataEntity implements SpatialAttributeSupplement {
/*    */   private static final long serialVersionUID = 273337004694210422L;
/*    */   
/*    */   private Collection<FeatureTypeList> featureTypeList;
/*    */   
/*    */   public SpatialAttributeSupplementImpl() {}
/*    */   
/*    */   public SpatialAttributeSupplementImpl(SpatialAttributeSupplement source) {
/* 65 */     super(source);
/*    */   }
/*    */   
/*    */   public SpatialAttributeSupplementImpl(Collection<? extends FeatureTypeList> featureTypeList) {
/* 72 */     setFeatureTypeList(featureTypeList);
/*    */   }
/*    */   
/*    */   public synchronized Collection<FeatureTypeList> getFeatureTypeList() {
/* 79 */     return this.featureTypeList = nonNullCollection(this.featureTypeList, FeatureTypeList.class);
/*    */   }
/*    */   
/*    */   public synchronized void setFeatureTypeList(Collection<? extends FeatureTypeList> newValues) {
/* 88 */     this.featureTypeList = copyCollection(newValues, this.featureTypeList, FeatureTypeList.class);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\SpatialAttributeSupplementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */