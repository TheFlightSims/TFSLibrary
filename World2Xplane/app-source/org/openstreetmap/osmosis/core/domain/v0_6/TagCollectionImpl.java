/*    */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.util.CollectionWrapper;
/*    */ import org.openstreetmap.osmosis.core.util.IntAsChar;
/*    */ 
/*    */ public class TagCollectionImpl extends CollectionWrapper<Tag> implements TagCollection {
/*    */   public TagCollectionImpl() {
/* 27 */     super(new ArrayList());
/*    */   }
/*    */   
/*    */   public TagCollectionImpl(Collection<? extends Tag> tags) {
/* 38 */     super(new ArrayList<Tag>(tags));
/*    */   }
/*    */   
/*    */   public TagCollectionImpl(StoreReader sr, StoreClassRegister scr) {
/* 52 */     super(new ArrayList());
/* 56 */     int tagCount = sr.readCharacter();
/* 57 */     for (int i = 0; i < tagCount; i++)
/* 58 */       add(new Tag(sr, scr)); 
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 68 */     sw.writeCharacter(IntAsChar.intToChar(size()));
/* 69 */     for (Tag tag : this)
/* 70 */       tag.store(sw, scr); 
/*    */   }
/*    */   
/*    */   public Map<String, String> buildMap() {
/* 81 */     Map<String, String> tagMap = new HashMap<String, String>(size());
/* 82 */     for (Tag tag : this)
/* 83 */       tagMap.put(tag.getKey(), tag.getValue()); 
/* 86 */     return tagMap;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\TagCollectionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */