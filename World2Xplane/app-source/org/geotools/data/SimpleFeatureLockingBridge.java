/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.geotools.data.simple.SimpleFeatureLocking;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ class SimpleFeatureLockingBridge extends SimpleFeatureStoreBridge implements SimpleFeatureLocking {
/*    */   public SimpleFeatureLockingBridge(FeatureLocking<SimpleFeatureType, SimpleFeature> delegate) {
/* 33 */     super(delegate);
/*    */   }
/*    */   
/*    */   protected FeatureLocking<SimpleFeatureType, SimpleFeature> delegate() {
/* 37 */     return (FeatureLocking<SimpleFeatureType, SimpleFeature>)this.delegate;
/*    */   }
/*    */   
/*    */   public int lockFeatures(Query query) throws IOException {
/* 41 */     return delegate().lockFeatures(query);
/*    */   }
/*    */   
/*    */   public int lockFeatures(Filter filter) throws IOException {
/* 45 */     return delegate().lockFeatures(filter);
/*    */   }
/*    */   
/*    */   public int lockFeatures() throws IOException {
/* 49 */     return delegate().lockFeatures();
/*    */   }
/*    */   
/*    */   public void setFeatureLock(FeatureLock lock) {
/* 53 */     delegate().setFeatureLock(lock);
/*    */   }
/*    */   
/*    */   public void unLockFeatures() throws IOException {
/* 57 */     delegate().unLockFeatures();
/*    */   }
/*    */   
/*    */   public void unLockFeatures(Filter filter) throws IOException {
/* 62 */     delegate().unLockFeatures(filter);
/*    */   }
/*    */   
/*    */   public void unLockFeatures(Query query) throws IOException {
/* 66 */     delegate().unLockFeatures(query);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\SimpleFeatureLockingBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */