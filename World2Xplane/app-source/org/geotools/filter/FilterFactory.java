package org.geotools.filter;

import com.vividsolutions.jts.geom.Envelope;
import org.geotools.factory.Factory;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.spatial.BBOX;
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

public interface FilterFactory extends Factory, FilterFactory2 {
  LogicFilter createLogicFilter(Filter paramFilter1, Filter paramFilter2, short paramShort) throws IllegalFilterException;
  
  LogicFilter createLogicFilter(short paramShort) throws IllegalFilterException;
  
  LogicFilter createLogicFilter(Filter paramFilter, short paramShort) throws IllegalFilterException;
  
  BBoxExpression createBBoxExpression(Envelope paramEnvelope) throws IllegalFilterException;
  
  LiteralExpression createLiteralExpression(int paramInt);
  
  MathExpression createMathExpression() throws IllegalFilterException;
  
  FidFilter createFidFilter();
  
  AttributeExpression createAttributeExpression(String paramString);
  
  AttributeExpression createAttributeExpression(SimpleFeatureType paramSimpleFeatureType, String paramString) throws IllegalFilterException;
  
  AttributeExpression createAttributeExpression(AttributeDescriptor paramAttributeDescriptor) throws IllegalFilterException;
  
  LiteralExpression createLiteralExpression(Object paramObject) throws IllegalFilterException;
  
  CompareFilter createCompareFilter(short paramShort) throws IllegalFilterException;
  
  LiteralExpression createLiteralExpression();
  
  LiteralExpression createLiteralExpression(String paramString);
  
  LiteralExpression createLiteralExpression(double paramDouble);
  
  AttributeExpression createAttributeExpression(SimpleFeatureType paramSimpleFeatureType);
  
  MathExpression createMathExpression(short paramShort) throws IllegalFilterException;
  
  NullFilter createNullFilter();
  
  BetweenFilter createBetweenFilter() throws IllegalFilterException;
  
  GeometryFilter createGeometryFilter(short paramShort) throws IllegalFilterException;
  
  GeometryDistanceFilter createGeometryDistanceFilter(short paramShort) throws IllegalFilterException;
  
  FidFilter createFidFilter(String paramString);
  
  LikeFilter createLikeFilter();
  
  FunctionExpression createFunctionExpression(String paramString);
  
  EnvironmentVariable createEnvironmentVariable(String paramString);
  
  Filter or(Filter paramFilter1, Filter paramFilter2);
  
  Filter and(Filter paramFilter1, Filter paramFilter2);
  
  Filter not(Filter paramFilter);
  
  BBOX bbox(Expression paramExpression, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, String paramString);
  
  Beyond beyond(Expression paramExpression1, Expression paramExpression2, double paramDouble, String paramString);
  
  Contains contains(Expression paramExpression1, Expression paramExpression2);
  
  Crosses crosses(Expression paramExpression1, Expression paramExpression2);
  
  Disjoint disjoint(Expression paramExpression1, Expression paramExpression2);
  
  DWithin dwithin(Expression paramExpression1, Expression paramExpression2, double paramDouble, String paramString);
  
  Equals equal(Expression paramExpression1, Expression paramExpression2);
  
  Intersects intersects(Expression paramExpression1, Expression paramExpression2);
  
  Overlaps overlaps(Expression paramExpression1, Expression paramExpression2);
  
  Touches touches(Expression paramExpression1, Expression paramExpression2);
  
  Within within(Expression paramExpression1, Expression paramExpression2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */