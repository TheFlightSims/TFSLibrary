package org.opengis.metadata.identification;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Keywords", specification = Specification.ISO_19115)
public interface Keywords {
  @UML(identifier = "keyword", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getKeywords();
  
  @UML(identifier = "type", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  KeywordType getType();
  
  @UML(identifier = "thesaurusName", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Citation getThesaurusName();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\Keywords.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */