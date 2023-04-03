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
/*     */ import org.opengis.filter.expression.Expression;
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
/*     */ import org.opengis.filter.temporal.BinaryTemporalOperator;
/*     */ import org.opengis.filter.temporal.During;
/*     */ import org.opengis.filter.temporal.EndedBy;
/*     */ import org.opengis.filter.temporal.Ends;
/*     */ import org.opengis.filter.temporal.Meets;
/*     */ import org.opengis.filter.temporal.MetBy;
/*     */ import org.opengis.filter.temporal.OverlappedBy;
/*     */ import org.opengis.filter.temporal.TContains;
/*     */ import org.opengis.filter.temporal.TEquals;
/*     */ import org.opengis.filter.temporal.TOverlaps;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public abstract class AbstractFinderFilterVisitor implements FilterVisitor, ExpressionVisitor {
/*     */   protected boolean found = false;
/*     */   
/*     */   public boolean isFound() {
/*  95 */     return this.found;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  98 */     this.found = false;
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object data) {
/* 102 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object data) {
/* 106 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object data) {
/* 110 */     if (filter.getChildren() != null)
/* 111 */       for (Filter child : filter.getChildren()) {
/* 112 */         child.accept(this, data);
/* 113 */         if (this.found)
/*     */           break; 
/*     */       }  
/* 116 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object data) {
/* 120 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object data) {
/* 124 */     if (filter.getFilter() != null)
/* 125 */       filter.getFilter().accept(this, data); 
/* 127 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object data) {
/* 131 */     if (filter.getChildren() != null)
/* 132 */       for (Filter child : filter.getChildren()) {
/* 133 */         child.accept(this, data);
/* 134 */         if (this.found)
/*     */           break; 
/*     */       }  
/* 137 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object data) {
/* 141 */     filter.getLowerBoundary().accept(this, data);
/* 142 */     if (this.found)
/* 142 */       return Boolean.valueOf(this.found); 
/* 143 */     filter.getExpression().accept(this, data);
/* 144 */     if (this.found)
/* 144 */       return Boolean.valueOf(this.found); 
/* 145 */     filter.getUpperBoundary().accept(this, data);
/* 146 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object data) {
/* 150 */     filter.getExpression1().accept(this, data);
/* 151 */     if (this.found)
/* 151 */       return Boolean.valueOf(this.found); 
/* 152 */     filter.getExpression2().accept(this, data);
/* 154 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object data) {
/* 158 */     filter.getExpression1().accept(this, data);
/* 159 */     if (this.found)
/* 159 */       return Boolean.valueOf(this.found); 
/* 160 */     filter.getExpression2().accept(this, data);
/* 162 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object data) {
/* 166 */     filter.getExpression1().accept(this, data);
/* 167 */     if (this.found)
/* 167 */       return Boolean.valueOf(this.found); 
/* 168 */     filter.getExpression2().accept(this, data);
/* 170 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object data) {
/* 174 */     filter.getExpression1().accept(this, data);
/* 175 */     if (this.found)
/* 175 */       return Boolean.valueOf(this.found); 
/* 176 */     filter.getExpression2().accept(this, data);
/* 178 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object data) {
/* 182 */     filter.getExpression1().accept(this, data);
/* 183 */     if (this.found)
/* 183 */       return Boolean.valueOf(this.found); 
/* 184 */     filter.getExpression2().accept(this, data);
/* 186 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object data) {
/* 190 */     filter.getExpression1().accept(this, data);
/* 191 */     if (this.found)
/* 191 */       return Boolean.valueOf(this.found); 
/* 192 */     filter.getExpression2().accept(this, data);
/* 194 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object data) {
/* 198 */     filter.getExpression().accept(this, data);
/* 199 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object data) {
/* 203 */     filter.getExpression().accept(this, data);
/* 204 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNil filter, Object data) {
/* 208 */     filter.getExpression().accept(this, data);
/* 209 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(final BBOX filter, Object data) {
/* 214 */     PropertyName property = new PropertyName() {
/*     */         public String getPropertyName() {
/* 216 */           return filter.getPropertyName();
/*     */         }
/*     */         
/*     */         public Object accept(ExpressionVisitor visitor, Object data) {
/* 219 */           return visitor.visit(this, data);
/*     */         }
/*     */         
/*     */         public Object evaluate(Object object) {
/* 222 */           return null;
/*     */         }
/*     */         
/*     */         public Object evaluate(Object object, Class context) {
/* 225 */           return null;
/*     */         }
/*     */         
/*     */         public NamespaceSupport getNamespaceContext() {
/* 229 */           return null;
/*     */         }
/*     */       };
/* 232 */     property.accept(this, data);
/* 233 */     if (this.found)
/* 233 */       return Boolean.valueOf(this.found); 
/* 234 */     filter.getExpression2().accept(this, data);
/* 236 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/* 240 */     filter.getExpression1().accept(this, data);
/* 241 */     if (this.found)
/* 241 */       return Boolean.valueOf(this.found); 
/* 242 */     filter.getExpression2().accept(this, data);
/* 243 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/* 247 */     filter.getExpression1().accept(this, data);
/* 248 */     if (this.found)
/* 248 */       return Boolean.valueOf(this.found); 
/* 249 */     filter.getExpression2().accept(this, data);
/* 250 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/* 254 */     filter.getExpression1().accept(this, data);
/* 255 */     if (this.found)
/* 255 */       return Boolean.valueOf(this.found); 
/* 256 */     filter.getExpression2().accept(this, data);
/* 257 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/* 261 */     filter.getExpression1().accept(this, data);
/* 262 */     if (this.found)
/* 262 */       return Boolean.valueOf(this.found); 
/* 263 */     filter.getExpression2().accept(this, data);
/* 264 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/* 268 */     filter.getExpression1().accept(this, data);
/* 269 */     if (this.found)
/* 269 */       return Boolean.valueOf(this.found); 
/* 270 */     filter.getExpression2().accept(this, data);
/* 271 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/* 275 */     filter.getExpression1().accept(this, data);
/* 276 */     filter.getExpression2().accept(this, data);
/* 277 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/* 281 */     filter.getExpression1().accept(this, data);
/* 282 */     if (this.found)
/* 282 */       return Boolean.valueOf(this.found); 
/* 283 */     filter.getExpression2().accept(this, data);
/* 285 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/* 289 */     filter.getExpression1().accept(this, data);
/* 290 */     if (this.found)
/* 290 */       return Boolean.valueOf(this.found); 
/* 291 */     filter.getExpression2().accept(this, data);
/* 293 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 297 */     filter.getExpression1().accept(this, data);
/* 298 */     if (this.found)
/* 298 */       return Boolean.valueOf(this.found); 
/* 299 */     filter.getExpression2().accept(this, data);
/* 301 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 305 */     filter.getExpression1().accept(this, data);
/* 306 */     if (this.found)
/* 306 */       return Boolean.valueOf(this.found); 
/* 307 */     filter.getExpression2().accept(this, data);
/* 309 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object data) {
/* 313 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object data) {
/* 317 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object data) {
/* 321 */     expression.getExpression1().accept(this, data);
/* 322 */     if (this.found)
/* 322 */       return Boolean.valueOf(this.found); 
/* 323 */     expression.getExpression2().accept(this, data);
/* 324 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object data) {
/* 328 */     expression.getExpression1().accept(this, data);
/* 329 */     if (this.found)
/* 329 */       return Boolean.valueOf(this.found); 
/* 330 */     expression.getExpression2().accept(this, data);
/* 331 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/* 335 */     for (Expression parameter : expression.getParameters())
/* 336 */       data = parameter.accept(this, data); 
/* 338 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object data) {
/* 342 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object data) {
/* 346 */     expression.getExpression1().accept(this, data);
/* 347 */     if (this.found)
/* 347 */       return Boolean.valueOf(this.found); 
/* 348 */     expression.getExpression2().accept(this, data);
/* 349 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/* 353 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object data) {
/* 357 */     expression.getExpression1().accept(this, data);
/* 358 */     if (this.found)
/* 358 */       return Boolean.valueOf(this.found); 
/* 359 */     expression.getExpression2().accept(this, data);
/* 360 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */   
/*     */   public Object visit(After after, Object extraData) {
/* 364 */     return visit((BinaryTemporalOperator)after, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 368 */     return visit((BinaryTemporalOperator)anyInteracts, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Before before, Object extraData) {
/* 372 */     return visit((BinaryTemporalOperator)before, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Begins begins, Object extraData) {
/* 376 */     return visit((BinaryTemporalOperator)begins, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(BegunBy begunBy, Object extraData) {
/* 380 */     return visit((BinaryTemporalOperator)begunBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(During during, Object extraData) {
/* 384 */     return visit((BinaryTemporalOperator)during, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(EndedBy endedBy, Object extraData) {
/* 388 */     return visit((BinaryTemporalOperator)endedBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Ends ends, Object extraData) {
/* 392 */     return visit((BinaryTemporalOperator)ends, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Meets meets, Object extraData) {
/* 396 */     return visit((BinaryTemporalOperator)meets, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(MetBy metBy, Object extraData) {
/* 400 */     return visit((BinaryTemporalOperator)metBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 404 */     return visit((BinaryTemporalOperator)overlappedBy, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TContains contains, Object extraData) {
/* 408 */     return visit((BinaryTemporalOperator)contains, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TEquals equals, Object extraData) {
/* 412 */     return visit((BinaryTemporalOperator)equals, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(TOverlaps contains, Object extraData) {
/* 416 */     return visit((BinaryTemporalOperator)contains, extraData);
/*     */   }
/*     */   
/*     */   protected Object visit(BinaryTemporalOperator filter, Object data) {
/* 420 */     filter.getExpression1().accept(this, data);
/* 421 */     if (this.found)
/* 421 */       return Boolean.valueOf(this.found); 
/* 422 */     filter.getExpression2().accept(this, data);
/* 423 */     return Boolean.valueOf(this.found);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\AbstractFinderFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */