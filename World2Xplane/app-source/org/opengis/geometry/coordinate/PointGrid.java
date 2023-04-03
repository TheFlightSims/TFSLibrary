package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;

@UML(identifier = "GM_PointGrid", specification = Specification.ISO_19107)
public interface PointGrid {
  int width();
  
  int height();
  
  DirectPosition get(int paramInt1, int paramInt2) throws IndexOutOfBoundsException;
  
  DirectPosition get(int paramInt1, int paramInt2, DirectPosition paramDirectPosition) throws IndexOutOfBoundsException;
  
  void set(int paramInt1, int paramInt2, DirectPosition paramDirectPosition) throws IndexOutOfBoundsException, UnsupportedOperationException;
  
  PointArray getRow(int paramInt) throws IndexOutOfBoundsException;
  
  @UML(identifier = "row", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<PointArray> rows();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\PointGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */