/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DirectedEdgeStar extends EdgeEndStar {
/*     */   private List resultAreaEdgeList;
/*     */   
/*     */   private Label label;
/*     */   
/*     */   public void insert(EdgeEnd ee) {
/*  67 */     DirectedEdge de = (DirectedEdge)ee;
/*  68 */     insertEdgeEnd(de, de);
/*     */   }
/*     */   
/*     */   public Label getLabel() {
/*  71 */     return this.label;
/*     */   }
/*     */   
/*     */   public int getOutgoingDegree() {
/*  75 */     int degree = 0;
/*  76 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/*  77 */       DirectedEdge de = it.next();
/*  78 */       if (de.isInResult())
/*  78 */         degree++; 
/*     */     } 
/*  80 */     return degree;
/*     */   }
/*     */   
/*     */   public int getOutgoingDegree(EdgeRing er) {
/*  84 */     int degree = 0;
/*  85 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/*  86 */       DirectedEdge de = it.next();
/*  87 */       if (de.getEdgeRing() == er)
/*  87 */         degree++; 
/*     */     } 
/*  89 */     return degree;
/*     */   }
/*     */   
/*     */   public DirectedEdge getRightmostEdge() {
/*  94 */     List<DirectedEdge> edges = getEdges();
/*  95 */     int size = edges.size();
/*  96 */     if (size < 1)
/*  96 */       return null; 
/*  97 */     DirectedEdge de0 = edges.get(0);
/*  98 */     if (size == 1)
/*  98 */       return de0; 
/*  99 */     DirectedEdge deLast = edges.get(size - 1);
/* 101 */     int quad0 = de0.getQuadrant();
/* 102 */     int quad1 = deLast.getQuadrant();
/* 103 */     if (Quadrant.isNorthern(quad0) && Quadrant.isNorthern(quad1))
/* 104 */       return de0; 
/* 105 */     if (!Quadrant.isNorthern(quad0) && !Quadrant.isNorthern(quad1))
/* 106 */       return deLast; 
/* 110 */     DirectedEdge nonHorizontalEdge = null;
/* 111 */     if (de0.getDy() != 0.0D)
/* 112 */       return de0; 
/* 113 */     if (deLast.getDy() != 0.0D)
/* 114 */       return deLast; 
/* 116 */     Assert.shouldNeverReachHere("found two horizontal edges incident on node");
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public void computeLabelling(GeometryGraph[] geom) {
/* 127 */     super.computeLabelling(geom);
/* 131 */     this.label = new Label(-1);
/* 132 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 133 */       EdgeEnd ee = it.next();
/* 134 */       Edge e = ee.getEdge();
/* 135 */       Label eLabel = e.getLabel();
/* 136 */       for (int i = 0; i < 2; i++) {
/* 137 */         int eLoc = eLabel.getLocation(i);
/* 138 */         if (eLoc == 0 || eLoc == 1)
/* 139 */           this.label.setLocation(i, 0); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mergeSymLabels() {
/* 151 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/* 152 */       DirectedEdge de = it.next();
/* 153 */       Label label = de.getLabel();
/* 154 */       label.merge(de.getSym().getLabel());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateLabelling(Label nodeLabel) {
/* 163 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/* 164 */       DirectedEdge de = it.next();
/* 165 */       Label label = de.getLabel();
/* 166 */       label.setAllLocationsIfNull(0, nodeLabel.getLocation(0));
/* 167 */       label.setAllLocationsIfNull(1, nodeLabel.getLocation(1));
/*     */     } 
/*     */   }
/*     */   
/*     */   private List getResultAreaEdges() {
/* 174 */     if (this.resultAreaEdgeList != null)
/* 174 */       return this.resultAreaEdgeList; 
/* 175 */     this.resultAreaEdgeList = new ArrayList();
/* 176 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/* 177 */       DirectedEdge de = it.next();
/* 178 */       if (de.isInResult() || de.getSym().isInResult())
/* 179 */         this.resultAreaEdgeList.add(de); 
/*     */     } 
/* 181 */     return this.resultAreaEdgeList;
/*     */   }
/*     */   
/* 184 */   private final int SCANNING_FOR_INCOMING = 1;
/*     */   
/* 185 */   private final int LINKING_TO_OUTGOING = 2;
/*     */   
/*     */   public void linkResultDirectedEdges() {
/* 207 */     getResultAreaEdges();
/* 209 */     DirectedEdge firstOut = null;
/* 210 */     DirectedEdge incoming = null;
/* 211 */     int state = 1;
/* 213 */     for (int i = 0; i < this.resultAreaEdgeList.size(); i++) {
/* 214 */       DirectedEdge nextOut = this.resultAreaEdgeList.get(i);
/* 215 */       DirectedEdge nextIn = nextOut.getSym();
/* 218 */       if (nextOut.getLabel().isArea()) {
/* 221 */         if (firstOut == null && nextOut.isInResult())
/* 221 */           firstOut = nextOut; 
/* 224 */         switch (state) {
/*     */           case 1:
/* 226 */             if (!nextIn.isInResult())
/*     */               break; 
/* 227 */             incoming = nextIn;
/* 228 */             state = 2;
/*     */             break;
/*     */           case 2:
/* 231 */             if (!nextOut.isInResult())
/*     */               break; 
/* 232 */             incoming.setNext(nextOut);
/* 233 */             state = 1;
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 238 */     if (state == 2) {
/* 240 */       if (firstOut == null)
/* 241 */         throw new TopologyException("no outgoing dirEdge found", getCoordinate()); 
/* 243 */       Assert.isTrue(firstOut.isInResult(), "unable to link last incoming dirEdge");
/* 244 */       incoming.setNext(firstOut);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void linkMinimalDirectedEdges(EdgeRing er) {
/* 250 */     DirectedEdge firstOut = null;
/* 251 */     DirectedEdge incoming = null;
/* 252 */     int state = 1;
/* 254 */     for (int i = this.resultAreaEdgeList.size() - 1; i >= 0; i--) {
/* 255 */       DirectedEdge nextOut = this.resultAreaEdgeList.get(i);
/* 256 */       DirectedEdge nextIn = nextOut.getSym();
/* 259 */       if (firstOut == null && nextOut.getEdgeRing() == er)
/* 259 */         firstOut = nextOut; 
/* 261 */       switch (state) {
/*     */         case 1:
/* 263 */           if (nextIn.getEdgeRing() != er)
/*     */             break; 
/* 264 */           incoming = nextIn;
/* 265 */           state = 2;
/*     */           break;
/*     */         case 2:
/* 268 */           if (nextOut.getEdgeRing() != er)
/*     */             break; 
/* 269 */           incoming.setNextMin(nextOut);
/* 270 */           state = 1;
/*     */           break;
/*     */       } 
/*     */     } 
/* 275 */     if (state == 2) {
/* 276 */       Assert.isTrue((firstOut != null), "found null for first outgoing dirEdge");
/* 277 */       Assert.isTrue((firstOut.getEdgeRing() == er), "unable to link last incoming dirEdge");
/* 278 */       incoming.setNextMin(firstOut);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void linkAllDirectedEdges() {
/* 283 */     getEdges();
/* 285 */     DirectedEdge prevOut = null;
/* 286 */     DirectedEdge firstIn = null;
/* 288 */     for (int i = this.edgeList.size() - 1; i >= 0; i--) {
/* 289 */       DirectedEdge nextOut = this.edgeList.get(i);
/* 290 */       DirectedEdge nextIn = nextOut.getSym();
/* 291 */       if (firstIn == null)
/* 291 */         firstIn = nextIn; 
/* 292 */       if (prevOut != null)
/* 292 */         nextIn.setNext(prevOut); 
/* 294 */       prevOut = nextOut;
/*     */     } 
/* 296 */     firstIn.setNext(prevOut);
/*     */   }
/*     */   
/*     */   public void findCoveredLineEdges() {
/* 319 */     int startLoc = -1;
/* 320 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/* 321 */       DirectedEdge nextOut = it.next();
/* 322 */       DirectedEdge nextIn = nextOut.getSym();
/* 323 */       if (!nextOut.isLineEdge()) {
/* 324 */         if (nextOut.isInResult()) {
/* 325 */           startLoc = 0;
/*     */           break;
/*     */         } 
/* 328 */         if (nextIn.isInResult()) {
/* 329 */           startLoc = 2;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 335 */     if (startLoc == -1)
/*     */       return; 
/* 342 */     int currLoc = startLoc;
/* 343 */     for (Iterator<DirectedEdge> iterator1 = iterator(); iterator1.hasNext(); ) {
/* 344 */       DirectedEdge nextOut = iterator1.next();
/* 345 */       DirectedEdge nextIn = nextOut.getSym();
/* 346 */       if (nextOut.isLineEdge()) {
/* 347 */         nextOut.getEdge().setCovered((currLoc == 0));
/*     */         continue;
/*     */       } 
/* 351 */       if (nextOut.isInResult())
/* 352 */         currLoc = 2; 
/* 353 */       if (nextIn.isInResult())
/* 354 */         currLoc = 0; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeDepths(DirectedEdge de) {
/* 361 */     int edgeIndex = findIndex(de);
/* 362 */     Label label = de.getLabel();
/* 363 */     int startDepth = de.getDepth(1);
/* 364 */     int targetLastDepth = de.getDepth(2);
/* 366 */     int nextDepth = computeDepths(edgeIndex + 1, this.edgeList.size(), startDepth);
/* 368 */     int lastDepth = computeDepths(0, edgeIndex, nextDepth);
/* 371 */     if (lastDepth != targetLastDepth)
/* 372 */       throw new TopologyException("depth mismatch at " + de.getCoordinate()); 
/*     */   }
/*     */   
/*     */   private int computeDepths(int startIndex, int endIndex, int startDepth) {
/* 383 */     int currDepth = startDepth;
/* 384 */     for (int i = startIndex; i < endIndex; i++) {
/* 385 */       DirectedEdge nextDe = this.edgeList.get(i);
/* 386 */       Label label = nextDe.getLabel();
/* 387 */       nextDe.setEdgeDepths(2, currDepth);
/* 388 */       currDepth = nextDe.getDepth(1);
/*     */     } 
/* 390 */     return currDepth;
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 395 */     System.out.println("DirectedEdgeStar: " + getCoordinate());
/* 396 */     for (Iterator<DirectedEdge> it = iterator(); it.hasNext(); ) {
/* 397 */       DirectedEdge de = it.next();
/* 398 */       out.print("out ");
/* 399 */       de.print(out);
/* 400 */       out.println();
/* 401 */       out.print("in ");
/* 402 */       de.getSym().print(out);
/* 403 */       out.println();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\DirectedEdgeStar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */