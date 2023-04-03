package org.hsqldb.lib;

import java.io.IOException;

public class FileArchiver {
  public static final int COMPRESSION_NONE = 0;
  
  public static final int COMPRESSION_ZIP = 1;
  
  public static final int COMPRESSION_GZIP = 2;
  
  private static final int COPY_BLOCK_SIZE = 65536;
  
  public static void copyFile(String paramString1, String paramString2, FileAccess paramFileAccess) throws IOException {
    archive(paramString1, paramString2, paramFileAccess, 0);
  }
  
  public static void archive(String paramString1, String paramString2, FileAccess paramFileAccess, int paramInt) throws IOException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aconst_null
    //   4: astore #5
    //   6: aconst_null
    //   7: astore #6
    //   9: aconst_null
    //   10: astore #7
    //   12: iconst_0
    //   13: istore #8
    //   15: aload_2
    //   16: aload_0
    //   17: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   22: ifne -> 26
    //   25: return
    //   26: ldc 65536
    //   28: newarray byte
    //   30: astore #9
    //   32: aload_2
    //   33: aload_0
    //   34: invokeinterface openInputStreamElement : (Ljava/lang/String;)Ljava/io/InputStream;
    //   39: astore #4
    //   41: aload_2
    //   42: aload_1
    //   43: invokeinterface openOutputStreamElement : (Ljava/lang/String;)Ljava/io/OutputStream;
    //   48: astore #5
    //   50: aload #5
    //   52: astore #6
    //   54: iload_3
    //   55: tableswitch default -> 131, 0 -> 128, 1 -> 80, 2 -> 108
    //   80: new java/util/zip/DeflaterOutputStream
    //   83: dup
    //   84: aload #5
    //   86: new java/util/zip/Deflater
    //   89: dup
    //   90: iconst_1
    //   91: invokespecial <init> : (I)V
    //   94: aload #9
    //   96: arraylength
    //   97: invokespecial <init> : (Ljava/io/OutputStream;Ljava/util/zip/Deflater;I)V
    //   100: dup
    //   101: astore #7
    //   103: astore #5
    //   105: goto -> 158
    //   108: new java/util/zip/GZIPOutputStream
    //   111: dup
    //   112: aload #5
    //   114: aload #9
    //   116: arraylength
    //   117: invokespecial <init> : (Ljava/io/OutputStream;I)V
    //   120: dup
    //   121: astore #7
    //   123: astore #5
    //   125: goto -> 158
    //   128: goto -> 158
    //   131: new java/lang/RuntimeException
    //   134: dup
    //   135: new java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial <init> : ()V
    //   142: ldc 'FileArchiver'
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: iload_3
    //   148: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   151: invokevirtual toString : ()Ljava/lang/String;
    //   154: invokespecial <init> : (Ljava/lang/String;)V
    //   157: athrow
    //   158: aload #4
    //   160: aload #9
    //   162: iconst_0
    //   163: aload #9
    //   165: arraylength
    //   166: invokevirtual read : ([BII)I
    //   169: istore #10
    //   171: iload #10
    //   173: iconst_m1
    //   174: if_icmpne -> 180
    //   177: goto -> 193
    //   180: aload #5
    //   182: aload #9
    //   184: iconst_0
    //   185: iload #10
    //   187: invokevirtual write : ([BII)V
    //   190: goto -> 158
    //   193: iconst_1
    //   194: istore #8
    //   196: aload #4
    //   198: ifnull -> 206
    //   201: aload #4
    //   203: invokevirtual close : ()V
    //   206: aload #5
    //   208: ifnull -> 247
    //   211: aload #7
    //   213: ifnull -> 221
    //   216: aload #7
    //   218: invokevirtual finish : ()V
    //   221: aload #6
    //   223: instanceof java/io/FileOutputStream
    //   226: ifeq -> 242
    //   229: aload_2
    //   230: aload #6
    //   232: invokeinterface getFileSync : (Ljava/io/OutputStream;)Lorg/hsqldb/lib/FileAccess$FileSync;
    //   237: invokeinterface sync : ()V
    //   242: aload #5
    //   244: invokevirtual close : ()V
    //   247: iload #8
    //   249: ifne -> 269
    //   252: aload_2
    //   253: aload_1
    //   254: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   259: ifeq -> 269
    //   262: aload_2
    //   263: aload_1
    //   264: invokeinterface removeElement : (Ljava/lang/String;)V
    //   269: goto -> 377
    //   272: astore #9
    //   274: aload #9
    //   276: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   279: athrow
    //   280: astore #9
    //   282: aload #9
    //   284: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   287: athrow
    //   288: astore #11
    //   290: aload #4
    //   292: ifnull -> 300
    //   295: aload #4
    //   297: invokevirtual close : ()V
    //   300: aload #5
    //   302: ifnull -> 341
    //   305: aload #7
    //   307: ifnull -> 315
    //   310: aload #7
    //   312: invokevirtual finish : ()V
    //   315: aload #6
    //   317: instanceof java/io/FileOutputStream
    //   320: ifeq -> 336
    //   323: aload_2
    //   324: aload #6
    //   326: invokeinterface getFileSync : (Ljava/io/OutputStream;)Lorg/hsqldb/lib/FileAccess$FileSync;
    //   331: invokeinterface sync : ()V
    //   336: aload #5
    //   338: invokevirtual close : ()V
    //   341: iload #8
    //   343: ifne -> 363
    //   346: aload_2
    //   347: aload_1
    //   348: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   353: ifeq -> 363
    //   356: aload_2
    //   357: aload_1
    //   358: invokeinterface removeElement : (Ljava/lang/String;)V
    //   363: goto -> 374
    //   366: astore #12
    //   368: aload #12
    //   370: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   373: athrow
    //   374: aload #11
    //   376: athrow
    //   377: return
    // Exception table:
    //   from	to	target	type
    //   26	196	280	java/lang/Throwable
    //   26	196	288	finally
    //   196	269	272	java/lang/Throwable
    //   280	290	288	finally
    //   290	363	366	java/lang/Throwable
  }
  
  public static void unarchive(String paramString1, String paramString2, FileAccess paramFileAccess, int paramInt) throws IOException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aconst_null
    //   4: astore #5
    //   6: iconst_0
    //   7: istore #6
    //   9: aload_2
    //   10: aload_0
    //   11: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   16: ifne -> 99
    //   19: aload #4
    //   21: ifnull -> 29
    //   24: aload #4
    //   26: invokevirtual close : ()V
    //   29: aload #5
    //   31: ifnull -> 65
    //   34: aload #5
    //   36: invokevirtual flush : ()V
    //   39: aload #5
    //   41: instanceof java/io/FileOutputStream
    //   44: ifeq -> 60
    //   47: aload_2
    //   48: aload #5
    //   50: invokeinterface getFileSync : (Ljava/io/OutputStream;)Lorg/hsqldb/lib/FileAccess$FileSync;
    //   55: invokeinterface sync : ()V
    //   60: aload #5
    //   62: invokevirtual close : ()V
    //   65: iload #6
    //   67: ifne -> 87
    //   70: aload_2
    //   71: aload_1
    //   72: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   77: ifeq -> 87
    //   80: aload_2
    //   81: aload_1
    //   82: invokeinterface removeElement : (Ljava/lang/String;)V
    //   87: goto -> 98
    //   90: astore #7
    //   92: aload #7
    //   94: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   97: athrow
    //   98: return
    //   99: aload_2
    //   100: aload_1
    //   101: invokeinterface removeElement : (Ljava/lang/String;)V
    //   106: ldc 65536
    //   108: newarray byte
    //   110: astore #7
    //   112: aload_2
    //   113: aload_0
    //   114: invokeinterface openInputStreamElement : (Ljava/lang/String;)Ljava/io/InputStream;
    //   119: astore #4
    //   121: iload_3
    //   122: tableswitch default -> 189, 0 -> 186, 1 -> 148, 2 -> 169
    //   148: new java/util/zip/InflaterInputStream
    //   151: dup
    //   152: aload #4
    //   154: new java/util/zip/Inflater
    //   157: dup
    //   158: invokespecial <init> : ()V
    //   161: invokespecial <init> : (Ljava/io/InputStream;Ljava/util/zip/Inflater;)V
    //   164: astore #4
    //   166: goto -> 216
    //   169: new java/util/zip/GZIPInputStream
    //   172: dup
    //   173: aload #4
    //   175: aload #7
    //   177: arraylength
    //   178: invokespecial <init> : (Ljava/io/InputStream;I)V
    //   181: astore #4
    //   183: goto -> 216
    //   186: goto -> 216
    //   189: new java/lang/RuntimeException
    //   192: dup
    //   193: new java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial <init> : ()V
    //   200: ldc 'FileArchiver: '
    //   202: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: iload_3
    //   206: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   209: invokevirtual toString : ()Ljava/lang/String;
    //   212: invokespecial <init> : (Ljava/lang/String;)V
    //   215: athrow
    //   216: aload_2
    //   217: aload_1
    //   218: invokeinterface openOutputStreamElement : (Ljava/lang/String;)Ljava/io/OutputStream;
    //   223: astore #5
    //   225: aload #4
    //   227: aload #7
    //   229: iconst_0
    //   230: aload #7
    //   232: arraylength
    //   233: invokevirtual read : ([BII)I
    //   236: istore #8
    //   238: iload #8
    //   240: iconst_m1
    //   241: if_icmpne -> 247
    //   244: goto -> 260
    //   247: aload #5
    //   249: aload #7
    //   251: iconst_0
    //   252: iload #8
    //   254: invokevirtual write : ([BII)V
    //   257: goto -> 225
    //   260: iconst_1
    //   261: istore #6
    //   263: aload #4
    //   265: ifnull -> 273
    //   268: aload #4
    //   270: invokevirtual close : ()V
    //   273: aload #5
    //   275: ifnull -> 309
    //   278: aload #5
    //   280: invokevirtual flush : ()V
    //   283: aload #5
    //   285: instanceof java/io/FileOutputStream
    //   288: ifeq -> 304
    //   291: aload_2
    //   292: aload #5
    //   294: invokeinterface getFileSync : (Ljava/io/OutputStream;)Lorg/hsqldb/lib/FileAccess$FileSync;
    //   299: invokeinterface sync : ()V
    //   304: aload #5
    //   306: invokevirtual close : ()V
    //   309: iload #6
    //   311: ifne -> 331
    //   314: aload_2
    //   315: aload_1
    //   316: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   321: ifeq -> 331
    //   324: aload_2
    //   325: aload_1
    //   326: invokeinterface removeElement : (Ljava/lang/String;)V
    //   331: goto -> 434
    //   334: astore #7
    //   336: aload #7
    //   338: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   341: athrow
    //   342: astore #7
    //   344: aload #7
    //   346: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   349: athrow
    //   350: astore #9
    //   352: aload #4
    //   354: ifnull -> 362
    //   357: aload #4
    //   359: invokevirtual close : ()V
    //   362: aload #5
    //   364: ifnull -> 398
    //   367: aload #5
    //   369: invokevirtual flush : ()V
    //   372: aload #5
    //   374: instanceof java/io/FileOutputStream
    //   377: ifeq -> 393
    //   380: aload_2
    //   381: aload #5
    //   383: invokeinterface getFileSync : (Ljava/io/OutputStream;)Lorg/hsqldb/lib/FileAccess$FileSync;
    //   388: invokeinterface sync : ()V
    //   393: aload #5
    //   395: invokevirtual close : ()V
    //   398: iload #6
    //   400: ifne -> 420
    //   403: aload_2
    //   404: aload_1
    //   405: invokeinterface isStreamElement : (Ljava/lang/String;)Z
    //   410: ifeq -> 420
    //   413: aload_2
    //   414: aload_1
    //   415: invokeinterface removeElement : (Ljava/lang/String;)V
    //   420: goto -> 431
    //   423: astore #10
    //   425: aload #10
    //   427: invokestatic toIOException : (Ljava/lang/Throwable;)Ljava/io/IOException;
    //   430: athrow
    //   431: aload #9
    //   433: athrow
    //   434: return
    // Exception table:
    //   from	to	target	type
    //   9	19	342	java/lang/Throwable
    //   9	19	350	finally
    //   19	87	90	java/lang/Throwable
    //   99	263	342	java/lang/Throwable
    //   99	263	350	finally
    //   263	331	334	java/lang/Throwable
    //   342	352	350	finally
    //   352	420	423	java/lang/Throwable
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\FileArchiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */