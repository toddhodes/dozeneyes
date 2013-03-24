package org.dozeneyes.aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum Pattern {
   SOLID, STRIPE, DOT;

   static Random random = new Random();
   static List<Pattern> patternSet = new ArrayList<Pattern>();

   public static Pattern next() {
      if (patternSet.isEmpty()) return reset();
      return patternSet.remove(0);
   }

   public static Pattern reset() {
      patternSet.clear();
      for (int i=0; i<3; i++) {
         patternSet.add(Pattern.values()[i]);
      }
      Collections.shuffle(patternSet, random);
      return patternSet.remove(0);
   }

}


