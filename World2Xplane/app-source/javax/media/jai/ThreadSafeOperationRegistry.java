/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.RWLock;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ 
/*     */ final class ThreadSafeOperationRegistry extends OperationRegistry {
/*  41 */   private RWLock lock = new RWLock(true);
/*     */   
/*     */   public String toString() {
/*     */     try {
/*  46 */       this.lock.forReading();
/*  47 */       String t = super.toString();
/*  48 */       this.lock.release();
/*  49 */       return t;
/*  50 */     } catch (RuntimeException e) {
/*  51 */       this.lock.release();
/*  52 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeToStream(OutputStream out) throws IOException {
/*     */     try {
/*  58 */       this.lock.forReading();
/*  59 */       super.writeToStream(out);
/*  60 */       this.lock.release();
/*  61 */     } catch (IOException ioe) {
/*  62 */       this.lock.release();
/*  63 */       throw ioe;
/*  64 */     } catch (RuntimeException e) {
/*  65 */       this.lock.release();
/*  66 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initializeFromStream(InputStream in) throws IOException {
/*     */     try {
/*  72 */       this.lock.forWriting();
/*  73 */       super.initializeFromStream(in);
/*  74 */       this.lock.release();
/*  75 */     } catch (IOException ioe) {
/*  76 */       this.lock.release();
/*  77 */       throw ioe;
/*  78 */     } catch (RuntimeException e) {
/*  79 */       this.lock.release();
/*  80 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateFromStream(InputStream in) throws IOException {
/*     */     try {
/*  86 */       this.lock.forWriting();
/*  87 */       super.updateFromStream(in);
/*  88 */       this.lock.release();
/*  89 */     } catch (IOException ioe) {
/*  90 */       this.lock.release();
/*  91 */       throw ioe;
/*  92 */     } catch (RuntimeException e) {
/*  93 */       this.lock.release();
/*  94 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*     */     try {
/* 102 */       this.lock.forWriting();
/* 103 */       super.readExternal(in);
/* 104 */       this.lock.release();
/* 105 */     } catch (IOException ioe) {
/* 106 */       this.lock.release();
/* 107 */       throw ioe;
/* 108 */     } catch (RuntimeException e) {
/* 109 */       this.lock.release();
/* 110 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/*     */     try {
/* 116 */       this.lock.forReading();
/* 117 */       super.writeExternal(out);
/* 118 */       this.lock.release();
/* 119 */     } catch (IOException ioe) {
/* 120 */       this.lock.release();
/* 121 */       throw ioe;
/* 122 */     } catch (RuntimeException e) {
/* 123 */       this.lock.release();
/* 124 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeRegistryMode(String modeName) {
/*     */     try {
/* 132 */       this.lock.forWriting();
/* 133 */       super.removeRegistryMode(modeName);
/* 134 */       this.lock.release();
/* 135 */     } catch (RuntimeException e) {
/* 136 */       this.lock.release();
/* 137 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getRegistryModes() {
/*     */     try {
/* 143 */       this.lock.forReading();
/* 144 */       String[] t = super.getRegistryModes();
/* 145 */       this.lock.release();
/* 146 */       return t;
/* 147 */     } catch (RuntimeException e) {
/* 148 */       this.lock.release();
/* 149 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void registerDescriptor(RegistryElementDescriptor descriptor) {
/*     */     try {
/* 155 */       this.lock.forWriting();
/* 156 */       super.registerDescriptor(descriptor);
/* 157 */       this.lock.release();
/* 158 */     } catch (RuntimeException e) {
/* 159 */       this.lock.release();
/* 160 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unregisterDescriptor(RegistryElementDescriptor descriptor) {
/*     */     try {
/* 166 */       this.lock.forWriting();
/* 167 */       super.unregisterDescriptor(descriptor);
/* 168 */       this.lock.release();
/* 169 */     } catch (RuntimeException e) {
/* 170 */       this.lock.release();
/* 171 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RegistryElementDescriptor getDescriptor(Class descriptorClass, String descriptorName) {
/*     */     try {
/* 178 */       this.lock.forReading();
/* 179 */       RegistryElementDescriptor t = super.getDescriptor(descriptorClass, descriptorName);
/* 180 */       this.lock.release();
/* 181 */       return t;
/* 182 */     } catch (RuntimeException e) {
/* 183 */       this.lock.release();
/* 184 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getDescriptors(Class descriptorClass) {
/*     */     try {
/* 190 */       this.lock.forReading();
/* 191 */       List t = super.getDescriptors(descriptorClass);
/* 192 */       this.lock.release();
/* 193 */       return t;
/* 194 */     } catch (RuntimeException e) {
/* 195 */       this.lock.release();
/* 196 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getDescriptorNames(Class descriptorClass) {
/*     */     try {
/* 202 */       this.lock.forReading();
/* 203 */       String[] t = super.getDescriptorNames(descriptorClass);
/* 204 */       this.lock.release();
/* 205 */       return t;
/* 206 */     } catch (RuntimeException e) {
/* 207 */       this.lock.release();
/* 208 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RegistryElementDescriptor getDescriptor(String modeName, String descriptorName) {
/*     */     try {
/* 215 */       this.lock.forReading();
/* 216 */       RegistryElementDescriptor t = super.getDescriptor(modeName, descriptorName);
/* 217 */       this.lock.release();
/* 218 */       return t;
/* 219 */     } catch (RuntimeException e) {
/* 220 */       this.lock.release();
/* 221 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getDescriptors(String modeName) {
/*     */     try {
/* 228 */       this.lock.forReading();
/* 229 */       List t = super.getDescriptors(modeName);
/* 230 */       this.lock.release();
/* 231 */       return t;
/* 232 */     } catch (RuntimeException e) {
/* 233 */       this.lock.release();
/* 234 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getDescriptorNames(String modeName) {
/*     */     try {
/* 240 */       this.lock.forReading();
/* 241 */       String[] t = super.getDescriptorNames(modeName);
/* 242 */       this.lock.release();
/* 243 */       return t;
/* 244 */     } catch (RuntimeException e) {
/* 245 */       this.lock.release();
/* 246 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setProductPreference(String modeName, String descriptorName, String preferredProductName, String otherProductName) {
/*     */     try {
/* 255 */       this.lock.forWriting();
/* 256 */       super.setProductPreference(modeName, descriptorName, preferredProductName, otherProductName);
/* 260 */       this.lock.release();
/* 261 */     } catch (RuntimeException e) {
/* 262 */       this.lock.release();
/* 263 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unsetProductPreference(String modeName, String descriptorName, String preferredProductName, String otherProductName) {
/*     */     try {
/* 272 */       this.lock.forWriting();
/* 273 */       super.unsetProductPreference(modeName, descriptorName, preferredProductName, otherProductName);
/* 277 */       this.lock.release();
/* 278 */     } catch (RuntimeException e) {
/* 279 */       this.lock.release();
/* 280 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearProductPreferences(String modeName, String descriptorName) {
/*     */     try {
/* 287 */       this.lock.forWriting();
/* 288 */       super.clearProductPreferences(modeName, descriptorName);
/* 289 */       this.lock.release();
/* 290 */     } catch (RuntimeException e) {
/* 291 */       this.lock.release();
/* 292 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[][] getProductPreferences(String modeName, String descriptorName) {
/*     */     try {
/* 299 */       this.lock.forReading();
/* 300 */       String[][] t = super.getProductPreferences(modeName, descriptorName);
/* 301 */       this.lock.release();
/* 302 */       return t;
/* 303 */     } catch (RuntimeException e) {
/* 304 */       this.lock.release();
/* 305 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Vector getOrderedProductList(String modeName, String descriptorName) {
/*     */     try {
/* 313 */       this.lock.forReading();
/* 314 */       Vector t = super.getOrderedProductList(modeName, descriptorName);
/* 315 */       this.lock.release();
/* 316 */       return t;
/* 317 */     } catch (RuntimeException e) {
/* 318 */       this.lock.release();
/* 319 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void registerFactory(String modeName, String descriptorName, String productName, Object factory) {
/*     */     try {
/* 329 */       this.lock.forWriting();
/* 330 */       super.registerFactory(modeName, descriptorName, productName, factory);
/* 334 */       this.lock.release();
/* 335 */     } catch (RuntimeException e) {
/* 336 */       this.lock.release();
/* 337 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unregisterFactory(String modeName, String descriptorName, String productName, Object factory) {
/*     */     try {
/* 346 */       this.lock.forWriting();
/* 347 */       super.unregisterFactory(modeName, descriptorName, productName, factory);
/* 351 */       this.lock.release();
/* 352 */     } catch (RuntimeException e) {
/* 353 */       this.lock.release();
/* 354 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFactoryPreference(String modeName, String descriptorName, String productName, Object preferredOp, Object otherOp) {
/*     */     try {
/* 364 */       this.lock.forWriting();
/* 365 */       super.setFactoryPreference(modeName, descriptorName, productName, preferredOp, otherOp);
/* 370 */       this.lock.release();
/* 371 */     } catch (RuntimeException e) {
/* 372 */       this.lock.release();
/* 373 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unsetFactoryPreference(String modeName, String descriptorName, String productName, Object preferredOp, Object otherOp) {
/*     */     try {
/* 383 */       this.lock.forWriting();
/* 384 */       super.unsetFactoryPreference(modeName, descriptorName, productName, preferredOp, otherOp);
/* 389 */       this.lock.release();
/* 390 */     } catch (RuntimeException e) {
/* 391 */       this.lock.release();
/* 392 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearFactoryPreferences(String modeName, String descriptorName, String productName) {
/*     */     try {
/* 400 */       this.lock.forWriting();
/* 401 */       super.clearFactoryPreferences(modeName, descriptorName, productName);
/* 404 */       this.lock.release();
/* 405 */     } catch (RuntimeException e) {
/* 406 */       this.lock.release();
/* 407 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[][] getFactoryPreferences(String modeName, String descriptorName, String productName) {
/*     */     try {
/* 415 */       this.lock.forReading();
/* 416 */       Object[][] t = super.getFactoryPreferences(modeName, descriptorName, productName);
/* 418 */       this.lock.release();
/* 419 */       return t;
/* 420 */     } catch (RuntimeException e) {
/* 421 */       this.lock.release();
/* 422 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getOrderedFactoryList(String modeName, String descriptorName, String productName) {
/*     */     try {
/* 430 */       this.lock.forReading();
/* 431 */       List t = super.getOrderedFactoryList(modeName, descriptorName, productName);
/* 434 */       this.lock.release();
/* 435 */       return t;
/* 436 */     } catch (RuntimeException e) {
/* 437 */       this.lock.release();
/* 438 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator getFactoryIterator(String modeName, String descriptorName) {
/*     */     try {
/* 445 */       this.lock.forReading();
/* 446 */       Iterator t = super.getFactoryIterator(modeName, descriptorName);
/* 447 */       this.lock.release();
/* 448 */       return t;
/* 449 */     } catch (RuntimeException e) {
/* 450 */       this.lock.release();
/* 451 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getFactory(String modeName, String descriptorName) {
/*     */     try {
/* 457 */       this.lock.forReading();
/* 458 */       Object t = super.getFactory(modeName, descriptorName);
/* 459 */       this.lock.release();
/* 460 */       return t;
/* 461 */     } catch (RuntimeException e) {
/* 462 */       this.lock.release();
/* 463 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object invokeFactory(String modeName, String descriptorName, Object[] args) {
/*     */     try {
/* 472 */       this.lock.forReading();
/* 473 */       Object t = super.invokeFactory(modeName, descriptorName, args);
/* 474 */       this.lock.release();
/* 475 */       return t;
/* 476 */     } catch (RuntimeException e) {
/* 477 */       this.lock.release();
/* 478 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPropertyGenerator(String modeName, String descriptorName, PropertyGenerator generator) {
/*     */     try {
/* 487 */       this.lock.forWriting();
/* 488 */       super.addPropertyGenerator(modeName, descriptorName, generator);
/* 491 */       this.lock.release();
/* 492 */     } catch (RuntimeException e) {
/* 493 */       this.lock.release();
/* 494 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removePropertyGenerator(String modeName, String descriptorName, PropertyGenerator generator) {
/*     */     try {
/* 502 */       this.lock.forWriting();
/* 503 */       super.removePropertyGenerator(modeName, descriptorName, generator);
/* 506 */       this.lock.release();
/* 507 */     } catch (RuntimeException e) {
/* 508 */       this.lock.release();
/* 509 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void copyPropertyFromSource(String modeName, String descriptorName, String propertyName, int sourceIndex) {
/*     */     try {
/* 518 */       this.lock.forWriting();
/* 519 */       super.copyPropertyFromSource(modeName, descriptorName, propertyName, sourceIndex);
/* 523 */       this.lock.release();
/* 524 */     } catch (RuntimeException e) {
/* 525 */       this.lock.release();
/* 526 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void suppressProperty(String modeName, String descriptorName, String propertyName) {
/*     */     try {
/* 534 */       this.lock.forWriting();
/* 535 */       super.suppressProperty(modeName, descriptorName, propertyName);
/* 538 */       this.lock.release();
/* 539 */     } catch (RuntimeException e) {
/* 540 */       this.lock.release();
/* 541 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void suppressAllProperties(String modeName, String descriptorName) {
/*     */     try {
/* 548 */       this.lock.forWriting();
/* 549 */       super.suppressAllProperties(modeName, descriptorName);
/* 550 */       this.lock.release();
/* 551 */     } catch (RuntimeException e) {
/* 552 */       this.lock.release();
/* 553 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearPropertyState(String modeName) {
/*     */     try {
/* 559 */       this.lock.forWriting();
/* 560 */       super.clearPropertyState(modeName);
/* 561 */       this.lock.release();
/* 562 */     } catch (RuntimeException e) {
/* 563 */       this.lock.release();
/* 564 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getGeneratedPropertyNames(String modeName, String descriptorName) {
/*     */     try {
/* 571 */       this.lock.forReading();
/* 572 */       String[] t = super.getGeneratedPropertyNames(modeName, descriptorName);
/* 573 */       this.lock.release();
/* 574 */       return t;
/* 575 */     } catch (RuntimeException e) {
/* 576 */       this.lock.release();
/* 577 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PropertySource getPropertySource(String modeName, String descriptorName, Object op, Vector sources) {
/*     */     try {
/* 586 */       this.lock.forReading();
/* 587 */       PropertySource t = super.getPropertySource(modeName, descriptorName, op, sources);
/* 589 */       this.lock.release();
/* 590 */       return t;
/* 591 */     } catch (RuntimeException e) {
/* 592 */       this.lock.release();
/* 593 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PropertySource getPropertySource(OperationNode op) {
/*     */     try {
/* 599 */       this.lock.forReading();
/* 600 */       PropertySource t = super.getPropertySource(op);
/* 601 */       this.lock.release();
/* 602 */       return t;
/* 603 */     } catch (RuntimeException e) {
/* 604 */       this.lock.release();
/* 605 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void registerServices(ClassLoader cl) throws IOException {
/*     */     try {
/* 611 */       this.lock.forWriting();
/* 612 */       super.registerServices(cl);
/* 613 */       this.lock.release();
/* 614 */     } catch (RuntimeException e) {
/* 615 */       this.lock.release();
/* 616 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unregisterOperationDescriptor(String operationName) {
/*     */     try {
/* 624 */       this.lock.forWriting();
/* 625 */       super.unregisterOperationDescriptor(operationName);
/* 626 */       this.lock.release();
/* 627 */     } catch (RuntimeException e) {
/* 628 */       this.lock.release();
/* 629 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearOperationPreferences(String operationName, String productName) {
/*     */     try {
/* 636 */       this.lock.forWriting();
/* 637 */       super.clearOperationPreferences(operationName, productName);
/* 638 */       this.lock.release();
/* 639 */     } catch (RuntimeException e) {
/* 640 */       this.lock.release();
/* 641 */       throw e;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ThreadSafeOperationRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */