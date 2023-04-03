package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Edge", specification = Specification.ISO_19108)
public interface TemporalEdge extends TemporalTopologicalPrimitive {
  @UML(identifier = "Realization", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Period getRealization();
  
  @UML(identifier = "start", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  TemporalNode getStart();
  
  @UML(identifier = "end", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  TemporalNode getEnd();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalEdge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */