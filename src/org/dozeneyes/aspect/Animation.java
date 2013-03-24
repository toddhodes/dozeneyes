package org.dozeneyes.aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum Animation {
   STEADY, GLITCH, PARTICLE;

   static Random random = new Random();
   static List<Animation> animationSet = new ArrayList<Animation>();

   public static Animation next() {
      if (animationSet.isEmpty()) return reset();
      return animationSet.remove(0);
   }

   public static Animation reset() {
      animationSet.clear();
      for (int i=0; i<3; i++) {
         animationSet.add(Animation.values()[i]);
      }
      Collections.shuffle(animationSet, random);
      return animationSet.remove(0);
   }

}


