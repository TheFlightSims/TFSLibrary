/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class DirectedEdge extends EdgeEnd {
/*     */   protected boolean isForward;
/*     */   
/*     */   public static int depthFactor(int currLocation, int nextLocation) {
/*  56 */     if (currLocation == 2 && nextLocation == 0)
/*  57 */       return 1; 
/*  58 */     if (currLocation == 0 && nextLocation == 2)
/*  59 */       return -1; 
/*  60 */     return 0;
/*     */   }
/*     */   
/*     */   private boolean isInResult = false;
/*     */   
/*     */   private boolean isVisited = false;
/*     */   
/*     */   private DirectedEdge sym;
/*     */   
/*     */   private DirectedEdge next;
/*     */   
/*     */   private DirectedEdge nextMin;
/*     */   
/*     */   private EdgeRing edgeRing;
/*     */   
/*     */   private EdgeRing minEdgeRing;
/*     */   
/*  76 */   private int[] depth = new int[] { 0, -999, -999 };
/*     */   
/*     */   public DirectedEdge(Edge edge, boolean isForward) {
/*  80 */     super(edge);
/*  81 */     this.isForward = isForward;
/*  82 */     if (isForward) {
/*  83 */       init(edge.getCoordinate(0), edge.getCoordinate(1));
/*     */     } else {
/*  86 */       int n = edge.getNumPoints() - 1;
/*  87 */       init(edge.getCoordinate(n), edge.getCoordinate(n - 1));
/*     */     } 
/*  89 */     computeDirectedLabel();
/*     */   }
/*     */   
/*     */   public Edge getEdge() {
/*  91 */     return this.edge;
/*     */   }
/*     */   
/*     */   public void setInResult(boolean isInResult) {
/*  92 */     this.isInResult = isInResult;
/*     */   }
/*     */   
/*     */   public boolean isInResult() {
/*  93 */     return this.isInResult;
/*     */   }
/*     */   
/*     */   public boolean isVisited() {
/*  94 */     return this.isVisited;
/*     */   }
/*     */   
/*     */   public void setVisited(boolean isVisited) {
/*  95 */     this.isVisited = isVisited;
/*     */   }
/*     */   
/*     */   public void setEdgeRing(EdgeRing edgeRing) {
/*  96 */     this.edgeRing = edgeRing;
/*     */   }
/*     */   
/*     */   public EdgeRing getEdgeRing() {
/*  97 */     return this.edgeRing;
/*     */   }
/*     */   
/*     */   public void setMinEdgeRing(EdgeRing minEdgeRing) {
/*  98 */     this.minEdgeRing = minEdgeRing;
/*     */   }
/*     */   
/*     */   public EdgeRing getMinEdgeRing() {
/*  99 */     return this.minEdgeRing;
/*     */   }
/*     */   
/*     */   public int getDepth(int position) {
/* 100 */     return this.depth[position];
/*     */   }
/*     */   
/*     */   public void setDepth(int position, int depthVal) {
/* 104 */     if (this.depth[position] != -999)
/* 108 */       if (this.depth[position] != depthVal)
/* 109 */         throw new TopologyException("assigned depths do not match", getCoordinate());  
/* 112 */     this.depth[position] = depthVal;
/*     */   }
/*     */   
/*     */   public int getDepthDelta() {
/* 117 */     int depthDelta = this.edge.getDepthDelta();
/* 118 */     if (!this.isForward)
/* 118 */       depthDelta = -depthDelta; 
/* 119 */     return depthDelta;
/*     */   }
/*     */   
/*     */   public void setVisitedEdge(boolean isVisited) {
/* 129 */     setVisited(isVisited);
/* 130 */     this.sym.setVisited(isVisited);
/*     */   }
/*     */   
/*     */   public DirectedEdge getSym() {
/* 137 */     return this.sym;
/*     */   }
/*     */   
/*     */   public boolean isForward() {
/* 138 */     return this.isForward;
/*     */   }
/*     */   
/*     */   public void setSym(DirectedEdge de) {
/* 141 */     this.sym = de;
/*     */   }
/*     */   
/*     */   public DirectedEdge getNext() {
/* 143 */     return this.next;
/*     */   }
/*     */   
/*     */   public void setNext(DirectedEdge next) {
/* 144 */     this.next = next;
/*     */   }
/*     */   
/*     */   public DirectedEdge getNextMin() {
/* 145 */     return this.nextMin;
/*     */   }
/*     */   
/*     */   public void setNextMin(DirectedEdge nextMin) {
/* 146 */     this.nextMin = nextMin;
/*     */   }
/*     */   
/*     */   public boolean isLineEdge() {
/* 157 */     boolean isLine = (this.label.isLine(0) || this.label.isLine(1));
/* 158 */     boolean isExteriorIfArea0 = (!this.label.isArea(0) || this.label.allPositionsEqual(0, 2));
/* 160 */     boolean isExteriorIfArea1 = (!this.label.isArea(1) || this.label.allPositionsEqual(1, 2));
/* 163 */     return (isLine && isExteriorIfArea0 && isExteriorIfArea1);
/*     */   }
/*     */   
/*     */   public boolean isInteriorAreaEdge() {
/* 176 */     boolean isInteriorAreaEdge = true;
/* 177 */     for (int i = 0; i < 2; i++) {
/* 178 */       if (!this.label.isArea(i) || this.label.getLocation(i, 1) != 0 || this.label.getLocation(i, 2) != 0)
/* 181 */         isInteriorAreaEdge = false; 
/*     */     } 
/* 184 */     return isInteriorAreaEdge;
/*     */   }
/*     */   
/*     */   private void computeDirectedLabel() {
/* 192 */     this.label = new Label(this.edge.getLabel());
/* 193 */     if (!this.isForward)
/* 194 */       this.label.flip(); 
/*     */   }
/*     */   
/*     */   public void setEdgeDepths(int position, int depth) {
/* 204 */     int depthDelta = getEdge().getDepthDelta();
/* 205 */     if (!this.isForward)
/* 205 */       depthDelta = -depthDelta; 
/* 208 */     int directionFactor = 1;
/* 209 */     if (position == 1)
/* 210 */       directionFactor = -1; 
/* 212 */     int oppositePos = Position.opposite(position);
/* 213 */     int delta = depthDelta * directionFactor;
/* 215 */     int oppositeDepth = depth + delta;
/* 216 */     setDepth(position, depth);
/* 217 */     setDepth(oppositePos, oppositeDepth);
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 222 */     super.print(out);
/* 223 */     out.print(" " + this.depth[1] + "/" + this.depth[2]);
/* 224 */     out.print(" (" + getDepthDelta() + ")");
/* 227 */     if (this.isInResult)
/* 227 */       out.print(" inResult"); 
/*     */   }
/*     */   
/*     */   public void printEdge(PrintStream out) {
/* 231 */     print(out);
/* 232 */     out.print(" ");
/* 233 */     if (this.isForward) {
/* 234 */       this.edge.print(out);
/*     */     } else {
/* 236 */       this.edge.printReverse(out);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\DirectedEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */