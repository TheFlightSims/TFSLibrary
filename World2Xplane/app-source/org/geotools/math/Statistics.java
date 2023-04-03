/*     */ package org.geotools.math;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.resources.i18n.Descriptions;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class Statistics implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -22884277805533726L;
/*     */   
/*  70 */   private double min = Double.NaN;
/*     */   
/*  77 */   private double max = Double.NaN;
/*     */   
/*  84 */   private double sum = 0.0D;
/*     */   
/*  91 */   private double sum2 = 0.0D;
/*     */   
/*  98 */   private int n = 0;
/*     */   
/* 106 */   private int nNaN = 0;
/*     */   
/*     */   public void reset() {
/* 120 */     this.min = Double.NaN;
/* 121 */     this.max = Double.NaN;
/* 122 */     this.sum = 0.0D;
/* 123 */     this.sum2 = 0.0D;
/* 124 */     this.n = 0;
/* 125 */     this.nNaN = 0;
/*     */   }
/*     */   
/*     */   public void add(double sample) {
/* 138 */     if (!Double.isNaN(sample)) {
/* 143 */       if (this.min > sample)
/* 143 */         this.min = sample; 
/* 144 */       if (this.max < sample)
/* 144 */         this.max = sample; 
/* 145 */       this.sum2 += sample * sample;
/* 146 */       this.sum += sample;
/* 147 */       this.n++;
/*     */     } else {
/* 149 */       this.nNaN++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(long sample) {
/* 163 */     double fdatum = sample;
/* 164 */     if (this.min > fdatum)
/* 164 */       this.min = fdatum; 
/* 165 */     if (this.max < fdatum)
/* 165 */       this.max = fdatum; 
/* 166 */     this.sum2 += fdatum * fdatum;
/* 167 */     this.sum += fdatum;
/* 168 */     this.n++;
/*     */   }
/*     */   
/*     */   public void add(Statistics stats) {
/* 179 */     if (stats != null) {
/* 181 */       if (Double.isNaN(this.min) || stats.min < this.min)
/* 181 */         this.min = stats.min; 
/* 182 */       if (Double.isNaN(this.max) || stats.max > this.max)
/* 182 */         this.max = stats.max; 
/* 183 */       this.sum2 += stats.sum2;
/* 184 */       this.sum += stats.sum;
/* 185 */       this.n += stats.n;
/* 186 */       this.nNaN += stats.nNaN;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int countNaN() {
/* 196 */     return Math.max(this.nNaN, 0);
/*     */   }
/*     */   
/*     */   public int count() {
/* 203 */     return this.n;
/*     */   }
/*     */   
/*     */   public double minimum() {
/* 212 */     return this.min;
/*     */   }
/*     */   
/*     */   public double maximum() {
/* 221 */     return this.max;
/*     */   }
/*     */   
/*     */   public double range() {
/* 233 */     return this.max - this.min;
/*     */   }
/*     */   
/*     */   public double mean() {
/* 240 */     return this.sum / this.n;
/*     */   }
/*     */   
/*     */   public double rms() {
/* 247 */     return Math.sqrt(this.sum2 / this.n);
/*     */   }
/*     */   
/*     */   public double standardDeviation(boolean allPopulation) {
/* 274 */     return Math.sqrt((this.sum2 - this.sum * this.sum / this.n) / (allPopulation ? this.n : (this.n - 1)));
/*     */   }
/*     */   
/*     */   public Statistics clone() {
/*     */     try {
/* 283 */       return (Statistics)super.clone();
/* 284 */     } catch (CloneNotSupportedException exception) {
/* 286 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 295 */     if (obj != null && getClass().equals(obj.getClass())) {
/* 296 */       Statistics cast = (Statistics)obj;
/* 297 */       return (this.n == cast.n && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(cast.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(cast.max) && Double.doubleToLongBits(this.sum) == Double.doubleToLongBits(cast.sum) && Double.doubleToLongBits(this.sum2) == Double.doubleToLongBits(cast.sum2));
/*     */     } 
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 311 */     long code = Double.doubleToLongBits(this.min) + 37L * (Double.doubleToLongBits(this.max) + 37L * (Double.doubleToLongBits(this.sum) + 37L * Double.doubleToLongBits(this.sum2)));
/* 315 */     return (int)code ^ (int)(code >>> 32L) ^ this.n;
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 325 */     return toString(null, false);
/*     */   }
/*     */   
/*     */   public String toString(Locale locale, boolean tabulations) {
/* 346 */     String text = Descriptions.getResources(locale).getString(3, new Number[] { Integer.valueOf(count()), Double.valueOf(minimum()), Double.valueOf(maximum()), Double.valueOf(mean()), Double.valueOf(rms()), Double.valueOf(standardDeviation(false)) });
/* 350 */     if (!tabulations) {
/* 351 */       TableWriter tmp = new TableWriter(null, 1);
/* 352 */       tmp.write(text);
/* 353 */       tmp.setColumnAlignment(1, 2);
/* 354 */       text = tmp.toString();
/*     */     } 
/* 356 */     return text;
/*     */   }
/*     */   
/*     */   public static class Delta extends Statistics {
/*     */     private static final long serialVersionUID = 3464306833883333219L;
/*     */     
/*     */     private Statistics delta;
/*     */     
/* 387 */     private double last = Double.NaN;
/*     */     
/*     */     private long lastAsLong;
/*     */     
/*     */     public Delta() {
/* 400 */       this.delta = new Statistics();
/* 401 */       this.delta.nNaN = -1;
/*     */     }
/*     */     
/*     */     public Delta(Statistics delta) {
/* 420 */       this.delta = delta;
/* 421 */       delta.reset();
/* 422 */       delta.nNaN = -1;
/*     */     }
/*     */     
/*     */     public Statistics getDeltaStatistics() {
/* 434 */       return this.delta;
/*     */     }
/*     */     
/*     */     public void reset() {
/* 443 */       super.reset();
/* 444 */       this.delta.reset();
/* 445 */       this.delta.nNaN = -1;
/* 446 */       this.last = Double.NaN;
/* 447 */       this.lastAsLong = 0L;
/*     */     }
/*     */     
/*     */     public void add(double sample) {
/* 458 */       super.add(sample);
/* 459 */       this.delta.add(sample - this.last);
/* 460 */       this.last = sample;
/* 461 */       this.lastAsLong = (long)sample;
/*     */     }
/*     */     
/*     */     public void add(long sample) {
/* 472 */       super.add(sample);
/* 473 */       if (this.last == this.lastAsLong) {
/* 476 */         this.delta.add(sample - this.lastAsLong);
/*     */       } else {
/* 480 */         this.delta.add(sample - this.last);
/*     */       } 
/* 482 */       this.last = sample;
/* 483 */       this.lastAsLong = sample;
/*     */     }
/*     */     
/*     */     public void add(Statistics stats) throws ClassCastException {
/* 499 */       if (stats != null) {
/* 500 */         Delta toAdd = (Delta)stats;
/* 501 */         if (toAdd.delta.nNaN >= 0) {
/* 502 */           this.delta.add(toAdd.delta);
/* 503 */           this.last = toAdd.last;
/* 504 */           this.lastAsLong = toAdd.lastAsLong;
/* 505 */           super.add(stats);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Delta clone() {
/* 515 */       Delta copy = (Delta)super.clone();
/* 516 */       copy.delta = copy.delta.clone();
/* 517 */       return copy;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 525 */       return (super.equals(obj) && this.delta.equals(((Delta)obj).delta));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 533 */       return super.hashCode() + 37 * this.delta.hashCode();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\Statistics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */