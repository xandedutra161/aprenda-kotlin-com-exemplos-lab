import java.lang.Exception

data class Usuario(val nome: String)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel)

class Formacao(private val nomeFormacao: String, private val conteudos: List<ConteudoEducacional>) {
    private val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        try {
            for (usuario in usuarios) {
                if (!inscritos.contains(usuario)) {
                    inscritos.add(usuario)
                    println("Usuário '${usuario.nome}' matriculado na formação '$nomeFormacao'.")
                } else {
                    throw Exception("'${usuario.nome}' já matriculado na formação '$nomeFormacao'")
                }
            }
        } catch (e: Exception) {
            println("Erro ao matricular usuário: ${e.message}")
            println()
        }
    }

    fun exibirInscritos() {
        println("Lista de inscritos na formação '$nomeFormacao': ${inscritos.map { it.nome }}")
    }

    fun exibirConteudos() {
        println("Conteúdos do Curso $nomeFormacao:")
        var tempoCurso = 0
        conteudos.forEach { conteudo ->
            println("- ${conteudo.nome}")
            tempoCurso += conteudo.duracao
        }

        println("Curso com duração de $tempoCurso minutos.")
        println()
    }
}

fun main() {
    val usuarioA = Usuario("João")
    val usuarioB = Usuario("Maria")
    val usuarioC = Usuario("Alexandre")

    val logicaDeProgramacao = ConteudoEducacional("Introdução a Lógica de Programação", 40, Nivel.BASICO)
    val poo = ConteudoEducacional("Programação Orientada a Objetos", 74, Nivel.INTERMEDIARIO)
    val levantamenteDeRequisitos = ConteudoEducacional("Levantamento de Requisitos", 48, Nivel.AVANCADO)
    val fKoltin = Formacao("Desenvolvedor Kotlin", listOf(logicaDeProgramacao, poo, levantamenteDeRequisitos))

    val android = ConteudoEducacional("Introdução ao Android", 40, Nivel.BASICO)
    val jetpackCompose = ConteudoEducacional("Jetpack Compose", 57, Nivel.INTERMEDIARIO)
    val mvvm = ConteudoEducacional("Arquitetura MVVM", 71, Nivel.AVANCADO)
    val fMobile = Formacao("Desenvolvedor Mobile", listOf(android, jetpackCompose, mvvm))

    val ws = ConteudoEducacional("Working Storage", 40, Nivel.BASICO)
    val rolagemDeTela = ConteudoEducacional("Rolagem de tela", 64, Nivel.INTERMEDIARIO)
    val fCOBOL = Formacao("Desenvolvedor COBOL", listOf(poo, ws, rolagemDeTela))

    fKoltin.exibirConteudos()
    fMobile.exibirConteudos()
    fCOBOL.exibirConteudos()

    fKoltin.exibirInscritos()
    fMobile.exibirInscritos()
    fCOBOL.exibirInscritos()

    println()

    fKoltin.matricular(usuarioA, usuarioB, usuarioC)
    fMobile.matricular(usuarioA, usuarioB, usuarioC)
    fCOBOL.matricular(usuarioA, usuarioB, usuarioC)
    fKoltin.matricular(usuarioA) // Tentativa de matricular o mesmo usuário novamente

    fKoltin.exibirInscritos()
    fMobile.exibirInscritos()
    fCOBOL.exibirInscritos()
}
