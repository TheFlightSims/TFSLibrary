/*     */ package org.geotools.filter.identity;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.identity.ResourceId;
/*     */ import org.opengis.filter.identity.Version;
/*     */ 
/*     */ public class ResourceIdImpl extends FeatureIdVersionedImpl implements ResourceId {
/*     */   private Date startTime;
/*     */   
/*     */   private Date endTime;
/*     */   
/*     */   private long version;
/*     */   
/*     */   public ResourceIdImpl(String fid, String featureVersion, Version version) {
/*  51 */     super(fid, featureVersion, (String)null);
/*  52 */     setVersion(version);
/*     */   }
/*     */   
/*     */   public ResourceIdImpl(String fid, String featureVersion) {
/*  63 */     this(fid, featureVersion, (Version)null);
/*     */   }
/*     */   
/*     */   public ResourceIdImpl(String fid, Date start, Date end) {
/*  80 */     this(fid, (String)null, (Version)null);
/*  81 */     if (start == null && end == null)
/*  82 */       throw new NullPointerException("At least one of start and end time are required for a lookup based on a date range"); 
/*  84 */     this.startTime = start;
/*  85 */     this.endTime = end;
/*     */   }
/*     */   
/*     */   public void setRid(String rid) {
/*  89 */     setID(rid);
/*     */   }
/*     */   
/*     */   public void setPreviousRid(String previousRid) {
/*  93 */     this.previousRid = previousRid;
/*     */   }
/*     */   
/*     */   public void setVersion(Version version) {
/*  97 */     if (version == null) {
/*  98 */       this.version = (new Version()).union();
/*     */     } else {
/* 100 */       this.version = version.union();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Date getStartTime() {
/* 106 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public void setStartTime(Date startTime) {
/* 110 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */   public Date getEndTime() {
/* 115 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(Date endTime) {
/* 119 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public Version getVersion() {
/* 124 */     return Version.valueOf(this.version);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 129 */     if (!(obj instanceof ResourceId))
/* 130 */       return false; 
/* 132 */     if (!super.equals(obj))
/* 133 */       return false; 
/* 135 */     ResourceId o = (ResourceId)obj;
/* 136 */     return (Utilities.equals(this.featureVersion, o.getFeatureVersion()) && Utilities.equals(this.previousRid, o.getPreviousRid()) && Utilities.equals(Long.valueOf(this.version), o.getVersion()) && Utilities.equals(this.startTime, o.getStartTime()) && Utilities.equals(this.endTime, o.getEndTime()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 145 */     int hash = super.hashCode();
/* 146 */     hash = Utilities.hash(this.previousRid, hash);
/* 147 */     hash = Utilities.hash(this.version, hash);
/* 148 */     hash = Utilities.hash(this.startTime, hash);
/* 149 */     hash = Utilities.hash(this.endTime, hash);
/* 150 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\identity\ResourceIdImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */