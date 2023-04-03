/*      */ package org.apache.commons.lang;
/*      */ 
/*      */ import java.io.File;
/*      */ 
/*      */ public class SystemUtils {
/*      */   private static final int JAVA_VERSION_TRIM_SIZE = 3;
/*      */   
/*      */   private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
/*      */   
/*      */   private static final String USER_HOME_KEY = "user.home";
/*      */   
/*      */   private static final String USER_DIR_KEY = "user.dir";
/*      */   
/*      */   private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
/*      */   
/*      */   private static final String JAVA_HOME_KEY = "java.home";
/*      */   
/*  105 */   public static final String AWT_TOOLKIT = getSystemProperty("awt.toolkit");
/*      */   
/*  129 */   public static final String FILE_ENCODING = getSystemProperty("file.encoding");
/*      */   
/*  149 */   public static final String FILE_SEPARATOR = getSystemProperty("file.separator");
/*      */   
/*  169 */   public static final String JAVA_AWT_FONTS = getSystemProperty("java.awt.fonts");
/*      */   
/*  189 */   public static final String JAVA_AWT_GRAPHICSENV = getSystemProperty("java.awt.graphicsenv");
/*      */   
/*  212 */   public static final String JAVA_AWT_HEADLESS = getSystemProperty("java.awt.headless");
/*      */   
/*  232 */   public static final String JAVA_AWT_PRINTERJOB = getSystemProperty("java.awt.printerjob");
/*      */   
/*  252 */   public static final String JAVA_CLASS_PATH = getSystemProperty("java.class.path");
/*      */   
/*  272 */   public static final String JAVA_CLASS_VERSION = getSystemProperty("java.class.version");
/*      */   
/*  293 */   public static final String JAVA_COMPILER = getSystemProperty("java.compiler");
/*      */   
/*  313 */   public static final String JAVA_ENDORSED_DIRS = getSystemProperty("java.endorsed.dirs");
/*      */   
/*  333 */   public static final String JAVA_EXT_DIRS = getSystemProperty("java.ext.dirs");
/*      */   
/*  353 */   public static final String JAVA_HOME = getSystemProperty("java.home");
/*      */   
/*  373 */   public static final String JAVA_IO_TMPDIR = getSystemProperty("java.io.tmpdir");
/*      */   
/*  393 */   public static final String JAVA_LIBRARY_PATH = getSystemProperty("java.library.path");
/*      */   
/*  414 */   public static final String JAVA_RUNTIME_NAME = getSystemProperty("java.runtime.name");
/*      */   
/*  435 */   public static final String JAVA_RUNTIME_VERSION = getSystemProperty("java.runtime.version");
/*      */   
/*  455 */   public static final String JAVA_SPECIFICATION_NAME = getSystemProperty("java.specification.name");
/*      */   
/*  475 */   public static final String JAVA_SPECIFICATION_VENDOR = getSystemProperty("java.specification.vendor");
/*      */   
/*  495 */   public static final String JAVA_SPECIFICATION_VERSION = getSystemProperty("java.specification.version");
/*      */   
/*  516 */   public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = getSystemProperty("java.util.prefs.PreferencesFactory");
/*      */   
/*  537 */   public static final String JAVA_VENDOR = getSystemProperty("java.vendor");
/*      */   
/*  557 */   public static final String JAVA_VENDOR_URL = getSystemProperty("java.vendor.url");
/*      */   
/*  577 */   public static final String JAVA_VERSION = getSystemProperty("java.version");
/*      */   
/*  598 */   public static final String JAVA_VM_INFO = getSystemProperty("java.vm.info");
/*      */   
/*  618 */   public static final String JAVA_VM_NAME = getSystemProperty("java.vm.name");
/*      */   
/*  638 */   public static final String JAVA_VM_SPECIFICATION_NAME = getSystemProperty("java.vm.specification.name");
/*      */   
/*  658 */   public static final String JAVA_VM_SPECIFICATION_VENDOR = getSystemProperty("java.vm.specification.vendor");
/*      */   
/*  678 */   public static final String JAVA_VM_SPECIFICATION_VERSION = getSystemProperty("java.vm.specification.version");
/*      */   
/*  698 */   public static final String JAVA_VM_VENDOR = getSystemProperty("java.vm.vendor");
/*      */   
/*  718 */   public static final String JAVA_VM_VERSION = getSystemProperty("java.vm.version");
/*      */   
/*  738 */   public static final String LINE_SEPARATOR = getSystemProperty("line.separator");
/*      */   
/*  758 */   public static final String OS_ARCH = getSystemProperty("os.arch");
/*      */   
/*  778 */   public static final String OS_NAME = getSystemProperty("os.name");
/*      */   
/*  798 */   public static final String OS_VERSION = getSystemProperty("os.version");
/*      */   
/*  818 */   public static final String PATH_SEPARATOR = getSystemProperty("path.separator");
/*      */   
/*  841 */   public static final String USER_COUNTRY = (getSystemProperty("user.country") == null) ? getSystemProperty("user.region") : getSystemProperty("user.country");
/*      */   
/*  863 */   public static final String USER_DIR = getSystemProperty("user.dir");
/*      */   
/*  883 */   public static final String USER_HOME = getSystemProperty("user.home");
/*      */   
/*  904 */   public static final String USER_LANGUAGE = getSystemProperty("user.language");
/*      */   
/*  924 */   public static final String USER_NAME = getSystemProperty("user.name");
/*      */   
/*  944 */   public static final String USER_TIMEZONE = getSystemProperty("user.timezone");
/*      */   
/*  962 */   public static final String JAVA_VERSION_TRIMMED = getJavaVersionTrimmed();
/*      */   
/*  988 */   public static final float JAVA_VERSION_FLOAT = getJavaVersionAsFloat();
/*      */   
/* 1009 */   public static final int JAVA_VERSION_INT = getJavaVersionAsInt();
/*      */   
/* 1025 */   public static final boolean IS_JAVA_1_1 = getJavaVersionMatches("1.1");
/*      */   
/* 1036 */   public static final boolean IS_JAVA_1_2 = getJavaVersionMatches("1.2");
/*      */   
/* 1047 */   public static final boolean IS_JAVA_1_3 = getJavaVersionMatches("1.3");
/*      */   
/* 1058 */   public static final boolean IS_JAVA_1_4 = getJavaVersionMatches("1.4");
/*      */   
/* 1069 */   public static final boolean IS_JAVA_1_5 = getJavaVersionMatches("1.5");
/*      */   
/* 1080 */   public static final boolean IS_JAVA_1_6 = getJavaVersionMatches("1.6");
/*      */   
/* 1093 */   public static final boolean IS_JAVA_1_7 = getJavaVersionMatches("1.7");
/*      */   
/* 1114 */   public static final boolean IS_OS_AIX = getOSMatchesName("AIX");
/*      */   
/* 1127 */   public static final boolean IS_OS_HP_UX = getOSMatchesName("HP-UX");
/*      */   
/* 1140 */   public static final boolean IS_OS_IRIX = getOSMatchesName("Irix");
/*      */   
/* 1153 */   public static final boolean IS_OS_LINUX = (getOSMatchesName("Linux") || getOSMatchesName("LINUX"));
/*      */   
/* 1166 */   public static final boolean IS_OS_MAC = getOSMatchesName("Mac");
/*      */   
/* 1179 */   public static final boolean IS_OS_MAC_OSX = getOSMatchesName("Mac OS X");
/*      */   
/* 1192 */   public static final boolean IS_OS_OS2 = getOSMatchesName("OS/2");
/*      */   
/* 1205 */   public static final boolean IS_OS_SOLARIS = getOSMatchesName("Solaris");
/*      */   
/* 1218 */   public static final boolean IS_OS_SUN_OS = getOSMatchesName("SunOS");
/*      */   
/* 1232 */   public static final boolean IS_OS_UNIX = (IS_OS_AIX || IS_OS_HP_UX || IS_OS_IRIX || IS_OS_LINUX || IS_OS_MAC_OSX || IS_OS_SOLARIS || IS_OS_SUN_OS);
/*      */   
/* 1247 */   public static final boolean IS_OS_WINDOWS = getOSMatchesName("Windows");
/*      */   
/* 1260 */   public static final boolean IS_OS_WINDOWS_2000 = getOSMatches("Windows", "5.0");
/*      */   
/* 1273 */   public static final boolean IS_OS_WINDOWS_95 = getOSMatches("Windows 9", "4.0");
/*      */   
/* 1287 */   public static final boolean IS_OS_WINDOWS_98 = getOSMatches("Windows 9", "4.1");
/*      */   
/* 1301 */   public static final boolean IS_OS_WINDOWS_ME = getOSMatches("Windows", "4.9");
/*      */   
/* 1315 */   public static final boolean IS_OS_WINDOWS_NT = getOSMatchesName("Windows NT");
/*      */   
/* 1329 */   public static final boolean IS_OS_WINDOWS_XP = getOSMatches("Windows", "5.1");
/*      */   
/* 1343 */   public static final boolean IS_OS_WINDOWS_VISTA = getOSMatches("Windows", "6.0");
/*      */   
/* 1356 */   public static final boolean IS_OS_WINDOWS_7 = getOSMatches("Windows", "6.1");
/*      */   
/*      */   public static File getJavaHome() {
/* 1370 */     return new File(System.getProperty("java.home"));
/*      */   }
/*      */   
/*      */   public static File getJavaIoTmpDir() {
/* 1386 */     return new File(System.getProperty("java.io.tmpdir"));
/*      */   }
/*      */   
/*      */   public static float getJavaVersion() {
/* 1403 */     return JAVA_VERSION_FLOAT;
/*      */   }
/*      */   
/*      */   private static float getJavaVersionAsFloat() {
/* 1427 */     return toVersionFloat(toJavaVersionIntArray(JAVA_VERSION, 3));
/*      */   }
/*      */   
/*      */   private static int getJavaVersionAsInt() {
/* 1451 */     return toVersionInt(toJavaVersionIntArray(JAVA_VERSION, 3));
/*      */   }
/*      */   
/*      */   private static boolean getJavaVersionMatches(String versionPrefix) {
/* 1464 */     return isJavaVersionMatch(JAVA_VERSION_TRIMMED, versionPrefix);
/*      */   }
/*      */   
/*      */   private static String getJavaVersionTrimmed() {
/* 1473 */     if (JAVA_VERSION != null)
/* 1474 */       for (int i = 0; i < JAVA_VERSION.length(); i++) {
/* 1475 */         char ch = JAVA_VERSION.charAt(i);
/* 1476 */         if (ch >= '0' && ch <= '9')
/* 1477 */           return JAVA_VERSION.substring(i); 
/*      */       }  
/* 1481 */     return null;
/*      */   }
/*      */   
/*      */   private static boolean getOSMatches(String osNamePrefix, String osVersionPrefix) {
/* 1494 */     return isOSMatch(OS_NAME, OS_VERSION, osNamePrefix, osVersionPrefix);
/*      */   }
/*      */   
/*      */   private static boolean getOSMatchesName(String osNamePrefix) {
/* 1505 */     return isOSNameMatch(OS_NAME, osNamePrefix);
/*      */   }
/*      */   
/*      */   private static String getSystemProperty(String property) {
/*      */     try {
/* 1525 */       return System.getProperty(property);
/* 1526 */     } catch (SecurityException ex) {
/* 1528 */       System.err.println("Caught a SecurityException reading the system property '" + property + "'; the SystemUtils property value will default to null.");
/* 1530 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static File getUserDir() {
/* 1546 */     return new File(System.getProperty("user.dir"));
/*      */   }
/*      */   
/*      */   public static File getUserHome() {
/* 1561 */     return new File(System.getProperty("user.home"));
/*      */   }
/*      */   
/*      */   public static boolean isJavaAwtHeadless() {
/* 1574 */     return (JAVA_AWT_HEADLESS != null) ? JAVA_AWT_HEADLESS.equals(Boolean.TRUE.toString()) : false;
/*      */   }
/*      */   
/*      */   public static boolean isJavaVersionAtLeast(float requiredVersion) {
/* 1595 */     return (JAVA_VERSION_FLOAT >= requiredVersion);
/*      */   }
/*      */   
/*      */   public static boolean isJavaVersionAtLeast(int requiredVersion) {
/* 1617 */     return (JAVA_VERSION_INT >= requiredVersion);
/*      */   }
/*      */   
/*      */   static boolean isJavaVersionMatch(String version, String versionPrefix) {
/* 1635 */     if (version == null)
/* 1636 */       return false; 
/* 1638 */     return version.startsWith(versionPrefix);
/*      */   }
/*      */   
/*      */   static boolean isOSMatch(String osName, String osVersion, String osNamePrefix, String osVersionPrefix) {
/* 1658 */     if (osName == null || osVersion == null)
/* 1659 */       return false; 
/* 1661 */     return (osName.startsWith(osNamePrefix) && osVersion.startsWith(osVersionPrefix));
/*      */   }
/*      */   
/*      */   static boolean isOSNameMatch(String osName, String osNamePrefix) {
/* 1677 */     if (osName == null)
/* 1678 */       return false; 
/* 1680 */     return osName.startsWith(osNamePrefix);
/*      */   }
/*      */   
/*      */   static float toJavaVersionFloat(String version) {
/* 1708 */     return toVersionFloat(toJavaVersionIntArray(version, 3));
/*      */   }
/*      */   
/*      */   static int toJavaVersionInt(String version) {
/* 1736 */     return toVersionInt(toJavaVersionIntArray(version, 3));
/*      */   }
/*      */   
/*      */   static int[] toJavaVersionIntArray(String version) {
/* 1760 */     return toJavaVersionIntArray(version, 2147483647);
/*      */   }
/*      */   
/*      */   private static int[] toJavaVersionIntArray(String version, int limit) {
/* 1782 */     if (version == null)
/* 1783 */       return ArrayUtils.EMPTY_INT_ARRAY; 
/* 1785 */     String[] strings = StringUtils.split(version, "._- ");
/* 1786 */     int[] ints = new int[Math.min(limit, strings.length)];
/* 1787 */     int j = 0;
/* 1788 */     for (int i = 0; i < strings.length && j < limit; i++) {
/* 1789 */       String s = strings[i];
/* 1790 */       if (s.length() > 0)
/*      */         try {
/* 1792 */           ints[j] = Integer.parseInt(s);
/* 1793 */           j++;
/* 1794 */         } catch (Exception e) {} 
/*      */     } 
/* 1798 */     if (ints.length > j) {
/* 1799 */       int[] newInts = new int[j];
/* 1800 */       System.arraycopy(ints, 0, newInts, 0, j);
/* 1801 */       ints = newInts;
/*      */     } 
/* 1803 */     return ints;
/*      */   }
/*      */   
/*      */   private static float toVersionFloat(int[] javaVersions) {
/* 1828 */     if (javaVersions == null || javaVersions.length == 0)
/* 1829 */       return 0.0F; 
/* 1831 */     if (javaVersions.length == 1)
/* 1832 */       return javaVersions[0]; 
/* 1834 */     StringBuffer builder = new StringBuffer();
/* 1835 */     builder.append(javaVersions[0]);
/* 1836 */     builder.append('.');
/* 1837 */     for (int i = 1; i < javaVersions.length; i++)
/* 1838 */       builder.append(javaVersions[i]); 
/*      */     try {
/* 1841 */       return Float.parseFloat(builder.toString());
/* 1842 */     } catch (Exception ex) {
/* 1843 */       return 0.0F;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int toVersionInt(int[] javaVersions) {
/* 1869 */     if (javaVersions == null)
/* 1870 */       return 0; 
/* 1872 */     int intVersion = 0;
/* 1873 */     int len = javaVersions.length;
/* 1874 */     if (len >= 1)
/* 1875 */       intVersion = javaVersions[0] * 100; 
/* 1877 */     if (len >= 2)
/* 1878 */       intVersion += javaVersions[1] * 10; 
/* 1880 */     if (len >= 3)
/* 1881 */       intVersion += javaVersions[2]; 
/* 1883 */     return intVersion;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\SystemUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */