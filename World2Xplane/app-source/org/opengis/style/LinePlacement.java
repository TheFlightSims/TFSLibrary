package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("LinePlacement")
public interface LinePlacement extends LabelPlacement {
  @XmlElement("PerpendicularOffset")
  Expression getPerpendicularOffset();
  
  @XmlElement("InitialGap")
  Expression getInitialGap();
  
  @XmlElement("Gap")
  Expression getGap();
  
  @XmlElement("IsRepeated")
  boolean isRepeated();
  
  @XmlElement("IsAligned")
  boolean IsAligned();
  
  @XmlElement("GeneralizeLine")
  boolean isGeneralizeLine();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\LinePlacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */