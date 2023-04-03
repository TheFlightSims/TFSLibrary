package org.opengis.metadata.distribution;

import java.util.Collection;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Medium", specification = Specification.ISO_19115)
public interface Medium {
  @UML(identifier = "name", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  MediumName getName();
  
  @UML(identifier = "density", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<Double> getDensities();
  
  @UML(identifier = "densityUnits", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Unit<?> getDensityUnits();
  
  @UML(identifier = "volumes", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Integer getVolumes();
  
  @UML(identifier = "mediumFormat", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<MediumFormat> getMediumFormats();
  
  @UML(identifier = "mediumNote", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getMediumNote();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\Medium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */