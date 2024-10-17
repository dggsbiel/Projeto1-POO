import java.util.Scanner;

public class Clinica {
    private static Paciente[] pacientes = new Paciente[100];
    private static Medico[] medicos = new Medico[5];
    private static Consulta[] consultas = new Consulta[100];
    private static int contadorPacientes = 0;
    private static int contadorConsultas = 0;

    private static boolean validarCpf(String cpf) {
        return cpf.matches("\\d{11}");
    }

    private static boolean validarData(String data) {
        return data.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private static boolean validarHora(String hora) {
        return hora.matches("\\d{2}:\\d{2}");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        medicos[0] = new Medico("Dr. João", "Cardiologia");
        medicos[1] = new Medico("Dra. Maria", "Pediatria");
        medicos[2] = new Medico("Dr. Carlos", "Dermatologia");
        medicos[3] = new Medico("Dra. Ana", "Ginecologia");
        medicos[4] = new Medico("Dr. Paulo", "Oftalmologia");

        System.out.print("Digite a senha para acessar o sistema: ");
        String senha = scanner.nextLine();

        if (!senha.equals("123")) {
            System.out.println("Senha incorreta. Acesso negado.");
        }

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=================================================");
            System.out.println("         SISTEMA DE GERENCIAMENTO CLÍNICA     ");
            System.out.println("=================================================");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Agendar Consulta");
            System.out.println("3. Buscar Paciente");
            System.out.println("4. Gerar Relatório de Consultas");
            System.out.println("5. Excluir Consulta");
            System.out.println("6. Sair");
            System.out.println("=================================================");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (contadorPacientes < pacientes.length) {
                        System.out.print("\nNome do Paciente: ");
                        String nome = scanner.nextLine();

                        String cpf;
                        do {
                            System.out.print("CPF do Paciente (somente números): ");
                            cpf = scanner.nextLine();
                            if (!validarCpf(cpf)) {
                                System.out.println("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
                            }
                        } while (!validarCpf(cpf));

                        pacientes[contadorPacientes++] = new Paciente(nome, cpf);
                        System.out.println("Paciente cadastrado com sucesso!");
                    } else {
                        System.out.println("Limite de pacientes atingido.");
                    }
                    break;

                case 2:
                    System.out.print("\nDigite o CPF do Paciente: ");
                    String cpfPaciente = scanner.nextLine();
                    Paciente pacienteEncontrado = null;
                    for (int i = 0; i < contadorPacientes; i++) {
                        if (pacientes[i].getCpf().equals(cpfPaciente)) {
                            pacienteEncontrado = pacientes[i];
                            break;
                        }
                    }

                    if (pacienteEncontrado == null) {
                        System.out.println("Paciente não encontrado.");
                    } else {
                        System.out.println("\nMédicos disponíveis:\n");
                        for (int i = 0; i < medicos.length; i++) {
                            Medico medico = medicos[i];
                            if (medico != null) {
                                System.out.println((i + 1) + ". " + medico.getNome() + " - " + medico.getEspecialidade());
                            }
                        }

                        System.out.print("\nEscolha o número do Médico: ");
                        int numeroMedico = scanner.nextInt();
                        scanner.nextLine();

                        if (numeroMedico < 1 || numeroMedico > medicos.length) {
                            System.out.println("Médico não encontrado.");
                        } else {
                            Medico medicoSelecionado = medicos[numeroMedico - 1];

                            String data;
                            do {
                                System.out.print("Digite a data da consulta (xx/xx/xxxx): ");
                                data = scanner.nextLine();
                                if (!validarData(data)) {
                                    System.out.println("Data inválida! Use o formato xx/xx/xxxx.");
                                }
                            } while (!validarData(data));

                            String hora;
                            do {
                                System.out.print("Digite a hora da consulta (xx:xx): ");
                                hora = scanner.nextLine();
                                if (!validarHora(hora)) {
                                    System.out.println("Hora inválida! Use o formato xx:xx.");
                                }
                            } while (!validarHora(hora));

                            if (medicoSelecionado.isDisponivel(data, hora)) {
                                Consulta novaConsulta = new Consulta(pacienteEncontrado, medicoSelecionado, data, hora);
                                medicoSelecionado.agendarConsulta(novaConsulta);
                                consultas[contadorConsultas++] = novaConsulta;
                                System.out.println("\nConsulta agendada com sucesso!");
                            } else {
                                System.out.println("\nMédico indisponível para essa data e hora.");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.print("\nDigite o CPF do Paciente: ");
                    String cpfBuscar = scanner.nextLine();
                    boolean pacienteAchado = false;

                    for (Paciente paciente : pacientes) {
                        if (paciente != null && paciente.getCpf().equals(cpfBuscar)) {
                            System.out.println("Paciente encontrado: " + paciente.getNome());
                            pacienteAchado = true;
                            break;
                        }
                    }

                    if (!pacienteAchado) {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\nRelatório de Consultas Agendadas:");
                    for (int i = 0; i < contadorConsultas; i++) {
                        if (consultas[i] != null) {
                            consultas[i].exibirConsulta(i + 1);
                        }
                    }
                    break;

                case 5:
                    System.out.print("\nDigite o CPF do Paciente: ");
                    String cpfExcluir = scanner.nextLine();
                    Paciente pacienteParaExcluir = null;

                    for (int i = 0; i < contadorPacientes; i++) {
                        if (pacientes[i].getCpf().equals(cpfExcluir)) {
                            pacienteParaExcluir = pacientes[i];
                            break;
                        }
                    }

                    if (pacienteParaExcluir == null) {
                        System.out.println("Paciente não encontrado.");
                    } else {
                        System.out.println("\nConsultas do Paciente:");
                        for (int i = 0; i < contadorConsultas; i++) {
                            if (consultas[i] != null && consultas[i].getPaciente().equals(pacienteParaExcluir)) {
                                consultas[i].exibirConsulta(i + 1);
                            }
                        }

                        System.out.print("\nEscolha o número da consulta a ser excluída: ");
                        int numeroConsultaExcluir = scanner.nextInt() - 1;

                        if (numeroConsultaExcluir < 0 || numeroConsultaExcluir >= contadorConsultas) {
                            System.out.println("Número de consulta inválido.");
                        } else {
                            Consulta consultaExcluir = consultas[numeroConsultaExcluir];
                            if (consultaExcluir != null && consultaExcluir.getPaciente().equals(pacienteParaExcluir)) {
                                consultaExcluir.getMedico().removerConsulta(consultaExcluir);
                                consultas[numeroConsultaExcluir] = null;
                                System.out.println("Consulta excluída com sucesso.");
                            } else {
                                System.out.println("Consulta não encontrada.");
                            }
                        }
                    }
                    break;

                case 6:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        System.out.println("Sistema encerrado.");
        scanner.close();
    }
}