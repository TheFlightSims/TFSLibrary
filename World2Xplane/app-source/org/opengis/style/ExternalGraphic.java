package org.opengis.style;

import java.util.Collection;
import javax.swing.Icon;
import org.opengis.annotation.XmlElement;
import org.opengis.metadata.citation.OnLineResource;

@XmlElement("ExternalGraphic")
public interface ExternalGraphic extends GraphicalSymbol {
  @XmlElement("OnlineResource")
  OnLineResource getOnlineResource();
  
  @XmlElement("InlineContent")
  Icon getInlineContent();
  
  @XmlElement("Format")
  String getFormat();
  
  @XmlElement("ColorReplacement")
  Collection<ColorReplacement> getColorReplacements();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ExternalGraphic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */