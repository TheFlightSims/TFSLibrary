package org.geotools.data;

public interface DelegatingFeatureReader<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> extends FeatureReader<T, F> {
  FeatureReader<T, F> getDelegate();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DelegatingFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */