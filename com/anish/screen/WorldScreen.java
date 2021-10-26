package com.anish.screen;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Arrow;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen{
    private ArrayList<Arrow> ar;
    private World world;
    private Calabash warrior;
    public WorldScreen() throws IOException{
        ar=new ArrayList<>();
        world = new World();
        warrior=new Calabash(Color.green, 1, world);
        world.put(warrior, 0, 0);
        
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }

        if(world.isDest(warrior.getX(), warrior.getY())){
            terminal.clear();
            terminal.write("You've made it through the maze!", 9, 24,Color.YELLOW);
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int xx=warrior.getX();
        int yy=warrior.getY();
        for(var i:ar){
            i.shrink();
            if(i.getColor()==Color.WHITE){
                //ar.remove(i);
                world.put(world.getOriginThing(i.getX(), i.getY()),i.getX(),i.getY());
            }
                
        }
        switch(key.getKeyCode()){
            case 37:{
                if(world.legalNode(xx-1, yy)){
                    warrior.moveTo(xx-1,yy);
                    Arrow a=new Arrow(world,(char) 27);
                    world.put(a, xx, yy);
                    ar.add(a);
                    //world.put(world.getOriginThing(xx, yy), xx, yy);
                }
                break;
            }
            case 38:{
                if(world.legalNode(xx, yy-1)){
                    warrior.moveTo(xx,yy-1);
                    Arrow a=new Arrow(world,(char) 24);
                    world.put(a, xx, yy);ar.add(a);
                }
                break;
            }
            case 39:{
                if(world.legalNode(xx+1, yy)){
                    warrior.moveTo(xx+1,yy);
                    Arrow a=new Arrow(world,(char) 26);
                    world.put(a, xx, yy);ar.add(a);
                }
                break;
            }
            case 40:{
                if(world.legalNode(xx, yy+1)){
                    warrior.moveTo(xx,yy+1);
                    Arrow a=new Arrow(world,(char) 25);
                    world.put(a, xx, yy);ar.add(a);
                }
                break;
            }
        }

        return this;
    }

}
