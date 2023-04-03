package org.geotools.data.shapefile.index;

import java.io.IOException;
import java.util.Iterator;

public interface CloseableIterator<E> extends Iterator<E> {
  void close() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\CloseableIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */