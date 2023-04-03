/*    */ package org.geotools.data.directory;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.geotools.data.FeatureLock;
/*    */ import org.geotools.data.Query;
/*    */ import org.geotools.data.simple.SimpleFeatureLocking;
/*    */ import org.geotools.data.simple.SimpleFeatureSource;
/*    */ import org.geotools.data.simple.SimpleFeatureStore;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public class DirectoryFeatureLocking extends DirectoryFeatureStore implements SimpleFeatureLocking {
/*    */   SimpleFeatureLocking flocking;
/*    */   
/*    */   public DirectoryFeatureLocking(SimpleFeatureLocking locking) {
/* 38 */     super((SimpleFeatureStore)locking);
/* 39 */     this.flocking = locking;
/*    */   }
/*    */   
/*    */   public int lockFeatures() throws IOException {
/* 43 */     return this.flocking.lockFeatures();
/*    */   }
/*    */   
/*    */   public int lockFeatures(Filter filter) throws IOException {
/* 47 */     return this.flocking.lockFeatures(filter);
/*    */   }
/*    */   
/*    */   public void setFeatureLock(FeatureLock lock) {
/* 51 */     this.flocking.setFeatureLock(lock);
/*    */   }
/*    */   
/*    */   public int lockFeatures(Query query) throws IOException {
/* 55 */     return this.flocking.lockFeatures(query);
/*    */   }
/*    */   
/*    */   public void unLockFeatures() throws IOException {
/* 59 */     this.flocking.unLockFeatures();
/*    */   }
/*    */   
/*    */   public void unLockFeatures(Filter filter) throws IOException {
/* 63 */     this.flocking.unLockFeatures(filter);
/*    */   }
/*    */   
/*    */   public void unLockFeatures(Query query) throws IOException {
/* 67 */     this.flocking.unLockFeatures(query);
/*    */   }
/*    */   
/*    */   public SimpleFeatureLocking unwrap() {
/* 72 */     return this.flocking;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DirectoryFeatureLocking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */