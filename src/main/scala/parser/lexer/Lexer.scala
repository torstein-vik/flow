package io.github.torsteinvik.flow.parser.lexer


class Lexer (data : String) {
    import Lexer._
    import Token._
    
    def analyze : Seq[Token] = {
        import scala.collection.mutable.{Seq => MSeq}
        var analysis : MSeq[Either[String, Token]] = MSeq(Left(data))
        
        
        
        return for {Right(token) <- analysis} yield token
    }
}

object Lexer {
    import Token._
    
    def getTokens(data : String) : Seq[Token] = {
        return new Lexer(data).analyze
    }
}
