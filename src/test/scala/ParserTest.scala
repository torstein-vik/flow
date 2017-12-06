package io.github.torsteinvik.flow.test

import io.github.torsteinvik.flow.parser.lexer.Token.Identifier
import io.github.torsteinvik.flow.parser._
import io.github.torsteinvik.flow._

import org.scalatest.FunSuite

class BasicParserTest extends FunSuite {
    import AST._
            
    test ("Parsing 'Semantic A;'") {
        val ast = Parser.getAST("Semantic A;")
        assert(ast === Block(Semantic(Identifier("A"))))
    }
    
    test ("Ignoring singleline comments") {
        val ast = Parser.getAST("// Hey im a comment \n Semantic A;\n // hey again!\n")
        assert(ast === Block(Semantic(Identifier("A"))))
    }
    
    test ("Ignoring multiline comments") {
        val ast = Parser.getAST("Semantic /* hey im a comment*/ A;\n/* hey \n im \n a \n multiline \n comment \n*/")
        assert(ast === Block(Semantic(Identifier("A"))))
    }
    
    
    
}

class FileParserTest extends FunSuite {
    import AST._
    import scala.io.Source
    
    
}
