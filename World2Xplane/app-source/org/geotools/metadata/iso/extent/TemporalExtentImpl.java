/*     */ package org.geotools.metadata.iso.extent;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.extent.TemporalExtent;
/*     */ import org.opengis.temporal.TemporalPrimitive;
/*     */ 
/*     */ public class TemporalExtentImpl extends MetadataEntity implements TemporalExtent {
/*     */   private static final long serialVersionUID = 3668140516657118045L;
/*     */   
/*  53 */   private long startTime = Long.MIN_VALUE;
/*     */   
/*  60 */   private long endTime = Long.MIN_VALUE;
/*     */   
/*     */   private TemporalPrimitive extent;
/*     */   
/*     */   public TemporalExtentImpl() {}
/*     */   
/*     */   public TemporalExtentImpl(TemporalExtent source) {
/*  79 */     super(source);
/*     */   }
/*     */   
/*     */   public TemporalExtentImpl(Date startTime, Date endTime) {
/*  86 */     setStartTime(startTime);
/*  87 */     setEndTime(endTime);
/*     */   }
/*     */   
/*     */   public synchronized Date getStartTime() {
/*  94 */     return (this.startTime != Long.MIN_VALUE) ? new Date(this.startTime) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setStartTime(Date newValue) {
/* 101 */     checkWritePermission();
/* 102 */     this.startTime = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public synchronized Date getEndTime() {
/* 109 */     return (this.endTime != Long.MIN_VALUE) ? new Date(this.endTime) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setEndTime(Date newValue) {
/* 116 */     checkWritePermission();
/* 117 */     this.endTime = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public TemporalPrimitive getExtent() {
/* 127 */     return this.extent;
/*     */   }
/*     */   
/*     */   public synchronized void setExtent(TemporalPrimitive newValue) {
/* 136 */     checkWritePermission();
/* 137 */     this.extent = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\TemporalExtentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */