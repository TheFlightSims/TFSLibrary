package org.opengis.filter.identity;

import org.opengis.annotation.XmlElement;

@XmlElement("FeatureId")
public interface FeatureId extends Identifier {
  public static final char VERSION_SEPARATOR = '@';
  
  @XmlElement("fid")
  String getID();
  
  boolean matches(Object paramObject);
  
  boolean equalsExact(FeatureId paramFeatureId);
  
  boolean equalsFID(FeatureId paramFeatureId);
  
  @XmlElement("rid")
  String getRid();
  
  @XmlElement("previousRid")
  String getPreviousRid();
  
  String getFeatureVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\identity\FeatureId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */