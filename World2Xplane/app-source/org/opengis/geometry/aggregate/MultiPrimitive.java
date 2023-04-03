package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Primitive;

@UML(identifier = "GM_MultiPrimitive", specification = Specification.ISO_19107)
public interface MultiPrimitive extends Aggregate {
  @UML(identifier = "element", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<? extends Primitive> getElements();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\aggregate\MultiPrimitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */