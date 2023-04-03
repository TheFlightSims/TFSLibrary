/*     */ package com.world2xplane.DataStore;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DatabaseStore implements DataStore {
/*     */   private Connection connection;
/*     */   
/*  44 */   int commitCount = 0;
/*     */   
/*     */   public DatabaseStore(boolean resume) throws SQLException {
/*     */     try {
/*  62 */       Class.forName("org.postgis.DriverWrapperLW");
/*  63 */       this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/xpst", "sa", "sa");
/*  66 */       this.connection.setAutoCommit(false);
/*  69 */       if (!resume) {
/*  70 */         Statement st = this.connection.createStatement();
/*  72 */         st.execute("DROP TABLE IF EXISTS nodes");
/*  73 */         st.execute("CREATE TABLE nodes (identifier bigint primary key, position GEOMETRY);\n");
/*  74 */         st.execute("CREATE INDEX node_index ON nodes USING GIST (position);\n ");
/*  76 */         st.execute("DROP TABLE IF EXISTS relations");
/*  77 */         st.execute("CREATE TABLE relations (identifier BIGINT PRIMARY KEY, declined BOOLEAN, wayAccepted BOOLEAN, height FLOAT, customFacade INT);\n");
/*  78 */         st.execute("DROP TABLE IF EXISTS relation_rules;");
/*  79 */         st.execute("DROP TABLE IF EXISTS relation_tiles;");
/*  80 */         st.execute("CREATE TABLE relation_rules (relation_id BIGINT, rule_number INT, filter_number INT);");
/*  81 */         st.execute("CREATE TABLE relation_tiles (relation_id BIGINT, lon INT, lat INT);");
/*  84 */         st.execute("DROP TABLE IF EXISTS ways");
/*  85 */         st.execute("CREATE TABLE ways (identifier bigint primary key, way bytea);\n");
/*     */       } 
/*  88 */     } catch (ClassNotFoundException e) {
/*  89 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void storeNode(long id, double lon, double lat) {
/*  94 */     GeometryFactory geometryFactory = new GeometryFactory();
/*     */     try {
/*  96 */       PreparedStatement ps = null;
/*  98 */       Statement st = this.connection.createStatement(1004, 1008);
/*  99 */       if (hasNode(id)) {
/* 100 */         ps = this.connection.prepareStatement("UPDATE nodes SET position=St_GeomFromText(?) WHERE identifier=?");
/* 101 */         ps.setObject(2, Long.valueOf(id));
/* 102 */         ps.setObject(1, "POINT(" + lon + " " + lat + ")");
/* 103 */         ps.execute();
/* 104 */         ps.close();
/* 105 */         autoCommit();
/*     */       } else {
/* 107 */         ps = this.connection.prepareStatement("INSERT INTO NODES(identifier,position) VALUES (?,St_GeomFromText(?));");
/* 108 */         ps.setObject(1, Long.valueOf(id));
/* 109 */         ps.setObject(2, "POINT(" + lon + " " + lat + ")");
/* 110 */         ps.execute();
/* 111 */         st.close();
/* 112 */         autoCommit();
/*     */       } 
/* 114 */     } catch (SQLException e) {
/* 115 */       e.printStackTrace();
/* 116 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void autoCommit() throws SQLException {
/* 121 */     if (this.commitCount > 10000) {
/* 122 */       this.commitCount = 0;
/* 123 */       this.connection.commit();
/*     */     } else {
/* 125 */       this.commitCount++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void storeRelation(long relationId, RelationInfo relationInfo) {
/*     */     try {
/* 140 */       Statement st = this.connection.createStatement(1004, 1008);
/* 141 */       PreparedStatement ps = null;
/* 142 */       ResultSet rs = st.executeQuery("SELECT * FROM relations WHERE identifier='" + relationId + "'");
/* 143 */       if (rs == null || !rs.first()) {
/* 152 */         String insertSQL = "INSERT into relations (identifier, declined, wayAccepted, height, customFacade) values(?,?,?,?,?);";
/* 153 */         ps = this.connection.prepareStatement(insertSQL);
/* 154 */         ps.setObject(1, Long.valueOf(relationId));
/* 155 */         ps.setBoolean(2, relationInfo.declined);
/* 156 */         ps.setBoolean(3, relationInfo.wayAccepted);
/* 157 */         ps.setFloat(4, (relationInfo.height != null) ? relationInfo.height.floatValue() : -1.0F);
/* 158 */         ps.setInt(5, (relationInfo.customFacade != null) ? relationInfo.customFacade.intValue() : -1);
/* 159 */         ps.executeUpdate();
/* 160 */         ps.close();
/* 162 */         if (relationInfo.rules != null && relationInfo.rules.size() > 0)
/* 163 */           for (AcceptingRule rule : relationInfo.rules) {
/* 164 */             insertSQL = "INSERT INTO relation_rules (relation_id, rule_number, filter_number) VALUES (?,?,?);";
/* 165 */             ps = this.connection.prepareStatement(insertSQL);
/* 166 */             ps.setObject(1, Long.valueOf(relationId));
/* 167 */             ps.setInt(2, rule.ruleNumber.shortValue());
/* 168 */             ps.setInt(3, rule.filterNumber.byteValue());
/* 169 */             ps.executeUpdate();
/* 170 */             ps.close();
/*     */           }  
/* 174 */         if (relationInfo.tiles != null && relationInfo.tiles.size() > 0)
/* 175 */           for (Tile rule : relationInfo.tiles) {
/* 176 */             insertSQL = "INSERT INTO relation_tiles (relation_id, lon, lat) VALUES (?,?,?);";
/* 177 */             ps = this.connection.prepareStatement(insertSQL);
/* 178 */             ps.setObject(1, Long.valueOf(relationId));
/* 179 */             ps.setInt(2, rule.lon);
/* 180 */             ps.setInt(3, rule.lat);
/* 181 */             ps.executeUpdate();
/* 182 */             ps.close();
/*     */           }  
/* 186 */         autoCommit();
/*     */       } 
/* 189 */     } catch (Exception e) {
/* 190 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public RelationInfo getRelationInfo(long relationId) {
/* 209 */     return null;
/*     */   }
/*     */   
/*     */   public void storeWay(long wayId, WayInfo wayInfo) {}
/*     */   
/*     */   public WayInfo getWayInfo(long wayId) {
/* 252 */     return null;
/*     */   }
/*     */   
/*     */   public List<double[]> getNodes() {
/* 257 */     GeometryFactory factory = new GeometryFactory();
/*     */     try {
/* 259 */       List<double[]> data = (List)new ArrayList<>();
/* 261 */       for (int x = -180; x <= 180; x++) {
/* 262 */         for (int y = -90; y <= 90; y++) {
/* 263 */           String sql = "SELECT count(*) FROM nodes WHERE ST_Contains(position,St_GeomFromText(?)) LIMIT 1;";
/* 264 */           PreparedStatement ps = this.connection.prepareStatement(sql, 1004, 1008);
/* 265 */           ps.setString(1, factory.toGeometry(new Envelope(x, (x + 1), y, (y + 1))).toText());
/* 267 */           ResultSet rs = ps.executeQuery();
/* 268 */           int count = rs.last() ? rs.getInt(1) : 0;
/* 269 */           if (count > 0)
/* 270 */             data.add(new double[] { x, y }); 
/* 271 */           rs.close();
/*     */         } 
/*     */       } 
/* 275 */       return data;
/* 276 */     } catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] getNode(long id) {
/* 298 */     return null;
/*     */   }
/*     */   
/*     */   public int numberOfRelations() {
/*     */     try {
/* 303 */       Statement st = this.connection.createStatement(1004, 1008);
/* 304 */       ResultSet rs = st.executeQuery("SELECT COUNT(1) FROM relations");
/* 305 */       int rowCount = rs.last() ? rs.getInt(1) : 0;
/* 306 */       return rowCount;
/* 308 */     } catch (SQLException e) {
/* 309 */       e.printStackTrace();
/* 310 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int numberOfNodes() {
/*     */     try {
/* 316 */       Statement st = this.connection.createStatement(1004, 1008);
/* 317 */       ResultSet rs = st.executeQuery("SELECT COUNT(1) FROM nodes");
/* 318 */       int rowCount = rs.last() ? rs.getInt(1) : 0;
/* 319 */       return rowCount;
/* 321 */     } catch (SQLException e) {
/* 322 */       e.printStackTrace();
/* 323 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNode(long id) {
/*     */     try {
/* 329 */       Statement st = this.connection.createStatement(1004, 1008);
/* 330 */       ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM nodes WHERE identifier = ' " + id + "' LIMIT 1");
/* 331 */       int rowCount = rs.last() ? rs.getInt(1) : 0;
/* 332 */       return (rowCount > 0);
/* 334 */     } catch (SQLException e) {
/* 335 */       e.printStackTrace();
/* 336 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean wayIsInRelation(long wayId) {
/* 354 */     return false;
/*     */   }
/*     */   
/*     */   public void commit() {
/*     */     try {
/* 359 */       this.connection.commit();
/* 360 */     } catch (SQLException e) {
/* 361 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 367 */       this.connection.close();
/* 368 */     } catch (SQLException e) {
/* 369 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markAsValid() {}
/*     */   
/*     */   public boolean isValid() {
/* 378 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\DatabaseStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */