package com.sistemadeaviso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SistemaAvisoMejorado {
    
    private static int notificacionesRestantes;
    private static Timer t = new Timer();

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Solicitar los parámetros al usuario
        System.out.println("Ingrese el intervalo entre avisos en minutos (X): ");
        int intervalMinutes = scanner.nextInt();

        System.out.println("Ingrese la duración del descanso en minutos (Y): ");
        int breakMinutes = scanner.nextInt();

        System.out.println("Ingrese el número máximo de avisos (Z): ");
        notificacionesRestantes = scanner.nextInt();

        System.out.println("El servicio ha comenzado.");
        startService(intervalMinutes, breakMinutes);

    }
    
    private static void startService(int intervaloMinutos, int descansoMinutos) {
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (notificacionesRestantes > 0) {
                    // Obtener la hora actual en formato HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String horaActual = sdf.format(new Date());

                    // Mostrar mensaje de aviso con la hora
                    System.out.println("[" + horaActual + "] ¡Hora de un descanso de " + descansoMinutos + " minutos!");

                    // Simular duración del descanso
                    try {
                        Thread.sleep(descansoMinutos * 60 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    notificacionesRestantes--;
                    if (notificacionesRestantes == 0) {
                        // Mostrar hora final con el mensaje
                        String horaFinal = sdf.format(new Date());
                        System.out.println("[" + horaFinal + "] ¡Todos los avisos completados! Servicio finalizado.");
                        t.cancel();
                    }
                }
            }
        }, 0, intervaloMinutos * 60 * 1000L);
    }
}