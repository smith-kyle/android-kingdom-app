package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by kylesmith on 3/25/15.
 */
public class Animations {
   public Animation getInAnimation() {
        AlphaAnimation InAnimation;
        InAnimation = new AlphaAnimation(0f, 1f);
        InAnimation.setDuration(200);
        return InAnimation;
   }

   public Animation getOutAnimation(){
       AlphaAnimation OutAnimation = new AlphaAnimation(1f, 0f);
       OutAnimation.setDuration(200);
       return OutAnimation;
   }
}


