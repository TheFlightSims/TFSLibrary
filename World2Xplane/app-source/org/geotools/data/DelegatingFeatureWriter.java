package org.geotools.data;

public interface DelegatingFeatureWriter<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> extends FeatureWriter<T, F> {
  FeatureWriter<T, F> getDelegate();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DelegatingFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */