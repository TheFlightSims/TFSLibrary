/*     */ package org.openstreetmap.osmosis.core.report.v0_6;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
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
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.EntityType;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.RelationMember;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Way;
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
/*     */ import org.openstreetmap.osmosis.core.filter.common.BitSetIdTracker;
/*     */ import org.openstreetmap.osmosis.core.filter.common.IdTracker;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*     */ 
/*     */ public class IntegrityReporter implements Sink, EntityProcessor {
/*  39 */   private static final Logger LOG = Logger.getLogger(IntegrityReporter.class.getName());
/*     */   
/*     */   private File file;
/*     */   
/*     */   private boolean initialized;
/*     */   
/*     */   private BufferedWriter writer;
/*     */   
/*     */   private IdTracker nodeBitSet;
/*     */   
/*     */   private IdTracker wayBitSet;
/*     */   
/*     */   public IntegrityReporter(File file) {
/*  55 */     this.file = file;
/*  57 */     this.initialized = false;
/*  58 */     this.nodeBitSet = (IdTracker)new BitSetIdTracker();
/*  59 */     this.wayBitSet = (IdTracker)new BitSetIdTracker();
/*     */   }
/*     */   
/*     */   private void write(String data) {
/*     */     try {
/*  71 */       this.writer.write(data);
/*  73 */     } catch (IOException e) {
/*  74 */       throw new OsmosisRuntimeException("Unable to write data.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeNewLine() {
/*     */     try {
/*  84 */       this.writer.newLine();
/*  86 */     } catch (IOException e) {
/*  87 */       throw new OsmosisRuntimeException("Unable to write data.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void initialize() {
/*  99 */     if (!this.initialized) {
/* 100 */       OutputStream outStream = null;
/*     */       try {
/* 103 */         outStream = new FileOutputStream(this.file);
/* 105 */         this.writer = new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8"));
/* 107 */         outStream = null;
/* 109 */       } catch (IOException e) {
/* 110 */         throw new OsmosisRuntimeException("Unable to open file " + this.file + " for writing.", e);
/*     */       } finally {
/* 112 */         if (outStream != null) {
/*     */           try {
/* 114 */             outStream.close();
/* 115 */           } catch (Exception e) {
/* 116 */             LOG.log(Level.SEVERE, "Unable to close output stream for file " + this.file + ".", e);
/*     */           } 
/* 118 */           outStream = null;
/*     */         } 
/*     */       } 
/* 122 */       this.initialized = true;
/* 124 */       write("Entity Type, Entity Id, Referred Type, Referred Id");
/* 125 */       writeNewLine();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {}
/*     */   
/*     */   public void process(EntityContainer entityContainer) {
/* 142 */     entityContainer.process(this);
/*     */   }
/*     */   
/*     */   public void process(BoundContainer bound) {}
/*     */   
/*     */   public void process(NodeContainer node) {
/* 158 */     this.nodeBitSet.set(node.getEntity().getId());
/*     */   }
/*     */   
/*     */   public void process(WayContainer wayContainer) {
/* 168 */     Way way = wayContainer.getEntity();
/* 170 */     this.wayBitSet.set(way.getId());
/* 172 */     for (WayNode wayNode : way.getWayNodes()) {
/* 173 */       if (!this.nodeBitSet.get(wayNode.getNodeId())) {
/* 174 */         initialize();
/* 176 */         write("Way," + way.getId() + ",Node," + wayNode.getNodeId());
/* 177 */         writeNewLine();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void process(RelationContainer relationContainer) {
/* 189 */     Relation relation = relationContainer.getEntity();
/* 191 */     for (RelationMember relationMember : relation.getMembers()) {
/* 194 */       EntityType memberType = relationMember.getMemberType();
/* 196 */       if (EntityType.Node.equals(memberType)) {
/* 197 */         if (!this.nodeBitSet.get(relationMember.getMemberId())) {
/* 198 */           initialize();
/* 200 */           write("Relation," + relation.getId() + ",Node," + relationMember.getMemberId());
/* 201 */           writeNewLine();
/*     */         } 
/*     */         continue;
/*     */       } 
/* 203 */       if (EntityType.Way.equals(memberType) && 
/* 204 */         !this.wayBitSet.get(relationMember.getMemberId())) {
/* 205 */         initialize();
/* 207 */         write("Relation," + relation.getId() + ",Way," + relationMember.getMemberId());
/* 208 */         writeNewLine();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {
/*     */     try {
/* 220 */       if (this.writer != null)
/* 221 */         this.writer.close(); 
/* 224 */     } catch (IOException e) {
/* 225 */       throw new OsmosisRuntimeException("Unable to complete writing to the file " + this.file + ".", e);
/*     */     } finally {
/* 227 */       this.initialized = false;
/* 228 */       this.writer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release() {
/*     */     try {
/*     */       try {
/* 239 */         if (this.writer != null)
/* 240 */           this.writer.close(); 
/* 242 */       } catch (IOException e) {
/* 243 */         LOG.log(Level.SEVERE, "Unable to close writer.", e);
/*     */       } 
/*     */     } finally {
/* 246 */       this.initialized = false;
/* 247 */       this.writer = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\report\v0_6\IntegrityReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */