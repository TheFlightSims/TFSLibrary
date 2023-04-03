/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.AnchorPoint;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ import org.opengis.style.PointPlacement;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class PointPlacementImpl implements PointPlacement, Cloneable {
/*  41 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   private final FilterFactory filterFactory;
/*     */   
/*  45 */   private AnchorPointImpl anchorPoint = new AnchorPointImpl();
/*     */   
/*  46 */   private DisplacementImpl displacement = new DisplacementImpl();
/*     */   
/*  47 */   private Expression rotation = null;
/*     */   
/*     */   public PointPlacementImpl() {
/*  50 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public PointPlacementImpl(FilterFactory factory) {
/*  54 */     this.filterFactory = factory;
/*     */     try {
/*  56 */       this.rotation = (Expression)this.filterFactory.literal(new Integer(0));
/*  57 */     } catch (IllegalFilterException ife) {
/*  58 */       LOGGER.severe("Failed to build defaultPointPlacement: " + ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   public AnchorPointImpl getAnchorPoint() {
/*  70 */     return this.anchorPoint;
/*     */   }
/*     */   
/*     */   public void setAnchorPoint(AnchorPoint anchorPoint) {
/*  79 */     if (this.anchorPoint == anchorPoint)
/*     */       return; 
/*  82 */     this.anchorPoint = AnchorPointImpl.cast(anchorPoint);
/*     */   }
/*     */   
/*     */   public Displacement getDisplacement() {
/*  92 */     return this.displacement;
/*     */   }
/*     */   
/*     */   public void setDisplacement(Displacement displacement) {
/* 101 */     if (this.displacement == displacement)
/*     */       return; 
/* 104 */     this.displacement = DisplacementImpl.cast(displacement);
/*     */   }
/*     */   
/*     */   public Expression getRotation() {
/* 113 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public void setRotation(Expression rotation) {
/* 122 */     this.rotation = rotation;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 126 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 130 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 138 */       PointPlacementImpl clone = (PointPlacementImpl)super.clone();
/* 139 */       clone.anchorPoint = (AnchorPointImpl)this.anchorPoint.clone();
/* 140 */       clone.displacement = (DisplacementImpl)this.displacement.clone();
/* 143 */       return clone;
/* 144 */     } catch (CloneNotSupportedException e) {
/* 145 */       throw new RuntimeException("Won't happen");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 153 */     if (this == obj)
/* 154 */       return true; 
/* 157 */     if (obj instanceof PointPlacementImpl) {
/* 158 */       PointPlacementImpl other = (PointPlacementImpl)obj;
/* 160 */       return (Utilities.equals(this.anchorPoint, other.anchorPoint) && Utilities.equals(this.displacement, other.displacement) && Utilities.equals(this.rotation, other.rotation));
/*     */     } 
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 172 */     int PRIME = 37;
/* 173 */     int result = 17;
/* 175 */     if (this.anchorPoint != null)
/* 176 */       result = result * 37 + this.anchorPoint.hashCode(); 
/* 179 */     if (this.displacement != null)
/* 180 */       result = result * 37 + this.displacement.hashCode(); 
/* 183 */     if (this.rotation != null)
/* 184 */       result = result * 37 + this.rotation.hashCode(); 
/* 187 */     return result;
/*     */   }
/*     */   
/*     */   static PointPlacementImpl cast(LabelPlacement placement) {
/* 191 */     if (placement == null)
/* 192 */       return null; 
/* 194 */     if (placement instanceof PointPlacementImpl)
/* 195 */       return (PointPlacementImpl)placement; 
/* 197 */     if (placement instanceof PointPlacement) {
/* 198 */       PointPlacement pointPlacement = (PointPlacement)placement;
/* 199 */       PointPlacementImpl copy = new PointPlacementImpl();
/* 200 */       copy.setAnchorPoint(AnchorPointImpl.cast(pointPlacement.getAnchorPoint()));
/* 201 */       copy.setDisplacement(DisplacementImpl.cast(pointPlacement.getDisplacement()));
/* 202 */       return copy;
/*     */     } 
/* 204 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\PointPlacementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */