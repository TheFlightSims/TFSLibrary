/*     */ package org.geotools.filter;
/*     */ 
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class IsEqualsToImpl extends MultiCompareFilterImpl implements PropertyIsEqualTo {
/*     */   protected IsEqualsToImpl(FilterFactory factory) {
/*  36 */     this(factory, (Expression)null, (Expression)null);
/*     */   }
/*     */   
/*     */   protected IsEqualsToImpl(FilterFactory factory, Expression expression1, Expression expression2) {
/*  40 */     this(factory, expression1, expression2, true);
/*     */   }
/*     */   
/*     */   protected IsEqualsToImpl(FilterFactory factory, Expression expression1, Expression expression2, MultiValuedFilter.MatchAction matchAction) {
/*  44 */     this(factory, expression1, expression2, true, matchAction);
/*     */   }
/*     */   
/*     */   protected IsEqualsToImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase) {
/*  49 */     super(factory, expression1, expression2, matchCase);
/*  52 */     this.filterType = 14;
/*     */   }
/*     */   
/*     */   protected IsEqualsToImpl(FilterFactory factory, Expression expression1, Expression expression2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  57 */     super(factory, expression1, expression2, matchCase, matchAction);
/*  60 */     this.filterType = 14;
/*     */   }
/*     */   
/*     */   public boolean evaluateInternal(Object value1, Object value2) {
/*  65 */     if (value1 == value2)
/*  67 */       return true; 
/*  69 */     if (value1 == null || value2 == null)
/*  72 */       return false; 
/*  89 */     if (value1.equals(value2))
/*  90 */       return true; 
/*  94 */     if (this.expression1 instanceof org.opengis.filter.expression.Literal && !(this.expression2 instanceof org.opengis.filter.expression.Literal)) {
/*  95 */       Object v1 = Converters.convert(value1, value2.getClass());
/*  96 */       if (v1 != null && value2.equals(v1))
/*  97 */         return true; 
/*  98 */     } else if (this.expression2 instanceof org.opengis.filter.expression.Literal && !(this.expression1 instanceof org.opengis.filter.expression.Literal)) {
/*  99 */       Object v2 = Converters.convert(value2, value1.getClass());
/* 100 */       if (v2 != null && value1.equals(v2))
/* 101 */         return true; 
/*     */     } 
/* 105 */     boolean isNumeric1 = value1 instanceof Number;
/* 106 */     boolean isNumeric2 = value2 instanceof Number;
/* 107 */     if ((isNumeric1 && isNumeric2) || (isNumeric1 && value2 instanceof CharSequence) || (isNumeric2 && value1 instanceof CharSequence)) {
/*     */       Number n1, n2;
/*     */       try {
/* 114 */         n1 = isNumeric1 ? (Number)value1 : parseToNumber(value1.toString());
/* 115 */         n2 = isNumeric2 ? (Number)value2 : parseToNumber(value2.toString());
/* 116 */       } catch (NumberFormatException e) {
/* 118 */         return false;
/*     */       } 
/* 120 */       double fp1 = n1.doubleValue();
/* 121 */       double fp2 = n2.doubleValue();
/*     */       long lg1, lg2;
/* 123 */       if (fp1 == (lg1 = n1.longValue()) && fp2 == (lg2 = n2.longValue()))
/* 126 */         return (lg1 == lg2); 
/* 131 */       return (fp1 == fp2 || (Double.isNaN(fp1) && Double.isNaN(fp2)));
/*     */     } 
/* 133 */     if (!isMatchingCase()) {
/* 135 */       String s1 = (String)Converters.convert(value1, String.class);
/* 136 */       String s2 = (String)Converters.convert(value2, String.class);
/* 138 */       return s1.equalsIgnoreCase(s2);
/*     */     } 
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   private static Number parseToNumber(String value) throws NumberFormatException {
/*     */     try {
/* 155 */       return Long.valueOf(value);
/* 156 */     } catch (NumberFormatException e) {
/* 157 */       return Double.valueOf(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 162 */     return visitor.visit(this, extraData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsEqualsToImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */