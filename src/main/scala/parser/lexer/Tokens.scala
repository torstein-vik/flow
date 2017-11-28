package io.github.torsteinvik.flow.parser.lexer

/** A token coming from the lexing of the input code */
abstract sealed class Token ()

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
    
}
