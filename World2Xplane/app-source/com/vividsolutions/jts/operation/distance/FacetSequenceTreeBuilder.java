/*     */ package com.vividsolutions.jts.operation.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FacetSequenceTreeBuilder {
/*     */   private static final int FACET_SEQUENCE_SIZE = 6;
/*     */   
/*     */   private static final int STR_TREE_NODE_CAPACITY = 4;
/*     */   
/*     */   public static STRtree build(Geometry g) {
/*  56 */     STRtree tree = new STRtree(4);
/*  57 */     List sections = computeFacetSequences(g);
/*  58 */     for (Iterator<FacetSequence> i = sections.iterator(); i.hasNext(); ) {
/*  59 */       FacetSequence section = i.next();
/*  60 */       tree.insert(section.getEnvelope(), section);
/*     */     } 
/*  62 */     tree.build();
/*  63 */     return tree;
/*     */   }
/*     */   
/*     */   private static List computeFacetSequences(Geometry g) {
/*  73 */     final List sections = new ArrayList();
/*  75 */     g.apply(new GeometryComponentFilter() {
/*     */           public void filter(Geometry geom) {
/*  78 */             CoordinateSequence seq = null;
/*  79 */             if (geom instanceof LineString) {
/*  80 */               seq = ((LineString)geom).getCoordinateSequence();
/*  81 */               FacetSequenceTreeBuilder.addFacetSequences(seq, sections);
/*  83 */             } else if (geom instanceof Point) {
/*  84 */               seq = ((Point)geom).getCoordinateSequence();
/*  85 */               FacetSequenceTreeBuilder.addFacetSequences(seq, sections);
/*     */             } 
/*     */           }
/*     */         });
/*  89 */     return sections;
/*     */   }
/*     */   
/*     */   private static void addFacetSequences(CoordinateSequence pts, List<FacetSequence> sections) {
/*  93 */     int i = 0;
/*  94 */     int size = pts.size();
/*  95 */     while (i <= size - 1) {
/*  96 */       int end = i + 6 + 1;
/*  99 */       if (end >= size - 1)
/* 100 */         end = size; 
/* 101 */       FacetSequence sect = new FacetSequence(pts, i, end);
/* 102 */       sections.add(sect);
/* 103 */       i += 6;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\FacetSequenceTreeBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */