package org.opengis.metadata.quality;

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

@UML(identifier = "DQ_Element", specification = Specification.ISO_19115)
public interface Element {
  @UML(identifier = "nameOfMeasure", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getNamesOfMeasure();
  
  @UML(identifier = "measureIdentification", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Identifier getMeasureIdentification();
  
  @UML(identifier = "measureDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getMeasureDescription();
  
  @UML(identifier = "evaluationMethodType", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  EvaluationMethodType getEvaluationMethodType();
  
  @UML(identifier = "evaluationMethodDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getEvaluationMethodDescription();
  
  @UML(identifier = "evaluationProcedure", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Citation getEvaluationProcedure();
  
  @UML(identifier = "dateTime", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Date> getDates();
  
  @UML(identifier = "result", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends Result> getResults();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\quality\Element.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */