import java.util.Scanner;

public class GerenciarEvento {
    private static Evento[] eventos = new Evento[5];
    private static int contadorEventos = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=====================================================");
            System.out.println("         SISTEMA DE GERENCIAMENTO DE EVENTOS     ");
            System.out.println("=====================================================");
            System.out.println("1. Cadastrar Evento");
            System.out.println("2. Registrar Participante");
            System.out.println("3. Excluir Participante");
            System.out.println("4. Listar Participantes");
            System.out.println("5. Excluir Evento");
            System.out.println("6. Listar Todos os Eventos");
            System.out.println("7. Sair");
            System.out.println("=====================================================");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (contadorEventos < eventos.length) {
                        System.out.print("Nome do Evento: ");
                        String nomeEvento = scanner.nextLine();
                        System.out.print("Nome do Local: ");
                        String nomeLocal = scanner.nextLine();
                        System.out.print("Endereço do Local: ");
                        String enderecoLocal = scanner.nextLine();
                        System.out.print("Capacidade Máxima: ");
                        int capacidade = scanner.nextInt();
                        scanner.nextLine();

                        Local local = new Local(nomeLocal, enderecoLocal);
                        eventos[contadorEventos++] = new Evento(nomeEvento, local, capacidade);
                        System.out.println("\nEvento cadastrado com sucesso!");
                    } else {
                        System.out.println("Limite de eventos atingido.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o nome do evento para registrar o participante: ");
                    String nomeEventoBuscar = scanner.nextLine();
                    Evento eventoSelecionado = buscarEvento(nomeEventoBuscar);

                    if (eventoSelecionado == null) {
                        System.out.println("Evento não encontrado.");
                    } else if (!eventoSelecionado.verificarLotacao()) {
                        System.out.println("Evento lotado.");
                    } else {
                        System.out.print("Nome do Participante: ");
                        String nomeParticipante = scanner.nextLine();
                        System.out.print("Email do Participante: ");
                        String emailParticipante = scanner.nextLine();
                        
                        String cpfParticipante;
                        do {
                            System.out.print("CPF do Participante (só números): ");
                            cpfParticipante = scanner.nextLine();
                        } while (!cpfValido(cpfParticipante));

                        Participante participante = new Participante(nomeParticipante, emailParticipante, cpfParticipante);
                        eventoSelecionado.registrarParticipante(participante);
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do evento para excluir o participante: ");
                    String nomeEventoExcluirParticipante = scanner.nextLine();
                    Evento eventoParaExcluirParticipante = buscarEvento(nomeEventoExcluirParticipante);

                    if (eventoParaExcluirParticipante == null) {
                        System.out.println("Evento não encontrado.");
                    } else {
                        System.out.print("Digite o CPF do participante a ser excluído: ");
                        String cpfExcluir = scanner.nextLine();
                        eventoParaExcluirParticipante.excluirParticipante(cpfExcluir);
                    }
                    break;

                case 4:
                    System.out.print("\nDigite o nome do evento para listar os participantes: ");
                    String nomeEventoListar = scanner.nextLine();
                    Evento eventoParaListar = buscarEvento(nomeEventoListar);

                    if (eventoParaListar == null) {
                        System.out.println("\nEvento não encontrado.");
                    } else {
                        eventoParaListar.listarParticipantes();
                    }
                    break;

                case 5:
                    System.out.print("Digite o nome do evento a ser excluído: ");
                    String nomeEventoExcluir = scanner.nextLine();
                    excluirEvento(nomeEventoExcluir);
                    break;

                case 6:
                    listarTodosOsEventos();
                    break;

                case 7:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static Evento buscarEvento(String nomeEvento) {
        for (Evento evento : eventos) {
            if (evento != null && evento.getNome().equalsIgnoreCase(nomeEvento)) {
                return evento;
            }
        }
        return null;
    }

    private static void excluirEvento(String nomeEvento) {
        for (int i = 0; i < contadorEventos; i++) {
            if (eventos[i] != null && eventos[i].getNome().equalsIgnoreCase(nomeEvento)) {
                for (int j = i; j < contadorEventos - 1; j++) {
                    eventos[j] = eventos[j + 1];
                }
                eventos[--contadorEventos] = null;
                System.out.println("\nEvento excluído com sucesso.");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    private static boolean cpfValido(String cpf) {
        if (cpf.length() == 11 && cpf.matches("\\d+")) {
            return true;
        } else {
            System.out.println("CPF inválido. O CPF deve conter exatamente 11 números.");
            return false;
        }
    }

    private static void listarTodosOsEventos() {
        System.out.println("\nEventos cadastrados:\n");
        for (int i = 0; i < contadorEventos; i++) {
            System.out.println("Evento: " + eventos[i].getNome() + " - Local: " + eventos[i].getLocal().getNome() + " - Endereço: " + eventos[i].getLocal().getEndereco());
        }
    }
}