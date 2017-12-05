package io.github.torsteinvik.flow


/** An abstract root of an abstract syntax tree */
abstract sealed class AST

/** Object with [[AST]] implementation */
object AST {
    case class Block(stmts : Statement*) extends AST
    
    abstract sealed class Statement 
    case class Semantic(classes : parser.lexer.Token.Identifier*)
    case class Define(machine : parser.lexer.Token.Identifier, machinespec : MachineSpec)
}