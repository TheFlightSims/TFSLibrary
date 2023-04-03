package org.opengis.style;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.filter.expression.Expression;

@XmlElement("Font")
public interface Font {
  @XmlParameter("font-familly")
  List<Expression> getFamily();
  
  @XmlParameter("font-style")
  Expression getStyle();
  
  @XmlParameter("font-weight")
  Expression getWeight();
  
  @XmlParameter("font-size")
  Expression getSize();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */