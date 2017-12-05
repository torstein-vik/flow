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
    /** A [[Statement]] which declares a semantic class 
     *  @param classes list of [[parser.lexer.Token.Identifier]]s to be declared as classes
     */
    case class Semantic(classes : parser.lexer.Token.Identifier*)
    /** A [[Statement]] which defines a machine 
     *  @param machine [[parser.lexer.Token.Identifier]] of the new machine
     *  @param machinespec specification of this machine
     */
    case class Define(machine : parser.lexer.Token.Identifier, machinespec : MachineSpec)
}