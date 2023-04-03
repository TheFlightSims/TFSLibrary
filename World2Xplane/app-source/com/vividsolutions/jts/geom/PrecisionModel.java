/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PrecisionModel implements Serializable, Comparable {
/*     */   private static final long serialVersionUID = 7777263578777803835L;
/*     */   
/*     */   public static PrecisionModel mostPrecise(PrecisionModel pm1, PrecisionModel pm2) {
/* 101 */     if (pm1.compareTo(pm2) >= 0)
/* 102 */       return pm1; 
/* 103 */     return pm2;
/*     */   }
/*     */   
/*     */   public static class Type implements Serializable {
/*     */     private static final long serialVersionUID = -5528602631731589822L;
/*     */     
/* 115 */     private static Map nameToTypeMap = new HashMap<>();
/*     */     
/*     */     private String name;
/*     */     
/*     */     public Type(String name) {
/* 117 */       this.name = name;
/* 118 */       nameToTypeMap.put(name, this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 121 */       return this.name;
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 128 */       return nameToTypeMap.get(this.name);
/*     */     }
/*     */   }
/*     */   
/* 136 */   public static final Type FIXED = new Type("FIXED");
/*     */   
/* 142 */   public static final Type FLOATING = new Type("FLOATING");
/*     */   
/* 148 */   public static final Type FLOATING_SINGLE = new Type("FLOATING SINGLE");
/*     */   
/*     */   public static final double maximumPreciseValue = 9.007199254740992E15D;
/*     */   
/*     */   private Type modelType;
/*     */   
/*     */   private double scale;
/*     */   
/*     */   public PrecisionModel() {
/* 173 */     this.modelType = FLOATING;
/*     */   }
/*     */   
/*     */   public PrecisionModel(Type modelType) {
/* 185 */     this.modelType = modelType;
/* 186 */     if (modelType == FIXED)
/* 188 */       setScale(1.0D); 
/*     */   }
/*     */   
/*     */   public PrecisionModel(double scale, double offsetX, double offsetY) {
/* 204 */     this.modelType = FIXED;
/* 205 */     setScale(scale);
/*     */   }
/*     */   
/*     */   public PrecisionModel(double scale) {
/* 216 */     this.modelType = FIXED;
/* 217 */     setScale(scale);
/*     */   }
/*     */   
/*     */   public PrecisionModel(PrecisionModel pm) {
/* 224 */     this.modelType = pm.modelType;
/* 225 */     this.scale = pm.scale;
/*     */   }
/*     */   
/*     */   public boolean isFloating() {
/* 235 */     return (this.modelType == FLOATING || this.modelType == FLOATING_SINGLE);
/*     */   }
/*     */   
/*     */   public int getMaximumSignificantDigits() {
/* 260 */     int maxSigDigits = 16;
/* 261 */     if (this.modelType == FLOATING) {
/* 262 */       maxSigDigits = 16;
/* 263 */     } else if (this.modelType == FLOATING_SINGLE) {
/* 264 */       maxSigDigits = 6;
/* 265 */     } else if (this.modelType == FIXED) {
/* 266 */       maxSigDigits = 1 + (int)Math.ceil(Math.log(getScale()) / Math.log(10.0D));
/*     */     } 
/* 268 */     return maxSigDigits;
/*     */   }
/*     */   
/*     */   public double getScale() {
/* 282 */     return this.scale;
/*     */   }
/*     */   
/*     */   public Type getType() {
/* 292 */     return this.modelType;
/*     */   }
/*     */   
/*     */   private void setScale(double scale) {
/* 300 */     this.scale = Math.abs(scale);
/*     */   }
/*     */   
/*     */   public double getOffsetX() {
/* 312 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getOffsetY() {
/* 325 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public void toInternal(Coordinate external, Coordinate internal) {
/* 337 */     if (isFloating()) {
/* 338 */       internal.x = external.x;
/* 339 */       internal.y = external.y;
/*     */     } else {
/* 342 */       internal.x = makePrecise(external.x);
/* 343 */       internal.y = makePrecise(external.y);
/*     */     } 
/* 345 */     internal.z = external.z;
/*     */   }
/*     */   
/*     */   public Coordinate toInternal(Coordinate external) {
/* 357 */     Coordinate internal = new Coordinate(external);
/* 358 */     makePrecise(internal);
/* 359 */     return internal;
/*     */   }
/*     */   
/*     */   public Coordinate toExternal(Coordinate internal) {
/* 371 */     Coordinate external = new Coordinate(internal);
/* 372 */     return external;
/*     */   }
/*     */   
/*     */   public void toExternal(Coordinate internal, Coordinate external) {
/* 384 */     external.x = internal.x;
/* 385 */     external.y = internal.y;
/*     */   }
/*     */   
/*     */   public double makePrecise(double val) {
/* 402 */     if (Double.isNaN(val))
/* 402 */       return val; 
/* 404 */     if (this.modelType == FLOATING_SINGLE) {
/* 405 */       float floatSingleVal = (float)val;
/* 406 */       return floatSingleVal;
/*     */     } 
/* 408 */     if (this.modelType == FIXED)
/* 409 */       return Math.round(val * this.scale) / this.scale; 
/* 413 */     return val;
/*     */   }
/*     */   
/*     */   public void makePrecise(Coordinate coord) {
/* 422 */     if (this.modelType == FLOATING)
/*     */       return; 
/* 424 */     coord.x = makePrecise(coord.x);
/* 425 */     coord.y = makePrecise(coord.y);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 431 */     String description = "UNKNOWN";
/* 432 */     if (this.modelType == FLOATING) {
/* 433 */       description = "Floating";
/* 434 */     } else if (this.modelType == FLOATING_SINGLE) {
/* 435 */       description = "Floating-Single";
/* 436 */     } else if (this.modelType == FIXED) {
/* 437 */       description = "Fixed (Scale=" + getScale() + ")";
/*     */     } 
/* 439 */     return description;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 443 */     if (!(other instanceof PrecisionModel))
/* 444 */       return false; 
/* 446 */     PrecisionModel otherPrecisionModel = (PrecisionModel)other;
/* 447 */     return (this.modelType == otherPrecisionModel.modelType && this.scale == otherPrecisionModel.scale);
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 464 */     PrecisionModel other = (PrecisionModel)o;
/* 466 */     int sigDigits = getMaximumSignificantDigits();
/* 467 */     int otherSigDigits = other.getMaximumSignificantDigits();
/* 468 */     return (new Integer(sigDigits)).compareTo(new Integer(otherSigDigits));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\PrecisionModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */