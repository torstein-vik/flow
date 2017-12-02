package io.github.torsteinvik.flow.parser.lexer


object Lexer {
    def getTokens(file : String) : Seq[Token] = {
        return Seq.empty[Token]
    }
    
    
    import Token._
    private val legalchars = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet ++ Set(' ', '\n', ':', ';', '=', '>', '-', '(', ')', ',')
    
}
