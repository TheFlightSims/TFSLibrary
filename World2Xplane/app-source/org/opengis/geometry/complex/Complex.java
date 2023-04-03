package org.opengis.geometry.complex;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Primitive;

@UML(identifier = "GM_Complex", specification = Specification.ISO_19107)
public interface Complex extends Geometry {
  @UML(identifier = "isMaximal", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isMaximal();
  
  @UML(identifier = "superComplex", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Complex[] getSuperComplexes();
  
  @UML(identifier = "subComplex", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Complex[] getSubComplexes();
  
  @UML(identifier = "element", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Collection<? extends Primitive> getElements();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\Complex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */