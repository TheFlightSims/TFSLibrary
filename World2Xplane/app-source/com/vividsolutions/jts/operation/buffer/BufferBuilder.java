/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeList;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import com.vividsolutions.jts.geomgraph.NodeFactory;
/*     */ import com.vividsolutions.jts.geomgraph.PlanarGraph;
/*     */ import com.vividsolutions.jts.noding.IntersectionAdder;
/*     */ import com.vividsolutions.jts.noding.MCIndexNoder;
/*     */ import com.vividsolutions.jts.noding.Noder;
/*     */ import com.vividsolutions.jts.noding.SegmentIntersector;
/*     */ import com.vividsolutions.jts.noding.SegmentString;
/*     */ import com.vividsolutions.jts.operation.overlay.OverlayNodeFactory;
/*     */ import com.vividsolutions.jts.operation.overlay.PolygonBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class BufferBuilder {
/*     */   private BufferParameters bufParams;
/*     */   
/*     */   private PrecisionModel workingPrecisionModel;
/*     */   
/*     */   private Noder workingNoder;
/*     */   
/*     */   private GeometryFactory geomFact;
/*     */   
/*     */   private PlanarGraph graph;
/*     */   
/*     */   private static int depthDelta(Label label) {
/*  72 */     int lLoc = label.getLocation(0, 1);
/*  73 */     int rLoc = label.getLocation(0, 2);
/*  74 */     if (lLoc == 0 && rLoc == 2)
/*  75 */       return 1; 
/*  76 */     if (lLoc == 2 && rLoc == 0)
/*  77 */       return -1; 
/*  78 */     return 0;
/*     */   }
/*     */   
/*  87 */   private EdgeList edgeList = new EdgeList();
/*     */   
/*     */   public BufferBuilder(BufferParameters bufParams) {
/*  94 */     this.bufParams = bufParams;
/*     */   }
/*     */   
/*     */   public void setWorkingPrecisionModel(PrecisionModel pm) {
/* 107 */     this.workingPrecisionModel = pm;
/*     */   }
/*     */   
/*     */   public void setNoder(Noder noder) {
/* 117 */     this.workingNoder = noder;
/*     */   }
/*     */   
/*     */   public Geometry buffer(Geometry g, double distance) {
/* 122 */     PrecisionModel precisionModel = this.workingPrecisionModel;
/* 123 */     if (precisionModel == null)
/* 124 */       precisionModel = g.getPrecisionModel(); 
/* 127 */     this.geomFact = g.getFactory();
/* 129 */     OffsetCurveBuilder curveBuilder = new OffsetCurveBuilder(precisionModel, this.bufParams);
/* 131 */     OffsetCurveSetBuilder curveSetBuilder = new OffsetCurveSetBuilder(g, distance, curveBuilder);
/* 133 */     List bufferSegStrList = curveSetBuilder.getCurves();
/* 136 */     if (bufferSegStrList.size() <= 0)
/* 137 */       return createEmptyResultGeometry(); 
/* 150 */     computeNodedEdges(bufferSegStrList, precisionModel);
/* 151 */     this.graph = new PlanarGraph((NodeFactory)new OverlayNodeFactory());
/* 152 */     this.graph.addEdges(this.edgeList.getEdges());
/* 154 */     List subgraphList = createSubgraphs(this.graph);
/* 155 */     PolygonBuilder polyBuilder = new PolygonBuilder(this.geomFact);
/* 156 */     buildSubgraphs(subgraphList, polyBuilder);
/* 157 */     List resultPolyList = polyBuilder.getPolygons();
/* 160 */     if (resultPolyList.size() <= 0)
/* 161 */       return createEmptyResultGeometry(); 
/* 164 */     Geometry resultGeom = this.geomFact.buildGeometry(resultPolyList);
/* 165 */     return resultGeom;
/*     */   }
/*     */   
/*     */   private Noder getNoder(PrecisionModel precisionModel) {
/* 170 */     if (this.workingNoder != null)
/* 170 */       return this.workingNoder; 
/* 173 */     MCIndexNoder noder = new MCIndexNoder();
/* 174 */     RobustLineIntersector robustLineIntersector = new RobustLineIntersector();
/* 175 */     robustLineIntersector.setPrecisionModel(precisionModel);
/* 176 */     noder.setSegmentIntersector((SegmentIntersector)new IntersectionAdder((LineIntersector)robustLineIntersector));
/* 178 */     return (Noder)noder;
/*     */   }
/*     */   
/*     */   private void computeNodedEdges(List bufferSegStrList, PrecisionModel precisionModel) {
/* 187 */     Noder noder = getNoder(precisionModel);
/* 188 */     noder.computeNodes(bufferSegStrList);
/* 189 */     Collection nodedSegStrings = noder.getNodedSubstrings();
/* 193 */     for (Iterator<SegmentString> i = nodedSegStrings.iterator(); i.hasNext(); ) {
/* 194 */       SegmentString segStr = i.next();
/* 200 */       Coordinate[] pts = segStr.getCoordinates();
/* 201 */       if (pts.length == 2 && pts[0].equals2D(pts[1]))
/*     */         continue; 
/* 204 */       Label oldLabel = (Label)segStr.getData();
/* 205 */       Edge edge = new Edge(segStr.getCoordinates(), new Label(oldLabel));
/* 206 */       insertUniqueEdge(edge);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void insertUniqueEdge(Edge e) {
/* 221 */     Edge existingEdge = this.edgeList.findEqualEdge(e);
/* 224 */     if (existingEdge != null) {
/* 225 */       Label existingLabel = existingEdge.getLabel();
/* 227 */       Label labelToMerge = e.getLabel();
/* 230 */       if (!existingEdge.isPointwiseEqual(e)) {
/* 231 */         labelToMerge = new Label(e.getLabel());
/* 232 */         labelToMerge.flip();
/*     */       } 
/* 234 */       existingLabel.merge(labelToMerge);
/* 237 */       int mergeDelta = depthDelta(labelToMerge);
/* 238 */       int existingDelta = existingEdge.getDepthDelta();
/* 239 */       int newDelta = existingDelta + mergeDelta;
/* 240 */       existingEdge.setDepthDelta(newDelta);
/*     */     } else {
/* 245 */       this.edgeList.add(e);
/* 246 */       e.setDepthDelta(depthDelta(e.getLabel()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private List createSubgraphs(PlanarGraph graph) {
/* 252 */     List<BufferSubgraph> subgraphList = new ArrayList();
/* 253 */     for (Iterator<Node> i = graph.getNodes().iterator(); i.hasNext(); ) {
/* 254 */       Node node = i.next();
/* 255 */       if (!node.isVisited()) {
/* 256 */         BufferSubgraph subgraph = new BufferSubgraph();
/* 257 */         subgraph.create(node);
/* 258 */         subgraphList.add(subgraph);
/*     */       } 
/*     */     } 
/* 267 */     Collections.sort(subgraphList, Collections.reverseOrder());
/* 268 */     return subgraphList;
/*     */   }
/*     */   
/*     */   private void buildSubgraphs(List subgraphList, PolygonBuilder polyBuilder) {
/* 281 */     List<BufferSubgraph> processedGraphs = new ArrayList();
/* 282 */     for (Iterator<BufferSubgraph> i = subgraphList.iterator(); i.hasNext(); ) {
/* 283 */       BufferSubgraph subgraph = i.next();
/* 284 */       Coordinate p = subgraph.getRightmostCoordinate();
/* 288 */       SubgraphDepthLocater locater = new SubgraphDepthLocater(processedGraphs);
/* 289 */       int outsideDepth = locater.getDepth(p);
/* 291 */       subgraph.computeDepth(outsideDepth);
/* 298 */       subgraph.findResultEdges();
/* 299 */       processedGraphs.add(subgraph);
/* 300 */       polyBuilder.add(subgraph.getDirectedEdges(), subgraph.getNodes());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Geometry convertSegStrings(Iterator<SegmentString> it) {
/* 306 */     GeometryFactory fact = new GeometryFactory();
/* 307 */     List<LineString> lines = new ArrayList();
/* 308 */     while (it.hasNext()) {
/* 309 */       SegmentString ss = it.next();
/* 310 */       LineString line = fact.createLineString(ss.getCoordinates());
/* 311 */       lines.add(line);
/*     */     } 
/* 313 */     return fact.buildGeometry(lines);
/*     */   }
/*     */   
/*     */   private Geometry createEmptyResultGeometry() {
/* 325 */     return (Geometry)this.geomFact.createPolygon(null, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\BufferBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */