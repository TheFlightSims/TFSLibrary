package org.opengis.temporal;

import java.util.Collection;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_TopologicalComplex", specification = Specification.ISO_19108)
public interface TemporalTopologicalComplex extends TemporalComplex {
  Collection<TemporalTopologicalPrimitive> getTemporalTopologicalPrimitives();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalTopologicalComplex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */