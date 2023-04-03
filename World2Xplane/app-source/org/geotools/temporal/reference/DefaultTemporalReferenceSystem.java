/*     */ package org.geotools.temporal.reference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.temporal.TemporalReferenceSystem;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultTemporalReferenceSystem implements TemporalReferenceSystem {
/*     */   private ReferenceIdentifier name;
/*     */   
/*     */   private Extent domainOfValidity;
/*     */   
/*     */   private Extent validArea;
/*     */   
/*     */   private InternationalString scope;
/*     */   
/*     */   private Collection<GenericName> alias;
/*     */   
/*     */   private Set<ReferenceIdentifier> identifiers;
/*     */   
/*     */   private InternationalString remarks;
/*     */   
/*     */   public DefaultTemporalReferenceSystem(ReferenceIdentifier name, Extent domainOfValidity) {
/*  55 */     this.name = name;
/*  56 */     this.domainOfValidity = domainOfValidity;
/*     */   }
/*     */   
/*     */   public ReferenceIdentifier getName() {
/*  60 */     return this.name;
/*     */   }
/*     */   
/*     */   public Extent getDomainOfValidity() {
/*  64 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Extent getValidArea() {
/*  73 */     return this.validArea;
/*     */   }
/*     */   
/*     */   public InternationalString getScope() {
/*  77 */     return this.scope;
/*     */   }
/*     */   
/*     */   public Collection<GenericName> getAlias() {
/*  81 */     return this.alias;
/*     */   }
/*     */   
/*     */   public Set<ReferenceIdentifier> getIdentifiers() {
/*  85 */     return this.identifiers;
/*     */   }
/*     */   
/*     */   public InternationalString getRemarks() {
/*  89 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public String toWKT() throws UnsupportedOperationException {
/*  93 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public void setName(ReferenceIdentifier name) {
/* 100 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setDomainOfValidity(Extent domainOfValidity) {
/* 104 */     this.domainOfValidity = domainOfValidity;
/*     */   }
/*     */   
/*     */   public void setValidArea(Extent validArea) {
/* 108 */     this.validArea = validArea;
/*     */   }
/*     */   
/*     */   public void setScope(InternationalString scope) {
/* 112 */     this.scope = scope;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 117 */     if (object instanceof DefaultTemporalReferenceSystem) {
/* 118 */       DefaultTemporalReferenceSystem that = (DefaultTemporalReferenceSystem)object;
/* 120 */       return (Utilities.equals(this.alias, that.alias) && Utilities.equals(this.domainOfValidity, that.domainOfValidity) && Utilities.equals(this.identifiers, that.identifiers) && Utilities.equals(this.name, that.name) && Utilities.equals(this.scope, that.scope) && Utilities.equals(this.validArea, that.validArea) && Utilities.equals(this.remarks, that.remarks));
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 133 */     int hash = 5;
/* 134 */     hash = 37 * hash + ((this.alias != null) ? this.alias.hashCode() : 0);
/* 135 */     hash = 37 * hash + ((this.domainOfValidity != null) ? this.domainOfValidity.hashCode() : 0);
/* 136 */     hash = 37 * hash + ((this.identifiers != null) ? this.identifiers.hashCode() : 0);
/* 137 */     hash = 37 * hash + ((this.name != null) ? this.name.hashCode() : 0);
/* 138 */     hash = 37 * hash + ((this.remarks != null) ? this.remarks.hashCode() : 0);
/* 139 */     hash = 37 * hash + ((this.scope != null) ? this.scope.hashCode() : 0);
/* 140 */     hash = 37 * hash + ((this.validArea != null) ? this.validArea.hashCode() : 0);
/* 141 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     StringBuilder s = (new StringBuilder("TemporalReferenceSystem:")).append('\n');
/* 147 */     if (this.name != null)
/* 148 */       s.append("name:").append(this.name).append('\n'); 
/* 150 */     if (this.domainOfValidity != null)
/* 151 */       s.append("domainOfValidity:").append(this.domainOfValidity).append('\n'); 
/* 153 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultTemporalReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */