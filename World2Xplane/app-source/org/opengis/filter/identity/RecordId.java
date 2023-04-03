package org.opengis.filter.identity;

import org.opengis.annotation.XmlElement;

@XmlElement("RecordId")
public interface RecordId extends Identifier {
  @XmlElement("id")
  String getID();
  
  boolean matches(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\identity\RecordId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */