package io.github.torsteinvik.flow.parser.lexer


object Lexer {
    import Token._
    def getTokens(file : String) : Seq[Token] = {
        return new Lexer(file).analyze
    }
    
    
    private val legalchars = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet ++ Set(' ', '\n', ':', ';', '=', '>', '-', '(', ')', ',')
    
}
