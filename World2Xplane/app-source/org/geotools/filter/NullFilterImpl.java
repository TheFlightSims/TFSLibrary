/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class NullFilterImpl extends AbstractFilterImpl implements NullFilter {
/*  35 */   private Expression nullCheck = null;
/*     */   
/*     */   protected NullFilterImpl() {
/*  41 */     super(CommonFactoryFinder.getFilterFactory(null));
/*  42 */     this.filterType = 21;
/*     */   }
/*     */   
/*     */   public final void nullCheckValue(Expression nullCheck) throws IllegalFilterException {
/*  60 */     setExpression(nullCheck);
/*     */   }
/*     */   
/*     */   public final Expression getNullCheckValue() {
/*  71 */     return (Expression)getExpression();
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/*  78 */     return this.nullCheck;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression nullCheck) {
/*  85 */     if (nullCheck instanceof AttributeExpression) {
/*  86 */       this.nullCheck = nullCheck;
/*     */     } else {
/*  88 */       throw new IllegalFilterException("Attempted to add non-attribute expression to a null filter.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object feature) {
/* 102 */     if (this.nullCheck == null)
/* 103 */       return false; 
/* 105 */     return (this.nullCheck.evaluate(feature) == null);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 115 */     return "[ " + this.nullCheck.toString() + " is null ]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 129 */     if (obj != null && obj.getClass() == getClass()) {
/* 130 */       NullFilterImpl nullFilter = (NullFilterImpl)obj;
/* 132 */       return (nullFilter.getFilterType() == this.filterType && nullFilter.getNullCheckValue().equals(this.nullCheck));
/*     */     } 
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 145 */     int result = 17;
/* 146 */     result = 37 * result + ((this.nullCheck == null) ? 0 : this.nullCheck.hashCode());
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 163 */     return visitor.visit(this, extraData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\NullFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */