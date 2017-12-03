package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.combinator._
import scala.util.matching.Regex



object Lexer extends RegexParsers {
    import Token._
    
    def getTokens(data : String) : Seq[Token] = parseAll(total, data) match {
        case Success(result, _) => result.filter{
            case Comment(_) => false
            case _ => true
        }
        case Failure(msg, _) => throw LexerException(msg)
        case Error(msg, _) => throw LexerException(msg)
    }
    
    val fixedTokens = Seq(Semantic, Define, Colon, Semicolon, Machine, Flow, TupleOpen, TupleClose, TupleSeparator)
    
    def fixedToken : Parser[Token] = fixedTokens.map(token => token.chars ^^^ token).reduceLeft(_ | _)
    
    def numeric : Parser[Token] = """-?\d+""".r ^^ (new Identifier(_) with Numeric)
    def identifier : Parser[Token] = """[A-Za-z0-9]+""".r ^^ (new Identifier(_))
    
    def singlelinecomment : Parser[Token] = """\/\/.*\n""".r ^^ (Comment(_))
    def multilinecomment : Parser[Token] = """\/\*((?!\*\/).|\n)*\*\/""".r ^^ (Comment(_))
    
    def total : Parser[Seq[Token]] = (multilinecomment | singlelinecomment | fixedToken | numeric | identifier).*
}
