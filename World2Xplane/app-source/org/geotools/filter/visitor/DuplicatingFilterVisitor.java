/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory2;
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
/*     */ import org.opengis.filter.expression.InternalFunction;
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
/*     */ public class DuplicatingFilterVisitor implements FilterVisitor, ExpressionVisitor {
/*     */   protected final FilterFactory2 ff;
/*     */   
/*     */   public DuplicatingFilterVisitor() {
/* 100 */     this(CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public DuplicatingFilterVisitor(FilterFactory2 factory) {
/* 105 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   protected FilterFactory2 getFactory(Object extraData) {
/* 109 */     if (extraData instanceof FilterFactory2)
/* 110 */       return (FilterFactory2)extraData; 
/* 111 */     return this.ff;
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object extraData) {
/* 115 */     return filter;
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object extraData) {
/* 120 */     return filter;
/*     */   }
/*     */   
/*     */   Expression visit(Expression expression, Object extraData) {
/* 130 */     if (expression == null)
/* 131 */       return null; 
/* 132 */     return (Expression)expression.accept(this, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object extraData) {
/* 136 */     List<Filter> children = filter.getChildren();
/* 137 */     List<Filter> newChildren = new ArrayList<Filter>();
/* 138 */     for (Iterator<Filter> iter = children.iterator(); iter.hasNext(); ) {
/* 139 */       Filter child = iter.next();
/* 140 */       if (child != null) {
/* 141 */         Filter newChild = (Filter)child.accept(this, extraData);
/* 142 */         newChildren.add(newChild);
/*     */       } 
/*     */     } 
/* 145 */     return getFactory(extraData).and(newChildren);
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object extraData) {
/* 149 */     return getFactory(extraData).id(filter.getIdentifiers());
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object extraData) {
/* 153 */     return getFactory(extraData).not((Filter)filter.getFilter().accept(this, extraData));
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object extraData) {
/* 157 */     List<Filter> children = filter.getChildren();
/* 158 */     List<Filter> newChildren = new ArrayList<Filter>();
/* 159 */     for (Iterator<Filter> iter = children.iterator(); iter.hasNext(); ) {
/* 160 */       Filter child = iter.next();
/* 161 */       if (child != null) {
/* 162 */         Filter newChild = (Filter)child.accept(this, extraData);
/* 163 */         newChildren.add(newChild);
/*     */       } 
/*     */     } 
/* 166 */     return getFactory(extraData).or(newChildren);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object extraData) {
/* 170 */     Expression expr = visit(filter.getExpression(), extraData);
/* 171 */     Expression lower = visit(filter.getLowerBoundary(), extraData);
/* 172 */     Expression upper = visit(filter.getUpperBoundary(), extraData);
/* 173 */     return getFactory(extraData).between(expr, lower, upper, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 177 */     Expression expr1 = visit(filter.getExpression1(), extraData);
/* 178 */     Expression expr2 = visit(filter.getExpression2(), extraData);
/* 179 */     boolean matchCase = filter.isMatchingCase();
/* 180 */     return getFactory(extraData).equal(expr1, expr2, matchCase, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 184 */     Expression expr1 = visit(filter.getExpression1(), extraData);
/* 185 */     Expression expr2 = visit(filter.getExpression2(), extraData);
/* 186 */     boolean matchCase = filter.isMatchingCase();
/* 187 */     return getFactory(extraData).notEqual(expr1, expr2, matchCase, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 191 */     Expression expr1 = visit(filter.getExpression1(), extraData);
/* 192 */     Expression expr2 = visit(filter.getExpression2(), extraData);
/* 193 */     return getFactory(extraData).greater(expr1, expr2, filter.isMatchingCase(), filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 197 */     Expression expr1 = visit(filter.getExpression1(), extraData);
/* 198 */     Expression expr2 = visit(filter.getExpression2(), extraData);
/* 199 */     return getFactory(extraData).greaterOrEqual(expr1, expr2, filter.isMatchingCase(), filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 203 */     Expression expr1 = visit(filter.getExpression1(), extraData);
/* 204 */     Expression expr2 = visit(filter.getExpression2(), extraData);
/* 205 */     return getFactory(extraData).less(expr1, expr2, filter.isMatchingCase(), filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 209 */     Expression expr1 = visit(filter.getExpression1(), extraData);
/* 210 */     Expression expr2 = visit(filter.getExpression2(), extraData);
/* 211 */     return getFactory(extraData).lessOrEqual(expr1, expr2, filter.isMatchingCase(), filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object extraData) {
/* 215 */     Expression expr = visit(filter.getExpression(), extraData);
/* 216 */     String pattern = filter.getLiteral();
/* 217 */     String wildcard = filter.getWildCard();
/* 218 */     String singleChar = filter.getSingleChar();
/* 219 */     String escape = filter.getEscape();
/* 220 */     boolean matchCase = filter.isMatchingCase();
/* 221 */     return getFactory(extraData).like(expr, pattern, wildcard, singleChar, escape, matchCase, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object extraData) {
/* 225 */     Expression expr = visit(filter.getExpression(), extraData);
/* 226 */     return getFactory(extraData).isNull(expr);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object extraData) {
/* 230 */     Expression expr = visit(filter.getExpression(), extraData);
/* 231 */     return getFactory(extraData).isNil(expr, filter.getNilReason());
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/* 235 */     Expression propertyName = visit(filter.getExpression1(), extraData);
/* 237 */     if (!(filter instanceof org.opengis.filter.spatial.BBOX3D)) {
/* 238 */       double minx = filter.getMinX();
/* 239 */       double miny = filter.getMinY();
/* 240 */       double maxx = filter.getMaxX();
/* 241 */       double maxy = filter.getMaxY();
/* 242 */       String srs = filter.getSRS();
/* 243 */       return getFactory(extraData).bbox(propertyName, minx, miny, maxx, maxy, srs, filter.getMatchAction());
/*     */     } 
/* 246 */     return getFactory(extraData).bbox(propertyName, filter.getBounds());
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 250 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 251 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 252 */     double distance = filter.getDistance();
/* 253 */     String units = filter.getDistanceUnits();
/* 254 */     return getFactory(extraData).beyond(geometry1, geometry2, distance, units, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 258 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 259 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 260 */     return getFactory(extraData).contains(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 264 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 265 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 266 */     return getFactory(extraData).crosses(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 270 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 271 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 272 */     return getFactory(extraData).disjoint(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 276 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 277 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 278 */     double distance = filter.getDistance();
/* 279 */     String units = filter.getDistanceUnits();
/* 280 */     return getFactory(extraData).dwithin(geometry1, geometry2, distance, units, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 284 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 285 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 286 */     return getFactory(extraData).equal(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 290 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 291 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 292 */     return getFactory(extraData).intersects(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 296 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 297 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 298 */     return getFactory(extraData).overlaps(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 302 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 303 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 304 */     return getFactory(extraData).touches(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 308 */     Expression geometry1 = visit(filter.getExpression1(), extraData);
/* 309 */     Expression geometry2 = visit(filter.getExpression2(), extraData);
/* 310 */     return getFactory(extraData).within(geometry1, geometry2, filter.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object extraData) {
/* 314 */     return null;
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object extraData) {
/* 318 */     return expression;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object extraData) {
/* 322 */     Expression expr1 = visit(expression.getExpression1(), extraData);
/* 323 */     Expression expr2 = visit(expression.getExpression2(), extraData);
/* 324 */     return getFactory(extraData).add(expr1, expr2);
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object extraData) {
/* 328 */     Expression expr1 = visit(expression.getExpression1(), extraData);
/* 329 */     Expression expr2 = visit(expression.getExpression2(), extraData);
/* 330 */     return getFactory(extraData).divide(expr1, expr2);
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object extraData) {
/*     */     Function duplicate;
/* 334 */     List old = expression.getParameters();
/* 335 */     Expression[] args = new Expression[old.size()];
/* 336 */     int i = 0;
/* 337 */     for (Iterator<Expression> iter = old.iterator(); iter.hasNext(); i++) {
/* 338 */       Expression exp = iter.next();
/* 339 */       args[i] = visit(exp, extraData);
/*     */     } 
/* 342 */     if (expression instanceof InternalFunction) {
/* 343 */       InternalFunction internalFunction = ((InternalFunction)expression).duplicate(args);
/*     */     } else {
/* 345 */       duplicate = getFactory(extraData).function(expression.getName(), args);
/*     */     } 
/* 347 */     return duplicate;
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object extraData) {
/* 351 */     return getFactory(extraData).literal(expression.getValue());
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object extraData) {
/* 355 */     Expression expr1 = visit(expression.getExpression1(), extraData);
/* 356 */     Expression expr2 = visit(expression.getExpression2(), extraData);
/* 357 */     return getFactory(extraData).multiply(expr1, expr2);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object extraData) {
/* 362 */     return getFactory(extraData).property(expression.getPropertyName(), expression.getNamespaceContext());
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object extraData) {
/* 366 */     Expression expr1 = visit(expression.getExpression1(), extraData);
/* 367 */     Expression expr2 = visit(expression.getExpression2(), extraData);
/* 368 */     return getFactory(extraData).subtract(expr1, expr2);
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 372 */     return getFactory(extraData).after(visit(after.getExpression1(), extraData), visit(after.getExpression2(), extraData), after.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 377 */     return getFactory(extraData).anyInteracts(visit(anyInteracts.getExpression1(), extraData), visit(anyInteracts.getExpression2(), extraData), anyInteracts.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 382 */     return getFactory(extraData).before(visit(before.getExpression1(), extraData), visit(before.getExpression2(), extraData), before.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 387 */     return getFactory(extraData).begins(visit(begins.getExpression1(), extraData), visit(begins.getExpression2(), extraData), begins.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 392 */     return getFactory(extraData).begunBy(visit(begunBy.getExpression1(), extraData), visit(begunBy.getExpression2(), extraData), begunBy.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 397 */     return getFactory(extraData).during(visit(during.getExpression1(), extraData), visit(during.getExpression2(), extraData), during.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 402 */     return getFactory(extraData).endedBy(visit(endedBy.getExpression1(), extraData), visit(endedBy.getExpression2(), extraData), endedBy.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 407 */     return getFactory(extraData).ends(visit(ends.getExpression1(), extraData), visit(ends.getExpression2(), extraData), ends.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 412 */     return getFactory(extraData).meets(visit(meets.getExpression1(), extraData), visit(meets.getExpression2(), extraData), meets.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 417 */     return getFactory(extraData).metBy(visit(metBy.getExpression1(), extraData), visit(metBy.getExpression2(), extraData), metBy.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 422 */     return getFactory(extraData).overlappedBy(visit(overlappedBy.getExpression1(), extraData), visit(overlappedBy.getExpression2(), extraData), overlappedBy.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 427 */     return getFactory(extraData).tcontains(visit(contains.getExpression1(), extraData), visit(contains.getExpression2(), extraData), contains.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 432 */     return getFactory(extraData).tequals(visit(equals.getExpression1(), extraData), visit(equals.getExpression2(), extraData), equals.getMatchAction());
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 437 */     return getFactory(extraData).tcontains(visit(contains.getExpression1(), extraData), visit(contains.getExpression2(), extraData), contains.getMatchAction());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\DuplicatingFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */