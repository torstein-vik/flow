package io.github.torsteinvik.flow.test

import io.github.torsteinvik.flow.parser.lexer._

import org.scalatest.FunSuite

class BasicLexerTest extends FunSuite {
    import Token._
    
    test ("Lexing keywords") {
        assert(Lexer.getTokens("Semantic") === Seq(Semantic))
        assert(Lexer.getTokens("Singleton") === Seq(Singleton))
        assert(Lexer.getTokens("Define") === Seq(Define))
    }
    
    test ("Lexing notation") {
        assert(Lexer.getTokens(":") === Seq(Colon))
        assert(Lexer.getTokens(";") === Seq(Semicolon))
        assert(Lexer.getTokens("=>") === Seq(Machine))
        assert(Lexer.getTokens("->") === Seq(Flow))
        assert(Lexer.getTokens("(") === Seq(TupleOpen))
        assert(Lexer.getTokens(")") === Seq(TupleClose))
        assert(Lexer.getTokens(",") === Seq(TupleSeparator))
    }
    
    test ("Lexing tuples") {
        assert(Lexer.getTokens("(1, 2, 3)") === Seq(TupleOpen, Numeric(1), TupleSeparator, Numeric(2), TupleSeparator, Numeric(3), TupleClose))
    }
    
    test ("Lexing 'Semantic A;'") {
        val tokens = Lexer.getTokens("Semantic A;")
        assert(tokens === Seq(Semantic, Identifier("A"), Semicolon))
    }
    
    test ("Lexing 'Semantic Singleton A;'") {
        val tokens = Lexer.getTokens("Semantic Singleton A;")
        assert(tokens === Seq(Semantic, Singleton, Identifier("A"), Semicolon))
    }
    
    test ("Lexing '123456'") {
        val tokens = Lexer.getTokens("123456")
        assert(tokens === Seq(Numeric(123456)))
        assert(tokens(0).isInstanceOf[Numeric])
        assert(tokens(0).asInstanceOf[Numeric].value === BigInt(123456))
    }
    
    test ("Lexing '-123456'") {
        val tokens = Lexer.getTokens("-123456")
        assert(tokens === Seq(Numeric(-123456)))
        assert(tokens(0).isInstanceOf[Numeric])
        assert(tokens(0).asInstanceOf[Numeric].value === BigInt(-123456))
    }
    
    test ("Ignoring singleline comments") {
        assert(Lexer.getTokens("// Hey im a comment \n Semantic A;\n // hey again!\n") === Seq(Semantic, Identifier("A"), Semicolon))
    }
    
    test ("Ignoring multiline comments") {
        assert(Lexer.getTokens("Semantic /* hey im a comment*/ A;\n/* hey \n im \n a \n multiline \n comment \n*/ Define;") === Seq(Semantic, Identifier("A"), Semicolon, Define, Semicolon))
    }
    
    test ("Ignoring whitespace and newline") {
        assert(Lexer.getTokens("Semantic    ;") === Seq(Semantic, Semicolon))
        assert(Lexer.getTokens("\n\nSemantic;\n\n") === Seq(Semantic, Semicolon))
    }
}

class FileLexerTest extends FunSuite {
    import Token._
    import scala.io.Source
    
    test ("Lexing example1.flw") {
        val source = Source.fromURL(getClass.getResource("/example1.flw"))
        val file = try source.mkString finally source.close
        assert(Lexer.getTokens(file) === Seq(
            Semantic, Identifier("A"), Semicolon, 
            Define, Identifier("f"), Colon, Identifier("A"), Machine, Identifier("A"), Semicolon
        ))
    }
    
    test ("Lexing example2.flw") {
        val source = Source.fromURL(getClass.getResource("/example2.flw"))
        val file = try source.mkString finally source.close
        assert(Lexer.getTokens(file) === Seq(
            Semantic, Identifier("Int"), Semicolon, 
            Semantic, Singleton, Identifier("0"), TupleSeparator, Identifier("1"), TupleSeparator, Identifier("2"), TupleSeparator, Identifier("3"), TupleSeparator, Identifier("4"), TupleSeparator, Identifier("5"), Semicolon, 
            Define, Identifier("+"), Colon, TupleOpen, Identifier("Int"), TupleSeparator, Identifier("Int"), TupleClose, Machine, Identifier("Int"), Semicolon,
            Define, Identifier("+"), Colon, TupleOpen, Identifier("0"), TupleSeparator, Identifier("1"), TupleClose, Machine, Identifier("1"), Semicolon,
            Define, Identifier("+"), Colon, TupleOpen, Identifier("1"), TupleSeparator, Identifier("1"), TupleClose, Machine, Identifier("2"), Semicolon,
            Define, Identifier("+"), Colon, TupleOpen, Identifier("2"), TupleSeparator, Identifier("1"), TupleClose, Machine, Identifier("3"), Semicolon,
            Define, Identifier("+"), Colon, TupleOpen, Identifier("3"), TupleSeparator, Identifier("1"), TupleClose, Machine, Identifier("4"), Semicolon,
            Define, Identifier("+"), Colon, TupleOpen, Identifier("4"), TupleSeparator, Identifier("1"), TupleClose, Machine, Identifier("5"), Semicolon
            
        ))
    }
    
}
