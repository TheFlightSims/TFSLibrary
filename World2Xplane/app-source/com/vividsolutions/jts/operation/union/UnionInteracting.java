/*     */ package com.vividsolutions.jts.operation.union;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.util.GeometryCombiner;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class UnionInteracting {
/*     */   private GeometryFactory geomFactory;
/*     */   
/*     */   private Geometry g0;
/*     */   
/*     */   private Geometry g1;
/*     */   
/*     */   private boolean[] interacts0;
/*     */   
/*     */   private boolean[] interacts1;
/*     */   
/*     */   public static Geometry union(Geometry g0, Geometry g1) {
/*  55 */     UnionInteracting uue = new UnionInteracting(g0, g1);
/*  56 */     return uue.union();
/*     */   }
/*     */   
/*     */   public UnionInteracting(Geometry g0, Geometry g1) {
/*  70 */     this.g0 = g0;
/*  71 */     this.g1 = g1;
/*  72 */     this.geomFactory = g0.getFactory();
/*  73 */     this.interacts0 = new boolean[g0.getNumGeometries()];
/*  74 */     this.interacts1 = new boolean[g1.getNumGeometries()];
/*     */   }
/*     */   
/*     */   public Geometry union() {
/*  79 */     computeInteracting();
/*  83 */     Geometry int0 = extractElements(this.g0, this.interacts0, true);
/*  84 */     Geometry int1 = extractElements(this.g1, this.interacts1, true);
/*  89 */     if (int0.isEmpty() || int1.isEmpty())
/*  90 */       System.out.println("found empty!"); 
/*  98 */     Geometry union = int0.union(int1);
/* 101 */     Geometry disjoint0 = extractElements(this.g0, this.interacts0, false);
/* 102 */     Geometry disjoint1 = extractElements(this.g1, this.interacts1, false);
/* 104 */     Geometry overallUnion = GeometryCombiner.combine(union, disjoint0, disjoint1);
/* 106 */     return overallUnion;
/*     */   }
/*     */   
/*     */   private Geometry bufferUnion(Geometry g0, Geometry g1) {
/* 112 */     GeometryFactory factory = g0.getFactory();
/* 113 */     GeometryCollection geometryCollection = factory.createGeometryCollection(new Geometry[] { g0, g1 });
/* 114 */     Geometry unionAll = geometryCollection.buffer(0.0D);
/* 115 */     return unionAll;
/*     */   }
/*     */   
/*     */   private void computeInteracting() {
/* 120 */     for (int i = 0; i < this.g0.getNumGeometries(); i++) {
/* 121 */       Geometry elem = this.g0.getGeometryN(i);
/* 122 */       this.interacts0[i] = computeInteracting(elem);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean computeInteracting(Geometry elem0) {
/* 128 */     boolean interactsWithAny = false;
/* 129 */     for (int i = 0; i < this.g1.getNumGeometries(); i++) {
/* 130 */       Geometry elem1 = this.g1.getGeometryN(i);
/* 131 */       boolean interacts = elem1.getEnvelopeInternal().intersects(elem0.getEnvelopeInternal());
/* 132 */       if (interacts)
/* 132 */         this.interacts1[i] = true; 
/* 133 */       if (interacts)
/* 134 */         interactsWithAny = true; 
/*     */     } 
/* 136 */     return interactsWithAny;
/*     */   }
/*     */   
/*     */   private Geometry extractElements(Geometry geom, boolean[] interacts, boolean isInteracting) {
/* 142 */     List<Geometry> extractedGeoms = new ArrayList();
/* 143 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 144 */       Geometry elem = geom.getGeometryN(i);
/* 145 */       if (interacts[i] == isInteracting)
/* 146 */         extractedGeoms.add(elem); 
/*     */     } 
/* 148 */     return this.geomFactory.buildGeometry(extractedGeoms);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operatio\\union\UnionInteracting.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */