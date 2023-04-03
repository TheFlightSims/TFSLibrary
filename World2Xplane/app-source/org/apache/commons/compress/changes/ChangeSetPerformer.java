/*     */ package org.apache.commons.compress.changes;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.utils.IOUtils;
/*     */ 
/*     */ public class ChangeSetPerformer {
/*     */   private final Set<Change> changes;
/*     */   
/*     */   public ChangeSetPerformer(ChangeSet changeSet) {
/*  49 */     this.changes = changeSet.getChanges();
/*     */   }
/*     */   
/*     */   public ChangeSetResults perform(ArchiveInputStream in, ArchiveOutputStream out) throws IOException {
/*  69 */     ChangeSetResults results = new ChangeSetResults();
/*  71 */     Set<Change> workingSet = new LinkedHashSet<Change>(this.changes);
/*  73 */     for (Iterator<Change> it = workingSet.iterator(); it.hasNext(); ) {
/*  74 */       Change change = it.next();
/*  76 */       if (change.type() == 2 && change.isReplaceMode()) {
/*  77 */         copyStream(change.getInput(), out, change.getEntry());
/*  78 */         it.remove();
/*  79 */         results.addedFromChangeSet(change.getEntry().getName());
/*     */       } 
/*     */     } 
/*  83 */     ArchiveEntry entry = null;
/*  84 */     while ((entry = in.getNextEntry()) != null) {
/*  85 */       boolean copy = true;
/*  87 */       for (Iterator<Change> iterator = workingSet.iterator(); iterator.hasNext(); ) {
/*  88 */         Change change = iterator.next();
/*  90 */         int type = change.type();
/*  91 */         String name = entry.getName();
/*  92 */         if (type == 1 && name != null) {
/*  93 */           if (name.equals(change.targetFile())) {
/*  94 */             copy = false;
/*  95 */             iterator.remove();
/*  96 */             results.deleted(name);
/*     */             break;
/*     */           } 
/*     */           continue;
/*     */         } 
/*  99 */         if (type == 4 && name != null)
/* 101 */           if (name.startsWith(change.targetFile() + "/")) {
/* 102 */             copy = false;
/* 103 */             results.deleted(name);
/*     */             break;
/*     */           }  
/*     */       } 
/* 109 */       if (copy && !isDeletedLater(workingSet, entry) && !results.hasBeenAdded(entry.getName())) {
/* 112 */         copyStream((InputStream)in, out, entry);
/* 113 */         results.addedFromStream(entry.getName());
/*     */       } 
/*     */     } 
/* 118 */     for (Iterator<Change> iterator1 = workingSet.iterator(); iterator1.hasNext(); ) {
/* 119 */       Change change = iterator1.next();
/* 121 */       if (change.type() == 2 && !change.isReplaceMode() && !results.hasBeenAdded(change.getEntry().getName())) {
/* 124 */         copyStream(change.getInput(), out, change.getEntry());
/* 125 */         iterator1.remove();
/* 126 */         results.addedFromChangeSet(change.getEntry().getName());
/*     */       } 
/*     */     } 
/* 129 */     out.finish();
/* 130 */     return results;
/*     */   }
/*     */   
/*     */   private boolean isDeletedLater(Set<Change> workingSet, ArchiveEntry entry) {
/* 143 */     String source = entry.getName();
/* 145 */     if (!workingSet.isEmpty())
/* 146 */       for (Change change : workingSet) {
/* 147 */         int type = change.type();
/* 148 */         String target = change.targetFile();
/* 149 */         if (type == 1 && source.equals(target))
/* 150 */           return true; 
/* 153 */         if (type == 4 && source.startsWith(target + "/"))
/* 154 */           return true; 
/*     */       }  
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   private void copyStream(InputStream in, ArchiveOutputStream out, ArchiveEntry entry) throws IOException {
/* 175 */     out.putArchiveEntry(entry);
/* 176 */     IOUtils.copy(in, (OutputStream)out);
/* 177 */     out.closeArchiveEntry();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\changes\ChangeSetPerformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */