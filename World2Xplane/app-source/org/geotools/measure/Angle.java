/*     */ package org.geotools.measure;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.util.Locale;
/*     */ import org.geotools.resources.ClassChanger;
/*     */ 
/*     */ public class Angle implements Comparable<Angle>, Serializable {
/*     */   private static final long serialVersionUID = 1158747349433104534L;
/*     */   
/*     */   private static Format format;
/*     */   
/*     */   private final double theta;
/*     */   
/*     */   static {
/*  57 */     ClassChanger.register(new ClassChanger<Angle, Double>(Angle.class, Double.class) {
/*     */           protected Double convert(Angle o) {
/*  59 */             return Double.valueOf(o.theta);
/*     */           }
/*     */           
/*     */           protected Angle inverseConvert(Double value) {
/*  63 */             return new Angle(value.doubleValue());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Angle(double theta) {
/*  79 */     this.theta = theta;
/*     */   }
/*     */   
/*     */   public Angle(String string) throws NumberFormatException {
/*     */     Angle theta;
/*  91 */     Format format = getAngleFormat();
/*     */     try {
/*  94 */       synchronized (Angle.class) {
/*  95 */         theta = (Angle)format.parseObject(string);
/*     */       } 
/*  97 */     } catch (ParseException exception) {
/*  98 */       NumberFormatException e = new NumberFormatException(exception.getLocalizedMessage());
/*  99 */       e.initCause(exception);
/* 100 */       throw e;
/*     */     } 
/* 102 */     if (getClass().isAssignableFrom(theta.getClass())) {
/* 103 */       this.theta = theta.theta;
/*     */     } else {
/* 105 */       throw new NumberFormatException(string);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double degrees() {
/* 113 */     return this.theta;
/*     */   }
/*     */   
/*     */   public double radians() {
/* 120 */     return Math.toRadians(this.theta);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 128 */     long code = Double.doubleToLongBits(this.theta);
/* 129 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 137 */     if (object == this)
/* 138 */       return true; 
/* 140 */     if (object != null && getClass().equals(object.getClass())) {
/* 141 */       Angle that = (Angle)object;
/* 142 */       return (Double.doubleToLongBits(this.theta) == Double.doubleToLongBits(that.theta));
/*     */     } 
/* 145 */     return false;
/*     */   }
/*     */   
/*     */   public int compareTo(Angle that) {
/* 154 */     return Double.compare(this.theta, that.theta);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 162 */     StringBuffer buffer = new StringBuffer();
/* 163 */     synchronized (Angle.class) {
/* 164 */       Format format = getAngleFormat();
/* 165 */       buffer = format.format(this, buffer, null);
/*     */     } 
/* 167 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private static Format getAngleFormat() {
/* 175 */     assert Thread.holdsLock(Angle.class);
/* 176 */     if (format == null)
/* 177 */       format = new AngleFormat("D°MM.m'", Locale.US); 
/* 179 */     return format;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\Angle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */