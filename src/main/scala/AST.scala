package io.github.torsteinvik.flow


/** An abstract root of an abstract syntax tree */
abstract sealed class AST

/** Object with [[AST]] implementation */
object AST {
    /** A list of to [[Statement]]s to be 'exectued' 
     *  @param stmts List of [[Statement]] that this block 'executes'
     */
    case class Block(stmts : Statement*) extends AST
    
    /** An abstact statement to be 'executed' */
    abstract sealed class Statement 
    case class Semantic(classes : parser.lexer.Token.Identifier*)
    case class Define(machine : parser.lexer.Token.Identifier, machinespec : MachineSpec)
}