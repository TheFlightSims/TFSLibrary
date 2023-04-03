/*     */ package org.geotools.data.shapefile.index.quadtree.fs;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.shapefile.index.quadtree.IndexStore;
/*     */ import org.geotools.data.shapefile.index.quadtree.Node;
/*     */ import org.geotools.data.shapefile.index.quadtree.QuadTree;
/*     */ import org.geotools.data.shapefile.index.quadtree.StoreException;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class FileSystemIndexStore implements IndexStore {
/*  46 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.index.quadtree");
/*     */   
/*     */   private File file;
/*     */   
/*     */   private byte byteOrder;
/*     */   
/*     */   public FileSystemIndexStore(File file) {
/*  57 */     this.file = file;
/*  58 */     this.byteOrder = 2;
/*     */   }
/*     */   
/*     */   public FileSystemIndexStore(File file, byte byteOrder) {
/*  68 */     this.file = file;
/*  69 */     this.byteOrder = byteOrder;
/*     */   }
/*     */   
/*     */   public void store(QuadTree tree) throws StoreException {
/*  77 */     tree.trim();
/*  80 */     FileOutputStream fos = null;
/*  81 */     FileChannel channel = null;
/*     */     try {
/*  84 */       fos = new FileOutputStream(this.file);
/*  85 */       channel = fos.getChannel();
/*  87 */       ByteBuffer buf = ByteBuffer.allocate(8);
/*  89 */       if (this.byteOrder > 0) {
/*  90 */         LOGGER.finest("Writing file header");
/*  92 */         IndexHeader header = new IndexHeader(this.byteOrder);
/*  93 */         header.writeTo(buf);
/*  94 */         buf.flip();
/*  95 */         channel.write(buf);
/*     */       } 
/*  98 */       ByteOrder order = byteToOrder(this.byteOrder);
/* 100 */       buf.clear();
/* 101 */       buf.order(order);
/* 103 */       buf.putInt(tree.getNumShapes());
/* 104 */       buf.putInt(tree.getMaxDepth());
/* 105 */       buf.flip();
/* 107 */       channel.write(buf);
/* 109 */       writeNode(tree.getRoot(), channel, order);
/* 110 */     } catch (IOException e) {
/* 111 */       throw new StoreException(e);
/*     */     } finally {
/*     */       try {
/* 114 */         channel.close();
/* 115 */       } catch (Exception e) {}
/*     */       try {
/* 119 */         fos.close();
/* 120 */       } catch (Exception e) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeNode(Node node, FileChannel channel, ByteOrder order) throws IOException, StoreException {
/* 141 */     int offset = getSubNodeOffset(node);
/* 143 */     ByteBuffer buf = ByteBuffer.allocate(44 + node.getNumShapeIds() * 4);
/* 146 */     buf.order(order);
/* 147 */     buf.putInt(offset);
/* 149 */     Envelope env = node.getBounds();
/* 150 */     buf.putDouble(env.getMinX());
/* 151 */     buf.putDouble(env.getMinY());
/* 152 */     buf.putDouble(env.getMaxX());
/* 153 */     buf.putDouble(env.getMaxY());
/* 155 */     buf.putInt(node.getNumShapeIds());
/*     */     int i;
/* 157 */     for (i = 0; i < node.getNumShapeIds(); i++)
/* 158 */       buf.putInt(node.getShapeId(i)); 
/* 161 */     buf.putInt(node.getNumSubNodes());
/* 162 */     buf.flip();
/* 164 */     channel.write(buf);
/* 166 */     for (i = 0; i < node.getNumSubNodes(); i++)
/* 167 */       writeNode(node.getSubNode(i), channel, order); 
/*     */   }
/*     */   
/*     */   private int getSubNodeOffset(Node node) throws StoreException {
/* 181 */     int offset = 0;
/* 182 */     Node tmp = null;
/* 184 */     for (int i = 0; i < node.getNumSubNodes(); i++) {
/* 185 */       tmp = node.getSubNode(i);
/* 186 */       offset += 32;
/* 187 */       offset += (tmp.getNumShapeIds() + 3) * 4;
/* 188 */       offset += getSubNodeOffset(tmp);
/*     */     } 
/* 191 */     return offset;
/*     */   }
/*     */   
/*     */   public QuadTree load(IndexFile indexfile, boolean useMemoryMapping) throws StoreException {
/* 202 */     QuadTree tree = null;
/*     */     try {
/* 205 */       if (LOGGER.isLoggable(Level.FINEST))
/* 206 */         LOGGER.finest("Opening QuadTree " + this.file.getCanonicalPath()); 
/* 210 */       final FileInputStream fis = new FileInputStream(this.file);
/* 211 */       final FileChannel channel = fis.getChannel();
/* 213 */       IndexHeader header = new IndexHeader(channel);
/* 215 */       ByteOrder order = byteToOrder(header.getByteOrder());
/* 216 */       ByteBuffer buf = ByteBuffer.allocate(8);
/* 217 */       buf.order(order);
/* 218 */       channel.read(buf);
/* 219 */       buf.flip();
/* 221 */       tree = new QuadTree(buf.getInt(), buf.getInt(), indexfile) {
/*     */           public void insert(int recno, Envelope bounds) {
/* 223 */             throw new UnsupportedOperationException("File quadtrees are immutable");
/*     */           }
/*     */           
/*     */           public boolean trim() {
/* 228 */             return false;
/*     */           }
/*     */           
/*     */           public void close() throws StoreException {
/* 232 */             super.close();
/*     */             try {
/* 234 */               channel.close();
/* 235 */               fis.close();
/* 236 */             } catch (IOException e) {
/* 237 */               throw new StoreException(e);
/*     */             } 
/*     */           }
/*     */         };
/* 242 */       tree.setRoot(FileSystemNode.readNode(0, null, channel, order, useMemoryMapping));
/* 244 */       LOGGER.finest("QuadTree opened");
/* 245 */     } catch (IOException e) {
/* 246 */       throw new StoreException(e);
/*     */     } 
/* 249 */     return tree;
/*     */   }
/*     */   
/*     */   private static ByteOrder byteToOrder(byte order) {
/* 259 */     ByteOrder ret = null;
/* 261 */     switch (order) {
/*     */       case 0:
/* 263 */         ret = ByteOrder.nativeOrder();
/*     */         break;
/*     */       case -1:
/*     */       case 1:
/* 269 */         ret = ByteOrder.LITTLE_ENDIAN;
/*     */         break;
/*     */       case -2:
/*     */       case 2:
/* 275 */         ret = ByteOrder.BIG_ENDIAN;
/*     */         break;
/*     */     } 
/* 280 */     return ret;
/*     */   }
/*     */   
/*     */   public int getByteOrder() {
/* 289 */     return this.byteOrder;
/*     */   }
/*     */   
/*     */   public void setByteOrder(byte byteOrder) {
/* 299 */     this.byteOrder = byteOrder;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\fs\FileSystemIndexStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */