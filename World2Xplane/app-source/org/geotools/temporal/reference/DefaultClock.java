/*     */ package org.geotools.temporal.reference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.temporal.Calendar;
/*     */ import org.opengis.temporal.Clock;
/*     */ import org.opengis.temporal.ClockTime;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultClock extends DefaultTemporalReferenceSystem implements Clock {
/*     */   private InternationalString referenceEvent;
/*     */   
/*     */   private ClockTime referenceTime;
/*     */   
/*     */   private ClockTime utcReference;
/*     */   
/*     */   private Collection<Calendar> dateBasis;
/*     */   
/*     */   public DefaultClock(ReferenceIdentifier name, Extent domainOfValidity, InternationalString referenceEvent, ClockTime referenceTime, ClockTime utcReference) {
/*  56 */     super(name, domainOfValidity);
/*  57 */     this.referenceEvent = referenceEvent;
/*  58 */     this.referenceTime = referenceTime;
/*  59 */     this.utcReference = utcReference;
/*     */   }
/*     */   
/*     */   public InternationalString getReferenceEvent() {
/*  63 */     return this.referenceEvent;
/*     */   }
/*     */   
/*     */   public ClockTime getReferenceTime() {
/*  67 */     return this.referenceTime;
/*     */   }
/*     */   
/*     */   public ClockTime getUTCReference() {
/*  71 */     return this.utcReference;
/*     */   }
/*     */   
/*     */   public ClockTime clkTrans(ClockTime uTime) {
/*  80 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public ClockTime utcTrans(ClockTime clkTime) {
/*  89 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public void setReferenceEvent(InternationalString referenceEvent) {
/*  93 */     this.referenceEvent = referenceEvent;
/*     */   }
/*     */   
/*     */   public void setReferenceTime(ClockTime referenceTime) {
/*  97 */     this.referenceTime = referenceTime;
/*     */   }
/*     */   
/*     */   public void setUtcReference(ClockTime utcReference) {
/* 101 */     this.utcReference = utcReference;
/*     */   }
/*     */   
/*     */   public Collection<Calendar> getDateBasis() {
/* 105 */     return this.dateBasis;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 110 */     if (object == this)
/* 111 */       return true; 
/* 113 */     if (super.equals(object))
/* 115 */       if (object instanceof DefaultClock) {
/* 116 */         DefaultClock that = (DefaultClock)object;
/* 118 */         return (Utilities.equals(this.dateBasis, that.dateBasis) && Utilities.equals(this.referenceEvent, that.referenceEvent) && Utilities.equals(this.referenceTime, that.referenceTime) && Utilities.equals(this.utcReference, that.utcReference));
/*     */       }  
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 129 */     int hash = 5;
/* 130 */     hash = 37 * hash + ((this.dateBasis != null) ? this.dateBasis.hashCode() : 0);
/* 131 */     hash = 37 * hash + ((this.referenceEvent != null) ? this.referenceEvent.hashCode() : 0);
/* 132 */     hash = 37 * hash + ((this.referenceTime != null) ? this.referenceTime.hashCode() : 0);
/* 133 */     hash = 37 * hash + ((this.utcReference != null) ? this.utcReference.hashCode() : 0);
/* 134 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 139 */     StringBuilder s = (new StringBuilder("Clock:")).append('\n');
/* 140 */     if (this.referenceEvent != null)
/* 141 */       s.append("referenceEvent:").append((CharSequence)this.referenceEvent).append('\n'); 
/* 143 */     if (this.referenceTime != null)
/* 144 */       s.append("referenceTime:").append(this.referenceTime).append('\n'); 
/* 146 */     if (this.utcReference != null)
/* 147 */       s.append("utcReference:").append(this.utcReference).append('\n'); 
/* 149 */     if (this.dateBasis != null)
/* 150 */       s.append("dateBasis:").append(this.dateBasis).append('\n'); 
/* 152 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultClock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */