/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PlanarGraph {
/*     */   public static void linkResultDirectedEdges(Collection nodes) {
/*  72 */     for (Iterator<Node> nodeit = nodes.iterator(); nodeit.hasNext(); ) {
/*  73 */       Node node = nodeit.next();
/*  74 */       ((DirectedEdgeStar)node.getEdges()).linkResultDirectedEdges();
/*     */     } 
/*     */   }
/*     */   
/*  78 */   protected List edges = new ArrayList();
/*     */   
/*     */   protected NodeMap nodes;
/*     */   
/*  80 */   protected List edgeEndList = new ArrayList();
/*     */   
/*     */   public PlanarGraph(NodeFactory nodeFact) {
/*  83 */     this.nodes = new NodeMap(nodeFact);
/*     */   }
/*     */   
/*     */   public PlanarGraph() {
/*  87 */     this.nodes = new NodeMap(new NodeFactory());
/*     */   }
/*     */   
/*     */   public Iterator getEdgeIterator() {
/*  90 */     return this.edges.iterator();
/*     */   }
/*     */   
/*     */   public Collection getEdgeEnds() {
/*  91 */     return this.edgeEndList;
/*     */   }
/*     */   
/*     */   public boolean isBoundaryNode(int geomIndex, Coordinate coord) {
/*  95 */     Node node = this.nodes.find(coord);
/*  96 */     if (node == null)
/*  96 */       return false; 
/*  97 */     Label label = node.getLabel();
/*  98 */     if (label != null && label.getLocation(geomIndex) == 1)
/*  98 */       return true; 
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   protected void insertEdge(Edge e) {
/* 103 */     this.edges.add(e);
/*     */   }
/*     */   
/*     */   public void add(EdgeEnd e) {
/* 107 */     this.nodes.add(e);
/* 108 */     this.edgeEndList.add(e);
/*     */   }
/*     */   
/*     */   public Iterator getNodeIterator() {
/* 111 */     return this.nodes.iterator();
/*     */   }
/*     */   
/*     */   public Collection getNodes() {
/* 112 */     return this.nodes.values();
/*     */   }
/*     */   
/*     */   public Node addNode(Node node) {
/* 113 */     return this.nodes.addNode(node);
/*     */   }
/*     */   
/*     */   public Node addNode(Coordinate coord) {
/* 114 */     return this.nodes.addNode(coord);
/*     */   }
/*     */   
/*     */   public Node find(Coordinate coord) {
/* 118 */     return this.nodes.find(coord);
/*     */   }
/*     */   
/*     */   public void addEdges(List edgesToAdd) {
/* 127 */     for (Iterator<Edge> it = edgesToAdd.iterator(); it.hasNext(); ) {
/* 128 */       Edge e = it.next();
/* 129 */       this.edges.add(e);
/* 131 */       DirectedEdge de1 = new DirectedEdge(e, true);
/* 132 */       DirectedEdge de2 = new DirectedEdge(e, false);
/* 133 */       de1.setSym(de2);
/* 134 */       de2.setSym(de1);
/* 136 */       add(de1);
/* 137 */       add(de2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void linkResultDirectedEdges() {
/* 148 */     for (Iterator<Node> nodeit = this.nodes.iterator(); nodeit.hasNext(); ) {
/* 149 */       Node node = nodeit.next();
/* 150 */       ((DirectedEdgeStar)node.getEdges()).linkResultDirectedEdges();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void linkAllDirectedEdges() {
/* 160 */     for (Iterator<Node> nodeit = this.nodes.iterator(); nodeit.hasNext(); ) {
/* 161 */       Node node = nodeit.next();
/* 162 */       ((DirectedEdgeStar)node.getEdges()).linkAllDirectedEdges();
/*     */     } 
/*     */   }
/*     */   
/*     */   public EdgeEnd findEdgeEnd(Edge e) {
/* 174 */     for (Iterator<EdgeEnd> i = getEdgeEnds().iterator(); i.hasNext(); ) {
/* 175 */       EdgeEnd ee = i.next();
/* 176 */       if (ee.getEdge() == e)
/* 177 */         return ee; 
/*     */     } 
/* 179 */     return null;
/*     */   }
/*     */   
/*     */   public Edge findEdge(Coordinate p0, Coordinate p1) {
/* 190 */     for (int i = 0; i < this.edges.size(); i++) {
/* 191 */       Edge e = this.edges.get(i);
/* 192 */       Coordinate[] eCoord = e.getCoordinates();
/* 193 */       if (p0.equals(eCoord[0]) && p1.equals(eCoord[1]))
/* 194 */         return e; 
/*     */     } 
/* 196 */     return null;
/*     */   }
/*     */   
/*     */   public Edge findEdgeInSameDirection(Coordinate p0, Coordinate p1) {
/* 207 */     for (int i = 0; i < this.edges.size(); i++) {
/* 208 */       Edge e = this.edges.get(i);
/* 210 */       Coordinate[] eCoord = e.getCoordinates();
/* 211 */       if (matchInSameDirection(p0, p1, eCoord[0], eCoord[1]))
/* 212 */         return e; 
/* 214 */       if (matchInSameDirection(p0, p1, eCoord[eCoord.length - 1], eCoord[eCoord.length - 2]))
/* 215 */         return e; 
/*     */     } 
/* 217 */     return null;
/*     */   }
/*     */   
/*     */   private boolean matchInSameDirection(Coordinate p0, Coordinate p1, Coordinate ep0, Coordinate ep1) {
/* 227 */     if (!p0.equals(ep0))
/* 228 */       return false; 
/* 230 */     if (CGAlgorithms.computeOrientation(p0, p1, ep1) == 0 && Quadrant.quadrant(p0, p1) == Quadrant.quadrant(ep0, ep1))
/* 232 */       return true; 
/* 233 */     return false;
/*     */   }
/*     */   
/*     */   public void printEdges(PrintStream out) {
/* 238 */     out.println("Edges:");
/* 239 */     for (int i = 0; i < this.edges.size(); i++) {
/* 240 */       out.println("edge " + i + ":");
/* 241 */       Edge e = this.edges.get(i);
/* 242 */       e.print(out);
/* 243 */       e.eiList.print(out);
/*     */     } 
/*     */   }
/*     */   
/*     */   void debugPrint(Object o) {
/* 248 */     System.out.print(o);
/*     */   }
/*     */   
/*     */   void debugPrintln(Object o) {
/* 252 */     System.out.println(o);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\PlanarGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */