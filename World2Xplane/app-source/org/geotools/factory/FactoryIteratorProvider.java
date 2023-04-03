package org.geotools.factory;

import java.util.Iterator;

public interface FactoryIteratorProvider {
  <T> Iterator<T> iterator(Class<T> paramClass);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\FactoryIteratorProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */