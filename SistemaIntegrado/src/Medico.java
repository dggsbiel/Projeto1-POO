class Medico {
    private String nome;
    private String especialidade;
    private Consulta[] consultas;
    private int contadorConsultas;

    public Medico(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.consultas = new Consulta[100];
        this.contadorConsultas = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public Consulta[] getConsultas() {
        return consultas;
    }

    public void agendarConsulta(Consulta consulta) {
        if (contadorConsultas < consultas.length) {
            consultas[contadorConsultas++] = consulta;
        } else {
            System.out.println("Este médico não pode agendar mais consultas.");
        }
    }

    public boolean isDisponivel(String data, String hora) {
        for (int i = 0; i < contadorConsultas; i++) {
            Consulta consulta = consultas[i];
            if (consulta != null && consulta.getData().equals(data) && consulta.getHora().equals(hora)) {
                return false; // Indisponível
            }
        }
        return true; // Disponível
    }

    public void removerConsulta(Consulta consulta) {
        for (int i = 0; i < contadorConsultas; i++) {
            if (consultas[i] != null && consultas[i].equals(consulta)) {
                consultas[i] = null;
                break;
            }
        }
    }
}