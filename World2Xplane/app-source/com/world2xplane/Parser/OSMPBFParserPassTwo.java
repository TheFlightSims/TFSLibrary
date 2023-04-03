/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.DataStore.RelationInfo;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMPBFParserPassTwo extends OSMBaseParser {
/*  44 */   private static Logger log = LoggerFactory.getLogger(OSMPBFParserPassTwo.class);
/*     */   
/*     */   public OSMPBFParserPassTwo(DataStore dataStore, GeneratorStore generatorStore) throws IOException {
/*  47 */     super(dataStore, generatorStore);
/*     */   }
/*     */   
/*     */   protected void parseRelations(List<Osmformat.Relation> relations) {}
/*     */   
/*     */   protected void parseDense(Osmformat.DenseNodes nodes) {}
/*     */   
/*     */   protected void parseNodes(List<Osmformat.Node> nodes) {}
/*     */   
/*     */   protected void parseWays(List<Osmformat.Way> ways) {
/*  68 */     for (Osmformat.Way way : ways) {
/*  69 */       if (this.dataStore.wayIsInRelation(way.getId())) {
/*  73 */         WayInfo wayInfo = this.dataStore.getWayInfo(way.getId());
/*  74 */         List<Integer> keys = way.getKeysList();
/*  75 */         List<Integer> vals = way.getValsList();
/*  76 */         int index = 0;
/*  77 */         for (Long relationNumber : wayInfo.getRelations()) {
/*  78 */           if (relationNumber == null)
/*     */             continue; 
/*  81 */           short role = (short)((Byte)wayInfo.roles.get(index)).byteValue();
/*  82 */           index++;
/*  85 */           RelationInfo relationInfo = this.dataStore.getRelationInfo(relationNumber.longValue());
/*  86 */           List<AcceptingRule> relationRules = relationInfo.rules;
/*  89 */           if (relationRules == null || relationRules.size() == 0) {
/*  93 */             HashMap<String, String> tags = new HashMap<>();
/*  94 */             for (int j = 0; j < way.getKeysCount(); j++) {
/*  95 */               String key = getStringById(way.getKeys(j));
/*  96 */               String val = getStringById(way.getVals(j));
/*  97 */               if (this.generatorStore.willAcceptKeyValue(key, val))
/*  98 */                 tags.put(key, val); 
/*     */             } 
/* 101 */             List<AcceptingRule> acceptingRules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 102 */             if (acceptingRules != null && acceptingRules.size() > 0) {
/* 105 */               if (!relationInfo.declined && role == 2)
/* 106 */                 relationInfo.rules = acceptingRules; 
/* 111 */               relationInfo.wayAccepted = true;
/* 112 */               this.dataStore.storeWay(way.getId(), wayInfo);
/* 113 */               this.dataStore.storeRelation(relationNumber.longValue(), relationInfo);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parse(Osmformat.HeaderBlock headerBlock) {}
/*     */   
/*     */   public void complete() {
/* 134 */     this.dataStore.commit();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMPBFParserPassTwo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */