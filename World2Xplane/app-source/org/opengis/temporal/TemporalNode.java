package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Node", specification = Specification.ISO_19108)
public interface TemporalNode extends TemporalTopologicalPrimitive {
  @UML(identifier = "Realization", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Instant getRealization();
  
  @UML(identifier = "previousEdge", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  TemporalEdge getPreviousEdge();
  
  @UML(identifier = "nextEdge", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  TemporalEdge getNextEdge();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */