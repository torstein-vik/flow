package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.combinator._
import scala.util.matching.Regex


/** Lexer turning some input data into a sequence of [[Token]]s. Use [[getTokens]], as parser doesn't filter away [[Token.Comment]] tokens */
object Lexer extends RegexParsers {
    import Token._
    
    /** Return the sequence of [[Token]]s coming from parsing the input data.
     *  @param data the data to tokenize
     */
    @throws[LexerException]("if there is a lexing error or no parse")
    def getTokens(data : String) : Seq[Token] = parseAll(total, data) match {
        case Success(result, _) => result.filter{
            case Comment(_) => false
            case _ => true
        }
        
        case Failure(msg, next) => throw LexerException(msg, next.pos)
        case Error(msg, next) => throw LexerException(msg, next.pos)
    }
    
    /** Tokens that have some fixed string representation */
    val fixedTokens = Seq(Semantic, Singleton, Define, Colon, Semicolon, Machine, Flow, TupleOpen, TupleClose, TupleSeparator)
    
    /** Parser for [[fixedTokens]] */
    def fixedToken : Parser[Token] = fixedTokens.map(token => token.chars ^^^ token).reduceLeft(_ | _)
    
    /** Parser for [[Token.Numeric]] [[Token.Identifier]]s */
    def numeric : Parser[Token] = """-?\d+""".r ^^ (new Identifier(_) with Numeric)
    /** Parser for [[Token.Identifier]]s*/
    def identifier : Parser[Token] = """[\*\+A-Za-z0-9]+""".r ^^ (new Identifier(_))
    
    /** Parser for single line [[Token.Comment]]s*/
    def singlelinecomment : Parser[Token] = """\/\/.*\r?\n""".r ^^ (Comment(_))
    /** Parser for multiline [[Token.Comment]]s*/
    def multilinecomment : Parser[Token] = """\/\*((?!\*\/).|\r?\n)*\*\/""".r ^^ (Comment(_))
    
    /** Parser for everything put together */
    def total : Parser[Seq[Token]] = positioned(multilinecomment | singlelinecomment | fixedToken | numeric | identifier).*
}
