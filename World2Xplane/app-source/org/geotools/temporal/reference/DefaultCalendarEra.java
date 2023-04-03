/*     */ package org.geotools.temporal.reference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.Calendar;
/*     */ import org.opengis.temporal.CalendarDate;
/*     */ import org.opengis.temporal.CalendarEra;
/*     */ import org.opengis.temporal.JulianDate;
/*     */ import org.opengis.temporal.Period;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultCalendarEra implements CalendarEra {
/*     */   private InternationalString name;
/*     */   
/*     */   private InternationalString referenceEvent;
/*     */   
/*     */   private CalendarDate referenceDate;
/*     */   
/*     */   private JulianDate julianReference;
/*     */   
/*     */   private Period epochOfUse;
/*     */   
/*     */   private Collection<Calendar> datingSystem;
/*     */   
/*     */   public DefaultCalendarEra(InternationalString name, InternationalString referenceEvent, CalendarDate referenceDate, JulianDate julianReference, Period epochOfUse) {
/*  65 */     this.name = name;
/*  66 */     this.referenceDate = referenceDate;
/*  67 */     this.referenceEvent = referenceEvent;
/*  68 */     this.julianReference = julianReference;
/*  69 */     this.epochOfUse = epochOfUse;
/*     */   }
/*     */   
/*     */   public InternationalString getName() {
/*  73 */     return this.name;
/*     */   }
/*     */   
/*     */   public InternationalString getReferenceEvent() {
/*  77 */     return this.referenceEvent;
/*     */   }
/*     */   
/*     */   public CalendarDate getReferenceDate() {
/*  81 */     return this.referenceDate;
/*     */   }
/*     */   
/*     */   public JulianDate getJulianReference() {
/*  85 */     return this.julianReference;
/*     */   }
/*     */   
/*     */   public Period getEpochOfUse() {
/*  89 */     return this.epochOfUse;
/*     */   }
/*     */   
/*     */   public void setName(InternationalString name) {
/*  93 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setReferenceEvent(InternationalString referenceEvent) {
/*  97 */     this.referenceEvent = referenceEvent;
/*     */   }
/*     */   
/*     */   public void setReferenceDate(CalendarDate referenceDate) {
/* 101 */     this.referenceDate = referenceDate;
/*     */   }
/*     */   
/*     */   public void setJulianReference(JulianDate julianReference) {
/* 105 */     this.julianReference = julianReference;
/*     */   }
/*     */   
/*     */   public void setEpochOfUse(Period epochOfUse) {
/* 109 */     this.epochOfUse = epochOfUse;
/*     */   }
/*     */   
/*     */   public Collection<Calendar> getDatingSystem() {
/* 113 */     return this.datingSystem;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 118 */     if (object instanceof CalendarEra) {
/* 119 */       DefaultCalendarEra that = (DefaultCalendarEra)object;
/* 121 */       return (Utilities.equals(this.datingSystem, that.datingSystem) && Utilities.equals(this.epochOfUse, that.epochOfUse) && Utilities.equals(this.julianReference, that.julianReference) && Utilities.equals(this.name, that.name) && Utilities.equals(this.referenceDate, that.referenceDate) && Utilities.equals(this.referenceEvent, that.referenceEvent));
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 133 */     int hash = 5;
/* 134 */     hash = 37 * hash + ((this.datingSystem != null) ? this.datingSystem.hashCode() : 0);
/* 135 */     hash = 37 * hash + ((this.epochOfUse != null) ? this.epochOfUse.hashCode() : 0);
/* 136 */     hash = 37 * hash + ((this.julianReference != null) ? this.julianReference.hashCode() : 0);
/* 137 */     hash = 37 * hash + ((this.name != null) ? this.name.hashCode() : 0);
/* 138 */     hash = 37 * hash + ((this.referenceDate != null) ? this.referenceDate.hashCode() : 0);
/* 139 */     hash = 37 * hash + ((this.referenceEvent != null) ? this.referenceEvent.hashCode() : 0);
/* 140 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 145 */     StringBuilder s = (new StringBuilder("CalendarEra:")).append('\n');
/* 146 */     if (this.name != null)
/* 147 */       s.append("name:").append((CharSequence)this.name).append('\n'); 
/* 149 */     if (this.epochOfUse != null)
/* 150 */       s.append("epochOfUse:").append(this.epochOfUse).append('\n'); 
/* 152 */     if (this.referenceEvent != null)
/* 153 */       s.append("referenceEvent:").append((CharSequence)this.referenceEvent).append('\n'); 
/* 155 */     if (this.referenceDate != null)
/* 156 */       s.append("referenceDate:").append(this.referenceDate).append('\n'); 
/* 158 */     if (this.julianReference != null)
/* 159 */       s.append("julianReference:").append(this.julianReference).append('\n'); 
/* 161 */     if (this.datingSystem != null)
/* 162 */       s.append("datingSystem:").append(this.datingSystem).append('\n'); 
/* 164 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultCalendarEra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */