/*     */ package org.opengis.filter.capability;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public final class GeometryOperand implements Name, Serializable {
/*     */   private static final long serialVersionUID = -9006169053542932716L;
/*     */   
/*  64 */   private static final Map<GeometryOperand, GeometryOperand> POOL = new HashMap<GeometryOperand, GeometryOperand>();
/*     */   
/*  68 */   public static final GeometryOperand Envelope = new GeometryOperand("Envelope");
/*     */   
/*  71 */   public static final GeometryOperand Point = new GeometryOperand("Point");
/*     */   
/*  74 */   public static final GeometryOperand LineString = new GeometryOperand("LineString");
/*     */   
/*  77 */   public static final GeometryOperand Polygon = new GeometryOperand("Polygon");
/*     */   
/*  80 */   public static final GeometryOperand ArcByCenterPoint = new GeometryOperand("ArcByCenterPoint");
/*     */   
/*  83 */   public static final GeometryOperand CircleByCenterPoint = new GeometryOperand("CircleByCenterPoint");
/*     */   
/*  86 */   public static final GeometryOperand Arc = new GeometryOperand("Arc");
/*     */   
/*  89 */   public static final GeometryOperand Circle = new GeometryOperand("Circle");
/*     */   
/*  92 */   public static final GeometryOperand ArcByBulge = new GeometryOperand("ArcByBulge");
/*     */   
/*  95 */   public static final GeometryOperand Bezier = new GeometryOperand("Bezier");
/*     */   
/*  98 */   public static final GeometryOperand Clothoid = new GeometryOperand("Clothoid");
/*     */   
/* 101 */   public static final GeometryOperand CubicSpline = new GeometryOperand("CubicSpline");
/*     */   
/* 104 */   public static final GeometryOperand Geodesic = new GeometryOperand("Geodesic");
/*     */   
/* 107 */   public static final GeometryOperand OffsetCurve = new GeometryOperand("OffsetCurve");
/*     */   
/* 110 */   public static final GeometryOperand Triangle = new GeometryOperand("Triangle");
/*     */   
/* 113 */   public static final GeometryOperand PolyhedralSurface = new GeometryOperand("PolyhedralSurface");
/*     */   
/* 116 */   public static final GeometryOperand TriangulatedSurface = new GeometryOperand("TriangulatedSurface");
/*     */   
/* 119 */   public static final GeometryOperand Tin = new GeometryOperand("Tin");
/*     */   
/* 122 */   public static final GeometryOperand Solid = new GeometryOperand("Solid");
/*     */   
/*     */   private final String namespaceURI;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private GeometryOperand(String name) {
/* 138 */     this("http://www.opengis.net/gml", name);
/*     */   }
/*     */   
/*     */   private GeometryOperand(String namespaceURI, String name) {
/* 145 */     this.namespaceURI = namespaceURI;
/* 146 */     this.name = name;
/* 147 */     POOL.put(this, this);
/*     */   }
/*     */   
/*     */   public static GeometryOperand get(String namespaceURI, String name) {
/* 158 */     if (namespaceURI == null || namespaceURI.trim().length() == 0)
/* 159 */       namespaceURI = "http://www.opengis.net/gml"; 
/* 161 */     name = name.trim();
/* 162 */     return POOL.get(new GeometryOperand(namespaceURI, name));
/*     */   }
/*     */   
/*     */   public String getLocalPart() {
/* 169 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 176 */     return this.namespaceURI;
/*     */   }
/*     */   
/*     */   public String getURI() {
/* 183 */     return this.namespaceURI + '/' + this.name;
/*     */   }
/*     */   
/*     */   public boolean isGlobal() {
/* 190 */     return false;
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/* 194 */     return "#";
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 202 */     return this.namespaceURI.hashCode() + 37 * this.name.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 210 */     if (other != null && other instanceof Name) {
/* 211 */       Name that = (Name)other;
/* 212 */       return (this.namespaceURI.equals(that.getNamespaceURI()) && this.name.equals(that.getLocalPart()));
/*     */     } 
/* 214 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 222 */     return getURI();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 229 */     GeometryOperand unique = POOL.get(this);
/* 230 */     return (unique != null) ? unique : this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\GeometryOperand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */