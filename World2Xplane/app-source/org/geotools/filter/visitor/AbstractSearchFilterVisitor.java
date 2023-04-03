/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNil;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
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
/*     */ public abstract class AbstractSearchFilterVisitor implements FilterVisitor, ExpressionVisitor {
/*     */   protected boolean found(Object data) {
/*  80 */     return (data != null);
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object data) {
/*  84 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object data) {
/*  88 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object data) {
/*  92 */     if (found(data))
/*  93 */       return data; 
/*  95 */     if (filter.getChildren() != null)
/*  96 */       for (Filter child : filter.getChildren()) {
/*  97 */         data = child.accept(this, data);
/*  98 */         if (found(data))
/*  99 */           return data; 
/*     */       }  
/* 103 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object data) {
/* 107 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object data) {
/* 111 */     Filter child = filter.getFilter();
/* 112 */     if (child != null)
/* 113 */       data = child.accept(this, data); 
/* 115 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object data) {
/* 119 */     if (found(data))
/* 120 */       return data; 
/* 122 */     if (filter.getChildren() != null) {
/* 123 */       for (Filter child : filter.getChildren())
/* 124 */         data = child.accept(this, data); 
/* 126 */       if (found(data))
/* 127 */         return data; 
/*     */     } 
/* 130 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object data) {
/* 134 */     data = filter.getLowerBoundary().accept(this, data);
/* 135 */     if (found(data))
/* 135 */       return data; 
/* 137 */     data = filter.getExpression().accept(this, data);
/* 138 */     if (found(data))
/* 138 */       return data; 
/* 140 */     data = filter.getUpperBoundary().accept(this, data);
/* 141 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object data) {
/* 145 */     data = filter.getExpression1().accept(this, data);
/* 146 */     if (found(data))
/* 146 */       return data; 
/* 148 */     data = filter.getExpression2().accept(this, data);
/* 150 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object data) {
/* 154 */     data = filter.getExpression1().accept(this, data);
/* 155 */     if (found(data))
/* 155 */       return data; 
/* 157 */     data = filter.getExpression2().accept(this, data);
/* 159 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object data) {
/* 163 */     data = filter.getExpression1().accept(this, data);
/* 164 */     if (found(data))
/* 164 */       return data; 
/* 165 */     data = filter.getExpression2().accept(this, data);
/* 167 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object data) {
/* 171 */     data = filter.getExpression1().accept(this, data);
/* 172 */     if (found(data))
/* 172 */       return data; 
/* 173 */     data = filter.getExpression2().accept(this, data);
/* 175 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object data) {
/* 179 */     data = filter.getExpression1().accept(this, data);
/* 180 */     if (found(data))
/* 180 */       return data; 
/* 181 */     data = filter.getExpression2().accept(this, data);
/* 183 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object data) {
/* 187 */     data = filter.getExpression1().accept(this, data);
/* 188 */     if (found(data))
/* 188 */       return data; 
/* 189 */     data = filter.getExpression2().accept(this, data);
/* 191 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object data) {
/* 195 */     data = filter.getExpression().accept(this, data);
/* 197 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object data) {
/* 201 */     data = filter.getExpression().accept(this, data);
/* 202 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object data) {
/* 206 */     data = filter.getExpression().accept(this, data);
/* 207 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object data) {
/* 211 */     data = filter.getExpression1().accept(this, data);
/* 212 */     if (found(data))
/* 212 */       return data; 
/* 213 */     data = filter.getExpression2().accept(this, data);
/* 214 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/* 218 */     data = filter.getExpression1().accept(this, data);
/* 219 */     if (found(data))
/* 219 */       return data; 
/* 220 */     data = filter.getExpression2().accept(this, data);
/* 221 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/* 225 */     data = filter.getExpression1().accept(this, data);
/* 226 */     if (found(data))
/* 226 */       return data; 
/* 227 */     data = filter.getExpression2().accept(this, data);
/* 228 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/* 232 */     data = filter.getExpression1().accept(this, data);
/* 233 */     if (found(data))
/* 233 */       return data; 
/* 234 */     data = filter.getExpression2().accept(this, data);
/* 235 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/* 239 */     data = filter.getExpression1().accept(this, data);
/* 240 */     if (found(data))
/* 240 */       return data; 
/* 241 */     data = filter.getExpression2().accept(this, data);
/* 242 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/* 246 */     data = filter.getExpression1().accept(this, data);
/* 247 */     if (found(data))
/* 247 */       return data; 
/* 248 */     data = filter.getExpression2().accept(this, data);
/* 249 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/* 253 */     data = filter.getExpression1().accept(this, data);
/* 254 */     if (found(data))
/* 254 */       return data; 
/* 255 */     data = filter.getExpression2().accept(this, data);
/* 256 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/* 260 */     data = filter.getExpression1().accept(this, data);
/* 261 */     if (found(data))
/* 261 */       return data; 
/* 262 */     data = filter.getExpression2().accept(this, data);
/* 264 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/* 268 */     data = filter.getExpression1().accept(this, data);
/* 269 */     if (found(data))
/* 269 */       return data; 
/* 270 */     data = filter.getExpression2().accept(this, data);
/* 272 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 276 */     data = filter.getExpression1().accept(this, data);
/* 277 */     if (found(data))
/* 277 */       return data; 
/* 278 */     data = filter.getExpression2().accept(this, data);
/* 280 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 284 */     data = filter.getExpression1().accept(this, data);
/* 285 */     if (found(data))
/* 285 */       return data; 
/* 286 */     data = filter.getExpression2().accept(this, data);
/* 288 */     return data;
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object data) {
/* 292 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object data) {
/* 296 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object data) {
/* 300 */     data = expression.getExpression1().accept(this, data);
/* 301 */     if (found(data))
/* 301 */       return data; 
/* 302 */     data = expression.getExpression2().accept(this, data);
/* 303 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object data) {
/* 307 */     data = expression.getExpression1().accept(this, data);
/* 308 */     if (found(data))
/* 308 */       return data; 
/* 309 */     data = expression.getExpression2().accept(this, data);
/* 310 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/* 314 */     if (found(data))
/* 314 */       return data; 
/* 315 */     if (expression.getParameters() != null)
/* 316 */       for (Expression parameter : expression.getParameters()) {
/* 317 */         data = parameter.accept(this, data);
/* 318 */         if (found(data))
/* 318 */           return data; 
/*     */       }  
/* 321 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object data) {
/* 325 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object data) {
/* 329 */     data = expression.getExpression1().accept(this, data);
/* 330 */     if (found(data))
/* 330 */       return data; 
/* 331 */     data = expression.getExpression2().accept(this, data);
/* 332 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/* 336 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object data) {
/* 340 */     data = expression.getExpression1().accept(this, data);
/* 341 */     if (found(data))
/* 341 */       return data; 
/* 342 */     data = expression.getExpression2().accept(this, data);
/* 343 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 347 */     return visit((BinaryTemporalOperator)after, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 351 */     return visit((BinaryTemporalOperator)anyInteracts, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 355 */     return visit((BinaryTemporalOperator)before, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 359 */     return visit((BinaryTemporalOperator)begins, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 363 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 367 */     return visit((BinaryTemporalOperator)during, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 371 */     return visit((BinaryTemporalOperator)endedBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 375 */     return visit((BinaryTemporalOperator)ends, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 379 */     return visit((BinaryTemporalOperator)meets, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 383 */     return visit((BinaryTemporalOperator)metBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 387 */     return visit((BinaryTemporalOperator)overlappedBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 391 */     return visit((BinaryTemporalOperator)contains, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 395 */     return visit((BinaryTemporalOperator)equals, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 399 */     return visit((BinaryTemporalOperator)contains, extraData);
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/* 403 */     data = filter.getExpression1().accept(this, data);
/* 404 */     if (found(data))
/* 404 */       return data; 
/* 405 */     data = filter.getExpression2().accept(this, data);
/* 406 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\AbstractSearchFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */