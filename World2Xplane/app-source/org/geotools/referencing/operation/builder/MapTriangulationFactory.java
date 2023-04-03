/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ 
/*     */ class MapTriangulationFactory {
/*     */   private final Quadrilateral quad;
/*     */   
/*     */   private final List<MappedPosition> vectors;
/*     */   
/*     */   public MapTriangulationFactory(Quadrilateral quad, List<MappedPosition> vectors) throws TriangulationException {
/*  49 */     this.quad = quad;
/*  50 */     this.vectors = vectors;
/*     */   }
/*     */   
/*     */   public Map getTriangleMap() throws TriangulationException {
/*  62 */     Quadrilateral mQuad = mappedQuad(this.quad, this.vectors);
/*  64 */     ExtendedPosition[] vertices = new ExtendedPosition[this.vectors.size()];
/*  67 */     for (int i = 0; i < this.vectors.size(); i++)
/*  68 */       vertices[i] = new ExtendedPosition(((MappedPosition)this.vectors.get(i)).getSource(), ((MappedPosition)this.vectors.get(i)).getTarget()); 
/*  72 */     TriangulationFactory triangulator = new TriangulationFactory(mQuad, (DirectPosition[])vertices);
/*  74 */     List<TINTriangle> taggedSourceTriangles = triangulator.getTriangulation();
/*  75 */     HashMap<Object, Object> triangleMap = new HashMap<Object, Object>();
/*  77 */     for (Iterator<TINTriangle> iterator = taggedSourceTriangles.iterator(); iterator.hasNext(); ) {
/*  78 */       TINTriangle sourceTriangle = iterator.next();
/*  79 */       triangleMap.put(sourceTriangle, new TINTriangle(((ExtendedPosition)sourceTriangle.p0).getMappedposition(), ((ExtendedPosition)sourceTriangle.p1).getMappedposition(), ((ExtendedPosition)sourceTriangle.p2).getMappedposition()));
/*     */     } 
/*  86 */     return triangleMap;
/*     */   }
/*     */   
/*     */   private Quadrilateral mappedQuad(Quadrilateral sourceQuad, List<MappedPosition> vectors) {
/* 100 */     if (vectors.isEmpty())
/* 101 */       return (Quadrilateral)sourceQuad.clone(); 
/* 105 */     MappedPosition[] mappedVertices = new MappedPosition[4];
/* 107 */     for (int i = 0; i < mappedVertices.length; i++)
/* 108 */       mappedVertices[i] = generateCoordFromNearestOne(sourceQuad.getPoints()[i], vectors); 
/* 112 */     return new Quadrilateral((DirectPosition)new ExtendedPosition(mappedVertices[0].getSource(), mappedVertices[0].getTarget()), (DirectPosition)new ExtendedPosition(mappedVertices[1].getSource(), mappedVertices[1].getTarget()), (DirectPosition)new ExtendedPosition(mappedVertices[2].getSource(), mappedVertices[2].getTarget()), (DirectPosition)new ExtendedPosition(mappedVertices[3].getSource(), mappedVertices[3].getTarget()));
/*     */   }
/*     */   
/*     */   protected MappedPosition generateCoordFromNearestOne(DirectPosition x, List<MappedPosition> vertices) {
/* 136 */     MappedPosition nearestOne = nearestMappedCoordinate(x, vertices);
/* 138 */     double dstX = x.getCoordinate()[0] + nearestOne.getTarget().getCoordinate()[0] - nearestOne.getSource().getCoordinate()[0];
/* 141 */     double dstY = x.getCoordinate()[1] + nearestOne.getTarget().getCoordinate()[1] - nearestOne.getSource().getCoordinate()[1];
/* 144 */     DirectPosition2D directPosition2D = new DirectPosition2D(nearestOne.getTarget().getCoordinateReferenceSystem(), dstX, dstY);
/* 148 */     return new MappedPosition(x, (DirectPosition)directPosition2D);
/*     */   }
/*     */   
/*     */   protected MappedPosition nearestMappedCoordinate(DirectPosition dp, List<MappedPosition> vertices) {
/* 161 */     DirectPosition2D x = new DirectPosition2D(dp);
/* 164 */     MappedPosition nearestOne = vertices.get(0);
/* 166 */     for (Iterator<MappedPosition> i = vertices.iterator(); i.hasNext(); ) {
/* 167 */       MappedPosition candidate = i.next();
/* 169 */       if (((DirectPosition2D)candidate.getSource()).distance(x.toPoint2D()) < ((DirectPosition2D)nearestOne.getSource()).distance(x.toPoint2D()))
/* 172 */         nearestOne = candidate; 
/*     */     } 
/* 176 */     return nearestOne;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\MapTriangulationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */