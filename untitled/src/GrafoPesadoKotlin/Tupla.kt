package GrafoPesadoKotlin

import GrafosPesados.GrafoPesado
import GrafosPesados.Tupla
import Negocio.DFS

class Tupla {
    private var verticeOrigen = 0
    private var verticeDestino = 0
    private var peso = 0.0

    fun Tupla(verticeOrigen: Int, verticeDestino: Int, peso: Double) {
        this.verticeOrigen = verticeOrigen
        this.verticeDestino = verticeDestino
        this.peso = peso
    }

    fun getVerticeOrigen(): Int {
        return verticeOrigen
    }

    fun setVerticeOrigen(verticeOrigen: Int) {
        this.verticeOrigen = verticeOrigen
    }

    fun getVerticeDestino(): Int {
        return verticeDestino
    }

    fun setVerticeDestino(verticeDestino: Int) {
        this.verticeDestino = verticeDestino
    }

    fun getPeso(): Double {
        return peso
    }

    fun setPeso(peso: Double) {
        this.peso = peso
    }

    /**
     * sera static por que no requerira instanciar objetos
     * sera propio de la clase
     *
     * metodo para obtemer un lista de tuplas ordenada
     * donde recibe un grafo y en base a ese grafo se recibe cada par de vertices con su peso
     * de manera ordenada
     * @param elGrafoPesado el grafo pesado
     * @return retorna una lista de tuplas ordenadas en base al peso
     * probando un comparato de manera funcional
     * con funcion lambda
     *
     *
     * la logica en esto es
     * antes de hacer un dfs
     * agarro todas las combinaciones de adyacentes en una lista
     *
     * luego de hacer eso recien hago un dfs
     * solamente agrego a la lista los no marcados
     */
    fun getSortedTupleList(elGrafoPesado: GrafoPesado?): List<Tupla> {
        val lista: MutableList<Tupla> = mutableListOf()
        val dfs = DFS(elGrafoPesado)
        dfs.ejecutarDFSRecursivoPesadoParaTupla(0, lista)
        lista.sortWith(Comparator { tupla1, tupla2 ->
            tupla1.peso.compareTo(tupla2.peso)
        })
        return lista
    }

    override fun toString(): String {
        return getVerticeOrigen().toString() + " " + this.getVerticeDestino() + " " + this.getPeso()
    }
}