package io.github.torsteinvik.flow.parser.lexer


class Lexer (seq : Seq[Char]) {
    import Lexer._
    import Token._
    
    def analyze : Seq[Token] = {
        return Seq.empty[Token]
    }
}

object Lexer {
    import Token._
    
    def getTokens(file : String) : Seq[Token] = {
        return new Lexer(file).analyze
    }
}
