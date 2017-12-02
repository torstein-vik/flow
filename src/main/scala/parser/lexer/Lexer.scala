package io.github.torsteinvik.flow.parser.lexer


object Lexer {
    def getTokens(file : String) : Seq[Token] = {
        return Seq.empty[Token]
    }
    
    private val identifier = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet
    private val numeric = ('0' to '9').toSet
    
    import Token._
}
