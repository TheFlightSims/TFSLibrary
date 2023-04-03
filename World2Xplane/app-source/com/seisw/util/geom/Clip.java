/*      */ package com.seisw.util.geom;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ 
/*      */ public class Clip {
/*      */   private static final boolean DEBUG = false;
/*      */   
/*      */   private static final double GPC_EPSILON = 2.220446049250313E-16D;
/*      */   
/*      */   private static final int LEFT = 0;
/*      */   
/*      */   private static final int RIGHT = 1;
/*      */   
/*      */   private static final int ABOVE = 0;
/*      */   
/*      */   private static final int BELOW = 1;
/*      */   
/*      */   private static final int CLIP = 0;
/*      */   
/*      */   private static final int SUBJ = 1;
/*      */   
/*      */   public static Poly intersection(Poly p1, Poly p2, Class<? extends Poly> polyClass) {
/*  112 */     return clip(OperationType.GPC_INT, p1, p2, polyClass);
/*      */   }
/*      */   
/*      */   public static Poly union(Poly p1, Poly p2, Class<? extends Poly> polyClass) {
/*  126 */     return clip(OperationType.GPC_UNION, p1, p2, polyClass);
/*      */   }
/*      */   
/*      */   public static Poly xor(Poly p1, Poly p2, Class<? extends Poly> polyClass) {
/*  140 */     return clip(OperationType.GPC_XOR, p1, p2, polyClass);
/*      */   }
/*      */   
/*      */   public static Poly difference(Poly p1, Poly p2, Class<? extends Poly> polyClass) {
/*  151 */     return clip(OperationType.GPC_DIFF, p1, p2, polyClass);
/*      */   }
/*      */   
/*      */   public static Poly intersection(Poly p1, Poly p2) {
/*  162 */     return clip(OperationType.GPC_INT, p1, p2, (Class)PolyDefault.class);
/*      */   }
/*      */   
/*      */   public static Poly union(Poly p1, Poly p2) {
/*  174 */     return clip(OperationType.GPC_UNION, p1, p2, (Class)PolyDefault.class);
/*      */   }
/*      */   
/*      */   public static Poly xor(Poly p1, Poly p2) {
/*  186 */     return clip(OperationType.GPC_XOR, p1, p2, (Class)PolyDefault.class);
/*      */   }
/*      */   
/*      */   public static Poly difference(Poly p1, Poly p2) {
/*  197 */     return clip(OperationType.GPC_DIFF, p1, p2, (Class)PolyDefault.class);
/*      */   }
/*      */   
/*      */   private static Poly createNewPoly(Class<? extends Poly> polyClass) {
/*      */     try {
/*  211 */       return polyClass.newInstance();
/*  213 */     } catch (Exception e) {
/*  215 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Poly clip(OperationType op, Poly subj, Poly clip, Class<? extends Poly> polyClass) {
/*  225 */     Poly result = createNewPoly(polyClass);
/*  228 */     if ((subj.isEmpty() && clip.isEmpty()) || (
/*  229 */       subj.isEmpty() && (op == OperationType.GPC_INT || op == OperationType.GPC_DIFF)) || (
/*  230 */       clip.isEmpty() && op == OperationType.GPC_INT))
/*  232 */       return result; 
/*  236 */     if ((op == OperationType.GPC_INT || op == OperationType.GPC_DIFF) && 
/*  237 */       !subj.isEmpty() && !clip.isEmpty())
/*  239 */       minimax_test(subj, clip, op); 
/*  243 */     LmtTable lmt_table = new LmtTable(null);
/*  244 */     ScanBeamTreeEntries sbte = new ScanBeamTreeEntries(null);
/*  245 */     if (!subj.isEmpty())
/*  247 */       build_lmt(lmt_table, sbte, subj, 1, op); 
/*  255 */     if (!clip.isEmpty())
/*  257 */       build_lmt(lmt_table, sbte, clip, 0, op); 
/*  267 */     if (lmt_table.top_node == null)
/*  269 */       return result; 
/*  273 */     double[] sbt = sbte.build_sbt();
/*  275 */     int[] parity = new int[2];
/*  276 */     parity[0] = 0;
/*  277 */     parity[1] = 0;
/*  280 */     if (op == OperationType.GPC_DIFF)
/*  282 */       parity[0] = 1; 
/*  290 */     LmtNode local_min = lmt_table.top_node;
/*  292 */     TopPolygonNode out_poly = new TopPolygonNode(null);
/*  294 */     AetTree aet = new AetTree(null);
/*  295 */     int scanbeam = 0;
/*  298 */     while (scanbeam < sbt.length) {
/*  301 */       double yb = sbt[scanbeam++];
/*  302 */       double yt = 0.0D;
/*  303 */       double dy = 0.0D;
/*  304 */       if (scanbeam < sbt.length) {
/*  306 */         yt = sbt[scanbeam];
/*  307 */         dy = yt - yb;
/*      */       } 
/*  313 */       if (local_min != null)
/*  315 */         if (local_min.y == yb) {
/*  318 */           for (EdgeNode edgeNode = local_min.first_bound; edgeNode != null; edgeNode = edgeNode.next_bound)
/*  320 */             add_edge_to_aet(aet, edgeNode); 
/*  323 */           local_min = local_min.next;
/*      */         }  
/*  332 */       double px = -1.7976931348623157E308D;
/*  335 */       EdgeNode e0 = aet.top_node;
/*  336 */       EdgeNode e1 = aet.top_node;
/*  339 */       aet.top_node.bundle[0][aet.top_node.type] = (aet.top_node.top.y != yb) ? 1 : 0;
/*  340 */       aet.top_node.bundle[0][(aet.top_node.type == 0) ? 1 : 0] = 0;
/*  341 */       aet.top_node.bstate[0] = BundleState.UNBUNDLED;
/*  343 */       for (EdgeNode next_edge = aet.top_node.next; next_edge != null; next_edge = next_edge.next) {
/*  345 */         int ne_type = next_edge.type;
/*  346 */         int ne_type_opp = (next_edge.type == 0) ? 1 : 0;
/*  349 */         next_edge.bundle[0][ne_type] = (next_edge.top.y != yb) ? 1 : 0;
/*  350 */         next_edge.bundle[0][ne_type_opp] = 0;
/*  351 */         next_edge.bstate[0] = BundleState.UNBUNDLED;
/*  354 */         if (next_edge.bundle[0][ne_type] == 1) {
/*  356 */           if (EQ(e0.xb, next_edge.xb) && EQ(e0.dx, next_edge.dx) && e0.top.y != yb) {
/*  358 */             next_edge.bundle[0][ne_type] = next_edge.bundle[0][ne_type] ^ e0.bundle[0][ne_type];
/*  359 */             next_edge.bundle[0][ne_type_opp] = e0.bundle[0][ne_type_opp];
/*  360 */             next_edge.bstate[0] = BundleState.BUNDLE_HEAD;
/*  361 */             e0.bundle[0][0] = 0;
/*  362 */             e0.bundle[0][1] = 0;
/*  363 */             e0.bstate[0] = BundleState.BUNDLE_TAIL;
/*      */           } 
/*  365 */           e0 = next_edge;
/*      */         } 
/*      */       } 
/*  369 */       int[] horiz = new int[2];
/*  370 */       horiz[0] = 0;
/*  371 */       horiz[1] = 0;
/*  373 */       int[] exists = new int[2];
/*  374 */       exists[0] = 0;
/*  375 */       exists[1] = 0;
/*  377 */       PolygonNode cf = null;
/*      */       EdgeNode edge;
/*  380 */       for (edge = aet.top_node; edge != null; edge = 
/*      */         
/*  543 */         edge.next) {
/*      */         exists[0] = edge.bundle[0][0] + (edge.bundle[1][0] << 1);
/*      */         exists[1] = edge.bundle[0][1] + (edge.bundle[1][1] << 1);
/*      */         if (exists[0] != 0 || exists[1] != 0) {
/*      */           edge.bside[0] = parity[0];
/*      */           edge.bside[1] = parity[1];
/*      */           boolean contributing = false;
/*      */           int br = 0, bl = 0, tr = 0, tl = 0;
/*      */           if (op == OperationType.GPC_DIFF || op == OperationType.GPC_INT) {
/*      */             contributing = !((exists[0] == 0 || (parity[1] == 0 && horiz[1] == 0)) && (exists[1] == 0 || (parity[0] == 0 && horiz[0] == 0)) && (exists[0] == 0 || exists[1] == 0 || parity[0] != parity[1]));
/*      */             br = (parity[0] != 0 && parity[1] != 0) ? 1 : 0;
/*      */             bl = ((parity[0] ^ edge.bundle[0][0]) != 0 && (parity[1] ^ edge.bundle[0][1]) != 0) ? 1 : 0;
/*      */             tr = ((parity[0] ^ ((horiz[0] != 0) ? 1 : 0)) != 0 && (parity[1] ^ ((horiz[1] != 0) ? 1 : 0)) != 0) ? 1 : 0;
/*      */             tl = ((parity[0] ^ ((horiz[0] != 0) ? 1 : 0) ^ edge.bundle[1][0]) != 0 && (parity[1] ^ ((horiz[1] != 0) ? 1 : 0) ^ edge.bundle[1][1]) != 0) ? 1 : 0;
/*      */           } else if (op == OperationType.GPC_XOR) {
/*      */             contributing = !(exists[0] == 0 && exists[1] == 0);
/*      */             br = parity[0] ^ parity[1];
/*      */             bl = parity[0] ^ edge.bundle[0][0] ^ parity[1] ^ edge.bundle[0][1];
/*      */             tr = parity[0] ^ ((horiz[0] != 0) ? 1 : 0) ^ parity[1] ^ ((horiz[1] != 0) ? 1 : 0);
/*      */             tl = parity[0] ^ ((horiz[0] != 0) ? 1 : 0) ^ edge.bundle[1][0] ^ parity[1] ^ ((horiz[1] != 0) ? 1 : 0) ^ edge.bundle[1][1];
/*      */           } else if (op == OperationType.GPC_UNION) {
/*      */             contributing = !((exists[0] == 0 || (parity[1] != 0 && horiz[1] == 0)) && (exists[1] == 0 || (parity[0] != 0 && horiz[0] == 0)) && (exists[0] == 0 || exists[1] == 0 || parity[0] != parity[1]));
/*      */             br = (parity[0] != 0 || parity[1] != 0) ? 1 : 0;
/*      */             bl = ((parity[0] ^ edge.bundle[0][0]) != 0 || (parity[1] ^ edge.bundle[0][1]) != 0) ? 1 : 0;
/*      */             tr = ((parity[0] ^ ((horiz[0] != 0) ? 1 : 0)) != 0 || (parity[1] ^ ((horiz[1] != 0) ? 1 : 0)) != 0) ? 1 : 0;
/*      */             tl = ((parity[0] ^ ((horiz[0] != 0) ? 1 : 0) ^ edge.bundle[1][0]) != 0 || (parity[1] ^ ((horiz[1] != 0) ? 1 : 0) ^ edge.bundle[1][1]) != 0) ? 1 : 0;
/*      */           } else {
/*      */             throw new IllegalStateException("Unknown op");
/*      */           } 
/*      */           parity[0] = parity[0] ^ edge.bundle[0][0];
/*      */           parity[1] = parity[1] ^ edge.bundle[0][1];
/*      */           if (exists[0] != 0)
/*      */             horiz[0] = HState.next_h_state[horiz[0]][(exists[0] - 1 << 1) + parity[0]]; 
/*      */           if (exists[1] != 0)
/*      */             horiz[1] = HState.next_h_state[horiz[1]][(exists[1] - 1 << 1) + parity[1]]; 
/*      */           if (contributing) {
/*      */             double xb = edge.xb;
/*      */             int vclass = VertexType.getType(tr, tl, br, bl);
/*      */             switch (vclass) {
/*      */               case 7:
/*      */               case 8:
/*      */                 edge.outp[0] = out_poly.add_local_min(xb, yb);
/*      */                 px = xb;
/*      */                 cf = edge.outp[0];
/*      */                 break;
/*      */               case 4:
/*      */                 if (xb != px) {
/*      */                   cf.add_right(xb, yb);
/*      */                   px = xb;
/*      */                 } 
/*      */                 edge.outp[0] = cf;
/*      */                 cf = null;
/*      */                 break;
/*      */               case 2:
/*      */                 edge.outp[1].add_left(xb, yb);
/*      */                 px = xb;
/*      */                 cf = edge.outp[1];
/*      */                 break;
/*      */               case 1:
/*      */                 if (xb != px) {
/*      */                   cf.add_left(xb, yb);
/*      */                   px = xb;
/*      */                 } 
/*      */                 out_poly.merge_right(cf, edge.outp[1]);
/*      */                 cf = null;
/*      */                 break;
/*      */               case 11:
/*      */                 if (xb != px) {
/*      */                   cf.add_left(xb, yb);
/*      */                   px = xb;
/*      */                 } 
/*      */                 edge.outp[0] = cf;
/*      */                 cf = null;
/*      */                 break;
/*      */               case 13:
/*      */                 edge.outp[1].add_right(xb, yb);
/*      */                 px = xb;
/*      */                 cf = edge.outp[1];
/*      */                 edge.outp[1] = null;
/*      */                 break;
/*      */               case 14:
/*      */                 if (xb != px) {
/*      */                   cf.add_right(xb, yb);
/*      */                   px = xb;
/*      */                 } 
/*      */                 out_poly.merge_left(cf, edge.outp[1]);
/*      */                 cf = null;
/*      */                 edge.outp[1] = null;
/*      */                 break;
/*      */               case 6:
/*      */                 if (xb != px) {
/*      */                   cf.add_right(xb, yb);
/*      */                   px = xb;
/*      */                 } 
/*      */                 out_poly.merge_left(cf, edge.outp[1]);
/*      */                 edge.outp[1] = null;
/*      */                 edge.outp[0] = out_poly.add_local_min(xb, yb);
/*      */                 cf = edge.outp[0];
/*      */                 break;
/*      */               case 9:
/*      */                 if (xb != px) {
/*      */                   cf.add_left(xb, yb);
/*      */                   px = xb;
/*      */                 } 
/*      */                 out_poly.merge_right(cf, edge.outp[1]);
/*      */                 edge.outp[1] = null;
/*      */                 edge.outp[0] = out_poly.add_local_min(xb, yb);
/*      */                 cf = edge.outp[0];
/*      */                 break;
/*      */               case 10:
/*      */                 if (edge.bot.y == yb)
/*      */                   edge.outp[1].add_left(xb, yb); 
/*      */                 edge.outp[0] = edge.outp[1];
/*      */                 px = xb;
/*      */                 break;
/*      */               case 5:
/*      */                 if (edge.bot.y == yb)
/*      */                   edge.outp[1].add_right(xb, yb); 
/*      */                 edge.outp[0] = edge.outp[1];
/*      */                 px = xb;
/*      */                 break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  554 */       for (edge = aet.top_node; edge != null; edge = edge.next) {
/*  556 */         if (edge.top.y == yb) {
/*  558 */           EdgeNode prev_edge = edge.prev;
/*  559 */           EdgeNode edgeNode1 = edge.next;
/*  561 */           if (prev_edge != null) {
/*  562 */             prev_edge.next = edgeNode1;
/*      */           } else {
/*  564 */             aet.top_node = edgeNode1;
/*      */           } 
/*  566 */           if (edgeNode1 != null)
/*  567 */             edgeNode1.prev = prev_edge; 
/*  570 */           if (edge.bstate[1] == BundleState.BUNDLE_HEAD && prev_edge != null)
/*  572 */             if (prev_edge.bstate[1] == BundleState.BUNDLE_TAIL) {
/*  574 */               prev_edge.outp[1] = edge.outp[1];
/*  575 */               prev_edge.bstate[1] = BundleState.UNBUNDLED;
/*  576 */               if (prev_edge.prev != null)
/*  578 */                 if (prev_edge.prev.bstate[1] == BundleState.BUNDLE_TAIL)
/*  580 */                   prev_edge.bstate[1] = BundleState.BUNDLE_HEAD;  
/*      */             }  
/*  588 */         } else if (edge.top.y == yt) {
/*  589 */           edge.xt = edge.top.x;
/*      */         } else {
/*  591 */           edge.xt = edge.bot.x + edge.dx * (yt - edge.bot.y);
/*      */         } 
/*      */       } 
/*  595 */       if (scanbeam < sbte.sbt_entries) {
/*  600 */         ItNodeTable it_table = new ItNodeTable(null);
/*  601 */         it_table.build_intersection_table(aet, dy);
/*  604 */         for (ItNode intersect = it_table.top_node; intersect != null; intersect = intersect.next) {
/*  606 */           e0 = intersect.ie[0];
/*  607 */           e1 = intersect.ie[1];
/*  610 */           if ((e0.bundle[0][0] != 0 || e0.bundle[0][1] != 0) && (
/*  611 */             e1.bundle[0][0] != 0 || e1.bundle[0][1] != 0)) {
/*  613 */             PolygonNode p = e0.outp[0];
/*  614 */             PolygonNode q = e1.outp[0];
/*  615 */             double ix = intersect.point.x;
/*  616 */             double iy = intersect.point.y + yb;
/*  618 */             int in_clip = ((e0.bundle[0][0] != 0 && e0.bside[0] == 0) || (
/*  619 */               e1.bundle[0][0] != 0 && e1.bside[0] != 0) || (
/*  620 */               e0.bundle[0][0] == 0 && e1.bundle[0][0] == 0 && 
/*  621 */               e0.bside[0] != 0 && e1.bside[0] != 0)) ? 1 : 0;
/*  623 */             int in_subj = ((e0.bundle[0][1] != 0 && e0.bside[1] == 0) || (
/*  624 */               e1.bundle[0][1] != 0 && e1.bside[1] != 0) || (
/*  625 */               e0.bundle[0][1] == 0 && e1.bundle[0][1] == 0 && 
/*  626 */               e0.bside[1] != 0 && e1.bside[1] != 0)) ? 1 : 0;
/*  628 */             int tr = 0, tl = 0, br = 0, bl = 0;
/*  630 */             if (op == OperationType.GPC_DIFF || op == OperationType.GPC_INT) {
/*  632 */               tr = (in_clip != 0 && in_subj != 0) ? 1 : 0;
/*  633 */               tl = ((in_clip ^ e1.bundle[0][0]) != 0 && (in_subj ^ e1.bundle[0][1]) != 0) ? 1 : 0;
/*  634 */               br = ((in_clip ^ e0.bundle[0][0]) != 0 && (in_subj ^ e0.bundle[0][1]) != 0) ? 1 : 0;
/*  635 */               bl = ((in_clip ^ e1.bundle[0][0] ^ e0.bundle[0][0]) != 0 && (
/*  636 */                 in_subj ^ e1.bundle[0][1] ^ e0.bundle[0][1]) != 0) ? 1 : 0;
/*  638 */             } else if (op == OperationType.GPC_XOR) {
/*  640 */               tr = in_clip ^ in_subj;
/*  641 */               tl = in_clip ^ e1.bundle[0][0] ^ in_subj ^ e1.bundle[0][1];
/*  642 */               br = in_clip ^ e0.bundle[0][0] ^ in_subj ^ e0.bundle[0][1];
/*  643 */               bl = in_clip ^ e1.bundle[0][0] ^ e0.bundle[0][0] ^ 
/*  644 */                 in_subj ^ e1.bundle[0][1] ^ e0.bundle[0][1];
/*  646 */             } else if (op == OperationType.GPC_UNION) {
/*  648 */               tr = (in_clip != 0 || in_subj != 0) ? 1 : 0;
/*  649 */               tl = ((in_clip ^ e1.bundle[0][0]) != 0 || (in_subj ^ e1.bundle[0][1]) != 0) ? 1 : 0;
/*  650 */               br = ((in_clip ^ e0.bundle[0][0]) != 0 || (in_subj ^ e0.bundle[0][1]) != 0) ? 1 : 0;
/*  651 */               bl = ((in_clip ^ e1.bundle[0][0] ^ e0.bundle[0][0]) != 0 || (
/*  652 */                 in_subj ^ e1.bundle[0][1] ^ e0.bundle[0][1]) != 0) ? 1 : 0;
/*      */             } else {
/*  656 */               throw new IllegalStateException("Unknown op type, " + op);
/*      */             } 
/*  659 */             int vclass = VertexType.getType(tr, tl, br, bl);
/*  660 */             switch (vclass) {
/*      */               case 8:
/*  663 */                 e0.outp[0] = out_poly.add_local_min(ix, iy);
/*  664 */                 e1.outp[0] = e0.outp[0];
/*      */                 break;
/*      */               case 4:
/*  667 */                 if (p != null) {
/*  669 */                   p.add_right(ix, iy);
/*  670 */                   e1.outp[0] = p;
/*  671 */                   e0.outp[0] = null;
/*      */                 } 
/*      */                 break;
/*      */               case 2:
/*  675 */                 if (q != null) {
/*  677 */                   q.add_left(ix, iy);
/*  678 */                   e0.outp[0] = q;
/*  679 */                   e1.outp[0] = null;
/*      */                 } 
/*      */                 break;
/*      */               case 1:
/*  683 */                 if (p != null && q != null) {
/*  685 */                   p.add_left(ix, iy);
/*  686 */                   out_poly.merge_right(p, q);
/*  687 */                   e0.outp[0] = null;
/*  688 */                   e1.outp[0] = null;
/*      */                 } 
/*      */                 break;
/*      */               case 7:
/*  692 */                 e0.outp[0] = out_poly.add_local_min(ix, iy);
/*  693 */                 e1.outp[0] = e0.outp[0];
/*      */                 break;
/*      */               case 11:
/*  696 */                 if (p != null) {
/*  698 */                   p.add_left(ix, iy);
/*  699 */                   e1.outp[0] = p;
/*  700 */                   e0.outp[0] = null;
/*      */                 } 
/*      */                 break;
/*      */               case 13:
/*  704 */                 if (q != null) {
/*  706 */                   q.add_right(ix, iy);
/*  707 */                   e0.outp[0] = q;
/*  708 */                   e1.outp[0] = null;
/*      */                 } 
/*      */                 break;
/*      */               case 14:
/*  712 */                 if (p != null && q != null) {
/*  714 */                   p.add_right(ix, iy);
/*  715 */                   out_poly.merge_left(p, q);
/*  716 */                   e0.outp[0] = null;
/*  717 */                   e1.outp[0] = null;
/*      */                 } 
/*      */                 break;
/*      */               case 6:
/*  721 */                 if (p != null && q != null) {
/*  723 */                   p.add_right(ix, iy);
/*  724 */                   out_poly.merge_left(p, q);
/*  725 */                   e0.outp[0] = out_poly.add_local_min(ix, iy);
/*  726 */                   e1.outp[0] = e0.outp[0];
/*      */                 } 
/*      */                 break;
/*      */               case 9:
/*  730 */                 if (p != null && q != null) {
/*  732 */                   p.add_left(ix, iy);
/*  733 */                   out_poly.merge_right(p, q);
/*  734 */                   e0.outp[0] = out_poly.add_local_min(ix, iy);
/*  735 */                   e1.outp[0] = e0.outp[0];
/*      */                 } 
/*      */                 break;
/*      */             } 
/*      */           } 
/*  739 */           if (e0
/*      */             
/*  744 */             .bundle[0][0] != 0)
/*  745 */             e1.bside[0] = (e1.bside[0] == 0) ? 1 : 0; 
/*  746 */           if (e1.bundle[0][0] != 0)
/*  747 */             e0.bside[0] = (e0.bside[0] == 0) ? 1 : 0; 
/*  748 */           if (e0.bundle[0][1] != 0)
/*  749 */             e1.bside[1] = (e1.bside[1] == 0) ? 1 : 0; 
/*  750 */           if (e1.bundle[0][1] != 0)
/*  751 */             e0.bside[1] = (e0.bside[1] == 0) ? 1 : 0; 
/*  754 */           EdgeNode prev_edge = e0.prev;
/*  755 */           EdgeNode edgeNode1 = e1.next;
/*  756 */           if (edgeNode1 != null)
/*  758 */             edgeNode1.prev = e0; 
/*  761 */           if (e0.bstate[0] == BundleState.BUNDLE_HEAD) {
/*  763 */             boolean search = true;
/*  764 */             while (search) {
/*  766 */               prev_edge = prev_edge.prev;
/*  767 */               if (prev_edge != null) {
/*  769 */                 if (prev_edge.bstate[0] != BundleState.BUNDLE_TAIL)
/*  771 */                   search = false; 
/*      */                 continue;
/*      */               } 
/*  776 */               search = false;
/*      */             } 
/*      */           } 
/*  780 */           if (prev_edge == null) {
/*  782 */             aet.top_node.prev = e1;
/*  783 */             e1.next = aet.top_node;
/*  784 */             aet.top_node = e0.next;
/*      */           } else {
/*  788 */             prev_edge.next.prev = e1;
/*  789 */             e1.next = prev_edge.next;
/*  790 */             prev_edge.next = e0.next;
/*      */           } 
/*  792 */           e0.next.prev = prev_edge;
/*  793 */           e1.next.prev = e1;
/*  794 */           e0.next = edgeNode1;
/*      */         } 
/*  802 */         for (EdgeNode edgeNode = aet.top_node; edgeNode != null; edgeNode = edgeNode.next) {
/*  804 */           EdgeNode edgeNode1 = edgeNode.next;
/*  805 */           EdgeNode succ_edge = edgeNode.succ;
/*  806 */           if (edgeNode.top.y == yt && succ_edge != null) {
/*  809 */             succ_edge.outp[1] = edgeNode.outp[0];
/*  810 */             succ_edge.bstate[1] = edgeNode.bstate[0];
/*  811 */             succ_edge.bundle[1][0] = edgeNode.bundle[0][0];
/*  812 */             succ_edge.bundle[1][1] = edgeNode.bundle[0][1];
/*  813 */             EdgeNode prev_edge = edgeNode.prev;
/*  814 */             if (prev_edge != null) {
/*  815 */               prev_edge.next = succ_edge;
/*      */             } else {
/*  817 */               aet.top_node = succ_edge;
/*      */             } 
/*  818 */             if (edgeNode1 != null)
/*  819 */               edgeNode1.prev = succ_edge; 
/*  820 */             succ_edge.prev = prev_edge;
/*  821 */             succ_edge.next = edgeNode1;
/*      */           } else {
/*  826 */             edgeNode.outp[1] = edgeNode.outp[0];
/*  827 */             edgeNode.bstate[1] = edgeNode.bstate[0];
/*  828 */             edgeNode.bundle[1][0] = edgeNode.bundle[0][0];
/*  829 */             edgeNode.bundle[1][1] = edgeNode.bundle[0][1];
/*  830 */             edgeNode.xb = edgeNode.xt;
/*      */           } 
/*  832 */           edgeNode.outp[0] = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*  838 */     result = out_poly.getResult(polyClass);
/*  840 */     return result;
/*      */   }
/*      */   
/*      */   private static boolean EQ(double a, double b) {
/*  845 */     return (Math.abs(a - b) <= 2.220446049250313E-16D);
/*      */   }
/*      */   
/*      */   private static int PREV_INDEX(int i, int n) {
/*  850 */     return (i - 1 + n) % n;
/*      */   }
/*      */   
/*      */   private static int NEXT_INDEX(int i, int n) {
/*  855 */     return (i + 1) % n;
/*      */   }
/*      */   
/*      */   private static boolean OPTIMAL(Poly p, int i) {
/*  860 */     if (p.getY(PREV_INDEX(i, p.getNumPoints())) == p.getY(i) && 
/*  861 */       p.getY(NEXT_INDEX(i, p.getNumPoints())) == p.getY(i))
/*  861 */       return false; 
/*      */     return true;
/*      */   }
/*      */   
/*      */   private static Rectangle2D[] create_contour_bboxes(Poly p) {
/*  866 */     Rectangle2D[] box = new Rectangle2D[p.getNumInnerPoly()];
/*  869 */     for (int c = 0; c < p.getNumInnerPoly(); c++) {
/*  871 */       Poly inner_poly = p.getInnerPoly(c);
/*  872 */       box[c] = inner_poly.getBounds();
/*      */     } 
/*  874 */     return box;
/*      */   }
/*      */   
/*      */   private static void minimax_test(Poly subj, Poly clip, OperationType op) {
/*  879 */     Rectangle2D[] s_bbox = create_contour_bboxes(subj);
/*  880 */     Rectangle2D[] c_bbox = create_contour_bboxes(clip);
/*  882 */     int subj_num_poly = subj.getNumInnerPoly();
/*  883 */     int clip_num_poly = clip.getNumInnerPoly();
/*  884 */     boolean[][] o_table = new boolean[subj_num_poly][clip_num_poly];
/*  887 */     for (int s = 0; s < subj_num_poly; s++) {
/*  889 */       for (int i = 0; i < clip_num_poly; i++)
/*  891 */         o_table[s][i] = (
/*  892 */           s_bbox[s].getMaxX() >= c_bbox[i].getMinX() && 
/*  893 */           s_bbox[s].getMinX() <= c_bbox[i].getMaxX() && 
/*  894 */           s_bbox[s].getMaxY() >= c_bbox[i].getMinY() && 
/*  895 */           s_bbox[s].getMinY() <= c_bbox[i].getMaxY()); 
/*      */     } 
/*  900 */     for (int c = 0; c < clip_num_poly; c++) {
/*  902 */       boolean overlap = false;
/*  903 */       for (int i = 0; !overlap && i < subj_num_poly; i++)
/*  905 */         overlap = o_table[i][c]; 
/*  907 */       if (!overlap)
/*  909 */         clip.setContributing(c, false); 
/*      */     } 
/*  913 */     if (op == OperationType.GPC_INT)
/*  916 */       for (int i = 0; i < subj_num_poly; i++) {
/*  918 */         boolean overlap = false;
/*  919 */         for (int j = 0; !overlap && j < clip_num_poly; j++)
/*  921 */           overlap = o_table[i][j]; 
/*  923 */         if (!overlap)
/*  925 */           subj.setContributing(i, false); 
/*      */       }  
/*      */   }
/*      */   
/*      */   private static LmtNode bound_list(LmtTable lmt_table, double y) {
/*  933 */     if (lmt_table.top_node == null) {
/*  935 */       lmt_table.top_node = new LmtNode(y);
/*  936 */       return lmt_table.top_node;
/*      */     } 
/*  940 */     LmtNode prev = null;
/*  941 */     LmtNode node = lmt_table.top_node;
/*  942 */     boolean done = false;
/*  943 */     while (!done) {
/*  945 */       if (y < node.y) {
/*  948 */         LmtNode existing_node = node;
/*  949 */         node = new LmtNode(y);
/*  950 */         node.next = existing_node;
/*  951 */         if (prev == null) {
/*  953 */           lmt_table.top_node = node;
/*      */         } else {
/*  957 */           prev.next = node;
/*      */         } 
/*  959 */         done = true;
/*      */         continue;
/*      */       } 
/*  961 */       if (y > node.y) {
/*  964 */         if (node.next == null) {
/*  966 */           node.next = new LmtNode(y);
/*  967 */           node = node.next;
/*  968 */           done = true;
/*      */           continue;
/*      */         } 
/*  972 */         prev = node;
/*  973 */         node = node.next;
/*      */         continue;
/*      */       } 
/*  979 */       done = true;
/*      */     } 
/*  982 */     return node;
/*      */   }
/*      */   
/*      */   private static void insert_bound(LmtNode lmt_node, EdgeNode e) {
/*  988 */     if (lmt_node.first_bound == null) {
/*  991 */       lmt_node.first_bound = e;
/*      */     } else {
/*  995 */       boolean done = false;
/*  996 */       EdgeNode prev_bound = null;
/*  997 */       EdgeNode current_bound = lmt_node.first_bound;
/*  998 */       while (!done) {
/* 1001 */         if (e.bot.x < current_bound.bot.x) {
/* 1004 */           if (prev_bound == null) {
/* 1006 */             lmt_node.first_bound = e;
/*      */           } else {
/* 1010 */             prev_bound.next_bound = e;
/*      */           } 
/* 1012 */           e.next_bound = current_bound;
/* 1014 */           done = true;
/*      */           continue;
/*      */         } 
/* 1016 */         if (e.bot.x == current_bound.bot.x) {
/* 1019 */           if (e.dx < current_bound.dx) {
/* 1022 */             if (prev_bound == null) {
/* 1024 */               lmt_node.first_bound = e;
/*      */             } else {
/* 1028 */               prev_bound.next_bound = e;
/*      */             } 
/* 1030 */             e.next_bound = current_bound;
/* 1031 */             done = true;
/*      */             continue;
/*      */           } 
/* 1036 */           if (current_bound.next_bound == null) {
/* 1038 */             current_bound.next_bound = e;
/* 1039 */             done = true;
/*      */             continue;
/*      */           } 
/* 1043 */           prev_bound = current_bound;
/* 1044 */           current_bound = current_bound.next_bound;
/*      */           continue;
/*      */         } 
/* 1051 */         if (current_bound.next_bound == null) {
/* 1053 */           current_bound.next_bound = e;
/* 1054 */           done = true;
/*      */           continue;
/*      */         } 
/* 1058 */         prev_bound = current_bound;
/* 1059 */         current_bound = current_bound.next_bound;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void add_edge_to_aet(AetTree aet, EdgeNode edge) {
/* 1068 */     if (aet.top_node == null) {
/* 1071 */       aet.top_node = edge;
/* 1072 */       edge.prev = null;
/* 1073 */       edge.next = null;
/*      */     } else {
/* 1077 */       EdgeNode current_edge = aet.top_node;
/* 1078 */       EdgeNode prev = null;
/* 1079 */       boolean done = false;
/* 1080 */       while (!done) {
/* 1083 */         if (edge.xb < current_edge.xb) {
/* 1086 */           edge.prev = prev;
/* 1087 */           edge.next = current_edge;
/* 1088 */           current_edge.prev = edge;
/* 1089 */           if (prev == null) {
/* 1091 */             aet.top_node = edge;
/*      */           } else {
/* 1095 */             prev.next = edge;
/*      */           } 
/* 1097 */           done = true;
/*      */           continue;
/*      */         } 
/* 1099 */         if (edge.xb == current_edge.xb) {
/* 1102 */           if (edge.dx < current_edge.dx) {
/* 1105 */             edge.prev = prev;
/* 1106 */             edge.next = current_edge;
/* 1107 */             current_edge.prev = edge;
/* 1108 */             if (prev == null) {
/* 1110 */               aet.top_node = edge;
/*      */             } else {
/* 1114 */               prev.next = edge;
/*      */             } 
/* 1116 */             done = true;
/*      */             continue;
/*      */           } 
/* 1121 */           prev = current_edge;
/* 1122 */           if (current_edge.next == null) {
/* 1124 */             current_edge.next = edge;
/* 1125 */             edge.prev = current_edge;
/* 1126 */             edge.next = null;
/* 1127 */             done = true;
/*      */             continue;
/*      */           } 
/* 1131 */           current_edge = current_edge.next;
/*      */           continue;
/*      */         } 
/* 1138 */         prev = current_edge;
/* 1139 */         if (current_edge.next == null) {
/* 1141 */           current_edge.next = edge;
/* 1142 */           edge.prev = current_edge;
/* 1143 */           edge.next = null;
/* 1144 */           done = true;
/*      */           continue;
/*      */         } 
/* 1148 */         current_edge = current_edge.next;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void add_to_sbtree(ScanBeamTreeEntries sbte, double y) {
/* 1157 */     if (sbte.sb_tree == null) {
/* 1160 */       sbte.sb_tree = new ScanBeamTree(y);
/* 1161 */       sbte.sbt_entries++;
/*      */       return;
/*      */     } 
/* 1164 */     ScanBeamTree tree_node = sbte.sb_tree;
/* 1165 */     boolean done = false;
/* 1166 */     while (!done) {
/* 1168 */       if (tree_node.y > y) {
/* 1170 */         if (tree_node.less == null) {
/* 1172 */           tree_node.less = new ScanBeamTree(y);
/* 1173 */           sbte.sbt_entries++;
/* 1174 */           done = true;
/*      */           continue;
/*      */         } 
/* 1178 */         tree_node = tree_node.less;
/*      */         continue;
/*      */       } 
/* 1181 */       if (tree_node.y < y) {
/* 1183 */         if (tree_node.more == null) {
/* 1185 */           tree_node.more = new ScanBeamTree(y);
/* 1186 */           sbte.sbt_entries++;
/* 1187 */           done = true;
/*      */           continue;
/*      */         } 
/* 1191 */         tree_node = tree_node.more;
/*      */         continue;
/*      */       } 
/* 1196 */       done = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static EdgeTable build_lmt(LmtTable lmt_table, ScanBeamTreeEntries sbte, Poly p, int type, OperationType op) {
/* 1208 */     EdgeTable edge_table = new EdgeTable(null);
/* 1210 */     for (int c = 0; c < p.getNumInnerPoly(); c++) {
/* 1212 */       Poly ip = p.getInnerPoly(c);
/* 1213 */       if (!ip.isContributing(0)) {
/* 1216 */         ip.setContributing(0, true);
/*      */       } else {
/* 1221 */         int num_vertices = 0;
/* 1222 */         int e_index = 0;
/* 1223 */         edge_table = new EdgeTable(null);
/* 1224 */         for (int i = 0; i < ip.getNumPoints(); i++) {
/* 1226 */           if (OPTIMAL(ip, i)) {
/* 1228 */             double x = ip.getX(i);
/* 1229 */             double y = ip.getY(i);
/* 1230 */             edge_table.addNode(x, y);
/* 1233 */             add_to_sbtree(sbte, ip.getY(i));
/* 1235 */             num_vertices++;
/*      */           } 
/*      */         } 
/*      */         int min;
/* 1240 */         for (min = 0; min < num_vertices; min++) {
/* 1243 */           if (edge_table.FWD_MIN(min)) {
/* 1246 */             int num_edges = 1;
/* 1247 */             int max = NEXT_INDEX(min, num_vertices);
/* 1248 */             while (edge_table.NOT_FMAX(max)) {
/* 1250 */               num_edges++;
/* 1251 */               max = NEXT_INDEX(max, num_vertices);
/*      */             } 
/* 1255 */             int v = min;
/* 1256 */             EdgeNode e = edge_table.getNode(e_index);
/* 1257 */             e.bstate[1] = BundleState.UNBUNDLED;
/* 1258 */             e.bundle[1][0] = 0;
/* 1259 */             e.bundle[1][1] = 0;
/* 1261 */             for (int j = 0; j < num_edges; j++) {
/* 1263 */               EdgeNode ei = edge_table.getNode(e_index + j);
/* 1264 */               EdgeNode ev = edge_table.getNode(v);
/* 1266 */               ei.xb = ev.vertex.x;
/* 1267 */               ei.bot.x = ev.vertex.x;
/* 1268 */               ei.bot.y = ev.vertex.y;
/* 1270 */               v = NEXT_INDEX(v, num_vertices);
/* 1271 */               ev = edge_table.getNode(v);
/* 1273 */               ei.top.x = ev.vertex.x;
/* 1274 */               ei.top.y = ev.vertex.y;
/* 1275 */               ei.dx = (ev.vertex.x - ei.bot.x) / (ei.top.y - ei.bot.y);
/* 1276 */               ei.type = type;
/* 1277 */               ei.outp[0] = null;
/* 1278 */               ei.outp[1] = null;
/* 1279 */               ei.next = null;
/* 1280 */               ei.prev = null;
/* 1281 */               ei.succ = (num_edges > 1 && j < num_edges - 1) ? edge_table.getNode(e_index + j + 1) : null;
/* 1282 */               ei.next_bound = null;
/* 1283 */               ei.bside[0] = (op == OperationType.GPC_DIFF) ? 1 : 0;
/* 1284 */               ei.bside[1] = 0;
/*      */             } 
/* 1286 */             insert_bound(bound_list(lmt_table, (edge_table.getNode(min)).vertex.y), e);
/* 1292 */             e_index += num_edges;
/*      */           } 
/*      */         } 
/* 1297 */         for (min = 0; min < num_vertices; min++) {
/* 1300 */           if (edge_table.REV_MIN(min)) {
/* 1303 */             int num_edges = 1;
/* 1304 */             int max = PREV_INDEX(min, num_vertices);
/* 1305 */             while (edge_table.NOT_RMAX(max)) {
/* 1307 */               num_edges++;
/* 1308 */               max = PREV_INDEX(max, num_vertices);
/*      */             } 
/* 1312 */             int v = min;
/* 1313 */             EdgeNode e = edge_table.getNode(e_index);
/* 1314 */             e.bstate[1] = BundleState.UNBUNDLED;
/* 1315 */             e.bundle[1][0] = 0;
/* 1316 */             e.bundle[1][1] = 0;
/* 1318 */             for (int j = 0; j < num_edges; j++) {
/* 1320 */               EdgeNode ei = edge_table.getNode(e_index + j);
/* 1321 */               EdgeNode ev = edge_table.getNode(v);
/* 1323 */               ei.xb = ev.vertex.x;
/* 1324 */               ei.bot.x = ev.vertex.x;
/* 1325 */               ei.bot.y = ev.vertex.y;
/* 1327 */               v = PREV_INDEX(v, num_vertices);
/* 1328 */               ev = edge_table.getNode(v);
/* 1330 */               ei.top.x = ev.vertex.x;
/* 1331 */               ei.top.y = ev.vertex.y;
/* 1332 */               ei.dx = (ev.vertex.x - ei.bot.x) / (ei.top.y - ei.bot.y);
/* 1333 */               ei.type = type;
/* 1334 */               ei.outp[0] = null;
/* 1335 */               ei.outp[1] = null;
/* 1336 */               ei.next = null;
/* 1337 */               ei.prev = null;
/* 1338 */               ei.succ = (num_edges > 1 && j < num_edges - 1) ? edge_table.getNode(e_index + j + 1) : null;
/* 1340 */               ei.next_bound = null;
/* 1341 */               ei.bside[0] = (op == OperationType.GPC_DIFF) ? 1 : 0;
/* 1342 */               ei.bside[1] = 0;
/*      */             } 
/* 1344 */             insert_bound(bound_list(lmt_table, (edge_table.getNode(min)).vertex.y), e);
/* 1350 */             e_index += num_edges;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1355 */     return edge_table;
/*      */   }
/*      */   
/*      */   private static StNode add_st_edge(StNode st, ItNodeTable it, EdgeNode edge, double dy) {
/* 1360 */     if (st == null) {
/* 1363 */       st = new StNode(edge, null);
/*      */     } else {
/* 1367 */       double den = st.xt - st.xb - edge.xt - edge.xb;
/* 1370 */       if (edge.xt >= st.xt || edge.dx == st.dx || Math.abs(den) <= 2.220446049250313E-16D) {
/* 1373 */         StNode existing_node = st;
/* 1374 */         st = new StNode(edge, existing_node);
/*      */       } else {
/* 1379 */         double r = (edge.xb - st.xb) / den;
/* 1380 */         double x = st.xb + r * (st.xt - st.xb);
/* 1381 */         double y = r * dy;
/* 1384 */         it.top_node = add_intersection(it.top_node, st.edge, edge, x, y);
/* 1387 */         st.prev = add_st_edge(st.prev, it, edge, dy);
/*      */       } 
/*      */     } 
/* 1390 */     return st;
/*      */   }
/*      */   
/*      */   private static ItNode add_intersection(ItNode it_node, EdgeNode edge0, EdgeNode edge1, double x, double y) {
/* 1399 */     if (it_node == null) {
/* 1402 */       it_node = new ItNode(edge0, edge1, x, y, null);
/* 1406 */     } else if (it_node.point.y > y) {
/* 1409 */       ItNode existing_node = it_node;
/* 1410 */       it_node = new ItNode(edge0, edge1, x, y, existing_node);
/*      */     } else {
/* 1415 */       it_node.next = add_intersection(it_node.next, edge0, edge1, x, y);
/*      */     } 
/* 1418 */     return it_node;
/*      */   }
/*      */   
/*      */   private static class OperationType {
/*      */     private String m_Type;
/*      */     
/*      */     private OperationType(String type) {
/* 1427 */       this.m_Type = type;
/*      */     }
/*      */     
/* 1429 */     public static final OperationType GPC_DIFF = new OperationType("Difference");
/*      */     
/* 1430 */     public static final OperationType GPC_INT = new OperationType("Intersection");
/*      */     
/* 1431 */     public static final OperationType GPC_XOR = new OperationType("Exclusive or");
/*      */     
/* 1432 */     public static final OperationType GPC_UNION = new OperationType("Union");
/*      */     
/*      */     public String toString() {
/* 1434 */       return this.m_Type;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class VertexType {
/*      */     public static final int EMX = 1;
/*      */     
/*      */     public static final int ELI = 2;
/*      */     
/*      */     public static final int ERI = 4;
/*      */     
/*      */     public static final int RED = 5;
/*      */     
/*      */     public static final int IMM = 6;
/*      */     
/*      */     public static final int IMN = 7;
/*      */     
/*      */     public static final int EMN = 8;
/*      */     
/*      */     public static final int EMM = 9;
/*      */     
/*      */     public static final int LED = 10;
/*      */     
/*      */     public static final int ILI = 11;
/*      */     
/*      */     public static final int IRI = 13;
/*      */     
/*      */     public static final int IMX = 14;
/*      */     
/*      */     public static int getType(int tr, int tl, int br, int bl) {
/* 1461 */       return tr + (tl << 1) + (br << 2) + (bl << 3);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class HState {
/*      */     public static final int NH = 0;
/*      */     
/*      */     public static final int BH = 1;
/*      */     
/*      */     public static final int TH = 2;
/*      */     
/* 1476 */     public static final int[][] next_h_state = new int[][] { { 1, 2, 2, 1 }, { 0, 0, 0, 0, 2, 2 }, { 0, 0, 0, 0, 1, 1 } };
/*      */   }
/*      */   
/*      */   private static class BundleState {
/*      */     private String m_State;
/*      */     
/*      */     private BundleState(String state) {
/* 1491 */       this.m_State = state;
/*      */     }
/*      */     
/* 1493 */     public static final BundleState UNBUNDLED = new BundleState("UNBUNDLED");
/*      */     
/* 1494 */     public static final BundleState BUNDLE_HEAD = new BundleState("BUNDLE_HEAD");
/*      */     
/* 1495 */     public static final BundleState BUNDLE_TAIL = new BundleState("BUNDLE_TAIL");
/*      */     
/*      */     public String toString() {
/* 1497 */       return this.m_State;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class VertexNode {
/*      */     double x;
/*      */     
/*      */     double y;
/*      */     
/*      */     VertexNode next;
/*      */     
/*      */     public VertexNode(double x, double y) {
/* 1511 */       this.x = x;
/* 1512 */       this.y = y;
/* 1513 */       this.next = null;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class PolygonNode {
/*      */     int active;
/*      */     
/*      */     boolean hole;
/*      */     
/* 1524 */     Clip.VertexNode[] v = new Clip.VertexNode[2];
/*      */     
/*      */     PolygonNode next;
/*      */     
/*      */     PolygonNode proxy;
/*      */     
/*      */     public PolygonNode(PolygonNode next, double x, double y) {
/* 1531 */       Clip.VertexNode vn = new Clip.VertexNode(x, y);
/* 1532 */       this.v[0] = vn;
/* 1533 */       this.v[1] = vn;
/* 1535 */       this.next = next;
/* 1536 */       this.proxy = this;
/* 1537 */       this.active = 1;
/*      */     }
/*      */     
/*      */     public void add_right(double x, double y) {
/* 1542 */       Clip.VertexNode nv = new Clip.VertexNode(x, y);
/* 1545 */       (this.proxy.v[1]).next = nv;
/* 1548 */       this.proxy.v[1] = nv;
/*      */     }
/*      */     
/*      */     public void add_left(double x, double y) {
/* 1553 */       Clip.VertexNode nv = new Clip.VertexNode(x, y);
/* 1556 */       nv.next = this.proxy.v[0];
/* 1559 */       this.proxy.v[0] = nv;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TopPolygonNode {
/* 1566 */     Clip.PolygonNode top_node = null;
/*      */     
/*      */     public Clip.PolygonNode add_local_min(double x, double y) {
/* 1570 */       Clip.PolygonNode existing_min = this.top_node;
/* 1572 */       this.top_node = new Clip.PolygonNode(existing_min, x, y);
/* 1574 */       return this.top_node;
/*      */     }
/*      */     
/*      */     public void merge_left(Clip.PolygonNode p, Clip.PolygonNode q) {
/* 1580 */       q.proxy.hole = true;
/* 1582 */       if (p.proxy != q.proxy) {
/* 1585 */         (p.proxy.v[1]).next = q.proxy.v[0];
/* 1586 */         q.proxy.v[0] = p.proxy.v[0];
/* 1589 */         Clip.PolygonNode target = p.proxy;
/* 1590 */         for (Clip.PolygonNode node = this.top_node; node != null; node = node.next) {
/* 1592 */           if (node.proxy == target) {
/* 1594 */             node.active = 0;
/* 1595 */             node.proxy = q.proxy;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void merge_right(Clip.PolygonNode p, Clip.PolygonNode q) {
/* 1604 */       q.proxy.hole = false;
/* 1606 */       if (p.proxy != q.proxy) {
/* 1609 */         (q.proxy.v[1]).next = p.proxy.v[0];
/* 1610 */         q.proxy.v[1] = p.proxy.v[1];
/* 1613 */         Clip.PolygonNode target = p.proxy;
/* 1614 */         for (Clip.PolygonNode node = this.top_node; node != null; node = node.next) {
/* 1616 */           if (node.proxy == target) {
/* 1618 */             node.active = 0;
/* 1619 */             node.proxy = q.proxy;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public int count_contours() {
/* 1627 */       int nc = 0;
/* 1628 */       for (Clip.PolygonNode polygon = this.top_node; polygon != null; polygon = polygon.next) {
/* 1630 */         if (polygon.active != 0) {
/* 1633 */           int nv = 0;
/* 1634 */           for (Clip.VertexNode v = polygon.proxy.v[0]; v != null; v = v.next)
/* 1636 */             nv++; 
/* 1640 */           if (nv > 2) {
/* 1642 */             polygon.active = nv;
/* 1643 */             nc++;
/*      */           } else {
/* 1648 */             polygon.active = 0;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1652 */       return nc;
/*      */     }
/*      */     
/*      */     public Poly getResult(Class<? extends Poly> polyClass) {
/* 1657 */       Poly result = Clip.createNewPoly(polyClass);
/* 1658 */       int num_contours = count_contours();
/* 1659 */       if (num_contours > 0) {
/* 1661 */         int c = 0;
/* 1662 */         Clip.PolygonNode npoly_node = null;
/* 1663 */         for (Clip.PolygonNode poly_node = this.top_node; poly_node != null; poly_node = npoly_node) {
/* 1665 */           npoly_node = poly_node.next;
/* 1666 */           if (poly_node.active != 0) {
/* 1668 */             Poly poly = result;
/* 1669 */             if (num_contours > 1)
/* 1671 */               poly = Clip.createNewPoly(polyClass); 
/* 1673 */             if (poly_node.proxy.hole)
/* 1675 */               poly.setIsHole(poly_node.proxy.hole); 
/* 1681 */             for (Clip.VertexNode vtx = poly_node.proxy.v[0]; vtx != null; vtx = vtx.next)
/* 1683 */               poly.add(vtx.x, vtx.y); 
/* 1685 */             if (num_contours > 1)
/* 1687 */               result.add(poly); 
/* 1689 */             c++;
/*      */           } 
/*      */         } 
/* 1696 */         Poly orig = result;
/* 1697 */         result = Clip.createNewPoly(polyClass);
/*      */         int i;
/* 1698 */         for (i = 0; i < orig.getNumInnerPoly(); i++) {
/* 1700 */           Poly inner = orig.getInnerPoly(i);
/* 1701 */           if (!inner.isHole())
/* 1703 */             result.add(inner); 
/*      */         } 
/* 1706 */         for (i = 0; i < orig.getNumInnerPoly(); i++) {
/* 1708 */           Poly inner = orig.getInnerPoly(i);
/* 1709 */           if (inner.isHole())
/* 1711 */             result.add(inner); 
/*      */         } 
/*      */       } 
/* 1715 */       return result;
/*      */     }
/*      */     
/*      */     public void print() {
/* 1720 */       System.out.println("---- out_poly ----");
/* 1721 */       int c = 0;
/* 1722 */       Clip.PolygonNode npoly_node = null;
/* 1723 */       for (Clip.PolygonNode poly_node = this.top_node; poly_node != null; poly_node = npoly_node) {
/* 1725 */         System.out.println("contour=" + c + "  active=" + poly_node.active + "  hole=" + poly_node.proxy.hole);
/* 1726 */         npoly_node = poly_node.next;
/* 1727 */         if (poly_node.active != 0) {
/* 1729 */           int v = 0;
/* 1730 */           for (Clip.VertexNode vtx = poly_node.proxy.v[0]; vtx != null; vtx = vtx.next)
/* 1732 */             System.out.println("v=" + v + "  vtx.x=" + vtx.x + "  vtx.y=" + vtx.y); 
/* 1734 */           c++;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private TopPolygonNode() {}
/*      */   }
/*      */   
/*      */   private static class EdgeNode {
/*      */     Point2D vertex;
/*      */     
/*      */     Point2D bot;
/*      */     
/*      */     Point2D top;
/*      */     
/*      */     double xb;
/*      */     
/*      */     double xt;
/*      */     
/*      */     double dx;
/*      */     
/*      */     int type;
/*      */     
/*      */     int[][] bundle;
/*      */     
/*      */     int[] bside;
/*      */     
/*      */     Clip.BundleState[] bstate;
/*      */     
/*      */     Clip.PolygonNode[] outp;
/*      */     
/*      */     EdgeNode prev;
/*      */     
/*      */     EdgeNode next;
/*      */     
/*      */     EdgeNode succ;
/*      */     
/*      */     EdgeNode next_bound;
/*      */     
/*      */     private EdgeNode() {
/* 1742 */       this.vertex = new Point2D();
/* 1743 */       this.bot = new Point2D();
/* 1744 */       this.top = new Point2D();
/* 1749 */       this.bundle = new int[2][2];
/* 1750 */       this.bside = new int[2];
/* 1751 */       this.bstate = new Clip.BundleState[2];
/* 1752 */       this.outp = new Clip.PolygonNode[2];
/*      */     }
/*      */   }
/*      */   
/*      */   private static class AetTree {
/*      */     Clip.EdgeNode top_node;
/*      */     
/*      */     private AetTree() {}
/*      */     
/*      */     public void print() {
/* 1766 */       System.out.println("");
/* 1767 */       System.out.println("aet");
/* 1768 */       for (Clip.EdgeNode edge = this.top_node; edge != null; edge = edge.next)
/* 1770 */         System.out.println("edge.vertex.x=" + edge.vertex.x + "  edge.vertex.y=" + edge.vertex.y); 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class EdgeTable {
/*      */     private List<Clip.EdgeNode> m_List;
/*      */     
/*      */     private EdgeTable() {
/* 1777 */       this.m_List = new ArrayList<Clip.EdgeNode>();
/*      */     }
/*      */     
/*      */     public void addNode(double x, double y) {
/* 1781 */       Clip.EdgeNode node = new Clip.EdgeNode(null);
/* 1782 */       node.vertex.x = x;
/* 1783 */       node.vertex.y = y;
/* 1784 */       this.m_List.add(node);
/*      */     }
/*      */     
/*      */     public Clip.EdgeNode getNode(int index) {
/* 1789 */       return this.m_List.get(index);
/*      */     }
/*      */     
/*      */     public boolean FWD_MIN(int i) {
/* 1794 */       Clip.EdgeNode prev = this.m_List.get(Clip.PREV_INDEX(i, this.m_List.size()));
/* 1795 */       Clip.EdgeNode next = this.m_List.get(Clip.NEXT_INDEX(i, this.m_List.size()));
/* 1796 */       Clip.EdgeNode ith = this.m_List.get(i);
/* 1797 */       if (prev.vertex.getY() >= ith.vertex.getY() && 
/* 1798 */         next.vertex.getY() > ith.vertex.getY())
/* 1798 */         return true; 
/*      */       return false;
/*      */     }
/*      */     
/*      */     public boolean NOT_FMAX(int i) {
/* 1803 */       Clip.EdgeNode next = this.m_List.get(Clip.NEXT_INDEX(i, this.m_List.size()));
/* 1804 */       Clip.EdgeNode ith = this.m_List.get(i);
/* 1805 */       return (next.vertex.getY() > ith.vertex.getY());
/*      */     }
/*      */     
/*      */     public boolean REV_MIN(int i) {
/* 1810 */       Clip.EdgeNode prev = this.m_List.get(Clip.PREV_INDEX(i, this.m_List.size()));
/* 1811 */       Clip.EdgeNode next = this.m_List.get(Clip.NEXT_INDEX(i, this.m_List.size()));
/* 1812 */       Clip.EdgeNode ith = this.m_List.get(i);
/* 1813 */       if (prev.vertex.getY() > ith.vertex.getY() && 
/* 1814 */         next.vertex.getY() >= ith.vertex.getY())
/* 1814 */         return true; 
/*      */       return false;
/*      */     }
/*      */     
/*      */     public boolean NOT_RMAX(int i) {
/* 1819 */       Clip.EdgeNode prev = this.m_List.get(Clip.PREV_INDEX(i, this.m_List.size()));
/* 1820 */       Clip.EdgeNode ith = this.m_List.get(i);
/* 1821 */       return (prev.vertex.getY() > ith.vertex.getY());
/*      */     }
/*      */   }
/*      */   
/*      */   private static class LmtNode {
/*      */     double y;
/*      */     
/*      */     Clip.EdgeNode first_bound;
/*      */     
/*      */     LmtNode next;
/*      */     
/*      */     public LmtNode(double yvalue) {
/* 1836 */       this.y = yvalue;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class LmtTable {
/*      */     Clip.LmtNode top_node;
/*      */     
/*      */     private LmtTable() {}
/*      */     
/*      */     public void print() {
/* 1846 */       int n = 0;
/* 1847 */       Clip.LmtNode lmt = this.top_node;
/* 1848 */       while (lmt != null) {
/* 1850 */         System.out.println("lmt(" + n + ")");
/* 1851 */         for (Clip.EdgeNode edge = lmt.first_bound; edge != null; edge = edge.next_bound)
/* 1853 */           System.out.println("edge.vertex.x=" + edge.vertex.x + "  edge.vertex.y=" + edge.vertex.y); 
/* 1855 */         n++;
/* 1856 */         lmt = lmt.next;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ScanBeamTree {
/*      */     double y;
/*      */     
/*      */     ScanBeamTree less;
/*      */     
/*      */     ScanBeamTree more;
/*      */     
/*      */     public ScanBeamTree(double yvalue) {
/* 1872 */       this.y = yvalue;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ScanBeamTreeEntries {
/*      */     int sbt_entries;
/*      */     
/*      */     Clip.ScanBeamTree sb_tree;
/*      */     
/*      */     private ScanBeamTreeEntries() {}
/*      */     
/*      */     public double[] build_sbt() {
/* 1886 */       double[] sbt = new double[this.sbt_entries];
/* 1888 */       int entries = 0;
/* 1889 */       entries = inner_build_sbt(entries, sbt, this.sb_tree);
/* 1890 */       if (entries != this.sbt_entries)
/* 1892 */         throw new IllegalStateException("Something went wrong buildign sbt from tree."); 
/* 1894 */       return sbt;
/*      */     }
/*      */     
/*      */     private int inner_build_sbt(int entries, double[] sbt, Clip.ScanBeamTree sbt_node) {
/* 1899 */       if (sbt_node.less != null)
/* 1901 */         entries = inner_build_sbt(entries, sbt, sbt_node.less); 
/* 1903 */       sbt[entries] = sbt_node.y;
/* 1904 */       entries++;
/* 1905 */       if (sbt_node.more != null)
/* 1907 */         entries = inner_build_sbt(entries, sbt, sbt_node.more); 
/* 1909 */       return entries;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ItNode {
/* 1918 */     Clip.EdgeNode[] ie = new Clip.EdgeNode[2];
/*      */     
/* 1919 */     Point2D point = new Point2D();
/*      */     
/*      */     ItNode next;
/*      */     
/*      */     public ItNode(Clip.EdgeNode edge0, Clip.EdgeNode edge1, double x, double y, ItNode next) {
/* 1924 */       this.ie[0] = edge0;
/* 1925 */       this.ie[1] = edge1;
/* 1926 */       this.point.x = x;
/* 1927 */       this.point.y = y;
/* 1928 */       this.next = next;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class ItNodeTable {
/*      */     Clip.ItNode top_node;
/*      */     
/*      */     private ItNodeTable() {}
/*      */     
/*      */     public void build_intersection_table(Clip.AetTree aet, double dy) {
/* 1938 */       Clip.StNode st = null;
/* 1941 */       for (Clip.EdgeNode edge = aet.top_node; edge != null; edge = edge.next) {
/* 1943 */         if (edge.bstate[0] == Clip.BundleState.BUNDLE_HEAD || 
/* 1944 */           edge.bundle[0][0] != 0 || 
/* 1945 */           edge.bundle[0][1] != 0)
/* 1947 */           st = Clip.add_st_edge(st, this, edge, dy); 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static class StNode {
/*      */     Clip.EdgeNode edge;
/*      */     
/*      */     double xb;
/*      */     
/*      */     double xt;
/*      */     
/*      */     double dx;
/*      */     
/*      */     StNode prev;
/*      */     
/*      */     public StNode(Clip.EdgeNode edge, StNode prev) {
/* 1966 */       this.edge = edge;
/* 1967 */       this.xb = edge.xb;
/* 1968 */       this.xt = edge.xt;
/* 1969 */       this.dx = edge.dx;
/* 1970 */       this.prev = prev;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void print_sbt(double[] sbt) {
/* 1979 */     System.out.println("");
/* 1980 */     System.out.println("sbt.length=" + sbt.length);
/* 1981 */     for (int i = 0; i < sbt.length; i++)
/* 1983 */       System.out.println("sbt[" + i + "]=" + sbt[i]); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\seis\\util\geom\Clip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */