public class Evento {
    private String nome;
    private Local local;
    private Participante[] participantes;
    private int totalParticipantes;
    private int capacidadeMaxima;

    public Evento(String nome, Local local, int capacidadeMaxima) {
        this.nome = nome;
        this.local = local;
        this.capacidadeMaxima = capacidadeMaxima;
        this.participantes = new Participante[capacidadeMaxima];
        this.totalParticipantes = 0;
    }

    public String getNome() {
        return nome;
    }

    public Local getLocal() {
        return local;
    }

    public boolean verificarLotacao() {
        return totalParticipantes < capacidadeMaxima;
    }

    public void registrarParticipante(Participante participante) {
        if (verificarLotacao()) {
            participantes[totalParticipantes++] = participante;
            System.out.println("\nParticipante " + participante.getNome() + " registrado com sucesso!");
        } else {
            System.out.println("\nEvento lotado! Não é possível registrar mais participantes.");
        }
    }

    public void excluirParticipante(String cpf) {
        boolean participanteEncontrado = false;
        for (int i = 0; i < totalParticipantes; i++) {
            if (participantes[i].getCpf().equals(cpf)) {
                participanteEncontrado = true;
                for (int j = i; j < totalParticipantes - 1; j++) {
                    participantes[j] = participantes[j + 1];
                }
                participantes[--totalParticipantes] = null;
                System.out.println("\nParticipante removido com sucesso.");
                break;
            }
        }
        if (!participanteEncontrado) {
            System.out.println("\nParticipante não encontrado.");
        }
    }

    public void listarParticipantes() {
        System.out.println("\nParticipantes do evento " + nome + ":\n");
        for (int i = 0; i < totalParticipantes; i++) {
            System.out.println(participantes[i].getNome() + " - " + participantes[i].getEmail() + " - CPF: " + participantes[i].getCpf());
        }
    }
}