package org.geotools.data.simple;

import org.geotools.data.DelegatingFeatureWriter;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

public interface DelegatingSimpleFeatureWriter extends DelegatingFeatureWriter<SimpleFeatureType, SimpleFeature>, SimpleFeatureWriter {
  SimpleFeatureWriter getDelegate();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\DelegatingSimpleFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */