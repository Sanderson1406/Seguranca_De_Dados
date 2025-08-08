import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Declaração de váriaveis
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        int opcao = 0;
        String mensagem = "";
        String senha = "";
        String cifra = "";

        // Entrada de dados
        try {
            System.out.print("Digite <1> para encriptar ou <2> para decriptar: ");
            opcao = Integer.parseInt(leitor.readLine());

            if (opcao == 1) {
                System.out.print("Digite o mensagem: ");
                mensagem = leitor.readLine();
            } else if (opcao == 2) {
                System.out.print("Digite a cifra: ");
                cifra = leitor.readLine();

            }
            System.out.print("Digite a senha: ");
            senha = leitor.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Processamento

        if (opcao == 1) {
            cifra = encriptar(mensagem, senha);
            System.out.println(cifra);
        }else if (opcao == 2) {
            mensagem = decriptar(cifra, senha);
            System.out.println(mensagem);
        }

    }

    private static String encriptar(String mensagem, String senha) {
        String cifra = "";
        for (int i = 0;i < mensagem.length(); i++) {
            int letraMensagem = mensagem.charAt(i);
            int letraSenha = senha.charAt(i % senha.length());
            int letraCifra = (letraMensagem ^ letraSenha);
            String temp = Integer.toHexString(0xFF & letraCifra);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            cifra += temp;
        }

        return cifra;
    }

    private static String decriptar(String cifra, String senha) {
        String mensagem = "";
        for (int i = 0;i < cifra.length(); i+=2) { // hexadecimal usa +=2 pois o hexadecimal para cada caracter eoe representa 2
            int letraCifra = Integer.parseInt(cifra.substring(i, i+2), 16);
            int letraSenha = senha.charAt(i / 2 % senha.length());
            int letraMensagem = (letraCifra ^ letraSenha);
            mensagem += ((char) letraMensagem);

        }

        return mensagem;
    }
}