/*    */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.util.CollectionWrapper;
/*    */ 
/*    */ public class UnmodifiableTagCollection extends CollectionWrapper<Tag> implements TagCollection {
/*    */   private TagCollection wrappedTags;
/*    */   
/*    */   public UnmodifiableTagCollection(TagCollection wrappedTags) {
/* 29 */     super(Collections.unmodifiableCollection(wrappedTags));
/* 31 */     this.wrappedTags = wrappedTags;
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 40 */     this.wrappedTags.store(sw, scr);
/*    */   }
/*    */   
/*    */   public Map<String, String> buildMap() {
/* 49 */     return this.wrappedTags.buildMap();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\UnmodifiableTagCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */