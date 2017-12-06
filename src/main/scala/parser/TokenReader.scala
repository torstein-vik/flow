package io.github.torsteinvik.flow.parser

import io.github.torsteinvik.flow.parser.lexer._

import scala.util.parsing.input._

class TokenReader (tokens : Seq[Token]) extends Reader[Token] {
    def atEnd: Boolean = ???
    def first: Token = ???
    def pos: Position = ???
    def rest: TokenReader = ???
}
