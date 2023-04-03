/*     */ package org.geotools.console;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class CommandLine {
/*     */   private static final String OPTION_PREFIX = "--";
/*     */   
/*     */   public static final int ILLEGAL_ARGUMENT_EXIT_CODE = 1;
/*     */   
/*     */   public static final int ABORT_EXIT_CODE = 2;
/*     */   
/*     */   public static final int BAD_CONTENT_EXIT_CODE = 3;
/*     */   
/*     */   public static final int IO_EXCEPTION_EXIT_CODE = 100;
/*     */   
/*     */   public static final int SQL_EXCEPTION_EXIT_CODE = 101;
/*     */   
/*     */   protected final PrintWriter out;
/*     */   
/*     */   protected final PrintWriter err;
/*     */   
/*     */   protected final Locale locale;
/*     */   
/*     */   protected final String[] arguments;
/*     */   
/*     */   protected CommandLine(String[] args) {
/* 118 */     this(args, 0);
/*     */   }
/*     */   
/*     */   protected CommandLine(String[] args, int maximumRemaining) {
/* 135 */     Arguments arguments = new Arguments(args);
/* 136 */     this.out = arguments.out;
/* 137 */     this.err = arguments.err;
/* 138 */     this.locale = arguments.locale;
/* 139 */     if (arguments.getFlag("--help")) {
/* 140 */       help();
/* 141 */       System.exit(0);
/*     */     } 
/* 143 */     setArgumentValues(getClass(), arguments);
/* 144 */     this.arguments = arguments.getRemainingArguments(maximumRemaining, "--".charAt(0));
/*     */   }
/*     */   
/*     */   private void setArgumentValues(Class<?> classe, Arguments arguments) throws UnsupportedOperationException {
/* 156 */     Class<?> parent = classe.getSuperclass();
/* 157 */     if (!CommandLine.class.equals(parent))
/* 158 */       setArgumentValues(parent, arguments); 
/* 160 */     for (Field field : classe.getDeclaredFields()) {
/* 161 */       Option option = field.<Option>getAnnotation(Option.class);
/* 162 */       if (option != null) {
/*     */         Object value;
/* 165 */         boolean mandatory = option.mandatory();
/* 166 */         Class<?> type = field.getType();
/* 167 */         String name = option.name().trim();
/* 168 */         if (name.length() == 0)
/* 169 */           name = field.getName(); 
/* 171 */         name = "--" + name;
/* 173 */         if (Boolean.class.isAssignableFrom(type) || boolean.class.equals(type)) {
/* 174 */           if (mandatory) {
/* 175 */             value = Boolean.valueOf(arguments.getRequiredBoolean(name));
/*     */           } else {
/* 177 */             value = Boolean.valueOf(arguments.getFlag(name));
/*     */           } 
/* 179 */         } else if (Integer.class.isAssignableFrom(type) || int.class.equals(type)) {
/* 180 */           if (mandatory) {
/* 181 */             value = Integer.valueOf(arguments.getRequiredInteger(name));
/*     */           } else {
/* 183 */             value = arguments.getOptionalInteger(name);
/*     */           } 
/* 185 */         } else if (Double.class.isAssignableFrom(type) || double.class.equals(type)) {
/* 186 */           if (mandatory) {
/* 187 */             value = Double.valueOf(arguments.getRequiredDouble(name));
/*     */           } else {
/* 189 */             value = arguments.getOptionalDouble(name);
/*     */           } 
/* 191 */         } else if (String.class.isAssignableFrom(type)) {
/* 192 */           if (mandatory) {
/* 193 */             value = arguments.getRequiredString(name);
/*     */           } else {
/* 195 */             value = arguments.getOptionalString(name);
/*     */           } 
/*     */         } else {
/*     */           String text;
/* 199 */           if (mandatory) {
/* 200 */             text = arguments.getRequiredString(name);
/*     */           } else {
/* 202 */             text = arguments.getOptionalString(name);
/*     */           } 
/* 204 */           value = parse(type, text);
/*     */         } 
/* 206 */         field.setAccessible(true);
/*     */         try {
/* 208 */           field.set(this, value);
/* 209 */         } catch (IllegalAccessException e) {
/* 210 */           throw new UnsupportedOperationException(e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected <T> T parse(Class<T> type, String value) throws UnsupportedOperationException {
/* 227 */     throw new UnsupportedOperationException(Errors.format(187, type));
/*     */   }
/*     */   
/*     */   private void getArguments(Class<?> classe, Map<String, String> mandatory, Map<String, String> optional) {
/* 240 */     Class<?> parent = classe.getSuperclass();
/* 241 */     if (!CommandLine.class.equals(parent))
/* 242 */       getArguments(parent, mandatory, optional); 
/* 244 */     for (Field field : classe.getDeclaredFields()) {
/* 245 */       Option option = field.<Option>getAnnotation(Option.class);
/* 246 */       if (option != null) {
/* 249 */         String description = option.description().trim();
/* 250 */         if (description.length() != 0) {
/* 251 */           String name = option.name().trim();
/* 252 */           if (name.length() == 0)
/* 253 */             name = field.getName(); 
/* 255 */           Class<?> type = Classes.primitiveToWrapper(field.getType());
/* 256 */           if (Number.class.isAssignableFrom(type)) {
/* 257 */             name = name + "=N";
/* 258 */           } else if (!Boolean.class.isAssignableFrom(type)) {
/* 259 */             name = name + "=S";
/*     */           } 
/* 261 */           if (option.mandatory()) {
/* 262 */             mandatory.put(name, description);
/*     */           } else {
/* 264 */             optional.put(name, description);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void help() {
/* 277 */     Map<String, String> mandatory = new TreeMap<String, String>();
/* 278 */     Map<String, String> optional = new TreeMap<String, String>();
/* 279 */     optional.put("help", "Print this summary.");
/* 280 */     optional.put("locale=S", "Set the locale for string, number and date formatting. Examples: \"fr\", \"fr_CA\".");
/* 281 */     optional.put("encoding=S", "Set the input and output encoding. Examples: \"UTF-8\", \"ISO-8859-1\".");
/* 282 */     getArguments(getClass(), mandatory, optional);
/* 283 */     if (!mandatory.isEmpty()) {
/* 284 */       this.out.println("Mandatory arguments:");
/* 285 */       print(mandatory);
/*     */     } 
/* 287 */     this.out.println("Optional arguments:");
/* 288 */     print(optional);
/*     */   }
/*     */   
/*     */   private void print(Map<String, String> options) {
/* 295 */     TableWriter table = new TableWriter(this.out, "  ");
/* 296 */     for (Map.Entry<String, String> entry : options.entrySet()) {
/* 297 */       table.write("  ");
/* 298 */       table.write("--");
/* 299 */       table.write(entry.getKey());
/* 300 */       table.nextColumn();
/* 301 */       table.write(entry.getValue());
/* 302 */       table.nextLine();
/*     */     } 
/*     */     try {
/* 305 */       table.flush();
/* 306 */     } catch (IOException e) {
/* 308 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\console\CommandLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */