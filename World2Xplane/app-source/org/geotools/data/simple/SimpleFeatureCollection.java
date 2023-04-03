package org.geotools.data.simple;

import org.geotools.feature.FeatureCollection;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;

public interface SimpleFeatureCollection extends FeatureCollection<SimpleFeatureType, SimpleFeature> {
  SimpleFeatureIterator features();
  
  SimpleFeatureCollection subCollection(Filter paramFilter);
  
  SimpleFeatureCollection sort(SortBy paramSortBy);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\simple\SimpleFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */