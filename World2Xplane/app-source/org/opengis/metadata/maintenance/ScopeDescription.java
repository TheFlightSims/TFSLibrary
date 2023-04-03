package org.opengis.metadata.maintenance;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;

@UML(identifier = "MD_ScopeDescription", specification = Specification.ISO_19115)
public interface ScopeDescription {
  @UML(identifier = "attributes", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Set<? extends AttributeType> getAttributes();
  
  @UML(identifier = "features", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Set<? extends FeatureType> getFeatures();
  
  @UML(identifier = "featureInstances", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Set<? extends FeatureType> getFeatureInstances();
  
  @UML(identifier = "attributeInstances", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Set<? extends AttributeType> getAttributeInstances();
  
  @UML(identifier = "dataset", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  String getDataset();
  
  @UML(identifier = "other", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  String getOther();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\maintenance\ScopeDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */