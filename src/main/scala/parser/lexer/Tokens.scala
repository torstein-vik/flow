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
    
}
