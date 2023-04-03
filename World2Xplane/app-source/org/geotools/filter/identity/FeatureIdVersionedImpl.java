/*    */ package org.geotools.filter.identity;
/*    */ 
/*    */ import org.opengis.filter.identity.FeatureId;
/*    */ 
/*    */ public class FeatureIdVersionedImpl extends FeatureIdImpl {
/*    */   protected String featureVersion;
/*    */   
/*    */   protected String previousRid;
/*    */   
/*    */   public FeatureIdVersionedImpl(String fid, String version) {
/* 40 */     this(fid, version, (String)null);
/*    */   }
/*    */   
/*    */   public FeatureIdVersionedImpl(String fid, String version, String previousRid) {
/* 43 */     super(fid);
/* 44 */     this.featureVersion = version;
/* 45 */     this.previousRid = previousRid;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return this.fid;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 54 */     if (obj instanceof FeatureId)
/* 55 */       return this.fid.equals(((FeatureId)obj).getID()); 
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 61 */     return this.fid.hashCode();
/*    */   }
/*    */   
/*    */   public boolean equalsExact(FeatureId id) {
/* 66 */     if (id instanceof FeatureId)
/* 67 */       return (this.fid.equals(id.getID()) && this.fid.equals(id.getRid()) && id.getPreviousRid() == null && id.getFeatureVersion() == null); 
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public boolean equalsFID(FeatureId id) {
/* 77 */     if (id == null)
/* 77 */       return false; 
/* 79 */     return getID().equals(id.getID());
/*    */   }
/*    */   
/*    */   public String getRid() {
/* 84 */     return (this.featureVersion == null) ? getID() : (getID() + '@' + this.featureVersion);
/*    */   }
/*    */   
/*    */   public String getPreviousRid() {
/* 90 */     return this.previousRid;
/*    */   }
/*    */   
/*    */   public String getFeatureVersion() {
/* 95 */     return this.featureVersion;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\identity\FeatureIdVersionedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */