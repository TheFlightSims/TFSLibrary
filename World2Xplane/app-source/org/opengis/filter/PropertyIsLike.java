package org.opengis.filter;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("PropertyIsLike")
public interface PropertyIsLike extends MultiValuedFilter {
  public static final String NAME = "Like";
  
  @XmlElement("PropertyName")
  Expression getExpression();
  
  @XmlElement("Literal")
  String getLiteral();
  
  @XmlElement("wildCard")
  String getWildCard();
  
  @XmlElement("singleChar")
  String getSingleChar();
  
  @XmlElement("escape")
  String getEscape();
  
  @XmlElement("matchCase")
  boolean isMatchingCase();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\PropertyIsLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */