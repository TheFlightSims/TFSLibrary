package org.opengis.util;

import java.util.Collection;
import java.util.Map;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "RecordSchema", specification = Specification.ISO_19103)
public interface RecordSchema {
  @UML(identifier = "schemaName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  LocalName getSchemaName();
  
  @UML(identifier = "description", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  Map<TypeName, RecordType> getDescription();
  
  @UML(identifier = "element", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19103)
  Collection<RecordType> getElements();
  
  @UML(identifier = "locate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  RecordType locate(TypeName paramTypeName);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\RecordSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */