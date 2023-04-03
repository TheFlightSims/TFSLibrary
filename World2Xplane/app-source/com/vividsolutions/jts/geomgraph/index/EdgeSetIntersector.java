package com.vividsolutions.jts.geomgraph.index;

import java.util.List;

public abstract class EdgeSetIntersector {
  public abstract void computeIntersections(List paramList, SegmentIntersector paramSegmentIntersector, boolean paramBoolean);
  
  public abstract void computeIntersections(List paramList1, List paramList2, SegmentIntersector paramSegmentIntersector);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\EdgeSetIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */