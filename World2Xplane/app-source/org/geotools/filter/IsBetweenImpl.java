/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public class IsBetweenImpl extends CompareFilterImpl implements BetweenFilter {
/*     */   private Expression expression;
/*     */   
/*     */   protected MultiValuedFilter.MatchAction matchAction;
/*     */   
/*     */   protected IsBetweenImpl(FilterFactory factory, Expression lower, Expression expression, Expression upper, MultiValuedFilter.MatchAction matchAction) {
/*  42 */     super(factory, lower, upper);
/*  43 */     this.expression = expression;
/*  44 */     this.matchAction = matchAction;
/*  47 */     this.filterType = 19;
/*     */   }
/*     */   
/*     */   protected IsBetweenImpl(FilterFactory factory, Expression lower, Expression expression, Expression upper) {
/*  51 */     this(factory, lower, expression, upper, MultiValuedFilter.MatchAction.ANY);
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/*  55 */     return this.expression;
/*     */   }
/*     */   
/*     */   public void setExpression(Expression expression) {
/*  58 */     this.expression = expression;
/*     */   }
/*     */   
/*     */   public MultiValuedFilter.MatchAction getMatchAction() {
/*  62 */     return this.matchAction;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object feature) {
/*  68 */     Object object0 = eval(this.expression, feature);
/*  69 */     Object object1 = eval(this.expression1, feature);
/*  70 */     Object object2 = eval(this.expression2, feature);
/*  72 */     if (object0 == null)
/*  73 */       return false; 
/*  76 */     if (!(object0 instanceof Collection) && !(object1 instanceof Collection) && !(object2 instanceof Collection))
/*  77 */       return evaluateInternal(object0, object1, object2); 
/*  80 */     Collection<Object> oValues = (object0 instanceof Collection) ? (Collection<Object>)object0 : Collections.<Object>singletonList(object0);
/*  82 */     Collection<Object> leftValues = (object1 instanceof Collection) ? (Collection<Object>)object1 : Collections.<Object>singletonList(object1);
/*  84 */     Collection<Object> rightValues = (object2 instanceof Collection) ? (Collection<Object>)object2 : Collections.<Object>singletonList(object2);
/*  87 */     int count = 0;
/*  89 */     for (Object value1 : leftValues) {
/*  90 */       for (Object value2 : rightValues) {
/*  91 */         for (Object value0 : oValues) {
/*  92 */           boolean temp = evaluateInternal(value0, value1, value2);
/*  93 */           if (temp)
/*  94 */             count++; 
/*  97 */           switch (this.matchAction) {
/*     */             case ONE:
/*  98 */               if (count > 1)
/*  98 */                 return false; 
/*     */             case ALL:
/*  99 */               if (!temp)
/*  99 */                 return false; 
/*     */             case ANY:
/* 100 */               if (temp)
/* 100 */                 return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 106 */     switch (this.matchAction) {
/*     */       case ONE:
/* 107 */         return (count == 1);
/*     */       case ALL:
/* 108 */         return true;
/*     */       case ANY:
/* 109 */         return false;
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   public boolean evaluateInternal(Object value, Object lower, Object upper) {
/* 116 */     Object o = value;
/* 117 */     if (o == null)
/* 118 */       return false; 
/* 121 */     Object l = Converters.convert(lower, o.getClass());
/* 122 */     Object u = Converters.convert(upper, o.getClass());
/* 123 */     if (l == null || u == null) {
/* 125 */       l = lower;
/* 126 */       o = Converters.convert(value, l.getClass());
/* 127 */       u = Converters.convert(upper, l.getClass());
/* 129 */       if (o == null || u == null) {
/* 131 */         u = upper;
/* 132 */         o = Converters.convert(value, u.getClass());
/* 133 */         l = Converters.convert(lower, u.getClass());
/* 135 */         if (o == null || l == null)
/* 137 */           return false; 
/*     */       } 
/*     */     } 
/* 142 */     Comparable<Object> lc = comparable(l);
/* 143 */     Comparable<Object> uc = comparable(u);
/* 145 */     return (lc.compareTo(o) <= 0 && uc.compareTo(o) >= 0);
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 149 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public Expression getLowerBoundary() {
/* 153 */     return getExpression1();
/*     */   }
/*     */   
/*     */   public void setLowerBoundary(Expression lowerBoundary) {
/* 157 */     setExpression1(lowerBoundary);
/*     */   }
/*     */   
/*     */   public Expression getUpperBoundary() {
/* 161 */     return getExpression2();
/*     */   }
/*     */   
/*     */   public void setUpperBoundary(Expression upperBoundary) {
/* 165 */     setExpression2(upperBoundary);
/*     */   }
/*     */   
/*     */   public final Expression getMiddleValue() {
/* 172 */     return (Expression)getExpression();
/*     */   }
/*     */   
/*     */   public void addMiddleValue(Expression middleValue) {
/* 179 */     setExpression(middleValue);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 183 */     return "[ " + this.expression + " BETWEEN " + this.expression1 + " AND " + this.expression2 + " ]";
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 188 */     int prime = 31;
/* 189 */     int result = super.hashCode();
/* 190 */     result = 31 * result + ((this.expression == null) ? 0 : this.expression.hashCode());
/* 191 */     result = 31 * result + ((this.matchAction == null) ? 0 : this.matchAction.hashCode());
/* 192 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 197 */     if (this == obj)
/* 198 */       return true; 
/* 199 */     if (!super.equals(obj))
/* 200 */       return false; 
/* 201 */     if (getClass() != obj.getClass())
/* 202 */       return false; 
/* 203 */     IsBetweenImpl other = (IsBetweenImpl)obj;
/* 204 */     if (this.expression == null) {
/* 205 */       if (other.expression != null)
/* 206 */         return false; 
/* 207 */     } else if (!this.expression.equals(other.expression)) {
/* 208 */       return false;
/*     */     } 
/* 209 */     if (this.matchAction != other.matchAction)
/* 210 */       return false; 
/* 211 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\IsBetweenImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */