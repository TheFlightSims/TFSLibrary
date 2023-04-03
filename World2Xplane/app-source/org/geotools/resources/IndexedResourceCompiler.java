/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.geotools.resources.i18n.Descriptions;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ 
/*     */ public final class IndexedResourceCompiler implements Comparator<Object> {
/*  52 */   private static final File SOURCE_DIRECTORY = new File("./src/main");
/*     */   
/*  58 */   private static final Class<? extends IndexedResourceBundle>[] RESOURCES_TO_PROCESS = new Class[] { Descriptions.class, Vocabulary.class, Loggings.class, Errors.class };
/*     */   
/*     */   private static final String PROPERTIES_EXT = ".properties";
/*     */   
/*     */   private static final String RESOURCES_EXT = ".utf";
/*     */   
/*     */   private static final String ARGUMENT_COUNT_PREFIX = "_$";
/*     */   
/*     */   private static final int COMMENT_LENGTH = 92;
/*     */   
/*     */   private final File sourceDirectory;
/*     */   
/*  98 */   private final Map<Integer, String> allocatedIDs = new HashMap<Integer, String>();
/*     */   
/* 104 */   private final Map<Object, Object> resources = new HashMap<Object, Object>();
/*     */   
/*     */   private final PrintWriter out;
/*     */   
/*     */   private IndexedResourceCompiler(File sourceDirectory, Class<? extends IndexedResourceBundle> bundleClass, boolean renumber, PrintWriter out) throws IOException {
/* 129 */     this.sourceDirectory = sourceDirectory;
/* 130 */     this.out = out;
/* 131 */     if (!renumber)
/*     */       try {
/* 132 */         String classname = toKeyClass(bundleClass.getName());
/* 133 */         Field[] fields = Class.forName(classname).getFields();
/* 134 */         out.print("Loading ");
/* 135 */         out.println(classname);
/* 139 */         Field.setAccessible((AccessibleObject[])fields, true);
/* 140 */         for (int i = fields.length; --i >= 0; ) {
/* 141 */           Field field = fields[i];
/* 142 */           String key = field.getName();
/*     */           try {
/* 144 */             Object ID = field.get((Object)null);
/* 145 */             if (ID instanceof Integer)
/* 146 */               this.allocatedIDs.put((Integer)ID, key); 
/* 148 */           } catch (IllegalAccessException exception) {
/* 149 */             File source = new File(classname.replace('.', '/') + ".class");
/* 150 */             warning(source, key, "Access denied", exception);
/*     */           } 
/*     */         } 
/* 153 */       } catch (ClassNotFoundException exception) {} 
/*     */   }
/*     */   
/*     */   private static String toKeyClass(String bundleClass) {
/* 167 */     if (bundleClass.endsWith("s"))
/* 168 */       bundleClass = bundleClass.substring(0, bundleClass.length() - 1); 
/* 170 */     return bundleClass + "Keys";
/*     */   }
/*     */   
/*     */   private static Properties loadPropertyFile(File file) throws IOException {
/* 177 */     InputStream input = new FileInputStream(file);
/* 178 */     Properties properties = new Properties();
/* 179 */     properties.load(input);
/* 180 */     input.close();
/* 181 */     return properties;
/*     */   }
/*     */   
/*     */   private void processPropertyFile(File file) throws IOException {
/* 194 */     Properties properties = loadPropertyFile(file);
/* 195 */     this.resources.clear();
/* 196 */     for (Map.Entry<Object, Object> entry : properties.entrySet()) {
/*     */       MessageFormat message;
/*     */       int argumentCount;
/* 197 */       String key = (String)entry.getKey();
/* 198 */       String value = (String)entry.getValue();
/* 202 */       if (key.trim().length() == 0) {
/* 203 */         warning(file, key, "Empty key.", null);
/*     */         continue;
/*     */       } 
/* 206 */       if (value.trim().length() == 0) {
/* 207 */         warning(file, key, "Empty value.", null);
/*     */         continue;
/*     */       } 
/*     */       try {
/* 215 */         message = new MessageFormat(toMessageFormatString(value));
/* 216 */       } catch (IllegalArgumentException exception) {
/* 217 */         warning(file, key, "Bad resource value", exception);
/*     */         continue;
/*     */       } 
/* 225 */       int index = key.lastIndexOf("_$");
/* 226 */       if (index < 0) {
/* 227 */         argumentCount = 0;
/* 228 */         this.resources.put(key, value);
/*     */       } else {
/*     */         try {
/* 230 */           String suffix = key.substring(index + "_$".length());
/* 231 */           argumentCount = Integer.parseInt(suffix);
/* 232 */           this.resources.put(key, message.toPattern());
/* 233 */         } catch (NumberFormatException exception) {
/* 234 */           warning(file, key, "Bad number in resource key", exception);
/*     */           continue;
/*     */         } 
/*     */       } 
/* 237 */       int expected = (message.getFormats()).length;
/* 238 */       if (argumentCount != expected) {
/* 239 */         String suffix = "_$" + expected;
/* 240 */         warning(file, key, "Key name should ends with \"" + suffix + "\".", null);
/*     */       } 
/*     */     } 
/* 247 */     String[] keys = (String[])this.resources.keySet().toArray((Object[])new String[this.resources.size()]);
/* 248 */     Arrays.sort(keys, this);
/* 249 */     int freeID = 0;
/* 250 */     for (int i = 0; i < keys.length; i++) {
/* 251 */       String key = keys[i];
/* 252 */       if (!this.allocatedIDs.containsValue(key))
/*     */         while (true) {
/* 255 */           Integer ID = Integer.valueOf(freeID++);
/* 256 */           if (!this.allocatedIDs.containsKey(ID)) {
/* 257 */             this.allocatedIDs.put(ID, key);
/*     */             break;
/*     */           } 
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeUTFFile(File file) throws IOException {
/* 270 */     int count = this.allocatedIDs.isEmpty() ? 0 : (((Integer)Collections.<Integer>max(this.allocatedIDs.keySet())).intValue() + 1);
/* 271 */     DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
/* 272 */     out.writeInt(count);
/* 273 */     for (int i = 0; i < count; i++) {
/* 274 */       String value = (String)this.resources.get(this.allocatedIDs.get(Integer.valueOf(i)));
/* 275 */       out.writeUTF((value != null) ? value : "");
/*     */     } 
/* 277 */     out.close();
/*     */   }
/*     */   
/*     */   private static String toMessageFormatString(String text) {
/* 285 */     int level = 0;
/* 286 */     int last = -1;
/* 287 */     StringBuilder buffer = new StringBuilder(text);
/* 288 */     for (int i = 0; i < buffer.length(); i++) {
/* 289 */       switch (buffer.charAt(i)) {
/*     */         case '{':
/* 296 */           level++;
/* 296 */           last = i;
/*     */           break;
/*     */         case '}':
/* 297 */           level--;
/* 297 */           last = i;
/*     */           break;
/*     */         case '\'':
/* 303 */           if (i + 2 < buffer.length() && buffer.charAt(i + 2) == '\'')
/* 304 */             switch (buffer.charAt(i + 1)) {
/*     */               case '{':
/* 305 */                 i += 2;
/*     */                 break;
/*     */               case '}':
/* 306 */                 i += 2;
/*     */                 break;
/*     */             }  
/* 309 */           if (level <= 0) {
/* 313 */             buffer.insert(i++, '\'');
/*     */             break;
/*     */           } 
/* 320 */           if (last >= 0 && buffer.charAt(last) == '{') {
/* 321 */             int scan = last;
/* 322 */             while (scan < i) {
/* 323 */               if (!Character.isDigit(buffer.charAt(++scan))) {
/* 324 */                 String choice = ",choice,";
/* 325 */                 int end = scan + ",choice,".length();
/* 326 */                 if (end < buffer.length() && buffer.substring(scan, end).equalsIgnoreCase(",choice,"))
/* 327 */                   buffer.insert(i++, '\''); 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/* 334 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private void warning(File file, String key, String message, Exception exception) {
/* 348 */     this.out.print("ERROR ");
/* 349 */     if (file != null) {
/* 350 */       String filename = file.getPath();
/* 351 */       if (filename.endsWith(".properties"))
/* 352 */         filename = filename.substring(0, filename.length() - ".properties".length()); 
/* 354 */       this.out.print('(');
/* 355 */       this.out.print(filename);
/* 356 */       this.out.print(')');
/*     */     } 
/* 358 */     this.out.print(": ");
/* 359 */     if (key != null) {
/* 360 */       this.out.print('"');
/* 361 */       this.out.print(key);
/* 362 */       this.out.print('"');
/*     */     } 
/* 364 */     this.out.println();
/* 365 */     this.out.print(message);
/* 366 */     if (exception != null) {
/* 367 */       this.out.print(": ");
/* 368 */       this.out.print(exception.getLocalizedMessage());
/*     */     } 
/* 370 */     this.out.println();
/* 371 */     this.out.println();
/* 372 */     this.out.flush();
/*     */   }
/*     */   
/*     */   private void writeJavaSource(Class bundleClass) throws IOException {
/* 383 */     String fullname = toKeyClass(bundleClass.getName());
/* 384 */     int packageEnd = fullname.lastIndexOf('.');
/* 385 */     String packageName = fullname.substring(0, packageEnd);
/* 386 */     String classname = fullname.substring(packageEnd + 1);
/* 387 */     File file = new File(this.sourceDirectory, "java/" + fullname.replace('.', '/') + ".java");
/* 389 */     if (!file.getParentFile().isDirectory()) {
/* 390 */       warning(file, null, "Parent directory not found.", null);
/*     */       return;
/*     */     } 
/* 393 */     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
/* 395 */     out.write("/*\n *    GeoTools - The Open Source Java GIS Toolkit\n *    http://geotools.org\n *    \n *    (C) 2003-2008, Open Source Geospatial Foundation (OSGeo)\n *    \n *    This library is free software; you can redistribute it and/or\n *    modify it under the terms of the GNU Lesser General Public\n *    License as published by the Free Software Foundation;\n *    version 2.1 of the License.\n *    \n *    This library is distributed in the hope that it will be useful,\n *    but WITHOUT ANY WARRANTY; without even the implied warranty of\n *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU\n *    Lesser General Public License for more details.\n *    \n *    THIS IS AN AUTOMATICALLY GENERATED FILE. DO NOT EDIT!\n *    Generated with: org.geotools.resources.IndexedResourceCompiler\n */\n");
/* 414 */     out.write("package ");
/* 415 */     out.write(packageName);
/* 416 */     out.write(";\n\n\n");
/* 417 */     out.write("/**\n * Resource keys. This class is used when compiling sources, but\n * no dependencies to {@code ResourceKeys} should appear in any\n * resulting class files.  Since Java compiler inlines final integer\n * values, using long identifiers will not bloat constant pools of\n * classes compiled against the interface, provided that no class\n * implements this interface.\n *\n * @see org.geotools.resources.IndexedResourceBundle\n * @see org.geotools.resources.IndexedResourceCompiler\n * @source $URL$\n */\n");
/* 429 */     out.write("public final class ");
/* 429 */     out.write(classname);
/* 429 */     out.write(" {\n");
/* 430 */     out.write("    private ");
/* 430 */     out.write(classname);
/* 430 */     out.write("() {\n");
/* 431 */     out.write("    }\n");
/* 432 */     Map.Entry[] entries = (Map.Entry[])this.allocatedIDs.entrySet().toArray((Object[])new Map.Entry[this.allocatedIDs.size()]);
/* 433 */     Arrays.sort(entries, this);
/* 434 */     for (int i = 0; i < entries.length; i++) {
/* 435 */       out.write(10);
/* 436 */       String key = entries[i].getValue();
/* 437 */       String ID = entries[i].getKey().toString();
/* 438 */       String message = (String)this.resources.get(key);
/* 439 */       if (message != null) {
/* 440 */         out.write("    /**\n");
/* 441 */         while ((message = message.trim()).length() != 0) {
/* 442 */           out.write("     * ");
/* 443 */           int stop = message.length();
/* 444 */           if (stop > 92) {
/* 445 */             stop = 92;
/* 446 */             while (stop > 20 && !Character.isWhitespace(message.charAt(stop)))
/* 447 */               stop--; 
/*     */           } 
/* 450 */           out.write(message.substring(0, stop).trim());
/* 451 */           out.write(10);
/* 452 */           message = message.substring(stop);
/*     */         } 
/* 454 */         out.write("     */\n");
/*     */       } 
/* 456 */       out.write("    public static final int ");
/* 457 */       out.write(key);
/* 458 */       out.write(" = ");
/* 459 */       out.write(ID);
/* 460 */       out.write(";\n");
/*     */     } 
/* 462 */     out.write("}\n");
/* 463 */     out.close();
/*     */   }
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/* 472 */     if (o1 instanceof Map.Entry)
/* 472 */       o1 = ((Map.Entry)o1).getValue(); 
/* 473 */     if (o2 instanceof Map.Entry)
/* 473 */       o2 = ((Map.Entry)o2).getValue(); 
/* 474 */     String key1 = (String)o1;
/* 475 */     String key2 = (String)o2;
/* 476 */     return key1.compareTo(key2);
/*     */   }
/*     */   
/*     */   private static void scanForResources(File sourceDirectory, Class<? extends IndexedResourceBundle> bundleClass, boolean renumber, PrintWriter out) throws IOException {
/* 495 */     String fullname = bundleClass.getName();
/* 496 */     int packageEnd = fullname.lastIndexOf('.');
/* 497 */     String packageName = fullname.substring(0, packageEnd);
/* 498 */     String classname = fullname.substring(packageEnd + 1);
/* 499 */     String packageDir = packageName.replace('.', '/');
/* 500 */     File srcDir = new File(sourceDirectory, "java/" + packageDir);
/* 501 */     File utfDir = new File(sourceDirectory, "resources/" + packageDir);
/* 502 */     if (!srcDir.isDirectory()) {
/* 503 */       out.print('"');
/* 504 */       out.print(srcDir.getPath());
/* 505 */       out.println("\" is not a directory.");
/*     */       return;
/*     */     } 
/* 508 */     if (!utfDir.isDirectory()) {
/* 509 */       out.print('"');
/* 510 */       out.print(utfDir.getPath());
/* 511 */       out.println("\" is not a directory.");
/*     */       return;
/*     */     } 
/* 514 */     IndexedResourceCompiler compiler = null;
/* 515 */     File[] content = srcDir.listFiles();
/* 516 */     File defaultLanguage = null;
/* 517 */     for (int i = 0; i < content.length; i++) {
/* 518 */       File file = content[i];
/* 519 */       String filename = file.getName();
/* 520 */       if (filename.startsWith(classname) && filename.endsWith(".properties")) {
/* 521 */         if (compiler == null)
/* 522 */           compiler = new IndexedResourceCompiler(sourceDirectory, bundleClass, renumber, out); 
/* 524 */         compiler.processPropertyFile(file);
/* 525 */         String noExt = filename.substring(0, filename.length() - ".properties".length());
/* 526 */         File utfFile = new File(utfDir, noExt + ".utf");
/* 527 */         compiler.writeUTFFile(utfFile);
/* 528 */         if (noExt.equals(classname))
/* 529 */           defaultLanguage = file; 
/*     */       } 
/*     */     } 
/* 533 */     if (compiler != null) {
/* 534 */       if (defaultLanguage != null) {
/* 535 */         compiler.resources.clear();
/* 536 */         compiler.resources.putAll(loadPropertyFile(defaultLanguage));
/*     */       } 
/* 538 */       compiler.writeJavaSource(bundleClass);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args, File sourceDirectory, Class<? extends IndexedResourceBundle>[] resourcesToProcess) {
/* 554 */     Arguments arguments = new Arguments(args);
/* 555 */     boolean renumber = arguments.getFlag("-renumber");
/* 556 */     PrintWriter out = arguments.out;
/* 557 */     args = arguments.getRemainingArguments(0);
/* 558 */     if (!sourceDirectory.isDirectory()) {
/* 559 */       out.print(sourceDirectory);
/* 560 */       out.println(" not found or is not a directory.");
/*     */       return;
/*     */     } 
/* 563 */     for (int i = 0; i < resourcesToProcess.length; i++) {
/*     */       try {
/* 565 */         scanForResources(sourceDirectory, resourcesToProcess[i], renumber, out);
/* 566 */       } catch (IOException exception) {
/* 567 */         out.println(exception.getLocalizedMessage());
/*     */       } 
/*     */     } 
/* 570 */     out.flush();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 577 */     main(args, SOURCE_DIRECTORY, RESOURCES_TO_PROCESS);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\IndexedResourceCompiler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */