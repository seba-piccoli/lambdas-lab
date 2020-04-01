package main.java.com.javanme.java8;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase con ejercicios nivel básico
 * Created by aalopez on 18/08/15.
 */
public class Basico {

    /**
     * Convertir a mayúsculas cada una de las palabras recibidas como parámetro.
     * Trata de usar uno de los nuevos métodos adicionados a las listas en Java 8, verifica cual método permite
     * reemplazar los items de la lista por medio de una función.
     *
     * @param palabras Listado de palabras a convertir
     * @return Lista que contiene las palabras en mayúsculas
     */
    public List<String> ejercicio1(List<String> palabras) {

    	List<String> resultado = null;
    	
    	resultado = palabras.stream().
    			    map(s -> s.toUpperCase()).
    			    collect(Collectors.toList());
    	
    	return resultado;
    }

    /**
     * Del listado de cadenas de texto eliminar las cadenas de ese listado cuyo tamaño sea inferior o igual a 10 caracteres.
     * Trata de usar uno de los nuevos métodos adicionados a las listas en Java 8
     *
     * @param listado cadenas de texto. Atención: Este listado es no modificable, por lo que una nueva lista de debe ser
     * creada a partir de este listado, por ejemplo: {@code List<String> nuevaLista = new ArrayList<>(listado);}
     * @return lista que contiene cadenas de texto cuyo tamaño de caracteres es superior a 10
     */
    public List<String> ejercicio2(List<String> listado) {
        List<String> aux = listado;
        aux.removeIf(s -> s.length() <= 10);
        return aux;
    }

    /**
     * Del listado pasado como parámetro, une la tercera, cuarta y quinta cadena separadas por guión (-).
     * Usa la API Stream y sus métodos que permiten saltar y limitar el stream.
     *
     * @param listado cadenas de texto
     * @return Cadena de texto que se compone de la tercera, cuarta y quinta cadena de texto separadas por guiones
     * @see java.util.stream.Stream
     * @see java.util.stream.Collectors
     */
    public String ejercicio3(List<String> listado) {
    	Optional<String> resultado = listado.stream().
     		               filter(s -> (listado.indexOf(s) >= 2)&&(listado.indexOf(s) < 5)).
     		               reduce((s1, s2) -> s1 + '-' + s2);
    	
    	return resultado.get();
    	
    }

    /**
     * Ordernar el listado pasado como parámetro usando orden natural numérico.
     * Atención, el listado contiene números como cadenas de texto, por lo tanto, se debe hacer conversión.
     * Usa la API Stream
     *
     * @param listado números a ordenar
     * @return Listado de números en orden natural
     * @see java.util.stream.Stream
     * @see java.util.stream.Collectors
     */
    public List<Integer> ejercicio4(List<String> listado) {
        return listado.stream().
        	   map(s -> Integer.valueOf(s)).
        	   sorted().
        	   collect(Collectors.toList());
    }

    /**
     * Ordernar el listado pasado como parámetro primero por tamaño de la cadena de texto y luego alfabeticamente
     * Usa la API Stream.
     * Crea un {@code Comparator<String>} compuesto para que compares primero por el tamaño y luego alfabeticamente.
     *
     * @param listado cadenas de texto a ordenar
     * @return Listado ordenado primero por número de caracteres y luego alfabeticamente
     * @see java.util.stream.Stream
     * @see java.util.stream.Collectors
     */
    public List<String> ejercicio5(List<String> listado) {
    	
    	@SuppressWarnings("hiding")
		class MyComparator<String> implements Comparator<String>{

			@Override
			public int compare(String o1, String o2) {

				if(o1.toString().equalsIgnoreCase(o2.toString()))
					return 0;
				
				int resultado = o1.toString().length() > o2.toString().length() ? 1 : 0;
				
				if(resultado == 1)
					return resultado;
				
				resultado = o1.toString().length() < o2.toString().length() ? -1 : 0;

				if(resultado == -1)
					return resultado;				
				
				return o1.toString().compareTo(o2.toString());

			}
    		

    	}
    	
    	Comparator<String> comparator = new MyComparator<String>();
    	
    	return listado.stream().
    		   sorted(comparator).
    		   collect(Collectors.toList());
    }
}
