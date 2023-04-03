/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.geotools.gml.producer.GeometryTransformer;
/*     */ import org.geotools.xml.transform.TransformerBase;
/*     */ import org.geotools.xml.transform.Translator;
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
/*     */ import org.opengis.filter.identity.Identifier;
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
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class FilterTransformer extends TransformerBase {
/*  99 */   private static String defaultNamespace = "http://www.opengis.net/ogc";
/*     */   
/* 102 */   private static Map comparisions = new HashMap<Object, Object>();
/*     */   
/* 105 */   private static Map spatial = new HashMap<Object, Object>();
/*     */   
/* 108 */   private static Map logical = new HashMap<Object, Object>();
/*     */   
/*     */   public String transform(Filter f) throws TransformerException {
/* 114 */     return transform(f);
/*     */   }
/*     */   
/*     */   public Translator createTranslator(ContentHandler handler) {
/* 118 */     return (Translator)new FilterTranslator(handler);
/*     */   }
/*     */   
/*     */   public static class FilterTranslator extends TransformerBase.TranslatorSupport implements FilterVisitor, ExpressionVisitor {
/*     */     GeometryTransformer.GeometryTranslator geometryEncoder;
/*     */     
/*     */     public FilterTranslator(ContentHandler handler) {
/* 124 */       super(handler, "ogc", FilterTransformer.defaultNamespace);
/* 126 */       this.geometryEncoder = new GeometryTransformer.GeometryTranslator(handler);
/* 128 */       addNamespaceDeclarations((TransformerBase.TranslatorSupport)this.geometryEncoder);
/*     */     }
/*     */     
/*     */     public Object visit(ExcludeFilter filter, Object extraData) {
/* 135 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(IncludeFilter filter, Object extraData) {
/* 142 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(And filter, Object extraData) {
/* 146 */       start("And");
/* 147 */       for (Filter child : filter.getChildren())
/* 148 */         child.accept(this, extraData); 
/* 150 */       end("And");
/* 151 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Id filter, Object extraData) {
/* 155 */       Set<Identifier> fids = filter.getIdentifiers();
/* 156 */       for (Identifier fid : fids) {
/* 157 */         AttributesImpl atts = new AttributesImpl();
/* 158 */         atts.addAttribute(null, "fid", "fid", null, fid.toString());
/* 159 */         element("FeatureId", null, atts);
/*     */       } 
/* 161 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Not filter, Object extraData) {
/* 165 */       start("Not");
/* 166 */       filter.getFilter().accept(this, extraData);
/* 167 */       end("Not");
/* 168 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Or filter, Object extraData) {
/* 173 */       start("Or");
/* 174 */       for (Filter child : filter.getChildren())
/* 175 */         child.accept(this, extraData); 
/* 177 */       end("Or");
/* 178 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsBetween filter, Object extraData) {
/* 182 */       Expression left = filter.getLowerBoundary();
/* 183 */       Expression mid = filter.getExpression();
/* 184 */       Expression right = filter.getUpperBoundary();
/* 186 */       String type = "PropertyIsBetween";
/* 188 */       start(type);
/* 189 */       mid.accept(this, extraData);
/* 190 */       start("LowerBoundary");
/* 191 */       left.accept(this, extraData);
/* 192 */       end("LowerBoundary");
/* 193 */       start("UpperBoundary");
/* 194 */       right.accept(this, extraData);
/* 195 */       end("UpperBoundary");
/* 196 */       end(type);
/* 198 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 202 */       Expression left = filter.getExpression1();
/* 203 */       Expression right = filter.getExpression2();
/* 205 */       String type = "PropertyIsEqualTo";
/* 207 */       start("PropertyIsEqualTo");
/* 208 */       left.accept(this, extraData);
/* 209 */       right.accept(this, extraData);
/* 210 */       end("PropertyIsEqualTo");
/* 211 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 215 */       Expression left = filter.getExpression1();
/* 216 */       Expression right = filter.getExpression2();
/* 218 */       String type = "PropertyIsNotEqualTo";
/* 220 */       start("PropertyIsNotEqualTo");
/* 221 */       left.accept(this, extraData);
/* 222 */       right.accept(this, extraData);
/* 223 */       end("PropertyIsNotEqualTo");
/* 224 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsGreaterThan filter, Object extraData) {
/* 228 */       Expression left = filter.getExpression1();
/* 229 */       Expression right = filter.getExpression2();
/* 231 */       String type = "PropertyIsGreaterThan";
/* 233 */       start("PropertyIsGreaterThan");
/* 234 */       left.accept(this, extraData);
/* 235 */       right.accept(this, extraData);
/* 236 */       end("PropertyIsGreaterThan");
/* 237 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object extraData) {
/* 241 */       Expression left = filter.getExpression1();
/* 242 */       Expression right = filter.getExpression2();
/* 244 */       String type = "PropertyIsGreaterThanOrEqualTo";
/* 246 */       start("PropertyIsGreaterThanOrEqualTo");
/* 247 */       left.accept(this, extraData);
/* 248 */       right.accept(this, extraData);
/* 249 */       end("PropertyIsGreaterThanOrEqualTo");
/* 250 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsLessThan filter, Object extraData) {
/* 254 */       Expression left = filter.getExpression1();
/* 255 */       Expression right = filter.getExpression2();
/* 257 */       String type = "PropertyIsLessThan";
/* 259 */       start("PropertyIsLessThan");
/* 260 */       left.accept(this, extraData);
/* 261 */       right.accept(this, extraData);
/* 262 */       end("PropertyIsLessThan");
/* 263 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsLessThanOrEqualTo filter, Object extraData) {
/* 267 */       Expression left = filter.getExpression1();
/* 268 */       Expression right = filter.getExpression2();
/* 270 */       String type = "PropertyIsLessThanOrEqualTo";
/* 272 */       start("PropertyIsLessThanOrEqualTo");
/* 273 */       left.accept(this, extraData);
/* 274 */       right.accept(this, extraData);
/* 275 */       end("PropertyIsLessThanOrEqualTo");
/* 276 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsLike filter, Object extraData) {
/* 280 */       String wcm = filter.getWildCard();
/* 281 */       String wcs = filter.getSingleChar();
/* 282 */       String esc = filter.getEscape();
/* 283 */       Expression expression = filter.getExpression();
/* 285 */       AttributesImpl atts = new AttributesImpl();
/* 286 */       atts.addAttribute("", "wildCard", "wildCard", "", wcm);
/* 287 */       atts.addAttribute("", "singleChar", "singleChar", "", wcs);
/* 288 */       atts.addAttribute("", "escape", "escape", "", esc);
/* 290 */       start("PropertyIsLike", atts);
/* 292 */       expression.accept(this, extraData);
/* 294 */       element("Literal", filter.getLiteral());
/* 296 */       end("PropertyIsLike");
/* 297 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsNull filter, Object extraData) {
/* 301 */       Expression expr = filter.getExpression();
/* 303 */       String type = "PropertyIsNull";
/* 304 */       start(type);
/* 305 */       expr.accept(this, extraData);
/* 306 */       end(type);
/* 307 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyIsNil filter, Object extraData) {
/* 311 */       Expression expr = filter.getExpression();
/* 313 */       AttributesImpl atts = new AttributesImpl();
/* 314 */       if (filter.getNilReason() != null)
/* 315 */         atts.addAttribute("", "nilReason", "nilReason", "", filter.getNilReason().toString()); 
/* 318 */       String type = "PropertyIsNil";
/* 319 */       start(type, atts);
/* 320 */       expr.accept(this, extraData);
/* 321 */       end(type);
/* 322 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(BBOX filter, Object extraData) {
/* 326 */       Expression left = filter.getExpression1();
/* 327 */       Expression right = filter.getExpression2();
/* 329 */       String type = "BBOX";
/* 330 */       start("BBOX");
/* 331 */       left.accept(this, extraData);
/* 332 */       if (right instanceof Literal) {
/* 333 */         Literal literal = (Literal)right;
/* 334 */         Envelope bbox = (Envelope)literal.evaluate(null, Envelope.class);
/* 335 */         if (bbox != null) {
/* 336 */           encode(bbox);
/*     */         } else {
/* 339 */           right.accept(this, extraData);
/*     */         } 
/*     */       } else {
/* 343 */         right.accept(this, extraData);
/*     */       } 
/* 345 */       end("BBOX");
/* 346 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Beyond filter, Object extraData) {
/* 350 */       Expression left = filter.getExpression1();
/* 351 */       Expression right = filter.getExpression2();
/* 353 */       String type = "Beyond";
/* 355 */       start("Beyond");
/* 356 */       left.accept(this, extraData);
/* 357 */       right.accept(this, extraData);
/* 358 */       element("Distance", String.valueOf(filter.getDistance()));
/* 359 */       element("DistanceUnits", String.valueOf(filter.getDistanceUnits()));
/* 360 */       end("Beyond");
/* 361 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Contains filter, Object extraData) {
/* 365 */       Expression left = filter.getExpression1();
/* 366 */       Expression right = filter.getExpression2();
/* 368 */       String type = "Contains";
/* 370 */       start("Contains");
/* 371 */       left.accept(this, extraData);
/* 372 */       right.accept(this, extraData);
/* 373 */       end("Contains");
/* 374 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Crosses filter, Object extraData) {
/* 378 */       Expression left = filter.getExpression1();
/* 379 */       Expression right = filter.getExpression2();
/* 381 */       String type = "Crosses";
/* 383 */       start("Crosses");
/* 384 */       left.accept(this, extraData);
/* 385 */       right.accept(this, extraData);
/* 386 */       end("Crosses");
/* 387 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Disjoint filter, Object extraData) {
/* 391 */       Expression left = filter.getExpression1();
/* 392 */       Expression right = filter.getExpression2();
/* 394 */       String type = "Disjoint";
/* 396 */       start("Disjoint");
/* 397 */       left.accept(this, extraData);
/* 398 */       right.accept(this, extraData);
/* 399 */       end("Disjoint");
/* 400 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(DWithin filter, Object extraData) {
/* 404 */       Expression left = filter.getExpression1();
/* 405 */       Expression right = filter.getExpression2();
/* 406 */       String type = "DWithin";
/* 408 */       start("DWithin");
/* 409 */       left.accept(this, extraData);
/* 410 */       right.accept(this, extraData);
/* 411 */       element("Distance", String.valueOf(filter.getDistance()));
/* 412 */       element("DistanceUnits", String.valueOf(filter.getDistanceUnits()));
/* 413 */       end("DWithin");
/* 414 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Equals filter, Object extraData) {
/* 418 */       Expression left = filter.getExpression1();
/* 419 */       Expression right = filter.getExpression2();
/* 421 */       String type = "Equals";
/* 423 */       start("Equals");
/* 424 */       left.accept(this, extraData);
/* 425 */       right.accept(this, extraData);
/* 426 */       end("Equals");
/* 427 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Intersects filter, Object extraData) {
/* 431 */       Expression left = filter.getExpression1();
/* 432 */       Expression right = filter.getExpression2();
/* 434 */       String type = "Intersects";
/* 436 */       start("Intersects");
/* 437 */       left.accept(this, extraData);
/* 438 */       right.accept(this, extraData);
/* 439 */       end("Intersects");
/* 440 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Overlaps filter, Object extraData) {
/* 444 */       Expression left = filter.getExpression1();
/* 445 */       Expression right = filter.getExpression2();
/* 447 */       String type = "Overlaps";
/* 449 */       start("Overlaps");
/* 450 */       left.accept(this, extraData);
/* 451 */       right.accept(this, extraData);
/* 452 */       end("Overlaps");
/* 453 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Touches filter, Object extraData) {
/* 457 */       Expression left = filter.getExpression1();
/* 458 */       Expression right = filter.getExpression2();
/* 460 */       String type = "Touches";
/* 462 */       start("Touches");
/* 463 */       left.accept(this, extraData);
/* 464 */       right.accept(this, extraData);
/* 465 */       end("Touches");
/* 466 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Within filter, Object extraData) {
/* 470 */       Expression left = filter.getExpression1();
/* 471 */       Expression right = filter.getExpression2();
/* 473 */       String type = "Within";
/* 475 */       start("Within");
/* 476 */       left.accept(this, extraData);
/* 477 */       right.accept(this, extraData);
/* 478 */       end("Within");
/* 479 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visitNullFilter(Object extraData) {
/* 484 */       return extraData;
/*     */     }
/*     */     
/*     */     public void encode(Object o) throws IllegalArgumentException {
/* 488 */       if (o instanceof Filter) {
/* 489 */         Filter filter = (Filter)o;
/* 490 */         filter.accept(this, null);
/* 492 */       } else if (o instanceof Expression) {
/* 493 */         Expression expression = (Expression)o;
/* 494 */         expression.accept(this, null);
/*     */       } else {
/* 497 */         throw new IllegalArgumentException("Cannot encode " + ((o == null) ? "null" : o.getClass().getName()) + " should be Filter or Expression");
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object visit(NilExpression expression, Object extraData) {
/* 503 */       element("Literal", "");
/* 504 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Add expression, Object extraData) {
/* 508 */       String type = "Add";
/* 509 */       start(type);
/* 510 */       expression.getExpression1().accept(this, extraData);
/* 511 */       expression.getExpression2().accept(this, extraData);
/* 512 */       end(type);
/* 513 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Divide expression, Object extraData) {
/* 517 */       String type = "Div";
/* 518 */       start(type);
/* 519 */       expression.getExpression1().accept(this, extraData);
/* 520 */       expression.getExpression2().accept(this, extraData);
/* 521 */       end(type);
/* 522 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Function expression, Object extraData) {
/* 526 */       String type = "Function";
/* 528 */       AttributesImpl atts = new AttributesImpl();
/* 529 */       atts.addAttribute("", "name", "name", "", expression.getName());
/* 530 */       start(type, atts);
/* 532 */       for (Expression parameter : expression.getParameters())
/* 533 */         parameter.accept(this, extraData); 
/* 535 */       end(type);
/* 536 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Literal expression, Object extraData) {
/* 540 */       Object value = expression.getValue();
/* 541 */       if (value == null) {
/* 542 */         element("Literal", "");
/* 544 */       } else if (value instanceof Geometry) {
/* 545 */         this.geometryEncoder.encode((Geometry)value);
/*     */       } else {
/* 548 */         String txt = (String)expression.evaluate(null, String.class);
/* 549 */         if (txt == null)
/* 550 */           txt = value.toString(); 
/* 552 */         element("Literal", txt);
/*     */       } 
/* 554 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Multiply expression, Object extraData) {
/* 558 */       String type = "Mul";
/* 559 */       start(type);
/* 560 */       expression.getExpression1().accept(this, extraData);
/* 561 */       expression.getExpression2().accept(this, extraData);
/* 562 */       end(type);
/* 563 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(PropertyName expression, Object extraData) {
/* 567 */       element("PropertyName", expression.getPropertyName());
/* 568 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(Subtract expression, Object extraData) {
/* 572 */       String type = "Sub";
/* 573 */       start(type);
/* 574 */       expression.getExpression1().accept(this, extraData);
/* 575 */       expression.getExpression2().accept(this, extraData);
/* 576 */       end(type);
/* 577 */       return extraData;
/*     */     }
/*     */     
/*     */     public Object visit(After after, Object extraData) {
/* 581 */       return visit((BinaryTemporalOperator)after, "After", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(AnyInteracts anyInteracts, Object extraData) {
/* 585 */       return visit((BinaryTemporalOperator)anyInteracts, "AnyInteracts", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(Before before, Object extraData) {
/* 589 */       return visit((BinaryTemporalOperator)before, "Before", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(Begins begins, Object extraData) {
/* 593 */       return visit((BinaryTemporalOperator)begins, "Begins", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(BegunBy begunBy, Object extraData) {
/* 597 */       return visit((BinaryTemporalOperator)begunBy, "BegunBy", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(During during, Object extraData) {
/* 601 */       return visit((BinaryTemporalOperator)during, "During", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(EndedBy endedBy, Object extraData) {
/* 605 */       return visit((BinaryTemporalOperator)endedBy, "EndedBy", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(Ends ends, Object extraData) {
/* 609 */       return visit((BinaryTemporalOperator)ends, "Ends", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(Meets meets, Object extraData) {
/* 613 */       return visit((BinaryTemporalOperator)meets, "Meets", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(MetBy metBy, Object extraData) {
/* 617 */       return visit((BinaryTemporalOperator)metBy, "MetBy", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(OverlappedBy overlappedBy, Object extraData) {
/* 621 */       return visit((BinaryTemporalOperator)overlappedBy, "OverlappedBy", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(TContains contains, Object extraData) {
/* 625 */       return visit((BinaryTemporalOperator)contains, "TContains", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(TEquals equals, Object extraData) {
/* 629 */       return visit((BinaryTemporalOperator)equals, "TEquals", extraData);
/*     */     }
/*     */     
/*     */     public Object visit(TOverlaps contains, Object extraData) {
/* 633 */       return visit((BinaryTemporalOperator)contains, "TOverlaps", extraData);
/*     */     }
/*     */     
/*     */     protected Object visit(BinaryTemporalOperator filter, String name, Object data) {
/* 637 */       start(name);
/* 638 */       filter.getExpression1().accept(this, data);
/* 639 */       filter.getExpression2().accept(this, data);
/* 640 */       end(name);
/* 641 */       return data;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */