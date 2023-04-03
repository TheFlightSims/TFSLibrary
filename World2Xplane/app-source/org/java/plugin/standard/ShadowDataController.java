/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipFile;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ 
/*     */ final class ShadowDataController {
/*     */   private static final String META_FILE_NAME = ".meta";
/*     */   
/* 500 */   private final Log log = LogFactory.getLog(ShadowDataController.class);
/*     */   
/*     */   private final File shadowFolder;
/*     */   
/*     */   private final URL shadowFolderUrl;
/*     */   
/*     */   private final Properties metaData;
/*     */   
/* 504 */   private final DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */   
/*     */   private final FileFilter fileFilter;
/*     */   
/*     */   static ShadowDataController init(File shadowFolder, FileFilter filter) throws IOException {
/* 509 */     ShadowDataController result = new ShadowDataController(shadowFolder, filter);
/* 511 */     result.quickCheck();
/* 512 */     result.save();
/* 513 */     return result;
/*     */   }
/*     */   
/*     */   private ShadowDataController(File folder, FileFilter filter) throws IOException {
/* 518 */     this.shadowFolder = folder;
/* 519 */     this.fileFilter = filter;
/* 520 */     this.shadowFolderUrl = IoUtil.file2url(folder);
/* 521 */     File metaFile = new File(this.shadowFolder, ".meta");
/* 522 */     this.metaData = new Properties();
/* 523 */     if (metaFile.isFile())
/*     */       try {
/* 525 */         InputStream in = new FileInputStream(metaFile);
/*     */         try {
/* 527 */           this.metaData.load(in);
/*     */         } finally {
/* 529 */           in.close();
/*     */         } 
/* 531 */         if (this.log.isDebugEnabled())
/* 532 */           this.log.debug("meta-data loaded from file " + metaFile); 
/* 534 */       } catch (IOException ioe) {
/* 535 */         this.log.warn("failed loading meta-data from file " + metaFile, ioe);
/*     */       }  
/*     */   }
/*     */   
/*     */   private void save() {
/* 541 */     File metaFile = new File(this.shadowFolder, ".meta");
/*     */     try {
/* 543 */       OutputStream out = new FileOutputStream(metaFile, false);
/*     */       try {
/* 545 */         this.metaData.store(out, "This is automatically generated file.");
/*     */       } finally {
/* 547 */         out.close();
/*     */       } 
/* 549 */       if (this.log.isDebugEnabled())
/* 550 */         this.log.debug("meta-data saved to file " + metaFile); 
/* 552 */     } catch (IOException ioe) {
/* 553 */       this.log.warn("failed saving meta-data to file " + metaFile, ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void quickCheck() {
/* 558 */     File[] files = this.shadowFolder.listFiles(new ShadowFileFilter());
/* 559 */     for (File file : files) {
/* 561 */       if (!this.metaData.containsValue(file.getName()))
/* 564 */         if (ShadingUtil.deleteFile(file)) {
/* 565 */           if (this.log.isDebugEnabled())
/* 566 */             this.log.debug("deleted shadow file " + file); 
/*     */         } else {
/* 569 */           this.log.warn("can't delete shadow file " + file);
/*     */         }  
/*     */     } 
/* 572 */     Set<Object> uids = new HashSet();
/* 573 */     for (Map.Entry<Object, Object> entry : this.metaData.entrySet()) {
/* 574 */       String key = (String)entry.getKey();
/* 575 */       if (!key.startsWith("uid:"))
/*     */         continue; 
/* 578 */       uids.add(entry.getValue());
/*     */     } 
/* 580 */     for (Object object : uids)
/* 581 */       quickCheck((String)object); 
/*     */   }
/*     */   
/*     */   private void quickCheck(String uid) {
/* 586 */     if (this.log.isDebugEnabled())
/* 587 */       this.log.debug("quick check of UID " + uid); 
/* 589 */     String url = this.metaData.getProperty("source:" + uid, null);
/* 590 */     String file = this.metaData.getProperty("file:" + uid, null);
/* 591 */     String modified = this.metaData.getProperty("modified:" + uid, null);
/* 592 */     if (url == null || file == null || modified == null) {
/* 593 */       if (this.log.isDebugEnabled())
/* 594 */         this.log.debug("meta-data incomplete, UID=" + uid); 
/* 596 */       remove(uid);
/*     */       return;
/*     */     } 
/*     */     try {
/* 600 */       if (!this.dtf.parse(modified).equals(ShadingUtil.getLastModified(ShadingUtil.buildURL(this.shadowFolderUrl, url)))) {
/* 602 */         if (this.log.isDebugEnabled())
/* 603 */           this.log.debug("source modification detected, UID=" + uid + ", source=" + url); 
/* 606 */         remove(uid);
/*     */       } 
/* 608 */     } catch (IOException ioe) {
/* 609 */       this.log.warn("quick check failed", ioe);
/* 610 */       remove(uid);
/* 611 */     } catch (ParseException pe) {
/* 612 */       this.log.warn("quick check failed", pe);
/* 613 */       remove(uid);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void remove(String uid) {
/* 618 */     String file = this.metaData.getProperty("file:" + uid, null);
/* 619 */     if (file != null) {
/* 620 */       File lostFile = new File(this.shadowFolder, file);
/* 621 */       if (ShadingUtil.deleteFile(lostFile)) {
/* 622 */         if (this.log.isDebugEnabled())
/* 623 */           this.log.debug("deleted lost file " + file); 
/*     */       } else {
/* 626 */         this.log.warn("can't delete lost file " + file);
/*     */       } 
/*     */     } 
/* 629 */     boolean removed = (this.metaData.remove("uid:" + uid) != null);
/* 630 */     int i = removed | ((this.metaData.remove("source:" + uid) != null) ? 1 : 0);
/* 631 */     i |= (this.metaData.remove("file:" + uid) != null) ? 1 : 0;
/* 632 */     i |= (this.metaData.remove("modified:" + uid) != null) ? 1 : 0;
/* 633 */     if (i != 0 && this.log.isDebugEnabled())
/* 634 */       this.log.debug("removed meta-data, UID=" + uid); 
/*     */   }
/*     */   
/*     */   private URL add(String uid, URL sourceUrl, File file, Date modified) throws IOException {
/* 640 */     URL result = IoUtil.file2url(file);
/* 641 */     this.metaData.setProperty("uid:" + uid, uid);
/* 642 */     String source = ShadingUtil.getRelativeUrl(this.shadowFolder, sourceUrl);
/* 643 */     this.metaData.setProperty("source:" + uid, source);
/* 644 */     this.metaData.setProperty("file:" + uid, file.getName());
/* 645 */     this.metaData.setProperty("modified:" + uid, this.dtf.format(modified));
/* 646 */     save();
/* 647 */     if (this.log.isDebugEnabled())
/* 648 */       this.log.debug("shading done, UID=" + uid + ", source=" + source + ", file=" + result + ", modified=" + this.dtf.format(modified)); 
/* 652 */     return result;
/*     */   }
/*     */   
/*     */   URL shadowResource(URL source, String uid, boolean unpack) {
/*     */     Date lastModified;
/*     */     try {
/* 658 */       URL result = deepCheck(source, uid);
/* 659 */       if (result != null) {
/* 660 */         if (this.log.isDebugEnabled())
/* 661 */           this.log.debug("got actual shaded resource, UID=" + uid + ", source=" + source + ", file=" + result); 
/* 665 */         return result;
/*     */       } 
/* 667 */     } catch (Exception e) {
/* 668 */       this.log.warn("deep check failed, UID=" + uid + ", URL=" + source, e);
/* 670 */       remove(uid);
/*     */     } 
/*     */     try {
/* 674 */       lastModified = ShadingUtil.getLastModified(source);
/* 675 */     } catch (IOException ioe) {
/* 676 */       this.log.error("shading failed, can't get modification date for " + source, ioe);
/* 678 */       return source;
/*     */     } 
/* 680 */     File file = IoUtil.url2file(source);
/* 681 */     if (file != null && file.isDirectory())
/*     */       try {
/* 684 */         File rootFolder = new File(this.shadowFolder, uid);
/* 685 */         IoUtil.copyFolder(file, rootFolder, true, true, this.fileFilter);
/* 686 */         return add(uid, source, rootFolder, lastModified);
/* 687 */       } catch (IOException ioe) {
/* 688 */         this.log.error("failed shading local folder " + file, ioe);
/* 689 */         return source;
/*     */       }  
/*     */     try {
/* 693 */       if ("jar".equalsIgnoreCase(source.getProtocol())) {
/* 694 */         String urlStr = source.toExternalForm();
/* 695 */         int p = urlStr.indexOf("!/");
/* 696 */         if (p == -1)
/* 697 */           p = urlStr.length(); 
/* 699 */         URL jarFileURL = new URL(urlStr.substring(4, p));
/* 700 */         if (!unpack) {
/*     */           InputStream in;
/* 701 */           String str = ShadingUtil.getExtension(jarFileURL.getFile());
/* 702 */           if (str == null)
/* 703 */             str = "jar"; 
/* 705 */           File shadowFile = new File(this.shadowFolder, uid + '.' + str);
/* 706 */           File sourceFile = IoUtil.url2file(jarFileURL);
/* 708 */           if (sourceFile != null) {
/* 709 */             in = new BufferedInputStream(new FileInputStream(sourceFile));
/*     */           } else {
/* 712 */             in = jarFileURL.openStream();
/*     */           } 
/*     */           try {
/* 715 */             OutputStream out = new FileOutputStream(shadowFile, false);
/*     */             try {
/* 718 */               IoUtil.copyStream(in, out, 1024);
/*     */             } finally {
/* 720 */               out.close();
/*     */             } 
/*     */           } finally {
/* 723 */             in.close();
/*     */           } 
/* 725 */           return add(uid, source, shadowFile, lastModified);
/*     */         } 
/* 727 */         URLConnection cnn = null;
/*     */         try {
/*     */           ZipFile zipFile;
/* 729 */           File sourceFile = IoUtil.url2file(jarFileURL);
/* 731 */           if (sourceFile != null) {
/* 732 */             zipFile = new ZipFile(sourceFile);
/*     */           } else {
/* 734 */             cnn = source.openConnection();
/* 735 */             cnn.setUseCaches(false);
/* 736 */             zipFile = ((JarURLConnection)cnn).getJarFile();
/*     */           } 
/* 738 */           File rootFolder = new File(this.shadowFolder, uid);
/*     */           try {
/* 740 */             ShadingUtil.unpack(zipFile, rootFolder);
/*     */           } finally {
/* 742 */             zipFile.close();
/*     */           } 
/* 744 */           return add(uid, source, rootFolder, lastModified);
/*     */         } finally {
/* 746 */           if (cnn != null)
/* 747 */             cnn.getInputStream().close(); 
/*     */         } 
/*     */       } 
/* 751 */     } catch (IOException ioe) {
/* 752 */       this.log.error("failed shading URL connection " + source, ioe);
/* 753 */       return source;
/*     */     } 
/* 755 */     String fileName = source.getFile();
/* 756 */     if (fileName == null) {
/* 757 */       this.log.warn("can't get file name from resource " + source + ", shading failed");
/* 759 */       return source;
/*     */     } 
/* 761 */     String ext = ShadingUtil.getExtension(fileName);
/* 762 */     if (ext == null) {
/* 763 */       this.log.warn("can't get file name extension for resource " + source + ", shading failed");
/* 765 */       return source;
/*     */     } 
/* 767 */     if (unpack && ("jar".equalsIgnoreCase(ext) || "zip".equalsIgnoreCase(ext)))
/*     */       try {
/* 770 */         InputStream strm = source.openStream();
/* 771 */         File rootFolder = new File(this.shadowFolder, uid);
/*     */         try {
/* 773 */           ShadingUtil.unpack(strm, rootFolder);
/*     */         } finally {
/* 775 */           strm.close();
/*     */         } 
/* 777 */         return add(uid, source, rootFolder, lastModified);
/* 778 */       } catch (IOException ioe) {
/* 779 */         this.log.error("failed shading packed resource " + source, ioe);
/* 780 */         return source;
/*     */       }  
/*     */     try {
/* 784 */       File shadowFile = new File(this.shadowFolder, uid + '.' + ext);
/* 785 */       InputStream in = source.openStream();
/*     */       try {
/* 787 */         OutputStream out = new FileOutputStream(shadowFile, false);
/*     */         try {
/* 789 */           IoUtil.copyStream(in, out, 1024);
/*     */         } finally {
/* 791 */           out.close();
/*     */         } 
/*     */       } finally {
/* 794 */         in.close();
/*     */       } 
/* 796 */       return add(uid, source, shadowFile, lastModified);
/* 797 */     } catch (IOException ioe) {
/* 798 */       this.log.error("failed shading resource file " + source, ioe);
/* 799 */       return source;
/*     */     } 
/*     */   }
/*     */   
/*     */   private URL deepCheck(URL source, String uid) throws Exception {
/* 804 */     String url = this.metaData.getProperty("source:" + uid, null);
/* 805 */     if (url == null) {
/* 806 */       if (this.log.isDebugEnabled())
/* 807 */         this.log.debug("URL not found in meta-data, UID=" + uid); 
/* 809 */       remove(uid);
/* 810 */       return null;
/*     */     } 
/* 812 */     if (this.log.isDebugEnabled())
/* 813 */       this.log.debug("URL found in meta-data, UID=" + uid + ", source=" + source + ", storedURL=" + url); 
/* 817 */     URL storedSource = ShadingUtil.buildURL(this.shadowFolderUrl, url);
/* 818 */     if (!storedSource.equals(source)) {
/* 819 */       if (this.log.isDebugEnabled())
/* 820 */         this.log.debug("inconsistent URL found in meta-data, UID=" + uid + ", source=" + source + ", storedSource=" + storedSource); 
/* 824 */       remove(uid);
/* 825 */       return null;
/*     */     } 
/* 827 */     String modified = this.metaData.getProperty("modified:" + uid, null);
/* 828 */     if (modified == null) {
/* 829 */       if (this.log.isDebugEnabled())
/* 830 */         this.log.debug("modification info not found in meta-data, UID=" + uid); 
/* 833 */       remove(uid);
/* 834 */       return null;
/*     */     } 
/* 836 */     if (!ShadingUtil.getLastModified(source).equals(this.dtf.parse(modified))) {
/* 837 */       if (this.log.isDebugEnabled())
/* 838 */         this.log.debug("source modification detected, UID=" + uid + ", source=" + source); 
/* 841 */       remove(uid);
/* 842 */       return null;
/*     */     } 
/* 844 */     String fileStr = this.metaData.getProperty("file:" + uid, null);
/* 845 */     if (fileStr == null) {
/* 846 */       if (this.log.isDebugEnabled())
/* 847 */         this.log.debug("file info not found in meta-data, UID=" + uid); 
/* 849 */       remove(uid);
/* 850 */       return null;
/*     */     } 
/* 852 */     File file = new File(this.shadowFolder, fileStr);
/* 853 */     if (!file.exists()) {
/* 854 */       if (this.log.isDebugEnabled())
/* 855 */         this.log.debug("shadow file not found, UID=" + uid + ", source=" + source + ", file=" + file); 
/* 859 */       remove(uid);
/* 860 */       return null;
/*     */     } 
/* 862 */     File sourceFile = IoUtil.url2file(source);
/* 863 */     if (sourceFile != null && sourceFile.isDirectory()) {
/* 864 */       IoUtil.synchronizeFolders(sourceFile, file, this.fileFilter);
/* 865 */       if (this.log.isDebugEnabled())
/* 866 */         this.log.debug("folders synchronized, UID=" + uid + ", srcFile=" + sourceFile + ", destFile=" + file); 
/* 871 */     } else if (this.log.isDebugEnabled()) {
/* 872 */       this.log.debug("source " + source + " (file is " + sourceFile + ") is not local folder, " + "skipping synchronization, UID=" + uid);
/*     */     } 
/* 877 */     return IoUtil.file2url(file);
/*     */   }
/*     */   
/*     */   static class ShadowFileFilter implements FileFilter {
/*     */     public boolean accept(File file) {
/* 885 */       return !".meta".equals(file.getName());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\ShadowDataController.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */