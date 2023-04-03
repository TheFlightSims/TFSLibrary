/*     */ package org.geotools.data.shapefile.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.shapefile.index.CloseableIterator;
/*     */ import org.geotools.data.shapefile.index.Data;
/*     */ import org.geotools.data.shapefile.index.DataDefinition;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ 
/*     */ public class LazySearchIterator implements CloseableIterator<Data> {
/*  46 */   static final int[] ZERO = new int[0];
/*     */   
/*  48 */   static final DataDefinition DATA_DEFINITION = new DataDefinition("US-ASCII");
/*     */   
/*     */   private static final int MAX_INDICES = 32768;
/*     */   
/*     */   static {
/*  52 */     DATA_DEFINITION.addField(Integer.class);
/*  53 */     DATA_DEFINITION.addField(Long.class);
/*     */   }
/*     */   
/*  56 */   Data next = null;
/*     */   
/*     */   Node current;
/*     */   
/*  60 */   int idIndex = 0;
/*     */   
/*     */   private boolean closed;
/*     */   
/*     */   private Envelope bounds;
/*     */   
/*     */   Iterator data;
/*     */   
/*     */   private IndexFile indexfile;
/*     */   
/*  70 */   ArrayList<Node> parents = new ArrayList<Node>();
/*     */   
/*  72 */   Indices indices = new Indices();
/*     */   
/*     */   QuadTree tree;
/*     */   
/*     */   public LazySearchIterator(QuadTree tree, Envelope bounds) {
/*  78 */     this.tree = tree;
/*  79 */     this.indexfile = tree.getIndexfile();
/*  80 */     tree.registerIterator((Iterator)this);
/*  81 */     this.current = tree.getRoot();
/*  82 */     this.bounds = bounds;
/*  83 */     this.closed = false;
/*  84 */     this.next = null;
/*  85 */     this.indexfile = this.indexfile;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  89 */     if (this.closed)
/*  90 */       throw new IllegalStateException("Iterator has been closed!"); 
/*  91 */     if (this.next != null)
/*  92 */       return true; 
/*  93 */     if (this.data != null && this.data.hasNext()) {
/*  94 */       this.next = this.data.next();
/*     */     } else {
/*  96 */       this.data = null;
/*  97 */       fillCache();
/*  98 */       if (this.data != null && this.data.hasNext())
/*  99 */         this.next = this.data.next(); 
/*     */     } 
/* 101 */     return (this.next != null);
/*     */   }
/*     */   
/*     */   private void fillCache() {
/* 105 */     this.indices.clear();
/* 106 */     ArrayList<Data> dataList = null;
/*     */     try {
/* 108 */       while (this.indices.size() < 32768 && this.current != null) {
/* 109 */         if (this.idIndex < this.current.getNumShapeIds() && !this.current.isVisited() && this.current.getBounds().intersects(this.bounds)) {
/* 111 */           this.indices.add(this.current.getShapeId(this.idIndex));
/* 112 */           this.idIndex++;
/*     */           continue;
/*     */         } 
/* 115 */         this.current.setShapesId(new int[0]);
/* 116 */         this.idIndex = 0;
/* 118 */         boolean foundUnvisited = false;
/* 119 */         for (int j = 0; j < this.current.getNumSubNodes(); j++) {
/* 120 */           Node node = this.current.getSubNode(j);
/* 121 */           if (!node.isVisited() && node.getBounds().intersects(this.bounds)) {
/* 123 */             foundUnvisited = true;
/* 124 */             this.parents.add(this.current);
/* 125 */             this.current = node;
/*     */             break;
/*     */           } 
/*     */         } 
/* 129 */         if (!foundUnvisited) {
/* 131 */           this.current.setVisited(true);
/* 132 */           this.current.clean();
/* 135 */           if (this.parents.isEmpty()) {
/* 136 */             this.current = null;
/*     */             continue;
/*     */           } 
/* 138 */           this.current = this.parents.remove(this.parents.size() - 1);
/*     */         } 
/*     */       } 
/* 144 */       this.indices.sort();
/* 145 */       int size = this.indices.size();
/* 146 */       dataList = new ArrayList(size);
/* 147 */       for (int i = 0; i < size; i++) {
/* 148 */         int recno = this.indices.get(i);
/* 149 */         Data data = new Data(DATA_DEFINITION);
/* 150 */         data.addValue(Integer.valueOf(recno + 1));
/* 151 */         data.addValue(new Long(this.indexfile.getOffsetInBytes(recno)));
/* 152 */         dataList.add(data);
/*     */       } 
/* 154 */     } catch (IOException e) {
/* 155 */       throw new RuntimeException(e);
/*     */     } 
/* 157 */     this.data = dataList.iterator();
/*     */   }
/*     */   
/*     */   public Data next() {
/* 161 */     if (!hasNext())
/* 162 */       throw new NoSuchElementException("No more elements available"); 
/* 163 */     Data temp = this.next;
/* 164 */     this.next = null;
/* 165 */     return temp;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 169 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 173 */     this.tree.close((Iterator)this);
/* 174 */     this.tree.close();
/* 175 */     this.closed = true;
/*     */   }
/*     */   
/*     */   class Indices {
/* 193 */     int[] indices = new int[100];
/*     */     
/* 194 */     int curr = -1;
/*     */     
/*     */     int size() {
/* 202 */       return this.curr + 1;
/*     */     }
/*     */     
/*     */     void add(int index) {
/* 211 */       this.curr++;
/* 212 */       if (this.curr * 2 + 1 >= this.indices.length) {
/* 213 */         int newSize = this.indices.length * 3 / 2;
/* 214 */         if (newSize < 10)
/* 215 */           newSize = 10; 
/* 217 */         int[] resized = new int[newSize];
/* 218 */         System.arraycopy(this.indices, 0, resized, 0, this.indices.length);
/* 219 */         this.indices = resized;
/*     */       } 
/* 221 */       this.indices[this.curr] = index;
/*     */     }
/*     */     
/*     */     void clear() {
/* 228 */       this.curr = -1;
/*     */     }
/*     */     
/*     */     int get(int position) {
/* 232 */       return this.indices[position];
/*     */     }
/*     */     
/*     */     void sort() {
/* 236 */       Arrays.sort(this.indices, 0, this.curr + 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\LazySearchIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */