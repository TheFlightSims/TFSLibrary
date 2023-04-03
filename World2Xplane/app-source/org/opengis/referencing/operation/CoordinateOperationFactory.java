package org.opengis.referencing.operation;

import java.util.Map;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@UML(identifier = "CT_CoordinateTransformationFactory", specification = Specification.OGC_01009)
public interface CoordinateOperationFactory extends ObjectFactory {
  @UML(identifier = "createFromCoordinateSystems", specification = Specification.OGC_01009)
  CoordinateOperation createOperation(CoordinateReferenceSystem paramCoordinateReferenceSystem1, CoordinateReferenceSystem paramCoordinateReferenceSystem2) throws OperationNotFoundException, FactoryException;
  
  CoordinateOperation createOperation(CoordinateReferenceSystem paramCoordinateReferenceSystem1, CoordinateReferenceSystem paramCoordinateReferenceSystem2, OperationMethod paramOperationMethod) throws OperationNotFoundException, FactoryException;
  
  CoordinateOperation createConcatenatedOperation(Map<String, ?> paramMap, CoordinateOperation[] paramArrayOfCoordinateOperation) throws FactoryException;
  
  Conversion createDefiningConversion(Map<String, ?> paramMap, OperationMethod paramOperationMethod, ParameterValueGroup paramParameterValueGroup) throws FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\CoordinateOperationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */