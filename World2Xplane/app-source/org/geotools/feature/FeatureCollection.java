package org.geotools.feature;

import java.io.IOException;
import java.util.Collection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.FeatureVisitor;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.opengis.util.ProgressListener;

public interface FeatureCollection<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> {
  FeatureIterator<F> features();
  
  T getSchema();
  
  String getID();
  
  void accepts(FeatureVisitor paramFeatureVisitor, ProgressListener paramProgressListener) throws IOException;
  
  FeatureCollection<T, F> subCollection(Filter paramFilter);
  
  FeatureCollection<T, F> sort(SortBy paramSortBy);
  
  ReferencedEnvelope getBounds();
  
  boolean contains(Object paramObject);
  
  boolean containsAll(Collection<?> paramCollection);
  
  boolean isEmpty();
  
  int size();
  
  Object[] toArray();
  
  <O> O[] toArray(O[] paramArrayOfO);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */