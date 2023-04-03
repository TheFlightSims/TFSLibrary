/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.filter.AttributeExpression;
/*     */ import org.geotools.filter.BetweenFilter;
/*     */ import org.geotools.filter.CompareFilter;
/*     */ import org.geotools.filter.Expression;
/*     */ import org.geotools.filter.FidFilter;
/*     */ import org.geotools.filter.Filter;
/*     */ import org.geotools.filter.FilterVisitor;
/*     */ import org.geotools.filter.FilterVisitor2;
/*     */ import org.geotools.filter.FunctionExpression;
/*     */ import org.geotools.filter.GeometryFilter;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.filter.LikeFilter;
/*     */ import org.geotools.filter.LiteralExpression;
/*     */ import org.geotools.filter.LogicFilter;
/*     */ import org.geotools.filter.MathExpression;
/*     */ import org.geotools.filter.NullFilter;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ 
/*     */ public class WFSBBoxFilterVisitor implements FilterVisitor2 {
/*  57 */   private static final Logger logger = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   Envelope maxbbox;
/*     */   
/*     */   public WFSBBoxFilterVisitor() {
/*  60 */     this(null);
/*     */   }
/*     */   
/*     */   public WFSBBoxFilterVisitor(Envelope fsd) {
/*  63 */     this.maxbbox = fsd;
/*     */   }
/*     */   
/*     */   public void visit(Filter filter) {
/*  66 */     if (Filter.NONE == filter)
/*     */       return; 
/*  69 */     switch (filter.getFilterType()) {
/*     */       case 19:
/*  71 */         visit((BetweenFilter)filter);
/*     */         break;
/*     */       case 14:
/*     */       case 15:
/*     */       case 16:
/*     */       case 17:
/*     */       case 18:
/*     */       case 23:
/*  81 */         visit((BetweenFilter)filter);
/*     */         break;
/*     */       case 22:
/*  86 */         visit((BetweenFilter)filter);
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 24:
/* 101 */         visit((GeometryFilter)filter);
/*     */         break;
/*     */       case 20:
/* 106 */         visit((LikeFilter)filter);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 113 */         visit((LogicFilter)filter);
/*     */         break;
/*     */       case 21:
/* 118 */         visit((NullFilter)filter);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(BetweenFilter filter) {
/* 129 */     if (filter != null) {
/* 130 */       if (filter.getLeftValue() != null)
/* 131 */         filter.getLeftValue().accept((FilterVisitor)this); 
/* 132 */       if (filter.getRightValue() != null)
/* 133 */         filter.getRightValue().accept((FilterVisitor)this); 
/* 134 */       if (filter.getMiddleValue() != null)
/* 135 */         filter.getMiddleValue().accept((FilterVisitor)this); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(CompareFilter filter) {
/* 142 */     if (filter != null) {
/* 143 */       if (filter.getLeftValue() != null)
/* 144 */         filter.getLeftValue().accept((FilterVisitor)this); 
/* 145 */       if (filter.getRightValue() != null)
/* 146 */         filter.getRightValue().accept((FilterVisitor)this); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(GeometryFilter filter) {
/* 153 */     if (filter != null) {
/*     */       Envelope bbox;
/*     */       LiteralExpression le;
/* 154 */       Expression leftGeometry = filter.getLeftGeometry();
/* 155 */       Expression rightGeometry = filter.getRightGeometry();
/* 156 */       switch (filter.getFilterType()) {
/*     */         case 4:
/* 160 */           bbox = null;
/* 161 */           le = null;
/* 162 */           if (leftGeometry != null && leftGeometry.getType() == 104) {
/* 163 */             le = (LiteralExpression)leftGeometry;
/* 164 */             if (le != null && le.getLiteral() != null && le.getLiteral() instanceof Geometry)
/* 165 */               bbox = ((Geometry)le.getLiteral()).getEnvelopeInternal(); 
/* 168 */           } else if (rightGeometry != null && rightGeometry.getType() == 104) {
/* 169 */             le = (LiteralExpression)rightGeometry;
/* 170 */             if (le != null && le.getLiteral() != null && le.getLiteral() instanceof Geometry) {
/* 171 */               Geometry g = (Geometry)le.getLiteral();
/* 172 */               bbox = g.getEnvelopeInternal();
/*     */             } 
/*     */           } 
/* 176 */           if (bbox != null) {
/* 177 */             boolean changed = false;
/* 179 */             double minx = bbox.getMinX();
/* 180 */             double miny = bbox.getMinY();
/* 181 */             double maxx = bbox.getMaxX();
/* 182 */             double maxy = bbox.getMaxY();
/* 183 */             if (this.maxbbox != null) {
/* 184 */               if (minx < this.maxbbox.getMinX()) {
/* 185 */                 minx = this.maxbbox.getMinX();
/* 186 */                 changed = true;
/*     */               } 
/* 188 */               if (maxx > this.maxbbox.getMaxX()) {
/* 189 */                 maxx = this.maxbbox.getMaxX();
/* 190 */                 changed = true;
/*     */               } 
/* 192 */               if (miny < this.maxbbox.getMinY()) {
/* 193 */                 miny = this.maxbbox.getMinY();
/* 194 */                 changed = true;
/*     */               } 
/* 196 */               if (maxy > this.maxbbox.getMaxY()) {
/* 197 */                 maxy = this.maxbbox.getMaxY();
/* 198 */                 changed = true;
/*     */               } 
/*     */             } 
/* 201 */             if (changed) {
/* 202 */               Envelope tmp = new Envelope(minx, maxx, miny, maxy);
/*     */               try {
/* 204 */                 le.setLiteral((new GeometryFactory()).toGeometry(tmp));
/* 205 */               } catch (IllegalFilterException e) {
/* 206 */                 logger.warning(e.toString());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           return;
/*     */       } 
/* 222 */       if (leftGeometry != null)
/* 223 */         leftGeometry.accept((FilterVisitor)this); 
/* 224 */       if (rightGeometry != null)
/* 225 */         rightGeometry.accept((FilterVisitor)this); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(LikeFilter filter) {
/* 234 */     if (filter != null && 
/* 235 */       filter.getValue() != null)
/* 236 */       filter.getValue().accept((FilterVisitor)this); 
/*     */   }
/*     */   
/*     */   public void visit(LogicFilter filter) {
/* 243 */     if (filter != null) {
/* 244 */       Iterator<Filter> i = filter.getFilterIterator();
/* 245 */       while (i.hasNext()) {
/* 246 */         Filter child = i.next();
/* 247 */         if (child instanceof Filter) {
/* 248 */           Filter tmp = (Filter)child;
/* 249 */           tmp.accept((FilterVisitor)this);
/*     */           continue;
/*     */         } 
/* 251 */         if (child instanceof IncludeFilter) {
/* 252 */           IncludeFilter include = (IncludeFilter)child;
/* 253 */           visit(include);
/*     */           continue;
/*     */         } 
/* 255 */         if (child instanceof ExcludeFilter) {
/* 256 */           ExcludeFilter exclude = (ExcludeFilter)child;
/* 257 */           visit(exclude);
/*     */           continue;
/*     */         } 
/* 260 */         logger.warning("Unnown filter:" + child);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(NullFilter filter) {
/* 269 */     if (filter != null && 
/* 270 */       filter.getNullCheckValue() != null)
/* 271 */       filter.getNullCheckValue().accept((FilterVisitor)this); 
/*     */   }
/*     */   
/*     */   public void visit(FidFilter filter) {}
/*     */   
/*     */   public void visit(AttributeExpression expression) {}
/*     */   
/*     */   public void visit(Expression expression) {}
/*     */   
/*     */   public void visit(LiteralExpression expression) {}
/*     */   
/*     */   public void visit(MathExpression expression) {}
/*     */   
/*     */   public void visit(FunctionExpression expression) {}
/*     */   
/*     */   public void visit(IncludeFilter filter) {}
/*     */   
/*     */   public void visit(ExcludeFilter filter) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\WFSBBoxFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */