package utils;

import java.util.Scanner;

public class Validation {

    public static int option(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine(); // Limpia el salto de línea después del número
                return input;
            } else {
                System.out.println("Error: Only numbers allowed.");
                sc.next(); // <--- ESTA ES LA CLAVE: consume la "basura" (la letra) para que el búfer quede limpio
            }
        }
    }
}
