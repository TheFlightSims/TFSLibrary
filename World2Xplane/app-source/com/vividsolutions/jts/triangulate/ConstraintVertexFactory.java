package com.vividsolutions.jts.triangulate;

import com.vividsolutions.jts.geom.Coordinate;

public interface ConstraintVertexFactory {
  ConstraintVertex createVertex(Coordinate paramCoordinate, Segment paramSegment);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\ConstraintVertexFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */