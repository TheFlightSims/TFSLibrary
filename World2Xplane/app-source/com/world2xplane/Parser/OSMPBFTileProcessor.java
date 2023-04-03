/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.DataStore.RelationInfo;
/*     */ import com.world2xplane.DataStore.Tile;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Network.NetworkItem;
/*     */ import com.world2xplane.OSM.Node;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import com.world2xplane.Rules.WayTrackerRule;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMPBFTileProcessor extends OSMBaseParser {
/*  47 */   private static Logger log = LoggerFactory.getLogger(OSMPBFTileProcessor.class);
/*     */   
/*     */   private final DSFTile dsfTile;
/*     */   
/*     */   private final OSMProcess.Hook hook;
/*     */   
/*     */   private final TagRejector tagRejector;
/*     */   
/*     */   public OSMPBFTileProcessor(DataStore dataStore, DSFTile dsfTile, GeneratorStore generatorStore, OSMProcess.Hook hook, TagRejector tagRejector) throws IOException {
/*  57 */     super(dataStore, generatorStore);
/*  58 */     this.dsfTile = dsfTile;
/*  59 */     this.hook = hook;
/*  60 */     this.tagRejector = tagRejector;
/*  63 */     for (NetworkItem item : dsfTile.getNetworkItems())
/*  64 */       item.init(dsfTile, dataStore); 
/*     */   }
/*     */   
/*     */   protected void parseRelations(List<Osmformat.Relation> relations) {}
/*     */   
/*     */   protected void parseDense(Osmformat.DenseNodes nodes) {
/*  75 */     long lastId = 0L, lastLat = 0L, lastLon = 0L;
/*  76 */     int j = 0;
/*  78 */     for (int i = 0; i < nodes.getIdCount(); i++) {
/*  79 */       long lat = nodes.getLat(i) + lastLat;
/*  80 */       lastLat = lat;
/*  81 */       long lon = nodes.getLon(i) + lastLon;
/*  82 */       lastLon = lon;
/*  83 */       long id = nodes.getId(i) + lastId;
/*  84 */       lastId = id;
/*  85 */       double latf = parseLat(lat), lonf = parseLon(lon);
/*  87 */       if (nodes.getKeysValsCount() > 0) {
/*  89 */         HashMap<String, String> tags = new HashMap<>();
/*  90 */         if (nodes.getKeysValsCount() > 0) {
/*  91 */           while (nodes.getKeysVals(j) != 0) {
/*  92 */             int keyid = nodes.getKeysVals(j++);
/*  93 */             int valueId = nodes.getKeysVals(j++);
/*  95 */             String key = getStringById(keyid);
/*  96 */             String value = getStringById(valueId);
/*  97 */             if (this.tagRejector != null && !this.tagRejector.acceptsTag(key, value))
/*     */               continue; 
/* 100 */             if (this.generatorStore.willAcceptKeyValue(key, value))
/* 101 */               tags.put(key, value); 
/*     */           } 
/* 104 */           j++;
/*     */         } 
/* 108 */         if (this.dsfTile.containsPoint(new Point2D(lonf, latf))) {
/* 110 */           List<AcceptingRule> rules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 112 */           if (rules != null && rules.size() > 0) {
/* 114 */             boolean accept = true;
/* 115 */             if (this.hook != null && 
/* 116 */               !this.hook.acceptTags(tags))
/* 117 */               accept = false; 
/* 120 */             if (accept)
/* 121 */               this.dsfTile.addNode(tags, lastId, new Point2D(lonf, latf), rules); 
/*     */           } 
/* 125 */           for (NetworkItem networkItem : this.dsfTile.getNetworkItems())
/* 126 */             networkItem.nodeRead(tags, id, lonf, latf); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parseNodes(List<Osmformat.Node> nodes) {}
/*     */   
/*     */   private void readWayInfo(Osmformat.Way way, HashMap<String, String> tags) {
/* 142 */     WayInfo wayInfo = this.dataStore.getWayInfo(way.getId());
/* 143 */     if (wayInfo.getRelations() != null && wayInfo.getRelations().size() > 0) {
/* 144 */       int index = 0;
/* 147 */       for (Long id : wayInfo.getRelations()) {
/* 149 */         Byte role = wayInfo.roles.get(index);
/* 150 */         index++;
/* 152 */         RelationInfo relationInfo = this.dataStore.getRelationInfo(id.longValue());
/* 154 */         if (relationInfo != null) {
/* 157 */           Tile tile = new Tile();
/* 158 */           tile.lon = this.dsfTile.getLongitude();
/* 159 */           tile.lat = this.dsfTile.getLatitude();
/* 160 */           boolean accept = (relationInfo.tiles != null && relationInfo.tiles.contains(tile));
/* 161 */           if (accept) {
/* 162 */             addMultiPolygon(id, way, tags, relationInfo, wayInfo, role);
/* 167 */             for (NetworkItem networkItem : this.dsfTile.getNetworkItems()) {
/* 168 */               if (networkItem.wayRead(tags, way) && 
/* 169 */                 !networkItem.isPassThrough())
/*     */                 break; 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parseWays(List<Osmformat.Way> ways) {
/* 185 */     for (Osmformat.Way way : ways) {
/* 186 */       boolean acceptTags = false;
/* 189 */       if (this.dataStore.wayIsInRelation(way.getId())) {
/* 190 */         HashMap<String, String> hashMap = new HashMap<>();
/* 191 */         for (int i = 0; i < way.getKeysCount(); i++) {
/* 192 */           String key = getStringById(way.getKeys(i));
/* 193 */           String val = getStringById(way.getVals(i));
/* 194 */           if (this.tagRejector == null || this.tagRejector.acceptsTag(key, val))
/* 197 */             if (this.generatorStore.willAcceptKeyValue(key, val))
/* 198 */               hashMap.put(key, val);  
/*     */         } 
/* 201 */         boolean bool = true;
/* 202 */         if (this.hook != null && 
/* 203 */           !this.hook.acceptTags(hashMap))
/* 204 */           bool = false; 
/* 208 */         if (bool)
/* 209 */           readWayInfo(way, hashMap); 
/*     */         continue;
/*     */       } 
/* 213 */       boolean accept = false;
/* 214 */       long lastRef = 0L;
/* 215 */       for (Long ref : way.getRefsList()) {
/* 216 */         lastRef += ref.longValue();
/* 218 */         double[] coords = this.dataStore.getNode(lastRef);
/* 219 */         if (coords != null && this.dsfTile.containsPoint(new Point2D(coords[0], coords[1]))) {
/* 220 */           accept = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 224 */       if (!accept)
/*     */         continue; 
/* 230 */       HashMap<String, String> tags = new HashMap<>();
/* 231 */       for (int j = 0; j < way.getKeysCount(); j++) {
/* 232 */         String key = getStringById(way.getKeys(j));
/* 233 */         String val = getStringById(way.getVals(j));
/* 234 */         if (this.tagRejector == null || this.tagRejector.acceptsTag(key, val))
/* 237 */           if (this.generatorStore.willAcceptKeyValue(key, val))
/* 238 */             tags.put(key, val);  
/*     */       } 
/* 242 */       List<AcceptingRule> rules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 245 */       acceptTags = (rules != null && rules.size() > 0);
/* 246 */       if (this.hook != null && 
/* 247 */         !this.hook.acceptTags(tags))
/* 248 */         acceptTags = false; 
/* 251 */       if (acceptTags) {
/* 254 */         for (NetworkItem networkItem : this.dsfTile.getNetworkItems()) {
/* 255 */           if (networkItem.wayRead(tags, way) && 
/* 256 */             !networkItem.isPassThrough())
/*     */             break; 
/*     */         } 
/* 262 */         boolean eaten = false;
/* 263 */         for (AcceptingRule entry : rules) {
/* 264 */           Rule rule = this.generatorStore.getRule(entry.ruleNumber.shortValue());
/* 265 */           if (rule instanceof WayTrackerRule) {
/* 266 */             addWayTracker(way, (WayTrackerRule)rule);
/* 267 */             if (!rule.isPassThrough()) {
/* 268 */               eaten = true;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 274 */         if (!eaten)
/* 275 */           addSimplePolygon(way, tags, rules); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addWayTracker(Osmformat.Way way, WayTrackerRule wayTrackerRule) {
/* 283 */     long lastRef = 0L;
/* 284 */     List<Node> nodes = new ArrayList<>();
/* 285 */     for (Long ref : way.getRefsList()) {
/* 286 */       lastRef += ref.longValue();
/* 288 */       double[] coords = this.dataStore.getNode(lastRef);
/* 289 */       if (coords != null)
/* 290 */         nodes.add(new Node(lastRef, coords[0], coords[1])); 
/*     */     } 
/* 293 */     this.dsfTile.addWayTracker(Long.valueOf(way.getId()), nodes, wayTrackerRule.getIdentifier());
/*     */   }
/*     */   
/*     */   private void addSimplePolygon(Osmformat.Way way, HashMap<String, String> tags, List<AcceptingRule> rules) {
/* 303 */     OSMPolygon shape = new OSMPolygon();
/* 304 */     long lastRef = 0L;
/* 305 */     for (Long ref : way.getRefsList()) {
/* 306 */       lastRef += ref.longValue();
/* 308 */       double[] coords = this.dataStore.getNode(lastRef);
/* 309 */       if (coords == null) {
/* 310 */         log.error("Can't find coordinates for node " + lastRef + " request in tile " + this.dsfTile);
/*     */         continue;
/*     */       } 
/* 313 */       Point2D point = new Point2D(coords[0], coords[1]);
/* 314 */       if (point.x() == 0.0D && point.y() == 0.0D) {
/* 315 */         log.error("Can't find coordinates for node " + lastRef + " request in tile " + this.dsfTile);
/*     */         continue;
/*     */       } 
/* 318 */       if (shape.vertexNumber() > 0 && shape.lastPoint().equals(point))
/*     */         continue; 
/* 321 */       shape.addVertex(point);
/*     */     } 
/* 326 */     if (this.hook != null && 
/* 327 */       !this.hook.acceptShape(tags, shape.getShape()))
/*     */       return; 
/* 332 */     Float ownHeight = getHeightFromTags(tags, Long.valueOf(way.getId()), (Long)null);
/* 333 */     Float minHeight = getMinHeightFromTags(tags, Long.valueOf(way.getId()), (Long)null);
/* 335 */     Integer generatedFacade = generateColoredFacade(tags, ownHeight, Long.valueOf(way.getId()), (Long)null);
/* 336 */     if (generatedFacade != null && generatedFacade.intValue() == -2)
/* 337 */       shape.setBuildingPart(true); 
/* 340 */     if (ownHeight != null)
/* 341 */       shape.setHeight(ownHeight); 
/* 345 */     if (generatedFacade != null)
/* 346 */       shape.setCustomFacade(generatedFacade); 
/* 349 */     if (this.generatorStore.isCreateBuildingParts() && ((ownHeight != null && ownHeight.floatValue() > this.generatorStore.getFacadeHeightLimit()) || tags.containsKey("building:part") || tags.containsKey("min_height") || tags.containsKey("building:min_level")))
/* 351 */       if (writeBuildingTags(tags, way.getId(), ownHeight) != null)
/* 352 */         shape.setBuildingPart(true);  
/* 356 */     shape.setIdentifier(way.getId());
/* 359 */     if (tags != null && tags.size() > 0) {
/* 361 */       List<AcceptingRule> acceptingRules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 362 */       if (acceptingRules != null && acceptingRules.size() > 0) {
/* 363 */         this.dsfTile.addSimplePolygon(shape, Long.valueOf(way.getId()), acceptingRules);
/*     */         return;
/*     */       } 
/*     */     } 
/* 369 */     if (rules != null && rules.size() > 0)
/* 371 */       this.dsfTile.addSimplePolygon(shape, Long.valueOf(way.getId()), rules); 
/*     */   }
/*     */   
/*     */   private void addMultiPolygon(Long relationId, Osmformat.Way way, HashMap<String, String> wayTags, RelationInfo relationInfo, WayInfo wayInfo, Byte role) {
/* 385 */     if (relationInfo.declined && !relationInfo.wayAccepted)
/*     */       return; 
/* 388 */     if (!relationInfo.wayAccepted && (relationInfo.rules == null || relationInfo.rules.size() == 0))
/*     */       return; 
/* 394 */     OSMPolygon shape = new OSMPolygon();
/* 395 */     long lastRef = 0L;
/* 396 */     for (Long ref : way.getRefsList()) {
/* 397 */       lastRef += ref.longValue();
/* 399 */       double[] coords = this.dataStore.getNode(lastRef);
/* 401 */       if (coords == null || (coords[0] == 0.0D && coords[1] == 0.0D)) {
/* 402 */         log.error("Can't find coordinates for node " + lastRef + " requested in tile " + this.dsfTile);
/*     */         return;
/*     */       } 
/* 405 */       Point2D point = new Point2D(coords[0], coords[1]);
/* 406 */       if (shape.vertexNumber() > 0 && shape.lastPoint().equals(point))
/*     */         continue; 
/* 410 */       shape.addVertex(point);
/*     */     } 
/* 415 */     if (this.hook != null && 
/* 416 */       !this.hook.acceptShape(wayTags, shape.getShape()))
/*     */       return; 
/* 421 */     shape.setIdentifier(way.getId());
/* 425 */     Float ownHeight = getHeightFromTags(wayTags, (Long)null, relationId);
/* 427 */     Integer generatedFacade = generateColoredFacade(wayTags, ownHeight, (Long)null, relationId);
/* 429 */     if (ownHeight != null)
/* 430 */       shape.setHeight(ownHeight); 
/* 432 */     if (generatedFacade != null && generatedFacade.intValue() == -2)
/* 433 */       shape.setBuildingPart(true); 
/* 436 */     if (this.generatorStore.isCreateBuildingParts() && ((ownHeight != null && ownHeight.floatValue() > this.generatorStore.getFacadeHeightLimit()) || wayTags.containsKey("building:part") || wayTags.containsKey("min_height") || wayTags.containsKey("building:min_level")))
/* 439 */       if (writeBuildingTags(wayTags, way.getId(), ownHeight) != null)
/* 440 */         shape.setBuildingPart(true);  
/* 444 */     if (generatedFacade != null)
/* 445 */       shape.setCustomFacade(generatedFacade); 
/* 449 */     List<AcceptingRule> acceptingRules = this.generatorStore.acceptingRulesAndFilters(wayTags);
/* 452 */     boolean eaten = false;
/* 453 */     for (AcceptingRule entry : acceptingRules) {
/* 454 */       Rule rule = this.generatorStore.getRule(entry.ruleNumber.shortValue());
/* 455 */       if (rule instanceof WayTrackerRule) {
/* 456 */         addWayTracker(way, (WayTrackerRule)rule);
/* 457 */         if (!rule.isPassThrough()) {
/* 458 */           eaten = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 464 */     if (!eaten)
/* 465 */       this.dsfTile.addMultiPolygon(relationId, shape, relationInfo, wayInfo, role.byteValue(), acceptingRules); 
/*     */   }
/*     */   
/*     */   protected void parse(Osmformat.HeaderBlock headerBlock) {}
/*     */   
/*     */   public void complete() {
/* 476 */     this.dataStore.commit();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMPBFTileProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */