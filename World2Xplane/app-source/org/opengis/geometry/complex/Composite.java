package org.opengis.geometry.complex;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Primitive;

@UML(identifier = "GM_Composite", specification = Specification.ISO_19107)
public interface Composite extends Complex {
  @UML(identifier = "generator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Collection<? extends Primitive> getGenerators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\Composite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */