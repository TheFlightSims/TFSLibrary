package org.opengis.referencing.datum;

import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.util.InternationalString;

@UML(identifier = "CD_Datum", specification = Specification.ISO_19111)
public interface Datum extends IdentifiedObject {
  public static final String ANCHOR_POINT_KEY = "anchorPoint";
  
  public static final String REALIZATION_EPOCH_KEY = "realizationEpoch";
  
  public static final String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";
  
  public static final String SCOPE_KEY = "scope";
  
  @UML(identifier = "anchorPoint", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  InternationalString getAnchorPoint();
  
  @UML(identifier = "realizationEpoch", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Date getRealizationEpoch();
  
  @UML(identifier = "domainOfValidity", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Extent getDomainOfValidity();
  
  @UML(identifier = "scope", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  InternationalString getScope();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\Datum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */