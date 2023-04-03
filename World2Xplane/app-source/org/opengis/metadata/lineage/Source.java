package org.opengis.metadata.lineage;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.identification.RepresentativeFraction;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;

@UML(identifier = "LI_Source", specification = Specification.ISO_19115)
public interface Source {
  @UML(identifier = "description", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  InternationalString getDescription();
  
  @UML(identifier = "scaleDenominator", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  RepresentativeFraction getScaleDenominator();
  
  @UML(identifier = "sourceReferenceSystem", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  ReferenceSystem getSourceReferenceSystem();
  
  @UML(identifier = "sourceCitation", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Citation getSourceCitation();
  
  @UML(identifier = "sourceExtent", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends Extent> getSourceExtents();
  
  @UML(identifier = "sourceStep", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ProcessStep> getSourceSteps();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\lineage\Source.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */