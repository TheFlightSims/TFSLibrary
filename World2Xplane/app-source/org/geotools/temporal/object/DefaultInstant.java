/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.Instant;
/*     */ import org.opengis.temporal.Period;
/*     */ import org.opengis.temporal.Position;
/*     */ 
/*     */ public class DefaultInstant extends DefaultTemporalGeometricPrimitive implements Instant {
/*     */   private Collection<Period> begunBy;
/*     */   
/*     */   private Collection<Period> endBy;
/*     */   
/*     */   private Position position;
/*     */   
/*     */   public DefaultInstant(Position position) {
/*  54 */     this.position = position;
/*     */   }
/*     */   
/*     */   public Position getPosition() {
/*  61 */     return this.position;
/*     */   }
/*     */   
/*     */   public Collection<Period> getBegunBy() {
/*  70 */     return this.begunBy;
/*     */   }
/*     */   
/*     */   public Collection<Period> getEndedBy() {
/*  79 */     return this.endBy;
/*     */   }
/*     */   
/*     */   public void setPosition(Position position) {
/*  83 */     this.position = position;
/*     */   }
/*     */   
/*     */   public void setBegunBy(Collection<Period> begunBy) {
/*  87 */     this.begunBy = begunBy;
/*     */   }
/*     */   
/*     */   public void setEndBy(Collection<Period> endBy) {
/*  91 */     this.endBy = endBy;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  99 */     if (object == this)
/* 100 */       return true; 
/* 102 */     if (object instanceof DefaultInstant) {
/* 103 */       DefaultInstant that = (DefaultInstant)object;
/* 105 */       return (Utilities.equals(this.position, that.position) && Utilities.equals(this.begunBy, that.begunBy) && Utilities.equals(this.endBy, that.endBy));
/*     */     } 
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 134 */     int hash = 5;
/* 135 */     hash = 37 * hash + ((this.position != null) ? this.position.hashCode() : 0);
/* 136 */     hash = 37 * hash + ((this.begunBy != null) ? this.begunBy.hashCode() : 0);
/* 137 */     hash = 37 * hash + ((this.endBy != null) ? this.endBy.hashCode() : 0);
/* 138 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 143 */     StringBuilder s = (new StringBuilder("Instant:")).append('\n');
/* 144 */     if (this.position != null)
/* 145 */       s.append("position:").append(this.position).append('\n'); 
/* 147 */     if (this.begunBy != null)
/* 148 */       s.append("begunBy:").append(this.begunBy).append('\n'); 
/* 150 */     if (this.endBy != null)
/* 151 */       s.append("endBy:").append(this.endBy).append('\n'); 
/* 153 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultInstant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */