/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.DataStore.RelationInfo;
/*     */ import com.world2xplane.DataStore.Tile;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.openstreetmap.osmosis.osmbinary.BinaryParser;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMPBFParserPassFive extends BinaryParser {
/*  42 */   private static Logger log = LoggerFactory.getLogger(OSMPBFParserPassFive.class);
/*     */   
/*     */   private final DataStore dataStore;
/*     */   
/*     */   private final GeneratorStore generatorStore;
/*     */   
/*     */   public OSMPBFParserPassFive(DataStore dataStore, GeneratorStore generatorStore) throws IOException {
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
/*  69 */     for (Osmformat.Way way : ways) {
/*  70 */       if (this.dataStore.wayIsInRelation(way.getId())) {
/*  71 */         WayInfo wayInfo = this.dataStore.getWayInfo(way.getId());
/*  73 */         long lastId = 0L;
/*  74 */         for (Iterator<Long> i$ = way.getRefsList().iterator(); i$.hasNext(); ) {
/*  74 */           long j = ((Long)i$.next()).longValue();
/*  76 */           double[] coords = this.dataStore.getNode(j + lastId);
/*  78 */           if (coords == null)
/*     */             continue; 
/*  82 */           int lon = (int)FastMath.floor(coords[0]);
/*  83 */           int lat = (int)FastMath.floor(coords[1]);
/*  84 */           Tile tile = new Tile();
/*  85 */           tile.lon = (short)lon;
/*  86 */           tile.lat = (short)lat;
/*  87 */           for (Long relationId : wayInfo.getRelations()) {
/*  88 */             RelationInfo relationInfo = this.dataStore.getRelationInfo(relationId.longValue());
/*  89 */             if (relationInfo.tiles == null)
/*  90 */               relationInfo.setTiles(new ArrayList()); 
/*  92 */             if (!relationInfo.tiles.contains(tile))
/*  93 */               relationInfo.tiles.add(tile); 
/*  95 */             this.dataStore.storeRelation(relationId.longValue(), relationInfo);
/*     */           } 
/*  97 */           lastId = j + lastId;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parse(Osmformat.HeaderBlock headerBlock) {}
/*     */   
/*     */   public void complete() {
/* 110 */     this.dataStore.commit();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMPBFParserPassFive.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */