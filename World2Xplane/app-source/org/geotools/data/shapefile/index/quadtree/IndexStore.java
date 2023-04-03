package org.geotools.data.shapefile.index.quadtree;

import org.geotools.data.shapefile.shp.IndexFile;

public interface IndexStore {
  void store(QuadTree paramQuadTree) throws StoreException;
  
  QuadTree load(IndexFile paramIndexFile, boolean paramBoolean) throws StoreException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\IndexStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */