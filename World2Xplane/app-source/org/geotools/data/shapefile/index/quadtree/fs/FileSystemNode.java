/*     */ package org.geotools.data.shapefile.index.quadtree.fs;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import org.geotools.data.shapefile.index.quadtree.Node;
/*     */ import org.geotools.data.shapefile.index.quadtree.StoreException;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ 
/*     */ public class FileSystemNode extends Node {
/*  41 */   static final int[] ZERO = new int[0];
/*     */   
/*     */   private ScrollingBuffer buffer;
/*     */   
/*     */   private ByteOrder order;
/*     */   
/*     */   private int subNodeStartByte;
/*     */   
/*     */   private int subNodesLength;
/*     */   
/*     */   private int numSubNodes;
/*     */   
/*     */   FileSystemNode(Envelope bounds, ScrollingBuffer buffer, int startByte, int subNodesLength) {
/*  63 */     super(bounds);
/*  64 */     this.buffer = buffer;
/*  65 */     this.subNodeStartByte = startByte;
/*  66 */     this.subNodesLength = subNodesLength;
/*     */   }
/*     */   
/*     */   public Node copy() throws IOException {
/*  70 */     FileSystemNode copy = new FileSystemNode(getBounds(), this.buffer, this.subNodeStartByte, this.subNodesLength);
/*  71 */     copy.numShapesId = this.numShapesId;
/*  72 */     copy.shapesId = new int[this.numShapesId];
/*  73 */     System.arraycopy(this.shapesId, 0, copy.shapesId, 0, this.numShapesId);
/*  74 */     copy.numSubNodes = this.numSubNodes;
/*  75 */     return copy;
/*     */   }
/*     */   
/*     */   public int getNumSubNodes() {
/*  84 */     return this.numSubNodes;
/*     */   }
/*     */   
/*     */   public void setNumSubNodes(int numSubNodes) {
/*  94 */     this.numSubNodes = numSubNodes;
/*     */   }
/*     */   
/*     */   public int getSubNodeStartByte() {
/* 103 */     return this.subNodeStartByte;
/*     */   }
/*     */   
/*     */   public int getSubNodesLength() {
/* 112 */     return this.subNodesLength;
/*     */   }
/*     */   
/*     */   public Node getSubNode(int pos) throws StoreException {
/* 119 */     if (this.subNodes.size() > pos)
/* 120 */       return super.getSubNode(pos); 
/*     */     try {
/* 124 */       FileSystemNode subNode = null;
/* 127 */       int offset = this.subNodeStartByte;
/* 129 */       if (pos > 0) {
/* 130 */         subNode = (FileSystemNode)getSubNode(pos - 1);
/* 131 */         offset = subNode.getSubNodeStartByte() + subNode.getSubNodesLength();
/*     */       } 
/* 135 */       this.buffer.goTo(offset);
/* 136 */       for (int i = 0, ii = this.subNodes.size(); i < pos + 1 - ii; i++) {
/* 137 */         subNode = readNode(pos, this, this.buffer);
/* 138 */         addSubNode(subNode);
/*     */       } 
/* 140 */     } catch (IOException e) {
/* 141 */       throw new StoreException(e);
/*     */     } 
/* 144 */     return super.getSubNode(pos);
/*     */   }
/*     */   
/*     */   public static FileSystemNode readNode(int id, Node parent, FileChannel channel, ByteOrder order, boolean useMemoryMapping) throws IOException {
/* 159 */     ScrollingBuffer buffer = new ScrollingBuffer(channel, order, useMemoryMapping);
/* 160 */     return readNode(id, parent, buffer);
/*     */   }
/*     */   
/*     */   static FileSystemNode readNode(int id, Node parent, ScrollingBuffer buf) throws IOException {
/* 166 */     int offset = buf.getInt();
/* 169 */     Envelope env = buf.getEnvelope();
/* 172 */     int numShapesId = buf.getInt();
/* 173 */     int[] ids = null;
/* 174 */     if (numShapesId > 0) {
/* 175 */       ids = new int[numShapesId];
/* 176 */       buf.getIntArray(ids);
/*     */     } else {
/* 178 */       ids = ZERO;
/*     */     } 
/* 180 */     int numSubNodes = buf.getInt();
/* 183 */     FileSystemNode node = new FileSystemNode(env, buf, (int)buf.getPosition(), offset);
/* 184 */     node.setShapesId(ids);
/* 185 */     node.setNumSubNodes(numSubNodes);
/* 187 */     return node;
/*     */   }
/*     */   
/*     */   public void close() {
/* 192 */     if (this.buffer != null)
/* 193 */       this.buffer.close(); 
/* 195 */     this.buffer = null;
/*     */   }
/*     */   
/*     */   private static class ScrollingBuffer {
/*     */     FileChannel channel;
/*     */     
/*     */     ByteOrder order;
/*     */     
/*     */     ByteBuffer buffer;
/*     */     
/*     */     long bufferStart;
/*     */     
/* 208 */     double[] envelope = new double[4];
/*     */     
/*     */     boolean useMemoryMapping;
/*     */     
/*     */     public ScrollingBuffer(FileChannel channel, ByteOrder order, boolean useMemoryMapping) throws IOException {
/* 213 */       this.channel = channel;
/* 214 */       this.order = order;
/* 215 */       this.useMemoryMapping = useMemoryMapping;
/* 217 */       this.bufferStart = channel.position();
/* 218 */       if (useMemoryMapping) {
/* 219 */         this.buffer = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(), channel.size() - channel.position());
/* 220 */         this.buffer.order(order);
/*     */       } else {
/* 223 */         this.buffer = NIOUtilities.allocate(8192);
/* 224 */         this.buffer.order(order);
/* 225 */         channel.read(this.buffer);
/* 226 */         this.buffer.flip();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 231 */       if (this.buffer != null) {
/* 232 */         NIOUtilities.clean(this.buffer, this.useMemoryMapping);
/* 233 */         this.buffer = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int getInt() throws IOException {
/* 239 */       if (!this.useMemoryMapping && this.buffer.remaining() < 4)
/* 240 */         refillBuffer(4); 
/* 242 */       return this.buffer.getInt();
/*     */     }
/*     */     
/*     */     public Envelope getEnvelope() throws IOException {
/* 246 */       if (!this.useMemoryMapping && this.buffer.remaining() < 32)
/* 247 */         refillBuffer(32); 
/* 249 */       this.buffer.asDoubleBuffer().get(this.envelope);
/* 250 */       this.buffer.position(this.buffer.position() + 32);
/* 251 */       return new Envelope(this.envelope[0], this.envelope[2], this.envelope[1], this.envelope[3]);
/*     */     }
/*     */     
/*     */     public void getIntArray(int[] array) throws IOException {
/* 255 */       int size = array.length * 4;
/* 256 */       if (this.buffer.remaining() < size)
/* 257 */         refillBuffer(size); 
/* 259 */       IntBuffer intView = this.buffer.asIntBuffer();
/* 260 */       intView.limit(array.length);
/* 261 */       intView.get(array);
/* 264 */       this.buffer.position(this.buffer.position() + size);
/*     */     }
/*     */     
/*     */     void refillBuffer(int requiredSize) throws IOException {
/* 274 */       long currentPosition = this.bufferStart + this.buffer.position();
/* 276 */       if (this.buffer.capacity() < requiredSize) {
/* 277 */         int size = this.buffer.capacity();
/* 278 */         while (size < requiredSize)
/* 279 */           size *= 2; 
/* 280 */         this.buffer = NIOUtilities.allocate(size);
/* 281 */         this.buffer.order(this.order);
/*     */       } 
/* 283 */       readBuffer(currentPosition);
/*     */     }
/*     */     
/*     */     private void readBuffer(long currentPosition) throws IOException {
/* 287 */       this.channel.position(currentPosition);
/* 288 */       this.buffer.clear();
/* 289 */       this.channel.read(this.buffer);
/* 290 */       this.buffer.flip();
/* 291 */       this.bufferStart = currentPosition;
/*     */     }
/*     */     
/*     */     public void goTo(long newPosition) throws IOException {
/* 304 */       if (this.useMemoryMapping || (newPosition >= this.bufferStart && newPosition <= this.bufferStart + this.buffer.limit())) {
/* 306 */         this.buffer.position((int)(newPosition - this.bufferStart));
/*     */       } else {
/* 308 */         readBuffer(newPosition);
/*     */       } 
/*     */     }
/*     */     
/*     */     public long getPosition() {
/* 318 */       return this.bufferStart + this.buffer.position();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\fs\FileSystemNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */