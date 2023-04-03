/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.identification.Usage;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class UsageImpl extends MetadataEntity implements Usage {
/*     */   private static final long serialVersionUID = 4059324536168287490L;
/*     */   
/*     */   private InternationalString specificUsage;
/*     */   
/*  58 */   private long usageDate = Long.MIN_VALUE;
/*     */   
/*     */   private InternationalString userDeterminedLimitations;
/*     */   
/*     */   private Collection<ResponsibleParty> userContactInfo;
/*     */   
/*     */   public UsageImpl() {}
/*     */   
/*     */   public UsageImpl(Usage source) {
/*  84 */     super(source);
/*     */   }
/*     */   
/*     */   public UsageImpl(InternationalString specificUsage, Collection<ResponsibleParty> userContactInfo) {
/*  93 */     setUserContactInfo(userContactInfo);
/*  94 */     setSpecificUsage(specificUsage);
/*     */   }
/*     */   
/*     */   public InternationalString getSpecificUsage() {
/* 101 */     return this.specificUsage;
/*     */   }
/*     */   
/*     */   public synchronized void setSpecificUsage(InternationalString newValue) {
/* 108 */     checkWritePermission();
/* 109 */     this.specificUsage = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Date getUsageDate() {
/* 116 */     return (this.usageDate != Long.MIN_VALUE) ? new Date(this.usageDate) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setUsageDate(Date newValue) {
/* 123 */     checkWritePermission();
/* 124 */     this.usageDate = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public InternationalString getUserDeterminedLimitations() {
/* 132 */     return this.userDeterminedLimitations;
/*     */   }
/*     */   
/*     */   public synchronized void setUserDeterminedLimitations(InternationalString newValue) {
/* 140 */     checkWritePermission();
/* 141 */     this.userDeterminedLimitations = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getUserContactInfo() {
/* 149 */     return this.userContactInfo = nonNullCollection(this.userContactInfo, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setUserContactInfo(Collection<? extends ResponsibleParty> newValues) {
/* 159 */     this.userContactInfo = copyCollection(newValues, this.userContactInfo, ResponsibleParty.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\UsageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */