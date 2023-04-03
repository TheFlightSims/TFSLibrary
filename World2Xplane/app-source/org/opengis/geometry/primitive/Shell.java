package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.complex.CompositeSurface;

@UML(identifier = "GM_Shell", specification = Specification.ISO_19107)
public interface Shell extends CompositeSurface {
  @UML(identifier = "isSimple", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isSimple();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Shell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */