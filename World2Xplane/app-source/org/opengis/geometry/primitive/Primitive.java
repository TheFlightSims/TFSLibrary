package org.opengis.geometry.primitive;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.complex.Complex;
import org.opengis.geometry.complex.Composite;

@UML(identifier = "GM_Primitive", specification = Specification.ISO_19107)
public interface Primitive extends Geometry {
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PrimitiveBoundary getBoundary();
  
  @UML(identifier = "containedPrimitive", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<Primitive> getContainedPrimitives();
  
  @UML(identifier = "containingPrimitive", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<Primitive> getContainingPrimitives();
  
  @UML(identifier = "complex", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<Complex> getComplexes();
  
  @UML(identifier = "composite", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  Composite getComposite();
  
  @UML(identifier = "proxy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
  OrientablePrimitive[] getProxy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Primitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */