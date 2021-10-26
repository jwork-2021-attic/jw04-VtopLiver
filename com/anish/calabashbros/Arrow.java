package com.anish.calabashbros;

import java.awt.Color;

public class Arrow extends Thing {
    public Arrow(World world,char srt) {
        super(Color.green, (char) srt, world);
    }
    public void shrink(){
        if(this.color.getRed()==0)
            this.color=new Color(63,255,63);
        else if(this.color.getRed()==63)
            this.color=new Color(127,255,127);
        else if(this.color.getRed()==127)
            this.color=new Color(191,255,191);
        else
            this.color=Color.white;
    }
}