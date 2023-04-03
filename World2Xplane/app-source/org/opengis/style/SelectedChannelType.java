package org.opengis.style;

import org.opengis.annotation.XmlElement;

@XmlElement("SelectedChannelType")
public interface SelectedChannelType {
  @XmlElement("SourceChannelName")
  String getChannelName();
  
  @XmlElement("SelectedChannelType")
  ContrastEnhancement getContrastEnhancement();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\SelectedChannelType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */