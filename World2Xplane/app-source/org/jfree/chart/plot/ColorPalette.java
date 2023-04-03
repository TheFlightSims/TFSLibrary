/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.axis.ValueTick;
/*     */ 
/*     */ public abstract class ColorPalette implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -9029901853079622051L;
/*     */   
/*  65 */   protected double minZ = -1.0D;
/*     */   
/*  68 */   protected double maxZ = -1.0D;
/*     */   
/*     */   protected int[] r;
/*     */   
/*     */   protected int[] g;
/*     */   
/*     */   protected int[] b;
/*     */   
/*  80 */   protected double[] tickValues = null;
/*     */   
/*     */   protected boolean logscale = false;
/*     */   
/*     */   protected boolean inverse = false;
/*     */   
/*  89 */   protected String paletteName = null;
/*     */   
/*     */   protected boolean stepped = false;
/*     */   
/*  95 */   protected static final double log10 = Math.log(10.0D);
/*     */   
/*     */   public Paint getColor(double value) {
/* 112 */     int izV = (int)(253.0D * (value - this.minZ) / (this.maxZ - this.minZ)) + 2;
/* 114 */     return new Color(this.r[izV], this.g[izV], this.b[izV]);
/*     */   }
/*     */   
/*     */   public Color getColor(int izV) {
/* 125 */     return new Color(this.r[izV], this.g[izV], this.b[izV]);
/*     */   }
/*     */   
/*     */   public Color getColorLinear(double value) {
/* 136 */     int izV = 0;
/* 137 */     if (this.stepped) {
/* 138 */       int index = Arrays.binarySearch(this.tickValues, value);
/* 139 */       if (index < 0)
/* 140 */         index = -1 * index - 2; 
/* 143 */       if (index < 0) {
/* 145 */         value = this.minZ;
/*     */       } else {
/* 148 */         value = this.tickValues[index];
/*     */       } 
/*     */     } 
/* 151 */     izV = (int)(253.0D * (value - this.minZ) / (this.maxZ - this.minZ)) + 2;
/* 152 */     izV = Math.min(izV, 255);
/* 153 */     izV = Math.max(izV, 2);
/* 154 */     return getColor(izV);
/*     */   }
/*     */   
/*     */   public Color getColorLog(double value) {
/* 165 */     int izV = 0;
/* 166 */     double minZtmp = this.minZ;
/* 167 */     double maxZtmp = this.maxZ;
/* 168 */     if (this.minZ <= 0.0D) {
/* 170 */       this.maxZ = maxZtmp - minZtmp + 1.0D;
/* 171 */       this.minZ = 1.0D;
/* 172 */       value = value - minZtmp + 1.0D;
/*     */     } 
/* 174 */     double minZlog = Math.log(this.minZ) / log10;
/* 175 */     double maxZlog = Math.log(this.maxZ) / log10;
/* 176 */     value = Math.log(value) / log10;
/* 178 */     if (this.stepped) {
/* 179 */       int numSteps = this.tickValues.length;
/* 180 */       int steps = 256 / (numSteps - 1);
/* 181 */       izV = steps * (int)(numSteps * (value - minZlog) / (maxZlog - minZlog)) + 2;
/*     */     } else {
/* 186 */       izV = (int)(253.0D * (value - minZlog) / (maxZlog - minZlog)) + 2;
/*     */     } 
/* 188 */     izV = Math.min(izV, 255);
/* 189 */     izV = Math.max(izV, 2);
/* 191 */     this.minZ = minZtmp;
/* 192 */     this.maxZ = maxZtmp;
/* 194 */     return getColor(izV);
/*     */   }
/*     */   
/*     */   public double getMaxZ() {
/* 203 */     return this.maxZ;
/*     */   }
/*     */   
/*     */   public double getMinZ() {
/* 212 */     return this.minZ;
/*     */   }
/*     */   
/*     */   public Paint getPaint(double value) {
/* 224 */     if (isLogscale())
/* 225 */       return getColorLog(value); 
/* 228 */     return getColorLinear(value);
/*     */   }
/*     */   
/*     */   public String getPaletteName() {
/* 238 */     return this.paletteName;
/*     */   }
/*     */   
/*     */   public double[] getTickValues() {
/* 247 */     return this.tickValues;
/*     */   }
/*     */   
/*     */   public abstract void initialize();
/*     */   
/*     */   public void invertPalette() {
/* 260 */     int[] red = new int[256];
/* 261 */     int[] green = new int[256];
/* 262 */     int[] blue = new int[256];
/*     */     int i;
/* 263 */     for (i = 0; i < 256; i++) {
/* 264 */       red[i] = this.r[i];
/* 265 */       green[i] = this.g[i];
/* 266 */       blue[i] = this.b[i];
/*     */     } 
/* 269 */     for (i = 2; i < 256; i++) {
/* 270 */       this.r[i] = red[257 - i];
/* 271 */       this.g[i] = green[257 - i];
/* 272 */       this.b[i] = blue[257 - i];
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isInverse() {
/* 282 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public boolean isLogscale() {
/* 291 */     return this.logscale;
/*     */   }
/*     */   
/*     */   public boolean isStepped() {
/* 300 */     return this.stepped;
/*     */   }
/*     */   
/*     */   public void setInverse(boolean inverse) {
/* 309 */     this.inverse = inverse;
/* 310 */     initialize();
/* 311 */     if (inverse)
/* 312 */       invertPalette(); 
/*     */   }
/*     */   
/*     */   public void setLogscale(boolean logscale) {
/* 323 */     this.logscale = logscale;
/*     */   }
/*     */   
/*     */   public void setMaxZ(double newMaxZ) {
/* 332 */     this.maxZ = newMaxZ;
/*     */   }
/*     */   
/*     */   public void setMinZ(double newMinZ) {
/* 341 */     this.minZ = newMinZ;
/*     */   }
/*     */   
/*     */   public void setPaletteName(String paletteName) {
/* 351 */     this.paletteName = paletteName;
/*     */   }
/*     */   
/*     */   public void setStepped(boolean stepped) {
/* 361 */     this.stepped = stepped;
/*     */   }
/*     */   
/*     */   public void setTickValues(double[] newTickValues) {
/* 371 */     this.tickValues = newTickValues;
/*     */   }
/*     */   
/*     */   public void setTickValues(List ticks) {
/* 380 */     this.tickValues = new double[ticks.size()];
/* 381 */     for (int i = 0; i < this.tickValues.length; i++)
/* 382 */       this.tickValues[i] = ((ValueTick)ticks.get(i)).getValue(); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 394 */     if (this == o)
/* 395 */       return true; 
/* 397 */     if (!(o instanceof ColorPalette))
/* 398 */       return false; 
/* 401 */     ColorPalette colorPalette = (ColorPalette)o;
/* 403 */     if (this.inverse != colorPalette.inverse)
/* 404 */       return false; 
/* 406 */     if (this.logscale != colorPalette.logscale)
/* 407 */       return false; 
/* 409 */     if (this.maxZ != colorPalette.maxZ)
/* 410 */       return false; 
/* 412 */     if (this.minZ != colorPalette.minZ)
/* 413 */       return false; 
/* 415 */     if (this.stepped != colorPalette.stepped)
/* 416 */       return false; 
/* 418 */     if (!Arrays.equals(this.b, colorPalette.b))
/* 419 */       return false; 
/* 421 */     if (!Arrays.equals(this.g, colorPalette.g))
/* 422 */       return false; 
/* 424 */     if ((this.paletteName != null) ? !this.paletteName.equals(colorPalette.paletteName) : (colorPalette.paletteName != null))
/* 427 */       return false; 
/* 429 */     if (!Arrays.equals(this.r, colorPalette.r))
/* 430 */       return false; 
/* 432 */     if (!Arrays.equals(this.tickValues, colorPalette.tickValues))
/* 433 */       return false; 
/* 436 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 447 */     long temp = Double.doubleToLongBits(this.minZ);
/* 448 */     int result = (int)(temp ^ temp >>> 32L);
/* 449 */     temp = Double.doubleToLongBits(this.maxZ);
/* 450 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 451 */     result = 29 * result + (this.logscale ? 1 : 0);
/* 452 */     result = 29 * result + (this.inverse ? 1 : 0);
/* 453 */     result = 29 * result + ((this.paletteName != null) ? this.paletteName.hashCode() : 0);
/* 455 */     result = 29 * result + (this.stepped ? 1 : 0);
/* 456 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 468 */     ColorPalette clone = (ColorPalette)super.clone();
/* 469 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\ColorPalette.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */