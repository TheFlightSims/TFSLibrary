package org.opengis.coverage;

import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.operation.MathTransform1D;
import org.opengis.util.InternationalString;

@UML(identifier = "CV_SampleDimension", specification = Specification.OGC_01004)
public interface SampleDimension {
  @UML(identifier = "description", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  InternationalString getDescription();
  
  @UML(identifier = "sampleDimensionType", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  SampleDimensionType getSampleDimensionType();
  
  @UML(identifier = "categoryNames", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  InternationalString[] getCategoryNames();
  
  @UML(identifier = "colorInterpretation", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  ColorInterpretation getColorInterpretation();
  
  @UML(identifier = "paletteInterpretation", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  PaletteInterpretation getPaletteInterpretation();
  
  @UML(identifier = "palette", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  int[][] getPalette();
  
  @UML(identifier = "noDataValue", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  double[] getNoDataValues();
  
  @UML(identifier = "minimumValue", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  double getMinimumValue();
  
  @UML(identifier = "maximumValue", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  double getMaximumValue();
  
  @UML(identifier = "units", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  Unit<?> getUnits();
  
  @UML(identifier = "offset", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  double getOffset();
  
  @UML(identifier = "scale", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  double getScale();
  
  MathTransform1D getSampleToGeophysics();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\SampleDimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */