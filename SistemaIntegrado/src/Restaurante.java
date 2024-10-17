import java.util.Scanner;

public class Restaurante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ItemDoPedido[] cardapio = new ItemDoPedido[6];
        cardapio[0] = new ItemDoPedido("Pizza de Frango", 31.0);
        cardapio[1] = new ItemDoPedido("Pizza de Calabresa", 28.0);
        cardapio[2] = new ItemDoPedido("Pizza Mista", 25.0);
        cardapio[3] = new ItemDoPedido("Refrigerante", 5.0);
        cardapio[4] = new ItemDoPedido("Suco", 7.0);
        cardapio[5] = new ItemDoPedido("Água", 3.0);
        
        Mesa[] mesas = new Mesa[5];
        for (int i = 0; i < mesas.length; i++) {
            mesas[i] = new Mesa(i + 1);
        }

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=====================================================");
            System.out.println("   Sistema de Gerenciamento de Pizzaria   ");
            System.out.println("=====================================================");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Remover Item do Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("4. Verificar Mesas Disponíveis");
            System.out.println("5. Ver Histórico de Pedidos por Mesa");
            System.out.println("6. Sair");
            System.out.println("=====================================================");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o número da mesa (1 a 5): ");
                    int numeroMesa = scanner.nextInt();
                    if (numeroMesa < 1 || numeroMesa > 5 || mesas[numeroMesa - 1].isOcupada()) {
                        System.out.println("Mesa inválida ou já ocupada.");
                    } else {
                        Pedido pedido = new Pedido(10);
                        boolean adicionarMais = true;
                        while (adicionarMais) {
                            System.out.println("\n----- Cardápio -----");
                            for (int i = 0; i < cardapio.length; i++) {
                                System.out.println((i + 1) + ". " + cardapio[i].getNome() + " - R$ " + cardapio[i].getPreco());
                            }
                            System.out.print("\nDigite o número do item: ");
                            int numeroItem = scanner.nextInt();
                            if (numeroItem < 1 || numeroItem > cardapio.length) {
                                System.out.println("Item inválido.");
                            } else {
                                pedido.adicionarItem(cardapio[numeroItem - 1]);
                                System.out.print("\nAdicionar outro item? (1-Sim, 2-Não): ");
                                adicionarMais = scanner.nextInt() == 1;
                            }
                        }
                        mesas[numeroMesa - 1].registrarPedido(pedido);
                        System.out.println("\nPedido registrado com sucesso!");
                    }
                    break;

                case 2:
                    System.out.print("\nDigite o número da mesa para remover um item do pedido (1 a 5): ");
                    int mesaRemover = scanner.nextInt();
                    if (mesaRemover < 1 || mesaRemover > 5 || !mesas[mesaRemover - 1].isOcupada()) {
                        System.out.println("Mesa inválida ou não há pedido registrado.");
                    } else {
                        Pedido pedidoAtual = mesas[mesaRemover - 1].getPedidoAtual();
                        pedidoAtual.listarItens();
                        System.out.print("Digite o número do item a ser removido: ");
                        int indiceRemover = scanner.nextInt() - 1;
                        pedidoAtual.removerItem(indiceRemover);
                        System.out.println("Item removido com sucesso!");
                    }
                    break;

                case 3:
                    System.out.print("\nDigite o número da mesa para fechar a conta (1 a 5): ");
                    int mesaFechar = scanner.nextInt();
                    if (mesaFechar < 1 || mesaFechar > 5) {
                        System.out.println("Mesa inválida.");
                    } else {
                        System.out.println("Escolha o método de pagamento (1-Dinheiro  2-Cartão de Crédito  3-Cartão de Débito): ");
                        int metodo = scanner.nextInt();
                        String metodoPagamento = "";
                        switch (metodo) {
                            case 1: metodoPagamento = "Dinheiro"; break;
                            case 2: metodoPagamento = "Cartão de Crédito"; break;
                            case 3: metodoPagamento = "Cartão de Débito"; break;
                            default: metodoPagamento = "Não especificado"; break;
                        }
                        mesas[mesaFechar - 1].fecharConta(metodoPagamento);
                    }
                    break;

                case 4:
                    System.out.println("\n----- Mesas Disponíveis -----");
                    for (int i = 0; i < mesas.length; i++) {
                        String status = mesas[i].isOcupada() ? "Ocupada" : "Livre";
                        System.out.println("Mesa " + (i + 1) + ": " + status);
                    }
                    break;

                case 5:
                    System.out.print("\nDigite o número da mesa para ver o histórico de pedidos (1 a 5): ");
                    int mesaHistorico = scanner.nextInt();
                    if (mesaHistorico < 1 || mesaHistorico > 5) {
                        System.out.println("Mesa inválida.");
                    } else {
                        System.out.println("\nHistórico de Pedidos da Mesa " + mesaHistorico + ":");
                        System.out.println(mesas[mesaHistorico - 1].getHistoricoPedidos());
                    }
                    break;

                case 6:
                    IntegracaoDosSistemas.main(new String[]{});
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}