/*     */ package com.world2xplane.DataStore;
/*     */ 
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.mapdb.Atomic;
/*     */ import org.mapdb.DB;
/*     */ import org.mapdb.DBMaker;
/*     */ import org.mapdb.HTreeMap;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class JDBMStore implements DataStore {
/*  40 */   private static Logger log = LoggerFactory.getLogger(JDBMStore.class);
/*     */   
/*     */   private final HTreeMap<Object, RelationInfo> relations;
/*     */   
/*     */   private final HTreeMap<Object, WayInfo> ways;
/*     */   
/*     */   private final HTreeMap<Object, Node> nodeMap;
/*     */   
/*     */   private final HTreeMap<Object, double[]> tiles;
/*     */   
/*     */   private final HTreeMap<Integer, Boolean> valid;
/*     */   
/*     */   private DB db;
/*     */   
/*     */   private final boolean resume;
/*     */   
/*  53 */   int nodeCount = 0;
/*     */   
/*  54 */   int wayCount = 0;
/*     */   
/*  55 */   int relationCount = 0;
/*     */   
/*     */   static final int COMMIT_COUNT = 5000000;
/*     */   
/*     */   public JDBMStore(boolean resume, File osmFile) throws Exception {
/*  63 */     FileInputStream fis = new FileInputStream(osmFile);
/*  64 */     String md5 = DigestUtils.md5Hex(fis);
/*  66 */     log.info("Using local storage.");
/*  67 */     this.resume = resume;
/*  68 */     if (!resume)
/*  69 */       FileUtils.deleteDirectory(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db")); 
/*  71 */     if (!(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db")).exists())
/*  72 */       FileUtils.forceMkdir(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db")); 
/*  74 */     File dbFile = new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db/osm.db");
/*     */     try {
/*  78 */       this.db = DBMaker.newFileDB(dbFile).closeOnJvmShutdown().mmapFileEnableIfSupported().transactionDisable().cacheSize(524288).make();
/*  84 */     } catch (Exception e) {
/*  85 */       if (dbFile.exists()) {
/*  86 */         log.error("Local cache is corrupt or incompatible with this version, removing cache.");
/*  87 */         FileUtils.forceDelete(dbFile);
/*  88 */         this.db = DBMaker.newFileDB(dbFile).closeOnJvmShutdown().mmapFileEnableIfSupported().transactionDisable().cacheSize(524288).make();
/*     */       } 
/*     */     } 
/* 100 */     Atomic.String storedMd5 = this.db.getAtomicString("stored-md5");
/* 101 */     if (storedMd5 != null && !storedMd5.toString().equals(md5)) {
/* 102 */       log.info("Unable to resume, OSM file is different from source used to generate");
/* 103 */       this.db.close();
/* 104 */       FileUtils.deleteDirectory(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db"));
/* 105 */       FileUtils.forceMkdir(new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db"));
/* 106 */       dbFile = new File(GeneratorStore.getGeneratorStore().getTemporaryFilePath(), "db/osm.db");
/* 107 */       this.db = DBMaker.newFileDB(dbFile).closeOnJvmShutdown().mmapFileEnableIfSupported().transactionDisable().cacheSize(524288).make();
/* 113 */       resume = false;
/*     */     } 
/* 117 */     this.db.getAtomicString("stored-md5").getAndSet(md5);
/* 118 */     this.nodeMap = this.db.createHashMap("nodes").valueSerializer(new Node.NodeSerializer()).makeOrGet();
/* 119 */     this.relations = this.db.createHashMap("relations").valueSerializer(new RelationInfo.RelationSerializer()).makeOrGet();
/* 120 */     this.tiles = this.db.getHashMap("tiles");
/* 121 */     this.ways = this.db.createHashMap("ways").valueSerializer(new WayInfo.WaySerializer()).makeOrGet();
/* 122 */     this.valid = this.db.getHashMap("info");
/* 130 */     if (resume)
/* 131 */       if (this.valid.containsKey(Integer.valueOf(1))) {
/* 132 */         log.info("Skipping initial passes. Resuming");
/*     */       } else {
/* 134 */         log.info("Rerunning initial passes. Data was not resumable.");
/* 135 */         this.nodeMap.clear();
/* 136 */         this.relations.clear();
/* 137 */         this.tiles.clear();
/* 138 */         this.ways.clear();
/* 139 */         this.valid.clear();
/*     */       }  
/*     */   }
/*     */   
/*     */   public void markAsValid() {
/* 150 */     this.valid.put(Integer.valueOf(1), Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 154 */     return this.valid.containsKey(Integer.valueOf(1));
/*     */   }
/*     */   
/*     */   public RelationInfo getRelationInfo(long relationId) {
/* 158 */     return (RelationInfo)this.relations.get(Long.valueOf(relationId));
/*     */   }
/*     */   
/*     */   public void storeNode(long id, double lon, double lat) {
/* 162 */     this.nodeMap.put(Long.valueOf(id), new Node(Double.valueOf(lon), Double.valueOf(lat)));
/* 163 */     this.nodeCount++;
/* 164 */     if (this.nodeCount > 5000000) {
/* 165 */       this.nodeCount = 0;
/* 166 */       this.db.commit();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void storeRelation(long relationId, RelationInfo relationInfo) {
/* 172 */     this.relations.put(Long.valueOf(relationId), relationInfo);
/* 174 */     this.relationCount++;
/* 175 */     if (this.relationCount > 5000000) {
/* 176 */       this.relationCount = 0;
/* 177 */       this.db.commit();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void storeWay(long wayId, WayInfo wayInfo) {
/* 182 */     this.ways.put(Long.valueOf(wayId), wayInfo);
/* 183 */     this.wayCount++;
/* 184 */     if (this.wayCount > 5000000) {
/* 185 */       this.wayCount = 0;
/* 186 */       this.db.commit();
/*     */     } 
/*     */   }
/*     */   
/*     */   public WayInfo getWayInfo(long wayId) {
/*     */     try {
/* 192 */       return (WayInfo)this.ways.get(Long.valueOf(wayId));
/* 193 */     } catch (Exception e) {
/* 194 */       e.printStackTrace();
/* 195 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<double[]> getNodes() {
/* 200 */     if (this.resume && this.tiles.size() > 0) {
/* 201 */       Iterator<double[]> iterator = (Iterator)this.tiles.values().iterator();
/* 202 */       ArrayList<double[]> arrayList = (ArrayList)new ArrayList<>();
/* 203 */       while (iterator.hasNext())
/* 204 */         arrayList.add(iterator.next()); 
/* 206 */       return (List<double[]>)arrayList;
/*     */     } 
/* 210 */     log.info("Removing Unused Nodes");
/* 213 */     ArrayList<double[]> data = (ArrayList)new ArrayList<>();
/* 215 */     Iterator<Object> it = this.nodeMap.keySet().iterator();
/* 217 */     while (it.hasNext()) {
/* 218 */       long key = ((Long)it.next()).longValue();
/* 219 */       Node node = (Node)this.nodeMap.get(Long.valueOf(key));
/* 222 */       double[] pos = { node.lon.doubleValue(), node.lat.doubleValue() };
/* 224 */       int hash = 7;
/* 225 */       int x = (int)pos[0];
/* 226 */       int y = (int)pos[1];
/* 227 */       if (node.lon.doubleValue() < 0.0D)
/* 228 */         x -= 181; 
/* 230 */       if (node.lat.doubleValue() < 0.0D)
/* 231 */         y += 91; 
/* 233 */       hash = 71 * hash + x;
/* 234 */       hash = 71 * hash + y;
/* 237 */       if (pos[0] == 0.0D && pos[1] == 0.0D) {
/* 238 */         this.nodeMap.remove(Long.valueOf(key));
/*     */         continue;
/*     */       } 
/* 240 */       if (!this.tiles.containsKey(Integer.valueOf(hash))) {
/* 241 */         this.tiles.put(Integer.valueOf(hash), pos);
/* 242 */         data.add(pos);
/*     */       } 
/*     */     } 
/* 247 */     this.db.commit();
/* 248 */     log.info("Compacting Database");
/* 249 */     this.db.compact();
/* 252 */     return (List<double[]>)data;
/*     */   }
/*     */   
/*     */   public double[] getNode(long id) {
/* 257 */     Node data = (Node)this.nodeMap.get(Long.valueOf(id));
/* 258 */     if (data != null)
/* 259 */       return new double[] { data.lon.doubleValue(), data.lat.doubleValue() }; 
/* 260 */     return null;
/*     */   }
/*     */   
/*     */   public int numberOfRelations() {
/* 265 */     return this.relations.size();
/*     */   }
/*     */   
/*     */   public int numberOfNodes() {
/* 269 */     return this.nodeMap.size();
/*     */   }
/*     */   
/*     */   public boolean hasNode(long id) {
/* 273 */     return this.nodeMap.containsKey(Long.valueOf(id));
/*     */   }
/*     */   
/*     */   public boolean wayIsInRelation(long wayId) {
/* 278 */     return this.ways.containsKey(Long.valueOf(wayId));
/*     */   }
/*     */   
/*     */   public void commit() {
/* 283 */     this.db.commit();
/*     */   }
/*     */   
/*     */   public void close() {
/* 287 */     this.db.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\JDBMStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */