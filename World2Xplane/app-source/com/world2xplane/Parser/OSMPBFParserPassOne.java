/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.world2xplane.DataStore.DataStore;
/*     */ import com.world2xplane.DataStore.RelationInfo;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.OSM.Hotspot;
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMPBFParserPassOne extends OSMBaseParser {
/*  47 */   private static Logger log = LoggerFactory.getLogger(OSMPBFParserPassOne.class);
/*     */   
/*     */   public OSMPBFParserPassOne(DataStore dataStore, GeneratorStore generatorStore) throws IOException {
/*  50 */     super(dataStore, generatorStore);
/*     */   }
/*     */   
/*     */   protected void parseRelations(List<Osmformat.Relation> relations) {
/*  57 */     for (Osmformat.Relation item : relations) {
/*  60 */       List<Integer> keys = item.getKeysList();
/*  61 */       List<Integer> vals = item.getValsList();
/*  64 */       int tagCount = 0;
/*  65 */       boolean buildingRelation = false;
/*  67 */       boolean process = true;
/*  68 */       HashMap<String, String> tags = new HashMap<>();
/*  69 */       for (int x = 0; x < keys.size(); x++) {
/*  70 */         String key = getStringById(((Integer)keys.get(x)).intValue());
/*  71 */         String val = getStringById(((Integer)vals.get(x)).intValue());
/*  72 */         if (!key.equals("type"))
/*  73 */           tagCount++; 
/*  76 */         if (key.equals("type") && val.equals("building"))
/*  77 */           buildingRelation = true; 
/*  82 */         if (key.equals("type") && (
/*  83 */           val.equals("route") || val.equals("boundary") || val.equals("restriction") || val.equals("public_transport") || val.equals("destination_sign") || val.equals("associatedStreet") || val.equals("route_master") || val.equals("enforcement"))) {
/*  86 */           process = false;
/*     */           break;
/*     */         } 
/*  92 */         if (this.generatorStore.willAcceptKeyValue(key, val) && !key.equals("type"))
/*  93 */           tags.put(key, val); 
/*     */       } 
/*  97 */       if (!process)
/*     */         continue; 
/* 103 */       List<AcceptingRule> rules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 106 */       List<Long> memberIds = item.getMemidsList();
/* 107 */       List<Integer> roleIds = item.getRolesSidList();
/* 109 */       RelationInfo relationInfo = new RelationInfo();
/* 110 */       if (rules != null && rules.size() > 0)
/* 111 */         relationInfo.rules = rules; 
/* 113 */       if (!buildingRelation && tagCount > 0 && rules.size() == 0)
/* 116 */         relationInfo.declined = true; 
/* 119 */       Float height = getHeightFromTags(tags, (Long)null, Long.valueOf(item.getId()));
/* 120 */       Float minHeight = getMinHeightFromTags(tags, (Long)null, Long.valueOf(item.getId()));
/* 121 */       Integer colorFacade = generateColoredFacade(tags, height, (Long)null, Long.valueOf(item.getId()));
/* 123 */       if (height != null)
/* 124 */         relationInfo.height = height; 
/* 127 */       if (colorFacade != null) {
/* 128 */         relationInfo.customFacade = Integer.valueOf(colorFacade.intValue());
/*     */       } else {
/* 130 */         relationInfo.customFacade = null;
/*     */       } 
/* 140 */       this.dataStore.storeRelation(item.getId(), relationInfo);
/* 143 */       long lastRef = 0L;
/* 146 */       for (int i = 0; i < memberIds.size(); i++) {
/* 147 */         long ref = lastRef + item.getMemids(i);
/* 148 */         lastRef = ref;
/* 152 */         String role = getStringById(item.getRolesSid(i));
/* 153 */         byte roleNumber = 0;
/* 154 */         if (role.equals("inner")) {
/* 155 */           roleNumber = WayInfo.INNER.byteValue();
/* 156 */         } else if (role.equals("outer")) {
/* 157 */           roleNumber = WayInfo.OUTER.byteValue();
/* 158 */         } else if (role.equals("part")) {
/* 159 */           roleNumber = WayInfo.PART.byteValue();
/* 160 */         } else if (role.equals("outline")) {
/* 161 */           roleNumber = WayInfo.OUTLINE.byteValue();
/*     */         } else {
/* 163 */           roleNumber = WayInfo.OUTER.byteValue();
/* 164 */           if (buildingRelation)
/* 165 */             roleNumber = WayInfo.OUTLINE.byteValue(); 
/*     */         } 
/* 169 */         if (item.getTypes(i) == Osmformat.Relation.MemberType.WAY) {
/* 170 */           WayInfo wayInfo = this.dataStore.getWayInfo(lastRef);
/* 172 */           if (wayInfo == null)
/* 173 */             wayInfo = new WayInfo(); 
/* 175 */           if (wayInfo.relations == null) {
/* 176 */             wayInfo.relations = new ArrayList();
/* 177 */             wayInfo.roles = new ArrayList();
/*     */           } 
/* 179 */           wayInfo.relations.add(Long.valueOf(item.getId()));
/* 180 */           wayInfo.roles.add(Byte.valueOf(roleNumber));
/* 181 */           this.dataStore.storeWay(lastRef, wayInfo);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parseDense(Osmformat.DenseNodes nodes) {
/* 192 */     long lastId = 0L, lastLat = 0L, lastLon = 0L;
/* 193 */     int j = 0;
/* 195 */     for (int i = 0; i < nodes.getIdCount(); i++) {
/* 196 */       long lat = nodes.getLat(i) + lastLat;
/* 197 */       lastLat = lat;
/* 198 */       long lon = nodes.getLon(i) + lastLon;
/* 199 */       lastLon = lon;
/* 200 */       long id = nodes.getId(i) + lastId;
/* 201 */       lastId = id;
/* 202 */       double latf = parseLat(lat), lonf = parseLon(lon);
/* 204 */       boolean accept = false;
/* 207 */       HashMap<String, String> tags = new HashMap<>();
/* 208 */       HashMap<String, String> allTags = new HashMap<>();
/* 210 */       if (nodes.getKeysValsCount() > 0) {
/* 211 */         while (nodes.getKeysVals(j) != 0) {
/* 212 */           int keyid = nodes.getKeysVals(j++);
/* 213 */           int valueId = nodes.getKeysVals(j++);
/* 215 */           String key = getStringById(keyid);
/* 216 */           String value = getStringById(valueId);
/* 217 */           if (this.generatorStore.willAcceptKeyValue(key, value))
/* 218 */             tags.put(key, value); 
/* 220 */           allTags.put(key, value);
/*     */         } 
/* 222 */         j++;
/*     */       } 
/* 224 */       if (tags.size() > 0) {
/* 227 */         for (Hotspot hotspot : this.generatorStore.getHotspots()) {
/* 228 */           if (hotspot.getFilterList().acceptsTag(tags) != null)
/* 229 */             this.generatorStore.logHotspot(hotspot, allTags, lonf, latf); 
/*     */         } 
/* 233 */         List<AcceptingRule> rules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 234 */         if (rules != null && rules.size() > 0)
/* 235 */           accept = true; 
/* 238 */         if (accept)
/* 239 */           this.dataStore.storeNode(id, lonf, latf); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parseNodes(List<Osmformat.Node> nodes) {}
/*     */   
/*     */   protected void parseWays(List<Osmformat.Way> ways) {
/* 253 */     for (Osmformat.Way way : ways) {
/* 254 */       boolean accept = false;
/* 257 */       HashMap<String, String> tags = new HashMap<>();
/* 258 */       for (int j = 0; j < way.getKeysCount(); j++) {
/* 259 */         String key = getStringById(way.getKeys(j));
/* 260 */         String val = getStringById(way.getVals(j));
/* 262 */         if (this.generatorStore.willAcceptKeyValue(key, val))
/* 263 */           tags.put(key, val); 
/*     */       } 
/* 268 */       if (tags.size() > 0) {
/* 270 */         List<AcceptingRule> rules = this.generatorStore.acceptingRulesAndFilters(tags);
/* 271 */         if (rules != null && rules.size() > 0)
/* 272 */           accept = true; 
/* 277 */         if (accept) {
/* 279 */           long lastRef = 0L;
/* 280 */           for (Long ref : way.getRefsList()) {
/* 281 */             lastRef += ref.longValue();
/* 282 */             this.dataStore.storeNode(lastRef, 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void parse(Osmformat.HeaderBlock headerBlock) {}
/*     */   
/*     */   public void complete() {
/* 295 */     this.dataStore.commit();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\OSMPBFParserPassOne.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */