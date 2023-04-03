/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ 
/*     */ final class ComparableAxisWrapper implements Comparable {
/*     */   private final CoordinateSystemAxis axis;
/*     */   
/*     */   private final DirectionAlongMeridian meridian;
/*     */   
/*     */   private ComparableAxisWrapper(CoordinateSystemAxis axis) {
/*  49 */     this.axis = axis;
/*  50 */     this.meridian = DirectionAlongMeridian.parse(axis.getDirection());
/*     */   }
/*     */   
/*     */   private static boolean isCompassDirection(AxisDirection direction) {
/*  57 */     int compass = DefaultCoordinateSystemAxis.getCompassAngle(direction, AxisDirection.NORTH);
/*  58 */     return (compass != Integer.MIN_VALUE);
/*     */   }
/*     */   
/*     */   public int compareTo(Object other) {
/*  66 */     ComparableAxisWrapper that = (ComparableAxisWrapper)other;
/*  67 */     AxisDirection d1 = this.axis.getDirection();
/*  68 */     AxisDirection d2 = that.axis.getDirection();
/*  69 */     int compass = DefaultCoordinateSystemAxis.getCompassAngle(d2, d1);
/*  70 */     if (compass != Integer.MIN_VALUE)
/*  71 */       return compass; 
/*  73 */     if (isCompassDirection(d1)) {
/*  74 */       assert !isCompassDirection(d2) : d2;
/*  75 */       return -1;
/*     */     } 
/*  77 */     if (isCompassDirection(d2)) {
/*  78 */       assert !isCompassDirection(d1) : d1;
/*  79 */       return 1;
/*     */     } 
/*  81 */     if (this.meridian != null) {
/*  82 */       if (that.meridian != null)
/*  83 */         return this.meridian.compareTo(that.meridian); 
/*  85 */       return -1;
/*     */     } 
/*  86 */     if (that.meridian != null)
/*  87 */       return 1; 
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean sort(CoordinateSystemAxis[] axis) {
/*     */     int j;
/*  98 */     ComparableAxisWrapper[] wrappers = new ComparableAxisWrapper[axis.length];
/*  99 */     for (int i = 0; i < axis.length; i++)
/* 100 */       wrappers[i] = new ComparableAxisWrapper(axis[i]); 
/* 102 */     Arrays.sort((Object[])wrappers);
/* 103 */     boolean changed = false;
/* 104 */     for (int k = 0; k < axis.length; k++) {
/* 105 */       CoordinateSystemAxis a = (wrappers[k]).axis;
/* 106 */       j = changed | ((axis[k] != a) ? 1 : 0);
/* 107 */       axis[k] = a;
/*     */     } 
/* 109 */     return j;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\ComparableAxisWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */