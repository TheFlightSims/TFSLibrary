/*      */ package org.geotools.filter;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.geotools.data.Parameter;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.filter.capability.ArithmeticOperatorsImpl;
/*      */ import org.geotools.filter.capability.ComparisonOperatorsImpl;
/*      */ import org.geotools.filter.capability.FilterCapabilitiesImpl;
/*      */ import org.geotools.filter.capability.FunctionNameImpl;
/*      */ import org.geotools.filter.capability.FunctionsImpl;
/*      */ import org.geotools.filter.capability.IdCapabilitiesImpl;
/*      */ import org.geotools.filter.capability.OperatorImpl;
/*      */ import org.geotools.filter.capability.ScalarCapabilitiesImpl;
/*      */ import org.geotools.filter.capability.SpatialCapabiltiesImpl;
/*      */ import org.geotools.filter.capability.SpatialOperatorImpl;
/*      */ import org.geotools.filter.capability.SpatialOperatorsImpl;
/*      */ import org.geotools.filter.capability.TemporalCapabilitiesImpl;
/*      */ import org.geotools.filter.capability.TemporalOperatorImpl;
/*      */ import org.geotools.filter.expression.AddImpl;
/*      */ import org.geotools.filter.expression.DivideImpl;
/*      */ import org.geotools.filter.expression.MultiplyImpl;
/*      */ import org.geotools.filter.expression.SubtractImpl;
/*      */ import org.geotools.filter.identity.FeatureIdImpl;
/*      */ import org.geotools.filter.identity.FeatureIdVersionedImpl;
/*      */ import org.geotools.filter.identity.GmlObjectIdImpl;
/*      */ import org.geotools.filter.identity.ResourceIdImpl;
/*      */ import org.geotools.filter.spatial.BBOX3DImpl;
/*      */ import org.geotools.filter.spatial.BBOXImpl;
/*      */ import org.geotools.filter.spatial.BeyondImpl;
/*      */ import org.geotools.filter.spatial.ContainsImpl;
/*      */ import org.geotools.filter.spatial.CrossesImpl;
/*      */ import org.geotools.filter.spatial.DWithinImpl;
/*      */ import org.geotools.filter.spatial.DisjointImpl;
/*      */ import org.geotools.filter.spatial.EqualsImpl;
/*      */ import org.geotools.filter.spatial.IntersectsImpl;
/*      */ import org.geotools.filter.spatial.OverlapsImpl;
/*      */ import org.geotools.filter.spatial.TouchesImpl;
/*      */ import org.geotools.filter.spatial.WithinImpl;
/*      */ import org.geotools.filter.temporal.AfterImpl;
/*      */ import org.geotools.filter.temporal.AnyInteractsImpl;
/*      */ import org.geotools.filter.temporal.BeforeImpl;
/*      */ import org.geotools.filter.temporal.BeginsImpl;
/*      */ import org.geotools.filter.temporal.BegunByImpl;
/*      */ import org.geotools.filter.temporal.DuringImpl;
/*      */ import org.geotools.filter.temporal.EndedByImpl;
/*      */ import org.geotools.filter.temporal.EndsImpl;
/*      */ import org.geotools.filter.temporal.MeetsImpl;
/*      */ import org.geotools.filter.temporal.MetByImpl;
/*      */ import org.geotools.filter.temporal.OverlappedByImpl;
/*      */ import org.geotools.filter.temporal.TContainsImpl;
/*      */ import org.geotools.filter.temporal.TEqualsImpl;
/*      */ import org.geotools.filter.temporal.TOverlapsImpl;
/*      */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*      */ import org.geotools.geometry.jts.ReferencedEnvelope3D;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.AttributeDescriptor;
/*      */ import org.opengis.feature.type.Name;
/*      */ import org.opengis.filter.And;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.Id;
/*      */ import org.opengis.filter.MultiValuedFilter;
/*      */ import org.opengis.filter.Not;
/*      */ import org.opengis.filter.Or;
/*      */ import org.opengis.filter.PropertyIsBetween;
/*      */ import org.opengis.filter.PropertyIsEqualTo;
/*      */ import org.opengis.filter.PropertyIsGreaterThan;
/*      */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*      */ import org.opengis.filter.PropertyIsLessThan;
/*      */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*      */ import org.opengis.filter.PropertyIsLike;
/*      */ import org.opengis.filter.PropertyIsNil;
/*      */ import org.opengis.filter.PropertyIsNotEqualTo;
/*      */ import org.opengis.filter.PropertyIsNull;
/*      */ import org.opengis.filter.capability.ArithmeticOperators;
/*      */ import org.opengis.filter.capability.ComparisonOperators;
/*      */ import org.opengis.filter.capability.FilterCapabilities;
/*      */ import org.opengis.filter.capability.FunctionName;
/*      */ import org.opengis.filter.capability.Functions;
/*      */ import org.opengis.filter.capability.GeometryOperand;
/*      */ import org.opengis.filter.capability.IdCapabilities;
/*      */ import org.opengis.filter.capability.Operator;
/*      */ import org.opengis.filter.capability.ScalarCapabilities;
/*      */ import org.opengis.filter.capability.SpatialCapabilities;
/*      */ import org.opengis.filter.capability.SpatialOperator;
/*      */ import org.opengis.filter.capability.SpatialOperators;
/*      */ import org.opengis.filter.capability.TemporalCapabilities;
/*      */ import org.opengis.filter.capability.TemporalOperator;
/*      */ import org.opengis.filter.expression.Add;
/*      */ import org.opengis.filter.expression.Divide;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.Function;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.filter.expression.Multiply;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ import org.opengis.filter.expression.Subtract;
/*      */ import org.opengis.filter.identity.FeatureId;
/*      */ import org.opengis.filter.identity.GmlObjectId;
/*      */ import org.opengis.filter.identity.ResourceId;
/*      */ import org.opengis.filter.identity.Version;
/*      */ import org.opengis.filter.sort.SortBy;
/*      */ import org.opengis.filter.sort.SortOrder;
/*      */ import org.opengis.filter.spatial.BBOX;
/*      */ import org.opengis.filter.spatial.BBOX3D;
/*      */ import org.opengis.filter.spatial.Beyond;
/*      */ import org.opengis.filter.spatial.Contains;
/*      */ import org.opengis.filter.spatial.Crosses;
/*      */ import org.opengis.filter.spatial.DWithin;
/*      */ import org.opengis.filter.spatial.Disjoint;
/*      */ import org.opengis.filter.spatial.Equals;
/*      */ import org.opengis.filter.spatial.Intersects;
/*      */ import org.opengis.filter.spatial.Overlaps;
/*      */ import org.opengis.filter.spatial.Touches;
/*      */ import org.opengis.filter.spatial.Within;
/*      */ import org.opengis.filter.temporal.After;
/*      */ import org.opengis.filter.temporal.AnyInteracts;
/*      */ import org.opengis.filter.temporal.Before;
/*      */ import org.opengis.filter.temporal.Begins;
/*      */ import org.opengis.filter.temporal.BegunBy;
/*      */ import org.opengis.filter.temporal.During;
/*      */ import org.opengis.filter.temporal.EndedBy;
/*      */ import org.opengis.filter.temporal.Ends;
/*      */ import org.opengis.filter.temporal.Meets;
/*      */ import org.opengis.filter.temporal.MetBy;
/*      */ import org.opengis.filter.temporal.OverlappedBy;
/*      */ import org.opengis.filter.temporal.TContains;
/*      */ import org.opengis.filter.temporal.TEquals;
/*      */ import org.opengis.filter.temporal.TOverlaps;
/*      */ import org.opengis.geometry.BoundingBox;
/*      */ import org.opengis.geometry.BoundingBox3D;
/*      */ import org.opengis.geometry.Geometry;
/*      */ import org.opengis.parameter.Parameter;
/*      */ import org.opengis.util.InternationalString;
/*      */ import org.xml.sax.helpers.NamespaceSupport;
/*      */ 
/*      */ public class FilterFactoryImpl implements FilterFactory {
/*      */   public FilterFactoryImpl() {
/*  185 */     this(null);
/*      */   }
/*      */   
/*  188 */   private FunctionFinder functionFinder = new FunctionFinder(null);
/*      */   
/*      */   public FilterFactoryImpl(Hints hints) {}
/*      */   
/*      */   public FeatureId featureId(String id) {
/*  192 */     return (FeatureId)new FeatureIdImpl(id);
/*      */   }
/*      */   
/*      */   public GmlObjectId gmlObjectId(String id) {
/*  196 */     return (GmlObjectId)new GmlObjectIdImpl(id);
/*      */   }
/*      */   
/*      */   public FeatureId featureId(String fid, String featureVersion) {
/*  201 */     return (FeatureId)new FeatureIdVersionedImpl(fid, featureVersion);
/*      */   }
/*      */   
/*      */   public ResourceId resourceId(String fid, String featureVersion, Version version) {
/*  206 */     return (ResourceId)new ResourceIdImpl(fid, featureVersion, version);
/*      */   }
/*      */   
/*      */   public ResourceId resourceId(String fid, Date startTime, Date endTime) {
/*  211 */     return (ResourceId)new ResourceIdImpl(fid, startTime, endTime);
/*      */   }
/*      */   
/*      */   public And and(Filter f, Filter g) {
/*  215 */     List<Filter> list = new ArrayList(2);
/*  216 */     list.add(f);
/*  217 */     list.add(g);
/*  218 */     return new AndImpl((FilterFactory)this, list);
/*      */   }
/*      */   
/*      */   public And and(List filters) {
/*  222 */     return new AndImpl((FilterFactory)this, filters);
/*      */   }
/*      */   
/*      */   public Or or(Filter f, Filter g) {
/*  226 */     List<Filter> list = new ArrayList(2);
/*  227 */     list.add(f);
/*  228 */     list.add(g);
/*  229 */     return new OrImpl((FilterFactory)this, list);
/*      */   }
/*      */   
/*      */   public Or or(List filters) {
/*  233 */     return new OrImpl((FilterFactory)this, filters);
/*      */   }
/*      */   
/*      */   public Not not(Filter filter) {
/*  238 */     return new NotImpl((FilterFactory)this, filter);
/*      */   }
/*      */   
/*      */   public Id id(Set id) {
/*  242 */     return new FidFilterImpl(id);
/*      */   }
/*      */   
/*      */   public Id id(FeatureId... fids) {
/*  246 */     Set<FeatureId> selection = new HashSet<FeatureId>();
/*  247 */     for (FeatureId featureId : fids) {
/*  248 */       if (featureId != null)
/*  249 */         selection.add(featureId); 
/*      */     } 
/*  251 */     return id(selection);
/*      */   }
/*      */   
/*      */   public PropertyName property(String name) {
/*  255 */     return new AttributeExpressionImpl(name);
/*      */   }
/*      */   
/*      */   public PropertyIsBetween between(Expression expr, Expression lower, Expression upper) {
/*  260 */     return new IsBetweenImpl((FilterFactory)this, lower, expr, upper);
/*      */   }
/*      */   
/*      */   public PropertyIsBetween between(Expression expr, Expression lower, Expression upper, MultiValuedFilter.MatchAction matchAction) {
/*  265 */     return new IsBetweenImpl((FilterFactory)this, lower, expr, upper, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsEqualTo equals(Expression expr1, Expression expr2) {
/*  269 */     return equal(expr1, expr2, true);
/*      */   }
/*      */   
/*      */   public PropertyIsEqualTo equal(Expression expr1, Expression expr2, boolean matchCase) {
/*  273 */     return new IsEqualsToImpl((FilterFactory)this, expr1, expr2, matchCase);
/*      */   }
/*      */   
/*      */   public PropertyIsEqualTo equal(Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  278 */     return new IsEqualsToImpl((FilterFactory)this, expr1, expr2, matchCase, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsNotEqualTo notEqual(Expression expr1, Expression expr2) {
/*  282 */     return notEqual(expr1, expr2, false);
/*      */   }
/*      */   
/*      */   public PropertyIsNotEqualTo notEqual(Expression expr1, Expression expr2, boolean matchCase) {
/*  286 */     return new IsNotEqualToImpl((FilterFactory)this, expr1, expr2, matchCase);
/*      */   }
/*      */   
/*      */   public PropertyIsNotEqualTo notEqual(Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  291 */     return new IsNotEqualToImpl((FilterFactory)this, expr1, expr2, matchCase, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsGreaterThan greater(Expression expr1, Expression expr2) {
/*  295 */     return greater(expr1, expr2, false);
/*      */   }
/*      */   
/*      */   public PropertyIsGreaterThan greater(Expression expr1, Expression expr2, boolean matchCase) {
/*  299 */     return new IsGreaterThanImpl((FilterFactory)this, expr1, expr2);
/*      */   }
/*      */   
/*      */   public PropertyIsGreaterThan greater(Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  304 */     return new IsGreaterThanImpl((FilterFactory)this, expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsGreaterThanOrEqualTo greaterOrEqual(Expression expr1, Expression expr2) {
/*  309 */     return greaterOrEqual(expr1, expr2, false);
/*      */   }
/*      */   
/*      */   public PropertyIsGreaterThanOrEqualTo greaterOrEqual(Expression expr1, Expression expr2, boolean matchCase) {
/*  314 */     return new IsGreaterThanOrEqualToImpl((FilterFactory)this, expr1, expr2, matchCase);
/*      */   }
/*      */   
/*      */   public PropertyIsGreaterThanOrEqualTo greaterOrEqual(Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  319 */     return new IsGreaterThanOrEqualToImpl((FilterFactory)this, expr1, expr2, matchCase, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsLessThan less(Expression expr1, Expression expr2) {
/*  323 */     return less(expr1, expr2, false);
/*      */   }
/*      */   
/*      */   public PropertyIsLessThan less(Expression expr1, Expression expr2, boolean matchCase) {
/*  328 */     return new IsLessThenImpl((FilterFactory)this, expr1, expr2, matchCase);
/*      */   }
/*      */   
/*      */   public PropertyIsLessThan less(Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  333 */     return new IsLessThenImpl((FilterFactory)this, expr1, expr2, matchCase, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsLessThanOrEqualTo lessOrEqual(Expression expr1, Expression expr2) {
/*  338 */     return lessOrEqual(expr1, expr2, false);
/*      */   }
/*      */   
/*      */   public PropertyIsLessThanOrEqualTo lessOrEqual(Expression expr1, Expression expr2, boolean matchCase) {
/*  343 */     return new IsLessThenOrEqualToImpl((FilterFactory)this, expr1, expr2, false);
/*      */   }
/*      */   
/*      */   public PropertyIsLessThanOrEqualTo lessOrEqual(Expression expr1, Expression expr2, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  348 */     return new IsLessThenOrEqualToImpl((FilterFactory)this, expr1, expr2, false, matchAction);
/*      */   }
/*      */   
/*      */   public PropertyIsLike like(Expression expr, String pattern) {
/*  352 */     return like(expr, pattern, "*", "?", "\\");
/*      */   }
/*      */   
/*      */   public PropertyIsLike like(Expression expr, String pattern, String wildcard, String singleChar, String escape) {
/*  357 */     return like(expr, pattern, wildcard, singleChar, escape, false);
/*      */   }
/*      */   
/*      */   public PropertyIsLike like(Expression expr, String pattern, String wildcard, String singleChar, String escape, boolean matchCase) {
/*  361 */     LikeFilterImpl filter = new LikeFilterImpl();
/*  362 */     filter.setExpression(expr);
/*  363 */     filter.setPattern(pattern, wildcard, singleChar, escape);
/*  364 */     filter.setMatchingCase(matchCase);
/*  366 */     return filter;
/*      */   }
/*      */   
/*      */   public PropertyIsLike like(Expression expr, String pattern, String wildcard, String singleChar, String escape, boolean matchCase, MultiValuedFilter.MatchAction matchAction) {
/*  371 */     LikeFilterImpl filter = new LikeFilterImpl(matchAction);
/*  372 */     filter.setExpression(expr);
/*  373 */     filter.setPattern(pattern, wildcard, singleChar, escape);
/*  374 */     filter.setMatchingCase(matchCase);
/*  376 */     return filter;
/*      */   }
/*      */   
/*      */   public PropertyIsNull isNull(Expression expr) {
/*  383 */     return new IsNullImpl((FilterFactory)this, expr);
/*      */   }
/*      */   
/*      */   public PropertyIsNil isNil(Expression expr, Object nilReason) {
/*  387 */     return new IsNilImpl((FilterFactory)this, expr, nilReason);
/*      */   }
/*      */   
/*      */   public BBOX bbox(String propertyName, double minx, double miny, double maxx, double maxy, String srs) {
/*  403 */     PropertyName name = property(propertyName);
/*  404 */     return bbox((Expression)name, minx, miny, maxx, maxy, srs);
/*      */   }
/*      */   
/*      */   public BBOX bbox(Expression geometry, Expression bounds) {
/*  408 */     return (BBOX)new BBOXImpl((FilterFactory)this, geometry, bounds);
/*      */   }
/*      */   
/*      */   public BBOX bbox(Expression geometry, BoundingBox bounds) {
/*  412 */     if (bounds instanceof BoundingBox3D)
/*  413 */       return (BBOX)bbox(geometry, (BoundingBox3D)bounds); 
/*  415 */     return (BBOX)bbox2d(geometry, bounds, MultiValuedFilter.MatchAction.ANY);
/*      */   }
/*      */   
/*      */   public BBOX bbox(Expression geometry, BoundingBox bounds, MultiValuedFilter.MatchAction matchAction) {
/*  420 */     if (bounds instanceof BoundingBox3D)
/*  421 */       return (BBOX)bbox(geometry, (BoundingBox3D)bounds); 
/*  423 */     return (BBOX)bbox2d(geometry, bounds, matchAction);
/*      */   }
/*      */   
/*      */   private BBOXImpl bbox2d(Expression e, BoundingBox bounds, MultiValuedFilter.MatchAction matchAction) {
/*  429 */     PropertyName name = null;
/*  430 */     if (e instanceof PropertyName) {
/*  431 */       name = (PropertyName)e;
/*      */     } else {
/*  434 */       throw new IllegalArgumentException();
/*      */     } 
/*  437 */     Literal bbox = null;
/*      */     try {
/*  439 */       bbox = createBBoxExpression((Envelope)ReferencedEnvelope.reference(bounds));
/*  441 */     } catch (IllegalFilterException ife) {
/*  442 */       (new IllegalArgumentException()).initCause(ife);
/*      */     } 
/*  445 */     return new BBOXImpl((FilterFactory)this, (Expression)name, (Expression)bbox, matchAction);
/*      */   }
/*      */   
/*      */   public BBOX bbox(String propertyName, double minx, double miny, double maxx, double maxy, String srs, MultiValuedFilter.MatchAction matchAction) {
/*  450 */     PropertyName name = property(propertyName);
/*  451 */     return bbox((Expression)name, minx, miny, maxx, maxy, srs, matchAction);
/*      */   }
/*      */   
/*      */   public BBOX bbox(Expression geometry, double minx, double miny, double maxx, double maxy, String srs) {
/*  455 */     return bbox(geometry, minx, miny, maxx, maxy, srs, MultiValuedFilter.MatchAction.ANY);
/*      */   }
/*      */   
/*      */   public BBOX bbox(Expression e, double minx, double miny, double maxx, double maxy, String srs, MultiValuedFilter.MatchAction matchAction) {
/*  461 */     BBOXImpl box = bbox2d(e, (BoundingBox)new ReferencedEnvelope(minx, maxx, miny, maxy, null), matchAction);
/*  462 */     box.setSRS(srs);
/*  464 */     return (BBOX)box;
/*      */   }
/*      */   
/*      */   public BBOX3D bbox(String propertyName, BoundingBox3D env) {
/*  468 */     return bbox((Expression)property(propertyName), env, MultiValuedFilter.MatchAction.ANY);
/*      */   }
/*      */   
/*      */   public BBOX3D bbox(String propertyName, BoundingBox3D env, MultiValuedFilter.MatchAction matchAction) {
/*  472 */     return bbox((Expression)property(propertyName), env, matchAction);
/*      */   }
/*      */   
/*      */   public BBOX3D bbox(Expression geometry, BoundingBox3D env) {
/*  476 */     return bbox(geometry, env, MultiValuedFilter.MatchAction.ANY);
/*      */   }
/*      */   
/*      */   public BBOX3D bbox(Expression e, BoundingBox3D env, MultiValuedFilter.MatchAction matchAction) {
/*  480 */     PropertyName name = null;
/*  481 */     if (e instanceof PropertyName) {
/*  482 */       name = (PropertyName)e;
/*      */     } else {
/*  485 */       throw new IllegalArgumentException();
/*      */     } 
/*  488 */     return (BBOX3D)new BBOX3DImpl(name, new ReferencedEnvelope3D(env), (FilterFactory)this);
/*      */   }
/*      */   
/*      */   public Beyond beyond(String propertyName, Geometry geometry, double distance, String units) {
/*  494 */     PropertyName name = property(propertyName);
/*  495 */     Literal geom = literal(geometry);
/*  497 */     return beyond((Expression)name, (Expression)geom, distance, units);
/*      */   }
/*      */   
/*      */   public Beyond beyond(String propertyName, Geometry geometry, double distance, String units, MultiValuedFilter.MatchAction matchAction) {
/*  502 */     PropertyName name = property(propertyName);
/*  503 */     Literal geom = literal(geometry);
/*  505 */     return beyond((Expression)name, (Expression)geom, distance, units, matchAction);
/*      */   }
/*      */   
/*      */   public Beyond beyond(Expression geometry1, Expression geometry2, double distance, String units) {
/*  512 */     BeyondImpl beyond = new BeyondImpl((FilterFactory)this, geometry1, geometry2);
/*  513 */     beyond.setDistance(distance);
/*  514 */     beyond.setUnits(units);
/*  516 */     return (Beyond)beyond;
/*      */   }
/*      */   
/*      */   public Beyond beyond(Expression geometry1, Expression geometry2, double distance, String units, MultiValuedFilter.MatchAction matchAction) {
/*  521 */     BeyondImpl beyond = new BeyondImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*  522 */     beyond.setDistance(distance);
/*  523 */     beyond.setUnits(units);
/*  525 */     return (Beyond)beyond;
/*      */   }
/*      */   
/*      */   public Contains contains(String propertyName, Geometry geometry) {
/*  529 */     PropertyName name = property(propertyName);
/*  530 */     Literal geom = literal(geometry);
/*  532 */     return contains((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Contains contains(Expression geometry1, Expression geometry2) {
/*  536 */     return (Contains)new ContainsImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Contains contains(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  540 */     return (Contains)new ContainsImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Contains contains(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  544 */     PropertyName name = property(propertyName);
/*  545 */     Literal geom = literal(geometry);
/*  547 */     return contains((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Crosses crosses(String propertyName, Geometry geometry) {
/*  551 */     PropertyName name = property(propertyName);
/*  552 */     Literal geom = literal(geometry);
/*  554 */     return crosses((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Crosses crosses(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  558 */     PropertyName name = property(propertyName);
/*  559 */     Literal geom = literal(geometry);
/*  561 */     return crosses((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Crosses crosses(Expression geometry1, Expression geometry2) {
/*  565 */     return (Crosses)new CrossesImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Crosses crosses(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  569 */     return (Crosses)new CrossesImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Disjoint disjoint(String propertyName, Geometry geometry) {
/*  573 */     PropertyName name = property(propertyName);
/*  574 */     Literal geom = literal(geometry);
/*  576 */     return disjoint((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Disjoint disjoint(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  580 */     PropertyName name = property(propertyName);
/*  581 */     Literal geom = literal(geometry);
/*  583 */     return disjoint((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Disjoint disjoint(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  587 */     return (Disjoint)new DisjointImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Disjoint disjoint(Expression geometry1, Expression geometry2) {
/*  591 */     return (Disjoint)new DisjointImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public DWithin dwithin(String propertyName, Geometry geometry, double distance, String units) {
/*  596 */     PropertyName name = property(propertyName);
/*  597 */     Literal geom = literal(geometry);
/*  599 */     return dwithin((Expression)name, (Expression)geom, distance, units);
/*      */   }
/*      */   
/*      */   public DWithin dwithin(String propertyName, Geometry geometry, double distance, String units, MultiValuedFilter.MatchAction matchAction) {
/*  604 */     PropertyName name = property(propertyName);
/*  605 */     Literal geom = literal(geometry);
/*  607 */     return dwithin((Expression)name, (Expression)geom, distance, units, matchAction);
/*      */   }
/*      */   
/*      */   public DWithin dwithin(Expression geometry1, Expression geometry2, double distance, String units, MultiValuedFilter.MatchAction matchAction) {
/*  612 */     DWithinImpl dwithin = new DWithinImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*  613 */     dwithin.setDistance(distance);
/*  614 */     dwithin.setUnits(units);
/*  616 */     return (DWithin)dwithin;
/*      */   }
/*      */   
/*      */   public DWithin dwithin(Expression geometry1, Expression geometry2, double distance, String units) {
/*  620 */     DWithinImpl dwithin = new DWithinImpl((FilterFactory)this, geometry1, geometry2);
/*  621 */     dwithin.setDistance(distance);
/*  622 */     dwithin.setUnits(units);
/*  624 */     return (DWithin)dwithin;
/*      */   }
/*      */   
/*      */   public Equals equals(String propertyName, Geometry geometry) {
/*  628 */     PropertyName name = property(propertyName);
/*  629 */     Literal geom = literal(geometry);
/*  631 */     return equal((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Equals equals(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  635 */     PropertyName name = property(propertyName);
/*  636 */     Literal geom = literal(geometry);
/*  638 */     return equal((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Equals equal(Expression geometry1, Expression geometry2) {
/*  642 */     return (Equals)new EqualsImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Equals equal(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  646 */     return (Equals)new EqualsImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Intersects intersects(String propertyName, Geometry geometry) {
/*  650 */     PropertyName name = property(propertyName);
/*  651 */     Literal geom = literal(geometry);
/*  653 */     return intersects((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Intersects intersects(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  657 */     PropertyName name = property(propertyName);
/*  658 */     Literal geom = literal(geometry);
/*  660 */     return intersects((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Intersects intersects(Expression geometry1, Expression geometry2) {
/*  664 */     return (Intersects)new IntersectsImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Intersects intersects(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  668 */     return (Intersects)new IntersectsImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Overlaps overlaps(String propertyName, Geometry geometry) {
/*  672 */     PropertyName name = property(propertyName);
/*  673 */     Literal geom = literal(geometry);
/*  675 */     return overlaps((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Overlaps overlaps(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  679 */     PropertyName name = property(propertyName);
/*  680 */     Literal geom = literal(geometry);
/*  682 */     return overlaps((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Overlaps overlaps(Expression geometry1, Expression geometry2) {
/*  686 */     return (Overlaps)new OverlapsImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Overlaps overlaps(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  690 */     return (Overlaps)new OverlapsImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Touches touches(String propertyName, Geometry geometry) {
/*  694 */     PropertyName name = property(propertyName);
/*  695 */     Literal geom = literal(geometry);
/*  697 */     return touches((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Touches touches(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  701 */     PropertyName name = property(propertyName);
/*  702 */     Literal geom = literal(geometry);
/*  704 */     return touches((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Touches touches(Expression geometry1, Expression geometry2) {
/*  708 */     return (Touches)new TouchesImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Touches touches(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  712 */     return (Touches)new TouchesImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Within within(String propertyName, Geometry geometry) {
/*  716 */     PropertyName name = property(propertyName);
/*  717 */     Literal geom = literal(geometry);
/*  719 */     return within((Expression)name, (Expression)geom);
/*      */   }
/*      */   
/*      */   public Within within(String propertyName, Geometry geometry, MultiValuedFilter.MatchAction matchAction) {
/*  723 */     PropertyName name = property(propertyName);
/*  724 */     Literal geom = literal(geometry);
/*  726 */     return within((Expression)name, (Expression)geom, matchAction);
/*      */   }
/*      */   
/*      */   public Within within(Expression geometry1, Expression geometry2) {
/*  730 */     return (Within)new WithinImpl((FilterFactory)this, geometry1, geometry2);
/*      */   }
/*      */   
/*      */   public Within within(Expression geometry1, Expression geometry2, MultiValuedFilter.MatchAction matchAction) {
/*  734 */     return (Within)new WithinImpl((FilterFactory)this, geometry1, geometry2, matchAction);
/*      */   }
/*      */   
/*      */   public Add add(Expression expr1, Expression expr2) {
/*  738 */     return (Add)new AddImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Divide divide(Expression expr1, Expression expr2) {
/*  742 */     return (Divide)new DivideImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Multiply multiply(Expression expr1, Expression expr2) {
/*  746 */     return (Multiply)new MultiplyImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Subtract subtract(Expression expr1, Expression expr2) {
/*  750 */     return (Subtract)new SubtractImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Function function(String name, Expression[] args) {
/*  754 */     Function function = this.functionFinder.findFunction(name, Arrays.asList(args));
/*  755 */     return function;
/*      */   }
/*      */   
/*      */   public Function function(Name name, Expression... args) {
/*  759 */     Function function = this.functionFinder.findFunction(name, Arrays.asList(args));
/*  760 */     return function;
/*      */   }
/*      */   
/*      */   public Function function(String name, Expression arg1) {
/*  764 */     Function function = this.functionFinder.findFunction(name, Arrays.asList(new Expression[] { arg1 }));
/*  765 */     return function;
/*      */   }
/*      */   
/*      */   public Function function(String name, Expression arg1, Expression arg2) {
/*  769 */     Function function = this.functionFinder.findFunction(name, Arrays.asList(new Expression[] { arg1, arg2 }));
/*  771 */     return function;
/*      */   }
/*      */   
/*      */   public Function function(String name, List<Expression> parameters, Literal fallback) {
/*  776 */     Function function = this.functionFinder.findFunction(name, parameters, fallback);
/*  779 */     return function;
/*      */   }
/*      */   
/*      */   public Function function(String name, Expression arg1, Expression arg2, Expression arg3) {
/*  783 */     Function function = this.functionFinder.findFunction(name, Arrays.asList(new Expression[] { arg1, arg2, arg3 }));
/*  786 */     return function;
/*      */   }
/*      */   
/*      */   public Literal literal(Object obj) {
/*      */     try {
/*  791 */       return new LiteralExpressionImpl(obj);
/*  793 */     } catch (IllegalFilterException e) {
/*  794 */       (new IllegalArgumentException()).initCause(e);
/*  797 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Literal literal(byte b) {
/*  801 */     return new LiteralExpressionImpl(b);
/*      */   }
/*      */   
/*      */   public Literal literal(short s) {
/*  805 */     return new LiteralExpressionImpl(s);
/*      */   }
/*      */   
/*      */   public Literal literal(int i) {
/*  809 */     return new LiteralExpressionImpl(i);
/*      */   }
/*      */   
/*      */   public Literal literal(long l) {
/*  813 */     return new LiteralExpressionImpl(l);
/*      */   }
/*      */   
/*      */   public Literal literal(float f) {
/*  817 */     return new LiteralExpressionImpl(f);
/*      */   }
/*      */   
/*      */   public Literal literal(double d) {
/*  821 */     return new LiteralExpressionImpl(d);
/*      */   }
/*      */   
/*      */   public Literal literal(char c) {
/*  825 */     return new LiteralExpressionImpl(c);
/*      */   }
/*      */   
/*      */   public Literal literal(boolean b) {
/*  829 */     return b ? new LiteralExpressionImpl(Boolean.TRUE) : new LiteralExpressionImpl(Boolean.FALSE);
/*      */   }
/*      */   
/*      */   public AttributeExpression createAttributeExpression(String xpath) {
/*  842 */     return new AttributeExpressionImpl(xpath);
/*      */   }
/*      */   
/*      */   public AttributeExpression createAttributeExpression(SimpleFeatureType schema) {
/*  852 */     return new AttributeExpressionImpl(schema);
/*      */   }
/*      */   
/*      */   public AttributeExpression createAttributeExpression(SimpleFeatureType schema, String path) throws IllegalFilterException {
/*  867 */     return new AttributeExpressionImpl(schema, path);
/*      */   }
/*      */   
/*      */   public AttributeExpression createAttributeExpression(AttributeDescriptor at) throws IllegalFilterException {
/*  870 */     return new AttributeExpressionImpl2(at);
/*      */   }
/*      */   
/*      */   public BBoxExpression createBBoxExpression(Envelope env) throws IllegalFilterException {
/*  884 */     return new BBoxExpressionImpl(env);
/*      */   }
/*      */   
/*      */   public BetweenFilter createBetweenFilter() throws IllegalFilterException {
/*  895 */     return new BetweenFilterImpl();
/*      */   }
/*      */   
/*      */   public CompareFilter createCompareFilter(short type) throws IllegalFilterException {
/*  912 */     switch (type) {
/*      */       case 14:
/*  914 */         return new IsEqualsToImpl((FilterFactory)this);
/*      */       case 23:
/*  917 */         return new IsNotEqualToImpl((FilterFactory)this);
/*      */       case 16:
/*  920 */         return new IsGreaterThanImpl((FilterFactory)this);
/*      */       case 18:
/*  923 */         return new IsGreaterThanOrEqualToImpl((FilterFactory)this);
/*      */       case 15:
/*  926 */         return new IsLessThenImpl((FilterFactory)this);
/*      */       case 17:
/*  929 */         return new IsLessThenOrEqualToImpl((FilterFactory)this);
/*      */       case 19:
/*  932 */         return new BetweenFilterImpl((FilterFactory)this);
/*      */     } 
/*  935 */     throw new IllegalFilterException("Must be one of <,<=,==,>,>=,<>");
/*      */   }
/*      */   
/*      */   public FidFilter createFidFilter() {
/*  944 */     return new FidFilterImpl();
/*      */   }
/*      */   
/*      */   public FidFilter createFidFilter(String fid) {
/*  955 */     return new FidFilterImpl(fid);
/*      */   }
/*      */   
/*      */   public GeometryFilter createGeometryFilter(short filterType) throws IllegalFilterException {
/*  969 */     switch (filterType) {
/*      */       case 5:
/*  971 */         return (GeometryFilter)new EqualsImpl((FilterFactory)this, null, null);
/*      */       case 6:
/*  974 */         return (GeometryFilter)new DisjointImpl((FilterFactory)this, null, null);
/*      */       case 24:
/*  977 */         return (GeometryFilter)new DWithinImpl((FilterFactory)this, null, null);
/*      */       case 7:
/*  980 */         return (GeometryFilter)new IntersectsImpl((FilterFactory)this, null, null);
/*      */       case 9:
/*  983 */         return (GeometryFilter)new CrossesImpl((FilterFactory)this, null, null);
/*      */       case 10:
/*  986 */         return (GeometryFilter)new WithinImpl((FilterFactory)this, null, null);
/*      */       case 11:
/*  989 */         return (GeometryFilter)new ContainsImpl((FilterFactory)this, null, null);
/*      */       case 12:
/*  992 */         return (GeometryFilter)new OverlapsImpl((FilterFactory)this, null, null);
/*      */       case 13:
/*  995 */         return (GeometryFilter)new BeyondImpl((FilterFactory)this, null, null);
/*      */       case 4:
/*  998 */         return (GeometryFilter)new BBOXImpl((FilterFactory)this, null, null);
/*      */       case 8:
/* 1001 */         return (GeometryFilter)new TouchesImpl((FilterFactory)this, null, null);
/*      */     } 
/* 1004 */     throw new IllegalFilterException("Not one of the accepted spatial filter types.");
/*      */   }
/*      */   
/*      */   public GeometryDistanceFilter createGeometryDistanceFilter(short filterType) throws IllegalFilterException {
/* 1020 */     switch (filterType) {
/*      */       case 13:
/* 1022 */         return (GeometryDistanceFilter)new BeyondImpl((FilterFactory)this, null, null);
/*      */       case 24:
/* 1025 */         return (GeometryDistanceFilter)new DWithinImpl((FilterFactory)this, null, null);
/*      */     } 
/* 1029 */     throw new IllegalFilterException("Not one of the accepted spatial filter types.");
/*      */   }
/*      */   
/*      */   public LikeFilter createLikeFilter() {
/* 1039 */     return new LikeFilterImpl();
/*      */   }
/*      */   
/*      */   public LiteralExpression createLiteralExpression() {
/* 1048 */     return new LiteralExpressionImpl();
/*      */   }
/*      */   
/*      */   public LiteralExpression createLiteralExpression(Object o) throws IllegalFilterException {
/* 1062 */     return new LiteralExpressionImpl(o);
/*      */   }
/*      */   
/*      */   public LiteralExpression createLiteralExpression(int i) {
/* 1073 */     return new LiteralExpressionImpl(i);
/*      */   }
/*      */   
/*      */   public LiteralExpression createLiteralExpression(double d) {
/* 1084 */     return new LiteralExpressionImpl(d);
/*      */   }
/*      */   
/*      */   public LiteralExpression createLiteralExpression(String s) {
/* 1095 */     return new LiteralExpressionImpl(s);
/*      */   }
/*      */   
/*      */   public LogicFilter createLogicFilter(short filterType) throws IllegalFilterException {
/* 1115 */     List children = new ArrayList();
/* 1116 */     switch (filterType) {
/*      */       case 2:
/* 1118 */         return new AndImpl((FilterFactory)this, children);
/*      */       case 1:
/* 1120 */         return new OrImpl((FilterFactory)this, children);
/*      */       case 3:
/* 1122 */         return new NotImpl((FilterFactory)this);
/*      */     } 
/* 1124 */     throw new IllegalFilterException("Must be one of AND,OR,NOT.");
/*      */   }
/*      */   
/*      */   public LogicFilter createLogicFilter(Filter filter, short filterType) throws IllegalFilterException {
/* 1145 */     List<Filter> children = new ArrayList();
/* 1146 */     children.add(filter);
/* 1148 */     switch (filterType) {
/*      */       case 2:
/* 1150 */         return new AndImpl((FilterFactory)this, children);
/*      */       case 1:
/* 1152 */         return new OrImpl((FilterFactory)this, children);
/*      */       case 3:
/* 1154 */         return new NotImpl((FilterFactory)this, filter);
/*      */     } 
/* 1157 */     throw new IllegalFilterException("Must be one of AND,OR,NOT.");
/*      */   }
/*      */   
/*      */   public LogicFilter createLogicFilter(Filter filter1, Filter filter2, short filterType) throws IllegalFilterException {
/* 1180 */     List<Filter> children = new ArrayList();
/* 1181 */     children.add(filter1);
/* 1182 */     children.add(filter2);
/* 1184 */     switch (filterType) {
/*      */       case 2:
/* 1186 */         return new AndImpl((FilterFactory)this, children);
/*      */       case 1:
/* 1188 */         return new OrImpl((FilterFactory)this, children);
/*      */       case 3:
/* 1191 */         return new NotImpl((FilterFactory)this, filter1);
/*      */     } 
/* 1194 */     throw new IllegalFilterException("Must be one of AND,OR,NOT.");
/*      */   }
/*      */   
/*      */   public MathExpression createMathExpression() {
/* 1212 */     throw new UnsupportedOperationException();
/*      */   }
/*      */   
/*      */   public MathExpression createMathExpression(short expressionType) throws IllegalFilterException {
/* 1227 */     switch (expressionType) {
/*      */       case 105:
/* 1229 */         return (MathExpression)new AddImpl(null, null);
/*      */       case 106:
/* 1231 */         return (MathExpression)new SubtractImpl(null, null);
/*      */       case 107:
/* 1233 */         return (MathExpression)new MultiplyImpl(null, null);
/*      */       case 108:
/* 1235 */         return (MathExpression)new DivideImpl(null, null);
/*      */     } 
/* 1238 */     throw new IllegalFilterException("Unsupported math expression");
/*      */   }
/*      */   
/*      */   public FunctionExpression createFunctionExpression(String name) {
/* 1249 */     return (FunctionExpression)this.functionFinder.findFunction(name);
/*      */   }
/*      */   
/*      */   public NullFilter createNullFilter() {
/* 1258 */     return new NullFilterImpl();
/*      */   }
/*      */   
/*      */   public EnvironmentVariable createEnvironmentVariable(String name) {
/* 1262 */     if (name.equalsIgnoreCase("MapScaleDenominator"))
/* 1263 */       return new MapScaleDenominatorImpl(); 
/* 1265 */     throw new RuntimeException("Unknown environment variable:" + name);
/*      */   }
/*      */   
/*      */   public Map getImplementationHints() {
/* 1269 */     return Collections.EMPTY_MAP;
/*      */   }
/*      */   
/*      */   public SortBy sort(String propertyName, SortOrder order) {
/* 1273 */     return new SortByImpl(property(propertyName), order);
/*      */   }
/*      */   
/*      */   public After after(Expression expr1, Expression expr2) {
/* 1277 */     return (After)new AfterImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public After after(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1281 */     return (After)new AfterImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public AnyInteracts anyInteracts(Expression expr1, Expression expr2) {
/* 1285 */     return (AnyInteracts)new AnyInteractsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public AnyInteracts anyInteracts(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1289 */     return (AnyInteracts)new AnyInteractsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public Before before(Expression expr1, Expression expr2) {
/* 1293 */     return (Before)new BeforeImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Before before(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1297 */     return (Before)new BeforeImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public Begins begins(Expression expr1, Expression expr2) {
/* 1301 */     return (Begins)new BeginsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Begins begins(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1305 */     return (Begins)new BeginsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public BegunBy begunBy(Expression expr1, Expression expr2) {
/* 1309 */     return (BegunBy)new BegunByImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public BegunBy begunBy(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1313 */     return (BegunBy)new BegunByImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public During during(Expression expr1, Expression expr2) {
/* 1317 */     return (During)new DuringImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public During during(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1321 */     return (During)new DuringImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public EndedBy endedBy(Expression expr1, Expression expr2) {
/* 1325 */     return (EndedBy)new EndedByImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public EndedBy endedBy(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1329 */     return (EndedBy)new EndedByImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public Ends ends(Expression expr1, Expression expr2) {
/* 1333 */     return (Ends)new EndsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Ends ends(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1337 */     return (Ends)new EndsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public Meets meets(Expression expr1, Expression expr2) {
/* 1341 */     return (Meets)new MeetsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public Meets meets(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1345 */     return (Meets)new MeetsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public MetBy metBy(Expression expr1, Expression expr2) {
/* 1349 */     return (MetBy)new MetByImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public MetBy metBy(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1353 */     return (MetBy)new MetByImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public OverlappedBy overlappedBy(Expression expr1, Expression expr2) {
/* 1357 */     return (OverlappedBy)new OverlappedByImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public OverlappedBy overlappedBy(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1361 */     return (OverlappedBy)new OverlappedByImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public TContains tcontains(Expression expr1, Expression expr2) {
/* 1365 */     return (TContains)new TContainsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public TContains tcontains(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1369 */     return (TContains)new TContainsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public TEquals tequals(Expression expr1, Expression expr2) {
/* 1373 */     return (TEquals)new TEqualsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public TEquals tequals(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1377 */     return (TEquals)new TEqualsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public TOverlaps toverlaps(Expression expr1, Expression expr2) {
/* 1381 */     return (TOverlaps)new TOverlapsImpl(expr1, expr2);
/*      */   }
/*      */   
/*      */   public TOverlaps toverlaps(Expression expr1, Expression expr2, MultiValuedFilter.MatchAction matchAction) {
/* 1385 */     return (TOverlaps)new TOverlapsImpl(expr1, expr2, matchAction);
/*      */   }
/*      */   
/*      */   public Filter and(Filter filter1, Filter filter2) {
/* 1389 */     return (Filter)and(filter1, filter2);
/*      */   }
/*      */   
/*      */   public Filter not(Filter filter) {
/* 1393 */     return (Filter)not(filter);
/*      */   }
/*      */   
/*      */   public Filter or(Filter filter1, Filter filter2) {
/* 1397 */     return (Filter)or(filter1, filter2);
/*      */   }
/*      */   
/*      */   public Beyond beyond(Expression geometry1, Geometry geometry2, double distance, String units) {
/* 1401 */     return beyond(geometry1, (Expression)literal(geometry2), distance, units);
/*      */   }
/*      */   
/*      */   public PropertyName property(Name name) {
/* 1404 */     return new AttributeExpressionImpl(name);
/*      */   }
/*      */   
/*      */   public PropertyName property(String name, NamespaceSupport namespaceContext) {
/* 1407 */     if (namespaceContext == null)
/* 1408 */       return property(name); 
/* 1410 */     return new AttributeExpressionImpl(name, namespaceContext);
/*      */   }
/*      */   
/*      */   public Within within(Expression geometry1, Geometry geometry2) {
/* 1413 */     return within(geometry1, (Expression)literal(geometry2));
/*      */   }
/*      */   
/*      */   public Operator operator(String name) {
/* 1417 */     return (Operator)new OperatorImpl(name);
/*      */   }
/*      */   
/*      */   public SpatialOperator spatialOperator(String name, GeometryOperand[] geometryOperands) {
/* 1422 */     return (SpatialOperator)new SpatialOperatorImpl(name, geometryOperands);
/*      */   }
/*      */   
/*      */   public TemporalOperator temporalOperator(String name) {
/* 1426 */     return (TemporalOperator)new TemporalOperatorImpl(name);
/*      */   }
/*      */   
/*      */   public <T> Parameter<T> parameter(String name, Class<T> type, InternationalString title, InternationalString description, boolean required, int minOccurs, int maxOccurs, T defaultValue) {
/* 1431 */     return (Parameter<T>)new Parameter(name, type, title, description, required, minOccurs, maxOccurs, defaultValue, null);
/*      */   }
/*      */   
/*      */   public FunctionName functionName(String name, int nargs) {
/* 1436 */     return (FunctionName)new FunctionNameImpl(name, nargs);
/*      */   }
/*      */   
/*      */   public FunctionName functionName(Name name, int nargs) {
/* 1441 */     return (FunctionName)new FunctionNameImpl(name, nargs);
/*      */   }
/*      */   
/*      */   public FunctionName functionName(String name, int nargs, List<String> argNames) {
/* 1445 */     return (FunctionName)new FunctionNameImpl(name, nargs, argNames);
/*      */   }
/*      */   
/*      */   public FunctionName functionName(Name name, int nargs, List<String> argNames) {
/* 1450 */     return (FunctionName)new FunctionNameImpl(name, nargs, argNames);
/*      */   }
/*      */   
/*      */   public FunctionName functionName(String name, List<Parameter<?>> args, Parameter<?> ret) {
/* 1454 */     return (FunctionName)new FunctionNameImpl(name, ret, args);
/*      */   }
/*      */   
/*      */   public FunctionName functionName(Name name, List<Parameter<?>> args, Parameter<?> ret) {
/* 1459 */     return (FunctionName)new FunctionNameImpl(name, ret, args);
/*      */   }
/*      */   
/*      */   public Functions functions(FunctionName[] functionNames) {
/* 1463 */     return (Functions)new FunctionsImpl(functionNames);
/*      */   }
/*      */   
/*      */   public SpatialOperators spatialOperators(SpatialOperator[] spatialOperators) {
/* 1467 */     return (SpatialOperators)new SpatialOperatorsImpl(spatialOperators);
/*      */   }
/*      */   
/*      */   public ArithmeticOperators arithmeticOperators(boolean simple, Functions functions) {
/* 1471 */     return (ArithmeticOperators)new ArithmeticOperatorsImpl(simple, functions);
/*      */   }
/*      */   
/*      */   public ComparisonOperators comparisonOperators(Operator[] comparisonOperators) {
/* 1475 */     return (ComparisonOperators)new ComparisonOperatorsImpl(comparisonOperators);
/*      */   }
/*      */   
/*      */   public FilterCapabilities capabilities(String version, ScalarCapabilities scalar, SpatialCapabilities spatial, IdCapabilities id) {
/* 1481 */     return (FilterCapabilities)new FilterCapabilitiesImpl(version, scalar, spatial, id);
/*      */   }
/*      */   
/*      */   public FilterCapabilities capabilities(String version, ScalarCapabilities scalar, SpatialCapabilities spatial, IdCapabilities id, TemporalCapabilities temporal) {
/* 1486 */     return (FilterCapabilities)new FilterCapabilitiesImpl(version, scalar, spatial, id, temporal);
/*      */   }
/*      */   
/*      */   public ScalarCapabilities scalarCapabilities(ComparisonOperators comparison, ArithmeticOperators arithmetic, boolean logicalOperators) {
/* 1492 */     return (ScalarCapabilities)new ScalarCapabilitiesImpl(comparison, arithmetic, logicalOperators);
/*      */   }
/*      */   
/*      */   public SpatialCapabilities spatialCapabilities(GeometryOperand[] geometryOperands, SpatialOperators spatial) {
/* 1498 */     return (SpatialCapabilities)new SpatialCapabiltiesImpl(geometryOperands, spatial);
/*      */   }
/*      */   
/*      */   public IdCapabilities idCapabilities(boolean eid, boolean fid) {
/* 1502 */     return (IdCapabilities)new IdCapabilitiesImpl(eid, fid);
/*      */   }
/*      */   
/*      */   public TemporalCapabilities temporalCapabilities(TemporalOperator[] temporalOperators) {
/* 1506 */     return (TemporalCapabilities)new TemporalCapabilitiesImpl(Arrays.asList(temporalOperators));
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */