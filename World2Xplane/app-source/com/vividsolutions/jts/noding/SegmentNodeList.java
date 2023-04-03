/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class SegmentNodeList {
/*  47 */   private Map nodeMap = new TreeMap<>();
/*     */   
/*     */   private NodedSegmentString edge;
/*     */   
/*     */   public SegmentNodeList(NodedSegmentString edge) {
/*  52 */     this.edge = edge;
/*     */   }
/*     */   
/*     */   public NodedSegmentString getEdge() {
/*  55 */     return this.edge;
/*     */   }
/*     */   
/*     */   public SegmentNode add(Coordinate intPt, int segmentIndex) {
/*  65 */     SegmentNode eiNew = new SegmentNode(this.edge, intPt, segmentIndex, this.edge.getSegmentOctant(segmentIndex));
/*  66 */     SegmentNode ei = (SegmentNode)this.nodeMap.get(eiNew);
/*  67 */     if (ei != null) {
/*  69 */       Assert.isTrue(ei.coord.equals2D(intPt), "Found equal nodes with different coordinates");
/*  73 */       return ei;
/*     */     } 
/*  76 */     this.nodeMap.put(eiNew, eiNew);
/*  77 */     return eiNew;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  83 */     return this.nodeMap.values().iterator();
/*     */   }
/*     */   
/*     */   private void addEndpoints() {
/*  90 */     int maxSegIndex = this.edge.size() - 1;
/*  91 */     add(this.edge.getCoordinate(0), 0);
/*  92 */     add(this.edge.getCoordinate(maxSegIndex), maxSegIndex);
/*     */   }
/*     */   
/*     */   private void addCollapsedNodes() {
/* 104 */     List collapsedVertexIndexes = new ArrayList();
/* 106 */     findCollapsesFromInsertedNodes(collapsedVertexIndexes);
/* 107 */     findCollapsesFromExistingVertices(collapsedVertexIndexes);
/* 110 */     for (Iterator<Integer> it = collapsedVertexIndexes.iterator(); it.hasNext(); ) {
/* 111 */       int vertexIndex = ((Integer)it.next()).intValue();
/* 112 */       add(this.edge.getCoordinate(vertexIndex), vertexIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findCollapsesFromExistingVertices(List<Integer> collapsedVertexIndexes) {
/* 122 */     for (int i = 0; i < this.edge.size() - 2; i++) {
/* 123 */       Coordinate p0 = this.edge.getCoordinate(i);
/* 124 */       Coordinate p1 = this.edge.getCoordinate(i + 1);
/* 125 */       Coordinate p2 = this.edge.getCoordinate(i + 2);
/* 126 */       if (p0.equals2D(p2))
/* 128 */         collapsedVertexIndexes.add(new Integer(i + 1)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void findCollapsesFromInsertedNodes(List<Integer> collapsedVertexIndexes) {
/* 142 */     int[] collapsedVertexIndex = new int[1];
/* 143 */     Iterator<SegmentNode> it = iterator();
/* 145 */     SegmentNode eiPrev = it.next();
/* 146 */     while (it.hasNext()) {
/* 147 */       SegmentNode ei = it.next();
/* 148 */       boolean isCollapsed = findCollapseIndex(eiPrev, ei, collapsedVertexIndex);
/* 149 */       if (isCollapsed)
/* 150 */         collapsedVertexIndexes.add(new Integer(collapsedVertexIndex[0])); 
/* 152 */       eiPrev = ei;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean findCollapseIndex(SegmentNode ei0, SegmentNode ei1, int[] collapsedVertexIndex) {
/* 159 */     if (!ei0.coord.equals2D(ei1.coord))
/* 159 */       return false; 
/* 161 */     int numVerticesBetween = ei1.segmentIndex - ei0.segmentIndex;
/* 162 */     if (!ei1.isInterior())
/* 163 */       numVerticesBetween--; 
/* 167 */     if (numVerticesBetween == 1) {
/* 168 */       collapsedVertexIndex[0] = ei0.segmentIndex + 1;
/* 169 */       return true;
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public void addSplitEdges(Collection<SegmentString> edgeList) {
/* 185 */     addEndpoints();
/* 186 */     addCollapsedNodes();
/* 188 */     Iterator<SegmentNode> it = iterator();
/* 190 */     SegmentNode eiPrev = it.next();
/* 191 */     while (it.hasNext()) {
/* 192 */       SegmentNode ei = it.next();
/* 193 */       SegmentString newEdge = createSplitEdge(eiPrev, ei);
/* 198 */       edgeList.add(newEdge);
/* 199 */       eiPrev = ei;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkSplitEdgesCorrectness(List<SegmentString> splitEdges) {
/* 211 */     Coordinate[] edgePts = this.edge.getCoordinates();
/* 214 */     SegmentString split0 = splitEdges.get(0);
/* 215 */     Coordinate pt0 = split0.getCoordinate(0);
/* 216 */     if (!pt0.equals2D(edgePts[0]))
/* 217 */       throw new RuntimeException("bad split edge start point at " + pt0); 
/* 219 */     SegmentString splitn = splitEdges.get(splitEdges.size() - 1);
/* 220 */     Coordinate[] splitnPts = splitn.getCoordinates();
/* 221 */     Coordinate ptn = splitnPts[splitnPts.length - 1];
/* 222 */     if (!ptn.equals2D(edgePts[edgePts.length - 1]))
/* 223 */       throw new RuntimeException("bad split edge end point at " + ptn); 
/*     */   }
/*     */   
/*     */   SegmentString createSplitEdge(SegmentNode ei0, SegmentNode ei1) {
/* 235 */     int npts = ei1.segmentIndex - ei0.segmentIndex + 2;
/* 237 */     Coordinate lastSegStartPt = this.edge.getCoordinate(ei1.segmentIndex);
/* 242 */     boolean useIntPt1 = (ei1.isInterior() || !ei1.coord.equals2D(lastSegStartPt));
/* 243 */     if (!useIntPt1)
/* 244 */       npts--; 
/* 247 */     Coordinate[] pts = new Coordinate[npts];
/* 248 */     int ipt = 0;
/* 249 */     pts[ipt++] = new Coordinate(ei0.coord);
/* 250 */     for (int i = ei0.segmentIndex + 1; i <= ei1.segmentIndex; i++)
/* 251 */       pts[ipt++] = this.edge.getCoordinate(i); 
/* 253 */     if (useIntPt1)
/* 253 */       pts[ipt] = ei1.coord; 
/* 255 */     return new NodedSegmentString(pts, this.edge.getData());
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 260 */     out.println("Intersections:");
/* 261 */     for (Iterator<SegmentNode> it = iterator(); it.hasNext(); ) {
/* 262 */       SegmentNode ei = it.next();
/* 263 */       ei.print(out);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentNodeList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */