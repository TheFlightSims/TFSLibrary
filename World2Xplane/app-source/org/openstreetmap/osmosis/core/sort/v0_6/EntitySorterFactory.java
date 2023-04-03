/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkSourceManager;
/*    */ 
/*    */ public class EntitySorterFactory extends TaskManagerFactory {
/*    */   private static final String ARG_COMPARATOR_TYPE = "type";
/*    */   
/* 32 */   private Map<String, Comparator<EntityContainer>> comparatorMap = new HashMap<String, Comparator<EntityContainer>>();
/*    */   
/*    */   private String defaultComparatorType;
/*    */   
/*    */   public void registerComparator(String comparatorType, Comparator<EntityContainer> comparator, boolean setAsDefault) {
/* 49 */     if (this.comparatorMap.containsKey(comparatorType))
/* 50 */       throw new OsmosisRuntimeException("Comparator type \"" + comparatorType + "\" already exists."); 
/* 53 */     if (setAsDefault)
/* 54 */       this.defaultComparatorType = comparatorType; 
/* 57 */     this.comparatorMap.put(comparatorType, comparator);
/*    */   }
/*    */   
/*    */   private Comparator<EntityContainer> getComparator(String comparatorType) {
/* 69 */     if (!this.comparatorMap.containsKey(comparatorType))
/* 70 */       throw new OsmosisRuntimeException("Comparator type " + comparatorType + " doesn't exist."); 
/* 74 */     return this.comparatorMap.get(comparatorType);
/*    */   }
/*    */   
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 86 */     Comparator<EntityContainer> comparator = getComparator(getStringArgument(taskConfig, "type", getDefaultStringArgument(taskConfig, this.defaultComparatorType)));
/* 94 */     return (TaskManager)new SinkSourceManager(taskConfig.getId(), new EntitySorter(comparator), taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\EntitySorterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */