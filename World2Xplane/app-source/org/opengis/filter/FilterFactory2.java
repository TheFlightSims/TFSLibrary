package org.opengis.filter;

import java.util.List;
import org.opengis.feature.type.Name;
import org.opengis.filter.capability.FunctionName;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.identity.FeatureId;
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
import org.opengis.geometry.BoundingBox;
import org.opengis.geometry.BoundingBox3D;
import org.opengis.parameter.Parameter;
import org.opengis.util.InternationalString;
import org.xml.sax.helpers.NamespaceSupport;

public interface FilterFactory2 extends FilterFactory {
  <T> Parameter<T> parameter(String paramString, Class<T> paramClass, InternationalString paramInternationalString1, InternationalString paramInternationalString2, boolean paramBoolean, int paramInt1, int paramInt2, T paramT);
  
  FunctionName functionName(String paramString, int paramInt, List<String> paramList);
  
  FunctionName functionName(Name paramName, int paramInt, List<String> paramList);
  
  FunctionName functionName(String paramString, List<Parameter<?>> paramList, Parameter<?> paramParameter);
  
  FunctionName functionName(Name paramName, List<Parameter<?>> paramList, Parameter<?> paramParameter);
  
  Id id(FeatureId... paramVarArgs);
  
  PropertyName property(Name paramName);
  
  PropertyName property(String paramString, NamespaceSupport paramNamespaceSupport);
  
  PropertyIsLike like(Expression paramExpression, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean);
  
  PropertyIsLike like(Expression paramExpression, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, MultiValuedFilter.MatchAction paramMatchAction);
  
  BBOX bbox(Expression paramExpression, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, String paramString);
  
  BBOX bbox(Expression paramExpression, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, String paramString, MultiValuedFilter.MatchAction paramMatchAction);
  
  BBOX3D bbox(Expression paramExpression, BoundingBox3D paramBoundingBox3D);
  
  BBOX3D bbox(Expression paramExpression, BoundingBox3D paramBoundingBox3D, MultiValuedFilter.MatchAction paramMatchAction);
  
  BBOX bbox(Expression paramExpression, BoundingBox paramBoundingBox);
  
  BBOX bbox(Expression paramExpression, BoundingBox paramBoundingBox, MultiValuedFilter.MatchAction paramMatchAction);
  
  Beyond beyond(Expression paramExpression1, Expression paramExpression2, double paramDouble, String paramString);
  
  Beyond beyond(Expression paramExpression1, Expression paramExpression2, double paramDouble, String paramString, MultiValuedFilter.MatchAction paramMatchAction);
  
  Contains contains(Expression paramExpression1, Expression paramExpression2);
  
  Contains contains(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Crosses crosses(Expression paramExpression1, Expression paramExpression2);
  
  Crosses crosses(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Disjoint disjoint(Expression paramExpression1, Expression paramExpression2);
  
  Disjoint disjoint(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  DWithin dwithin(Expression paramExpression1, Expression paramExpression2, double paramDouble, String paramString);
  
  DWithin dwithin(Expression paramExpression1, Expression paramExpression2, double paramDouble, String paramString, MultiValuedFilter.MatchAction paramMatchAction);
  
  Equals equal(Expression paramExpression1, Expression paramExpression2);
  
  Equals equal(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Intersects intersects(Expression paramExpression1, Expression paramExpression2);
  
  Intersects intersects(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Overlaps overlaps(Expression paramExpression1, Expression paramExpression2);
  
  Overlaps overlaps(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Touches touches(Expression paramExpression1, Expression paramExpression2);
  
  Touches touches(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
  
  Within within(Expression paramExpression1, Expression paramExpression2);
  
  Within within(Expression paramExpression1, Expression paramExpression2, MultiValuedFilter.MatchAction paramMatchAction);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\FilterFactory2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */