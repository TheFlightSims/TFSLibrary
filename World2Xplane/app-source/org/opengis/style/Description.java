package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.util.InternationalString;

@XmlElement("Description")
public interface Description {
  @XmlElement("Title")
  InternationalString getTitle();
  
  @XmlElement("Abstract")
  InternationalString getAbstract();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Description.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */