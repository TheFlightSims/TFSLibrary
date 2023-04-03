/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class TopologyValidationError {
/*     */   public static final int ERROR = 0;
/*     */   
/*     */   public static final int REPEATED_POINT = 1;
/*     */   
/*     */   public static final int HOLE_OUTSIDE_SHELL = 2;
/*     */   
/*     */   public static final int NESTED_HOLES = 3;
/*     */   
/*     */   public static final int DISCONNECTED_INTERIOR = 4;
/*     */   
/*     */   public static final int SELF_INTERSECTION = 5;
/*     */   
/*     */   public static final int RING_SELF_INTERSECTION = 6;
/*     */   
/*     */   public static final int NESTED_SHELLS = 7;
/*     */   
/*     */   public static final int DUPLICATE_RINGS = 8;
/*     */   
/*     */   public static final int TOO_FEW_POINTS = 9;
/*     */   
/*     */   public static final int INVALID_COORDINATE = 10;
/*     */   
/*     */   public static final int RING_NOT_CLOSED = 11;
/*     */   
/* 118 */   public static final String[] errMsg = new String[] { 
/* 118 */       "Topology Validation Error", "Repeated Point", "Hole lies outside shell", "Holes are nested", "Interior is disconnected", "Self-intersection", "Ring Self-intersection", "Nested shells", "Duplicate Rings", "Too few distinct points in geometry component", 
/* 118 */       "Invalid Coordinate", "Ring is not closed" };
/*     */   
/*     */   private int errorType;
/*     */   
/*     */   private Coordinate pt;
/*     */   
/*     */   public TopologyValidationError(int errorType, Coordinate pt) {
/* 144 */     this.errorType = errorType;
/* 145 */     if (pt != null)
/* 146 */       this.pt = (Coordinate)pt.clone(); 
/*     */   }
/*     */   
/*     */   public TopologyValidationError(int errorType) {
/* 157 */     this(errorType, null);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 165 */     return this.pt;
/*     */   }
/*     */   
/*     */   public int getErrorType() {
/* 172 */     return this.errorType;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 180 */     return errMsg[this.errorType];
/*     */   }
/*     */   
/*     */   public String toString() {
/* 188 */     String locStr = "";
/* 189 */     if (this.pt != null)
/* 190 */       locStr = " at or near point " + this.pt; 
/* 191 */     return getMessage() + locStr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\TopologyValidationError.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */