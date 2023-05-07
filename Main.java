import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();
        boolean exit = false;

        while (true) {
            System.out.println("Digite\n1 para criar uma conta\n2 para ver o saldo de uma conta\n3 para sacar\n4 para depositar\nqualquer outra tecla para sair:");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
            case "1": //cria Conta
                contas = addConta(contas);
                break;
            case "2": //mostra saldo de dada Conta
                Conta conta = buscaConta(contas);
                if (conta == null) {
                    System.out.println("Conta não encontrada.");
                } else {
                    System.out.println("Saldo da conta de " + conta.getNome_titular() + ": R$" + conta.getSaldo());
                }
                break;
            case "3": //sacar valor de dada Conta
                conta = buscaConta(contas);

                if (conta == null) {
                    System.out.println("Conta não encontrada.");
                } else {
                    System.out.println("Digite o valor a ser sacado:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    if (conta.sacar(valor)) {
                        System.out.println("Saque efetuado com sucesso! Novo saldo: R$" + conta.getSaldo());
                    } else {
                        System.out.println("Saldo insuficiente.");
                    }
                }
                break;
            case "4":
                conta = buscaConta(contas);

                if (conta == null) {
                    System.out.println("Conta não encontrada.");
                } else {
                    System.out.println("Digite o valor a ser depositado:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    conta.depositar(valor);
                    System.out.println("Novo saldo: R$" + conta.getSaldo());
                }
                break;
            default:
                exit = true;
                break;
            }
            if (exit) break;
            scanner.nextLine();
        }
        scanner.close();
    }
    
    static Conta buscaConta(List<Conta> contas){
        System.out.println("Digite o número da conta:");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();
        Conta conta = null;
        for (Conta c : contas) {
            if (c.getNumero() == numeroConta) {
                conta = c;
                break;
            }
        }
        return conta;
    }

    static List<Conta> addConta(List<Conta> contas){
        System.out.println("Digite o nome do titular da conta:");
        String nomeTitular = scanner.nextLine();
        System.out.println("Digite o tipo de conta a ser criada:\n1 para normal\n2 para especial\n3 para poupança\noutro numero: inválido.");
        String tipoConta = scanner.nextLine();
        switch(tipoConta){
            case "1":
                Conta conta = new Conta(contas.size()+1, nomeTitular);
                contas.add(conta);

                System.out.println("Conta criada com sucesso! O número da conta é " + conta.getNumero());
                break;
            case "2":
                System.out.println("Insira o limite da conta especial:");
                double limite = scanner.nextDouble();
                scanner.nextLine();

                ContaEspecial contaE = new ContaEspecial(contas.size()+1, nomeTitular, limite);
                contas.add(contaE);

                System.out.println("Conta criada com sucesso! O número da conta é " + contaE.getNumero());
                break;
            case "3":
                ContaPoupanca contaP = new ContaPoupanca(contas.size()+1, nomeTitular);
                contas.add(contaP);

                System.out.println("Conta criada com sucesso! O número da conta é " + contaP.getNumero());
                break;
            default:
                System.out.println("Número inválido. Conta do titular " + nomeTitular + " não foi criada.");
                break;
        }
        return contas;
    }
}
