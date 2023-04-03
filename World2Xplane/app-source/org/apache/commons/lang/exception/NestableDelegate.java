/*     */ package org.apache.commons.lang.exception;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class NestableDelegate implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private static final transient String MUST_BE_THROWABLE = "The Nestable implementation passed to the NestableDelegate(Nestable) constructor must extend java.lang.Throwable";
/*     */   
/*  68 */   private Throwable nestable = null;
/*     */   
/*     */   public static boolean topDown = true;
/*     */   
/*     */   public static boolean trimStackFrames = true;
/*     */   
/*     */   public static boolean matchSubclasses = true;
/*     */   
/*     */   public NestableDelegate(Nestable nestable) {
/* 109 */     if (nestable instanceof Throwable) {
/* 110 */       this.nestable = (Throwable)nestable;
/*     */     } else {
/* 112 */       throw new IllegalArgumentException("The Nestable implementation passed to the NestableDelegate(Nestable) constructor must extend java.lang.Throwable");
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getMessage(int index) {
/* 130 */     Throwable t = getThrowable(index);
/* 131 */     if (Nestable.class.isInstance(t))
/* 132 */       return ((Nestable)t).getMessage(0); 
/* 134 */     return t.getMessage();
/*     */   }
/*     */   
/*     */   public String getMessage(String baseMsg) {
/* 148 */     Throwable nestedCause = ExceptionUtils.getCause(this.nestable);
/* 149 */     String causeMsg = (nestedCause == null) ? null : nestedCause.getMessage();
/* 150 */     if (nestedCause == null || causeMsg == null)
/* 151 */       return baseMsg; 
/* 153 */     if (baseMsg == null)
/* 154 */       return causeMsg; 
/* 156 */     return baseMsg + ": " + causeMsg;
/*     */   }
/*     */   
/*     */   public String[] getMessages() {
/* 169 */     Throwable[] throwables = getThrowables();
/* 170 */     String[] msgs = new String[throwables.length];
/* 171 */     for (int i = 0; i < throwables.length; i++)
/* 172 */       msgs[i] = Nestable.class.isInstance(throwables[i]) ? ((Nestable)throwables[i]).getMessage(0) : throwables[i].getMessage(); 
/* 177 */     return msgs;
/*     */   }
/*     */   
/*     */   public Throwable getThrowable(int index) {
/* 193 */     if (index == 0)
/* 194 */       return this.nestable; 
/* 196 */     Throwable[] throwables = getThrowables();
/* 197 */     return throwables[index];
/*     */   }
/*     */   
/*     */   public int getThrowableCount() {
/* 208 */     return ExceptionUtils.getThrowableCount(this.nestable);
/*     */   }
/*     */   
/*     */   public Throwable[] getThrowables() {
/* 220 */     return ExceptionUtils.getThrowables(this.nestable);
/*     */   }
/*     */   
/*     */   public int indexOfThrowable(Class type, int fromIndex) {
/* 248 */     if (type == null)
/* 249 */       return -1; 
/* 251 */     if (fromIndex < 0)
/* 252 */       throw new IndexOutOfBoundsException("The start index was out of bounds: " + fromIndex); 
/* 254 */     Throwable[] throwables = ExceptionUtils.getThrowables(this.nestable);
/* 255 */     if (fromIndex >= throwables.length)
/* 256 */       throw new IndexOutOfBoundsException("The start index was out of bounds: " + fromIndex + " >= " + throwables.length); 
/* 259 */     if (matchSubclasses) {
/* 260 */       for (int i = fromIndex; i < throwables.length; i++) {
/* 261 */         if (type.isAssignableFrom(throwables[i].getClass()))
/* 262 */           return i; 
/*     */       } 
/*     */     } else {
/* 266 */       for (int i = fromIndex; i < throwables.length; i++) {
/* 267 */         if (type.equals(throwables[i].getClass()))
/* 268 */           return i; 
/*     */       } 
/*     */     } 
/* 272 */     return -1;
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 280 */     printStackTrace(System.err);
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 291 */     synchronized (out) {
/* 292 */       PrintWriter pw = new PrintWriter(out, false);
/* 293 */       printStackTrace(pw);
/* 295 */       pw.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 311 */     Throwable throwable = this.nestable;
/* 313 */     if (ExceptionUtils.isThrowableNested()) {
/* 314 */       if (throwable instanceof Nestable) {
/* 315 */         ((Nestable)throwable).printPartialStackTrace(out);
/*     */       } else {
/* 317 */         throwable.printStackTrace(out);
/*     */       } 
/*     */       return;
/*     */     } 
/* 323 */     List stacks = new ArrayList();
/* 324 */     while (throwable != null) {
/* 325 */       String[] st = getStackFrames(throwable);
/* 326 */       stacks.add(st);
/* 327 */       throwable = ExceptionUtils.getCause(throwable);
/*     */     } 
/* 331 */     String separatorLine = "Caused by: ";
/* 332 */     if (!topDown) {
/* 333 */       separatorLine = "Rethrown as: ";
/* 334 */       Collections.reverse(stacks);
/*     */     } 
/* 338 */     if (trimStackFrames)
/* 339 */       trimStackFrames(stacks); 
/* 342 */     synchronized (out) {
/* 343 */       for (Iterator iter = stacks.iterator(); iter.hasNext(); ) {
/* 344 */         String[] st = (String[])iter.next();
/* 345 */         for (int i = 0, len = st.length; i < len; i++)
/* 346 */           out.println(st[i]); 
/* 348 */         if (iter.hasNext())
/* 349 */           out.print(separatorLine); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String[] getStackFrames(Throwable t) {
/* 365 */     StringWriter sw = new StringWriter();
/* 366 */     PrintWriter pw = new PrintWriter(sw, true);
/* 369 */     if (t instanceof Nestable) {
/* 370 */       ((Nestable)t).printPartialStackTrace(pw);
/*     */     } else {
/* 372 */       t.printStackTrace(pw);
/*     */     } 
/* 374 */     return ExceptionUtils.getStackFrames(sw.getBuffer().toString());
/*     */   }
/*     */   
/*     */   protected void trimStackFrames(List stacks) {
/* 386 */     for (int size = stacks.size(), i = size - 1; i > 0; i--) {
/* 387 */       String[] curr = stacks.get(i);
/* 388 */       String[] next = stacks.get(i - 1);
/* 390 */       List currList = new ArrayList(Arrays.asList((Object[])curr));
/* 391 */       List nextList = new ArrayList(Arrays.asList((Object[])next));
/* 392 */       ExceptionUtils.removeCommonFrames(currList, nextList);
/* 394 */       int trimmed = curr.length - currList.size();
/* 395 */       if (trimmed > 0) {
/* 396 */         currList.add("\t... " + trimmed + " more");
/* 397 */         stacks.set(i, currList.toArray(new String[currList.size()]));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\exception\NestableDelegate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */