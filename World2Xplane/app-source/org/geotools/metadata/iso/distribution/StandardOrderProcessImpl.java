/*     */ package org.geotools.metadata.iso.distribution;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.distribution.StandardOrderProcess;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class StandardOrderProcessImpl extends MetadataEntity implements StandardOrderProcess {
/*     */   private static final long serialVersionUID = -6503378937452728631L;
/*     */   
/*     */   private InternationalString fees;
/*     */   
/*  57 */   private long plannedAvailableDateTime = Long.MIN_VALUE;
/*     */   
/*     */   private InternationalString orderingInstructions;
/*     */   
/*     */   private InternationalString turnaround;
/*     */   
/*     */   public StandardOrderProcessImpl() {}
/*     */   
/*     */   public StandardOrderProcessImpl(StandardOrderProcess source) {
/*  81 */     super(source);
/*     */   }
/*     */   
/*     */   public InternationalString getFees() {
/*  89 */     return this.fees;
/*     */   }
/*     */   
/*     */   public synchronized void setFees(InternationalString newValue) {
/*  97 */     checkWritePermission();
/*  98 */     this.fees = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Date getPlannedAvailableDateTime() {
/* 105 */     return (this.plannedAvailableDateTime != Long.MIN_VALUE) ? new Date(this.plannedAvailableDateTime) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setPlannedAvailableDateTime(Date newValue) {
/* 113 */     checkWritePermission();
/* 114 */     this.plannedAvailableDateTime = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public InternationalString getOrderingInstructions() {
/* 121 */     return this.orderingInstructions;
/*     */   }
/*     */   
/*     */   public synchronized void setOrderingInstructions(InternationalString newValue) {
/* 128 */     checkWritePermission();
/* 129 */     this.orderingInstructions = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getTurnaround() {
/* 136 */     return this.turnaround;
/*     */   }
/*     */   
/*     */   public synchronized void setTurnaround(InternationalString newValue) {
/* 143 */     checkWritePermission();
/* 144 */     this.turnaround = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\distribution\StandardOrderProcessImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */