/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.util.ConverterFactory;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class BinaryComparisonAbstract extends AbstractFilter implements BinaryComparisonOperator {
/*     */   protected Expression expression1;
/*     */   
/*     */   protected Expression expression2;
/*     */   
/*     */   boolean matchingCase;
/*     */   
/*     */   protected BinaryComparisonAbstract(FilterFactory factory) {
/*  44 */     this(factory, (Expression)null, (Expression)null);
/*     */   }
/*     */   
/*     */   protected BinaryComparisonAbstract(FilterFactory factory, Expression expression1, Expression expression2) {
/*  48 */     this(factory, expression1, expression2, true);
/*     */   }
/*     */   
/*     */   protected BinaryComparisonAbstract(FilterFactory factory, Expression expression1, Expression expression2, boolean matchingCase) {
/*  52 */     super(factory);
/*  53 */     this.expression1 = expression1;
/*  54 */     this.expression2 = expression2;
/*  55 */     this.matchingCase = matchingCase;
/*     */   }
/*     */   
/*     */   public Expression getExpression1() {
/*  59 */     return this.expression1;
/*     */   }
/*     */   
/*     */   public void setExpression1(Expression expression) {
/*  63 */     this.expression1 = expression;
/*     */   }
/*     */   
/*     */   public Expression getExpression2() {
/*  67 */     return this.expression2;
/*     */   }
/*     */   
/*     */   public void setExpression2(Expression expression) {
/*  71 */     this.expression2 = expression;
/*     */   }
/*     */   
/*     */   public boolean isMatchingCase() {
/*  75 */     return this.matchingCase;
/*     */   }
/*     */   
/*     */   public MultiValuedFilter.MatchAction getMatchAction() {
/*  79 */     return MultiValuedFilter.MatchAction.ANY;
/*     */   }
/*     */   
/*     */   public Filter and(Filter filter) {
/*  83 */     return (Filter)this.factory.and(this, filter);
/*     */   }
/*     */   
/*     */   public Filter or(Filter filter) {
/*  87 */     return (Filter)this.factory.or(this, filter);
/*     */   }
/*     */   
/*     */   public Filter not() {
/*  91 */     return (Filter)this.factory.not(this);
/*     */   }
/*     */   
/*     */   protected Object[] eval(Object object) {
/* 104 */     Object v1 = eval(getExpression1(), object);
/* 105 */     Object v2 = eval(getExpression2(), object);
/* 107 */     return eval(v1, v2);
/*     */   }
/*     */   
/*     */   protected Object[] eval(Object v1, Object v2) {
/* 120 */     if (v1 != null && v2 != null) {
/* 122 */       if (v1.getClass().equals(v2.getClass()))
/* 124 */         return new Object[] { v1, v2 }; 
/* 128 */       Hints hints = new Hints((RenderingHints.Key)ConverterFactory.SAFE_CONVERSION, Boolean.TRUE);
/* 129 */       Object o = Converters.convert(v2, v1.getClass(), hints);
/* 130 */       if (o != null)
/* 131 */         return new Object[] { v1, o }; 
/* 134 */       o = Converters.convert(v1, v2.getClass(), hints);
/* 135 */       if (o != null)
/* 136 */         return new Object[] { o, v2 }; 
/* 140 */       hints.put(ConverterFactory.SAFE_CONVERSION, Boolean.FALSE);
/* 141 */       o = Converters.convert(v2, v1.getClass(), hints);
/* 142 */       if (o != null)
/* 143 */         return new Object[] { v1, o }; 
/* 145 */       o = Converters.convert(v1, v2.getClass(), hints);
/* 146 */       if (o != null)
/* 147 */         return new Object[] { o, v2 }; 
/*     */     } 
/* 152 */     return new Object[] { v1, v2 };
/*     */   }
/*     */   
/*     */   protected final Comparable comparable(Object value) {
/* 161 */     if (value == null)
/* 162 */       return null; 
/* 163 */     if (value instanceof Comparable)
/* 164 */       return (Comparable)value; 
/* 166 */     return String.valueOf(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\BinaryComparisonAbstract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */