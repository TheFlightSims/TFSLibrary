/*     */ package org.geotools.metadata.iso.maintenance;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.maintenance.MaintenanceFrequency;
/*     */ import org.opengis.metadata.maintenance.MaintenanceInformation;
/*     */ import org.opengis.metadata.maintenance.ScopeCode;
/*     */ import org.opengis.metadata.maintenance.ScopeDescription;
/*     */ import org.opengis.temporal.PeriodDuration;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class MaintenanceInformationImpl extends MetadataEntity implements MaintenanceInformation {
/*     */   private static final long serialVersionUID = 8523463344581266776L;
/*     */   
/*     */   private MaintenanceFrequency maintenanceAndUpdateFrequency;
/*     */   
/*  65 */   private long dateOfNextUpdate = Long.MIN_VALUE;
/*     */   
/*     */   private PeriodDuration userDefinedMaintenanceFrequency;
/*     */   
/*     */   private Collection<ScopeCode> updateScopes;
/*     */   
/*     */   private Collection<ScopeDescription> updateScopeDescriptions;
/*     */   
/*     */   private Collection<InternationalString> maintenanceNotes;
/*     */   
/*     */   private Collection<ResponsibleParty> contacts;
/*     */   
/*     */   public MaintenanceInformationImpl() {}
/*     */   
/*     */   public MaintenanceInformationImpl(MaintenanceInformation source) {
/* 105 */     super(source);
/*     */   }
/*     */   
/*     */   public MaintenanceInformationImpl(MaintenanceFrequency maintenanceAndUpdateFrequency) {
/* 112 */     setMaintenanceAndUpdateFrequency(maintenanceAndUpdateFrequency);
/*     */   }
/*     */   
/*     */   public MaintenanceFrequency getMaintenanceAndUpdateFrequency() {
/* 120 */     return this.maintenanceAndUpdateFrequency;
/*     */   }
/*     */   
/*     */   public synchronized void setMaintenanceAndUpdateFrequency(MaintenanceFrequency newValue) {
/* 128 */     checkWritePermission();
/* 129 */     this.maintenanceAndUpdateFrequency = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Date getDateOfNextUpdate() {
/* 136 */     return (this.dateOfNextUpdate != Long.MIN_VALUE) ? new Date(this.dateOfNextUpdate) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setDateOfNextUpdate(Date newValue) {
/* 143 */     checkWritePermission();
/* 144 */     this.dateOfNextUpdate = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public PeriodDuration getUserDefinedMaintenanceFrequency() {
/* 156 */     return this.userDefinedMaintenanceFrequency;
/*     */   }
/*     */   
/*     */   public synchronized void setUserDefinedMaintenanceFrequency(PeriodDuration newValue) {
/* 163 */     checkWritePermission();
/* 164 */     this.userDefinedMaintenanceFrequency = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ScopeCode> getUpdateScopes() {
/* 173 */     return this.updateScopes = nonNullCollection(this.updateScopes, ScopeCode.class);
/*     */   }
/*     */   
/*     */   public synchronized void setUpdateScopes(Collection<? extends ScopeCode> newValues) {
/* 182 */     this.updateScopes = copyCollection(newValues, this.updateScopes, ScopeCode.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ScopeDescription> getUpdateScopeDescriptions() {
/* 191 */     return this.updateScopeDescriptions = nonNullCollection(this.updateScopeDescriptions, ScopeDescription.class);
/*     */   }
/*     */   
/*     */   public synchronized void setUpdateScopeDescriptions(Collection<? extends ScopeDescription> newValues) {
/* 202 */     this.updateScopeDescriptions = copyCollection(newValues, this.updateScopeDescriptions, ScopeDescription.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getMaintenanceNotes() {
/* 211 */     return this.maintenanceNotes = nonNullCollection(this.maintenanceNotes, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setMaintenanceNotes(Collection<? extends InternationalString> newValues) {
/* 222 */     this.maintenanceNotes = copyCollection(newValues, this.maintenanceNotes, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getContacts() {
/* 232 */     return this.contacts = nonNullCollection(this.contacts, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setContacts(Collection<? extends ResponsibleParty> newValues) {
/* 242 */     this.contacts = copyCollection(newValues, this.contacts, ResponsibleParty.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\maintenance\MaintenanceInformationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */