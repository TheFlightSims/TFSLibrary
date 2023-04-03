package org.opengis.filter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.opengis.feature.type.Name;
import org.opengis.filter.capability.ArithmeticOperators;
import org.opengis.filter.capability.ComparisonOperators;
import org.opengis.filter.capability.FilterCapabilities;
import org.opengis.filter.capability.FunctionName;
import org.opengis.filter.capability.Functions;
import org.opengis.filter.capability.GeometryOperand;
import org.opengis.filter.capability.IdCapabilities;
import org.opengis.filter.capability.Operator;
import org.opengis.filter.capability.ScalarCapabilities;
import org.opengis.filter.capability.SpatialCapabilities;
import org.opengis.filter.capability.SpatialOperator;
import org.opengis.filter.capability.SpatialOperators;
import org.opengis.filter.capability.TemporalCapabilities;
import org.opengis.filter.capability.TemporalOperator;
import org.opengis.filter.expression.Add;
import org.opengis.filter.expression.Divide;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.Multiply;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.expression.Subtract;
import org.opengis.filter.identity.FeatureId;
import org.opengis.filter.identity.GmlObjectId;
import org.opengis.filter.identity.Identifier;
import org.opengis.filter.identity.ResourceId;
import org.opengis.filter.identity.Version;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;
import org.opengis.filter.spatial.BBOX;
import org.opengis.filter.spatial.BBOX3D;
import org.opengis.filter.spatial.Beyond;
import org.opengis.filter.spatial.Contains;
import org.opengis.filter.spatial.Crosses;
import org.opengis.filter.spatial.DWithin;
import org.opengis.filter.spatial.Disjoint;
import org.opengis.filter.spatial.Equals;
import org.opengis.filter.spatial.Intersects;
import org.opengis.filter.spatial.Overlaps;
import org.opengis.filter.spatial.Touches;
import org.opengis.filter.spatial.Within;
import org.opengis.filter.temporal.After;
import org.opengis.filter.temporal.AnyInteracts;
import org.opengis.filter.temporal.Before;
import org.opengis.filter.temporal.Begins;
import org.opengis.filter.temporal.BegunBy;
import org.opengis.filter.temporal.During;
import org.opengis.filter.temporal.EndedBy;
import org.opengis.filter.temporal.Ends;
import org.opengis.filter.temporal.Meets;
import org.opengis.filter.temporal.MetBy;
import org.opengis.filter.temporal.OverlappedBy;
import org.opengis.filter.temporal.TContains;
import org.opengis.filter.temporal.TEquals;
import org.opengis.filter.temporal.TOverlaps;
import org.opengis.geometry.BoundingBox3D;
import org.opengis.geometry.Geometry;

public interface FilterFactory {
  FeatureId featureId(String paramString);
  
  FeatureId featureId(String paramString1, String paramString2);
  
  GmlObjectId gmlObjectId(String paramString);
  
  ResourceId resourceId(String paramString1, String paramString2, Version paramVersion);
  
  ResourceId resourceId(String paramString, Date paramDate1, Date paramDate2);
  
  And and(Filter paramFilter1, Filter paramFilter2);
  
  And and(List<Filter> paramList);
  
  Or or(Filter paramFilter1, Filter paramFilter2);
  
  Or or(List<Filter> paramList);
  
  Not not(Filter paramFilter);
  
  Id id(Set<? extends Identifier> paramSet);
  
  PropertyName property(String paramString);
  
  PropertyIsBetween between(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3);
  
  PropertyIsBetween between(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsEqualTo equals(Expression paramExpression1, Expression paramExpression2);
  
  PropertyIsEqualTo equal(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean);
  
  PropertyIsEqualTo equal(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsNotEqualTo notEqual(Expression paramExpression1, Expression paramExpression2);
  
  PropertyIsNotEqualTo notEqual(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean);
  
  PropertyIsNotEqualTo notEqual(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsGreaterThan greater(Expression paramExpression1, Expression paramExpression2);
  
  PropertyIsGreaterThan greater(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean);
  
  PropertyIsGreaterThan greater(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsGreaterThanOrEqualTo greaterOrEqual(Expression paramExpression1, Expression paramExpression2);
  
  PropertyIsGreaterThanOrEqualTo greaterOrEqual(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean);
  
  PropertyIsGreaterThanOrEqualTo greaterOrEqual(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsLessThan less(Expression paramExpression1, Expression paramExpression2);
  
  PropertyIsLessThan less(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean);
  
  PropertyIsLessThan less(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsLessThanOrEqualTo lessOrEqual(Expression paramExpression1, Expression paramExpression2);
  
  PropertyIsLessThanOrEqualTo lessOrEqual(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean);
  
  PropertyIsLessThanOrEqualTo lessOrEqual(Expression paramExpression1, Expression paramExpression2, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsLike like(Expression paramExpression, String paramString);
  
  PropertyIsLike like(Expression paramExpression, String paramString1, String paramString2, String paramString3, String paramString4);
  
  PropertyIsLike like(Expression paramExpression, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean);
  
  PropertyIsLike like(Expression paramExpression, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  PropertyIsNull isNull(Expression paramExpression);
  
  PropertyIsNil isNil(Expression paramExpression, Object paramObject);
  
  BBOX bbox(String paramString1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, String paramString2);
  
  BBOX3D bbox(String paramString, BoundingBox3D paramBoundingBox3D);
  
  BBOX3D bbox(String paramString, BoundingBox3D paramBoundingBox3D, MultiValuedFilter.MatchAction paramMatchAction);
  
  BBOX bbox(String paramString1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, String paramString2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Beyond beyond(String paramString1, Geometry paramGeometry, double paramDouble, String paramString2);
  
  Beyond beyond(String paramString1, Geometry paramGeometry, double paramDouble, String paramString2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Contains contains(String paramString, Geometry paramGeometry);
  
  Contains contains(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  Crosses crosses(String paramString, Geometry paramGeometry);
  
  Crosses crosses(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  Disjoint disjoint(String paramString, Geometry paramGeometry);
  
  Disjoint disjoint(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  DWithin dwithin(String paramString1, Geometry paramGeometry, double paramDouble, String paramString2);
  
  DWithin dwithin(String paramString1, Geometry paramGeometry, double paramDouble, String paramString2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Equals equals(String paramString, Geometry paramGeometry);
  
  Equals equals(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  Intersects intersects(String paramString, Geometry paramGeometry);
  
  Intersects intersects(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  Overlaps overlaps(String paramString, Geometry paramGeometry);
  
  Overlaps overlaps(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  Touches touches(String paramString, Geometry paramGeometry);
  
  Touches touches(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  Within within(String paramString, Geometry paramGeometry);
  
  Within within(String paramString, Geometry paramGeometry, MultiValuedFilter.MatchAction paramMatchAction);
  
  After after(Expression paramExpression1, Expression paramExpression2);
  
  After after(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  AnyInteracts anyInteracts(Expression paramExpression1, Expression paramExpression2);
  
  AnyInteracts anyInteracts(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Before before(Expression paramExpression1, Expression paramExpression2);
  
  Before before(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Begins begins(Expression paramExpression1, Expression paramExpression2);
  
  Begins begins(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  BegunBy begunBy(Expression paramExpression1, Expression paramExpression2);
  
  BegunBy begunBy(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  During during(Expression paramExpression1, Expression paramExpression2);
  
  During during(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  EndedBy endedBy(Expression paramExpression1, Expression paramExpression2);
  
  EndedBy endedBy(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Ends ends(Expression paramExpression1, Expression paramExpression2);
  
  Ends ends(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Meets meets(Expression paramExpression1, Expression paramExpression2);
  
  Meets meets(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  MetBy metBy(Expression paramExpression1, Expression paramExpression2);
  
  MetBy metBy(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  OverlappedBy overlappedBy(Expression paramExpression1, Expression paramExpression2);
  
  OverlappedBy overlappedBy(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  TOverlaps toverlaps(Expression paramExpression1, Expression paramExpression2);
  
  TOverlaps toverlaps(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  TContains tcontains(Expression paramExpression1, Expression paramExpression2);
  
  TContains tcontains(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  TEquals tequals(Expression paramExpression1, Expression paramExpression2);
  
  TEquals tequals(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Add add(Expression paramExpression1, Expression paramExpression2);
  
  Divide divide(Expression paramExpression1, Expression paramExpression2);
  
  Multiply multiply(Expression paramExpression1, Expression paramExpression2);
  
  Subtract subtract(Expression paramExpression1, Expression paramExpression2);
  
  Function function(String paramString, Expression... paramVarArgs);
  
  Function function(Name paramName, Expression... paramVarArgs);
  
  Literal literal(Object paramObject);
  
  Literal literal(byte paramByte);
  
  Literal literal(short paramShort);
  
  Literal literal(int paramInt);
  
  Literal literal(long paramLong);
  
  Literal literal(float paramFloat);
  
  Literal literal(double paramDouble);
  
  Literal literal(char paramChar);
  
  Literal literal(boolean paramBoolean);
  
  SortBy sort(String paramString, SortOrder paramSortOrder);
  
  Operator operator(String paramString);
  
  SpatialOperator spatialOperator(String paramString, GeometryOperand[] paramArrayOfGeometryOperand);
  
  TemporalOperator temporalOperator(String paramString);
  
  FunctionName functionName(String paramString, int paramInt);
  
  FunctionName functionName(Name paramName, int paramInt);
  
  Functions functions(FunctionName[] paramArrayOfFunctionName);
  
  SpatialOperators spatialOperators(SpatialOperator[] paramArrayOfSpatialOperator);
  
  ComparisonOperators comparisonOperators(Operator[] paramArrayOfOperator);
  
  ArithmeticOperators arithmeticOperators(boolean paramBoolean, Functions paramFunctions);
  
  ScalarCapabilities scalarCapabilities(ComparisonOperators paramComparisonOperators, ArithmeticOperators paramArithmeticOperators, boolean paramBoolean);
  
  SpatialCapabilities spatialCapabilities(GeometryOperand[] paramArrayOfGeometryOperand, SpatialOperators paramSpatialOperators);
  
  IdCapabilities idCapabilities(boolean paramBoolean1, boolean paramBoolean2);
  
  TemporalCapabilities temporalCapabilities(TemporalOperator[] paramArrayOfTemporalOperator);
  
  FilterCapabilities capabilities(String paramString, ScalarCapabilities paramScalarCapabilities, SpatialCapabilities paramSpatialCapabilities, IdCapabilities paramIdCapabilities);
  
  FilterCapabilities capabilities(String paramString, ScalarCapabilities paramScalarCapabilities, SpatialCapabilities paramSpatialCapabilities, IdCapabilities paramIdCapabilities, TemporalCapabilities paramTemporalCapabilities);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\FilterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */