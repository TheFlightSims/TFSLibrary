/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectReader;
/*    */ import org.openstreetmap.osmosis.core.store.GenericObjectWriter;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ import org.openstreetmap.osmosis.core.task.common.ChangeAction;
/*    */ 
/*    */ public class ChangeContainer implements Storeable {
/*    */   private EntityContainer entityContainer;
/*    */   
/*    */   private ChangeAction action;
/*    */   
/*    */   public ChangeContainer(EntityContainer entityContainer, ChangeAction action) {
/* 33 */     this.entityContainer = entityContainer;
/* 34 */     this.action = action;
/*    */   }
/*    */   
/*    */   public ChangeContainer(StoreReader sr, StoreClassRegister scr) {
/* 48 */     this.entityContainer = (EntityContainer)(new GenericObjectReader(sr, scr)).readObject();
/* 49 */     this.action = ChangeAction.valueOf(sr.readString());
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 57 */     (new GenericObjectWriter(sw, scr)).writeObject(this.entityContainer);
/* 58 */     sw.writeString(this.action.toString());
/*    */   }
/*    */   
/*    */   public EntityContainer getEntityContainer() {
/* 68 */     return this.entityContainer;
/*    */   }
/*    */   
/*    */   public ChangeAction getAction() {
/* 78 */     return this.action;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\ChangeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */