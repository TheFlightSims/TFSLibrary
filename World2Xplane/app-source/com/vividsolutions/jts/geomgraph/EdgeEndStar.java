/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.algorithm.locate.SimplePointInAreaLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public abstract class EdgeEndStar {
/*  58 */   protected Map edgeMap = new TreeMap<>();
/*     */   
/*     */   protected List edgeList;
/*     */   
/*  66 */   private int[] ptInAreaLocation = new int[] { -1, -1 };
/*     */   
/*     */   public abstract void insert(EdgeEnd paramEdgeEnd);
/*     */   
/*     */   protected void insertEdgeEnd(EdgeEnd e, Object obj) {
/*  84 */     this.edgeMap.put(e, obj);
/*  85 */     this.edgeList = null;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  93 */     Iterator<EdgeEnd> it = iterator();
/*  94 */     if (!it.hasNext())
/*  94 */       return null; 
/*  95 */     EdgeEnd e = it.next();
/*  96 */     return e.getCoordinate();
/*     */   }
/*     */   
/*     */   public int getDegree() {
/* 100 */     return this.edgeMap.size();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 111 */     return getEdges().iterator();
/*     */   }
/*     */   
/*     */   public List getEdges() {
/* 115 */     if (this.edgeList == null)
/* 116 */       this.edgeList = new ArrayList(this.edgeMap.values()); 
/* 118 */     return this.edgeList;
/*     */   }
/*     */   
/*     */   public EdgeEnd getNextCW(EdgeEnd ee) {
/* 122 */     getEdges();
/* 123 */     int i = this.edgeList.indexOf(ee);
/* 124 */     int iNextCW = i - 1;
/* 125 */     if (i == 0)
/* 126 */       iNextCW = this.edgeList.size() - 1; 
/* 127 */     return this.edgeList.get(iNextCW);
/*     */   }
/*     */   
/*     */   public void computeLabelling(GeometryGraph[] geomGraph) {
/* 132 */     computeEdgeEndLabels(geomGraph[0].getBoundaryNodeRule());
/* 136 */     propagateSideLabels(0);
/* 139 */     propagateSideLabels(1);
/* 172 */     boolean[] hasDimensionalCollapseEdge = { false, false };
/* 173 */     for (Iterator<EdgeEnd> iterator1 = iterator(); iterator1.hasNext(); ) {
/* 174 */       EdgeEnd e = iterator1.next();
/* 175 */       Label label = e.getLabel();
/* 176 */       for (int geomi = 0; geomi < 2; geomi++) {
/* 177 */         if (label.isLine(geomi) && label.getLocation(geomi) == 1)
/* 178 */           hasDimensionalCollapseEdge[geomi] = true; 
/*     */       } 
/*     */     } 
/* 182 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 183 */       EdgeEnd e = it.next();
/* 184 */       Label label = e.getLabel();
/* 186 */       for (int geomi = 0; geomi < 2; geomi++) {
/* 187 */         if (label.isAnyNull(geomi)) {
/* 188 */           int loc = -1;
/* 189 */           if (hasDimensionalCollapseEdge[geomi]) {
/* 190 */             loc = 2;
/*     */           } else {
/* 193 */             Coordinate p = e.getCoordinate();
/* 194 */             loc = getLocation(geomi, p, geomGraph);
/*     */           } 
/* 196 */           label.setAllLocationsIfNull(geomi, loc);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeEdgeEndLabels(BoundaryNodeRule boundaryNodeRule) {
/* 208 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 209 */       EdgeEnd ee = it.next();
/* 210 */       ee.computeLabel(boundaryNodeRule);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getLocation(int geomIndex, Coordinate p, GeometryGraph[] geom) {
/* 217 */     if (this.ptInAreaLocation[geomIndex] == -1)
/* 218 */       this.ptInAreaLocation[geomIndex] = SimplePointInAreaLocator.locate(p, geom[geomIndex].getGeometry()); 
/* 220 */     return this.ptInAreaLocation[geomIndex];
/*     */   }
/*     */   
/*     */   public boolean isAreaLabelsConsistent(GeometryGraph geomGraph) {
/* 225 */     computeEdgeEndLabels(geomGraph.getBoundaryNodeRule());
/* 226 */     return checkAreaLabelsConsistent(0);
/*     */   }
/*     */   
/*     */   private boolean checkAreaLabelsConsistent(int geomIndex) {
/* 233 */     List<EdgeEnd> edges = getEdges();
/* 235 */     if (edges.size() <= 0)
/* 236 */       return true; 
/* 238 */     int lastEdgeIndex = edges.size() - 1;
/* 239 */     Label startLabel = ((EdgeEnd)edges.get(lastEdgeIndex)).getLabel();
/* 240 */     int startLoc = startLabel.getLocation(geomIndex, 1);
/* 241 */     Assert.isTrue((startLoc != -1), "Found unlabelled area edge");
/* 243 */     int currLoc = startLoc;
/* 244 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 245 */       EdgeEnd e = it.next();
/* 246 */       Label label = e.getLabel();
/* 248 */       Assert.isTrue(label.isArea(geomIndex), "Found non-area edge");
/* 249 */       int leftLoc = label.getLocation(geomIndex, 1);
/* 250 */       int rightLoc = label.getLocation(geomIndex, 2);
/* 254 */       if (leftLoc == rightLoc)
/* 255 */         return false; 
/* 259 */       if (rightLoc != currLoc)
/* 261 */         return false; 
/* 263 */       currLoc = leftLoc;
/*     */     } 
/* 265 */     return true;
/*     */   }
/*     */   
/*     */   void propagateSideLabels(int geomIndex) {
/* 271 */     int startLoc = -1;
/* 275 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 276 */       EdgeEnd e = it.next();
/* 277 */       Label label = e.getLabel();
/* 278 */       if (label.isArea(geomIndex) && label.getLocation(geomIndex, 1) != -1)
/* 279 */         startLoc = label.getLocation(geomIndex, 1); 
/*     */     } 
/* 283 */     if (startLoc == -1)
/*     */       return; 
/* 285 */     int currLoc = startLoc;
/* 286 */     for (Iterator<EdgeEnd> iterator1 = iterator(); iterator1.hasNext(); ) {
/* 287 */       EdgeEnd e = iterator1.next();
/* 288 */       Label label = e.getLabel();
/* 290 */       if (label.getLocation(geomIndex, 0) == -1)
/* 291 */         label.setLocation(geomIndex, 0, currLoc); 
/* 293 */       if (label.isArea(geomIndex)) {
/* 294 */         int leftLoc = label.getLocation(geomIndex, 1);
/* 295 */         int rightLoc = label.getLocation(geomIndex, 2);
/* 297 */         if (rightLoc != -1) {
/* 299 */           if (rightLoc != currLoc)
/* 300 */             throw new TopologyException("side location conflict", e.getCoordinate()); 
/* 301 */           if (leftLoc == -1)
/* 302 */             Assert.shouldNeverReachHere("found single null side (at " + e.getCoordinate() + ")"); 
/* 304 */           currLoc = leftLoc;
/*     */           continue;
/*     */         } 
/* 313 */         Assert.isTrue((label.getLocation(geomIndex, 1) == -1), "found single null side");
/* 314 */         label.setLocation(geomIndex, 2, currLoc);
/* 315 */         label.setLocation(geomIndex, 1, currLoc);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int findIndex(EdgeEnd eSearch) {
/* 323 */     iterator();
/* 324 */     for (int i = 0; i < this.edgeList.size(); i++) {
/* 325 */       EdgeEnd e = this.edgeList.get(i);
/* 326 */       if (e == eSearch)
/* 326 */         return i; 
/*     */     } 
/* 328 */     return -1;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 333 */     System.out.println("EdgeEndStar:   " + getCoordinate());
/* 334 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 335 */       EdgeEnd e = it.next();
/* 336 */       e.print(out);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 342 */     StringBuffer buf = new StringBuffer();
/* 343 */     buf.append("EdgeEndStar:   " + getCoordinate());
/* 344 */     buf.append("\n");
/* 345 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 346 */       EdgeEnd e = it.next();
/* 347 */       buf.append(e);
/* 348 */       buf.append("\n");
/*     */     } 
/* 350 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeEndStar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */