package com.world2xplane.Parser;

import com.world2xplane.XPlane.DSFTile;
import java.util.List;
import math.geom2d.Point2D;

public interface ExternalProcessor {
  List<Point2D> getBoundCoords();
  
  boolean acceptsTile(DSFTile paramDSFTile);
  
  void process(DSFTile paramDSFTile);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\ExternalProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */