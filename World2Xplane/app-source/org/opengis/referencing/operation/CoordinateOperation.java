package org.opengis.referencing.operation;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

@UML(identifier = "CC_CoordinateOperation", specification = Specification.ISO_19111)
public interface CoordinateOperation extends IdentifiedObject {
  public static final String OPERATION_VERSION_KEY = "operationVersion";
  
  public static final String COORDINATE_OPERATION_ACCURACY_KEY = "coordinateOperationAccuracy";
  
  public static final String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";
  
  public static final String SCOPE_KEY = "scope";
  
  @UML(identifier = "sourceCRS", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getSourceCRS();
  
  @UML(identifier = "targetCRS", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  CoordinateReferenceSystem getTargetCRS();
  
  @UML(identifier = "operationVersion", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
  String getOperationVersion();
  
  @UML(identifier = "coordinateOperationAccuracy", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Collection<PositionalAccuracy> getCoordinateOperationAccuracy();
  
  @UML(identifier = "domainOfValidity", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Extent getDomainOfValidity();
  
  @UML(identifier = "scope", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  InternationalString getScope();
  
  @UML(identifier = "CT_CoordinateTransformation.getMathTransform", specification = Specification.OGC_01009)
  MathTransform getMathTransform();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\CoordinateOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */