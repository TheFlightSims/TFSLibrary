package org.opengis.util;

import java.util.Map;
import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "RecordType", specification = Specification.ISO_19103)
public interface RecordType {
  @UML(identifier = "typeName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  TypeName getTypeName();
  
  @UML(identifier = "container", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19103)
  RecordSchema getContainer();
  
  @UML(identifier = "attributeTypes", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  Map<MemberName, TypeName> getAttributeTypes();
  
  Set<MemberName> getMembers();
  
  @UML(identifier = "locate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  TypeName locate(MemberName paramMemberName);
  
  boolean isInstance(Record paramRecord);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\RecordType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */