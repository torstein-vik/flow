package io.github.torsteinvik.flow.parser.lexer


class Lexer (seq : Seq[Char]) {
    import Lexer._
    import Token._
    
    if (!seq.forall(legalchars.contains(_))) throw LexerException("Input contains illegal characters! " + seq.filter(legalchars.contains(_)))
    
}

object Lexer {
    import Token._
    def getTokens(file : String) : Seq[Token] = {
        return new Lexer(file).analyze
    }
    
    
    private val legalchars = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet ++ Set(' ', '\n', ':', ';', '=', '>', '-', '(', ')', ',')
    
}
