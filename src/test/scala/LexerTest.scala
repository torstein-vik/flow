package io.github.torsteinvik.flow.test

import io.github.torsteinvik.flow.parser.lexer._

import org.scalatest.FunSuite

class LexerTest extends FunSuite {
    import Token._
    
    test ("Lexer throws LexerExcpetion on illegal input characters") {
        assertThrows[LexerException] {
            new Lexer("æøå")
        }
    }
    
    test ("Lexing 'Semantic'") {
        val tokens = Lexer.getTokens("Semantic")
        assert(tokens === Seq(Semantic))
    }
    
    test ("Lexing 'Semantic A;'") {
        val tokens = Lexer.getTokens("Semantic A;")
        assert(tokens === Seq(Semantic, Identifier("A"), Semicolon))
    }
    
    test ("Lexing '123456'") {
        val tokens = Lexer.getTokens("123456")
        assert(tokens === Seq(new Identifier("123456")))
        assert(tokens(0).isInstanceOf[Numeric])
        assert(tokens(0).asInstanceOf[Numeric].value === BigInt(123456))
    }
}