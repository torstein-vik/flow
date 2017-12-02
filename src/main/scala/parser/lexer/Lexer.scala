package io.github.torsteinvik.flow.parser.lexer


class Lexer (data : String) {
    import Lexer._
    import Token._
    
    def analyze : Seq[(Token, Int)] = {
        import scala.collection.mutable.{Seq => MSeq}
        var analysis : MSeq[(Either[String, Token], Int)] = MSeq()
        
        
        
        return for {(Right(token), linenum) <- analysis} yield (token, linenum)
    }
}

object Lexer {
    import Token._
    
    def getTokens(data : String) : Seq[Token] = {
        return new Lexer(data).analyze.map{case (token, _) => token}
    }
}
