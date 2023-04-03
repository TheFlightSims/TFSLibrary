/*     */ package org.openstreetmap.osmosis.core.report.v0_6;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.BoundContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.EntityProcessor;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.RelationContainer;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*     */ 
/*     */ public class EntityReporter implements Sink {
/*     */   private static final int COLUMN_WIDTH_USER_NAME = 50;
/*     */   
/*     */   private static final int COLUMN_WIDTH_NODE_COUNT = 7;
/*     */   
/*     */   private static final int COLUMN_WIDTH_WAY_COUNT = 7;
/*     */   
/*     */   private static final int COLUMN_WIDTH_RELATION_COUNT = 7;
/*     */   
/*  40 */   private Logger log = Logger.getLogger(EntityReporter.class.getName());
/*     */   
/*     */   private File file;
/*     */   
/*     */   private FileWriter fileWriter;
/*     */   
/*     */   private Map<String, UserStatistics> userMap;
/*     */   
/*     */   private UserStatistics anonymousUser;
/*     */   
/*     */   private UserStatistics totalUser;
/*     */   
/*     */   public EntityReporter(File file) {
/*  56 */     this.file = file;
/*  58 */     this.userMap = new HashMap<String, UserStatistics>();
/*  59 */     this.anonymousUser = new UserStatistics("anonymous");
/*  60 */     this.totalUser = new UserStatistics("Total");
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {}
/*     */   
/*     */   public void process(EntityContainer entityContainer) {
/*     */     final UserStatistics user;
/*  80 */     String userName = entityContainer.getEntity().getUser().getName();
/*  81 */     if (userName != null && userName.length() > 0) {
/*  82 */       if (this.userMap.containsKey(userName)) {
/*  83 */         user = this.userMap.get(userName);
/*     */       } else {
/*  85 */         user = new UserStatistics(userName);
/*  86 */         this.userMap.put(userName, user);
/*     */       } 
/*     */     } else {
/*  89 */       user = this.anonymousUser;
/*     */     } 
/*  93 */     entityContainer.process(new EntityProcessor() {
/*  95 */           private EntityReporter.UserStatistics processorUser = user;
/*     */           
/*     */           public void process(BoundContainer bound) {}
/*     */           
/*     */           public void process(NodeContainer node) {
/* 102 */             this.processorUser.incrementNodeCount();
/* 103 */             EntityReporter.this.totalUser.incrementNodeCount();
/*     */           }
/*     */           
/*     */           public void process(WayContainer way) {
/* 107 */             this.processorUser.incrementWayCount();
/* 108 */             EntityReporter.this.totalUser.incrementWayCount();
/*     */           }
/*     */           
/*     */           public void process(RelationContainer relation) {
/* 112 */             this.processorUser.incrementRelationCount();
/* 113 */             EntityReporter.this.totalUser.incrementRelationCount();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void writeColumnValue(BufferedWriter writer, String data, int columnWidth) throws IOException {
/* 135 */     int padLength = columnWidth - data.length() + 1;
/* 136 */     if (padLength < 1)
/* 137 */       padLength = 1; 
/* 140 */     writer.write(data);
/* 141 */     for (int i = 0; i < padLength; i++)
/* 142 */       writer.write(32); 
/*     */   }
/*     */   
/*     */   private void writeUserLine(BufferedWriter writer, UserStatistics userStatistics) throws IOException {
/* 156 */     writeColumnValue(writer, userStatistics.getUserName(), 50);
/* 157 */     writeColumnValue(writer, Integer.toString(userStatistics.getNodeCount()), 7);
/* 158 */     writeColumnValue(writer, Integer.toString(userStatistics.getWayCount()), 7);
/* 159 */     writeColumnValue(writer, Integer.toString(userStatistics.getRelationCount()), 7);
/* 160 */     writer.newLine();
/*     */   }
/*     */   
/*     */   private void writeUserReport(BufferedWriter writer) throws IOException {
/* 174 */     List<UserStatistics> userList = new ArrayList<UserStatistics>(this.userMap.values());
/* 175 */     Collections.sort(userList, new Comparator<UserStatistics>() {
/*     */           public int compare(EntityReporter.UserStatistics o1, EntityReporter.UserStatistics o2) {
/* 179 */             return o1.getUserName().compareTo(o2.getUserName());
/*     */           }
/*     */         });
/* 184 */     writer.write("********** User Report **********");
/* 185 */     writer.newLine();
/* 186 */     writeColumnValue(writer, "USER NAME", 50);
/* 187 */     writeColumnValue(writer, "NODES", 7);
/* 188 */     writeColumnValue(writer, "WAYS", 7);
/* 189 */     writeColumnValue(writer, "RELNS", 7);
/* 190 */     writer.newLine();
/* 191 */     writeUserLine(writer, this.anonymousUser);
/* 192 */     for (UserStatistics userStatistics : userList)
/* 193 */       writeUserLine(writer, userStatistics); 
/* 195 */     writer.newLine();
/* 196 */     writeUserLine(writer, this.totalUser);
/*     */   }
/*     */   
/*     */   public void complete() {
/*     */     try {
/* 207 */       this.fileWriter = new FileWriter(this.file);
/* 208 */       BufferedWriter writer = new BufferedWriter(this.fileWriter);
/* 211 */       writeUserReport(writer);
/* 213 */       writer.close();
/* 214 */       this.fileWriter = null;
/* 216 */     } catch (IOException e) {
/* 217 */       throw new OsmosisRuntimeException("Unable to write report to file " + this.file + ".");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release() {
/* 226 */     if (this.fileWriter != null)
/*     */       try {
/* 228 */         this.fileWriter.close();
/* 229 */       } catch (IOException e) {
/* 230 */         this.log.log(Level.SEVERE, "Unable to close file writer for file " + this.file + ".", e);
/*     */       } finally {
/* 232 */         this.fileWriter = null;
/*     */       }  
/*     */   }
/*     */   
/*     */   private static class UserStatistics {
/*     */     private String userName;
/*     */     
/*     */     private int nodeCount;
/*     */     
/*     */     private int wayCount;
/*     */     
/*     */     private int relationCount;
/*     */     
/*     */     public UserStatistics(String userName) {
/* 257 */       this.userName = userName;
/*     */     }
/*     */     
/*     */     public void incrementNodeCount() {
/* 265 */       this.nodeCount++;
/*     */     }
/*     */     
/*     */     public void incrementWayCount() {
/* 273 */       this.wayCount++;
/*     */     }
/*     */     
/*     */     public void incrementRelationCount() {
/* 281 */       this.relationCount++;
/*     */     }
/*     */     
/*     */     public String getUserName() {
/* 291 */       return this.userName;
/*     */     }
/*     */     
/*     */     public int getNodeCount() {
/* 301 */       return this.nodeCount;
/*     */     }
/*     */     
/*     */     public int getWayCount() {
/* 311 */       return this.wayCount;
/*     */     }
/*     */     
/*     */     public int getRelationCount() {
/* 321 */       return this.relationCount;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\report\v0_6\EntityReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */