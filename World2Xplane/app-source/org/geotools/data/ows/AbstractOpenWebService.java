/*     */ package org.geotools.data.ows;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.ResourceInfo;
/*     */ import org.geotools.data.ServiceInfo;
/*     */ import org.geotools.ows.ServiceException;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public abstract class AbstractOpenWebService<C extends Capabilities, R> {
/*     */   private HTTPClient httpClient;
/*     */   
/*     */   protected final URL serverURL;
/*     */   
/*     */   protected C capabilities;
/*     */   
/*     */   protected ServiceInfo info;
/*     */   
/*  58 */   protected Map<R, ResourceInfo> resourceInfo = new HashMap<R, ResourceInfo>();
/*     */   
/*     */   protected Specification[] specs;
/*     */   
/*     */   protected Specification specification;
/*     */   
/*  64 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.ows");
/*     */   
/*     */   public AbstractOpenWebService(URL serverURL) throws IOException, ServiceException {
/*  75 */     this(serverURL, new SimpleHttpClient(), null);
/*     */   }
/*     */   
/*     */   public AbstractOpenWebService(URL serverURL, int requestTimeout) throws IOException, ServiceException {
/*  83 */     this(serverURL, new SimpleHttpClient(), null);
/*  84 */     this.httpClient.setConnectTimeout(requestTimeout);
/*  85 */     this.httpClient.setReadTimeout(requestTimeout);
/*     */   }
/*     */   
/*     */   public AbstractOpenWebService(C capabilties, URL serverURL) throws ServiceException, IOException {
/*  92 */     this(serverURL, new SimpleHttpClient(), capabilties);
/*     */   }
/*     */   
/*     */   public AbstractOpenWebService(URL serverURL, HTTPClient httpClient, C capabilities) throws ServiceException, IOException {
/*  97 */     if (serverURL == null)
/*  98 */       throw new NullPointerException("serverURL"); 
/* 100 */     if (httpClient == null)
/* 101 */       throw new NullPointerException("httpClient"); 
/* 104 */     this.serverURL = serverURL;
/* 105 */     this.httpClient = httpClient;
/* 107 */     setupSpecifications();
/* 109 */     if (capabilities == null) {
/* 110 */       this.capabilities = negotiateVersion();
/* 111 */       if (this.capabilities == null)
/* 112 */         throw new ServiceException("Unable to retrieve or parse Capabilities document."); 
/*     */     } else {
/* 115 */       this.capabilities = capabilities;
/*     */     } 
/* 118 */     for (int i = 0; i < this.specs.length; i++) {
/* 119 */       if (this.specs[i].getVersion().equals(this.capabilities.getVersion())) {
/* 120 */         this.specification = this.specs[i];
/*     */         break;
/*     */       } 
/*     */     } 
/* 125 */     if (this.specification == null) {
/* 126 */       this.specification = this.specs[this.specs.length - 1];
/* 127 */       LOGGER.warning("Unable to choose a specification based on cached capabilities. Arbitrarily choosing spec '" + this.specification.getVersion() + "'.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setHttpClient(HTTPClient httpClient) {
/* 133 */     this.httpClient = httpClient;
/*     */   }
/*     */   
/*     */   public HTTPClient getHTTPClient() {
/* 137 */     return this.httpClient;
/*     */   }
/*     */   
/*     */   public abstract C getCapabilities();
/*     */   
/*     */   public ServiceInfo getInfo() {
/* 158 */     synchronized (this.capabilities) {
/* 159 */       if (this.info == null && this.capabilities != null)
/* 160 */         this.info = createInfo(); 
/* 162 */       return this.info;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract ServiceInfo createInfo();
/*     */   
/*     */   public ResourceInfo getInfo(R resource) {
/* 172 */     synchronized (this.capabilities) {
/* 173 */       if (!this.resourceInfo.containsKey(resource))
/* 174 */         this.resourceInfo.put(resource, createInfo(resource)); 
/*     */     } 
/* 177 */     return this.resourceInfo.get(resource);
/*     */   }
/*     */   
/*     */   protected abstract ResourceInfo createInfo(R paramR);
/*     */   
/*     */   private void syncrhonized(Capabilities capabilities2) {}
/*     */   
/*     */   protected abstract void setupSpecifications();
/*     */   
/*     */   protected C negotiateVersion() throws IOException, ServiceException {
/*     */     ServiceException serviceException;
/* 227 */     List<String> versions = new ArrayList<String>(this.specs.length);
/* 228 */     Exception exception = null;
/* 230 */     for (int i = 0; i < this.specs.length; i++)
/* 231 */       versions.add(i, this.specs[i].getVersion()); 
/* 234 */     int minClient = 0;
/* 235 */     int maxClient = this.specs.length - 1;
/* 237 */     int test = maxClient;
/* 239 */     if (this.serverURL.getQuery() != null) {
/* 240 */       String[] tokens = this.serverURL.getQuery().split("&");
/* 241 */       for (String token : tokens) {
/* 242 */         String[] param = token.split("=");
/* 243 */         if (param != null && param.length > 1 && param[0] != null && param[0].equalsIgnoreCase("version"))
/* 245 */           if (versions.contains(param[1]))
/* 246 */             test = versions.indexOf(param[1]);  
/*     */       } 
/*     */     } 
/* 251 */     while (minClient <= test && test <= maxClient) {
/*     */       Capabilities capabilities;
/* 252 */       Specification tempSpecification = this.specs[test];
/* 253 */       String clientVersion = tempSpecification.getVersion();
/* 255 */       GetCapabilitiesRequest request = tempSpecification.createGetCapabilitiesRequest(this.serverURL);
/*     */       try {
/* 260 */         capabilities = issueRequest(request).getCapabilities();
/* 261 */       } catch (ServiceException e) {
/* 262 */         capabilities = null;
/* 263 */         serviceException = e;
/*     */       } 
/* 266 */       int compare = -1;
/* 267 */       String serverVersion = clientVersion;
/* 269 */       if (capabilities != null) {
/* 271 */         serverVersion = capabilities.getVersion();
/* 273 */         compare = serverVersion.compareTo(clientVersion);
/*     */       } 
/* 276 */       if (compare == 0) {
/* 278 */         this.specification = tempSpecification;
/* 280 */         return (C)capabilities;
/*     */       } 
/* 283 */       if (capabilities != null && versions.contains(serverVersion)) {
/* 285 */         int index = versions.indexOf(serverVersion);
/* 286 */         this.specification = this.specs[index];
/* 288 */         return (C)capabilities;
/*     */       } 
/* 290 */       if (compare < 0) {
/* 292 */         maxClient = test - 1;
/* 296 */         clientVersion = before(versions, serverVersion);
/* 298 */         if (clientVersion == null) {
/* 299 */           if (serviceException != null) {
/* 300 */             if (serviceException instanceof ServiceException)
/* 301 */               throw serviceException; 
/* 303 */             IOException e = new IOException(serviceException.getMessage());
/* 304 */             throw e;
/*     */           } 
/* 306 */           return null;
/*     */         } 
/* 309 */         test = versions.indexOf(clientVersion);
/*     */         continue;
/*     */       } 
/* 312 */       minClient = test + 1;
/* 315 */       clientVersion = after(versions, serverVersion);
/* 317 */       if (clientVersion == null) {
/* 318 */         if (serviceException != null) {
/* 319 */           if (serviceException instanceof ServiceException)
/* 320 */             throw serviceException; 
/* 322 */           IOException e = new IOException(serviceException.getMessage());
/* 323 */           throw e;
/*     */         } 
/* 325 */         return null;
/*     */       } 
/* 328 */       test = versions.indexOf(clientVersion);
/*     */     } 
/* 333 */     if (serviceException != null) {
/* 334 */       IOException e = new IOException(serviceException.getMessage());
/* 335 */       throw e;
/*     */     } 
/* 337 */     return null;
/*     */   }
/*     */   
/*     */   String before(List known, String version) {
/* 348 */     if (known.isEmpty())
/* 349 */       return null; 
/* 352 */     String before = null;
/* 354 */     for (Iterator<String> i = known.iterator(); i.hasNext(); ) {
/* 355 */       String test = i.next();
/* 357 */       if (test.compareTo(version) < 0)
/* 359 */         if (before == null || before.compareTo(test) < 0)
/* 360 */           before = test;  
/*     */     } 
/* 365 */     return before;
/*     */   }
/*     */   
/*     */   String after(List known, String version) {
/* 376 */     if (known.isEmpty())
/* 377 */       return null; 
/* 380 */     String after = null;
/* 382 */     for (Iterator<String> i = known.iterator(); i.hasNext(); ) {
/* 383 */       String test = i.next();
/* 385 */       if (test.compareTo(version) > 0 && (
/* 386 */         after == null || after.compareTo(test) < 0))
/* 387 */         after = test; 
/*     */     } 
/* 392 */     return after;
/*     */   }
/*     */   
/*     */   protected Response internalIssueRequest(Request request) throws IOException, ServiceException {
/*     */     HTTPResponse httpResponse;
/* 406 */     URL finalURL = request.getFinalURL();
/* 410 */     if (request.requiresPost()) {
/* 412 */       String postContentType = request.getPostContentType();
/* 414 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 415 */       request.performPostOutput(out);
/* 416 */       InputStream in = new ByteArrayInputStream(out.toByteArray());
/*     */       try {
/* 419 */         httpResponse = this.httpClient.post(finalURL, in, postContentType);
/*     */       } finally {
/* 421 */         in.close();
/*     */       } 
/*     */     } else {
/* 424 */       httpResponse = this.httpClient.get(finalURL);
/*     */     } 
/* 427 */     Response response = request.createResponse(httpResponse);
/* 429 */     if (LOGGER.isLoggable(Level.FINE))
/* 430 */       LOGGER.fine("Executed request to URL: " + finalURL.toExternalForm()); 
/* 433 */     return response;
/*     */   }
/*     */   
/*     */   public GetCapabilitiesResponse issueRequest(GetCapabilitiesRequest request) throws IOException, ServiceException {
/* 437 */     return (GetCapabilitiesResponse)internalIssueRequest(request);
/*     */   }
/*     */   
/*     */   public void setLoggingLevel(Level newLevel) {
/* 441 */     LOGGER.setLevel(newLevel);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\AbstractOpenWebService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */