package org.geotools.data.crs;

import java.io.IOException;
import java.util.Set;
import org.geotools.factory.Factory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface CRSAuthoritySpi extends Factory {
  Set getCodes();
  
  CoordinateReferenceSystem decode(String paramString) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\CRSAuthoritySpi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */