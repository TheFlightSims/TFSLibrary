/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class BetweenFilterImpl extends CompareFilterImpl implements BetweenFilter {
/*  47 */   protected Expression middleValue = null;
/*     */   
/*     */   protected BetweenFilterImpl(FilterFactory factory) {
/*  50 */     super(factory, (Expression)null, (Expression)null);
/*  51 */     this.filterType = 19;
/*     */   }
/*     */   
/*     */   protected BetweenFilterImpl() throws IllegalFilterException {
/*  60 */     super((short)19);
/*     */   }
/*     */   
/*     */   public final void addMiddleValue(Expression middleValue) {
/*  71 */     setExpression(middleValue);
/*     */   }
/*     */   
/*     */   public void setExpression(Expression expression) {
/*  78 */     this.middleValue = expression;
/*     */   }
/*     */   
/*     */   public final Expression getMiddleValue() {
/*  90 */     return (Expression)getExpression();
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/*  99 */     return this.middleValue;
/*     */   }
/*     */   
/*     */   public Expression getLowerBoundary() {
/* 106 */     return getExpression1();
/*     */   }
/*     */   
/*     */   public void setLowerBoundary(Expression lowerBounds) {
/* 113 */     setExpression1(lowerBounds);
/*     */   }
/*     */   
/*     */   public Expression getUpperBoundary() {
/* 120 */     return getExpression2();
/*     */   }
/*     */   
/*     */   public void setUpperBoundary(Expression upperBounds) {
/* 127 */     setExpression2(upperBounds);
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object feature) {
/* 140 */     if (this.middleValue == null)
/* 141 */       return false; 
/* 155 */     Object middleObj = eval(this.middleValue, feature);
/* 157 */     Object leftObj = eval(this.expression1, feature, middleObj.getClass());
/* 158 */     Object rightObj = eval(this.expression2, feature, middleObj.getClass());
/* 161 */     if (leftObj instanceof Number && middleObj instanceof Number && rightObj instanceof Number) {
/* 164 */       double left = ((Number)leftObj).doubleValue();
/* 165 */       double right = ((Number)rightObj).doubleValue();
/* 166 */       double mid = ((Number)middleObj).doubleValue();
/* 168 */       return (left <= mid && right >= mid);
/*     */     } 
/* 174 */     if (leftObj.getClass() == middleObj.getClass() && rightObj.getClass() == middleObj.getClass() && leftObj instanceof Comparable)
/* 179 */       return (((Comparable<Object>)leftObj).compareTo(middleObj) <= 0 && ((Comparable<Object>)middleObj).compareTo(rightObj) <= 0); 
/* 182 */     String mesg = "Supplied between values are either not compatible or not supported for comparison: " + leftObj + " <= " + middleObj + " <= " + rightObj;
/* 185 */     throw new IllegalArgumentException(mesg);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 198 */     return "[ " + this.expression1.toString() + " < " + this.middleValue.toString() + " < " + this.expression2.toString() + " ]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object oFilter) {
/* 212 */     if (oFilter.getClass() == getClass()) {
/* 213 */       BetweenFilterImpl bFilter = (BetweenFilterImpl)oFilter;
/* 215 */       return (bFilter.getFilterType() == this.filterType && bFilter.getLeftValue().equals(this.expression1) && bFilter.getMiddleValue().equals(this.middleValue) && bFilter.getRightValue().equals(this.expression2));
/*     */     } 
/* 220 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 230 */     int result = 17;
/* 232 */     result = 37 * result + ((this.expression1 == null) ? 0 : this.expression1.hashCode());
/* 234 */     result = 37 * result + ((this.middleValue == null) ? 0 : this.middleValue.hashCode());
/* 236 */     result = 37 * result + ((this.expression2 == null) ? 0 : this.expression2.hashCode());
/* 239 */     return result;
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 253 */     return visitor.visit(this, extraData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\BetweenFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */