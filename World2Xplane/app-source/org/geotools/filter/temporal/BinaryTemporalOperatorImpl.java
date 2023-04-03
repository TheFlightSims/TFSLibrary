/*     */ package org.geotools.filter.temporal;
/*     */ 
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.temporal.BinaryTemporalOperator;
/*     */ import org.opengis.temporal.Instant;
/*     */ import org.opengis.temporal.Period;
/*     */ import org.opengis.temporal.RelativePosition;
/*     */ import org.opengis.temporal.TemporalPrimitive;
/*     */ 
/*     */ public abstract class BinaryTemporalOperatorImpl implements BinaryTemporalOperator {
/*     */   protected Expression e1;
/*     */   
/*     */   protected Expression e2;
/*     */   
/*     */   protected MultiValuedFilter.MatchAction matchAction;
/*     */   
/*     */   protected BinaryTemporalOperatorImpl(Expression e1, Expression e2) {
/*  30 */     this(e1, e2, MultiValuedFilter.MatchAction.ANY);
/*     */   }
/*     */   
/*     */   protected BinaryTemporalOperatorImpl(Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/*  34 */     this.e1 = e1;
/*  35 */     this.e2 = e2;
/*  36 */     this.matchAction = matchAction;
/*     */   }
/*     */   
/*     */   public Expression getExpression1() {
/*  40 */     return this.e1;
/*     */   }
/*     */   
/*     */   public Expression getExpression2() {
/*  44 */     return this.e2;
/*     */   }
/*     */   
/*     */   public MultiValuedFilter.MatchAction getMatchAction() {
/*  48 */     return this.matchAction;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/*  52 */     TemporalPrimitive left = toTemporal(object, this.e1);
/*  53 */     TemporalPrimitive right = toTemporal(object, this.e2);
/*  55 */     if (left == null || right == null)
/*  56 */       return false; 
/*  59 */     RelativePosition pos = left.relativePosition(right);
/*  60 */     return (pos != null && doEvaluate(pos));
/*     */   }
/*     */   
/*     */   protected Instant toInstant(Object object, Expression e) {
/*  64 */     return (Instant)e.evaluate(object, Instant.class);
/*     */   }
/*     */   
/*     */   protected Period toPeriod(Object object, Expression e) {
/*  68 */     return (Period)e.evaluate(object, Period.class);
/*     */   }
/*     */   
/*     */   protected TemporalPrimitive toTemporal(Object object, Expression e) {
/*  72 */     Period period = toPeriod(object, e);
/*  73 */     if (period != null)
/*  74 */       return (TemporalPrimitive)period; 
/*  77 */     Instant instant = toInstant(object, e);
/*  78 */     if (instant != null)
/*  79 */       return (TemporalPrimitive)instant; 
/*  82 */     return null;
/*     */   }
/*     */   
/*     */   protected abstract boolean doEvaluate(RelativePosition paramRelativePosition);
/*     */   
/*     */   public int hashCode() {
/*  89 */     int prime = 31;
/*  90 */     int result = 1;
/*  91 */     result = 31 * result + ((this.e1 == null) ? 0 : this.e1.hashCode());
/*  92 */     result = 31 * result + ((this.e2 == null) ? 0 : this.e2.hashCode());
/*  93 */     result = 31 * result + ((this.matchAction == null) ? 0 : this.matchAction.hashCode());
/*  94 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  99 */     if (this == obj)
/* 100 */       return true; 
/* 101 */     if (obj == null)
/* 102 */       return false; 
/* 103 */     if (getClass() != obj.getClass())
/* 104 */       return false; 
/* 105 */     BinaryTemporalOperatorImpl other = (BinaryTemporalOperatorImpl)obj;
/* 106 */     if (this.e1 == null) {
/* 107 */       if (other.e1 != null)
/* 108 */         return false; 
/* 109 */     } else if (!this.e1.equals(other.e1)) {
/* 110 */       return false;
/*     */     } 
/* 111 */     if (this.e2 == null) {
/* 112 */       if (other.e2 != null)
/* 113 */         return false; 
/* 114 */     } else if (!this.e2.equals(other.e2)) {
/* 115 */       return false;
/*     */     } 
/* 116 */     if (this.matchAction != other.matchAction)
/* 117 */       return false; 
/* 118 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\temporal\BinaryTemporalOperatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */