package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.UnmodifiableGeometryException;
import org.opengis.geometry.coordinate.Position;

@UML(identifier = "GM_Point", specification = Specification.ISO_19107)
public interface Point extends Primitive, Position {
  @UML(identifier = "position", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getDirectPosition();
  
  @UML(identifier = "position", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  void setDirectPosition(DirectPosition paramDirectPosition) throws UnmodifiableGeometryException;
  
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PrimitiveBoundary getBoundary();
  
  @UML(identifier = "bearing", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Bearing getBearing(Position paramPosition);
  
  @UML(identifier = "proxy", obligation = Obligation.FORBIDDEN, specification = Specification.ISO_19107)
  OrientablePrimitive[] getProxy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Point.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */