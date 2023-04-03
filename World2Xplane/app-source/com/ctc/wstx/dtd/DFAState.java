/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public final class DFAState {
/*     */   final int mIndex;
/*     */   
/*     */   final boolean mAccepting;
/*     */   
/*     */   BitSet mTokenSet;
/*     */   
/*  33 */   HashMap mNext = new HashMap();
/*     */   
/*     */   public DFAState(int index, BitSet tokenSet) {
/*  43 */     this.mIndex = index;
/*  45 */     this.mAccepting = tokenSet.get(0);
/*  46 */     this.mTokenSet = tokenSet;
/*     */   }
/*     */   
/*     */   public static DFAState constructDFA(ContentSpec rootSpec) {
/*  52 */     ModelNode modelRoot = rootSpec.rewrite();
/*  57 */     TokenModel eofToken = TokenModel.getNullToken();
/*  58 */     ConcatModel dummyRoot = new ConcatModel(modelRoot, eofToken);
/*  63 */     ArrayList tokens = new ArrayList();
/*  64 */     tokens.add(eofToken);
/*  65 */     dummyRoot.indexTokens(tokens);
/*  70 */     int flen = tokens.size();
/*  71 */     BitSet[] followPos = new BitSet[flen];
/*  72 */     PrefixedName[] tokenNames = new PrefixedName[flen];
/*  73 */     for (int i = 0; i < flen; i++) {
/*  74 */       followPos[i] = new BitSet(flen);
/*  75 */       tokenNames[i] = ((TokenModel)tokens.get(i)).getName();
/*     */     } 
/*  77 */     dummyRoot.calcFollowPos(followPos);
/*  83 */     BitSet initial = new BitSet(flen);
/*  84 */     dummyRoot.addFirstPos(initial);
/*  85 */     DFAState firstState = new DFAState(0, initial);
/*  86 */     ArrayList stateList = new ArrayList();
/*  87 */     stateList.add(firstState);
/*  88 */     HashMap stateMap = new HashMap();
/*  89 */     stateMap.put(initial, firstState);
/*  91 */     int j = 0;
/*  92 */     while (j < stateList.size()) {
/*  93 */       DFAState curr = stateList.get(j++);
/*  94 */       curr.calcNext(tokenNames, followPos, stateList, stateMap);
/*     */     } 
/* 105 */     return firstState;
/*     */   }
/*     */   
/*     */   public boolean isAcceptingState() {
/* 115 */     return this.mAccepting;
/*     */   }
/*     */   
/*     */   public int getIndex() {
/* 119 */     return this.mIndex;
/*     */   }
/*     */   
/*     */   public DFAState findNext(PrefixedName elemName) {
/* 123 */     return (DFAState)this.mNext.get(elemName);
/*     */   }
/*     */   
/*     */   public TreeSet getNextNames() {
/* 128 */     TreeSet names = new TreeSet();
/* 129 */     Iterator it = this.mNext.keySet().iterator();
/* 130 */     while (it.hasNext()) {
/* 131 */       Object o = it.next();
/* 132 */       names.add(o);
/*     */     } 
/* 134 */     return names;
/*     */   }
/*     */   
/*     */   public void calcNext(PrefixedName[] tokenNames, BitSet[] tokenFPs, List stateList, Map stateMap) {
/* 143 */     int first = -1;
/* 148 */     BitSet tokenSet = (BitSet)this.mTokenSet.clone();
/* 150 */     this.mTokenSet = null;
/* 152 */     while ((first = tokenSet.nextSetBit(first + 1)) >= 0) {
/* 153 */       PrefixedName tokenName = tokenNames[first];
/* 158 */       if (tokenName == null)
/*     */         continue; 
/* 162 */       BitSet nextGroup = (BitSet)tokenFPs[first].clone();
/* 163 */       int second = first;
/* 165 */       while ((second = tokenSet.nextSetBit(second + 1)) > 0) {
/* 166 */         if (tokenNames[second] == tokenName) {
/* 168 */           tokenSet.clear(second);
/* 169 */           nextGroup.or(tokenFPs[second]);
/*     */         } 
/*     */       } 
/* 174 */       DFAState next = (DFAState)stateMap.get(nextGroup);
/* 175 */       if (next == null) {
/* 176 */         next = new DFAState(stateList.size(), nextGroup);
/* 177 */         stateList.add(next);
/* 178 */         stateMap.put(nextGroup, next);
/*     */       } 
/* 180 */       this.mNext.put(tokenName, next);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 192 */     StringBuffer sb = new StringBuffer();
/* 193 */     sb.append("State #" + this.mIndex + ":\n");
/* 194 */     sb.append("  Accepting: " + this.mAccepting);
/* 195 */     sb.append("\n  Next states:\n");
/* 196 */     Iterator it = this.mNext.entrySet().iterator();
/* 197 */     while (it.hasNext()) {
/* 198 */       Map.Entry en = it.next();
/* 199 */       sb.append(en.getKey());
/* 200 */       sb.append(" -> ");
/* 201 */       DFAState next = (DFAState)en.getValue();
/* 202 */       sb.append(next.getIndex());
/* 203 */       sb.append("\n");
/*     */     } 
/* 205 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DFAState.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */