/*     */ package org.jfree.base.modules;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public abstract class AbstractModule extends DefaultModuleInfo implements Module {
/*     */   private ModuleInfo[] requiredModules;
/*     */   
/*     */   private ModuleInfo[] optionalModules;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private String producer;
/*     */   
/*     */   private String subsystem;
/*     */   
/*     */   private static class ReaderHelper {
/*     */     private String buffer;
/*     */     
/*     */     private final BufferedReader reader;
/*     */     
/*     */     public ReaderHelper(BufferedReader reader) {
/* 119 */       this.reader = reader;
/*     */     }
/*     */     
/*     */     public boolean hasNext() throws IOException {
/* 131 */       if (this.buffer == null)
/* 133 */         this.buffer = readLine(); 
/* 135 */       return (this.buffer != null);
/*     */     }
/*     */     
/*     */     public String next() {
/* 145 */       String line = this.buffer;
/* 146 */       this.buffer = null;
/* 147 */       return line;
/*     */     }
/*     */     
/*     */     public void pushBack(String line) {
/* 158 */       this.buffer = line;
/*     */     }
/*     */     
/*     */     protected String readLine() throws IOException {
/* 169 */       String line = this.reader.readLine();
/* 170 */       while (line != null && (line.length() == 0 || line.startsWith("#")))
/* 173 */         line = this.reader.readLine(); 
/* 175 */       return line;
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 185 */       this.reader.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public AbstractModule() {
/* 208 */     setModuleClass(getClass().getName());
/*     */   }
/*     */   
/*     */   protected void loadModuleInfo() throws ModuleInitializeException {
/* 219 */     InputStream in = ObjectUtilities.getResourceRelativeAsStream("module.properties", getClass());
/* 221 */     if (in == null)
/* 223 */       throw new ModuleInitializeException("File 'module.properties' not found in module package."); 
/* 226 */     loadModuleInfo(in);
/*     */   }
/*     */   
/*     */   protected void loadModuleInfo(InputStream in) throws ModuleInitializeException {
/*     */     try {
/* 241 */       if (in == null)
/* 243 */         throw new NullPointerException("Given InputStream is null."); 
/* 246 */       ReaderHelper rh = new ReaderHelper(new BufferedReader(new InputStreamReader(in, "ISO-8859-1")));
/* 249 */       ArrayList optionalModules = new ArrayList();
/* 250 */       ArrayList dependendModules = new ArrayList();
/* 252 */       while (rh.hasNext()) {
/* 254 */         String lastLineRead = rh.next();
/* 255 */         if (lastLineRead.startsWith("module-info:")) {
/* 257 */           readModuleInfo(rh);
/*     */           continue;
/*     */         } 
/* 259 */         if (lastLineRead.startsWith("depends:")) {
/* 261 */           dependendModules.add(readExternalModule(rh));
/*     */           continue;
/*     */         } 
/* 263 */         if (lastLineRead.startsWith("optional:"))
/* 265 */           optionalModules.add(readExternalModule(rh)); 
/*     */       } 
/* 273 */       rh.close();
/* 275 */       this.optionalModules = optionalModules.<ModuleInfo>toArray(new ModuleInfo[optionalModules.size()]);
/* 278 */       this.requiredModules = dependendModules.<ModuleInfo>toArray(new ModuleInfo[dependendModules.size()]);
/* 281 */     } catch (IOException ioe) {
/* 283 */       throw new ModuleInitializeException("Failed to load properties", ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String readValue(ReaderHelper reader, String firstLine) throws IOException {
/* 298 */     StringBuffer b = new StringBuffer(firstLine.trim());
/* 299 */     boolean newLine = true;
/* 300 */     while (isNextLineValueLine(reader)) {
/* 302 */       firstLine = reader.next();
/* 303 */       String trimedLine = firstLine.trim();
/* 304 */       if (trimedLine.length() == 0 && !newLine) {
/* 306 */         b.append("\n");
/* 307 */         newLine = true;
/*     */         continue;
/*     */       } 
/* 311 */       if (!newLine)
/* 313 */         b.append(" "); 
/* 315 */       b.append(parseValue(trimedLine));
/* 316 */       newLine = false;
/*     */     } 
/* 319 */     return b.toString();
/*     */   }
/*     */   
/*     */   private boolean isNextLineValueLine(ReaderHelper reader) throws IOException {
/* 331 */     if (!reader.hasNext())
/* 333 */       return false; 
/* 335 */     String firstLine = reader.next();
/* 336 */     if (firstLine == null)
/* 338 */       return false; 
/* 340 */     if (parseKey(firstLine) != null) {
/* 342 */       reader.pushBack(firstLine);
/* 343 */       return false;
/*     */     } 
/* 345 */     reader.pushBack(firstLine);
/* 346 */     return true;
/*     */   }
/*     */   
/*     */   private void readModuleInfo(ReaderHelper reader) throws IOException {
/* 358 */     while (reader.hasNext()) {
/* 360 */       String lastLineRead = reader.next();
/* 362 */       if (!Character.isWhitespace(lastLineRead.charAt(0))) {
/* 365 */         reader.pushBack(lastLineRead);
/*     */         return;
/*     */       } 
/* 369 */       String line = lastLineRead.trim();
/* 370 */       String key = parseKey(line);
/* 371 */       if (key != null) {
/* 374 */         String b = readValue(reader, parseValue(line.trim()));
/* 376 */         if (key.equals("name")) {
/* 378 */           setName(b);
/*     */           continue;
/*     */         } 
/* 380 */         if (key.equals("producer")) {
/* 382 */           setProducer(b);
/*     */           continue;
/*     */         } 
/* 384 */         if (key.equals("description")) {
/* 386 */           setDescription(b);
/*     */           continue;
/*     */         } 
/* 388 */         if (key.equals("subsystem")) {
/* 390 */           setSubSystem(b);
/*     */           continue;
/*     */         } 
/* 392 */         if (key.equals("version.major")) {
/* 394 */           setMajorVersion(b);
/*     */           continue;
/*     */         } 
/* 396 */         if (key.equals("version.minor")) {
/* 398 */           setMinorVersion(b);
/*     */           continue;
/*     */         } 
/* 400 */         if (key.equals("version.patchlevel"))
/* 402 */           setPatchLevel(b); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String parseKey(String line) {
/* 417 */     int idx = line.indexOf(':');
/* 418 */     if (idx == -1)
/* 420 */       return null; 
/* 422 */     return line.substring(0, idx);
/*     */   }
/*     */   
/*     */   private String parseValue(String line) {
/* 433 */     int idx = line.indexOf(':');
/* 434 */     if (idx == -1)
/* 436 */       return line; 
/* 438 */     if (idx + 1 == line.length())
/* 440 */       return ""; 
/* 442 */     return line.substring(idx + 1);
/*     */   }
/*     */   
/*     */   private DefaultModuleInfo readExternalModule(ReaderHelper reader) throws IOException {
/* 456 */     DefaultModuleInfo mi = new DefaultModuleInfo();
/* 458 */     while (reader.hasNext()) {
/* 460 */       String lastLineRead = reader.next();
/* 462 */       if (!Character.isWhitespace(lastLineRead.charAt(0))) {
/* 465 */         reader.pushBack(lastLineRead);
/* 466 */         return mi;
/*     */       } 
/* 469 */       String line = lastLineRead.trim();
/* 470 */       String key = parseKey(line);
/* 471 */       if (key != null) {
/* 473 */         String b = readValue(reader, parseValue(line));
/* 474 */         if (key.equals("module")) {
/* 476 */           mi.setModuleClass(b);
/*     */           continue;
/*     */         } 
/* 478 */         if (key.equals("version.major")) {
/* 480 */           mi.setMajorVersion(b);
/*     */           continue;
/*     */         } 
/* 482 */         if (key.equals("version.minor")) {
/* 484 */           mi.setMinorVersion(b);
/*     */           continue;
/*     */         } 
/* 486 */         if (key.equals("version.patchlevel"))
/* 488 */           mi.setPatchLevel(b); 
/*     */       } 
/*     */     } 
/* 492 */     return mi;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 504 */     return this.name;
/*     */   }
/*     */   
/*     */   protected void setName(String name) {
/* 514 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 525 */     return this.description;
/*     */   }
/*     */   
/*     */   protected void setDescription(String description) {
/* 535 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getProducer() {
/* 547 */     return this.producer;
/*     */   }
/*     */   
/*     */   protected void setProducer(String producer) {
/* 557 */     this.producer = producer;
/*     */   }
/*     */   
/*     */   public ModuleInfo[] getRequiredModules() {
/* 569 */     ModuleInfo[] retval = new ModuleInfo[this.requiredModules.length];
/* 570 */     System.arraycopy(this.requiredModules, 0, retval, 0, this.requiredModules.length);
/* 571 */     return retval;
/*     */   }
/*     */   
/*     */   public ModuleInfo[] getOptionalModules() {
/* 583 */     ModuleInfo[] retval = new ModuleInfo[this.optionalModules.length];
/* 584 */     System.arraycopy(this.optionalModules, 0, retval, 0, this.optionalModules.length);
/* 585 */     return retval;
/*     */   }
/*     */   
/*     */   protected void setRequiredModules(ModuleInfo[] requiredModules) {
/* 595 */     this.requiredModules = new ModuleInfo[requiredModules.length];
/* 596 */     System.arraycopy(requiredModules, 0, this.requiredModules, 0, requiredModules.length);
/*     */   }
/*     */   
/*     */   public void setOptionalModules(ModuleInfo[] optionalModules) {
/* 606 */     this.optionalModules = new ModuleInfo[optionalModules.length];
/* 607 */     System.arraycopy(optionalModules, 0, this.optionalModules, 0, optionalModules.length);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 618 */     StringBuffer buffer = new StringBuffer();
/* 619 */     buffer.append("Module : ");
/* 620 */     buffer.append(getName());
/* 621 */     buffer.append("\n");
/* 622 */     buffer.append("ModuleClass : ");
/* 623 */     buffer.append(getModuleClass());
/* 624 */     buffer.append("\n");
/* 625 */     buffer.append("Version: ");
/* 626 */     buffer.append(getMajorVersion());
/* 627 */     buffer.append(".");
/* 628 */     buffer.append(getMinorVersion());
/* 629 */     buffer.append(".");
/* 630 */     buffer.append(getPatchLevel());
/* 631 */     buffer.append("\n");
/* 632 */     buffer.append("Producer: ");
/* 633 */     buffer.append(getProducer());
/* 634 */     buffer.append("\n");
/* 635 */     buffer.append("Description: ");
/* 636 */     buffer.append(getDescription());
/* 637 */     buffer.append("\n");
/* 638 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   protected static boolean isClassLoadable(String name) {
/*     */     try {
/* 652 */       Thread.currentThread().getContextClassLoader().loadClass(name);
/* 653 */       return true;
/* 655 */     } catch (Exception e) {
/* 657 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void configure(SubSystem subSystem) {
/* 669 */     InputStream in = ObjectUtilities.getResourceRelativeAsStream("configuration.properties", getClass());
/* 671 */     if (in == null)
/*     */       return; 
/* 675 */     subSystem.getPackageManager().getPackageConfiguration().load(in);
/*     */   }
/*     */   
/*     */   protected void performExternalInitialize(String classname) throws ModuleInitializeException {
/*     */     try {
/* 690 */       Class c = Thread.currentThread().getContextClassLoader().loadClass(classname);
/* 691 */       ModuleInitializer mi = (ModuleInitializer)c.newInstance();
/* 692 */       mi.performInit();
/* 694 */     } catch (ModuleInitializeException mie) {
/* 696 */       throw mie;
/* 698 */     } catch (Exception e) {
/* 700 */       throw new ModuleInitializeException("Failed to load specified initializer class.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getSubSystem() {
/* 712 */     if (this.subsystem == null)
/* 714 */       return getName(); 
/* 716 */     return this.subsystem;
/*     */   }
/*     */   
/*     */   protected void setSubSystem(String name) {
/* 726 */     this.subsystem = name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\modules\AbstractModule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */