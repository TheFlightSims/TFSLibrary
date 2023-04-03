package org.opengis.metadata.identification;

import java.net.URI;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_BrowseGraphic", specification = Specification.ISO_19115)
public interface BrowseGraphic {
  @UML(identifier = "fileName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  URI getFileName();
  
  @UML(identifier = "fileDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getFileDescription();
  
  @UML(identifier = "fileType", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getFileType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\BrowseGraphic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */