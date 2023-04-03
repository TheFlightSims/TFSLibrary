package org.opengis.filter;

import org.opengis.annotation.XmlElement;

@XmlElement("Not")
public interface Not extends Filter {
  Filter getFilter();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\Not.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */