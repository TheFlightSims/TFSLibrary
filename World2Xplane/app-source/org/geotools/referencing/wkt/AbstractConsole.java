/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.LineNumberReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ 
/*     */ public abstract class AbstractConsole implements Runnable {
/*     */   protected final LineNumberReader in;
/*     */   
/*     */   protected final Writer out;
/*     */   
/*     */   protected final PrintWriter err;
/*     */   
/*     */   protected final String lineSeparator;
/*     */   
/*     */   protected final Format parser;
/*     */   
/*  77 */   private String prompt = "crs>";
/*     */   
/*     */   private transient String line;
/*     */   
/*     */   private volatile transient boolean stop;
/*     */   
/*     */   public AbstractConsole(Format parser) {
/*  97 */     this(parser, new LineNumberReader(Arguments.getReader(System.in)));
/*     */   }
/*     */   
/*     */   public AbstractConsole(Format parser, LineNumberReader in) {
/* 109 */     this(parser, in, Arguments.getWriter(System.out), new PrintWriter(Arguments.getWriter(System.err), true), System.getProperty("line.separator", "\n"));
/*     */   }
/*     */   
/*     */   public AbstractConsole(Format parser, LineNumberReader in, Writer out, PrintWriter err, String lineSeparator) {
/* 129 */     this.parser = parser;
/* 130 */     this.in = in;
/* 131 */     this.out = out;
/* 132 */     this.err = err;
/* 133 */     this.lineSeparator = lineSeparator;
/*     */   }
/*     */   
/*     */   public Object parseObject(String text, Class type) throws ParseException, FactoryException {
/* 151 */     if (this.parser instanceof Preprocessor) {
/* 152 */       Preprocessor parser = (Preprocessor)this.parser;
/* 153 */       parser.offset = (this.line != null) ? Math.max(0, this.line.indexOf(text)) : 0;
/* 154 */       return parser.parseObject(text, type);
/*     */     } 
/* 156 */     return this.parser.parseObject(text);
/*     */   }
/*     */   
/*     */   public void addDefinition(String name, String value) throws ParseException {
/* 171 */     if (this.parser instanceof Preprocessor)
/* 172 */       ((Preprocessor)this.parser).addDefinition(name, value); 
/*     */   }
/*     */   
/*     */   public void loadDefinitions(LineNumberReader in) throws IOException, ParseException {
/* 188 */     while ((this.line = readLine(in)) != null) {
/* 189 */       String name = this.line, value = null;
/* 190 */       int i = this.line.indexOf('=');
/* 191 */       if (i >= 0) {
/* 192 */         name = this.line.substring(0, i).trim();
/* 193 */         value = this.line.substring(i + 1).trim();
/*     */       } 
/* 195 */       addDefinition(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printDefinitions() throws IOException {
/* 207 */     if (this.parser instanceof Preprocessor)
/* 208 */       ((Preprocessor)this.parser).printDefinitions(this.out); 
/*     */   }
/*     */   
/*     */   public String getPrompt() {
/* 216 */     return this.prompt;
/*     */   }
/*     */   
/*     */   public void setPrompt(String prompt) {
/* 223 */     this.prompt = prompt;
/*     */   }
/*     */   
/*     */   private static String readLine(LineNumberReader in) throws IOException {
/*     */     String line;
/* 237 */     while ((line = in.readLine()) != null) {
/* 238 */       line = line.trim();
/* 239 */       if (line.length() == 0)
/*     */         continue; 
/* 243 */       if (line.startsWith("//"));
/*     */     } 
/* 249 */     return line;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     try {
/* 261 */       while (!this.stop) {
/* 262 */         if (this.prompt != null)
/* 263 */           this.out.write(this.prompt); 
/* 265 */         this.out.flush();
/* 266 */         this.line = readLine(this.in);
/* 267 */         if (this.line == null)
/*     */           break; 
/*     */         try {
/* 271 */           execute(this.line);
/* 272 */         } catch (Exception exception) {
/* 273 */           reportError(exception);
/*     */         } 
/*     */       } 
/* 276 */       this.out.flush();
/* 277 */       this.stop = false;
/* 278 */     } catch (IOException exception) {
/* 279 */       reportError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void executeAll() throws Exception {
/* 289 */     while ((this.line = readLine(this.in)) != null) {
/*     */       try {
/* 291 */         execute(this.line);
/* 292 */         this.out.flush();
/* 293 */       } catch (Exception e) {
/* 294 */         reportError(e);
/* 295 */         throw e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void execute(String paramString) throws Exception;
/*     */   
/*     */   public void stop() {
/* 314 */     this.stop = true;
/*     */   }
/*     */   
/*     */   protected void reportError(Exception exception) {
/*     */     try {
/* 327 */       this.out.flush();
/* 328 */     } catch (IOException ignore) {
/* 329 */       Logging.unexpectedException(AbstractConsole.class, "reportError", ignore);
/*     */     } 
/* 331 */     this.err.print(Classes.getShortClassName(exception));
/* 332 */     this.err.print(" at line ");
/* 333 */     this.err.print(this.in.getLineNumber());
/* 334 */     Throwable cause = exception;
/*     */     while (true) {
/* 336 */       String message = cause.getLocalizedMessage();
/* 337 */       if (message != null) {
/* 338 */         this.err.print(": ");
/* 339 */         this.err.print(message);
/*     */       } 
/* 341 */       this.err.println();
/* 342 */       cause = cause.getCause();
/* 343 */       if (cause == null)
/*     */         break; 
/* 346 */       this.err.print("Caused by ");
/* 347 */       this.err.print(Classes.getShortClassName(cause));
/*     */     } 
/* 349 */     this.err.println("Type 'stacktrace' for stack trace information.");
/* 350 */     if (this.line != null && exception instanceof ParseException)
/* 351 */       AbstractParser.reportError(this.err, this.line, ((ParseException)exception).getErrorOffset()); 
/* 353 */     this.err.println();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\AbstractConsole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */