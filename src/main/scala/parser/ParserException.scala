package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.input._

case class ParserException(msg : String, pos : Position) extends 
    Exception("Syntax error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
