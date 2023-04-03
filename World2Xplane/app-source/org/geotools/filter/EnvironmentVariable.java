package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;

public interface EnvironmentVariable extends Expression {
  Object getValue(SimpleFeature paramSimpleFeature);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\EnvironmentVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */