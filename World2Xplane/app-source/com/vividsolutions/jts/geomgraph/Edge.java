/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*     */ import com.vividsolutions.jts.geomgraph.index.MonotoneChainEdge;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class Edge extends GraphComponent {
/*     */   Coordinate[] pts;
/*     */   
/*     */   private Envelope env;
/*     */   
/*     */   public static void updateIM(Label label, IntersectionMatrix im) {
/*  60 */     im.setAtLeastIfValid(label.getLocation(0, 0), label.getLocation(1, 0), 1);
/*  61 */     if (label.isArea()) {
/*  62 */       im.setAtLeastIfValid(label.getLocation(0, 1), label.getLocation(1, 1), 2);
/*  63 */       im.setAtLeastIfValid(label.getLocation(0, 2), label.getLocation(1, 2), 2);
/*     */     } 
/*     */   }
/*     */   
/*  69 */   EdgeIntersectionList eiList = new EdgeIntersectionList(this);
/*     */   
/*     */   private String name;
/*     */   
/*     */   private MonotoneChainEdge mce;
/*     */   
/*     */   private boolean isIsolated = true;
/*     */   
/*  73 */   private Depth depth = new Depth();
/*     */   
/*  74 */   private int depthDelta = 0;
/*     */   
/*     */   public Edge(Coordinate[] pts, Label label) {
/*  78 */     this.pts = pts;
/*  79 */     this.label = label;
/*     */   }
/*     */   
/*     */   public Edge(Coordinate[] pts) {
/*  83 */     this(pts, null);
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/*  86 */     return this.pts.length;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  87 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  88 */     return this.pts;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  91 */     return this.pts[i];
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  95 */     if (this.pts.length > 0)
/*  95 */       return this.pts[0]; 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/* 101 */     if (this.env == null) {
/* 102 */       this.env = new Envelope();
/* 103 */       for (int i = 0; i < this.pts.length; i++)
/* 104 */         this.env.expandToInclude(this.pts[i]); 
/*     */     } 
/* 107 */     return this.env;
/*     */   }
/*     */   
/*     */   public Depth getDepth() {
/* 110 */     return this.depth;
/*     */   }
/*     */   
/*     */   public int getDepthDelta() {
/* 116 */     return this.depthDelta;
/*     */   }
/*     */   
/*     */   public void setDepthDelta(int depthDelta) {
/* 117 */     this.depthDelta = depthDelta;
/*     */   }
/*     */   
/*     */   public int getMaximumSegmentIndex() {
/* 121 */     return this.pts.length - 1;
/*     */   }
/*     */   
/*     */   public EdgeIntersectionList getEdgeIntersectionList() {
/* 123 */     return this.eiList;
/*     */   }
/*     */   
/*     */   public MonotoneChainEdge getMonotoneChainEdge() {
/* 127 */     if (this.mce == null)
/* 127 */       this.mce = new MonotoneChainEdge(this); 
/* 128 */     return this.mce;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 133 */     return this.pts[0].equals(this.pts[this.pts.length - 1]);
/*     */   }
/*     */   
/*     */   public boolean isCollapsed() {
/* 141 */     if (!this.label.isArea())
/* 141 */       return false; 
/* 142 */     if (this.pts.length != 3)
/* 142 */       return false; 
/* 143 */     if (this.pts[0].equals(this.pts[2]))
/* 143 */       return true; 
/* 144 */     return false;
/*     */   }
/*     */   
/*     */   public Edge getCollapsedEdge() {
/* 148 */     Coordinate[] newPts = new Coordinate[2];
/* 149 */     newPts[0] = this.pts[0];
/* 150 */     newPts[1] = this.pts[1];
/* 151 */     Edge newe = new Edge(newPts, Label.toLineLabel(this.label));
/* 152 */     return newe;
/*     */   }
/*     */   
/*     */   public void setIsolated(boolean isIsolated) {
/* 157 */     this.isIsolated = isIsolated;
/*     */   }
/*     */   
/*     */   public boolean isIsolated() {
/* 161 */     return this.isIsolated;
/*     */   }
/*     */   
/*     */   public void addIntersections(LineIntersector li, int segmentIndex, int geomIndex) {
/* 170 */     for (int i = 0; i < li.getIntersectionNum(); i++)
/* 171 */       addIntersection(li, segmentIndex, geomIndex, i); 
/*     */   }
/*     */   
/*     */   public void addIntersection(LineIntersector li, int segmentIndex, int geomIndex, int intIndex) {
/* 181 */     Coordinate intPt = new Coordinate(li.getIntersection(intIndex));
/* 182 */     int normalizedSegmentIndex = segmentIndex;
/* 183 */     double dist = li.getEdgeDistance(geomIndex, intIndex);
/* 186 */     int nextSegIndex = normalizedSegmentIndex + 1;
/* 187 */     if (nextSegIndex < this.pts.length) {
/* 188 */       Coordinate nextPt = this.pts[nextSegIndex];
/* 193 */       if (intPt.equals2D(nextPt)) {
/* 195 */         normalizedSegmentIndex = nextSegIndex;
/* 196 */         dist = 0.0D;
/*     */       } 
/*     */     } 
/* 202 */     EdgeIntersection ei = this.eiList.add(intPt, normalizedSegmentIndex, dist);
/*     */   }
/*     */   
/*     */   public void computeIM(IntersectionMatrix im) {
/* 213 */     updateIM(this.label, im);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 225 */     if (!(o instanceof Edge))
/* 225 */       return false; 
/* 226 */     Edge e = (Edge)o;
/* 228 */     if (this.pts.length != e.pts.length)
/* 228 */       return false; 
/* 230 */     boolean isEqualForward = true;
/* 231 */     boolean isEqualReverse = true;
/* 232 */     int iRev = this.pts.length;
/* 233 */     for (int i = 0; i < this.pts.length; i++) {
/* 234 */       if (!this.pts[i].equals2D(e.pts[i]))
/* 235 */         isEqualForward = false; 
/* 237 */       if (!this.pts[i].equals2D(e.pts[--iRev]))
/* 238 */         isEqualReverse = false; 
/* 240 */       if (!isEqualForward && !isEqualReverse)
/* 240 */         return false; 
/*     */     } 
/* 242 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isPointwiseEqual(Edge e) {
/* 250 */     if (this.pts.length != e.pts.length)
/* 250 */       return false; 
/* 252 */     for (int i = 0; i < this.pts.length; i++) {
/* 253 */       if (!this.pts[i].equals2D(e.pts[i]))
/* 254 */         return false; 
/*     */     } 
/* 257 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 262 */     StringBuffer buf = new StringBuffer();
/* 263 */     buf.append("edge " + this.name + ": ");
/* 264 */     buf.append("LINESTRING (");
/* 265 */     for (int i = 0; i < this.pts.length; i++) {
/* 266 */       if (i > 0)
/* 266 */         buf.append(","); 
/* 267 */       buf.append((this.pts[i]).x + " " + (this.pts[i]).y);
/*     */     } 
/* 269 */     buf.append(")  " + this.label + " " + this.depthDelta);
/* 270 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 274 */     out.print("edge " + this.name + ": ");
/* 275 */     out.print("LINESTRING (");
/* 276 */     for (int i = 0; i < this.pts.length; i++) {
/* 277 */       if (i > 0)
/* 277 */         out.print(","); 
/* 278 */       out.print((this.pts[i]).x + " " + (this.pts[i]).y);
/*     */     } 
/* 280 */     out.print(")  " + this.label + " " + this.depthDelta);
/*     */   }
/*     */   
/*     */   public void printReverse(PrintStream out) {
/* 284 */     out.print("edge " + this.name + ": ");
/* 285 */     for (int i = this.pts.length - 1; i >= 0; i--)
/* 286 */       out.print(this.pts[i] + " "); 
/* 288 */     out.println("");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\Edge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */