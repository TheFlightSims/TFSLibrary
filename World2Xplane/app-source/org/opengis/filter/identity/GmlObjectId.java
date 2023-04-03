package org.opengis.filter.identity;

import org.opengis.annotation.XmlElement;

@XmlElement("GMLObjectId")
public interface GmlObjectId extends Identifier {
  @XmlElement("id")
  String getID();
  
  boolean matches(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\identity\GmlObjectId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */