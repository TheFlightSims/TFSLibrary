/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.BinarySpatialOperator;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ import org.opengis.filter.temporal.After;
/*     */ import org.opengis.filter.temporal.AnyInteracts;
/*     */ import org.opengis.filter.temporal.Before;
/*     */ import org.opengis.filter.temporal.Begins;
/*     */ import org.opengis.filter.temporal.BegunBy;
/*     */ import org.opengis.filter.temporal.BinaryTemporalOperator;
/*     */ import org.opengis.filter.temporal.During;
/*     */ import org.opengis.filter.temporal.EndedBy;
/*     */ import org.opengis.filter.temporal.Ends;
/*     */ import org.opengis.filter.temporal.Meets;
/*     */ import org.opengis.filter.temporal.MetBy;
/*     */ import org.opengis.filter.temporal.OverlappedBy;
/*     */ import org.opengis.filter.temporal.TContains;
/*     */ import org.opengis.filter.temporal.TEquals;
/*     */ import org.opengis.filter.temporal.TOverlaps;
/*     */ 
/*     */ public class LiteralDemultiplyingFilterVisitor extends DuplicatingFilterVisitor {
/*     */   protected class BinaryComparisonOperatorReplacer implements FilterReplacer<BinaryComparisonOperator> {
/*     */     protected Method method;
/*     */     
/*     */     public BinaryComparisonOperatorReplacer(String methodName) {
/*     */       try {
/*  88 */         this.method = LiteralDemultiplyingFilterVisitor.this.ff.getClass().getMethod(methodName, new Class[] { Expression.class, Expression.class, boolean.class, MultiValuedFilter.MatchAction.class });
/*  89 */       } catch (Exception e) {
/*  90 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Expression getExpression1(BinaryComparisonOperator filter) {
/*  96 */       return filter.getExpression1();
/*     */     }
/*     */     
/*     */     public Expression getExpression2(BinaryComparisonOperator filter) {
/* 101 */       return filter.getExpression2();
/*     */     }
/*     */     
/*     */     public Filter replaceExpressions(BinaryComparisonOperator filter, Expression expression1, Expression expression2) {
/*     */       try {
/* 107 */         return (Filter)this.method.invoke(LiteralDemultiplyingFilterVisitor.this.ff, new Object[] { expression1, expression2, Boolean.valueOf(filter.isMatchingCase()), filter.getMatchAction() });
/* 108 */       } catch (Exception e) {
/* 109 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected class BinarySpatialOperatorReplacer implements FilterReplacer<BinarySpatialOperator> {
/*     */     protected Method method;
/*     */     
/*     */     public BinarySpatialOperatorReplacer(String methodName) {
/*     */       try {
/* 127 */         this.method = LiteralDemultiplyingFilterVisitor.this.ff.getClass().getMethod(methodName, new Class[] { Expression.class, Expression.class, MultiValuedFilter.MatchAction.class });
/* 128 */       } catch (Exception e) {
/* 129 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Expression getExpression1(BinarySpatialOperator filter) {
/* 135 */       return filter.getExpression1();
/*     */     }
/*     */     
/*     */     public Expression getExpression2(BinarySpatialOperator filter) {
/* 140 */       return filter.getExpression2();
/*     */     }
/*     */     
/*     */     public Filter replaceExpressions(BinarySpatialOperator filter, Expression expression1, Expression expression2) {
/*     */       try {
/* 146 */         return (Filter)this.method.invoke(LiteralDemultiplyingFilterVisitor.this.ff, new Object[] { expression1, expression2, filter.getMatchAction() });
/* 147 */       } catch (Exception e) {
/* 148 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected class BinaryTemporalOperatorReplacer implements FilterReplacer<BinaryTemporalOperator> {
/*     */     protected Method method;
/*     */     
/*     */     public BinaryTemporalOperatorReplacer(String methodName) {
/*     */       try {
/* 166 */         this.method = LiteralDemultiplyingFilterVisitor.this.ff.getClass().getMethod(methodName, new Class[] { Expression.class, Expression.class, MultiValuedFilter.MatchAction.class });
/* 167 */       } catch (Exception e) {
/* 168 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Expression getExpression1(BinaryTemporalOperator filter) {
/* 174 */       return filter.getExpression1();
/*     */     }
/*     */     
/*     */     public Expression getExpression2(BinaryTemporalOperator filter) {
/* 179 */       return filter.getExpression2();
/*     */     }
/*     */     
/*     */     public Filter replaceExpressions(BinaryTemporalOperator filter, Expression expression1, Expression expression2) {
/*     */       try {
/* 185 */         return (Filter)this.method.invoke(LiteralDemultiplyingFilterVisitor.this.ff, new Object[] { expression1, expression2, filter.getMatchAction() });
/* 186 */       } catch (Exception e) {
/* 187 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected <T extends MultiValuedFilter> Filter demultiplyFirst(T filter, FilterReplacer<T> replacer) {
/* 202 */     Expression one = replacer.getExpression1(filter);
/* 203 */     Expression two = replacer.getExpression2(filter);
/* 205 */     if (one instanceof Literal) {
/* 206 */       Literal l = (Literal)one;
/* 207 */       Object value = l.getValue();
/* 208 */       if (value instanceof java.util.Collection) {
/* 209 */         List<Filter> filters = new ArrayList<Filter>();
/* 210 */         for (Object valueElement : value)
/* 212 */           filters.add(replacer.replaceExpressions(filter, (Expression)this.ff.literal(valueElement), two)); 
/* 215 */         if (filter.getMatchAction() == MultiValuedFilter.MatchAction.ANY)
/* 216 */           return (Filter)this.ff.or(filters); 
/* 217 */         if (filter.getMatchAction() == MultiValuedFilter.MatchAction.ALL)
/* 218 */           return (Filter)this.ff.and(filters); 
/* 219 */         if (filter.getMatchAction() == MultiValuedFilter.MatchAction.ONE) {
/* 220 */           List<Filter> filters2 = new ArrayList<Filter>();
/* 221 */           for (int i = 0; i < filters.size(); i++) {
/* 222 */             List<Filter> filters3 = new ArrayList<Filter>();
/* 223 */             for (int j = 0; j < filters.size(); j++) {
/* 224 */               if (i == j) {
/* 225 */                 filters3.add(filters.get(j));
/*     */               } else {
/* 227 */                 filters3.add(this.ff.not(filters.get(j)));
/*     */               } 
/*     */             } 
/* 230 */             filters2.add(this.ff.and(filters3));
/*     */           } 
/* 232 */           return (Filter)this.ff.or(filters2);
/*     */         } 
/*     */       } 
/*     */     } 
/* 237 */     return (Filter)filter;
/*     */   }
/*     */   
/*     */   protected <T extends MultiValuedFilter> Filter demultiply(T filter, FilterReplacer<T> replacer) {
/* 249 */     Expression one = replacer.getExpression1(filter);
/* 250 */     Expression two = replacer.getExpression2(filter);
/* 252 */     if (two instanceof Literal) {
/* 253 */       Literal l = (Literal)two;
/* 254 */       Object value = l.getValue();
/* 255 */       if (value instanceof java.util.Collection) {
/* 256 */         List<Filter> filters = new ArrayList<Filter>();
/* 257 */         for (Object valueElement : value)
/* 259 */           filters.add(demultiplyFirst((MultiValuedFilter)replacer.replaceExpressions(filter, one, (Expression)this.ff.literal(valueElement)), replacer)); 
/* 262 */         if (filter.getMatchAction() == MultiValuedFilter.MatchAction.ANY)
/* 263 */           return (Filter)this.ff.or(filters); 
/* 264 */         if (filter.getMatchAction() == MultiValuedFilter.MatchAction.ALL)
/* 265 */           return (Filter)this.ff.and(filters); 
/* 266 */         if (filter.getMatchAction() == MultiValuedFilter.MatchAction.ONE) {
/* 267 */           List<Filter> filters2 = new ArrayList<Filter>();
/* 268 */           for (int i = 0; i < filters.size(); i++) {
/* 269 */             List<Filter> filters3 = new ArrayList<Filter>();
/* 270 */             for (int j = 0; j < filters.size(); j++) {
/* 271 */               if (i == j) {
/* 272 */                 filters3.add(filters.get(j));
/*     */               } else {
/* 274 */                 filters3.add(this.ff.not(filters.get(j)));
/*     */               } 
/*     */             } 
/* 277 */             filters2.add(this.ff.and(filters3));
/*     */           } 
/* 279 */           return (Filter)this.ff.or(filters2);
/*     */         } 
/*     */       } 
/*     */     } 
/* 284 */     return demultiplyFirst(filter, replacer);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/* 290 */     return super.visit(filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 295 */     return demultiply(filter, new BinaryComparisonOperatorReplacer("equal"));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 300 */     return demultiply(filter, new BinaryComparisonOperatorReplacer("notEqual"));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 305 */     return demultiply(filter, new BinaryComparisonOperatorReplacer("greater"));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 310 */     return demultiply(filter, new BinaryComparisonOperatorReplacer("greaterOrEqual"));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 315 */     return demultiply(filter, new BinaryComparisonOperatorReplacer("less"));
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 320 */     return demultiply(filter, new BinaryComparisonOperatorReplacer("lessOrEqual"));
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 325 */     return demultiply(filter, new BinarySpatialOperatorReplacer("bbox"));
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 330 */     return demultiply(filter, new FilterReplacer<Beyond>() {
/*     */           public Expression getExpression1(Beyond filter) {
/* 334 */             return filter.getExpression1();
/*     */           }
/*     */           
/*     */           public Expression getExpression2(Beyond filter) {
/* 339 */             return filter.getExpression2();
/*     */           }
/*     */           
/*     */           public Filter replaceExpressions(Beyond filter, Expression expression1, Expression expression2) {
/* 344 */             return (Filter)LiteralDemultiplyingFilterVisitor.this.ff.beyond(expression1, expression2, filter.getDistance(), filter.getDistanceUnits(), filter.getMatchAction());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 352 */     return demultiply(filter, new BinarySpatialOperatorReplacer("contains"));
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 357 */     return demultiply(filter, new BinarySpatialOperatorReplacer("crosses"));
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 362 */     return demultiply(filter, new BinarySpatialOperatorReplacer("disjoint"));
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 367 */     return demultiply(filter, new FilterReplacer<DWithin>() {
/*     */           public Expression getExpression1(DWithin filter) {
/* 371 */             return filter.getExpression1();
/*     */           }
/*     */           
/*     */           public Expression getExpression2(DWithin filter) {
/* 376 */             return filter.getExpression2();
/*     */           }
/*     */           
/*     */           public Filter replaceExpressions(DWithin filter, Expression expression1, Expression expression2) {
/* 381 */             return (Filter)LiteralDemultiplyingFilterVisitor.this.ff.dwithin(expression1, expression2, filter.getDistance(), filter.getDistanceUnits(), filter.getMatchAction());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 389 */     return demultiply(filter, new BinarySpatialOperatorReplacer("equal"));
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 394 */     return demultiply(filter, new BinarySpatialOperatorReplacer("intersects"));
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 399 */     return demultiply(filter, new BinarySpatialOperatorReplacer("overlaps"));
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 404 */     return demultiply(filter, new BinarySpatialOperatorReplacer("touches"));
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 409 */     return demultiply(filter, new BinarySpatialOperatorReplacer("within"));
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 414 */     return demultiply(after, new BinaryTemporalOperatorReplacer("after"));
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 419 */     return demultiply(anyInteracts, new BinaryTemporalOperatorReplacer("anyInteracts"));
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 424 */     return demultiply(before, new BinaryTemporalOperatorReplacer("before"));
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 429 */     return demultiply(begins, new BinaryTemporalOperatorReplacer("begins"));
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 434 */     return demultiply(begunBy, new BinaryTemporalOperatorReplacer("begunBy"));
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 439 */     return demultiply(during, new BinaryTemporalOperatorReplacer("during"));
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 444 */     return demultiply(endedBy, new BinaryTemporalOperatorReplacer("endedBy"));
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 449 */     return demultiply(ends, new BinaryTemporalOperatorReplacer("ends"));
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 454 */     return demultiply(meets, new BinaryTemporalOperatorReplacer("meets"));
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 459 */     return demultiply(metBy, new BinaryTemporalOperatorReplacer("metBy"));
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 464 */     return demultiply(overlappedBy, new BinaryTemporalOperatorReplacer("overlappedBy"));
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 469 */     return demultiply(contains, new BinaryTemporalOperatorReplacer("tcontains"));
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 474 */     return demultiply(equals, new BinaryTemporalOperatorReplacer("tequals"));
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps overlaps, Object extraData) {
/* 479 */     return demultiply(overlaps, new BinaryTemporalOperatorReplacer("toverlaps"));
/*     */   }
/*     */   
/*     */   protected static interface FilterReplacer<F extends MultiValuedFilter> {
/*     */     Expression getExpression1(F param1F);
/*     */     
/*     */     Expression getExpression2(F param1F);
/*     */     
/*     */     Filter replaceExpressions(F param1F, Expression param1Expression1, Expression param1Expression2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\LiteralDemultiplyingFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */