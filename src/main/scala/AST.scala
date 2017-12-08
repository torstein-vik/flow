package io.github.torsteinvik.flow


/** An abstract root of an abstract syntax tree */
abstract sealed class AST

/** Object with [[AST]] implementation */
object AST {
    /** A list of to [[Statement]]s to be 'executed' 
     *  @param stmts List of [[Statement]] that this block 'executes'
     */
    case class Block(stmts : Statement*) extends AST
    
    /** An abstact statement to be 'executed' */
    abstract sealed class Statement 
    /** A [[Statement]] which declares a semantic class 
     *  @param classes list of [[parser.lexer.Token.Identifier]]s to be declared as classes
     */
    case class Semantic(singleton : Boolean)(classes : parser.lexer.Token.Identifier*) extends Statement
    object Semantic {
        def apply(classes : parser.lexer.Token.Identifier*) : Semantic = new Semantic(false)(classes : _*)
    }
    /** A [[Statement]] which defines a machine 
     *  @param machine [[parser.lexer.Token.Identifier]] of the new machine
     *  @param machinespec specification of this machine
     */
    case class Define(machine : parser.lexer.Token.Identifier, machinespec : MachineSpec) extends Statement
    
    /** An abstract class for values corresponding to a class */
    abstract sealed class Class
    /** A [[Class]] coming from an [[parser.lexer.Token.Identifier]] 
     *  @param identifier The [[parser.lexer.Token.Identifier]] for this IdentifiedClass
     */
    case class IdentifiedClass (identifier : parser.lexer.Token.Identifier) extends Class
    
    /** An abstract class for values corresponding to a class */
    abstract sealed class Machine
    /** A [[Machine]] coming from an [[parser.lexer.Token.Identifier]] 
     *  @param identifier The [[parser.lexer.Token.Identifier]] for this IdentifiedClass
     */
    case class IdentifiedMachine (identifier : parser.lexer.Token.Identifier) extends Machine
    
    /** An abstract class for the specification of a [[Machine]]. Implemented using a chain-based system. */
    abstract sealed class MachineSpec
    /** Terminator of a [[MachineSpec]] chain
     *  @param output The [[FlowTerm]] that the [[MachineSpec]] chains terminates with
     */
    case class FinalOutput(ouput : FlowTerm) extends MachineSpec
    /** Part of a [[MachineSpec]] chain with machine-based inference
     *  @param current The [[FlowTerm]] that the MachineSpec chain is currently at
     *  @param next The next [[MachineSpec]] in the chain
     */
    case class MachineTo(current : FlowTerm, next : MachineSpec) extends MachineSpec
    /** Part of a [[MachineSpec]] chain with flow-based inference
     *  @param current The [[FlowTerm]] that the MachineSpec chain is currently at
     *  @param next The next [[MachineSpec]] in the chain
     */
    case class FlowTo(current : FlowTerm, next : MachineSpec) extends MachineSpec
    
    /** A term in a [[MachineSpec]] chain, implemented as a tree of tuples */
    abstract sealed class FlowTerm
    /** A leaf in the [[FlowTerm]] tree
     *  @param leaf The [[Class]] in this leaf node
     */
    case class ClassTerm(leaf : Class) extends FlowTerm
    /** A node in the [[FlowTerm]] tree, with sub-[[FlowTerm]]s
     *  @param nodes The subnodes of this node
     */
    case class TupleTerm(nodes : FlowTerm*) extends FlowTerm
}
