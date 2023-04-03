package org.opengis.filter;

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

public interface FilterVisitor {
  Object visitNullFilter(Object paramObject);
  
  Object visit(ExcludeFilter paramExcludeFilter, Object paramObject);
  
  Object visit(IncludeFilter paramIncludeFilter, Object paramObject);
  
  Object visit(And paramAnd, Object paramObject);
  
  Object visit(Id paramId, Object paramObject);
  
  Object visit(Not paramNot, Object paramObject);
  
  Object visit(Or paramOr, Object paramObject);
  
  Object visit(PropertyIsBetween paramPropertyIsBetween, Object paramObject);
  
  Object visit(PropertyIsEqualTo paramPropertyIsEqualTo, Object paramObject);
  
  Object visit(PropertyIsNotEqualTo paramPropertyIsNotEqualTo, Object paramObject);
  
  Object visit(PropertyIsGreaterThan paramPropertyIsGreaterThan, Object paramObject);
  
  Object visit(PropertyIsGreaterThanOrEqualTo paramPropertyIsGreaterThanOrEqualTo, Object paramObject);
  
  Object visit(PropertyIsLessThan paramPropertyIsLessThan, Object paramObject);
  
  Object visit(PropertyIsLessThanOrEqualTo paramPropertyIsLessThanOrEqualTo, Object paramObject);
  
  Object visit(PropertyIsLike paramPropertyIsLike, Object paramObject);
  
  Object visit(PropertyIsNull paramPropertyIsNull, Object paramObject);
  
  Object visit(PropertyIsNil paramPropertyIsNil, Object paramObject);
  
  Object visit(BBOX paramBBOX, Object paramObject);
  
  Object visit(Beyond paramBeyond, Object paramObject);
  
  Object visit(Contains paramContains, Object paramObject);
  
  Object visit(Crosses paramCrosses, Object paramObject);
  
  Object visit(Disjoint paramDisjoint, Object paramObject);
  
  Object visit(DWithin paramDWithin, Object paramObject);
  
  Object visit(Equals paramEquals, Object paramObject);
  
  Object visit(Intersects paramIntersects, Object paramObject);
  
  Object visit(Overlaps paramOverlaps, Object paramObject);
  
  Object visit(Touches paramTouches, Object paramObject);
  
  Object visit(Within paramWithin, Object paramObject);
  
  Object visit(After paramAfter, Object paramObject);
  
  Object visit(AnyInteracts paramAnyInteracts, Object paramObject);
  
  Object visit(Before paramBefore, Object paramObject);
  
  Object visit(Begins paramBegins, Object paramObject);
  
  Object visit(BegunBy paramBegunBy, Object paramObject);
  
  Object visit(During paramDuring, Object paramObject);
  
  Object visit(EndedBy paramEndedBy, Object paramObject);
  
  Object visit(Ends paramEnds, Object paramObject);
  
  Object visit(Meets paramMeets, Object paramObject);
  
  Object visit(MetBy paramMetBy, Object paramObject);
  
  Object visit(OverlappedBy paramOverlappedBy, Object paramObject);
  
  Object visit(TContains paramTContains, Object paramObject);
  
  Object visit(TEquals paramTEquals, Object paramObject);
  
  Object visit(TOverlaps paramTOverlaps, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\FilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */