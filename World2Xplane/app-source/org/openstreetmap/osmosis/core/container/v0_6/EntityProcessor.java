package org.openstreetmap.osmosis.core.container.v0_6;

public interface EntityProcessor {
  void process(BoundContainer paramBoundContainer);
  
  void process(NodeContainer paramNodeContainer);
  
  void process(WayContainer paramWayContainer);
  
  void process(RelationContainer paramRelationContainer);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\EntityProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */