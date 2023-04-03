/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.DataStore.RelationInfo;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.osmbinary.BinaryParser;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMPBFParserPassThree extends BinaryParser {
/*  42 */   private static Logger log = LoggerFactory.getLogger(OSMPBFParserPassThree.class);
/*     */   
/*     */   private final DataStore dataStore;
/*     */   
/*     */   private final GeneratorStore generatorStore;
/*     */   
/*     */   public OSMPBFParserPassThree(DataStore dataStore, GeneratorStore generatorStore) throws IOException {
/*  48 */     this.dataStore = dataStore;
/*  49 */     this.generatorStore = generatorStore;
/*     */   }
/*     */   
/*     */   protected void parseRelations(List<Osmformat.Relation> relations) {}
/*     */   
/*     */   protected void parseDense(Osmformat.DenseNodes nodes) {}
/*     */   
/*     */   protected void parseNodes(List<Osmformat.Node> nodes) {}
/*     */   
/*     */   protected void parseWays(List<Osmformat.Way> ways) {
/*  70 */     for (Osmformat.Way way : ways) {
/*  71 */       if (this.dataStore.wayIsInRelation(way.getId())) {
/*  73 */         WayInfo wayInfo = this.dataStore.getWayInfo(way.getId());
/*  74 */         boolean accepted = false;
/*  75 */         for (Long item : wayInfo.relations) {
/*  76 */           RelationInfo relationInfo = this.dataStore.getRelationInfo(item.longValue());
/*  77 */           if (relationInfo.wayAccepted) {
/*  78 */             accepted = true;
/*     */             break;
/*     */           } 
/*  81 */           if (relationInfo.rules != null && relationInfo.rules.size() > 0) {
/*  82 */             accepted = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*  89 */         if (accepted) {
/*  90 */           long lastRef = 0L;
/*  91 */           for (Long ref : way.getRefsList()) {
/*  92 */             lastRef += ref.longValue();
/*  93 */             this.dataStore.storeNode(lastRef, 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parse(Osmformat.HeaderBlock headerBlock) {}
/*     */   
/*     */   public void complete() {
/* 106 */     this.dataStore.commit();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMPBFParserPassThree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */