import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var banho = new MaquinaBanho();
        int menu;

        do {
            // ----- Menu -----
            System.out.println("====BEM-VINDO AO PETSHOP====");
            System.out.println("1- Dar Banho");
            System.out.println("2- Abastecer Água");
            System.out.println("3- Abastecer Shampoo");
            System.out.println("4- Verificar Água");
            System.out.println("5- Verificar Shampoo");
            System.out.println("6- Verificar Pet no Banho");
            System.out.println("7- Colocar Pet na Máquina");
            System.out.println("8- Retirar Pet da Máquina");
            System.out.println("9- Limpar Máquina");
            System.out.println("0- SAIR \n");
            System.out.print("Escolha uma opção: ");
            menu = scanner.nextInt();

            switch (menu) {
                case 1: // Dar banho
                    if (banho.darBanho()) {
                        System.out.println("Banho iniciado");
                        try {
                            Thread.sleep(1500); // simula 1,5 s de banho
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Banho finalizado, retire o pet da máquina");
                    } else if (!banho.getPetNaMaquina()) {
                        System.out.println("Não há pet na máquina");
                    } else if (banho.isPetLimpo()) {
                        System.out.println("Esse pet já tomou banho");
                    } else {
                        System.out.println("Falta água (10L) ou shampoo (2L)");
                    }
                    break;

                case 2: // Abastecer água
                    if (banho.abastecerAgua()) {
                        System.out.println("+2L adicionados. Água = " + banho.getAgua() + "L");
                    } else {
                        System.out.println("Capacidade máxima atingida. Água = " + banho.getAgua() + "L");
                    }
                    break;

                case 3: // Abastecer shampoo
                    if (banho.abastecerShampoo()) {
                        System.out.println("+2L adicionados. Shampoo = " + banho.getShampoo() + "L");
                    } else {
                        System.out.println("Capacidade máxima atingida. Shampoo = " + banho.getShampoo() + "L");
                    }
                    break;

                case 4: // Verificar água
                    System.out.println("O nível de água é: " + banho.getAgua() + "L");
                    break;

                case 5: // Verificar shampoo
                    System.out.println("O nível de shampoo é: " + banho.getShampoo() + "L");
                    break;

                case 6: // Verificar pet
                    if (banho.getPetNaMaquina()) {
                        System.out.println("Tem um pet na máquina");
                    } else {
                        System.out.println("A máquina está vazia");
                    }
                    break;

                case 7: // Colocar pet
                    if (banho.colocarPet()) {
                        System.out.println("Pet colocado na máquina");
                    } else if (banho.getPetNaMaquina()) {
                        System.out.println("Já tem um pet na máquina");
                    } else {
                        System.out.println("Máquina suja: limpe antes de colocar outro pet");
                    }
                    break;

                case 8: // Retirar pet
                    if (banho.retirarPet()) {
                        System.out.println("Pet retirado da máquina");
                        if (banho.isMaquinaSuja()) {
                            System.out.println("Atenção: o pet saiu sem banho, a máquina precisa de limpeza");
                        }
                    } else {
                        System.out.println("Não tem um pet na máquina");
                    }
                    break;

                case 9: // Limpar máquina
                    if (banho.limparMaquina()) {
                        System.out.println("Máquina limpa. Água = " + banho.getAgua()
                                + "L, Shampoo = " + banho.getShampoo() + "L");
                    } else if (!banho.isMaquinaSuja()) {
                        System.out.println("A máquina não precisa de limpeza");
                    } else {
                        System.out.println("Falta água (3L) ou shampoo (1L) para limpar");
                    }
                    break;

                case 0: // Sair
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        } while (menu != 0);
    }
}