/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.Array;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.measure.AngleFormat;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.LocalName;
/*     */ 
/*     */ public class ParameterWriter extends FilterWriter {
/*  73 */   private Locale locale = Locale.getDefault();
/*     */   
/*     */   private transient NumberFormat numberFormat;
/*     */   
/*     */   private transient DateFormat dateFormat;
/*     */   
/*     */   private transient AngleFormat angleFormat;
/*     */   
/*     */   public ParameterWriter() {
/*  95 */     this(Arguments.getWriter(System.out));
/*     */   }
/*     */   
/*     */   public ParameterWriter(Writer out) {
/* 102 */     super(out);
/*     */   }
/*     */   
/*     */   public static void print(OperationMethod operation) {
/* 112 */     ParameterWriter writer = new ParameterWriter();
/*     */     try {
/* 114 */       writer.format(operation);
/* 115 */     } catch (IOException exception) {
/* 117 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void print(ParameterDescriptorGroup descriptor) {
/* 129 */     ParameterWriter writer = new ParameterWriter();
/*     */     try {
/* 131 */       writer.format(descriptor);
/* 132 */     } catch (IOException exception) {
/* 134 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void print(ParameterValueGroup values) {
/* 146 */     ParameterWriter writer = new ParameterWriter();
/*     */     try {
/* 148 */       writer.format(values);
/* 149 */     } catch (IOException exception) {
/* 151 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void format(OperationMethod operation) throws IOException {
/* 162 */     synchronized (this.lock) {
/* 163 */       format(operation.getName().getCode(), operation.getParameters(), (ParameterValueGroup)null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void format(ParameterDescriptorGroup descriptor) throws IOException {
/* 174 */     synchronized (this.lock) {
/* 175 */       format(descriptor.getName().getCode(), descriptor, (ParameterValueGroup)null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void format(ParameterValueGroup values) throws IOException {
/* 186 */     ParameterDescriptorGroup descriptor = values.getDescriptor();
/* 187 */     synchronized (this.lock) {
/* 188 */       format(descriptor.getName().getCode(), descriptor, values);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void format(String name, ParameterDescriptorGroup group, ParameterValueGroup values) throws IOException {
/* 209 */     String lineSeparator = System.getProperty("line.separator", "\n");
/* 210 */     this.out.write(32);
/* 211 */     this.out.write(name);
/* 212 */     this.out.write(lineSeparator);
/* 213 */     Collection<GenericName> alias = group.getAlias();
/* 214 */     if (alias != null) {
/* 215 */       boolean first = true;
/* 216 */       for (GenericName a : alias) {
/* 217 */         this.out.write(first ? " alias " : "       ");
/* 218 */         this.out.write(a.toInternationalString().toString(this.locale));
/* 219 */         this.out.write(lineSeparator);
/* 220 */         first = false;
/*     */       } 
/*     */     } 
/* 226 */     Vocabulary resources = Vocabulary.getResources(this.locale);
/* 227 */     TableWriter table = new TableWriter(this.out, " │ ");
/* 228 */     table.setMultiLinesCells(true);
/* 229 */     table.writeHorizontalSeparator();
/* 230 */     table.write(resources.getString(146));
/* 231 */     table.nextColumn();
/* 232 */     table.write(resources.getString(18));
/* 233 */     table.nextColumn();
/* 234 */     table.write("Minimum");
/* 235 */     table.nextColumn();
/* 236 */     table.write("Maximum");
/* 237 */     table.nextColumn();
/* 238 */     table.write(resources.getString((values == null) ? 43 : 236));
/* 240 */     table.nextColumn();
/* 241 */     table.write("Units");
/* 242 */     table.nextLine();
/* 243 */     table.nextLine('═');
/* 250 */     List<Object> deferredGroups = null;
/* 251 */     Object[] array1 = new Object[1];
/* 252 */     Collection<?> elements = (values != null) ? values.values() : group.descriptors();
/* 253 */     for (Object element : elements) {
/*     */       GeneralParameterValue generalValue;
/*     */       GeneralParameterDescriptor generalDescriptor;
/* 256 */       if (values != null) {
/* 257 */         generalValue = (GeneralParameterValue)element;
/* 258 */         generalDescriptor = generalValue.getDescriptor();
/*     */       } else {
/* 260 */         generalValue = null;
/* 261 */         generalDescriptor = (GeneralParameterDescriptor)element;
/*     */       } 
/* 267 */       if (generalDescriptor instanceof ParameterDescriptorGroup) {
/* 268 */         if (deferredGroups == null)
/* 269 */           deferredGroups = new ArrayList(); 
/* 271 */         deferredGroups.add(element);
/*     */         continue;
/*     */       } 
/* 278 */       ReferenceIdentifier referenceIdentifier = generalDescriptor.getName();
/* 279 */       table.write(referenceIdentifier.getCode());
/* 280 */       alias = generalDescriptor.getAlias();
/* 281 */       if (alias != null)
/* 282 */         for (GenericName a : alias) {
/* 283 */           if (!referenceIdentifier.equals(a)) {
/* 284 */             table.write(lineSeparator);
/* 285 */             table.write(a.tip().toInternationalString().toString(this.locale));
/*     */           } 
/*     */         }  
/* 289 */       table.nextColumn();
/* 295 */       if (generalDescriptor instanceof ParameterDescriptor) {
/*     */         Object array;
/* 296 */         ParameterDescriptor descriptor = (ParameterDescriptor)generalDescriptor;
/* 297 */         table.write(Classes.getShortName(descriptor.getValueClass()));
/* 298 */         table.nextColumn();
/* 299 */         table.setAlignment(2);
/* 300 */         Object value = descriptor.getMinimumValue();
/* 301 */         if (value != null)
/* 302 */           table.write(formatValue(value)); 
/* 304 */         table.nextColumn();
/* 305 */         value = descriptor.getMaximumValue();
/* 306 */         if (value != null)
/* 307 */           table.write(formatValue(value)); 
/* 309 */         table.nextColumn();
/* 310 */         if (generalValue != null) {
/* 311 */           value = ((ParameterValue)generalValue).getValue();
/*     */         } else {
/* 313 */           value = descriptor.getDefaultValue();
/*     */         } 
/* 321 */         if (value != null && value.getClass().isArray()) {
/* 322 */           array = value;
/*     */         } else {
/* 324 */           array = array1;
/* 325 */           array1[0] = value;
/*     */         } 
/* 327 */         int length = Array.getLength(array);
/* 328 */         for (int i = 0; i < length; i++) {
/* 329 */           value = Array.get(array, i);
/* 330 */           if (value != null) {
/* 331 */             if (i != 0)
/* 332 */               table.write(lineSeparator); 
/* 334 */             table.write(formatValue(value));
/*     */           } 
/*     */         } 
/* 337 */         table.nextColumn();
/* 338 */         table.setAlignment(0);
/* 339 */         value = descriptor.getUnit();
/* 340 */         if (value != null)
/* 341 */           table.write(value.toString()); 
/*     */       } 
/* 344 */       table.writeHorizontalSeparator();
/*     */     } 
/* 346 */     table.flush();
/* 351 */     if (deferredGroups != null)
/* 352 */       for (Object element : deferredGroups) {
/*     */         ParameterValueGroup value;
/*     */         ParameterDescriptorGroup descriptor;
/* 355 */         if (element instanceof ParameterValueGroup) {
/* 356 */           value = (ParameterValueGroup)element;
/* 357 */           descriptor = value.getDescriptor();
/*     */         } else {
/* 359 */           value = null;
/* 360 */           descriptor = (ParameterDescriptorGroup)element;
/*     */         } 
/* 362 */         this.out.write(lineSeparator);
/* 363 */         format(name + '/' + descriptor.getName().getCode(), descriptor, value);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void summary(Collection<? extends IdentifiedObject> parameters, Set<String> scopes) throws IOException {
/* 388 */     Map<Object, Integer> titles = new LinkedHashMap<Object, Integer>();
/* 389 */     List<String[]> names = (List)new ArrayList<String>();
/* 390 */     Locale locale = this.locale;
/* 391 */     String[] descriptions = null;
/* 392 */     titles.put(null, Integer.valueOf(0));
/* 393 */     for (IdentifiedObject element : parameters) {
/* 394 */       Collection<GenericName> aliases = element.getAlias();
/* 395 */       String[] elementNames = new String[titles.size()];
/* 396 */       elementNames[0] = element.getName().getCode();
/* 397 */       if (aliases != null) {
/* 403 */         int count = 0;
/* 404 */         for (GenericName alias : aliases) {
/*     */           Object title;
/* 405 */           GenericName scope = alias.scope().name();
/* 406 */           LocalName localName = alias.tip();
/* 408 */           if (scope != null) {
/* 409 */             if (scopes != null && !scopes.contains(scope.toString()))
/*     */               continue; 
/* 417 */             title = scope.toInternationalString().toString(locale);
/*     */           } else {
/* 419 */             title = Integer.valueOf(count++);
/*     */           } 
/* 427 */           Integer position = titles.get(title);
/* 428 */           if (position == null) {
/* 429 */             position = Integer.valueOf(titles.size());
/* 430 */             titles.put(title, position);
/*     */           } 
/* 439 */           int index = position.intValue();
/* 440 */           if (index >= elementNames.length)
/* 441 */             elementNames = (String[])XArray.resize((Object[])elementNames, index + 1); 
/* 443 */           String oldName = elementNames[index];
/* 444 */           String newName = localName.toInternationalString().toString(locale);
/* 445 */           if (oldName == null || oldName.length() > newName.length())
/* 451 */             elementNames[index] = newName; 
/*     */         } 
/*     */       } 
/* 459 */       InternationalString remarks = element.getRemarks();
/* 460 */       if (remarks != null) {
/* 461 */         if (descriptions == null)
/* 462 */           descriptions = new String[parameters.size()]; 
/* 464 */         descriptions[names.size()] = remarks.toString(locale);
/*     */       } 
/* 466 */       names.add(elementNames);
/*     */     } 
/* 473 */     boolean[] hide = new boolean[titles.size()];
/*     */     int column;
/* 474 */     label90: for (column = hide.length; --column >= 1; ) {
/* 475 */       for (String[] alias : names) {
/* 476 */         if (alias.length > column) {
/* 477 */           String name = alias[column];
/* 478 */           if (name != null && !name.equals(alias[0]))
/*     */             continue label90; 
/*     */         } 
/*     */       } 
/* 486 */       hide[column] = true;
/*     */     } 
/* 493 */     column = 0;
/* 494 */     synchronized (this.lock) {
/* 495 */       TableWriter table = new TableWriter(this.out, " │ ");
/* 496 */       table.setMultiLinesCells(true);
/* 497 */       table.writeHorizontalSeparator();
/* 501 */       for (Object element : titles.keySet()) {
/*     */         String title;
/* 502 */         if (hide[column++])
/*     */           continue; 
/* 506 */         if (element == null) {
/* 507 */           title = "Identifier";
/* 508 */         } else if (element instanceof String) {
/* 509 */           title = (String)element;
/*     */         } else {
/* 511 */           title = "Alias " + element;
/*     */         } 
/* 513 */         table.write(title);
/* 514 */         table.nextColumn();
/*     */       } 
/* 516 */       if (descriptions != null)
/* 517 */         table.write("Description"); 
/* 519 */       table.writeHorizontalSeparator();
/* 523 */       int counter = 0;
/* 524 */       for (String[] aliases : names) {
/* 525 */         for (column = 0; column < hide.length; column++) {
/* 526 */           if (!hide[column]) {
/* 529 */             if (column < aliases.length) {
/* 530 */               String alias = aliases[column];
/* 531 */               if (alias != null)
/* 532 */                 table.write(alias); 
/*     */             } 
/* 535 */             table.nextColumn();
/*     */           } 
/*     */         } 
/* 537 */         if (descriptions != null) {
/* 538 */           String remarks = descriptions[counter++];
/* 539 */           if (remarks != null)
/* 540 */             table.write(remarks); 
/*     */         } 
/* 543 */         table.nextLine();
/*     */       } 
/* 545 */       table.writeHorizontalSeparator();
/* 546 */       table.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/* 555 */     return this.locale;
/*     */   }
/*     */   
/*     */   public void setLocale(Locale locale) {
/* 562 */     synchronized (this.lock) {
/* 563 */       this.locale = locale;
/* 564 */       this.numberFormat = null;
/* 565 */       this.dateFormat = null;
/* 566 */       this.angleFormat = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String formatValue(Object value) {
/* 581 */     if (value instanceof Number) {
/* 582 */       if (this.numberFormat == null)
/* 583 */         this.numberFormat = NumberFormat.getNumberInstance(this.locale); 
/* 585 */       return this.numberFormat.format(value);
/*     */     } 
/* 587 */     if (value instanceof java.util.Date) {
/* 588 */       if (this.dateFormat == null)
/* 589 */         this.dateFormat = DateFormat.getDateInstance(2, this.locale); 
/* 591 */       return this.dateFormat.format(value);
/*     */     } 
/* 593 */     if (value instanceof org.geotools.measure.Angle) {
/* 594 */       if (this.angleFormat == null)
/* 595 */         this.angleFormat = AngleFormat.getInstance(this.locale); 
/* 597 */       return this.angleFormat.format(value);
/*     */     } 
/* 599 */     return String.valueOf(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ParameterWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */