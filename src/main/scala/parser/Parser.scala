package io.github.torsteinvik.flow.parser

import io.github.torsteinvik.flow._
import io.github.torsteinvik.flow.parser.lexer._

import scala.util.parsing.combinator._

object Parser extends Parsers {
    type Elem = Token
    

    def block : Parser[AST] = success(AST.Block())
    
}