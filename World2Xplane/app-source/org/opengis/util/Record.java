package org.opengis.util;

import java.util.Map;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "Record", specification = Specification.ISO_19103)
public interface Record {
  @UML(identifier = "recordType", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19103)
  RecordType getRecordType();
  
  @UML(identifier = "attributes", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  Map<MemberName, Object> getAttributes();
  
  @UML(identifier = "locate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  Object locate(MemberName paramMemberName);
  
  void set(MemberName paramMemberName, Object paramObject) throws UnsupportedOperationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\Record.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */