package org.geotools.referencing.factory.gridshift;

import java.net.URL;
import org.opengis.referencing.Factory;

public interface GridShiftLocator extends Factory {
  URL locateGrid(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\gridshift\GridShiftLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */