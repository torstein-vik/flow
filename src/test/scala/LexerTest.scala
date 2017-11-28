package io.github.torsteinvik.flow.test

import io.github.torsteinvik.flow.parser.lexer._

import org.scalatest.FunSuite

class LexerTest extends FunSuite {
    import Token._
    
    test ("Lexing 'Semantic'") {
        val tokens = Lexer.getTokens("Semantic")
        assert(tokens === Seq(Semantic))
    }
    
    }
}