/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import org.opengis.filter.capability.IdCapabilities;
/*     */ 
/*     */ public class IdCapabilitiesImpl implements IdCapabilities {
/*     */   boolean eid;
/*     */   
/*     */   boolean fid;
/*     */   
/*     */   public IdCapabilitiesImpl() {
/*  37 */     this(false, false);
/*     */   }
/*     */   
/*     */   public IdCapabilitiesImpl(boolean eid, boolean fid) {
/*  40 */     this.eid = eid;
/*  41 */     this.fid = fid;
/*     */   }
/*     */   
/*     */   public IdCapabilitiesImpl(IdCapabilities copy) {
/*  44 */     this(copy.hasEID(), copy.hasFID());
/*     */   }
/*     */   
/*     */   public boolean hasEID() {
/*  48 */     return this.eid;
/*     */   }
/*     */   
/*     */   public void setEid(boolean eid) {
/*  51 */     this.eid = eid;
/*     */   }
/*     */   
/*     */   public boolean hasFID() {
/*  54 */     return this.fid;
/*     */   }
/*     */   
/*     */   public void setFID(boolean fid) {
/*  57 */     this.fid = fid;
/*     */   }
/*     */   
/*     */   public void addAll(IdCapabilities copy) {
/*  60 */     if (copy == null)
/*     */       return; 
/*  61 */     if (copy.hasEID())
/*  62 */       this.eid = true; 
/*  64 */     if (copy.hasFID())
/*  65 */       this.fid = true; 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  70 */     StringBuffer buf = new StringBuffer();
/*  71 */     buf.append("IdCapabilitiesImpl[");
/*  72 */     if (this.fid)
/*  73 */       buf.append(" FeatureId"); 
/*  75 */     if (this.eid)
/*  76 */       buf.append(" GMLObjectId"); 
/*  78 */     buf.append(" ]");
/*  79 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  83 */     int prime = 31;
/*  84 */     int result = 1;
/*  85 */     result = 31 * result + (this.eid ? 1231 : 1237);
/*  86 */     result = 31 * result + (this.fid ? 1231 : 1237);
/*  87 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  91 */     if (this == obj)
/*  92 */       return true; 
/*  93 */     if (obj == null)
/*  94 */       return false; 
/*  95 */     if (getClass() != obj.getClass())
/*  96 */       return false; 
/*  97 */     IdCapabilitiesImpl other = (IdCapabilitiesImpl)obj;
/*  98 */     if (this.eid != other.eid)
/*  99 */       return false; 
/* 100 */     if (this.fid != other.fid)
/* 101 */       return false; 
/* 102 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\IdCapabilitiesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */