/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.Instant;
/*     */ import org.opengis.temporal.Period;
/*     */ import org.opengis.temporal.RelativePosition;
/*     */ import org.opengis.temporal.TemporalPrimitive;
/*     */ 
/*     */ public class DefaultPeriod extends DefaultTemporalGeometricPrimitive implements Period {
/*     */   private Instant begining;
/*     */   
/*     */   private Instant ending;
/*     */   
/*     */   public DefaultPeriod(Instant begining, Instant ending) {
/*  46 */     if (begining.relativePosition((TemporalPrimitive)ending).equals(RelativePosition.BEFORE)) {
/*  47 */       this.begining = begining;
/*  48 */       this.ending = ending;
/*     */     } else {
/*  54 */       throw new IllegalArgumentException("The temporal position of the beginning of the period must be less than (i.e. earlier than) the temporal position of the end of the period");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Instant getBeginning() {
/*  62 */     return this.begining;
/*     */   }
/*     */   
/*     */   public void setBegining(Instant begining) {
/*  66 */     this.begining = begining;
/*     */   }
/*     */   
/*     */   public void setBegining(Date date) {
/*  70 */     this.begining = new DefaultInstant(new DefaultPosition(date));
/*     */   }
/*     */   
/*     */   public Instant getEnding() {
/*  77 */     return this.ending;
/*     */   }
/*     */   
/*     */   public void setEnding(Instant ending) {
/*  81 */     this.ending = ending;
/*     */   }
/*     */   
/*     */   public void setEnding(Date date) {
/*  85 */     this.ending = new DefaultInstant(new DefaultPosition(date));
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  93 */     if (object == this)
/*  94 */       return true; 
/*  96 */     if (object instanceof DefaultPeriod) {
/*  97 */       DefaultPeriod that = (DefaultPeriod)object;
/*  99 */       return (Utilities.equals(this.begining, that.begining) && Utilities.equals(this.ending, that.ending));
/*     */     } 
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 128 */     int hash = 5;
/* 129 */     hash = 37 * hash + ((this.begining != null) ? this.begining.hashCode() : 0);
/* 130 */     hash = 37 * hash + ((this.ending != null) ? this.ending.hashCode() : 0);
/* 131 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 136 */     StringBuilder s = (new StringBuilder("Period:")).append('\n');
/* 137 */     if (this.begining != null)
/* 138 */       s.append("begin:").append(this.begining).append('\n'); 
/* 140 */     if (this.ending != null)
/* 141 */       s.append("end:").append(this.ending).append('\n'); 
/* 144 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultPeriod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */