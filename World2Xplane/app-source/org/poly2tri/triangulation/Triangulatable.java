package org.poly2tri.triangulation;

import java.util.List;
import org.poly2tri.triangulation.delaunay.DelaunayTriangle;

public interface Triangulatable {
  void prepareTriangulation(TriangulationContext<?> paramTriangulationContext);
  
  List<DelaunayTriangle> getTriangles();
  
  List<TriangulationPoint> getPoints();
  
  void addTriangle(DelaunayTriangle paramDelaunayTriangle);
  
  void addTriangles(List<DelaunayTriangle> paramList);
  
  void clearTriangulation();
  
  TriangulationMode getTriangulationMode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\Triangulatable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */