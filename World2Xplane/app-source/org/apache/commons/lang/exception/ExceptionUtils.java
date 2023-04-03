/*      */ package org.apache.commons.lang.exception;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringWriter;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.commons.lang.ArrayUtils;
/*      */ import org.apache.commons.lang.ClassUtils;
/*      */ import org.apache.commons.lang.NullArgumentException;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.apache.commons.lang.SystemUtils;
/*      */ 
/*      */ public class ExceptionUtils {
/*      */   static final String WRAPPED_MARKER = " [wrapped] ";
/*      */   
/*   60 */   private static final Object CAUSE_METHOD_NAMES_LOCK = new Object();
/*      */   
/*   65 */   private static String[] CAUSE_METHOD_NAMES = new String[] { 
/*   65 */       "getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", 
/*   65 */       "getLinkedCause", "getThrowable" };
/*      */   
/*      */   private static final Method THROWABLE_CAUSE_METHOD;
/*      */   
/*      */   private static final Method THROWABLE_INITCAUSE_METHOD;
/*      */   
/*      */   static {
/*      */     Method method;
/*      */   }
/*      */   
/*      */   static {
/*      */     try {
/*   93 */       method = Throwable.class.getMethod("getCause", null);
/*   94 */     } catch (Exception e) {
/*   95 */       method = null;
/*      */     } 
/*   97 */     THROWABLE_CAUSE_METHOD = method;
/*      */     try {
/*   99 */       method = Throwable.class.getMethod("initCause", new Class[] { Throwable.class });
/*  100 */     } catch (Exception e) {
/*  101 */       method = null;
/*      */     } 
/*  103 */     THROWABLE_INITCAUSE_METHOD = method;
/*      */   }
/*      */   
/*      */   public static void addCauseMethodName(String methodName) {
/*  126 */     if (StringUtils.isNotEmpty(methodName) && !isCauseMethodName(methodName)) {
/*  127 */       List list = getCauseMethodNameList();
/*  128 */       if (list.add(methodName))
/*  129 */         synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  130 */           CAUSE_METHOD_NAMES = toArray(list);
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void removeCauseMethodName(String methodName) {
/*  145 */     if (StringUtils.isNotEmpty(methodName)) {
/*  146 */       List list = getCauseMethodNameList();
/*  147 */       if (list.remove(methodName))
/*  148 */         synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  149 */           CAUSE_METHOD_NAMES = toArray(list);
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean setCause(Throwable target, Throwable cause) {
/*  184 */     if (target == null)
/*  185 */       throw new NullArgumentException("target"); 
/*  187 */     Object[] causeArgs = { cause };
/*  188 */     boolean modifiedTarget = false;
/*  189 */     if (THROWABLE_INITCAUSE_METHOD != null)
/*      */       try {
/*  191 */         THROWABLE_INITCAUSE_METHOD.invoke(target, causeArgs);
/*  192 */         modifiedTarget = true;
/*  193 */       } catch (IllegalAccessException ignored) {
/*      */       
/*  195 */       } catch (InvocationTargetException ignored) {} 
/*      */     try {
/*  200 */       Method setCauseMethod = target.getClass().getMethod("setCause", new Class[] { Throwable.class });
/*  201 */       setCauseMethod.invoke(target, causeArgs);
/*  202 */       modifiedTarget = true;
/*  203 */     } catch (NoSuchMethodException ignored) {
/*      */     
/*  205 */     } catch (IllegalAccessException ignored) {
/*      */     
/*  207 */     } catch (InvocationTargetException ignored) {}
/*  210 */     return modifiedTarget;
/*      */   }
/*      */   
/*      */   private static String[] toArray(List list) {
/*  219 */     return (String[])list.toArray((Object[])new String[list.size()]);
/*      */   }
/*      */   
/*      */   private static ArrayList getCauseMethodNameList() {
/*  228 */     synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  229 */       return new ArrayList(Arrays.asList((Object[])CAUSE_METHOD_NAMES));
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isCauseMethodName(String methodName) {
/*  243 */     synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  244 */       return (ArrayUtils.indexOf((Object[])CAUSE_METHOD_NAMES, methodName) >= 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Throwable getCause(Throwable throwable) {
/*  281 */     synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  282 */       return getCause(throwable, CAUSE_METHOD_NAMES);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Throwable getCause(Throwable throwable, String[] methodNames) {
/*  305 */     if (throwable == null)
/*  306 */       return null; 
/*  308 */     Throwable cause = getCauseUsingWellKnownTypes(throwable);
/*  309 */     if (cause == null) {
/*  310 */       if (methodNames == null)
/*  311 */         synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  312 */           methodNames = CAUSE_METHOD_NAMES;
/*      */         }  
/*  315 */       for (int i = 0; i < methodNames.length; i++) {
/*  316 */         String methodName = methodNames[i];
/*  317 */         if (methodName != null) {
/*  318 */           cause = getCauseUsingMethodName(throwable, methodName);
/*  319 */           if (cause != null)
/*      */             break; 
/*      */         } 
/*      */       } 
/*  325 */       if (cause == null)
/*  326 */         cause = getCauseUsingFieldName(throwable, "detail"); 
/*      */     } 
/*  329 */     return cause;
/*      */   }
/*      */   
/*      */   public static Throwable getRootCause(Throwable throwable) {
/*  350 */     List list = getThrowableList(throwable);
/*  351 */     return (list.size() < 2) ? null : list.get(list.size() - 1);
/*      */   }
/*      */   
/*      */   private static Throwable getCauseUsingWellKnownTypes(Throwable throwable) {
/*  365 */     if (throwable instanceof Nestable)
/*  366 */       return ((Nestable)throwable).getCause(); 
/*  367 */     if (throwable instanceof SQLException)
/*  368 */       return ((SQLException)throwable).getNextException(); 
/*  369 */     if (throwable instanceof InvocationTargetException)
/*  370 */       return ((InvocationTargetException)throwable).getTargetException(); 
/*  372 */     return null;
/*      */   }
/*      */   
/*      */   private static Throwable getCauseUsingMethodName(Throwable throwable, String methodName) {
/*  384 */     Method method = null;
/*      */     try {
/*  386 */       method = throwable.getClass().getMethod(methodName, null);
/*  387 */     } catch (NoSuchMethodException ignored) {
/*      */     
/*  389 */     } catch (SecurityException ignored) {}
/*  393 */     if (method != null && Throwable.class.isAssignableFrom(method.getReturnType()))
/*      */       try {
/*  395 */         return (Throwable)method.invoke(throwable, ArrayUtils.EMPTY_OBJECT_ARRAY);
/*  396 */       } catch (IllegalAccessException ignored) {
/*      */       
/*  398 */       } catch (IllegalArgumentException ignored) {
/*      */       
/*  400 */       } catch (InvocationTargetException ignored) {} 
/*  404 */     return null;
/*      */   }
/*      */   
/*      */   private static Throwable getCauseUsingFieldName(Throwable throwable, String fieldName) {
/*  415 */     Field field = null;
/*      */     try {
/*  417 */       field = throwable.getClass().getField(fieldName);
/*  418 */     } catch (NoSuchFieldException ignored) {
/*      */     
/*  420 */     } catch (SecurityException ignored) {}
/*  424 */     if (field != null && Throwable.class.isAssignableFrom(field.getType()))
/*      */       try {
/*  426 */         return (Throwable)field.get(throwable);
/*  427 */       } catch (IllegalAccessException ignored) {
/*      */       
/*  429 */       } catch (IllegalArgumentException ignored) {} 
/*  433 */     return null;
/*      */   }
/*      */   
/*      */   public static boolean isThrowableNested() {
/*  446 */     return (THROWABLE_CAUSE_METHOD != null);
/*      */   }
/*      */   
/*      */   public static boolean isNestedThrowable(Throwable throwable) {
/*  459 */     if (throwable == null)
/*  460 */       return false; 
/*  463 */     if (throwable instanceof Nestable)
/*  464 */       return true; 
/*  465 */     if (throwable instanceof SQLException)
/*  466 */       return true; 
/*  467 */     if (throwable instanceof InvocationTargetException)
/*  468 */       return true; 
/*  469 */     if (isThrowableNested())
/*  470 */       return true; 
/*  473 */     Class cls = throwable.getClass();
/*  474 */     synchronized (CAUSE_METHOD_NAMES_LOCK) {
/*  475 */       for (int i = 0, isize = CAUSE_METHOD_NAMES.length; i < isize; i++) {
/*      */         try {
/*  477 */           Method method = cls.getMethod(CAUSE_METHOD_NAMES[i], null);
/*  478 */           if (method != null && Throwable.class.isAssignableFrom(method.getReturnType()))
/*  479 */             return true; 
/*  481 */         } catch (NoSuchMethodException ignored) {
/*      */         
/*  483 */         } catch (SecurityException ignored) {}
/*      */       } 
/*      */     } 
/*      */     try {
/*  490 */       Field field = cls.getField("detail");
/*  491 */       if (field != null)
/*  492 */         return true; 
/*  494 */     } catch (NoSuchFieldException ignored) {
/*      */     
/*  496 */     } catch (SecurityException ignored) {}
/*  500 */     return false;
/*      */   }
/*      */   
/*      */   public static int getThrowableCount(Throwable throwable) {
/*  521 */     return getThrowableList(throwable).size();
/*      */   }
/*      */   
/*      */   public static Throwable[] getThrowables(Throwable throwable) {
/*  544 */     List list = getThrowableList(throwable);
/*  545 */     return (Throwable[])list.toArray((Object[])new Throwable[list.size()]);
/*      */   }
/*      */   
/*      */   public static List getThrowableList(Throwable throwable) {
/*  568 */     List list = new ArrayList();
/*  569 */     while (throwable != null && !list.contains(throwable)) {
/*  570 */       list.add(throwable);
/*  571 */       throwable = getCause(throwable);
/*      */     } 
/*  573 */     return list;
/*      */   }
/*      */   
/*      */   public static int indexOfThrowable(Throwable throwable, Class clazz) {
/*  592 */     return indexOf(throwable, clazz, 0, false);
/*      */   }
/*      */   
/*      */   public static int indexOfThrowable(Throwable throwable, Class clazz, int fromIndex) {
/*  615 */     return indexOf(throwable, clazz, fromIndex, false);
/*      */   }
/*      */   
/*      */   public static int indexOfType(Throwable throwable, Class type) {
/*  635 */     return indexOf(throwable, type, 0, true);
/*      */   }
/*      */   
/*      */   public static int indexOfType(Throwable throwable, Class type, int fromIndex) {
/*  659 */     return indexOf(throwable, type, fromIndex, true);
/*      */   }
/*      */   
/*      */   private static int indexOf(Throwable throwable, Class type, int fromIndex, boolean subclass) {
/*  674 */     if (throwable == null || type == null)
/*  675 */       return -1; 
/*  677 */     if (fromIndex < 0)
/*  678 */       fromIndex = 0; 
/*  680 */     Throwable[] throwables = getThrowables(throwable);
/*  681 */     if (fromIndex >= throwables.length)
/*  682 */       return -1; 
/*  684 */     if (subclass) {
/*  685 */       for (int i = fromIndex; i < throwables.length; i++) {
/*  686 */         if (type.isAssignableFrom(throwables[i].getClass()))
/*  687 */           return i; 
/*      */       } 
/*      */     } else {
/*  691 */       for (int i = fromIndex; i < throwables.length; i++) {
/*  692 */         if (type.equals(throwables[i].getClass()))
/*  693 */           return i; 
/*      */       } 
/*      */     } 
/*  697 */     return -1;
/*      */   }
/*      */   
/*      */   public static void printRootCauseStackTrace(Throwable throwable) {
/*  720 */     printRootCauseStackTrace(throwable, System.err);
/*      */   }
/*      */   
/*      */   public static void printRootCauseStackTrace(Throwable throwable, PrintStream stream) {
/*  743 */     if (throwable == null)
/*      */       return; 
/*  746 */     if (stream == null)
/*  747 */       throw new IllegalArgumentException("The PrintStream must not be null"); 
/*  749 */     String[] trace = getRootCauseStackTrace(throwable);
/*  750 */     for (int i = 0; i < trace.length; i++)
/*  751 */       stream.println(trace[i]); 
/*  753 */     stream.flush();
/*      */   }
/*      */   
/*      */   public static void printRootCauseStackTrace(Throwable throwable, PrintWriter writer) {
/*  776 */     if (throwable == null)
/*      */       return; 
/*  779 */     if (writer == null)
/*  780 */       throw new IllegalArgumentException("The PrintWriter must not be null"); 
/*  782 */     String[] trace = getRootCauseStackTrace(throwable);
/*  783 */     for (int i = 0; i < trace.length; i++)
/*  784 */       writer.println(trace[i]); 
/*  786 */     writer.flush();
/*      */   }
/*      */   
/*      */   public static String[] getRootCauseStackTrace(Throwable throwable) {
/*  804 */     if (throwable == null)
/*  805 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/*  807 */     Throwable[] throwables = getThrowables(throwable);
/*  808 */     int count = throwables.length;
/*  809 */     ArrayList frames = new ArrayList();
/*  810 */     List nextTrace = getStackFrameList(throwables[count - 1]);
/*  811 */     for (int i = count; --i >= 0; ) {
/*  812 */       List trace = nextTrace;
/*  813 */       if (i != 0) {
/*  814 */         nextTrace = getStackFrameList(throwables[i - 1]);
/*  815 */         removeCommonFrames(trace, nextTrace);
/*      */       } 
/*  817 */       if (i == count - 1) {
/*  818 */         frames.add(throwables[i].toString());
/*      */       } else {
/*  820 */         frames.add(" [wrapped] " + throwables[i].toString());
/*      */       } 
/*  822 */       for (int j = 0; j < trace.size(); j++)
/*  823 */         frames.add(trace.get(j)); 
/*      */     } 
/*  826 */     return frames.<String>toArray(new String[0]);
/*      */   }
/*      */   
/*      */   public static void removeCommonFrames(List causeFrames, List wrapperFrames) {
/*  838 */     if (causeFrames == null || wrapperFrames == null)
/*  839 */       throw new IllegalArgumentException("The List must not be null"); 
/*  841 */     int causeFrameIndex = causeFrames.size() - 1;
/*  842 */     int wrapperFrameIndex = wrapperFrames.size() - 1;
/*  843 */     while (causeFrameIndex >= 0 && wrapperFrameIndex >= 0) {
/*  846 */       String causeFrame = causeFrames.get(causeFrameIndex);
/*  847 */       String wrapperFrame = wrapperFrames.get(wrapperFrameIndex);
/*  848 */       if (causeFrame.equals(wrapperFrame))
/*  849 */         causeFrames.remove(causeFrameIndex); 
/*  851 */       causeFrameIndex--;
/*  852 */       wrapperFrameIndex--;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String getFullStackTrace(Throwable throwable) {
/*  868 */     StringWriter sw = new StringWriter();
/*  869 */     PrintWriter pw = new PrintWriter(sw, true);
/*  870 */     Throwable[] ts = getThrowables(throwable);
/*  871 */     for (int i = 0; i < ts.length; i++) {
/*  872 */       ts[i].printStackTrace(pw);
/*  873 */       if (isNestedThrowable(ts[i]))
/*      */         break; 
/*      */     } 
/*  877 */     return sw.getBuffer().toString();
/*      */   }
/*      */   
/*      */   public static String getStackTrace(Throwable throwable) {
/*  894 */     StringWriter sw = new StringWriter();
/*  895 */     PrintWriter pw = new PrintWriter(sw, true);
/*  896 */     throwable.printStackTrace(pw);
/*  897 */     return sw.getBuffer().toString();
/*      */   }
/*      */   
/*      */   public static String[] getStackFrames(Throwable throwable) {
/*  914 */     if (throwable == null)
/*  915 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/*  917 */     return getStackFrames(getStackTrace(throwable));
/*      */   }
/*      */   
/*      */   static String[] getStackFrames(String stackTrace) {
/*  934 */     String linebreak = SystemUtils.LINE_SEPARATOR;
/*  935 */     StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/*  936 */     List list = new ArrayList();
/*  937 */     while (frames.hasMoreTokens())
/*  938 */       list.add(frames.nextToken()); 
/*  940 */     return toArray(list);
/*      */   }
/*      */   
/*      */   static List getStackFrameList(Throwable t) {
/*  956 */     String stackTrace = getStackTrace(t);
/*  957 */     String linebreak = SystemUtils.LINE_SEPARATOR;
/*  958 */     StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/*  959 */     List list = new ArrayList();
/*  960 */     boolean traceStarted = false;
/*  961 */     while (frames.hasMoreTokens()) {
/*  962 */       String token = frames.nextToken();
/*  964 */       int at = token.indexOf("at");
/*  965 */       if (at != -1 && token.substring(0, at).trim().length() == 0) {
/*  966 */         traceStarted = true;
/*  967 */         list.add(token);
/*      */         continue;
/*      */       } 
/*  968 */       if (traceStarted)
/*      */         break; 
/*      */     } 
/*  972 */     return list;
/*      */   }
/*      */   
/*      */   public static String getMessage(Throwable th) {
/*  987 */     if (th == null)
/*  988 */       return ""; 
/*  990 */     String clsName = ClassUtils.getShortClassName(th, null);
/*  991 */     String msg = th.getMessage();
/*  992 */     return clsName + ": " + StringUtils.defaultString(msg);
/*      */   }
/*      */   
/*      */   public static String getRootCauseMessage(Throwable th) {
/* 1007 */     Throwable root = getRootCause(th);
/* 1008 */     root = (root == null) ? th : root;
/* 1009 */     return getMessage(root);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\exception\ExceptionUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */