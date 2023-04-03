/*     */ package org.geotools.metadata.iso.distribution;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.metadata.distribution.DigitalTransferOptions;
/*     */ import org.opengis.metadata.distribution.Medium;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DigitalTransferOptionsImpl extends MetadataEntity implements DigitalTransferOptions {
/*     */   private static final long serialVersionUID = -1533064478468754337L;
/*     */   
/*     */   private InternationalString unitsOfDistribution;
/*     */   
/*     */   private Double transferSize;
/*     */   
/*     */   private Collection<OnLineResource> onLines;
/*     */   
/*     */   private Medium offLines;
/*     */   
/*     */   public DigitalTransferOptionsImpl() {}
/*     */   
/*     */   public DigitalTransferOptionsImpl(DigitalTransferOptions source) {
/*  82 */     super(source);
/*     */   }
/*     */   
/*     */   public InternationalString getUnitsOfDistribution() {
/*  89 */     return this.unitsOfDistribution;
/*     */   }
/*     */   
/*     */   public synchronized void setUnitsOfDistribution(InternationalString newValue) {
/*  96 */     checkWritePermission();
/*  97 */     this.unitsOfDistribution = newValue;
/*     */   }
/*     */   
/*     */   public Double getTransferSize() {
/* 106 */     return this.transferSize;
/*     */   }
/*     */   
/*     */   public synchronized void setTransferSize(Double newValue) {
/* 114 */     checkWritePermission();
/* 115 */     this.transferSize = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<OnLineResource> getOnLines() {
/* 122 */     return this.onLines = nonNullCollection(this.onLines, OnLineResource.class);
/*     */   }
/*     */   
/*     */   public synchronized void setOnLines(Collection<? extends OnLineResource> newValues) {
/* 129 */     this.onLines = copyCollection(newValues, this.onLines, OnLineResource.class);
/*     */   }
/*     */   
/*     */   public Medium getOffLine() {
/* 136 */     return this.offLines;
/*     */   }
/*     */   
/*     */   public synchronized void setOffLine(Medium newValue) {
/* 143 */     checkWritePermission();
/* 144 */     this.offLines = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\distribution\DigitalTransferOptionsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */