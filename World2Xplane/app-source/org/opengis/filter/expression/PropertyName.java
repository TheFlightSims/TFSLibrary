package org.opengis.filter.expression;

import org.opengis.annotation.XmlElement;
import org.xml.sax.helpers.NamespaceSupport;

@XmlElement("PropertyName")
public interface PropertyName extends Expression {
  String getPropertyName();
  
  NamespaceSupport getNamespaceContext();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\PropertyName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */