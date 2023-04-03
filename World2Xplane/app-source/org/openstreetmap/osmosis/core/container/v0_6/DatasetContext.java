package org.openstreetmap.osmosis.core.container.v0_6;

import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.lifecycle.Completable;
import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;

public interface DatasetContext extends Completable {
  EntityManager<Node> getNodeManager();
  
  EntityManager<Way> getWayManager();
  
  EntityManager<Relation> getRelationManager();
  
  @Deprecated
  Node getNode(long paramLong);
  
  @Deprecated
  Way getWay(long paramLong);
  
  @Deprecated
  Relation getRelation(long paramLong);
  
  ReleasableIterator<EntityContainer> iterate();
  
  ReleasableIterator<EntityContainer> iterateBoundingBox(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\DatasetContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */