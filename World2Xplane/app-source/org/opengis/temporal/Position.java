package org.opengis.temporal;

import java.sql.Time;
import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_Position", specification = Specification.ISO_19108)
public interface Position {
  @UML(identifier = "anyOther", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  TemporalPosition anyOther();
  
  @UML(identifier = "date8601", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Date getDate();
  
  @UML(identifier = "time8601", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Time getTime();
  
  @UML(identifier = "dateTime8601", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  InternationalString getDateTime();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\Position.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */