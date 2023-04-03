package org.opengis.style;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;
import org.opengis.filter.Filter;
import org.opengis.metadata.citation.OnLineResource;

@XmlElement("Rule")
@UML(identifier = "PF_PortrayalRule", specification = Specification.ISO_19117)
public interface Rule {
  @XmlElement("Name")
  @UML(identifier = "ruleName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19117)
  String getName();
  
  @XmlElement("Description")
  @UML(identifier = "description", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19117)
  Description getDescription();
  
  @XmlElement("LegendGraphic")
  GraphicLegend getLegend();
  
  @XmlElement("Filter")
  @UML(identifier = "queryStatement", obligation = Obligation.MANDATORY, specification = Specification.ISO_19117)
  Filter getFilter();
  
  @XmlElement("ElseFilter")
  boolean isElseFilter();
  
  @XmlElement("MinScaleDenominator")
  double getMinScaleDenominator();
  
  @XmlElement("MaxScaleDenominator")
  double getMaxScaleDenominator();
  
  @XmlElement("Symbolizer")
  @UML(identifier = "portrayAction", obligation = Obligation.MANDATORY, specification = Specification.ISO_19117)
  List<? extends Symbolizer> symbolizers();
  
  OnLineResource getOnlineResource();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Rule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */