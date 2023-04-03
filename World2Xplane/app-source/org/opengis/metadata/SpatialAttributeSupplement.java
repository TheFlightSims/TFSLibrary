package org.opengis.metadata;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_SpatialAttributeSupplement", specification = Specification.ISO_19115)
public interface SpatialAttributeSupplement {
  @UML(identifier = "theFeatureTypeList", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<FeatureTypeList> getFeatureTypeList();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\SpatialAttributeSupplement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */