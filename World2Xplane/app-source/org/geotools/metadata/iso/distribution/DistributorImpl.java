/*     */ package org.geotools.metadata.iso.distribution;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.distribution.DigitalTransferOptions;
/*     */ import org.opengis.metadata.distribution.Distributor;
/*     */ import org.opengis.metadata.distribution.Format;
/*     */ import org.opengis.metadata.distribution.StandardOrderProcess;
/*     */ 
/*     */ public class DistributorImpl extends MetadataEntity implements Distributor {
/*     */   private static final long serialVersionUID = 7142984376823483766L;
/*     */   
/*     */   private ResponsibleParty distributorContact;
/*     */   
/*     */   private Collection<StandardOrderProcess> distributionOrderProcesses;
/*     */   
/*     */   private Collection<Format> distributorFormats;
/*     */   
/*     */   private Collection<DigitalTransferOptions> distributorTransferOptions;
/*     */   
/*     */   public DistributorImpl() {}
/*     */   
/*     */   public DistributorImpl(Distributor source) {
/*  82 */     super(source);
/*     */   }
/*     */   
/*     */   public DistributorImpl(ResponsibleParty distributorContact) {
/*  89 */     setDistributorContact(distributorContact);
/*     */   }
/*     */   
/*     */   public ResponsibleParty getDistributorContact() {
/*  96 */     return this.distributorContact;
/*     */   }
/*     */   
/*     */   public synchronized void setDistributorContact(ResponsibleParty newValue) {
/* 103 */     checkWritePermission();
/* 104 */     this.distributorContact = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<StandardOrderProcess> getDistributionOrderProcesses() {
/* 112 */     return this.distributionOrderProcesses = nonNullCollection(this.distributionOrderProcesses, StandardOrderProcess.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDistributionOrderProcesses(Collection<? extends StandardOrderProcess> newValues) {
/* 123 */     this.distributionOrderProcesses = copyCollection(newValues, this.distributionOrderProcesses, StandardOrderProcess.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Format> getDistributorFormats() {
/* 131 */     return this.distributorFormats = nonNullCollection(this.distributorFormats, Format.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDistributorFormats(Collection<? extends Format> newValues) {
/* 138 */     this.distributorFormats = copyCollection(newValues, this.distributorFormats, Format.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<DigitalTransferOptions> getDistributorTransferOptions() {
/* 145 */     return this.distributorTransferOptions = nonNullCollection(this.distributorTransferOptions, DigitalTransferOptions.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDistributorTransferOptions(Collection<? extends DigitalTransferOptions> newValues) {
/* 155 */     this.distributorTransferOptions = copyCollection(newValues, this.distributorTransferOptions, DigitalTransferOptions.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\distribution\DistributorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */