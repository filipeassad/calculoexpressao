import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {		
		
		System.out.println("Gerar uma expressão aleatória!");
        Random random = new Random();
        Utilitarios utilitarios = new Utilitarios();
        int nVariaveis = random.nextInt(8) + 2; 
        
        List<Integer> listaVariaveis = utilitarios.gerarVariaveis(nVariaveis, random);
        List<String> listaOperadores = utilitarios.gerarOperadores(nVariaveis, random);           
        
        String expressao = utilitarios.gerarExpressao(listaVariaveis, listaOperadores);
        
        System.out.println("nVariaveis: " + nVariaveis);
        System.out.println("listaVariaveis.Size: " + listaVariaveis.size());
        System.out.println("Expressão: " + expressao + " = " + utilitarios.calcularExpressao(expressao));       
        
	}
}
