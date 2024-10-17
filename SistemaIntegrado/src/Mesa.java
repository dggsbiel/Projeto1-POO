class Mesa {
    private int numeroMesa;
    private Pedido pedidoAtual;
    private boolean ocupada;
    private String historicoPedidos;

    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.ocupada = false;
        this.pedidoAtual = null;
        this.historicoPedidos = "";
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void registrarPedido(Pedido pedido) {
        this.pedidoAtual = pedido;
        this.ocupada = true;
    }

    public void fecharConta(String metodoPagamento) {
        if (pedidoAtual != null) {
            System.out.println("\n----- Fechando a Conta -----");
            pedidoAtual.listarItens();
            double total = pedidoAtual.calcularTotal();
            System.out.println("\nTotal a pagar: R$ " + total);
            System.out.println("Método de pagamento: " + metodoPagamento);
            System.out.println("----------------------------\n");
            
            historicoPedidos += "Pedido total: R$ " + total + " - Método de pagamento: " + metodoPagamento + "\n";
            
            pedidoAtual = null;
            ocupada = false;
        } else {
            System.out.println("\nNão há nenhum pedido registrado.");
        }
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public String getHistoricoPedidos() {
        return historicoPedidos.isEmpty() ? "Nenhum pedido registrado ainda." : historicoPedidos;
    }

    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }
}