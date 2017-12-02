package io.github.torsteinvik.flow.parser.lexer

/** A token coming from the lexing of the input code */
abstract sealed class Token () {
    /** Returns true if the input string matches this token */
    def accept(str : String) : Boolean
}

/** Object with all the possible tokens */
object Token {
    /** A [[Token]] which is a keyword */
    trait Keyword extends Token
    
    /** 'Semantic' [[Keyword]] [[Token]] */
    case object Semantic extends Token with Keyword
    
    /** 'Define' [[Keyword]] [[Token]] */
    case object Define extends Token with Keyword
    
    /** Colon ':', [[Token]] */
    case object Colon extends Token
    
    /** Semicolon ';' [[Token]] */
    case object Semicolon extends Token
    
    /** Machine '=>' [[Token]] */
    case object Machine extends Token
    
    /** Flow operation '->' [[Token]] */
    case object Flow extends Token
    
    /** Open tuple '(' [[Token]] */
    case object TupleOpen extends Token
    
    /** Close tuple ')' [[Token]] */
    case object TupleClose extends Token
    
    /** Seperate tuple members ',' [[Token]] */
    case object TupleSeparator extends Token
    
    /** Identifier [[Token]] 
     *  @param name the text value of this identifier
     */
    case class Identifier (val name : String) extends Token
    
    /** Numeric [[Identifier]], meaning where the name is a number (of arbitrary length) */
    trait Numeric extends Identifier {
         
        /** The numeric value of this [[Numeric]] [[Identifier]]*/
        def value : BigInt = BigInt(name) 
    }
}
