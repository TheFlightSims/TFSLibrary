/*     */ package org.geotools.metadata.iso.distribution;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.distribution.DigitalTransferOptions;
/*     */ import org.opengis.metadata.distribution.Distribution;
/*     */ import org.opengis.metadata.distribution.Distributor;
/*     */ import org.opengis.metadata.distribution.Format;
/*     */ 
/*     */ public class DistributionImpl extends MetadataEntity implements Distribution {
/*     */   private static final long serialVersionUID = -5899590027802365131L;
/*     */   
/*     */   private Collection<Format> distributionFormats;
/*     */   
/*     */   private Collection<Distributor> distributors;
/*     */   
/*     */   private Collection<DigitalTransferOptions> transferOptions;
/*     */   
/*     */   public DistributionImpl() {}
/*     */   
/*     */   public DistributionImpl(Distribution source) {
/*  76 */     super(source);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Format> getDistributionFormats() {
/*  83 */     return this.distributionFormats = nonNullCollection(this.distributionFormats, Format.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDistributionFormats(Collection<? extends Format> newValues) {
/*  90 */     this.distributionFormats = copyCollection(newValues, this.distributionFormats, Format.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Distributor> getDistributors() {
/*  97 */     return this.distributors = nonNullCollection(this.distributors, Distributor.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDistributors(Collection<? extends Distributor> newValues) {
/* 104 */     this.distributors = copyCollection(newValues, this.distributors, Distributor.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<DigitalTransferOptions> getTransferOptions() {
/* 112 */     return this.transferOptions = nonNullCollection(this.transferOptions, DigitalTransferOptions.class);
/*     */   }
/*     */   
/*     */   public synchronized void setTransferOptions(Collection<? extends DigitalTransferOptions> newValues) {
/* 122 */     this.transferOptions = copyCollection(newValues, this.transferOptions, DigitalTransferOptions.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\distribution\DistributionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */