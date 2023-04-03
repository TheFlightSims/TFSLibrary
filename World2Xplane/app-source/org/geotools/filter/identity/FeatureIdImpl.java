/*     */ package org.geotools.filter.identity;
/*     */ 
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ public class FeatureIdImpl implements FeatureId {
/*     */   protected String fid;
/*     */   
/*     */   protected String origionalFid;
/*     */   
/*     */   public FeatureIdImpl(String fid) {
/*  42 */     this.fid = fid;
/*  43 */     if (fid == null)
/*  44 */       throw new NullPointerException("fid must not be null"); 
/*     */   }
/*     */   
/*     */   public String getID() {
/*  49 */     return this.fid;
/*     */   }
/*     */   
/*     */   public void setID(String id) {
/*  53 */     if (id == null)
/*  54 */       throw new NullPointerException("fid must not be null"); 
/*  56 */     if (this.origionalFid == null)
/*  57 */       this.origionalFid = this.fid; 
/*  59 */     this.fid = id;
/*     */   }
/*     */   
/*     */   public boolean matches(Feature feature) {
/*  63 */     if (feature == null)
/*  64 */       return false; 
/*  66 */     return equalsExact(feature.getIdentifier());
/*     */   }
/*     */   
/*     */   public boolean matches(Object object) {
/*  71 */     if (object instanceof Feature)
/*  72 */       return matches((Feature)object); 
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  78 */     return this.fid;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  82 */     if (obj instanceof FeatureId)
/*  83 */       return this.fid.equals(((FeatureId)obj).getID()); 
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  89 */     return this.fid.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equalsExact(FeatureId id) {
/*  94 */     if (id instanceof FeatureId)
/*  95 */       return (this.fid.equals(id.getID()) && this.fid.equals(id.getRid()) && id.getPreviousRid() == null && id.getFeatureVersion() == null); 
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equalsFID(FeatureId id) {
/* 105 */     if (id == null)
/* 105 */       return false; 
/* 107 */     return getID().equals(id.getID());
/*     */   }
/*     */   
/*     */   public String getRid() {
/* 112 */     return getID();
/*     */   }
/*     */   
/*     */   public String getPreviousRid() {
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public String getFeatureVersion() {
/* 122 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\identity\FeatureIdImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */