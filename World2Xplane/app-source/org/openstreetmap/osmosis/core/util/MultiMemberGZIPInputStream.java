/*     */ package org.openstreetmap.osmosis.core.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ 
/*     */ public class MultiMemberGZIPInputStream extends GZIPInputStream {
/*     */   private MultiMemberGZIPInputStream parent;
/*     */   
/*     */   private MultiMemberGZIPInputStream child;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private boolean eos;
/*     */   
/*     */   public MultiMemberGZIPInputStream(InputStream in, int size) throws IOException {
/*  29 */     super(new PushbackInputStream(in, size), size);
/*  30 */     this.size = size;
/*     */   }
/*     */   
/*     */   public MultiMemberGZIPInputStream(InputStream in) throws IOException {
/*  43 */     super(new PushbackInputStream(in, 1024));
/*  44 */     this.size = -1;
/*     */   }
/*     */   
/*     */   private MultiMemberGZIPInputStream(MultiMemberGZIPInputStream parent) throws IOException {
/*  49 */     super(parent.in);
/*  50 */     this.size = -1;
/*  51 */     if (parent.parent == null) {
/*  52 */       this.parent = parent;
/*     */     } else {
/*  54 */       this.parent = parent.parent;
/*     */     } 
/*  56 */     this.parent.child = this;
/*     */   }
/*     */   
/*     */   private MultiMemberGZIPInputStream(MultiMemberGZIPInputStream parent, int size) throws IOException {
/*  61 */     super(parent.in, size);
/*  62 */     this.size = size;
/*  63 */     if (parent.parent == null) {
/*  64 */       this.parent = parent;
/*     */     } else {
/*  66 */       this.parent = parent.parent;
/*     */     } 
/*  68 */     this.parent.child = this;
/*     */   }
/*     */   
/*     */   public int read(byte[] inputBuffer, int inputBufferOffset, int inputBufferLen) throws IOException {
/*  84 */     if (this.eos)
/*  85 */       return -1; 
/*  87 */     if (this.child != null)
/*  88 */       return this.child.read(inputBuffer, inputBufferOffset, inputBufferLen); 
/*  92 */     int charsRead = super.read(inputBuffer, inputBufferOffset, inputBufferLen);
/*  94 */     if (charsRead == -1) {
/*     */       MultiMemberGZIPInputStream tmpChild;
/*  99 */       int n = this.inf.getRemaining() - 8;
/* 100 */       if (n > 0) {
/* 104 */         ((PushbackInputStream)this.in).unread(this.buf, this.len - n, n);
/*     */       } else {
/* 111 */         byte[] b = new byte[1];
/* 112 */         int ret = this.in.read(b, 0, 1);
/* 113 */         if (ret == -1) {
/* 114 */           this.eos = true;
/* 115 */           return -1;
/*     */         } 
/* 117 */         ((PushbackInputStream)this.in).unread(b, 0, 1);
/*     */       } 
/* 122 */       if (this.size == -1) {
/* 123 */         tmpChild = new MultiMemberGZIPInputStream(this);
/*     */       } else {
/* 125 */         tmpChild = new MultiMemberGZIPInputStream(this, this.size);
/*     */       } 
/* 127 */       return tmpChild.read(inputBuffer, inputBufferOffset, inputBufferLen);
/*     */     } 
/* 129 */     return charsRead;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\MultiMemberGZIPInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */