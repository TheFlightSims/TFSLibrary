package org.opengis.util;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MemberName", specification = Specification.ISO_19103)
public interface MemberName extends LocalName {
  @UML(identifier = "attributeType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  TypeName getAttributeType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\MemberName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */