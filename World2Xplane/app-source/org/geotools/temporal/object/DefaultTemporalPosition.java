/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.temporal.reference.DefaultTemporalReferenceSystem;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.temporal.IndeterminateValue;
/*     */ import org.opengis.temporal.TemporalPosition;
/*     */ import org.opengis.temporal.TemporalReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultTemporalPosition implements TemporalPosition {
/*     */   private TemporalReferenceSystem frame;
/*     */   
/*     */   private IndeterminateValue indeterminatePosition;
/*     */   
/*     */   public DefaultTemporalPosition(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition) {
/*  55 */     this.frame = frame;
/*  56 */     this.indeterminatePosition = indeterminatePosition;
/*     */   }
/*     */   
/*     */   public IndeterminateValue getIndeterminatePosition() {
/*  65 */     return this.indeterminatePosition;
/*     */   }
/*     */   
/*     */   public TemporalReferenceSystem getFrame() {
/*  74 */     if (this.frame == null)
/*  75 */       this.frame = (TemporalReferenceSystem)new DefaultTemporalReferenceSystem((ReferenceIdentifier)new NamedIdentifier(Citations.CRS, (InternationalString)new SimpleInternationalString("Gregorian calendar")), null); 
/*  78 */     return this.frame;
/*     */   }
/*     */   
/*     */   public void setFrame(TemporalReferenceSystem frame) {
/*  82 */     this.frame = frame;
/*     */   }
/*     */   
/*     */   public void setIndeterminatePosition(IndeterminateValue indeterminatePosition) {
/*  86 */     this.indeterminatePosition = indeterminatePosition;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  91 */     if (object == this)
/*  92 */       return true; 
/*  94 */     if (object instanceof DefaultTemporalPosition) {
/*  95 */       DefaultTemporalPosition that = (DefaultTemporalPosition)object;
/*  97 */       return (Utilities.equals(this.frame, that.frame) && Utilities.equals(this.indeterminatePosition, that.indeterminatePosition));
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     int hash = 5;
/* 106 */     hash = 37 * hash + ((this.frame != null) ? this.frame.hashCode() : 0);
/* 107 */     hash = 37 * hash + ((this.indeterminatePosition != null) ? this.indeterminatePosition.hashCode() : 0);
/* 108 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 113 */     StringBuilder s = (new StringBuilder("TemporalPosition:")).append('\n');
/* 114 */     if (this.frame != null)
/* 115 */       s.append("frame:").append(this.frame).append('\n'); 
/* 117 */     if (this.indeterminatePosition != null)
/* 118 */       s.append("indeterminatePosition:").append(this.indeterminatePosition).append('\n'); 
/* 120 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultTemporalPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */