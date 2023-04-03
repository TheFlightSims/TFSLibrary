package org.opengis.metadata.quality;

import java.util.Collection;
import javax.measure.unit.Unit;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.util.RecordType;

@UML(identifier = "DQ_QuantitativeResult", specification = Specification.ISO_19115)
public interface QuantitativeResult extends Result {
  @UML(identifier = "value", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends Record> getValues();
  
  @UML(identifier = "valueType", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  RecordType getValueType();
  
  @UML(identifier = "valueUnit", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Unit<?> getValueUnit();
  
  @UML(identifier = "errorStatistic", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getErrorStatistic();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\quality\QuantitativeResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */