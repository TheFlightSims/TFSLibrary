package org.opengis.referencing;

import java.util.Collection;
import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;

@UML(identifier = "IO_IdentifiedObject", specification = Specification.ISO_19111)
public interface IdentifiedObject {
  public static final String NAME_KEY = "name";
  
  public static final String ALIAS_KEY = "alias";
  
  public static final String IDENTIFIERS_KEY = "identifiers";
  
  public static final String REMARKS_KEY = "remarks";
  
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  ReferenceIdentifier getName();
  
  @UML(identifier = "alias", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Collection<GenericName> getAlias();
  
  @UML(identifier = "identifier", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Set<ReferenceIdentifier> getIdentifiers();
  
  @UML(identifier = "remarks", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  InternationalString getRemarks();
  
  String toWKT() throws UnsupportedOperationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\IdentifiedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */