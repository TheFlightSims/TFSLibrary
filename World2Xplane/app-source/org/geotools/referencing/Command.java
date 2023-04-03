/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.referencing.datum.BursaWolfParameters;
/*     */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*     */ import org.geotools.referencing.factory.FactoryDependencies;
/*     */ import org.geotools.referencing.wkt.Parser;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.CRSUtilities;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.Factory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ final class Command {
/*  67 */   private static final Hints HINTS = null;
/*     */   
/*     */   private final AuthorityFactory factory;
/*     */   
/*     */   private final Parser formatter;
/*     */   
/*     */   private Command(String authority) {
/*  83 */     this.factory = (authority == null) ? (AuthorityFactory)CRS.getAuthorityFactory(false) : (AuthorityFactory)ReferencingFactoryFinder.getCRSAuthorityFactory(authority, HINTS);
/*  85 */     this.formatter = new Parser();
/*     */   }
/*     */   
/*     */   private static char[] getSeparator() {
/*  92 */     char[] separator = new char[79];
/*  93 */     Arrays.fill(separator, '─');
/*  94 */     return separator;
/*     */   }
/*     */   
/*     */   private static void help(PrintWriter out) {
/* 101 */     out.println("Display informations about CRS identified by authority codes.");
/* 102 */     out.println("Usage: java org.geotools.referencing.CRS [options] [codes]");
/* 103 */     out.println("Options:");
/* 104 */     out.println(" -authority=ARG : Uses the specified authority factory (default to all).");
/* 105 */     out.println(" -bursawolfs    : Lists Bursa-Wolf parameters for the specified CRS.");
/* 106 */     out.println(" -codes         : Lists all available CRS codes with their description.");
/* 107 */     out.println(" -colors        : Enable syntax coloring on ANSI X3.64 compatible terminal.");
/* 108 */     out.println(" -dependencies  : Lists authority factory dependencies as a tree.");
/* 109 */     out.println(" -factories     : Lists all availables CRS authority factories.");
/* 110 */     out.println(" -forcexy       : Force \"longitude first\" axis order.");
/* 111 */     out.println(" -help          : Prints this message.");
/* 112 */     out.println(" -locale=ARG    : Formats texts in the specified locale.");
/* 113 */     out.println(" -operations    : Prints all available coordinate operations between a pair of CRS.");
/* 114 */     out.println(" -transform     : Prints the preferred math transform between a pair of CRS.");
/*     */   }
/*     */   
/*     */   private void list(PrintWriter out, String[] args) throws FactoryException {
/* 121 */     char[] separator = null;
/* 122 */     for (int i = 0; i < args.length; i++) {
/* 123 */       if (separator == null) {
/* 124 */         separator = getSeparator();
/*     */       } else {
/* 126 */         out.println(separator);
/*     */       } 
/* 128 */       out.println(this.formatter.format(this.factory.createObject(args[i])));
/* 129 */       String warning = this.formatter.getWarning();
/* 130 */       if (warning != null) {
/* 131 */         out.println();
/* 132 */         out.print(Vocabulary.format(242));
/* 133 */         out.print(": ");
/* 134 */         out.println(warning);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void codes(PrintWriter out) throws FactoryException {
/* 143 */     Set<String> codes = this.factory.getAuthorityCodes(CoordinateReferenceSystem.class);
/* 144 */     TableWriter table = new TableWriter(out);
/* 145 */     table.writeHorizontalSeparator();
/* 146 */     table.write(Vocabulary.format(21));
/* 147 */     table.nextColumn();
/* 148 */     table.write(Vocabulary.format(46));
/* 149 */     table.writeHorizontalSeparator();
/* 150 */     for (String code : codes) {
/* 151 */       table.write(code);
/* 152 */       table.nextColumn();
/*     */       try {
/* 154 */         InternationalString description = this.factory.getDescriptionText(code);
/* 155 */         if (description != null)
/* 156 */           table.write(description.toString()); 
/* 158 */       } catch (NoSuchAuthorityCodeException e) {
/* 159 */         table.write(e.getLocalizedMessage());
/*     */       } 
/* 161 */       table.nextLine();
/*     */     } 
/* 163 */     table.writeHorizontalSeparator();
/*     */     try {
/* 165 */       table.flush();
/* 166 */     } catch (IOException e) {
/* 168 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void factories(PrintWriter out) {
/* 176 */     Set<Citation> done = new HashSet<Citation>();
/* 177 */     TableWriter table = new TableWriter(out, " │ ");
/* 178 */     TableWriter notes = new TableWriter(out, " ");
/* 179 */     int noteCount = 0;
/* 180 */     notes.setMultiLinesCells(true);
/* 181 */     table.setMultiLinesCells(true);
/* 182 */     table.writeHorizontalSeparator();
/* 183 */     table.write(Vocabulary.format(6));
/* 184 */     table.nextColumn();
/* 185 */     table.write(Vocabulary.format(46));
/* 186 */     table.nextColumn();
/* 187 */     table.write(Vocabulary.format(151));
/* 188 */     table.writeHorizontalSeparator();
/* 189 */     for (CRSAuthorityFactory cRSAuthorityFactory : ReferencingFactoryFinder.getCRSAuthorityFactories(HINTS)) {
/* 190 */       Citation authority = cRSAuthorityFactory.getAuthority();
/* 191 */       Iterator<? extends Identifier> identifiers = authority.getIdentifiers().iterator();
/* 192 */       if (!identifiers.hasNext())
/*     */         continue; 
/* 196 */       if (!done.add(authority))
/*     */         continue; 
/* 200 */       table.write(((Identifier)identifiers.next()).getCode());
/* 201 */       table.nextColumn();
/* 202 */       table.write(authority.getTitle().toString().trim());
/* 203 */       if (cRSAuthorityFactory instanceof AbstractAuthorityFactory) {
/*     */         String str;
/*     */         try {
/* 206 */           str = ((AbstractAuthorityFactory)cRSAuthorityFactory).getBackingStoreDescription();
/* 207 */         } catch (FactoryException e) {
/* 208 */           str = e.getLocalizedMessage();
/*     */         } 
/* 210 */         if (str != null) {
/* 211 */           String n = String.valueOf(++noteCount);
/* 212 */           table.nextColumn();
/* 213 */           table.write(40);
/* 213 */           table.write(n);
/* 213 */           table.write(41);
/* 214 */           notes.write(40);
/* 214 */           notes.write(n);
/* 214 */           notes.write(41);
/* 215 */           notes.nextColumn();
/* 216 */           notes.write(str.trim());
/* 217 */           notes.nextLine();
/*     */         } 
/*     */       } 
/* 220 */       table.nextLine();
/*     */     } 
/* 222 */     table.writeHorizontalSeparator();
/*     */     try {
/* 224 */       table.flush();
/* 225 */       notes.flush();
/* 226 */     } catch (IOException e) {
/* 228 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void bursaWolfs(PrintWriter out, String[] args) throws FactoryException {
/* 236 */     NumberFormat nf = NumberFormat.getNumberInstance();
/* 237 */     nf.setMinimumFractionDigits(3);
/* 238 */     nf.setMaximumFractionDigits(3);
/* 239 */     TableWriter table = new TableWriter(out);
/* 240 */     table.writeHorizontalSeparator();
/* 241 */     String[] titles = { Vocabulary.format(210), "dx", "dy", "dz", "ex", "ey", "ez", "ppm" };
/*     */     int i;
/* 245 */     for (i = 0; i < titles.length; i++) {
/* 246 */       table.write(titles[i]);
/* 247 */       table.nextColumn();
/* 248 */       table.setAlignment(1);
/*     */     } 
/* 250 */     table.writeHorizontalSeparator();
/* 251 */     for (i = 0; i < args.length; i++) {
/*     */       Datum datum;
/* 252 */       IdentifiedObject object = this.factory.createObject(args[i]);
/* 253 */       if (object instanceof CoordinateReferenceSystem)
/* 254 */         datum = CRSUtilities.getDatum((CoordinateReferenceSystem)object); 
/* 256 */       if (datum instanceof DefaultGeodeticDatum) {
/* 257 */         BursaWolfParameters[] params = ((DefaultGeodeticDatum)datum).getBursaWolfParameters();
/* 259 */         for (int j = 0; j < params.length; j++) {
/* 260 */           BursaWolfParameters p = params[j];
/* 261 */           table.setAlignment(0);
/* 262 */           table.write(p.targetDatum.getName().getCode());
/* 263 */           table.nextColumn();
/* 264 */           table.setAlignment(2);
/* 266 */           for (int k = 0; k < 7; k++) {
/*     */             double v;
/* 267 */             switch (k) {
/*     */               case 0:
/* 268 */                 v = p.dx;
/*     */                 break;
/*     */               case 1:
/* 269 */                 v = p.dy;
/*     */                 break;
/*     */               case 2:
/* 270 */                 v = p.dz;
/*     */                 break;
/*     */               case 3:
/* 271 */                 v = p.ex;
/*     */                 break;
/*     */               case 4:
/* 272 */                 v = p.ey;
/*     */                 break;
/*     */               case 5:
/* 273 */                 v = p.ez;
/*     */                 break;
/*     */               case 6:
/* 274 */                 v = p.ppm;
/*     */                 break;
/*     */               default:
/* 275 */                 throw new AssertionError(k);
/*     */             } 
/* 277 */             table.write(nf.format(v));
/* 278 */             table.nextColumn();
/*     */           } 
/* 280 */           table.nextLine();
/*     */         } 
/* 282 */         table.writeHorizontalSeparator();
/*     */       } 
/*     */     } 
/*     */     try {
/* 286 */       table.flush();
/* 287 */     } catch (IOException e) {
/* 289 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void operations(PrintWriter out, String[] args) throws FactoryException {
/* 297 */     if (!(this.factory instanceof CoordinateOperationAuthorityFactory))
/*     */       return; 
/* 300 */     CoordinateOperationAuthorityFactory factory = (CoordinateOperationAuthorityFactory)this.factory;
/* 302 */     char[] separator = null;
/* 303 */     for (int i = 0; i < args.length; i++) {
/* 304 */       for (int j = i + 1; j < args.length; j++) {
/* 306 */         Set<CoordinateOperation> op = factory.createFromCoordinateReferenceSystemCodes(args[i], args[j]);
/* 307 */         for (CoordinateOperation operation : op) {
/* 308 */           if (separator == null) {
/* 309 */             separator = getSeparator();
/*     */           } else {
/* 311 */             out.println(separator);
/*     */           } 
/* 313 */           out.println(this.formatter.format(operation));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void transform(PrintWriter out, String[] args) throws FactoryException {
/* 323 */     if (!(this.factory instanceof CRSAuthorityFactory))
/*     */       return; 
/* 326 */     CRSAuthorityFactory factory = (CRSAuthorityFactory)this.factory;
/* 327 */     CoordinateOperationFactory opFactory = ReferencingFactoryFinder.getCoordinateOperationFactory(HINTS);
/* 329 */     char[] separator = null;
/* 330 */     for (int i = 0; i < args.length; i++) {
/* 331 */       CoordinateReferenceSystem crs1 = factory.createCoordinateReferenceSystem(args[i]);
/* 332 */       for (int j = i + 1; j < args.length; j++) {
/*     */         CoordinateOperation op;
/* 333 */         CoordinateReferenceSystem crs2 = factory.createCoordinateReferenceSystem(args[j]);
/*     */         try {
/* 336 */           op = opFactory.createOperation(crs1, crs2);
/* 337 */         } catch (OperationNotFoundException exception) {
/* 338 */           out.println(exception.getLocalizedMessage());
/*     */         } 
/* 341 */         if (separator == null) {
/* 342 */           separator = getSeparator();
/*     */         } else {
/* 344 */           out.println(separator);
/*     */         } 
/* 346 */         out.println(this.formatter.format(op.getMathTransform()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void printAuthorityFactoryDependencies(PrintWriter out, boolean colors) {
/* 355 */     FactoryDependencies printer = new FactoryDependencies((Factory)CRS.getAuthorityFactory(false));
/* 356 */     printer.setAttributeEnabled(true);
/* 357 */     printer.setColorEnabled(colors);
/* 358 */     printer.print(out);
/* 359 */     out.flush();
/*     */   }
/*     */   
/*     */   private void dispose() throws FactoryException {
/* 366 */     if (this.factory instanceof AbstractAuthorityFactory)
/* 367 */       ((AbstractAuthorityFactory)this.factory).dispose(); 
/*     */   }
/*     */   
/*     */   public static void execute(String[] args) {
/* 375 */     Arguments arguments = new Arguments(args);
/* 376 */     PrintWriter out = arguments.out;
/* 377 */     Locale.setDefault(arguments.locale);
/* 378 */     if (arguments.getFlag("-forcexy"))
/* 379 */       Hints.putSystemDefault((RenderingHints.Key)Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE); 
/* 381 */     if (arguments.getFlag("-help")) {
/* 382 */       args = arguments.getRemainingArguments(0);
/* 383 */       help(out);
/*     */       return;
/*     */     } 
/* 386 */     if (arguments.getFlag("-factories")) {
/* 387 */       args = arguments.getRemainingArguments(0);
/* 388 */       factories(out);
/*     */       return;
/*     */     } 
/* 391 */     if (arguments.getFlag("-dependencies")) {
/* 392 */       boolean colors = arguments.getFlag("-colors");
/* 393 */       args = arguments.getRemainingArguments(0);
/* 394 */       printAuthorityFactoryDependencies(out, colors);
/*     */       return;
/*     */     } 
/* 397 */     String authority = arguments.getOptionalString("-authority");
/* 398 */     Command command = new Command(authority);
/* 399 */     command.formatter.setColorEnabled(arguments.getFlag("-colors"));
/*     */     try {
/* 401 */       if (arguments.getFlag("-codes")) {
/* 402 */         args = arguments.getRemainingArguments(0);
/* 403 */         command.codes(out);
/* 404 */       } else if (arguments.getFlag("-bursawolfs")) {
/* 405 */         args = arguments.getRemainingArguments(2147483647, '-');
/* 406 */         command.bursaWolfs(out, args);
/* 407 */       } else if (arguments.getFlag("-operations")) {
/* 408 */         args = arguments.getRemainingArguments(2, '-');
/* 409 */         command.operations(out, args);
/* 410 */       } else if (arguments.getFlag("-transform")) {
/* 411 */         args = arguments.getRemainingArguments(2, '-');
/* 412 */         command.transform(out, args);
/*     */       } else {
/* 414 */         args = arguments.getRemainingArguments(2147483647, '-');
/* 415 */         command.list(out, args);
/*     */       } 
/* 417 */       out.flush();
/* 418 */       command.dispose();
/* 419 */     } catch (FactoryException exception) {
/* 420 */       out.flush();
/* 421 */       arguments.err.println(exception.getLocalizedMessage());
/* 422 */     } catch (Exception exception) {
/* 423 */       out.flush();
/* 424 */       exception.printStackTrace(arguments.err);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */