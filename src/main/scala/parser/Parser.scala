package io.github.torsteinvik.flow.parser

import io.github.torsteinvik.flow._
import io.github.torsteinvik.flow.parser.lexer._

import scala.util.parsing.combinator._

object Parser extends Parsers {
    import Token._
    import AST._
    
    type Elem = Token
    
    def getAST (data : String) : AST = getASTFromTokens(Lexer.getTokens(data))
    
    def getASTFromTokens (tokens : Seq[Token]) : AST = phrase(block)(new TokenReader(tokens)) match {
        case Success(result, _) => result
        case Failure(msg, next) => throw ParserException(msg, next.pos)
        case Error(msg, next) => throw ParserException(msg, next.pos)
    }
    
    def identifier : Parser[Identifier] = acceptMatch("name", {case x : Identifier => x})
    
    def block : Parser[AST] = (statement <~ Semicolon).* ^^ (Block(_ : _*))
    
    def statement : Parser[Statement] = (semantic)
    def semantic : Parser[AST.Semantic] = (Token.Semantic ~> rep1sep(identifier, Colon)) ^^ (AST.Semantic(_ : _*))
    
}
