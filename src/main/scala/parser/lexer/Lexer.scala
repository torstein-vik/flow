package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.combinator._
import scala.util.matching.Regex



object Lexer extends RegexParsers {
    import Token._
    
    def getTokens(data : String) : Seq[Token] = {
        
    }
}
