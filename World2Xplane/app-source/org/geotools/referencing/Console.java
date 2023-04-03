/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.LineNumberReader;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.geotools.referencing.crs.AbstractCRS;
/*     */ import org.geotools.referencing.wkt.AbstractConsole;
/*     */ import org.geotools.referencing.wkt.Parser;
/*     */ import org.geotools.referencing.wkt.Preprocessor;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class Console extends AbstractConsole {
/* 121 */   private final Locale locale = Locale.US;
/*     */   
/* 126 */   private final NumberFormat numberFormat = NumberFormat.getNumberInstance(this.locale);
/*     */   
/*     */   private final String numberSeparator;
/*     */   
/* 138 */   private final CoordinateOperationFactory factory = ReferencingFactoryFinder.getCoordinateOperationFactory(null);
/*     */   
/*     */   private CoordinateReferenceSystem sourceCRS;
/*     */   
/*     */   private CoordinateReferenceSystem targetCRS;
/*     */   
/*     */   private DirectPosition sourcePosition;
/*     */   
/*     */   private DirectPosition targetPosition;
/*     */   
/*     */   private MathTransform transform;
/*     */   
/*     */   private double[] tolerance;
/*     */   
/*     */   private transient Exception lastError;
/*     */   
/*     */   public Console() {
/* 175 */     super((Format)new Preprocessor((Format)new Parser()));
/* 176 */     this.numberSeparator = getNumberSeparator(this.numberFormat);
/*     */   }
/*     */   
/*     */   public Console(LineNumberReader in) {
/* 185 */     super((Format)new Preprocessor((Format)new Parser()), in);
/* 186 */     this.numberSeparator = getNumberSeparator(this.numberFormat);
/*     */   }
/*     */   
/*     */   private static String getNumberSeparator(NumberFormat numberFormat) {
/* 194 */     numberFormat.setGroupingUsed(false);
/* 195 */     numberFormat.setMinimumFractionDigits(6);
/* 196 */     numberFormat.setMaximumFractionDigits(6);
/* 197 */     if (numberFormat instanceof DecimalFormat) {
/* 198 */       char decimalSeparator = ((DecimalFormat)numberFormat).getDecimalFormatSymbols().getDecimalSeparator();
/* 200 */       if (decimalSeparator == ',')
/* 201 */         return ";"; 
/*     */     } 
/* 204 */     return ",";
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*     */     LineNumberReader input;
/*     */     Console console;
/* 225 */     Arguments arguments = new Arguments(args);
/* 226 */     String load = arguments.getOptionalString("-load");
/* 227 */     String file = arguments.getOptionalString("-file");
/* 228 */     args = arguments.getRemainingArguments(0);
/* 229 */     Locale.setDefault(arguments.locale);
/* 238 */     if (file == null) {
/* 239 */       input = null;
/* 240 */       console = new Console();
/*     */     } else {
/*     */       try {
/* 242 */         input = new LineNumberReader(new FileReader(file));
/* 243 */         console = new Console(input);
/* 244 */         console.setPrompt(null);
/* 245 */       } catch (IOException exception) {
/* 246 */         System.err.println(exception.getLocalizedMessage());
/*     */         return;
/*     */       } 
/*     */     } 
/* 253 */     if (load != null)
/*     */       try {
/* 254 */         LineNumberReader in = new LineNumberReader(new FileReader(load));
/*     */         try {
/* 256 */           console.loadDefinitions(in);
/* 257 */         } catch (ParseException exception) {
/* 258 */           console.reportError(exception);
/* 259 */           in.close();
/*     */           return;
/*     */         } 
/* 262 */         in.close();
/* 263 */       } catch (IOException exception) {
/* 264 */         console.reportError(exception);
/*     */         return;
/*     */       }  
/* 270 */     console.run();
/* 271 */     if (input != null)
/*     */       try {
/* 272 */         input.close();
/* 273 */       } catch (IOException exception) {
/* 274 */         console.reportError(exception);
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void execute(String instruction) throws IOException, ParseException, FactoryException, TransformException {
/* 291 */     String value = null;
/* 292 */     int i = instruction.indexOf('=');
/* 293 */     if (i >= 0) {
/* 294 */       value = instruction.substring(i + 1).trim();
/* 295 */       instruction = instruction.substring(0, i).trim();
/*     */     } 
/* 297 */     StringTokenizer keywords = new StringTokenizer(instruction);
/* 298 */     if (keywords.hasMoreTokens()) {
/* 299 */       String key0 = keywords.nextToken();
/* 300 */       if (!keywords.hasMoreTokens()) {
/* 304 */         if (key0.equalsIgnoreCase("exit")) {
/* 305 */           if (value != null)
/* 306 */             throw unexpectedArgument("exit"); 
/* 308 */           stop();
/*     */           return;
/*     */         } 
/* 314 */         if (key0.equalsIgnoreCase("stacktrace")) {
/* 315 */           if (value != null)
/* 316 */             throw unexpectedArgument("stacktrace"); 
/* 318 */           if (this.lastError != null)
/* 319 */             this.lastError.printStackTrace(this.err); 
/*     */           return;
/*     */         } 
/* 326 */         if (key0.equalsIgnoreCase("transform")) {
/* 327 */           this.transform = (MathTransform)parseObject(value, MathTransform.class);
/* 328 */           this.sourceCRS = null;
/* 329 */           this.targetCRS = null;
/*     */           return;
/*     */         } 
/*     */       } else {
/* 333 */         String key1 = keywords.nextToken();
/* 334 */         if (!keywords.hasMoreTokens()) {
/* 338 */           if (key0.equalsIgnoreCase("print")) {
/* 339 */             if (value != null)
/* 340 */               throw unexpectedArgument("print"); 
/* 342 */             if (key1.equalsIgnoreCase("set")) {
/* 343 */               printDefinitions();
/*     */               return;
/*     */             } 
/* 346 */             if (key1.equalsIgnoreCase("crs")) {
/* 347 */               printCRS();
/*     */               return;
/*     */             } 
/* 350 */             if (key1.equalsIgnoreCase("pts")) {
/* 351 */               printPts();
/*     */               return;
/*     */             } 
/*     */           } 
/* 358 */           if (key0.equalsIgnoreCase("set")) {
/* 359 */             addDefinition(key1, value);
/*     */             return;
/*     */           } 
/* 365 */           if (key0.equalsIgnoreCase("test") && 
/* 366 */             key1.equalsIgnoreCase("tolerance")) {
/* 367 */             this.tolerance = parseVector(value);
/*     */             return;
/*     */           } 
/* 374 */           if (key1.equalsIgnoreCase("crs")) {
/* 375 */             if (key0.equalsIgnoreCase("source")) {
/* 376 */               this.sourceCRS = (CoordinateReferenceSystem)parseObject(value, CoordinateReferenceSystem.class);
/* 378 */               this.transform = null;
/*     */               return;
/*     */             } 
/* 381 */             if (key0.equalsIgnoreCase("target")) {
/* 382 */               this.targetCRS = (CoordinateReferenceSystem)parseObject(value, CoordinateReferenceSystem.class);
/* 384 */               this.transform = null;
/*     */               return;
/*     */             } 
/*     */           } 
/* 391 */           if (key1.equalsIgnoreCase("pt")) {
/* 392 */             if (key0.equalsIgnoreCase("source")) {
/* 393 */               this.sourcePosition = (DirectPosition)new GeneralDirectPosition(parseVector(value));
/*     */               return;
/*     */             } 
/* 396 */             if (key0.equalsIgnoreCase("target")) {
/* 397 */               this.targetPosition = (DirectPosition)new GeneralDirectPosition(parseVector(value));
/* 398 */               if (this.tolerance != null && this.sourcePosition != null) {
/* 399 */                 update();
/* 400 */                 if (this.transform != null)
/* 401 */                   test(); 
/*     */               } 
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 410 */     throw new ParseException(Errors.format(68, instruction), 0);
/*     */   }
/*     */   
/*     */   private void printCRS() throws FactoryException, IOException {
/* 417 */     Locale locale = null;
/* 418 */     Vocabulary resources = Vocabulary.getResources(locale);
/* 419 */     TableWriter table = new TableWriter(this.out, " │ ");
/* 420 */     table.setMultiLinesCells(true);
/* 421 */     char separator = '─';
/* 422 */     if (this.sourceCRS != null || this.targetCRS != null) {
/* 423 */       table.writeHorizontalSeparator();
/* 424 */       table.write(resources.getString(200));
/* 425 */       table.nextColumn();
/* 426 */       table.write(resources.getString(211));
/* 427 */       table.nextLine();
/* 428 */       table.writeHorizontalSeparator();
/* 429 */       if (this.sourceCRS != null)
/* 430 */         table.write(this.parser.format(this.sourceCRS)); 
/* 432 */       table.nextColumn();
/* 433 */       if (this.targetCRS != null)
/* 434 */         table.write(this.parser.format(this.targetCRS)); 
/* 436 */       table.nextLine();
/* 437 */       separator = '═';
/*     */     } 
/* 442 */     update();
/* 443 */     if (this.transform != null) {
/* 444 */       table.nextLine(separator);
/* 445 */       table.write(resources.getString(135));
/* 446 */       table.nextColumn();
/* 447 */       table.write(resources.getString(114));
/* 448 */       table.nextLine();
/* 449 */       table.writeHorizontalSeparator();
/* 450 */       table.write(this.parser.format(this.transform));
/* 451 */       table.nextColumn();
/*     */       try {
/* 453 */         table.write(this.parser.format(this.transform.inverse()));
/* 454 */       } catch (NoninvertibleTransformException exception) {
/* 455 */         table.write(exception.getLocalizedMessage());
/*     */       } 
/* 457 */       table.nextLine();
/*     */     } 
/* 459 */     table.writeHorizontalSeparator();
/* 460 */     table.flush();
/*     */   }
/*     */   
/*     */   private void printPts() throws FactoryException, TransformException, IOException {
/*     */     GeneralDirectPosition generalDirectPosition;
/* 471 */     update();
/* 472 */     DirectPosition transformedSource = null;
/* 473 */     DirectPosition transformedTarget = null;
/* 474 */     String targetException = null;
/* 475 */     if (this.transform != null) {
/* 476 */       if (this.sourcePosition != null)
/* 477 */         transformedSource = this.transform.transform(this.sourcePosition, null); 
/* 479 */       if (this.targetPosition != null)
/*     */         try {
/* 480 */           transformedTarget = this.transform.inverse().transform(this.targetPosition, null);
/* 481 */         } catch (NoninvertibleTransformException exception) {
/* 482 */           targetException = exception.getLocalizedMessage();
/* 483 */           if (this.sourcePosition != null) {
/* 485 */             GeneralDirectPosition p = new GeneralDirectPosition(this.sourcePosition.getDimension());
/* 486 */             Arrays.fill(p.ordinates, Double.NaN);
/*     */           } 
/*     */         }  
/*     */     } 
/* 490 */     Locale locale = null;
/* 491 */     Vocabulary resources = Vocabulary.getResources(locale);
/* 492 */     TableWriter table = new TableWriter(this.out, 0);
/* 493 */     table.setMultiLinesCells(true);
/* 494 */     table.writeHorizontalSeparator();
/* 495 */     table.setAlignment(2);
/* 496 */     if (this.sourcePosition != null) {
/* 497 */       table.write(resources.getLabel(201));
/* 498 */       print(this.sourcePosition, table);
/* 499 */       print(transformedSource, table);
/* 500 */       table.nextLine();
/*     */     } 
/* 502 */     if (this.targetPosition != null) {
/* 503 */       table.write(resources.getLabel(212));
/* 504 */       print((DirectPosition)generalDirectPosition, table);
/* 505 */       print(this.targetPosition, table);
/* 506 */       table.nextLine();
/*     */     } 
/* 508 */     if (this.sourceCRS != null && this.targetCRS != null) {
/* 509 */       table.write(resources.getLabel(49));
/* 510 */       printDistance(this.sourceCRS, this.sourcePosition, (DirectPosition)generalDirectPosition, table);
/* 511 */       printDistance(this.targetCRS, this.targetPosition, transformedSource, table);
/* 512 */       table.nextLine();
/*     */     } 
/* 514 */     table.writeHorizontalSeparator();
/* 515 */     table.flush();
/* 516 */     if (targetException != null) {
/* 517 */       this.out.write(targetException);
/* 518 */       this.out.write(this.lineSeparator);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void print(DirectPosition point, TableWriter table) throws IOException {
/* 530 */     if (point != null) {
/* 531 */       table.nextColumn();
/* 532 */       table.write("  (");
/* 533 */       double[] coords = point.getCoordinate();
/* 534 */       for (int i = 0; i < coords.length; i++) {
/* 535 */         if (i != 0)
/* 536 */           table.write(", "); 
/* 538 */         table.nextColumn();
/* 539 */         table.write(this.numberFormat.format(coords[i]));
/*     */       } 
/* 541 */       table.write(41);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void printDistance(CoordinateReferenceSystem crs, DirectPosition position1, DirectPosition position2, TableWriter table) throws IOException {
/* 554 */     if (position1 == null)
/*     */       return; 
/* 558 */     for (int i = crs.getCoordinateSystem().getDimension(); --i >= 0;)
/* 559 */       table.nextColumn(); 
/* 561 */     if (position2 != null && 
/* 562 */       crs instanceof AbstractCRS)
/*     */       try {
/* 564 */         Measure distance = ((AbstractCRS)crs).distance(position1.getCoordinate(), position2.getCoordinate());
/* 566 */         table.setAlignment(2);
/* 567 */         table.write(this.numberFormat.format(distance.doubleValue()));
/* 568 */         table.write("  ");
/* 569 */         table.nextColumn();
/* 570 */         table.write(String.valueOf(distance.getUnit()));
/* 571 */         table.setAlignment(0);
/*     */         return;
/* 573 */       } catch (UnsupportedOperationException ignore) {} 
/* 580 */     table.nextColumn();
/*     */   }
/*     */   
/*     */   protected void test() throws TransformException, MismatchedDimensionException {
/* 604 */     DirectPosition transformedSource = this.transform.transform(this.sourcePosition, null);
/* 605 */     int sourceDim = transformedSource.getDimension();
/* 606 */     int targetDim = this.targetPosition.getDimension();
/* 607 */     if (sourceDim != targetDim)
/* 608 */       throw new MismatchedDimensionException(Errors.format(93, Integer.valueOf(sourceDim), Integer.valueOf(targetDim))); 
/* 611 */     for (int i = 0; i < sourceDim; i++) {
/* 613 */       if (Math.abs(transformedSource.getOrdinate(i) - this.targetPosition.getOrdinate(i)) > this.tolerance[Math.min(i, this.tolerance.length - 1)])
/* 617 */         throw new TransformException("Expected " + this.targetPosition + " but got " + transformedSource); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String removeDelimitors(String text, char start, char end) {
/* 631 */     text = text.trim();
/* 632 */     int endPos = text.length() - 1;
/* 633 */     if (endPos >= 1 && 
/* 634 */       text.charAt(0) == start && text.charAt(endPos) == end)
/* 635 */       text = text.substring(1, endPos).trim(); 
/* 638 */     return text;
/*     */   }
/*     */   
/*     */   private double[] parseVector(String text) throws ParseException {
/* 653 */     text = removeDelimitors(text, '(', ')');
/* 654 */     StringTokenizer st = new StringTokenizer(text, this.numberSeparator);
/* 655 */     double[] values = new double[st.countTokens()];
/* 656 */     for (int i = 0; i < values.length; i++) {
/* 659 */       String token = st.nextToken().trim().toUpperCase(this.locale);
/* 660 */       ParsePosition position = new ParsePosition(0);
/* 661 */       Number result = this.numberFormat.parse(token, position);
/* 662 */       if (position.getIndex() != token.length())
/* 663 */         throw new ParseException(Errors.format(191, token), position.getErrorIndex()); 
/* 666 */       values[i] = result.doubleValue();
/*     */     } 
/* 668 */     return values;
/*     */   }
/*     */   
/*     */   private void update() throws FactoryException {
/* 676 */     if (this.transform == null && this.sourceCRS != null && this.targetCRS != null)
/* 677 */       this.transform = this.factory.createOperation(this.sourceCRS, this.targetCRS).getMathTransform(); 
/*     */   }
/*     */   
/*     */   private static ParseException unexpectedArgument(String instruction) {
/* 688 */     return new ParseException(Errors.format(172, instruction), 0);
/*     */   }
/*     */   
/*     */   protected void reportError(Exception exception) {
/* 699 */     super.reportError(exception);
/* 700 */     this.lastError = exception;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\Console.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */