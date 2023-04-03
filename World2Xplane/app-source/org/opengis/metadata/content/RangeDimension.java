package org.opengis.metadata.content;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.util.MemberName;

@UML(identifier = "MD_RangeDimension", specification = Specification.ISO_19115)
public interface RangeDimension {
  @UML(identifier = "sequenceIdentifier", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  MemberName getSequenceIdentifier();
  
  @UML(identifier = "descriptor", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getDescriptor();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\RangeDimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */