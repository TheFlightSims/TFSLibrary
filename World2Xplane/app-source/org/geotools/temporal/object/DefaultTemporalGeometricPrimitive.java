/*     */ package org.geotools.temporal.object;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.opengis.temporal.Duration;
/*     */ import org.opengis.temporal.Instant;
/*     */ import org.opengis.temporal.Period;
/*     */ import org.opengis.temporal.RelativePosition;
/*     */ import org.opengis.temporal.Separation;
/*     */ import org.opengis.temporal.TemporalGeometricPrimitive;
/*     */ import org.opengis.temporal.TemporalPrimitive;
/*     */ 
/*     */ public abstract class DefaultTemporalGeometricPrimitive extends DefaultTemporalPrimitive implements TemporalGeometricPrimitive, Separation {
/*     */   public Duration distance(TemporalGeometricPrimitive other) {
/*  48 */     Duration response = null;
/*  49 */     long diff = 0L;
/*  51 */     if (this instanceof Instant && other instanceof Instant)
/*  52 */       if (((Instant)this).getPosition().anyOther() != null && ((Instant)other).getPosition().anyOther() != null) {
/*  53 */         if (!((DefaultTemporalPosition)((Instant)this).getPosition().anyOther()).getFrame().equals(((DefaultTemporalPosition)((Instant)other).getPosition().anyOther()).getFrame()))
/*     */           try {
/*  55 */             throw new Exception("the TM_TemporalPositions are not both associated with the same TM_ReferenceSystem !");
/*  56 */           } catch (Exception ex) {
/*  57 */             Logger.getLogger(DefaultTemporalGeometricPrimitive.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */           }  
/*  60 */       } else if (((Instant)this).getPosition().anyOther() != null) {
/*  61 */         if (((Instant)this).getPosition().anyOther().getIndeterminatePosition() != null || ((DefaultTemporalPosition)((Instant)this).getPosition().anyOther()).getFrame() instanceof org.opengis.temporal.OrdinalReferenceSystem)
/*     */           try {
/*  64 */             throw new Exception("either of the two TM_TemporalPositions is indeterminate or is associated with a TM_OrdianlReferenceSystem !");
/*  65 */           } catch (Exception ex) {
/*  66 */             Logger.getLogger(DefaultTemporalGeometricPrimitive.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */           }  
/*  69 */       } else if (((Instant)other).getPosition().anyOther() != null && ((
/*  70 */         (Instant)other).getPosition().anyOther().getIndeterminatePosition() != null || ((DefaultTemporalPosition)((Instant)other).getPosition().anyOther()).getFrame() instanceof org.opengis.temporal.OrdinalReferenceSystem)) {
/*     */         try {
/*  73 */           throw new Exception("either of the two TM_TemporalPositions is indeterminate or is associated with a TM_OrdianlReferenceSystem !");
/*  74 */         } catch (Exception ex) {
/*  75 */           Logger.getLogger(DefaultTemporalGeometricPrimitive.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }  
/*  81 */     if (relativePosition((TemporalPrimitive)other).equals(RelativePosition.BEFORE) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.AFTER)) {
/*  82 */       if (this instanceof Instant && other instanceof Instant) {
/*  83 */         diff = Math.min(Math.abs(((Instant)other).getPosition().getDate().getTime() - ((Instant)this).getPosition().getDate().getTime()), Math.abs(((Instant)this).getPosition().getDate().getTime() - ((Instant)other).getPosition().getDate().getTime()));
/*  86 */       } else if (this instanceof Instant && other instanceof Period) {
/*  87 */         diff = Math.min(Math.abs(((Period)other).getBeginning().getPosition().getDate().getTime() - ((Instant)this).getPosition().getDate().getTime()), Math.abs(((Period)other).getEnding().getPosition().getDate().getTime() - ((Instant)this).getPosition().getDate().getTime()));
/*  90 */       } else if (this instanceof Period && other instanceof Instant) {
/*  91 */         diff = Math.min(Math.abs(((Instant)other).getPosition().getDate().getTime() - ((Period)this).getEnding().getPosition().getDate().getTime()), Math.abs(((Instant)other).getPosition().getDate().getTime() - ((Period)this).getBeginning().getPosition().getDate().getTime()));
/*  94 */       } else if (this instanceof Period && other instanceof Period) {
/*  95 */         diff = Math.min(Math.abs(((Period)other).getEnding().getPosition().getDate().getTime() - ((Period)this).getBeginning().getPosition().getDate().getTime()), Math.abs(((Period)other).getBeginning().getPosition().getDate().getTime() - ((Period)this).getEnding().getPosition().getDate().getTime()));
/*     */       } 
/* 102 */     } else if (relativePosition((TemporalPrimitive)other).equals(RelativePosition.BEGINS) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.BEGUN_BY) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.CONTAINS) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.DURING) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.ENDED_BY) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.ENDS) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.EQUALS) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.MEETS) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.MET_BY) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.OVERLAPPED_BY) || relativePosition((TemporalPrimitive)other).equals(RelativePosition.OVERLAPS)) {
/* 113 */       diff = 0L;
/*     */     } 
/* 117 */     response = new DefaultPeriodDuration(Math.abs(diff));
/* 118 */     return response;
/*     */   }
/*     */   
/*     */   public Duration length() {
/* 126 */     Duration response = null;
/* 127 */     long diff = 0L;
/* 128 */     if (this instanceof Instant) {
/* 129 */       response = new DefaultPeriodDuration(Math.abs(diff));
/* 130 */       return response;
/*     */     } 
/* 132 */     if (this instanceof Period && (
/* 133 */       (Period)this).getBeginning() != null && ((Period)this).getEnding() != null) {
/* 135 */       response = ((DefaultInstant)((Period)this).getBeginning()).distance((DefaultInstant)((Period)this).getEnding());
/* 136 */       return response;
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultTemporalGeometricPrimitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */