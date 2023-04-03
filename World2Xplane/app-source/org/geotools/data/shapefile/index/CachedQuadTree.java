/*     */ package org.geotools.data.shapefile.index;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.shapefile.index.quadtree.Node;
/*     */ import org.geotools.data.shapefile.index.quadtree.QuadTree;
/*     */ import org.geotools.data.shapefile.index.quadtree.StoreException;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ 
/*     */ public class CachedQuadTree {
/*  41 */   static final DataDefinition DATA_DEFINITION = new DataDefinition("US-ASCII");
/*     */   
/*     */   MemoryNode root;
/*     */   
/*     */   Indices offsets;
/*     */   
/*     */   static {
/*  43 */     DATA_DEFINITION.addField(Integer.class);
/*  44 */     DATA_DEFINITION.addField(Long.class);
/*     */   }
/*     */   
/*     */   public CachedQuadTree(QuadTree tree) throws IOException {
/*  51 */     this.offsets = new Indices();
/*  52 */     this.root = cloneAndTranslate(tree.getRoot(), tree.getIndexfile());
/*     */   }
/*     */   
/*     */   public Envelope getBounds() {
/*  56 */     return new Envelope(this.root.minx, this.root.maxx, this.root.miny, this.root.maxy);
/*     */   }
/*     */   
/*     */   private MemoryNode cloneAndTranslate(Node node, IndexFile indexfile) throws IOException {
/*  61 */     node.pack();
/*  62 */     int[] shapeIds = node.getShapesId();
/*  63 */     int start = -1;
/*  64 */     int end = -1;
/*  65 */     if (shapeIds != null && shapeIds.length > 0) {
/*  66 */       start = this.offsets.size();
/*  68 */       for (int j = 0; j < shapeIds.length; j++)
/*  69 */         this.offsets.add(indexfile.getOffsetInBytes(shapeIds[j])); 
/*  71 */       end = this.offsets.size();
/*     */     } 
/*  73 */     node.clean();
/*  76 */     MemoryNode mem = new MemoryNode(node.getBounds(), start, end, node.getNumSubNodes());
/*  77 */     for (int i = 0; i < node.getNumSubNodes(); i++)
/*  78 */       mem.subnodes[i] = cloneAndTranslate(node.getSubNode(i), indexfile); 
/*  80 */     node.clearSubNodes();
/*  82 */     return mem;
/*     */   }
/*     */   
/*     */   public CloseableIterator<Data> search(Envelope bounds) throws StoreException {
/*  87 */     final Indices indices = new Indices();
/*  88 */     collectIndices(indices, this.root, bounds);
/*  89 */     indices.sort();
/*  90 */     final Data data = new Data(DATA_DEFINITION);
/*  91 */     return new CloseableIterator<Data>() {
/*     */         boolean read = true;
/*     */         
/*  93 */         int idx = 0;
/*     */         
/*     */         public void remove() {
/*  96 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public Data next() {
/* 100 */           if (!hasNext())
/* 101 */             throw new NoSuchElementException(); 
/* 104 */           this.read = true;
/* 105 */           return data;
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 109 */           if (!this.read)
/* 110 */             return true; 
/* 113 */           if (this.idx >= indices.size())
/* 114 */             return false; 
/*     */           try {
/* 118 */             data.clear();
/* 119 */             data.addValue(Integer.valueOf(0));
/* 120 */             data.addValue(Long.valueOf(indices.get(this.idx)));
/* 121 */             this.idx++;
/* 122 */             this.read = false;
/* 123 */           } catch (Exception e) {
/* 124 */             throw new RuntimeException(e);
/*     */           } 
/* 127 */           return true;
/*     */         }
/*     */         
/*     */         public void close() throws IOException {
/* 131 */           indices.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   void collectIndices(Indices indices, MemoryNode node, Envelope bounds) throws StoreException {
/* 137 */     if (!node.intersects(bounds))
/*     */       return; 
/* 141 */     if (node.start > -1 && node.end >= node.start)
/* 142 */       for (int i = node.start; i < node.end; i++)
/* 143 */         indices.add(this.offsets.get(i));  
/* 147 */     for (MemoryNode child : node.subnodes)
/* 148 */       collectIndices(indices, child, bounds); 
/*     */   }
/*     */   
/*     */   class Indices {
/* 167 */     int[] indices = new int[100];
/*     */     
/* 168 */     int curr = -1;
/*     */     
/*     */     int size() {
/* 176 */       return this.curr + 1;
/*     */     }
/*     */     
/*     */     void add(int index) {
/* 185 */       this.curr++;
/* 186 */       if (this.curr * 2 + 1 >= this.indices.length) {
/* 187 */         int newSize = this.indices.length * 3 / 2;
/* 188 */         if (newSize < 10)
/* 189 */           newSize = 10; 
/* 191 */         int[] resized = new int[newSize];
/* 192 */         System.arraycopy(this.indices, 0, resized, 0, this.indices.length);
/* 193 */         this.indices = resized;
/*     */       } 
/* 195 */       this.indices[this.curr] = index;
/*     */     }
/*     */     
/*     */     void clear() {
/* 202 */       this.curr = -1;
/*     */     }
/*     */     
/*     */     int get(int position) {
/* 206 */       return this.indices[position];
/*     */     }
/*     */     
/*     */     void sort() {
/* 210 */       Arrays.sort(this.indices, 0, this.curr + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   static class MemoryNode {
/*     */     float minx;
/*     */     
/*     */     float miny;
/*     */     
/*     */     float maxx;
/*     */     
/*     */     float maxy;
/*     */     
/*     */     int start;
/*     */     
/*     */     int end;
/*     */     
/*     */     MemoryNode[] subnodes;
/*     */     
/*     */     public MemoryNode(Envelope envelope, int start, int end, int numSubnodes) {
/* 221 */       this.minx = (float)envelope.getMinX();
/* 222 */       this.miny = (float)envelope.getMinY();
/* 223 */       this.maxx = (float)envelope.getMaxX();
/* 224 */       this.maxy = (float)envelope.getMaxY();
/* 225 */       this.start = start;
/* 226 */       this.end = end;
/* 227 */       this.subnodes = new MemoryNode[numSubnodes];
/*     */     }
/*     */     
/*     */     public boolean intersects(Envelope bounds) {
/* 232 */       return (new Envelope(this.minx, this.maxx, this.miny, this.maxy)).intersects(bounds);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\CachedQuadTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */