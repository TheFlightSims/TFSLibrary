/*     */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.EntityType;
/*     */ import org.openstreetmap.osmosis.core.task.common.ChangeAction;
/*     */ 
/*     */ public class ChangeForSeekableApplierComparator implements Comparator<ChangeContainer> {
/*     */   private int calculateSortWeight(ChangeContainer changeEntity) {
/*  46 */     ChangeAction action = changeEntity.getAction();
/*  47 */     Entity entity = changeEntity.getEntityContainer().getEntity();
/*  49 */     if (entity.getType().equals(EntityType.Bound)) {
/*  50 */       if (action.equals(ChangeAction.Create))
/*  51 */         return 1; 
/*  53 */       if (action.equals(ChangeAction.Modify))
/*  54 */         return 8; 
/*  56 */       if (action.equals(ChangeAction.Delete))
/*  57 */         return 12; 
/*  59 */     } else if (entity.getType().equals(EntityType.Node)) {
/*  60 */       if (action.equals(ChangeAction.Create))
/*  61 */         return 2; 
/*  63 */       if (action.equals(ChangeAction.Modify))
/*  64 */         return 7; 
/*  66 */       if (action.equals(ChangeAction.Delete))
/*  67 */         return 11; 
/*  69 */     } else if (entity.getType().equals(EntityType.Way)) {
/*  70 */       if (action.equals(ChangeAction.Create))
/*  71 */         return 3; 
/*  73 */       if (action.equals(ChangeAction.Modify))
/*  74 */         return 6; 
/*  76 */       if (action.equals(ChangeAction.Delete))
/*  77 */         return 10; 
/*  79 */     } else if (entity.getType().equals(EntityType.Relation)) {
/*  80 */       if (action.equals(ChangeAction.Create))
/*  81 */         return 4; 
/*  83 */       if (action.equals(ChangeAction.Modify))
/*  84 */         return 5; 
/*  86 */       if (action.equals(ChangeAction.Delete))
/*  87 */         return 9; 
/*     */     } 
/*  91 */     throw new OsmosisRuntimeException("The change entity with action " + action + " type " + entity.getType() + " and id " + entity.getId() + " was not recognised.");
/*     */   }
/*     */   
/*     */   public int compare(ChangeContainer o1, ChangeContainer o2) {
/* 104 */     return calculateSortWeight(o1) - calculateSortWeight(o2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\ChangeForSeekableApplierComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */