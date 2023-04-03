package org.opengis.metadata.maintenance;

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.temporal.PeriodDuration;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_MaintenanceInformation", specification = Specification.ISO_19115)
public interface MaintenanceInformation {
  @UML(identifier = "maintenanceAndUpdateFrequency", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  MaintenanceFrequency getMaintenanceAndUpdateFrequency();
  
  @UML(identifier = "dateOfNextUpdate", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Date getDateOfNextUpdate();
  
  @UML(identifier = "userDefinedMaintenanceFrequency", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  PeriodDuration getUserDefinedMaintenanceFrequency();
  
  @UML(identifier = "updateScope", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<ScopeCode> getUpdateScopes();
  
  @UML(identifier = "updateScopeDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ScopeDescription> getUpdateScopeDescriptions();
  
  @UML(identifier = "maintenanceNote", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getMaintenanceNotes();
  
  @UML(identifier = "contact", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ResponsibleParty> getContacts();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\maintenance\MaintenanceInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */