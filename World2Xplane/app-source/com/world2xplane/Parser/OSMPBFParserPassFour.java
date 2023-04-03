/*    */ package com.world2xplane.Parser;
/*    */ 
/*    */ import com.world2xplane.DataStore.DataStore;
/*    */ import com.world2xplane.Rules.GeneratorStore;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.openstreetmap.osmosis.osmbinary.BinaryParser;
/*    */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class OSMPBFParserPassFour extends BinaryParser {
/* 40 */   private static Logger log = LoggerFactory.getLogger(OSMPBFParserPassFour.class);
/*    */   
/*    */   private final DataStore dataStore;
/*    */   
/*    */   private final GeneratorStore generatorStore;
/*    */   
/*    */   public OSMPBFParserPassFour(DataStore dataStore, GeneratorStore generatorStore) throws IOException {
/* 46 */     this.dataStore = dataStore;
/* 47 */     this.generatorStore = generatorStore;
/*    */   }
/*    */   
/*    */   protected void parseRelations(List<Osmformat.Relation> relations) {}
/*    */   
/*    */   protected void parseDense(Osmformat.DenseNodes nodes) {
/* 57 */     long lastId = 0L, lastLat = 0L, lastLon = 0L;
/* 58 */     for (int i = 0; i < nodes.getIdCount(); i++) {
/* 59 */       long lat = nodes.getLat(i) + lastLat;
/* 60 */       lastLat = lat;
/* 61 */       long lon = nodes.getLon(i) + lastLon;
/* 62 */       lastLon = lon;
/* 63 */       long id = nodes.getId(i) + lastId;
/* 64 */       lastId = id;
/* 65 */       double latf = parseLat(lat), lonf = parseLon(lon);
/* 68 */       if (this.dataStore.hasNode(id))
/* 70 */         this.dataStore.storeNode(id, lonf, latf); 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void parseNodes(List<Osmformat.Node> nodes) {}
/*    */   
/*    */   protected void parseWays(List<Osmformat.Way> ways) {}
/*    */   
/*    */   protected void parse(Osmformat.HeaderBlock headerBlock) {}
/*    */   
/*    */   public void complete() {
/* 92 */     this.dataStore.commit();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMPBFParserPassFour.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */