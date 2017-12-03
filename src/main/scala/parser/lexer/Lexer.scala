package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.combinator._
import scala.util.matching.Regex



object Lexer extends RegexParsers {
    import Token._
    
    def getTokens(data : String) : Seq[Token] = parseAll(total, data) match {
        case Success(result, _) => result
        case Failure(msg, _) => throw LexerException(msg)
        case Error(msg, _) => throw LexerException(msg)
    }
}
