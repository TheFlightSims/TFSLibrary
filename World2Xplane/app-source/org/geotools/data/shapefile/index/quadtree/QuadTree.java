/*     */ package org.geotools.data.shapefile.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.shapefile.index.CloseableIterator;
/*     */ import org.geotools.data.shapefile.index.Data;
/*     */ import org.geotools.data.shapefile.shp.IndexFile;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class QuadTree {
/*     */   private static final double SPLITRATIO = 0.55D;
/*     */   
/*  49 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.index.quadtree");
/*     */   
/*     */   private Node root;
/*     */   
/*     */   private int numShapes;
/*     */   
/*     */   private int maxDepth;
/*     */   
/*     */   private IndexFile indexfile;
/*     */   
/*  58 */   private Set iterators = new HashSet();
/*     */   
/*     */   public QuadTree(int numShapes, Envelope maxBounds, IndexFile file) {
/*  69 */     this(numShapes, 0, maxBounds, file);
/*     */   }
/*     */   
/*     */   public QuadTree(int numShapes, int maxDepth, Envelope maxBounds, IndexFile file) {
/*  84 */     if (maxDepth > 65535)
/*  85 */       throw new IllegalArgumentException("maxDepth must be <= 65535"); 
/*  88 */     this.numShapes = numShapes;
/*  89 */     this.maxDepth = maxDepth;
/*  91 */     if (maxBounds != null)
/*  92 */       this.root = new Node(new Envelope(maxBounds)); 
/*  94 */     if (maxDepth < 1) {
/*  99 */       int numNodes = 1;
/* 100 */       this.maxDepth = 0;
/* 102 */       while (numNodes * 4 < numShapes) {
/* 103 */         this.maxDepth++;
/* 104 */         numNodes *= 2;
/*     */       } 
/*     */     } 
/* 107 */     this.indexfile = file;
/*     */   }
/*     */   
/*     */   public QuadTree(int numShapes, int maxDepth, IndexFile file) {
/* 120 */     this(numShapes, maxDepth, null, file);
/*     */   }
/*     */   
/*     */   public void insert(int recno, Envelope bounds) throws StoreException {
/* 132 */     insert(this.root, recno, bounds, this.maxDepth);
/*     */   }
/*     */   
/*     */   public void insert(Node node, int recno, Envelope bounds, int maxDepth) throws StoreException {
/* 147 */     if (maxDepth > 1 && node.getNumSubNodes() > 0) {
/* 152 */       Node subNode = null;
/* 153 */       for (int i = 0; i < node.getNumSubNodes(); i++) {
/* 154 */         subNode = node.getSubNode(i);
/* 155 */         if (subNode.getBounds().contains(bounds)) {
/* 156 */           insert(subNode, recno, bounds, maxDepth - 1);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 161 */     if (maxDepth > 1 && node.getNumSubNodes() < 4) {
/* 168 */       Envelope[] tmp = splitBounds(node.getBounds());
/* 169 */       Envelope half1 = tmp[0];
/* 170 */       Envelope half2 = tmp[1];
/* 172 */       tmp = splitBounds(half1);
/* 173 */       Envelope quad1 = tmp[0];
/* 174 */       Envelope quad2 = tmp[1];
/* 176 */       tmp = splitBounds(half2);
/* 177 */       Envelope quad3 = tmp[0];
/* 178 */       Envelope quad4 = tmp[1];
/* 180 */       Node subnode = null;
/* 181 */       if (quad1.contains(bounds)) {
/* 182 */         subnode = new Node(quad1);
/* 183 */       } else if (quad2.contains(bounds)) {
/* 184 */         subnode = new Node(quad2);
/* 185 */       } else if (quad3.contains(bounds)) {
/* 186 */         subnode = new Node(quad3);
/* 187 */       } else if (quad4.contains(bounds)) {
/* 188 */         subnode = new Node(quad4);
/*     */       } 
/* 191 */       if (subnode != null) {
/* 192 */         node.addSubNode(subnode);
/* 193 */         insert(subnode, recno, bounds, maxDepth - 1);
/*     */         return;
/*     */       } 
/*     */     } 
/* 199 */     node.addShapeId(recno);
/*     */   }
/*     */   
/*     */   public CloseableIterator<Data> search(Envelope bounds) throws StoreException {
/* 208 */     if (LOGGER.isLoggable(Level.FINEST))
/* 209 */       LOGGER.log(Level.FINEST, "Querying " + bounds); 
/*     */     try {
/* 213 */       return new LazySearchIterator(this, bounds);
/* 214 */     } catch (RuntimeException e) {
/* 215 */       LOGGER.warning("IOException occurred while reading root");
/* 216 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close(Iterator iter) throws IOException {
/* 226 */     this.iterators.remove(iter);
/*     */   }
/*     */   
/*     */   public boolean trim() throws StoreException {
/* 233 */     LOGGER.fine("Trimming the tree...");
/* 234 */     return trim(this.root);
/*     */   }
/*     */   
/*     */   private boolean trim(Node node) throws StoreException {
/* 245 */     Node[] dummy = new Node[node.getNumSubNodes()];
/*     */     int i;
/* 246 */     for (i = 0; i < node.getNumSubNodes(); i++)
/* 247 */       dummy[i] = node.getSubNode(i); 
/* 250 */     for (i = 0; i < dummy.length; i++) {
/* 251 */       if (trim(dummy[i]))
/* 252 */         node.removeSubNode(dummy[i]); 
/*     */     } 
/* 260 */     if (node.getNumSubNodes() == 1 && node.getNumShapeIds() == 0) {
/* 261 */       Node subNode = node.getSubNode(0);
/* 263 */       node.clearSubNodes();
/* 264 */       for (int j = 0; j < subNode.getNumSubNodes(); j++)
/* 265 */         node.addSubNode(subNode.getSubNode(j)); 
/* 268 */       node.setShapesId(subNode.getShapesId());
/* 269 */       node.setBounds(subNode.getBounds());
/*     */     } 
/* 272 */     return (node.getNumSubNodes() == 0 && node.getNumShapeIds() == 0);
/*     */   }
/*     */   
/*     */   private Envelope[] splitBounds(Envelope in) {
/* 283 */     Envelope[] ret = new Envelope[2];
/* 286 */     if (in.getMaxX() - in.getMinX() > in.getMaxY() - in.getMinY()) {
/* 288 */       double range = in.getMaxX() - in.getMinX();
/* 290 */       double calc = in.getMinX() + range * 0.55D;
/* 291 */       ret[0] = new Envelope(in.getMinX(), calc, in.getMinY(), in.getMaxY());
/* 294 */       calc = in.getMaxX() - range * 0.55D;
/* 295 */       ret[1] = new Envelope(calc, in.getMaxX(), in.getMinY(), in.getMaxY());
/*     */     } else {
/* 299 */       double range = in.getMaxY() - in.getMinY();
/* 301 */       double calc = in.getMinY() + range * 0.55D;
/* 302 */       ret[0] = new Envelope(in.getMinX(), in.getMaxX(), in.getMinY(), calc);
/* 305 */       calc = in.getMaxY() - range * 0.55D;
/* 306 */       ret[1] = new Envelope(in.getMinX(), in.getMaxX(), calc, in.getMaxY());
/*     */     } 
/* 310 */     return ret;
/*     */   }
/*     */   
/*     */   public int getMaxDepth() {
/* 317 */     return this.maxDepth;
/*     */   }
/*     */   
/*     */   public void setMaxDepth(int maxDepth) {
/* 325 */     this.maxDepth = maxDepth;
/*     */   }
/*     */   
/*     */   public int getNumShapes() {
/* 332 */     return this.numShapes;
/*     */   }
/*     */   
/*     */   public void setNumShapes(int numShapes) {
/* 340 */     this.numShapes = numShapes;
/*     */   }
/*     */   
/*     */   public Node getRoot() {
/* 347 */     return this.root;
/*     */   }
/*     */   
/*     */   public void setRoot(Node root) {
/* 355 */     this.root = root;
/*     */   }
/*     */   
/*     */   public void close() throws StoreException {
/*     */     try {
/* 360 */       this.indexfile.close();
/* 361 */       this.root.close();
/* 362 */     } catch (IOException e) {
/* 363 */       throw new StoreException("error closing indexfile", e.getCause());
/*     */     } 
/* 365 */     if (!this.iterators.isEmpty())
/* 366 */       throw new StoreException("There are still open iterators!!"); 
/*     */   }
/*     */   
/*     */   public void registerIterator(Iterator object) {
/* 371 */     this.iterators.add(object);
/*     */   }
/*     */   
/*     */   public IndexFile getIndexfile() {
/* 375 */     return this.indexfile;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\QuadTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */