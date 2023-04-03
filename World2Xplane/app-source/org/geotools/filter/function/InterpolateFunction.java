/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.Parameter;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.text.Text;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.KVP;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class InterpolateFunction implements Function {
/* 100 */   private static final Logger LOGGER = Logger.getLogger(InterpolateFunction.class.getName());
/*     */   
/* 102 */   private static final FilterFactory2 ff2 = CommonFactoryFinder.getFilterFactory2(null);
/*     */   
/*     */   private static final double EPS = 1.0E-8D;
/*     */   
/*     */   public static final String MODE_LINEAR = "linear";
/*     */   
/*     */   public static final String MODE_COSINE = "cosine";
/*     */   
/*     */   public static final String MODE_CUBIC = "cubic";
/*     */   
/*     */   public static final String METHOD_NUMERIC = "numeric";
/*     */   
/*     */   public static final String METHOD_COLOR = "color";
/*     */   
/*     */   private static final String METHOD_COLOUR = "colour";
/*     */   
/*     */   private Mode mode;
/*     */   
/*     */   private boolean modeSpecified;
/*     */   
/*     */   private Method method;
/*     */   
/*     */   private boolean methodSpecified;
/*     */   
/*     */   private List<InterpPoint> interpPoints;
/*     */   
/*     */   public static final String RASTER_DATA = "Rasterdata";
/*     */   
/*     */   private final List<Expression> parameters;
/*     */   
/*     */   private final Literal fallback;
/*     */   
/*     */   public static final FunctionName NAME;
/*     */   
/*     */   private enum Mode {
/* 130 */     LINEAR, COSINE, CUBIC;
/*     */   }
/*     */   
/*     */   private enum Method {
/* 136 */     NUMERIC, COLOR;
/*     */   }
/*     */   
/*     */   private static class InterpPoint {
/*     */     Expression data;
/*     */     
/*     */     Expression value;
/*     */     
/*     */     public InterpPoint(Expression data, Expression value) {
/* 150 */       this.data = data;
/* 151 */       this.value = value;
/*     */     }
/*     */   }
/*     */   
/*     */   static {
/* 171 */     Parameter<Object> lookup = new Parameter("lookup", Object.class, 1, 1);
/* 172 */     Parameter<Object> table = new Parameter("data value pairs", Object.class, 4, -1);
/* 173 */     Parameter<String> mode = new Parameter("mode", String.class, Text.text("mode"), Text.text("linear, cosine or cubic"), true, 1, 1, "linear", (Map)new KVP(new Object[] { "options", Arrays.asList(new String[] { "linear", "cosine", "cubic" }) }));
/* 181 */     Parameter<String> method = new Parameter("method", String.class, Text.text("method"), Text.text("numeric or color"), false, 0, 1, "numeric", (Map)new KVP(new Object[] { "options", Arrays.asList(new String[] { "numeric", "color" }) }));
/* 189 */     NAME = (FunctionName)new FunctionNameImpl("Interpolate", (Parameter)lookup, new Parameter[] { (Parameter)table, (Parameter)mode, (Parameter)method });
/*     */   }
/*     */   
/*     */   public InterpolateFunction() {
/* 192 */     this(new ArrayList<Expression>(), null);
/*     */   }
/*     */   
/*     */   public InterpolateFunction(List<Expression> parameters, Literal fallback) {
/* 196 */     this.parameters = parameters;
/* 197 */     this.fallback = fallback;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 201 */     return "Interpolate";
/*     */   }
/*     */   
/*     */   public FunctionName getFunctionName() {
/* 204 */     return NAME;
/*     */   }
/*     */   
/*     */   public List<Expression> getParameters() {
/* 207 */     return Collections.unmodifiableList(this.parameters);
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 211 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/* 215 */     return evaluate(object, Object.class);
/*     */   }
/*     */   
/*     */   public <T> T evaluate(Object object, Class<T> context) {
/* 219 */     initialize();
/* 221 */     if (this.method == Method.NUMERIC && Color.class.isAssignableFrom(context))
/* 222 */       throw new IllegalArgumentException("Trying to evaluate the function as Color but the method parameter is set as NUMERIC"); 
/* 226 */     if (this.method == Method.COLOR && !Color.class.isAssignableFrom(context))
/* 227 */       throw new IllegalArgumentException("Trying to evaluate the function as " + context.getSimpleName() + " but the method parameter is set as COLOR"); 
/* 238 */     Expression lookup = this.parameters.get(0);
/* 239 */     Double lookupValue = null;
/* 244 */     String lookupString = (String)lookup.evaluate(object, String.class);
/* 245 */     if (lookupString.equalsIgnoreCase("Rasterdata")) {
/* 246 */       lookupValue = Double.valueOf(((Number)object).doubleValue());
/*     */     } else {
/* 248 */       lookupValue = Double.valueOf(lookupString);
/*     */     } 
/* 255 */     if (this.interpPoints.size() == 1)
/* 256 */       return (T)((InterpPoint)this.interpPoints.get(0)).value.evaluate(object, context); 
/* 259 */     int segment = findSegment(lookupValue, object);
/* 260 */     if (segment <= 0)
/* 262 */       return (T)((InterpPoint)this.interpPoints.get(0)).value.evaluate(object, context); 
/* 264 */     if (segment >= this.interpPoints.size())
/* 266 */       return (T)((InterpPoint)this.interpPoints.get(this.interpPoints.size() - 1)).value.evaluate(object, context); 
/* 273 */     switch (this.mode) {
/*     */       case COSINE:
/* 275 */         return cosineInterpolate(lookupValue, object, segment, context);
/*     */       case CUBIC:
/* 278 */         return cubicInterpolate(lookupValue, object, segment, context);
/*     */     } 
/* 282 */     return linearInterpolate(lookupValue, object, segment, context);
/*     */   }
/*     */   
/*     */   private <T> T linearInterpolate(Double lookupValue, Object object, int segment, Class<T> context) {
/* 287 */     if (segment < 1 || segment >= this.interpPoints.size())
/* 288 */       throw new IllegalArgumentException("segment index outside valid range"); 
/* 291 */     Double data1 = (Double)((InterpPoint)this.interpPoints.get(segment)).data.evaluate(object, Double.class);
/* 292 */     Double data0 = (Double)((InterpPoint)this.interpPoints.get(segment - 1)).data.evaluate(object, Double.class);
/* 293 */     if (this.method == Method.COLOR) {
/* 294 */       Color color1 = (Color)((InterpPoint)this.interpPoints.get(segment)).value.evaluate(object, Color.class);
/* 295 */       Color color0 = (Color)((InterpPoint)this.interpPoints.get(segment - 1)).value.evaluate(object, Color.class);
/* 296 */       int r = (int)clamp(Math.round(doLinear(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), color0.getRed(), color1.getRed())), 0.0D, 255.0D);
/* 297 */       int g = (int)clamp(Math.round(doLinear(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), color0.getGreen(), color1.getGreen())), 0.0D, 255.0D);
/* 298 */       int b = (int)clamp(Math.round(doLinear(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), color0.getBlue(), color1.getBlue())), 0.0D, 255.0D);
/* 299 */       return (T)new Color(r, g, b);
/*     */     } 
/* 302 */     Double value1 = (Double)((InterpPoint)this.interpPoints.get(segment)).value.evaluate(object, Double.class);
/* 303 */     Double value0 = (Double)((InterpPoint)this.interpPoints.get(segment - 1)).value.evaluate(object, Double.class);
/* 305 */     double interpolated = doLinear(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), value0.doubleValue(), value1.doubleValue());
/* 306 */     return (T)Converters.convert(Double.valueOf(interpolated), context);
/*     */   }
/*     */   
/*     */   private <T> T cosineInterpolate(Double lookupValue, Object object, int segment, Class<T> context) {
/* 311 */     if (segment < 1 || segment >= this.interpPoints.size())
/* 312 */       throw new IllegalArgumentException("segment index outside valid range"); 
/* 315 */     Double data1 = (Double)((InterpPoint)this.interpPoints.get(segment)).data.evaluate(object, Double.class);
/* 316 */     Double data0 = (Double)((InterpPoint)this.interpPoints.get(segment - 1)).data.evaluate(object, Double.class);
/* 317 */     if (this.method == Method.COLOR) {
/* 318 */       Color color1 = (Color)((InterpPoint)this.interpPoints.get(segment)).value.evaluate(object, Color.class);
/* 319 */       Color color0 = (Color)((InterpPoint)this.interpPoints.get(segment - 1)).value.evaluate(object, Color.class);
/* 320 */       int r = (int)clamp(Math.round(doCosine(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), color0.getRed(), color1.getRed())), 0.0D, 255.0D);
/* 321 */       int g = (int)clamp(Math.round(doCosine(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), color0.getGreen(), color1.getGreen())), 0.0D, 255.0D);
/* 322 */       int b = (int)clamp(Math.round(doCosine(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), color0.getBlue(), color1.getBlue())), 0.0D, 255.0D);
/* 323 */       return (T)new Color(r, g, b);
/*     */     } 
/* 326 */     Double value1 = (Double)((InterpPoint)this.interpPoints.get(segment)).value.evaluate(object, Double.class);
/* 327 */     Double value0 = (Double)((InterpPoint)this.interpPoints.get(segment - 1)).value.evaluate(object, Double.class);
/* 329 */     double interpolated = doCosine(lookupValue.doubleValue(), data0.doubleValue(), data1.doubleValue(), value0.doubleValue(), value1.doubleValue());
/* 330 */     return (T)Converters.convert(Double.valueOf(interpolated), context);
/*     */   }
/*     */   
/*     */   private <T> T cubicInterpolate(Double lookupValue, Object object, int segment, Class<T> context) {
/* 335 */     if (segment < 1 || segment >= this.interpPoints.size())
/* 336 */       throw new IllegalArgumentException("segment index outside valid range"); 
/* 339 */     double[] xi = new double[4];
/* 340 */     double[] yi = new double[4];
/* 341 */     List<InterpPoint> workingPoints = new ArrayList<InterpPoint>(this.interpPoints);
/* 351 */     if (segment == 1) {
/* 352 */       double data0 = ((Double)((InterpPoint)workingPoints.get(0)).data.evaluate(object, Double.class)).doubleValue();
/* 353 */       double data1 = ((Double)((InterpPoint)workingPoints.get(1)).data.evaluate(object, Double.class)).doubleValue();
/* 354 */       workingPoints.add(0, new InterpPoint((Expression)ff2.literal(2.0D * data0 - data1), ((InterpPoint)workingPoints.get(0)).value));
/* 357 */       segment++;
/* 359 */     } else if (segment == this.interpPoints.size() - 1) {
/* 360 */       double data0 = ((Double)((InterpPoint)workingPoints.get(segment)).data.evaluate(object, Double.class)).doubleValue();
/* 361 */       double data1 = ((Double)((InterpPoint)workingPoints.get(segment - 1)).data.evaluate(object, Double.class)).doubleValue();
/* 362 */       workingPoints.add(new InterpPoint((Expression)ff2.literal(2.0D * data0 - data1), ((InterpPoint)workingPoints.get(segment)).value));
/*     */     } 
/*     */     int i, k;
/* 367 */     for (i = segment - 2, k = 0; k < 4; i++, k++)
/* 368 */       xi[k] = ((Double)((InterpPoint)workingPoints.get(i)).data.evaluate(object, Double.class)).doubleValue(); 
/* 371 */     if (this.method == Method.COLOR) {
/* 372 */       Color[] ci = new Color[4];
/*     */       int j, n;
/* 373 */       for (j = segment - 2, n = 0; n < 4; j++, n++)
/* 374 */         ci[n] = (Color)((InterpPoint)workingPoints.get(j)).value.evaluate(object, Color.class); 
/* 377 */       for (j = 0; j < 4; ) {
/* 377 */         yi[j] = ci[j].getRed();
/* 377 */         j++;
/*     */       } 
/* 378 */       int r = (int)clamp(Math.round(doCubic(lookupValue.doubleValue(), xi, yi)), 0.0D, 255.0D);
/* 380 */       for (int m = 0; m < 4; ) {
/* 380 */         yi[m] = ci[m].getGreen();
/* 380 */         m++;
/*     */       } 
/* 381 */       int g = (int)clamp(Math.round(doCubic(lookupValue.doubleValue(), xi, yi)), 0.0D, 255.0D);
/* 383 */       for (int i1 = 0; i1 < 4; ) {
/* 383 */         yi[i1] = ci[i1].getBlue();
/* 383 */         i1++;
/*     */       } 
/* 384 */       int b = (int)clamp(Math.round(doCubic(lookupValue.doubleValue(), xi, yi)), 0.0D, 255.0D);
/* 386 */       return (T)new Color(r, g, b);
/*     */     } 
/* 389 */     for (i = segment - 2, k = 0; k < 4; i++, k++)
/* 390 */       yi[k] = ((Double)((InterpPoint)workingPoints.get(i)).value.evaluate(object, Double.class)).doubleValue(); 
/* 392 */     double interpolated = doCubic(lookupValue.doubleValue(), xi, yi);
/* 393 */     return (T)Converters.convert(Double.valueOf(interpolated), context);
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/* 398 */     return this.fallback;
/*     */   }
/*     */   
/*     */   private void initialize() {
/* 406 */     setMode();
/* 407 */     setMethod();
/* 408 */     int numControlParameters = (this.modeSpecified ? 1 : 0) + (this.methodSpecified ? 1 : 0);
/* 411 */     int numInterpolationParmaters = this.parameters.size() - numControlParameters - 1;
/* 412 */     if (numInterpolationParmaters < 2)
/* 413 */       throw new IllegalArgumentException("Need at least one interpolation point"); 
/* 416 */     if (numInterpolationParmaters % 2 != 0)
/* 417 */       throw new IllegalArgumentException("Missing data or value in interpolation points ?"); 
/* 420 */     List<Expression> sub = this.parameters.subList(1, this.parameters.size() - numControlParameters);
/* 421 */     this.interpPoints = new ArrayList<InterpPoint>();
/* 422 */     for (int i = 0; i < numInterpolationParmaters; i += 2)
/* 423 */       this.interpPoints.add(new InterpPoint(sub.get(i), sub.get(i + 1))); 
/* 426 */     if (this.mode == Mode.CUBIC && 
/* 427 */       this.interpPoints.size() < 3) {
/* 428 */       if (LOGGER.isLoggable(Level.INFO))
/* 429 */         LOGGER.info("Cubic interpolation requested but not enoughpoints supplied. Falling back to linear interpolation"); 
/* 432 */       this.mode = Mode.LINEAR;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMode() {
/* 438 */     boolean specified = false;
/* 441 */     int n = this.parameters.size();
/* 442 */     for (int i = 2; i >= 1 && !specified; i--) {
/* 443 */       int index = n - i;
/* 444 */       if (index > 1) {
/* 445 */         Expression expr = this.parameters.get(index);
/* 446 */         if (expr instanceof Literal && ((Literal)expr).getValue() instanceof String) {
/* 447 */           String value = (String)((Literal)expr).getValue();
/* 448 */           if (value.equalsIgnoreCase("linear")) {
/* 449 */             this.mode = Mode.LINEAR;
/* 450 */             specified = true;
/* 452 */           } else if (value.equalsIgnoreCase("cosine")) {
/* 453 */             this.mode = Mode.COSINE;
/* 454 */             specified = true;
/* 456 */           } else if (value.equalsIgnoreCase("cubic")) {
/* 457 */             this.mode = Mode.CUBIC;
/* 458 */             specified = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 465 */     if (!specified)
/* 466 */       this.mode = Mode.LINEAR; 
/* 469 */     this.modeSpecified = specified;
/*     */   }
/*     */   
/*     */   private void setMethod() {
/* 473 */     boolean specified = false;
/* 476 */     int n = this.parameters.size();
/* 477 */     for (int i = 2; i >= 1 && !specified; i--) {
/* 478 */       int index = n - i;
/* 479 */       if (index > 1) {
/* 480 */         Expression expr = this.parameters.get(index);
/* 481 */         if (expr instanceof Literal && ((Literal)expr).getValue() instanceof String) {
/* 482 */           String value = (String)((Literal)expr).getValue();
/* 483 */           if (value.equalsIgnoreCase("numeric")) {
/* 484 */             this.method = Method.NUMERIC;
/* 485 */             specified = true;
/* 487 */           } else if (value.equalsIgnoreCase("color") || value.equalsIgnoreCase("colour")) {
/* 489 */             this.method = Method.COLOR;
/* 490 */             specified = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 497 */     if (!specified)
/* 498 */       this.method = Method.NUMERIC; 
/* 501 */     this.methodSpecified = specified;
/*     */   }
/*     */   
/*     */   private int findSegment(Double lookupValue, Object object) {
/* 515 */     int segment = this.interpPoints.size();
/* 517 */     for (int i = 0; i < this.interpPoints.size(); i++) {
/* 518 */       Double data = (Double)((InterpPoint)this.interpPoints.get(i)).data.evaluate(object, Double.class);
/* 519 */       if (lookupValue.doubleValue() <= data.doubleValue()) {
/* 520 */         segment = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 525 */     return segment;
/*     */   }
/*     */   
/*     */   private double doLinear(double x, double x0, double x1, double y0, double y1) {
/* 540 */     double xspan = getSpan(x0, x1);
/* 541 */     double t = (x - x0) / xspan;
/* 542 */     return y0 + t * (y1 - y0);
/*     */   }
/*     */   
/*     */   private double doCosine(double x, double x0, double x1, double y0, double y1) {
/* 557 */     double xspan = getSpan(x0, x1);
/* 558 */     double t = (x - x0) / xspan;
/* 559 */     double tcos = 0.5D * (1.0D - Math.cos(t * Math.PI));
/* 560 */     return y0 + tcos * (y1 - y0);
/*     */   }
/*     */   
/*     */   private double doCubic(double x, double[] xi, double[] yi) {
/* 581 */     double span12 = getSpan(xi[1], xi[2]);
/* 582 */     double t = (x - xi[1]) / span12;
/* 584 */     if (t < 1.0E-8D)
/* 585 */       return yi[1]; 
/* 586 */     if (1.0D - t < 1.0E-8D)
/* 587 */       return yi[2]; 
/* 590 */     double span01 = getSpan(xi[0], xi[1]);
/* 591 */     double span23 = getSpan(xi[2], xi[3]);
/* 592 */     double t2 = t * t;
/* 593 */     double t3 = t2 * t;
/* 595 */     double m1 = 0.5D * ((yi[2] - yi[1]) / span12 + (yi[1] - yi[0]) / span01);
/* 596 */     double m2 = 0.5D * ((yi[3] - yi[2]) / span23 + (yi[2] - yi[1]) / span12);
/* 598 */     double y = (2.0D * t3 - 3.0D * t2 + 1.0D) * yi[1] + (t3 - 2.0D * t2 + t) * span12 * m1 + (-2.0D * t3 + 3.0D * t2) * yi[2] + (t3 - t2) * span12 * m2;
/* 603 */     return y;
/*     */   }
/*     */   
/*     */   private double getSpan(double x0, double x1) {
/* 618 */     double xspan = x1 - x0;
/* 620 */     if (xspan < 1.0E-8D)
/* 621 */       throw new IllegalArgumentException("Interpolation points must be in ascending order of data (lookup) values with no ties"); 
/* 625 */     return xspan;
/*     */   }
/*     */   
/*     */   private double clamp(double x, double min, double max) {
/* 636 */     return Math.max(min, Math.min(max, x));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\InterpolateFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */