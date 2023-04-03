package org.opengis.style;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;

@UML(identifier = "PF_PortrayalCatalog", specification = Specification.ISO_19117)
@XmlElement("UserStyle")
public interface Style {
  @XmlElement("UserStyle")
  String getName();
  
  @XmlElement("Description")
  Description getDescription();
  
  @XmlElement("IsDefault")
  boolean isDefault();
  
  @UML(identifier = "featurePortrayal", obligation = Obligation.MANDATORY, specification = Specification.ISO_19117)
  @XmlElement("FeatureTypeStyle")
  List<? extends FeatureTypeStyle> featureTypeStyles();
  
  @UML(identifier = "defaultPortrayalSpec", obligation = Obligation.MANDATORY, specification = Specification.ISO_19117)
  Symbolizer getDefaultSpecification();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Style.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */