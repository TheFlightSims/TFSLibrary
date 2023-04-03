/*      */ package org.apache.commons.compress.archivers.tar;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.Date;
/*      */ import java.util.Locale;
/*      */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*      */ import org.apache.commons.compress.archivers.zip.ZipEncoding;
/*      */ import org.apache.commons.compress.utils.ArchiveUtils;
/*      */ 
/*      */ public class TarArchiveEntry implements TarConstants, ArchiveEntry {
/*  183 */   private String magic = "ustar\000";
/*      */   
/*  184 */   private String version = "00";
/*      */   
/*  185 */   private String name = "";
/*      */   
/*  186 */   private String linkName = "";
/*      */   
/*      */   private int mode;
/*      */   
/*      */   private int userId;
/*      */   
/*      */   private int groupId;
/*      */   
/*      */   private long size;
/*      */   
/*      */   private long modTime;
/*      */   
/*      */   private byte linkFlag;
/*      */   
/*      */   private String userName;
/*      */   
/*      */   private String groupName;
/*      */   
/*      */   private int devMajor;
/*      */   
/*      */   private int devMinor;
/*      */   
/*      */   private boolean isExtended;
/*      */   
/*      */   private long realSize;
/*      */   
/*      */   private File file;
/*      */   
/*      */   public static final int MAX_NAMELEN = 31;
/*      */   
/*      */   public static final int DEFAULT_DIR_MODE = 16877;
/*      */   
/*      */   public static final int DEFAULT_FILE_MODE = 33188;
/*      */   
/*      */   public static final int MILLIS_PER_SECOND = 1000;
/*      */   
/*      */   private TarArchiveEntry() {
/*  188 */     String user = System.getProperty("user.name", "");
/*  190 */     if (user.length() > 31)
/*  191 */       user = user.substring(0, 31); 
/*  194 */     this.userId = 0;
/*  195 */     this.groupId = 0;
/*  196 */     this.userName = user;
/*  197 */     this.groupName = "";
/*  198 */     this.file = null;
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(String name) {
/*  208 */     this(name, false);
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(String name, boolean preserveLeadingSlashes) {
/*  222 */     this();
/*  224 */     name = normalizeFileName(name, preserveLeadingSlashes);
/*  225 */     boolean isDir = name.endsWith("/");
/*  227 */     this.devMajor = 0;
/*  228 */     this.devMinor = 0;
/*  229 */     this.name = name;
/*  230 */     this.mode = isDir ? 16877 : 33188;
/*  231 */     this.linkFlag = isDir ? 53 : 48;
/*  232 */     this.userId = 0;
/*  233 */     this.groupId = 0;
/*  234 */     this.size = 0L;
/*  235 */     this.modTime = (new Date()).getTime() / 1000L;
/*  236 */     this.linkName = "";
/*  237 */     this.userName = "";
/*  238 */     this.groupName = "";
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(String name, byte linkFlag) {
/*  248 */     this(name);
/*  249 */     this.linkFlag = linkFlag;
/*  250 */     if (linkFlag == 76) {
/*  251 */       this.magic = "ustar ";
/*  252 */       this.version = " \000";
/*      */     } 
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(File file) {
/*  264 */     this(file, normalizeFileName(file.getPath(), false));
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(File file, String fileName) {
/*  275 */     this();
/*  277 */     this.file = file;
/*  279 */     this.linkName = "";
/*  281 */     if (file.isDirectory()) {
/*  282 */       this.mode = 16877;
/*  283 */       this.linkFlag = 53;
/*  285 */       int nameLength = fileName.length();
/*  286 */       if (nameLength == 0 || fileName.charAt(nameLength - 1) != '/') {
/*  287 */         this.name = fileName + "/";
/*      */       } else {
/*  289 */         this.name = fileName;
/*      */       } 
/*  291 */       this.size = 0L;
/*      */     } else {
/*  293 */       this.mode = 33188;
/*  294 */       this.linkFlag = 48;
/*  295 */       this.size = file.length();
/*  296 */       this.name = fileName;
/*      */     } 
/*  299 */     this.modTime = file.lastModified() / 1000L;
/*  300 */     this.devMajor = 0;
/*  301 */     this.devMinor = 0;
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(byte[] headerBuf) {
/*  312 */     this();
/*  313 */     parseTarHeader(headerBuf);
/*      */   }
/*      */   
/*      */   public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding) throws IOException {
/*  327 */     this();
/*  328 */     parseTarHeader(headerBuf, encoding);
/*      */   }
/*      */   
/*      */   public boolean equals(TarArchiveEntry it) {
/*  339 */     return getName().equals(it.getName());
/*      */   }
/*      */   
/*      */   public boolean equals(Object it) {
/*  351 */     if (it == null || getClass() != it.getClass())
/*  352 */       return false; 
/*  354 */     return equals((TarArchiveEntry)it);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  364 */     return getName().hashCode();
/*      */   }
/*      */   
/*      */   public boolean isDescendent(TarArchiveEntry desc) {
/*  376 */     return desc.getName().startsWith(getName());
/*      */   }
/*      */   
/*      */   public String getName() {
/*  385 */     return this.name.toString();
/*      */   }
/*      */   
/*      */   public void setName(String name) {
/*  394 */     this.name = normalizeFileName(name, false);
/*      */   }
/*      */   
/*      */   public void setMode(int mode) {
/*  403 */     this.mode = mode;
/*      */   }
/*      */   
/*      */   public String getLinkName() {
/*  412 */     return this.linkName.toString();
/*      */   }
/*      */   
/*      */   public void setLinkName(String link) {
/*  423 */     this.linkName = link;
/*      */   }
/*      */   
/*      */   public int getUserId() {
/*  432 */     return this.userId;
/*      */   }
/*      */   
/*      */   public void setUserId(int userId) {
/*  441 */     this.userId = userId;
/*      */   }
/*      */   
/*      */   public int getGroupId() {
/*  450 */     return this.groupId;
/*      */   }
/*      */   
/*      */   public void setGroupId(int groupId) {
/*  459 */     this.groupId = groupId;
/*      */   }
/*      */   
/*      */   public String getUserName() {
/*  468 */     return this.userName.toString();
/*      */   }
/*      */   
/*      */   public void setUserName(String userName) {
/*  477 */     this.userName = userName;
/*      */   }
/*      */   
/*      */   public String getGroupName() {
/*  486 */     return this.groupName.toString();
/*      */   }
/*      */   
/*      */   public void setGroupName(String groupName) {
/*  495 */     this.groupName = groupName;
/*      */   }
/*      */   
/*      */   public void setIds(int userId, int groupId) {
/*  505 */     setUserId(userId);
/*  506 */     setGroupId(groupId);
/*      */   }
/*      */   
/*      */   public void setNames(String userName, String groupName) {
/*  516 */     setUserName(userName);
/*  517 */     setGroupName(groupName);
/*      */   }
/*      */   
/*      */   public void setModTime(long time) {
/*  527 */     this.modTime = time / 1000L;
/*      */   }
/*      */   
/*      */   public void setModTime(Date time) {
/*  536 */     this.modTime = time.getTime() / 1000L;
/*      */   }
/*      */   
/*      */   public Date getModTime() {
/*  545 */     return new Date(this.modTime * 1000L);
/*      */   }
/*      */   
/*      */   public Date getLastModifiedDate() {
/*  550 */     return getModTime();
/*      */   }
/*      */   
/*      */   public File getFile() {
/*  559 */     return this.file;
/*      */   }
/*      */   
/*      */   public int getMode() {
/*  568 */     return this.mode;
/*      */   }
/*      */   
/*      */   public long getSize() {
/*  577 */     return this.size;
/*      */   }
/*      */   
/*      */   public void setSize(long size) {
/*  587 */     if (size < 0L)
/*  588 */       throw new IllegalArgumentException("Size is out of range: " + size); 
/*  590 */     this.size = size;
/*      */   }
/*      */   
/*      */   public int getDevMajor() {
/*  600 */     return this.devMajor;
/*      */   }
/*      */   
/*      */   public void setDevMajor(int devNo) {
/*  611 */     if (devNo < 0)
/*  612 */       throw new IllegalArgumentException("Major device number is out of range: " + devNo); 
/*  615 */     this.devMajor = devNo;
/*      */   }
/*      */   
/*      */   public int getDevMinor() {
/*  625 */     return this.devMinor;
/*      */   }
/*      */   
/*      */   public void setDevMinor(int devNo) {
/*  636 */     if (devNo < 0)
/*  637 */       throw new IllegalArgumentException("Minor device number is out of range: " + devNo); 
/*  640 */     this.devMinor = devNo;
/*      */   }
/*      */   
/*      */   public boolean isExtended() {
/*  650 */     return this.isExtended;
/*      */   }
/*      */   
/*      */   public long getRealSize() {
/*  659 */     return this.realSize;
/*      */   }
/*      */   
/*      */   public boolean isGNUSparse() {
/*  668 */     return (this.linkFlag == 83);
/*      */   }
/*      */   
/*      */   public boolean isGNULongNameEntry() {
/*  677 */     return (this.linkFlag == 76 && this.name.toString().equals("././@LongLink"));
/*      */   }
/*      */   
/*      */   public boolean isPaxHeader() {
/*  690 */     return (this.linkFlag == 120 || this.linkFlag == 88);
/*      */   }
/*      */   
/*      */   public boolean isGlobalPaxHeader() {
/*  702 */     return (this.linkFlag == 103);
/*      */   }
/*      */   
/*      */   public boolean isDirectory() {
/*  711 */     if (this.file != null)
/*  712 */       return this.file.isDirectory(); 
/*  715 */     if (this.linkFlag == 53)
/*  716 */       return true; 
/*  719 */     if (getName().endsWith("/"))
/*  720 */       return true; 
/*  723 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isFile() {
/*  732 */     if (this.file != null)
/*  733 */       return this.file.isFile(); 
/*  735 */     if (this.linkFlag == 0 || this.linkFlag == 48)
/*  736 */       return true; 
/*  738 */     return !getName().endsWith("/");
/*      */   }
/*      */   
/*      */   public boolean isSymbolicLink() {
/*  747 */     return (this.linkFlag == 50);
/*      */   }
/*      */   
/*      */   public boolean isLink() {
/*  756 */     return (this.linkFlag == 49);
/*      */   }
/*      */   
/*      */   public boolean isCharacterDevice() {
/*  765 */     return (this.linkFlag == 51);
/*      */   }
/*      */   
/*      */   public boolean isBlockDevice() {
/*  774 */     return (this.linkFlag == 52);
/*      */   }
/*      */   
/*      */   public boolean isFIFO() {
/*  783 */     return (this.linkFlag == 54);
/*      */   }
/*      */   
/*      */   public TarArchiveEntry[] getDirectoryEntries() {
/*  793 */     if (this.file == null || !this.file.isDirectory())
/*  794 */       return new TarArchiveEntry[0]; 
/*  797 */     String[] list = this.file.list();
/*  798 */     TarArchiveEntry[] result = new TarArchiveEntry[list.length];
/*  800 */     for (int i = 0; i < list.length; i++)
/*  801 */       result[i] = new TarArchiveEntry(new File(this.file, list[i])); 
/*  804 */     return result;
/*      */   }
/*      */   
/*      */   public void writeEntryHeader(byte[] outbuf) {
/*      */     try {
/*  816 */       writeEntryHeader(outbuf, TarUtils.DEFAULT_ENCODING, false);
/*  817 */     } catch (IOException ex) {
/*      */       try {
/*  819 */         writeEntryHeader(outbuf, TarUtils.FALLBACK_ENCODING, false);
/*  820 */       } catch (IOException ex2) {
/*  822 */         throw new RuntimeException(ex2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeEntryHeader(byte[] outbuf, ZipEncoding encoding, boolean starMode) throws IOException {
/*  839 */     int offset = 0;
/*  841 */     offset = TarUtils.formatNameBytes(this.name, outbuf, offset, 100, encoding);
/*  843 */     offset = writeEntryHeaderField(this.mode, outbuf, offset, 8, starMode);
/*  844 */     offset = writeEntryHeaderField(this.userId, outbuf, offset, 8, starMode);
/*  846 */     offset = writeEntryHeaderField(this.groupId, outbuf, offset, 8, starMode);
/*  848 */     offset = writeEntryHeaderField(this.size, outbuf, offset, 12, starMode);
/*  849 */     offset = writeEntryHeaderField(this.modTime, outbuf, offset, 12, starMode);
/*  852 */     int csOffset = offset;
/*  854 */     for (int c = 0; c < 8; c++)
/*  855 */       outbuf[offset++] = 32; 
/*  858 */     outbuf[offset++] = this.linkFlag;
/*  859 */     offset = TarUtils.formatNameBytes(this.linkName, outbuf, offset, 100, encoding);
/*  861 */     offset = TarUtils.formatNameBytes(this.magic, outbuf, offset, 6);
/*  862 */     offset = TarUtils.formatNameBytes(this.version, outbuf, offset, 2);
/*  863 */     offset = TarUtils.formatNameBytes(this.userName, outbuf, offset, 32, encoding);
/*  865 */     offset = TarUtils.formatNameBytes(this.groupName, outbuf, offset, 32, encoding);
/*  867 */     offset = writeEntryHeaderField(this.devMajor, outbuf, offset, 8, starMode);
/*  869 */     offset = writeEntryHeaderField(this.devMinor, outbuf, offset, 8, starMode);
/*  872 */     while (offset < outbuf.length)
/*  873 */       outbuf[offset++] = 0; 
/*  876 */     long chk = TarUtils.computeCheckSum(outbuf);
/*  878 */     TarUtils.formatCheckSumOctalBytes(chk, outbuf, csOffset, 8);
/*      */   }
/*      */   
/*      */   private int writeEntryHeaderField(long value, byte[] outbuf, int offset, int length, boolean starMode) {
/*  883 */     if (!starMode && (value < 0L || value >= 1L << 3 * (length - 1)))
/*  888 */       return TarUtils.formatLongOctalBytes(0L, outbuf, offset, length); 
/*  890 */     return TarUtils.formatLongOctalOrBinaryBytes(value, outbuf, offset, length);
/*      */   }
/*      */   
/*      */   public void parseTarHeader(byte[] header) {
/*      */     try {
/*  902 */       parseTarHeader(header, TarUtils.DEFAULT_ENCODING);
/*  903 */     } catch (IOException ex) {
/*      */       try {
/*  905 */         parseTarHeader(header, TarUtils.DEFAULT_ENCODING, true);
/*  906 */       } catch (IOException ex2) {
/*  908 */         throw new RuntimeException(ex2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void parseTarHeader(byte[] header, ZipEncoding encoding) throws IOException {
/*  924 */     parseTarHeader(header, encoding, false);
/*      */   }
/*      */   
/*      */   private void parseTarHeader(byte[] header, ZipEncoding encoding, boolean oldStyle) throws IOException {
/*  930 */     int offset = 0;
/*  932 */     this.name = oldStyle ? TarUtils.parseName(header, offset, 100) : TarUtils.parseName(header, offset, 100, encoding);
/*  934 */     offset += 100;
/*  935 */     this.mode = (int)TarUtils.parseOctalOrBinary(header, offset, 8);
/*  936 */     offset += 8;
/*  937 */     this.userId = (int)TarUtils.parseOctalOrBinary(header, offset, 8);
/*  938 */     offset += 8;
/*  939 */     this.groupId = (int)TarUtils.parseOctalOrBinary(header, offset, 8);
/*  940 */     offset += 8;
/*  941 */     this.size = TarUtils.parseOctalOrBinary(header, offset, 12);
/*  942 */     offset += 12;
/*  943 */     this.modTime = TarUtils.parseOctalOrBinary(header, offset, 12);
/*  944 */     offset += 12;
/*  945 */     offset += 8;
/*  946 */     this.linkFlag = header[offset++];
/*  947 */     this.linkName = oldStyle ? TarUtils.parseName(header, offset, 100) : TarUtils.parseName(header, offset, 100, encoding);
/*  949 */     offset += 100;
/*  950 */     this.magic = TarUtils.parseName(header, offset, 6);
/*  951 */     offset += 6;
/*  952 */     this.version = TarUtils.parseName(header, offset, 2);
/*  953 */     offset += 2;
/*  954 */     this.userName = oldStyle ? TarUtils.parseName(header, offset, 32) : TarUtils.parseName(header, offset, 32, encoding);
/*  956 */     offset += 32;
/*  957 */     this.groupName = oldStyle ? TarUtils.parseName(header, offset, 32) : TarUtils.parseName(header, offset, 32, encoding);
/*  959 */     offset += 32;
/*  960 */     this.devMajor = (int)TarUtils.parseOctalOrBinary(header, offset, 8);
/*  961 */     offset += 8;
/*  962 */     this.devMinor = (int)TarUtils.parseOctalOrBinary(header, offset, 8);
/*  963 */     offset += 8;
/*  965 */     int type = evaluateType(header);
/*  966 */     switch (type) {
/*      */       case 2:
/*  968 */         offset += 12;
/*  969 */         offset += 12;
/*  970 */         offset += 12;
/*  971 */         offset += 4;
/*  972 */         offset++;
/*  973 */         offset += 96;
/*  974 */         this.isExtended = TarUtils.parseBoolean(header, offset);
/*  975 */         offset++;
/*  976 */         this.realSize = TarUtils.parseOctal(header, offset, 12);
/*  977 */         offset += 12;
/*      */         return;
/*      */     } 
/*  982 */     String prefix = oldStyle ? TarUtils.parseName(header, offset, 155) : TarUtils.parseName(header, offset, 155, encoding);
/*  987 */     if (isDirectory() && !this.name.endsWith("/"))
/*  988 */       this.name += "/"; 
/*  990 */     if (prefix.length() > 0)
/*  991 */       this.name = prefix + "/" + this.name; 
/*      */   }
/*      */   
/*      */   private static String normalizeFileName(String fileName, boolean preserveLeadingSlashes) {
/* 1003 */     String osname = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
/* 1005 */     if (osname != null)
/* 1010 */       if (osname.startsWith("windows")) {
/* 1011 */         if (fileName.length() > 2) {
/* 1012 */           char ch1 = fileName.charAt(0);
/* 1013 */           char ch2 = fileName.charAt(1);
/* 1015 */           if (ch2 == ':' && ((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z')))
/* 1018 */             fileName = fileName.substring(2); 
/*      */         } 
/* 1021 */       } else if (osname.indexOf("netware") > -1) {
/* 1022 */         int colon = fileName.indexOf(':');
/* 1023 */         if (colon != -1)
/* 1024 */           fileName = fileName.substring(colon + 1); 
/*      */       }  
/* 1029 */     fileName = fileName.replace(File.separatorChar, '/');
/* 1034 */     while (!preserveLeadingSlashes && fileName.startsWith("/"))
/* 1035 */       fileName = fileName.substring(1); 
/* 1037 */     return fileName;
/*      */   }
/*      */   
/*      */   private int evaluateType(byte[] header) {
/* 1047 */     if (ArchiveUtils.matchAsciiBuffer("ustar ", header, 257, 6))
/* 1048 */       return 2; 
/* 1050 */     if (ArchiveUtils.matchAsciiBuffer("ustar\000", header, 257, 6))
/* 1051 */       return 3; 
/* 1053 */     return 0;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\tar\TarArchiveEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */