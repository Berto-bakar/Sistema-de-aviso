package com.sistemadeaviso;

import java.util.Timer;
import java.util.TimerTask;

public class SistemaAviso extends TimerTask{

    public static void main(String[] args) {
        
        Timer temporizador = new Timer();
        temporizador.schedule(new SistemaAviso(), 1000,2000);

    }

    @Override
    public void run() {
        System.out.println("Aviso");
    }

}
