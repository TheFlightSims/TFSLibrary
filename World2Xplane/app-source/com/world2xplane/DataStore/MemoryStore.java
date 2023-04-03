/*     */ package com.world2xplane.DataStore;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import gnu.trove.iterator.TIntObjectIterator;
/*     */ import gnu.trove.iterator.TLongObjectIterator;
/*     */ import gnu.trove.iterator.TShortObjectIterator;
/*     */ import gnu.trove.map.hash.TIntObjectHashMap;
/*     */ import gnu.trove.map.hash.TLongObjectHashMap;
/*     */ import gnu.trove.map.hash.TShortObjectHashMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MemoryStore implements DataStore {
/*     */   private final TLongObjectHashMap<RelationInfo> relations;
/*     */   
/*     */   private final TLongObjectHashMap<WayInfo> ways;
/*     */   
/*     */   private final TLongObjectHashMap<int[]> nodeMap;
/*     */   
/*     */   private final TIntObjectHashMap<RelationInfo> irelations;
/*     */   
/*     */   private final TIntObjectHashMap<WayInfo> iways;
/*     */   
/*     */   private final TIntObjectHashMap<int[]> inodeMap;
/*     */   
/*     */   private final TShortObjectHashMap<RelationInfo> srelations;
/*     */   
/*     */   private final TShortObjectHashMap<WayInfo> sways;
/*     */   
/*     */   private final TShortObjectHashMap<int[]> snodeMap;
/*     */   
/*  54 */   double compressionRatio = 1000000.0D;
/*     */   
/*     */   public MemoryStore() throws Exception {
/*  61 */     this.relations = new TLongObjectHashMap();
/*  62 */     this.nodeMap = new TLongObjectHashMap();
/*  63 */     this.ways = new TLongObjectHashMap();
/*  65 */     this.irelations = new TIntObjectHashMap();
/*  66 */     this.inodeMap = new TIntObjectHashMap();
/*  67 */     this.iways = new TIntObjectHashMap();
/*  69 */     this.srelations = new TShortObjectHashMap();
/*  70 */     this.sways = new TShortObjectHashMap();
/*  71 */     this.snodeMap = new TShortObjectHashMap();
/*     */   }
/*     */   
/*     */   public RelationInfo getRelationInfo(long relationId) {
/*  76 */     if (relationId < 32767L)
/*  77 */       return (RelationInfo)this.srelations.get((short)(int)relationId); 
/*  79 */     if (relationId < 2147483647L)
/*  80 */       return (RelationInfo)this.irelations.get((int)relationId); 
/*  82 */     return (RelationInfo)this.relations.get(relationId);
/*     */   }
/*     */   
/*     */   public void storeNode(long id, double lon, double lat) {
/*  86 */     int l1 = (int)(lon * this.compressionRatio);
/*  87 */     int l2 = (int)(lat * this.compressionRatio);
/*  89 */     if (id < 32767L) {
/*  90 */       this.snodeMap.put((short)(int)id, new int[] { l1, l2 });
/*  91 */     } else if (id < 2147483647L) {
/*  92 */       this.inodeMap.put((int)id, new int[] { l1, l2 });
/*     */     } else {
/*  94 */       this.nodeMap.put(id, new int[] { l1, l2 });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void storeRelation(long relationId, RelationInfo relationInfo) {
/*  99 */     if (relationId < 32767L) {
/* 100 */       this.srelations.put((short)(int)relationId, relationInfo);
/* 101 */     } else if (relationId < 2147483647L) {
/* 102 */       this.irelations.put((int)relationId, relationInfo);
/*     */     } else {
/* 104 */       this.relations.put(relationId, relationInfo);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void storeWay(long wayId, WayInfo wayInfo) {
/* 108 */     if (wayId < 32767L) {
/* 109 */       this.sways.put((short)(int)wayId, wayInfo);
/* 110 */     } else if (wayId < 2147483647L) {
/* 111 */       this.iways.put((int)wayId, wayInfo);
/*     */     } else {
/* 113 */       this.ways.put(wayId, wayInfo);
/*     */     } 
/*     */   }
/*     */   
/*     */   public WayInfo getWayInfo(long wayId) {
/* 117 */     if (wayId < 32767L)
/* 118 */       return (WayInfo)this.sways.get((short)(int)wayId); 
/* 119 */     if (wayId < 2147483647L)
/* 120 */       return (WayInfo)this.iways.get((int)wayId); 
/* 122 */     return (WayInfo)this.ways.get(wayId);
/*     */   }
/*     */   
/*     */   public List<double[]> getNodes() {
/* 126 */     ArrayList<double[]> data = (ArrayList)new ArrayList<>();
/* 128 */     HashMap<Integer, double[]> tiles = (HashMap)new HashMap<>();
/* 129 */     TLongObjectIterator<int[]> iterator = this.nodeMap.iterator();
/* 130 */     while (iterator.hasNext()) {
/* 131 */       iterator.advance();
/* 132 */       int[] item = (int[])iterator.value();
/* 133 */       int hash = 7;
/* 134 */       double[] pos = { item[0] / this.compressionRatio, item[1] / this.compressionRatio };
/* 136 */       int x = (int)pos[0];
/* 137 */       int y = (int)pos[1];
/* 139 */       if (item[0] < 0)
/* 140 */         x -= 181; 
/* 142 */       if (item[1] < 0)
/* 143 */         y += 91; 
/* 145 */       hash = 71 * hash + x;
/* 146 */       hash = 71 * hash + y;
/* 149 */       if (item[0] == 0 && item[1] == 0)
/*     */         continue; 
/* 152 */       if (!tiles.containsKey(Integer.valueOf(hash))) {
/* 153 */         tiles.put(Integer.valueOf(hash), pos);
/* 154 */         data.add(pos);
/*     */       } 
/*     */     } 
/* 159 */     TShortObjectIterator<int[]> siterator = this.snodeMap.iterator();
/* 160 */     while (siterator.hasNext()) {
/* 161 */       siterator.advance();
/* 162 */       int[] item = (int[])siterator.value();
/* 164 */       double[] pos = { item[0] / this.compressionRatio, item[1] / this.compressionRatio };
/* 166 */       int x = (int)pos[0];
/* 167 */       int y = (int)pos[1];
/* 169 */       if (item[0] < 0)
/* 170 */         x -= 181; 
/* 172 */       if (item[1] < 0)
/* 173 */         y += 91; 
/* 176 */       int hash = Objects.hashCode(new Object[] { Integer.valueOf(x), Integer.valueOf(y) });
/* 179 */       if (item[0] == 0 && item[1] == 0)
/*     */         continue; 
/* 182 */       if (!tiles.containsKey(Integer.valueOf(hash))) {
/* 183 */         tiles.put(Integer.valueOf(hash), pos);
/* 184 */         data.add(pos);
/*     */       } 
/*     */     } 
/* 189 */     TIntObjectIterator<int[]> iiterator = this.inodeMap.iterator();
/* 190 */     while (iiterator.hasNext()) {
/* 191 */       iiterator.advance();
/* 192 */       int[] item = (int[])iiterator.value();
/* 193 */       double[] pos = { item[0] / this.compressionRatio, item[1] / this.compressionRatio };
/* 195 */       int x = (int)pos[0];
/* 196 */       int y = (int)pos[1];
/* 198 */       if (item[0] < 0)
/* 199 */         x -= 181; 
/* 201 */       if (item[1] < 0)
/* 202 */         y += 91; 
/* 204 */       int hash = Objects.hashCode(new Object[] { Integer.valueOf(x), Integer.valueOf(y) });
/* 206 */       if (item[0] == 0 && item[1] == 0)
/*     */         continue; 
/* 209 */       if (!tiles.containsKey(Integer.valueOf(hash))) {
/* 210 */         tiles.put(Integer.valueOf(hash), pos);
/* 211 */         data.add(pos);
/*     */       } 
/*     */     } 
/* 216 */     return (List<double[]>)data;
/*     */   }
/*     */   
/*     */   public double[] getNode(long id) {
/* 222 */     int[] data = null;
/* 224 */     if (id < 32767L) {
/* 225 */       data = (int[])this.snodeMap.get((short)(int)id);
/* 226 */     } else if (id < 2147483647L) {
/* 227 */       data = (int[])this.inodeMap.get((int)id);
/*     */     } else {
/* 229 */       data = (int[])this.nodeMap.get(id);
/*     */     } 
/* 231 */     if (data != null)
/* 232 */       return new double[] { data[0] / this.compressionRatio, data[1] / this.compressionRatio }; 
/* 234 */     return null;
/*     */   }
/*     */   
/*     */   public int numberOfRelations() {
/* 239 */     return this.relations.size() + this.srelations.size() + this.irelations.size();
/*     */   }
/*     */   
/*     */   public int numberOfNodes() {
/* 244 */     return this.nodeMap.size() + this.inodeMap.size() + this.snodeMap.size();
/*     */   }
/*     */   
/*     */   public boolean hasNode(long id) {
/* 248 */     if (id < 32767L)
/* 249 */       return this.snodeMap.containsKey((short)(int)id); 
/* 250 */     if (id < 2147483647L)
/* 251 */       return this.inodeMap.containsKey((int)id); 
/* 254 */     return this.nodeMap.containsKey(id);
/*     */   }
/*     */   
/*     */   public boolean wayIsInRelation(long wayId) {
/* 259 */     if (wayId < 32767L)
/* 260 */       return this.sways.containsKey((short)(int)wayId); 
/* 262 */     if (wayId < 2147483647L)
/* 263 */       return this.iways.containsKey((int)wayId); 
/* 266 */     return this.ways.containsKey(wayId);
/*     */   }
/*     */   
/*     */   public void commit() {}
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public void markAsValid() {}
/*     */   
/*     */   public boolean isValid() {
/* 283 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\MemoryStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */