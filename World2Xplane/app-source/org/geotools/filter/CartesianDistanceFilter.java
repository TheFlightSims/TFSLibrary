/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class CartesianDistanceFilter extends GeometryFilterImpl implements GeometryDistanceFilter {
/*     */   private double distance;
/*     */   
/*     */   private String units;
/*     */   
/*     */   protected CartesianDistanceFilter(FilterFactory factory) {
/*  62 */     super(factory);
/*     */   }
/*     */   
/*     */   protected CartesianDistanceFilter(FilterFactory factory, Expression e1, Expression e2) {
/*  66 */     super(factory, e1, e2);
/*     */   }
/*     */   
/*     */   protected CartesianDistanceFilter(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/*  70 */     super(factory, e1, e2, matchAction);
/*     */   }
/*     */   
/*     */   protected CartesianDistanceFilter(short filterType) throws IllegalFilterException {
/*  84 */     super(filterType);
/*  86 */     if (isGeometryDistanceFilter(filterType)) {
/*  87 */       this.filterType = filterType;
/*     */     } else {
/*  89 */       throw new IllegalFilterException("Attempted to create distance geometry filter with nondistance geometry type.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDistance(double distance) {
/* 100 */     this.distance = distance;
/*     */   }
/*     */   
/*     */   public double getDistance() {
/* 109 */     return this.distance;
/*     */   }
/*     */   
/*     */   public String getDistanceUnits() {
/* 113 */     return this.units;
/*     */   }
/*     */   
/*     */   public void setUnits(String units) {
/* 117 */     this.units = units;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 126 */     String operator = null;
/* 129 */     if (this.filterType == 13) {
/* 130 */       operator = " beyond ";
/* 131 */     } else if (this.filterType == 24) {
/* 132 */       operator = " dwithin ";
/*     */     } 
/* 135 */     String distStr = ", distance: " + this.distance;
/* 137 */     Expression leftGeometry = getExpression1();
/* 138 */     Expression rightGeometry = getExpression2();
/* 140 */     if (leftGeometry == null && rightGeometry == null)
/* 141 */       return "[ null" + operator + "null" + distStr + " ]"; 
/* 142 */     if (leftGeometry == null)
/* 143 */       return "[ null" + operator + rightGeometry.toString() + distStr + " ]"; 
/* 145 */     if (rightGeometry == null)
/* 146 */       return "[ " + leftGeometry.toString() + operator + "null" + distStr + " ]"; 
/* 150 */     return "[ " + leftGeometry.toString() + operator + rightGeometry.toString() + distStr + " ]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object oFilter) {
/* 164 */     return (super.equals(oFilter) && this.distance == this.distance);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 173 */     int result = super.hashCode();
/* 174 */     long bits = Double.doubleToLongBits(this.distance);
/* 175 */     result = 37 * result + (int)(bits ^ bits >>> 32L);
/* 177 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\CartesianDistanceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */