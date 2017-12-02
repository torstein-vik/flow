package io.github.torsteinvik.flow.parser.lexer

/** A token coming from the lexing of the input code */
abstract sealed class Token () {
    /** Returns true if the input string matches this token */
    def accept(str : String) : Boolean
}

/** Object with all the possible tokens */
object Token {
    /** A [[Token]] which only matches a fixed string */
    abstract class FixedToken(token : String) extends Token {
        override def accept (str : String) = token == str
    }
    
    /** A [[Token]] which is a keyword */
    abstract class Keyword(keyword : String) extends FixedToken(keyword) 
    
    /** 'Semantic' [[Keyword]] [[Token]] */
    case object Semantic extends Keyword("Semantic")
    
    /** 'Define' [[Keyword]] [[Token]] */
    case object Define extends Keyword("Define")
    
    /** Colon ':', [[Token]] */
    case object Colon extends FixedToken(":")
    
    /** Semicolon ';' [[Token]] */
    case object Semicolon extends FixedToken(";")
    
    /** Machine '=>' [[Token]] */
    case object Machine extends FixedToken("=>")
    
    /** Flow operation '->' [[Token]] */
    case object Flow extends FixedToken("->")
    
    /** Open tuple '(' [[Token]] */
    case object TupleOpen extends FixedToken("(")
    
    /** Close tuple ')' [[Token]] */
    case object TupleClose extends FixedToken(")")
    
    /** Seperate tuple members ',' [[Token]] */
    case object TupleSeparator extends FixedToken(",")
    
    /** Empty space ' ' [[Token]] */
    case object Space extends FixedToken(" ")
    
    /** New line '\n' [[Token]] */
    case object Newline extends FixedToken("\n")
    
    /** Identifier [[Token]] 
     *  @param name the text value of this identifier
     */
    case class Identifier (val name : String) extends Token {
        
        private val identifier = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet
        override def accept(str : String) = str.forall(identifier.contains(_))
    }
    
    /** Numeric [[Identifier]], meaning where the name is a number (of arbitrary length) */
    trait Numeric extends Identifier {
         
        /** The numeric value of this [[Numeric]] [[Identifier]]*/
        def value : BigInt = BigInt(name) 
    }
}
