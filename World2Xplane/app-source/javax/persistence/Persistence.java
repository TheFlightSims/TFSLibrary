/*     */ package javax.persistence;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.persistence.spi.PersistenceProvider;
/*     */ 
/*     */ public class Persistence {
/*     */   public static final String PERSISTENCE_PROVIDER = "javax.persistence.spi.PeristenceProvider";
/*     */   
/*  67 */   protected static final Set<PersistenceProvider> providers = new HashSet<PersistenceProvider>();
/*     */   
/*  70 */   private static final String SERVICE_NAME = "META-INF/services/" + PersistenceProvider.class.getName();
/*     */   
/*     */   private static final String PERSISTENCE_XML_NAME = "META-INF/persistence.xml";
/*     */   
/*     */   public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
/*  83 */     return createEntityManagerFactory(persistenceUnitName, null);
/*     */   }
/*     */   
/*     */   public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map properties) {
/*  99 */     EntityManagerFactory emf = null;
/* 100 */     Set<PersistenceProvider> providersFound = null;
/*     */     try {
/* 103 */       providersFound = findAllProviders();
/* 104 */     } catch (IOException exc) {}
/* 106 */     Map<String, String> errors = new HashMap<String, String>();
/* 107 */     Set<String> returnedNull = new HashSet<String>();
/* 108 */     for (PersistenceProvider provider : providersFound) {
/*     */       try {
/* 110 */         emf = provider.createEntityManagerFactory(persistenceUnitName, properties);
/* 111 */         if (emf != null)
/*     */           break; 
/* 114 */         returnedNull.add(provider.getClass().getName());
/* 116 */       } catch (Throwable t) {
/* 120 */         errors.put(provider.getClass().getName(), createErrorMessage(t));
/*     */       } 
/*     */     } 
/* 124 */     if (emf == null) {
/* 125 */       StringBuffer message = new StringBuffer("No Persistence provider for EntityManager named " + persistenceUnitName + ": ");
/* 128 */       if (!exists("META-INF/persistence.xml")) {
/* 129 */         message.append(" No META-INF/persistence.xml was found in classpath.\n");
/*     */       } else {
/* 131 */         Map<String, String> reasons = getReasons();
/* 132 */         for (Map.Entry<String, String> me : reasons.entrySet()) {
/* 133 */           message.append("Provider named ");
/* 134 */           message.append(me.getKey());
/* 135 */           message.append(" threw exception at initialization: ");
/* 136 */           message.append((new StringBuilder()).append(me.getValue()).append("\n").toString());
/*     */         } 
/* 139 */         for (Map.Entry<String, String> me : errors.entrySet()) {
/* 140 */           message.append("Provider named ");
/* 141 */           message.append(me.getKey());
/* 142 */           message.append(" threw unexpected exception at create EntityManagerFactory: \n");
/* 143 */           message.append((new StringBuilder()).append(me.getValue()).append("\n").toString());
/*     */         } 
/* 146 */         if (!returnedNull.isEmpty()) {
/* 147 */           message.append(" The following providers:\n");
/* 148 */           for (String n : returnedNull)
/* 149 */             message.append(n + "\n"); 
/* 151 */           message.append("Returned null to createEntityManagerFactory.\n");
/*     */         } 
/*     */       } 
/* 154 */       throw new PersistenceException(message.toString());
/*     */     } 
/* 156 */     return emf;
/*     */   }
/*     */   
/*     */   private static Set<PersistenceProvider> findAllProviders() throws IOException {
/* 162 */     HashSet<PersistenceProvider> providersFound = new HashSet<PersistenceProvider>();
/* 163 */     ClassLoader loader = Thread.currentThread().getContextClassLoader();
/* 164 */     Enumeration<URL> resources = loader.getResources(SERVICE_NAME);
/* 166 */     if (!resources.hasMoreElements())
/* 167 */       throw new PersistenceException("No resource files named " + SERVICE_NAME + " were found. Please make sure that the persistence provider jar file is in your classpath."); 
/* 170 */     Set<String> names = new HashSet<String>();
/* 171 */     while (resources.hasMoreElements()) {
/* 172 */       URL url = resources.nextElement();
/* 173 */       InputStream is = url.openStream();
/*     */       try {
/* 175 */         names.addAll(providerNamesFromReader(new BufferedReader(new InputStreamReader(is))));
/*     */       } finally {
/* 177 */         is.close();
/*     */       } 
/*     */     } 
/* 181 */     if (names.isEmpty())
/* 182 */       throw new PersistenceException("No provider names were found in " + SERVICE_NAME); 
/* 184 */     for (String s : names) {
/*     */       try {
/* 186 */         providersFound.add((PersistenceProvider)loader.loadClass(s).newInstance());
/* 187 */       } catch (ClassNotFoundException exc) {
/*     */       
/* 188 */       } catch (InstantiationException exc) {
/*     */       
/* 189 */       } catch (IllegalAccessException exc) {}
/*     */     } 
/* 192 */     return providersFound;
/*     */   }
/*     */   
/* 195 */   private static final Pattern nonCommentPattern = Pattern.compile("^([^#]+)");
/*     */   
/*     */   private static Set<String> providerNamesFromReader(BufferedReader reader) throws IOException {
/* 199 */     Set<String> names = new HashSet<String>();
/*     */     String line;
/* 201 */     while ((line = reader.readLine()) != null) {
/* 202 */       line = line.trim();
/* 203 */       Matcher m = nonCommentPattern.matcher(line);
/* 204 */       if (m.find())
/* 205 */         names.add(m.group().trim()); 
/*     */     } 
/* 208 */     return names;
/*     */   }
/*     */   
/*     */   private static boolean exists(String fileName) {
/*     */     Enumeration enumeration;
/* 212 */     ClassLoader loader = Thread.currentThread().getContextClassLoader();
/*     */     try {
/* 215 */       enumeration = loader.getResources(fileName);
/* 216 */     } catch (IOException ex) {
/* 217 */       enumeration = null;
/*     */     } 
/* 219 */     return (enumeration == null) ? false : enumeration.hasMoreElements();
/*     */   }
/*     */   
/*     */   private static Map<String, String> getReasons() {
/* 223 */     Map<String, String> reasons = new HashMap<String, String>();
/* 224 */     ClassLoader loader = Thread.currentThread().getContextClassLoader();
/* 225 */     Set<String> names = new HashSet<String>();
/*     */     try {
/* 228 */       Enumeration<URL> resources = loader.getResources(SERVICE_NAME);
/* 229 */       while (resources.hasMoreElements()) {
/* 230 */         URL url = resources.nextElement();
/* 231 */         InputStream is = url.openStream();
/*     */         try {
/* 233 */           names.addAll(providerNamesFromReader(new BufferedReader(new InputStreamReader(is))));
/*     */         } finally {
/* 235 */           is.close();
/*     */         } 
/*     */       } 
/* 238 */     } catch (IOException exc) {}
/* 240 */     for (String s : names) {
/*     */       try {
/* 242 */         loader.loadClass(s).newInstance();
/* 243 */       } catch (ClassNotFoundException exc) {
/* 244 */         reasons.put(s, exc.getClass().getName() + " " + exc.getMessage());
/* 245 */       } catch (InstantiationException exc) {
/* 246 */         reasons.put(s, createErrorMessage(exc));
/* 247 */       } catch (IllegalAccessException exc) {
/* 248 */         reasons.put(s, createErrorMessage(exc));
/* 249 */       } catch (RuntimeException exc) {
/* 250 */         reasons.put(s, createErrorMessage(exc));
/*     */       } 
/*     */     } 
/* 253 */     return reasons;
/*     */   }
/*     */   
/*     */   private static String createErrorMessage(Throwable t) {
/* 257 */     StringWriter errorMessage = new StringWriter();
/* 258 */     errorMessage.append(t.getClass().getName()).append("\r\n");
/* 259 */     t.printStackTrace(new PrintWriter(errorMessage));
/* 260 */     errorMessage.append("\r\n");
/* 261 */     return errorMessage.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\Persistence.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */