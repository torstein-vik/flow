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
    
    def statement : Parser[Statement] = (semantic | define)
    def semantic : Parser[AST.Semantic] = (Token.Semantic ~> rep1sep(identifier, TupleSeparator)) ^^ (AST.Semantic(_ : _*))
    
    def machinespec : Parser[MachineSpec] = ((flowterm ~ acceptMatch("machine or flow", {
        case Token.Machine => AST.MachineTo(_, _)
        case Token.Flow => AST.FlowTo(_, _)
    })).* ~ (flowterm)) ^^ {
        case xs ~ x => xs.foldRight[MachineSpec](AST.FinalOutput(x)){case (x ~ f, to) => f(x, to)}
    }
    
    def flowterm : Parser[FlowTerm] = tupleterm | classterm
    def tupleterm : Parser[TupleTerm] = (TupleOpen ~> repsep(flowterm, TupleSeparator) <~ TupleClose) ^^ (TupleTerm(_ : _*))
    def classterm : Parser[ClassTerm] = classvalue ^^ (ClassTerm(_))
    
    def classvalue : Parser[AST.Class] = identifiedclass
    def identifiedclass : Parser[IdentifiedClass] = identifier ^^ (IdentifiedClass(_))
    
}
