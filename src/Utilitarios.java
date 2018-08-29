import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilitarios {
	
	public List<Integer> gerarVariaveis(int nVariaveis, Random random){
		List<Integer> listaVariaveis = new ArrayList<Integer>();
		
		int i = 0;
        for(i = 0; i < nVariaveis; i++){
            listaVariaveis.add(random.nextInt(29) + 1);
        }
		
		return listaVariaveis;
	}
	
	public List<String> gerarOperadores(int nVariaveis, Random random){
		
		List<String> listaOperadoresDisponiveis = new ArrayList<String>();
		List<String> listaOperadores = new ArrayList<String>(); 
		
        listaOperadoresDisponiveis.add("+");
        listaOperadoresDisponiveis.add("-");
        listaOperadoresDisponiveis.add("x");
        listaOperadoresDisponiveis.add("÷");      
		
		int i = 0;
		for(i = 0; i < nVariaveis - 1; i++){
            listaOperadores.add(listaOperadoresDisponiveis.get((random.nextInt(3) + 1)));
        }
		
		return listaOperadores;
	}
	
	public String gerarExpressao(List<Integer> listaVariaveis, List<String> listaOperadores) {
		
		String expressao = listaVariaveis.get(0) + " " + listaOperadores.get(0) + " " + listaVariaveis.get(1);
		
		int i = 2;
		int j = 1;
		
		while(i < listaVariaveis.size() && j < listaOperadores.size()) {
			expressao += " " + listaOperadores.get(j) + " " + listaVariaveis.get(i);
			i++;
			j++;
		}
		
		return expressao;
	}
	
	public double calcularExpressao(String expressao) {
		double resultado = 0;
		
		expressao = expressao.replace("÷", "/");
		expressao = expressao.replace("x", "*");
		
		String[] expressaoArray = expressao.split(" ");
		List<String> listaExpressao = new ArrayList<>();
		List<String> listaOperadoresOrdenados = new ArrayList<>();		
		
		for(int i=0; i < expressaoArray.length; i++) {
			listaExpressao.add(expressaoArray[i]);
			if(expressaoArray[i].equals("+") || expressaoArray[i].equals("-") || expressaoArray[i].equals("*") || expressaoArray[i].equals("/")) 
				listaOperadoresOrdenados = ordenaOperadores(expressaoArray[i], listaOperadoresOrdenados);
		}
		
		while(listaOperadoresOrdenados.size() != 0) {
			
			String operadorBusca = listaOperadoresOrdenados.remove(0);			
			int posicaoOperador = buscarOperador(listaExpressao, operadorBusca);
			
			String valor1 = listaExpressao.remove(posicaoOperador - 1);
			posicaoOperador = posicaoOperador - 1;
			String valor2 = listaExpressao.remove(posicaoOperador + 1);
			String operador = listaExpressao.get(posicaoOperador);
			
			listaExpressao = subititiurOperador(listaExpressao, posicaoOperador, calcularValores(valor1, valor2, operador) + "");			
		}
		
		resultado = Double.parseDouble(listaExpressao.get(0));
		
		return resultado;
	}
	
	public List<String> ordenaOperadores(String operador, List<String> listaOperadoresOrdenados){		
		List<String> listaAux = new ArrayList<>();
		
		if(operador.equals("+") || operador.equals("-"))
			listaOperadoresOrdenados.add(operador);
		else {
			listaAux.add(operador);
			for(String op : listaOperadoresOrdenados) {
				listaAux.add(op);
			}
			listaOperadoresOrdenados = listaAux;
		}	
		
		return listaOperadoresOrdenados;
	}
	
	public int buscarOperador(List<String> listaExpressao, String operadorBusca) {
		int posicao = 0;
		
		for(String operador :listaExpressao) {
			if(operador.equals(operadorBusca)) {
				return posicao;
			}
			posicao++;
		}
		return 0;
	}
	
	public double calcularValores(String valor1, String valor2, String operador) {
		double resultado = 0;
		
		double primeiro = Double.parseDouble(valor1);
		double segundo = Double.parseDouble(valor2);
		
		if(operador.equals("+"))
			resultado = primeiro + segundo;
		else if(operador.equals("-"))
			resultado = primeiro - segundo;
		else if(operador.equals("*"))
			resultado = primeiro * segundo;
		else 
			resultado = primeiro / segundo;
		
		return resultado;
	}
	
	public List<String> subititiurOperador(List<String> listaExpressao, int posicaoOperador, String valor) {
		
		List<String> listaAux = new ArrayList<>();
		
		for(int i = 0; i < listaExpressao.size(); i++) {
			if(i == posicaoOperador)
				listaAux.add(valor);
			else
				listaAux.add(listaExpressao.get(i));
			
		}
		return listaExpressao = listaAux;
	}
}
