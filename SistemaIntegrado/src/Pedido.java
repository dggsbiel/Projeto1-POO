class Pedido {
    private ItemDoPedido[] itens;
    private int quantidadeItens;

    public Pedido(int tamanhoMaximo) {
        itens = new ItemDoPedido[tamanhoMaximo];
        quantidadeItens = 0;
    }

    public void adicionarItem(ItemDoPedido item) {
        if (quantidadeItens < itens.length) {
            itens[quantidadeItens] = item;
            quantidadeItens++;
        } else {
            System.out.println("Não é possível adicionar mais itens ao pedido.");
        }
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < quantidadeItens) {
            for (int i = indice; i < quantidadeItens - 1; i++) {
                itens[i] = itens[i + 1];
            }
            itens[quantidadeItens - 1] = null;
            quantidadeItens--;
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < quantidadeItens; i++) {
            total += itens[i].getPreco();
        }
        return total;
    }

    public void listarItens() {
        System.out.println("\nItens no pedido:");
        for (int i = 0; i < quantidadeItens; i++) {
            System.out.println((i + 1) + ". " + itens[i].getNome() + " - R$ " + itens[i].getPreco());
        }
    }
}