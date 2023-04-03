package org.opengis.style;

import java.util.List;
import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.type.Name;
import org.opengis.filter.Id;
import org.opengis.metadata.citation.OnLineResource;

@XmlElement("FeatureTypeStyle")
@UML(identifier = "PF_FeaturePortrayal", specification = Specification.ISO_19117)
public interface FeatureTypeStyle {
  @XmlElement("Name")
  String getName();
  
  @XmlElement("Description")
  @UML(identifier = "description", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19117)
  Description getDescription();
  
  @UML(identifier = "definedForInst", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19117)
  Id getFeatureInstanceIDs();
  
  @XmlElement("FeatureTypeName")
  @UML(identifier = "definedFor", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19117)
  Set<Name> featureTypeNames();
  
  @XmlElement("SemanticTypeIdentifier")
  Set<SemanticType> semanticTypeIdentifiers();
  
  @XmlElement("Rule")
  @UML(identifier = "portrayalRule", obligation = Obligation.MANDATORY, specification = Specification.ISO_19117)
  List<? extends Rule> rules();
  
  OnLineResource getOnlineResource();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\FeatureTypeStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */