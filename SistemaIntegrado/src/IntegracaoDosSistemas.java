import java.util.Scanner;

public class IntegracaoDosSistemas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n============================================================");
            System.out.println("         SISTEMA INTEGRADO DE GERENCIAMENTOS");
            System.out.println("============================================================");
            System.out.println("1. Sistema de Gerenciamento de Clínica");
            System.out.println("2. Sistema de Gerenciamento de Eventos");
            System.out.println("3. Sistema de Gerenciamento de Restaurante");
            System.out.println("4. Sair");
            System.out.println("===========================================");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Clinica.main(new String[]{}); 
                    break;
                case 2:
                    GerenciarEvento.main(new String[]{}); 
                    break;
                case 3:
                    Restaurante.main(new String[]{});
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Saindo do sistema integrado...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}