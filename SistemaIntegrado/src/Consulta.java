class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String hora;

    public Consulta(Paciente paciente, Medico medico, String data, String hora) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public void exibirConsulta(int numero) {
        System.out.println("Consulta " + numero + ":");
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Médico: " + medico.getNome());
        System.out.println("Data: " + data);
        System.out.println("Hora: " + hora);
        System.out.println();
    }
}