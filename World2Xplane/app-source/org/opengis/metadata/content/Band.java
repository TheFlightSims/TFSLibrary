package org.opengis.metadata.content;

import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_Band", specification = Specification.ISO_19115)
public interface Band extends RangeDimension {
  @UML(identifier = "maxValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getMaxValue();
  
  @UML(identifier = "minValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getMinValue();
  
  @UML(identifier = "units", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Unit<Length> getUnits();
  
  @UML(identifier = "peakResponse", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getPeakResponse();
  
  @UML(identifier = "bitsPerValue", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Integer getBitsPerValue();
  
  @UML(identifier = "toneGradation", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Integer getToneGradation();
  
  @UML(identifier = "scaleFactor", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getScaleFactor();
  
  @UML(identifier = "offset", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getOffset();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\Band.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */