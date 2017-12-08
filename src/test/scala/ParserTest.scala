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
    
    test ("Parsing 'Semantic A, B;'") {
        val ast = Parser.getAST("Semantic A, B;")
        assert(ast === Block(Semantic(Identifier("A"), Identifier("B"))))
    }
    
    test ("Parsing 'Semantic A, B, C;'") {
        val ast = Parser.getAST("Semantic A, B, C;")
        assert(ast === Block(Semantic(Identifier("A"), Identifier("B"), Identifier("C"))))
    }
    
    test ("Parsing 'Semantic Singleton A;'") {
        val ast = Parser.getAST("Semantic Singleton A;")
        assert(ast === Block(Semantic(true, Identifier("A"))))
    }
    
    test ("Parsing 'Semantic Singleton A, B;'") {
        val ast = Parser.getAST("Semantic Singleton A, B;")
        assert(ast === Block(Semantic(true, Identifier("A"), Identifier("B"))))
    }
    
    test ("Parsing 'Semantic Singleton A, B, C;'") {
        val ast = Parser.getAST("Semantic Singleton A, B, C;")
        assert(ast === Block(Semantic(true, Identifier("A"), Identifier("B"), Identifier("C"))))
    }
    
    test ("Ignoring singleline comments") {
        val ast = Parser.getAST("// Hey im a comment \n Semantic A;\n // hey again!\n")
        assert(ast === Block(Semantic(Identifier("A"))))
    }
    
    test ("Ignoring multiline comments") {
        val ast = Parser.getAST("Semantic /* hey im a comment*/ A;\n/* hey \n im \n a \n multiline \n comment \n*/")
        assert(ast === Block(Semantic(Identifier("A"))))
    }
    
    test ("Parsing 'Define f: X => Y;'") {
        val ast = Parser.getAST("Define f: X => Y;")
        assert(ast === Block(Define(Identifier("f"), 
            MachineTo(ClassTerm(IdentifiedClass(Identifier("X"))), 
            FinalOutput(ClassTerm(IdentifiedClass(Identifier("Y"))))))))
    }
    
    test ("Parsing 'Define g: (X, Y) -> (Y, X);'") {
        val ast = Parser.getAST("Define g: (X, Y) -> (Y, X);")
        assert(ast === Block(Define(Identifier("g"), 
            FlowTo(TupleTerm(ClassTerm(IdentifiedClass(Identifier("X"))), ClassTerm(IdentifiedClass(Identifier("Y")))), 
            FinalOutput(TupleTerm(ClassTerm(IdentifiedClass(Identifier("Y"))), ClassTerm(IdentifiedClass(Identifier("X")))))))))
    }
    
    test ("Parsing 'Define h: (X, Y) => (Y, X) -> R;'") {
        val ast = Parser.getAST("Define h: (X, Y) => (Y, X) -> R;")
        assert(ast === Block(Define(Identifier("h"), 
            MachineTo(TupleTerm(ClassTerm(IdentifiedClass(Identifier("X"))), ClassTerm(IdentifiedClass(Identifier("Y")))), 
            FlowTo(TupleTerm(ClassTerm(IdentifiedClass(Identifier("Y"))), ClassTerm(IdentifiedClass(Identifier("X")))), 
            FinalOutput(ClassTerm(IdentifiedClass(Identifier("R")))))))))
    }
}

class FileParserTest extends FunSuite {
    import AST._
    import scala.io.Source
    
    test ("Parsing example1.flw") {
        val source = Source.fromURL(getClass.getResource("/example1.flw"))
        val file = try source.mkString finally source.close
        assert(Parser.getAST(file) === Block(Semantic(Identifier("A")), Define(Identifier("f"), 
            MachineTo(ClassTerm(IdentifiedClass(Identifier("A"))), 
            FinalOutput(ClassTerm(IdentifiedClass(Identifier("A"))))))))
    }
    
}
