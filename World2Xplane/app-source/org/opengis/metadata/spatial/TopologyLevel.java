/*     */ package org.opengis.metadata.spatial;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_TopologyLevelCode", specification = Specification.ISO_19115)
/*     */ public final class TopologyLevel extends CodeList<TopologyLevel> {
/*     */   private static final long serialVersionUID = -179324311133793389L;
/*     */   
/*  43 */   private static final List<TopologyLevel> VALUES = new ArrayList<TopologyLevel>(9);
/*     */   
/*     */   @UML(identifier = "geometryOnly", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final TopologyLevel GEOMETRY_ONLY = new TopologyLevel("GEOMETRY_ONLY");
/*     */   
/*     */   @UML(identifier = "topology1D", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final TopologyLevel TOPOLOGY_1D = new TopologyLevel("TOPOLOGY_1D");
/*     */   
/*     */   @UML(identifier = "planarGraph", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final TopologyLevel PLANAR_GRAPH = new TopologyLevel("PLANAR_GRAPH");
/*     */   
/*     */   @UML(identifier = "fullPlanarGraph", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final TopologyLevel FULL_PLANAR_GRAPH = new TopologyLevel("FULL_PLANAR_GRAPH");
/*     */   
/*     */   @UML(identifier = "surfaceGraph", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final TopologyLevel SURFACE_GRAPH = new TopologyLevel("SURFACE_GRAPH");
/*     */   
/*     */   @UML(identifier = "fullSurfaceGraph", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final TopologyLevel FULL_SURFACE_GRAPH = new TopologyLevel("FULL_SURFACE_GRAPH");
/*     */   
/*     */   @UML(identifier = "topology3D", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  85 */   public static final TopologyLevel TOPOLOGY_3D = new TopologyLevel("TOPOLOGY_3D");
/*     */   
/*     */   @UML(identifier = "fullTopology3D", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  91 */   public static final TopologyLevel FULL_TOPOLOGY_3D = new TopologyLevel("FULL_TOPOLOGY_3D");
/*     */   
/*     */   @UML(identifier = "abstract", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  97 */   public static final TopologyLevel ABSTRACT = new TopologyLevel("ABSTRACT");
/*     */   
/*     */   private TopologyLevel(String name) {
/* 106 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static TopologyLevel[] values() {
/* 115 */     synchronized (VALUES) {
/* 116 */       return VALUES.<TopologyLevel>toArray(new TopologyLevel[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TopologyLevel[] family() {
/* 124 */     return values();
/*     */   }
/*     */   
/*     */   public static TopologyLevel valueOf(String code) {
/* 135 */     return (TopologyLevel)valueOf(TopologyLevel.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\TopologyLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */