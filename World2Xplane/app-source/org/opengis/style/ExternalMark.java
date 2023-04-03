package org.opengis.style;

import javax.swing.Icon;
import org.opengis.annotation.XmlElement;
import org.opengis.metadata.citation.OnLineResource;

public interface ExternalMark {
  @XmlElement("OnlineResource")
  OnLineResource getOnlineResource();
  
  @XmlElement("InlineContent")
  Icon getInlineContent();
  
  @XmlElement("Format")
  String getFormat();
  
  @XmlElement("MarkIndex")
  int getMarkIndex();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ExternalMark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */