package org.opengis.referencing.operation;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.Factory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchIdentifierException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;

@UML(identifier = "CT_MathTransformFactory", specification = Specification.OGC_01009)
public interface MathTransformFactory extends Factory {
  Set<OperationMethod> getAvailableMethods(Class<? extends Operation> paramClass);
  
  OperationMethod getLastMethodUsed();
  
  ParameterValueGroup getDefaultParameters(String paramString) throws NoSuchIdentifierException;
  
  MathTransform createBaseToDerived(CoordinateReferenceSystem paramCoordinateReferenceSystem, ParameterValueGroup paramParameterValueGroup, CoordinateSystem paramCoordinateSystem) throws NoSuchIdentifierException, FactoryException;
  
  @UML(identifier = "createParameterizedTransform", obligation = Obligation.MANDATORY, specification = Specification.OGC_01009)
  MathTransform createParameterizedTransform(ParameterValueGroup paramParameterValueGroup) throws NoSuchIdentifierException, FactoryException;
  
  @UML(identifier = "createAffineTransform", obligation = Obligation.MANDATORY, specification = Specification.OGC_01009)
  MathTransform createAffineTransform(Matrix paramMatrix) throws FactoryException;
  
  @UML(identifier = "createConcatenatedTransform", obligation = Obligation.MANDATORY, specification = Specification.OGC_01009)
  MathTransform createConcatenatedTransform(MathTransform paramMathTransform1, MathTransform paramMathTransform2) throws FactoryException;
  
  @UML(identifier = "createPassThroughTransform", obligation = Obligation.MANDATORY, specification = Specification.OGC_01009)
  MathTransform createPassThroughTransform(int paramInt1, MathTransform paramMathTransform, int paramInt2) throws FactoryException;
  
  @UML(identifier = "createFromXML", obligation = Obligation.MANDATORY, specification = Specification.OGC_01009)
  MathTransform createFromXML(String paramString) throws FactoryException;
  
  @UML(identifier = "createFromWKT", obligation = Obligation.MANDATORY, specification = Specification.OGC_01009)
  MathTransform createFromWKT(String paramString) throws FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\operation\MathTransformFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */