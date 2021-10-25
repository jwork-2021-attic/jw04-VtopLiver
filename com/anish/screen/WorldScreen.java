package com.anish.screen;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen{

    private World world;
    private Calabash[][] military;
    String[] sortSteps;
    public final int SZ=16;
    public WorldScreen() throws IOException{
        world = new World();
        
        military=new Calabash[SZ][SZ];
        int[] tmp=new int[768];
        File fl=new File("C:/Users/lenovo/Desktop/Java/jw02-VtopLiver/S191220144/source.txt");
        int cnt=0;
        try(Scanner sc=new Scanner(fl)){
        while (sc.hasNext()) {
            int value=sc.nextInt();
            tmp[cnt++]=value;
            //sc.close();
        }
        }
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<SZ*SZ;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        cnt=0;
        
        for(int i=0;i<SZ;i++){
            for(int j=0;j<SZ;j++){
                int tmmp=list.get(cnt);
                cnt++;
                military[i][j]=new Calabash(new Color(tmp[3*tmmp],tmp[3*tmmp+1],tmp[3*tmmp+2]), tmmp, world);
            }
        }
        int row=2;
        int col=2;
        for(int i=0;i<SZ;i++){
            row=2;
            for(int j=0;j<SZ;j++){
                world.put(military[i][j],row,col);
                row+=3;
            }
            col+=3;
        }
        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(military);
        b.sort();
        //System.out.println(b.getPlan());
        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[][] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[][] bros, int rank) {
        for (int i=0;i<bros.length;i++) {
            for(int j=0;j<bros[0].length;j++){
                if (bros[i][j].getRank() == rank) {
                    return bros[i][j];
                }
            }
            
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(military, sortSteps[i]);
            i++;
        }

        return this;
    }

}
