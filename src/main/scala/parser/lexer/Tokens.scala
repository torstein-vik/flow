package io.github.torsteinvik.flow.parser.lexer

/** A token coming from the lexing of the input code */
abstract sealed class Token () {
    /** Returns the text representation of this Token */
    def chars : String
}

/** Object with all the possible tokens */
object Token {
    /** A [[Token]] whose chars is a fixed string */
    abstract class FixedToken(token : String) extends Token {
        override def chars = token
    }
    
    /** A [[FixedToken]] which is a keyword */
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
    
    /** [[Token]] for single- and multiline-comments, normally removed before further pasing */
    case class Comment(content : String) extends FixedToken("/* " + content + " */")
    
    /** Identifier [[Token]] 
     *  @param name the text value of this identifier
     */
    case class Identifier (val name : String) extends FixedToken(name)
    
    /** Numeric [[Identifier]], meaning where the name is a number (of arbitrary length) */
    trait Numeric extends Identifier {
        
        /** The numeric value of this [[Numeric]] [[Identifier]]*/
        def value : BigInt = BigInt(name) 
    }
    
    /** An object for creating (and pattern matching) [[Numeric]] [[Identifier]]s*/
    object Numeric {
        /** Returns a [[Numeric]] [[Identifier]] given some BigInt*/
        def apply (n : BigInt) : Numeric = new Identifier(n.toString) with Numeric
        /** Returns the underlying BigInt of a [[Numeric]] [[Identifier]]*/
        def unapply (num : Numeric) : BigInt = num.value
    }
    
    /* /** String Literal [[Token]] 
     *  @param content the content of this string literal
     */
    case class StringLiteral (val content : String) extends FixedToken("\"" + content + "\"")*/
    
}
