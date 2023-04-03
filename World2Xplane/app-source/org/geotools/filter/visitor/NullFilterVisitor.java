/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNil;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ import org.opengis.filter.temporal.After;
/*     */ import org.opengis.filter.temporal.AnyInteracts;
/*     */ import org.opengis.filter.temporal.Before;
/*     */ import org.opengis.filter.temporal.Begins;
/*     */ import org.opengis.filter.temporal.BegunBy;
/*     */ import org.opengis.filter.temporal.During;
/*     */ import org.opengis.filter.temporal.EndedBy;
/*     */ import org.opengis.filter.temporal.Ends;
/*     */ import org.opengis.filter.temporal.Meets;
/*     */ import org.opengis.filter.temporal.MetBy;
/*     */ import org.opengis.filter.temporal.OverlappedBy;
/*     */ import org.opengis.filter.temporal.TContains;
/*     */ import org.opengis.filter.temporal.TEquals;
/*     */ import org.opengis.filter.temporal.TOverlaps;
/*     */ 
/*     */ public abstract class NullFilterVisitor implements FilterVisitor, ExpressionVisitor {
/* 109 */   public static NullFilterVisitor NULL_VISITOR = new NullFilterVisitor() {
/*     */       public Object visit(And filter, Object data) {
/* 112 */         return data;
/*     */       }
/*     */       
/*     */       public Object visit(Or filter, Object data) {
/* 116 */         return data;
/*     */       }
/*     */       
/*     */       public Object visit(Not filter, Object data) {
/* 120 */         return data;
/*     */       }
/*     */     };
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object data) {
/* 128 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object data) {
/* 132 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object data) {
/* 136 */     if (data == null)
/* 136 */       return null; 
/* 137 */     if (filter.getChildren() != null)
/* 138 */       for (Filter child : filter.getChildren()) {
/* 139 */         data = child.accept(this, data);
/* 140 */         if (data == null)
/* 140 */           return null; 
/*     */       }  
/* 143 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object data) {
/* 147 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object data) {
/* 151 */     if (data == null)
/* 151 */       return data; 
/* 153 */     Filter child = filter.getFilter();
/* 154 */     if (child != null)
/* 155 */       data = child.accept(this, data); 
/* 157 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object data) {
/* 161 */     if (data == null)
/* 161 */       return null; 
/* 162 */     if (filter.getChildren() != null)
/* 163 */       for (Filter child : filter.getChildren()) {
/* 164 */         data = child.accept(this, data);
/* 165 */         if (data == null)
/* 165 */           return null; 
/*     */       }  
/* 168 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object data) {
/* 172 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object data) {
/* 176 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object data) {
/* 180 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object data) {
/* 184 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object data) {
/* 188 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object data) {
/* 192 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object data) {
/* 196 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object data) {
/* 200 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object data) {
/* 204 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object data) {
/* 208 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object data) {
/* 212 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/* 216 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/* 220 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/* 224 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/* 228 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/* 232 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/* 236 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/* 240 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/* 244 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 248 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 252 */     return data;
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object data) {
/* 256 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object data) {
/* 260 */     return null;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object data) {
/* 264 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object data) {
/* 268 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/* 272 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object data) {
/* 276 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object data) {
/* 280 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/* 284 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object data) {
/* 288 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object data) {
/* 292 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object data) {
/* 296 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object data) {
/* 300 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object data) {
/* 304 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object data) {
/* 308 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object data) {
/* 312 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object data) {
/* 316 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object data) {
/* 320 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object data) {
/* 324 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object data) {
/* 328 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object data) {
/* 332 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object data) {
/* 336 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object data) {
/* 340 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object data) {
/* 344 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\NullFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */