/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public interface FeatureLocking<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> extends FeatureStore<T, F> {
/*     */   void setFeatureLock(FeatureLock paramFeatureLock);
/*     */   
/*     */   int lockFeatures(Query paramQuery) throws IOException;
/*     */   
/*     */   int lockFeatures(Filter paramFilter) throws IOException;
/*     */   
/*     */   int lockFeatures() throws IOException;
/*     */   
/*     */   void unLockFeatures() throws IOException;
/*     */   
/*     */   void unLockFeatures(Filter paramFilter) throws IOException;
/*     */   
/*     */   void unLockFeatures(Query paramQuery) throws IOException;
/*     */   
/*     */   public static class Response {
/*     */     String authID;
/*     */     
/*     */     Set<String> locked;
/*     */     
/*     */     Set<String> notLocked;
/*     */     
/*     */     public Response(FeatureLock lock, Set<String> lockedFids, Set<String> notLockedFids) {
/* 200 */       this.authID = lock.getAuthorization();
/* 201 */       this.locked = lockedFids;
/* 202 */       this.notLocked = notLockedFids;
/*     */     }
/*     */     
/*     */     public String getAuthorizationID() {
/* 206 */       return this.authID;
/*     */     }
/*     */     
/*     */     public Set<String> getLockedFids() {
/* 210 */       return this.locked;
/*     */     }
/*     */     
/*     */     public Set<String> getNotLockedFids() {
/* 214 */       return this.notLocked;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureLocking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */