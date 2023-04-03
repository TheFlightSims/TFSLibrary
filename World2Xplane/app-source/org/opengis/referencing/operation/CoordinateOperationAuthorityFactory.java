package org.opengis.referencing.operation;

import java.util.Set;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

@UML(identifier = "CT_CoordinateTransformationAuthorityFactory", specification = Specification.OGC_01009)
public interface CoordinateOperationAuthorityFactory extends AuthorityFactory {
  @UML(identifier = "createFromTransformationCode", specification = Specification.OGC_01009)
  CoordinateOperation createCoordinateOperation(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  @UML(identifier = "createFromCoordinateSystemCodes", specification = Specification.OGC_01009)
  Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String paramString1, String paramString2) throws NoSuchAuthorityCodeException, FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\CoordinateOperationAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */