package org.opengis.metadata.citation;

import java.net.URI;
import java.util.Collection;
import org.opengis.referencing.Factory;
import org.opengis.util.InternationalString;

public interface CitationFactory extends Factory {
  Address createAddress(Collection<String> paramCollection1, InternationalString paramInternationalString1, InternationalString paramInternationalString2, String paramString, InternationalString paramInternationalString3, Collection<String> paramCollection2);
  
  Contact createContact(Telephone paramTelephone, Address paramAddress, OnLineResource paramOnLineResource, InternationalString paramInternationalString1, InternationalString paramInternationalString2);
  
  OnLineResource createOnLineResource(URI paramURI, String paramString1, String paramString2, InternationalString paramInternationalString, OnLineFunction paramOnLineFunction);
  
  ResponsibleParty createResponsibleParty(String paramString, InternationalString paramInternationalString1, InternationalString paramInternationalString2, Contact paramContact, Role paramRole);
  
  Telephone createTelephone(String paramString1, String paramString2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\CitationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */