/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public abstract class DefaultFilterVisitor implements FilterVisitor, ExpressionVisitor {
/*     */   public Object visit(ExcludeFilter filter, Object data) {
/* 107 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object data) {
/* 111 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object data) {
/* 115 */     List<Filter> childList = filter.getChildren();
/* 116 */     if (childList != null)
/* 117 */       for (Filter child : childList) {
/* 118 */         if (child == null)
/*     */           continue; 
/* 119 */         data = child.accept(this, data);
/*     */       }  
/* 122 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object data) {
/* 126 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object data) {
/* 130 */     Filter child = filter.getFilter();
/* 131 */     if (child != null)
/* 132 */       data = child.accept(this, data); 
/* 134 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object data) {
/* 138 */     List<Filter> childList = filter.getChildren();
/* 139 */     if (childList != null)
/* 140 */       for (Filter child : childList) {
/* 141 */         if (child == null)
/*     */           continue; 
/* 142 */         data = child.accept(this, data);
/*     */       }  
/* 145 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object data) {
/* 149 */     data = filter.getLowerBoundary().accept(this, data);
/* 150 */     data = filter.getExpression().accept(this, data);
/* 151 */     data = filter.getUpperBoundary().accept(this, data);
/* 152 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object data) {
/* 156 */     data = filter.getExpression1().accept(this, data);
/* 157 */     data = filter.getExpression2().accept(this, data);
/* 159 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object data) {
/* 163 */     data = filter.getExpression1().accept(this, data);
/* 164 */     data = filter.getExpression2().accept(this, data);
/* 166 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object data) {
/* 170 */     data = filter.getExpression1().accept(this, data);
/* 171 */     data = filter.getExpression2().accept(this, data);
/* 173 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object data) {
/* 177 */     data = filter.getExpression1().accept(this, data);
/* 178 */     data = filter.getExpression2().accept(this, data);
/* 180 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object data) {
/* 184 */     data = filter.getExpression1().accept(this, data);
/* 185 */     data = filter.getExpression2().accept(this, data);
/* 187 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object data) {
/* 191 */     data = filter.getExpression1().accept(this, data);
/* 192 */     data = filter.getExpression2().accept(this, data);
/* 194 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object data) {
/* 198 */     data = filter.getExpression().accept(this, data);
/* 200 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object data) {
/* 204 */     data = filter.getExpression().accept(this, data);
/* 205 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object data) {
/* 209 */     data = filter.getExpression().accept(this, data);
/* 210 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object data) {
/* 214 */     data = filter.getExpression1().accept(this, data);
/* 215 */     data = filter.getExpression2().accept(this, data);
/* 216 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/* 220 */     data = filter.getExpression1().accept(this, data);
/* 221 */     data = filter.getExpression2().accept(this, data);
/* 222 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/* 226 */     data = filter.getExpression1().accept(this, data);
/* 227 */     data = filter.getExpression2().accept(this, data);
/* 228 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/* 232 */     data = filter.getExpression1().accept(this, data);
/* 233 */     data = filter.getExpression2().accept(this, data);
/* 234 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/* 238 */     data = filter.getExpression1().accept(this, data);
/* 239 */     data = filter.getExpression2().accept(this, data);
/* 240 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/* 244 */     data = filter.getExpression1().accept(this, data);
/* 245 */     data = filter.getExpression2().accept(this, data);
/* 246 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/* 250 */     data = filter.getExpression1().accept(this, data);
/* 251 */     data = filter.getExpression2().accept(this, data);
/* 252 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/* 256 */     data = filter.getExpression1().accept(this, data);
/* 257 */     data = filter.getExpression2().accept(this, data);
/* 259 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/* 263 */     data = filter.getExpression1().accept(this, data);
/* 264 */     data = filter.getExpression2().accept(this, data);
/* 266 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 270 */     data = filter.getExpression1().accept(this, data);
/* 271 */     data = filter.getExpression2().accept(this, data);
/* 273 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 277 */     data = filter.getExpression1().accept(this, data);
/* 278 */     data = filter.getExpression2().accept(this, data);
/* 280 */     return data;
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object data) {
/* 284 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object data) {
/* 288 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object data) {
/* 292 */     data = expression.getExpression1().accept(this, data);
/* 293 */     data = expression.getExpression2().accept(this, data);
/* 294 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object data) {
/* 298 */     data = expression.getExpression1().accept(this, data);
/* 299 */     data = expression.getExpression2().accept(this, data);
/* 300 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/* 304 */     if (expression.getParameters() != null)
/* 305 */       for (Expression parameter : expression.getParameters())
/* 306 */         data = parameter.accept(this, data);  
/* 309 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object data) {
/* 313 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object data) {
/* 317 */     data = expression.getExpression1().accept(this, data);
/* 318 */     data = expression.getExpression2().accept(this, data);
/* 319 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/* 323 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object data) {
/* 327 */     data = expression.getExpression1().accept(this, data);
/* 328 */     data = expression.getExpression2().accept(this, data);
/* 329 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object data) {
/* 333 */     data = after.getExpression1().accept(this, data);
/* 334 */     data = after.getExpression2().accept(this, data);
/* 335 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object data) {
/* 339 */     data = anyInteracts.getExpression1().accept(this, data);
/* 340 */     data = anyInteracts.getExpression2().accept(this, data);
/* 341 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object data) {
/* 345 */     data = before.getExpression1().accept(this, data);
/* 346 */     data = before.getExpression2().accept(this, data);
/* 347 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object data) {
/* 351 */     data = begins.getExpression1().accept(this, data);
/* 352 */     data = begins.getExpression2().accept(this, data);
/* 353 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object data) {
/* 357 */     data = begunBy.getExpression1().accept(this, data);
/* 358 */     data = begunBy.getExpression2().accept(this, data);
/* 359 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object data) {
/* 363 */     data = during.getExpression1().accept(this, data);
/* 364 */     data = during.getExpression2().accept(this, data);
/* 365 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object data) {
/* 369 */     data = endedBy.getExpression1().accept(this, data);
/* 370 */     data = endedBy.getExpression2().accept(this, data);
/* 371 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object data) {
/* 375 */     data = ends.getExpression1().accept(this, data);
/* 376 */     data = ends.getExpression2().accept(this, data);
/* 377 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object data) {
/* 381 */     data = meets.getExpression1().accept(this, data);
/* 382 */     data = meets.getExpression2().accept(this, data);
/* 383 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object data) {
/* 387 */     data = metBy.getExpression1().accept(this, data);
/* 388 */     data = metBy.getExpression2().accept(this, data);
/* 389 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object data) {
/* 393 */     data = overlappedBy.getExpression1().accept(this, data);
/* 394 */     data = overlappedBy.getExpression2().accept(this, data);
/* 395 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object data) {
/* 399 */     data = contains.getExpression1().accept(this, data);
/* 400 */     data = contains.getExpression2().accept(this, data);
/* 401 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object data) {
/* 405 */     data = equals.getExpression1().accept(this, data);
/* 406 */     data = equals.getExpression2().accept(this, data);
/* 407 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object data) {
/* 411 */     data = contains.getExpression1().accept(this, data);
/* 412 */     data = contains.getExpression2().accept(this, data);
/* 413 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\DefaultFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */